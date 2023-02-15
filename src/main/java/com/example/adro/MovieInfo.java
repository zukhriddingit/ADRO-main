package com.example.adro;

import java.util.Date;

public class MovieInfo {
    private String title, session;
    private Date date;
    private int numberTickets;

    public MovieInfo(String title, String session, Date date, int numberTickets) {
        this.title = title;
        this.session = session;
        this.date = date;
        this.numberTickets = numberTickets;
    }

    public String getTitle() {
        return title;
    }

    public MovieInfo(String title, Date date, int numberTickets) {
        this.title = title;
        this.date = date;
        this.numberTickets = numberTickets;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(int numberTickets) {
        this.numberTickets = numberTickets;
    }
}
