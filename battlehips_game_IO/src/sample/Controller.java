package sample;

import AI.AI;
import bs_game_backend.Cell;
import bs_game_backend.Ship;
import controller.Player1BattleViewController;
import controller.Player1ViewController;
import controller.Player2ViewController;
//import db.dbConnection;
import db.DbConnection;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private boolean shipIsVertical = true;

    private int iter; //usunąc

    private boolean aiSootWithPlayer1Now = false; // true ai grajace z graczem 1 strzela teraz
    private AI ai;

    private boolean player2AllShipSet = false , player1AllShipSet = false;
    private boolean player1ShotNow = true, player2ShotNow = true;

    private long  timeOfAiCahnge = 1_000;   // do testó ustawiona mała wartość wrócić do 1_000_000_000








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

    public Pane getBoarderpl1_fxml() {
        return boarderpl1_fxml;
    }

    public List<Integer> getList_of_ships1() {
        return list_of_ships1;
    }

    public List<Integer> getList_of_ships2() {
        return list_of_ships2;
    }

    public List<Integer> getList_of_ships3() {
        return list_of_ships3;
    }

    public List<Integer> getList_of_ships4() {
        return list_of_ships4;
    }

    public void createBoardPl1(){
        this.player1Board = createBoardController(false, list_of_ships1, eventHandlerPl1, eventHandlerShipViewEnteredPl1, eventHandlerShipViewExit);
    }
    public void createBoardPl2(){
        this.player2Board = createBoardController(false, list_of_ships2, eventHandlerPl2, eventHandlerShipViewEnteredPl2, eventHandlerShipViewExit);
    }
    public void createBoardAi1(){
      this.ai1Board = createBoardController(true, list_of_ships3, eventHandlerAi);
      randomPositionShipAi(list_of_ships3, ai1Board);
    }
    public void createBoardAi2(){
        this.ai2Board = createBoardController(true, list_of_ships4, eventHandlerAi);
        randomPositionShipAi(list_of_ships4, ai2Board);
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


    public void insertBoardShip(Scene scene, BoardController board){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        board.setEnableShot(false);
        board.render();
        pane.getChildren().add(board);
        if(board == player1Board){
            player1ShotNow = true;
        }
        if(board == player2Board){
            player2ShotNow = true;
        }

    }

    public void insertBoardShoot(Scene scene, BoardController board){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #enemyBoardP1Battle"));
        board.setEnableShot(true);
        board.render();
        pane.getChildren().add(board);

    }
    public void clearShipOnBoardPL(List<Integer> list, BoardController board){
        board.clearShip();
        list.clear();
        makeListOfShips(list);
        if(list == list_of_ships1){
            player1AllShipSet = false;
        }
        if(list == list_of_ships2){
            player2AllShipSet = false;
        }



    }

//    public void clearShipOnBoardPl1(){
//        player1Board.clearShip();
//        list_of_ships1.clear();
//        makeListOfShips(list_of_ships1);
//        player1AllShipSet = false;
//
//    }
//    public void clearShipOnBoardPl2( ){
//        player2Board.clearShip();
//        list_of_ships2.clear();
//        makeListOfShips(list_of_ships2);
//        player2AllShipSet = false;
//    }



    EventHandler<MouseEvent> eventHandlerPl1 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY) {
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
                        if (player1Board.placeShip(new Ship(list_of_ships1.get(0), shipIsVertical), cell.get_x(), cell.get_y())) {

                            try {
                                list_of_ships1.remove(0);
                            } catch (IndexOutOfBoundsException e) {
                            }
                            if(list_of_ships1.isEmpty()){
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
                    }
                } else {
                    if (player2ShotNow) {
                        if (player1Board.isEnableShot()) {

                            if (cell.get_isWasShot()) {
                                return;
                            } else {
                                cell.shoot();
                                if(player1Board.endGame()){
                                    winner("Player 2 is the winner");
                                    //dbConnection db = new dbConnection();
    //                                try {
    //                                    db.setGame(myJson.strigifyMy(player1Board),myJson.strigifyMy(player1Board) ); /// linijka do zapisu gry po zakończeniu
    //                                } catch (JsonProcessingException e) {
    //                                    e.printStackTrace();
    //                                }
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
            }else {
                shipIsVertical = !shipIsVertical;
                try {
                    Cell cell = (Cell) event.getSource();
                    player1Board.render();
                    player1Board.placeShipView(new Ship(list_of_ships1.get(0), shipIsVertical), cell.get_x(), cell.get_y());

                } catch (Exception e) {
                }
            }


        }
    };


    EventHandler<MouseEvent> eventHandlerPl2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Cell cell = (Cell) event.getSource();
            if(event.getButton() != MouseButton.PRIMARY){
                shipIsVertical = !shipIsVertical;
                try {
                    player2Board.render();
                    player2Board.placeShipView(new Ship(list_of_ships2.get(0), shipIsVertical), cell.get_x(), cell.get_y());

                } catch (Exception e) {
                }
            }else{
                if(list_of_ships2.isEmpty()){
                    player2AllShipSet = true;
                    player2ViewController.setStartGameButtonEnable();
                }
                if (!player2AllShipSet) {
                    if(!list_of_ships2.isEmpty()) {
                        if (player2Board.placeShip(new Ship(list_of_ships2.get(0), shipIsVertical), cell.get_x(), cell.get_y())) {
                            try {
                                list_of_ships2.remove(0);
                            } catch (IndexOutOfBoundsException e) {
                            }
                            if(list_of_ships2.isEmpty()){
                                player2AllShipSet = true;
                                player2ViewController.setStartGameButtonEnable();
                            }
                        }
                    }
                    else {
                        System.out.println("Juz postawiono wszystkie statki");
                        try {
                            player2ViewController.setStartGameButtonEnable();
                        } catch (Exception e) {
                        }
                        player2AllShipSet = true;
                    }
                }else {
                    if (player1ShotNow) {
                        if (player2Board.isEnableShot()) {
                            if (cell.get_isWasShot()) {
                                return;
                            } else {
                                cell.shoot();
                                if(player2Board.endGame()){
                                  winner("Player 1 is the winner");
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
        }
    };
    EventHandler<MouseEvent> eventHandlerAi = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Cell cell = (Cell) event.getSource();
                if (player1ShotNow && !aiSootWithPlayer1Now) {
                    if (ai1Board.isEnableShot()) {
                        if (cell.get_isWasShot()) {
                            return;
                        } else {
                            cell.shoot();
                            if(ai1Board.endGame()){
                               winner("Player 1 is the winner");
                            }
                            if (cell.getShip() == null) {
                                player1ShotNow = false;
                               // strzelanie bota
                                player1BattleViewController.playerNumberLabel.setText("Strzela Komputer");
                                aiSootWithPlayer1Now = true;
                                aiShootInPlayer1();
                                if(ai1Board.endGame()){
                                    winner("Player 1 is the winner");
                                }
                            }
                        }

                    }
                } else {
                }

            }

    };
    EventHandler<MouseEvent> eventHandlerShipViewEnteredPl1 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Cell cell = (Cell) event.getSource();
                player1Board.placeShipView(new Ship(list_of_ships1.get(0), shipIsVertical), cell.get_x(), cell.get_y());
            } catch (Exception e) {
            }
        }

    };
    EventHandler<MouseEvent> eventHandlerShipViewEnteredPl2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Cell cell = (Cell) event.getSource();
                player2Board.placeShipView(new Ship(list_of_ships2.get(0), shipIsVertical), cell.get_x(), cell.get_y());
            } catch (Exception e) {
            }
        }

    };
    EventHandler<MouseEvent> eventHandlerShipViewExit = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Cell cell = (Cell) event.getSource();
                cell.getBoardController().render();
            } catch (Exception e) {
            }
        }
    };




























    public void winner(String string){ // 1 - winner PL1 , 2 - winner PL2 , 3 - winner AI1 , 4 - winner AI2 ,

        System.out.println(string);
      //  hideBoardPl1(player1BattleViewController.nextButton.getScene());
      //  hideBoardPl2(player1BattleViewController.nextButton.getScene());
        player1BattleViewController.playerNumberLabel.setText(string);
        player1BattleViewController.playerNumberLabel.setTextFill(Color.RED);
        player1BattleViewController.setContinueButtonEnable();
    }

