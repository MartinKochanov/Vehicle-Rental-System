package demo.utils;

import demo.enums.VehicleTypesEnum;
import demo.model.Car;
import demo.model.CargoVan;
import demo.model.Motorcycle;
import demo.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static demo.constants.CustomerInteractionMessages.*;
import static demo.constants.Scales.CAR_SAFETY_RATING_LOWER_BOUND;
import static demo.constants.Scales.CAR_SAFETY_RATING_UPPER_BOUND;

/**
 * The Validations class provides static methods for validating user inputs related to vehicle rental
 * and ensuring data integrity during the application's execution.
 */
public class Validations {

    /**
     * Validates and retrieves the customer's name input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated customer name as a String.
     */
    public static String validateCustomerName(Scanner scanner) {
        boolean isNameValid = false;
        String name = null;
        while (!isNameValid) {
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println(ENTER_YOUR_NAME);
            } else {
                isNameValid = true;
            }
        }
        return name;
    }

    /**
     * Validates and retrieves the vehicle brand input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated vehicle brand as a String.
     */
    public static String validateVehicleBrand(Scanner scanner) {
        boolean isBrandValid = false;
        String brand = null;
        while (!isBrandValid) {
            brand = scanner.nextLine();
            if (brand.isBlank()) {
                System.out.println(ENTER_VEHICLE_BRAND);
            } else {
                isBrandValid = true;
            }
        }
        return brand;
    }

    /**
     * Validates and retrieves the vehicle model input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated vehicle model as a String.
     */
    public static String validateVehicleModel(Scanner scanner) {
        boolean isModelValid = false;
        String model = null;
        while (!isModelValid) {
            model = scanner.nextLine();
            if (model.isBlank()) {
                System.out.println(ENTER_VEHICLE_MODEL);
            } else {
                isModelValid = true;
            }
        }
        return model;
    }

    /**
     * Validates and retrieves the vehicle type input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated vehicle type as a String.
     */
    public static String validateVehicleType(Scanner scanner) {
        String vehicleType = scanner.nextLine().trim().toLowerCase();

        while (!VehicleTypesEnum.contains(vehicleType)) {
            System.out.println(ENTER_VEHICLE_TYPE);
            vehicleType = scanner.nextLine().trim().toLowerCase();

        }
        return vehicleType;
    }

    /**
     * Validates and retrieves a BigDecimal value input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated BigDecimal value.
     */
    public static BigDecimal validateValue(Scanner scanner) {
        boolean isValueValid = false;
        BigDecimal value = BigDecimal.ZERO;

        while (!isValueValid) {
            try {
                value = new BigDecimal(scanner.nextLine());
                isValueValid = true;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_VEHICLE_VALUE);
            }
        }
        return value;
    }

    /**
     * Validates and retrieves the safety rating input for a car from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated safety rating as an integer.
     */
    public static int validateSafetyRating(Scanner scanner) {
        int carSafetyRating = 0;
        boolean isSafetyRatingValid = false;
        boolean outOfBounds = true;
        while (!isSafetyRatingValid || outOfBounds) {
            try {
                carSafetyRating = Integer.parseInt(scanner.nextLine());
                isSafetyRatingValid = true;
                outOfBounds = carSafetyRating < CAR_SAFETY_RATING_LOWER_BOUND || carSafetyRating > CAR_SAFETY_RATING_UPPER_BOUND;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_CAR_SAFETY_RATING);
                continue;
            }
            if (outOfBounds) {
                System.out.println(ENTER_CAR_SAFETY_RATING);
            }
        }
        return carSafetyRating;
    }

    /**
     * Validates and retrieves the age input for a motorcycle rider from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated rider age as an integer.
     */
    public static int validateRiderAge(Scanner scanner) {
        int riderAge = 0;
        boolean isAgeValid = false;
        while (!isAgeValid) {
            try {
                riderAge = Integer.parseInt(scanner.nextLine());
                isAgeValid = riderAge > 16;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_YOUR_AGE);
            }
            if (!isAgeValid) {
                System.out.println(ENTER_YOUR_AGE);
            }
        }

        return riderAge;
    }

    /**
     * Validates and retrieves the driving experience input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated driving experience as an integer.
     */
    public static int validateDriversExperience(Scanner scanner) {
        int driverExperience = -1;
        boolean isExperienceValid = false;

        while (!isExperienceValid) {
            try {
                driverExperience = Integer.parseInt(scanner.nextLine());
                isExperienceValid = driverExperience > -1;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_DRIVING_EXPERIENCE);

            }
            if (!isExperienceValid) {
                System.out.println(ENTER_DRIVING_EXPERIENCE);

            }
        }

        return driverExperience;
    }

    /**
     * Validates and retrieves the rental period input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The validated rental period as an integer.
     */
    public static int validateRentalPeriod(Scanner scanner) {
        int rentalPeriod = 0;
        boolean isPeriodValid = false;

        while (!isPeriodValid) {
            try {
                rentalPeriod = Integer.parseInt(scanner.nextLine());
                isPeriodValid = rentalPeriod > 0;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_RENTAL_PERIOD);
            }
            if (!isPeriodValid) {
                System.out.println(ENTER_RENTAL_PERIOD);
            }
        }

        return rentalPeriod;
    }

    /**
     * Validates and retrieves the actual return date input from the scanner.
     *
     * @param scanner   The Scanner object used for input.
     * @param formatter The DateTimeFormatter object used to parse the date.
     * @param startDate The start date to validate against.
     * @return The validated actual return date as a LocalDate.
     */
    public static LocalDate validateActualReturnDate(Scanner scanner, DateTimeFormatter formatter, LocalDate startDate) {
        boolean isDateValid = false;
        LocalDate date = null;

        while (!isDateValid) {
            try {
                date = LocalDate.parse(scanner.nextLine(), formatter);
                isDateValid = date.isAfter(startDate);
            } catch (DateTimeParseException e) {
                System.out.println(ENTER_RETURN_DATE);
            }
            if (!isDateValid) {
                System.out.println(ENTER_RETURN_DATE);
            }
        }
        return date;
    }

    /**
     * Creates and returns a Vehicle object based on the validated inputs.
     *
     * @param vehicleType   The type of vehicle as a String.
     * @param scanner       The Scanner object used for input.
     * @param brand         The validated vehicle brand as a String.
     * @param model         The validated vehicle model as a String.
     * @param vehicleValue  The validated vehicle value as a BigDecimal.
     * @param rentalPeriod  The validated rental period as an integer.
     * @return The created Vehicle object.
     */
    public static Vehicle getVehicle(String vehicleType, Scanner scanner, String brand, String model, BigDecimal vehicleValue, int rentalPeriod) {
        Vehicle vehicle = null;

        if (VehicleTypesEnum.CAR.getSimpleName().equals(vehicleType)) {
            System.out.println(ENTER_CAR_SAFETY_RATING);
            int carSafetyRating = validateSafetyRating(scanner);
            vehicle = new Car(brand, model, vehicleValue, rentalPeriod, carSafetyRating);
        }

        if (VehicleTypesEnum.MOTORCYCLE.getSimpleName().equals(vehicleType)) {
            System.out.println(ENTER_YOUR_AGE);
            int riderAge = validateRiderAge(scanner);
            vehicle = new Motorcycle(brand, model, vehicleValue, rentalPeriod, riderAge);
        }

        if (VehicleTypesEnum.CARGO_VAN.getSimpleName().equals(vehicleType)) {
            System.out.println(ENTER_DRIVING_EXPERIENCE);
            int drivingExperience = validateDriversExperience(scanner);
            vehicle = new CargoVan(brand, model, vehicleValue, rentalPeriod, drivingExperience);
        }
        return vehicle;
    }

    /**
     * Validates and retrieves the rental start date input from the scanner.
     *
     * @param scanner   The Scanner object used for input.
     * @param formatter The DateTimeFormatter object used to parse the date.
     * @return The validated rental start date as a LocalDate.
     */
    public static LocalDate validateRentalStartDate(Scanner scanner, DateTimeFormatter formatter) {
        boolean isDateValid = false;
        LocalDate date = null;
        while (!isDateValid) {
            try {
                date = LocalDate.parse(scanner.nextLine(), formatter);
                isDateValid = true;
            } catch (DateTimeParseException e) {
                System.out.println(ENTER_START_RENTAL_DATE);
            }
        }
        return date;
    }
}
