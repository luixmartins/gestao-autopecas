/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danis
 */
public class Entrada {
    private int id_entrada;
    private List<ItensEntrada> itens_entrada;
    private Fornecedor fornecedor;
    private String numero_nota;
    private String chave_acesso;
    private String valor_total_nota;
    private Date data_entrada;

    public Entrada(int id_entrada, List<ItensEntrada> itens_entrada, Fornecedor fornecedor, String numero_nota, String chave_acesso, String valor_total_nota, Date data_entrada) {
        this.id_entrada = id_entrada;
        this.itens_entrada = itens_entrada;
        this.fornecedor = fornecedor;
        this.numero_nota = numero_nota;
        this.chave_acesso = chave_acesso;
        this.valor_total_nota = valor_total_nota;
        this.data_entrada = data_entrada;
    }

    public Entrada() {
        this.itens_entrada = new ArrayList<>();
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public List<ItensEntrada> getItens_entrada() {
        return itens_entrada;
    }

    public void setItens_entrada(List<ItensEntrada> itens_entrada) {
        this.itens_entrada = itens_entrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumero_nota() {
        return numero_nota;
    }

    public void setNumero_nota(String numero_nota) {
        this.numero_nota = numero_nota;
    }

    public String getChave_acesso() {
        return chave_acesso;
    }

    public void setChave_acesso(String chave_acesso) {
        this.chave_acesso = chave_acesso;
    }

    public String getValor_total_nota() {
        return valor_total_nota;
    }

    public void setValor_total_nota(String valor_total_nota) {
        this.valor_total_nota = valor_total_nota;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }
}
