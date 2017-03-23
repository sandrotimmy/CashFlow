package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
public class FolhaDePagamento implements Serializable {

    @Id
    @GeneratedValue(generator = "s_folhaDePagamento")
    @GenericGenerator(name = "s_folhaDePagamento", strategy = "increment")
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date competencia;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal totalProventos;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal totalDescontos;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal saldoApagar;
    @ManyToOne(optional = false)
    @JoinColumn(name = "empregado", foreignKey = @ForeignKey(name = "fk_folhaDePagamento_empregado"))
    private Empregados empregado;
    @OneToMany(mappedBy = "folhaDePagamento", fetch = FetchType.LAZY)
    private List<Proventos> listaProventos;
    @OneToMany(mappedBy = "folhaDePagamentoDesc", fetch = FetchType.LAZY)
    private List<ItemDesconto> listaItemDescontos;

    public FolhaDePagamento() {
    }

    public FolhaDePagamento(Date competencia, BigDecimal totalProventos, BigDecimal totalDescontos, BigDecimal saldoApagar, Empregados empregado, List<Proventos> listaProventos, List <ItemDesconto> listaItemDescontos) {
        this.competencia = competencia;
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.saldoApagar = saldoApagar;
        this.empregado = empregado;
        this.listaProventos = listaProventos;
        this.listaItemDescontos = listaItemDescontos;
    }

    public FolhaDePagamento(Date competencia, Empregados empregado) {
        this.competencia = competencia;
        this.empregado = empregado;
    }

    public int getCod() {
        return cod;
    }

    public Date getCompetencia() {
        return competencia;
    }

    public BigDecimal getTotalProventos() {
        return totalProventos;
    }

    public BigDecimal getTotalDescontos() {
        return totalDescontos;
    }

    public BigDecimal getSaldoApagar() {
        return saldoApagar;
    }

    public Empregados getEmpregado() {
        return empregado;
    }

    public List<Proventos> getListaProventos() {
        return listaProventos;
    }

    public List<ItemDesconto> getListaItemDescontos() {
        return listaItemDescontos;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setCompetencia(Date competencia) {
        this.competencia = competencia;
    }

    public void setTotalProventos(BigDecimal totalProventos) {
        this.totalProventos = totalProventos;
    }

    public void setTotalDescontos(BigDecimal totalDescontos) {
        this.totalDescontos = totalDescontos;
    }

    public void setSaldoApagar(BigDecimal saldoApagar) {
        this.saldoApagar = saldoApagar;
    }

    public void setEmpregado(Empregados empregado) {
        this.empregado = empregado;
    }

    public void setListaProventos(List<Proventos> listaProventos) {
        this.listaProventos = listaProventos;
    }

    public void setListaItemDescontos(List<ItemDesconto> listaItemDescontos) {
        this.listaItemDescontos = listaItemDescontos;
    }

}
