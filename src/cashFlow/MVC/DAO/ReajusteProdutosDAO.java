package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.ReajusteProdutos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class ReajusteProdutosDAO {

    private EntityManager em;
    private final ArrayList<ReajusteProdutos> listaReajustes;

    public ReajusteProdutosDAO() {
        this.listaReajustes = new ArrayList();
    }

    public void cadastrarReajuste(ReajusteProdutos reajuste) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(reajuste);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Reajuste Cadastrado com Sucesso!");
    }

    public void removeReajuste(int codReajuste) {
        em = ConexaoEntityManager.getInstance();
        ReajusteProdutos reajuste = em.find(ReajusteProdutos.class, codReajuste);
        if (reajuste != null) {
            em.getTransaction().begin();
            em.remove(reajuste);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Reajuste Removido com Sucesso!");
        }
    }

    public void atualizaReajuste(ReajusteProdutos reajuste) {
        if (reajuste != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(reajuste);
            em.getTransaction().commit();
            em.close();
        }
    }

    public ArrayList getListaReajuste(Integer codProduto) {
        em = ConexaoEntityManager.getInstance();
        listaReajustes.clear();
        TypedQuery<ReajusteProdutos> q = em.createQuery("FROM ReajusteProdutos WHERE produto = " + codProduto + " ORDER BY dataReajuste", ReajusteProdutos.class);
        for (ReajusteProdutos each : q.getResultList()) {
            listaReajustes.add(new ReajusteProdutos(each.getCod(), each.getDataReajuste(), each.getMotivo(), each.getTipoReajuste(), each.getValorAntigo(), each.getReajuste(), each.getValorReajustado(), each.getProduto()));
        }
        return listaReajustes;
    }

    public ReajusteProdutos pesquisaReajustes(Integer codReajuste) {
        ReajusteProdutos reajuste = em.find(ReajusteProdutos.class, codReajuste);
        return reajuste;
    }

    public int getProximoreajuste() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (REAJUSTEPRODUTOS.COD) AS ULTIMO FROM REAJUSTEPRODUTOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
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
