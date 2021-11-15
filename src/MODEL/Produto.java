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
public class Produto{

    private int cod_produto;
    private String descricao;
    private int quantidade;
    private int quantidadeMinima;
    private int codigo_barras;
    private String valor_custo;
    private String valor_venda;

    private MarcaProduto marca;
    private CategoriaProduto categoria;

    public Produto() {

        this.marca = new MarcaProduto();
        this.categoria = new CategoriaProduto();
    }

    public Produto(int cod_produto, String descricao, int quantidade, int quantidadeMinima, int codigo_barras, String valor_custo, String valor_venda) {
        this.cod_produto = cod_produto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.codigo_barras = codigo_barras;
        this.valor_custo = valor_custo;
        this.valor_venda = valor_venda;
        this.marca = new MarcaProduto();
        this.categoria = new CategoriaProduto();

    }

    public Produto(int cod_produto, String descricao, int quantidade, int quantidadeMinima, int codigo_barras, String valor_custo, String valor_venda, MarcaProduto marca, CategoriaProduto categoria) {
        this.cod_produto = cod_produto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.codigo_barras = codigo_barras;
        this.valor_custo = valor_custo;
        this.valor_venda = valor_venda;
        this.marca = marca;
        this.categoria = categoria;
    }

    
    
    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(int codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getValor_custo() {
        return valor_custo;
    }

    public void setValor_custo(String valor_custo) {
        this.valor_custo = valor_custo;
    }

    public String getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(String valor_venda) {
        this.valor_venda = valor_venda;
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
