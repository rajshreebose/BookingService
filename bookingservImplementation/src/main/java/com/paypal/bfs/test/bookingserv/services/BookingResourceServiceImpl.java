package com.paypal.bfs.test.bookingserv.services;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingResourceServiceImpl implements BookingResourceService {
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;

    public BookingResourceServiceImpl(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Booking create(@Valid Booking booking) {

        com.paypal.bfs.test.bookingserv.datamodel.Booking bookingModel  =
                modelMapper.map(booking, com.paypal.bfs.test.bookingserv.datamodel.Booking.class);
        bookingModel = this.bookingRepository.save(bookingModel);
        return modelMapper.map(bookingModel, Booking.class);
    }

    @Override
    public List<Booking> getAll() {
        List<com.paypal.bfs.test.bookingserv.datamodel.Booking> bookings =
                bookingRepository.findAll();
        return bookings
                .stream()
                .map(record -> modelMapper.map(record, Booking.class))
                .collect(Collectors.toList());
    }

    @Override
    public Booking get(Integer bookingId) {
        Optional<com.paypal.bfs.test.bookingserv.datamodel.Booking> booking =
                bookingRepository.findById(bookingId);
        return modelMapper.map(booking, Booking.class);
    }
}
