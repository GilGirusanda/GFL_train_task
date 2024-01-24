package patterns.example;

public class TextStatementBuilder implements StatementBuilder {

    private StringBuilder statementBuilder = new StringBuilder();

    @Override
    public StatementBuilder addHeader(String customerName) {
        statementBuilder.append("Rental Record for ")
                        .append(customerName)
                        .append("\n");
        return this;
    }

    @Override
    public StatementBuilder addRental(String movieTitle, double amount) {
        statementBuilder.append("\t")
                .append(movieTitle)
                .append("\t")
                .append(amount)
                .append("\n");
        return this;
    }

    @Override
    public StatementBuilder addFooter(double totalAmount, int frequentRenterPoints) {
        statementBuilder.append("Amount owed is ")
                        .append(totalAmount)
                        .append("\n")
                        .append("You earned ")
                        .append(frequentRenterPoints)
                        .append(" frequent renter points");
        return this;
    }

    @Override
    public String build() {
        return statementBuilder.toString();
    }
}
