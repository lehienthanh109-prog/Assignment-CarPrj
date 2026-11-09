
package com;

public class Car implements Comparable<Car> {

    // 1. Attributes
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    // 2. Default Constructor
    public Car() {
        this.carID = "";
        this.brand = null;
        this.color = "";
        this.frameID = "";
        this.engineID = "";
    }

    // 3. Constructor with parameters
    public Car(String carID, Brand brand, String color,
               String frameID, String engineID) {

        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    // 4. Getters
    public String getCarID() {
        return carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    // 5. Setters
    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    // 6. Compare cars by brand name, then car ID
    @Override
    public int compareTo(Car c) {

        int d = this.brand.getBrandName()
                .compareTo(c.brand.getBrandName());

        if (d != 0) {
            return d;
        }

        return this.carID.compareTo(c.carID);
    }

    // 7. String for saving to cars.txt
    @Override
    public String toString() {

        return carID + ", "
                + brand.getBrandID() + ", "
                + color + ", "
                + frameID + ", "
                + engineID;
    }

    // 8. String for displaying on screen
    public String screenString() {

        return brand.toString() + "\n"
                + carID + ", "
                + color + ", "
                + frameID + ", "
                + engineID;
    }
}
