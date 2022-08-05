/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anam
 */
public class DatabaseConfig {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String host = "jdbc:mysql://localhost/db_rentalmobil";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;
    public static String kode = "";
    
    
    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(host, username, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "KONEKSI GAGAL "+e);
        }
        return conn;
    }
    public static void main(String[] args) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(host, username, password);
            System.out.println("Koneksi Berhasil");
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil!");
            conn.close();
        } catch (SQLException | ClassNotFoundException se) {
            System.out.println("koneksi gagal" + se.getMessage());
        }
    }
}
