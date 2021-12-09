import com.fasterxml.jackson.databind.JsonNode;
import java.sql.*;

import savingClasses.InitialState;

import java.io.IOException;

public class JsonTestMail {
    public  static void main(String[] arg){
        String jsonSource = "{\n" +
                "  \"title\": \"to jest mój tytuł\"\n" +
                "}";
        try {
            JsonNode node = myJson.parse(jsonSource);
//            System.out.println(node.get("title").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection=dbConnection.getConnection();
            System.out.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(connection == null){
            System.exit(1);
        }
        dbConnection db = new dbConnection();
//        db.getUser_list();

        InitialState initaiState = db.getInitialState(1);

        System.out.println(initaiState);

        System.out.println(initaiState.getInitial_state().get(0).getShip4().get(3));

    }
//        public boolean isDatabeseConnected(){
//
//            return connection !=null;
//        }


}
