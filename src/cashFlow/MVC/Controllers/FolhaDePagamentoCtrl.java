/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ComissoesDAO;
import cashFlow.MVC.DAO.DescontosDAO;
import cashFlow.MVC.DAO.EmpregadosDAO;
import cashFlow.MVC.DAO.FolhaDePagamentoDAO;
import cashFlow.MVC.DAO.ItemDescontosDAO;
import cashFlow.MVC.DAO.ProventosDAO;
import cashFlow.MVC.Models.Comissoes;
import cashFlow.MVC.Models.Descontos;
import cashFlow.MVC.Models.Empregados;
import cashFlow.MVC.Models.FolhaDePagamento;
import cashFlow.MVC.Models.ItemDesconto;
import cashFlow.MVC.Models.Proventos;
import java.util.List;

/**
 *
 * @author Sandro Machado
 */
public class FolhaDePagamentoCtrl {

    private EmpregadosDAO empregadosDAO;
    private final DescontosDAO persistDescontos;
    private final ComissoesDAO persistComissoes;
    private final FolhaDePagamentoDAO persistFolhaDePagamento;
    private final ProventosDAO persistProventos;
    private final ItemDescontosDAO persistItemDesconto;

    public FolhaDePagamentoCtrl() {
        this.empregadosDAO = new EmpregadosDAO();
        this.persistDescontos = new DescontosDAO();
        this.persistComissoes = new ComissoesDAO();
        this.persistFolhaDePagamento = new FolhaDePagamentoDAO();
        this.persistProventos = new ProventosDAO();
        this.persistItemDesconto = new ItemDescontosDAO();
    }

    //empregados
    public Empregados getEmpregado(int codEmpregado) {
        return empregadosDAO.pesquisaEmpregado(codEmpregado);
    }

    //descontos
    public List getListaDescontos() {
        return persistDescontos.getListaDescontos();
    }

    public boolean removeDesconto(int cod) {
        return persistDescontos.removeDesconto(cod);
    }

    public int getProximoCodDesconto() {
        return persistDescontos.getProximoCodDesconto();
    }

    public void cadastrarDesconto(Descontos descontos) {
        persistDescontos.cadastrarDesconto(descontos);
    }

    public void atualizaDesconto(Descontos descontos) {
        persistDescontos.atualizaDesconto(descontos);
    }

    public Descontos getDesconto(int idDesconto) {
        return persistDescontos.getDescontos(idDesconto);
    }

    //comissoes
    public List getListaComissoes() {
        return persistComissoes.getListaComissoes();
    }

    public boolean removeComissao(int cod) {
        return persistComissoes.removeComissao(cod);
    }

    public int getProximoCodComissao() {
        return persistComissoes.getProximoCodComissao();
    }

    public void cadastrarComissao(Comissoes comissoes) {
        persistComissoes.cadastrarComissao(comissoes);
    }

    public void atualizaComissao(Comissoes comissoes) {
        persistComissoes.atualizaComissao(comissoes);
    }

    public Comissoes getComissao(int idComissao) {
        return persistComissoes.getComissoes(idComissao);
    }

    //Folha de pagamento
    public void cadastraFolhaDePagamento(FolhaDePagamento folhadePagamento) {
        persistFolhaDePagamento.cadastraFolhaDePagamento(folhadePagamento);
    }

    public boolean existeFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        return persistFolhaDePagamento.existeFolhaDePagamento(folhaDePagamento);
    }

    public FolhaDePagamento pesquisaFolhaDePagamento(String competencia) {
        return persistFolhaDePagamento.pesquisaFolhaDePagamento(competencia);
    }

    public void atualizaFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        persistFolhaDePagamento.atualizaFolhaDePagamento(folhaDePagamento);
    }

    //proventos
    public void cadastrarProvento(Proventos provento) {
        persistProventos.cadastrarProvento(provento);

    }
    public void atualizaProvento(Proventos provento) {
        persistProventos.atualizaProvento(provento);
    }
    public void removeProvento(int codProvento) {
        persistProventos.removeProvento(codProvento);
    }

    public Proventos getProventos(int codProvento) {
        return persistProventos.getProventos(codProvento);
    }

    //Item Descontos
    public void cadastrarItemDesconto(ItemDesconto itemDesconto) {
        persistItemDesconto.cadastrarDesconto(itemDesconto);
    }

    public ItemDesconto getItemDesconto(Integer codItemDesconto) {
        return persistItemDesconto.getItemDesconto(codItemDesconto);
    }

    public boolean removeItemDesconto(int idItemDesconto) {
        return persistItemDesconto.removeItemDesconto(idItemDesconto);
    }
}
