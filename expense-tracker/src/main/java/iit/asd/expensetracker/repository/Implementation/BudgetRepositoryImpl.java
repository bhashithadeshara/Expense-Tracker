package iit.asd.expensetracker.repository.Implementation;

import iit.asd.expensetracker.entity.Budget;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.repository.IBudgetRepository;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;

public class BudgetRepositoryImpl implements IBudgetRepository {
    @Override
    public List<Budget> getAllBudgets() {
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
    public void createBudget(Budget budget) {
        DataStore.getInstance().getBudgetList().add(budget);
    }


    @Override
    public void updateBudget(Budget budget) {
        int pointerIndex = 0;

        for (Budget b : getAllBudgets()) {
            if (b.getId() == budget.getId()) {
                getAllBudgets().set(pointerIndex, budget);
                break;
            }

            pointerIndex++;
        }
    }


    @Override
    public void deleteBudget(int id) {
        List<Budget> budgets = DataStore.getInstance().getBudgetList();
        int pointerIndex = 0;
        for (Budget budget : budgets) {
            if (budget.getId() == id) {
                budgets.remove(pointerIndex);
                break;
            }
            pointerIndex++;
        }
    }

    @Override
    public Budget getBudgetByDateAndCategory(int year, Month month, Category category) {
        return getAllBudgets().stream()
                .filter(o -> o.getMonth() == month)
                .filter(o -> o.getYear() == year)
                .filter(o -> o.getCategory() == category)
                .findFirst()
                .orElse(null);
    }
}

