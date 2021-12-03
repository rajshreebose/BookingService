package com.paypal.bfs.test.bookingserv.datamodel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private LocalDateTime checkinDatetime;
    @Column
    private LocalDateTime checkoutDatetime;
    @Column
    private Double totalPrice;
    @Column
    private Double deposit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Booking() {
    }

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
}
