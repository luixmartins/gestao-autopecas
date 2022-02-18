package MODEL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {

    private int id_entrada;
    private List<ItensVenda> itens_venda;
    private Cliente cliente;
    private Funcionario vendedor;
    private String valor_total;
    private Date data_venda;
    private Produto produto;
    private ItensVenda itensVenda;
    

    public Venda() {
        this.itens_venda = new ArrayList<>();
    }

    public Venda(int id_entrada, List<ItensVenda> itens_venda, Cliente cliente, Funcionario vendedor, String valor_total, Date data_venda, Produto produto, ItensVenda itensVenda) {
        this.id_entrada = id_entrada;
        this.itens_venda = itens_venda;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.valor_total = valor_total;
        this.data_venda = data_venda;
        this.produto = produto;
        this.itensVenda = itensVenda;
    }

    public Venda(int id_entrada, List<ItensVenda> itens_venda, Cliente cliente, Funcionario vendedor, String valor_total, Date data_venda) {
        this.id_entrada = id_entrada;
        this.itens_venda = itens_venda;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.valor_total = valor_total;
        this.data_venda = data_venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public List<ItensVenda> getItens_venda() {
        return itens_venda;
    }

    public void setItens_venda(List<ItensVenda> itens_venda) {
        this.itens_venda = itens_venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public String getValor_total() {
        return valor_total;
    }

    public void setValor_total(String valor_total) {
        this.valor_total = valor_total;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

}
