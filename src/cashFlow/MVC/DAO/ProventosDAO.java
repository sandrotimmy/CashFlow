package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Proventos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class ProventosDAO {

    private EntityManager em;


    public ProventosDAO() {

    }

    //Métodos do Cadastro de Proventos
    public int getProximoCodProvento() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (Proventos.COD)AS ULTIMO FROM PROVENTOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarProvento(Proventos provento) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(provento);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Provento Cadastrado com Sucesso!");
    }

    public void atualizaProvento(Proventos provento) {
        if (provento != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(provento);
            em.getTransaction().commit();
            em.close();
        }
    }

    public boolean removeProvento(int idProvento) {
        em = ConexaoEntityManager.getInstance();
        Proventos provento = em.find(Proventos.class, idProvento);
        try {
            if (provento != null) {
                em.getTransaction().begin();
                em.remove(provento);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Provento Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir esta Provento\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaProventos(int codFolhaDePagamento) {
        em = ConexaoEntityManager.getInstance();
        List listaProventos = em.createQuery("FROM Proventos c where FOLHADEPAGAMENTO = "+codFolhaDePagamento+" ORDER BY c.dataComissao").getResultList();
        return listaProventos;
    }

    public Proventos getProventos(Integer codProventos) {
        em = ConexaoEntityManager.getInstance();
        Proventos provento = em.find(Proventos.class, codProventos);
        return provento;
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
