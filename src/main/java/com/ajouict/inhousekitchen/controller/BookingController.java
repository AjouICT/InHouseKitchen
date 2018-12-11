package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.service.BookingService;
import com.ajouict.inhousekitchen.service.VisitService;
import com.ajouict.inhousekitchen.util.LoginUser;
import com.ajouict.inhousekitchen.domain.Booking;
import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.service.BookingService;
import com.ajouict.inhousekitchen.service.HostService;
import com.ajouict.inhousekitchen.service.SearchService;
import com.ajouict.inhousekitchen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;

    @Autowired
    private VisitService visitService;
    SearchService searchService;

    @Autowired
    UserService userService;

    @PostMapping("/{id}")
    public String createBooking(@PathVariable Long id, @RequestParam(name="bookingDate") String bookingDate,
                                @RequestParam(name="bookingTime") String bookingTime,
                                @RequestParam(name="bookingGuest") String bookingGuest,
                                @RequestParam(name="bookingMessage") String bookingMessage,
                                HttpSession session, @LoginUser User user){

        bookingService.createBooking(id ,bookingDate, bookingTime, bookingGuest, bookingMessage, session);
        visitService.record(id, user);

        return "booking/bookingSuccess";
    }

    @GetMapping("/list/{id}")
    public ModelAndView getBookingList(@PathVariable String id){
        List<Booking> bookingList=bookingService.getBookingList(id);
        ModelAndView mv=new ModelAndView("booking/bookingList", "bookingList", bookingList);
        return mv;
    }

}
