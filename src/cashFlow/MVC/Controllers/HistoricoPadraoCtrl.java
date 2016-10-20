package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.Models.HistoricoPadrao;
import java.util.List;

public class HistoricoPadraoCtrl {

    private final HistoricosDAO persistHistoricoPadrao;

    public HistoricoPadraoCtrl() {
        this.persistHistoricoPadrao = new HistoricosDAO();
    }

    public List getListaHistorico() {
        return persistHistoricoPadrao.getListaHistorico();
    }

    public boolean removeHistorico(int cod) {
        return persistHistoricoPadrao.removeHistorico(cod);
    }

    public int getProximoCodHistorico() {
        return persistHistoricoPadrao.getProximoCodHistorico();
    }

    public void cadastrarHistorico(HistoricoPadrao historico) {
        persistHistoricoPadrao.cadastrarHistorico(historico);
    }

    public void atualizaHistorico(HistoricoPadrao historico) {
        persistHistoricoPadrao.atualizaHistorico(historico);
    }

    public HistoricoPadrao getHistorico(int idHistorico) {
        return persistHistoricoPadrao.getHistorico(idHistorico);
    }
}
