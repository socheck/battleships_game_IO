package db.game_Classes;

import sample.CellToDB;

import java.util.ArrayList;

public class InitialState {
    private ArrayList<CellToDB> p1InitialArray;
    private ArrayList<CellToDB> p2InitialArray;

    public InitialState(ArrayList<CellToDB> p1InitialArray, ArrayList<CellToDB> p2InitialArray) {
        this.p1InitialArray = p1InitialArray;
        this.p2InitialArray = p2InitialArray;
    }

    public ArrayList<CellToDB> getP2InitialArray() {
        return p2InitialArray;
    }

    public void setP2InitialArray(ArrayList<CellToDB> p2InitialArray) {
        this.p2InitialArray = p2InitialArray;
    }

    public ArrayList<CellToDB> getP1InitialArray() {
        return p1InitialArray;
    }

    public void setP1InitialArray(ArrayList<CellToDB> p1InitialArray) {
        this.p1InitialArray = p1InitialArray;
    }

    @Override
    public String toString() {
        return "InitialState{" +
                "p1InitialArray=" + p1InitialArray +
                ", p2InitialArray=" + p2InitialArray +
                '}';
    }
}
