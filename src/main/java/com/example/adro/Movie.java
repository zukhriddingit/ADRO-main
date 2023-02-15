package com.example.adro;

import java.util.Date;


public class Movie {
    String name, theatre, language, session;
    Date time;
    int tickets_num, price;

    int id;




    public Movie(String name,int id, String language, Date time,String session,int price) {
        this.name = name;
        this.id = id;
        this.language = language;
        this.price = price;
        this.time = time;
        this.session=session;
    }

    public Movie(String movie_name, int movie_id, int tickets_num, int price) {
        this.name = movie_name;
        this.id = movie_id;
        this.tickets_num = tickets_num;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTickets_num(int tickets_num) {
        this.tickets_num = tickets_num;
    }

    public int getTickets_num() {
        return tickets_num;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getTheatre() {
        return theatre;
    }
}
