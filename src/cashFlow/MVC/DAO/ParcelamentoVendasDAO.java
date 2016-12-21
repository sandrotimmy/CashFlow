package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ParcelamentoVendas;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class ParcelamentoVendasDAO {

    private EntityManager em;

    public ParcelamentoVendasDAO() {
    }

    public void cadastraParcelamentoVenda(ParcelamentoVendas venda) {
       if (venda != null){
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
        em.close();
       }
    }

    public void atualizaParcelamentoVenda(ParcelamentoVendas venda) {
        if (venda != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeParcelamentoVenda(int idVenda) {
        em = ConexaoEntityManager.getInstance();
        ParcelamentoVendas venda = em.find(ParcelamentoVendas.class, idVenda);
        if (venda != null) {
            em.getTransaction().begin();
            em.remove(venda);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Parcelamento excluido com Sucesso!");
        }
    }

    public ParcelamentoVendas pesquisaProdutos(Integer idParcelamentoVendas) {
        em = ConexaoEntityManager.getInstance();
        ParcelamentoVendas parcelamentoVendas = em.find(ParcelamentoVendas.class, idParcelamentoVendas);
        return parcelamentoVendas;
    }

}
