package com.theironyard;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main"); //establishes the server and connection

        Statement stmt = conn.createStatement(); //sends SQL stuff to your database
        stmt.execute("CREATE TABLE IF NOT EXISTS players (name VARCHAR, health DOUBLE, score INT, is_alive BOOLEAN)"); //Creates table
        stmt.execute("INSERT INTO players VALUES ('Alice', 100, 10, true)"); //inserts info into the table
        stmt.execute("INSERT INTO players VALUES ('Bob', 95, 10, true)"); //inserts info into the table
        stmt.execute("UPDATE players SET health = 50 WHERE name = 'Alice'"); //sets the health of a VARCHAR in the table
        stmt.execute("DELETE players WHERE name = 'Bob'"); //deletes information from your table

        ResultSet results = stmt.executeQuery("SELECT * FROM players");//selects player
        while (results.next()){//loops over player
            String name = results.getString("name");//player info
            Double health = results.getDouble("health");//player info
            int score = results.getInt("score");//player info
            boolean isAlive = results.getBoolean("is_alive");//player info
            System.out.println(String.format("%s %s %s %s", name, health, score, isAlive));
        }
        stmt.execute("DROP TABLE players"); //deletes the table each time you run the program

        conn.close();//closes the connection
    }
}
