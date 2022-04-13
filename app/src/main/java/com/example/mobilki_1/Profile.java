package com.example.mobilki_1;

import java.util.Comparator;
import java.util.Date;

public class Profile {
    String name;
    int score;
    Date lastGameDate;

    public Profile() {
    }

    public Profile(String name, int score) {
        this.name = name;
        this.score = score;
        this.lastGameDate = new Date();
    }

    public static Comparator<Profile> scoreComparator = new Comparator<Profile>() {
        public int compare(Profile profile1, Profile profile2) {
            int score1 = profile1.score;
            int score2 = profile2.score;
            return score2-score1;
        }};
}
