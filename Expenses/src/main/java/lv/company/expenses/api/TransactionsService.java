package lv.company.expenses.api;

import lv.company.expenses.domain.opeartions.Expense;
import lv.company.expenses.domain.opeartions.Transaction;

import java.util.List;

public interface TransactionsService {

    List<Transaction> execute(List<Expense> expanses);

}
