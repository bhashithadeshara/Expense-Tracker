package iit.asd.expensetracker.util;


import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.entity.Expenditure;
import iit.asd.expensetracker.entity.Income;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.enums.TransactionType;

public class TransactionFactory {
    public static AccountTransaction createTransaction(TransactionType type, String title, double amount, String note, Category category, Month month, int year) {
        switch (type) {
            case ONETIME_EXPENSE:
                return new Expenditure(title, amount, note, category, false, month, year);
            case RECURRING_EXPENSE:
                return new Expenditure(title, amount, note, category, true, month, year);
            case ONETIME_INCOME:
                return new Income(title, amount, note, category, false, month, year);
            case RECURRING_INCOME:
                return new Income(title, amount, note, category, true, month, year);
            default:
                return null;
        }
    }
}