public void aiShootInPlayer1(){
    long time = System.nanoTime();
    AnimationTimer timerAi = new AnimationTimer() {
        @Override
        public void handle(long l) {
            // zmienić 1 na 1_000_000_000
            if(l - timeOfAiCahnge > time){
                ai = new AI(ai1Level, player1Board);
                player1Board = ai.moveAI();
                if(player1Board.endGame()){
                  winner("AI is the winner");
                    super.stop();
                    return;
                }
                hideBoardPl1(player1BattleViewController.nextButton.getScene());
                insertBoardShip(player1BattleViewController.nextButton.getScene(),player1Board);
                player1BattleViewController.playerNumberLabel.setText("Tura Gracza Nr 1");
                super.stop();
                if(player1Board.isPreviousShotTelling()){
                    aiShootInPlayer1();
                }else {
                    aiSootWithPlayer1Now = false;
                }
            }
        }
    };
    timerAi.start();
}
    public void ai2ShootInAi1(){

        long time = System.nanoTime();
        AnimationTimer changeView2 = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long l) {
                if(l - timeOfAiCahnge > time){
                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShip(player1BattleViewController.nextButton.getScene(),ai1Board);
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai2Board);
                    super.stop();
                    aiVsAiStartGame();
                }
            }
        };

        AnimationTimer timerAi = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // zmienić 1 na 1_000_000_000
                if(l - (timeOfAiCahnge/2) > time){
                    ai = new AI(ai2Level, ai1Board);
                    ai1Board = ai.moveAI();
                    if(ai1Board.endGame()){
                        winner("AI 2 is the winner");
                        super.stop();
                        return;
                    }
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai1Board);

                    super.stop();

                    if(ai1Board.isPreviousShotTelling()){
                        ai2ShootInAi1();
                    }else{
                        changeView2.start();
                    }
                }
            }
        };
        timerAi.start();

    }



    public void aiVsAiStartGame(){
        //ustawiono staki 1 i strzelanie2


        player1BattleViewController.playerNumberLabel.setText("Tura AI 1");

        long time = System.nanoTime();

        AnimationTimer changeView2 = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long l) {
                if(l - (timeOfAiCahnge*2) > time){
                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShip(player1BattleViewController.nextButton.getScene(),ai1Board);
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai2Board);
                    super.stop();
                    aiVsAiStartGame();
                }
            }
        };

        AnimationTimer timerAi2Shoot = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l - (timeOfAiCahnge+timeOfAiCahnge/2) > time){
                    ai = new AI(ai2Level, ai1Board);
                    ai1Board = ai.moveAI();
                    if (ai1Board.isPreviousShotTelling()){
                      super.stop();
                      ai2ShootInAi1();
                      return;
                    }
                    if(ai1Board.endGame()){
                        winner("AI 2 is the winner");
                        super.stop();
                        return;
                    }
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai1Board);
                    super.stop();
                    changeView2.start();

                }
            }
        };
        AnimationTimer changeView = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long l) {
                if(l - timeOfAiCahnge > time){
                    player1BattleViewController.playerNumberLabel.setText("Tura AI 2");
                    hideBoardPl1(player1BattleViewController.nextButton.getScene());
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShip(player1BattleViewController.nextButton.getScene(), ai2Board);
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai1Board);
                    super.stop();
                    timerAi2Shoot.start();

                }
            }
        };


        AnimationTimer timerAi1Shoot = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(l - (timeOfAiCahnge/2) > time){
                    ai = new AI(ai1Level, ai2Board);
                    ai2Board = ai.moveAI();
                    if(ai2Board.isPreviousShotTelling()) {
                        super.stop();
                        aiVsAiStartGame();
                        return;
                    }

                    if(ai2Board.endGame()){
                        winner("AI 1 is the winner");
                        super.stop();
                        DbConnection dbConnection = new DbConnection();
                        ai2Board.makeChangesShootToDB();
                        ai1Board.makeChangesShootToDB();
                        System.out.println(dbConnection.setGame(ai1Board.getInitilaState(ai1Board), ai2Board.getInitilaState(ai2Board), ai1Board.getChangesToDB(), ai2Board.getChangesToDB(), 1,2,1));


                        return;
                    }
                    hideBoardPl2(player1BattleViewController.nextButton.getScene());
                    insertBoardShoot(player1BattleViewController.nextButton.getScene(),ai2Board);
                    super.stop();
                    changeView.start();

                }
            }
        };
            timerAi1Shoot.start();
    }



