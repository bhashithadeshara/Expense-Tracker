package iit.asd.expensetracker.repository;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface ITransactionRepository {

    public List<AccountTransaction> getAllTransactionsByMonth(Month month, int year);

    public List<AccountTransaction> getAllTransactions();

    public void createTransaction(AccountTransaction transaction);


    public void updateTransaction(AccountTransaction transaction);


    public void deleteTransaction(int transactionId);
}
