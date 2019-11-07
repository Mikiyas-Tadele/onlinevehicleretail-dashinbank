package com.company;

import com.company.database.JDBCConnection;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        JDBCConnection con=new JDBCConnection();
        con.readAllCarValues();
        con.updateCarPriceById(820000,1);
        con.readCarById(1);
        con.DeleteCarById(2);
        con.readAllCarValues();
    }
}
