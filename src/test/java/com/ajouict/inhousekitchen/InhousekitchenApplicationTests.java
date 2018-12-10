package com.ajouict.inhousekitchen;

import com.ajouict.inhousekitchen.domain.Booking;
import com.ajouict.inhousekitchen.domain.BookingRepository;
import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InhousekitchenApplicationTests {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getBookingList(){
        String userId="1";
        User user=userRepository.findByUserId(userId);
        List<Booking> bookingList=bookingRepository.findBookingByUser(user);
        System.out.println(bookingList);
    }


}