public BoardController createBoardController(boolean isAi, List<Integer> listOfShips, EventHandler<? super MouseEvent> handler ){
    makeListOfShips(listOfShips);
    return new BoardController(isAi, handler);
    }

    public BoardController createBoardController(boolean isAi, List<Integer> listOfShips, EventHandler<? super MouseEvent> handler,EventHandler<? super MouseEvent> handlerSetShipEntered, EventHandler<? super MouseEvent> handlerSetShipExit){
        makeListOfShips(listOfShips);
        return new BoardController(isAi, handler,handlerSetShipEntered,handlerSetShipExit);
    }

    public void makeListOfShips(List<Integer> listOfShips){
        listOfShips.add(4);
        listOfShips.add(3);
        listOfShips.add(3);
        listOfShips.add(2);
        listOfShips.add(2);
        listOfShips.add(2);
        listOfShips.add(1);
        listOfShips.add(1);
        listOfShips.add(1);
        listOfShips.add(1);
    }


    public void randomPositionShipAi(List<Integer> list_of_ships, BoardController boardController){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships.size();
        if(type == 0){
            boardController.clearShip();
            makeListOfShips(list_of_ships);
            type = list_of_ships.size();
        }
        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (boardController.placeShip(new Ship(list_of_ships.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                list_of_ships.remove(0);
            }
        }

    }
}
