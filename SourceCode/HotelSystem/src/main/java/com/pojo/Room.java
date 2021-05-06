package com.pojo;


import com.dao.RoomDao;

public class Room {
    int id;
    int floor;
    int number;
    double price;
    RoomType type;

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    boolean isUsing=false;

    public Room(int floor, int number, RoomType type, double price) {
        this.id = RoomDao.getNextRoomId();
        this.floor = floor;
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public RoomType getType() {
        return type;
    }
}
