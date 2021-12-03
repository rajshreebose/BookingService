package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.services.BookingResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookingResourceImpl implements BookingResource {
    private BookingResourceService bookingResourceService;

    public BookingResourceImpl(BookingResourceService bookingResourceService) {
        this.bookingResourceService = bookingResourceService;
    }

    @Override
    public ResponseEntity<Booking> create(@Valid Booking booking) {
        if (booking == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Booking result = bookingResourceService.get(booking.getId());
            if (result == null){
                result = bookingResourceService.create(booking);
            }
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Booking>> getAll() {
        return new ResponseEntity<>(bookingResourceService.getAll(), HttpStatus.OK);
    }
}
