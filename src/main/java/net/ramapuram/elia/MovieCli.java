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
    private static final int SERIALIZE_DATABASE     = 12;
    private static final int DE_SERIALIZE_DATABASE  = 13;


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
                default:
                    System.out.format("There was a problem with your choice.  Try Again or type %d to exit\n", EXIT);
            }

        }

    }

    private static void printMenu() {
        int count = 0;
        Map<Integer, String> menuMap = new LinkedHashMap<Integer, String>() {{
            put(EXIT,                   "Exit");
            put(VIEW_MOVIE_DETAILS,     "View Movie Details");
            put(REMOVE_MOVIE,           "Remove Movie");
            put(RENT_MOVIE,             "Rent Movie");
            put(ADD_MOVIE,              "Add Movie");
            put(AVAILABLE_MOVIES,       "Available Movies");
            put(ALL_MOVIES,             "All Movies");
            put(LOAD_DATABASE,          "Load Database");
            put(SAVE_DATABASE,          "Save Database");
            put(RETURN_MOVIE,           "Return Movie");
//            put(SERIALIZE_DATABASE,     "Serialize Database");
//            put(DE_SERIALIZE_DATABASE,  "Deserialize Database");
            put(REMOVE_ALL_MOVIES,      "Remove All Movies");
        }};
        menuMap = sortMap(menuMap);
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

    private static Map<Integer,String> sortMap(Map<Integer, String> menuMap) {
        List<Map.Entry<Integer,String>> entries =
                new ArrayList<Map.Entry<Integer, String>>(menuMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer,String>>() {
            public int compare(Map.Entry<Integer,String> a, Map.Entry<Integer,String> b){
                return a.getKey().compareTo(b.getKey());
            }
        });
        Map<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
        for (Map.Entry<Integer,String> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private static void displayMovies(List<Movie> movieList) {
        int count = 0;
        System.out.println(Util.repeat('=',166));
        System.out.print("|");
        for(int x=0; x<5;x++){
            System.out.printf("%29s:%2s|", "Movie Name", "No");
        }
        System.out.println();
        System.out.println(Util.repeat('=',166));
        System.out.print("|");
        movieList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Movie movie : movieList) {
            count++;
            String movieName = movie.getName();
            if(movieName.length() > 30){
                movieName = movieName.substring(0, 26) + "...";
            }
            System.out.printf("%29s:%2d|", movieName, movie.getCopies());
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
