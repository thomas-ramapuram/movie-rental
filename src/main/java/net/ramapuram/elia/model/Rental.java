package net.ramapuram.elia.model;

import java.util.Date;

/**
 * Created by Elia Thomas Ramapuram on 29/09/16.
 * as part of the Computer Science Project 2016-17
 */
public class Rental {
    private Date rentedOn;
    private Date returnedOn;
    private String customer;


    public Rental(String customer) {
        this.customer = customer;
        rentedOn = new Date();
    }

    public String getCustomer() {
        return customer;
    }

    public Date getRentedOn() {
        return rentedOn;
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public boolean isActive() {
        return returnedOn == null;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentedOn=" + rentedOn +
                ", returnedOn=" + returnedOn +
                ", customer='" + customer + '\'' +
                '}';
    }
}
