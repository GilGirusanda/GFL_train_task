package patterns.example;

public class HtmlStatementBuilder implements StatementBuilder {

    private StringBuilder statementBuilder = new StringBuilder();

    @Override
    public StatementBuilder addHeader(String customerName) {
        statementBuilder.append("<h1>Rental Record for ")
                .append(customerName)
                .append("</h1>")
                .append("<ul>");
        return this;
    }

    @Override
    public StatementBuilder addRental(String movieTitle, double amount) {
        statementBuilder.append("<li>")
                .append(movieTitle)
                .append(": ")
                .append(amount)
                .append("</li>");
        return this;
    }

    @Override
    public StatementBuilder addFooter(double totalAmount, int frequentRenterPoints) {
        statementBuilder.append("</ul>")
                .append("<p>Amount owed is ")
                .append(totalAmount)
                .append("</p>")
                .append("<p>You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points")
                .append("</p>");
        return this;
    }

    @Override
    public String build() {
        return statementBuilder.toString();
    }
}
