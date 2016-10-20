package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HistoricoPadrao implements Serializable {

    @Id
    private int cod;
    @Column(length = 7, nullable = false)
    private String tipo;
    @Column(length = 300, nullable = false)
    private String nomeHistorico;
    @Column(length = 7)
    private String utilizador;

    public HistoricoPadrao() {
    }

    public HistoricoPadrao(int cod, String tipo, String nomeHistorico, String utilizador) {
        this.cod = cod;
        this.tipo = tipo;
        this.nomeHistorico = nomeHistorico;
        this.utilizador = utilizador;
    }

    public HistoricoPadrao(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeHistorico() {
        return nomeHistorico;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNomeHistorico(String nomeHistorico) {
        this.nomeHistorico = nomeHistorico;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
