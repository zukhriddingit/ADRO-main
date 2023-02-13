package com.example.adro;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBaseConnect {
    private static String HOST = "127.0.0.1";
    private static int PORT = 3306;
    private static String DB_NAME = "adro";
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    private static Connection connection;

    public static Connection getConnect (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  connection;
    }

    public static void insertData(String sql) throws SQLException {
        Statement statement = getConnect().createStatement();
        if (statement.executeUpdate(sql)>0){
            System.out.println("Successfully added!");
        }else{
            System.out.println("Something went wrong!!!");
        }
    }

    public static boolean getInfo(String username) throws SQLException {
        PreparedStatement preparedStatement = getConnect().prepareStatement("select * from `register` where username = ?");
        preparedStatement.setString(1,username);
        ResultSet r1 = preparedStatement.executeQuery();
        if (r1.next()){
            return true;
        }
        else return false;
    }

    public static boolean checkPassword(String username,String password, String tableName) throws SQLException {
        PreparedStatement preparedStatement = getConnect().prepareStatement("SELECT password FROM `"+tableName+"` WHERE BINARY username=? AND BINARY  password=?;");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet r = preparedStatement.executeQuery();
        if (r.next()){
            return true;
        }
        return false;
    }

    public static AdminMovie dashMovie(int id) throws SQLException {
        PreparedStatement ps=getConnect().prepareStatement("SELECT * FROM `movies` WHERE id="+id);
        ResultSet r= ps.executeQuery();
        AdminMovie adminMovie = new AdminMovie();
        if (r.next()){
            adminMovie.setDescription(r.getString("description"));
            adminMovie.setDuration(r.getInt("duration"));
            adminMovie.setGenre(r.getString("genre"));
            adminMovie.setLanguage(r.getString("language"));
            adminMovie.setPrice(r.getInt("price"));
            adminMovie.setSession(r.getString("session"));
            adminMovie.setTitle(r.getString("title"));
            adminMovie.setNumberTickets(r.getInt("number_tickets"));
            adminMovie.setStartDate(r.getDate("start_date"));
            adminMovie.setEndDate(r.getDate("end_date"));
        }
        return adminMovie;
    }

    public ResultSet getMovies(String sql) throws SQLException {
        PreparedStatement ps = getConnect().prepareStatement(sql);
        return ps.executeQuery();
    }

    public static void main(String[] args) throws SQLException {
        dashMovie(1);
    }
}
