package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedores implements Serializable {

    @Id
    private Integer cod;
    int tipoInscricao;
    @Column(length = 14)
    private String cpfCnpj;
    @Column(length = 50)
    private String razaosocial;
    @Column(length = 30)
    private String nomefantasia;
    @Column(length = 30)
    private String endereco;
    private String numero;
    @Column(length = 10)
    private String complemento;
    @Column(length = 20)
    private String municipio;
    @Column(length = 2)
    private String uf;
    @Column(length = 8)
    private String cep;
    @Column(length = 7)
    private String caixapostal;
    @Column(length = 3)
    private String ddd;
    @Column(length = 8)
    private String fone;
    @Column(length = 9)
    private String cel;
    @Column(length = 40)
    private String email;

    public Fornecedores() {
    }

    public Fornecedores(Integer cod, int tipoInscricao, String cpfCnpj, String razaosocial, String nomefantasia, String endereco, String numero, String complemento, String municipio, String uf, String cep, String caixapostal, String ddd, String fone, String cel, String email) {
        this.cod = cod;
        this.tipoInscricao = tipoInscricao;
        this.cpfCnpj = cpfCnpj;
        this.razaosocial = razaosocial;
        this.nomefantasia = nomefantasia;
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

    public Fornecedores(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }

    public int getTipoInscricao() {
        return tipoInscricao;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getCaixapostal() {
        return caixapostal;
    }

    public String getDdd() {
        return ddd;
    }

    public String getFone() {
        return fone;
    }

    public String getCel() {
        return cel;
    }

    public String getEmail() {
        return email;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public void setTipoInscricao(int tipoInscricao) {
        this.tipoInscricao = tipoInscricao;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCaixapostal(String caixapostal) {
        this.caixapostal = caixapostal;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Classes.Fornecedores[ cod=" + cod + " ]";
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
