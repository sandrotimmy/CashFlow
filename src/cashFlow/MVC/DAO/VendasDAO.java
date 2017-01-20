package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Vendas;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class VendasDAO {

    private EntityManager em;

    public VendasDAO() {
    }

    public int getProximaVenda() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (VENDAS.COD) AS ULTIMO FROM VENDAS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastraVenda(Vendas venda) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaVenda(Vendas venda) {
        if (venda != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeVenda(int idVenda) {
        em = ConexaoEntityManager.getInstance();
        Vendas venda = em.find(Vendas.class, idVenda);
        if (venda != null) {
            em.getTransaction().begin();
            em.remove(venda);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Venda Excluida com Sucesso!");
        }
    }

    public List getListaVendasData(Date dataInicial, Date dataFinal) {
        em = ConexaoEntityManager.getInstance();
        List listaVendas = em.createQuery("FROM Vendas WHERE DATAVENDA BETWEEN '" + dataInicial + "' and '" + dataFinal + "' ORDER BY DATAVENDA, COD").getResultList();
        return listaVendas;
    }

    public List getListaVendasCliente(int codCliente) {
        em = ConexaoEntityManager.getInstance();
        List listaVendas = em.createQuery("FROM Vendas WHERE CLIENTE = '" + codCliente + "' ORDER BY DATAVENDA, COD").getResultList();
        return listaVendas;
    }

    public List getVenda(int codVenda) {
        em = ConexaoEntityManager.getInstance();
        List listaVendas = em.createQuery("FROM Vendas WHERE COD = " + codVenda).getResultList();
        return listaVendas;
    }

    public Vendas pesquisaProdutos(Integer codVenda) {
        em = ConexaoEntityManager.getInstance();
        Vendas venda = em.find(Vendas.class, codVenda);
        return venda;
    }

    public java.sql.Date convDataBanco(String dataSistema) {
        //Conversão da data do sistema para formato da data do Banco
        java.sql.Date dataBanco = new java.sql.Date(1);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataBanco = new java.sql.Date(format.parse(dataSistema).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBanco;
    }

    public String convDataSistema(java.util.Date dataBanco) {
        String dataSistema;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dataSistema = format.format(dataBanco);
        return dataSistema;

    }
}
