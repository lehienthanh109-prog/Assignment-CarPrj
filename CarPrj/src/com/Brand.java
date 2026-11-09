/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

public class Brand {

    // 1. Attributes
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    // 2. Default Constructor
    public Brand() {
        this.brandID = "";
        this.brandName = "";
        this.soundBrand = "";
        this.price = 0;
    }

    // 3. Constructor with parameters
    public Brand(String brandID, String brandName,
            String soundBrand, double price) {

        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    // 4. Getters
    public String getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public double getPrice() {
        return price;
    }

    // 5. Setters
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // 6. toString method
    @Override
    public String toString() {
        return brandID + ", " + brandName + ", "
                + soundBrand + ": " + price;
    }
}
