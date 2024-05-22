package com.mycompany.casheri;

import java.sql.Connection;
import org.jxmapviewer.viewer.GeoPosition;

public class Trip {

    private int id;
    private int driverId;
    private String datetime;
    private String duration;
    private GeoPosition coordStart;
    private GeoPosition coordEnd;
    private int passengerCapacity;
    private int repeatTrip;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getRepeatTrip() {
        return repeatTrip;
    }

    public void setRepeatTrip(int repeatTrip) {
        this.repeatTrip = repeatTrip;
    }
    private float cost;
    
    
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    

    public GeoPosition getCoordStart() {
        return coordStart;
    }

    public void setCoordStart(GeoPosition coordStart) {
        this.coordStart = coordStart;
    }
    
    public GeoPosition getCoordEnd() {
        return coordEnd;
    }

    public void setCoordEnd(GeoPosition coordEnd) {
        this.coordEnd = coordEnd;
    }

    public void storeTrip() {
        Connection con = (new Database()).con();
        String query = String.format("INSERT INTO TRIP VALUES (NULL, %d, '%s', '%s', %f, %f, %f, %f, %d, %d, %f) ",
            driverId, datetime, duration, coordStart.getLatitude(), coordStart.getLongitude(),
            coordEnd.getLatitude(), coordEnd.getLongitude(), passengerCapacity, repeatTrip, cost);
        try{
            con.createStatement().executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
