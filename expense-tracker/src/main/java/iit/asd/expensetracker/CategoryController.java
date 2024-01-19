package iit.asd.expensetracker;

import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.service.CategoryService;
import iit.asd.expensetracker.service.impl.CategoryServiceImpl;
import iit.asd.expensetracker.util.singleton.MainStage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    private boolean isUpdate = false;
    private Category selectedCategory;
    @FXML
    private TextField catName;
    @FXML
    private TextField catAmount;
    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private Button catBtnSave;
    @FXML
    private Button catBtnDelete;
    @FXML
    private Button catBtnClear;
    private CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImpl();
    }

    private List<Category> getAll() {
        return categoryService.getAll();
    }

    private Category getById(int id) {
        return categoryService.getCategoryById(id);
    }

    private void create(Category category) {
        categoryService.create(category);
    }

    private void update(Category category) {
        categoryService.update(category);
    }

    private void delete(int id) {
        categoryService.delete(id);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableColumns();
        initTableData();
        catBtnDelete.setVisible(false);
    }

    private void initTableData() {
        categoryTable.setItems(FXCollections.observableArrayList(this.getAll()));
        categoryTable.refresh();
    }

    private void initTableColumns() {
        TableColumn<Category, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Category, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        categoryTable.getColumns().addAll(columnId, columnName);
    }

    @FXML
    protected void onClickBackBtnCategory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage stage = MainStage.getInstance();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidUpdate(Category category) {
        List<Category> categories = this.getAll();
        return catName.getText() != null &&
                !Objects.equals(catName.getText(), "") &&
                category != null &&
                categories.stream().anyMatch(o -> o.getName().equals(category.getName()));
    }

    private boolean isValidCreate(Category category) {
        List<Category> categories = this.getAll();
        return catName.getText() != null &&
                !Objects.equals(catName.getText(), "") &&
                category != null &&
                categories.stream().noneMatch(o -> o.getName().equals(category.getName()));
    }

    private boolean isValidDelete(int id) {
        return this.getById(id) != null;
    }

    @FXML
    protected void onSaveCategory() {
        try {
            if (!isUpdate) {
                Category category = new Category(catName.getText());
                if(isValidCreate(category)) {
                    this.create(category);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid category");
                    alert.setContentText("Please enter valid category");
                    alert.showAndWait();
                }
            } else {
                selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

                if(isValidUpdate(selectedCategory)) {
                    Category category = selectedCategory;
                    category.setName(catName.getText());
                    this.update(category);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Budget");
                    alert.setContentText("Please enter valid budget");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException ne) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Budget");
            alert.setContentText("Please enter valid budget");
            alert.showAndWait();
        }

        clearForm();
        initTableData();
    }

    private void clearForm() {
        catName.clear();

        catBtnSave.setText("Save");
        catBtnDelete.setVisible(false);
        isUpdate = false;
    }

    @FXML
    protected void onDeleteCategory() {
        if (isValidDelete(selectedCategory.getId())) {
            this.delete(selectedCategory.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid category");
            alert.setContentText("Please select a valid category to proceed");
            alert.showAndWait();
        }

        initTableData();
        clearForm();
    }

    @FXML
    protected void onClickSingleRow() {
        selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        catName.setText(selectedCategory.getName());

        catBtnSave.setText("Update");
        catBtnDelete.setVisible(true);
        isUpdate = true;
    }

    @FXML
    protected void clearCategoryForm() {
        clearForm();
    }
}
