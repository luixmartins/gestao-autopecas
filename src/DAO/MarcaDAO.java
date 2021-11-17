
import DAO.Conexao;
import MODEL.MarcaProduto;
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

public class MarcaDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    /* Instanciando as classes */
    MarcaProduto marca;

    /* Métodos de Salvar */
    public void SalvarMarca(MarcaProduto marca) throws SQLException {
        String SQLCategoria;
        SQLCategoria = "insert into marca values (?,?)";
        pst = Conexao.getInstance().prepareStatement(SQLCategoria);
        pst.setInt(1, 0);
        pst.setString(2, marca.getNome_marca());
        pst.execute();
        pst.close();
    }

    /* Método de Excluir */
    public void ExcluirMarca(int codigo_marca) throws SQLException {
        try {
            String deleta_marca;
            deleta_marca = "delete from marca where cod_marca = " + marca.getCodigo_marca();
            pst = Conexao.getInstance().prepareStatement(deleta_marca);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
    }

    /* Método de Listar */
    public List<MarcaProduto> listarMarcas() {
        try {
            List<MarcaProduto> lista = new ArrayList<>();
            String SQLBuscaMarca;
            SQLBuscaMarca = "select * from categoria";
            pst = Conexao.getInstance().prepareStatement(SQLBuscaMarca);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Categoria */
                MarcaProduto marca = new MarcaProduto();
                /* Setando Atributos Categoria */
                marca.setCodigo_marca(rs.getInt("cod_marca"));
                marca.setNome_marca(rs.getString("nome_narca"));
                lista.add(marca);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }
    
     public List<MarcaProduto> listarMarcas(String nome) {
        try {
            List<MarcaProduto> lista = new ArrayList<>();
            String SQLBuscaMarca;
            SQLBuscaMarca = "select * from marca where nome_marca like \"" + nome + "\"";
            pst = Conexao.getInstance().prepareStatement(SQLBuscaMarca);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Categoria */
                MarcaProduto marca = new MarcaProduto();
                /* Setando Atributos Categoria */
                marca.setCodigo_marca(rs.getInt("cod_marca"));
                marca.setNome_marca(rs.getString("nome_narca"));
                lista.add(marca);
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }

    /* Método de Alterar */
    public void AlterarMarca(MarcaProduto marca) throws SQLException {

        try {
            String alteraMarca;
            alteraMarca = "UPDATE marca set nome_marca =? where marca.cod_marca=?";
            pst = Conexao.getInstance().prepareStatement(alteraMarca);
            pst.setString(1, marca.getNome_marca());
            pst.setInt(2, marca.getCodigo_marca());
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }
}
