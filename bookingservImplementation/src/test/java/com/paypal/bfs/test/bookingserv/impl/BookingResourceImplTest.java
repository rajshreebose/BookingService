package com.paypal.bfs.test.bookingserv.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.services.BookingResourceService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BookingResourceImplTest {
    private MockMvc mockMvc;
    private Booking booking;
    @MockBean
    private BookingResourceService service;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookingResourceImpl(service)).build();
        Address address = new Address();
        address.setLine1("By lane 1");
        address.setCity("guwahati");
        address.setState("assam");
        address.setZipcode("781012");
        booking = new Booking();
        booking.setId(1);
        booking.setFirstName("Rajshree");
        booking.setLastName("Bose");
        //booking.setDateOfBirth(LocalDate.of(1993,1,28));
        //booking.setCheckinDatetime(LocalDateTime.now());
        booking.setTotalPrice(8000.00);
        booking.setDeposit(4000.00);
        booking.setAddress(address);
    }

    @Test
    void testCreateWhenRequestBodyHasRequiredFields() {
        setup();
        when(service.get(booking.getId())).thenReturn(booking);
        when(service.create(booking)).thenReturn(booking);
        String uri = "/v1/bfs/booking";
        try {
            Gson gson = new Gson();
            String inputJson = gson.toJson(booking);
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(201, status);
            String content = mvcResult.getResponse().getContentAsString();
            assertEquals(content, inputJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testCreateWhenRequestBodyDoesnotHaveRequiredFields() {
        booking = getWithoutMandatoryFields();
        when(service.get(booking.getId())).thenReturn(booking);
        when(service.create(booking)).thenReturn(booking);
        String uri = "/v1/bfs/booking";
        try {
            Gson gson = new Gson();
            String inputJson = gson.toJson(booking);
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(400, status);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Booking getWithoutMandatoryFields() {
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

    @Test
    void getAll() {
    }
}