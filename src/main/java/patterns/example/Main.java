package patterns.example;

import java.io.IOException;
import java.util.List;

import static patterns.example.Movie.MovieType.*;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Customers management
        List<Rental> rentals = List.of(
                new Rental(new Movie.Builder("Rambo", REGULAR).setActors(List.of("John Wick", "Jason Statham")).build(), 1),
                new Rental(new Movie.Builder("Lord of the Rings", NEW_RELEASE).build(), 4),
                new Rental(new Movie.Builder("Harry Potter", CHILDREN).build(), 5)
        );

        List<Rental> rentals2 = List.of(
                new Rental(new Movie.Builder("Inception", Movie.MovieType.REGULAR).setActors(List.of("Leonardo DiCaprio")).build(), 3),
                new Rental(new Movie.Builder("The Shawshank Redemption", Movie.MovieType.REGULAR).setActors(List.of("Tim Robbins", "Morgan Freeman")).build(), 2)
        );

        Customer customer = new Customer("John Doe", rentals);
        Customer customer2 = new Customer("Jane Doe", rentals2);

        CustomerRegistry registry = new CustomerRegistry();
        registry.addCustomer(customer);
        registry.addCustomer(customer2);

        registry.generateStatements(new HtmlStatementBuilder());


        // Catalog
        MovieCatalog mc = new MovieCatalog();
        mc.addMovie(new Movie.Builder("Inception", DRAMA)
                .setCountry("USA")
                .setDescription("A mind-bending heist thriller")
                .setDirectors(List.of("Christopher Nolan"))
                .setActors(List.of("Leonardo DiCaprio", "Ellen Page"))
                .build());

        mc.addMovie(new Movie.Builder("The Shawshank Redemption", DRAMA)
                .setCountry("USA")
                .setDescription("Two imprisoned men bond over several years")
                .setDirectors(List.of("Frank Darabont"))
                .setActors(List.of("Tim Robbins", "Morgan Freeman"))
                .build());

        mc.addMovie(new Movie.Builder("The Dark Knight", THRILLER)
                .setCountry("USA")
                .setDescription("Batman faces a new criminal mastermind")
                .setDirectors(List.of("Christopher Nolan"))
                .setActors(List.of("Christian Bale", "Heath Ledger"))
                .build());

        mc.addMovie(new Movie.Builder("Forrest Gump", COMEDY)
                .setCountry("USA")
                .setDescription("Life is like a box of chocolates")
                .setDirectors(List.of("Robert Zemeckis"))
                .setActors(List.of("Tom Hanks", "Robin Wright"))
                .build());

        mc.addMovie(new Movie.Builder("The Matrix", THRILLER)
                .setCountry("USA")
                .setDescription("A computer hacker learns the truth about his reality")
                .setDirectors(List.of("Lana Wachowski", "Lilly Wachowski"))
                .setActors(List.of("Keanu Reeves", "Carrie-Anne Moss"))
                .build());

        mc.addMovie(new Movie.Builder("Pulp Fiction", THRILLER)
                .setCountry("USA")
                .setDescription("The lives of two mob hitmen, a boxer, and a pair of diner bandits")
                .setDirectors(List.of("Quentin Tarantino"))
                .setActors(List.of("John Travolta", "Samuel L. Jackson"))
                .build());

        mc.addMovie(new Movie.Builder("Titanic", DRAMA)
                .setCountry("USA")
                .setDescription("A love story on the ill-fated R.M.S. Titanic")
                .setDirectors(List.of("James Cameron"))
                .setActors(List.of("Leonardo DiCaprio", "Kate Winslet"))
                .build());

        mc.addMovie(new Movie.Builder("Revolutionary Road", DRAMA)
                .setCountry("USA")
                .setDescription("A couple living in the suburbs of Connecticut struggle to come to terms with their personal problems while trying to raise their two children.")
                .setDirectors(List.of("Sam Mendes"))
                .setActors(List.of("Leonardo DiCaprio", "Kate Winslet"))
                .build());

        mc.addMovie(new Movie.Builder("The Beach", THRILLER)
                .setCountry("USA")
                .setDescription("A young traveler's quest for a utopian island paradise in Thailand leads him on a journey of self-discovery.")
                .setDirectors(List.of("Danny Boyle"))
                .setActors(List.of("Leonardo DiCaprio", "Tilda Swinton", "Virginie Ledoyen"))
                .build());

        mc.addMovie(new Movie.Builder("The Godfather", THRILLER)
                .setCountry("USA")
                .setDescription("The aging patriarch of an organized crime dynasty transfers control to his reluctant son")
                .setDirectors(List.of("Francis Ford Coppola"))
                .setActors(List.of("Marlon Brando", "Al Pacino"))
                .build());

        mc.addMovie(new Movie.Builder("Avatar", REGULAR)
                .setCountry("USA")
                .setDescription("A paraplegic marine dispatched to the moon Pandora on a unique mission")
                .setDirectors(List.of("James Cameron"))
                .setActors(List.of("Sam Worthington", "Zoe Saldana"))
                .build());

        mc.addMovie(new Movie.Builder("Schindler's List", DRAMA)
                .setCountry("USA")
                .setDescription("In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution")
                .setDirectors(List.of("Steven Spielberg"))
                .setActors(List.of("Liam Neeson", "Ben Kingsley"))
                .build());

        // File IO
        mc.saveToFile("films.dat");
        mc.getMovies().clear();
        System.out.println("\nMovie Catalog after cleaning: ");
        mc.viewCatalog();
        System.out.println("\nLoading from the file...");
        mc.loadFromFile("films.dat");

        // Filtering
        System.out.println("\nCatalog from the file: ");
        mc.viewCatalog();
        System.out.println("\nA film with `Liam Neeson` and `Ben Kingsley` (list): " + mc.findByActors(List.of("Liam Neeson", "Ben Kingsley")));
        System.out.println("\nA film with `Leonardo DiCaprio` (object): " + mc.findByActor("Leonardo DiCaprio"));
        System.out.println("\nA film with `Leonardo DiCaprio` (list): " + mc.findByActors(List.of("Leonardo DiCaprio")));
        System.out.println("\nFilms with `Leonardo DiCaprio` (list): " + mc.findAllByActors(List.of("Leonardo DiCaprio")));
    }
}