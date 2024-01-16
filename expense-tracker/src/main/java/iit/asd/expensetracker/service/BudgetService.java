package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.util.enums.Month;

import java.util.List;

public interface BudgetService {

    List<Budget> getAll();

    Budget getBudgetById(int id);

    void create(Budget budget);

    void update(Budget budget);

    void delete(int id);

    Budget getBudgetByMonthOfYearAndCategory(Month month, int year, Category category);

}
