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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                ven.getProduto().getCodigo_barras(),
                ven.getProduto().getDescricao(),
                ven.getItensVenda().getQuantidade(),
                ven.getProduto().getValor_venda(),
                ven.getItensVenda().getPreco_unitario(),
                ven.getVendedor().getNome_funcionario(),
                ven.getCliente().getNome_cliente(),
                ven.getData_venda(),});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_Reimprimir = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        txt_cod_venda = new javax.swing.JTextField();
        btn_reimprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Dialog_Filtro = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        btn_filtrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_data_inicial = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_data_final = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        opcao_reimprimir = new javax.swing.JRadioButton();
        opcao_periodo = new javax.swing.JRadioButton();
        btn_relCompleto = new javax.swing.JButton();
        btn_relFiltrado = new javax.swing.JButton();
        lbl_periodo = new javax.swing.JLabel();

        Dialog_Reimprimir.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Reimprimir.setMinimumSize(new java.awt.Dimension(630, 135));
        Dialog_Reimprimir.setModal(true);
        Dialog_Reimprimir.setName("Dialog_Reimprimir"); // NOI18N
        Dialog_Reimprimir.setResizable(false);
        Dialog_Reimprimir.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Dialog_ReimprimirWindowOpened(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("COD Venda: ");

        txt_cod_venda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_vendaKeyPressed(evt);
            }
        });

        btn_reimprimir.setText("Reimprimir");
        btn_reimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reimprimirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("REIMPRIMIR COMPROVANTE");

        javax.swing.GroupLayout Dialog_ReimprimirLayout = new javax.swing.GroupLayout(Dialog_Reimprimir.getContentPane());
        Dialog_Reimprimir.getContentPane().setLayout(Dialog_ReimprimirLayout);
        Dialog_ReimprimirLayout.setHorizontalGroup(
            Dialog_ReimprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ReimprimirLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Dialog_ReimprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ReimprimirLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ReimprimirLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cod_venda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_reimprimir)
                        .addGap(19, 19, 19))))
        );
        Dialog_ReimprimirLayout.setVerticalGroup(
            Dialog_ReimprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dialog_ReimprimirLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(Dialog_ReimprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cod_venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btn_reimprimir))
                .addGap(38, 38, 38))
        );

        Dialog_Filtro.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog_Filtro.setMinimumSize(new java.awt.Dimension(630, 135));
        Dialog_Filtro.setModal(true);
        Dialog_Filtro.setName("Dialog_Reimprimir"); // NOI18N
        Dialog_Filtro.setPreferredSize(new java.awt.Dimension(529, 120));
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
            txt_data_inicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
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
            txt_data_final.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
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
                .addGroup(Dialog_FiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btn_filtrar)
                    .addComponent(txt_data_inicial)
                    .addComponent(jLabel6)
                    .addComponent(txt_data_final))
                .addGap(38, 38, 38))
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
                "ID", "Código Barra", "Descrição", "Quantidade", "Valor Produto", "Sub Total", "Vendedor", "Cliente", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, true
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

        opcao_reimprimir.setText("Reimprimir Comprovante");
        opcao_reimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao_reimprimirActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(opcao_reimprimir)
                        .addGap(18, 18, 18)
                        .addComponent(opcao_periodo)
                        .addGap(30, 30, 30)
                        .addComponent(btn_relCompleto)
                        .addGap(18, 18, 18)
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
                    .addComponent(opcao_reimprimir)
                    .addComponent(opcao_periodo)
                    .addComponent(btn_relCompleto)
                    .addComponent(btn_relFiltrado))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
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

    private void opcao_reimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao_reimprimirActionPerformed
        opcao_periodo.setSelected(false);
        if (opcao_reimprimir.isSelected() == false && opcao_periodo.isSelected() == false) {
            listarVendas();
            btn_filtrar.setVisible(false);
            btn_relCompleto.setVisible(true);
            lbl_periodo.setVisible(false);
        }
        if (opcao_reimprimir.isSelected() == true) {
            Dialog_Reimprimir.setLocationRelativeTo(null);
            Dialog_Reimprimir.setVisible(true);
        }
    }//GEN-LAST:event_opcao_reimprimirActionPerformed

    private void opcao_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao_periodoActionPerformed
        opcao_reimprimir.setSelected(false);
        if (opcao_reimprimir.isSelected() == false && opcao_periodo.isSelected() == false) {
            listarVendas();
            btn_relFiltrado.setVisible(false);
            btn_relCompleto.setVisible(true);
            lbl_periodo.setVisible(false);

        }
        if (opcao_periodo.isSelected() == true) {
            Dialog_Filtro.setLocationRelativeTo(null);
            Dialog_Filtro.setVisible(true);
        }
    }//GEN-LAST:event_opcao_periodoActionPerformed

    private void txt_cod_vendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_vendaKeyPressed

    }//GEN-LAST:event_txt_cod_vendaKeyPressed

    private void Dialog_ReimprimirWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_ReimprimirWindowOpened

    }//GEN-LAST:event_Dialog_ReimprimirWindowOpened

    private void btn_reimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reimprimirActionPerformed
        try {
            vendaDAO.ReimprimirComprovante(Integer.parseInt(txt_cod_venda.getText()));
            Dialog_Reimprimir.dispose();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, código invalido ou inexistente !");

        }
    }//GEN-LAST:event_btn_reimprimirActionPerformed

    private void btn_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarActionPerformed

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
                    ven.getProduto().getCodigo_barras(),
                    ven.getProduto().getDescricao(),
                    ven.getItensVenda().getQuantidade(),
                    ven.getProduto().getValor_venda(),
                    ven.getItensVenda().getPreco_unitario(),
                    ven.getVendedor().getNome_funcionario(),
                    ven.getCliente().getNome_cliente(),
                    ven.getData_venda(),});
            }
        } catch (Exception e) {
        }
        btn_relCompleto.setVisible(false);
        btn_relFiltrado.setVisible(true);
        lbl_periodo.setVisible(true);
        lbl_periodo.setText("Filtrando por Período : Início: " + txt_data_inicial.getText() + " Final " + txt_data_final.getText());

        Dialog_Filtro.dispose();
    }//GEN-LAST:event_btn_filtrarActionPerformed

    private void Dialog_FiltroWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Dialog_FiltroWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_Dialog_FiltroWindowOpened

    private void txt_data_inicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_inicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_inicialActionPerformed

    private void btn_relFiltradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relFiltradoActionPerformed
        try {
            vendaDAO.gerarRelatorioPeriodo(txt_data_inicial.getText(), txt_data_final.getText());
            btn_relCompleto.setVisible(true);
            btn_relFiltrado.setVisible(false);
            opcao_periodo.setSelected(false);
            listarVendas();
        } catch (DocumentException ex) {
            Logger.getLogger(RelVendas_VIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_relFiltradoActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        if (opcao_periodo.isSelected() == true) {
            btn_relCompleto.setVisible(false);
            btn_relFiltrado.setVisible(true);
        } else {
            btn_relCompleto.setVisible(true);
            btn_relFiltrado.setVisible(false);
        }
    }//GEN-LAST:event_formWindowStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Filtro;
    private javax.swing.JDialog Dialog_Reimprimir;
    private javax.swing.JButton btn_filtrar;
    private javax.swing.JButton btn_reimprimir;
    private javax.swing.JButton btn_relCompleto;
    private javax.swing.JButton btn_relFiltrado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_periodo;
    private javax.swing.JRadioButton opcao_periodo;
    private javax.swing.JRadioButton opcao_reimprimir;
    private javax.swing.JTable tabelaVendas;
    private javax.swing.JTextField txt_cod_venda;
    private javax.swing.JFormattedTextField txt_data_final;
    private javax.swing.JFormattedTextField txt_data_inicial;
    // End of variables declaration//GEN-END:variables
}
