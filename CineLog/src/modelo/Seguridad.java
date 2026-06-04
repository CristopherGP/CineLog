/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.security.MessageDigest;
/**
 *
 * @author Cristopher GP
 */
public class Seguridad {

    public static String encriptar(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(texto.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
