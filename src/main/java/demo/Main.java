package demo;

import demo.enums.VehicleTypesEnum;
import demo.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static demo.utils.Validations.*;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Vehicle vehicle = null;

        Scanner scanner = new Scanner(System.in);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Enter your name: (ex. John Doe)");
        String customerName = scanner.nextLine().trim();

        System.out.println("Enter vehicle type: (car, motorcycle, cargo van)");
        String vehicleType = getValidatedVehicleType(scanner);

        System.out.println("Enter vehicle brand: ");
        String brand = scanner.nextLine().trim();

        System.out.println("Enter vehicle model: ");
        String model = scanner.nextLine().trim();

        System.out.println("Enter vehicle value: ");
        BigDecimal vehicleValue = getValidatedValue(scanner);

        System.out.println("Enter a rental period: (in days ex. 10)");
        int rentalPeriod = validateRentalPeriod(scanner);

        if (VehicleTypesEnum.CAR.getSimpleName().equals(vehicleType)) {
            System.out.println("Enter a car safety rating: (1-5)");
            int carSafetyRating = getValidatedSafetyRating(scanner);
            vehicle = new Car(brand, model, vehicleValue, rentalPeriod, carSafetyRating);
        }

        if (VehicleTypesEnum.MOTORCYCLE.getSimpleName().equals(vehicleType)) {
            System.out.println("Enter your age: ");
            int riderAge = getValidatedRiderAge(scanner);
            vehicle = new Motorcycle(brand, model, vehicleValue, rentalPeriod, riderAge);
        }

        if (VehicleTypesEnum.CARGO_VAN.getSimpleName().equals(vehicleType)) {
            System.out.println("Enter your driving experience: (in years ex. 5)");
            int drivingExperience = validateDriversExperience(scanner);
            vehicle = new CargoVan(brand, model, vehicleValue, rentalPeriod, drivingExperience);
        }

        System.out.println("Enter the actual return date: (ex. 2024-06-03)");
        LocalDate actualReturnDate = LocalDate.parse(scanner.nextLine(), formatter);

        assert vehicle != null;
        Rental rental = new Rental(customerName, vehicle, actualReturnDate);

        System.out.println(rental.invoice());
    }

    //TODO: Validate the input for customer name, actual return date, model and brand of the vehicle
}

