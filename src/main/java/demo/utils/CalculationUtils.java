package demo.utils;

import demo.model.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.Scales.ROUNDING_SCALE;

public class CalculationUtils {
    public static final int WEEK_DAYS = 7;


    public static BigDecimal setRentalCostPerDay(int rentalPeriod, int dailyRentalCostForLessThanWeek, int dailyRentalCostForMoreThanWeek) {
        return new BigDecimal(rentalPeriod <= WEEK_DAYS
                ? dailyRentalCostForLessThanWeek
                : dailyRentalCostForMoreThanWeek).setScale(ROUNDING_SCALE, RoundingMode.CEILING);
    }

    public static BigDecimal setInitialInsuranceCostPerDay(BigDecimal value, BigDecimal insurancePercentage) {
        return value.multiply(insurancePercentage).setScale(ROUNDING_SCALE, RoundingMode.CEILING);
    }

    public static BigDecimal setTotalRentCost(Vehicle vehicle, int actualRentalDays) {
        int remainingDays = vehicle.getRentalPeriod() - actualRentalDays;
        BigDecimal rentalCostForFullDays = vehicle.getRentalCostPerDay().multiply(new BigDecimal(actualRentalDays));
        BigDecimal rentalCostForRemainingDays = vehicle.getRentalCostPerDay()
                .divide(new BigDecimal(2)).setScale(ROUNDING_SCALE, RoundingMode.CEILING)
                .multiply(new BigDecimal(remainingDays));
        return rentalCostForFullDays.add(rentalCostForRemainingDays);
    }
}
