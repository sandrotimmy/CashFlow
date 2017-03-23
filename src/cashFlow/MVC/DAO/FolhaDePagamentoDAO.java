package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.FolhaDePagamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class FolhaDePagamentoDAO {

    private EntityManager em;
    private final List<FolhaDePagamento> listaFolhaDePagamento;

    public FolhaDePagamentoDAO() {
        this.listaFolhaDePagamento = new ArrayList();
    }

    public int getProximoFolhaDePagamento() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (FolhaDePagamento.COD) AS ULTIMO FROM FolhaDePagamento").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastraFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(folhaDePagamento);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        if (folhaDePagamento != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(folhaDePagamento);
            em.getTransaction().commit();
            em.close();
        }
    }

    public boolean removeFolhaDePagamento(int idFolhaDePagamento) {
        em = ConexaoEntityManager.getInstance();
        FolhaDePagamento folhaDePagamento = em.find(FolhaDePagamento.class, idFolhaDePagamento);
        try {
            if (folhaDePagamento != null) {
                em.getTransaction().begin();
                em.remove(folhaDePagamento);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Folha de Pagamento Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Folha de Pagamento\nPossui calculo de Folha de Pgto!");
            return false;
        }
        return true;
    }

    public boolean existeFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        em = ConexaoEntityManager.getInstance();
        FolhaDePagamento folhaTemp = em.find(FolhaDePagamento.class, folhaDePagamento.getCod());
        if (folhaTemp != null) {
            return true;
        } else {
            return false;
        }

    }

    public FolhaDePagamento pesquisaFolhaDePagamento(String competencia) {
        em = ConexaoEntityManager.getInstance();
        FolhaDePagamento folhaDePagamento = em.createQuery("FROM FolhaDePagamento where competencia = " + competencia, FolhaDePagamento.class).getSingleResult();
        return folhaDePagamento;
    }

    public List getListaFolhaDePagamento() {
        em = ConexaoEntityManager.getInstance();
        List listaFolhaDePagamento = em.createQuery("FROM FolhaDePagamento e ORDER BY e.cod", FolhaDePagamento.class).getResultList();
        return listaFolhaDePagamento;
    }
}
