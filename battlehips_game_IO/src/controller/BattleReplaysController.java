package controller;

import AI.AI;
import db.DbConnection;
import db.game_Classes.Changes;
import db.game_Classes.GameDB;
import db.game_Classes.InitialState;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.BoardController;
import sample.CellToDB;
import sample.Controller;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

public class BattleReplaysController {
    @FXML
    public Label playerNumberLabel;
    @FXML
    public Label  player1Label;
    @FXML
    public Label  player2Label;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button exitButton;
    private BoardController player2Board, player1Board;
    private InitialState initialState;
    private Changes changes;
    private boolean plyer1Hit = false;

    private int countReplaysPlayer1 = 0;
    private int countReplaysPlayer2 = 0;

    private GameDB gameDB;
    private DbConnection dbConnection;
    Controller controller;
    User player1, player2;
    long time;

    public void initialize(){
        dbConnection = new DbConnection();
        backToMenuButton.setDisable(true);
    }

    public void setToReplays(User player1, User player2, GameDB game){
        //ArrayList<CellToDB> p1InitialArray, ArrayList<CellToDB> p2InitialArray,ArrayList<CellToDB> p1ChangesArray, ArrayList<CellToDB> p2ChangesArray
        gameDB = dbConnection.getSpecyficGame(game.getId());
//        System.out.println("========================================");
//        System.out.println(gameDB.getInitialState().getP1InitialArray());
//        System.out.println(gameDB.getInitialState().getP2InitialArray());
//        System.out.println("dupa");
//        System.out.println(gameDB.getChanges().getP1ChangesArray());
//        System.out.println(gameDB.getChanges().getP2ChangesArray());
        this.player1 = player1;
        this.player2 = player2;
        player1Label.setText(player1.getUsername());
        player2Label.setText(player2.getUsername());

        ArrayList<CellToDB> p1InitialArray = gameDB.getInitialState().getP1InitialArray();
        ArrayList<CellToDB> p2InitialArray = gameDB.getInitialState().getP2InitialArray();
        ArrayList<CellToDB> p1ChangesArray = gameDB.getChanges().getP1ChangesArray();
        ArrayList<CellToDB> p2ChangesArray = gameDB.getChanges().getP2ChangesArray();

        initialState = new InitialState(p1InitialArray,p2InitialArray);
        changes = new Changes(p1ChangesArray,p2ChangesArray);

        player1Board = new BoardController();
        player2Board = new BoardController();
        player1Board.render();
        player2Board.render();
        Pane pane = (Pane) (exitButton.getScene().lookup("#mainPaneP1Battle #myBoardP1Battle"));
        pane.getChildren().add(player1Board);
        Pane pane2 = (Pane) (exitButton.getScene().lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        pane2.getChildren().add(player2Board);
//        System.out.println("++++++++++++++++++++++++++++++++++++++");
//        System.out.println(initialState.getP1InitialArray());
//        System.out.println("==========================================");
//        System.out.println(initialState.getP2InitialArray());
//        System.out.println("dupadupadupa");
//        System.out.println(changes.getP1ChangesArray());
//        System.out.println("=============================================");
//        System.out.println(changes.getP2ChangesArray());

        for (CellToDB cellToDB:
             initialState.getP1InitialArray()) {
            if (cellToDB.getShip() != null) {
                player1Board.setCell(cellToDB.getX(),cellToDB.getY(),cellToDB.getShip().getType(), cellToDB.getShip().isVertical());
            }
        }
        player1Board.render();
        for (CellToDB cellToDB:
                initialState.getP2InitialArray()) {
            if (cellToDB.getShip() != null) {
                player2Board.setCell(cellToDB.getX(),cellToDB.getY(),cellToDB.getShip().getType(), cellToDB.getShip().isVertical());
            }
        }
        player2Board.render();
        countReplaysPlayer1 = 0;
        countReplaysPlayer2 = 0;

        startReplays();


    }

    public void startReplays(){
        playerNumberLabel.setText("Now is turn player: " + player1.getUsername());
        long  timeOfAiCahnge = 1_000_000_000;  // zmienić 1 na 1_000_000_000 do testów zmniejszone
        time = System.nanoTime();
        AnimationTimer timerAi1 = new AnimationTimer() {

            @Override
            public void handle(long l) {
                // zmienić 1 na 1_000_000_000
                if(l - (timeOfAiCahnge) > time){
                    if(player1Board.endGame()){
                        super.stop();
                        backToMenuButton.setDisable(false);
                        return;
                    }
                    if(countReplaysPlayer1 == changes.getP1ChangesArray().size()){
                        super.stop();
                        backToMenuButton.setDisable(false);
                        return;
                    }
                    player1Board.getCell(changes.getP1ChangesArray().get(countReplaysPlayer1).getX(),changes.getP1ChangesArray().get(countReplaysPlayer1).getY()).shoot();
                    if( player1Board.getCell(changes.getP1ChangesArray().get(countReplaysPlayer1).getX(),changes.getP1ChangesArray().get(countReplaysPlayer1).getY()).getShip() != null){
                        plyer1Hit = true;
                        countReplaysPlayer1+= 1;
                        super.stop();
                    }else {
                        player1Board.render();
                        countReplaysPlayer1+= 1;
                        super.stop();
                        startReplays();
                    }
                }
            }
        };
        if(plyer1Hit){
            playerNumberLabel.setText("Now is turn player: " + player1.getUsername());
            time = System.nanoTime();
            timerAi1.start();
            plyer1Hit = false;
            return;
        }
        AnimationTimer timerAi2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // zmienić 1 na 1_000_000_000
                if(l - (timeOfAiCahnge) > time){
                    if(player2Board.endGame()){
                        super.stop();
                        backToMenuButton.setDisable(false);
                        return;
                    }
                    player2Board.getCell(changes.getP2ChangesArray().get(countReplaysPlayer2).getX(),changes.getP2ChangesArray().get(countReplaysPlayer2).getY()).shoot();
                    if(player2Board.getCell(changes.getP2ChangesArray().get(countReplaysPlayer2).getX(),changes.getP2ChangesArray().get(countReplaysPlayer2).getY()).getShip() !=null){
                        plyer1Hit = false;
                        super.stop();
                        startReplays();
                        player2Board.render();
                        countReplaysPlayer2 += 1;
                        backToMenuButton.setDisable(false);
                        return;

                    }
                    player2Board.render();
                    countReplaysPlayer2 += 1;
                    if(countReplaysPlayer2 == changes.getP2ChangesArray().size()){
                        super.stop();
                        backToMenuButton.setDisable(false);
                        return;
                    }
                    super.stop();
                    playerNumberLabel.setText("Now is turn player: " + player2.getUsername());
                    time = System.nanoTime();
                    timerAi1.start();
                    return;
                }

            }
        };
        timerAi2.start();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }

    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        ((Stage) backToMenuButton.getScene().getWindow()).close();

        return;


    }

}
