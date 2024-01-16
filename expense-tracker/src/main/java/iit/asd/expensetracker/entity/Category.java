package iit.asd.expensetracker.entity;

import java.util.Random;

public class Category {
    private int id;
    private String name;

    public Category(String name) {
        Random rand = new Random();
        id = rand.nextInt(99999 - 10000 + 1) + 10000;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
