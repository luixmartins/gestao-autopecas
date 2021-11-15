/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author User
 */
public class Fornecedor {

    private int cod_fornecedor;
    private String nome;
    private String cnpj;
    private String inscricao_estadual;
    /* Instanciamentos */
    private Endereco endFor;
    private Contato contatoFor;
    private Produto produto;

    public Fornecedor() {
        this.endFor = new Endereco();
        this.contatoFor = new Contato();
        this.produto = new Produto();
    }

    public Fornecedor(int cod_fornecedor, String nome, String cnpj, String inscricao_estadual) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.endFor = new Endereco();
        this.contatoFor = new Contato();
        this.produto = new Produto();
    }

    public Fornecedor(int cod_fornecedor, String nome, String cnpj, String inscricao_estadual, Endereco endFor, Contato contatoFor) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.endFor = endFor;
        this.contatoFor = contatoFor;
    }

    public Fornecedor(int cod_fornecedor, String nome, String cnpj, String inscricao_estadual, Produto produto) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.produto = produto;
    }

    public int getCod_fornecedor() {
        return cod_fornecedor;
    }

    public void setCod_fornecedor(int cod_fornecedor) {
        this.cod_fornecedor = cod_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public Endereco getEndFor() {
        return endFor;
    }

    public void setEndFor(Endereco endFor) {
        this.endFor = endFor;
    }

    public Contato getContatoFor() {
        return contatoFor;
    }

    public void setContatoFor(Contato contatoFor) {
        this.contatoFor = contatoFor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
    
    
    
    

}
