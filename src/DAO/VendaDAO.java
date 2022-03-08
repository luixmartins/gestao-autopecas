package DAO;

import MODEL.CategoriaProduto;
import MODEL.Cliente;
import MODEL.Funcionario;
import MODEL.ItensVenda;
import MODEL.MarcaProduto;
import MODEL.Produto;
import MODEL.Venda;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

public class VendaDAO {

    PreparedStatement pst;
    String sql;
    public static int idUltimaVenda;

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
        //NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);        
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
        idUltimaVenda = idVenda;
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

    public List<Venda> listarVendasPeriodo(String data_inicial, String data_final) {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente WHERE venda.data_venda >= date_format(str_to_date(\"" + data_inicial + "\" , '%d-%m-%Y'), '%Y-%m-%d') and venda.data_venda <= date_format(str_to_date(\"" + data_final + "\" , '%d-%m-%Y'), '%Y-%m-%d') GROUP BY venda.idVenda ORDER BY itensvenda.Venda_idVenda ASC";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
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

    public List<Venda> listarVendas() {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente GROUP BY venda.idVenda ORDER BY venda.idVenda ASC";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
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

    public List<Venda> listarVendasDetalhado(int idTabelaVenda) {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente WHERE venda.idVenda = \"" + idTabelaVenda + "\" ORDER BY itensvenda.Venda_idVenda ASC";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
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

    public List<Venda> listarTOP10Vendas() {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT produto.cod_Produto, produto.descricao_produto, produto.codigo_barras, SUM(itensvenda.quantidade_itens)  AS quantiaVendida , marca.nome_marca , categoria.nome_categoria FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda \n"
                    + "INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN marca on produto.FK_marca = marca.cod_marca\n"
                    + "INNER JOIN categoria on produto.FK_categoria = categoria.cod_categoria INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente GROUP BY produto.cod_Produto ORDER BY quantiaVendida DESC LIMIT 10; ";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Produto produto = new Produto();
                ItensVenda itensVenda = new ItensVenda();
                Venda venda = new Venda();
                CategoriaProduto categoria = new CategoriaProduto();
                MarcaProduto marca = new MarcaProduto();
                /* Setando Produto */
                produto.setCod_produto(rs.getInt("cod_Produto"));
                produto.setDescricao(rs.getString("descricao_produto"));
                produto.setCodigo_barras(rs.getString("codigo_barras"));
                /* Setando Marca e Categoria */
                marca.setNome_marca(rs.getString("nome_marca"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                /* Setando Atributos ItensVenda */
                itensVenda.setQuantidade(rs.getInt("quantiaVendida"));
                /* Setando Marca e Categoria em Produto */
                produto.setCategoria(categoria);
                produto.setMarca(marca);
                /* Setando Atributos Venda */
                venda.setProduto(produto);
                venda.setItensVenda(itensVenda);
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

    public List<Venda> listarUltimaVenda() {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente WHERE venda.idVenda = \"" + idUltimaVenda + "\"";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
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

    public List<Venda> ListReimprimirVenda(int idVendaImprime) {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente WHERE venda.idVenda = \"" + idVendaImprime + "\"";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
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

    public void gerarDocumentoVendaCompleto() throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarVendas();
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioVendaCompleto" + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
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

            Paragraph titulo2 = new Paragraph("RELATÓRIO VENDAS - COMPLETO", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("ID VENDA", f5));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR VENDA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho6 = new PdfPCell(new Paragraph("CLIENTE", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho7 = new PdfPCell(new Paragraph("VENDEDOR", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho8 = new PdfPCell(new Paragraph("DATA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);
            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho6);
            tabela.addCell(cabecalho7);
            tabela.addCell(cabecalho8);

            for (Venda venda : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(venda.getItensVenda().getIdItens_venda()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p4 = new Paragraph("R$ " + venda.getValor_total(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col5.setBorder(0);
                Paragraph p6 = new Paragraph(venda.getCliente().getNome_cliente(), f5);
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col6 = new PdfPCell(p6);
                //col5.setBorder(0);
                Paragraph p7 = new Paragraph(venda.getVendedor().getNome_funcionario(), f5);
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col7 = new PdfPCell(p7);
                //col5.setBorder(0);
                Paragraph p8 = new Paragraph(new SimpleDateFormat("dd-MM-yyyy").format(venda.getData_venda()), f5);
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col8 = new PdfPCell(p8);
                //col5.setBorder(0);
                tabela.addCell(col0);
                tabela.addCell(col4);
                tabela.addCell(col6);
                tabela.addCell(col7);
                tabela.addCell(col8);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioVendaCompleto.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }

    public void gerarRelatorioPeriodo(String data_inical, String data_final) throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarVendasPeriodo(data_inical, data_final);
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioVendaPeriodo" + data_inical + "-" + data_final + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
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

            Paragraph titulo2 = new Paragraph("RELATÓRIO VENDAS - PERÍODO", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);
            Paragraph titulo3 = new Paragraph("INICIO: " + data_inical + " FINAL: " + data_final, f1);
            titulo3.setAlignment(Element.ALIGN_CENTER);
            titulo3.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("ID VENDA", f5));
            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR VENDA", f5));
            PdfPCell cabecalho6 = new PdfPCell(new Paragraph("CLIENTE", f5));
            PdfPCell cabecalho7 = new PdfPCell(new Paragraph("VENDEDOR", f5));
            PdfPCell cabecalho8 = new PdfPCell(new Paragraph("DATA", f5));

            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho6);
            tabela.addCell(cabecalho7);
            tabela.addCell(cabecalho8);

            for (Venda venda : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(venda.getItensVenda().getIdItens_venda()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);
                //col3.setBorder(0);
                Paragraph p4 = new Paragraph("R$ " + venda.getValor_total(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col5.setBorder(0);
                Paragraph p6 = new Paragraph(venda.getCliente().getNome_cliente(), f5);
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col6 = new PdfPCell(p6);
                //col5.setBorder(0);
                Paragraph p7 = new Paragraph(venda.getVendedor().getNome_funcionario(), f5);
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col7 = new PdfPCell(p7);
                //col5.setBorder(0);
                Paragraph p8 = new Paragraph(new SimpleDateFormat("dd-MM-yyyy").format(venda.getData_venda()), f5);
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col8 = new PdfPCell(p8);
                //col5.setBorder(0);
                tabela.addCell(col0);
                tabela.addCell(col4);
                tabela.addCell(col6);
                tabela.addCell(col7);
                tabela.addCell(col8);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(titulo3);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioVendaPeriodo" + data_inical + "-" + data_final + ".pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }

    public void gerarDocumentoVenda() throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarUltimaVenda();
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioVenda-ID-" + idUltimaVenda + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            String nomeCliente = null;
            String valorTotal = null;
            String vendedor = null;
            Date dataVenda = null;

            for (Venda venda2 : lista) {
                nomeCliente = venda2.getCliente().getNome_cliente();
                valorTotal = venda2.getValor_total();
                vendedor = venda2.getVendedor().getNome_funcionario();
                dataVenda = venda2.getData_venda();
            }
            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("COMPROVANTE COMPRA ID: " + idUltimaVenda + "\nDATA: " + new SimpleDateFormat("dd-MM-yyyy").format(dataVenda), f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            Paragraph titulo3 = new Paragraph("Cliente: " + nomeCliente, f1);
            titulo3.setSpacingAfter(10);

            Paragraph titulo4 = new Paragraph("Vendedor: " + vendedor, f1);
            titulo4.setSpacingAfter(10);

            Paragraph titulo5 = new Paragraph("Valor Total: R$ " + valorTotal, f1);
            titulo5.setAlignment(Element.ALIGN_RIGHT);
            titulo5.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("COD BARRA", f3));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("DESCRIÇÃO", f3));

            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho2.setBorder(0);
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("QUANT", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho3.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho5 = new PdfPCell(new Paragraph("SUB TOTAL", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho5);

            for (Venda venda : lista) {
                Paragraph p1 = new Paragraph(venda.getProduto().getCodigo_barras(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                //col1.setBorder(0);
                Paragraph p2 = new Paragraph(venda.getProduto().getDescricao(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                //col2.setBorder(0);
                Paragraph p3 = new Paragraph(Integer.toString(venda.getItensVenda().getQuantidade()) + " UN", f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                //col3.setBorder(0);
                Paragraph p4 = new Paragraph("R$ " + venda.getProduto().getValor_venda(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col4.setBorder(0);
                Paragraph p5 = new Paragraph("R$ " + venda.getItensVenda().getPreco_unitario(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col5 = new PdfPCell(p5);
                //col5.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(titulo3);
            doc.add(titulo4);
            doc.add(tabela);
            doc.add(titulo5);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioVenda-ID-" + idUltimaVenda + ".pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }

    public void gerarRelatorioTOP10() throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarTOP10Vendas();
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioTOP10" + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO TOP 10 VENDIDOS");
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(6);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1, 1});
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("COD PRODUTO", f3));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("DESCRIÇÃO", f3));

            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho2.setBorder(0);
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("COD BARRA", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho3.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("QUANT VENDIDA", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho5 = new PdfPCell(new Paragraph("MARCA", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho6 = new PdfPCell(new Paragraph("CATEGORIA", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho5);
            tabela.addCell(cabecalho6);

            for (Venda venda : lista) {
                Paragraph p1 = new Paragraph(Integer.toString(venda.getProduto().getCod_produto()), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                //col1.setBorder(0);
                Paragraph p2 = new Paragraph(venda.getProduto().getDescricao(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                //col2.setBorder(0);
                Paragraph p3 = new Paragraph(venda.getProduto().getCodigo_barras(), f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                //col3.setBorder(0);
                Paragraph p4 = new Paragraph(Integer.toString(venda.getItensVenda().getQuantidade()) + " UN", f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col4.setBorder(0);
                Paragraph p5 = new Paragraph(venda.getProduto().getMarca().getNome_marca(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col5 = new PdfPCell(p5);

                Paragraph p6 = new Paragraph(venda.getProduto().getCategoria().getNome_categoria(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col6 = new PdfPCell(p6);

                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
                tabela.addCell(col6);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioTOP10.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");

        }

    }

    public void ReimprimirComprovante(int idVendaImprime) throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = ListReimprimirVenda(idVendaImprime);
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioVenda-ID-" + idVendaImprime + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            String nomeCliente = null;
            String valorTotal = null;
            String vendedor = null;
            Date dataVenda = null;

            for (Venda venda2 : lista) {
                nomeCliente = venda2.getCliente().getNome_cliente();
                valorTotal = venda2.getValor_total();
                vendedor = venda2.getVendedor().getNome_funcionario();
                dataVenda = venda2.getData_venda();
            }
            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("COMPROVANTE COMPRA ID: " + idVendaImprime + "\nDATA: " + new SimpleDateFormat("dd-MM-yyyy").format(dataVenda), f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            Paragraph titulo3 = new Paragraph("Cliente: " + nomeCliente, f1);
            titulo3.setSpacingAfter(10);

            Paragraph titulo4 = new Paragraph("Vendedor: " + vendedor, f1);
            titulo4.setSpacingAfter(10);

            Paragraph titulo5 = new Paragraph("Valor Total: R$ " + valorTotal, f1);
            titulo5.setAlignment(Element.ALIGN_RIGHT);
            titulo5.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("COD BARRA", f3));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("DESCRIÇÃO", f3));

            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho2.setBorder(0);
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("QUANT", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho3.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho5 = new PdfPCell(new Paragraph("SUB TOTAL", f3));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho5);

            for (Venda venda : lista) {
                Paragraph p1 = new Paragraph(venda.getProduto().getCodigo_barras(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                //col1.setBorder(0);
                Paragraph p2 = new Paragraph(venda.getProduto().getDescricao(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                //col2.setBorder(0);
                Paragraph p3 = new Paragraph(Integer.toString(venda.getItensVenda().getQuantidade()) + " UN", f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                //col3.setBorder(0);
                Paragraph p4 = new Paragraph("R$ " + venda.getProduto().getValor_venda(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col4.setBorder(0);
                Paragraph p5 = new Paragraph("R$ " + venda.getItensVenda().getPreco_unitario(), f5);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col5 = new PdfPCell(p5);
                //col5.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(titulo3);
            doc.add(titulo4);
            doc.add(tabela);
            doc.add(titulo5);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioVenda-ID-" + idVendaImprime + ".pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");

        }

    }

    public List<Venda> listarFaturamento(String data_inicial, String data_final) {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente WHERE  venda.data_venda >= date_format(str_to_date(\"" + data_inicial + "\" , '%d-%m-%Y'), '%Y-%m-%d') and venda.data_venda <= date_format(str_to_date(\"" + data_final + "\" , '%d-%m-%Y'), '%Y-%m-%d') GROUP BY venda.idVenda ORDER BY itensvenda.Venda_idVenda ASC";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
                /* Setando Atributos Venda */
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setCliente(cliente);
                venda.setVendedor(funcionario);
                venda.setProduto(produto);
                venda.setItensVenda(itensVenda);
                //venda.setItens_venda((List<ItensVenda>) itensVenda);
                venda.setValor_total(rs.getString("valor_total_venda"));
                //produto.setValor_venda(rs.getString("valorFaturamento"));
                venda.setProduto(produto);

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

    public List<Venda> listarFaturamentoTodo() {
        try {
            List<Venda> lista = new ArrayList<>();

            String sql = "SELECT * FROM itensvenda INNER JOIN venda ON itensvenda.Venda_idVenda = venda.idVenda INNER JOIN produto on itensvenda.Produto_cod_Produto = produto.cod_Produto INNER JOIN funcionario on venda.Funcionario_idFuncionario = funcionario.idFuncionario INNER JOIN cliente ON venda.cliente_cod_cliente = cliente.cod_cliente GROUP BY venda.idVenda ORDER BY itensvenda.Venda_idVenda ASC";

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
                itensVenda.setIdItens_venda(rs.getInt("Venda_idVenda"));
                /* Setando Atributos Venda */
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setCliente(cliente);
                venda.setVendedor(funcionario);
                venda.setProduto(produto);
                venda.setItensVenda(itensVenda);
                //venda.setItens_venda((List<ItensVenda>) itensVenda);
                venda.setValor_total(rs.getString("valor_total_venda"));
                //produto.setValor_venda(rs.getString("valorFaturamento"));
                venda.setProduto(produto);

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

    public void gerarRelatorioFaturamentoPeriodo(String data_inical, String data_final, double valorTotalVenda) throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarFaturamento(data_inical, data_final);
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioFaturamento" + data_inical + "-" + data_final + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO FATURAMENTO - Periodo: Inicio: " + data_inical + " Final:" + data_final, f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            Paragraph titulo3 = new Paragraph("TOTAL R$: " + valorTotalVenda, f2);
            titulo3.setAlignment(Element.ALIGN_LEFT);
            titulo3.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("ID VENDA", f5));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR VENDA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho6 = new PdfPCell(new Paragraph("CLIENTE", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho7 = new PdfPCell(new Paragraph("VENDEDOR", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho8 = new PdfPCell(new Paragraph("DATA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);
            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho6);
            tabela.addCell(cabecalho7);
            tabela.addCell(cabecalho8);

            for (Venda venda : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(venda.getItensVenda().getIdItens_venda()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p4 = new Paragraph("R$ " + venda.getValor_total(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col5.setBorder(0);
                Paragraph p6 = new Paragraph(venda.getCliente().getNome_cliente(), f5);
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col6 = new PdfPCell(p6);
                //col5.setBorder(0);
                Paragraph p7 = new Paragraph(venda.getVendedor().getNome_funcionario(), f5);
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col7 = new PdfPCell(p7);
                //col5.setBorder(0);
                Paragraph p8 = new Paragraph(new SimpleDateFormat("dd-MM-yyyy").format(venda.getData_venda()), f5);
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col8 = new PdfPCell(p8);
                //col5.setBorder(0);
                tabela.addCell(col0);
                tabela.addCell(col4);
                tabela.addCell(col6);
                tabela.addCell(col7);
                tabela.addCell(col8);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.add(titulo3);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioFaturamento" + data_inical + "-" + data_final + ".pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }

    public void gerarRelatorioFaturamentoCompleto(double valorTotalVenda) throws DocumentException {
        try {

            List<Venda> lista = new ArrayList();
            lista = listarFaturamentoTodo();
            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioFaturamentoCompleto" + ".pdf"));
            // Abrindo o Arquivo
            doc.open();
            // Tamanho de Fonte
            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("GESTÃO AUTO PEÇAS", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("RELATÓRIO FATURAMENTO - COMPLETO", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            Paragraph titulo3 = new Paragraph("TOTAL R$: " + valorTotalVenda, f2);
            titulo3.setAlignment(Element.ALIGN_LEFT);
            titulo3.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(5);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("ID VENDA", f5));
            //cabecalho1.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho1.setBorder(0);

            PdfPCell cabecalho4 = new PdfPCell(new Paragraph("VALOR VENDA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho4.setBorder(0);

            PdfPCell cabecalho6 = new PdfPCell(new Paragraph("CLIENTE", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho7 = new PdfPCell(new Paragraph("VENDEDOR", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);

            PdfPCell cabecalho8 = new PdfPCell(new Paragraph("DATA", f5));
            //cabecalho2.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            //cabecalho5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cabecalho5.setBorder(0);
            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho4);
            tabela.addCell(cabecalho6);
            tabela.addCell(cabecalho7);
            tabela.addCell(cabecalho8);

            for (Venda venda : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(venda.getItensVenda().getIdItens_venda()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p4 = new Paragraph("R$ " + venda.getValor_total(), f5);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col4 = new PdfPCell(p4);
                //col5.setBorder(0);
                Paragraph p6 = new Paragraph(venda.getCliente().getNome_cliente(), f5);
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col6 = new PdfPCell(p6);
                //col5.setBorder(0);
                Paragraph p7 = new Paragraph(venda.getVendedor().getNome_funcionario(), f5);
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col7 = new PdfPCell(p7);
                //col5.setBorder(0);
                Paragraph p8 = new Paragraph(new SimpleDateFormat("dd-MM-yyyy").format(venda.getData_venda()), f5);
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col8 = new PdfPCell(p8);
                //col5.setBorder(0);
                tabela.addCell(col0);
                tabela.addCell(col4);
                tabela.addCell(col6);
                tabela.addCell(col7);
                tabela.addCell(col8);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.add(titulo3);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/SRS/RelatorioFaturamentoCompleto.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (IOException exx) {
            exx.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }
    }
}
