package AI;
import bs_game_backend.Cell;
import bs_game_backend.BoardController;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private int gameMode;
    private BoardController board;
    private Random random = new Random();
    private boolean enemyTurn = true;//to jest do pętli ale nie wiem po co //było false
//    medium mode
    private ArrayList<Cell> potentialShoots;
//    hard mode
    private ArrayList<Cell> forbiddenShoots;

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
        this.forbiddenShoots = new ArrayList<Cell>();

    }
    public BoardController moveAI(){
        switch (getGameMode()) {
            case 0: return easyMode(0);
            case 1: return mediumMode(1);
            case 2: return hardMode(2);
            default:
//                System.out.println("Nie wybrano żadnego trybu!");
                return null;
        }
    }
    public BoardController easyMode(int useByOtherMode){
        int x;
        int y;
        while (enemyTurn) {
            x = random.nextInt(10);
            y = random.nextInt(10);
            Cell cell = board.getCell(x, y);
            if(useByOtherMode == 2 && this.board.getForbiddenShoots().contains(cell)){
//                System.out.println("forbidden nie strzelam w " + cell);

                continue;
            }
            if (cell.get_isWasShot() || (useByOtherMode == 2 && this.board.getForbiddenShoots().contains(cell)) ) {
                continue;
            }
            enemyTurn = cell.shoot();
            board.render();
            if(useByOtherMode >= 1){
                if( cell.getShip() != null){ // czy jest trafiony
                    if(!cell.getShip().isAlive() && useByOtherMode < 2 ){ // sprawdzenie czy statek jest zatopiony
                        enemyTurn = true;//było true
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;
                    }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
                        for (Cell c :
                                this.board.getShipNeighbors(cell)) {
                            this.board.getForbiddenShoots().add(c);
                        }
//                        System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
//                        System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
                        enemyTurn = true;//było true
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;
                    }
                    this.board.firstShipCell = cell;
                    if(0<cell.get_x()+1 && cell.get_x()+1<10){
                        if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x()+1, cell.get_y())))){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
                        }else if(useByOtherMode != 2){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
                        }
                    }
                    if(-1<cell.get_x()-1 && cell.get_x()-1<9){
                        if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x()-1, cell.get_y())))){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
                        }else if(useByOtherMode !=2){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
                        }
                    }
                    if(0<cell.get_y()+1 && cell.get_y()+1<10){
                        if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x(), cell.get_y()+1)))){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
                        }else if (useByOtherMode != 2){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
                        }
                    }
                    if(-1<cell.get_y()-1 && cell.get_y()-1<9){
                        if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x(), cell.get_y()-1)))){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
                        }else if(useByOtherMode != 2){
                            this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
                        }
                    }
//                    System.out.println("potencjalne: " + this.board.potentialShoots);
                    this.board.addChange(cell);
                    return this.board;
                }
            }
            enemyTurn = true;//było true
            board.addChange(cell);
            return this.board;
        }
        return board;
    }
    private BoardController mediumMode(int useByOtherMode){
//        System.out.println("forbidden "+ board.getForbiddenShoots().size() + " " + board.getForbiddenShoots());
//        System.out.println("potential "+ board.getPotentialShoots().size() + " " + board.getPotentialShoots());
        if( this.board.potentialShoots.isEmpty()) { //szukamy na razie statku
//            System.out.println("easy w meddium");
            return easyMode(useByOtherMode);
        }else{
//            System.out.println("medium");
            while (true) {
//                System.out.println("wewnątrz while potential "+ board.getPotentialShoots().size() + " " + board.getPotentialShoots());
                Cell actualCell = this.board.potentialShoots.get(0);//bierzemy następną
                this.board.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy
                Cell cell = board.getCell(actualCell.get_x(), actualCell.get_y());
                if (cell.get_isWasShot()){
                    continue;
                }
                enemyTurn = cell.shoot();
                board.render();
                if( cell.getShip() != null){ // wcześniej trafiony ale teraz nie//ustalamy czy pionowy czy poziomy
                    if(!cell.getShip().isAlive() && useByOtherMode == 1 ){ // sprawdzenie czy statek jest zatopiony
                        enemyTurn = true;//było true
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;
                    }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
                        for (Cell c :
                                this.board.getShipNeighbors(cell)) {
                            this.board.getForbiddenShoots().add(c);
                        }
//                        System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
//                        System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
                        enemyTurn = true;//było true
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;
                    }
//                    System.out.println("s pot " + this.board.getPotentialShoots());
//                    System.out.println("s first " + this.board.firstShipCell);
//                    System.out.println("s cell " + cell);
                    if(this.board.firstShipCell.get_x() == cell.get_x()){ //te same x więc usuwamy te co mają inny x
//                        System.out.println("potential" + this.board.potentialShoots);
                        this.board.potentialShoots.removeIf(c -> cell.get_x() != c.get_x());
//                        System.out.println("potential" + this.board.potentialShoots);
                        if(this.board.firstShipCell.get_y() < cell.get_y() && cell.get_y()+1 < 10){//ta kolejna komórka jest pod nią
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
                            }else if(useByOtherMode == 1){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()+1));
                            }
                        }
                        if(this.board.firstShipCell.get_y() > cell.get_y() && cell.get_y()-1 > -1){//ta kolejna komórka jes nad pierwszą
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
                            }else if(useByOtherMode== 1){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()-1));
                            }
                        }
                    }
                    if(this.board.firstShipCell.get_y() == cell.get_y()){ //te same y więc usuwamy te co mają inny y
//                        System.out.println("potential" + this.board.potentialShoots);
                        this.board.potentialShoots.removeIf(c -> cell.get_y() != c.get_y());
//                        System.out.println("potential" + this.board.potentialShoots);
                        if(this.board.firstShipCell.get_x() < cell.get_x() && cell.get_x()+1 < 10){//ta kolejna komórka jest z prawej
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
                            }else if(useByOtherMode == 1){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()+1, cell.get_y()));
                            }
                        }
                        if(this.board.firstShipCell.get_x() > cell.get_x() && cell.get_x()-1 > -1){//ta kolejna komórka jest z lewej
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
                            }else if(useByOtherMode == 1){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()-1, cell.get_y()));
                            }
                        }
                    }
//                    System.out.println("potencjalne"+this.board.potentialShoots);
//                    System.out.println("forbidden "+ this.board.getForbiddenShoots());
                }
                this.board.addChange(cell);
                return this.board;
            }
        }
    }
    public BoardController hardMode(int useByOtherMode){
//        System.out.println("HardMode");
        return mediumMode(useByOtherMode);
    }
}