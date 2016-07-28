package lv.company.expenses.v1;

import lv.company.expenses.v1.average.Average;
import lv.company.expenses.v1.average.AverageValue;
import lv.company.expenses.v1.average.CachedAverage;
import lv.company.expenses.v1.domain.Person;
import lv.company.expenses.v1.domain.opeartions.CompensatedExpense;
import lv.company.expenses.v1.domain.opeartions.Expense;
import lv.company.expenses.v1.domain.opeartions.Purchase;
import lv.company.expenses.v1.domain.opeartions.Transaction;
import lv.company.expenses.v1.index.BorderIndex;
import lv.company.expenses.v1.index.CachedIndex;
import lv.company.expenses.v1.index.Index;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Purchases {

    private final Collection<Purchase> purchases;

    public Purchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Transaction> transactions() {
        List<Expense> expanses = map(purchases);

        if (expanses.isEmpty()) {
            return Collections.emptyList();
        }

        Collections.sort(expanses, getAmountComparator());

        Average average = new CachedAverage(new AverageValue(expanses));
        Index index = new CachedIndex(new BorderIndex(expanses, average.average()));

        List<CompensatedExpense> retrievedExpenses = convert(expanses.subList(0, index.index()));
        List<Expense> refundExpenses = expanses.subList(index.index(), expanses.size());

        List<Transaction> transactions = new ArrayList<>();
        for (int i = refundExpenses.size() - 1; i >= 0; i--) {
            Iterator<CompensatedExpense> iterator = retrievedExpenses.iterator();
            Expense refundExpense = refundExpenses.get(i);
            splitAmount(iterator, transactions, refundExpense.getPerson(), average, new Compensation(average.average() - refundExpense.getAmount()));

        }

        return transactions;
    }

    private List<Expense> map(Collection<Purchase> purchases) {
        if (purchases.isEmpty()) {
            return Collections.emptyList();
        }

        Function<Purchase, Person> function = Purchase::getPerson;
        Function<Purchase, Expense> valueMapper = purchase -> new Expense(purchase.getPerson(), purchase.getProduct().getPrice());
        BinaryOperator<Expense> binaryOperator = (expense, expense2) -> expense.changeAmount(expense2.getAmount());

        return purchases
                .stream()
                .collect(Collectors.toMap(function, valueMapper, binaryOperator))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    private List<CompensatedExpense> convert(Collection<Expense> expenses) {
        return expenses
                .stream()
                .map(CompensatedExpense::new)
                .collect(Collectors.toList());
    }

    private void splitAmount(Iterator<CompensatedExpense> iterator,
                             List<Transaction> transactions,
                             Person from,
                             Average average,
                             Compensation shouldCompensate) {

        if (!iterator.hasNext()) {
            return;
        }

        CompensatedExpense to = iterator.next();
        if (to.isFullyRefunded()) {
            splitAmount(iterator, transactions, from, average, shouldCompensate);
        }


        long leftToCover = to.getAmount() - to.getRefunded() - average.average();
        if (leftToCover != 0) {
            Transaction transaction;
            if (leftToCover >= shouldCompensate.toLong()) {
                transaction = new Transaction(from, to.getPerson(), shouldCompensate.toLong());
            } else {
                transaction = new Transaction(from, to.getPerson(), leftToCover);
            }

            Compensation compensated = new Compensation(transaction.getAmount());
            to.incrementRefunded(compensated);
            to.setFullyRefunded((to.getAmount() - to.getRefunded()) == average.average());
            transactions.add(transaction);

            if (!shouldCompensate.equals(compensated)) {
                splitAmount(iterator, transactions, from, average, shouldCompensate.reduce(compensated));
            }
        }
    }

    private Comparator<Expense> getAmountComparator() {
        return (o1, o2) -> Long.compare(o2.getAmount(), o1.getAmount());
    }

}
