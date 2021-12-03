package com.paypal.bfs.test.bookingserv.services;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.List;

public interface BookingResourceService {
    Booking create(Booking booking);
    List<Booking> getAll();
    Booking get(Integer bookingId);
}
