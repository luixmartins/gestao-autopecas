package DAO;

/* Importando Fornecedor */
import MODEL.Contato;
import MODEL.Endereco;
import MODEL.Fornecedor;
/* Importações do SQL */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedorDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;
    /* Importação de Classes */
    Fornecedor fornecedor;
    Endereco endFor;
    Contato contatoFor;

    /* Salvar Fornecedor */
    
    public void salvarFornecedor(Fornecedor fornecedor, Endereco endFor, Contato contatoFor) throws SQLException {
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, endFor.getRua());
        pst.setInt(3, endFor.getNumero());
        pst.setString(4, endFor.getBairro());
        pst.setString(5, endFor.getCidade());
        pst.setString(6, endFor.getEstado());
        pst.setString(7, endFor.getCep());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int idEndereco = 0;
        if (rs.next()) {
            idEndereco = rs.getInt(1);
        }
        /* Inserindo o Contato */
        String sqlContato;
        sqlContato = "insert into contato values (?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, contatoFor.getTelefone_cliente());
        pst.setString(3, contatoFor.getCelular_cliente());
        pst.setString(4, contatoFor.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }
        /* Inserindo Fornecedor  */
        sql = "insert into fornecedor values (?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, fornecedor.getNome());
        pst.setString(3, fornecedor.getCnpj());
        pst.setString(4, fornecedor.getInscricao_estadual());
        pst.setInt(5, idEndereco);
        pst.setInt(6, idContato);
        pst.execute();
        pst.close();
    }

    /* Excluir Fornecedor */
    
    public void excluirFornecedor(Fornecedor fornecedor) throws SQLException {
        try {
            String deleta_fornecedor;
            deleta_fornecedor = "delete from fornecedor where idFornecedor=" + fornecedor.getCod_fornecedor();
            pst = Conexao.getInstance().prepareStatement(deleta_fornecedor);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }

    /* Alterar Fornecedor */
    
    public void alterarFornecedor(Fornecedor fornecedor, Endereco endereco, Contato contato) throws SQLException {
        try {
            String buscaDados;
            buscaDados = "SELECT * FROM fornecedor where  idFornecedor = " + fornecedor.getCod_fornecedor();
            pst = Conexao.getInstance().prepareStatement(buscaDados);
            ResultSet rs = pst.executeQuery(buscaDados);
            int codEndereco = 0;
            int codContato = 0;
            while (rs.next()) {
                codContato = rs.getInt("contato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }
            sql = "UPDATE fornecedor,contato,endereco SET fornecedor.razao_social=?, fornecedor.cnpj=?, fornecedor.inscricao_estadual=?, endereco.rua_endereco=?,endereco.numero_endereco=?,endereco.bairro_endereco=?,endereco.cidade_endereco=?,endereco.estado_endereco=?,endereco.cep_endereco=?,contato.telefone_contato=?,contato.celular_contato=?,contato.email_contato=? where fornecedor.idFornecedor=? and endereco.cod_endereco=? and contato.cod_contato = ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getCnpj());
            pst.setString(3, fornecedor.getInscricao_estadual());
            pst.setString(4, endereco.getRua());
            pst.setInt(5, endereco.getNumero());
            pst.setString(6, endereco.getBairro());
            pst.setString(7, endereco.getCidade());
            pst.setString(8, endereco.getEstado());
            pst.setString(9, endereco.getCep());
            pst.setString(10, contato.getTelefone_cliente());
            pst.setString(11, contato.getCelular_cliente());
            pst.setString(12, contato.getEmail());
            pst.setInt(13, fornecedor.getCod_fornecedor());
            pst.setInt(14, codEndereco);
            pst.setInt(15, codContato);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }
    
    /* Consultar Fornecedor */
    
    public List<Fornecedor> listarFornecedores() {
        try {
            List<Fornecedor> lista = new ArrayList<>();

            String sql = "select * from fornecedor inner join contato on contato.cod_contato = fornecedor.contato_cod_contato inner join endereco on endereco.cod_endereco = fornecedor.endereco_cod_endereco";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Fornecedor fornecedor = new Fornecedor();
                Contato contato = new Contato();
                Endereco endereco = new Endereco();
                /* Setando Atributos Cliente */
                fornecedor.setCod_fornecedor(rs.getInt("idFornecedor"));
                fornecedor.setNome(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setInscricao_estadual(rs.getString("inscricao_estadual"));                
                /* Setando Endereço */
                fornecedor.setEndFor(endereco);
                /* Setando Atributos CliEndereco */
                endereco.setRua(rs.getString("rua_endereco"));
                endereco.setNumero(rs.getInt("numero_endereco"));
                endereco.setBairro(rs.getString("bairro_endereco"));
                endereco.setCidade(rs.getString("cidade_endereco"));
                endereco.setEstado(rs.getString("estado_endereco"));
                endereco.setCep(rs.getString("cep_endereco"));
                /* Setando CliContato*/
                fornecedor.setContatoFor(contato);
                /* Setando Atributos Contato */
                contato.setCelular_cliente(rs.getString("celular_contato"));
                contato.setTelefone_cliente(rs.getString("telefone_contato"));
                contato.setEmail(rs.getString("email_contato"));
                
                /* Adicionando dados na Lista */                
                lista.add(fornecedor);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }
    
    
    public List<Fornecedor> listarFornecedores(String nome) {
        try {
            List<Fornecedor> lista = new ArrayList<>();

            String sql = "select * from fornecedor inner join contato on contato.cod_contato = fornecedor.contato_cod_contato inner join endereco on endereco.cod_endereco = fornecedor.endereco_cod_endereco where razao_social like \"" + nome + "\"";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Fornecedor fornecedor = new Fornecedor();
                Contato contato = new Contato();
                Endereco endereco = new Endereco();
                /* Setando Atributos Cliente */
                fornecedor.setCod_fornecedor(rs.getInt("idFornecedor"));
                fornecedor.setNome(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setInscricao_estadual(rs.getString("inscricao_estadual"));                
                /* Setando Endereço */
                fornecedor.setEndFor(endereco);
                /* Setando Atributos CliEndereco */
                endereco.setRua(rs.getString("rua_endereco"));
                endereco.setNumero(rs.getInt("numero_endereco"));
                endereco.setBairro(rs.getString("bairro_endereco"));
                endereco.setCidade(rs.getString("cidade_endereco"));
                endereco.setEstado(rs.getString("estado_endereco"));
                endereco.setCep(rs.getString("cep_endereco"));
                /* Setando CliContato*/
                fornecedor.setContatoFor(contato);
                /* Setando Atributos Contato */
                contato.setCelular_cliente(rs.getString("celular_contato"));
                contato.setTelefone_cliente(rs.getString("telefone_contato"));
                contato.setEmail(rs.getString("email_contato"));
                
                /* Adicionando dados na Lista */                
                lista.add(fornecedor);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

}
