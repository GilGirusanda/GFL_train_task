package patterns.example;

import java.util.List;

import static patterns.example.Movie.MovieType.NEW_RELEASE;

@SuppressWarnings("StringConcatenationInLoop")
public class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return statement(new TextStatementBuilder());
    }

    public String statement(StatementBuilder stmtBuilder) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        // HEADER
        stmtBuilder.addHeader(getName());
        for (Rental each : rentals) {
            PriceCalculator priceCalculator = getPriceCalculator(each.getMovie().getPriceCode());
            //determine amounts for each line
            double thisAmount = priceCalculator.calculateAmount(each.getDaysRented());
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a 2-day new release rental
            if ((each.getMovie().getPriceCode() == NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            stmtBuilder.addRental(each.getMovie().getTitle(), thisAmount);
            totalAmount += thisAmount;
        }
        // FOOTER
        return stmtBuilder.addFooter(totalAmount, frequentRenterPoints).build();
    }

    private PriceCalculator getPriceCalculator(Movie.MovieType movieType) {
        return switch (movieType) {
            case REGULAR -> new RegularPriceCalculator();
            case NEW_RELEASE -> new NewReleasePriceCalculator();
            case CHILDREN -> new ChildrenPriceCalculator();
            default -> throw new IllegalArgumentException("Invalid MovieType");
        };
    }

}