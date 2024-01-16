package iit.asd.expensetracker.entity;

import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.enums.TransactionType;

public class Income extends AccountTransaction {
    public Income(String subject, double value, String description, Category category, boolean isRecurring, Month month, int year) {
        super(subject, value, description, category, isRecurring, month, year);
    }

    public TransactionType getType() {
        if (super.isRecurring()) {
            return TransactionType.RECURRING_INCOME;
        } else {
            return TransactionType.ONETIME_INCOME;
        }
    }
}
