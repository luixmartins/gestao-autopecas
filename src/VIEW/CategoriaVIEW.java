/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.CategoriaDAO;
import MODEL.CategoriaProduto;

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
public class CategoriaVIEW extends javax.swing.JFrame {

    CategoriaProduto categoria;
    CategoriaDAO categoriaDAO;

    public CategoriaVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);
        categoriaDAO = new CategoriaDAO();
        txtCodCategoria.setEnabled(false);
        btnAlterarCategoria.setVisible(false);
        fechaBotoes();
        fechaCampos();
        txtBuscaCategoria.setEnabled(true);
        listarCategoria();
    }

    public void listarCategoria() {
        categoriaDAO = new CategoriaDAO();
        List<CategoriaProduto> lista = categoriaDAO.listarCategorias();
        DefaultTableModel dadosTable = (DefaultTableModel) tabelaCategoria.getModel();
        dadosTable.setNumRows(0);
        for (CategoriaProduto C : lista) {
            dadosTable.addRow(new Object[]{
                C.getCodigo_categoria(),
                C.getNome_categoria(),});
        }
    }

    public void fechaBotoes() {
        btnSalvarCategoria.setEnabled(false);
        btnCancelarCategoria.setEnabled(false);
        btnExcluirCategoria.setEnabled(false);
    }

    public void fechaCampos() {
        txtNomeCategoria.setEnabled(false);
    }

    public void abreCampos() {
        txtNomeCategoria.setEnabled(true);
    }

    public void limpaCampos() {
        txtNomeCategoria.setText("");
        txtCodCategoria.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();
        if (txtNomeCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            listaCampos.add(txtNomeCategoria.getText());
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
        txtCodCategoria = new javax.swing.JTextField();
        txtNomeCategoria = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarCategoria = new javax.swing.JButton();
        btnCancelarCategoria = new javax.swing.JButton();
        btnExcluirCategoria = new javax.swing.JButton();
        btnAlterarCategoria = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscaCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCategoria = new javax.swing.JTable();
        btnNovoCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Categoria");

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

        jLabel3.setText("Nome da Categoria");

        txtNomeCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeCategoriaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvarCategoria.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvarCategoria.setText("Salvar");
        btnSalvarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCategoriaActionPerformed(evt);
            }
        });

        btnCancelarCategoria.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarCategoria.setText("Cancelar");
        btnCancelarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCategoriaActionPerformed(evt);
            }
        });

        btnExcluirCategoria.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluirCategoria.setText("Excluir");
        btnExcluirCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCategoriaActionPerformed(evt);
            }
        });

        btnAlterarCategoria.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterarCategoria.setText("Alterar");
        btnAlterarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 480, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlterarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluirCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel20)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(169, 169, 169)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(btnSalvarCategoria)
                    .addComponent(btnCancelarCategoria)
                    .addComponent(btnExcluirCategoria)
                    .addComponent(btnAlterarCategoria))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados categorias", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Nome da categoria");

        txtBuscaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaCategoriaActionPerformed(evt);
            }
        });
        txtBuscaCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaCategoriaKeyPressed(evt);
            }
        });

        tabelaCategoria.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCategoria);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 395, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBuscaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de categorias", jPanel3);

        btnNovoCategoria.setBackground(new java.awt.Color(255, 255, 255));
        btnNovoCategoria.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovoCategoria.setText("Novo");
        btnNovoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoCategoria)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCategoriaActionPerformed
        abreCampos();
        txtBuscaCategoria.setEnabled(true);
        //btnBuscar.setEnabled(false);
        btnNovoCategoria.setEnabled(false);
        btnExcluirCategoria.setEnabled(false);
        btnAlterarCategoria.setVisible(false);
        btnCancelarCategoria.setEnabled(true);
        btnSalvarCategoria.setVisible(true);
        btnSalvarCategoria.setEnabled(true);
    }//GEN-LAST:event_btnNovoCategoriaActionPerformed

    private void txtBuscaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaCategoriaActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void tabelaCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCategoriaMouseClicked
        /* Pegando os Dados */
        jTabbedPane1.setSelectedIndex(0);
        txtCodCategoria.setText(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 0).toString());
        txtNomeCategoria.setText(tabelaCategoria.getValueAt(tabelaCategoria.getSelectedRow(), 1).toString());

        abreCampos();
        btnSalvarCategoria.setVisible(false);
        btnNovoCategoria.setEnabled(false);

        btnAlterarCategoria.setVisible(true);
        btnAlterarCategoria.setEnabled(true);
        btnExcluirCategoria.setEnabled(true);
        btnCancelarCategoria.setEnabled(true);

    }//GEN-LAST:event_tabelaCategoriaMouseClicked

    private void txtBuscaCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaCategoriaKeyPressed

        String nome = "%" + txtBuscaCategoria.getText() + "%";

        categoriaDAO = new CategoriaDAO();

        List<CategoriaProduto> lista = categoriaDAO.listarCategorias(nome);
        DefaultTableModel dadosTabela = (DefaultTableModel) tabelaCategoria.getModel();

        dadosTabela.setNumRows(0);

        for (CategoriaProduto c : lista) {
            dadosTabela.addRow(new Object[]{
                c.getCodigo_categoria(),
                c.getNome_categoria(),
            });
        }


    }//GEN-LAST:event_txtBuscaCategoriaKeyPressed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnAlterarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCategoriaActionPerformed
        List<String> listaCampos = getCampos();
        categoria = new CategoriaProduto();

        categoria.setCodigo_categoria(Integer.parseInt(txtCodCategoria.getText()));
        categoria.setNome_categoria(txtNomeCategoria.getText());

        try {
            categoriaDAO.AlterarCategoria(categoria);

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaVIEW.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        btnAlterarCategoria.setVisible(false);
        btnSalvarCategoria.setVisible(true);
        btnSalvarCategoria.setEnabled(false);
        btnNovoCategoria.setEnabled(true);
        limpaCampos();
        fechaCampos();
        fechaBotoes();
        tabelaCategoria.removeAll();
        listarCategoria();
    }//GEN-LAST:event_btnAlterarCategoriaActionPerformed

    private void btnExcluirCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCategoriaActionPerformed
        categoria = new CategoriaProduto();
        categoria.setCodigo_categoria(Integer.parseInt(txtCodCategoria.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtNomeCategoria.getText() + "?");

        if (confirma == 0) {
            try {
                categoriaDAO.ExcluirCategoria(categoria);

            } catch (SQLException ex) {
                Logger.getLogger(CategoriaVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            limpaCampos();
            fechaBotoes();
            fechaCampos();
            btnAlterarCategoria.setVisible(false);
            btnSalvarCategoria.setVisible(true);
            btnNovoCategoria.setEnabled(true);
            tabelaCategoria.removeAll();
            listarCategoria();

        }
    }//GEN-LAST:event_btnExcluirCategoriaActionPerformed

    private void btnCancelarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCategoriaActionPerformed
        limpaCampos();
        fechaBotoes();
        fechaCampos();

        btnAlterarCategoria.setEnabled(false);
        btnNovoCategoria.setEnabled(true);
    }//GEN-LAST:event_btnCancelarCategoriaActionPerformed

    private void btnSalvarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCategoriaActionPerformed
        List<String> listaCampos = getCampos();

        if (txtNomeCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha os campos");
        } else {
            categoria = new CategoriaProduto();

            categoria.setNome_categoria(listaCampos.get(0));

            try {
                categoriaDAO.SalvarCategoria(categoria);

            } catch (SQLException ex) {
                Logger.getLogger(CategoriaVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

            limpaCampos();
            fechaCampos();
            fechaBotoes();

            btnNovoCategoria.setEnabled(true);
            txtBuscaCategoria.setEnabled(true);
            tabelaCategoria.removeAll();
            listarCategoria();
        }

    }//GEN-LAST:event_btnSalvarCategoriaActionPerformed

    private void txtNomeCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeCategoriaActionPerformed

    }//GEN-LAST:event_txtNomeCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarCategoria;
    private javax.swing.JButton btnCancelarCategoria;
    private javax.swing.JButton btnExcluirCategoria;
    private javax.swing.JButton btnNovoCategoria;
    private javax.swing.JButton btnSalvarCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabelaCategoria;
    private javax.swing.JTextField txtBuscaCategoria;
    private javax.swing.JTextField txtCodCategoria;
    private javax.swing.JTextField txtNomeCategoria;
    // End of variables declaration//GEN-END:variables
}
