package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getCategoryById(int id);

    void create(Category category);

    void update(Category category);

    void delete(int Id);
}

