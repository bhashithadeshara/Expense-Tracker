package iit.asd.expensetracker;

import iit.asd.expensetracker.entity.AccountTransaction;
import iit.asd.expensetracker.entity.Category;
import iit.asd.expensetracker.entity.Expenditure;
import iit.asd.expensetracker.entity.Income;
import iit.asd.expensetracker.service.TransactionService;
import iit.asd.expensetracker.service.impl.TransactionServiceImpl;
import iit.asd.expensetracker.util.TransactionFactory;
import iit.asd.expensetracker.util.enums.Month;
import iit.asd.expensetracker.util.enums.TransactionType;
import iit.asd.expensetracker.util.singleton.DataStore;
import iit.asd.expensetracker.util.singleton.MainStage;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    // The status of the update/save
    private boolean isUpdate = false;
    // The selected transaction from table
    private AccountTransaction selectedTransaction;
    // The transaction dao
    private TransactionService transactionDAO;
    // The transaction table view
    @FXML
    private TableView<AccountTransaction> transactionTable;
    // The transaction title
    @FXML
    private TextField trxnTitle;
    // The amount
    @FXML
    private TextField trxnAmount;
    // The type
    @FXML
    private ComboBox<TransactionType> trxnType;
    // The category
    @FXML
    private ComboBox<Category> trxnCategory;
    // The month
    @FXML
    private ComboBox<Month> trxnMonth;
    // The year
    @FXML
    private ComboBox<Integer> trxnYear;
    // The notes
    @FXML
    private TextArea trxnNotes;
    // The save button
    @FXML
    private Button trxnBtnSave;
    // The delete button
    @FXML
    private Button trxnBtnDelete;

    public TransactionController() {
        transactionDAO = new TransactionServiceImpl();
    }

    /**
     * get transaction list through transaction dao
     * @return transaction list
     */
    private List<AccountTransaction> getAll() {
        return transactionDAO.getAll();
    }

    /**
     * create a transaction through transaction dao
     * @param transaction Transaction object
     */
    private void create(AccountTransaction transaction) {
        transactionDAO.create(transaction);
    }

    /**
     * update an existing Transaction through Transaction dao
     * @param transaction Transaction object
     */
    private void update(AccountTransaction transaction) {
        transactionDAO.update(transaction);
    }

    /**
     * delete a Transaction through Transaction dao
     * @param id: Transaction id
     */
    private void delete(int id) {
        transactionDAO.delete(id);
    }

    /**
     * initializae the UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initForm();
        initTableColumns();
        initTableData();
        trxnBtnDelete.setVisible(false);
    }

    /**
     * load Transaction table data
     */
    private void initTableData() {
        transactionTable.setItems(FXCollections.observableArrayList(transactionDAO.getAll()));
        transactionTable.refresh();
    }

    /**
     * initialize the Transaction table
     */
    private void initTableColumns() {
        transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<AccountTransaction, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<AccountTransaction, String> columnTitle = new TableColumn<>("Title");
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<AccountTransaction, Double> columnAmount = new TableColumn<>("Amount");
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<AccountTransaction, String> columnNote = new TableColumn<>("Note");
        columnNote.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn<AccountTransaction, Category> columnCategory = new TableColumn<>("Category");
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<AccountTransaction, Boolean> columnRecurring = new TableColumn<>("Recurring");
        columnRecurring.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isRecurring()));
        columnRecurring.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isRecurring()));
        columnRecurring.setCellFactory(column -> new TableCell<AccountTransaction, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item ? "Yes" : "No");
                }
            }
        });

        TableColumn<AccountTransaction, Month> columnMonth = new TableColumn<>("Month");
        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<AccountTransaction, Integer> columnYear = new TableColumn<>("Year");
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<AccountTransaction, TransactionType> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Expenditure) {
                return new SimpleObjectProperty<>(((Expenditure) cellData.getValue()).getType());
            } else if (cellData.getValue() instanceof Income) {
                return new SimpleObjectProperty<>(((Income) cellData.getValue()).getType());
            }
            return null;
        });
        typeColumn.setCellFactory(column -> new TableCell<AccountTransaction, TransactionType>() {
            @Override
            protected void updateItem(TransactionType item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.name());
                }
            }
        });

        transactionTable.getColumns().addAll(columnId, columnTitle, columnAmount, columnNote, columnCategory,
                columnRecurring, columnMonth, columnYear, typeColumn);
    }

    /**
     * initialize the Transaction form
     */
    private void initForm() {
        trxnType.setItems(FXCollections.observableArrayList(DataStore.getInstance().getTransactionTypeList()));
        trxnType.setCellFactory(new Callback<ListView<TransactionType>, ListCell<TransactionType>>() {
            @Override
            public ListCell<TransactionType> call(ListView<TransactionType> param) {
                return new ListCell<TransactionType>() {
                    @Override
                    protected void updateItem(TransactionType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.name());
                        }
                    }
                };
            }
        });
        trxnType.setConverter(new StringConverter<TransactionType>() {
            @Override
            public String toString(TransactionType category) {
                if (category == null) {
                    return null;
                } else {
                    return category.name();
                }
            }

            @Override
            public TransactionType fromString(String categoryString) {
                return trxnType.getItems().stream().filter(a -> a.name().equals(categoryString)).findFirst().orElse(null);
            }
        });


        trxnCategory.setItems(FXCollections.observableArrayList(DataStore.getInstance().getCategoryList()));
        trxnCategory.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
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
        trxnCategory.setConverter(new StringConverter<Category>() {
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
                return trxnCategory.getItems().stream().filter(a -> a.getName().equals(categoryString)).findFirst().orElse(null);
            }
        });

        trxnMonth.setItems(FXCollections.observableArrayList(DataStore.getInstance().getMonthList()));
        trxnMonth.setCellFactory(new Callback<ListView<Month>, ListCell<Month>>() {
            @Override
            public ListCell<Month> call(ListView<Month> param) {
                return new ListCell<Month>() {
                    @Override
                    protected void updateItem(Month item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.name());
                        }
                    }
                };
            }
        });
        trxnMonth.setConverter(new StringConverter<Month>() {
            @Override
            public String toString(Month month) {
                if (month == null) {
                    return null;
                } else {
                    return month.name();
                }
            }

            @Override
            public Month fromString(String monthString) {
                return trxnMonth.getItems().stream().filter(a -> a.name().equals(monthString)).findFirst().orElse(null);
            }
        });

        trxnYear.setItems(FXCollections.observableArrayList(DataStore.getInstance().getYearList()));
        trxnYear.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
            @Override
            public ListCell<Integer> call(ListView<Integer> param) {
                return new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        });
        trxnYear.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer month) {
                if (month == null) {
                    return null;
                } else {
                    return month.toString();
                }
            }

            @Override
            public Integer fromString(String monthString) {
                return trxnYear.getItems().stream().filter(a -> a.toString().equals(monthString)).findFirst().orElse(null);
            }
        });
    }

    /**
     * load home page when click bask button
     */
    @FXML
    protected void onClickBackBtnTransactions() {
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

    /**
     * save/update a transaction
     */
    @FXML
    protected void onSaveTransaction() {
        if (isMandatoryFieldsCompleted()) {
            if (!isUpdate) {
                this.create(TransactionFactory.createTransaction(
                        trxnType.getValue(),
                        trxnTitle.getText(),
                        Double.parseDouble(trxnAmount.getText()),
                        trxnNotes.getText(),
                        trxnCategory.getValue(),
                        trxnMonth.getValue(),
                        trxnYear.getValue()));
                clearForm();
                initTableData();
            } else {
                selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();

                selectedTransaction.setSubject(trxnTitle.getText());
                selectedTransaction.setValue(Double.parseDouble(trxnAmount.getText()));
                selectedTransaction.setDescription(trxnNotes.getText());
                selectedTransaction.setCategory(trxnCategory.getValue());
                selectedTransaction.setMonth(trxnMonth.getValue());
                selectedTransaction.setYear(trxnYear.getValue());

                this.update(selectedTransaction);

                clearForm();
                initTableData();
            }
        }
    }

    /**
     * validate all the filled are correct or not
     * @return is valid or not
     */
    private boolean isMandatoryFieldsCompleted() {
        if (trxnTitle.getText().isEmpty()
                || trxnAmount.getText().isEmpty()
                || (trxnType.getValue() == null)
                || (trxnCategory.getValue() == null)
                || (trxnYear.getValue() == null)
                || (trxnMonth.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Mandatory fields can't be empty!");
            alert.setContentText("Please enter a valid value to the mandatory fields!");
            alert.showAndWait();
            return false;
        } else if (!trxnAmount.getText().matches("-?\\d+(\\.\\d+)?")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            alert.setHeaderText("Please enter valid Amount");
            alert.showAndWait();
            return false;
        } else
            return true;
    }

    /**
     * delete a Transaction
     */
    @FXML
    protected void onDeleteTransaction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Please confirm");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.delete(selectedTransaction.getId());
            initTableData();
            clearForm();
        }

    }

    /**
     * load Transaction data into the Transaction form when click table row
     */
    @FXML
    protected void onClickSingleRow() {
        selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();

        trxnTitle.setText(selectedTransaction.getSubject());
        trxnAmount.setText(String.valueOf(selectedTransaction.getValue()));
        trxnCategory.setValue(selectedTransaction.getCategory());
        trxnMonth.setValue(selectedTransaction.getMonth());
        trxnYear.setValue(selectedTransaction.getYear());
        trxnNotes.setText(selectedTransaction.getDescription());

        if (selectedTransaction instanceof Expenditure) {
            Expenditure selectedExpense = (Expenditure) selectedTransaction;
            trxnType.setValue(selectedExpense.getType());
            trxnType.setDisable(true);
        } else if (selectedTransaction instanceof Income) {
            Income selectedIncome = (Income) selectedTransaction;
            trxnType.setValue(selectedIncome.getType());
            trxnType.setDisable(true);
        }

        trxnBtnSave.setText("Update");
        trxnBtnDelete.setVisible(true);
        isUpdate = true;
    }

    /**
     * clear the Transaction form
     */
    @FXML
    protected void clearTransactionForm() {
        clearForm();
    }

    /**
     * clear the form
     */
    private void clearForm() {
        trxnTitle.clear();
        trxnAmount.clear();
        trxnType.getItems().clear();
        trxnType.setDisable(false);
        trxnCategory.getItems().clear();
        trxnMonth.getItems().clear();
        trxnYear.getItems().clear();
        trxnNotes.clear();
        initForm();

        trxnBtnSave.setText("Save");
        trxnBtnDelete.setVisible(false);
        isUpdate = false;
    }


}
