package sample;

import db.DbConnection;
import db.game_Classes.GameDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        System.out.println("dupa");
//        File dir = new File("/img/avatars");
//        File[] directoryListing = dir.listFiles();
//        System.out.println(dir.listFiles());
//        if (directoryListing != null) {
//            for (File child : directoryListing) {
//                // Do something with child
//                System.out.println(child);
//            }
//        } else {
//            // Handle the case where dir is not really a directory.
//            // Checking dir.isDirectory() above would not be sufficient
//            // to avoid race conditions with another process that deletes
//            // directories.
//            System.out.println("kurwica");
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        stage.setTitle("MENU");
        stage.setResizable(false);
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);


        stage.show();



//        String filename = "src/test/resources/test_md5.txt";
//        String checksum = "5EB63BBBE01EEED093CB22BB8F5ACDC3";
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(StandardCharsets.UTF_8.encode(filename));
//        System.out.println("2e10e5602b4fa46d734f96f09dd65d21");
//        System.out.println(String.format("%032x", new BigInteger(1, md5.digest())));
//
//


//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(StandardCharsets.UTF_8.encode(filename));
//        String.format("%032x", new BigInteger(1, md5.digest()))
//        DbConnection dbConnection = new DbConnection();
//        ArrayList<User> a = dbConnection.getUser_list();
 //       System.out.println("=============================================================== a");
 //       System.out.println(dbConnection.getSpecyficGame(41));
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
//        GameDB g = dbConnection.getSpecyficGame(9);
//        System.out.println(g);
//
//        System.out.println(g.getInitialStateArray(1));
//        System.out.println(g.getChangesArray(1));
//        System.out.println("wypisywanie gier danego użytkownika");
//        System.out.println(dbConnection.getGamesArray(2));
//        System.out.println("wypisywanie konkretnego użytkownika");
//        System.out.println(dbConnection.getUser(2));






    }


    public static void main(String[] args) {
        launch(args);
    }
}
