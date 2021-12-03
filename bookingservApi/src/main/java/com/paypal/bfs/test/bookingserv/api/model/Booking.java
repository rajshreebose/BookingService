
package com.paypal.bfs.test.bookingserv.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinDatetime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutDatetime;
    @NotNull
    private Double totalPrice;
    @NotNull
    private Double deposit;
    @NotNull
    private Address address;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCheckinDatetime() {
        return checkinDatetime;
    }

    public void setCheckinDatetime(LocalDateTime checkinDatetime) {
        this.checkinDatetime = checkinDatetime;
    }

    public LocalDateTime getCheckoutDatetime() {
        return checkoutDatetime;
    }

    public void setCheckoutDatetime(LocalDateTime checkoutDatetime) {
        this.checkoutDatetime = checkoutDatetime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", checkinDatetime=" + checkinDatetime +
                ", checkoutDatetime=" + checkoutDatetime +
                ", totalPrice=" + totalPrice +
                ", deposit=" + deposit +
                ", address=" + address +
                '}';
    }
}
