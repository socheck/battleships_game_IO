package bs_game_backend;

import javafx.scene.Parent;

public class Ship extends Parent {
    private int type;
    private boolean vertical = true;
    private int health;

    public Ship(int type, boolean vertical){
        this.type = type;
        this.vertical = vertical;
        health = type;
    }

    public void hit(){
        health--;
    }

    public int getType() {
        return type;
    }

    public boolean isVertical() {
        return vertical;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive(){
        if (health > 0) {
            return true;
        }else {
            return false;
        }
    }



}
