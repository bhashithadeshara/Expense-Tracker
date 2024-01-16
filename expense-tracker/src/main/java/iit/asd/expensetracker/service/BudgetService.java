package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface BudgetService {

    public List<Budget> getAll();

    public Budget getBudgetById(int id);

    public void create(Budget budget);

    public void update(Budget budget);

    public void delete(int id);

    public Budget getBudgetByMonthOfYearAndCategory(Month month, int year, Category category);

}
