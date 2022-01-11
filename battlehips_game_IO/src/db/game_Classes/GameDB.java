package db.game_Classes;

import sample.CellToDB;

import java.util.ArrayList;

public class GameDB {
    private int id;
    private InitialState initialState;
    private Changes changes;
    private int player1ID;
    private int player2ID;
    private int winnerID;

    @Override
    public String toString() {
        return "GameDB{" +
                "id=" + id +
                ", initialState=" + initialState +
                ", changes=" + changes +
                ", player1ID=" + player1ID +
                ", player2ID=" + player2ID +
                ", winnerID=" + winnerID +
                '}';
    }

    public GameDB(int id, InitialState initialState, Changes changes, int player1ID, int player2ID, int winnerID) {
        this.id = id;
        this.initialState = initialState;
        this.changes = changes;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
    }

    public GameDB(int id, int player1ID, int player2ID, int winnerID) {
        this.id = id;
        this.initialState = null;
        this.changes = null;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
    }

    public InitialState getInitialState() {
        return initialState;
    }

    public void setInitialState(InitialState initialState) {
        this.initialState = initialState;
    }

    public Changes getChanges() {
        return changes;
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public ArrayList<CellToDB> getInitialStateArray(int id) {
        if (id == this.getPlayer1ID()){
            return this.initialState.getP1InitialArray();

        }
        else if(id == this.getPlayer2ID()){
            return this.initialState.getP2InitialArray();
        }else{
            System.out.println("błąd GameDB getInitialStateArray");
            return null;
        }
    }

    public ArrayList<CellToDB> getChangesArray(int id) {
        if (id == this.getPlayer1ID()){
            return this.changes.getP1ChangesArray();

        }
        else if(id == this.getPlayer2ID()){
            return this.initialState.getP2InitialArray();
        }else{
            System.out.println("błąd GameDB getChangesArray");
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public int getPlayer1ID() {
        return player1ID;
    }

    public int getPlayer2ID() {
        return player2ID;
    }

    public int getWinnerID() {
        return winnerID;
    }
}
