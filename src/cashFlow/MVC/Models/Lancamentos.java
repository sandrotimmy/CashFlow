    package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lancamentos implements Serializable {

    @Id
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataLancamento;
    private String observacoes;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorDebito;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorCredito;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "historico", foreignKey = @ForeignKey(name = "fk_historicoPadrao_lancamento"))
    HistoricoPadrao historico;

    public Lancamentos() {
    }

    public Lancamentos(int cod, Date dataLancamento, HistoricoPadrao historico, String observacoes, BigDecimal valorDebito, BigDecimal valorCredito) {
        this.cod = cod;
        this.dataLancamento = dataLancamento;
        this.historico = historico;
        this.observacoes = observacoes;
        this.valorDebito = valorDebito;
        this.valorCredito = valorCredito;
    }

    public Lancamentos(int cod, Date dataLancamento, String observacoes, BigDecimal valorDebito, BigDecimal valorCredito) {
        this.cod = cod;
        this.dataLancamento = dataLancamento;
        this.observacoes = observacoes;
        this.valorDebito = valorDebito;
        this.valorCredito = valorCredito;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public HistoricoPadrao getHistorico() {
        return historico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public BigDecimal getValorDebito() {
        return valorDebito;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setHistorico(HistoricoPadrao historico) {
        this.historico = historico;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }
}
