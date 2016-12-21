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
    /**
     * The Name of the Movie
     */
    private String name;
    /**
     * The Number of Copies of the movie are there in the store
     */
    private int copies;
    /**
     * The Actors in the Movie
     */
    private String[] actorList;
    /**
     * The Reviews of the movie
     */
    private List<String> reviews = new ArrayList<>();
    /**
     * The Rental Details of the Movie
     */
    private List<Rental> rentalList = new ArrayList<>();

    /**
     * Constructor for the Movie Value Object
     * @param name the name of the movie
     * @param copies the number of copies of the movie
     * @param actorList actors acting in the movie
     */
    public Movie(String name, int copies, String[] actorList) {
        this.name = name;
        this.copies = copies;
        this.actorList = actorList;
    }

    /**
     * Constructor for the Movie Value Object
     * @param name the name of the movie
     * @param copies the number of copies of the movie
     * @param actorList actors acting in the movie
     * @param reviews reviews of the movie
     * @param rentals rentals of the movie
     */
    public Movie(String name, int copies, String[] actorList, String[] reviews, ArrayList<Rental> rentals) {
        this.name = name;
        this.copies = copies;
        this.actorList = actorList;
        this.reviews = Arrays.asList(reviews);
        this.rentalList = rentals;
    }

    /**
     * Modifies the number of copies the movie has after the movie has been created
     * @param copies the number of copies
     */
    public void setCopies(int copies){
        this.copies = copies;
    }

    /**
     * Modifies the actors in the move after the movie has been created
     * @param actorList A list of actors acting in the movie
     */
    public void setActorList(String[] actorList){
        this.actorList = actorList;
    }

    /**
     * Returns the name of the movie
     * @return the name of the movie
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the number of copies of the movie that the store has
     * @return the number of copies of the movie that the store has.
     */
    public int getCopies() {
        return copies;
    }

    /**
     * Returns the list of actors in the movie
     * @return the list of actors in the movie
     */
    public String[] getActorList() {
        return actorList;
    }

    /**
     * Returns the reviews of the movie.
     * @return the reviews of other regarding the movie
     */
    public List<String> getReviews() {
        return reviews;
    }

    /**
     * Returns the rental details of theis movie
     * @return a list of Rental Details of the movie
     */
    public List<Rental> getRentalList() {
        return rentalList;
    }

    public int getAvailableCopies(){
        return (copies - getActiveRentalList().size());
    }

    public void addReview(String review){
        reviews.add(review);
    }

    /**
     * Active Rental List is a list of Rentals that are currently in circulation and not yet returnd
     * @return a List of Rental Details of copies of the current movie that is currently in circulation.
     */
    public List<Rental> getActiveRentalList() {
        List<Rental> returnValue = new ArrayList<>();
        for (Rental rental : rentalList) {
            if (rental.isActive()) {
                returnValue.add(rental);
            }
        }
        return returnValue;
    }

    public boolean isRentedTo(String customerName) {
        boolean returnValue = false;
        for (Rental rental : getActiveRentalList()) {
            if (rental.getCustomer().equals(customerName)) {
                returnValue = true;
                break;
            }
        }
        return returnValue;
    }

    public void rentMovie(String customer) {
        Rental rental = new Rental(customer);
        rentalList.add(rental);

    }

    public void returnMovie(String customerName) {
        for(Rental rental: getActiveRentalList()){
            if(rental.getCustomer().equals(customerName)){
                rental.setReturnedOn(new Date());
                break;
            }
        }
    }

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
