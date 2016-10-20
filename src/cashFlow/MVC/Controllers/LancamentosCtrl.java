package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.Lancamentos;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class LancamentosCtrl {

    private final LancamentosDAO persistLancamentos;
    private final HistoricoPadraoCtrl historicoPadraoCtrl;

    public LancamentosCtrl() {
        this.persistLancamentos = new LancamentosDAO();
        this.historicoPadraoCtrl = new HistoricoPadraoCtrl();
    }

    public int getProximoCodLancamento() {
        return persistLancamentos.getProximoCodLancamento();
    }

    public void cadastrarLancamento(Lancamentos lancamento) {
        persistLancamentos.cadastrarLancamento(lancamento);
    }

    public void removeLancamento(int idLancamento) {
        persistLancamentos.removeLancamento(idLancamento);
    }

    public List getListaLancamentos(Date dataInicial, Date dataFinal) {
        return persistLancamentos.getListaLancamentos(dataInicial, dataFinal);
    }

    public HistoricoPadrao pesquisaHistorico(int codHistorico) {
        return historicoPadraoCtrl.getHistorico(codHistorico);
    }

    public BigDecimal getSaldoPeriodo(Date dataInicial, Date dataFinal) {
        return persistLancamentos.getSaldoPeriodo(dataInicial, dataFinal);
    }
}
