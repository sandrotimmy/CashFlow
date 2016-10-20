package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ClientesDAO;
import cashFlow.MVC.Models.Clientes;

public class ClientesCtrl {

    private final ClientesDAO persistClientes;

    public ClientesCtrl() {
        this.persistClientes = new ClientesDAO();
    }

    public Clientes pesquisaCliente(int idCliente) {
        return persistClientes.pesquisaCliente(idCliente);
    }

}
