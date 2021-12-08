package bs_game_backend;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.BoardController;


public class Cell extends Rectangle {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", ship=" + ship +
                ", isWasShot=" + isWasShot +
                '}';
    }

    //zmienić na privet
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

    public Ship getShip() {
        return ship;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isWasShot() {
        return isWasShot;
    }

    public void setWasShot(boolean wasShot) {
        isWasShot = wasShot;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
