package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ItemVendaDAO;
import cashFlow.MVC.DAO.ParcelamentoVendasDAO;
import cashFlow.MVC.DAO.ParcelasVendaDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.ItemVenda;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.ParcelasVenda;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.Vendas;
import java.util.List;

public class VendasCtrl {

    private final VendasDAO persistVendas;
    private final ParcelamentoVendasDAO persistParcelamento;
    private final ItemVendaDAO persistItensVenda;
    private final ParcelasVendaDAO persistParcelasVenda;
    private final ProdutosDAO persistProduto;

    public VendasCtrl() {
        this.persistVendas = new VendasDAO();
        this.persistParcelamento = new ParcelamentoVendasDAO();
        this.persistItensVenda = new ItemVendaDAO();
        this.persistParcelasVenda = new ParcelasVendaDAO();
        this.persistProduto = new ProdutosDAO();
    }

    public void cadastraVenda(Vendas venda) {
        persistVendas.cadastraVenda(venda);
        atualizaProdutos(venda.getItemVenda());
        cadastraParcelamento(venda.getParcelamentoVenda());
    }

    public Produtos pesquisaProdutos(int idProduto) {
        return persistProduto.pesquisaProdutos(idProduto);
    }

    public void atualizaProdutos(List<ItemVenda> listaItemVenda) {
        listaItemVenda.stream().forEach((each) -> {
            Produtos atuProduto = persistProduto.pesquisaProdutos(each.getCodProduto());
            atuProduto.setQuantidade(atuProduto.getQuantidade().subtract(each.getQuantidade()));
            atuProduto.setValorTotal(atuProduto.getQuantidade().multiply(atuProduto.getValorUnitario()));
            atuProduto.setValorTotalVenda(atuProduto.getQuantidade().multiply(atuProduto.getValorUnitarioVenda()));
            persistProduto.atualizaProduto(atuProduto);
            cadastraItemVenda(each);
        });
    }

    public void cadastraItemVenda(ItemVenda itemVenda) {
        persistItensVenda.cadastraItemVenda(itemVenda);
    }

    public void cadastraParcelamento(ParcelamentoVendas parcelamentoVenda) {
        persistParcelamento.cadastraParcelamentoVenda(parcelamentoVenda);
        parcelamentoVenda.getParcelasVenda().stream().forEach((each) -> {
            cadastraParcelasVenda(each);
        });
    }

    public void cadastraParcelasVenda(ParcelasVenda parcelaVenda) {
        persistParcelasVenda.cadastraParcelasVenda(parcelaVenda);
    }
}
