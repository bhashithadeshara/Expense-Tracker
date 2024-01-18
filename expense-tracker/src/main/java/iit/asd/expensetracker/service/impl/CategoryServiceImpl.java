package iit.asd.expensetracker.service.impl;

import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.service.CategoryService;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAll() {
        return DataStore.getInstance().getCategoryList();
    }

    @Override
    public Category getCategoryById(int id) {
        return DataStore.getInstance().getCategoryList().stream()
                .filter(o -> o.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void create(Category category) {
        DataStore.getInstance().getCategoryList().add(category);
    }


    @Override
    public void update(Category category) {
        int index = 0;

        for (Category localCategory: getAll()) {
            if(localCategory.getId() == category.getId()) {
                getAll().set(index, category);
                break;
            }

            index++;
        }
    }

    @Override
    public void delete(int id) {
        List<Category> categories = DataStore.getInstance().getCategoryList();
        int index = 0;
        for (Category category: categories) {
            if (category.getId() == id) {
                categories.remove(index);
                break;
            }
            index++;
        }
    }

}
