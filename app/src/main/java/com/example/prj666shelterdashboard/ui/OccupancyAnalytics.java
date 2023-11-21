package com.example.prj666shelterdashboard.ui;

public class OccupancyAnalytics {
    private int totalBeds;
    private int availableBeds;
    private int occupiedBeds;
    private int unavailableBeds;

    // Default constructor
    public OccupancyAnalytics() {
        // Empty constructor
    }

    // Parameterized constructor
    public OccupancyAnalytics(int totalBeds, int availableBeds, int occupiedBeds, int unavailableBeds) {
        this.totalBeds = totalBeds;
        this.availableBeds = availableBeds;
        this.occupiedBeds = occupiedBeds;
        this.unavailableBeds = unavailableBeds;
    }

    // Getter and Setter methods
    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public int getOccupiedBeds() {
        return occupiedBeds;
    }

    public void setOccupiedBeds(int occupiedBeds) {
        this.occupiedBeds = occupiedBeds;
    }

    public int getUnavailableBeds() {
        return unavailableBeds;
    }

    public void setUnavailableBeds(int unavailableBeds) {
        this.unavailableBeds = unavailableBeds;
    }
}
