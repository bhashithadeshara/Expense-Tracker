package iit.asd.expensetracker.service.impl;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.service.TransactionService;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<AccountTransaction> getAllByMonthOfYear(Month month, int year) {
        return getAll().stream()
                .filter(o -> o.getMonth() == month)
                .filter(o -> o.getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountTransaction> getAll() {
        return DataStore.getInstance().getTransactionList();
    }

    @Override
    public void create(AccountTransaction transaction) {
        DataStore.getInstance().getTransactionList().add(transaction);
    }

    @Override
    public void update(AccountTransaction transaction) {
        int index = 0;
        for (AccountTransaction trxn: getAll()) {
            if (trxn.getId() == transaction.getId()) {
                getAll().set(index, transaction);
                break;
            }
            index++;
        }
    }

    @Override
    public void delete(int transactionId) {
        List<AccountTransaction> transactionList = getAll();
        int index = 0;
        for (AccountTransaction trxn: transactionList) {
            if (trxn.getId() == transactionId) {
                transactionList.remove(index);
                break;
            }
            index++;
        }
    }
}
