package sample;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
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

    private BoardController player2Board, player1Board;
    List<Integer> list_of_ships1 = new ArrayList<Integer>();
    List<Integer> list_of_ships2 = new ArrayList<Integer>();

    public Controller controller;


    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    void changetologin(ActionEvent event) throws IOException {

    }

    @FXML
    void changetopl1(ActionEvent event) throws IOException {

    }

    @FXML
    void changetopl2(ActionEvent event) throws IOException {

    }

    @FXML
    void SignInP2(ActionEvent event) throws IOException {

    }

    @FXML
    void BufforToP1View(ActionEvent event) throws IOException {

    }
    @FXML
    void player1BattleView(ActionEvent event) throws IOException {

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
        System.out.println("1");
        System.out.println(player1Board);

    }
    void createBoard2(){
        this.player2Board = createContentPl2();
    }

    public void insertBoardPl1(Scene scene){
        Pane pane = (Pane) (scene.lookup("#pl1Pane #boarderpl1"));
        System.out.println(pane);
        System.out.println("2");
        System.out.println(player1Board);
        pane.getChildren().add(player1Board);
    }

    void insertBoardPl2(){
        Pane pane = (Pane) (boarderpl2_fxml.getScene().lookup("#pl2Pane #boarderpl2"));
        player2Board = createContentPl2();
        pane.getChildren().add(player2Board);
    }

    public void insertBoardPl1Ready(Scene scene){
        Pane pane = (Pane) (scene.lookup("#mainPaneP1Battle #myBoardP1Battle"));
        pane.getChildren().add(player1Board);
    }









    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
//            switchToP2Button.setDisable(true);

            Cell cell = (Cell) event.getSource();
            if(!list_of_ships1.isEmpty()) {
                if (player1Board.placeShip(new Ship(list_of_ships1.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                    try {
                        System.out.println(list_of_ships1.get(0));
                        list_of_ships1.remove(0);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Juz postawiono wszystkie statki");
                    }
                }

            }
            else{
                System.out.println("Juz postawiono wszystkie statki");
//                switchToP2Button.setDisable(false);
                end_s = true;

            }
        }
    };


    EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            Cell cell = (Cell) event.getSource();
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
                end_s = true;
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



}
