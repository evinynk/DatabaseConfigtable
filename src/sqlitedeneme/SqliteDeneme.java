/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitedeneme;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SqliteDeneme {
     private Connection connect() {
       
        String url = "jdbc:sqlite:C://sqlite/config.db"; //veritabanı dosya yolu
        Connection conn = null; //bağlantı nesnemiz
        try {
            conn = DriverManager.getConnection(url); //DriverManager:uygulamayı bir veritabanı url i ile belirlenen bir veri kaynağına bağlar.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    public void selectAll(){
        String sql = "SELECT key, value FROM configtable";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement(); // statement --> sorgu ifadeleri için kullanılır.
             ResultSet rs    = stmt.executeQuery(sql)){ //executeQuery() --> sorgulama için kullanılır(select sorgusu için)
                                                        //sorgu sonuçları ResultSet arabirimi sayesinde elde edilir.
            
            while (rs.next()) {
                System.out.println("key: " + rs.getString("key")); 
                System.out.println("value: " + rs.getString("value"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
  
     public void insert(String key, String value) {
        String sql = "INSERT INTO configtable(key,value) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public void delete(String key){
         String sql = "DELETE from configtable WHERE key = ?";
         try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     }
     
    public static void main(String[] args) {     
        SqliteDeneme app = new SqliteDeneme();
        //app.insert("DENEME", "2");
        //app.delete("DENEME");
        app.insert("DENEME2", 3);
        app.selectAll();  
       
    }
}