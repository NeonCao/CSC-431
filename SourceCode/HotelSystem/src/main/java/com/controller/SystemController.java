package com.controller;


import com.dao.OrderDao;
import com.dao.RoomDao;
import com.dao.UserDao;
import com.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pojo.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class SystemController {
    @Autowired
    RoomDao roomDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;


    @ModelAttribute("rooms")
    public Collection<Room> getAllRooms() {
        return roomDao.findAll();
    }

    @RequestMapping(value = "/")
    public String homePage() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public String doLogin(Model model, @ModelAttribute("rooms") Collection<Room> rooms, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        System.out.println(userName + "|" + password);
        int userId = userDao.getUserId(userName, password);
        if (userId < 1) return "login";
        model.addAttribute("userId", userId);
        return "mainpage";
    }

    @RequestMapping(value = "/doSearch/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String doSearch(Model model, @RequestParam(value = "roomNumber") String roomNumber, @PathVariable("userId") String userId) {
        int number = Integer.parseInt(roomNumber);
        Collection<Room> rooms = new ArrayList<>();
        for (Room room : roomDao.findAll()) {
            if (room.getNumber() == number) rooms.add(room);
        }
        model.addAttribute("myRooms", userDao.findById(Integer.parseInt(userId)).getRoomList());
        model.addAttribute("rooms", rooms);
        model.addAttribute("userId", userId);
        return "mainpage";
    }

    @RequestMapping(value = "/doView/{roomId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String doView(Model model, @PathVariable("roomId") String roomId, @PathVariable("userId") String userId) {
        model.addAttribute("room", roomDao.findById(Integer.parseInt(roomId)));
        model.addAttribute("userId", userId);
        return "roomDetail";
    }

    @RequestMapping(value = "/subscribeRoom/{roomId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String subscribeRoom(Model model, @PathVariable("roomId") String roomId, @PathVariable("userId") String userId) {
        model.addAttribute("room", roomDao.findById(Integer.parseInt(roomId)));
        model.addAttribute("userId", userId);
        return "roomDetail";
    }

    @RequestMapping(value = "/generateOrder/{roomId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String generateOrder(Model model, @PathVariable("roomId") String roomId,
                                @RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate")
                                        String endDate, @PathVariable("userId") String userId) {
        System.out.println(startDate);
        System.out.println(endDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try {
            Date start = simpleDateFormat.parse(startDate);
            Date end = simpleDateFormat.parse(endDate);
            long diff = (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
            //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Room room = roomDao.findById(Integer.parseInt(roomId));
            Order order = new Order(OrderDao.getNextOrderId(), Integer.parseInt(userId), Integer.parseInt(roomId), date, diff * room.getPrice(), room, 0);
            orderDao.addOrder(order);
            model.addAttribute("order", order);
            if (diff > 0) return "order";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "dateError";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "dateError";
    }

    @RequestMapping(value = "/doPay/{roomId}/{userId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String doPay(@ModelAttribute("rooms") Collection<Room> rooms,Model model, @PathVariable("roomId") String roomId,
                        @PathVariable("userId") String userId) {
        userDao.findById(Integer.parseInt(userId)).addRoom(roomDao.findById(Integer.parseInt(roomId)));
        model.addAttribute("myRooms", userDao.findById(Integer.parseInt(userId)).getRoomList());
        model.addAttribute("rooms", rooms);
        model.addAttribute("userId", userId);
        return "mainpage";
    }


}
