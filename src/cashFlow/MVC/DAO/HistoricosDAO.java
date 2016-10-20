package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.HistoricoPadrao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class HistoricosDAO {

    private EntityManager em;
    private final ArrayList<HistoricoPadrao> listaHistoricos;

    public HistoricosDAO() {
        this.listaHistoricos = new ArrayList();
    }

    //Métodos do Cadastro de Historicos
    public int getProximoCodHistorico() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (HISTORICOPADRAO.COD)AS ULTIMO FROM HISTORICOPADRAO").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarHistorico(HistoricoPadrao historico) {
        em = ConexaoEntityManager.getInstance();

        em.getTransaction().begin();
        em.persist(historico);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Historico Cadastrado com Sucesso!");
    }

    public void atualizaHistorico(HistoricoPadrao historico) {
        if (historico != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(historico);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Histórico Atualizado com Sucesso!");
    }

    public boolean removeHistorico(int idHistorico) {
        em = ConexaoEntityManager.getInstance();
        HistoricoPadrao historico = em.find(HistoricoPadrao.class, idHistorico);
        try {
            if (historico != null) {
                em.getTransaction().begin();
                em.remove(historico);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Historico Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Historico\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaHistorico() {
        em = ConexaoEntityManager.getInstance();
        List listaHistoricos = em.createQuery("FROM HistoricoPadrao h ORDER BY h.cod").getResultList();
        return listaHistoricos;
    }

    public HistoricoPadrao getHistorico(Integer codHistorico) {
        em = ConexaoEntityManager.getInstance();
        HistoricoPadrao historico = em.find(HistoricoPadrao.class, codHistorico);
        return historico;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

}
