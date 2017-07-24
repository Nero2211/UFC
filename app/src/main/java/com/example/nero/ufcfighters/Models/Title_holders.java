package com.example.nero.ufcfighters.Models;

/**
 * Created by Nero on 09/07/2017.
 */

public class Title_holders {

    private transient int postion;
    private String nickname;
    private String first_name;
    private String last_name;
    private int id;
    private int statid;
    private int wins;
    private int losses;
    private int draws;
    private String rank;
    private String fighter_status;
    private String belt_thumbnail;
    private String profile_image;
    private String weight_class;
    private String link;

    public Title_holders(int id, int statid, String nickname, String firstname, String lastname,
                         int wins, int losses, int draws, String rank, String status, String thumbnail,
                         String profilePic, String weightClass, String link){
        this.id = id;
        this.statid = statid;
        this.nickname = nickname;
        this.first_name = firstname;
        this.last_name = lastname;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.rank = rank;
        this.fighter_status = status;
        this.belt_thumbnail = thumbnail;
        this.profile_image = profilePic;
        this.weight_class = weightClass;
        this.link = link;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getId() {
        return id;
    }

    public int getStatid() {
        return statid;
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

    public String getRank() {
        return rank;
    }

    public String getFighter_status() {
        return fighter_status;
    }

    public String getBelt_thumbnail() {
        return belt_thumbnail;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public String getLink() {
        return link;
    }
}
