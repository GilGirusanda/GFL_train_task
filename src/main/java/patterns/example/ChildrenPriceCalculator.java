package patterns.example;

public class ChildrenPriceCalculator implements PriceCalculator{
    @Override
    public double calculateAmount(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
