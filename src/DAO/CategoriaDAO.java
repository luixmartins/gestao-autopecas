package DAO;

import MODEL.CategoriaProduto;
/* Importações do Java */
import java.sql.PreparedStatement;
import java.sql.SQLException;
/* Importações do SQL */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    /* Instanciando as classes */
    CategoriaProduto categoria;

    /* Método de Salvar */
    public void SalvarCategoria(CategoriaProduto categoria) throws SQLException {
        String SQLCategoria;
        SQLCategoria = "insert into categoria values (?,?)";
        pst = Conexao.getInstance().prepareStatement(SQLCategoria);
        pst.setInt(1, 0);
        pst.setString(2, categoria.getNome_categoria());
        pst.execute();
        pst.close();
    }

    /* Método de Excluir */
    public void ExcluirCategoria(int codigo_categoria) throws SQLException {
        try {
            String deleta_categoria;
            deleta_categoria = "delete from categoria where cod_categoria = " + categoria.getCodigo_categoria();
            pst = Conexao.getInstance().prepareStatement(deleta_categoria);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
    }

    /* Método de Listar */
    public List<CategoriaProduto> listarCategorias() {
        try {
            List<CategoriaProduto> lista = new ArrayList<>();
            String SQLBuscaCategoria;
            SQLBuscaCategoria = "select * from categoria";
            pst = Conexao.getInstance().prepareStatement(SQLBuscaCategoria);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Categoria */
                CategoriaProduto categoria = new CategoriaProduto();
                /* Setando Atributos Categoria */
                categoria.setCodigo_categoria(rs.getInt("cod_categoria"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                lista.add(categoria);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }

    /* Método de Alterar */
    public void AlterarCategoria(CategoriaProduto categoria) throws SQLException {

        try {
            String alteraCategoria;
            alteraCategoria = "UPDATE categoria set nome_categoria =? where categoria.cod_categoria=?";
            pst = Conexao.getInstance().prepareStatement(alteraCategoria);
            pst.setString(1, categoria.getNome_categoria());
            pst.setInt(1, categoria.getCodigo_categoria());
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }
}
