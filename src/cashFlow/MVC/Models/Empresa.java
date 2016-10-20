package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empresa implements Serializable {

    @Id
    private int idEmpresa;
    @Column(length = 14)
    private String cnpj;
    @Column(length = 50)
    private String nomeEmpresa;
    @Column(length = 30)
    private String nomeFantasia;
    @Column(length = 30)
    private String endereco;
    @Column(length = 10)
    private String complemento;
    @Column(length = 7)
    private String numero;
    @Column(length = 20)
    private String municipio;
    @Column(length = 2)
    private String uf;
    @Column(length = 8)
    private String cep;
    @Column(length = 7)
    private String caixaPostal;
    @Column(length = 3)
    private String ddd;
    @Column(length = 8)
    private String fone;
    @Column(length = 9)
    private String cel;
    @Column(length = 40)
    private String email;

    public Empresa() {
    }

    public Empresa(int idEmpresa, String nomeEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
    }

    public Empresa(int idEmpresa, String cnpj, String nomeEmpresa, String nomeFantasia, String endereco, String complemento, String numero, String municipio, String uf, String cep, String caixaPostal, String ddd, String fone, String cel, String email) {
        this.idEmpresa = idEmpresa;
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.complemento = complemento;
        this.numero = numero;
        this.municipio = municipio;
        this.uf = uf;
        this.cep = cep;
        this.caixaPostal = caixaPostal;
        this.ddd = ddd;
        this.fone = fone;
        this.cel = cel;
        this.email = email;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getCaixaPostal() {
        return caixaPostal;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public String getCel() {
        return cel;
    }

    public String getFone() {
        return fone;
    }

    public String getDdd() {
        return ddd;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getNumero() {
        return numero;
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

    public String validaCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "").replaceAll(" ", "");
        if (cnpj.equals("")) {
            return "";
        } else {
            return cnpj;
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
