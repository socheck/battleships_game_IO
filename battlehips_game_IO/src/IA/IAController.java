package IA;

import bs_game_backend.Ship;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import sample.BoardController;
import sample.Controller;

public class IAController {
    private int gameMode;
    private BoardController boardAI;


    public IAController(int gameMode){
        this.boardAI = new BoardController(true, null);
        this.gameMode = gameMode;

    }


    public void moveAI(){
        switch (getGameMode()) {
            case 0 -> easyMode();
            case 1 -> mediumMode();
            case 2 -> hardMode();
            default -> System.out.println("Nie wybrano żadnego trybu!");
        }
    }

    private void easyMode(){
        System.out.println("EasyMode");

    }

    private void mediumMode(){
        System.out.println("MediumMode");
    }

    private void hardMode(){
        System.out.println("HardMode");
    }


    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

//    public void placeShip(){
//        int type = list_of_ships2.size();
//        if(type == 0){
//            createBoard2();
//            type = list_of_ships2.size();
//            insertBoardPl2(scene);
//        }
//
//        while(type > 0) {
//            int x = this.random.nextInt(10);
//            int y = this.random.nextInt(10);
//
//            if (player2Board.placeShip(new Ship(list_of_ships2.get(0), Math.random() < 0.5D), x, y)) {
//                --type;
//                list_of_ships2.remove(0);
//            }
//        }
//
//    }
}
