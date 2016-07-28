package lv.company.expenses.v1.service;

import lv.company.expenses.v1.domain.Person;
import lv.company.expenses.v1.domain.opeartions.CompensatedExpense;
import lv.company.expenses.v1.domain.opeartions.Expense;
import lv.company.expenses.v1.domain.opeartions.Purchase;
import lv.company.expenses.v1.domain.opeartions.Transaction;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionsServiceImpl implements TransactionsService {

    public List<Transaction> execute(Collection<Purchase> purchases) {
        List<Expense> expanses = map(purchases);

        if (expanses == null || expanses.isEmpty()) {
            return Collections.emptyList();
        }


        Collections.sort(expanses, getAmountComparator());

        Long personAverageSum = getAverage(expanses);

        int index = findBorderIndex(expanses, personAverageSum);
        if (index == -1) {
            throw new IllegalArgumentException("Failed to determine borer index");
        }

        List<CompensatedExpense> retrievedExpenses = convert(expanses.subList(0, index));
        List<Expense> refundExpenses = expanses.subList(index, expanses.size());

        List<Transaction> transactions = new ArrayList<>();
        for (int i = refundExpenses.size() - 1; i >= 0; i--) {
            Iterator<CompensatedExpense> iterator = retrievedExpenses.iterator();
            Expense refundExpense = refundExpenses.get(i);
            splitAmount(iterator, transactions, refundExpense.getPerson(), personAverageSum, personAverageSum - refundExpense.getAmount());

        }

        return transactions;
    }

    private List<Expense> map(Collection<Purchase> purchases) {
        if (purchases == null || purchases.isEmpty()) {
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

    private Long getAverage(List<Expense> expanses) {
        Long personAverageSum;
        OptionalDouble average = expanses.stream()
                .mapToLong(Expense::getAmount)
                .average();
        if (average.isPresent()) {
            personAverageSum = (long) average.getAsDouble();
        } else {
            throw new IllegalArgumentException("Failed to determine average person sum");
        }
        return personAverageSum;
    }

    private List<CompensatedExpense> convert(Collection<Expense> expenses) {
        return expenses
                .stream()
                .map(CompensatedExpense::new)
                .collect(Collectors.toList());
    }

    private void splitAmount(Iterator<CompensatedExpense> iterator, List<Transaction> transactions, Person from, long personAverageSum, long canCompensate) {
        if (!iterator.hasNext()) {
            return;
        }

        CompensatedExpense to = iterator.next();
        if (to.isFullyRefunded()) {
            splitAmount(iterator, transactions, from, personAverageSum, canCompensate);
        }


        long leftToCover = to.getAmount() - to.getRefunded() - personAverageSum;
        if (leftToCover != 0) {
            Transaction transaction;
            if (leftToCover >= canCompensate) {
                transaction = new Transaction(from, to.getPerson(), canCompensate);
            } else {
                transaction = new Transaction(from, to.getPerson(), leftToCover);
            }

            Long compensated = transaction.getAmount();
            to.incrementRefunded(compensated);
            to.setFullyRefunded((to.getAmount() - to.getRefunded()) == personAverageSum);
            transactions.add(transaction);

            if (canCompensate != compensated) {
                splitAmount(iterator, transactions, from, personAverageSum, canCompensate - compensated);
            }
        }
    }

    private int findBorderIndex(List<Expense> expanses, Long personSum) {
        for (int i = 0; i < expanses.size(); i++) {
            Expense expanse = expanses.get(i);
            if (expanse.getAmount() < personSum) {
                return i;
            }
        }
        return -1;

    }

    private Comparator<Expense> getAmountComparator() {
        return (o1, o2) -> Long.compare(o2.getAmount(), o1.getAmount());
    }

}
