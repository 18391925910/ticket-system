package com.btw.TicketService.entity;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:36
 */
public class City {

    private String id;
    private String city;
    private String station_quantity;
    private String time_area;
    private String location;
    private String hot_star;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public void setStation_quantity(String station_quantity) {
        this.station_quantity = station_quantity;
    }
    public String getStation_quantity() {
        return station_quantity;
    }

    public void setTime_area(String time_area) {
        this.time_area = time_area;
    }
    public String getTime_area() {
        return time_area;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setHot_star(String hot_star) {
        this.hot_star = hot_star;
    }
    public String getHot_star() {
        return hot_star;
    }

}