package cashFlow.MVC.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CancelamentoVendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "s_cancelamentoVenda")
    @GenericGenerator(name = "s_cancelamentoVenda", strategy = "increment")
    private int cod;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataCancelamento;
    @Column(length = 200)
    private String motivo;
    @OneToOne(mappedBy = "cancelamentoVenda")
    private Vendas venda;
    

    public CancelamentoVendas() {
    }

    public CancelamentoVendas(int cod, Date dataCancelamento, String motivo, Vendas venda) {
        this.cod = cod;
        this.dataCancelamento = dataCancelamento;
        this.motivo = motivo;
        this.venda = venda;
    }

    public CancelamentoVendas(Date dataCancelamento, String motivo, Vendas venda) {
        this.dataCancelamento = dataCancelamento;
        this.motivo = motivo;
        this.venda = venda;
    }

    public int getCod() {
        return cod;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public String getMotivo() {
        return motivo;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }
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
