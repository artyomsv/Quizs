package lv.company.expenses.domain.opeartions;

import lv.company.expenses.domain.Person;
import lv.company.expenses.domain.Product;

public class Purchase {

    private final Person person;
    private final Product product;

    public Purchase(Person person, Product product) {
        this.person = person;
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase operation = (Purchase) o;

        if (person != null ? !person.equals(operation.person) : operation.person != null) return false;
        return product != null ? product.equals(operation.product) : operation.product == null;

    }

    @Override
    public int hashCode() {
        int result = person != null ? person.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }


}
