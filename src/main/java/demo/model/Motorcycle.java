package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.*;
import static demo.constants.Scales.ROUNDING_SCALE;
import static demo.constants.VehiclePrices.MOTORCYCLE_DAILY_RENTAL_COST_RENTED_FOR_LESS_THAN_WEEK;
import static demo.constants.VehiclePrices.MOTORCYCLE_DAILY_RENTAL_COST_RENTED_FOR_MORE_THAN_WEEK;
import static demo.utils.CalculationUtils.setInitialInsuranceCostPerDay;
import static demo.utils.CalculationUtils.setRentalCostPerDay;

public class Motorcycle extends Vehicle {
    private final int riderAge;

    private final BigDecimal insuranceAdditionalCostPerDay;


    public Motorcycle(String brand, String model, BigDecimal value, int rentalPeriod, int riderAge) {
        super(brand,
                model,
                value,
                setRentalCostPerDay(rentalPeriod,
                        MOTORCYCLE_DAILY_RENTAL_COST_RENTED_FOR_LESS_THAN_WEEK,
                        MOTORCYCLE_DAILY_RENTAL_COST_RENTED_FOR_MORE_THAN_WEEK),
                setInitialInsuranceCostPerDay(value, MOTORCYCLE_INSURANCE_PERCENTAGE)
                , rentalPeriod);
        this.riderAge = riderAge;
        this.insuranceAdditionalCostPerDay = setInsuranceAdditionsPerDay(riderAge);
    }

    public int getRiderAge() {
        return riderAge;
    }

    public BigDecimal getInsuranceAdditionalCostPerDay() {
        return insuranceAdditionalCostPerDay;
    }

    private BigDecimal setInsuranceAdditionsPerDay(int riderAge) {
        return riderAge < 25
                ? getInitialInsuranceCostPerDay().multiply(MOTORCYCLE_INSURANCE_PERCENTAGE_WITH_INCREASE).setScale(ROUNDING_SCALE, RoundingMode.CEILING)
                : BigDecimal.ZERO;
    }
}
