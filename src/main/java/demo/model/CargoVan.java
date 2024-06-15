package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.*;
import static demo.constants.Scales.ROUNDING_SCALE;
import static demo.constants.VehiclePrices.*;
import static demo.utils.CalculationUtils.setInitialInsuranceCostPerDay;
import static demo.utils.CalculationUtils.setRentalCostPerDay;

public class CargoVan extends Vehicle {

    private final int driverExperience;

    private final BigDecimal insuranceDiscountPerDay;

    public CargoVan(String brand, String model, BigDecimal value, int rentalPeriod, int driverExperience) {
        super(brand,
                model,
                value,
                setRentalCostPerDay(rentalPeriod,
                        CARGO_VAN_DAILY_RENTAL_COST_RENTED_FOR_LESS_THAN_WEEK,
                        CARGO_VAN_DAILY_RENTAL_COST_RENTED_FOR_MORE_THAN_WEEK),
                setInitialInsuranceCostPerDay(value, CARGO_VAN_INSURANCE_PERCENTAGE),
                rentalPeriod
        );
        this.driverExperience = driverExperience;
        this.insuranceDiscountPerDay = setInsuranceDiscountPerDay(driverExperience);
    }

    private BigDecimal setInsuranceDiscountPerDay(int driverExperience) {
        return driverExperience > 5
                ? getInitialInsuranceCostPerDay().multiply(CARGO_VAN_INSURANCE_PERCENTAGE_WITH_DISCOUNT).setScale(ROUNDING_SCALE, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
    }

    public int getDriverExperience() {
        return driverExperience;
    }

    public BigDecimal getInsuranceDiscountPerDay() {
        return insuranceDiscountPerDay;
    }
}
