package net.ramapuram.elia.manager;
import net.ramapuram.elia.model.Movie;

import java.util.List;

/**
 * Created by Elia Thomas Ramapuram on 29/09/16.
 * as part of the Computer Science Project 2016-17
 */
public interface MovieRentalManager {
    int getTotalNumberOfMovies();
    Movie getMovie(String name);
    void removeMovie(String name);
    void rentMovie(String movieName, String customer);
    void returnMovie(String movieName, String customer, String review);
    void addMovie(Movie movie);
    List<Movie> getAvailableMovies();
    List<Movie> getAllMovies();
    void loadDatabase();
    void saveDatabase();
    void deleteAll();
}
