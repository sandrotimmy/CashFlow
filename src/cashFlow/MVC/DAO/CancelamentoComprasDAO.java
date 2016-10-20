package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.CancelamentoCompras;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class CancelamentoComprasDAO {
 
    private EntityManager em;
    private final ArrayList<CancelamentoCompras> listaCancelamentoCompras;

    public CancelamentoComprasDAO() {
        em = ConexaoEntityManager.getInstance();
        this.listaCancelamentoCompras = new ArrayList();
    }

    //Métodos do Cadastro de Historicos
    public int getProximoCodCancelamento() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (CANCELAMENTOCOMPRAS.COD)AS ULTIMO FROM CANCELAMENTOCOMPRAS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarCancelamento(CancelamentoCompras cancelamentoCompra) {
        if (!em.isOpen()) {
            em = ConexaoEntityManager.getInstance();
        } else {
            em.getTransaction().begin();
            em.persist(cancelamentoCompra);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Compra Cancelada com Sucesso!");
    }

    public void atualizaCancelamento(CancelamentoCompras historico) {
        if (historico != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(historico);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Cancelamento atualizado com Sucesso!");
    }

    public boolean removeCancelamento(int idCancelamento) {
        em = ConexaoEntityManager.getInstance();
        CancelamentoCompras historico = em.find(CancelamentoCompras.class, idCancelamento);
        try {
            if (historico != null) {
                em.getTransaction().begin();
                em.remove(historico);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Cancelamento excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Cancelamento\nEstá vinculado a outro Processo!");
            return false;
        }
        return true;
    }

    public ArrayList getListaCancelamento() {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<CancelamentoCompras> q = em.createQuery("FROM CancelamentoCompras c ORDER BY c.cod", CancelamentoCompras.class);
        for (CancelamentoCompras each : q.getResultList()) {
            listaCancelamentoCompras.add(new CancelamentoCompras(each.getCod(), each.getDataCancelamento(), each.getMotivo(), each.getCompra()));
        }
        return listaCancelamentoCompras;
    }

    public CancelamentoCompras pesquisaCancelamento(Integer codCancelamento) {
        em = ConexaoEntityManager.getInstance();
        CancelamentoCompras historico = em.find(CancelamentoCompras.class, codCancelamento);
        return historico;
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

}
