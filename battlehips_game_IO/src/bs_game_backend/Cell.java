package bs_game_backend;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.BoardController;


public class Cell extends Rectangle {
    private int x;
    private int y;
    public Ship ship = null; // Is part of any ship?
    private boolean isWasShot = false;
    private BoardController boardController; // Reference to including on board (my or enemy)


    public Cell(int x, int y, BoardController boardController){
        super(30, 30);
        this.x = x;
        this.y = y;
        this.boardController = boardController;
        setFill(Color.LIGHTGREY);
        setStroke(Color.BLACK);
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public boolean get_isWasShot() {
        return isWasShot;
    }

    public boolean shoot(){
        isWasShot = true;
        setFill(Color.BLACK);

        if(ship != null) {
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()){
                boardController.ships--;
            }
            return true;
        }
        return false;
    }

}
