package com.example.android.project2;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class Player {
    private int number, score;
    private String team;

    public Player(int nr,String team, int score){this.number = nr; this.team = team; this.score = score;}

    public void setNR(int nr) {this.number = nr;}
    public int getNumber() {return number;}

    public void setTeam(String team) {this.team = team;}
    public String getTeam() {return team;}

    public void setScore(int score) {this.score = score;}
    public int getScore() {return score;}

    /**
     * Overriding equals(Object o) to make it compare objects by their unique number
     *  credits : aioobe http://stackoverflow.com/questions/6737212/how-to-find-duplicates-in-an-arraylistobject
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (number != player.number) return false;

        return true;
    }
}
