package com.example.prj666shelterdashboard.ui;

public class Shelter {
    private int _id;
    private int locationId;
    private int shelterId;
    private int programId;
    private int organizationId;
    private String organizationName;
    private String locationName;
    private String locationAddress;
    private String locationPostalCode;
    private String locationCity;
    private String sector;
    private String programName;
    private String overnightServiceType;
    private String programArea;
    private int serviceUserCount;
    private int capacityFundingRoom;
    private int capacityActualRoom;
    private int occupiedRooms;
    private int unoccupiedRooms;
    private int unavailableRooms;
    private String capacityType;

    public Shelter(
            int _id,
            int locationId,
            int shelterId,
            int programId,
            int organizationId,
            String organizationName,
            String locationName,
            String locationAddress,
            String locationPostalCode,
            String locationCity,
            String sector,
            String programName,
            String overnightServiceType,
            String programArea,
            int serviceUserCount,
            int capacityFundingRoom,
            int capacityActualRoom,
            int occupiedRooms,
            int unoccupiedRooms,
            int unavailableRooms,
            String capacityType
    ) {
        this._id = _id;
        this.locationId = locationId;
        this.shelterId = shelterId;
        this.programId = programId;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.locationPostalCode = locationPostalCode;
        this.locationCity = locationCity;
        this.sector = sector;
        this.programName = programName;
        this.overnightServiceType = overnightServiceType;
        this.programArea = programArea;
        this.serviceUserCount = serviceUserCount;
        this.capacityFundingRoom = capacityFundingRoom;
        this.capacityActualRoom = capacityActualRoom;
        this.occupiedRooms = occupiedRooms;
        this.unoccupiedRooms = unoccupiedRooms;
        this.unavailableRooms = unavailableRooms;
        this.capacityType = capacityType;
    }


    // Getters and Setters
    public int get_id() {
        return _id;
    }


    public int getLocationId() {
        return locationId;
    }


    public int getShelterId() {
        return shelterId;
    }

    public int getProgramId() {
        return programId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationPostalCode() {
        return locationPostalCode;
    }

    public void setLocationPostalCode(String locationPostalCode) {
        this.locationPostalCode = locationPostalCode;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getOvernightServiceType() {
        return overnightServiceType;
    }

    public void setOvernightServiceType(String overnightServiceType) {
        this.overnightServiceType = overnightServiceType;
    }

    public String getProgramArea() {
        return programArea;
    }

    public void setProgramArea(String programArea) {
        this.programArea = programArea;
    }

    public int getServiceUserCount() {
        return serviceUserCount;
    }

    public void setServiceUserCount(int serviceUserCount) {
        this.serviceUserCount = serviceUserCount;
    }

    public int getCapacityFundingRoom() {
        return capacityFundingRoom;
    }

    public void setCapacityFundingRoom(int capacityFundingRoom) {
        this.capacityFundingRoom = capacityFundingRoom;
    }

    public int getCapacityActualRoom() {
        return capacityActualRoom;
    }

    public void setCapacityActualRoom(int capacityActualRoom) {
        this.capacityActualRoom = capacityActualRoom;
    }

    public int getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(int occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    public int getUnoccupiedRooms() {
        return unoccupiedRooms;
    }

    public void setUnoccupiedRooms(int unoccupiedRooms) {
        this.unoccupiedRooms = unoccupiedRooms;
    }

    public int getUnavailableRooms() {
        return unavailableRooms;
    }

    public void setUnavailableRooms(int unavailableRooms) {
        this.unavailableRooms = unavailableRooms;
    }

    public String getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(String capacityType) {
        this.capacityType = capacityType;
    }
}
