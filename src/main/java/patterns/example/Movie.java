package patterns.example;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private MovieType priceCode;
    private String country;
    private String description;
    private List<String> directors;
    private List<String> actors;

    public enum MovieType {
        REGULAR, NEW_RELEASE, CHILDREN, DRAMA, COMEDY, THRILLER
    }

    public Movie(Builder builder) {
        this.title = builder.title;
        this.priceCode = builder.priceCode;
        this.country = builder.country;
        this.description = builder.description;
        this.directors = builder.directors;
        this.actors = builder.actors;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setPriceCode(MovieType priceCode) {
        this.priceCode = priceCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String country) {
        this.description = description;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return this.prettify();
    }

    public String prettify() {
        return new StringBuilder()
                .append("Movie(")
                .append("Title: ")
                .append(title)
                .append(",\tPriceCode: ")
                .append(priceCode)
                .append(",\tCountry: ")
                .append(country)
                .append(",\tDescription: ")
                .append(description)
                .append(",\tDirectors: ")
                .append(directors)
                .append(",\tActors: ")
                .append(actors)
                .append(")")
                .toString();
    }

    public static class Builder {
        private String title;
        private MovieType priceCode;
        private String country;
        private String description;
        private List<String> directors;
        private List<String> actors;

        public Builder(String title, MovieType priceCode) {
            this.title = title;
            this.priceCode = priceCode;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDirectors(List<String> directors) {
            this.directors = directors;
            return this;
        }

        public Builder setActors(List<String> actors) {
            this.actors = actors;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}