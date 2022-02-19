package VIEW;

import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.EntradaDAO;
import DAO.FornecedorDAO;
import DAO.FuncionarioDAO;
import DAO.MarcaProduto_DAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import MODEL.CategoriaProduto;
import MODEL.Cliente;
import MODEL.Entrada;
import MODEL.Fornecedor;
import MODEL.Funcionario;
import MODEL.ItensVenda;
import MODEL.MarcaProduto;
import MODEL.Produto;
import MODEL.Usuario;
import MODEL.Venda;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendasVIEW extends javax.swing.JFrame {

    Fornecedor fornecedor;
    FornecedorDAO fornecedorDAO;

    Produto produto;
    CategoriaProduto categoria;
    MarcaProduto marca;
    ProdutoDAO produtoDAO;

    Venda venda;
    VendaDAO vendaDAO;

    FuncionarioDAO funcionarioDAO;
    Funcionario funcionario;

    ClienteDAO clienteDAO;
    Cliente cliente;

    MarcaProduto_DAO marcaDAO;
    CategoriaDAO categoriaDAO;
    ItensVenda itensVenda;
    Entrada entrada;
    EntradaDAO entradaDao;
    List<Produto> listaProdutos;
    List<ItensVenda> itens;
    Usuario user;

    public VendasVIEW(Usuario user) {
        initComponents();
        this.setLocationRelativeTo(null);
        //ClienteDAO
        //FuncionarioDAO
        //VendaDAO
        txt_nomeProduto.setEnabled(false);
        txt_NomeCliente.setEnabled(false);
        listaProdutos = new ArrayList<>();
        itens = new ArrayList<>();
        if (user.getNivel_acesso() == 1) {
            this.dispose();
        } else {

        }
        labelTotalProduto.setVisible(false);
        resultadoTotal.setVisible(false);
        fechaBotoes();
        fechaCampos();
    }

    public void atualizaLabel() {
        float totalSomaLabel = 0;
        if (tabelaFornecedores.getRowCount() == 0) {
            resultadoTotal.setVisible(false);
            labelTotalProduto.setVisible(false);
            resultadoTotal.setText("");
        } else {
            for (int i2 = 0; i2 < tabelaFornecedores.getRowCount(); i2++) {

                NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
                totalSomaLabel += (Float.parseFloat((String) tabelaFornecedores.getValueAt(i2, 1)) * Float.parseFloat((String) tabelaFornecedores.getValueAt(i2, 2)));
                ((DecimalFormat) df).applyPattern("0.00");
                resultadoTotal.setVisible(true);
                resultadoTotal.setText("R$ " + df.format(totalSomaLabel));
                labelTotalProduto.setVisible(true);
            }
        }
    }

    public void listarProdutos() {
        produtoDAO = new ProdutoDAO();
        List<Produto> lista = produtoDAO.listaProduto();
        DefaultTableModel dados = (DefaultTableModel) tbl_produto.getModel();
        dados.setNumRows(0);
        for (Produto c : lista) {
            dados.addRow(new Object[]{
                c.getCod_produto(),
                c.getDescricao(),
                c.getCodigo_barras(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),
                c.getValor_venda()
            });
        }
    }

    public void listarVendedor() throws ParseException {
        funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> lista = funcionarioDAO.listarFuncionarios();
        DefaultTableModel dados = (DefaultTableModel) tbl_vendedor.getModel();
        dados.setNumRows(0);

        lista.forEach((Funcionario f) -> {
            dados.addRow(new Object[]{
                f.getCod_funcionario(),
                f.getNome_funcionario(),});
        });
    }

    public void listarFisica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listarClientesFisica();
        DefaultTableModel dados = (DefaultTableModel) tbl_Cliente.getModel();
        dados.setNumRows(0);
        for (Cliente c : lista) {
            dados.addRow(new Object[]{
                c.getCod_cliente(),
                c.getNome_cliente(),});
        }
    }

    public void listarJuridica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.ListarClienteJuridica();
        DefaultTableModel dados = (DefaultTableModel) tbl_Cliente.getModel();
        dados.setNumRows(0);
        for (Cliente c : lista) {
            dados.addRow(new Object[]{
                c.getCod_cliente(),
                c.getNome_cliente(),});
        }
    }

    public void fechaBotoes() {
        btnSalvarVenda.setEnabled(false);
        btnCancelarProdutos.setEnabled(false);
        btnAdicionar.setEnabled(false);
        btnSubtrair.setEnabled(false);
        btn_selecionar_cliente.setEnabled(false);
        btn_selecionar_produto.setEnabled(false);
        btnSelecionarVendedor.setEnabled(false);
    }

    public void fechaCampos() {
        txtPrecoUnit.setEnabled(false);
        txtQuantidade.setEnabled(false);
        txt_NomeCliente.setEnabled(false);
        txt_nomeProduto.setEnabled(false);
        txt_vendedor.setEnabled(false);
    }

    public void abreCampos() {
        txtPrecoUnit.setEnabled(true);
        txtQuantidade.setEnabled(true);

        txt_vendedor.setEnabled(true);

    }

    public void limpaCampos() {
        txtPrecoUnit.setText("");
        txtQuantidade.setText("");
        txt_NomeCliente.setText("");
        txt_nomeProduto.setText("");
        txt_vendedor.setText("");
        resultadoTotal.setText("");

    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtPrecoUnit.getText().isEmpty() || txtQuantidade.getText().isEmpty()
                || txt_NomeCliente.getText().isEmpty()
                || txt_nomeProduto.getText().isEmpty() || txt_vendedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            listaCampos.add(txtPrecoUnit.getText());
            listaCampos.add(txtQuantidade.getText());
            listaCampos.add(txt_NomeCliente.getText());
            listaCampos.add(txt_nomeProduto.getText());
            listaCampos.add(txt_vendedor.getText());
            return listaCampos;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_Produto = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        txt_busca_produto = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_produto = new javax.swing.JTable();
        Dialog_Vendedor = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txt_buscaVendedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_vendedor = new javax.swing.JTable();
        Dialog_Cliente = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        txt_buscaCliente = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_Cliente = new javax.swing.JTable();
        rbn_fisica = new javax.swing.JRadioButton();
        rbn_juridica = new javax.swing.JRadioButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarVenda = new javax.swing.JButton();
        btnCancelarProdutos = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        txtPrecoUnit = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFornecedores = new javax.swing.JTable();
        btnSubtrair = new javax.swing.JButton();
        txt_NomeCliente = new javax.swing.JTextField();
        btn_selecionar_cliente = new javax.swing.JButton();
        txt_nomeProduto = new javax.swing.JTextField();
        btn_selecionar_produto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_vendedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnSelecionarVendedor = new javax.swing.JButton();
        labelTotalProduto = new javax.swing.JLabel();
        resultadoTotal = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        Dialog_Produto.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Produto.setMinimumSize(new java.awt.Dimension(859, 429));
        Dialog_Produto.setModal(true);
        Dialog_Produto.setName("Dialog_Produto"); // NOI18N
        Dialog_Produto.setResizable(false);
        Dialog_Produto.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_ProdutoWindowOpened(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Buscar");

        txt_busca_produto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busca_produtoKeyPressed(evt);
            }
        });

        tbl_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Cod. Barras", "Categoria", "Marca", "Valor Venda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_produtoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_produto);

        javax.swing.GroupLayout Dialog_ProdutoLayout = new javax.swing.GroupLayout(Dialog_Produto.getContentPane());
        Dialog_Produto.getContentPane().setLayout(Dialog_ProdutoLayout);
        Dialog_ProdutoLayout.setHorizontalGroup(
            Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ProdutoLayout.createSequentialGroup()
                .addGroup(Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dialog_ProdutoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Dialog_ProdutoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busca_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Dialog_ProdutoLayout.setVerticalGroup(
            Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ProdutoLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_busca_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        Dialog_Vendedor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Vendedor.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_Vendedor.setMinimumSize(new java.awt.Dimension(859, 429));
        Dialog_Vendedor.setModal(true);
        Dialog_Vendedor.setName("Dialog_Vendedor"); // NOI18N
        Dialog_Vendedor.setResizable(false);
        Dialog_Vendedor.addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                Dialog_VendedorWindowStateChanged(evt);
            }
        });
        Dialog_Vendedor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Dialog_VendedorPropertyChange(evt);
            }
        });
        Dialog_Vendedor.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_VendedorWindowOpened(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Vendedor");

        txt_buscaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscaVendedorActionPerformed(evt);
            }
        });
        txt_buscaVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscaVendedorKeyPressed(evt);
            }
        });

        tbl_vendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        tbl_vendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vendedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_vendedor);

        javax.swing.GroupLayout Dialog_VendedorLayout = new javax.swing.GroupLayout(Dialog_Vendedor.getContentPane());
        Dialog_Vendedor.getContentPane().setLayout(Dialog_VendedorLayout);
        Dialog_VendedorLayout.setHorizontalGroup(
            Dialog_VendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_VendedorLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(Dialog_VendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Dialog_VendedorLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Dialog_VendedorLayout.setVerticalGroup(
            Dialog_VendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_VendedorLayout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(Dialog_VendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_buscaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        Dialog_Cliente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Cliente.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_Cliente.setMinimumSize(new java.awt.Dimension(859, 429));
        Dialog_Cliente.setModal(true);
        Dialog_Cliente.setName("Dialog_Vendedor"); // NOI18N
        Dialog_Cliente.setResizable(false);
        Dialog_Cliente.addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                Dialog_ClienteWindowStateChanged(evt);
            }
        });
        Dialog_Cliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Dialog_ClientePropertyChange(evt);
            }
        });
        Dialog_Cliente.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_ClienteWindowOpened(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Cliente");

        txt_buscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscaClienteActionPerformed(evt);
            }
        });
        txt_buscaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscaClienteKeyPressed(evt);
            }
        });

        tbl_Cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        tbl_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClienteMouseClicked(evt);
            }
        });
        tbl_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_ClienteKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_Cliente);

        rbn_fisica.setText("Física ");
        rbn_fisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbn_fisicaActionPerformed(evt);
            }
        });

        rbn_juridica.setText("Jurídica ");
        rbn_juridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbn_juridicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Dialog_ClienteLayout = new javax.swing.GroupLayout(Dialog_Cliente.getContentPane());
        Dialog_Cliente.getContentPane().setLayout(Dialog_ClienteLayout);
        Dialog_ClienteLayout.setHorizontalGroup(
            Dialog_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ClienteLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(Dialog_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Dialog_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbn_fisica)
                        .addGap(18, 18, 18)
                        .addComponent(rbn_juridica)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Dialog_ClienteLayout.setVerticalGroup(
            Dialog_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ClienteLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(Dialog_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Dialog_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_buscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbn_fisica)
                        .addComponent(rbn_juridica))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1035, 364));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("Cliente");

        jLabel7.setText("Quantidade");

        jLabel8.setText("Preço Unitário");

        jLabel9.setText("Produto");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvarVenda.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvarVenda.setText("Salvar");
        btnSalvarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarVendaActionPerformed(evt);
            }
        });

        btnCancelarProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarProdutos.setText("Cancelar");
        btnCancelarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProdutosActionPerformed(evt);
            }
        });

        btnAdicionar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdicionar.setText("+");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        tabelaFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade", "Preço Unitário", "Preço Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFornecedores);

        btnSubtrair.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSubtrair.setText("-");
        btnSubtrair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubtrairActionPerformed(evt);
            }
        });

        btn_selecionar_cliente.setText("Selecionar");
        btn_selecionar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selecionar_clienteActionPerformed(evt);
            }
        });

        btn_selecionar_produto.setText("Selecionar");
        btn_selecionar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selecionar_produtoActionPerformed(evt);
            }
        });

        jLabel5.setText("Vendedor");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Dados do vendedor");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Dados produto");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Dados cliente");

        btnSelecionarVendedor.setText("Selecionar");
        btnSelecionarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarVendedorActionPerformed(evt);
            }
        });

        labelTotalProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalProduto.setForeground(new java.awt.Color(255, 102, 51));
        labelTotalProduto.setText("Valor Total dos Produtos:");

        resultadoTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelTotalProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resultadoTotal))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_selecionar_cliente))
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_selecionar_produto))
                                            .addComponent(txtPrecoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(98, 98, 98)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnSubtrair, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(btnSalvarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCancelarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSelecionarVendedor)
                                    .addComponent(jLabel20))))))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(82, 82, 82))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(btnSelecionarVendedor)))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubtrair)
                            .addComponent(btnAdicionar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_selecionar_produto)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_selecionar_cliente))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPrecoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSalvarVenda)
                                .addComponent(btnCancelarProdutos))
                            .addComponent(jLabel20)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTotalProduto)
                            .addComponent(resultadoTotal))))
                .addGap(19, 19, 19))
        );

        jTabbedPane2.addTab("Dados entrada", jPanel2);

        btnNovo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Venda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnNovo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarVendaActionPerformed
        if (tabelaFornecedores.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "A tabela está vazia. Adcione os itens", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int row = tabelaFornecedores.getRowCount();

            produto = new Produto();
            funcionario = new Funcionario();
            vendaDAO = new VendaDAO();
            venda = new Venda();
            cliente = new Cliente();
            List<ItensVenda> itens = new ArrayList<>();
            int cod_funcionario = -1, cod_cliente = -1;
            try {
                cod_funcionario = vendaDAO.buscaVendedor(txt_vendedor.getText());
                cod_cliente = vendaDAO.buscaCliente(txt_NomeCliente.getText());
            } catch (SQLException ex) {
                Logger.getLogger(VendasVIEW.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (cod_funcionario != -1 && cod_cliente != -1) {
                funcionario.setCod_funcionario(cod_funcionario);
                cliente.setCod_cliente(cod_cliente);
                venda.setVendedor(funcionario);
                venda.setCliente(cliente);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar o vendedor", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            float total_venda = 0;
            for (int i = 0; i < row; i++) {
                try {
                    itensVenda = new ItensVenda();
                    produto = new Produto();
                    produto.setCod_produto(vendaDAO.buscaProduto((String) tabelaFornecedores.getValueAt(i, 0)));
                    itensVenda.setPreco_unitario((String) tabelaFornecedores.getValueAt(i, 3));
                    itensVenda.setQuantidade(Integer.parseInt((String) tabelaFornecedores.getValueAt(i, 1)));
                    itensVenda.setProduto(produto);
                    itens.add(itensVenda);
                    total_venda = total_venda + (Float.parseFloat((String) tabelaFornecedores.getValueAt(i, 1))
                            * Float.parseFloat((String) tabelaFornecedores.getValueAt(i, 2)));
                } catch (SQLException ex) {
                    Logger.getLogger(VendasVIEW.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Impossível encontrar o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            try {
                venda.setItens_venda(itens);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                decimalFormat.setRoundingMode(RoundingMode.DOWN);

                venda.setValor_total(String.valueOf(decimalFormat.format(total_venda)));
                vendaDAO.SalvarVenda(venda);
                String nomediretorio = null;
                String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
                String separador = java.io.File.separator;
                try {
                    nomediretorio = "C:" + separador + nomepasta;
                    if (!new File(nomediretorio).exists()) {
                        (new File(nomediretorio)).mkdir();
                    }
                    vendaDAO.gerarDocumentoVenda();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                Logger.getLogger(EntradaVIEW.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            DefaultTableModel table = (DefaultTableModel) tabelaFornecedores.getModel();

            table.setRowCount(0);
            fechaBotoes();
            limpaCampos();

            btnNovo.setEnabled(true);

            resultadoTotal.setVisible(false);
            labelTotalProduto.setVisible(false);
        }
    }//GEN-LAST:event_btnSalvarVendaActionPerformed

    private void btnCancelarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutosActionPerformed
        DefaultTableModel table = (DefaultTableModel) tabelaFornecedores.getModel();

        table.setRowCount(0);
        btnNovo.setEnabled(true);

        limpaCampos();
        fechaBotoes();
        fechaCampos();

        resultadoTotal.setVisible(false);
        labelTotalProduto.setVisible(false);
    }//GEN-LAST:event_btnCancelarProdutosActionPerformed

    private void tabelaFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedoresMouseClicked

    }//GEN-LAST:event_tabelaFornecedoresMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btn_selecionar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selecionar_clienteActionPerformed
        Dialog_Cliente.setLocationRelativeTo(null);
        Dialog_Cliente.setVisible(true);
    }//GEN-LAST:event_btn_selecionar_clienteActionPerformed

    private void btn_selecionar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selecionar_produtoActionPerformed
        Dialog_Produto.setLocationRelativeTo(null);
        Dialog_Produto.setVisible(true);
        listarProdutos();
    }//GEN-LAST:event_btn_selecionar_produtoActionPerformed

    private void tbl_vendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vendedorMouseClicked

        jTabbedPane2.setSelectedIndex(0);
        /* Pegando os Dados */
        txt_vendedor.setText(tbl_vendedor.getValueAt(tbl_vendedor.getSelectedRow(), 1).toString());

        Funcionario funcionario = new Funcionario();

        funcionario.setCod_funcionario((int) tbl_vendedor.getValueAt(tbl_vendedor.getSelectedRow(), 0));

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
        btn_selecionar_cliente.setEnabled(false);

        Dialog_Vendedor.dispose();
    }//GEN-LAST:event_tbl_vendedorMouseClicked

    private void Dialog_VendedorWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendedorWindowStateChanged

    }//GEN-LAST:event_Dialog_VendedorWindowStateChanged

    private void Dialog_VendedorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Dialog_VendedorPropertyChange

    }//GEN-LAST:event_Dialog_VendedorPropertyChange

    private void txt_buscaVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscaVendedorKeyPressed

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
    }//GEN-LAST:event_txt_buscaVendedorKeyPressed

    private void tbl_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_produtoMouseClicked
        jTabbedPane2.setSelectedIndex(0);
        /* Pegando os Dados */
        txt_nomeProduto.setText(tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 1).toString());
        produto = new Produto();

        produto.setCod_produto((int) tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 0));
        txtPrecoUnit.setText((String) tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 5));
        txtQuantidade.requestFocus();
        txtPrecoUnit.setVisible(true);
        txtQuantidade.setVisible(true);

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);

        Dialog_Produto.dispose();
    }//GEN-LAST:event_tbl_produtoMouseClicked

    private void txt_busca_produtoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busca_produtoKeyPressed
        String nome = "%" + txt_busca_produto.getText() + "%";

        produtoDAO = new ProdutoDAO();
        List<Produto> lista2 = produtoDAO.listaProduto(nome);
        DefaultTableModel dados2 = (DefaultTableModel) tbl_produto.getModel();
        dados2.setNumRows(0);
        for (Produto c : lista2) {
            dados2.addRow(new Object[]{
                c.getCod_produto(),
                c.getDescricao(),
                c.getCodigo_barras(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),});
        }
        abreCampos();
        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
    }//GEN-LAST:event_txt_busca_produtoKeyPressed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        btnCancelarProdutos.setEnabled(true);
        btnSalvarVenda.setVisible(true);
        btnSalvarVenda.setEnabled(true);
        btnAdicionar.setEnabled(true);
        btnSubtrair.setEnabled(true);
        btn_selecionar_cliente.setEnabled(true);
        btn_selecionar_produto.setEnabled(true);
        btnSelecionarVendedor.setEnabled(true);
        abreCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void Dialog_ProdutoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ProdutoWindowOpened
        listarProdutos();
    }//GEN-LAST:event_Dialog_ProdutoWindowOpened

    private void Dialog_VendedorWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendedorWindowOpened
        try {
            listarVendedor();
        } catch (ParseException ex) {
            Logger.getLogger(VendasVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Dialog_VendedorWindowOpened

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (txtPrecoUnit.getText().isEmpty() || txtQuantidade.getText().isEmpty()
                || txt_NomeCliente.getText().isEmpty()
                || txt_nomeProduto.getText().isEmpty()
                || txt_vendedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int qtd = Integer.parseInt(txtQuantidade.getText());

            DefaultTableModel table = (DefaultTableModel) tabelaFornecedores.getModel();
            NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
            ((DecimalFormat) df).applyPattern("0.00");
            table.addRow(new Object[]{
                txt_nomeProduto.getText(),
                txtQuantidade.getText(),
                txtPrecoUnit.getText(),
                df.format(Float.parseFloat((String) txtPrecoUnit.getText()) * Float.parseFloat((String) txtQuantidade.getText())),});

            txtPrecoUnit.setText("");
            txtQuantidade.setText("");
            txt_nomeProduto.setText("");
            atualizaLabel();
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSelecionarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarVendedorActionPerformed
        Dialog_Vendedor.setLocationRelativeTo(null);
        Dialog_Vendedor.setVisible(true);
    }//GEN-LAST:event_btnSelecionarVendedorActionPerformed

    private void btnSubtrairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubtrairActionPerformed
        if (tabelaFornecedores.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) tabelaFornecedores.getModel();

            int[] rows = tabelaFornecedores.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                model.removeRow(rows[i] - i);
            }
            atualizaLabel();
        }
    }//GEN-LAST:event_btnSubtrairActionPerformed

    private void txt_buscaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscaVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscaVendedorActionPerformed

    private void txt_buscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscaClienteActionPerformed

    private void txt_buscaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscaClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscaClienteKeyPressed

    private void tbl_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClienteMouseClicked
        jTabbedPane2.setSelectedIndex(0);
        /* Pegando os Dados */
        txt_NomeCliente.setText(tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 1).toString());
        Cliente cliente = new Cliente();

        cliente.setCod_cliente((int) tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 0));

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);

        Dialog_Cliente.dispose();
    }//GEN-LAST:event_tbl_ClienteMouseClicked

    private void Dialog_ClienteWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ClienteWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_ClienteWindowStateChanged

    private void Dialog_ClientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Dialog_ClientePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_ClientePropertyChange

    private void Dialog_ClienteWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ClienteWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_ClienteWindowOpened

    private void rbn_juridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbn_juridicaActionPerformed
        rbn_fisica.setSelected(false);
        listarJuridica();
    }//GEN-LAST:event_rbn_juridicaActionPerformed

    private void rbn_fisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbn_fisicaActionPerformed
        rbn_juridica.setSelected(false);
        listarFisica();
    }//GEN-LAST:event_rbn_fisicaActionPerformed

    private void tbl_ClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_ClienteKeyPressed

    }//GEN-LAST:event_tbl_ClienteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Cliente;
    private javax.swing.JDialog Dialog_Produto;
    private javax.swing.JDialog Dialog_Vendedor;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelarProdutos;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvarVenda;
    private javax.swing.JButton btnSelecionarVendedor;
    private javax.swing.JButton btnSubtrair;
    private javax.swing.JButton btn_selecionar_cliente;
    private javax.swing.JButton btn_selecionar_produto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel labelTotalProduto;
    private javax.swing.JRadioButton rbn_fisica;
    private javax.swing.JRadioButton rbn_juridica;
    private javax.swing.JLabel resultadoTotal;
    private javax.swing.JTable tabelaFornecedores;
    private javax.swing.JTable tbl_Cliente;
    private javax.swing.JTable tbl_produto;
    private javax.swing.JTable tbl_vendedor;
    private javax.swing.JTextField txtPrecoUnit;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txt_NomeCliente;
    private javax.swing.JTextField txt_buscaCliente;
    private javax.swing.JTextField txt_buscaVendedor;
    private javax.swing.JTextField txt_busca_produto;
    private javax.swing.JTextField txt_nomeProduto;
    private javax.swing.JTextField txt_vendedor;
    // End of variables declaration//GEN-END:variables

}
