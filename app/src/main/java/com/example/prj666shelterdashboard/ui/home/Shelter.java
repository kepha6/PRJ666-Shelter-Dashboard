package com.example.prj666shelterdashboard.ui.home;

public class Shelter {
    private String name;
    private String address;
    private String city;
    private int bedsAvailable;

    public Shelter() {
        // Default constructor
    }

    public Shelter(String name, String address, String city, int bedsAvailable) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.bedsAvailable = bedsAvailable;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBedsAvailable() {
        return bedsAvailable;
    }

    public void setBedsAvailable(int bedsAvailable) {
        this.bedsAvailable = bedsAvailable;
    }
}
