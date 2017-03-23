package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comissoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_comissoes")
    @GenericGenerator(name = "s_comissoes", strategy = "increment")
    private int cod;
    @Column(length = 300, nullable = false)
    private String nome;
    @Column(length = 11, nullable = false)
    private String tipo;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal valor;

    public Comissoes() {
    }

        public Comissoes(String nome, String tipo, BigDecimal valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }
        
    public Comissoes(int cod, String nome, String tipo, BigDecimal valor) {
        this.cod = cod;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

 
}
