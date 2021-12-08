package AI;
import bs_game_backend.*;
import bs_game_backend.Cell;
import sample.BoardController;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private int gameMode;
    private BoardController board;
    private Random random = new Random();
    private boolean enemyTurn = true;//to jest do pętli ale nie wiem po co //było false
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

    public AI(int gameMode, BoardController board){
        this.board = board;
        this.gameMode = gameMode;
        this.potentialShoots = new ArrayList<Cell>();
        this.earlierShot = null;
        this.forbiddenShoots = new ArrayList<Cell>();

    }


    public BoardController moveAI(){
        switch (getGameMode()) {
            case 0:
                return easyMode(false);

            case 1: return mediumMode(false);
//            case 2 -> hardMode();
            default:
                System.out.println("Nie wybrano żadnego trybu!");
                return null;
        }
    }

    public BoardController easyMode(boolean isUsedByOtherMode){

        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = board.getCell(x, y);

            if (cell.get_isWasShot())
                continue;

            enemyTurn = cell.shoot();
            board.render();


            if(isUsedByOtherMode){
                if(!cell.getShip().isAlive()){ // sprawdzenie czy statek jest zatopiony
                    enemyTurn = true;//było true
//                    board.addChange(null);
                    this.earlierShot = null;
                    return board;
                }

                if( cell.getShip() != null){ // czy jest trafiony
                    this.earlierShot = cell;
//                    board.addChange(cell);// trafiliśmy w statek --> dodanie ostatnio trafionej Cell
                    this.potentialShoots.add(new Cell(cell.get_x()+1, cell.get_y(), cell.getBoardController()));
                    this.potentialShoots.add(new Cell(cell.get_x()-1, cell.get_y(), cell.getBoardController()));
                    this.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()+1, cell.getBoardController()));
                    this.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()-1, cell.getBoardController()));
                    System.out.println(this.potentialShoots);
                }
            }

            enemyTurn = true;//było true
            return board;
        }


        return board;

    }

//    public BoardController mediumMode(Cell trafiona, boolean isUsedByOtherMode){
//        System.out.println("MediumMode");
//
////        if(czy_był_zatopiony w poprzednim_kroku && !isUsedByOtherMode){
////            this.potentialShoots.clear();
////
////        }
//
//        if(trafiona == null && this.earlierShot == null){ //szukamy na razie statku
//            return easyMode();
//        }
//        if(trafiona !=null && this.earlierShot == null){ //pierwsze trafienie
//            this.earlierShot = trafiona;
//            //dodajemy te 4
//            this.potentialShoots.add(new Cell(trafiona.get_x()+1, trafiona.get_y(), trafiona.getBoardController()));
//            this.potentialShoots.add(new Cell(trafiona.get_x()-1, trafiona.get_y(), trafiona.getBoardController()));
//            this.potentialShoots.add(new Cell(trafiona.get_x(), trafiona.get_y()+1, trafiona.getBoardController()));
//            this.potentialShoots.add(new Cell(trafiona.get_x(), trafiona.get_y()-1, trafiona.getBoardController()));
//        }
//
//        while (enemyTurn) {
//            if(!this.potentialShoots.isEmpty()){
////            Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
////            this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy
////          ...
//
//                Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
//                this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy
//
//                Cell cell = board.getCell(actualCell.get_x(), actualCell.get_y());
//                if (cell.get_isWasShot()){
//                    continue;
//                }else{
//                    enemyTurn = cell.shoot();
//                    return this.board;
//                }
////              do usunięcia jak działa
//////                System.out.println("czegoś nie przewidziałem jeszce");
////
//////                return null;
//
//            }else{
//                return easyMode();
//            }
//
//        }
//
//
//
//
////        dodatkowo jeżeli trafi to strzela wokół aż nie zatopi kolejnego
////        strzzał --> zapisujemy ostatni strzał jeśli jest trafiony --> potencjaly strzał 4 (pion, poziom) --> próba pion (góra lub dół) --> 1. trafiony --> usuwamy potencjalne strzały z boków(mają inną jedną z x lub y) i dodajemy ten moniżej nowego --> 2. pudło --> idziemy do następnego z potencjalnych --> jak zatopiony to wywalamy z potencjalnych
//
//        System.out.println("czegoś nie przewidziałem jeszce");
//        return null;
//    }






    private BoardController mediumMode(boolean isUsedByOtherMode){
        System.out.println("MediumMode");


        if(this.board.getLatestShot() == null && this.potentialShoots.isEmpty()) { //szukamy na razie statku
            return easyMode(true);
        }else{
            while (true) {

                    Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
                    this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy

                    Cell cell = board.getCell(actualCell.get_x(), actualCell.get_y());
                    if (cell.get_isWasShot()){
                        continue;
                    }

                    enemyTurn = cell.shoot();
                    board.render();

                    if(!cell.getShip().isAlive()){ // sprawdzenie czy statek jest zatopiony
                        enemyTurn = true;
                        this.earlierShot = null;
                        this.potentialShoots.clear();//czyścimy
                        return board;
                    }

                    if( cell.getShip() != null){ // wcześniej trafiony ale teraz nie//ustalamy czy pionowy czy poziomy
//                        usuwamy wszystkie (może ich tam już nie być) Cell z tablicy które mają niepasujące koordynaty
                        if(this.earlierShot.get_x() == cell.get_x()){ //te same x więc usuwamy te co mają inny x
                            this.potentialShoots.removeIf(c -> cell.get_x() != c.get_x());
                        }
                        if(this.earlierShot.get_y() == cell.get_y()){ //te same y więc usuwamy te co mają inny y
                            this.potentialShoots.removeIf(c -> cell.get_y() != c.get_y());
                        }

                        System.out.println(this.potentialShoots);

                    }

                    System.out.println("czegoś nie przewidziałem jeszce");
                    return this.board;

            }

        }
//        dodatkowo jeżeli trafi to strzela wokół aż nie zatopi kolejnego
//        strzzał --> zapisujemy ostatni strzał jeśli jest trafiony --> potencjaly strzał 4 (pion, poziom) --> próba pion (góra lub dół) --> 1. trafiony --> usuwamy potencjalne strzały z boków(mają inną jedną z x lub y) i dodajemy ten moniżej nowego --> 2. pudło --> idziemy do następnego z potencjalnych --> jak zatopiony to wywalamy z potencjalnych


    }

















    public void hardMode(){
        System.out.println("HardMode");
//        to samo co medium tylko mamy jeszcze zakazane pola jak zatopiony to dodajemy pola obwódki do zakazanych i sprawdzamy czy w nie nie strzeeliliśmy wpętli i dopiero oddajemy strzał w jakiś dozwolony


//        if(czy_był_zatopiony w poprzednim_kroku){
//            //dodajemy wszystktie Cell statku oraz obwódki jeżeli jest
//            this.forbiddenShoots.add()
//        }
    }



}
