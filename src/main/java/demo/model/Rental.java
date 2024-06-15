package demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static demo.constants.InvoiceOutputMessages.*;
import static demo.constants.Scales.ROUNDING_SCALE;
import static demo.utils.CalculationUtils.*;

/**
 * The Rental class represents a rental transaction for a Vehicle.
 * It generates an invoice detailing the rental information and costs.
 */
public
class Rental {

    private final String customerName;
    private final LocalDate reservationDate;
    private final LocalDate returnDate;
    private final LocalDate actualReturnDate;
    private final Vehicle vehicle;
    private final int actualRentalDays;
    private final BigDecimal totalRent;


    public Rental(String customerName, Vehicle vehicle, LocalDate actualReturnDate, LocalDate rentalStartDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.reservationDate = LocalDate.parse(rentalStartDate.format(formatter));
        this.returnDate = reservationDate.plusDays(vehicle.getRentalPeriod());
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.actualReturnDate = actualReturnDate;
        this.actualRentalDays = (int) ChronoUnit.DAYS.between(reservationDate, actualReturnDate);
        this.totalRent = setTotalRentCost(vehicle, actualRentalDays);
    }


    private String getCustomerName() {
        return customerName;
    }


    private LocalDate getReservationDate() {
        return reservationDate;
    }


    private LocalDate getReturnDate() {
        return returnDate;
    }


    private LocalDate getActualReturnDate() {
        return actualReturnDate;
    }


    private int getActualRentalDays() {
        return actualRentalDays;
    }

    /**
     * Generates an invoice detailing the rental information and costs.
     *
     * @return A formatted string representing the rental invoice.
     */
    public String invoice() {
        StringBuilder sb = new StringBuilder();
        String separator = System.lineSeparator();
        BigDecimal insuranceCostPerDay = BigDecimal.ZERO;
        BigDecimal totalInsuranceCost;
        BigDecimal total;
        boolean isRentalDiscount = actualRentalDays < vehicle.getRentalPeriod();

        sb.append(INVOICE_SEPARATOR).append(separator);
        sb.append(DATE_LABEL).append(getActualReturnDate()).append(separator);
        sb.append(CUSTOMER_NAME_LABEL).append(getCustomerName()).append(separator);
        sb.append(RENTED_VEHICLE_LABEL).append(vehicle.getBrand()).append(" ").append(vehicle.getModel()).append(separator);

        sb.append(separator);

        sb.append(RESERVATION_START_DATE_LABEL).append(getReservationDate()).append(separator);
        sb.append(RESERVATION_END_DATE_LABEL).append(getReturnDate()).append(separator);
        sb.append(RESERVED_RENTAL_DAYS_LABEL).append(vehicle.getRentalPeriod()).append(separator);

        sb.append(separator);

        sb.append(ACTUAL_RETURN_DATE_LABEL).append(getActualReturnDate()).append(separator);
        sb.append(ACTUAL_RENTAL_DAYS_LABEL).append(getActualRentalDays()).append(separator);

        sb.append(separator);

        sb.append(RENTAL_COST_PER_DAY_LABEL).append(vehicle.getRentalCostPerDay()).append(separator);

        if (vehicle instanceof Car car) {

            if (car.getInsuranceDiscountPerDay().compareTo(BigDecimal.ZERO) > 0) {
                insuranceCostPerDay = vehicle.getInitialInsuranceCostPerDay().subtract(car.getInsuranceDiscountPerDay())
                        .setScale(ROUNDING_SCALE, RoundingMode.CEILING);

                sb.append(INITIAL_INSURANCE_PER_DAY_LABEL).append(car.getInitialInsuranceCostPerDay()).append(separator);
                sb.append(INSURANCE_DISCOUNT_PER_DAY_LABEL).append(car.getInsuranceDiscountPerDay()).append(separator);
                sb.append(INSURANCE_PER_DAY_LABEL)
                        .append(insuranceCostPerDay)
                        .append(separator);

            } else {
                sb.append(INSURANCE_PER_DAY_LABEL).append(car.getInitialInsuranceCostPerDay()).append(separator);
                insuranceCostPerDay = car.getInitialInsuranceCostPerDay();
            }
        }

        if (vehicle instanceof CargoVan cargoVan) {

            if (cargoVan.getInsuranceDiscountPerDay().compareTo(BigDecimal.ZERO) > 0) {
                insuranceCostPerDay = vehicle.getInitialInsuranceCostPerDay().subtract(cargoVan.getInsuranceDiscountPerDay())
                        .setScale(ROUNDING_SCALE, RoundingMode.CEILING);

                sb.append(INITIAL_INSURANCE_PER_DAY_LABEL).append(cargoVan.getInitialInsuranceCostPerDay()).append(separator);
                sb.append(INSURANCE_DISCOUNT_PER_DAY_LABEL).append(cargoVan.getInsuranceDiscountPerDay()).append(separator);
                sb.append(INSURANCE_PER_DAY_LABEL).append(insuranceCostPerDay).append(separator);

            } else {
                sb.append(INSURANCE_PER_DAY_LABEL).append(cargoVan.getInitialInsuranceCostPerDay()).append(separator);
                insuranceCostPerDay = cargoVan.getInitialInsuranceCostPerDay();
            }
        }

        if (vehicle instanceof Motorcycle motorcycle) {

            if (motorcycle.getInsuranceAdditionalCostPerDay().compareTo(BigDecimal.ZERO) > 0) {
                insuranceCostPerDay = vehicle.getInitialInsuranceCostPerDay().add(motorcycle.getInsuranceAdditionalCostPerDay())
                        .setScale(ROUNDING_SCALE, RoundingMode.CEILING);

                sb.append(INITIAL_INSURANCE_PER_DAY_LABEL).append(motorcycle.getInitialInsuranceCostPerDay()).append(separator);
                sb.append(INSURANCE_ADDITION_PER_DAY_LABEL).append(motorcycle.getInsuranceAdditionalCostPerDay()).append(separator);
                sb.append(INSURANCE_PER_DAY_LABEL).append(insuranceCostPerDay).append(separator);

            } else {
                sb.append(INSURANCE_PER_DAY_LABEL).append(motorcycle.getInitialInsuranceCostPerDay()).append(separator);
                insuranceCostPerDay = motorcycle.getInitialInsuranceCostPerDay();
            }
        }
        totalInsuranceCost = insuranceCostPerDay
                .multiply(new BigDecimal(actualRentalDays))
                .setScale(ROUNDING_SCALE, RoundingMode.CEILING);

        sb.append(separator);

        total = totalRent.add(totalInsuranceCost).setScale(ROUNDING_SCALE, RoundingMode.CEILING);

        if (isRentalDiscount) {
            sb.append(EARLY_RETURN_DISCOUNT_RENT_LABEL).append(getDiscountForRent(vehicle, totalRent)).append(separator);
            sb.append(EARLY_RETURN_DISCOUNT_INSURANCE_LABEL).append(getDiscountForInsurance( vehicle, actualRentalDays, insuranceCostPerDay)).append(separator);
            sb.append(separator);
        }

        sb.append(TOTAL_RENT_LABEL).append(totalRent).append(separator);
        sb.append(TOTAL_INSURANCE_LABEL).append(totalInsuranceCost).append(separator);
        sb.append(TOTAL_COST_LABEL).append(total).append(separator);
        sb.append(INVOICE_SEPARATOR);

        return sb.toString();
    }

}
