package patterns.example;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MovieCatalog {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void viewCatalog() {
        if(movies.isEmpty()) System.out.println("Empty catalog");
        for(Movie movie: movies) {
            System.out.println(movie.prettify());
        }
    }

    public Movie findByTitle(String title) {
        return movies.stream()
                .filter((m) -> m.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByTitle(String title) {
        return movies.stream()
                .filter((m) -> m.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public Movie findByDescription(String description) {
        return movies.stream()
                .filter((m) -> m.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByDescription(String description) {
        return movies.stream()
                .filter((m) -> m.getDescription().equalsIgnoreCase(description))
                .collect(Collectors.toList());
    }

    public Movie findByCountry(String country) {
        return movies.stream()
                .filter((m) -> m.getCountry().equalsIgnoreCase(country))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByCountry(String country) {
        return movies.stream()
                .filter((m) -> m.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    public Movie findByMovieType(Movie.MovieType priceCode) {
        return movies.stream()
                .filter((m) -> m.getPriceCode().equals(priceCode))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByMovieType(Movie.MovieType priceCode) {
        return movies.stream()
                .filter((m) -> m.getPriceCode().equals(priceCode))
                .collect(Collectors.toList());
    }

    public Movie findByActor(String actor) {
        return movies.stream()
                .filter(movie -> movie.getActors().stream().anyMatch(a-> a.contains(actor)))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByActor(String actor) {
        return movies.stream()
                .filter(movie -> movie.getActors().stream().anyMatch(a-> a.contains(actor)))
                .collect(Collectors.toList());
    }

    public Movie findByActors(List<String> actors) {
        return movies.stream()
                .filter((m) -> new HashSet<>(m.getActors()).containsAll(actors))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByActors(List<String> actors) {
        return movies.stream()
                .filter((m) -> new HashSet<>(m.getActors()).containsAll(actors))
                .collect(Collectors.toList());
    }

    public Movie findByDirectors(List<String> directors) {
        return movies.stream()
                .filter((m) -> m.getDirectors().contains(directors))
                .findFirst()
                .orElse(null);
    }

    public List<Movie> findAllByDirectors(List<String> directors) {
        return movies.stream()
                .filter((m) -> m.getDirectors().contains(directors))
                .collect(Collectors.toList());
    }

    public void updateMoviePriceCode(String title, Movie.MovieType newPriceCode) {
        movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(movie -> movie.setPriceCode(newPriceCode));
    }

    public void saveToFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        for (Movie obj : movies) {
            objOutputStream.writeObject(obj);
            objOutputStream.reset();
        }
        objOutputStream.close();
    }

    public void loadFromFile(String filename) throws ClassNotFoundException, IOException {
        List<Movie> localMovies = new ArrayList<>();
//        FileInputStream fis = new FileInputStream(filename);
//        ObjectInputStream obj = new ObjectInputStream(fis);
//        try {
//            while (fis.available() != -1) {
//                Movie acc = (Movie) obj.readObject();
//                localMovies.add(acc);
//            }
//        } catch (EOFException e) {
//            e.printStackTrace();
//        }

        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Movie movie = (Movie) obj.readObject();
                    localMovies.add(movie);
                } catch (EOFException e) {
                    // stop
                    break;
                }
            }
        }

        movies.addAll(localMovies);
    }

}
