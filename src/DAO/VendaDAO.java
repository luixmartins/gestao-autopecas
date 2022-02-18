package DAO;

import MODEL.Cliente;
import MODEL.Funcionario;
import MODEL.ItensVenda;
import MODEL.Produto;
import MODEL.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class VendaDAO {

    PreparedStatement pst;
    String sql;

    /* Função para a Data */
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /* Método para Salvar uma Venda */
    public void SalvarVenda(Venda venda) throws SQLException {
        int idVenda = 0;
        sql = "INSERT INTO Venda (valor_total_venda, data_venda, "
                + " cliente_cod_cliente, Funcionario_idFuncionario) VALUES (?,?,?,?)";
        //pst.setInt(1, 0);
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, venda.getValor_total());
        System.out.println(getDateTime());
        pst.setString(2, getDateTime());
        pst.setInt(3, venda.getCliente().getCod_cliente());
        pst.setInt(4, venda.getVendedor().getCod_funcionario());
        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()) {
            idVenda = rs.getInt(1);
        }

        SalvarListaVenda(venda.getItens_venda(), idVenda);
        //pst.close();
    }

    /* Método para Salvar Itens Venda */
    public void SalvarListaVenda(List<ItensVenda> itensVenda, int idVenda) throws SQLException {
        try {
            for (ItensVenda itens : itensVenda) {
                sql = "INSERT INTO ItensVenda VALUES (?, ?, ? , ? , ? )";

                pst = Conexao.getInstance().prepareStatement(sql);

                pst.setInt(1, 0);
                pst.setInt(2, itens.getQuantidade());
                pst.setInt(3, itens.getProduto().getCod_produto());
                pst.setInt(4, idVenda);
                pst.setString(5, itens.getPreco_unitario());

                pst.execute();
                /* Realizando o Update da Quantidade */

                String busca = "select * from produto where cod_produto = " + itens.getProduto().getCod_produto();

                ResultSet rs = pst.executeQuery(busca);

                int quantidade = 0;

                while (rs.next()) {
                    quantidade = rs.getInt("quantidade");

                }

                if (itens.getQuantidade() <= quantidade) {
                    /* Subtraindo a quantidade*/
                    int subQuantidade = quantidade - itens.getQuantidade();
                    String atualiza;
                    /* Atualizando Quantidade */
                    atualiza = "UPDATE produto set quantidade = ? WHERE cod_Produto = ?";
                    pst = Conexao.getInstance().prepareStatement(atualiza);
                    pst.setInt(1, subQuantidade);
                    pst.setInt(2, itens.getProduto().getCod_produto());
                    pst.execute();
                    pst.close();
                    JOptionPane.showMessageDialog(null, "A venda foi salva com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Um dos produtos não contém a quantidade necessária de estoque para realizar a venda !", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
    }

    public int buscaCliente(String nome) throws SQLException {
        String sql = "select * from cliente where nome_cliente = \"" + nome + "\"";
        int cod = 0;

        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            cod = rs.getInt("cod_cliente");
        }

        return cod;
    }

    public int buscaVendedor(String nome) throws SQLException {
        String sql = "select * from Funcionario where nome_funcionario = \"" + nome + "\"";
        int cod = 0;

        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            cod = rs.getInt("idFuncionario");
        }

        return cod;
    }

    public int buscaProduto(String nome) throws SQLException {
        String sql = "select * from produto where descricao_produto = \"" + nome + "\"";

        int cod = 0;

        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            cod = rs.getInt("cod_Produto");
        }

        return cod;
    }

    public List<Venda> listarVendas() {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Funcionario funcionario = new Funcionario();
                Produto produto = new Produto();
                Cliente cliente = new Cliente();
                ItensVenda itensVenda = new ItensVenda();
                Venda venda = new Venda();
                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando Atributos Funcionário */
                funcionario.setCod_funcionario(rs.getInt("idFuncionario"));
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                /* Setando Produto */
                produto.setCodigo_barras(rs.getString("codigo_barras"));
                produto.setCod_produto(rs.getInt("cod_Produto"));
                produto.setDescricao(rs.getString("descricao_produto"));
                produto.setValor_venda(rs.getString("valor_venda"));
                /* Setando Atributos ItensVenda */
                itensVenda.setPreco_unitario(rs.getString("preco_total_itens"));
                itensVenda.setQuantidade(rs.getInt("quantidade_itens"));
                /* Setando Atributos Venda */
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setCliente(cliente);
                venda.setVendedor(funcionario);
                venda.setProduto(produto);
                venda.setItensVenda(itensVenda);
                //venda.setItens_venda((List<ItensVenda>) itensVenda);
                venda.setValor_total(rs.getString("valor_total_venda"));
                /* Adicionando dados na Lista */
                lista.add(venda);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    public void gerarDocumento() {
        try {

        } catch (Exception e) {
        }
    }
}
