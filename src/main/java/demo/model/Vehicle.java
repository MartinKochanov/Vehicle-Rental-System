package demo.model;

import java.math.BigDecimal;


/**
 * The Vehicle class represents a generic vehicle that can be rented.
 * It serves as an abstract base class for specific types of vehicles.
 */
public abstract class Vehicle {

    private final String brand;
    private final String model;
    private final BigDecimal value;
    private final BigDecimal rentalCostPerDay;
    private final BigDecimal initialInsuranceCostPerDay;
    private final int rentalPeriod;


    public Vehicle(String brand, String model, BigDecimal value, BigDecimal rentalCostPerDay, BigDecimal initialInsuranceCostPerDay, int rentalPeriod) {
        this.brand = brand;
        this.model = model;
        this.value = value;
        this.rentalCostPerDay = rentalCostPerDay;
        this.initialInsuranceCostPerDay = initialInsuranceCostPerDay;
        this.rentalPeriod = rentalPeriod;
    }


    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public BigDecimal getValue() {
        return value;
    }


    public BigDecimal getRentalCostPerDay() {
        return rentalCostPerDay;
    }


    public BigDecimal getInitialInsuranceCostPerDay() {
        return initialInsuranceCostPerDay;
    }

    public int getRentalPeriod() {
        return rentalPeriod;
    }
}
