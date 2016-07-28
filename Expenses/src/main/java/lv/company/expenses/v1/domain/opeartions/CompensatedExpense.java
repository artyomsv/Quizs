package lv.company.expenses.v1.domain.opeartions;

import lv.company.expenses.v1.Compensation;

public class CompensatedExpense extends Expense {

    private long refunded;
    private boolean fullyRefunded;

    public CompensatedExpense(Expense expense) {
        super(expense.getPerson(), expense.getAmount());
    }

    public long getRefunded() {
        return refunded;
    }

    public void incrementRefunded(Compensation refunded) {
        this.refunded += refunded.toLong();
    }

    public boolean isFullyRefunded() {
        return fullyRefunded;
    }

    public void setFullyRefunded(boolean fullyRefunded) {
        this.fullyRefunded = fullyRefunded;
    }
}
