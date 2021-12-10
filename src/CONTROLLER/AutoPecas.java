package CONTROLLER;

import DAO.EntradaDAO;
import MODEL.Entrada;
import MODEL.Fornecedor;
import MODEL.ItensEntrada;
import MODEL.Produto;
import VIEW.LoginVIEW;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoPecas {

    public static void main(String[] args) throws SQLException, ParseException {

        LoginVIEW login = new LoginVIEW();
        login.setVisible(true);
        login.setLocationRelativeTo(null);/*
        EntradaDAO entradadao = new EntradaDAO();
        Entrada entrada = new Entrada();
        ItensEntrada itensEntrada = new ItensEntrada();
        
        Produto produto = new Produto();
        Fornecedor fornecedor = new Fornecedor();

        String sDate1 = "2021-12-05 15:00:15";
        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sDate1);

        entrada.setNumero_nota("20");
        entrada.setChave_acesso("123123312");
        entrada.setValor_total_nota("8500,20");
        entrada.setData_entrada(date1);
        
        fornecedor.setCod_fornecedor(1);
        produto.setCod_produto(2);
        
        List<ItensEntrada> itens = new ArrayList<>();
        
        itensEntrada.setPreco_unitario("250.00");
        itensEntrada.setProduto(produto);
        itensEntrada.setQuantidade(30);
        
        itens.add(itensEntrada);
        
        
        entrada.setFornecedor(fornecedor);
        entrada.setItens_entrada(itens);
        
        
        
        
        entradadao.SalvarEntrada(entrada);
        //entradadao.SalvarListaEntrada(itens, 1);*/
    }
}
