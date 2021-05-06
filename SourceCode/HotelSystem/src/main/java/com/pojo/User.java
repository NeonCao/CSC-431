package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String password;
    String phone;
    List<Room> roomList = new ArrayList<Room>();

    public void addRoom(Room room) {
        this.roomList.add(room);
    }

    public List<Room> getRoomList() {
        return this.roomList;
    }

    int sn;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getSn() {
        return sn;
    }

    public int getId() {
        return id;
    }

    int id;

    public User(int id, String name, String password, String phone, int sn) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.sn = sn;
    }

    public void execute() {

    }
}
