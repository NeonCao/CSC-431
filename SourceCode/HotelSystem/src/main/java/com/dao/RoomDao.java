package com.dao;


import org.springframework.stereotype.Repository;
import com.pojo.Room;
import com.pojo.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoomDao {
    private static Map<Integer, Room> rooms = null;
    private static Integer initId = 1;
    static {
        rooms = new HashMap<Integer, Room>();
        rooms.put(initId, new Room(1, 102, RoomType.SINGLE, 12));
        rooms.put(initId, new Room(2, 202, RoomType.TWIN, 22));
        rooms.put(initId, new Room(3, 303, RoomType.SUITE, 32));
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public static int getNextRoomId() {
        return initId++;
    }

    public void deleteRoom(Room room) {
        if (rooms.get(room.getId()) != null) rooms.remove(room.getId());
    }

    public Room searchRoom(String number) {
        try {
            int roomNumber = Integer.valueOf(number);
            for (Map.Entry<Integer, Room> m : rooms.entrySet()) {
                if (m.getValue().getNumber() == roomNumber) return m.getValue();
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }
    public Collection<Room> findAll()
    {
        Collection<Room> roomList=new ArrayList<>();
        for (Map.Entry<Integer, Room> m : rooms.entrySet()) {
            roomList.add(m.getValue());
        }
        return roomList;
    }
    public Room findById(int id)
    {
        return rooms.get(id);
    }
}
