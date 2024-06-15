package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.*;
import static demo.constants.Scales.ROUNDING_SCALE;
import static demo.constants.VehiclePrices.*;
import static demo.utils.CalculationUtils.setInitialInsuranceCostPerDay;
import static demo.utils.CalculationUtils.setRentalCostPerDay;

/**
 * The CargoVan class represents a cargo van that can be rented.
 * It extends the Vehicle class and includes additional information specific to cargo vans,
 * such as the driver's experience and insurance discount per day.
 */
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

    /**
     * Calculates the insurance discount per day based on the driver's experience.
     *
     * @param driverExperience the driver's experience in years
     * @return the insurance discount per day
     */
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
