package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vendas implements Serializable {

    @Id
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataVenda;
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
    private BigDecimal valorTotalVenda;
    @Column(length = 11)
    private String TipoDesconto;
    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY)
    private List<ItemVenda> itemVenda;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "fk_venda_cliente"))
    Clientes cliente;
    @OneToOne
    @JoinColumn(name = "cancelamentoVenda", foreignKey = @ForeignKey(name = "fk_venda_cancelamento"))
    private CancelamentoVendas cancelamentoVenda;
    @OneToOne
    @JoinColumn(name = "parcelamentoVenda", foreignKey = @ForeignKey(name = "fk_venda_parcelamento"))
    private ParcelamentoVendas parcelamentoVenda;
    
    public Vendas() {
    }

    public Vendas(int cod) {
        this.cod = cod;
        this.itemVenda = new ArrayList();
    }

    public Vendas(BigDecimal valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public Vendas(int cod, Date dataVenda, String observacoes, BigDecimal valorTotalProdutos,
            BigDecimal valorDesconto, BigDecimal valorAcrescimo, String MotivoAcrescimo,
            BigDecimal valorTotalVenda, String TipoDesconto, List<ItemVenda> itemVenda,
            Clientes cliente) {
        this.cod = cod;
        this.dataVenda = dataVenda;
        this.observacoes = observacoes;
        this.valorTotalProdutos = valorTotalProdutos;
        this.valorDesconto = valorDesconto;
        this.valorAcrescimo = valorAcrescimo;
        this.MotivoAcrescimo = MotivoAcrescimo;
        this.valorTotalVenda = valorTotalVenda;
        this.TipoDesconto = TipoDesconto;
        this.itemVenda = itemVenda;
        this.cliente = cliente;
    }

    public Vendas(int cod, Date dataVenda, String observacoes, BigDecimal valorTotalProdutos,
            BigDecimal valorDesconto, BigDecimal valorAcrescimo, String MotivoAcrescimo,
            BigDecimal valorTotalVenda, String TipoDesconto, List<ItemVenda> itemVenda,
            Clientes cliente, CancelamentoVendas cancelamentoVenda) {
        this.cod = cod;
        this.dataVenda = dataVenda;
        this.observacoes = observacoes;
        this.valorTotalProdutos = valorTotalProdutos;
        this.valorDesconto = valorDesconto;
        this.valorAcrescimo = valorAcrescimo;
        this.MotivoAcrescimo = MotivoAcrescimo;
        this.valorTotalVenda = valorTotalVenda;
        this.TipoDesconto = TipoDesconto;
        this.itemVenda = itemVenda;
        this.cliente = cliente;
        this.cancelamentoVenda = cancelamentoVenda;
    }

    public Vendas(int cod, Date dataVenda, String observacoes) {
        this.cod = cod;
        this.dataVenda = dataVenda;
        this.observacoes = observacoes;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotalVenda() {
        return valorTotalVenda;
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

    public CancelamentoVendas getCancelamentoVenda() {
        return cancelamentoVenda;
    }

    public ParcelamentoVendas getParcelamentoVenda() {
        return parcelamentoVenda;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setItemVenda(List<ItemVenda> itemVenda) {
        this.itemVenda = itemVenda;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public void setValorTotalVenda(BigDecimal valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
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

    public void setCancelamentoVenda(CancelamentoVendas cancelamentoVenda) {
        this.cancelamentoVenda = cancelamentoVenda;
    }

    public void setParcelamentoVenda(ParcelamentoVendas parcelamentoVenda) {
        this.parcelamentoVenda = parcelamentoVenda;
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

    public BigDecimal convValorBanco(String valorSistema) {
        return new BigDecimal(valorSistema.replaceAll("\\.", "").replaceAll(",", "."));
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
