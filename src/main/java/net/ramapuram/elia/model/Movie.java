package net.ramapuram.elia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Movie Value Object which stores infromation about the Movie.
 * Created by Elia Thomas Ramapuram on 29/09/16.
 * as part of the Computer Science Project 2016-17
 */
public class Movie implements Serializable {
    private String name;
    private int copies;
    private String[] actorList;
    private List<String> reviews = new ArrayList<String>();
    private List<Rental> rentalList = new ArrayList<Rental>();

    public Movie(String name, int copies, String[] actorList) {
        this.name = name;
        this.copies = copies;
        this.actorList = actorList;
    }

    public Movie(String name, int copies, String[] actorList, String[] reviews, ArrayList<Rental> rentals) {
        this.name = name;
        this.copies = copies;
        this.actorList = actorList;
        this.reviews = Arrays.asList(reviews);
        this.rentalList = rentals;
    }

    public String getName() {
        return name;
    }


    public int getCopies() {
        return copies;
    }

    public String[] getActorList() {
        return actorList;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void addReview(String review){
        reviews.add(review);
    }

    public List<Rental> getActiveRentalList() {
        List<Rental> rv = new ArrayList<Rental>();
        for (Rental rental : rentalList) {
            if (rental.isActive()) {
                rv.add(rental);
            }
        }
        return rv;
    }

    public boolean isRentedTo(String customerName) {
        boolean rv = false;
        for (Rental rental : getActiveRentalList()) {
            if (rental.getCustomer().equals(customerName)) {
                rv = true;
                break;
            }
        }
        return rv;
    }

    public void rentMovie(String customer) {
        Rental rental = new Rental(customer);
        rentalList.add(rental);

    }

    public void returnMovie(String customerName) {
        for(Rental rental: getActiveRentalList()){
            if(rental.getCustomer().equals(customerName)){
                rental.setReturnedOn(new Date());
            }
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "\n\tname='" + name + "\'" +
                ", \n\tcopies=" + copies +
                ", \n\tactorList=" + Arrays.toString(actorList) +
                ", \n\treviews=" + reviews +
                ", \n\trentalList=" + rentalList +
                "\n}";
    }

}
