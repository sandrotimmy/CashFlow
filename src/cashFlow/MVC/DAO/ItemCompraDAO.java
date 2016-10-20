package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ItemCompra;
import cashFlow.MVC.Models.Compras;
import java.util.ArrayList;
import javax.persistence.EntityManager;

public class ItemCompraDAO {

    private EntityManager em;
    private final ArrayList<ItemCompra> listaItemCompra;

    public ItemCompraDAO() {
        this.listaItemCompra = new ArrayList();
    }

    public void cadastraItemCompra(ItemCompra itemCompra) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(itemCompra);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaItemCompra(ItemCompra itemCompra) {
        if (itemCompra != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(itemCompra);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeItemCompra(int idItemCompra) {
        em = ConexaoEntityManager.getInstance();
        Compras itemCompra = em.find(Compras.class, idItemCompra);
        if (itemCompra != null) {
            em.getTransaction().begin();
            em.remove(itemCompra);
            em.getTransaction().commit();
            em.close();
        }
    }
}
