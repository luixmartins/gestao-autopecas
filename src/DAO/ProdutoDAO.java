package DAO;


/* Importações do Java */
import MODEL.CategoriaProduto;
import MODEL.Fornecedor;
import MODEL.MarcaProduto;
import MODEL.Produto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* Importações do SQL */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
        salvaProduto = "INSERT INTO produto VALUES (?,?,?,?,?,?,?,?,?,?)";
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
        pst.setString(10, produto.getPorcentagem());
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
                produto.setPorcentagem(rs.getString("pct_lucro"));
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
                produto.setPorcentagem(rs.getString("pct_lucro"));
                /* Adicionando dados na Lista */
                lista.add(produto);
            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }

    public List<Produto> listaCategoria(String nome) {
        try {
            List<Produto> lista = new ArrayList<>();
            String sqlBuscaProduto2;
            sqlBuscaProduto2 = "select * from produto inner join marca on marca.cod_marca = produto.FK_marca inner join categoria on categoria.cod_categoria = produto.FK_categoria where categoria.nome_categoria like \"" + nome + "\" ";
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
                produto.setPorcentagem(rs.getString("pct_lucro"));
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
                produto.setPorcentagem(rs.getString("pct_lucro"));
                /* Adicionando dados na Lista */
                lista.add(produto);
            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }
        return null;
    }

    public void gerarDocumentoCompletoProdutos() {
        try {
            List<Produto> lista = new ArrayList<>();
            lista = listaProduto();

            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioProdutosCompleto" + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            String nomeproduto = null;
            String qtd_produto = null;

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO ESTOQUE", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(6);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("CODIGO", f5));
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("DESCRIÇÃO", f5));
            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("QUANTIDADE", f5));
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("QUANTIDADE MINIMA", f5));
            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("MARCA", f5));
            PdfPCell cabecalho5 = new PdfPCell(new Paragraph("CATEGORIA", f5));

            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho5);

            for (Produto produto : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(produto.getCod_produto()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p1 = new Paragraph(produto.getDescricao(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);

                Paragraph p2 = new Paragraph(Integer.toString(produto.getQuantidade()) + " UN", f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);

                Paragraph p3 = new Paragraph(Integer.toString(produto.getQuantidadeMinima()) + " UN", f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);

                Paragraph p4 = new Paragraph(produto.getMarca().getNome_marca(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                
                Paragraph p5 = new Paragraph(produto.getCategoria().getNome_categoria(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col5 = new PdfPCell(p5);


                tabela.addCell(col0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho;
            caminho = "C:/SRS/RelatorioProdutosCompleto.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }
    

    public void gerarRelatorioCategoria(String categoria) throws DocumentException {
        try {

            List<Produto> lista = new ArrayList();
            lista = listaCategoria(categoria);
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioCategoria.pdf"));
            // Abrindo o Arquivo
            doc.open();            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            String nomeCliente = null;
            String valorTotal = null;
            Date dataVenda = null;

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO PRODUTOS - " + categoria, f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);
            

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("CODIGO", f5));
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("DESCRIÇÃO", f5));
            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("QUANTIDADE", f5));
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("QUANTIDADE MINIMA", f5));
            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("MARCA", f5));

            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);

            for (Produto produto : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(produto.getCod_produto()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p1 = new Paragraph(produto.getDescricao(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);

                Paragraph p2 = new Paragraph(Integer.toString(produto.getQuantidade()) + " UN", f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);

                Paragraph p3 = new Paragraph(Integer.toString(produto.getQuantidadeMinima()) + " UN", f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);

                Paragraph p4 = new Paragraph(produto.getMarca().getNome_marca(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);

                tabela.addCell(col0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho;
            caminho = "C:/SRS/RelatorioCategoria.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }

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
        updateProduto = "UPDATE produto SET descricao_produto = ?, FK_marca = ?,  FK_categoria = ?, quantidade = ?, quantidade_minima = ?, codigo_barras = ?, valor_custo = ?, valor_venda = ?, pct_lucro = ? where cod_Produto = ? ";
        pst = Conexao.getInstance().prepareStatement(updateProduto);
        pst.setString(1, produto.getDescricao());
        pst.setInt(2, codMarca);
        pst.setInt(3, codCategoria);
        pst.setInt(4, produto.getQuantidade());
        pst.setInt(5, produto.getQuantidadeMinima());
        pst.setString(6, produto.getCodigo_barras());
        pst.setString(7, produto.getValor_custo());
        pst.setString(8, produto.getValor_venda());
        pst.setString(9, produto.getPorcentagem());
        pst.setInt(10, produto.getCod_produto());
        pst.execute();
        pst.close();
    }
    
    public void gerarDocumentoMinimoProdutos() {
        try {
            List<Produto> lista = new ArrayList<>();
            lista = listaQuantidade();

            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioProdutosMinimo" + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            String nomeproduto = null;
            String qtd_produto = null;

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO PRODUTOS ABAIXO DA QUANTIDADE MINIMA", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(6);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("CODIGO", f5));
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("DESCRIÇÃO", f5));
            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("QUANTIDADE", f5));
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("QUANTIDADE MINIMA", f5));
            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("MARCA", f5));
            PdfPCell cabecalho5 = new PdfPCell(new Paragraph("CATEGORIA", f5));

            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho5);

            for (Produto produto : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(produto.getCod_produto()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p1 = new Paragraph(produto.getDescricao(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);

                Paragraph p2 = new Paragraph(Integer.toString(produto.getQuantidade()) + " UN", f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);

                Paragraph p3 = new Paragraph(Integer.toString(produto.getQuantidadeMinima()) + " UN", f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);

                Paragraph p4 = new Paragraph(produto.getMarca().getNome_marca(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                
                Paragraph p5 = new Paragraph(produto.getCategoria().getNome_categoria(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col5 = new PdfPCell(p5);


                tabela.addCell(col0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho;
            caminho = "C:/SRS/RelatorioProdutosMinimo.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }

}
