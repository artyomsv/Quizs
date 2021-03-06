package lv.company.expenses.v1.service;

import com.google.common.collect.Lists;
import lv.company.expenses.v1.Purchases;
import lv.company.expenses.v1.domain.Person;
import lv.company.expenses.v1.domain.Product;
import lv.company.expenses.v1.domain.opeartions.Purchase;
import lv.company.expenses.v1.domain.opeartions.Transaction;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionsServiceImplTest {

    @Test
    public void simpleTest() throws Exception {
        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");

        List<Purchase> purchases = Lists.newArrayList();
        purchases.add(new Purchase(lisa, new Product("Cake", 500L)));
        purchases.add(new Purchase(lisa, new Product("Hotel", 12000L)));
        purchases.add(new Purchase(lisa, new Product("Museum ticket", 2000L)));
        purchases.add(new Purchase(hans, new Product("Museum ticket", 2000L)));
        purchases.add(new Purchase(hans, new Product("Museum ticket", 2000L)));
        purchases.add(new Purchase(hans, new Product("Diner", 3400L)));
        purchases.add(new Purchase(ivan, new Product("Railway ticket", 4800L)));
        purchases.add(new Purchase(ivan, new Product("Supper", 3300L)));

        List<Transaction> result = new Purchases(purchases).transactions();

        assertThat(result.size(), is(2));

        Collections.sort(result, (o1, o2) -> o1.getFrom().name().compareTo(o2.getFrom().name()));

        Iterator<Transaction> iterator = result.iterator();

        Transaction hansTransaction = iterator.next();
        assertThat(hansTransaction.getFrom().name(), is("Hans"));
        assertThat(hansTransaction.getTo().name(), is("Lisa"));
        assertThat(hansTransaction.getAmount(), is(2600L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().name(), is("Ivan"));
        assertThat(ivanTransaction.getTo().name(), is("Lisa"));
        assertThat(ivanTransaction.getAmount(), is(1900L));

    }

    @Test
    public void toughTest_TwoPersonsExpensesAreEquals() throws Exception {
        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        List<Purchase> purchases = Lists.newArrayList();
        purchases.add(new Purchase(lisa, new Product("P1", 10L)));
        purchases.add(new Purchase(hans, new Product("P2", 10L)));
        purchases.add(new Purchase(ivan, new Product("P3", 3L)));
        purchases.add(new Purchase(petr, new Product("P4", 1L)));

        List<Transaction> result = new Purchases(purchases).transactions();

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().name(), is("Petr"));
        assertThat(petrTransaction.getTo().name(), is("Lisa"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().name(), is("Petr"));
        assertThat(petrTransaction2.getTo().name(), is("Hans"));
        assertThat(petrTransaction2.getAmount(), is(1L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().name(), is("Ivan"));
        assertThat(ivanTransaction.getTo().name(), is("Hans"));
        assertThat(ivanTransaction.getAmount(), is(3L));

    }

    @Test
    public void toughTest_TwoPersonsExpensesAreNear() throws Exception {
        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        List<Purchase> purchases = Lists.newArrayList();
        purchases.add(new Purchase(lisa, new Product("P1", 10L)));
        purchases.add(new Purchase(hans, new Product("P2", 8L)));
        purchases.add(new Purchase(ivan, new Product("P3", 5L)));
        purchases.add(new Purchase(petr, new Product("P4", 1L)));

        List<Transaction> result = new Purchases(purchases).transactions();

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().name(), is("Petr"));
        assertThat(petrTransaction.getTo().name(), is("Lisa"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().name(), is("Petr"));
        assertThat(petrTransaction2.getTo().name(), is("Hans"));
        assertThat(petrTransaction2.getAmount(), is(1L));

        Transaction ivanTransaction = iterator.next();
        assertThat(ivanTransaction.getFrom().name(), is("Ivan"));
        assertThat(ivanTransaction.getTo().name(), is("Hans"));
        assertThat(ivanTransaction.getAmount(), is(1L));

    }

    @Test
    public void toughTest_OneOfPersonsExpenseAreSameAsAverage() throws Exception {
        Person lisa = new Person("Lisa");
        Person hans = new Person("Hans");
        Person ivan = new Person("Ivan");
        Person petr = new Person("Petr");

        List<Purchase> purchases = Lists.newArrayList();
        purchases.add(new Purchase(lisa, new Product("P1", 10L)));
        purchases.add(new Purchase(hans, new Product("P2", 7L)));
        purchases.add(new Purchase(ivan, new Product("P3", 6L)));
        purchases.add(new Purchase(petr, new Product("P4", 1L)));

        List<Transaction> result = new Purchases(purchases).transactions();

        Iterator<Transaction> iterator = result.iterator();

        Transaction petrTransaction = iterator.next();
        assertThat(petrTransaction.getFrom().name(), is("Petr"));
        assertThat(petrTransaction.getTo().name(), is("Lisa"));
        assertThat(petrTransaction.getAmount(), is(4L));

        Transaction petrTransaction2 = iterator.next();
        assertThat(petrTransaction2.getFrom().name(), is("Petr"));
        assertThat(petrTransaction2.getTo().name(), is("Hans"));
        assertThat(petrTransaction2.getAmount(), is(1L));

    }
}