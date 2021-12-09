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
        this.board.earlierShot  = null;
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
//        this.board.potentialShoots = new ArrayList<Cell>();
        this.potentialShoots = new ArrayList<Cell>();
//        this.board.earlierShot = null;
        this.earlierShot = null;
        this.forbiddenShoots = new ArrayList<Cell>();

    }


    public BoardController moveAI(){
        switch (getGameMode()) {
            case 0: return easyMode(0);
            case 1: return mediumMode(1);
            case 2: return hardMode(2);
            default:
                System.out.println("Nie wybrano żadnego trybu!");
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
                System.out.println("forbidden nie strzelam w " + cell);

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
//                    board.addChange(null);
                        this.earlierShot = null;
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;


                    }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
                        for (Cell c :
                                this.board.getShipNeighbors(cell)) {
                            this.board.getForbiddenShoots().add(c);
                        }
                        System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
                        System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
                        enemyTurn = true;//było true
//                    board.addChange(null);
                        this.earlierShot = null;
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;
                    }

                    this.board.earlierShot = cell;
                    this.board.firstShipCell = cell;
//                    board.addChange(cell);// trafiliśmy w statek --> dodanie ostatnio trafionej Cell
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
//                    this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                    this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                    this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                    this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));


//                    this.board.potentialShoots.add(new Cell(cell.get_x()+1, cell.get_y(), cell.getBoardController()));
//                    this.board.potentialShoots.add(new Cell(cell.get_x()-1, cell.get_y(), cell.getBoardController()));
//                    this.board.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()+1, cell.getBoardController()));
//                    this.board.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()-1, cell.getBoardController()));
                    System.out.println("potencjalne: " + this.board.potentialShoots);
//                    System.out.println("forbidden "+ this.board.getForbiddenShoots());



//                    dopisane
//                    mediumMode(useByOtherMode);
                    this.board.addChange(cell);
                    return this.board;
                }
            }

            enemyTurn = true;//było true

