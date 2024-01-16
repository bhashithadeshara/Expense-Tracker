package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface TransactionService {


    List<AccountTransaction> getAllByMonthOfYear(Month month, int year);

    List<AccountTransaction> getAll();

    void create(AccountTransaction transaction);

    void update(AccountTransaction transaction);

    void delete(int transactionId);
}
