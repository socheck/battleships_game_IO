package controller;

import db.DbConnection;
import db.game_Classes.Changes;
import db.game_Classes.GameDB;
import db.game_Classes.InitialState;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import bs_game_backend.BoardController;
import bs_game_backend.CellToDB;
import bs_game_backend.Controller;
import bs_game_backend.User;

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
    @FXML
    public Pane player1Pane;
    @FXML
    public Pane player2Pane;
    @FXML
    public Pane winnerPane;
    @FXML
    public Button backToReplaysButton;

    private BoardController player2Board, player1Board;
    private InitialState initialState;
    private Changes changes;
    private boolean plyer1Hit = false;
    private boolean backToMenu = false;


    private int countReplaysPlayer1 = 0;
    private int countReplaysPlayer2 = 0;

    private GameDB gameDB;
    private DbConnection dbConnection;
    Controller controller;
    User player1, player2;
    long time;

    public void initialize(){
        dbConnection = new DbConnection();
    }

    public void setToReplays(User player1, User player2, GameDB game){
        player1Board = new BoardController();
        player2Board = new BoardController();
        player1Board.render();
        player2Board.render();
        Pane pane = (Pane) (exitButton.getScene().lookup("#mainPaneP1Battle #myBoardP1Battle"));
        pane.getChildren().add(player1Board);
        Pane pane2 = (Pane) (exitButton.getScene().lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        pane2.getChildren().add(player2Board);
        gameDB = dbConnection.getSpecyficGame(game.getId());
        this.player1 = player1;
        this.player2 = player2;
        player1Label.setText(player1.getUsername());
        player2Label.setText(player2.getUsername());
        player1Pane.getChildren().add(player1.getPhoto());
        player2Pane.getChildren().add(player2.getPhoto());

        ArrayList<CellToDB> p1InitialArray = gameDB.getInitialState().getP1InitialArray();
        ArrayList<CellToDB> p2InitialArray = gameDB.getInitialState().getP2InitialArray();
        ArrayList<CellToDB> p1ChangesArray = gameDB.getChanges().getP1ChangesArray();
        ArrayList<CellToDB> p2ChangesArray = gameDB.getChanges().getP2ChangesArray();

        initialState = new InitialState(p1InitialArray,p2InitialArray);
        changes = new Changes(p1ChangesArray,p2ChangesArray);

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
        if(backToMenu){
            return;
        }
        long  timeOfAiCahnge = 1_000_000_000;
        time = System.nanoTime();
        AnimationTimer timerAi1 = new AnimationTimer() {

            @Override
            public void handle(long l) {
                // zmienić 1 na 1_000_000_000
                if(l - (timeOfAiCahnge) > time){
                    if(player1Board.endGame()){
                        playerNumberLabel.setText("Winner is "+ player2.getUsername());
                        winnerPane.getChildren().add(player2.getPhoto());
                        player1Pane.getChildren().remove(player1.getPhoto());
                        super.stop();
                        return;
                    }
                    if(countReplaysPlayer1 == changes.getP1ChangesArray().size()){
                        super.stop();
                        return;
                    }
                    player1Board.getCell(changes.getP1ChangesArray().get(countReplaysPlayer1).getX(),changes.getP1ChangesArray().get(countReplaysPlayer1).getY()).shoot();
                    if( player1Board.getCell(changes.getP1ChangesArray().get(countReplaysPlayer1).getX(),changes.getP1ChangesArray().get(countReplaysPlayer1).getY()).getShip() != null){
                        plyer1Hit = true;
                        countReplaysPlayer1+= 1;
                        super.stop();
                        startReplays();
                        return;
                    }else {
                        player1Board.render();
                        countReplaysPlayer1+= 1;
                        super.stop();
                        startReplays();

                        return;
                    }

                }
            }
        };
        if(plyer1Hit){
            time = System.nanoTime();
            timerAi1.start();
            plyer1Hit = false;
            return;
        }
        AnimationTimer timerAi2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l - (timeOfAiCahnge) > time){
                    if(player2Board.endGame()){
                        playerNumberLabel.setText("Winner is "+ player1.getUsername());
                        winnerPane.getChildren().add(player1.getPhoto());
                        player2Pane.getChildren().remove(player2.getPhoto());
                        super.stop();
                        return;
                    }
                    if(countReplaysPlayer2 == changes.getP2ChangesArray().size()){
                        super.stop();
                        return;
                    }
                    player2Board.getCell(changes.getP2ChangesArray().get(countReplaysPlayer2).getX(),changes.getP2ChangesArray().get(countReplaysPlayer2).getY()).shoot();
                    if(player2Board.getCell(changes.getP2ChangesArray().get(countReplaysPlayer2).getX(),changes.getP2ChangesArray().get(countReplaysPlayer2).getY()).getShip() !=null){
                        plyer1Hit = false;
                        player2Board.render();
                        countReplaysPlayer2 += 1;
                        super.stop();
                        startReplays();
                        return;
                    }
                    player2Board.render();
                    countReplaysPlayer2 += 1;
                    super.stop();
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
        backToMenu = true;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();
        return;
    }

    @FXML
    public void  backToReplaysAction() throws IOException {
        backToMenu = true;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/replaysMenuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToReplaysButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Replays");
        stage.show();
        return;
    }
}
