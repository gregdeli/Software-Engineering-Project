/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.casheri;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Greg
 */
public class SocialMediaFeed {
    private int range;
    private ArrayList<Post> posts;
    private int userId;

    // Constructor
    public SocialMediaFeed(int range, int userId, Connection connection) throws SQLException {
        this.range = range;
        this.userId = userId;
        this.posts = new ArrayList<>();
        searchPosts(connection);
    }

    // Getters and setters
    public int getRange() {
        return range;
    }
    
    // Method to search and retrieve posts from the database
    public void searchPosts(Connection connection)throws SQLException{
        String query = "SELECT * FROM post";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int postId = resultSet.getInt("id");
                String postTitle = resultSet.getString("title");
                String postPhoto = resultSet.getString("photo");
                int driverId = resultSet.getInt("driver_id");
                int maxPass = resultSet.getInt("max_passengers");
                Date dbDate = resultSet.getDate("post_datetime");
                
                LocalDate postDate = dbDate.toLocalDate();
                int tripId = resultSet.getInt("trip_id");
                
                double postLat = resultSet.getDouble("post_location_lat");
                double postLong = resultSet.getDouble("post_location_long");
                Coordinates postLocation = new Coordinates(postLat, postLong);
                
                // Dhmiourgia tou Post Object xwris Driver Object, Trip Object, Passenger array
                Post post = new Post(postId, postTitle, postPhoto, maxPass, postDate, postLocation);
                
                // Add the Passengers of the post
                post.setPassengers(connection);
                
                this.posts.add(post);
            }
        }
        
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void createPost(Post post) {
        // Method gia tin dhmiourgia post stin vasi 
    }

    // Method to add a post to the social media feed
    public void addPost(Post post) {
        posts.add(post);
    }

    // Method to remove a post from the social media feed
    public void removePost(Post post) {
        posts.remove(post);
    }
    
    public String checkUserType() {
        // Anazitisi tou userId stin vasi kai prosdiorismos tous eidous tou user
        return null;
    }
}