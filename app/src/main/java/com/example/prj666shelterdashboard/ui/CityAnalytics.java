package com.example.prj666shelterdashboard.ui;

public class CityAnalytics {
    private String location;
    private int totalUnoccupiedBeds;
    private int totalOccupiedRooms;

    // Default constructor
    public CityAnalytics() {
        // Empty constructor
    }

    // Parameterized constructor
    public CityAnalytics(String location, int totalUnoccupiedBeds, int totalOccupiedRooms) {
        this.location = location;
        this.totalUnoccupiedBeds = totalUnoccupiedBeds;
        this.totalOccupiedRooms = totalOccupiedRooms;
    }

    // Getter and Setter methods
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalUnoccupiedBeds() {
        return totalUnoccupiedBeds;
    }

    public void setTotalUnoccupiedBeds(int totalUnoccupiedBeds) {
        this.totalUnoccupiedBeds = totalUnoccupiedBeds;
    }

    public int getTotalOccupiedRooms() {
        return totalOccupiedRooms;
    }

    public void setTotalOccupiedRooms(int totalOccupiedRooms) {
        this.totalOccupiedRooms = totalOccupiedRooms;
    }
}
