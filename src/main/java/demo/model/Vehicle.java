package demo.model;

import java.math.BigDecimal;



public abstract class Vehicle {

    private String brand;
    private String model;
    private BigDecimal value;
    private BigDecimal rentalCostPerDay;
    private BigDecimal initialInsuranceCostPerDay;
    private int rentalPeriod;

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
