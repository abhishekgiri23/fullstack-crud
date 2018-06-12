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

    
    public MysqlConnection(){
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
    

    
    private static void addProductsData() {

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "kunal",
                    "root");
        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/products.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;

            while ((fileName = br.readLine()) != null) {
                lineNumber++;

                //break comma separated line using ","
                st = new StringTokenizer(fileName, ",");

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

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "kunal",
                    "root");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/user.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;

            while ((fileName = br.readLine()) != null) {
                lineNumber++;

                //break comma separated line using ","
                st = new StringTokenizer(fileName, ",");

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
                                name.replaceAll("'","''") + "'" + ", " + "'" + email + "'" + "," + "'" + userName + "'"+ "," + "'" + phone + "'" + ")";
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

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "kunal",
                    "root");
            System.out.println("Connection is here \n\n\n\n"+( connection != null )+"\n\n\n\n");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/user-impl/src/main/mockdata/txn.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;

            while ((fileName = br.readLine()) != null) {
                lineNumber++;

                //break comma separated line using ","
                st = new StringTokenizer(fileName, ",");

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
}
