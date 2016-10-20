
package cashFlow.MVC.Models;

import java.math.BigDecimal;
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
public class ParcelasVenda {

    @Id
    @GeneratedValue(generator = "s_parcelamentoVenda")
    @GenericGenerator(name = "s_parcelamentoVenda", strategy = "increment")
    int id;
    int numParcela;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataParcela;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorCredito;
    @ManyToOne(optional = false)
    @JoinColumn(name = "parcelamentoVenda", foreignKey = @ForeignKey(name = "fk_parcelamentoVenda_parcela"))
    private ParcelamentoVendas parcelamentoVenda;

    public ParcelasVenda() {
    }

    public ParcelasVenda(int numParcela, Date dataParcela, BigDecimal valorCredito, ParcelamentoVendas parcelamentoVenda) {
        this.numParcela = numParcela;
        this.dataParcela = dataParcela;
        this.valorCredito = valorCredito;
        this.parcelamentoVenda = parcelamentoVenda;
    }

    public int getId() {
        return id;
    }

    public Date getDataParcela() {
        return dataParcela;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public ParcelamentoVendas getProduto() {
        return parcelamentoVenda;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public ParcelamentoVendas getParcelamentoVenda() {
        return parcelamentoVenda;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataParcela(Date dataParcela) {
        this.dataParcela = dataParcela;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public void setProduto(ParcelamentoVendas parcelamentoVendas) {
        this.parcelamentoVenda = parcelamentoVendas;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public void setParcelamentoVenda(ParcelamentoVendas parcelamentoVenda) {
        this.parcelamentoVenda = parcelamentoVenda;
    }
    
    
}
