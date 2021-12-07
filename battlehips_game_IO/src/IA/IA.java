package IA;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import sample.BoardController;
import sample.Controller;

import java.util.ArrayList;
import java.util.Random;

public class IA {
    private int gameMode;
    private BoardController board;
    private Random random = new Random();
    private boolean enemyTurn = true;//to jest do pętli //było false
//    medium mode
    private ArrayList<Cell> potentialShoots;
    private Cell earlierShot;
//    hard mode
    private ArrayList<Cell> forbiddenShoots;


    public void endGame() {
        this.gameMode = -1;
        this.board = null;
        this.potentialShoots.clear();
        this.earlierShot = null;
        this.forbiddenShoots.clear();

    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public BoardController getBoard() {
        return board;
    }

    public void setBoard(BoardController board) {
        this.board = board;
    }

    public IA(int gameMode, BoardController board){
        this.board = board;
        this.gameMode = gameMode;
        this.potentialShoots = new ArrayList<Cell>();
        this.earlierShot = null;
        this.forbiddenShoots = new ArrayList<Cell>();
    }


    public void moveAI(){
        switch (getGameMode()) {
            case 0 -> easyMode();
            case 1 -> mediumMode();//trzeba przesłać zmienne
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
        enemyTurn = true;
        return board;

        }

    private BoardController mediumMode(Cell trafiona, boolean isUsedByOtherMode){
        System.out.println("MediumMode");

//        if(czy_był_zatopiony w poprzednim_kroku && !isUsedByOtherMode){
//            this.potentialShoots.clear();
//
//        }

        if(trafiona == null && this.earlierShot == null){ //szukamy na razie statku
            return easyMode();
        }
        if(trafiona !=null && this.earlierShot == null){ //pierwsze trafienie
            this.earlierShot = trafiona;
            //dodajemy te 4
            this.potentialShoots.add(new Cell(trafiona.get_x()+1, trafiona.get_y(), trafiona.getBoardController()));
            this.potentialShoots.add(new Cell(trafiona.get_x()-1, trafiona.get_y(), trafiona.getBoardController()));
            this.potentialShoots.add(new Cell(trafiona.get_x(), trafiona.get_y()+1, trafiona.getBoardController()));
            this.potentialShoots.add(new Cell(trafiona.get_x(), trafiona.get_y()-1, trafiona.getBoardController()));
        }

        while (enemyTurn) {
            if(!this.potentialShoots.isEmpty()){
//            Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
//            this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy
//          ...

                Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
                this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy

                Cell cell = board.getCell(actualCell.get_x(), actualCell.get_y());
                if (cell.get_isWasShot()){
                    continue;
                }else{
                    enemyTurn = cell.shoot();
                    return board;
                }
//              do usunięcia jak działa
                System.out.println("czegoś nie przewidziałem jeszce");
                return null;

            }
            else{
                return easyMode();
            }

        }




//        dodatkowo jeżeli trafi to strzela wokół aż nie zatopi kolejnego
//        strzzał --> zapisujemy ostatni strzał jeśli jest trafiony --> potencjaly strzał 4 (pion, poziom) --> próba pion (góra lub dół) --> 1. trafiony --> usuwamy potencjalne strzały z boków(mają inną jedną z x lub y) i dodajemy ten moniżej nowego --> 2. pudło --> idziemy do następnego z potencjalnych --> jak zatopiony to wywalamy z potencjalnych

        System.out.println("czegoś nie przewidziałem jeszce");
        return null;
    }

    private void hardMode(){
        System.out.println("HardMode");
//        to samo co medium tylko mamy jeszcze zakazane pola jak zatopiony to dodajemy pola obwódki do zakazanych i sprawdzamy czy w nie nie strzeeliliśmy wpętli i dopiero oddajemy strzał w jakiś dozwolony


//        if(czy_był_zatopiony w poprzednim_kroku){
//            //dodajemy wszystktie Cell statku oraz obwódki jeżeli jest
//            this.forbiddenShoots.add()
//        }
    }



}
