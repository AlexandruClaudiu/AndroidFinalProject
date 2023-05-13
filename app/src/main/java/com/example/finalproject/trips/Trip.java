package com.example.finalproject.trips;

public class Trip {

    private String name;
    private String destination;
    private String tripType;
    private double price;
    private String startDate;
    private String endDate;
    private double rating;
    private String imageUrl;

    public Trip(){}

    public void build(String name, String destination, String tripType){
        this.name = name;
        this.destination = destination;
        this.tripType = tripType;
        this.price = 0.0;
        this.startDate = null;
        this.endDate = null;
        this.rating = 0.0;
        this.imageUrl = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getTripType() {
        return tripType;
    }

    public double getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", tripType='" + tripType + '\'' +
                ", price=" + price +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", rating=" + rating +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
