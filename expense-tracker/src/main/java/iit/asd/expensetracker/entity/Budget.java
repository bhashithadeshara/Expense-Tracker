package iit.asd.expensetracker.entity;


import iit.asd.expensetracker.util.enums.Month;

import java.util.Random;


public class Budget {

    private int id;
    private  float budget;
    private Category category;
    private Month month;
    private int year;

    public Budget(float budget, Category category, Month month, int year) {
        Random rand = new Random();
        id = rand.nextInt(99999 - 10000 + 1) + 10000;
        this.budget = budget;
        this.category = category;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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


}
