/* Nome do Pacote */
package DAO;

/* Importações*/
import MODEL.Cliente;
import MODEL.ClienteContato;
import MODEL.ClienteEndereco;
import MODEL.ClienteFisica;
import MODEL.ClienteJuridica;
/* Importações do SQL */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Classe ClienteDAO */
public class ClienteDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;
    
    ClienteEndereco cliEndereco;
    ClienteContato cliContato;

    /* Método para Salvar Pessoa Fisica */
    public void salvarFisica(Cliente cliente, ClienteFisica cliFisica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "insert into cliente values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into clienteendereco values (?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.setInt(8, id);
        pst.execute();
        /* Inserindo o Contato */
        String sqlContato;
        sqlContato = "insert into clientecontato values (?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato);
        pst.setInt(1, 0);
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.setInt(5, id);
        pst.setInt(6, id);
        pst.execute();
        /* Inserindo Pessoa Fisica */
        String sqlFisica;
        sqlFisica = "insert into clientefisica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlFisica);
        pst.setString(1, cliFisica.getCpf());
        pst.setString(2, cliFisica.getRg());
        pst.setInt(3, id);
        pst.execute();
        pst.close();
    }

    /* Método para Salvar Pessoa Juridica */
    public void salvarJuridica(Cliente cliente, ClienteJuridica cliJuridica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "insert into cliente values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into clienteendereco values (?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.setInt(8, id);
        pst.execute();
        /* Inserindo o Endereço */
        String sqlContato;
        sqlContato = "insert into clientecontato values (?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato);
        pst.setInt(1, 0);
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.setInt(5, id);
        pst.setInt(6, id);
        pst.execute();
        /* Inserindo Pessoa Fisica */
        String sqlJuridica;
        sqlJuridica = "insert into clientejuridica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlJuridica);
        pst.setString(1, cliJuridica.getCnpj());
        pst.setString(2, cliJuridica.getInscricao_estadual());
        pst.setInt(3, id);
        pst.execute();
        pst.close();
    }

    /* Método para Recuperar um Registro*/
    public Cliente buscarFisica(int codigo) throws SQLException {
        sql = "select * from cliente" +
" inner join clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente" +
" inner join clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente" +
" inner join clientefisica on clientefisica.Cliente_cod_cliente = cliente.cod_cliente "
                + "where cliente.cod_cliente = " + codigo;
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        Cliente cli = null;

        while (rs.next()) {
            cliContato = new ClienteContato();
            cliContato.setCelular_cliente(rs.getString("celular_contato"));
            cliContato.setEmail(rs.getString("email_contato"));
            cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
            
            //Endereco
            //Fisica 
            
            cli = new Cliente(rs.getInt("cod_cliente"), rs.getString("nome_cliente"), rs.getInt("tipo_cliente"), cliContato);
        }
        pst.close();
        return cli;
    }

    public Cliente buscarJuridica(int codigo) throws SQLException {
        sql = "SELECT  cliente.cod_cliente,cliente.nome_cliente,  cliente.tipo_cliente, "
                + "clienteendereco.rua_endereco, "
                + "clienteendereco.numero_endereco, "
                + "clienteendereco.bairro_endereco, "
                + "clienteendereco.cidade_endereco,clienteendereco.estado_endereco,clienteendereco.cep_endereco,"
                + "clientecontato.telefone_contato,clientecontato.celular_contato,clientecontato.email_contato,"
                + "clientejuridica.cnpj, "
                + "clientejuridica.inscricao_estadual"
                + "FROM cliente"
                + "INNER JOIN clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente"
                + "INNER JOIN clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente"
                + "INNER JOIN clientejuridica on clientejuridica.Cliente_cod_cliente = cliente.cod_cliente WHERE cliente.cod_cliente = " + codigo;
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        Cliente cli = null;

        while (rs.next()) {
            cli = new Cliente(rs.getInt("cod_cliente"), rs.getString("nome_cliente"), rs.getInt("tipo_cliente"), cli.getCliFisica(), cli.getCliJuridica(), cli.getCliEndereco(), cli.getCliContato());
        }
        pst.close();
        return cli;
    }

    /* Método para Excluir */
    public void excluirFisica(Cliente cliente) throws SQLException {
        sql = "delete from clientefisica where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();

        String deleta_endereco;
        deleta_endereco = "delete from clienteendereco where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_endereco);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();

        String deleta_contato;
        deleta_contato = "delete from clientecontato where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_contato);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();
        
        String deleta_cliente;
        deleta_cliente = "delete from cliente where cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_cliente);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();
        pst.close();
    }

    public void excluirJuridica(Cliente cliente) throws SQLException {
        sql = "delete from clientejuridica where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();

        String deleta_endereco;
        deleta_endereco = "delete from clienteendereco where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_endereco);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();

        String deleta_contato;
        deleta_contato = "delete from clientecontato where Cliente_cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_contato);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();
        
        String deleta_cliente;
        deleta_cliente = "delete from cliente where cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_cliente);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();
        pst.close();
    }

    /* Método para Alterar Registros */
    public void alterarFisica(Cliente cliente, ClienteFisica cliFisica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "UPDATE cliente, clientecontato, clienteendereco, clientefisica"
                + "SET cliente.nome_cliente = ?,"
                + "clienteendereco.rua_endereco = ?, clienteendereco.numero_endereco = ?,"
                + "clienteendereco.bairro_endereco = ? , clienteendereco.cidade_endereco = ? ,clienteendereco.estado_endereco = ? ,clienteendereco.cep_endereco = ? ,clientecontato.telefone_contato = ? ,clientecontato.celular_contato = ? ,clientecontato.email_contato = ? ,"
                + "clientefisica.cpf= ? , clientefisica.rg = ? where cliente.cod_cliente = ;";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, cliente.getNome_cliente());
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.setString(8, cliContato.getTelefone_cliente());
        pst.setString(9, cliContato.getCelular_cliente());
        pst.setString(10, cliContato.getEmail());
        pst.setString(11, cliFisica.getCpf());
        pst.setString(12, cliFisica.getRg());
        pst.execute();
        pst.close();
    }

    public void alterarJuridica(Cliente cliente, ClienteJuridica cliJuridica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "UPDATE cliente, clientecontato, clienteendereco, clientejuridica"
                + "SET cliente.nome_cliente = ?, "
                + "clienteendereco.rua_endereco = ?, clienteendereco.numero_endereco = ?,"
                + "clienteendereco.bairro_endereco = ? , clienteendereco.cidade_endereco =? ,clienteendereco.estado_endereco =? ,clienteendereco.cep_endereco =? ,clientecontato.telefone_contato =? ,clientecontato.celular_contato =? ,clientecontato.email_contato =? ,"
                + "clientejuridica.cnpj=? , clientejuridica.inscricao_estadual = ? where cliente.cod_cliente = ;";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, cliente.getNome_cliente());
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.setString(8, cliContato.getTelefone_cliente());
        pst.setString(9, cliContato.getCelular_cliente());
        pst.setString(10, cliContato.getEmail());
        pst.setString(11, cliJuridica.getCnpj());
        pst.setString(12, cliJuridica.getInscricao_estadual());
        pst.execute();
        pst.close();
    }

}
