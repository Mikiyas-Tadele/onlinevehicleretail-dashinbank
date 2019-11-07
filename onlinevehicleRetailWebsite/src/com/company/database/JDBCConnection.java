package com.company.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
    private Connection connection=null;
    private Statement statement = null;

    public JDBCConnection(){
        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:sqlite:/home/user/onlineVehicleRetailDB");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table if exists cars");
            statement.executeUpdate("create table cars (id integer, make string,model string,year integer,transmission string,condition string,price real)");
            statement.executeUpdate("insert into cars values(1, 'Toyota','Corolla',2007,'Automatic','Used',830000)");
            statement.executeUpdate("insert into person values(2, 'Nissan','Kicks',2019,'Automatic',''New',2000000)");
        }
        catch(ClassNotFoundException | SQLException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public void readAllCarValues(){
        try{
            ResultSet rs = statement.executeQuery("select * from cars");
            while(rs.next())
            {
                // read the result set
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("make = " + rs.getString("make"));
                System.out.println("model = " + rs.getString("model"));
                System.out.println("year = " + rs.getInt("year"));
                System.out.println("transmission= " + rs.getString("transmission"));
                System.out.println("condition = " + rs.getString("condition"));
                System.out.println("price= " + rs.getDouble("price"));
            }
        }catch(SQLException ex){
            System.out.println("Error: unable to execute");
        }

    }

    public void readCarById(int id){
        try{
            ResultSet rs = statement.executeQuery("select * from cars where id="+id);
            while(rs.next())
            {
                // read the result set
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("make = " + rs.getString("make"));
                System.out.println("model = " + rs.getString("model"));
                System.out.println("year = " + rs.getInt("year"));
                System.out.println("transmission= " + rs.getString("transmission"));
                System.out.println("condition = " + rs.getString("condition"));
                System.out.println("price= " + rs.getDouble("price"));
            }
        } catch(SQLException ex){
            System.out.println("Error: unable to execute");
        }
    }

    public void updateCarPriceById(double price,int id){

        try{
            statement.executeUpdate("update cars set price="+price+" where id="+id);
        }
        catch(SQLException ex){
            System.out.println("Error: unable to execute");
        }
    }

    public void DeleteCarById(int id){
        try{
            statement.executeUpdate("delete cars where id="+id);
        }catch(SQLException ex){
            System.out.println("Error: unable to execute");
        }
    }
}
