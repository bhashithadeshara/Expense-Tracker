package iit.asd.expensetracker;

import iit.asd.expensetracker.entity.*;
import iit.asd.expensetracker.service.BudgetService;
import iit.asd.expensetracker.service.CategoryService;
import iit.asd.expensetracker.service.TransactionService;
import iit.asd.expensetracker.service.impl.BudgetServiceImpl;
import iit.asd.expensetracker.service.impl.CategoryServiceImpl;
import iit.asd.expensetracker.service.impl.TransactionServiceImpl;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.singleton.DataStore;
import iit.asd.expensetracker.util.singleton.MainStage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    private Budget selectedBudget;
    private boolean isUpdate = false;
    @FXML
    private ComboBox<Category> budgetCategory;
    @FXML
    private ComboBox<Month> month;
    @FXML
    private TextField amountText;
    @FXML
    private Label overallProgress;
    @FXML
    private ComboBox<Integer> year;
    @FXML
    private ComboBox<Integer> yearBudgetProgress;
    @FXML
    private ComboBox<Month> monthBudgetProgress;
    @FXML
    private TableView<Budget> budgetTable;
    @FXML
    private TableView<BudgetUpdate> budgetReportTable;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    private BudgetService budgetService;
    private TransactionService transactionService;
    private CategoryService categoryService;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public BudgetController() {
        budgetService = new BudgetServiceImpl();
        transactionService = new TransactionServiceImpl();
        categoryService = new CategoryServiceImpl();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelete.setVisible(false);
        initForm();
        initTableColumns();
        initTableData();
        initBudgetProgress();
    }

    private void initForm() {
        budgetCategory.setItems(FXCollections.observableArrayList(DataStore.getInstance().getCategoryList()));
        budgetCategory.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        budgetCategory.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category category) {
                if (category == null) {
                    return null;
                } else {
                    return category.getName();
                }
            }

            @Override
            public Category fromString(String categoryString) {
                return budgetCategory.getItems().stream().filter(a -> a.getName().equals(categoryString)).findFirst().orElse(null);
            }
        });

        month.getItems().addAll(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        month.getSelectionModel().select(Month.JANUARY);

        year.getItems().addAll(2021, 2022, 2023, 2024);
        year.setValue(2023);

        monthBudgetProgress.getItems().addAll(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        monthBudgetProgress.getSelectionModel().select(Month.JANUARY);

        yearBudgetProgress.getItems().addAll(2021, 2022, 2023, 2024);
        yearBudgetProgress.setValue(2023);
    }

    private boolean isValidAmount() {

        return amountText.getText() != null &&
                !Objects.equals(amountText.getText(), "") &&
                budgetCategory.getValue() != null;
    }

    private boolean isValidCreate(){
       Budget budget = budgetService.getBudgetByMonthOfYearAndCategory(month.getValue(), year.getValue(),budgetCategory.getValue());

        return budget == null;
    }

    private boolean isValid(int id) {
        return budgetService.getBudgetById(id) != null;
    }

    @FXML
    protected void onSaveBudget() {
        try{
            if (!isUpdate) {
                if(isValidAmount() && isValidCreate()) {
                    Budget budget = new Budget(Float.parseFloat(amountText.getText()), budgetCategory.getValue(), month.getValue(), year.getValue());
                    create(budget);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Budget");
                    alert.setContentText("Please enter valid budget");
                    alert.showAndWait();
                }

            } else {
                if(isValidAmount()) {
                    selectedBudget = budgetTable.getSelectionModel().getSelectedItem();
                    selectedBudget.setBudget(Float.parseFloat(amountText.getText()));
                    selectedBudget.setCategory(budgetCategory.getValue());
                    selectedBudget.setMonth(month.getValue());
                    selectedBudget.setYear(year.getValue());

                   this.update(selectedBudget);
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

    private  void create(Budget budget){
        budgetService.create(budget);
    }

    private void update(Budget budget) {
        budgetService.update(budget);
    }

    private void delete(int id) {
        budgetService.delete(id);
    }

    @FXML
    protected void onDeleteBudget(){
        if(isValid(selectedBudget.getId())) {
            this.delete(selectedBudget.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid budget");
            alert.setContentText("Please select a valid budget to proceed");
            alert.showAndWait();
        }

        initTableData();
        clearForm();
    }

    @FXML
    protected void onClickSingleRow() {
        selectedBudget = budgetTable.getSelectionModel().getSelectedItem();

        Budget selectedTBudget  = budgetTable.getSelectionModel().getSelectedItem();
        amountText.setText(String.valueOf(selectedTBudget.getBudget()));

        month.setValue(selectedTBudget.getMonth());
        year.setValue(selectedTBudget.getYear());
        budgetCategory.setValue(selectedTBudget.getCategory());

        btnSave.setText("Update");
        btnDelete.setVisible(true);
        isUpdate = true;
    }

    private void initTableColumns() {
        budgetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Budget, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Budget, String> columnCategory = new TableColumn<>("Category");
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Budget, Month> columnMonth = new TableColumn<>("Month");
        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<Budget, Integer> columnYear = new TableColumn<>("Year");
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Budget, Double> columnBudget = new TableColumn<>("Budget");
        columnBudget.setCellValueFactory(new PropertyValueFactory<>("budget"));

        budgetTable.getColumns().addAll(columnId,columnCategory, columnBudget, columnMonth, columnYear);
    }

    private void initBudgetProgress(){
        budgetReportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<BudgetUpdate, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<BudgetUpdate, String> columnCategory = new TableColumn<>("Progress");
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("value"));
        budgetReportTable.getColumns().addAll(category,columnCategory);
    }

    private void initTableData() {
        budgetTable.setItems(FXCollections.observableArrayList(budgetService.getAll()));
        budgetTable.refresh();
    }

    @FXML
    protected void onClickBackBtnBudget() {
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

    @FXML
    protected void clearBudgetForm(){
        clearForm();
    }

    private void clearForm() {
        amountText.clear();
        month.getItems().clear();
        year.getItems().clear();
        budgetCategory.getItems().clear();

        initForm();
        btnSave.setText("Save");
        btnDelete.setVisible(false);
        isUpdate = false;
    }

    private double calculatePercentage(double totalBudget, double totalExpenditure) {
        return (totalBudget != 0 && totalExpenditure != 0) ? (totalExpenditure / totalBudget) * 100 : 0;
    }

    private List<BudgetUpdate> calculateCategorizedProgress(List<Category> categories, List<AccountTransaction> transactions, Month m, int y){
        List<BudgetUpdate> report = new ArrayList<>();
        for(Category c : categories){
            float amount = 0;
            for(AccountTransaction t: transactions){

                if(t.getCategory() == c){
                    if(t instanceof Expenditure) {
                        amount += t.getValue();
                    } else {
                        amount -= t.getValue();
                    }
                }
            }
            Budget budget = budgetService.getBudgetByMonthOfYearAndCategory(m,y,c);
            BudgetUpdate p = new BudgetUpdate(c.getName(), df.format((amount/budget.getBudget())*100)+ " % ");
            report.add(p);
        }

        return report;
    }

    @FXML
    protected void showReport(){
        String m = String.valueOf(monthBudgetProgress.getValue());
        int yearValue = Integer.parseInt(String.valueOf(yearBudgetProgress.getValue()));

        List<AccountTransaction> transactions = transactionService.getAllByMonthOfYear(Month.valueOf(m),yearValue);

        double totalExpenditure = 0;
        for (AccountTransaction transaction: transactions) {
            if(transaction instanceof Expenditure) {
                totalExpenditure += transaction.getValue();
            } else {
                totalExpenditure -= transaction.getValue();
            }
        }

        List<Budget> budgets = budgetService.getAll();

        double totalBudget = budgets.stream()
                .filter(o -> o.getMonth() == Month.valueOf(m))
                .filter(o -> o.getYear() == yearValue)
                .mapToDouble(Budget::getBudget)
                .sum();

        double usagePercentage = calculatePercentage(totalBudget, totalExpenditure);

        overallProgress.setText(df.format(usagePercentage) + " % (" + totalExpenditure + " /" + totalBudget + ")");

        List<Category> categories = categoryService.getAll();

        List<BudgetUpdate> report = this.calculateCategorizedProgress(categories, transactions,Month.valueOf(m),yearValue);

        budgetReportTable.setItems(FXCollections.observableArrayList(report));
        budgetReportTable.refresh();
    }
}
