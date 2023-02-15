package com.example.adro;

import java.sql.Date;

public class AdminMovie {
    private String title,description,genre,language,image_path;

    private int duration;

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    public float getImdb() {
        return imdb;
    }

    public void setImdb(float imdb) {
        this.imdb = imdb;
    }

    public AdminMovie(String title, String image_path) {
        this.title = title;
        this.image_path = image_path;
    }

    private int yearRelease;

    private float imdb;

    private int numberTickets;

    private String session;

    private Date startDate, endDate;

    private int price;

    private int id;

    public AdminMovie(){}

    public AdminMovie(String title, String description, String genre, String language, int duration, int numberTickets, String session, Date startDate, Date endDate, int price) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.numberTickets = numberTickets;
        this.session = session;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public AdminMovie(String title, String description, String genre, String language, int duration, int numberTickets, String session, Date startDate, Date endDate, int price, int id) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.numberTickets = numberTickets;
        this.session = session;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(int numberTickets) {
        this.numberTickets = numberTickets;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return 0;
    }
}
