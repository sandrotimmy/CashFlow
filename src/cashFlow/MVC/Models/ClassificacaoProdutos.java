package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ClassificacaoProdutos implements Serializable {

    @Id
    @GeneratedValue(generator = "s_classProd")
    @GenericGenerator(name = "s_classProd", strategy = "increment")
    private int cod;
    @Column(length = 300, nullable = false)
    private String nomeClassificacao;

    public ClassificacaoProdutos() {
    }

    public ClassificacaoProdutos(String nomeClassificacao) {
        this.nomeClassificacao = nomeClassificacao;
    }

    public int getCod() {
        return cod;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNomeClassificacao(String nomeClassificacao) {
        this.nomeClassificacao = nomeClassificacao;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
