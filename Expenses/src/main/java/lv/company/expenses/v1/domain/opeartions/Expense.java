package lv.company.expenses.v1.domain.opeartions;

import lv.company.expenses.v1.domain.Person;

public class Expense {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expanse = (Expense) o;

        if (amount != expanse.amount) return false;
        return person != null ? person.equals(expanse.person) : expanse.person == null;

    }

    @Override
    public int hashCode() {
        int result = person != null ? person.hashCode() : 0;
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }
}
