package com.dao;

import com.pojo.Order;
import com.pojo.Room;
import com.pojo.RoomType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderDao {
    private static Map<Integer, Order> orders = null;
    private static Integer initId = 1;

    static {
        orders = new HashMap<Integer, Order>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public static int getNextOrderId() {
        return initId++;
    }

    public void deleteOrder(Order order) {
        if (orders.get(order.getId()) != null) orders.remove(order.getId());
    }

    public Collection<Order> findAll() {
        Collection<Order> orderList = new ArrayList<>();
        for (Map.Entry<Integer, Order> order : orders.entrySet()) {
            orderList.add(order.getValue());
        }
        return orderList;
    }

    public Order findById(int id) {
        return orders.get(id);
    }
}
