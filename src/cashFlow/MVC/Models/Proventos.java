package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Proventos implements Serializable {

    @Id
    @GeneratedValue(generator = "s_proventos")
    @GenericGenerator(name = "s_proventos", strategy = "increment")
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataComissao;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100)
    private String observacoes;
    @Column(length = 100, nullable = false)
    private String bloco;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorServico;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal comissao;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorTotalComissao;
    @Column(length = 10)
    private String situacao;
    @ManyToOne(optional = false)
    @JoinColumn(name = "folhaDePagamento", foreignKey = @ForeignKey(name = "fk_provento_folhaDePagamento"))
    private FolhaDePagamento folhaDePagamento;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "itemDesconto", foreignKey = @ForeignKey(name = "fk_provento_itemDesconto"))
    private ItemDesconto itemDesconto;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "comissoes", foreignKey = @ForeignKey(name = "fk_comissao_provento"))
    Comissoes comissoes;

    public Proventos() {
    }

    public Proventos(Date dataComissao, String nome, String observacoes, String bloco, BigDecimal valorServico, BigDecimal comissao, BigDecimal valorTotalComissao, String situacao, FolhaDePagamento folhaDePagamento, Comissoes comissoes) {

        this.dataComissao = dataComissao;
        this.nome = nome;
        this.observacoes = observacoes;
        this.bloco = bloco;
        this.valorServico = valorServico;
        this.comissao = comissao;
        this.valorTotalComissao = valorTotalComissao;
        this.situacao = situacao;
        this.folhaDePagamento = folhaDePagamento;
        this.comissoes = comissoes;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataComissao() {
        return dataComissao;
    }

    public String getNome() {
        return nome;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getBloco() {
        return bloco;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public BigDecimal getValorTotalComissao() {
        return valorTotalComissao;
    }

    public String getSituacao() {
        return situacao;
    }

    public FolhaDePagamento getFolhaDePagamento() {
        return folhaDePagamento;
    }

    public Comissoes getComissoes() {
        return comissoes;
    }

    public ItemDesconto getItemDesconto() {
        return itemDesconto;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataComissao(Date dataComissao) {
        this.dataComissao = dataComissao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public void setValorTotalComissao(BigDecimal valorTotalComissao) {
        this.valorTotalComissao = valorTotalComissao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
        this.folhaDePagamento = folhaDePagamento;
    }

    public void setComissoes(Comissoes comissoes) {
        this.comissoes = comissoes;
    }

    public void setItemDesconto(ItemDesconto itemDesconto) {
        this.itemDesconto = itemDesconto;
    }

}
