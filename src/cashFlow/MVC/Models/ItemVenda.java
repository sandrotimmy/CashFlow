
package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ItemVenda implements Serializable {

    @Id
    @GeneratedValue(generator = "s_itemVenda")
    @GenericGenerator(name = "s_itemVenda", strategy = "increment")
    private int cod;
    @Column(nullable = false)
    int sequenciaProduto;
    private int codProduto;
    @Column(length = 100, nullable = false)
    private String produto;
    @Column(length = 3)
    private String unidade;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal quantidade;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorUnitario;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorTotal;
    @ManyToOne(optional = false)
    @JoinColumn(name = "venda", foreignKey = @ForeignKey(name = "fk_itemVenda_venda"))
    private Vendas venda;

    public ItemVenda() {
    }

    public ItemVenda(int sequenciaProduto, int codProduto, String produto, String unidade, BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, Vendas venda) {
        this.sequenciaProduto = sequenciaProduto;
        this.codProduto = codProduto;
        this.produto = produto;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.venda = venda;
    }

    public int getCod() {
        return cod;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public String getProduto() {
        return produto;
    }

    public String getUnidade() {
        return unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Vendas getVenda() {
        return venda;
    }

    public int getSequenciaProduto() {
        return sequenciaProduto;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public void setSequenciaProduto(int sequenciaProduto) {
        this.sequenciaProduto = sequenciaProduto;
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
