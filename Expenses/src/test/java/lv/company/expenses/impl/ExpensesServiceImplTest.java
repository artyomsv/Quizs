package lv.company.expenses.impl;

import com.google.common.collect.Lists;
import lv.company.expenses.api.ExpensesService;
import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Purchase;
import lv.company.expenses.domain.Person;
import lv.company.expenses.domain.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpensesServiceImplTest {

    @Test
    public void testExpansesCreator() throws Exception {
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
        assertThat(map.size(), is(3));

        Expense lisaExpanse = map.get(lisa);
        assertThat(lisaExpanse.getPerson().getName(), is("Lisa"));
        assertThat(lisaExpanse.getAmount(), is(14500L));

        Expense hansExpanse = map.get(hans);
        assertThat(hansExpanse.getPerson().getName(), is("Hans"));
        assertThat(hansExpanse.getAmount(), is(7400L));

        Expense ivanExpanse = map.get(ivan);
        assertThat(ivanExpanse.getPerson().getName(), is("Ivan"));
        assertThat(ivanExpanse.getAmount(), is(8100L));


    }
}