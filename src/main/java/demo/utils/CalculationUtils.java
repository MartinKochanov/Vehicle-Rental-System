package demo.utils;

import demo.model.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static demo.constants.Scales.ROUNDING_SCALE;
/**
 * The CalculationUtils class provides utility methods for calculating rental and insurance costs,
 * as well as discounts related to vehicle rentals.
 */
public class CalculationUtils {

    /**
     * Number of days in a week used for determining rental costs.
     */
    public static final int WEEK_DAYS = 7;

    /**
     * Calculates the rental cost per day based on the rental period and daily costs.
     *
     * @param rentalPeriod               The total rental period in days.
     * @param dailyRentalCostForLessThanWeek   The daily rental cost if rental period is less than or equal to a week.
     * @param dailyRentalCostForMoreThanWeek   The daily rental cost if rental period is more than a week.
     * @return The calculated rental cost per day as a BigDecimal.
     */
    public static BigDecimal setRentalCostPerDay(int rentalPeriod, int dailyRentalCostForLessThanWeek, int dailyRentalCostForMoreThanWeek) {
        return new BigDecimal(rentalPeriod <= WEEK_DAYS
                ? dailyRentalCostForLessThanWeek
                : dailyRentalCostForMoreThanWeek).setScale(ROUNDING_SCALE, RoundingMode.CEILING);
    }

    /**
     * Calculates the initial insurance cost per day based on a value and insurance percentage.
     *
     * @param value              The initial value to be insured.
     * @param insurancePercentage   The percentage of the value to calculate insurance cost.
     * @return The calculated initial insurance cost per day as a BigDecimal.
     */
    public static BigDecimal setInitialInsuranceCostPerDay(BigDecimal value, BigDecimal insurancePercentage) {
        return value.multiply(insurancePercentage).setScale(ROUNDING_SCALE, RoundingMode.CEILING);
    }


    /**
     * Calculates the total rent cost for a vehicle rental based on the actual rental days and vehicle details.
     *
     * @param vehicle           The Vehicle object being rented.
     * @param actualRentalDays  The actual number of days the vehicle was rented.
     * @return The total rent cost as a BigDecimal.
     */
    public static BigDecimal setTotalRentCost(Vehicle vehicle, int actualRentalDays) {
        int remainingDays = vehicle.getRentalPeriod() - actualRentalDays;
        BigDecimal rentalCostForFullDays = vehicle.getRentalCostPerDay().multiply(new BigDecimal(actualRentalDays));
        BigDecimal rentalCostForRemainingDays = vehicle.getRentalCostPerDay()
                .divide(new BigDecimal(2)).setScale(ROUNDING_SCALE, RoundingMode.CEILING)
                .multiply(new BigDecimal(remainingDays));
        return rentalCostForFullDays.add(rentalCostForRemainingDays);
    }

    /**
     * Calculates the discount amount for the rental cost based on the difference between full price and total rent.
     *
     * @param vehicle   The Vehicle object being rented.
     * @param totalRent The total rent cost calculated.
     * @return The discount amount for the rental cost as a BigDecimal.
     */
    public static BigDecimal getDiscountForRent(Vehicle vehicle, BigDecimal totalRent) {
        BigDecimal fullPrice = vehicle.getRentalCostPerDay().multiply(new BigDecimal(vehicle.getRentalPeriod()));

        return fullPrice.subtract(totalRent);
    }

    /**
     * Calculates the discount amount for the insurance cost based on the difference between full price and actual price.
     *
     * @param vehicle               The Vehicle object being rented.
     * @param actualRentalDays      The actual number of days the vehicle was rented.
     * @param insuranceCostPerDay   The insurance cost per day calculated.
     * @return The discount amount for the insurance cost as a BigDecimal.
     */
    public static BigDecimal getDiscountForInsurance(Vehicle vehicle, int actualRentalDays, BigDecimal insuranceCostPerDay) {
        BigDecimal fullPrice = insuranceCostPerDay.multiply(new BigDecimal(vehicle.getRentalPeriod()));
        BigDecimal actualPrice = insuranceCostPerDay.multiply(new BigDecimal(actualRentalDays));

        return fullPrice.subtract(actualPrice);
    }
}
