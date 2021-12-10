package db;

import savingClasses.InitialState;

import java.io.IOException;
import java.sql.*;

public class dbConnection {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD ="dbpassword";
    private static final String CONN ="jdbc:mysql://localhost/login";
    private static final String SQCONN = "jdbc:sqlite:src\\battleship_db.db";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        }catch (ClassNotFoundException ex){
            System.err.println("Problem z otwarciem polaczenia");
            ex.printStackTrace();
        }
        return null;
    }

//    public Connection getPolaczenie() throws SQLException{
//        try {
//            Class.forName("org.sqlite.JDBC");
//            return DriverManager.getConnection(SQCONN);
//
//        }catch (ClassNotFoundException ex){
//            System.err.println("Problem z otwarciem polaczenia");
//            ex.printStackTrace();
//        }
//        return null;
//    }

    public void getUser_list(){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        String komendaSQL = "SELECT * FROM users_list;";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            resultSet = preparedStatement.executeQuery();
            System.out.println("result główny"+resultSet);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") +  "\t" +
                        resultSet.getString("username") + "\t" +
                        resultSet.getString("password") + "\t" +
                        resultSet.getString("avatar_path") + "\t" +
                        resultSet.getInt("wins") + "\t" +
                        resultSet.getInt("battles") + "\t" +
                        resultSet.getDouble("aim_ratio"));
            }

        } catch (SQLException throwables) {
            System.out.println("szukam");
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public InitialState getInitialState(int initialStateID){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        String komendaSQL = "SELECT initial_state FROM games WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setInt(1, initialStateID);
            resultSet = preparedStatement.executeQuery();
            System.out.println("result główny"+resultSet);
            while (resultSet.next()){
                System.out.println(resultSet.getString("initial_state"));
//                System.out.println(resultSet.getInt("id") +  "\t" +
//                        resultSet.getString("username") + "\t" +
//                        resultSet.getString("password") + "\t" +
//                        resultSet.getString("avatar_path") + "\t" +
//                        resultSet.getInt("wins") + "\t" +
//                        resultSet.getInt("battles") + "\t" +
//                        resultSet.getDouble("aim_ratio"));
                return  myJson.fromJsonMy(resultSet.getString("initial_state"), InitialState.class);
            }

        } catch (SQLException | IOException throwables) {
            System.out.println("szukam exeption");
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    public void setGame(String initialState, String changes){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        String komendaSQL = "INSERT INTO games VALUES(NULL,?,?,0, 0, 0, NULL);";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setString(1, initialState);
            preparedStatement.setString(2, changes);

            preparedStatement.execute();
            System.out.println("setGae insert");


        } catch (SQLException throwables) {
            System.out.println("szukam exeption");
            throwables.printStackTrace();
        }

    }




}