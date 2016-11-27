package net.ramapuram.elia;

import net.ramapuram.elia.manager.MovieRentalManager;
import net.ramapuram.elia.manager.impl.MovieRentalManagerImpl;
import net.ramapuram.elia.model.Movie;

import java.util.*;

/**
 * Created by Elia Thomas Ramapuram on 21/10/16.
 * as part of the Computer Science Project 2016-17
 */
public class MovieCli {

    private static final int EXIT                   =  1;
    private static final int AVAILABLE_MOVIES       =  2;
    private static final int ALL_MOVIES             =  3;
    private static final int VIEW_MOVIE_DETAILS     =  4;
    private static final int ADD_MOVIE              =  5;
    private static final int REMOVE_MOVIE           =  6;
    private static final int REMOVE_ALL_MOVIES      =  7;
    private static final int RENT_MOVIE             =  8;
    private static final int RETURN_MOVIE           =  9;
    private static final int LOAD_DATABASE          = 10;
    private static final int SAVE_DATABASE          = 11;
    private static final int LOAD_JSON_DATABASE     = 12;
    private static final int SAVE_JSON_DATABASE     = 13;


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
                case REMOVE_ALL_MOVIES:
                    manager.deleteAll();
                    break;
                case LOAD_JSON_DATABASE:
                    manager.loadJsonFile();
                    break;
                case SAVE_JSON_DATABASE:
                    manager.saveJsonFile();
                    break;
                default:
                    System.out.format("There was a problem with your choice.  Try Again or type %d to exit\n", EXIT);
            }

        }

    }

    private static void printMenu() {
        Map<Integer, String> menuMap = new LinkedHashMap<Integer, String>();
        menuMap.put(EXIT,                   "Exit");
        menuMap.put(AVAILABLE_MOVIES,       "Available Movies");
        menuMap.put(ALL_MOVIES,             "All Movies");
        menuMap.put(VIEW_MOVIE_DETAILS,     "View Movie Details");
        menuMap.put(ADD_MOVIE,              "Add Movie");
        menuMap.put(REMOVE_MOVIE,           "Remove Movie");
        menuMap.put(REMOVE_ALL_MOVIES,      "Remove All Movies");
        menuMap.put(RENT_MOVIE,             "Rent Movie");
        menuMap.put(RETURN_MOVIE,           "Return Movie");
        menuMap.put(LOAD_DATABASE,          "Load Database");
        menuMap.put(SAVE_DATABASE,          "Save Database");
        menuMap.put(LOAD_JSON_DATABASE,     "Load Dtabase from JSON File");
        menuMap.put(SAVE_JSON_DATABASE,     "Save Database in JSON Format");

        String deco = Util.repeat('=', 80);
        //The statement prints '=' 80 times then prints 'Menu' then prints '=' 80 times
        System.out.printf("\n%sMenu%s\n", deco,deco);  //printf prints string in a fromatted fashion.
        System.out.println("Enter your Choice");
        int count = 0;
        //Iterating through a set of Entries.   Entry is a data type which has a key and a value.
        //An example of the new for loop.  which has 2 parts seperated by ':'
        //the first part defines the type of one element.  The second part is the collection of the elemts
        //The loop iteretates through each of the elements in the collection assigiing each element to the
        // variable defined in the first part of the for loop.
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
        System.out.println(Util.repeat('=',166));
        System.out.print("|");
        for(int x=0; x<5;x++){
            System.out.printf("%26s:%2s:%2s|", "Movie Name", "A", "T");
        }
        System.out.println();
        System.out.println(Util.repeat('=',166));
        System.out.print("|");
        for (Movie movie : movieList) {
            count++;
            String movieName = movie.getName();
            if(movieName.length() > 27){
                movieName = movieName.substring(0, 23) + "...";
            }
            System.out.printf("%26s:%2d:%2d|", movieName, movie.getAvailableCopies(), movie.getCopies());
            if(count%5 == 0){
                System.out.println();
                System.out.print("|");
            }
        }
        System.out.println();
        System.out.println(Util.repeat('=',166));
    }

    private static void exit() {
        System.out.println("Exit");
        System.exit(0);//0 means we exited the program normally
    }

    private static void availableMovies() {
        System.out.println("Available Movies");
        List<Movie> availableMovies = manager.getAvailableMovies();
        displayMovies(availableMovies);
    }

    private static void addMovie() {
        System.out.println("Type Name of the Movie:");
        String movieName = getInput();
        System.out.println("Type Number of copies");
        Integer copies = Integer.parseInt(getInput());
        System.out.println("Enter Actors seperated by ',' ");
        String actorInput = getInput();
        String[] actorList = actorInput.split(",");
        Movie movie = new Movie(movieName, copies, actorList);
        manager.addMovie(movie);
        System.out.println("Add Movies");

    }

    private static void allMovies() {
        System.out.println("All Movies");
        List<Movie> allMovies = manager.getAllMovies();
        displayMovies(allMovies);
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
        try {
            manager.returnMovie(movie, customer, review);
            System.out.printf("The movie %s was returned by %s", movie, customer);
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
    }

    private static void rentMovie() {
        System.out.println("Enter Movie");
        String movie = getInput();
        System.out.println("Enter Customer");
        String customer = getInput();
        try{
            manager.rentMovie(movie, customer);
            System.out.printf("Movie %s rented to %s\n", movie, customer);
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
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
        if(selectedMovie!=null){
            System.out.println(selectedMovie.toString());
        } else {
            System.out.println("No Such Movie");
        }
    }

    private static String getInput(){
        Scanner sc = new Scanner(System.in);
        String rv = sc.nextLine();
        return rv;
    }

}
