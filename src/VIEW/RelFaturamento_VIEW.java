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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RelFaturamento_VIEW extends javax.swing.JFrame {

    VendaDAO vendaDAO;
    ProdutoDAO produtoDAO;
    Funcionario funcionario;
    Produto produto;
    Cliente cliente;
    ItensVenda itensVenda;
    Venda venda;
    Usuario user;

    public RelFaturamento_VIEW(Usuario user) {
        initComponents();
        this.setLocationRelativeTo(null);

        vendaDAO = new VendaDAO();
        listarFaturamentoCompleto();
        btnRelPeriodo.setVisible(false);

    }

    public static double valorTotalVenda = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_relCompleto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_data_inicial_faturamento = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_data_final_faturamento = new javax.swing.JFormattedTextField();
        lblPeriodo = new javax.swing.JLabel();
        btnRelCompleto = new javax.swing.JButton();
        btnRelPeriodo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFaturamento = new javax.swing.JTable();
        lblTotalFaturamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Relatório Faturamento");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        btn_relCompleto.setText("Filtrar Período");
        btn_relCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relCompletoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Data Inicial:");

        try {
            txt_data_inicial_faturamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Data Final:");

        try {
            txt_data_final_faturamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblPeriodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPeriodo.setText("Periodo: ");

        btnRelCompleto.setText("Rel. Completo");
        btnRelCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelCompletoActionPerformed(evt);
            }
        });

        btnRelPeriodo.setText("Rel. Periodo");
        btnRelPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelPeriodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPeriodo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_data_inicial_faturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_data_final_faturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btn_relCompleto)
                        .addGap(18, 18, 18)
                        .addComponent(btnRelCompleto)
                        .addGap(18, 18, 18)
                        .addComponent(btnRelPeriodo)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lblPeriodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_data_final_faturamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_data_inicial_faturamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btn_relCompleto)
                    .addComponent(btnRelCompleto)
                    .addComponent(btnRelPeriodo))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(249, 249, 249))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(399, 399, 399))
        );

        tabelaFaturamento.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaFaturamento.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaFaturamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFaturamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFaturamento);

        lblTotalFaturamento.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTotalFaturamento.setForeground(new java.awt.Color(153, 51, 0));
        lblTotalFaturamento.setText("TOTAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalFaturamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(lblTotalFaturamento)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(49, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void listarFaturamentoCompleto() {
        lblPeriodo.setText("Período: Todo");
        vendaDAO = new VendaDAO();
        produto = new Produto();
        itensVenda = new ItensVenda();
        List<Venda> lista = vendaDAO.listarFaturamentoTodo();

        DefaultTableModel dadosTable = (DefaultTableModel) tabelaFaturamento.getModel();
        dadosTable.setNumRows(0);
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        ((DecimalFormat) df).applyPattern("0.00");
        //txtValorVenda.setText(df.format(porcentagem));
        for (Venda ven : lista) {
            dadosTable.addRow(new Object[]{
                ven.getItensVenda().getIdItens_venda(),
                df.format(Double.parseDouble(ven.getValor_total())),
                ven.getVendedor().getNome_funcionario(),
                ven.getCliente().getNome_cliente(),
                new SimpleDateFormat("dd-MM-yyyy").format(ven.getData_venda()),
                this.valorTotalVenda += Double.parseDouble(ven.getValor_total()),});

            lblTotalFaturamento.setText("TOTAL: R$ " + df.format(this.valorTotalVenda));

        }
    }

    public void listarFaturamentoPeriodo() {
        lblPeriodo.setText("Período: Início: " + txt_data_inicial_faturamento.getText() + " Final: " + txt_data_final_faturamento.getText());
        vendaDAO = new VendaDAO();
        produto = new Produto();
        itensVenda = new ItensVenda();
        List<Venda> lista2 = vendaDAO.listarFaturamento(txt_data_inicial_faturamento.getText(), txt_data_final_faturamento.getText());

        DefaultTableModel dadosTable = (DefaultTableModel) tabelaFaturamento.getModel();
        dadosTable.setNumRows(0);
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        ((DecimalFormat) df).applyPattern("0.00");
        //txtValorVenda.setText(df.format(porcentagem));
        for (Venda ven2 : lista2) {
            dadosTable.addRow(new Object[]{
                ven2.getItensVenda().getIdItens_venda(),
                df.format(Double.parseDouble(ven2.getValor_total())),
                ven2.getVendedor().getNome_funcionario(),
                ven2.getCliente().getNome_cliente(),
                new SimpleDateFormat("dd-MM-yyyy").format(ven2.getData_venda()),
                this.valorTotalVenda += Double.parseDouble(ven2.getValor_total()),});

            lblTotalFaturamento.setText("TOTAL: R$ " + df.format(this.valorTotalVenda));

        }
    }


    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void btn_relCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relCompletoActionPerformed

        if (txt_data_inicial_faturamento.getText().matches(".*\\d.*") == false
                || txt_data_final_faturamento.getText().matches(".*\\d.*") == false) {
            JOptionPane.showMessageDialog(null, "Preencha os campos DATA INICIO e DATA FINAL");
        } else {
            this.valorTotalVenda = 0;
            btnRelCompleto.setVisible(false);
            btnRelPeriodo.setVisible(true);
            listarFaturamentoPeriodo();
        }
    }//GEN-LAST:event_btn_relCompletoActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void tabelaFaturamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFaturamentoMouseClicked

    }//GEN-LAST:event_tabelaFaturamentoMouseClicked

    private void btnRelPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelPeriodoActionPerformed

        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.gerarRelatorioFaturamentoPeriodo(txt_data_inicial_faturamento.getText(), txt_data_final_faturamento.getText(), this.valorTotalVenda);
            btnRelPeriodo.setVisible(false);
            btn_relCompleto.setVisible(true);
            this.valorTotalVenda = 0;
            listarFaturamentoCompleto();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnRelPeriodoActionPerformed

    private void btnRelCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelCompletoActionPerformed
        String nomediretorio = null;
        String nomepasta = "SRS"; // Informe o nome da pasta que armazenará o relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            vendaDAO.gerarRelatorioFaturamentoCompleto(this.valorTotalVenda);
            this.valorTotalVenda = 0;
            listarFaturamentoCompleto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRelCompletoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRelCompleto;
    private javax.swing.JButton btnRelPeriodo;
    private javax.swing.JButton btn_relCompleto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblTotalFaturamento;
    private javax.swing.JTable tabelaFaturamento;
    private javax.swing.JFormattedTextField txt_data_final_faturamento;
    private javax.swing.JFormattedTextField txt_data_inicial_faturamento;
    // End of variables declaration//GEN-END:variables
}
