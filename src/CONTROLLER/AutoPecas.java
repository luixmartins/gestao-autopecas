package CONTROLLER;

import VIEW.LoginVIEW;
import java.sql.SQLException;
/*
import DAO.VendaDAO;
import MODEL.Cliente;
import MODEL.Funcionario;
import MODEL.ItensVenda;
import MODEL.Produto;
import MODEL.Venda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
*/
public class AutoPecas {

    public static void main(String[] args) throws SQLException {

        LoginVIEW login = new LoginVIEW();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        /*
        VendaDAO vendadao = new VendaDAO();
        Venda venda = new Venda();
        ItensVenda itensVenda = new ItensVenda();
        Produto produto = new Produto();
        Funcionario funcionario = new Funcionario();
        Cliente cliente = new Cliente();

        String sDate1 = "2021-12-05 15:00:15";
        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sDate1);
        venda.setValor_total("8500,20");
        venda.setData_venda(date1);
        cliente.setCod_cliente(1);
        venda.setCliente(cliente);
        funcionario.setCod_funcionario(2);
        venda.setVendedor(funcionario);

        produto.setCod_produto(4);
        List<ItensVenda> itens = new ArrayList<>();
        itensVenda.setQuantidade(2);
        itensVenda.setProduto(produto);
        itensVenda.setPreco_unitario("250.00");

        
        
        itens.add(itensVenda);

        venda.setItens_venda(itens);

        vendadao.SalvarVenda(venda);
//vendadao.SalvarListaVenda(itens, 1);*/
    }
}
