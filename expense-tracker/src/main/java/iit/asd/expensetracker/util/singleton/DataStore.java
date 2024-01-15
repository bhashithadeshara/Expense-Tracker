package iit.asd.expensetracker.util.singleton;

import com.iit.budgeteyes.models.*;
import com.iit.budgeteyes.util.TransactionFactory;
import com.iit.budgeteyes.util.enums.Month;
import com.iit.budgeteyes.util.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    // The DataStore
    private static DataStore dataStore = null;
    // The Transaction list
    private  List<Transaction> transactionList;
    // The category list
    private  List<Category> categoryList;
    // The monthe list
    private  List<Month> monthList;
    // The Budget list
    private List<Budget> budgetList;
    // The Transaction list
    private List<TransactionType> transactionTypeList;
    // The year list
    private List<Integer> yearList;

    private DataStore() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Travel"));
        categoryList.add(new Category("Food"));
        categoryList.add(new Category("Bills"));

        budgetList = new ArrayList<Budget>();
        budgetList.add(new Budget(20000, categoryList.get(0), Month.APRIL,2023));
        budgetList.add(new Budget(20000, categoryList.get(1), Month.APRIL,2023));
        budgetList.add(new Budget(30000, categoryList.get(2), Month.APRIL,2023));

        transactionList = new ArrayList<>();
        transactionList.add(TransactionFactory.createTransaction(TransactionType.ONETIME_EXPENSE, "Lunch Outing", 2000.00, "Expense", categoryList.get(0), Month.APRIL, 2023));
        transactionList.add(TransactionFactory.createTransaction(TransactionType.ONETIME_INCOME, "Bonus", 50000.00, "Income", categoryList.get(1), Month.APRIL, 2023));
        transactionList.add(TransactionFactory.createTransaction(TransactionType.ONETIME_EXPENSE, "KFC", 50000.00, "Expense", categoryList.get(1),  Month.APRIL, 2023));
        transactionList.add(TransactionFactory.createTransaction(TransactionType.ONETIME_EXPENSE, "Water Bill", 2000.00, "Expense", categoryList.get(2), Month.APRIL, 2023));

        monthList = new ArrayList<>();
        monthList.add(Month.JANUARY);
        monthList.add(Month.FEBRUARY);
        monthList.add(Month.MARCH);
        monthList.add(Month.APRIL);
        monthList.add(Month.MAY);
        monthList.add(Month.JUNE);
        monthList.add(Month.JULY);
        monthList.add(Month.AUGUST);
        monthList.add(Month.SEPTEMBER);
        monthList.add(Month.OCTOBER);
        monthList.add(Month.NOVEMBER);
        monthList.add(Month.DECEMBER);

        transactionTypeList = new ArrayList<>();
        transactionTypeList.add(TransactionType.ONETIME_EXPENSE);
        transactionTypeList.add(TransactionType.ONETIME_INCOME);
        transactionTypeList.add(TransactionType.RECURRING_EXPENSE);
        transactionTypeList.add(TransactionType.RECURRING_INCOME);

        yearList = new ArrayList<>();
        yearList.add(2021);
        yearList.add(2022);
        yearList.add(2023);
        yearList.add(2024);
    }

    public static DataStore getInstance() {
        if (dataStore == null)
            dataStore = new DataStore();
        return dataStore;
    }

    public List<Transaction> getTransactionList() {
        return dataStore.transactionList;
    }

    public List<Category> getCategoryList() {
        return dataStore.categoryList;
    }

    public List<Budget> getBudgetList() {
        return dataStore.budgetList;
    }

    public List<Month> getMonthList() {
        return monthList;
    }

    public List<TransactionType> getTransactionTypeList() {
        return transactionTypeList;
    }

    public List<Integer> getYearList() {
        return yearList;
    }
}
