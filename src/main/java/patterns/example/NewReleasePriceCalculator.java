package patterns.example;

public class NewReleasePriceCalculator implements PriceCalculator{
    @Override
    public double calculateAmount(int daysRented) {
        return daysRented * 3;
    }
}
