package cashFlow.MVC.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empregados implements Serializable {

    @Id
    private Integer cod;
    int tipoInscricao;
    @Column(length = 14)
    private String cpfCnpj;
    @Column(length = 20)
    private String inscEstadual;
    @Column(length = 100, nullable = false)
    private String nome;
    @Temporal(TemporalType.DATE)
    @Column
    private Date dataNascimento;
    @Column(length = 100)
    private String endereco;
    @Column(length = 7)
    private String numero;
    @Column(length = 10)
    private String complemento;
    @Column(length = 100)
    private String municipio;
    @Column(length = 30)
    private String bairro;
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
    @Column(length = 40)
    private String cargo;
    @Temporal(TemporalType.DATE)
    @Column
    private Date dataAdmissao;
    @Column(length = 12)
    private String categoria;
    @Column(length = 30)
    private String formaPgto;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal salario;
    @OneToMany(mappedBy = "empregado", fetch = FetchType.LAZY)
    private List<FolhaDePagamento> listaFolhaPagamento;

    
    public Empregados(Integer cod, int tipoInscricao, String cpfCnpj, String inscEstadual, String nome, Date dataNascimento, String endereco, String numero, String complemento, String municipio, String bairro, String uf, String caixapostal, String cep, String ddd, String fone, String cel, String email, String cargo, Date dataAdmissao, String categoria, String formaPgto, BigDecimal salario) {
        this.cod = cod;
        this.tipoInscricao = tipoInscricao;
        this.cpfCnpj = cpfCnpj;
        this.inscEstadual = inscEstadual;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
        this.bairro = bairro;
        this.uf = uf;
        this.caixapostal = caixapostal;
        this.cep = cep;
        this.ddd = ddd;
        this.fone = fone;
        this.cel = cel;
        this.email = email;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.categoria = categoria;
        this.formaPgto = formaPgto;
        this.salario = salario;
    }

    public Empregados() {
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

    public String getInscEstadual() {
        return inscEstadual;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
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

    public String getBairro() {
        return bairro;
    }

    public String getUf() {
        return uf;
    }

    public String getCaixapostal() {
        return caixapostal;
    }

    public String getCep() {
        return cep;
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

    public String getCargo() {
        return cargo;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public List<FolhaDePagamento> getListaFolhaPagamento() {
        return listaFolhaPagamento;
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

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCaixapostal(String caixapostal) {
        this.caixapostal = caixapostal;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setListaFolhaPagamento(List<FolhaDePagamento> listaFolhaPagamento) {
        this.listaFolhaPagamento = listaFolhaPagamento;
    }


}
