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

/* Classe ClienteDAO */
public class ClienteDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    /* Método para Salvar Pessoa Fisica */
    public void salvarFisica(Cliente cliente, ClienteFisica cliFisica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "insert into cliente (cod_cliente,nome_cliente,tipo_cliente,rua_endereco,"
                + "numero_endereco,bairro_endereco,cidade_endereco,estado_endereco,cep_endereco,"
                + "telefone_contato,celular_contato,email_contato,cpf,rg)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setString(4, cliEnd.getRua());
        pst.setInt(5, cliEnd.getNumero());
        pst.setString(6, cliEnd.getBairro());
        pst.setString(7, cliEnd.getCidade());
        pst.setString(8, cliEnd.getEstado());
        pst.setInt(9, cliEnd.getCep());
        pst.setInt(10, cliContato.getTelefone_cliente());
        pst.setInt(11, cliContato.getCelular_cliente());
        pst.setString(12, cliContato.getEmail());
        pst.setString(13, cliFisica.getCpf());
        pst.setString(14, cliFisica.getRg());
        pst.execute();
        pst.close();
    }

    /* Método para Salvar Pessoa Juridica */
    public void salvarJuridica(Cliente cliente, ClienteJuridica cliJuridica, ClienteEndereco cliEnd, ClienteContato cliContato) throws SQLException {
        sql = "insert into cliente (cod_cliente,nome_cliente,tipo_cliente,rua_endereco,"
                + "numero_endereco,bairro_endereco,cidade_endereco,estado_endereco,cep_endereco,"
                + "telefone_contato,celular_contato,email_contato,cnpj,inscricao_estadual)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setString(4, cliEnd.getRua());
        pst.setInt(5, cliEnd.getNumero());
        pst.setString(6, cliEnd.getBairro());
        pst.setString(7, cliEnd.getCidade());
        pst.setString(8, cliEnd.getEstado());
        pst.setInt(9, cliEnd.getCep());
        pst.setInt(10, cliContato.getTelefone_cliente());
        pst.setInt(11, cliContato.getCelular_cliente());
        pst.setString(12, cliContato.getEmail());
        pst.setString(13, cliJuridica.getCnpj());
        pst.setString(14, cliJuridica.getInscricao_estadual());
        pst.execute();
        pst.close();
    }
    /* Método para Recuperar um Registro*/
 /* Método para Excluir */
 /* Método para Alterar Registros */
}
