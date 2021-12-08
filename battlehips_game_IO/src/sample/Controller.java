package sample;

import AI.AI;
import bs_game_backend.Cell;
import bs_game_backend.Ship;
import controller.Player1BattleViewController;
import controller.Player1ViewController;
import controller.Player2ViewController;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML
    private Pane boarderpl1_fxml;
    @FXML
    private Pane boarderpl2_fxml;
    @FXML
    private Pane myBoardFXML;
    @FXML
    private Button napieprzajbutton;

    @FXML
    private Button login_pl_1;
    @FXML
    private Button login_pl_2;

    @FXML
    private Button switchToP2Button;
    @FXML
    private Button startGameButton;
    @FXML
    private Button goP1Turn;
    @FXML
    private Button goP2Turn;
    private Random random = new Random();
    public BoardController player2Board, player1Board, ai1Board, ai2Board;
    List<Integer> list_of_ships1 = new ArrayList<Integer>();
    List<Integer> list_of_ships2 = new ArrayList<Integer>();
    List<Integer> list_of_ships3 = new ArrayList<Integer>();
    List<Integer> list_of_ships4 = new ArrayList<Integer>();

    public Controller controller;

    public Player1ViewController player1ViewController;
    public Player2ViewController player2ViewController;
    private Player1BattleViewController player1BattleViewController;

    private boolean Player1Is = false;
    private boolean Player2Is = false;
    private boolean Ai1Is = false;
    private boolean Ai2Is = false;
    private int ai1Level = 0;
    private int ai2Level = 0;


    private AI ai;


    private boolean player2AllShipSet = false , player1AllShipSet = false;
    private boolean player1ShotNow = true, player2ShotNow = true;

    public boolean isPlayer1Is() {
        return Player1Is;
    }

    public void setPlayer1Is(boolean player1Is) {
        Player1Is = player1Is;
    }

    public boolean isPlayer2Is() {
        return Player2Is;
    }

    public void setPlayer2Is(boolean player2Is) {
        Player2Is = player2Is;
    }

    public boolean isAi1Is() {
        return Ai1Is;
    }

    public void setAi1Is(boolean ai1Is) {
        Ai1Is = ai1Is;
    }

    public boolean isAi2Is() {
        return Ai2Is;
    }

    public void setAi2Is(boolean ai2Is) {
        Ai2Is = ai2Is;
    }


    public void setPlayer1BattleViewController(Player1BattleViewController player1BattleViewController) {
        this.player1BattleViewController = player1BattleViewController;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setPlayer1ViewController(Player1ViewController player1ViewController) {
        this.player1ViewController = player1ViewController;
    }

    public void setPlayer2ViewController(Player2ViewController player2ViewController) {
        this.player2ViewController = player2ViewController;
    }

    public int getAi1Level() {
        return ai1Level;
    }

    public void setAi1Level(int ai1Level) {
        this.ai1Level = ai1Level;
    }

    public int getAi2Level() {
        return ai2Level;
    }

    public void setAi2Level(int ai2Level) {
        this.ai2Level = ai2Level;
    }

    @FXML
    void player2BattleView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/player2BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setController(controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        try{
            ((Stage) goP2Turn.getScene().getWindow()).close();
        }catch (Exception e){

        }
    }

    @FXML
    void BufforToP2View(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/bufforToP2View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
        Controller controller = fxmlLoader.getController();
        controller.setController(controller);
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        try{
            ((Stage) napieprzajbutton.getScene().getWindow()).close();
        }catch (Exception e){

        }
    }
    public void createBoardPl1(){
        this.player1Board = createContentPl1();

    }
    public void createBoardPl2(){
        this.player2Board = createContentPl2();
    }
    public void createBoardAi1(){
      this.ai1Board = createContentAi1();
      randomPositionShipAi1();
    }

    public void createBoardAi2(){
        this.ai2Board = createContentAi2();
        randomPositionShipAi2();
    }

    public void insertBoardPl1(Scene scene){
        Pane pane = (Pane) (scene.lookup("#pl1Pane #boarderpl1"));
        pane.getChildren().add(player1Board);
    }

    public void insertBoardPl2(Scene scene){
        Pane pane = (Pane) (scene.lookup("#pl2Pane #boarderpl2"));
        pane.getChildren().add(player2Board);
    }

    public void insertBoardPl1Ready(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        pane.getChildren().add(player1Board);
    }
    public void insertBoardPl2Ready(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        pane.getChildren().add(player2Board);
    }
    public void hideBoardPl1(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        try {
            pane.getChildren().remove(player1Board);
        } catch (Exception e) {
        }
        try {
            pane.getChildren().remove(player2Board);
        } catch (Exception e) {

        }
        try {
            pane.getChildren().remove(ai1Board);
        } catch (Exception e) {

        }
        try {
            pane.getChildren().remove(ai2Board);
        } catch (Exception e) {

        }


    }
    public void hideBoardPl2(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        try {
            pane.getChildren().remove(player2Board);
        } catch (Exception e) {

        }
        try {
            pane.getChildren().remove(player1Board);
        } catch (Exception e) {

        }
        try {
            pane.getChildren().remove(ai1Board);
        } catch (Exception e) {

        }
        try {
            pane.getChildren().remove(ai2Board);
        } catch (Exception e) {

        }
    }
    public void insertBoardPl2Ship(Scene scene){ // pokazywanie planszy gracza 2 ze statkami, tura gracza 2
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        player2Board.setEnableShot(false);
        player2Board.render();
        pane.getChildren().add(player2Board);
        player2ShotNow = true;
    }
    public void insertBoardPl1Shoot(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        player1Board.setEnableShot(true);
        player1Board.render();
        pane.getChildren().add(player1Board);

    }
    public void insertBoardPl1Ship(Scene scene){ // pokazywanie planszy gracza 2 ze statkami, tura gracza 1
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        player1Board.setEnableShot(false);
        player1Board.render();
        pane.getChildren().add(player1Board);
        player1ShotNow = true;
    }
    public void insertBoardPl2Shoot(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        player2Board.setEnableShot(true);
        player2Board.render();
        pane.getChildren().add(player2Board);

    }
    public void insertBoardAi1Ship(Scene scene){ // pokazywanie planszy gracza 2 ze statkami, tura gracza 1
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        ai1Board.setEnableShot(false);
        ai1Board.render();
        pane.getChildren().add(ai1Board);
    }

    public void insertBoardAi1Shoot(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        ai1Board.setEnableShot(true);
        ai1Board.render();
        pane.getChildren().add(ai1Board);
    }
    public void insertBoardAi2Ship(Scene scene){ // pokazywanie planszy gracza 2 ze statkami, tura gracza 1
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        ai2Board.setEnableShot(false);
        ai2Board.render();
        pane.getChildren().add(ai2Board);
    }
    public void insertBoardAi2Shoot(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        ai2Board.setEnableShot(true);
        ai2Board.render();
        pane.getChildren().add(ai2Board);
    }





    EventHandler<MouseEvent> eventHandlerPl1 = new EventHandler<MouseEvent>() {
        @Override

        public void handle(MouseEvent event) {
            boolean end_s = false;

            player1ViewController.setSwitchToP2ButtonDisabel();
            Cell cell = (Cell) event.getSource();
            if(list_of_ships1.isEmpty()){
                player1AllShipSet = true;
                if (!player1ViewController.isNextGameWithAi()) {
                    player1ViewController.setSwitchToP2ButtonEnable();
                }
                if (player1ViewController.isNextGameWithAi()) {
                    player1ViewController.setStartGameAiButtonEnable();
                }
            }

            if (!player1AllShipSet) {
                if(!list_of_ships1.isEmpty()) {
                    if (player1Board.placeShip(new Ship(list_of_ships1.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                        try {
                            System.out.println(list_of_ships1.get(0));
                            list_of_ships1.remove(0);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Juz postawiono wszystkie statki");
                            if (!player1ViewController.isNextGameWithAi()) {
                                player1ViewController.setSwitchToP2ButtonEnable();
                            }
                            if (player1ViewController.isNextGameWithAi()) {
                                player1ViewController.setStartGameAiButtonEnable();
                            }
                        }
                    }
                }
                else{
                    System.out.println("Juz postawiono wszystkie statki");
                    try {
                        if (!player1ViewController.isNextGameWithAi()) {
                            player1ViewController.setSwitchToP2ButtonEnable();
                        }
                        if (player1ViewController.isNextGameWithAi()) {
                            player1ViewController.setStartGameAiButtonEnable();
                        }
                    } catch (Exception e) {
                   }
                    player1AllShipSet = true;
                    end_s = true;
                }
            } else {
                if (player2ShotNow) {
                    if (player1Board.isEnableShot()) {

                        if (cell.get_isWasShot()) {
                            return;
                        } else {
                            cell.shoot();

                            if(player1Board.endGame()){
                                System.out.println("Wygrał gracz Nr 2");
                                hideBoardPl1(player1BattleViewController.nextButton.getScene());
                                hideBoardPl2(player1BattleViewController.nextButton.getScene());
                                player1BattleViewController.playerNumberLabel.setText("Wygrał gracz Nr 2");
                                player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                                player1BattleViewController.setContinueButtonEnable();
                            }
                            if (cell.getShip() == null) {
                                player2ShotNow = false;
                                player1BattleViewController.setNextButtonEnable();
                            }
                        }

                    }
                } else {
                }



            }


        }
    };


    EventHandler<MouseEvent> eventHandlerPl2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            Cell cell = (Cell) event.getSource();

            if(list_of_ships2.isEmpty()){
                player2AllShipSet = true;
                player2ViewController.setStartGameButtonEnable();
            }
            if (!player2AllShipSet) {

                if(!list_of_ships2.isEmpty()) {
                    if (player2Board.placeShip(new Ship(list_of_ships2.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                        try {

                            list_of_ships2.remove(0);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Juz postawiono wszystkie statki");
                        }
                    }
                }
                else{
                    System.out.println("Juz postawiono wszystkie statki");
                    try {
                        player2ViewController.setStartGameButtonEnable();
                    } catch (Exception e) {
                    }
                    player2AllShipSet = true;
                    end_s = true;
                }
            }else {

                if (player1ShotNow) {
                    if (player2Board.isEnableShot()) {
                        if (cell.get_isWasShot()) {
                            return;
                        } else {
                            cell.shoot();
                            if(player2Board.endGame()){
                                System.out.println("Wygrał gracz Nr 1");
                                hideBoardPl1(player1BattleViewController.nextButton.getScene());
                                hideBoardPl2(player1BattleViewController.nextButton.getScene());
                                player1BattleViewController.playerNumberLabel.setText("Wygrał gracz Nr 1");
                                player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                                player1BattleViewController.setContinueButtonEnable();
                            }
                            if (cell.getShip() == null) {
                                player1ShotNow = false;
                                player1BattleViewController.setNextButtonEnable();
                            }
                        }

                    }
                } else {
                }
            }
        }
    };
    EventHandler<MouseEvent> eventHandlerAi = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Cell cell = (Cell) event.getSource();
                if (player1ShotNow) {
                    if (ai1Board.isEnableShot()) {
                        if (cell.get_isWasShot()) {
                            return;
                        } else {
                            cell.shoot();
                            if(ai1Board.endGame()){
                                System.out.println("Wygrał gracz Nr 1");
                                hideBoardPl1(player1BattleViewController.nextButton.getScene());    // ukrywanie planszy po wygranej
                                hideBoardPl2(player1BattleViewController.nextButton.getScene());
                                player1BattleViewController.playerNumberLabel.setText("Wygrał gracz Nr 1");
                                player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                                player1BattleViewController.setContinueButtonEnable();
                            }
                            if (cell.getShip() == null) {
                                player1ShotNow = false;
                               // strzelanie bota
                                player1BattleViewController.playerNumberLabel.setText("Strzela Komputer");

                                /// do wyjebania przydatbne w testach
                                ai = new AI(ai1Level, player1Board);
                                player1Board = ai.moveAI();
                                if(player1Board.endGame()){
                                    System.out.println("Wygrało AI");
                                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                                    player1BattleViewController.playerNumberLabel.setText("Wygrało AI");
                                    player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                                    player1BattleViewController.setContinueButtonEnable();
                                    return;
                                }
                                hideBoardPl1(player1BattleViewController.nextButton.getScene());
                                insertBoardPl1Ship(player1BattleViewController.nextButton.getScene());
                                player1BattleViewController.playerNumberLabel.setText("Tura Gracza Nr 1");
                                ///
                                //to zostawić
//                                long time = System.nanoTime();
//                                AnimationTimer timerAi = new AnimationTimer() {
//                                    @Override
//                                    public void handle(long l) {
//                                        if(l - 1_000_000_000 > time){
//                                            ai = new AI(aiLevel, player1Board);
//                                            player1Board = ai.moveAI();
            //                                if(player1Board.endGame()){
            //                                    System.out.println("Wygrało AI");
            //                                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
            //                                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
            //                                    player1BattleViewController.playerNumberLabel.setText("Wygrało AI");
            //                                    player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
            //                                    player1BattleViewController.setContinueButtonEnable();
            //                                    return;
            //                                }
//                                            hideBoardPl1(player1BattleViewController.nextButton.getScene());
//                                            insertBoardPl1Ship(player1BattleViewController.nextButton.getScene());
//                                            player1BattleViewController.playerNumberLabel.setText("Tura Gracza Nr 1");
//                                            super.stop();
//                                        }
//                                    }
//                                };
//                                timerAi.start();
                                if(ai1Board.endGame()){
                                    System.out.println("Wygrał gracz Nr 1");
                                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                                    player1BattleViewController.playerNumberLabel.setText("Wygrał gracz Nr 1");
                                    player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                                    player1BattleViewController.setContinueButtonEnable();
                                }




                            }
                        }

                    }
                } else {
                }

            }

    };

    public void aiVsAiStartGame(){
        //ustawiono staki 1 i strzelanie2

        player1BattleViewController.playerNumberLabel.setText("Tura AI 1");

        long time = System.nanoTime();

        AnimationTimer changeView2 = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long l) {
                if(l - 2_000_000 > time){
                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardAi1Ship(player1BattleViewController.nextButton.getScene());
                    insertBoardAi2Shoot(player1BattleViewController.nextButton.getScene());
                    super.stop();
                    aiVsAiStartGame();
                }
            }
        };

        AnimationTimer timerAi2Shoot = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l - 1_500_000 > time){
                    ai = new AI(ai2Level, ai1Board);
                    ai1Board = ai.moveAI();
                    if(ai1Board.endGame()){
                        System.out.println("Wygrało AI 2");
                        hideBoardPl1(player1BattleViewController.nextButton.getScene());
                        hideBoardPl2(player1BattleViewController.nextButton.getScene());
                        player1BattleViewController.playerNumberLabel.setText("Wygrało AI 2");
                        player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                        player1BattleViewController.setContinueButtonEnable();
                        super.stop();
                        return;
                    }
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardAi1Shoot(player1BattleViewController.nextButton.getScene());
                    super.stop();
                    changeView2.start();

                }
            }
        };
        AnimationTimer changeView = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long l) {
                if(l - 1_000_000 > time){
                    player1BattleViewController.playerNumberLabel.setText("Tura AI 2");
                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardAi2Ship(player1BattleViewController.nextButton.getScene());
                    insertBoardAi1Shoot(player1BattleViewController.nextButton.getScene());
                    super.stop();
                    timerAi2Shoot.start();

                }
            }
        };


        AnimationTimer timerAi1Shoot = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l - 500_000 > time){
                    ai = new AI(ai1Level, ai2Board);
                    ai2Board = ai.moveAI();
                    if(ai2Board.endGame()){
                        System.out.println("Wygrało AI 1");
                        hideBoardPl1(player1BattleViewController.nextButton.getScene());
                        hideBoardPl2(player1BattleViewController.nextButton.getScene());
                        player1BattleViewController.playerNumberLabel.setText("Wygrało AI 1");
                        player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
                        player1BattleViewController.setContinueButtonEnable();
                        super.stop();
                        return;
                    }
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardAi2Shoot(player1BattleViewController.nextButton.getScene());
                    super.stop();
                    changeView.start();

                }
            }
        };
            timerAi1Shoot.start();



    }






    public BoardController createContentPl1() {
        list_of_ships1.add(4);
        list_of_ships1.add(3);
        list_of_ships1.add(3);
        list_of_ships1.add(2);
        list_of_ships1.add(2);
        list_of_ships1.add(2);
        list_of_ships1.add(1);
        list_of_ships1.add(1);
        list_of_ships1.add(1);
        list_of_ships1.add(1);
        return new BoardController(false, eventHandlerPl1);
    }


    public BoardController createContentPl2() {
        list_of_ships2.add(4);
        list_of_ships2.add(3);
        list_of_ships2.add(3);
        list_of_ships2.add(2);
        list_of_ships2.add(2);
        list_of_ships2.add(2);
        list_of_ships2.add(1);
        list_of_ships2.add(1);
        list_of_ships2.add(1);
        list_of_ships2.add(1);

        return new BoardController(false, eventHandlerPl2);
    }
    public BoardController createContentAi1() {
        list_of_ships3.add(4);
        list_of_ships3.add(3);
        list_of_ships3.add(3);
        list_of_ships3.add(2);
        list_of_ships3.add(2);
        list_of_ships3.add(2);
        list_of_ships3.add(1);
        list_of_ships3.add(1);
        list_of_ships3.add(1);
        list_of_ships3.add(1);

        return new BoardController(true, eventHandlerAi);
    }
    public BoardController createContentAi2() {
        list_of_ships4.add(4);
        list_of_ships4.add(3);
        list_of_ships4.add(3);
        list_of_ships4.add(2);
        list_of_ships4.add(2);
        list_of_ships4.add(2);
        list_of_ships4.add(1);
        list_of_ships4.add(1);
        list_of_ships4.add(1);
        list_of_ships4.add(1);

        return new BoardController(true, eventHandlerAi);
    }

    public void randomPositionShipPlayer1(Scene scene){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships1.size();
        if(type == 0){
            createBoardPl1();
            type = list_of_ships1.size();
            insertBoardPl1(scene);
        }

        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (player1Board.placeShip(new Ship(list_of_ships1.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                list_of_ships1.remove(0);
            }
        }
        if (!player1ViewController.isNextGameWithAi()) {
            player1ViewController.setSwitchToP2ButtonEnable();
        }
        if (player1ViewController.isNextGameWithAi()) {
            player1ViewController.setStartGameAiButtonEnable();
        }
    }
    public void randomPositionShipPlayer2(Scene scene){        //randowmowe ustawianie staktów dla player 2
        int type = list_of_ships2.size();
        if(type == 0){
            createBoardPl2();
            type = list_of_ships2.size();
            insertBoardPl2(scene);
        }

        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (player2Board.placeShip(new Ship(list_of_ships2.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                list_of_ships2.remove(0);
            }
        }
        player2ViewController.setStartGameEnable();

    }
    public void randomPositionShipAi1(){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships3.size();
        if(type == 0){
            createBoardAi1();
            type = list_of_ships3.size();
        }
        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (ai1Board.placeShip(new Ship(list_of_ships3.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                list_of_ships3.remove(0);
            }
        }

    }
    public void randomPositionShipAi2(){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships4.size();
        if(type == 0){
            createBoardAi1();
            type = list_of_ships4.size();
        }
        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (ai2Board.placeShip(new Ship(list_of_ships4.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                list_of_ships4.remove(0);
            }
        }

    }




}
