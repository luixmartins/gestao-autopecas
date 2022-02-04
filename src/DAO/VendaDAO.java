package DAO;


import MODEL.ItensVenda;
import MODEL.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


                /* Subtraindo a quantidade*/
                
                
                int subQuantidade = quantidade - itens.getQuantidade() ;
                String atualiza;
                /* Atualizando Quantidade */
                atualiza = "UPDATE produto set quantidade = ? WHERE cod_Produto = ?";
                pst = Conexao.getInstance().prepareStatement(atualiza);
                pst.setInt(1, subQuantidade);
                pst.setInt(2, itens.getProduto().getCod_produto());
                pst.execute();
                pst.close();

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
        String sql = "select * from produto where descricao_produto = \"" + nome + "\"and quantidade >= quantidade_minima";
        
        int cod = 0;

        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            cod = rs.getInt("cod_Produto");
        }

        return cod;
    }
}
