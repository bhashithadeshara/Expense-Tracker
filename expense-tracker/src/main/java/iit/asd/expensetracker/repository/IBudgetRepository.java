package iit.asd.expensetracker.repository;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface IBudgetRepository {

    public List<Budget> getAllBudgets();

    public Budget getBudgetById(int id);

    public void createBudget(Budget budget);

    public void updateBudget(Budget budget);

    public void deleteBudget(int id);

    public Budget getBudgetByDateAndCategory(int year, Month month, Category category);

}
