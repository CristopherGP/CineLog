/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Cristopher GP
 */
public class Conexion {
     public static Connection conectar() {
    try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cinelog",
                "root",
                "Samara182"
            );
            return con;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
