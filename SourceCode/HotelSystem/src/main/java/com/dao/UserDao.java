package com.dao;

import com.pojo.User;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class UserDao {
    private static Map<Integer, User> Users = null;
    private static Integer initId = 1;

    static {
        Users = new HashMap<Integer, User>();
        Users.put(initId, new User(initId++, "user1", "user1", "123", 1));
        Users.put(initId, new User(initId++, "user2", "user2", "456", 2));
    }

    public void addUser(User User) {
        Users.put(User.getId(), User);
    }

    public static int getNextUserId() {
        return initId++;
    }

    public void deleteUser(User User) {
        if (Users.get(User.getId()) != null) Users.remove(User.getId());
    }

    public Collection<User> findAll() {
        Collection<User> UserList = new ArrayList<>();
        for (Map.Entry<Integer, User> m : Users.entrySet()) {
            UserList.add(m.getValue());
        }
        return UserList;
    }

    public int getUserId(String userName, String password) {
        for (Map.Entry<Integer, User> m : Users.entrySet()) {
            if(m.getValue().getPassword().equals(password)&&m.getValue().getName().equals(userName)) return m.getKey();
        }
        return -1;
    }

    public User findById(int id) {
        return Users.get(id);
    }
}