//            board.setLatestShot(cell);
            board.addChange(cell);
            return this.board;
        }


        return board;

    }


    private BoardController mediumMode(int useByOtherMode){
//        System.out.println("MediumMode");
//        System.out.println("useBy..."+ useByOtherMode);
        System.out.println("forbidden "+ board.getForbiddenShoots().size() + " " + board.getForbiddenShoots());
        System.out.println("potential "+ board.getPotentialShoots().size() + " " + board.getPotentialShoots());


//        if(this.earlierShot == null && this.board.potentialShoots.isEmpty()) {//szukamy na razie statku
        if( this.board.potentialShoots.isEmpty()) { //szukamy na razie statku
            System.out.println("easy w meddium");
            return easyMode(useByOtherMode);
        }else{
            System.out.println("medium");
//            while (!this.board.potentialShoots.isEmpty()) {
            while (true) {
                System.out.println("wewnątrz while potential "+ board.getPotentialShoots().size() + " " + board.getPotentialShoots());
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
//                    board.addChange(null);
                        this.earlierShot = null;
                        this.board.firstShipCell = null;
                        this.board.addChange(cell);
                        this.board.potentialShoots.clear();

                        return this.board;


                    }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
                        for (Cell c :
                                this.board.getShipNeighbors(cell)) {
                            this.board.getForbiddenShoots().add(c);
                        }
                        System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
                        System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
                        enemyTurn = true;//było true
//                    board.addChange(null);
                        this.earlierShot = null;
                        this.board.firstShipCell = null;
                        this.board.potentialShoots.clear();
                        this.board.addChange(cell);
                        return this.board;

                    }

//                    if(this.board.earlierShot.get_x() == cell.get_x()){ //te same x więc usuwamy te co mają inny x
//                        System.out.println("potential" + this.board.potentialShoots);
//                        this.board.potentialShoots.removeIf(c -> cell.get_x() != c.get_x());
//                        System.out.println("potential" + this.board.potentialShoots);
//                        if(this.board.earlierShot.get_y() == cell.get_y()-1 && cell.get_y()+1 < 10){//ta kolejna komórka jest pod nią
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                            }else if(useByOtherMode == 1){
//
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()+1));
//                            }
//                        }
//                        if(this.board.earlierShot.get_y() == cell.get_y()+1 && cell.get_y()-1 > -1){//ta kolejna komórka jes nad pierwszą
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                            }else if(useByOtherMode== 1){
//
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()-1));
//                            }
//                        }
//                    }
//                    if(this.board.earlierShot.get_y() == cell.get_y()){ //te same y więc usuwamy te co mają inny y
//                        System.out.println("potential" + this.board.potentialShoots);
//                        this.board.potentialShoots.removeIf(c -> cell.get_y() != c.get_y());
//                        System.out.println("potential" + this.board.potentialShoots);
//
//                        if(this.board.earlierShot.get_x() == cell.get_x()-1 && cell.get_x()+1 < 10){//ta kolejna komórka jest z prawej
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                            }else if(useByOtherMode == 1){
//
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()+1, cell.get_y()));
//                            }
//                        }
//                        if(this.board.earlierShot.get_x() == cell.get_x()+1 && cell.get_x()-1 > -1){//ta kolejna komórka jest z lewej
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                            }else if(useByOtherMode == 1){
//
//                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()-1, cell.get_y()));
//                            }
//                        }
//                    }

                    System.out.println("s pot " + this.board.getPotentialShoots());
                    System.out.println("s first " + this.board.firstShipCell);
                    System.out.println("s cell " + cell);
                    if(this.board.firstShipCell.get_x() == cell.get_x()){ //te same x więc usuwamy te co mają inny x
                        System.out.println("potential" + this.board.potentialShoots);
                        this.board.potentialShoots.removeIf(c -> cell.get_x() != c.get_x());
                        System.out.println("potential" + this.board.potentialShoots);
                        if(this.board.firstShipCell.get_y() < cell.get_y() && cell.get_y()+1 < 10){//ta kolejna komórka jest pod nią
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
                            }else if(useByOtherMode == 1){

                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()+1));
                            }
                        }
                        if(this.board.firstShipCell.get_y() > cell.get_y() && cell.get_y()-1 > -1){//ta kolejna komórka jes nad pierwszą
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
                            }else if(useByOtherMode== 1){

                                this.board.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()-1));
                            }
                        }
                    }

                    if(this.board.firstShipCell.get_y() == cell.get_y()){ //te same y więc usuwamy te co mają inny y
                        System.out.println("potential" + this.board.potentialShoots);
                        this.board.potentialShoots.removeIf(c -> cell.get_y() != c.get_y());
                        System.out.println("potential" + this.board.potentialShoots);

                        if(this.board.firstShipCell.get_x() < cell.get_x() && cell.get_x()+1 < 10){//ta kolejna komórka jest z prawej
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
                            }else if(useByOtherMode == 1){

                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()+1, cell.get_y()));
                            }
                        }
                        if(this.board.firstShipCell.get_x() > cell.get_x() && cell.get_x()-1 > -1){//ta kolejna komórka jest z lewej
                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
                            }else if(useByOtherMode == 1){

                                this.board.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()-1, cell.get_y()));
                            }
                        }
                    }

                    System.out.println("potencjalne"+this.board.potentialShoots);
                    System.out.println("forbidden "+ this.board.getForbiddenShoots());

                }

//                System.out.println("czegoś nie przewidziałem jeszce");
//                return this.board;
                this.board.addChange(cell);
                return this.board;
            }

//            return this.board;

        }
//        dodatkowo jeżeli trafi to strzela wokół aż nie zatopi kolejnego
//        strzzał --> zapisujemy ostatni strzał jeśli jest trafiony --> potencjaly strzał 4 (pion, poziom) --> próba pion (góra lub dół) --> 1. trafiony --> usuwamy potencjalne strzały z boków(mają inną jedną z x lub y) i dodajemy ten moniżej nowego --> 2. pudło --> idziemy do następnego z potencjalnych --> jak zatopiony to wywalamy z potencjalnych


    }

    public BoardController hardMode(int useByOtherMode){
        System.out.println("HardMode");

        return mediumMode(useByOtherMode);
//        to samo co medium tylko mamy jeszcze zakazane pola jak zatopiony to dodajemy pola obwódki do zakazanych i sprawdzamy czy w nie nie strzeeliliśmy wpętli i dopiero oddajemy strzał w jakiś dozwolony


    }



//  ==============================================BACKUP================================================


