package net.ramapuram.elia.manager.impl;

import net.ramapuram.elia.manager.MovieRentalManager;
import net.ramapuram.elia.model.Movie;
import net.ramapuram.elia.model.Rental;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Elia Thomas Ramapuram on 01/10/16.
 * as part of the Computer Science Project 2016-17
 */
public class MovieRentalManagerImplTest {
    private static final String SLEEPING_BEAUTY = "Sleeping Beauty";
    public static final String EAST_OF_EDEN = "East of Eden";
    String[] myArray = new String[]{"Thomas", "Geeta"};
    MovieRentalManager manager;
    Movie[] movies = new Movie[]{
            new Movie(
                    SLEEPING_BEAUTY,2, new String[]{},  new String[]{"Very Good","Not Bad"},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Pulp Fiction",             3, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{
                        add(new Rental("Thomas"));
                        add(new Rental("Geeta"));
                        add(new Rental("Elia"));
                    }}
            ),
            new Movie(
                    "Superman",                 7, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Spiderman",                  1, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Batman",                     3, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Casablanca",                 6, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Gone with the wind",         8, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Fight Club",                 5, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Avengers",                   2, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Jungle Book",                3, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Alladin",                    6, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Mulan",                      7, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Cinderella",                 9, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "The Beauty and the Beast",   5, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{}}
            ),
            new Movie(
                    "My Fair Lady",               2, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "Modern Times",               4, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            ),
            new Movie(
                    "City Lights",                3, new String[]{}, new String[]{},
                    new ArrayList<Rental>(){{

                    }}
            )
};

    @Before
    public void setUp() throws Exception {
        manager = new MovieRentalManagerImpl();
        for(Movie movie : movies){
            manager.addMovie(movie);
        }
    }

    @Test
    public void getMovie() throws Exception {
        Movie movie = manager.getMovie(SLEEPING_BEAUTY);
        assertSame(movie, movies[0]);
    }

    @Test
    public void discontinueMovie() throws Exception {
        assertEquals(17, manager.getTotalNumberOfMovies());
        manager.removeMovie(SLEEPING_BEAUTY);
        assertEquals(16, manager.getTotalNumberOfMovies());
        assertNull(manager.getMovie(SLEEPING_BEAUTY));
    }

    @Test
    public void addRental() throws Exception {
        manager.rentMovie(SLEEPING_BEAUTY, "Thomas");
    }

    @Test
    public void addMovie() throws Exception {
        assertEquals(17, manager.getTotalNumberOfMovies());
        manager.addMovie(new Movie(EAST_OF_EDEN, 5, new String[]{}, new String[]{}, new ArrayList<Rental>()));
        assertEquals(18, manager.getTotalNumberOfMovies());
        assertNotNull(manager.getMovie(EAST_OF_EDEN));

    }

    @Test
    public void getAvailableMovies() throws Exception {
        assertEquals(16, manager.getAvailableMovies().size());
    }

    @Test
    public void getAllMovies() throws Exception {
        assertEquals(17, manager.getAllMovies().size());
    }

    @Test
    public void loadDatabase() throws Exception {
        assertEquals(17, manager.getTotalNumberOfMovies());
        manager.deleteAll();
        assertEquals(0, manager.getTotalNumberOfMovies());
        manager.loadDatabase();
        assertEquals(17, manager.getTotalNumberOfMovies());
    }

    @Test
    public void saveDatabase() throws Exception {
        manager.saveDatabase();

    }

}