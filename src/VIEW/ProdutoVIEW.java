package VIEW;

/* Importações do DAO */
import DAO.CategoriaDAO;
import DAO.MarcaProduto_DAO;
import DAO.ProdutoDAO;
/* Importações do MODEL */
import MODEL.CategoriaProduto;
import MODEL.MarcaProduto;
import MODEL.Produto;
import MODEL.Usuario;
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
/* Importações do JAVA */
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoVIEW extends javax.swing.JFrame {

    Produto produto;
    CategoriaProduto categoria;
    MarcaProduto marca;

    ProdutoDAO produtoDAO;
    MarcaProduto_DAO marcaDAO;
    CategoriaDAO categoriaDAO;
    Usuario user;

    public ProdutoVIEW(Usuario user) {

        initComponents();
        this.setLocationRelativeTo(null);
        produtoDAO = new ProdutoDAO();
        marcaDAO = new MarcaProduto_DAO();
        categoriaDAO = new CategoriaDAO();
        txtCodProdutos.setEnabled(false);
        btnAlterarProdutos.setVisible(false);
        txtBuscaProdutos.setEnabled(true);
        fechaBotoes();
        fechaCampos();
        listarProdutos();
        listarCategoria();
        listarMarca();

        if (user.getNivel_acesso() == 1) {
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setSelectedIndex(1);
            btnNovoProdutos.setVisible(false);

        } else if (user.getNivel_acesso() == 2) {
            btnExcluirProdutos.setVisible(false);

        } else {

        }

    }

    public void listaProdutosNome() {

        String nome = "%" + txtBuscaProdutos.getText() + "%";

        produtoDAO = new ProdutoDAO();
        List<Produto> lista2 = produtoDAO.listaProduto(nome);
        DefaultTableModel dados2 = (DefaultTableModel) tabelaConsultaProdutos.getModel();
        dados2.setNumRows(0);
        for (Produto c : lista2) {
            dados2.addRow(new Object[]{
                c.getCod_produto(),
                c.getDescricao(),
                c.getQuantidade(),
                c.getQuantidadeMinima(),
                c.getCodigo_barras(),
                c.getValor_custo(),
                c.getValor_venda(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),
                c.getPorcentagem(),});
        }
    }

    public void listarProdutos() {
        produtoDAO = new ProdutoDAO();
        List<Produto> lista = produtoDAO.listaProduto();
        DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();
        dados.setNumRows(0);
        for (Produto c : lista) {
            dados.addRow(new Object[]{
                c.getCod_produto(),
                c.getDescricao(),
                c.getQuantidade(),
                c.getQuantidadeMinima(),
                c.getCodigo_barras(),
                c.getValor_custo(),
                c.getValor_venda(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),
                c.getPorcentagem(),});
        }
    }

    public void listaQuantidade() {
        produtoDAO = new ProdutoDAO();
        List<Produto> lista = produtoDAO.listaQuantidade();
        DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();
        dados.setNumRows(0);
        for (Produto c : lista) {
            dados.addRow(new Object[]{
                c.getCod_produto(),
                c.getDescricao(),
                c.getQuantidade(),
                c.getQuantidadeMinima(),
                c.getCodigo_barras(),
                c.getValor_custo(),
                c.getValor_venda(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),
                c.getPorcentagem(),});
        }
    }

    public void listarCategoria() {
        categoriaDAO = new CategoriaDAO();
        for (CategoriaProduto c : categoriaDAO.listarCategorias()) {
            jcbCategoria.addItem(c.getNome_categoria());
        }

    }

    public void listarMarca() {
        marcaDAO = new MarcaProduto_DAO();
        for (MarcaProduto c : marcaDAO.listarMarcas()) {
            jcbMarca.addItem(c.getNome_marca());
        }
    }

    public void fechaBotoes() {
        btnSalvarProdutos.setEnabled(false);
        btnCancelarProdutos.setEnabled(false);
        btnExcluirProdutos.setEnabled(false);
    }

    public void fechaCampos() {
        txtCodigoBarras.setEnabled(false);
        txtValorVenda.setEnabled(false);
        txtDescricaoProdutos.setEnabled(false);
        txtQuantidadeProd.setEnabled(false);
        txtQuantidadeMinProd.setEnabled(false);
        txtValorCustoProd.setEnabled(false);
        txt_porcentagem.setEnabled(false);
        jcbCategoria.setEnabled(false);
        jcbMarca.setEnabled(false);
    }

    public void abreCampos() {
        txtCodigoBarras.setEnabled(true);
        txtValorVenda.setEnabled(false);
        txtDescricaoProdutos.setEnabled(true);
        txtQuantidadeProd.setEnabled(true);
        txtQuantidadeMinProd.setEnabled(true);
        txtValorCustoProd.setEnabled(true);
        txt_porcentagem.setEnabled(true);
        jcbCategoria.setEnabled(true);
        jcbMarca.setEnabled(true);
    }

    public void limpaCampos() {
        txtCodigoBarras.setText("");
        txtValorVenda.setText("");
        txtDescricaoProdutos.setText("");
        txtQuantidadeProd.setText("");
        txtQuantidadeMinProd.setText("");
        txtValorCustoProd.setText("");
        txtCodProdutos.setText("");
        jcbCategoria.setSelectedIndex(0);
        jcbMarca.setSelectedIndex(0);
        txt_porcentagem.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtDescricaoProdutos.getText().isEmpty() || txtQuantidadeProd.getText().isEmpty() || txtQuantidadeMinProd.getText().isEmpty()
                || txtCodigoBarras.getText().isEmpty() || txtValorVenda.getText().isEmpty()
                || txtValorCustoProd.getText().isEmpty() || txt_porcentagem.getText().isEmpty() || jcbCategoria.getSelectedItem() == "Selecione" || jcbMarca.getSelectedItem() == "Selecione") {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            listaCampos.add(txtDescricaoProdutos.getText());
            listaCampos.add(txtQuantidadeMinProd.getText());
            listaCampos.add(txtCodigoBarras.getText());
            listaCampos.add(txtValorCustoProd.getText());
            listaCampos.add(txtValorVenda.getText());
            listaCampos.add(txtQuantidadeProd.getText());
            listaCampos.add(txt_porcentagem.getText());
            return listaCampos;
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_Produto = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        txt_relCategoria = new javax.swing.JTextField();
        btn_RelCategoria = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_produto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnl_cadastraprodutos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtQuantidadeProd = new javax.swing.JTextField();
        txtQuantidadeMinProd = new javax.swing.JTextField();
        txtCodigoBarras = new javax.swing.JTextField();
        txtCodProdutos = new javax.swing.JTextField();
        txtDescricaoProdutos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarProdutos = new javax.swing.JButton();
        btnCancelarProdutos = new javax.swing.JButton();
        btnExcluirProdutos = new javax.swing.JButton();
        btnAlterarProdutos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jcbCategoria = new javax.swing.JComboBox<>();
        jcbMarca = new javax.swing.JComboBox<>();
        btnMarca = new javax.swing.JToggleButton();
        btnCategoria = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        txt_porcentagem = new javax.swing.JTextField();
        txtValorCustoProd = new javax.swing.JTextField();
        txtValorVenda = new javax.swing.JTextField();
        pnl_consultaproduto = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscaProdutos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultaProdutos = new javax.swing.JTable();
        rbt_quantidaMinima = new javax.swing.JRadioButton();
        btn_relproduto = new javax.swing.JToggleButton();
        opc_Categoria = new javax.swing.JRadioButton();
        btnNovoProdutos = new javax.swing.JButton();

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

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Buscar");

        txt_relCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_relCategoriaActionPerformed(evt);
            }
        });
        txt_relCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_relCategoriaKeyPressed(evt);
            }
        });

        btn_RelCategoria.setText("Relatório Categoria");
        btn_RelCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RelCategoriaActionPerformed(evt);
            }
        });

        tbl_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_relCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_RelCategoria)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Dialog_ProdutoLayout.setVerticalGroup(
            Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ProdutoLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_relCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(btn_RelCategoria, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Produtos");

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

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        pnl_cadastraprodutos.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cadastraprodutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_cadastraprodutosMouseClicked(evt);
            }
        });

        jLabel2.setText("Código");

        jLabel3.setText("Nome");

        jLabel6.setText("Valor custo");

        jLabel7.setText("Quantidade mínima");

        jLabel8.setText("Código de barras");

        jLabel9.setText("Quantidade");

        jLabel10.setText("Valor venda");

        txtQuantidadeMinProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeMinProdActionPerformed(evt);
            }
        });

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

        btnExcluirProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluirProdutos.setText("Excluir");
        btnExcluirProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutosActionPerformed(evt);
            }
        });

        btnAlterarProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterarProdutos.setText("Alterar");
        btnAlterarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarProdutosActionPerformed(evt);
            }
        });

        jLabel5.setText("Categoria");

        jLabel11.setText("Marca");

        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jcbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbCategoriaMouseClicked(evt);
            }
        });
        jcbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCategoriaActionPerformed(evt);
            }
        });

        jcbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jcbMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbMarcaMouseClicked(evt);
            }
        });
        jcbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMarcaActionPerformed(evt);
            }
        });

        btnMarca.setText("Adicionar Marcas");
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });

        btnCategoria.setText("Adicionar Categorias");
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        jLabel4.setText("Taxa de Lucro");

        txt_porcentagem.setEnabled(false);
        txt_porcentagem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_porcentagemCaretUpdate(evt);
            }
        });
        txt_porcentagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_porcentagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_cadastraprodutosLayout = new javax.swing.GroupLayout(pnl_cadastraprodutos);
        pnl_cadastraprodutos.setLayout(pnl_cadastraprodutosLayout);
        pnl_cadastraprodutosLayout.setHorizontalGroup(
            pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addComponent(txtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValorCustoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                                        .addComponent(txtQuantidadeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_porcentagem, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jcbCategoria, 0, 200, Short.MAX_VALUE)
                                    .addComponent(jcbMarca, 0, 200, Short.MAX_VALUE)
                                    .addComponent(txtValorVenda))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCategoria)
                            .addComponent(btnMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(168, 168, 168))
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescricaoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidadeMinProd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addComponent(btnAlterarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addContainerGap(34, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))))))
        );
        pnl_cadastraprodutosLayout.setVerticalGroup(
            pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtValorCustoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_porcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescricaoProdutos))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantidadeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(3, 3, 3)))
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidadeMinProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jcbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMarca)
                            .addComponent(jLabel8)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(btnCategoria))
                        .addGap(76, 76, 76)))
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_cadastraprodutosLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel20))
                    .addGroup(pnl_cadastraprodutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirProdutos)
                        .addComponent(btnAlterarProdutos)
                        .addComponent(btnSalvarProdutos)
                        .addComponent(btnCancelarProdutos)))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Dados produtos", pnl_cadastraprodutos);

        pnl_consultaproduto.setBackground(new java.awt.Color(255, 255, 255));
        pnl_consultaproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_consultaprodutoMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Buscar");

        txtBuscaProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaProdutosActionPerformed(evt);
            }
        });
        txtBuscaProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaProdutosKeyPressed(evt);
            }
        });

        tabelaConsultaProdutos.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaConsultaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Quantidade", "Quantidade mínima", "Código de Barra", "Valor custo", "Valor venda", "Categoria", "Marca", "Taxa de Lucro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConsultaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaConsultaProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConsultaProdutos);

        rbt_quantidaMinima.setText("Quantidade Miníma");
        rbt_quantidaMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbt_quantidaMinimaMouseClicked(evt);
            }
        });
        rbt_quantidaMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_quantidaMinimaActionPerformed(evt);
            }
        });

        btn_relproduto.setText("Relatório de Estoque");
        btn_relproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relprodutoActionPerformed(evt);
            }
        });

        opc_Categoria.setText("Categoria");
        opc_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opc_CategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_consultaprodutoLayout = new javax.swing.GroupLayout(pnl_consultaproduto);
        pnl_consultaproduto.setLayout(pnl_consultaprodutoLayout);
        pnl_consultaprodutoLayout.setHorizontalGroup(
            pnl_consultaprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_consultaprodutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbt_quantidaMinima)
                .addGap(18, 18, 18)
                .addComponent(opc_Categoria)
                .addGap(18, 18, 18)
                .addComponent(btn_relproduto)
                .addContainerGap(330, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnl_consultaprodutoLayout.setVerticalGroup(
            pnl_consultaprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_consultaprodutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_consultaprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbt_quantidaMinima)
                    .addComponent(btn_relproduto)
                    .addComponent(opc_Categoria))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de produtos", pnl_consultaproduto);

        btnNovoProdutos.setBackground(new java.awt.Color(255, 255, 255));
        btnNovoProdutos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovoProdutos.setText("Novo");
        btnNovoProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoProdutosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNovoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoProdutos)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoProdutosActionPerformed

        txtBuscaProdutos.setEnabled(true);
        //btnBuscar.setEnabled(false);
        btnNovoProdutos.setEnabled(false);
        btnExcluirProdutos.setEnabled(false);
        btnAlterarProdutos.setVisible(false);
        btnAlterarProdutos.setEnabled(false);
        btnCancelarProdutos.setEnabled(true);
        btnSalvarProdutos.setVisible(true);
        btnSalvarProdutos.setEnabled(true);
        txtQuantidadeProd.setEnabled(true);
        abreCampos();
    }//GEN-LAST:event_btnNovoProdutosActionPerformed

    private void btnCancelarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutosActionPerformed

        btnAlterarProdutos.setEnabled(false);
        btnAlterarProdutos.setVisible(false);
        btnNovoProdutos.setEnabled(true);
        limpaCampos();
        fechaBotoes();
        fechaCampos();
    }//GEN-LAST:event_btnCancelarProdutosActionPerformed

    private void btnSalvarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutosActionPerformed
        List<String> listaCampos = getCampos();

        if (jcbCategoria.getSelectedItem() == "Selecione" || jcbMarca.getSelectedItem() == "Selecione") {
            JOptionPane.showMessageDialog(null, "Selecione a Marca e Categoria");
        } else {
            produto = new Produto();
            produto.setDescricao(listaCampos.get(0));
            produto.setQuantidadeMinima(Integer.parseInt(listaCampos.get(1)));
            produto.setCodigo_barras(listaCampos.get(2));
            produto.setValor_custo(listaCampos.get(3));
            produto.setValor_venda(listaCampos.get(4));
            produto.setQuantidade(Integer.parseInt(listaCampos.get(5)));
            produto.setPorcentagem(listaCampos.get(6));
            produto.setMarca(marca);

            marca = new MarcaProduto();
            marca.setNome_marca((String) jcbMarca.getSelectedItem());

            produto.setCategoria(categoria);

            categoria = new CategoriaProduto();
            categoria.setNome_categoria((String) jcbCategoria.getSelectedItem());

            try {
                produtoDAO.SalvarProduto(produto, marca, categoria);

            } catch (SQLException ex) {
                Logger.getLogger(ProdutoVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

            limpaCampos();
            fechaCampos();
            fechaBotoes();

            btnNovoProdutos.setEnabled(true);
            txtBuscaProdutos.setEnabled(true);

            jcbCategoria.setSelectedIndex(0);
            jcbMarca.setSelectedIndex(0);
            tabelaConsultaProdutos.removeAll();
            listarProdutos();
        }
    }//GEN-LAST:event_btnSalvarProdutosActionPerformed

    private void txtBuscaProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaProdutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaProdutosActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void pnl_consultaprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_consultaprodutoMouseClicked

    }//GEN-LAST:event_pnl_consultaprodutoMouseClicked

    private void pnl_cadastraprodutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_cadastraprodutosMouseClicked

    }//GEN-LAST:event_pnl_cadastraprodutosMouseClicked

    private void btnAlterarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarProdutosActionPerformed
        List<String> listaCampos = getCampos();

        produto = new Produto();

        produto.setDescricao(txtDescricaoProdutos.getText());

        produto.setMarca(marca);
        marca = new MarcaProduto();

        marca.setNome_marca((String) jcbMarca.getSelectedItem());

        produto.setCategoria(categoria);

        categoria = new CategoriaProduto();
        categoria.setNome_categoria((String) jcbCategoria.getSelectedItem());

        produto.setQuantidade(Integer.parseInt(txtQuantidadeProd.getText()));
        produto.setQuantidadeMinima(Integer.parseInt(txtQuantidadeMinProd.getText()));
        produto.setCodigo_barras(txtCodigoBarras.getText());
        produto.setValor_custo(txtValorCustoProd.getText());
        produto.setValor_venda(txtValorVenda.getText());
        produto.setPorcentagem(txt_porcentagem.getText());
        produto.setCod_produto(Integer.parseInt(txtCodProdutos.getText()));

        try {
            produtoDAO.alterarProduto(produto, marca, categoria);

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoVIEW.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        limpaCampos();
        fechaCampos();
        fechaBotoes();

        btnNovoProdutos.setEnabled(true);
        txtBuscaProdutos.setEnabled(true);
        btnAlterarProdutos.setEnabled(false);
        jcbCategoria.setSelectedIndex(0);
        jcbMarca.setSelectedIndex(0);
        tabelaConsultaProdutos.removeAll();
        listarProdutos();

        if (user.getNivel_acesso() == 2) {
            btnExcluirProdutos.setVisible(false);

        }
    }//GEN-LAST:event_btnAlterarProdutosActionPerformed

    private void btnExcluirProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutosActionPerformed
        produto = new Produto();
        produto.setCod_produto(Integer.parseInt(txtCodProdutos.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtDescricaoProdutos.getText() + "?");

        if (confirma == 0) {
            try {
                produtoDAO.ExcluirProduto(produto);

            } catch (SQLException ex) {
                Logger.getLogger(ProdutoVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            limpaCampos();
            fechaBotoes();
            fechaCampos();
            btnAlterarProdutos.setVisible(false);
            btnSalvarProdutos.setVisible(true);
            btnNovoProdutos.setEnabled(true);
            txtBuscaProdutos.setEnabled(true);
            tabelaConsultaProdutos.removeAll();
            listarProdutos();
        }
    }//GEN-LAST:event_btnExcluirProdutosActionPerformed

    private void tabelaConsultaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaProdutosMouseClicked

        if (user.getNivel_acesso() == 1) {

        } else {

            /* Pegando os Dados */
            jTabbedPane1.setSelectedIndex(0);
            txtCodProdutos.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 0).toString());
            txtDescricaoProdutos.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 1).toString());
            txtQuantidadeProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 2).toString());
            txtQuantidadeMinProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 3).toString());
            txtCodigoBarras.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 4).toString());
            txtValorCustoProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 5).toString());
            txtValorVenda.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 6).toString());
            jcbCategoria.setSelectedItem(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 7).toString());
            jcbMarca.setSelectedItem(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 8).toString());
            txt_porcentagem.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 9).toString());
            abreCampos();
            btnSalvarProdutos.setVisible(false);
            btnNovoProdutos.setEnabled(false);
            btnAlterarProdutos.setVisible(true);
            btnAlterarProdutos.setEnabled(true);
            btnExcluirProdutos.setEnabled(true);
            btnCancelarProdutos.setEnabled(true);
            txtQuantidadeProd.setEnabled(false);
            if (user.getNivel_acesso() == 2) {
                btnExcluirProdutos.setVisible(false);

            }
        }
    }//GEN-LAST:event_tabelaConsultaProdutosMouseClicked

    private void txtBuscaProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaProdutosKeyPressed
        listaProdutosNome();
    }//GEN-LAST:event_txtBuscaProdutosKeyPressed

    private void txtQuantidadeMinProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeMinProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeMinProdActionPerformed

    private void btnMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcaActionPerformed
        MarcaVIEW marcatela = new MarcaVIEW();
        marcatela.setVisible(true);
    }//GEN-LAST:event_btnMarcaActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        CategoriaVIEW categoriatela = new CategoriaVIEW();
        categoriatela.setVisible(true);
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void rbt_quantidaMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbt_quantidaMinimaMouseClicked

    }//GEN-LAST:event_rbt_quantidaMinimaMouseClicked

    private void rbt_quantidaMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_quantidaMinimaActionPerformed
        if (rbt_quantidaMinima.isSelected()) {
            txtBuscaProdutos.setEnabled(false);
            btn_relproduto.setVisible(false);
            listaQuantidade();
            txtBuscaProdutos.setText("");
        } else {
            txtBuscaProdutos.setEnabled(true);
            btn_relproduto.setVisible(true);
            txtBuscaProdutos.setText("");
            listarProdutos();
        }
    }//GEN-LAST:event_rbt_quantidaMinimaActionPerformed

    private void jcbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCategoriaActionPerformed

    }//GEN-LAST:event_jcbCategoriaActionPerformed

    private void jcbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMarcaActionPerformed

    }//GEN-LAST:event_jcbMarcaActionPerformed

    private void jcbCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbCategoriaMouseClicked
        jcbCategoria.removeAllItems();
        jcbCategoria.addItem("Selecione");
        listarCategoria();
    }//GEN-LAST:event_jcbCategoriaMouseClicked

    private void jcbMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbMarcaMouseClicked
        jcbMarca.removeAllItems();
        jcbMarca.addItem("Selecione");
        listarMarca();
    }//GEN-LAST:event_jcbMarcaMouseClicked

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost

    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void txt_porcentagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_porcentagemActionPerformed

    }//GEN-LAST:event_txt_porcentagemActionPerformed

    private void txt_porcentagemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_porcentagemCaretUpdate

        if (txtValorCustoProd.getText().isEmpty() || txt_porcentagem.getText().isEmpty()) {

        } else {
            NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
            float porcentagem = (Float.parseFloat(txtValorCustoProd.getText()) * (Float.parseFloat(txt_porcentagem.getText()) / 100)) + Float.parseFloat(txtValorCustoProd.getText());
            ((DecimalFormat) df).applyPattern("0.00");
            txtValorVenda.setText(df.format(porcentagem));

        }
    }//GEN-LAST:event_txt_porcentagemCaretUpdate


    private void btn_relprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relprodutoActionPerformed
        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            produtoDAO.gerarDocumentoCompletoProdutos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_relprodutoActionPerformed

    private void txt_relCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_relCategoriaKeyPressed
        String nome = "%" + txt_relCategoria.getText() + "%";

        produtoDAO = new ProdutoDAO();
        List<Produto> lista2 = produtoDAO.listaProduto(nome);
        DefaultTableModel dados2 = (DefaultTableModel) tbl_produto.getModel();
        dados2.setNumRows(0);
        for (Produto c : lista2) {
            dados2.addRow(new Object[]{
                c.getCod_produto(),
                c.getCategoria().getNome_categoria(),
                c.getMarca().getNome_marca(),});
        }
        abreCampos();
        btn_relproduto.setVisible(false);
        btn_RelCategoria.setVisible(true);
        
        
    }//GEN-LAST:event_txt_relCategoriaKeyPressed

    private void tbl_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_produtoMouseClicked
        /* Pegando os Dados */
       
        txtCodProdutos.setText(tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 0).toString());
        txt_relCategoria.setText(tbl_produto.getValueAt(tbl_produto.getSelectedRow(), 1).toString());
      
    }//GEN-LAST:event_tbl_produtoMouseClicked

    private void Dialog_ProdutoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ProdutoWindowOpened
        listarProdutos();
    }//GEN-LAST:event_Dialog_ProdutoWindowOpened

    private void opc_CategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opc_CategoriaActionPerformed
        
        if (opc_Categoria.isSelected() == true) {
            Dialog_Produto.setLocationRelativeTo(null);
            Dialog_Produto.setVisible(true);
        }
    }//GEN-LAST:event_opc_CategoriaActionPerformed

    private void btn_RelCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RelCategoriaActionPerformed
         String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            produtoDAO.gerarRelatorioCategoria(txt_relCategoria.getText());
            Dialog_Produto.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_RelCategoriaActionPerformed

    private void txt_relCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_relCategoriaActionPerformed
         try {
            produtoDAO.gerarRelatorioCategoria(txt_relCategoria.getText());
            Dialog_Produto.dispose();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, código invalido ou inexistente !");

        }
    }//GEN-LAST:event_txt_relCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Produto;
    private javax.swing.JButton btnAlterarProdutos;
    private javax.swing.JButton btnCancelarProdutos;
    private javax.swing.JToggleButton btnCategoria;
    private javax.swing.JButton btnExcluirProdutos;
    private javax.swing.JToggleButton btnMarca;
    private javax.swing.JButton btnNovoProdutos;
    private javax.swing.JButton btnSalvarProdutos;
    private javax.swing.JButton btn_RelCategoria;
    private javax.swing.JToggleButton btn_relproduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbCategoria;
    private javax.swing.JComboBox<String> jcbMarca;
    private javax.swing.JRadioButton opc_Categoria;
    private javax.swing.JPanel pnl_cadastraprodutos;
    private javax.swing.JPanel pnl_consultaproduto;
    private javax.swing.JRadioButton rbt_quantidaMinima;
    private javax.swing.JTable tabelaConsultaProdutos;
    private javax.swing.JTable tbl_produto;
    private javax.swing.JTextField txtBuscaProdutos;
    private javax.swing.JTextField txtCodProdutos;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtDescricaoProdutos;
    private javax.swing.JTextField txtQuantidadeMinProd;
    private javax.swing.JTextField txtQuantidadeProd;
    private javax.swing.JTextField txtValorCustoProd;
    private javax.swing.JTextField txtValorVenda;
    private javax.swing.JTextField txt_porcentagem;
    private javax.swing.JTextField txt_relCategoria;
    // End of variables declaration//GEN-END:variables
}
