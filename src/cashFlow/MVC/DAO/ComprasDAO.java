package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.Vendas;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class ComprasDAO {

    private EntityManager em;

    public ComprasDAO() {
    }

    public int getProximaCompra() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (COMPRAS.COD) AS ULTIMO FROM COMPRAS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastraCompra(Compras compra) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(compra);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizaCompra(Compras compra) {
        if (compra != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(compra);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void removeCompra(int idCompra) {
        em = ConexaoEntityManager.getInstance();
        Compras compra = em.find(Compras.class, idCompra);
        if (compra != null) {
            em.getTransaction().begin();
            em.remove(compra);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Compra Excluida com Sucesso!");
        }
    }
        public List getListaComprasData(Date dataInicial, Date dataFinal) {
        em = ConexaoEntityManager.getInstance();
        List listaCompras = em.createQuery("FROM Compras WHERE DATACOMPRA BETWEEN '" + dataInicial + "' and '" + dataFinal + "' ORDER BY DATACOMPRA, COD").getResultList();
        return listaCompras;
    }
        public List getListaComprasFornecedor(int codFornecedor) {
        em = ConexaoEntityManager.getInstance();
        List listaCompras = em.createQuery("FROM Compras WHERE FORNECEDOR = '" + codFornecedor + "' ORDER BY DATACOMPRA, COD").getResultList();
        return listaCompras;
    }
        
    public Compras pesquisaProdutos(Integer codCompra) {
        em = ConexaoEntityManager.getInstance();
        Compras compra = em.find(Compras.class, codCompra);
        return compra;
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