//    public BoardController easyMode(int useByOtherMode){
//    int x;
//    int y;
//    while (enemyTurn) {
//        x = random.nextInt(10);
//        y = random.nextInt(10);
//
//        Cell cell = board.getCell(x, y);
//        if(useByOtherMode == 2 && this.board.getForbiddenShoots().contains(cell)){
//            System.out.println("forbidden nie strzelam w " + cell);
//
//            continue;
//        }
//
//        if (cell.get_isWasShot() || (useByOtherMode == 2 && this.board.getForbiddenShoots().contains(cell)) ) {
//            continue;
//        }
//        enemyTurn = cell.shoot();
//        board.render();
//
//
//        if(useByOtherMode >= 1){
//
//
//            if( cell.getShip() != null){ // czy jest trafiony
//
//                if(!cell.getShip().isAlive() && useByOtherMode < 2 ){ // sprawdzenie czy statek jest zatopiony
//                    enemyTurn = true;//było true
////                    board.addChange(null);
//                    this.earlierShot = null;
//                    return board;
//
//
//                }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
//                    for (Cell c :
//                            this.board.getShipNeighbors(cell)) {
//                        this.board.getForbiddenShoots().add(c);
//                    }
//                    System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
//                    System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
//                    enemyTurn = true;//było true
////                    board.addChange(null);
//                    this.earlierShot = null;
//                    return board;
//                }
//
//                this.earlierShot = cell;
////                    board.addChange(cell);// trafiliśmy w statek --> dodanie ostatnio trafionej Cell
//                if(0<cell.get_x()+1 && cell.get_x()+1<10){
//                    if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x()+1, cell.get_y())))){
//
//                        this.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                    }else if(useByOtherMode != 2){
//
//                        this.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                    }
//                }
//                if(-1<cell.get_x()-1 && cell.get_x()-1<9){
//                    if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x()-1, cell.get_y())))){
//                        this.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                    }else if(useByOtherMode !=2){
//
//                        this.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                    }
//                }
//                if(0<cell.get_y()+1 && cell.get_y()+1<10){
//                    if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x(), cell.get_y()+1)))){
//                        this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                    }else if (useByOtherMode != 2){
//
//                        this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                    }
//                }
//                if(-1<cell.get_y()-1 && cell.get_y()-1<9){
//                    if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(this.board.getCell(cell.get_x(), cell.get_y()-1)))){
//                        this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                    }else if(useByOtherMode != 2){
//
//                        this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                    }
//                }
////                    this.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
////                    this.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
////                    this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
////                    this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//
//
////                    this.potentialShoots.add(new Cell(cell.get_x()+1, cell.get_y(), cell.getBoardController()));
////                    this.potentialShoots.add(new Cell(cell.get_x()-1, cell.get_y(), cell.getBoardController()));
////                    this.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()+1, cell.getBoardController()));
////                    this.potentialShoots.add(new Cell(cell.get_x(), cell.get_y()-1, cell.getBoardController()));
//                System.out.println("potencjalne: " + this.potentialShoots);
////                    System.out.println("forbidden "+ this.board.getForbiddenShoots());
//
//
//
////                    dopisane
//                mediumMode(useByOtherMode);
//            }
//        }
//
//        enemyTurn = true;//było true
//
////            board.setLatestShot(cell);
//        board.addChange(cell);
//        return board;
//    }
//
//
//    return board;
//
//}
//
//
//    private BoardController mediumMode(int useByOtherMode){
////        System.out.println("MediumMode");
////        System.out.println("useBy..."+ useByOtherMode);
//        System.out.println("forbidden "+ board.getForbiddenShoots().size() + " " + board.getForbiddenShoots());
//
//
//
//        if(this.board.getLatestShot() == null && this.potentialShoots.isEmpty()) { //szukamy na razie statku
//            return easyMode(useByOtherMode);
//        }else{
//            while (!this.potentialShoots.isEmpty()) {
//
//                Cell actualCell = this.potentialShoots.get(0);//bierzemy następną
//                this.potentialShoots.remove(0);  //usuwamy tą co wzieliśmy
//
//                Cell cell = board.getCell(actualCell.get_x(), actualCell.get_y());
//                if (cell.get_isWasShot()){
//                    continue;
//                }
//
//                enemyTurn = cell.shoot();
//                board.render();
//
//
//                if( cell.getShip() != null){ // wcześniej trafiony ale teraz nie//ustalamy czy pionowy czy poziomy
////                        usuwamy wszystkie (może ich tam już nie być) Cell z tablicy które mają niepasujące koordynaty
////                    if(!cell.getShip().isAlive()){ // sprawdzenie czy statek jest zatopiony
////                        enemyTurn = true;
////                        this.earlierShot = null;
////                        this.potentialShoots.clear();//czyścimy
////                        return board;
////                    }
//                    if(!cell.getShip().isAlive() && useByOtherMode == 1 ){ // sprawdzenie czy statek jest zatopiony
//                        enemyTurn = true;//było true
////                    board.addChange(null);
//                        this.earlierShot = null;
//                        return board;
//
//
//                    }else if(!cell.getShip().isAlive() && useByOtherMode == 2){//hard
//                        for (Cell c :
//                                this.board.getShipNeighbors(cell)) {
//                            this.board.getForbiddenShoots().add(c);
//                        }
//                        System.out.println("neighbors "+ this.board.getShipNeighbors(cell).length);
//                        System.out.println("forbiddenShoots " + this.board.getForbiddenShoots().size() + " " + this.board.getForbiddenShoots());
//                        enemyTurn = true;//było true
////                    board.addChange(null);
//                        this.earlierShot = null;
//                        return board;
//                    }
//
//                    if(this.earlierShot.get_x() == cell.get_x()){ //te same x więc usuwamy te co mają inny x
//                        this.potentialShoots.removeIf(c -> cell.get_x() != c.get_x());
//                        if(this.earlierShot.get_y() == cell.get_y()-1 && cell.get_y()+1 < 10){//ta kolejna komórka jest pod nią
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                            }else if(useByOtherMode == 1){
//
//                                this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()+1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()+1));
//                            }
//                        }
//                        if(this.earlierShot.get_y() == cell.get_y()+1 && cell.get_y()-1 > -1){//ta kolejna komórka jes nad pierwszą
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                            }else if(useByOtherMode== 1){
//
//                                this.potentialShoots.add(this.board.getCell(cell.get_x(), cell.get_y()-1));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x(), cell.get_y()-1));
//                            }
//                        }
//                    }
//                    if(this.earlierShot.get_y() == cell.get_y()){ //te same y więc usuwamy te co mają inny y
//                        this.potentialShoots.removeIf(c -> cell.get_y() != c.get_y());
//
//                        if(this.earlierShot.get_x() == cell.get_x()-1 && cell.get_x()+1 < 10){//ta kolejna komórka jest z prawej
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                            }else if(useByOtherMode == 1){
//
//                                this.potentialShoots.add(this.board.getCell(cell.get_x()+1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()+1, cell.get_y()));
//                            }
//                        }
//                        if(this.earlierShot.get_x() == cell.get_x()+1 && cell.get_x()-1 > -1){//ta kolejna komórka jest z lewej
//                            if((useByOtherMode == 2 && !this.board.getForbiddenShoots().contains(cell))){
//                                this.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                            }else if(useByOtherMode == 1){
//
//                                this.potentialShoots.add(this.board.getCell(cell.get_x()-1, cell.get_y()));
//                                System.out.println("dodaje"+ this.board.getCell(cell.get_x()-1, cell.get_y()));
//                            }
//                        }
//                    }
//
//                    System.out.println("potencjalne"+this.potentialShoots);
//                    System.out.println("forbidden "+ this.board.getForbiddenShoots());
//
//                }
//
////                System.out.println("czegoś nie przewidziałem jeszce");
////                return this.board;
//
//            }
//            return this.board;
//
//        }
////        dodatkowo jeżeli trafi to strzela wokół aż nie zatopi kolejnego
////        strzzał --> zapisujemy ostatni strzał jeśli jest trafiony --> potencjaly strzał 4 (pion, poziom) --> próba pion (góra lub dół) --> 1. trafiony --> usuwamy potencjalne strzały z boków(mają inną jedną z x lub y) i dodajemy ten moniżej nowego --> 2. pudło --> idziemy do następnego z potencjalnych --> jak zatopiony to wywalamy z potencjalnych
//
//
//    }
//
//
//    public BoardController hardMode(int useByOtherMode){
//        System.out.println("HardMode");
//
//        return mediumMode(useByOtherMode);
////        to samo co medium tylko mamy jeszcze zakazane pola jak zatopiony to dodajemy pola obwódki do zakazanych i sprawdzamy czy w nie nie strzeeliliśmy wpętli i dopiero oddajemy strzał w jakiś dozwolony
//
//
//    }


}
