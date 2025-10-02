/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lenovo
 */
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of login method, of class UsuarioDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Usuario usuariodao = null;
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.login(usuariodao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginNombre method, of class UsuarioDAO.
     */
    @Test
    public void testLoginNombre() {
        System.out.println("loginNombre");
        Usuario usuariodao = null;
        UsuarioDAO instance = new UsuarioDAO();
        String expResult = "";
        String result = instance.loginNombre(usuariodao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Registrar method, of class UsuarioDAO.
     */
    @Test
    public void testRegistrar() {
        System.out.println("Registrar");
        Usuario reg = null;
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.Registrar(reg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListarUsuarios method, of class UsuarioDAO.
     */
    @Test
    public void testListarUsuarios() {
        System.out.println("ListarUsuarios");
        UsuarioDAO instance = new UsuarioDAO();
        List expResult = null;
        List result = instance.ListarUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BuscarId method, of class UsuarioDAO.
     */
    @Test
    public void testBuscarId() {
        System.out.println("BuscarId");
        int id = 0;
        UsuarioDAO instance = new UsuarioDAO();
        Usuario expResult = null;
        Usuario result = instance.BuscarId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ModificarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testModificarUsuario() {
        System.out.println("ModificarUsuario");
        Usuario usuario = null;
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.ModificarUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EliminarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testEliminarUsuario() {
        System.out.println("EliminarUsuario");
        int id = 0;
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.EliminarUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}