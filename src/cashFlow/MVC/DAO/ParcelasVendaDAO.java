
package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ParcelasVenda;
import javax.persistence.EntityManager;

public class ParcelasVendaDAO {
        private EntityManager em;

    public ParcelasVendaDAO() {
    }

    public void cadastraParcelasVenda(ParcelasVenda parcelaVenda) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(parcelaVenda);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaParcelasVenda(ParcelasVenda parcelaVenda) {
        if (parcelaVenda != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(parcelaVenda);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeParcelasVenda(int idParcelasVenda) {
        em = ConexaoEntityManager.getInstance();
        ParcelasVenda parcelaVenda = em.find(ParcelasVenda.class, idParcelasVenda);
        if (parcelaVenda != null) {
            em.getTransaction().begin();
            em.remove(parcelaVenda);
            em.getTransaction().commit();
            em.close();
        }
    }
}
