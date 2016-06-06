package lv.company.expenses.impl;

import lv.company.expenses.api.ExpensesService;
import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Purchase;
import lv.company.expenses.domain.Person;

import java.util.*;

public class ExpensesServiceImpl implements ExpensesService {

    public Map<Person, Expense> execute(Collection<Purchase> operations) {
        if (operations == null || operations.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Person, Expense> expanseMap = new HashMap<Person, Expense>();
        for (Purchase operation : operations) {
            Expense expanse = expanseMap.get(operation.getPerson());
            if (expanse != null) {
                expanseMap.put(operation.getPerson(), expanse.changeAmount(operation.getProduct().getPrice()));
            } else {
                expanseMap.put(operation.getPerson(), new Expense(operation.getPerson(), operation.getProduct().getPrice()));
            }
        }

        return expanseMap;
    }
}
