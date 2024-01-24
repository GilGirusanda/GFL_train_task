package patterns.example;

import java.util.ArrayList;
import java.util.List;

public class CustomerRegistry {
    private final List<Customer> customers;

    public CustomerRegistry() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void generateStatements(StatementBuilder builder) {
        for (Customer customer : customers) {
            String statement = customer.statement(builder);
            System.out.println("\n"+statement);
        }
    }
}