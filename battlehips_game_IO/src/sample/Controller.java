package sample;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
    private Button napieprzajbutton;

    @FXML
    private Button login_pl_1;
    @FXML
    private Button login_pl_2;

    @FXML
    private Button switchToP2Button;

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

    void insertBoardPl1(){
        Pane pane = (Pane) (boarderpl1_fxml.getScene().lookup("#pl1Pane #boarderpl1"));
        this.player1Board = createContentPl1();
        pane.getChildren().add(player1Board);
    }

    void insertBoardPl2(){
        Pane pane = (Pane) (boarderpl2_fxml.getScene().lookup("#pl2Pane #boarderpl2"));
        this.player2Board = createContentPl2();
        pane.getChildren().add(player2Board);
    }




    private BoardController player2Board, player1Board;
    List<Integer> list_of_ships = new ArrayList<Integer>();



    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            boolean end_s = false;
            switchToP2Button.setDisable(true);

            Cell cell = (Cell) event.getSource();
            if(!list_of_ships.isEmpty()) {
                if (player1Board.placeShip(new Ship(list_of_ships.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                    try {
                        System.out.println(list_of_ships.get(0));
                        list_of_ships.remove(0);

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
            if(!list_of_ships.isEmpty()) {
                if (player2Board.placeShip(new Ship(list_of_ships.get(0), event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                    try {
                        System.out.println(list_of_ships.get(0));
                        list_of_ships.remove(0);

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


    private BoardController createContentPl1() {
        list_of_ships.add(4);
        list_of_ships.add(3);
        list_of_ships.add(3);
        list_of_ships.add(2);
        list_of_ships.add(2);
        list_of_ships.add(2);
        list_of_ships.add(1);
        list_of_ships.add(1);
        list_of_ships.add(1);
        list_of_ships.add(1);

        return new BoardController(false, eventHandler);
    }


    private BoardController createContentPl2() {
        list_of_ships.add(4);
        list_of_ships.add(3);
        list_of_ships.add(3);
        list_of_ships.add(2);
        list_of_ships.add(2);
        list_of_ships.add(2);
        list_of_ships.add(1);
        list_of_ships.add(1);
        list_of_ships.add(1);
        list_of_ships.add(1);

        return new BoardController(false, eventHandler2);
    }

}
