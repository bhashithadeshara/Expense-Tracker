package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.util.enums.Month;
import java.util.List;

public interface TransactionServiceDAO {


    public List<AccountTransaction> getAllByMonthOfYear(Month month, int year);

    public List<AccountTransaction> getAll();

    public void create(AccountTransaction transaction);

    public void update(AccountTransaction transaction);

    public void delete(int transactionId);
}
