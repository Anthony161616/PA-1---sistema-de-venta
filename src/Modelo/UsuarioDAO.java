
package Modelo;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public boolean login(Usuario usuariodao){
        boolean sw=false;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ? AND rol=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuariodao.getCorreo());
            ps.setString(2, usuariodao.getPass());
            ps.setString(3, usuariodao.getRol());
            rs= ps.executeQuery();
            if (rs.next()) {
                sw=true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return sw;
   }
    
    public String loginNombre(Usuario usuariodao){
        String nombre="";
        String sql = "SELECT nombre FROM usuarios WHERE correo = ? AND pass = ? AND rol=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuariodao.getCorreo());
            ps.setString(2, usuariodao.getPass());
            ps.setString(3, usuariodao.getRol());
            rs= ps.executeQuery();
            if (rs.next()) {
                nombre=rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return nombre;
   }
    
     public boolean Registrar(Usuario reg){
        String sql = "INSERT INTO usuarios (nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarUsuarios(){
       List<Usuario> Lista = new ArrayList();
       String sql = "SELECT * FROM usuarios";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Usuario usuario = new Usuario();
               usuario.setId(rs.getInt("id"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setCorreo(rs.getString("correo"));
               usuario.setPass(rs.getString("pass"));
               usuario.setRol(rs.getString("rol"));
               Lista.add(usuario);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return Lista;
   }
    
     public Usuario BuscarId(int id){
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return usuario;
    }
     
     public boolean ModificarUsuario(Usuario usuario){
       String sql = "UPDATE usuarios SET  nombre=?, correo=?, pass=?, rol=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, usuario.getNombre());
           ps.setString(2, usuario.getCorreo());
           ps.setString(3, usuario.getPass());
           ps.setString(4, usuario.getRol());
           ps.setInt(5, usuario.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
     
     public boolean EliminarUsuario(int id){
       String sql = "DELETE FROM usuarios WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
}
