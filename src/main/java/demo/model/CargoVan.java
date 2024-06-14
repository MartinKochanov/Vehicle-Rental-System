package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.CARGO_VAN_INSURANCE_PERCENTAGE;
import static demo.constants.InsuranceCostsPercentages.CARGO_VAN_INSURANCE_PERCENTAGE_WITH_DISCOUNT;
import static demo.constants.Scales.ROUNDING_SCALE;

public class CargoVan extends Vehicle {

    private final int driverExperience;

    public CargoVan(String brand, String model, BigDecimal value, int driverExperience) {
        super(brand, model, value);
        this.driverExperience = driverExperience;
    }

    @Override
    public BigDecimal calculateInsuranceCost(int rentalDays) {
        BigDecimal insuranceCost = getValue().multiply(new BigDecimal(CARGO_VAN_INSURANCE_PERCENTAGE));

        if (driverExperience > 5) {
            insuranceCost = insuranceCost.multiply(new BigDecimal(CARGO_VAN_INSURANCE_PERCENTAGE_WITH_DISCOUNT));
        }
        return insuranceCost.multiply(new BigDecimal(rentalDays)).setScale(ROUNDING_SCALE, RoundingMode.HALF_UP);
    }
}
