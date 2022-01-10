package sample;

import db.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        stage.setTitle("MENU");
        stage.setResizable(false);
        stage.setScene(new Scene(pane));
        stage.show();
//        DbConnection dbConnection = new DbConnection();
//        ArrayList<User> a = dbConnection.getUser_list();
//        System.out.println("=============================================================== a");
//        System.out.println(a);
//        User testUser = a.get(0);
//        //set user
////        System.out.println("set user");
////        System.out.println(dbConnection.setUser("1test", testUser.getPassword(), testUser.getAvatar_path()));
//        //update password
//        System.out.println("ubdate password");
//        System.out.println("old: "+ testUser.getPassword());
//        System.out.println(dbConnection.updateUser_password("456", testUser.getId()));
//        System.out.println("new: "+ testUser.getPassword());
//        System.out.println("ubdate avatar");
//        System.out.println("old: "+ testUser.getAvatar_path());
//        System.out.println(dbConnection.updateUser_avatar("test_avatar_path", testUser.getId()));
//        System.out.println("new: "+ testUser.getAvatar_path());
//        System.out.println("delete user");
//        System.out.println(dbConnection.deleteUser(4));
//        System.out.println("ubdate statystyki");
//        System.out.println(dbConnection.updateUser_statistics(5, 3, testUser.getId()));
//        System.out.println("wypisywanie gier danego użytkownika");
//        System.out.println(dbConnection.getGamesArray(2));
//        System.out.println("wypisywanie konkretnej gry");
//        System.out.println(dbConnection.getSpecyficGame(7));







    }


    public static void main(String[] args) {
        launch(args);
    }
}
