
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
public class ItemCompra implements Serializable {

    @Id
    @GeneratedValue(generator = "s_itemCompra")
    @GenericGenerator(name = "s_itemCompra", strategy = "increment")
    private int cod;
    @Column(nullable = false)
    int sequenciaProduto;
    private int codProduto;
    @Column(length = 100, nullable = false)
    private String produto;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal quantidade;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorUnitario;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valorTotal;
    @ManyToOne(optional = false)
    @JoinColumn(name = "compra", foreignKey = @ForeignKey(name = "fk_itemCompra_compra"))
    private Compras compra;

    public ItemCompra() {
    }

    public ItemCompra(int sequenciaProduto, int codProduto, String produto, BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, Compras compra) {
        this.sequenciaProduto = sequenciaProduto;
        this.codProduto = codProduto;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.compra = compra;
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

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Compras getCompra() {
        return compra;
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

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
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
