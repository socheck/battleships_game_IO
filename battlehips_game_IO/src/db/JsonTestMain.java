package db;

import bs_game_backend.Cell;
import bs_game_backend.Ship;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.sql.*;

import przykladoweKlasy.*;
import savingClasses.InitialState;

import java.io.IOException;

public class JsonTestMain {
    public  static void main(String[] arg) {
        String jsonSource = "{\n" +
                "  \"title\": \"to jest mój tytuł\"\n" +
                "}";
        try {
            JsonNode node = MyJson.parse(jsonSource);
//            System.out.println(node.get("title").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            System.out.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (connection == null) {
            System.exit(1);
        }
        DbConnection db = new DbConnection();
        db.getUser_list();

//        ===========================================odczyt --> konstruktory trzeba zakomentować===================================
        InitialState initaiState = db.getInitialState(3);

        System.out.println(initaiState);
        try {
            System.out.println(MyJson.prettyPrintMy(initaiState));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        System.out.println(initaiState.getInitial_state().get(0).getShip4().get(3));

        System.out.println("===============================================");
        System.out.println();

//        ===========================================wpisywanie --> potrzebne konstruktowy===================================
//        List<String> s1 = Arrays.asList("01", "02");
//        List<String> s2 = Arrays.asList("03", "04");
//        List<String> s3 = Arrays.asList("05", "06");
//
//        List<String> s4 = Arrays.asList("91", "92");
//        List<String> s5 = Arrays.asList("93", "94");
//        List<String> s6 = Arrays.asList("95", "96");
//        List<PlayerPlacing> p1 = Arrays.asList(new PlayerPlacing(2, s1, s2, s3), new PlayerPlacing(2, s3, s4, s5));
//
//
//        InitialState myInitialState = new InitialState(2,1, p1);
//
//        System.out.println("obiekt" + myInitialState);
//
//        System.out.println("===============================================");
//
//        try {
//            db.setGame(myJson.strigifyMy(myInitialState), "{ jakaś gra }");
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


//    }
//        public boolean isDatabeseConnected(){
//
//            return connection !=null;
//        }


//    TEST ZDOLNOŚCI METOD ZAMIENIAJĄCYCH KLASY NA STRINGI I NA ODWRÓT
//    BoardController //NIE PRZEJDZIE BO MA MouseEvent
//    Cell cellTest = new Cell(); //NIE PRZEJDZIE BO BOARD, extend Rectangle i pewnie cos jeszcze
        TestShip shipT = new TestShip(4, true);//to już może
        //typ to jest ilość pól, czyli długość statku
        TestCell cellT = new TestCell(1, 1, shipT, true);//to już tak

        Test t = new Test(shipT, 5);//to już może
        Test2 t2 = new Test2(shipT, 8, "jakiś tekst");//to już tak --> można dziedziczyć z klas POJO

        TestBoardController boardT = new TestBoardController(true);//to już może
        try {
            System.out.println(MyJson.strigifyMy(t2));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
