package patterns.example;

public interface StatementBuilder {
    StatementBuilder addHeader(String customerName);
    StatementBuilder addRental(String movieTitle, double amount);
    StatementBuilder addFooter(double totalAmount, int frequentRenterPoints);
    String build();
}