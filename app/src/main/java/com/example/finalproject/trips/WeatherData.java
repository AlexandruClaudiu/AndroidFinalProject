package com.example.finalproject.trips;


import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private Current current;

    public static class Location{
        @SerializedName("localtime")
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class Current{

        @SerializedName("temp_c")
        private String temperature;

        @SerializedName("is_day")
        private int isDay;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public int getIsDay() {
            return isDay;
        }

        public void setIsDay(int isDay) {
            this.isDay = isDay;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
