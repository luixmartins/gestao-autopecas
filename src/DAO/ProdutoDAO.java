package DAO;


/* Importações do Java */
import MODEL.CategoriaProduto;
import MODEL.Fornecedor;
import MODEL.MarcaProduto;
import MODEL.Produto;
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

public class ProdutoDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    /* Instanciando as classes */
    CategoriaProduto categoria;
    MarcaProduto marca;
    Produto produto;
    Fornecedor fornecedor;

    /* Salvar Produto */
    public void SalvarProduto(Produto produto, MarcaProduto marca, CategoriaProduto categoria) throws SQLException {
        String buscaMarca, buscaCategoria, buscaFornecedor, salvaProduto, salvarProdutoHasFornecedor;
        /* Busca Marca */
        buscaMarca = "SELECT * FROM  marca where  nome_marca  = " + marca.getNome_marca();
        pst = Conexao.getInstance().prepareStatement(buscaMarca);
        ResultSet rs = pst.executeQuery(buscaMarca);
        int codMarca = 0;
        while (rs.next()) {
            codMarca = rs.getInt("cod_marca");
        }
        pst.execute();
        /* Buscando Categoria */
        buscaCategoria = "SELECT * FROM  categoria where  nome_categoria  = " + categoria.getNome_categoria();
        pst = Conexao.getInstance().prepareStatement(buscaCategoria);
        ResultSet rs2 = pst.executeQuery(buscaCategoria);
        int codCategoria = 0;
        while (rs2.next()) {
            codCategoria = rs2.getInt("cod_categoria");
        }
        pst.execute();
        /* Buscando Fornecedor */
        buscaFornecedor = "SELECT * FROM  fornecedor where  razao_social  = " + fornecedor.getNome();
        pst = Conexao.getInstance().prepareStatement(buscaFornecedor);
        ResultSet rs4 = pst.executeQuery(buscaFornecedor);
        int codFornecedor = 0;
        while (rs4.next()) {
            codFornecedor = rs4.getInt("cod_categoria");
        }
        pst.execute();
        /* Salvando Produto */
        salvaProduto = "INSERT INTO produto VALUES (?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(salvaProduto, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, produto.getDescricao());
        pst.setInt(3, codMarca);
        pst.setInt(4, codCategoria);
        pst.setInt(5, produto.getQuantidade());
        pst.setInt(6, produto.getQuantidadeMinima());
        pst.setString(7, produto.getCodigo_barras());
        pst.setString(8, produto.getValor_custo());
        pst.setString(9, produto.getValor_venda());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs3 = pst.getGeneratedKeys();
        int idProduto = 0;
        if (rs3.next()) {
            idProduto = rs3.getInt(1);
        }
        salvarProdutoHasFornecedor = "INSERT INTO produto_has_fornecedor VALUES(?,?,?)";
        pst.setInt(1, 0);
        pst.setInt(2, idProduto);
        pst.setInt(3, codFornecedor);
        pst.execute();
        pst.close();
    }

    /* Excluir Produto */
    public void ExcluirProduto(int codigo_produto) throws SQLException {
        try {
            String deleta_produto;
            deleta_produto = "delete from fornecedor where idFornecedor = " + produto.getCod_produto();
            pst = Conexao.getInstance().prepareStatement(deleta_produto);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
    }

    /* Buscar Produto */
    public List<Produto> listaProduto() {
        try {
            List<Produto> lista = new ArrayList<>();
            String sqlBuscaProduto;
            sqlBuscaProduto = "select * from produto_has_fornecedor inner join produto on produto.cod_Produto = produto_has_fornecedor.Produto_cod_Produto inner join fornecedor on fornecedor.idFornecedor = produto_has_fornecedor.cod_ProdFornecedor inner join marca on marca.cod_marca = produto.FK_marca inner join categoria on categoria.cod_categoria = produto.FK_categoria";
            pst = Conexao.getInstance().prepareStatement(sqlBuscaProduto);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Classes */
                Produto produto = new Produto();
                MarcaProduto marca = new MarcaProduto();
                Fornecedor fornecedor = new Fornecedor();
                CategoriaProduto categoria = new CategoriaProduto();
                /* Setando Atributos Produto */
                produto.setCod_produto(rs.getInt("cod_Produto"));
                produto.setDescricao(rs.getString("descricao_produto"));
                /* Setando Marca */
                produto.setMarca(marca);
                marca.setNome_marca(rs.getString("nome_marca"));
                /* Setando Categoria */
                produto.setCategoria(categoria);
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                /* Continuação Produto */
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                produto.setCodigo_barras(rs.getString("codigo_barras"));
                produto.setValor_custo(rs.getString("valor_custo"));
                produto.setValor_venda(rs.getString("valor_venda"));
                /* Adicionando dados na Lista */
                lista.add(produto);
            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }

    /* Alterar Produto */
    public void alterarProduto(Produto produto, MarcaProduto marca, CategoriaProduto categoria) throws SQLException {
        String buscaMarca, buscaCategoria, buscaFornecedor, updateProduto, Produto_Has_Fornecedor, buscaProduto_Has_Fornecedor;
        /* Busca Marca */
        buscaMarca = "SELECT * FROM  marca where  nome_marca  = " + marca.getNome_marca();
        pst = Conexao.getInstance().prepareStatement(buscaMarca);
        ResultSet rs = pst.executeQuery(buscaMarca);
        int codMarca = 0;
        while (rs.next()) {
            codMarca = rs.getInt("cod_marca");
        }
        pst.execute();
        /* Buscando Categoria */
        buscaCategoria = "SELECT * FROM  categoria where  nome_categoria  = " + categoria.getNome_categoria();
        pst = Conexao.getInstance().prepareStatement(buscaCategoria);
        ResultSet rs2 = pst.executeQuery(buscaCategoria);
        int codCategoria = 0;
        while (rs2.next()) {
            codCategoria = rs2.getInt("cod_categoria");
        }
        pst.execute();
        /* Buscando Fornecedor */
        buscaFornecedor = "SELECT * FROM  fornecedor where  razao_social  = " + fornecedor.getNome();
        pst = Conexao.getInstance().prepareStatement(buscaFornecedor);
        ResultSet rs3 = pst.executeQuery(buscaFornecedor);
        int codFornecedor = 0;
        while (rs3.next()) {
            codFornecedor = rs3.getInt("cod_categoria");
        }
        pst.execute();
        /* Update Produto */
        updateProduto = "UPDATE produto SET descricao_produto = ?, FK_marca = ?,  FK_categoria = ?, quantidade = ?, quantidade_minima = ?, codigo_barras = ?, valor_custo = ?, valor_venda = ? where cod_Produto = ? ";
        pst = Conexao.getInstance().prepareStatement(updateProduto);
        pst.setString(1, produto.getDescricao());
        pst.setInt(2, codMarca);
        pst.setInt(3, codCategoria);
        pst.setInt(4, produto.getQuantidade());
        pst.setInt(5, produto.getQuantidadeMinima());
        pst.setString(6, produto.getCodigo_barras());
        pst.setString(7, produto.getValor_custo());
        pst.setString(8, produto.getValor_venda());
        pst.setInt(9, produto.getCod_produto());
        pst.execute();
        /* Buscando Código PK da Table Produto Has Fornecedor */
         buscaFornecedor = "SELECT * FROM  produto_has_fornecedor  where  Produto_cod_Produto  = " + produto.getCod_produto();
        pst = Conexao.getInstance().prepareStatement(buscaFornecedor);
        ResultSet rs4 = pst.executeQuery(buscaFornecedor);
        int codProdFornecedor = 0;
        while (rs4.next()) {
            codProdFornecedor = rs4.getInt("cod_ProdFornecedor");
        }
        pst.execute();
        buscaProduto_Has_Fornecedor = "";
        /* Update Produto Has Fornecedor  */
        Produto_Has_Fornecedor = "UPDATE produto_has_fornecedor SET Produto_cod_Produto = ?, Fornecedor_idFornecedor = ? where cod_ProdFornecedor = ? ";
        pst = Conexao.getInstance().prepareStatement(Produto_Has_Fornecedor);
        pst.setInt(1, produto.getCod_produto());
        pst.setInt(2, codFornecedor);
        pst.setInt(3, codProdFornecedor);
        pst.execute();
        pst.close();
    }
}
