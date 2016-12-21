package net.ramapuram.elia.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The Rental Details of the Movie
 * Created by Elia Thomas Ramapuram on 29/09/16.
 * as part of the Computer Science Project 2016-17
 */
public class Rental implements Serializable {
    /**
     * The Day it was rented on
     */
    private Date rentedOn;
    /**
     * The Day the movie was returned
     */
    private Date returnedOn;
    /**
     * The Customer who rented the movie.
     */
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
                "\n\t\trentedOn=" + rentedOn +
                ",\n\t\treturnedOn=" + returnedOn +
                ",\n\t\tcustomer='" + customer + '\'' +
                "\n\t}";
    }
}
