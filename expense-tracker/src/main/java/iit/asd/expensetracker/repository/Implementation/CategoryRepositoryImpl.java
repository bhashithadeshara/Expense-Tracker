package iit.asd.expensetracker.repository.Implementation;

import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.repository.ICategoryRepository;
import iit.asd.expensetracker.util.singleton.DataStore;

import java.util.List;

public class CategoryRepositoryImpl implements ICategoryRepository {
    @Override
    public List<Category> getAllCategories() {
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
    public void createCategory(Category category) {
        DataStore.getInstance().getCategoryList().add(category);
    }

    @Override
    public void updateCategory(Category category) {
        int pointerIndex = 0;

        for (Category localCategory : getAllCategories()) {
            if (localCategory.getId() == category.getId()) {
                getAllCategories().set(pointerIndex, category);
                break;
            }

            pointerIndex++;
        }
    }

    @Override
    public void deleteCategory(int id) {
        List<Category> categories = DataStore.getInstance().getCategoryList();
        int pointerIndex = 0;
        for (Category category : categories) {
            if (category.getId() == id) {
                categories.remove(pointerIndex);
                break;
            }
            pointerIndex++;
        }
    }

}
