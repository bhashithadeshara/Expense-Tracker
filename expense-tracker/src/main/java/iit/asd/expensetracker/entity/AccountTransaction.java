package iit.asd.expensetracker.entity;

import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.enums.TransactionType;

import java.util.Random;

public abstract class AccountTransaction {
    private int id;
    private String subject;

    private double value;

    private  String description;

    private Category category;

    private boolean isRecurring;

    private Month month;

    private int year;

    public AccountTransaction(String title, double amount, String note, Category category, boolean isRecurring, Month month, int year) {
        Random rand = new Random();
        id = rand.nextInt(99999 - 10000 + 1) + 10000;
        this.subject = title;
        this.value = amount;
        this.description = note;
        this.category = category;
        this.isRecurring = isRecurring;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", isRecurring=" + isRecurring +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public abstract TransactionType getType();
}
