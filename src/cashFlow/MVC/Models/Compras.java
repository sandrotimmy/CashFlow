package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compras implements Serializable {

    @Id
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataCompra;
    @Column(length = 200)
    private String observacoes;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorTotalProdutos;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorDesconto;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorAcrescimo;
    @Column(length = 200)
    private String MotivoAcrescimo;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorTotalCompra;
    @Column(length = 11)
    private String TipoDesconto;
    @OneToMany(mappedBy = "compra")
    private List<ItemCompra> itemCompra;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fornecedor", foreignKey = @ForeignKey(name = "fk_compra_fornecedor"))
    Fornecedores fornecedor;
    @OneToOne
    @JoinColumn(name = "cancelamentoCompra", foreignKey = @ForeignKey(name = "fk_compra_cancelamento"))
    private CancelamentoCompras cancelamentoCompra;

    public Compras() {
    }

    public Compras(int cod) {
        this.cod = cod;
    }

    public Compras(int cod, Date dataCompra, String observacoes, BigDecimal valorTotalProdutos,
            BigDecimal valorDesconto, BigDecimal valorAcrescimo, String MotivoAcrescimo,
            BigDecimal valorTotalCompra, String TipoDesconto, List<ItemCompra> itemCompra,
            Fornecedores fornecedor) {
        this.cod = cod;
        this.dataCompra = dataCompra;
        this.observacoes = observacoes;
        this.valorTotalProdutos = valorTotalProdutos;
        this.valorDesconto = valorDesconto;
        this.valorAcrescimo = valorAcrescimo;
        this.MotivoAcrescimo = MotivoAcrescimo;
        this.valorTotalCompra = valorTotalCompra;
        this.TipoDesconto = TipoDesconto;
        this.itemCompra = itemCompra;
        this.fornecedor = fornecedor;
    }

    public Compras(int cod, Date dataCompra, String observacoes, BigDecimal valorTotalProdutos,
            BigDecimal valorDesconto, BigDecimal valorAcrescimo, String MotivoAcrescimo,
            BigDecimal valorTotalCompra, String TipoDesconto, List<ItemCompra> itemCompra,
            Fornecedores fornecedor, CancelamentoCompras cancelamentoCompra) {
        this.cod = cod;
        this.dataCompra = dataCompra;
        this.observacoes = observacoes;
        this.valorTotalProdutos = valorTotalProdutos;
        this.valorDesconto = valorDesconto;
        this.valorAcrescimo = valorAcrescimo;
        this.MotivoAcrescimo = MotivoAcrescimo;
        this.valorTotalCompra = valorTotalCompra;
        this.TipoDesconto = TipoDesconto;
        this.itemCompra = itemCompra;
        this.fornecedor = fornecedor;
        this.cancelamentoCompra = cancelamentoCompra;
    }

    public Compras(int cod, Date dataCompra, String observacoes) {
        this.cod = cod;
        this.dataCompra = dataCompra;
        this.observacoes = observacoes;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public BigDecimal getValorTotalCompra() {
        return valorTotalCompra;
    }

    public String getTipoDesconto() {
        return TipoDesconto;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public BigDecimal getValorAcrescimo() {
        return valorAcrescimo;
    }

    public String getMotivoAcrescimo() {
        return MotivoAcrescimo;
    }

    public BigDecimal getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public CancelamentoCompras getCancelamentoCompra() {
        return cancelamentoCompra;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setValorTotalCompra(BigDecimal valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }

    public void setTipoDesconto(String TipoDesconto) {
        this.TipoDesconto = TipoDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setValorAcrescimo(BigDecimal valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    public void setMotivoAcrescimo(String MotivoAcrescimo) {
        this.MotivoAcrescimo = MotivoAcrescimo;
    }

    public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
    }

    public void setCancelamentoCompra(CancelamentoCompras cancelamentoCompra) {
        this.cancelamentoCompra = cancelamentoCompra;
    }

    public BigDecimal convValorBanco(String valorSistema) {
        return new BigDecimal(valorSistema.replaceAll("\\.", "").replaceAll(",", "."));
    }
    public String getDataInicial() {
        LocalDate dataAtual = LocalDate.now();
        dataAtual.withDayOfMonth(01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.withDayOfMonth(1).format(formatter);
    }

    public String getDataFinal() {
        LocalDate dataAtual = LocalDate.now();
        dataAtual.withDayOfMonth(01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.withDayOfMonth(dataAtual.lengthOfMonth()).format(formatter);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
