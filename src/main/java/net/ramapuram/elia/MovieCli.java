package net.ramapuram.elia;

import net.ramapuram.elia.manager.MovieRentalManager;
import net.ramapuram.elia.manager.impl.MovieRentalManagerImpl;
import net.ramapuram.elia.model.Movie;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Elia Thomas Ramapuram on 21/10/16.
 * as part of the Computer Science Project 2016-17
 */
public class MovieCli {
    private final static Logger LOGGER = Logger.getLogger(MovieCli.class.getName());


    private static final int EXIT = 10;
    private static final int VIEW_MOVIE_DETAILS = 1;
    private static final int REMOVE_MOVIE = 2;
    private static final int RENT_MOVIE = 3;
    private static final int ADD_MOVIE = 4;
    private static final int AVAILABLE_MOVIES = 5;
    private static final int ALL_MOVIES = 6;
    private static final int LOAD_DATABASE = 7;
    private static final int SAVE_DATABASE = 8;
    private static final int RETURN_MOVIE = 9;

    private static MovieRentalManager manager = new MovieRentalManagerImpl();


    public static void main(String[] args) {
        loadDatabase();
        mainLoop();
    }

    private static void mainLoop() {
        while (true) {
            printMenu();
            int i = 0;
                i = Integer.parseInt(getInput());
            switch (i) {
                case EXIT:
                    exit();
                    break;
                case VIEW_MOVIE_DETAILS:
                    viewMovieDetails();
                    break;
                case REMOVE_MOVIE:
                    removeMovie();
                    break;
                case RENT_MOVIE:
                    rentMovie();
                    break;
                case ADD_MOVIE:
                    addMovie();
                    break;
                case AVAILABLE_MOVIES:
                    availableMovies();
                    break;
                case ALL_MOVIES:
                    allMovies();
                    break;
                case LOAD_DATABASE:
                    loadDatabase();
                    break;
                case SAVE_DATABASE:
                    saveDatabase();
                    break;
                case RETURN_MOVIE:
                    returnMovie();
                    break;
                default:
                    System.out.format("There was a problem with your choice.  Try Again or type %d to exit\n", EXIT);
            }

        }

    }

    private static void printMenu() {
        int count = 0;
        Map<Integer, String> menuMap = new HashMap<Integer, String>() {{
            put(10, "Exit");
            put(1, "View Movie Details");
            put(2, "Remove Movie");
            put(3, "Rent Movie");
            put(4, "Add Movie");
            put(5, "Available Movies");
            put(6, "All Movies");
            put(7, "Load Database");
            put(8, "Save Database");
            put(9, "Return Movie");
        }};
        String deco = Util.repeat('=', 80);
        System.out.printf("\n%sMenu%s\n", deco,deco);
        System.out.println("Enter your Choice");
        for(Map.Entry<Integer, String> entry : menuMap.entrySet()){
            count++;
            System.out.printf("%2d : %-30s",  entry.getKey(),entry.getValue());
            if(count%5 == 0){
                System.out.println();
            }
        }
        System.out.printf("\n%sMenu%s\n", deco,deco);
    }

    private static void displayMovies(List<Movie> movieList) {
        int count = 0;
        System.out.println(Util.repeat('=',120));
        System.out.printf("|%30s|%7s|", "Movie Name", "Copies");
        System.out.printf("|%30s|%7s|", "Movie Name", "Copies");
        System.out.printf("|%30s|%7s|\n", "Movie Name", "Copies");
        System.out.println(Util.repeat('=',120));
        for (Movie movie : manager.getAllMovies()) {
            count++;
            String movieName = movie.getName();
            if(movieName.length() > 30){
                movieName = movieName.substring(0, 27) + "...";
            }
            System.out.printf("|%30s|%7d|", movieName, movie.getCopies());
            if(count%3 == 0){
                System.out.println();
            }
        }
        System.out.println();
        System.out.println(Util.repeat('=',120));
    }

    private static void exit() {
        System.out.println("Exit");
        System.exit(0);
    }

    private static void availableMovies() {
        System.out.println("Available Movies");
        displayMovies(manager.getAvailableMovies());
    }

    private static void addMovie() {
        System.out.println("Type Name of the Movie:");
        String movieName = getInput();
        System.out.println("Type Number of copies");
        Integer copies = Integer.parseInt(getInput());
        System.out.println("Enter Actors seperated by ',' ");
        String actorInput = getInput();
        String[] actorList = actorInput.split(",");
        manager.addMovie(new Movie(movieName, copies, actorList));
        System.out.println("Add Movies");

    }

    private static void allMovies() {
        System.out.println("All Movies");
        displayMovies(manager.getAllMovies());
    }

    private static void saveDatabase() {
        manager.saveDatabase();
        System.out.println("Database was Saved");
    }

    private static void loadDatabase() {
        manager.loadDatabase();
        System.out.println("Database Loaded");
    }

    private static void returnMovie() {
        System.out.println("Enter Movie");
        String movie = getInput();
        System.out.println("Enter Customer");
        String customer = getInput();
        System.out.println("Enter Review or Enter empty String for no review");
        String review = getInput();
        manager.returnMovie(movie, customer, review);
        System.out.printf("The movie %s was returned by %s", movie, customer);
    }

    private static void rentMovie() {
        System.out.println("Enter Movie");
        String movie = getInput();
        System.out.println("Enter Customer");
        String customer = getInput();
        manager.rentMovie(movie, customer);
        System.out.printf("Movie %s rented to %s\n", movie, customer);
    }

    private static void removeMovie() {
        System.out.println("Enter name of movie to remove: ");
        String movie = getInput();
        manager.removeMovie(movie);
        System.out.printf("Movie %s removed", movie);
    }

    private static void viewMovieDetails() {
        System.out.println("Type name of Movie");
        String movieName = getInput();
        Movie selectedMovie = manager.getMovie(movieName);
        System.out.println(selectedMovie.toString());
    }

    private static String getInput(){
        Scanner sc = new Scanner(System.in);
        String rv = sc.nextLine();
        return rv;
    }

}
