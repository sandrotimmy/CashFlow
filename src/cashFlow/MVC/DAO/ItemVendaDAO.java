package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ItemVenda;
import cashFlow.MVC.Models.Vendas;
import java.util.List;
import javax.persistence.EntityManager;

public class ItemVendaDAO {

    private EntityManager em;

    public ItemVendaDAO() {
    }

    public void cadastraItemVenda(ItemVenda itemVenda) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(itemVenda);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaItemVenda(ItemVenda itemVenda) {
        if (itemVenda != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(itemVenda);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeItemVenda(int idItemVenda) {
        em = ConexaoEntityManager.getInstance();
        Vendas itemVenda = em.find(Vendas.class, idItemVenda);
        if (itemVenda != null) {
            em.getTransaction().begin();
            em.remove(itemVenda);
            em.getTransaction().commit();
            em.close();
        }
    }
        public List getListaItemVenda(int codVenda) {
        em = ConexaoEntityManager.getInstance();
        List listaItemVenda = em.createQuery("FROM ItemVenda WHERE VENDA = " + codVenda).getResultList();
        return listaItemVenda;
    }
}
