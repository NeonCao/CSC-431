package com.pojo;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private double price;
    private Room item;
    private int status;
    private int userId;
    private int roomId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Order(int id, int userId, int roomId, Date date, double price, Room item, int status) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.date = date;
        this.price = price;
        this.item = item;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public Room getItem() {
        return item;
    }

    public int getStatus() {
        return status;
    }
}
