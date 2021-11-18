/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.ClienteDAO;
import MODEL.Cliente;
import MODEL.Contato;
import MODEL.Endereco;
import MODEL.ClienteFisica;
import MODEL.ClienteJuridica;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luizh
 */
public class ProdutoVIEW extends javax.swing.JFrame {

    Cliente cliente;
    Contato cliContato;
    Endereco cliEndereco;
    ClienteFisica cliFisica;
    ClienteJuridica cliJuridica;
    ClienteDAO clienteDAO;

    public ProdutoVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);

        clienteDAO = new ClienteDAO();

        txtCodProdutos.setEnabled(false);

        btnAlterarProdutos.setVisible(false);
        txtBuscaProdutos.setEnabled(false);
        fechaBotoes();
        fechaCampos();
    }

    public void listarClientesFisica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listarClientesFisica();
        DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();
        dados.setNumRows(0);
        for (Cliente c : lista) {
            dados.addRow(new Object[]{
                c.getCod_cliente(),
                c.getNome_cliente(),
                c.getCliFisica().getCpf(),
                c.getCliFisica().getRg(),
                c.getCliContato().getCelular_cliente(),
                c.getCliContato().getTelefone_cliente(),
                c.getCliContato().getEmail(),
                c.getCliEndereco().getRua(),
                c.getCliEndereco().getNumero(),
                c.getCliEndereco().getBairro(),
                c.getCliEndereco().getCidade(),
                c.getCliEndereco().getEstado(),
                c.getCliEndereco().getCep()
            });
        }
    }

    public void listarClientesJuridica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista2 = clienteDAO.ListarClienteJuridica();
        DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();
        dados.setNumRows(0);
        for (Cliente c : lista2) {
            dados.addRow(new Object[]{
                c.getCod_cliente(),
                c.getNome_cliente(),
                c.getCliJuridica().getCnpj(),
                c.getCliJuridica().getInscricao_estadual(),
                c.getCliContato().getCelular_cliente(),
                c.getCliContato().getTelefone_cliente(),
                c.getCliContato().getEmail(),
                c.getCliEndereco().getRua(),
                c.getCliEndereco().getNumero(),
                c.getCliEndereco().getBairro(),
                c.getCliEndereco().getCidade(),
                c.getCliEndereco().getEstado(),
                c.getCliEndereco().getCep()
            });
        }
    }

    public void fechaBotoes() {
        btnSalvarProdutos.setEnabled(false);
        btnCancelarProdutos.setEnabled(false);
        btnExcluirProdutos.setEnabled(false);
    }

    public void fechaCampos() {
        txtCodigoBarras.setEnabled(false);
        txtValorCustoProd.setEnabled(false);
        txtDescricaoProdutos.setEnabled(false);
        txtQuantidadeMinProd.setEnabled(false);
        txtQuantidadeProd.setEnabled(false);
    }

    public void abreCampos() {
        txtCodigoBarras.setEnabled(true);
        txtValorCustoProd.setEnabled(true);
        txtDescricaoProdutos.setEnabled(true);
        txtQuantidadeMinProd.setEnabled(true);
        txtQuantidadeProd.setEnabled(true);
    }

    public void limpaCampos() {
        txtCodigoBarras.setText("");
        txtValorCustoProd.setText("");
        txtDescricaoProdutos.setText("");
        txtQuantidadeMinProd.setText("");
        txtQuantidadeProd.setText("");
        txtCodProdutos.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtDescricaoProdutos.getText().isEmpty() || txtQuantidadeMinProd.getText().isEmpty()
                || txtCodigoBarras.getText().isEmpty() || txtValorCustoProd.getText().isEmpty()
               
                || jcbFornecedor.getSelectedItem() == "Selecione" || txtQuantidadeProd.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            listaCampos.add(txtDescricaoProdutos.getText());
            listaCampos.add(txtQuantidadeMinProd.getText());
            listaCampos.add(txtCodigoBarras.getText());
            listaCampos.add(txtValorCustoProd.getText());


            listaCampos.add(txtQuantidadeProd.getText());

            return listaCampos;
        }

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        txtValorCustoProd = new javax.swing.JTextField();
        txtCodProdutos = new javax.swing.JTextField();
        txtDescricaoProdutos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarProdutos = new javax.swing.JButton();
        btnCancelarProdutos = new javax.swing.JButton();
        btnExcluirProdutos = new javax.swing.JButton();
        btnAlterarProdutos = new javax.swing.JButton();
        txtValorVendaProd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jcbCategoria = new javax.swing.JComboBox<>();
        jcbMarca = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscaProdutos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultaProdutos = new javax.swing.JTable();
        btnNovoProdutos = new javax.swing.JButton();

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

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("Código");

        jLabel3.setText("Descrição");

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

        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        jcbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidadeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidadeMinProd, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescricaoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 199, Short.MAX_VALUE)
                                .addComponent(btnAlterarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnExcluirProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(93, 93, 93)
                                        .addComponent(jLabel20))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(64, 64, 64))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(247, 247, 247)
                                        .addComponent(jLabel11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorVendaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorCustoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValorCustoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescricaoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantidadeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantidadeMinProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValorVendaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(14, 14, 14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(btnSalvarProdutos)
                    .addComponent(btnCancelarProdutos)
                    .addComponent(btnExcluirProdutos)
                    .addComponent(btnAlterarProdutos))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados produtos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Nome Produtos");

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
                "Código", "Descrição", "Quantidade", "Quantidade mínima", "código barras", "Valor custo", "Valor venda", "Categoria", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 765, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de produtos", jPanel3);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovoProdutos)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoProdutosActionPerformed
        abreCampos();
        txtBuscaProdutos.setEnabled(false);
        //btnBuscar.setEnabled(false);
        btnNovoProdutos.setEnabled(false);
        btnExcluirProdutos.setEnabled(false);
        btnAlterarProdutos.setVisible(false);
        btnCancelarProdutos.setEnabled(true);
        btnSalvarProdutos.setVisible(true);
        btnSalvarProdutos.setEnabled(true);
    }//GEN-LAST:event_btnNovoProdutosActionPerformed

    private void btnCancelarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProdutosActionPerformed
        limpaCampos();
        fechaBotoes();
        fechaCampos();

        btnAlterarProdutos.setEnabled(false);
        btnNovoProdutos.setEnabled(true);
    }//GEN-LAST:event_btnCancelarProdutosActionPerformed

    private void btnSalvarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutosActionPerformed
        List<String> listaCampos = getCampos();
        if (rbnPF.isSelected() == false && rbnPJ.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Defina o tipo de pessoa.");
        }
        if (rbnPF.isSelected()) {
            //System.out.println("Entrei fora" + txtCpf.getText() + txtRg.getText());
            if (txtCpf.getText().matches(".*\\d.*") == false
                    || txtRg.getText().matches(".*\\d.*") == false) {
                JOptionPane.showMessageDialog(null, "Preencha os campos CPF/RG");
            } else {
                cliente = new Cliente();

                cliente.setNome_cliente(listaCampos.get(0));
                cliente.setTipo_cliente(0);

                cliContato = new Contato();

                cliContato.setCelular_cliente(listaCampos.get(5));
                cliContato.setTelefone_cliente(listaCampos.get(6));
                cliContato.setEmail(listaCampos.get(7));

                cliEndereco = new Endereco();

                cliEndereco.setBairro(listaCampos.get(2));
                cliEndereco.setCep(listaCampos.get(4));
                cliEndereco.setCidade(listaCampos.get(3));
                cliEndereco.setEstado((String) jcbFornecedor.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                cliFisica = new ClienteFisica();

                

                try {
                    clienteDAO.salvarFisica(cliente, cliFisica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoProdutos.setEnabled(true);
            }
        }
        if (rbnPJ.isSelected()) {
            if (txtCnpj.getText().matches(".*\\d.*") == false
                    || txtIe.getText().matches(".*\\d.*") == false) {
                JOptionPane.showMessageDialog(null, "Preencha os campos CNPJ/INSCRIÇÃO ESTADUAL");
            } else {
                cliente = new Cliente();

                cliente.setNome_cliente(listaCampos.get(0));
                cliente.setTipo_cliente(1);

                cliContato = new Contato();

                cliContato.setCelular_cliente(listaCampos.get(5));
                cliContato.setTelefone_cliente(listaCampos.get(6));
                cliContato.setEmail(listaCampos.get(7));

                cliEndereco = new Endereco();

                cliEndereco.setBairro(listaCampos.get(2));
                cliEndereco.setCep(listaCampos.get(4));
                cliEndereco.setCidade(listaCampos.get(3));
                cliEndereco.setEstado((String) jcbFornecedor.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                

                try {
                    clienteDAO.salvarJuridica(cliente, cliJuridica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoProdutos.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnSalvarProdutosActionPerformed

    private void txtBuscaProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaProdutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaProdutosActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnAlterarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarProdutosActionPerformed
        List<String> listaCampos = getCampos();
        cliente = new Cliente();

        cliente.setCod_cliente(Integer.parseInt(txtCodProdutos.getText()));
        cliente.setNome_cliente(listaCampos.get(0));

        cliEndereco = new Endereco();

        cliEndereco.setBairro(listaCampos.get(2));
        cliEndereco.setCep(listaCampos.get(4));
        cliEndereco.setCidade(listaCampos.get(3));
        cliEndereco.setEstado((String) jcbFornecedor.getSelectedItem());
        cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
        cliEndereco.setRua(listaCampos.get(8));

        cliContato = new Contato();

        cliContato.setCelular_cliente(listaCampos.get(5));
        cliContato.setTelefone_cliente(listaCampos.get(6));
        cliContato.setEmail(listaCampos.get(7));

        try {
            clienteDAO.alterar(cliente, cliEndereco, cliContato);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        btnAlterarProdutos.setVisible(false);
        btnSalvarProdutos.setVisible(true);
        btnSalvarProdutos.setEnabled(false);
        btnNovoProdutos.setEnabled(true);
        limpaCampos();
        fechaCampos();
        fechaBotoes();

    }//GEN-LAST:event_btnAlterarProdutosActionPerformed

    private void btnExcluirProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutosActionPerformed
        cliente = new Cliente();
        cliente.setCod_cliente(Integer.parseInt(txtCodProdutos.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtDescricaoProdutos.getText() + "?");

        if (confirma == 0) {
            try {
                clienteDAO.excluir(cliente);

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

        }
    }//GEN-LAST:event_btnExcluirProdutosActionPerformed

    private void tabelaConsultaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaProdutosMouseClicked
        /* Pegando os Dados */
        jTabbedPane1.setSelectedIndex(0);
        txtCodProdutos.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 0).toString());
        txtDescricaoProdutos.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 1).toString());
        
        txtQuantidadeProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 7).toString());
        txtQuantidadeMinProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 8).toString());
        txtCodigoBarras.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 9).toString());
        txtValorCustoProd.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 10).toString());
        

        if (rbnBuscaFisica.isSelected()) {
            txtCpf.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 2).toString());
            txtRg.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 3).toString());

            txtCnpj.setText("");
            txtIe.setText("");
        } else {
            if (rbnBuscaJuridica.isSelected()) {
                txtCnpj.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 2).toString());
                txtIe.setText(tabelaConsultaProdutos.getValueAt(tabelaConsultaProdutos.getSelectedRow(), 3).toString());

                txtCpf.setText("");
                txtRg.setText("");
            }
        }

        abreCampos();
        btnSalvarProdutos.setVisible(false);
        btnNovoProdutos.setEnabled(false);

        btnAlterarProdutos.setVisible(true);
        btnAlterarProdutos.setEnabled(true);
        btnExcluirProdutos.setEnabled(true);
        btnCancelarProdutos.setEnabled(true);

        
    }//GEN-LAST:event_tabelaConsultaProdutosMouseClicked

    private void txtBuscaProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaProdutosKeyPressed
        if (rbnBuscaFisica.isSelected()) {
            String nome = "%" + txtBuscaProdutos.getText() + "%";

            clienteDAO = new ClienteDAO();

            List<Cliente> lista = clienteDAO.buscaClientesFisica(nome);
            DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();

            dados.setNumRows(0);

            for (Cliente c : lista) {
                dados.addRow(new Object[]{
                    c.getCod_cliente(),
                    c.getNome_cliente(),
                    c.getCliFisica().getCpf(),
                    c.getCliFisica().getRg(),
                    c.getCliContato().getCelular_cliente(),
                    c.getCliContato().getTelefone_cliente(),
                    c.getCliContato().getEmail(),
                    c.getCliEndereco().getRua(),
                    c.getCliEndereco().getNumero(),
                    c.getCliEndereco().getBairro(),
                    c.getCliEndereco().getCidade(),
                    c.getCliEndereco().getEstado(),
                    c.getCliEndereco().getCep()
                });
            }

        } else {
            if (rbnBuscaJuridica.isSelected()) {
                String nome = "%" + txtBuscaProdutos.getText() + "%";

                clienteDAO = new ClienteDAO();

                List<Cliente> lista2 = clienteDAO.buscaClienteJuridica(nome);
                DefaultTableModel dados = (DefaultTableModel) tabelaConsultaProdutos.getModel();

                dados.setNumRows(0);

                for (Cliente c : lista2) {
                    dados.addRow(new Object[]{
                        c.getCod_cliente(),
                        c.getNome_cliente(),
                        c.getCliJuridica().getCnpj(),
                        c.getCliJuridica().getInscricao_estadual(),
                        c.getCliContato().getCelular_cliente(),
                        c.getCliContato().getTelefone_cliente(),
                        c.getCliContato().getEmail(),
                        c.getCliEndereco().getRua(),
                        c.getCliEndereco().getNumero(),
                        c.getCliEndereco().getBairro(),
                        c.getCliEndereco().getCidade(),
                        c.getCliEndereco().getEstado(),
                        c.getCliEndereco().getCep()
                    });
                }
            }
        }
    }//GEN-LAST:event_txtBuscaProdutosKeyPressed

    private void txtQuantidadeMinProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeMinProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeMinProdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarProdutos;
    private javax.swing.JButton btnCancelarProdutos;
    private javax.swing.JButton btnExcluirProdutos;
    private javax.swing.JButton btnNovoProdutos;
    private javax.swing.JButton btnSalvarProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbCategoria;
    private javax.swing.JComboBox<String> jcbMarca;
    private javax.swing.JTable tabelaConsultaProdutos;
    private javax.swing.JTextField txtBuscaProdutos;
    private javax.swing.JTextField txtCodProdutos;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtDescricaoProdutos;
    private javax.swing.JTextField txtQuantidadeMinProd;
    private javax.swing.JTextField txtQuantidadeProd;
    private javax.swing.JTextField txtValorCustoProd;
    private javax.swing.JTextField txtValorVendaProd;
    // End of variables declaration//GEN-END:variables
}
