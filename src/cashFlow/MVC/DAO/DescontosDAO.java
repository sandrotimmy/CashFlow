package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Descontos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class DescontosDAO {

    private EntityManager em;
    private final ArrayList<Descontos> listaDescontos;

    public DescontosDAO() {
        this.listaDescontos = new ArrayList();
    }

    //Métodos do Cadastro de Descontos
    public int getProximoCodDesconto() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (ItemDesconto.COD)AS ULTIMO FROM ItemDesconto").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarDesconto(Descontos desconto) {
        em = ConexaoEntityManager.getInstance();

        em.getTransaction().begin();
        em.persist(desconto);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Desconto Cadastrado com Sucesso!");
    }

    public void atualizaDesconto(Descontos desconto) {
        if (desconto != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(desconto);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Desconto Atualizado com Sucesso!");
    }

    public boolean removeDesconto(int idDesconto) {
        em = ConexaoEntityManager.getInstance();
        Descontos desconto = em.find(Descontos.class, idDesconto);
        try {
            if (desconto != null) {
                em.getTransaction().begin();
                em.remove(desconto);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Desconto Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir esta Desconto\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaDescontos() {
        em = ConexaoEntityManager.getInstance();
        List listaDescontos = em.createQuery("FROM Descontos c ORDER BY c.cod").getResultList();
        return listaDescontos;
    }

    public Descontos getDescontos(Integer codDescontos) {
        em = ConexaoEntityManager.getInstance();
        Descontos desconto = em.find(Descontos.class, codDescontos);
        return desconto;
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
