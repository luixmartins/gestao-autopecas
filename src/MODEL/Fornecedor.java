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
    private String rua;
    private int numeroCasa;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone_cliente;
    private String celular_cliente;
    private String email;

    private Produto produto;

    private MarcaProduto marca;
    private CategoriaProduto categoria;

    public Fornecedor() {
        this.produto = new Produto();
        this.marca = new MarcaProduto();
        this.categoria = new CategoriaProduto();

    }

    public Fornecedor(int cod_fornecedor, String nome, String cnpj, String inscricao_estadual, String rua, int numeroCasa, String bairro, String cidade, String estado, String cep, String telefone_cliente, String celular_cliente, String email) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone_cliente = telefone_cliente;
        this.celular_cliente = celular_cliente;
        this.email = email;
        this.produto = new Produto();
        this.marca = new MarcaProduto();
        this.categoria = new CategoriaProduto();
    }

    public Fornecedor(int cod_fornecedor, String nome, String cnpj, String inscricao_estadual, String rua, int numeroCasa, String bairro, String cidade, String estado, String cep, String telefone_cliente, String celular_cliente, String email, Produto produto, MarcaProduto marca, CategoriaProduto categoria) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone_cliente = telefone_cliente;
        this.celular_cliente = celular_cliente;
        this.email = email;
        this.produto = produto;
        this.marca = marca;
        this.categoria = categoria;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getCelular_cliente() {
        return celular_cliente;
    }

    public void setCelular_cliente(String celular_cliente) {
        this.celular_cliente = celular_cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public MarcaProduto getMarca() {
        return marca;
    }

    public void setMarca(MarcaProduto marca) {
        this.marca = marca;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

}
