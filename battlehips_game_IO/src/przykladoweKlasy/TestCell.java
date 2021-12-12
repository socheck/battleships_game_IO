package przykladoweKlasy;

import bs_game_backend.Ship;
import javafx.scene.shape.Rectangle;

public class TestCell {

        private int x;
        private int y;


        //zmienić na privet
        private TestShip ship = null; // Is part of any ship?
        private boolean isWasShot = false;



        public TestCell(int x, int y, TestShip ship, boolean ifshot){
//            super(30, 30);
            this.x = x;
            this.y = y;
            this.ship = ship;
            this.isWasShot = ifshot;

        }


    public int getMyX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public int getMyY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TestShip getShip() {
        return ship;
    }

    public void setShip(TestShip ship) {
        this.ship = ship;
    }

    public boolean isWasShot() {
        return isWasShot;
    }

    public void setWasShot(boolean wasShot) {
        isWasShot = wasShot;
    }
}
