package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
public class ReajusteProdutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "s_reajusteProduto")
    @GenericGenerator(name = "s_reajusteProduto", strategy = "increment")
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataReajuste;
    @Column(length = 200)
    private String motivo;
    @Column(length = 11, nullable = false)
    private String tipoReajuste;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorAntigo;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal reajuste;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorReajustado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto", foreignKey = @ForeignKey(name = "fk_reajusteProduto_produto"))
    private Produtos produto;

    public ReajusteProdutos() {
    }

    public ReajusteProdutos(int cod, Date dataReajuste, String motivo, String tipoReajuste, BigDecimal valorAntigo, BigDecimal reajuste, BigDecimal valorReajustado, Produtos produto) {
        this.cod = cod;
        this.dataReajuste = dataReajuste;
        this.motivo = motivo;
        this.tipoReajuste = tipoReajuste;
        this.valorAntigo = valorAntigo;
        this.reajuste = reajuste;
        this.valorReajustado = valorReajustado;
        this.produto = produto;
    }

    public ReajusteProdutos(Date dataReajuste, String motivo, String tipoReajuste, BigDecimal valorAntigo, BigDecimal reajuste, BigDecimal valorReajustado, Produtos produto) {
        this.cod = cod;
        this.dataReajuste = dataReajuste;
        this.motivo = motivo;
        this.tipoReajuste = tipoReajuste;
        this.valorAntigo = valorAntigo;
        this.reajuste = reajuste;
        this.valorReajustado = valorReajustado;
        this.produto = produto;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataReajuste() {
        return dataReajuste;
    }

    public String getMotivo() {
        return motivo;
    }

    public BigDecimal getReajuste() {
        return reajuste;
    }

    public Produtos getProduto() {
        return produto;
    }

    public BigDecimal getValorAntigo() {
        return valorAntigo;
    }

    public BigDecimal getValorReajustado() {
        return valorReajustado;
    }

    public String getTipoReajuste() {
        return tipoReajuste;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataReajuste(Date dataReajuste) {
        this.dataReajuste = dataReajuste;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setReajuste(BigDecimal reajuste) {
        this.reajuste = reajuste;
    }

    public void setTipoReajuste(String tipoReajuste) {
        this.tipoReajuste = tipoReajuste;
    }

    public void setValorAntigo(BigDecimal valorAntigo) {
        this.valorAntigo = valorAntigo;
    }

    public void setValorReajustado(BigDecimal valorReajustado) {
        this.valorReajustado = valorReajustado;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }
    public BigDecimal convValorBanco(String valorSistema) {
        return new BigDecimal(valorSistema.replaceAll("\\.", "").replaceAll(",", "."));
    }
    //Pega a data do Computador
    public String getDataAtual() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}
