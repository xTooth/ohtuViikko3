package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private String course;
    private ArrayList<Integer> exercises;
    private int hours;

    public void setCourse(String course) {
        this.course = course;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCourse() {
        return course;
    }

    public ArrayList<Integer> getExercises() {
        return exercises;
    }

    public int getHours() {
        return hours;
    }
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return course + ", viikko "+ week +" tehtyjä tehtäviä yhteensä " + this.exercises.size() + " käytetyt tunnit: " + hours + "tehävät: " + this.exercises.toString();
    }
    
}