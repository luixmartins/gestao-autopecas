package DAO;


/* Importações do Java */
import MODEL.CategoriaProduto;
import MODEL.Fornecedor;
import MODEL.MarcaProduto;
import MODEL.Produto;
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
        buscaMarca = "SELECT * FROM  marca where  nome_marca  = '" + marca.getNome_marca() + "' ";
        pst = Conexao.getInstance().prepareStatement(buscaMarca);
        ResultSet rs = pst.executeQuery(buscaMarca);
        int codMarca = 0;
        while (rs.next()) {
            codMarca = rs.getInt("cod_marca");
        }
        pst.execute();
        /* Buscando Categoria */
        buscaCategoria = "SELECT * FROM  categoria where  nome_categoria  = '" + categoria.getNome_categoria() + "' ";
        pst = Conexao.getInstance().prepareStatement(buscaCategoria);
        ResultSet rs2 = pst.executeQuery(buscaCategoria);
        int codCategoria = 0;
        while (rs2.next()) {
            codCategoria = rs2.getInt("cod_categoria");
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
        pst.close();
    }

    /* Excluir Produto */
    public void ExcluirProduto(Produto produto) throws SQLException {
        try {
            String deleta_produto;
            deleta_produto = "delete from produto where cod_Produto = " + produto.getCod_produto();
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
            sqlBuscaProduto = "select * from produto  inner join marca on marca.cod_marca = produto.FK_marca inner join categoria on categoria.cod_categoria = produto.FK_categoria";
            pst = Conexao.getInstance().prepareStatement(sqlBuscaProduto);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Classes */
                Produto produto = new Produto();
                MarcaProduto marca = new MarcaProduto();
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

    public List<Produto> listaProduto(String nome) {
        try {
            List<Produto> lista = new ArrayList<>();
            String sqlBuscaProduto2;
            sqlBuscaProduto2 = "select * from produto inner join marca on marca.cod_marca = produto.FK_marca inner join categoria on categoria.cod_categoria = produto.FK_categoria where produto.cod_Produto like \"" + nome + "\" or produto.descricao_produto like \"" + nome + "\" or categoria.nome_categoria like \"" + nome + "\" or marca.nome_marca like \"" + nome + "\"";
            pst = Conexao.getInstance().prepareStatement(sqlBuscaProduto2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Classes */
                Produto produto = new Produto();
                MarcaProduto marca = new MarcaProduto();
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

    public List<Produto> listaQuantidade() {
        try {
            List<Produto> lista = new ArrayList<>();
            String sqlBuscaProduto3;
            sqlBuscaProduto3 = "select * from produto  inner join marca on marca.cod_marca = produto.FK_marca inner join categoria on categoria.cod_categoria = produto.FK_categoria where produto.quantidade < produto.quantidade_minima order by produto.quantidade ASC ";
            pst = Conexao.getInstance().prepareStatement(sqlBuscaProduto3);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* Instanciando Classes */
                Produto produto = new Produto();
                MarcaProduto marca = new MarcaProduto();
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
        String buscaMarca, buscaCategoria, updateProduto;
        /* Busca Marca */
        buscaMarca = "SELECT * FROM  marca where nome_marca  = '" + marca.getNome_marca() + "' ";
        pst = Conexao.getInstance().prepareStatement(buscaMarca);
        ResultSet rs = pst.executeQuery(buscaMarca);
        int codMarca = 0;
        while (rs.next()) {
            codMarca = rs.getInt("cod_marca");
        }
        pst.execute();
        /* Buscando Categoria */
        buscaCategoria = "SELECT * FROM  categoria where nome_categoria  = '" + categoria.getNome_categoria() + "' ";
        pst = Conexao.getInstance().prepareStatement(buscaCategoria);
        ResultSet rs2 = pst.executeQuery(buscaCategoria);
        int codCategoria = 0;
        while (rs2.next()) {
            codCategoria = rs2.getInt("cod_categoria");
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
        pst.close();
    }

}
