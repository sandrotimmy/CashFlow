package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ItemDesconto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class ItemDescontosDAO {

    private EntityManager em;
    private final ArrayList<ItemDesconto> listaItemDesconto;

    public ItemDescontosDAO() {
        this.listaItemDesconto = new ArrayList();
    }

    //Métodos do Cadastro de ItemDesconto
    public int getProximoCodItemDesconto() {
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

    public void cadastrarDesconto(ItemDesconto itemDesconto) {
        em = ConexaoEntityManager.getInstance();

        em.getTransaction().begin();
        em.persist(itemDesconto);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Desconto Cadastrado com Sucesso!");
    }

    public void atualizaItemDesconto(ItemDesconto itemDesconto) {
        if (itemDesconto != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(itemDesconto);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Desconto Atualizado com Sucesso!");
    }

    public boolean removeItemDesconto(int idItemDesconto) {
        em = ConexaoEntityManager.getInstance();
        ItemDesconto itemDesconto = em.find(ItemDesconto.class, idItemDesconto);
        try {
            if (itemDesconto != null) {
                em.getTransaction().begin();
                em.remove(itemDesconto);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Desconto Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Desconto\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public List getListaItemDesconto() {
        em = ConexaoEntityManager.getInstance();
        List listaItemDesconto = em.createQuery("FROM ItemDesconto c ORDER BY c.cod").getResultList();
        return listaItemDesconto;
    }

    public ItemDesconto getItemDesconto(Integer codItemDesconto) {
        em = ConexaoEntityManager.getInstance();
        ItemDesconto itemDesconto = em.find(ItemDesconto.class, codItemDesconto);
        return itemDesconto;
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
