package sample;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    private int iter = 0;



    @FXML
    void changetologin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logowanie.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        ((Stage) napieprzajbutton.getScene().getWindow()).close();
    }

    @FXML
    void changetopl1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Controller controller = (Controller) fxmlLoader.getController();
//        controller.createBoard1();
        stage.setScene(new Scene(root2));
        stage.setResizable(true);
        controller.insertBoardPl1();
        stage.show();
        ((Stage) login_pl_1.getScene().getWindow()).close();
    }

    @FXML
    void changetopl2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player2View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Controller controller = (Controller) fxmlLoader.getController();
//        controller.createBoard2();
        stage.setScene(new Scene(root2));
        stage.setResizable(true);
        controller.insertBoardPl2();
        stage.show();
        ((Stage) login_pl_2.getScene().getWindow()).close();
    }

    @FXML
    void SignInP2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignInP2.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        ((Stage) switchToP2Button.getScene().getWindow()).close();
    }

    @FXML
    void BufforToP1View(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BufforToP1View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        try{
            ((Stage) startGameButton.getScene().getWindow()).close();
        }catch (Exception e){

        }
    }
    @FXML
    void player1BattleView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player1BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Controller controller2 = (Controller) fxmlLoader.getController();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        controller2.insertBoardPl1Ready();
        stage.show();
        try{
            ((Stage) goP1Turn.getScene().getWindow()).close();
        }catch (Exception e){

        }
    }

    @FXML
    void player2BattleView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player2BattleView.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BufforToP2View.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
        try{
            ((Stage) napieprzajbutton.getScene().getWindow()).close();
        }catch (Exception e){

        }
    }
    void createBoard1(){
        this.player1Board = createContentPl1();
    }
    void createBoard2(){
        this.player2Board = createContentPl2();
    }

    void insertBoardPl1(){
        Pane pane = (Pane) (boarderpl1_fxml.getScene().lookup("#pl1Pane #boarderpl1"));
        pane.getChildren().add(player1Board);
        System.out.println("xdddddd2");
        System.out.println(player1Board);
    }

    void insertBoardPl2(){
        Pane pane = (Pane) (boarderpl2_fxml.getScene().lookup("#pl2Pane #boarderpl2"));
        pane.getChildren().add(player2Board);
    }

    void insertBoardPl1Ready(){
        Pane pane = (Pane) (myBoardFXML.getScene().lookup("#mainPaneP1Battle #myBoardP1Battle"));
        System.out.println("xd");
        System.out.println(player1Board);
        pane.getChildren().add(player1Board);
    }









    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            switchToP2Button.setDisable(true);

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
                switchToP2Button.setDisable(false);
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
