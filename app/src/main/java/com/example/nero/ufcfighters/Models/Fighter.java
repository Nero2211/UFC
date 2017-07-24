package com.example.nero.ufcfighters.Models;

/**
 * Created by Nero on 09/07/2017.
 */

public class Fighter {

    private transient int position;
    private int id, wins, losses, draws;
    private String nickname, last_name, first_name, weight_class, profile_image, link, rank;

    public Fighter(int id, String nickname, String last_name, String first_name,
                   int wins, int losses, int draws, String weight_class, String image,
                   String link, String rank){
        this.id = id;
        this.nickname = nickname;
        this.last_name = last_name;
        this.first_name = first_name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.weight_class = weight_class;
        this.profile_image = image;
        this.link = link;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getLink() {
        return link;
    }
}
