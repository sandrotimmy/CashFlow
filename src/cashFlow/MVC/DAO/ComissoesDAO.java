package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.Comissoes;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class ComissoesDAO {

    private EntityManager em;
    private final ArrayList<Comissoes> listaComissoes;

    public ComissoesDAO() {
        this.listaComissoes = new ArrayList();
    }

    //Métodos do Cadastro de Comissoes
    public int getProximoCodComissao() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (COMISSOES.COD)AS ULTIMO FROM COMISSOES").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarComissao(Comissoes comissao) {
        em = ConexaoEntityManager.getInstance();

        em.getTransaction().begin();
        em.persist(comissao);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Comissão Cadastrada com Sucesso!");
    }

    public void atualizaComissao(Comissoes comissao) {
        if (comissao != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(comissao);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Comissão Atualizada com Sucesso!");
    }

    public boolean removeComissao(int idComissao) {
        em = ConexaoEntityManager.getInstance();
        Comissoes comissao = em.find(Comissoes.class, idComissao);
        try {
            if (comissao != null) {
                em.getTransaction().begin();
                em.remove(comissao);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Comissão Excluida com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir esta Comissão\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaComissoes() {
        em = ConexaoEntityManager.getInstance();
        List listaComissoes = em.createQuery("FROM Comissoes c ORDER BY c.cod").getResultList();
        return listaComissoes;
    }

    public Comissoes getComissoes(Integer codComissoes) {
        em = ConexaoEntityManager.getInstance();
        Comissoes comissao = em.find(Comissoes.class, codComissoes);
        return comissao;
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
