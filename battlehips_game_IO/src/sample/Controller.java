package sample;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import controller.Player1BattleViewController;
import controller.Player1ViewController;
import controller.Player2ViewController;
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
    public BoardController player2Board, player1Board;
    List<Integer> list_of_ships1 = new ArrayList<Integer>();
    List<Integer> list_of_ships2 = new ArrayList<Integer>();

    public Controller controller;

    public Player1ViewController player1ViewController;
    public Player2ViewController player2ViewController;
    private Player1BattleViewController player1BattleViewController;


    private boolean player2AllShipSet = false , player1AllShipSet = false;

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
    public void createBoard1(){
        this.player1Board = createContentPl1();

    }
    public void createBoard2(){
        this.player2Board = createContentPl2();
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









    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            player1ViewController.getSwitchToP2Button().setDisable(true);
            System.out.println("1");
            Cell cell = (Cell) event.getSource();
            if(!list_of_ships1.isEmpty()) {
                if (player1Board.placeShip(new Ship(list_of_ships1.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                    try {
                        System.out.println(list_of_ships1.get(0));
                        list_of_ships1.remove(0);
                        System.out.println("2");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Juz postawiono wszystkie statki");
                    }
                }

            }
            else{
                System.out.println("Juz postawiono wszystkie statki");
                player1ViewController.getSwitchToP2Button().setDisable(false);
                end_s = true;
                System.out.println("3");

            }




        }
    };


    EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            Cell cell = (Cell) event.getSource();
            if(list_of_ships2.isEmpty()){
                player2AllShipSet = true;
            }
            if (!player2AllShipSet) {

                if(!list_of_ships2.isEmpty()) {
                    if (player2Board.placeShip(new Ship(list_of_ships2.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                        try {
                            System.out.println(list_of_ships2.get(0));
                            list_of_ships2.remove(0);

                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Juz postawiono wszystkie statki");
                        }
                    }
                }
                else{
                    System.out.println("Juz postawiono wszystkie statki");
                    player2ViewController.setStartGameButtonEnable();
                    player2AllShipSet = true;
                    end_s = true;
                }
            }else {

                if (player2Board.isAI()) {

                    if (cell.get_isWasShot()) {
                        return;
                    } else {
                        cell.shoot();
//                        ODWRACANIE WIDOKU








                    }

                }
            }
        }
    };




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
        return new BoardController(false, eventHandler);
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

        return new BoardController(false, eventHandler2);
    }

    public void randomPositionShipPlayer1(Scene scene){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships1.size();
        System.out.println(scene);
        if(type == 0){
            createBoard1();
            type = list_of_ships1.size();
            insertBoardPl1(scene);
        }
        System.out.println(type);
        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (player1Board.placeShip(new Ship(list_of_ships1.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                System.out.println("xd");
                System.out.println(type);
                list_of_ships1.remove(0);
            }
        }
        player1ViewController.setSwitchToP2ButtonEnable();

    }
    public void randomPositionShipPlayer2(Scene scene){        //randowmowe ustawianie staktów dla player 1
        int type = list_of_ships2.size();
        System.out.println(scene);
        if(type == 0){
            createBoard2();
            type = list_of_ships2.size();
            insertBoardPl2(scene);
        }
        System.out.println(type);
        while(type > 0) {
            int x = this.random.nextInt(10);
            int y = this.random.nextInt(10);

            if (player2Board.placeShip(new Ship(list_of_ships2.get(0), Math.random() < 0.5D), x, y)) {
                --type;
                System.out.println("xd");
                System.out.println(type);
                list_of_ships2.remove(0);
            }
        }
        player2ViewController.setStartGameEnable();

    }

}
