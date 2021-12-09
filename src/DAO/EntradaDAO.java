package DAO;

import MODEL.Entrada;
import MODEL.ItensEntrada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class EntradaDAO {

    PreparedStatement pst;
    String sql;

    /* Função para a Data */
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /* Método para Salvar uma Entrada */
    public void SalvarEntrada(Entrada entrada) throws SQLException {
        int idEntrada = 0;
        sql = "INSERT INTO entrada (numero_nota, chave_acesso, valor_total_nota, "
                + " data_entrada, Fornecedor_idFornecedor) VALUES (?,?,?,?,?)";
        //pst.setInt(1, 0);
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, entrada.getNumero_nota());
        pst.setString(2, entrada.getChave_acesso());
        pst.setString(3, entrada.getValor_total_nota());

        System.out.println(getDateTime());
        pst.setString(4, getDateTime());
        pst.setInt(5, entrada.getFornecedor().getCod_fornecedor());
        pst.execute();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()) {
            idEntrada = rs.getInt(1);
        }

        SalvarListaEntrada(entrada.getItens_entrada(), idEntrada);
        //pst.close();
    }

    /* Método para Salvar Itens Entrada */
    public void SalvarListaEntrada(List<ItensEntrada> itensEntrada, int idEntrada) throws SQLException {
        try {
            for (ItensEntrada itens : itensEntrada) {
                sql = "INSERT INTO item_entrada VALUES (?, ?, ? , ? , ? )";

                pst = Conexao.getInstance().prepareStatement(sql);

                pst.setInt(1, 0);
                pst.setInt(2, itens.getQuantidade());
                pst.setString(3, itens.getPreco_unitario());
                pst.setInt(4, idEntrada);
                pst.setInt(5, itens.getProduto().getCod_produto());
                pst.execute();
                /* Realizando o Update da Quantidade e Preço */
                //.close();
                String busca = "select * from produto where cod_produto = " + itens.getProduto().getCod_produto();

                ResultSet rs = pst.executeQuery(busca);

                int quantidade = 0;
                String pct = null;
                String valor_venda = null;
                while (rs.next()) {
                    quantidade = rs.getInt("quantidade");
                    pct = rs.getString("pct_lucro");
                    valor_venda = rs.getString("valor_venda");
                }
                float CalculoVenda = (Float.parseFloat(itens.getPreco_unitario()) * (Float.parseFloat(pct) / 100)) + Float.parseFloat(itens.getPreco_unitario());
                DecimalFormat df = new DecimalFormat("#.00");

                /* Fazendo Calculo de Valor Venda*/
                int somaQuantidade = itens.getQuantidade() + quantidade;
                String atualiza;
                
                /* Atualizando Quantidade, Preços */
                atualiza = "UPDATE produto set quantidade = ?, valor_custo = ?, valor_venda = ? WHERE cod_Produto = ?";
                pst = Conexao.getInstance().prepareStatement(atualiza);
                pst.setInt(1, somaQuantidade);
                pst.setString(2, itens.getPreco_unitario());
                pst.setString(3, df.format(CalculoVenda));
                pst.setInt(4, itens.getProduto().getCod_produto());
                pst.execute();
                pst.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
    }
}
