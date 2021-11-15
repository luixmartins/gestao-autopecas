/* Nome do Pacote */
package DAO;

/* Importações*/
import MODEL.Cliente;
import MODEL.Contato;
import MODEL.Endereco;
import MODEL.ClienteFisica;
import MODEL.ClienteJuridica;
import MODEL.WebServiceCep;
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

    Endereco cliEndereco;
    Contato cliContato;
    ClienteJuridica cliJuridica;
    ClienteFisica cliFisica;

    /* Método para Salvar Pessoa Fisica */
    public void salvarFisica(Cliente cliente, ClienteFisica cliFisica, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
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
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }

        /* Inserindo Cliente */
        sql = "insert into cliente values (?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setInt(4, idContato);
        pst.setInt(5, idEndereco);
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs3 = pst.getGeneratedKeys();
        int idCliente = 0;
        if (rs3.next()) {
            idCliente = rs3.getInt(1);
        }

        /* Inserindo Pessoa Fisica */
        String sqlFisica;
        sqlFisica = "insert into clientefisica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlFisica);
        pst.setString(1, cliFisica.getCpf());
        pst.setString(2, cliFisica.getRg());
        pst.setInt(3, idCliente);
        pst.execute();

        pst.close();
    }

    /* Método para Salvar Pessoa Juridica */
    public void salvarJuridica(Cliente cliente, ClienteJuridica cliJuridica, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
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
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }

        /* Inserindo Cliente */
        sql = "insert into cliente values (?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setInt(4, idContato);
        pst.setInt(5, idEndereco);
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs3 = pst.getGeneratedKeys();
        int idCliente = 0;
        if (rs3.next()) {
            idCliente = rs3.getInt(1);
        }
        /* Inserindo Pessoa Fisica */
        String sqlJuridica;
        sqlJuridica = "insert into clientejuridica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlJuridica);
        pst.setString(1, cliJuridica.getCnpj());
        pst.setString(2, cliJuridica.getInscricao_estadual());
        pst.setInt(3, idCliente);
        pst.execute();

        pst.close();
    }

    /* Método para Excluir */
    public void excluir(Cliente cliente) throws SQLException {
        try {
            String deleta_cliente, deleta_contato, buscaDados, delete_endereco, deleta_fisica;
            buscaDados = "SELECT * FROM cliente where  cod_cliente = " + cliente.getCod_cliente();
            pst = Conexao.getInstance().prepareStatement(buscaDados);
            ResultSet rs = pst.executeQuery(buscaDados);
            int codEndereco = 0;
            int codContato = 0;

            while (rs.next()) {
                codContato = rs.getInt("clientecontato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }

            deleta_cliente = "delete from cliente where cod_cliente=" + cliente.getCod_cliente();;
            pst = Conexao.getInstance().prepareStatement(deleta_cliente);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }

    /* Método para Alterar Registros */
    public void alterar(Cliente cliente, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Update dados Cliente */

        try {
            String buscaDados;
            buscaDados = "SELECT * FROM cliente where  cod_cliente = " + cliente.getCod_cliente();
            pst = Conexao.getInstance().prepareStatement(buscaDados);
            ResultSet rs = pst.executeQuery(buscaDados);
            int codEndereco = 0;
            int codContato = 0;
            while (rs.next()) {
                codContato = rs.getInt("clientecontato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }
            sql = "UPDATE cliente,contato,endereco SET cliente.nome_cliente=?,endereco.rua_endereco=?,endereco.numero_endereco=?,endereco.bairro_endereco=?,endereco.cidade_endereco=?,endereco.estado_endereco=?,endereco.cep_endereco=?,contato.telefone_contato=?,contato.celular_contato=?,contato.email_contato=? where cliente.cod_cliente=? and endereco.cod_endereco=? and contato.cod_contato = ?";
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
            pst.setInt(12, codEndereco);
            pst.setInt(13, codContato);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }

    /* Lista para Buscar Cliente Fisica */
    public List<Cliente> listarClientesFisica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from clientefisica inner join cliente on cliente.cod_cliente = clientefisica.cliente_cod_cliente inner join contato on contato.cod_contato = cliente.clientecontato_cod_contato inner join endereco on endereco.cod_endereco = cliente.endereco_cod_endereco";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteFisica cliFisica = new ClienteFisica();
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

                cliente.setCliFisica(cliFisica);

                cliFisica.setCpf(rs.getString("cpf"));
                cliFisica.setRg(rs.getString("rg"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    /* Lista para Buscar Cliente Juridica */
    public List<Cliente> ListarClienteJuridica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from clientejuridica inner join cliente on cliente.cod_cliente = clientejuridica.cliente_cod_cliente inner join contato on contato.cod_contato = cliente.clientecontato_cod_contato inner join endereco on endereco.cod_endereco = cliente.endereco_cod_endereco";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteJuridica cliJuridica = new ClienteJuridica();
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

                cliente.setCliJuridica(cliJuridica);

                cliJuridica.setCnpj(rs.getString("cnpj"));
                cliJuridica.setInscricao_estadual(rs.getString("inscricao_estadual"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    //METODOS DE BUSCA POR DIGITAÇÃO
    public List<Cliente> buscaClientesFisica(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clientefisica on clientefisica.Cliente_cod_cliente = cliente.cod_cliente where nome_cliente like \"" + nome + "\"";

            pst = Conexao.getInstance().prepareStatement(sql);
            //pst.setString(0, nome);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteFisica cliFisica = new ClienteFisica();

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

                cliente.setCliFisica(cliFisica);

                cliFisica.setCpf(rs.getString("cpf"));
                cliFisica.setRg(rs.getString("rg"));

                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    /* Lista para Buscar Cliente Juridica */
    public List<Cliente> buscaClienteJuridica(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join clientecontato on clientecontato.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clienteendereco on clienteendereco.Cliente_cod_cliente = cliente.cod_cliente"
                    + " inner join clientejuridica on clientejuridica.Cliente_cod_cliente = cliente.cod_cliente where nome_cliente like \"" + nome + "\"";

            pst = Conexao.getInstance().prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteJuridica cliJuridica = new ClienteJuridica();

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

                cliente.setCliJuridica(cliJuridica);

                cliJuridica.setCnpj(rs.getString("cnpj"));
                cliJuridica.setInscricao_estadual(rs.getString("inscricao_estadual"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    //BUSCA DE CEP 
    public Endereco buscaCep(String cep) {
        try {
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            Endereco cliEndereco = new Endereco();

            if (webServiceCep.wasSuccessful()) {
                cliEndereco.setRua((webServiceCep.getLogradouroFull()));
                cliEndereco.setCidade((webServiceCep.getCidade()));
                cliEndereco.setEstado(webServiceCep.getUf());
                cliEndereco.setBairro(webServiceCep.getBairro());

                return cliEndereco;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao encontrar endereço! Insira manualmente.");

                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha no sistema! Contate o desenvolvedor.");
        }
        return null;
    }
}
