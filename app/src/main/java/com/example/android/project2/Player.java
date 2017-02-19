package com.example.android.project2;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class Player {
    private int number, score;

    public Player(int nr){this.number = nr;}

    public void setNR(int nr) {this.number = nr;}
    public int getNumber() {return number;}

    public void setScore(int score) {this.score = score;}
    public int getScore() {return score;}
}
