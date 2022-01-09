package db;

import bs_game_backend.Cell;
import com.fasterxml.jackson.core.JsonProcessingException;
import db.game_Classes.Changes;
import sample.CellToDB;
import sample.User;
import db.game_Classes.InitialState;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

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

//  users_list TABLE
    public ArrayList<User> getUser_list(){
        ArrayList<User> userArrayList = new ArrayList<User>();
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
//                System.out.println(resultSet.getInt("id") +  "\t" +
//                        resultSet.getString("username") + "\t" +
//                        resultSet.getString("password") + "\t" +
//                        resultSet.getString("avatar_path") + "\t" +
//                        resultSet.getInt("wins") + "\t" +
//                        resultSet.getInt("battles") + "\t" +
//                        resultSet.getDouble("aim_ratio"));
//                System.out.println("===========================================================");

                userArrayList.add(new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("avatar_path"), resultSet.getInt("wins"), resultSet.getInt("battles"), resultSet.getDouble("aim_ratio")));

            }
            return userArrayList;

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
        return userArrayList;
    }

    public boolean setUser(String username, String password, String avatar_path){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement= null;


        String komendaSQL = "INSERT INTO users_list VALUES (NULL, ?, ?, ?, 0, 0, 0);";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, avatar_path);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            System.out.println("Dodawanie Usera nie powiodło się");
            throwables.printStackTrace();
            return false;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean updateUser_password(String password, int id){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStatement= null;

        String komendaSQL = "UPDATE users_list SET password = ? WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Update hasła nie powiódł się");
            throwables.printStackTrace();
            return false;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean updateUser_avatar(String avatar_path, int id){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStatement= null;

        String komendaSQL = "UPDATE users_list SET avatar_path = ? WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setString(1, avatar_path);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Update avatar_path nie powiódł się");
            throwables.printStackTrace();
            return false;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean deleteUser(int id){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement preparedStatement= null;

        String komendaSQL = "DELETE FROM users_list WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Usuwanie Usera nie powiodło się");
            throwables.printStackTrace();
            return false;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

//  games TABLE

    public boolean setGame(ArrayList<CellToDB> p1initial, ArrayList<CellToDB> p2initial, ArrayList<CellToDB> p1changes, ArrayList<CellToDB> p2changes, int p1, int p2, int winner){
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement= null;


        String komendaSQL = "INSERT INTO games VALUES (NULL, ?, ?, ?, ?, ?, NULL);";

        Changes changesObj = new Changes(p1changes, p2changes);
        InitialState initialStateObj = new InitialState(p1initial, p2initial);

        try {
            preparedStatement = connection.prepareStatement(komendaSQL);
            try {
                preparedStatement.setString(1, MyJson.strigifyMy(initialStateObj));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.setString(2, MyJson.strigifyMy(changesObj));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            preparedStatement.setInt(3, p1);
            preparedStatement.setInt(4, p2);
            preparedStatement.setInt(5, winner);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            System.out.println("Dodawanie Gry nie powiodło się");
            throwables.printStackTrace();
            return false;
        }finally {
            try {
                preparedStatement.close();
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
                return  MyJson.fromJsonMy(resultSet.getString("initial_state"), InitialState.class);
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