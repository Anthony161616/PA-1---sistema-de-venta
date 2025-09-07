
package Modelo;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

public class Conexion {
    private String driver, url, user, password;
    private Connection con;
    private Statement sent=null;
    private CallableStatement cts;
    private PreparedStatement psmt = null;

    public Connection getConnection() {
        this.driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        
        String ruta=System.getProperty("user.dir")+"\\src\\BaseDatos\\sistemaventa.accdb";

        this.url =  "jdbc:ucanaccess://"+ruta;
               
        this.con = null;
        
        try{
          
        Class.forName(this.driver);
        con=DriverManager.getConnection(this.url);
         }
        catch(  ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
       return con; 

    }
}
