package demo.utils;

import demo.enums.VehicleTypesEnum;

import java.math.BigDecimal;
import java.util.Scanner;

import static demo.constants.Scales.CAR_SAFETY_RATING_LOWER_BOUND;
import static demo.constants.Scales.CAR_SAFETY_RATING_UPPER_BOUND;

public class Validations {
    public static String getValidatedVehicleType(Scanner scanner) {
        String vehicleType = scanner.nextLine().trim().toLowerCase();

        while (!VehicleTypesEnum.contains(vehicleType)) {
            System.out.println("Enter vehicle type: (car, motorcycle, cargo van)");
            vehicleType = scanner.nextLine().trim().toLowerCase();

        }
        return vehicleType;
    }

    public static BigDecimal getValidatedValue(Scanner scanner) {
        boolean isValueValid = false;
        BigDecimal value = BigDecimal.ZERO;

        while (!isValueValid) {
            try {
                value = new BigDecimal(scanner.nextLine());
                isValueValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter vehicle value: ");
            }
        }
        return value;
    }

    public static int getValidatedSafetyRating(Scanner scanner) {
        int carSafetyRating = 0;
        boolean isSafetyRatingValid = false;
        boolean outOfBounds = true;
        while (!isSafetyRatingValid || outOfBounds) {
            try {
                carSafetyRating = Integer.parseInt(scanner.nextLine());
                isSafetyRatingValid = true;
                outOfBounds = carSafetyRating < CAR_SAFETY_RATING_LOWER_BOUND || carSafetyRating > CAR_SAFETY_RATING_UPPER_BOUND;
            } catch (NumberFormatException e) {
                System.out.println("Enter a car safety rating: (1-5)");
                continue;
            }
            if (outOfBounds) {
                System.out.println("Enter a car safety rating: (1-5)");
            }
        }
        return carSafetyRating;
    }

    public static int getValidatedRiderAge(Scanner scanner) {
        int riderAge = 0;
        boolean isAgeValid = false;
        while (!isAgeValid) {
            try {
                riderAge = Integer.parseInt(scanner.nextLine());
                isAgeValid = riderAge > 16 ;
            } catch (NumberFormatException e) {
                System.out.println("Enter your age: ");
            }
            if (!isAgeValid) {
                System.out.println("Enter your age: ");
            }
        }

        return riderAge;
    }

    public static int validateDriversExperience(Scanner scanner) {
        int driverExperience = -1;
        boolean isExperienceValid = false;

        while (!isExperienceValid) {
            try {
                driverExperience = Integer.parseInt(scanner.nextLine());
                isExperienceValid = driverExperience > -1;
            } catch (NumberFormatException e) {
                System.out.println("Enter a rental period: (in days ex. 10)");

            }
            if (!isExperienceValid) {
                System.out.println("Enter a rental period: (in days ex. 10)");

            }
        }

        return driverExperience;
    }

    public static int validateRentalPeriod(Scanner scanner) {
        int rentalPeriod = 0;
        boolean isPeriodValid = false;

        while (!isPeriodValid) {
            try {
                rentalPeriod = Integer.parseInt(scanner.nextLine());
                isPeriodValid = rentalPeriod > 0;
            } catch (NumberFormatException e) {
                System.out.println("Enter a rental period: (in days ex. 10)");
            }
            if (!isPeriodValid) {
                System.out.println("Enter a rental period: (in days ex. 10)");
            }
        }

        return rentalPeriod;
    }
}
