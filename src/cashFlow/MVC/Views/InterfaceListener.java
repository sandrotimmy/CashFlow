package cashFlow.MVC.Views;

import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;

public interface InterfaceListener {

    public void mensagemCodHistorico(String msg);

    public void mensagemCodHistoricoBusca(String msg);

    public void mensagemCodClienteVenda(String msg);

    public void mensagemCodClienteBusca(String msg);

    public void mensagemCodFornecedorCompra(String msg);

    public void mensagemCodFornecedorBusca(String msg);

    public void mensagemCodProdutoVenda(String msg);

    public void mensagemCodProdutoCompra(String msg);

    public void mensagemCodProdutoBusca(String msg);

    public void mensagemCodProdutoReajuste(String msg);

    public void vendaCancelar(Vendas venda);

    public void compraCancelar(Compras compra);

    public void mensagemCodClienteConsultVenda(String msg);
    
    public void parcelamentoVendas (ParcelamentoVendas parcelamentoVendas);
    
    public void mensagemCodComissoes (String codComissao);
    
    public void mensagemCodDescontos(String codDesconto);
    
    public void mensagemCodCalculoFolhaPagamento(String codBusca);
}
