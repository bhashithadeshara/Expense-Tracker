package iit.asd.expensetracker.service;

import iit.asd.expensetracker.entity.Category;
import java.util.List;

public interface CategoryServiceDAO {

    public List<Category> getAll();

    public Category getCategoryById(int id);


    public void create(Category category);


    public void update(Category category);
    
    public void delete(int Id);
}

