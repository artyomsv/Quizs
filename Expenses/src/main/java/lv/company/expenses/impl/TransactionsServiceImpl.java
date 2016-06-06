package lv.company.expenses.impl;

import lv.company.expenses.api.TransactionsService;
import lv.company.expenses.domain.Person;
import lv.company.expenses.domain.opeartions.CompensatedExpense;
import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class TransactionsServiceImpl implements TransactionsService {

    public List<Transaction> execute(List<Expense> expanses) {
        if (expanses == null || expanses.isEmpty()) {
            return Collections.emptyList();
        }


        Collections.sort(expanses, getAmountComparator());

        Long personAverageSum = getAverage(expanses);
        if (personAverageSum == -1L) {
            throw new IllegalArgumentException("Failed to determine average person sum");
        }


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

    private Long getAverage(List<Expense> expanses) {
        Long personAverageSum;
        OptionalDouble average = expanses.stream()
                .mapToLong(Expense::getAmount)
                .average();
        if (average.isPresent()) {
            personAverageSum = (long) average.getAsDouble();
        } else {
            personAverageSum = -1L;
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
        return new Comparator<Expense>() {
            public int compare(Expense o1, Expense o2) {
                return Long.compare(o2.getAmount(), o1.getAmount());
            }
        };
    }

}
