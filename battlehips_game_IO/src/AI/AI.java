package AI;

import bs_game_backend.Cell;
import sample.BoardController;

import java.util.Random;

public class AI {
    private int gameMode;
    private BoardController board;
    private Random random = new Random();
    private boolean enemyTurn = true;


    public AI(int gameMode, BoardController board){
        this.board = board;
        this.gameMode = gameMode;

    }


    public BoardController moveAI(){
        switch (getGameMode()) {
            case 0 -> easyMode();
            case 1 -> mediumMode();
            case 2 -> hardMode();
            default -> System.out.println("Nie wybrano żadnego trybu!");
        }
        return board;
    }

    private BoardController easyMode(){

        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = board.getCell(x, y);
            if (cell.get_isWasShot())
                continue;

            enemyTurn = cell.shoot();
            board.render();
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
