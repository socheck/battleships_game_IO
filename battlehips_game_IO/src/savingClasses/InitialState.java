package savingClasses;

import java.util.List;

public class InitialState {
    private int player1;
    private int player2;
    private List<PlayerPlacing> initial_state;

//    public InitialState(int player1, int player2, List<PlayerPlacing> initial_state) {
//        this.player1 = player1;
//        this.player2 = player2;
//        this.initial_state = initial_state;
//    }

    @Override
    public String toString() {
        return "InitialState{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", initial_state=" + initial_state +
                '}';
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public List<PlayerPlacing> getInitial_state() {
        return initial_state;
    }

    public void setInitial_state(List<PlayerPlacing> initial_state) {
        this.initial_state = initial_state;
    }
}
