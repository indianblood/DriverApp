package sati.shubham.driverapp;

public class Driver {
    private String carDetails, driverID, isAvailable, name , currentBookingID;
    private double latitude,longitude;

    public Driver(String carDetails, String driverId,
                  String isAvailable, String name, String currentBookingID,
                  double latitude, double longitude) {
        this.carDetails = carDetails;
        this.driverID = driverId;
        this.isAvailable = isAvailable;
        this.name = name;
        this.currentBookingID = currentBookingID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Driver()
    {

    }
    public String getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(String carDetails) {
        this.carDetails = carDetails;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverId) {
        this.driverID = driverId;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentBookingID() {
        return currentBookingID;
    }

    public void setCurrentBookingID(String currentBookingID) {
        this.currentBookingID = currentBookingID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
