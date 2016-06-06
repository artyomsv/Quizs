package lv.company.expenses.api;

import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Purchase;
import lv.company.expenses.domain.Person;

import java.util.Collection;
import java.util.Map;

public interface ExpensesService {

    Map<Person, Expense> execute(Collection<Purchase> operations);

}
