package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.CAR_INSURANCE_PERCENTAGE;
import static demo.constants.InsuranceCostsPercentages.CAR_INSURANCE_PERCENTAGE_WITH_DISCOUNT;
import static demo.constants.Scales.ROUNDING_SCALE;

public class Car extends Vehicle{

    private final int safetyRating;

    public Car(String brand, String model, BigDecimal value, int safetyRating) {
        super(brand, model, value);
        this.safetyRating = safetyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }

    @Override
    public BigDecimal calculateInsuranceCost(int rentalDays) {
        BigDecimal insuranceCost = getValue().multiply(new BigDecimal(CAR_INSURANCE_PERCENTAGE));
        if (safetyRating >=4) {
            insuranceCost = insuranceCost.multiply(new BigDecimal(CAR_INSURANCE_PERCENTAGE_WITH_DISCOUNT));
        }
        return insuranceCost.multiply(new BigDecimal(rentalDays)).setScale(ROUNDING_SCALE, RoundingMode.HALF_UP);
    }
}
