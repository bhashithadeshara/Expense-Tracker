package iit.asd.expensetracker.entity;

public class BudgetUpdate {

    private String category;

    private String value;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BudgetUpdate(String category, String amount) {
        this.category = category;
        this.value = amount;
    }
}
