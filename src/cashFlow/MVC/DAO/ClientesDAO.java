package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class ClientesDAO {

    private EntityManager em;

    public ClientesDAO() {

    }

    public int getProximoCliente() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (CLIENTES.COD) AS ULTIMO FROM CLIENTES").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastraClientes(Clientes cliente) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
    }

    public void atualizaClientes(Clientes cliente) {
        if (cliente != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Cliente Atualizado com Sucesso!");
    }

    public boolean removeCliente(int idCliente) {
        em = ConexaoEntityManager.getInstance();
        Clientes cliente = em.find(Clientes.class, idCliente);
        try {
            if (cliente != null) {
                em.getTransaction().begin();
                em.remove(cliente);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Cliente\nEstá vinculado a uma Venda!");
            return false;
        }
        return true;
    }

    public Clientes pesquisaCliente(Integer codCliente) {
        em = ConexaoEntityManager.getInstance();
        Clientes cliente = em.find(Clientes.class, codCliente);
        return cliente;
    }

    public List getUmCliente(Integer codCliente) {
        em = ConexaoEntityManager.getInstance();
        List<Clientes> listaClientes = em.createQuery("FROM Clientes where cod = " + codCliente).getResultList();
        return listaClientes;
    }

    public List getListaClientes() {
        em = ConexaoEntityManager.getInstance();
        List<Clientes> listaClientes = em.createQuery("FROM Clientes c ORDER BY c.nome").getResultList();
        return listaClientes;
    }

    public List getListaAniversariantes(int mes) {
        em = ConexaoEntityManager.getInstance();
        List<Clientes> listaClientes = em.createQuery("FROM Clientes c WHERE (extract (month from dataNascimento)) = "+mes+" ORDER BY c.dataNascimento").getResultList();
        return listaClientes;
    }
}
