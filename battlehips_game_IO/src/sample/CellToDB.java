package sample;

import bs_game_backend.Ship;


public class CellToDB {

    private int x;
    private int y;
    private ShipToDB ship = null; // Is part of any ship?
    private boolean isWasShot = false;

    public CellToDB(int x, int y, ShipToDB ship, boolean isWasShot) {
        this.x = x;
        this.y = y;
        this.ship = ship;
        this.isWasShot = isWasShot;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ShipToDB getShip() {
        return ship;
    }

    public void setShip(ShipToDB ship) {
        this.ship = ship;
    }

    public boolean isWasShot() {
        return isWasShot;
    }

    public void setWasShot(boolean wasShot) {
        isWasShot = wasShot;
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                ", y=" + y +
                ", ship=" + ship +
                ", isWasShot=" + isWasShot +
                '}';
    }
}
