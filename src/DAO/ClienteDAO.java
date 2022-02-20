/* Nome do Pacote */
package DAO;

/* Importações*/
import MODEL.Cliente;
import MODEL.Contato;
import MODEL.Endereco;
import MODEL.ClienteFisica;
import MODEL.ClienteJuridica;
import MODEL.Produto;
import com.lowagie.text.Document;
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
/* Importações do SQL */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/* Classe ClienteDAO */
public class ClienteDAO {

    /* Instanciamento e criação do String SQL*/
    PreparedStatement pst;
    String sql;

    Endereco cliEndereco;
    Contato cliContato;
    ClienteJuridica cliJuridica;
    ClienteFisica cliFisica;

    /* Método para Salvar Pessoa Fisica */
    public void salvarFisica(Cliente cliente, ClienteFisica cliFisica, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int idEndereco = 0;
        if (rs.next()) {
            idEndereco = rs.getInt(1);
        }

        /* Inserindo o Contato */
        String sqlContato;
        sqlContato = "insert into contato values (?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }

        /* Inserindo Cliente */
        sql = "insert into cliente values (?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setInt(4, idContato);
        pst.setInt(5, idEndereco);
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs3 = pst.getGeneratedKeys();
        int idCliente = 0;
        if (rs3.next()) {
            idCliente = rs3.getInt(1);
        }

        /* Inserindo Pessoa Fisica */
        String sqlFisica;
        sqlFisica = "insert into clientefisica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlFisica);
        pst.setString(1, cliFisica.getCpf());
        pst.setString(2, cliFisica.getRg());
        pst.setInt(3, idCliente);
        pst.execute();

        pst.close();
    }

    /* Método para Salvar Pessoa Juridica */
    public void salvarJuridica(Cliente cliente, ClienteJuridica cliJuridica, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Inserindo o Endereço */
        String Endereco;
        Endereco = "insert into endereco values (?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(Endereco, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliEnd.getRua());
        pst.setInt(3, cliEnd.getNumero());
        pst.setString(4, cliEnd.getBairro());
        pst.setString(5, cliEnd.getCidade());
        pst.setString(6, cliEnd.getEstado());
        pst.setString(7, cliEnd.getCep());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs = pst.getGeneratedKeys();
        int idEndereco = 0;
        if (rs.next()) {
            idEndereco = rs.getInt(1);
        }

        /* Inserindo o Contato */
        String sqlContato;
        sqlContato = "insert into contato values (?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliContato.getTelefone_cliente());
        pst.setString(3, cliContato.getCelular_cliente());
        pst.setString(4, cliContato.getEmail());
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs2 = pst.getGeneratedKeys();
        int idContato = 0;
        if (rs2.next()) {
            idContato = rs2.getInt(1);
        }

        /* Inserindo Cliente */
        sql = "insert into cliente values (?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, 0);
        pst.setString(2, cliente.getNome_cliente());
        pst.setInt(3, cliente.getTipo_cliente());
        pst.setInt(4, idContato);
        pst.setInt(5, idEndereco);
        pst.execute();
        /* Pegando o ultimo ID inserido */
        ResultSet rs3 = pst.getGeneratedKeys();
        int idCliente = 0;
        if (rs3.next()) {
            idCliente = rs3.getInt(1);
        }
        /* Inserindo Pessoa Fisica */
        String sqlJuridica;
        sqlJuridica = "insert into clientejuridica values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sqlJuridica);
        pst.setString(1, cliJuridica.getCnpj());
        pst.setString(2, cliJuridica.getInscricao_estadual());
        pst.setInt(3, idCliente);
        pst.execute();

        pst.close();
    }

