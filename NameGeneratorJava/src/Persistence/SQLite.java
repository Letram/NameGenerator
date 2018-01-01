package Persistence;

import java.sql.*;
import java.util.ArrayList;

public class SQLite {
    public static Connection connect(){
        String url = "jdbc:sqlite:../names.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't connect to the db.");
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ArrayList<String> getRandomFullName(Connection connection, Statement statement) throws SQLException {
        if(connection == null)return new ArrayList<>();
        ArrayList result= new ArrayList<String>();
        String nameQuery = "SELECT name FROM names ORDER BY RANDOM() LIMIT 1";
        String lastnameQuery = "SELECT lastname FROM lastnames ORDER BY RANDOM() LIMIT 1";
        ResultSet resultSet =  statement.executeQuery(nameQuery);
        result.add(resultSet.getString("name"));
        resultSet = statement.executeQuery(lastnameQuery);
        result.add(resultSet.getString("lastname"));

        resultSet.close();

        return result;
    }

    public boolean insertData(Connection connection, String name, String lastname) {
        if(connection == null)return false;
        Boolean result = true;
        if(!insert(connection, "names(name)", name))result = false;
        if(!insert(connection, "lastnames(lastname)", lastname))result = false;
        if(!result){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private boolean insert(Connection connection, String table, String data){
        if(!data.equals("")){
            String query = "INSERT INTO " + table + " VALUES(?)";
            System.out.println(query);
            PreparedStatement preparedStatement;
            try{
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, data);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static int getCount(String tableName) {
        Connection connection = connect();
        int res = -1;
        if(connection != null){
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT COUNT(*) FROM " + tableName;
                ResultSet resultSet = statement.executeQuery(query);
                res = resultSet.getInt(1);

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return res;
            }
        }
        return res;
    }
}
