package cashFlow.MVC.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ParcelamentoVendas {

    @Id
    @GeneratedValue(generator = "s_parcelamentoVenda")
    @GenericGenerator(name = "s_parcelamentoVenda", strategy = "increment")
    int id;
    @Column(length = 100)
    private String formaDePgto;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal totalVenda;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal entrada;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal restante;
    private int numParcelas;
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    private int demaisParcelas;
    @OneToOne(mappedBy = "cancelamentoVenda")
    private Vendas venda;
    @OneToMany(mappedBy = "parcelamentoVenda", cascade = ALL)
    private List<ParcelasVenda> parcelasVenda;

    public ParcelamentoVendas() {
    }

    public ParcelamentoVendas(String formaDePgto, BigDecimal totalVenda, BigDecimal entrada,
            BigDecimal restante, int numParcelas, Date dataEntrada, int demaisParcelas) {
        this.formaDePgto = formaDePgto;
        this.totalVenda = totalVenda;
        this.entrada = entrada;
        this.restante = restante;
        this.numParcelas = numParcelas;
        this.dataEntrada = dataEntrada;
        this.demaisParcelas = demaisParcelas;
    }

    public String getFormaDePgto() {
        return formaDePgto;
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public BigDecimal getEntrada() {
        return entrada;
    }

    public BigDecimal getRestante() {
        return restante;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public int getDemaisParcelas() {
        return demaisParcelas;
    }

    public Vendas getVenda() {
        return venda;
    }

    public List<ParcelasVenda> getParcelasVenda() {
        return parcelasVenda;
    }

    public void setFormaDePgto(String formaDePgto) {
        this.formaDePgto = formaDePgto;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        this.totalVenda = totalVenda;
    }

    public void setEntrada(BigDecimal entrada) {
        this.entrada = entrada;
    }

    public void setRestante(BigDecimal restante) {
        this.restante = restante;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDemaisParcelas(int demaisParcelas) {
        this.demaisParcelas = demaisParcelas;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public void setParcelasVenda(List<ParcelasVenda> parcelasVenda) {
        this.parcelasVenda = parcelasVenda;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
