/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.List;
import javax.swing.JTable;
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
public class VentaDAOTest {
    
    public VentaDAOTest() {
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
     * Test of IdVenta method, of class VentaDAO.
     */
    @Test
    public void testIdVenta() {
        System.out.println("IdVenta");
        VentaDAO instance = new VentaDAO();
        int expResult = 0;
        int result = instance.IdVenta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RegistrarVenta method, of class VentaDAO.
     */
    @Test
    public void testRegistrarVenta() {
        System.out.println("RegistrarVenta");
        Venta v = null;
        VentaDAO instance = new VentaDAO();
        int expResult = 0;
        int result = instance.RegistrarVenta(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RegistrarDetalle method, of class VentaDAO.
     */
    @Test
    public void testRegistrarDetalle() {
        System.out.println("RegistrarDetalle");
        Detalle Dv = null;
        VentaDAO instance = new VentaDAO();
        int expResult = 0;
        int result = instance.RegistrarDetalle(Dv);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ActualizarStock method, of class VentaDAO.
     */
    @Test
    public void testActualizarStock() {
        System.out.println("ActualizarStock");
        int cant = 0;
        int id = 0;
        VentaDAO instance = new VentaDAO();
        boolean expResult = false;
        boolean result = instance.ActualizarStock(cant, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Listarventas method, of class VentaDAO.
     */
    @Test
    public void testListarventas() {
        System.out.println("Listarventas");
        VentaDAO instance = new VentaDAO();
        List expResult = null;
        List result = instance.Listarventas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BuscarVenta method, of class VentaDAO.
     */
    @Test
    public void testBuscarVenta() {
        System.out.println("BuscarVenta");
        int id = 0;
        VentaDAO instance = new VentaDAO();
        Venta expResult = null;
        Venta result = instance.BuscarVenta(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pdfV method, of class VentaDAO.
     */
    @Test
    public void testPdfV() {
        System.out.println("pdfV");
        VentaDAO instance = new VentaDAO();
        instance.pdfV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pdfTicket method, of class VentaDAO.
     */
    @Test
    public void testPdfTicket() {
        System.out.println("pdfTicket");
        String nomvendedor = "";
        String dni = "";
        String nomcliente = "";
        JTable tabladatos = null;
        VentaDAO instance = new VentaDAO();
        instance.pdfTicket(nomvendedor, dni, nomcliente, tabladatos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}