package controller;

import db.DbConnection;
import db.game_Classes.GameDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import bs_game_backend.User;

import java.io.IOException;
import java.util.ArrayList;

public class StaticticProfileController {
    @FXML
    public Button exitButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public TableView statisticTableView;
    @FXML
    public TableColumn<User,String> avatarTableColumn;
    @FXML
    public TableColumn<User,String> UsarnameTableColumn;
    @FXML
    public TableColumn<User,String> winsTablecolumn;
    @FXML
    public TableColumn<User,String> battlesTablecolumn;
    @FXML
    public TableColumn<User,String> ratioTablecolumn;
    @FXML
    public Button modyfiProfileButton;
    @FXML
    public Label errorLabel;
    @FXML
    public Button addPlayer;
    @FXML
    public MenuItem deleteMenuItem;

    private  User selectedUser;


    private DbConnection dbConnection;
    ArrayList<User> userArrayList;
    public void initialize(){
        dbConnection = new DbConnection();

        userArrayList = dbConnection.getAllUser_listToGameStatistic();

        avatarTableColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        UsarnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsTablecolumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        battlesTablecolumn.setCellValueFactory(new PropertyValueFactory<>("battles"));
        ratioTablecolumn.setCellValueFactory(new PropertyValueFactory<>("aimRatio"));

        for (User u :
                userArrayList) {
            statisticTableView.getItems().add(u);
        }
        modyfiProfileButton.setDisable(true);

    }

    public void modyfiProfileAction() throws IOException {
        if(selectedUser.getId() < 4){
            errorLabel.setText("You can't modify this AI");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/editProfileScreen.fxml"));
        Pane pane =(Pane) fxmlLoader.load();
        EditProfileController editProfileController = (EditProfileController)  fxmlLoader.getController();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)modyfiProfileButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Modify profile");
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
        editProfileController.setSelectedUser(selectedUser);
        editProfileController.setAvatarView();
    }

    public void selectProfileOnMouseClicked(){
        try {
            selectedUser = (User) statisticTableView.getSelectionModel().getSelectedItem();
            modyfiProfileButton.setDisable(false);
            if(selectedUser.getId() < 4){
                modyfiProfileButton.setDisable(true);
                return;
            }
        } catch (Exception e) {
        }
    }

public void addPlayerAction() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/newPlayerScreen.fxml"));
    Pane pane =(Pane) fxmlLoader.load();
    NewPlayerController newPlayerController = (NewPlayerController)  fxmlLoader.getController();
    Scene scene = new Scene(pane);
    Stage stage = (Stage) ((Node)modyfiProfileButton).getScene().getWindow();
    stage.setResizable(false);
    stage.setScene(scene);
    String css = this.getClass().getResource("/css/application.css").toExternalForm();
    scene.getStylesheets().add(css);
    stage.setTitle("New Player");
    stage.show();
}
    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("MENU");
        stage.show();

    }
    public void deleteAction() throws IOException {
        User userToDelete = (User) statisticTableView.getSelectionModel().getSelectedItem();
        dbConnection.deleteUser(userToDelete.getId());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/statisticProfileScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)backToMenuButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Statistics/Profile");
        stage.show();
    }
    public void menuContexMenuAction(){
        User userToDelete = (User) statisticTableView.getSelectionModel().getSelectedItem();
        ArrayList<GameDB> gameDBS = dbConnection.getGamesArray(userToDelete.getId());
        if(userToDelete.getId() < 4){
            deleteMenuItem.setVisible(false);
            return;
        }else {
            deleteMenuItem.setVisible(true);
        }
        if(gameDBS.size() != 0){
            deleteMenuItem.setVisible(false);
            return;
        }else {
            deleteMenuItem.setVisible(true);
        }
    }
}
