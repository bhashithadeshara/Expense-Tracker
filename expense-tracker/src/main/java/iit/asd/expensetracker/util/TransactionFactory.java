package iit.asd.expensetracker.util;

import com.iit.budgeteyes.models.Category;
import com.iit.budgeteyes.models.Expense;
import com.iit.budgeteyes.models.Income;
import com.iit.budgeteyes.models.Transaction;
import com.iit.budgeteyes.util.enums.Month;
import com.iit.budgeteyes.util.enums.TransactionType;

public class TransactionFactory {
    public static Transaction createTransaction(TransactionType type, String title, double amount, String note, Category category, Month month, int year) {
        switch (type) {
            case ONETIME_EXPENSE:
                return new Expense(title, amount, note, category, false, month, year);
            case RECURRING_EXPENSE:
                return new Expense(title, amount, note, category, true, month, year);
            case ONETIME_INCOME:
                return new Income(title, amount, note, category, false, month, year);
            case RECURRING_INCOME:
                return new Income(title, amount, note, category, true, month, year);
            default:
                return null;
        }
    }
}


