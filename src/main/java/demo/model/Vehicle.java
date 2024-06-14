package demo.model;

import java.math.BigDecimal;

public abstract class Vehicle {

    private String brand;
    private String model;
    private BigDecimal value;

    public Vehicle(String brand, String model, BigDecimal value) {
        this.brand = brand;
        this.model = model;
        this.value = value;
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



    public abstract BigDecimal calculateInsuranceCost(int rentalDays);
}
