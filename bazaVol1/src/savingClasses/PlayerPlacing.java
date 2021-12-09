package savingClasses;

import przykladoweKlasy.BookPOJO;

import java.util.List;

public class PlayerPlacing {
    private int player;
    private List<String> ship4;
    private List<String> ship31;
    private List<String> ship32;
    private List<String> ship21;
    private List<String> ship22;
    private List<String> ship23;
//    private List<String> ship24;
    private List<String> ship11;
    private List<String> ship12;
    private List<String> ship13;
    private List<String> ship14;

    @Override
    public String toString() {
        return "PlayerPlacing{" +
                "player=" + player +
                ", ship4=" + ship4 +
                ", ship31=" + ship31 +
                ", ship32=" + ship32 +
                ", ship21=" + ship21 +
                ", ship22=" + ship22 +
                ", ship23=" + ship23 +
                ", ship11=" + ship11 +
                ", ship12=" + ship12 +
                ", ship13=" + ship13 +
                ", ship14=" + ship14 +
                '}';
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public List<String> getShip4() {
        return ship4;
    }

    public void setShip4(List<String> ship4) {
        this.ship4 = ship4;
    }

    public List<String> getShip31() {
        return ship31;
    }

    public void setShip31(List<String> ship31) {
        this.ship31 = ship31;
    }

    public List<String> getShip32() {
        return ship32;
    }

    public void setShip32(List<String> ship32) {
        this.ship32 = ship32;
    }

    public List<String> getShip21() {
        return ship21;
    }

    public void setShip21(List<String> ship21) {
        this.ship21 = ship21;
    }

    public List<String> getShip22() {
        return ship22;
    }

    public void setShip22(List<String> ship22) {
        this.ship22 = ship22;
    }

    public List<String> getShip23() {
        return ship23;
    }

    public void setShip23(List<String> ship23) {
        this.ship23 = ship23;
    }

    public List<String> getShip11() {
        return ship11;
    }

    public void setShip11(List<String> ship11) {
        this.ship11 = ship11;
    }

    public List<String> getShip12() {
        return ship12;
    }

    public void setShip12(List<String> ship12) {
        this.ship12 = ship12;
    }

    public List<String> getShip13() {
        return ship13;
    }

    public void setShip13(List<String> ship13) {
        this.ship13 = ship13;
    }

    public List<String> getShip14() {
        return ship14;
    }

    public void setShip14(List<String> ship14) {
        this.ship14 = ship14;
    }
}
