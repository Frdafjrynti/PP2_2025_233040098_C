/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Firda
 */
public class KoneksiDB {
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try{
            // url database (ganti 'root' dan '' sesuai user/pass database lokasl anda)
            String url = "jdbc:mysql://localhost:3306/kampus_db"; 
            String user = "root";
            String pass = "";

            // Registrasi Driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // Buat Koneksi
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        }catch (SQLException e) {
            // Menampilkan pesan error jika koneksi gagal
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage()); 
        }

         return mysqlconfig;
    }
    
}
