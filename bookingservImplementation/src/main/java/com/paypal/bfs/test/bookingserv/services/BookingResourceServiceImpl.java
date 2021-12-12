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

        com.paypal.bfs.test.bookingserv.datamodel.Booking bookingModel =
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
        if (booking.isPresent()) {
            return modelMapper.map(booking.get(), Booking.class);
        }
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        Optional<com.paypal.bfs.test.bookingserv.datamodel.Booking> b = bookingRepository.findById(booking.getId());
        com.paypal.bfs.test.bookingserv.datamodel.Booking bookingModel =
                modelMapper.map(booking, com.paypal.bfs.test.bookingserv.datamodel.Booking.class);
        com.paypal.bfs.test.bookingserv.datamodel.Booking saved= null;
        if (b.isPresent()){
            saved = b.get();
            saved.setId(bookingModel.getId());
            saved.setFirstName(bookingModel.getFirstName());
            saved.setLastName(bookingModel.getLastName());
            bookingModel = bookingRepository.save(saved);
            return modelMapper.map(bookingModel, Booking.class);
        }
        return null;
    }

    @Override
    public void delete(int bookingId) {
        Optional<com.paypal.bfs.test.bookingserv.datamodel.Booking> b = bookingRepository.findById(bookingId);
        if (b.isPresent()){
            bookingRepository.delete(b.get());
        }
    }
}
