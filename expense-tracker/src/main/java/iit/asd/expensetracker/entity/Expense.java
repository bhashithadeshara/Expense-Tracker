package iit.asd.expensetracker.entity;

import iit.asd.expensetracker.util.enums.TransactionType;
import iit.asd.expensetracker.util.enums.Month;

public class Expense extends AccountTransaction {
    public Expense(String subject, double value, String description, Category category, boolean isRecurring, Month month, int year) {
        super(subject, value, description, category, isRecurring, month, year);
    }

    public TransactionType getType() {
        if (super.isRecurring()) {
            return TransactionType.RECURRING_EXPENSE;
        } else {
            return TransactionType.ONETIME_EXPENSE;
        }
    }
}
