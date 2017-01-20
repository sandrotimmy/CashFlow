package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class ClientesDAO {

    private EntityManager em;
    private final ArrayList<Clientes> listaClientes;

    public ClientesDAO() {
        this.listaClientes = new ArrayList();
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

    public ArrayList getUmCliente(Integer codCliente) {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Clientes> q = em.createQuery("FROM Clientes where cod = " + codCliente, Clientes.class);
        for (Clientes each : q.getResultList()) {
            listaClientes.add(new Clientes(
                    each.getCod(),
                    each.getTipoInscricao(),
                    each.getCpfCnpj(),
                    each.getInscEstadual(),
                    each.getNome(),
                    each.getEndereco(),
                    each.getNumero(),
                    each.getComplemento(),
                    each.getMunicipio(),
                    each.getBairro(),
                    each.getUf(),
                    each.getCep(),
                    each.getCaixapostal(),
                    each.getDdd(),
                    each.getFone(),
                    each.getCel(),
                    each.getEmail()));
        }
        return listaClientes;
    }

    public ArrayList getListaClientes() {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Clientes> q = em.createQuery("FROM Clientes c ORDER BY c.cod", Clientes.class);
        for (Clientes each : q.getResultList()) {
            listaClientes.add(new Clientes(
                    each.getCod(),
                    each.getTipoInscricao(),
                    each.getCpfCnpj(),
                    each.getInscEstadual(),
                    each.getNome(),
                    each.getEndereco(),
                    each.getNumero(),
                    each.getComplemento(),
                    each.getMunicipio(),
                    each.getBairro(),
                    each.getUf(),
                    each.getCep(),
                    each.getCaixapostal(),
                    each.getDdd(),
                    each.getFone(),
                    each.getCel(),
                    each.getEmail()));
        }
        return listaClientes;
    }
}
