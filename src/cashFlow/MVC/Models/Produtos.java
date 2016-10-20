package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer cod;
    @Column(length = 10)
    private String identificador;
    @Column(length = 100)
    private String descricao;
    @Column(length = 3)
    private String unidade;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal quantidade;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorUnitario;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorTotal;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorUnitarioVenda;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valorTotalVenda;
    @OneToMany(mappedBy = "produto", cascade = ALL)
    private List<ReajusteProdutos> reajusteProdutos;

    public Produtos() {
    }

    public Produtos(Integer cod, String identificador, String descricao, String unidade, BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, BigDecimal valorUnitarioVenda, BigDecimal valorTotalVenda, List<ReajusteProdutos> reajusteProdutos) {
        this.cod = cod;
        this.identificador = identificador;
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.valorUnitarioVenda = valorUnitarioVenda;
        this.valorTotalVenda = valorTotalVenda;
        this.reajusteProdutos = reajusteProdutos;
    }

    public Produtos(int cod, String identificador, String descricao, String unidade, BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, BigDecimal valorUnitarioVenda, BigDecimal valorTotalVenda) {
        this.cod = cod;
        this.identificador = identificador;
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.valorUnitarioVenda = valorUnitarioVenda;
        this.valorTotalVenda = valorTotalVenda;
    }

    public Produtos(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ReajusteProdutos> getReajusteProdutos() {
        return reajusteProdutos;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorUnitarioVenda() {
        return valorUnitarioVenda;
    }

    public void setValorUnitarioVenda(BigDecimal valorUnitarioVenda) {
        this.valorUnitarioVenda = valorUnitarioVenda;
    }

    public BigDecimal getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(BigDecimal valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public void setReajusteProdutos(List<ReajusteProdutos> reajusteProdutos) {
        this.reajusteProdutos = reajusteProdutos;
    }

    public void atualizaValorDeVenda(BigDecimal novoValor) {
        this.valorUnitarioVenda = novoValor;
        this.valorTotalVenda = novoValor.multiply(quantidade);
    }

    public BigDecimal convValorBanco(String valorSistema) {
        return new BigDecimal(valorSistema.replaceAll("\\.", "").replaceAll(",", "."));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Produtos[ cod=" + cod + " ]";
    }
}
