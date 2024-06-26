package com.mycompany.casheri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.jxmapviewer.viewer.GeoPosition;

public class Post {
    private int id;
    private String title;
    private String photo;
    private Driver driver;
    private int maxPassengers;
    private ArrayList<Passenger> passengers;
    private LocalDate date;
    private Trip trip;
    private GeoPosition postLocation;
    private String description;
    
    public Post(int id, String title, String photo, int maxPassengers, LocalDate date, GeoPosition postLocation, String description) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.maxPassengers = maxPassengers;
        this.date = date;
        this.postLocation = postLocation;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    
    public void addPassenger(Passenger passenger){
        // Method gia tin prothiki passenger sto post
        // Tha prepei na ginetai elegxos tou pediou maxPassenegers
    }

    public void setPassengers(Connection connection)throws SQLException {
        passengers = new ArrayList<Passenger>();
        String query = "SELECT passenger_id FROM post_passenger WHERE post_id=" + this.id;
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int passId = resultSet.getInt("passenger_id");
                Passenger tempPassenger = new Passenger(passId);
                passengers.add(tempPassenger);
            }
        // Check if the number of passengers exceeds maxPassengers
        if (passengers.size() > this.maxPassengers) {
            System.out.println("Error: Number of passengers exceeds the maximum allowed (" + this.maxPassengers + ")");
        } else {
            this.passengers = passengers;
        }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public GeoPosition getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(GeoPosition postLocation) {
        this.postLocation = postLocation;
    }
    
    public String getDescription() {
        return description;
    }
}