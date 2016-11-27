package net.ramapuram.elia.manager.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import net.ramapuram.elia.manager.MovieRentalManager;
import net.ramapuram.elia.model.Movie;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Elia Thomas Ramapuram on 29/09/16.
 * as part of the Computer Science Project 2016-17
 */
public class MovieRentalManagerImpl implements MovieRentalManager {
    private Map<String, Movie> data = new HashMap<String, Movie>();
    public static final String FILE_PATH_JSON = System.getProperty("user.home") + File.separator + ".moviedb" + File.separator + "movies.json";
    public static final String FILE_PATH_BIN = System.getProperty("user.home") + File.separator + ".moviedb" + File.separator + "movies.ser";

    public Movie getMovie(String name) {
        return data.get(name);
    }

    public int getTotalNumberOfMovies() {
        return data.entrySet().size();
    }

    public void removeMovie(String name) {
        data.remove(name);
    }

    public void addMovie(Movie movie) {
        data.put(movie.getName(), movie);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(data.values());
    }

    public List<Movie> getAvailableMovies() {
        List<Movie> rv = new ArrayList<Movie>();
        for (Movie movie : data.values()) {
            if (movie.getCopies() > movie.getActiveRentalList().size()) {
                rv.add(movie);
            }
        }
        return rv;
    }

    public void rentMovie(String movieName, String customerName) {
        Movie movie = getMovie(movieName);
        if (movie == null) {
            throw new RuntimeException("Movie Not Found");
        }
        movie.rentMovie(customerName);
    }

    public void returnMovie(String movieName, String customerName, String review) {
        Movie movie = getMovie(movieName);
        if (movie == null) {
            throw new RuntimeException("Movie Not Found");
        }
        movie.returnMovie(customerName);
        if (review.trim() != "") {
            movie.addReview(review);
        }
    }

    public void loadDatabase() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_PATH_BIN);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (HashMap<String, Movie>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public void deleteAll() {
        data = new HashMap<String, Movie>();
    }

    @Override
    public void saveDatabase() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(FILE_PATH_BIN);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void loadJsonFile() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FILE_PATH_JSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader reader = new JsonReader(fileReader);

        Type listType = new TypeToken<HashMap<String, Movie>>() {
        }.getType();
        data = new Gson().fromJson(reader, listType);
    }

    @Override
    public void saveJsonFile() {
        File file = new File(FILE_PATH_JSON);

        // if file does not exists, then create it
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(gson.toJson(data));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
