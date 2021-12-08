/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author danis
 */
public class ItensEntrada {
   private int id_itens_entrada;
   private int quantidade;
   private Produto produto;
   private String preco_unitario;

    public ItensEntrada() {
        this.produto = new Produto();
    }

    public ItensEntrada(int id_itens_entrada, int quantidade, Produto produto, String preco_unitario) {
        this.id_itens_entrada = id_itens_entrada;
        this.quantidade = quantidade;
        this.produto = new Produto();
        this.preco_unitario = preco_unitario;
        
    }

    public int getId_itens_entrada() {
        return id_itens_entrada;
    }

    public void setId_itens_entrada(int id_itens_entrada) {
        this.id_itens_entrada = id_itens_entrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(String preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}
