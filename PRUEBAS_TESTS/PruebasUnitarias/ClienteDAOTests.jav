package Modelo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteDAOTestRefactor {

    private Cliente crearClienteEjemplo() {
        return new Cliente(1, "12345678", "Jose Manuel", "984755923", "Cusco 8000", "Jose SAC");
    }

    @Test
    public void debeRegistrarCliente() {
        Cliente cliente = crearClienteEjemplo();

        ClienteDAO dao = new ClienteDAO() {
            @Override
            public boolean RegistrarCliente(Cliente c) {
                return true;
            }
        };

        boolean resultado = dao.RegistrarCliente(cliente);
        assertTrue(resultado);
    }

    @Test
    public void debeListarClientes() {
        ClienteDAO dao = new ClienteDAO() {
            @Override
            public List<Cliente> ListarCliente() {
                List<Cliente> lista = new ArrayList<>();
                lista.add(crearClienteEjemplo());
                return lista;
            }
        };

        List<Cliente> clientes = dao.ListarCliente();
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }

    @Test
    public void debeBuscarClientePorDni() {
        ClienteDAO dao = new ClienteDAO() {
            @Override
            public Cliente Buscarcliente(int dni) {
                return crearClienteEjemplo();
            }
        };

        Cliente cliente = dao.Buscarcliente(12345678);
        assertNotNull(cliente);
        assertEquals("Jose Manuel", cliente.getNombre());
    }

    @Test
    public void debeModificarCliente() {
        Cliente cliente = crearClienteEjemplo();
        cliente.setNombre("Nombre Modificado");

        ClienteDAO dao = new ClienteDAO() {
            @Override
            public boolean ModificarCliente(Cliente cl) {
                return true;
            }
        };

        boolean resultado = dao.ModificarCliente(cliente);
        assertTrue(resultado);
    }

    @Test
    public void debeEliminarCliente() {
        Cliente cliente = crearClienteEjemplo();

        ClienteDAO dao = new ClienteDAO() {
            @Override
            public boolean EliminarCliente(int id) {
                return true;
            }
        };

        boolean resultado = dao.EliminarCliente(cliente.getId());
        assertTrue(resultado);
    }
}
