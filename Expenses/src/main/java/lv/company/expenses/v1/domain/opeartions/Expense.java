package lv.company.expenses.v1.domain.opeartions;

import lombok.EqualsAndHashCode;
import lv.company.expenses.v1.LongNumber;
import lv.company.expenses.v1.domain.Person;

@EqualsAndHashCode
public class Expense implements LongNumber {

    private final Person person;
    private final long amount;

    public Expense(Person person, long amount) {
        this.person = person;
        this.amount = amount;
    }

    public Expense changeAmount(Long amount) {
        return new Expense(person, this.amount + amount);
    }

    public Person getPerson() {
        return person;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public long toLong() {
        return amount;
    }
}
