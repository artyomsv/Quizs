package lv.company.expenses.v1.domain.opeartions;

import lombok.EqualsAndHashCode;
import lv.company.expenses.v1.domain.Person;

@EqualsAndHashCode
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
    public String toString() {
        return from.name() + " -> " + to.name() + ": " + (amount) + "$";
    }
}
