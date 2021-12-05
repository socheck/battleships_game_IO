package sample;
import java.util.Random;


import bs_game_backend.Cell;
import bs_game_backend.Ship;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private boolean running = false;
    private BoardController enemyBoardController, playerBoardController;

    private int shipsToPlace = 10;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

        enemyBoardController = new BoardController(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.get_isWasShot())
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoardController.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoardController = new BoardController(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoardController.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.get_x(), cell.get_y())) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        VBox vbox = new VBox(50, enemyBoardController, playerBoardController);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoardController.getCell(x, y);
            if (cell.get_isWasShot())
                continue;

            enemyTurn = cell.shoot();

            if (playerBoardController.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoardController.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("startView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(scene));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
