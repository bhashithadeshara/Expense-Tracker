package iit.asd.expensetracker.repository.Implementation;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.repository.ITransactionRepository;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepositoryImpl implements ITransactionRepository {
    @Override
    public List<AccountTransaction> getAllTransactionsByMonth(Month month, int year) {
        return getAllTransactions().stream()
                .filter(o -> o.getMonth() == month)
                .filter(o -> o.getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountTransaction> getAllTransactions() {
        return DataStore.getInstance().getTransactionList();
    }

    @Override
    public void createTransaction(AccountTransaction transaction) {
        DataStore.getInstance().getTransactionList().add(transaction);
    }

    @Override
    public void updateTransaction(AccountTransaction transaction) {
        int pointerIndex = 0;
        for (AccountTransaction trx : getAllTransactions()) {
            if (trx.getId() == transaction.getId()) {
                getAllTransactions().set(pointerIndex, transaction);
                break;
            }
            pointerIndex++;
        }
    }

    @Override
    public void deleteTransaction(int transactionId) {
        List<AccountTransaction> transactionList = getAllTransactions();
        int pointerIndex = 0;
        for (AccountTransaction trx : transactionList) {
            if (trx.getId() == transactionId) {
                transactionList.remove(pointerIndex);
                break;
            }
            pointerIndex++;
        }
    }
}
