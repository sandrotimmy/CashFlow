package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ClassificacaoProdutos;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class ClassificacaoProdutosDAO {

    private EntityManager em;

    public ClassificacaoProdutosDAO() {
    }

    //Métodos do Cadastro de Classificacaos
    public int getProximoCodClassificacao() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (CLASSIFICACAOPRODUTOS.COD)AS ULTIMO FROM CLASSIFICACAOPRODUTOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarClassificacao(ClassificacaoProdutos classificacao) {
        em = ConexaoEntityManager.getInstance();

        em.getTransaction().begin();
        em.persist(classificacao);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Classificacao Cadastrada com Sucesso!");
    }

    public void atualizaClassificacao(ClassificacaoProdutos classificacao) {
        if (classificacao != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(classificacao);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Classificação Atualizada com Sucesso!");
    }

    public boolean removeClassificacao(int idClassificacao) {
        em = ConexaoEntityManager.getInstance();
        ClassificacaoProdutos classificacao = em.find(ClassificacaoProdutos.class, idClassificacao);
        try {
            if (classificacao != null) {
                em.getTransaction().begin();
                em.remove(classificacao);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Classificacao Excluida com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir esta Classificacao\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaClassificacao() {
        em = ConexaoEntityManager.getInstance();
        List listaClassificacao = em.createQuery("FROM ClassificacaoProdutos h ORDER BY h.cod").getResultList();
        return listaClassificacao;
    }

    public ClassificacaoProdutos getClassificacao(Integer codClassificacao) {
        em = ConexaoEntityManager.getInstance();
        ClassificacaoProdutos classificacao = em.find(ClassificacaoProdutos.class, codClassificacao);
        return classificacao;
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
