package IA;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import sample.BoardController;
import sample.Controller;

import java.util.Random;

public class IA {
    private int gameMode;
    private BoardController board;
    private Random random = new Random();
    private boolean enemyTurn = false;


    public IA(int gameMode, BoardController board){
        this.board = board;
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

    private BoardController easyMode(){
        System.out.println("EasyMode");
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = board.getCell(x, y);
            if (cell.get_isWasShot())
                continue;

            enemyTurn = cell.shoot();
        }
        return board;

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

}
