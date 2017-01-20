package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.Lancamentos;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class LancamentosDAO {

    private EntityManager em;
    private final ArrayList<HistoricoPadrao> listaLancamentos;

    public LancamentosDAO() {
        this.listaLancamentos = new ArrayList();
    }

    public void cadastrarLancamento(Lancamentos lancamento) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(lancamento);
        em.getTransaction().commit();
        em.close();
    }

    public void removeLancamento(int idLancamento) {
        em = ConexaoEntityManager.getInstance();
        Lancamentos lancamento = em.find(Lancamentos.class, idLancamento);
        if (lancamento != null) {
            em.getTransaction().begin();
            em.remove(lancamento);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Lançamento Removido com Sucesso!");
        }
    }

    public List getListaLancamentos(Date dataInicial, Date dataFinal) {
        em = ConexaoEntityManager.getInstance();
        List listaLancamentos = em.createQuery("FROM Lancamentos WHERE DATALANCAMENTO BETWEEN '" + dataInicial + "' and '" + dataFinal + "' ORDER BY DATALANCAMENTO, COD").getResultList();
        return listaLancamentos;
    }
    
    public List getListaLancamentosGeral () {
        em = ConexaoEntityManager.getInstance();
        List listaLancamentos = em.createQuery("FROM Lancamentos").getResultList();
        return listaLancamentos;
    }

    //Retorna a proxima posição diponível para cadastrar o lançamento.
    public int getProximoCodLancamento() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (LANCAMENTOS.COD)AS ULTIMO FROM LANCAMENTOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public BigDecimal getSaldoPeriodo(Date dataInicial, Date dataFinal) {
        em = ConexaoEntityManager.getInstance();
        BigDecimal saldoTotal = new BigDecimal(0);
        Object ultimo = em.createNativeQuery("SELECT SUM (VALORDEBITO) - SUM (VALORCREDITO) FROM LANCAMENTOS WHERE DATALANCAMENTO BETWEEN '" + dataInicial + "' AND '" + dataFinal + "'").getSingleResult();
        if (ultimo != null) {
            saldoTotal = new BigDecimal(ultimo.toString());
        }
        return saldoTotal;
    }
}
