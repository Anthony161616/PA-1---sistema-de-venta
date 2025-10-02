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
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
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
     * Test of RegistrarCliente method, of class ClienteDAO.
     */
    @Test
    public void testRegistrarCliente() {
        System.out.println("RegistrarCliente");
        Cliente cliente = null;
        ClienteDAO instance = new ClienteDAO();
        boolean expResult = false;
        boolean result = instance.RegistrarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListarCliente method, of class ClienteDAO.
     */
    @Test
    public void testListarCliente() {
        System.out.println("ListarCliente");
        ClienteDAO instance = new ClienteDAO();
        List expResult = null;
        List result = instance.ListarCliente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EliminarCliente method, of class ClienteDAO.
     */
    @Test
    public void testEliminarCliente() {
        System.out.println("EliminarCliente");
        int id = 0;
        ClienteDAO instance = new ClienteDAO();
        boolean expResult = false;
        boolean result = instance.EliminarCliente(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ModificarCliente method, of class ClienteDAO.
     */
    @Test
    public void testModificarCliente() {
        System.out.println("ModificarCliente");
        Cliente cl = null;
        ClienteDAO instance = new ClienteDAO();
        boolean expResult = false;
        boolean result = instance.ModificarCliente(cl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail. 
    }

    /**
     * Test of Buscarcliente method, of class ClienteDAO.
     */
    @Test
    public void testBuscarcliente() {
        System.out.println("Buscarcliente");
        int dni = 0;
        ClienteDAO instance = new ClienteDAO();
        Cliente expResult = null;
        Cliente result = instance.Buscarcliente(dni);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}