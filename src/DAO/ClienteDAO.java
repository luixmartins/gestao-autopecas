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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/* Classe ClienteDAO */
public class ClienteDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    ClienteEndereco cliEndereco;
    ClienteContato cliContato;
    ClienteJuridica cliJuridica;
    ClienteFisica cliFisica;

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

    /* Método para Excluir */
    public void excluir(Cliente cliente) throws SQLException {
        String deleta_cliente;
        deleta_cliente = "delete from cliente where cod_cliente=?";
        pst = Conexao.getInstance().prepareStatement(deleta_cliente);
        pst.setInt(1, cliente.getCod_cliente());
        pst.execute();
        pst.close();
    }

    /* Método para Alterar Registros */
    public void alterar(Cliente cliente, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        /* Update dados Cliente */
        sql = "UPDATE cliente,clientecontato,clienteendereco SET cliente.nome_cliente=?,clienteendereco.rua_endereco=?,clienteendereco.numero_endereco=?,clienteendereco.bairro_endereco=?,clienteendereco.cidade_endereco=?,clienteendereco.estado_endereco=?,clienteendereco.cep_endereco=?,clientecontato.telefone_contato=?,clientecontato.celular_contato=?,clientecontato.email_contato=? where cliente.cod_cliente=? and clienteendereco.Cliente_cod_cliente=? and clientecontato.Cliente_cod_cliente=?";
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
        pst.setInt(11, cliente.getCod_cliente());
        pst.setInt(12, cliente.getCod_cliente());
        pst.setInt(13, cliente.getCod_cliente());
        pst.execute();
        pst.close();
    }

    /* Lista para Buscar Cliente Fisica */
    public List<Cliente> listarClientesFisica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clientefisica on clientefisica.Cliente_cod_cliente = cliente.cod_cliente where cliente.tipo_cliente = 0";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                ClienteContato cliContato = new ClienteContato();
                ClienteEndereco cliEndereco = new ClienteEndereco();
                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro");
        }

        return null;
    }

    /* Lista para Buscar Cliente Juridica */
    public List<Cliente> ListarClienteJuridica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clientejuridica on clientejuridica.Cliente_cod_cliente = cliente.cod_cliente where cliente.tipo_cliente = 1 ";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                ClienteContato cliContato = new ClienteContato();
                ClienteEndereco cliEndereco = new ClienteEndereco();
                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro");
        }

        return null;
    }

}
