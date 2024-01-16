package iit.asd.expensetracker.entity;

public class BudgetUpdate {
    // The Category
    private String category;
    // The amount
    private String amount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BudgetUpdate(String category, String amount) {
        this.category = category;
        this.amount = amount;
    }
}
