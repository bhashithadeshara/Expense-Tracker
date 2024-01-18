package iit.asd.expensetracker.service.impl;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.service.BudgetService;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;

public class BudgetServiceImpl implements BudgetService {
    @Override
    public List<Budget> getAll() {
        return DataStore.getInstance().getBudgetList();
    }

    @Override
    public Budget getBudgetById(int id) {
        return DataStore.getInstance().getBudgetList().stream()
                .filter(o -> o.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void create(Budget budget) {
        DataStore.getInstance().getBudgetList().add(budget);
    }

    @Override
    public void update(Budget budget) {
        int index = 0;

        for (Budget b: getAll()) {
            if(b.getId() == budget.getId()) {
                getAll().set(index, budget);
                break;
            }

            index++;
        }
    }

    @Override
    public void delete(int id) {
        List<Budget> budgets = DataStore.getInstance().getBudgetList();
        int index = 0;
        for (Budget budget: budgets) {
            if (budget.getId() == id) {
                budgets.remove(index);
                break;
            }
            index++;
        }
    }
    @Override
    public Budget getBudgetByMonthOfYearAndCategory(Month month, int year,Category category) {
        return  getAll().stream()
                .filter(o -> o.getMonth() == month)
                .filter(o -> o.getYear() == year)
                .filter(o-> o.getCategory() ==  category)
                .findFirst()
                .orElse(null);
    }
}

