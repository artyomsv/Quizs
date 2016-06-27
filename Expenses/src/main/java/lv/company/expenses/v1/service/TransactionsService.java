package lv.company.expenses.v1.service;

import lv.company.expenses.v1.domain.opeartions.Purchase;
import lv.company.expenses.v1.domain.opeartions.Transaction;

import java.util.Collection;
import java.util.List;

public interface TransactionsService {

    List<Transaction> execute(Collection<Purchase> purchases);

}
