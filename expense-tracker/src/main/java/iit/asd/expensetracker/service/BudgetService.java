package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface BudgetService {
    /**
     * This method return all Budget list in the DataStore
     * @return Budget list
     */
    public List<Budget> getAll();

    /**
     * This method return a Budget related to a id from the DataStore
     * @param id : Budget Id
     * @return Budget Object
     */
    public Budget getBudgetById(int id);

    /**
     * This method add a Budget objet into the DataStore
     * @param budget: Budget Object
     */
    public void create(Budget budget);

    /**
     * This method update an existing Budget objet in the DataStore
     * @param budget: Budget objet
     */
    public void update(Budget budget);

    /**
     * This method delete an existing Budget objet in the DataStore
     * @param id: Budget Id
     */
    public void delete(int id);

    /**
     * This method return a Budget list from the DataStore based on some parameters
     * @param month: The Month
     * @param year: The year
     * @param category: The Category
     * @return Budget List
     */
    public Budget getBudgetByMonthOfYearAndCategory(Month month, int year, Category category);

}
