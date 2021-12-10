package VIEW;

import DAO.CategoriaDAO;
import DAO.EntradaDAO;
import DAO.FornecedorDAO;
import DAO.MarcaProduto_DAO;
import DAO.ProdutoDAO;
import MODEL.CategoriaProduto;
import MODEL.Entrada;
import MODEL.Fornecedor;
import MODEL.ItensEntrada;
import MODEL.MarcaProduto;
import MODEL.Produto;
import MODEL.Usuario;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EntradaVIEW extends javax.swing.JFrame {

    Fornecedor fornecedor;
    FornecedorDAO fornecedorDAO;

    Produto produto;
    CategoriaProduto categoria;
    MarcaProduto marca;

    ProdutoDAO produtoDAO;
    MarcaProduto_DAO marcaDAO;
    CategoriaDAO categoriaDAO;
    ItensEntrada itensEntrada;
    Entrada entrada;
    EntradaDAO entradaDao;
    

    Usuario user;

    public EntradaVIEW(Usuario user) {
        initComponents();
        this.setLocationRelativeTo(null);
        fornecedorDAO = new FornecedorDAO();
        
        txt_nomeProduto.setEnabled(false);
        txt_Nomeforneceedor.setEnabled(false);
        
        if (user.getNivel_acesso() == 1) {
            this.dispose();
        } else {

        }
        fechaBotoes();
        fechaCampos();
    }

    public void listarFornecedores() {
        fornecedorDAO = new FornecedorDAO();
        List<Fornecedor> lista = fornecedorDAO.listarFornecedores();
        DefaultTableModel dadosTable = (DefaultTableModel) tbl_fornecedor.getModel();
        dadosTable.setNumRows(0);
        for (Fornecedor f : lista) {
            dadosTable.addRow(new Object[]{
                f.getCod_fornecedor(),
                f.getNome(),
                f.getCnpj(),
                f.getInscricao_estadual(),});
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
                c.getMarca().getNome_marca(),});
        }
    }

    public void fechaBotoes() {
        btnSalvarProdutos.setEnabled(false);
        btnCancelarProdutos.setEnabled(false);
        btnAdicionar.setEnabled(false);
        btnSubtrair.setEnabled(false);
        btn_selecionar_fornecedor.setEnabled(false);
        btn_selecionar_produto.setEnabled(false);
        btnConfirmaNF.setEnabled(false);
    }

    public void fechaCampos() {
        txtPrecoUnit.setEnabled(false);
        txtQuantidade.setEnabled(false);
        txtValorNota.setEnabled(false);
        txt_Nomeforneceedor.setEnabled(false);
        txt_chavedeacesso.setEnabled(false);
        txt_nomeProduto.setEnabled(false);
        txt_notafiscal.setEnabled(false);
    }

    public void abreCampos() {
        txtPrecoUnit.setEnabled(true);
        txtQuantidade.setEnabled(true);
        txtValorNota.setEnabled(true);
        
        txt_chavedeacesso.setEnabled(true);
        
        txt_notafiscal.setEnabled(true);
    }

    public void limpaCampos() {
        txtPrecoUnit.setText("");
        txtQuantidade.setText("");
        txtValorNota.setText("");
        txt_Nomeforneceedor.setText("");
        txt_chavedeacesso.setText("");
        txt_nomeProduto.setText("");
        txt_notafiscal.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtPrecoUnit.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtValorNota.getText().isEmpty()
                || txt_Nomeforneceedor.getText().isEmpty() || txt_chavedeacesso.getText().isEmpty()
                || txt_nomeProduto.getText().isEmpty() || txt_notafiscal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            listaCampos.add(txtPrecoUnit.getText());
            listaCampos.add(txtQuantidade.getText());
            listaCampos.add(txtValorNota.getText());
            listaCampos.add(txt_Nomeforneceedor.getText());
            listaCampos.add(txt_chavedeacesso.getText());
            listaCampos.add(txt_nomeProduto.getText());
            listaCampos.add(txt_notafiscal.getText());
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
        Dialog_Fornecedor = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txt_buscaFornecedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_fornecedor = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarProdutos = new javax.swing.JButton();
        btnCancelarProdutos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        txtPrecoUnit = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        txtValorNota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFornecedores = new javax.swing.JTable();
        btnSubtrair = new javax.swing.JButton();
        txt_Nomeforneceedor = new javax.swing.JTextField();
        btn_selecionar_fornecedor = new javax.swing.JButton();
        txt_nomeProduto = new javax.swing.JTextField();
        btn_selecionar_produto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_notafiscal = new javax.swing.JTextField();
        txt_chavedeacesso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnConfirmaNF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();

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
                "ID", "Nome", "Cod. Barras", "Categoria", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        Dialog_Fornecedor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Fornecedor.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_Fornecedor.setMinimumSize(new java.awt.Dimension(859, 429));
        Dialog_Fornecedor.setModal(true);
        Dialog_Fornecedor.setName("Dialog_Fornecedor"); // NOI18N
        Dialog_Fornecedor.setResizable(false);
        Dialog_Fornecedor.addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                Dialog_FornecedorWindowStateChanged(evt);
            }
        });
        Dialog_Fornecedor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Dialog_FornecedorPropertyChange(evt);
            }
        });
        Dialog_Fornecedor.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_FornecedorWindowOpened(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Razão Social");

        txt_buscaFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscaFornecedorKeyPressed(evt);
            }
        });

        tbl_fornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Razão Social", "CNPJ", "IE"
            }
        ));
        tbl_fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_fornecedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_fornecedor);

        javax.swing.GroupLayout Dialog_FornecedorLayout = new javax.swing.GroupLayout(Dialog_Fornecedor.getContentPane());
        Dialog_Fornecedor.getContentPane().setLayout(Dialog_FornecedorLayout);
        Dialog_FornecedorLayout.setHorizontalGroup(
            Dialog_FornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_FornecedorLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(Dialog_FornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Dialog_FornecedorLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Dialog_FornecedorLayout.setVerticalGroup(
            Dialog_FornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_FornecedorLayout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(Dialog_FornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_buscaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("Fornecedor");

        jLabel7.setText("Quantidade");

        jLabel8.setText("Preço Unitário");

        jLabel9.setText("Produto");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvarProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvarProdutos.setText("Salvar");
        btnSalvarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProdutosActionPerformed(evt);
            }
        });

        btnCancelarProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarProdutos.setText("Cancelar");
        btnCancelarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProdutosActionPerformed(evt);
            }
        });

        jLabel11.setText("Valor Total da Nota");

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
                "Produto", "Quantidade", "Preço Unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        btn_selecionar_fornecedor.setText("Selecionar");
        btn_selecionar_fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selecionar_fornecedorActionPerformed(evt);
            }
        });

        btn_selecionar_produto.setText("Selecionar");
        btn_selecionar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selecionar_produtoActionPerformed(evt);
            }
        });

        jLabel5.setText("Nota Fiscal");

        jLabel6.setText("Chave de Acesso");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Dados notal fiscal");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Dados produto");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Dados fornecedor");

        btnConfirmaNF.setText("Confirmar NF");
        btnConfirmaNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaNFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPrecoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_selecionar_produto)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSubtrair, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_Nomeforneceedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_selecionar_fornecedor)))
                        .addGap(472, 472, 472))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(130, 130, 130))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(110, 110, 110)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(btnSalvarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCancelarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_notafiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_chavedeacesso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorNota, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnConfirmaNF)
                            .addComponent(jLabel20))))
                .addGap(107, 107, 107))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubtrair)
                            .addComponent(btnAdicionar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_notafiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txt_nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_selecionar_produto)
                                .addComponent(jLabel2)
                                .addComponent(txt_Nomeforneceedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(btn_selecionar_fornecedor)
                                .addComponent(btnConfirmaNF)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_chavedeacesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtPrecoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValorNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(15, 15, 15)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvarProdutos)
                        .addComponent(btnCancelarProdutos))
                    .addComponent(jLabel20))
                .addGap(20, 20, 20))
        );

        jTabbedPane2.addTab("Dados entrada", jPanel2);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Entrada");

        btnNovo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutosActionPerformed

    }//GEN-LAST:event_btnSalvarProdutosActionPerformed

    private void btnCancelarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutosActionPerformed

        btnNovo.setEnabled(true);
        tabelaFornecedores.removeAll();
        limpaCampos();
        fechaBotoes();
        fechaCampos();
    }//GEN-LAST:event_btnCancelarProdutosActionPerformed

    private void tabelaFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedoresMouseClicked

    }//GEN-LAST:event_tabelaFornecedoresMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btn_selecionar_fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selecionar_fornecedorActionPerformed
        Dialog_Fornecedor.setLocationRelativeTo(null);
        Dialog_Fornecedor.setVisible(true);
        listarFornecedores();
    }//GEN-LAST:event_btn_selecionar_fornecedorActionPerformed

    private void btn_selecionar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selecionar_produtoActionPerformed
        Dialog_Produto.setLocationRelativeTo(null);
        Dialog_Produto.setVisible(true);
        listarProdutos();
    }//GEN-LAST:event_btn_selecionar_produtoActionPerformed

    private void tbl_fornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_fornecedorMouseClicked

        jTabbedPane2.setSelectedIndex(0);
        /* Pegando os Dados */
        txt_Nomeforneceedor.setText(tbl_fornecedor.getValueAt(tbl_fornecedor.getSelectedRow(), 1).toString());
        
        fornecedor = new Fornecedor();
        
        fornecedor.setCod_fornecedor((int) tbl_fornecedor.getValueAt(tbl_fornecedor.getSelectedRow(), 0));

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
        btn_selecionar_fornecedor.setEnabled(false);
        
        Dialog_Fornecedor.dispose();
    }//GEN-LAST:event_tbl_fornecedorMouseClicked

    private void Dialog_FornecedorWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_FornecedorWindowStateChanged

    }//GEN-LAST:event_Dialog_FornecedorWindowStateChanged

    private void Dialog_FornecedorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Dialog_FornecedorPropertyChange

    }//GEN-LAST:event_Dialog_FornecedorPropertyChange

    private void txt_buscaFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscaFornecedorKeyPressed

        String nome = "%" + txt_buscaFornecedor.getText() + "%";

        fornecedorDAO = new FornecedorDAO();

        List<Fornecedor> lista2 = fornecedorDAO.listarFornecedores(nome);
        DefaultTableModel dados = (DefaultTableModel) tbl_fornecedor.getModel();

        dados.setNumRows(0);

        for (Fornecedor f : lista2) {
            dados.addRow(new Object[]{
                f.getCod_fornecedor(),
                f.getNome(),
                f.getCnpj(),
                f.getInscricao_estadual(),});
        }

        btnNovo.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
    }//GEN-LAST:event_txt_buscaFornecedorKeyPressed

    private void tbl_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_produtoMouseClicked
        jTabbedPane2.setSelectedIndex(0);
        /* Pegando os Dados */
        txt_nomeProduto.setText(tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 1).toString());
        produto = new Produto();
        
        produto.setCod_produto((int) tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 0));
        
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
        btnSalvarProdutos.setVisible(true);
        btnSalvarProdutos.setEnabled(true);
        btnAdicionar.setEnabled(true);
        btnSubtrair.setEnabled(true);
        btn_selecionar_fornecedor.setEnabled(true);
        btn_selecionar_produto.setEnabled(true);
        btnConfirmaNF.setEnabled(true);
        abreCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void Dialog_ProdutoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ProdutoWindowOpened
        listarProdutos();
    }//GEN-LAST:event_Dialog_ProdutoWindowOpened

    private void Dialog_FornecedorWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_FornecedorWindowOpened
        listarFornecedores();
    }//GEN-LAST:event_Dialog_FornecedorWindowOpened

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if(txtPrecoUnit.getText().isEmpty() || txtQuantidade.getText().isEmpty() ||
                txtValorNota.getText().isEmpty() || txt_Nomeforneceedor.getText().isEmpty() ||
                txt_chavedeacesso.getText().isEmpty() || txt_nomeProduto.getText().isEmpty() ||
                txt_notafiscal.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int qtd = Integer.parseInt(txtQuantidade.getText());
            
            DefaultTableModel table = (DefaultTableModel) tabelaFornecedores.getModel();
            
            table.addRow(new Object[]{
                
                txt_nomeProduto.getText(),
                txtQuantidade.getText(), 
                txtPrecoUnit.getText(),
                
            });
            
            txtPrecoUnit.setText("");
            txtQuantidade.setText("");
            txt_nomeProduto.setText("");
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnConfirmaNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaNFActionPerformed
        if(txt_chavedeacesso.getText().isEmpty() || txt_notafiscal.getText().isEmpty() ||
                txtValorNota.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            txt_chavedeacesso.setEnabled(false);
            txt_notafiscal.setEnabled(false);
            txtValorNota.setEnabled(false);
            btnConfirmaNF.setEnabled(false);
        }
    }//GEN-LAST:event_btnConfirmaNFActionPerformed

    private void btnSubtrairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubtrairActionPerformed
        if(tabelaFornecedores.getSelectedRow() != -1){
            DefaultTableModel model = (DefaultTableModel) tabelaFornecedores.getModel();
            
            int[] rows = tabelaFornecedores.getSelectedRows();
            for(int i = 0; i < rows.length; i++){
                model.removeRow(rows[i] - i);
            }
        }
    }//GEN-LAST:event_btnSubtrairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Fornecedor;
    private javax.swing.JDialog Dialog_Produto;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelarProdutos;
    private javax.swing.JButton btnConfirmaNF;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvarProdutos;
    private javax.swing.JButton btnSubtrair;
    private javax.swing.JButton btn_selecionar_fornecedor;
    private javax.swing.JButton btn_selecionar_produto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tabelaFornecedores;
    private javax.swing.JTable tbl_fornecedor;
    private javax.swing.JTable tbl_produto;
    private javax.swing.JTextField txtPrecoUnit;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValorNota;
    private javax.swing.JTextField txt_Nomeforneceedor;
    private javax.swing.JTextField txt_buscaFornecedor;
    private javax.swing.JTextField txt_busca_produto;
    private javax.swing.JTextField txt_chavedeacesso;
    private javax.swing.JTextField txt_nomeProduto;
    private javax.swing.JTextField txt_notafiscal;
    // End of variables declaration//GEN-END:variables
}
