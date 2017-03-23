package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Descontos implements Serializable {

    @Id
    @GeneratedValue(generator = "s_descontos")
    @GenericGenerator(name = "s_descontos", strategy = "increment")
    private int cod;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 15)
    private String tipo;
    @Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
    private BigDecimal valor;

    public Descontos() {
    }

    public Descontos(String nome, String tipo, BigDecimal valor) {
        this.cod = cod;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
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


    


}
