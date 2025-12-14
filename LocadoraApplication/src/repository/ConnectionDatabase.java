/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;

/**
 *
 * @author alefi
 */
public class ConnectionDatabase {
    private static Connection conn;
    
    public static boolean conectar(){
        String url = System.getenv("FIREBIRD_URL");
        String user = System.getenv("FIREBIRD_USER");
        String password = System.getenv("FIREBIRD_PASSWORD");
        try{
           conn = DriverManager.getConnection(url,user,password);
           return true;
        }
        catch(SQLException e){
            System.out.print(e.getMessage());
            return false;
        }
    }
    
    public static void statusConexao(){
        if (conn != null){
            System.out.print("Conexão bem sucedida!!");
        }
    }
    
    public static void desconectar(){
        if (conn != null){
            try{
               conn.close();
            }
            catch(SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }
    
    public static boolean verificarUser(String user, String password){
        if (conn != null){
            try{
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*)"
                        + "FROM USUARIO WHERE login = ? and password = ?");
                stmt.setString(1, user);
                stmt.setString(2, password);
                ResultSet result = stmt.executeQuery();
                result.next();
                return (result.getInt(1) > 0)? true : false;
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
}
}
