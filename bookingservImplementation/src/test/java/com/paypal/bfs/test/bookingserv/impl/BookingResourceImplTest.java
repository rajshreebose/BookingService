package com.paypal.bfs.test.bookingserv.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.services.BookingResourceService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BookingResourceImplTest {
    @Mock
    private BookingResourceService service;
    @InjectMocks
    BookingResourceImpl bookingResourceImpl;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookingResourceImpl(service)).build();
    }

    @Test
    void testCreateWhenInputValidationIsPassed() {
        Booking booking = getBookingWithRequiredFields();
        when(service.get(booking.getId())).thenReturn(booking);
        when(service.create(booking)).thenReturn(booking);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.create(booking)).thenReturn(booking);

        ResponseEntity<Booking> responseEntity = bookingResourceImpl.create(booking);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(booking, responseEntity.getBody());
    }

    @Test
    void testCreateWhenInputValidationFails() {
        Booking booking = getWithoutMandatoryFields();
        ObjectMapper mapper = new ObjectMapper();
        String res = null;
        try {
            res = mockMvc.perform(post("/v1/bfs/booking")
                    .content(mapper.writeValueAsString(booking))
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Booking booking = getWithoutMandatoryFields();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.create(booking)).thenReturn(booking);

        ResponseEntity<Booking> responseEntity = bookingResourceImpl.create(booking);

        assertEquals(400, responseEntity.getStatusCodeValue());*/

    }

    @Test
    void getAll() {
        List<Booking> bookings = singletonList(getBookingWithRequiredFields());
        given(service.getAll()).willReturn(bookings);

        //when
        ResponseEntity<List<Booking>> responseEntity = bookingResourceImpl.getAll();
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(bookings, responseEntity.getBody());
    }

    private Booking getBookingWithRequiredFields() {
        Booking booking = new Booking();
        Address address = new Address();
        address.setLine1("By lane 1");
        address.setCity("guwahati");
        address.setState("assam");
        address.setZipcode("781012");
        booking.setId(1);
        booking.setFirstName("Rajshree");
        booking.setLastName("Bose");
        booking.setDateOfBirth(LocalDate.of(1993, 1, 28));
        booking.setCheckinDatetime(LocalDateTime.now());
        booking.setTotalPrice(8000.00);
        booking.setDeposit(4000.00);
        booking.setAddress(address);
        return booking;
    }

    private Booking getWithoutMandatoryFields() {
        Booking booking = new Booking();
        Address address = new Address();
        address.setLine1("By lane 1");
        address.setCity("guwahati");
        address.setState("assam");
        address.setZipcode("781012");
        booking = new Booking();
        booking.setId(1);
        //booking.setDateOfBirth(LocalDate.of(1993,1,28));
        //booking.setCheckinDatetime(LocalDateTime.now());
        booking.setTotalPrice(8000.00);
        booking.setDeposit(4000.00);
        booking.setAddress(address);
        return booking;
    }
}