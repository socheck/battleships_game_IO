package controller;

import db.DbConnection;
import db.game_Classes.Changes;
import db.game_Classes.GameDB;
import db.game_Classes.InitialState;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

class GameToComboBox{
    private int id;
    private int player1ID;
    private int player2ID;
    private int winnerID;
    private String player1Username;
    private String player2Username;
    private String playerWinnerUsername;


    public GameToComboBox(int id, int player1ID, int player2ID, int winnerID) {
        DbConnection dbConnection = new DbConnection();
        this.id = id;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
        User user = dbConnection.getUser(player1ID);
        player1Username = user.getUsername();
        user = dbConnection.getUser(player2ID);
        player2Username = user.getUsername();
        user = dbConnection.getUser(winnerID);
        playerWinnerUsername = user.getUsername();
    }

    @Override
    public String toString() {
        return
                "P1- " + player1Username +
                " P2 " + player2Username +
                " winner " + playerWinnerUsername ;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(int player1ID) {
        this.player1ID = player1ID;
    }

    public int getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(int player2ID) {
        this.player2ID = player2ID;
    }

    public int getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(int winnerID) {
        this.winnerID = winnerID;
    }
}

public class ReplaysMenuController {
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button replaysButton;
    @FXML
    public ComboBox playerComboBox;
    @FXML
    public ComboBox replaysComboBox;
    @FXML
    public ImageView plyerImageView;
    @FXML
    public ImageView enemyImageView;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label winsaLabel;
    @FXML
    public Label batlesLabel;
    @FXML
    public Label enemyUsernameLabel;
    @FXML
    public Label enemyWinsaLabel;
    @FXML
    public Label enemyBatlesLabel;
    @FXML
    public Label winnerLabel;
    private DbConnection dbConnection;
    private User selectedPlayer;
    private GameToComboBox gameDB;
    private User player1, player2, playerShow;


    ArrayList<User> userArrayList;
    private ObservableList<User> playerObservableList;
    private ListProperty<User> listPropertyPlayer;

    ArrayList<GameToComboBox> gameArrayList;
    private ObservableList<GameToComboBox> gameObservableList;
    private ListProperty<GameToComboBox> listPropertyGame;


    public void initialize(){
        dbConnection = new DbConnection();
        userArrayList = dbConnection.getAllUser_list();
        listPropertyPlayer = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy
        playerObservableList = FXCollections.observableArrayList(userArrayList);
        listPropertyPlayer.set(playerObservableList);
        playerComboBox.itemsProperty().bindBidirectional(listPropertyPlayer);

        replaysButton.setDisable(true);
        replaysComboBox.setDisable(true);
    }







    public void backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("MENU");
        stage.show();
    }

    public void replaysAction(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/battleReplaysScreen.fxml"));
        Parent pane = null;
        try {
            pane = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BattleReplaysController battleReplaysController = (BattleReplaysController) fxmlLoader.getController();
        Stage stage = (Stage) ((Node)replaysButton).getScene().getWindow();
        stage.setTitle("Replays");

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        battleReplaysController.setToReplays(player1,player2,new GameDB(gameDB.getId(), gameDB.getPlayer1ID(), gameDB.getPlayer2ID(), gameDB.getWinnerID()));
    }

    public void playerComboBoxAction(){

        replaysComboBox.setPromptText("");
        replaysButton.setDisable(true);
        selectedPlayer = (User) playerComboBox.getSelectionModel().getSelectedItem();
        usernameLabel.setText(selectedPlayer.getUsername());
        winsaLabel.setText(String.valueOf(selectedPlayer.getWins()));
        batlesLabel.setText(String.valueOf(selectedPlayer.getBattles()));
        Image photo = new Image(selectedPlayer.getAvatar_path());
        System.out.println("dupa2");
        plyerImageView.setImage(photo);
        System.out.println("dupa2.2.2");
        setReplaceInComboBox();
        System.out.println("dupa22");
        enemyUsernameLabel.setText("");
        enemyWinsaLabel.setText("");
        enemyBatlesLabel.setText("");
        winnerLabel.setText("");
        enemyImageView.setImage(null);


    }

    public void replaysComboBoxAction(){
        System.out.println("dupa3");

        gameDB = (GameToComboBox) replaysComboBox.getSelectionModel().getSelectedItem();
        if(gameDB == null){
            return;
        }
        player1 = dbConnection.getUser(gameDB.getPlayer1ID());
        player2 = dbConnection.getUser(gameDB.getPlayer2ID());
        playerShow = player2;
        if(player2.getId() == selectedPlayer.getId()){
//            User tmp = player2;
//            player2 = player1;
//            player1 = tmp;
            playerShow = player1;
        }
        enemyUsernameLabel.setText(playerShow.getUsername());
        enemyWinsaLabel.setText(String.valueOf(playerShow.getWins()));
        enemyBatlesLabel.setText(String.valueOf(playerShow.getBattles()));
        Image photo = new Image(playerShow.getAvatar_path());
        enemyImageView.setImage(photo);

        if(gameDB.getWinnerID() == player1.getId()){
            winnerLabel.setText(player1.getUsername());
        }else {
            winnerLabel.setText(player2.getUsername());
        }
        replaysButton.setDisable(false);
    }
    public void setReplaceInComboBox(){
        gameArrayList = new ArrayList<>();
        replaysComboBox.setDisable(false);
        ArrayList<GameDB> gameDBS = dbConnection.getGamesArray(selectedPlayer.getId());

        for (GameDB game:
             gameDBS) {
            gameArrayList.add(new GameToComboBox(game.getId(), game.getPlayer1ID(), game.getPlayer2ID(), game.getWinnerID()));
        }
        replaysComboBox.setItems(null);
        listPropertyGame = new SimpleListProperty<>(); //OBSlUGA Comboboxa z listy
        gameObservableList = FXCollections.observableArrayList(gameArrayList);
        listPropertyGame.set(gameObservableList);
        replaysComboBox.itemsProperty().bindBidirectional(listPropertyGame);

    }

}
