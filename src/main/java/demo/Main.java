package demo;

import demo.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static demo.constants.CustomerInteractionMessages.*;
import static demo.utils.Validations.*;


/**
 * Main class for the rental application.
 * This class interacts with the user to gather rental information,
 * validates user inputs, and displays the rental invoice.
 */
public class Main {

    /**
     * Entry point of the rental application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Vehicle vehicle;

        Scanner scanner = new Scanner(System.in);

        System.out.println(ENTER_YOUR_NAME);
        String customerName = validateCustomerName(scanner);

        System.out.println(ENTER_VEHICLE_TYPE);
        String vehicleType = validateVehicleType(scanner);

        System.out.println(ENTER_VEHICLE_BRAND);
        String brand = validateVehicleBrand(scanner);

        System.out.println(ENTER_VEHICLE_MODEL);
        String model = validateVehicleModel(scanner);

        System.out.println(ENTER_VEHICLE_VALUE);
        BigDecimal vehicleValue = validateValue(scanner);

        System.out.println(ENTER_START_RENTAL_DATE);
        LocalDate rentalStartDate = validateRentalStartDate(scanner, formatter);

        System.out.println(ENTER_RENTAL_PERIOD);
        int rentalPeriod = validateRentalPeriod(scanner);

        vehicle = getVehicle(vehicleType, scanner, brand, model, vehicleValue, rentalPeriod);

        System.out.println(ENTER_RETURN_DATE);
        LocalDate actualReturnDate = validateActualReturnDate(scanner, formatter, rentalStartDate);


        Rental rental = new Rental(customerName, vehicle, actualReturnDate, rentalStartDate);

        System.out.println(rental.invoice());
    }

}

