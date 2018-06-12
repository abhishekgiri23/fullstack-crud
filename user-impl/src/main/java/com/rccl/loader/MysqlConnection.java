package com.rccl.loader;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

@Singleton
public class MysqlConnection {
    
    private static String TRANSACTION_FILE_PATH = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/txn.csv";
    private static String USER_FILE_PATH = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/user.csv";
    private static String PRODUCT_FILE_PATH = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/products.csv";
    private static String JDBC_URL = "jdbc:mysql://localhost/txns";
    private static String USERNAME = "kunal";
    private static String PASSWORD = "root";
    
    public MysqlConnection() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException e) {
            
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;
            
        }
        addtxnsData();
        addProductsData();
        addUserData();
    }
    
    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return connection;
    }
    
    private static void addProductsData() {
        
        Connection connection = getConnection();
        Statement stmt = null;
        
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE_PATH));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;
            
            while ((PRODUCT_FILE_PATH = br.readLine()) != null) {
                lineNumber++;
                
                //break comma separated line using ","
                st = new StringTokenizer(PRODUCT_FILE_PATH, ",");
                
                while (st.hasMoreTokens()) {
                    //display csv values
                    Statement statement = null;
                    try {
                        statement = connection.createStatement();
                        String productId = st.nextToken();
                        String productName = st.nextToken();
                        String price = st.nextToken();
                        String theStatement = "INSERT IGNORE INTO  Products VALUES(" + productId + ", " + "'" + productName + "'" + ", " + price + ")";
                        
                        statement.executeUpdate(theStatement);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
    
    
    private static void addUserData() {
        
        Connection connection = getConnection();
        Statement stmt = null;
        
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;
            
            while ((USER_FILE_PATH = br.readLine()) != null) {
                lineNumber++;
                
                //break comma separated line using ","
                st = new StringTokenizer(USER_FILE_PATH, ",");
                
                while (st.hasMoreTokens()) {
                    //display csv values
                    Statement statement = null;
                    try {
                        statement = connection.createStatement();
                        String userId = st.nextToken();
                        String name = st.nextToken();
                        String email = st.nextToken();
                        String userName = st.nextToken();
                        String phone = st.nextToken();
                        String theStatement = "INSERT IGNORE INTO  USER_DETAILS VALUES(" + userId + ", " + "'" +
                                name.replaceAll("'", "''") + "'" + ", " + "'" + email + "'" + "," + "'" + userName + "'" + "," + "'" + phone + "'" + ")";
//						System.out.println(theStatement);
                        statement.executeUpdate(theStatement);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
    
    
    private static void addtxnsData() {
        
        Connection connection = getConnection();
        Statement stmt = null;
        
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_FILE_PATH));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;
            
            while ((TRANSACTION_FILE_PATH = br.readLine()) != null) {
                lineNumber++;
                
                //break comma separated line using ","
                st = new StringTokenizer(TRANSACTION_FILE_PATH, ",");
                
                while (st.hasMoreTokens()) {
                    //display csv values
                    Statement statement = null;
                    try {
                        assert connection != null;
                        statement = connection.createStatement();
                        String userID = st.nextToken();
                        String productID = st.nextToken();
                        String qunatity = st.nextToken();
                        String theStatement = "INSERT IGNORE INTO  txn VALUES(" + userID + ", " + productID + ", " + qunatity + ")";
                        statement.executeUpdate(theStatement);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
