package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.InsuranceCostsPercentages.*;
import static demo.constants.Scales.ROUNDING_SCALE;
import static demo.constants.VehiclePrices.CAR_DAILY_RENTAL_COST_RENTED_FOR_LESS_THAN_WEEK;
import static demo.constants.VehiclePrices.CAR_DAILY_RENTAL_COST_RENTED_FOR_MORE_THAN_WEEK;
import static demo.utils.CalculationUtils.setInitialInsuranceCostPerDay;
import static demo.utils.CalculationUtils.setRentalCostPerDay;

public class Car extends Vehicle {

    private final int carSafetyRating;
    private final BigDecimal insuranceDiscountPerDay;

    public Car(String brand, String model, BigDecimal value, int rentalPeriod, int carSafetyRating) {
        super(brand,
                model,
                value,
                setRentalCostPerDay(rentalPeriod,
                        CAR_DAILY_RENTAL_COST_RENTED_FOR_LESS_THAN_WEEK,
                        CAR_DAILY_RENTAL_COST_RENTED_FOR_MORE_THAN_WEEK),
                setInitialInsuranceCostPerDay(value, CAR_INSURANCE_PERCENTAGE),
                rentalPeriod);

        this.carSafetyRating = carSafetyRating;
        this.insuranceDiscountPerDay = setInsuranceDiscountPerDay(carSafetyRating);
    }

    private BigDecimal setInsuranceDiscountPerDay(int carSafetyRating) {
        return carSafetyRating >= 4
                ? getInitialInsuranceCostPerDay().multiply(CAR_INSURANCE_DISCOUNT_PERCENTAGE).setScale(ROUNDING_SCALE, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
    }

    public int getCarSafetyRating() {
        return carSafetyRating;
    }

    public BigDecimal getInsuranceDiscountPerDay() {
        return insuranceDiscountPerDay;
    }

}
