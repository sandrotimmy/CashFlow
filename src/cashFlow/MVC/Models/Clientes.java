package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Clientes implements Serializable {

    @Id
    private Integer cod;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100)
    private String endereco;
    @Column(length = 7)
    private String numero;
    @Column(length = 10)
    private String complemento;
    @Column(length = 100)
    private String municipio;
    @Column(length = 2)
    private String uf;
    @Column(length = 7)
    private String caixapostal;
    @Column(length = 8)
    private String cep;
    @Column(length = 3)
    private String ddd;
    @Column(length = 8)
    private String fone;
    @Column(length = 9)
    private String cel;
    @Column(length = 40)
    private String email;

    public Clientes() {
    }

    public Clientes(Integer cod) {
        this.cod = cod;
    }

    public Clientes(Integer cod, String nome, String endereco, String numero, String complemento, String municipio, String uf, String cep, String caixapostal, String ddd, String fone, String cel, String email) {
        this.cod = cod;
        this.nome = nome;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
        this.uf = uf;
        this.cep = cep;
        this.caixapostal = caixapostal;
        this.ddd = ddd;
        this.fone = fone;
        this.cel = cel;
        this.email = email;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCaixapostal() {
        return caixapostal;
    }

    public void setCaixapostal(String caixapostal) {
        this.caixapostal = caixapostal;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String validaFone(String fone) {
        fone = fone.replaceAll("-", "").replaceAll(" ", "");
        if (fone.equals("")) {
            return "";
        } else {
            return fone;
        }
    }

    public String validaCep(String cep) {
        cep = cep.replaceAll("\\.", "").replaceAll("-", "").replaceAll(" ", "");
        if (cep.equals("")) {
            return "";
        } else {
            return cep;
        }
    }

    public String validaNumeros(String numero) {
        if (numero.equals("")) {
            return "";
        } else {
            return numero;
        }
    }

    @Override
    public String toString() {
        return "Classes.Clientes[ cod=" + cod + " ]";
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
