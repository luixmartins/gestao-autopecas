package VIEW;

import DAO.ProdutoDAO;
import DAO.VendaDAO;
import MODEL.Cliente;
import MODEL.Funcionario;
import MODEL.ItensVenda;
import MODEL.Produto;
import MODEL.Usuario;
import MODEL.Venda;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RelVendas_VIEW extends javax.swing.JFrame {

    VendaDAO vendaDAO;
    ProdutoDAO produtoDAO;
    Funcionario funcionario;
    Produto produto;
    Cliente cliente;
    ItensVenda itensVenda;
    Venda venda;
    Usuario user;
    public static String idTabelaVenda;

    public RelVendas_VIEW(Usuario user) {
        initComponents();
        this.setLocationRelativeTo(null);

        vendaDAO = new VendaDAO();

        listarVendas();

        btn_relFiltrado.setVisible(false);

        lbl_periodo.setVisible(false);

    }

    public void listarVendas() {
        vendaDAO = new VendaDAO();
        produto = new Produto();
        itensVenda = new ItensVenda();
        List<Venda> lista = vendaDAO.listarVendas();

        DefaultTableModel dadosTable = (DefaultTableModel) tabelaVendas.getModel();
        dadosTable.setNumRows(0);

        for (Venda ven : lista) {
            dadosTable.addRow(new Object[]{
                ven.getItensVenda().getIdItens_venda(),
                ven.getValor_total(),
                ven.getVendedor().getNome_funcionario(),
                ven.getCliente().getNome_cliente(),
                new SimpleDateFormat("dd-MM-yyyy").format(ven.getData_venda()),});
        }
    }

    public void listarDetalhado() {
        List<Venda> lista = vendaDAO.listarVendasDetalhado(Integer.parseInt(idTabelaVenda));

        DefaultTableModel dadosDetalhado = (DefaultTableModel) tbl_vendaDetalhado.getModel();
        dadosDetalhado.setNumRows(0);
        lbl_idVenda.setText("VENDA COD: " + Integer.parseInt(idTabelaVenda));
        for (Venda ven : lista) {
            lbl_cliente.setText("Cliente: " + ven.getCliente().getNome_cliente());
            lbl_vendedor.setText("Vendedor: " + ven.getVendedor().getNome_funcionario());
            lbl_dtVenda.setText("Data da Venda: " + new SimpleDateFormat("dd-MM-yyyy").format(ven.getData_venda()));
            lbl_totalVenda.setText("Total: R$ " + ven.getValor_total());
            dadosDetalhado.addRow(new Object[]{
                //ven.getItensVenda().getIdItens_venda(),
                ven.getProduto().getCodigo_barras(),
                ven.getProduto().getDescricao(),
                ven.getItensVenda().getQuantidade(),
                ven.getProduto().getValor_venda(),
                ven.getItensVenda().getPreco_unitario(), //ven.getVendedor().getNome_funcionario(),
            //ven.getCliente().getNome_cliente(),
            //new SimpleDateFormat("dd-MM-yyyy").format(ven.getData_venda()),
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_Filtro = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        btn_filtrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_data_inicial = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_data_final = new javax.swing.JFormattedTextField();
        Dialog_VendaDetalhado = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_vendaDetalhado = new javax.swing.JTable();
        lbl_cliente = new javax.swing.JLabel();
        lbl_dtVenda = new javax.swing.JLabel();
        lbl_totalVenda = new javax.swing.JLabel();
        lbl_vendedor = new javax.swing.JLabel();
        btn_imprimeDetalhado = new javax.swing.JButton();
        lbl_idVenda = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        opcao_periodo = new javax.swing.JRadioButton();
        btn_relCompleto = new javax.swing.JButton();
        btn_relFiltrado = new javax.swing.JButton();
        btn_relTOP10 = new javax.swing.JButton();
        lbl_periodo = new javax.swing.JLabel();

        Dialog_Filtro.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Filtro.setMinimumSize(new java.awt.Dimension(630, 135));
        Dialog_Filtro.setModal(true);
        Dialog_Filtro.setName("Dialog_Reimprimir"); // NOI18N
        Dialog_Filtro.setResizable(false);
        Dialog_Filtro.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_FiltroWindowOpened(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Data Inicial:");

        btn_filtrar.setText("Filtrar");
        btn_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("FILTRAR POR PERÍODO");

        try {
            txt_data_inicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_data_inicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_inicialActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Data Final:");

        try {
            txt_data_final.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout Dialog_FiltroLayout = new javax.swing.GroupLayout(Dialog_Filtro.getContentPane());
        Dialog_Filtro.getContentPane().setLayout(Dialog_FiltroLayout);
        Dialog_FiltroLayout.setHorizontalGroup(
            Dialog_FiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_FiltroLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data_final, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_FiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(151, 151, 151))
        );
        Dialog_FiltroLayout.setVerticalGroup(
            Dialog_FiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_FiltroLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(Dialog_FiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_data_inicial, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Dialog_FiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btn_filtrar)
                        .addComponent(jLabel6)
                        .addComponent(txt_data_final)))
                .addGap(38, 38, 38))
        );

        Dialog_VendaDetalhado.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_VendaDetalhado.setMinimumSize(new java.awt.Dimension(900, 429));
        Dialog_VendaDetalhado.setModal(true);
        Dialog_VendaDetalhado.setName("Dialog_VendaDetalhado"); // NOI18N
        Dialog_VendaDetalhado.setPreferredSize(new java.awt.Dimension(900, 429));
        Dialog_VendaDetalhado.setResizable(false);
        Dialog_VendaDetalhado.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                Dialog_VendaDetalhadoWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                Dialog_VendaDetalhadoWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Dialog_VendaDetalhadoWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_VendaDetalhadoWindowOpened(evt);
            }
        });

        tbl_vendaDetalhado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Barra", "Descrição", "Quantidade", "Valor Produto", "Total Produto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vendaDetalhado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vendaDetalhadoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_vendaDetalhado);

        lbl_cliente.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_cliente.setText("Cliente");

        lbl_dtVenda.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_dtVenda.setText("Data da Venda: ");

        lbl_totalVenda.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_totalVenda.setForeground(new java.awt.Color(153, 0, 0));
        lbl_totalVenda.setText("Total");

        lbl_vendedor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_vendedor.setText("Vendedor");

        btn_imprimeDetalhado.setText("IMPRIMIR");
        btn_imprimeDetalhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimeDetalhadoActionPerformed(evt);
            }
        });

        lbl_idVenda.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_idVenda.setText("ID VENDA");

        javax.swing.GroupLayout Dialog_VendaDetalhadoLayout = new javax.swing.GroupLayout(Dialog_VendaDetalhado.getContentPane());
        Dialog_VendaDetalhado.getContentPane().setLayout(Dialog_VendaDetalhadoLayout);
        Dialog_VendaDetalhadoLayout.setHorizontalGroup(
            Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_VendaDetalhadoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dialog_VendaDetalhadoLayout.createSequentialGroup()
                        .addComponent(lbl_totalVenda)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Dialog_VendaDetalhadoLayout.createSequentialGroup()
                        .addGroup(Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Dialog_VendaDetalhadoLayout.createSequentialGroup()
                                .addComponent(lbl_vendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_imprimeDetalhado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Dialog_VendaDetalhadoLayout.createSequentialGroup()
                                .addComponent(lbl_cliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_idVenda))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Dialog_VendaDetalhadoLayout.createSequentialGroup()
                                .addGroup(Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_dtVenda, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))))
        );
        Dialog_VendaDetalhadoLayout.setVerticalGroup(
            Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_VendaDetalhadoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cliente)
                    .addComponent(lbl_idVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Dialog_VendaDetalhadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_imprimeDetalhado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_dtVenda)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_totalVenda)
                .addGap(15, 15, 15))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Relatório Vendas");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Tipo de consulta:");

        tabelaVendas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Valor Venda", "Vendedor", "Cliente", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaVendas);

        opcao_periodo.setText("Periodo");
        opcao_periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao_periodoActionPerformed(evt);
            }
        });

        btn_relCompleto.setText("Relatório Completo");
        btn_relCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relCompletoActionPerformed(evt);
            }
        });

        btn_relFiltrado.setText("Relatório Filtrado");
        btn_relFiltrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relFiltradoActionPerformed(evt);
            }
        });

        btn_relTOP10.setText("TOP 10 Vendidos");
        btn_relTOP10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relTOP10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(opcao_periodo)
                        .addGap(30, 30, 30)
                        .addComponent(btn_relCompleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_relTOP10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_relFiltrado)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(opcao_periodo)
                    .addComponent(btn_relCompleto)
                    .addComponent(btn_relFiltrado)
                    .addComponent(btn_relTOP10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbl_periodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_periodo.setText("Label do Periodo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .addComponent(lbl_periodo)
                .addGap(277, 277, 277)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_periodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaVendasMouseClicked
        this.idTabelaVenda = tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 0).toString();
        Dialog_VendaDetalhado.setLocationRelativeTo(null);
        Dialog_VendaDetalhado.setVisible(true);
    }//GEN-LAST:event_tabelaVendasMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void btn_relCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relCompletoActionPerformed
        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.gerarDocumentoVendaCompleto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_relCompletoActionPerformed

    private void opcao_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao_periodoActionPerformed
        if (opcao_periodo.isSelected() == true) {
            Dialog_Filtro.setLocationRelativeTo(null);
            Dialog_Filtro.setVisible(true);
        }
    }//GEN-LAST:event_opcao_periodoActionPerformed

    private void btn_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarActionPerformed
        if (txt_data_inicial.getText().matches(".*\\d.*") == false
                || txt_data_final.getText().matches(".*\\d.*") == false) {
            JOptionPane.showMessageDialog(null, "Preencha os campos DATA INICIO e DATA FINAL");
        } else {
            try {
                vendaDAO = new VendaDAO();
                produto = new Produto();
                itensVenda = new ItensVenda();
                List<Venda> lista = vendaDAO.listarVendasPeriodo(txt_data_inicial.getText(), txt_data_final.getText());

                DefaultTableModel dadosTable = (DefaultTableModel) tabelaVendas.getModel();
                dadosTable.setNumRows(0);

                for (Venda ven : lista) {
                    dadosTable.addRow(new Object[]{
                        ven.getItensVenda().getIdItens_venda(),
                        ven.getValor_total(),
                        ven.getVendedor().getNome_funcionario(),
                        ven.getCliente().getNome_cliente(),
                        new SimpleDateFormat("dd-MM-yyyy").format(ven.getData_venda()),});
                }
            } catch (Exception e) {
            }
            btn_relTOP10.setVisible(false);
            btn_relCompleto.setVisible(false);
            btn_relFiltrado.setVisible(true);
            lbl_periodo.setVisible(true);
            lbl_periodo.setText("Filtrando por Período : Início: " + txt_data_inicial.getText() + " Final " + txt_data_final.getText());

            Dialog_Filtro.dispose();
        }
    }//GEN-LAST:event_btn_filtrarActionPerformed

    private void Dialog_FiltroWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_FiltroWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_FiltroWindowOpened

    private void txt_data_inicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_inicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_inicialActionPerformed

    private void btn_relFiltradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relFiltradoActionPerformed

        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.gerarRelatorioPeriodo(txt_data_inicial.getText(), txt_data_final.getText());
            btn_relCompleto.setVisible(true);
            btn_relTOP10.setVisible(true);
            btn_relFiltrado.setVisible(false);
            opcao_periodo.setSelected(false);
            listarVendas();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_relFiltradoActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        if (opcao_periodo.isSelected() == true) {
            btn_relCompleto.setVisible(false);
            btn_relTOP10.setVisible(false);
            btn_relFiltrado.setVisible(true);
        } else {
            btn_relCompleto.setVisible(true);
            btn_relTOP10.setVisible(true);
            btn_relFiltrado.setVisible(false);
        }
    }//GEN-LAST:event_formWindowStateChanged

    private void btn_relTOP10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relTOP10ActionPerformed
        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.gerarRelatorioTOP10();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_relTOP10ActionPerformed

    private void tbl_vendaDetalhadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vendaDetalhadoMouseClicked

    }//GEN-LAST:event_tbl_vendaDetalhadoMouseClicked

    private void Dialog_VendaDetalhadoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendaDetalhadoWindowOpened

    }//GEN-LAST:event_Dialog_VendaDetalhadoWindowOpened

    private void btn_imprimeDetalhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimeDetalhadoActionPerformed
        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.ReimprimirComprovante(Integer.parseInt(idTabelaVenda));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_imprimeDetalhadoActionPerformed

    private void Dialog_VendaDetalhadoWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendaDetalhadoWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_VendaDetalhadoWindowClosed

    private void Dialog_VendaDetalhadoWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendaDetalhadoWindowClosing

    }//GEN-LAST:event_Dialog_VendaDetalhadoWindowClosing

    private void Dialog_VendaDetalhadoWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_VendaDetalhadoWindowActivated
        listarDetalhado();
    }//GEN-LAST:event_Dialog_VendaDetalhadoWindowActivated

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Filtro;
    private javax.swing.JDialog Dialog_VendaDetalhado;
    private javax.swing.JButton btn_filtrar;
    private javax.swing.JButton btn_imprimeDetalhado;
    private javax.swing.JButton btn_relCompleto;
    private javax.swing.JButton btn_relFiltrado;
    private javax.swing.JButton btn_relTOP10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JLabel lbl_dtVenda;
    private javax.swing.JLabel lbl_idVenda;
    private javax.swing.JLabel lbl_periodo;
    private javax.swing.JLabel lbl_totalVenda;
    private javax.swing.JLabel lbl_vendedor;
    private javax.swing.JRadioButton opcao_periodo;
    private javax.swing.JTable tabelaVendas;
    private javax.swing.JTable tbl_vendaDetalhado;
    private javax.swing.JFormattedTextField txt_data_final;
    private javax.swing.JFormattedTextField txt_data_inicial;
    // End of variables declaration//GEN-END:variables
}
