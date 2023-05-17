package com.example.bmwdemo.dto;

import javax.persistence.*;

@Entity(name = "car")
public class CarDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String colour;
    private String year;
    private String price;

    private String engineCapacity;

    public CarDTO(String model, String colour, String year, String price, String engineCapacity) {
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.price = price;
        this.engineCapacity = engineCapacity;
    }

    public CarDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
