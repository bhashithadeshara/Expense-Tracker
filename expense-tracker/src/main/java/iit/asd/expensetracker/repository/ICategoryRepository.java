package iit.asd.expensetracker.repository;

import iit.asd.expensetracker.entity.Category;

import java.util.List;

public interface ICategoryRepository {

    public List<Category> getAllCategories();


    public Category getCategoryById(int id);


    public void createCategory(Category category);


    public void updateCategory(Category category);


    public void deleteCategory(int id);
}
