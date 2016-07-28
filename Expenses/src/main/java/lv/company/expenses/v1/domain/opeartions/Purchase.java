package lv.company.expenses.v1.domain.opeartions;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lv.company.expenses.v1.domain.Person;
import lv.company.expenses.v1.domain.Product;

@EqualsAndHashCode
@ToString
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

}
