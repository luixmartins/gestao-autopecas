/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Contato;
import MODEL.Endereco;
import MODEL.Funcionario;
import MODEL.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luizh
 */
public class FuncionarioDAO {
    
    PreparedStatement pst;
    
    Endereco funcEndereco;
    Contato funcContato;
    

    private String getDateTime() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            
            return dateFormat.format(date);
    }
    
    public void salvarFuncionario(Funcionario funcionario, Endereco end, Contato
                                    cont) throws SQLException{
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, end.getRua());
        pst.setInt(3, end.getNumero());
        pst.setString(4, end.getBairro());
        pst.setString(5, end.getCidade());
        pst.setString(6, end.getEstado());
        pst.setString(7, end.getCep());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int idEndereco = 0;
        if (rs.next()) {
            idEndereco = rs.getInt(1);
        }
        
        String sqlContato;
        sqlContato = "insert into contato values (?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cont.getTelefone_cliente());
        pst.setString(3, cont.getCelular_cliente());
        pst.setString(4, cont.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }
        
        String sqlFunc = "insert into funcionario values (?,?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlFunc, Statement.RETURN_GENERATED_KEYS);
        
        pst.setInt(1, 0);
        pst.setString(2,funcionario.getNome_funcionario());
        pst.setString(3, funcionario.getCpf_funcionario());
        pst.setString(4, funcionario.getRg_funcionario());
        pst.setString(5, funcionario.getSenha());
        pst.setString(6, getDateTime());
        pst.setInt(7, funcionario.getStatus());
        pst.setInt(8, funcionario.getNivel_acesso());
        pst.setInt(9, idContato);
        pst.setInt(10, idEndereco);
        
        pst.execute();
        pst.close();
    }
    
    public void excluirFuncionario(Funcionario funcionario) throws SQLException{
        try {
            String dltFuncionario = "delete from funcionario where idFuncionario = " +
                    funcionario.getCod_funcionario();
            
            pst = Conexao.getInstance().prepareStatement(dltFuncionario);
            pst.execute();
            
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void alterarFuncionario(Funcionario func, Endereco end, Contato cont) throws SQLException{
        try {
            String getData;
            
            getData = "select * from funcionario where idFuncionario = " + 
                    func.getCod_funcionario();
            
            pst = Conexao.getInstance().prepareStatement(getData);
            ResultSet rs = pst.executeQuery(getData);
            
            int codEndereco = 0, codContato = 0;
            
            while(rs.next()){
                codContato = rs.getInt("contato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }
            
            String update = "update funcionario, contato, endereco set "
                    + "nome_funcionario = ?,"
                    + "cpf_funcionario = ?,"
                    + "rg_funcionario = ?,"
                    + "senha = ?,"
                    + "status = ?,"
                    + "nivel_acesso = ?,"
                    + "endereco.rua_endereco = ?,"
                    + "endereco.numero_endereco = ?,"
                    + "endereco.bairro_endereco = ?,"
                    + "endereco.cidade_endereco = ?,"
                    + "endereco.estado_endereco = ?,"
                    + "endereco.cep_endereco = ?,"
                    + "contato.telefone_contato = ?,"
                    + "contato.celular_contato = ?,"
                    + "contato.email_contato = ?"
                    + " where funcionario.idFuncionario = ? "
                    + "and endereco.cod_endereco = ? "
                    + "and contato.cod_contato = ?";
            
            pst = Conexao.getInstance().prepareCall(update);
            
            pst.setString(1, func.getNome_funcionario());
            pst.setString(2, func.getCpf_funcionario());
            pst.setString(3, func.getRg_funcionario());
            pst.setString(4, func.getSenha());
            pst.setInt(5, func.getStatus());
            pst.setInt(6, func.getNivel_acesso());
            
            pst.setString(7, end.getRua());
            pst.setInt(8, end.getNumero());
            pst.setString(9, end.getBairro());
            pst.setString(10, end.getCidade());
            pst.setString(11, end.getEstado());
            pst.setString(12, end.getCep());
            
            pst.setString(13, cont.getTelefone_cliente());
            pst.setString(14, cont.getCelular_cliente());
            pst.setString(15, cont.getEmail());
            
            pst.setInt(16, func.getCod_funcionario());
            pst.setInt(17, codEndereco);
            pst.setInt(18, codContato);
            
            pst.execute();
            
            pst.close();
            
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<Funcionario> listarFuncionarios() throws ParseException{
        try {
            List<Funcionario> lista = new ArrayList<>();
        
            String sql = "select * from funcionario " +
                    "inner join contato on contato.cod_contato = funcionario.contato_cod_contato " +
                    "inner join endereco on endereco.cod_endereco = funcionario.endereco_cod_endereco";
            
            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Funcionario func = new Funcionario();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));
                
                func.setNome_funcionario(rs.getString("nome_funcionario"));
                func.setCpf_funcionario(rs.getString("cpf_funcionario"));
                func.setRg_funcionario(rs.getString("rg_funcionario"));
                func.setSenha(rs.getString("senha"));
                
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rs.getString("data_admissao"));  
                func.setData_admissao(date);
                
                func.setStatus(rs.getInt("status"));
                func.setNivel_acesso(rs.getInt("nivel_acesso"));
                func.setCod_funcionario(rs.getInt("idFuncionario"));
                
                func.setFuncContato(cliContato);
                func.setFuncEndereco(cliEndereco);
                
                lista.add(func);
            }
            return lista;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Funcionario> buscarFuncionarios(String text) throws ParseException{
        try {
            List<Funcionario> lista = new ArrayList<>();
        
            String sql = "select * from funcionario " +
                    "inner join contato on contato.cod_contato = funcionario.contato_cod_contato " +
                    "inner join endereco on endereco.cod_endereco = funcionario.endereco_cod_endereco"
                    + " where nome_funcionario like \"" + text + "\"";
            
            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Funcionario func = new Funcionario();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));
                
                func.setNome_funcionario(rs.getString("nome_funcionario"));
                func.setCpf_funcionario(rs.getString("cpf_funcionario"));
                func.setRg_funcionario(rs.getString("rg_funcionario"));
                func.setSenha(rs.getString("senha"));
                
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rs.getString("data_admissao"));  
                func.setData_admissao(date);
                
                func.setStatus(rs.getInt("status"));
                func.setNivel_acesso(rs.getInt("nivel_acesso"));
                func.setCod_funcionario(rs.getInt("idFuncionario"));
                
                func.setFuncContato(cliContato);
                func.setFuncEndereco(cliEndereco);
                
                
                lista.add(func);
            }
            return lista;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Funcionario> buscarFuncionariosCodigo(String cod) throws ParseException{
        try {
            List<Funcionario> lista = new ArrayList<>();
        
            String sql = "select * from funcionario " +
                    "inner join contato on contato.cod_contato = funcionario.contato_cod_contato " +
                    "inner join endereco on endereco.cod_endereco = funcionario.endereco_cod_endereco"
                    + " where idFuncionario = " + cod;
            
            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Funcionario func = new Funcionario();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));
                
                func.setNome_funcionario(rs.getString("nome_funcionario"));
                func.setCpf_funcionario(rs.getString("cpf_funcionario"));
                func.setRg_funcionario(rs.getString("rg_funcionario"));
                func.setSenha(rs.getString("senha"));
                
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rs.getString("data_admissao"));  
                func.setData_admissao(date);
                
                func.setStatus(rs.getInt("status"));
                func.setNivel_acesso(rs.getInt("nivel_acesso"));
                func.setCod_funcionario(rs.getInt("idFuncionario"));
                
                func.setFuncContato(cliContato);
                func.setFuncEndereco(cliEndereco);
                
                lista.add(func);
            }
            return lista;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