    /* Método para Excluir */
    public void excluir(Cliente cliente) throws SQLException {
        try {
            String deleta_cliente, deleta_contato, buscaDados, delete_endereco, deleta_fisica;
            buscaDados = "SELECT * FROM cliente where  cod_cliente = " + cliente.getCod_cliente();
            pst = Conexao.getInstance().prepareStatement(buscaDados);
            ResultSet rs = pst.executeQuery(buscaDados);
            int codEndereco = 0;
            int codContato = 0;

            while (rs.next()) {
                codContato = rs.getInt("clientecontato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }

            deleta_cliente = "delete from cliente where cod_cliente=" + cliente.getCod_cliente();;
            pst = Conexao.getInstance().prepareStatement(deleta_cliente);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }

    /* Método para Alterar Registros */
    public void alterar(Cliente cliente, Endereco cliEnd, Contato cliContato) throws SQLException {
        /* Update dados Cliente */

        try {
            String buscaDados;
            buscaDados = "SELECT * FROM cliente where  cod_cliente = " + cliente.getCod_cliente();
            pst = Conexao.getInstance().prepareStatement(buscaDados);
            ResultSet rs = pst.executeQuery(buscaDados);
            int codEndereco = 0;
            int codContato = 0;
            while (rs.next()) {
                codContato = rs.getInt("clientecontato_cod_contato");
                codEndereco = rs.getInt("endereco_cod_endereco");
            }
            sql = "UPDATE cliente,contato,endereco SET cliente.nome_cliente=?,endereco.rua_endereco=?,endereco.numero_endereco=?,endereco.bairro_endereco=?,endereco.cidade_endereco=?,endereco.estado_endereco=?,endereco.cep_endereco=?,contato.telefone_contato=?,contato.celular_contato=?,contato.email_contato=? where cliente.cod_cliente=? and endereco.cod_endereco=? and contato.cod_contato = ?";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setString(1, cliente.getNome_cliente());
            pst.setString(2, cliEnd.getRua());
            pst.setInt(3, cliEnd.getNumero());
            pst.setString(4, cliEnd.getBairro());
            pst.setString(5, cliEnd.getCidade());
            pst.setString(6, cliEnd.getEstado());
            pst.setString(7, cliEnd.getCep());
            pst.setString(8, cliContato.getTelefone_cliente());
            pst.setString(9, cliContato.getCelular_cliente());
            pst.setString(10, cliContato.getEmail());
            pst.setInt(11, cliente.getCod_cliente());
            pst.setInt(12, codEndereco);
            pst.setInt(13, codContato);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

    }

    /* Lista para Buscar Cliente Fisica */
    public List<Cliente> listarClientesFisica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from clientefisica inner join cliente on cliente.cod_cliente = clientefisica.cliente_cod_cliente inner join contato on contato.cod_contato = cliente.clientecontato_cod_contato inner join endereco on endereco.cod_endereco = cliente.endereco_cod_endereco";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteFisica cliFisica = new ClienteFisica();
                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));

                cliente.setCliFisica(cliFisica);

                cliFisica.setCpf(rs.getString("cpf"));
                cliFisica.setRg(rs.getString("rg"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    /* Lista para Buscar Cliente Juridica */
    public List<Cliente> ListarClienteJuridica() {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from clientejuridica inner join cliente on cliente.cod_cliente = clientejuridica.cliente_cod_cliente inner join contato on contato.cod_contato = cliente.clientecontato_cod_contato inner join endereco on endereco.cod_endereco = cliente.endereco_cod_endereco";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteJuridica cliJuridica = new ClienteJuridica();
                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));

                cliente.setCliJuridica(cliJuridica);

                cliJuridica.setCnpj(rs.getString("cnpj"));
                cliJuridica.setInscricao_estadual(rs.getString("inscricao_estadual"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    //METODOS DE BUSCA POR DIGITAÇÃO
    public List<Cliente> buscaClientesFisica(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join contato on contato.cod_contato = cliente.cod_cliente"
                    + " inner join endereco on endereco.cod_endereco = cliente.cod_cliente"
                    + " inner join clientefisica on clientefisica.Cliente_cod_cliente = cliente.cod_cliente where nome_cliente like \"" + nome + "\"";

            pst = Conexao.getInstance().prepareStatement(sql);
            //pst.setString(0, nome);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteFisica cliFisica = new ClienteFisica();

                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));

                cliente.setCliFisica(cliFisica);

                cliFisica.setCpf(rs.getString("cpf"));
                cliFisica.setRg(rs.getString("rg"));

                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    /* Lista para Buscar Cliente Juridica */
    public List<Cliente> buscaClienteJuridica(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from cliente"
                    + " inner join contato on contato.cod_contato = cliente.cod_cliente"
                    + " inner join endereco on endereco.cod_endereco = cliente.cod_cliente"
                    + " inner join clientejuridica on clientejuridica.Cliente_cod_cliente = cliente.cod_cliente where nome_cliente like \"" + nome + "\"";

            pst = Conexao.getInstance().prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /* Instanciando */
                Cliente cliente = new Cliente();
                Contato cliContato = new Contato();
                Endereco cliEndereco = new Endereco();
                ClienteJuridica cliJuridica = new ClienteJuridica();

                /* Setando Atributos Cliente */
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                /* Setando CliContato*/
                cliente.setCliContato(cliContato);
                /* Setando Atributos CliContato */
                cliContato.setCelular_cliente(rs.getString("celular_contato"));
                cliContato.setTelefone_cliente(rs.getString("telefone_contato"));
                cliContato.setEmail(rs.getString("email_contato"));
                /* Setando CliEndereço */
                cliente.setCliEndereco(cliEndereco);
                /* Setando Atributos CliEndereco */
                cliEndereco.setRua(rs.getString("rua_endereco"));
                cliEndereco.setNumero(rs.getInt("numero_endereco"));
                cliEndereco.setBairro(rs.getString("bairro_endereco"));
                cliEndereco.setCidade(rs.getString("cidade_endereco"));
                cliEndereco.setEstado(rs.getString("estado_endereco"));
                cliEndereco.setCep(rs.getString("cep_endereco"));

                cliente.setCliJuridica(cliJuridica);

                cliJuridica.setCnpj(rs.getString("cnpj"));
                cliJuridica.setInscricao_estadual(rs.getString("inscricao_estadual"));
                /* Adicionando dados na Lista */
                lista.add(cliente);
            }

            System.out.println(lista);
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados. Contate o desenvolvedor");
        }

        return null;
    }

    public void gerarDocumentoCompletoPClientes() {

        try {
            // Chamando a função de listar Cliente Física
            List<Cliente> lista = new ArrayList<>();
            lista = listarClientesFisica();
            // Chamando a função de listar Cliente Jurídica
            List<Cliente> lista2 = new ArrayList<>();
            lista2 = ListarClienteJuridica();

            // Definindo o tamanho do Documento
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            // Salvando o Arquivo
            PdfWriter.getInstance(doc, new FileOutputStream("C:/SRS/RelatorioClientesCompleto" + ".pdf"));
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

            Paragraph titulo2 = new Paragraph("RELATÓRIO CLIENTES", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(4);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            tabela.setWidths(new int[]{1, 1, 1, 1});
            PdfPCell cabecalho0 = new PdfPCell(new Paragraph("CODIGO", f5));
            PdfPCell cabecalho1 = new PdfPCell(new Paragraph("NOME", f5));
            PdfPCell cabecalho2 = new PdfPCell(new Paragraph("CPF / CNPJ", f5));
            PdfPCell cabecalho3 = new PdfPCell(new Paragraph("TEL / CEL", f5));


            tabela.addCell(cabecalho0);
            tabela.addCell(cabecalho1);
            tabela.addCell(cabecalho2);
            tabela.addCell(cabecalho3);
            for (Cliente cliente : lista) {
                Paragraph p0 = new Paragraph(Integer.toString(cliente.getCod_cliente()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p1 = new Paragraph(cliente.getNome_cliente(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);

                Paragraph p2 = new Paragraph(cliente.getCliFisica().getCpf(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);

                Paragraph p3 = new Paragraph(cliente.getCliContato().getCelular_cliente(), f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                

                tabela.addCell(col0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
            }
            for (Cliente cliente : lista2) {
                Paragraph p0 = new Paragraph(Integer.toString(cliente.getCod_cliente()), f5);
                p0.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col0 = new PdfPCell(p0);

                Paragraph p1 = new Paragraph(cliente.getNome_cliente(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);

                Paragraph p2 = new Paragraph(cliente.getCliJuridica().getCnpj(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);

                Paragraph p3 = new Paragraph(cliente.getCliContato().getTelefone_cliente(), f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);

                tabela.addCell(col0);
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
            }
            doc.add(titulo1);
            doc.add(titulo2);
            doc.add(tabela);
            doc.close();
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho;
            caminho = "C:/SRS/RelatorioClientesCompleto.pdf";
            Desktop.getDesktop().open(new File(caminho));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Documento de Requisitos aberto. Feche para gerar um novo.");
        }

    }
}
