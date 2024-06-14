package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.MOTORCYCLE_INSURANCE_PERCENTAGE;
import static demo.constants.InsuranceCostsPercentages.MOTORCYCLE_INSURANCE_PERCENTAGE_WITH_INCREASE;
import static demo.constants.Scales.ROUNDING_SCALE;

public class Motorcycle extends Vehicle{
    private final int driverAge;

    public Motorcycle(String brand, String model, BigDecimal value, int age) {
        super(brand, model, value);
        this.driverAge = age;
    }

    public int getDriverAge() {
        return driverAge;
    }

    @Override
    public BigDecimal calculateInsuranceCost(int rentalDays) {
        BigDecimal insuranceCost = getValue().multiply(new BigDecimal(MOTORCYCLE_INSURANCE_PERCENTAGE));

        if (driverAge < 25) {
            insuranceCost = insuranceCost.multiply(new BigDecimal(MOTORCYCLE_INSURANCE_PERCENTAGE_WITH_INCREASE));
        }
        return insuranceCost.multiply(new BigDecimal(rentalDays)).setScale(ROUNDING_SCALE, RoundingMode.HALF_UP);
    }
}
