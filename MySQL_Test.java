package mySQL_test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL_Test {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement prepState = null;
    private ResultSet results = null;
    
    public void Read_DB() throws Exception{
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "userName";
        String password = "userPassword";
        
        try{
            connect = DriverManager.getConnection(url, user, password);
            
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM test");
            if(results.next()){
                System.out.println("Result is: " + results.getString(1));
            }else{
                System.out.println("No results for some reason.");
            }
            
        } catch(SQLException e){
            System.out.println("Error in Read_DB: " + e.getMessage());
        } finally {
            try {
                if (results != null) {
                    results.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }

            } catch (SQLException ex) {
                System.out.println("WTF closing error: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args){
        MySQL_Test test = new MySQL_Test();
        try {
            test.Read_DB();
        } catch (Exception ex) {
            System.out.println("Main level error: " + ex.getMessage());
        }
    }
}
