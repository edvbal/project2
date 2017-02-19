package com.example.android.project2;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class Player {
    private int number, score;
    private String team;

    public Player(int nr,String team){this.number = nr; this.team = team;}

    public void setNR(int nr) {this.number = nr;}
    public int getNumber() {return number;}

    public void setTeam(String team) {this.team = team;}
    public String getTeam() {return team;}

    public void setScore(int score) {this.score = score;}
    public int getScore() {return score;}
}
