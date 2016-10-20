package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.CancelamentoVendas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class CancelamentoVendasDAO {

    private EntityManager em;
    private final ArrayList<CancelamentoVendas> listaCancelamentoVendas;

    public CancelamentoVendasDAO() {
        em = ConexaoEntityManager.getInstance();
        this.listaCancelamentoVendas = new ArrayList();
    }

    //Métodos do Cadastro de Historicos
    public int getProximoCodCancelamento() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (CANCELAMENTOVENDAS.COD)AS ULTIMO FROM CANCELAMENTOVENDAS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastrarCancelamento(CancelamentoVendas cancelamentoVenda) {
        if (!em.isOpen()) {
            em = ConexaoEntityManager.getInstance();
        } else {
            em.getTransaction().begin();
            em.persist(cancelamentoVenda);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Venda Cancelada com Sucesso!");
    }

    public void atualizaCancelamento(CancelamentoVendas historico) {
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
        CancelamentoVendas historico = em.find(CancelamentoVendas.class, idCancelamento);
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
        TypedQuery<CancelamentoVendas> q = em.createQuery("FROM CancelamentoVendas c ORDER BY c.cod", CancelamentoVendas.class);
        for (CancelamentoVendas each : q.getResultList()) {
            listaCancelamentoVendas.add(new CancelamentoVendas(each.getCod(), each.getDataCancelamento(), each.getMotivo(), each.getVenda()));
        }
        return listaCancelamentoVendas;
    }

    public CancelamentoVendas pesquisaCancelamento(Integer codCancelamento) {
        em = ConexaoEntityManager.getInstance();
        CancelamentoVendas historico = em.find(CancelamentoVendas.class, codCancelamento);
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
