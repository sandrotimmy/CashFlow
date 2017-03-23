package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ItemDesconto implements Serializable {

    @Id
    @GeneratedValue(generator = "s_itemDesconto")
    @GenericGenerator(name = "s_itemDesconto", strategy = "increment")
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataDesconto;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100)
    private String observacoes;
    @Column(length = 11)
    private String tipo;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorBase;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorDesconto;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorTotalDesconto;
    @ManyToOne(optional = false)
    @JoinColumn(name = "folhaDePagamentoDesc", foreignKey = @ForeignKey(name = "fk_desconto_folhaDePagamento"))
    private FolhaDePagamento folhaDePagamentoDesc;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "descontos", foreignKey = @ForeignKey(name = "fk_desconto_itemDesconto"))
    Descontos descontos;
    @OneToMany(mappedBy = "itemDesconto", fetch = FetchType.LAZY)
    private List<Proventos> listaProventos;

    public ItemDesconto() {
    }

    public ItemDesconto(Date dataDesconto, String nome, String observacoes, String tipo, BigDecimal valorBase, BigDecimal valorDesconto, BigDecimal valorTotalDesconto, FolhaDePagamento folhaDePagamento, Descontos descontos, List<Proventos> listaProventos) {

        this.dataDesconto = dataDesconto;
        this.nome = nome;
        this.observacoes = observacoes;
        this.tipo = tipo;
        this.valorBase = valorBase;
        this.valorDesconto = valorDesconto;
        this.valorTotalDesconto = valorTotalDesconto;
        this.folhaDePagamentoDesc = folhaDePagamento;
        this.descontos = descontos;
        this.listaProventos = listaProventos;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataDesconto() {
        return dataDesconto;
    }

    public String getNome() {
        return nome;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public BigDecimal getValorTotalDesconto() {
        return valorTotalDesconto;
    }

    public FolhaDePagamento getFolhaDePagamentoDesc() {
        return folhaDePagamentoDesc;
    }

    public Descontos getDescontos() {
        return descontos;
    }

    public List<Proventos> getListaProventos() {
        return listaProventos;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataDesconto(Date dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setValorTotalDesconto(BigDecimal valorTotalDesconto) {
        this.valorTotalDesconto = valorTotalDesconto;
    }

    public void setFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        this.folhaDePagamentoDesc = folhaDePagamento;
    }

    public void setDescontos(Descontos descontos) {
        this.descontos = descontos;
    }

    public void setListaProventos(List<Proventos> listaProventos) {
        this.listaProventos = listaProventos;
    }

}
