package sample;
import java.util.ArrayList;
import java.util.List;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class BoardController extends Parent {

    private VBox rows = new VBox();
    private boolean enableShot = false;  // false widzimy nasze statki//
    private boolean boardIsAi = false;      //false planasza jest gracza
    public int ships = 10;
    public Cell earlierShot;
    public Cell firstShipCell;
    private ArrayList<Cell> forbiddenShoots;
    public ArrayList<Cell> potentialShoots;
    private ArrayList<Cell> changes;
    private ArrayList<CellToDB> forbiddenShootsToDB;
    private int iter = 0;

    public ArrayList<Cell> getForbiddenShoots() {
        return forbiddenShoots;
    }

    public boolean isPreviousShotTelling(){ //true jeżeli ostatni strzał byl trafiony
        if(getEarlierShot().isWasShot() && getEarlierShot().getShip() != null){
            return true;
        }
        return false;
    }

    public Cell getEarlierShot() {
        return earlierShot;
    }

    public ArrayList<Cell> getPotentialShoots() {
        return potentialShoots;
    }


    public ArrayList<Cell> getChanges() {
        return changes;
    }
    public void addChange(Cell lastest){
        this.earlierShot = lastest;
        this.changes.add(lastest);
    }


    public BoardController(boolean boardIsAi, EventHandler<? super MouseEvent> handler) {
        this.boardIsAi = boardIsAi;

        this.earlierShot = null;
        this.changes = new ArrayList<Cell>();
        this.forbiddenShoots = new ArrayList<Cell>();
        this.potentialShoots = new ArrayList<Cell>();

        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell c = new Cell(x, y, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }

        getChildren().add(rows);
        render();
    }
    public BoardController(boolean boardIsAi, EventHandler<? super MouseEvent> handler,EventHandler<? super MouseEvent> handlerSetShipEntered,EventHandler<? super MouseEvent> handlerSetShipExit) {
        this.boardIsAi = boardIsAi;

        this.earlierShot = null;
        this.changes = new ArrayList<Cell>();
        this.forbiddenShoots = new ArrayList<Cell>();
        this.potentialShoots = new ArrayList<Cell>();

        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell c = new Cell(x, y, this);
                c.setOnMouseClicked(handler);
                c.setOnMouseEntered(handlerSetShipEntered);
                c.setOnMouseExited(handlerSetShipExit);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }

        getChildren().add(rows);
        render();
    }

    @Override
    public String toString() {
        return "BoardController{" +
                "rows=" + rows +
                ", isAI=" + enableShot +
                ", ships=" + ships +
                '}';
    }
    public void render(){

        Image water = new Image("/img/water.gif");
        Image myBoard_shipHit = new Image("/img/myBoard_shipHit.gif");
        Image myBoard_shipNoHit = new Image("/img/myBoard_shipNoHit.jpg");
        Image myBoard_shipSunk = new Image("/img/myBoard_shipSunk.gif");
        Image myBoard_shotNoHit = new Image("/img/myBoard_shotNoHit.gif");
        Image shooting_shot = new Image("/img/shooting_shot.gif");
        Image shooting_shotHit = new Image("/img/shooting_shotHit.gif");
        Image shooting_shotSunk = new Image("/img/shooting_shotSunk.gif");


        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell cell = getCell(x, y);

                // enableshoot - false widzimy nasze statki

                if(enableShot){  // widok do strzelania
                    cell.setFill(Color.LIGHTGREY);  // nie strzelone
                    cell.setStroke(Color.BLACK);
                    cell.setFill(new ImagePattern(water));
                    if(cell.get_isWasShot() && cell.getShip() == null){
                        cell.setFill(Color.BLACK);   //sztrzelone nie trafione
                        cell.setFill(new ImagePattern(shooting_shot));
                    }
                    if(cell.get_isWasShot() && cell.getShip() != null){
                        cell.setFill(Color.RED); //strzelone trafione
                        cell.setFill(new ImagePattern(shooting_shotHit));
                        if(!cell.ship.isAlive()){
                        //trafiony zatopiony zatopiony
                            cell.setFill(new ImagePattern(shooting_shotSunk));
                        }
                    }


                }
                if(!enableShot){
                    cell.setFill(Color.LIGHTGREY);  // nie strzelone
                    cell.setStroke(Color.BLACK);
                    cell.setFill(new ImagePattern(water));
                    if(cell.ship != null){  // statek
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                        cell.setFill(new ImagePattern(myBoard_shipNoHit));
                    }
                    if(cell.get_isWasShot() && cell.getShip() != null){ // trafienie statku
                        cell.setFill(Color.BLUE);
                        cell.setStroke(Color.GRAY);
                        cell.setFill(new ImagePattern(myBoard_shipHit));
                        if(!cell.ship.isAlive()){ //trafiony zatopiony
                            cell.setFill(new ImagePattern(myBoard_shipSunk));

                        }
                    }
                    if(cell.get_isWasShot() && cell.getShip() == null){ // strzał bez statku
                        cell.setFill(Color.MAROON);
                        cell.setStroke(Color.GRAY);
                        cell.setFill(new ImagePattern(myBoard_shotNoHit));
                    }


                }

            }
        }

    }


    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.getType();

            if (ship.isVertical()) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                    if (!enableShot) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                        render();
                    }
                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                    if (!enableShot) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                        render();
                    }
                }
            }

            return true;
        }

        return false;
    }

    public void placeShipView(Ship ship, int x, int y) {

        if (canPlaceShip(ship, x, y)) {
            int length = ship.getType();

            if (ship.isVertical()) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);

                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);


                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);

                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);


                }
            }
        }else{
            try {
                int length = ship.getType();
                if (ship.isVertical()) {
                    for (int i = y; i < y + length; i++) {

                        Cell cell = getCell(x, i);

                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.RED);


                    }
                }
                else {
                    for (int i = x; i < x + length; i++) {
                        Cell cell = getCell(i, y);

                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.RED);



                    }
                }
            } catch (Exception e) {

            }

        }
    }

    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    public Cell[] getShipNeighbors(Cell cell){

        List<Cell> neighborList = new ArrayList<Cell>();
        List<Cell> shipCells = new ArrayList<Cell>();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell c = this.getCell(x,y);

                if(cell.getShip() == c.getShip()){

                    shipCells.add(c);
                }
            }
        }

        for (Cell shipCell :
                shipCells) {
            for (Cell neighborCell :
                 this.getNeighbors(shipCell.get_x(), shipCell.get_y())) {
                neighborList.add(neighborCell);

            }
        }
        System.out.println("metoda neighborShip "+ neighborList);
        return neighborList.toArray(new Cell[0]);
    }

    public Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1),
                new Point2D(x + 1, y + 1 ),
                new Point2D(x + 1, y - 1),
                new Point2D(x - 1, y + 1),
                new Point2D(x - 1, y - 1)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.getType();

        if (ship.isVertical()) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i)){
                    System.out.println("1");
                    return false;
                }


                Cell cell = getCell(x, i);
                if (cell.ship != null){
                    System.out.println("2");
                    return false;
                }



                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i)){
                        System.out.println("3");
                        return false;
                    }


                    if (neighbor.ship != null){
                        System.out.println("4");
                        return false;
                    }

                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public void setEnableShot(boolean enableShot) {
        this.enableShot = enableShot;
    }

    public VBox getRows() {
        return rows;
    }

    public void setRows(VBox rows) {
        this.rows = rows;
    }

    public boolean isEnableShot() {
        return enableShot;
    }

    public int getShips() {
        return ships;
    }

    public void setShips(int ships) {
        this.ships = ships;
    }
    public boolean endGame(){ //return true = koniec gry
        if(ships == 0){
            return true;
        }
        return false;
    }
    public void clearShip(){
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell c = getCell(x,y);
                c.ship = null;
            }
        }
        render();
    }

    public ArrayList<CellToDB> getInitilaState(BoardController boardController){
        ArrayList<CellToDB> cellToDBArrayList = new ArrayList<CellToDB>();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell c = boardController.getCell(x,y);
                cellToDBArrayList.add(new CellToDB(c.get_x(),c.get_y(),c.getShip(),false));
            }
        }
        return cellToDBArrayList;
    }
    public void makeForbidenShootToDB(){
        forbiddenShootsToDB = new ArrayList<CellToDB>();
        for (Cell c:
             changes) {
            forbiddenShootsToDB.add(new CellToDB(c.get_x(), c.get_y(), c.getShip(), c.isWasShot()));
        }
        System.out.println(forbiddenShootsToDB);
        System.out.println("============================================");
        System.out.println(changes);
    }


}