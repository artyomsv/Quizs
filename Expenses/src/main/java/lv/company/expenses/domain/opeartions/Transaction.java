package lv.company.expenses.domain.opeartions;

import lv.company.expenses.domain.Person;

public class Transaction {

    private final Person from;
    private final Person to;
    private final Long amount;

    public Transaction(Person from, Person to, Long amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Person getFrom() {
        return from;
    }

    public Person getTo() {
        return to;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return from.getName() + " -> " + to.getName() + ": " + (amount) + "$";
    }
}
