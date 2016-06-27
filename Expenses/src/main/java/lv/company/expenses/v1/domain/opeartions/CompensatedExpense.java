package lv.company.expenses.v1.domain.opeartions;

public class CompensatedExpense extends Expense {

    private long refunded;
    private boolean fullyRefunded;

    public CompensatedExpense(Expense expense) {
        super(expense.getPerson(), expense.getAmount());
    }

    public long getRefunded() {
        return refunded;
    }

    public void incrementRefunded(long refunded) {
        this.refunded += refunded;
    }

    public boolean isFullyRefunded() {
        return fullyRefunded;
    }

    public void setFullyRefunded(boolean fullyRefunded) {
        this.fullyRefunded = fullyRefunded;
    }
}
