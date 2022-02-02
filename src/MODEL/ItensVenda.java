package MODEL;

public class ItensVenda {

    private int idItens_venda;
    private int quantidade;
    private Produto produto;
    private String preco_unitario;

    public ItensVenda() {
        this.produto = new Produto();
    }

    public ItensVenda(int idItens_venda, int quantidade, Produto produto, String preco_unitario) {
        this.idItens_venda = idItens_venda;
        this.quantidade = quantidade;
        this.produto = produto;
        this.preco_unitario = preco_unitario;
    }

    public int getIdItens_venda() {
        return idItens_venda;
    }

    public void setIdItens_venda(int idItens_venda) {
        this.idItens_venda = idItens_venda;
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
