package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Fornecedores;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class FornecedoresDAO {

    private EntityManager em;
    private final ArrayList<Fornecedores> listaFornecedores;

    public FornecedoresDAO() {
        this.listaFornecedores = new ArrayList();
    }

    public void cadastraFornecedor(Fornecedores fornecedor) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(fornecedor);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");
    }

    public void atualizaFornecedor(Fornecedores fornecedor) {
        if (fornecedor != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Fornecedor Atualizado com Sucesso!");
    }

    public boolean removeFornecedor(int idFornecedor) {
        em = ConexaoEntityManager.getInstance();
        Fornecedores fornecedor = em.find(Fornecedores.class, idFornecedor);
        try {
            if (fornecedor != null) {
                em.getTransaction().begin();
                em.remove(fornecedor);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Fornecedor Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Fornecedor\nEstá vinculado a uma compra!");
            return false;
        }
        return true;
    }

    public Fornecedores pesquisaFornecedor(Integer codFornecedor) {
        em = ConexaoEntityManager.getInstance();
        Fornecedores fornecedor = em.find(Fornecedores.class, codFornecedor);
        return fornecedor;
    }
    public ArrayList getUmFornecedor(Integer codFornecedor) {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Fornecedores> q = em.createQuery("FROM Fornecedores f where cod = "+codFornecedor, Fornecedores.class);
        for (Fornecedores each : q.getResultList()) {
            listaFornecedores.add(new Fornecedores(
                    each.getCod(),
                    each.getTipoInscricao(),
                    each.getCpfCnpj(),
                    each.getInscEstadual(),
                    each.getRazaosocial(),
                    each.getNomefantasia(),
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
        return listaFornecedores;
    }
    public ArrayList getListaFornecedores() {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Fornecedores> q = em.createQuery("FROM Fornecedores f ORDER BY f.cod", Fornecedores.class);
        for (Fornecedores each : q.getResultList()) {
            listaFornecedores.add(new Fornecedores(
                    each.getCod(),
                    each.getTipoInscricao(),
                    each.getCpfCnpj(),
                    each.getInscEstadual(),
                    each.getRazaosocial(),
                    each.getNomefantasia(),
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
        return listaFornecedores;
    }

    public int getProximoFornecedor() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (FORNECEDORES.COD) AS ULTIMO FROM FORNECEDORES").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }
}
