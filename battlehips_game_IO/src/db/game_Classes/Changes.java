package db.game_Classes;

import sample.CellToDB;

import java.util.ArrayList;

public class Changes {

    ArrayList<CellToDB> p1ChangesArray;
    ArrayList<CellToDB> p2ChangesArray;

    public ArrayList<CellToDB> getP2ChangesArray() {
        return p2ChangesArray;
    }

    public void setP2ChangesArray(ArrayList<CellToDB> p2ChangesArray) {
        this.p2ChangesArray = p2ChangesArray;
    }

    public Changes(ArrayList<CellToDB> p1ChangesArray, ArrayList<CellToDB> p2ChangesArray) {
        this.p1ChangesArray = p1ChangesArray;
        this.p2ChangesArray = p2ChangesArray;
    }

    public Changes(){}

    public ArrayList<CellToDB> getP1ChangesArray() {
        return p1ChangesArray;
    }

    public void setP1ChangesArray(ArrayList<CellToDB> p1ChangesArray) {
        this.p1ChangesArray = p1ChangesArray;
    }

    @Override
    public String toString() {
        return "Changes{" +
                "p1ChangesArray=" + p1ChangesArray +
                ", p2ChangesArray=" + p2ChangesArray +
                '}';
    }
}
