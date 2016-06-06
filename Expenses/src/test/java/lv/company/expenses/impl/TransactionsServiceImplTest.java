package lv.company.expenses.impl;

import com.google.common.collect.Lists;
import lv.company.expenses.api.ExpensesService;
import lv.company.expenses.api.TransactionsService;
import lv.company.expenses.domain.*;
import lv.company.expenses.domain.opeartions.Transaction;
import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Purchase;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionsServiceImplTest {

    @Test
    public void simpleTest() throws Exception {
        ExpensesService service = new ExpensesServiceImpl();

        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");

        ArrayList<Purchase> operations = Lists.newArrayList();
        operations.add(new Purchase(lisa, new Product("Cake", 500L)));
        operations.add(new Purchase(lisa, new Product("Hotel", 12000L)));
        operations.add(new Purchase(lisa, new Product("Museum ticket", 2000L)));
        operations.add(new Purchase(hans, new Product("Museum ticket", 2000L)));
        operations.add(new Purchase(hans, new Product("Museum ticket", 2000L)));
        operations.add(new Purchase(hans, new Product("Diner", 3400L)));
        operations.add(new Purchase(ivan, new Product("Railway ticket", 4800L)));
        operations.add(new Purchase(ivan, new Product("Supper", 3300L)));

        Map<Person, Expense> map = service.execute(operations);

        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<Transaction> result = transactionsService.execute(new ArrayList<Expense>(map.values()));

        assertThat(result.size(), is(2));

        Collections.sort(result, new Comparator<Transaction>() {
            public int compare(Transaction o1, Transaction o2) {
                return o1.getFrom().getName().compareTo(o2.getFrom().getName());
            }
        });

        Iterator<Transaction> iterator = result.iterator();

        Transaction hansTransaction = iterator.next();
        assertThat(hansTransaction.getFrom().getName(), is("Hans"));
        assertThat(hansTransaction.getTo().getName(), is("Lisa"));
        assertThat(hansTransaction.getAmount(), is(2600L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().getName(), is("Ivan"));
        assertThat(ivanTransaction.getTo().getName(), is("Lisa"));
        assertThat(ivanTransaction.getAmount(), is(1900L));

    }

    @Test
    public void toughTest_TwoPersonsExpensesAreEquals() throws Exception {
        ExpensesService service = new ExpensesServiceImpl();

        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        ArrayList<Purchase> operations = Lists.newArrayList();
        operations.add(new Purchase(lisa, new Product("P1", 10L)));
        operations.add(new Purchase(hans, new Product("P2", 10L)));
        operations.add(new Purchase(ivan, new Product("P3", 3L)));
        operations.add(new Purchase(petr, new Product("P4", 1L)));

        Map<Person, Expense> map = service.execute(operations);
        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<Transaction> result = transactionsService.execute(new ArrayList<Expense>(map.values()));

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction.getTo().getName(), is("Hans"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction2.getTo().getName(), is("Lisa"));
        assertThat(petrTransaction2.getAmount(), is(1L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().getName(), is("Ivan"));
        assertThat(ivanTransaction.getTo().getName(), is("Lisa"));
        assertThat(ivanTransaction.getAmount(), is(3L));

    }

    @Test
    public void toughTest_TwoPersonsExpensesAreNear() throws Exception {
        ExpensesService service = new ExpensesServiceImpl();

        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        ArrayList<Purchase> operations = Lists.newArrayList();
        operations.add(new Purchase(lisa, new Product("P1", 10L)));
        operations.add(new Purchase(hans, new Product("P2", 8L)));
        operations.add(new Purchase(ivan, new Product("P3", 5L)));
        operations.add(new Purchase(petr, new Product("P4", 1L)));

        Map<Person, Expense> map = service.execute(operations);
        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<Transaction> result = transactionsService.execute(new ArrayList<Expense>(map.values()));

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction.getTo().getName(), is("Lisa"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction2.getTo().getName(), is("Hans"));
        assertThat(petrTransaction2.getAmount(), is(1L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().getName(), is("Ivan"));
        assertThat(ivanTransaction.getTo().getName(), is("Hans"));
        assertThat(ivanTransaction.getAmount(), is(1L));

    }

    @Test
    public void toughTest_OneOfPersonsExpenseAreSameAsAverage() throws Exception {
        ExpensesService service = new ExpensesServiceImpl();

        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        ArrayList<Purchase> operations = Lists.newArrayList();
        operations.add(new Purchase(lisa, new Product("P1", 10L)));
        operations.add(new Purchase(hans, new Product("P2", 7L)));
        operations.add(new Purchase(ivan, new Product("P3", 6L)));
        operations.add(new Purchase(petr, new Product("P4", 1L)));

        Map<Person, Expense> map = service.execute(operations);
        TransactionsService transactionsService = new TransactionsServiceImpl();
        List<Transaction> result = transactionsService.execute(new ArrayList<Expense>(map.values()));

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction.getTo().getName(), is("Lisa"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().getName(), is("Petr"));
        assertThat(petrTransaction2.getTo().getName(), is("Hans"));
        assertThat(petrTransaction2.getAmount(), is(1L));

    }
}