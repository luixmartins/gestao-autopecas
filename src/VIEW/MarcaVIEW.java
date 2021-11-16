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
public class MarcaVIEW extends javax.swing.JFrame {

    Cliente cliente;
    Contato cliContato;
    Endereco cliEndereco;
    ClienteFisica cliFisica;
    ClienteJuridica cliJuridica;
    ClienteDAO clienteDAO;

    public MarcaVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);

        clienteDAO = new ClienteDAO();

        txtCodMarca.setEnabled(false);

        btnAlterarMarca.setVisible(false);
        txtBuscaMarca.setEnabled(false);
        fechaBotoes();
        fechaCampos();
    }

    public void listarClientesFisica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listarClientesFisica();
        DefaultTableModel dados = (DefaultTableModel) tabelaMarca.getModel();
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
        DefaultTableModel dados = (DefaultTableModel) tabelaMarca.getModel();
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
        btnSalvarMarca.setEnabled(false);
        btnCancelarMarca.setEnabled(false);
        btnExcluirMarca.setEnabled(false);
    }

    public void fechaCampos() {
        txtNomeMarca.setEnabled(false);
    }

    public void abreCampos() {       
        txtNomeMarca.setEnabled(true);
    }

    public void limpaCampos() {
        txtNomeMarca.setText("");
        txtCodMarca.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtNomeMarca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            listaCampos.add(txtNomeMarca.getText());

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
        txtCodMarca = new javax.swing.JTextField();
        txtNomeMarca = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarMarca = new javax.swing.JButton();
        btnCancelarMarca = new javax.swing.JButton();
        btnExcluirMarca = new javax.swing.JButton();
        btnAlterarMarca = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscaMarca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMarca = new javax.swing.JTable();
        rbnBuscaMarca = new javax.swing.JRadioButton();
        btnNovoMarca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Marca");

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

        jLabel3.setText("Nome da Marca");

        txtCodMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodMarcaActionPerformed(evt);
            }
        });

        txtNomeMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeMarcaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvarMarca.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvarMarca.setText("Salvar");
        btnSalvarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMarcaActionPerformed(evt);
            }
        });

        btnCancelarMarca.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarMarca.setText("Cancelar");
        btnCancelarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarMarcaActionPerformed(evt);
            }
        });

        btnExcluirMarca.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluirMarca.setText("Excluir");
        btnExcluirMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMarcaActionPerformed(evt);
            }
        });

        btnAlterarMarca.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterarMarca.setText("Alterar");
        btnAlterarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(btnAlterarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluirMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel20)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(169, 169, 169)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(btnSalvarMarca)
                    .addComponent(btnCancelarMarca)
                    .addComponent(btnExcluirMarca)
                    .addComponent(btnAlterarMarca))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados marcas", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Nome da marca");

        txtBuscaMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaMarcaActionPerformed(evt);
            }
        });
        txtBuscaMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaMarcaKeyPressed(evt);
            }
        });

        tabelaMarca.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMarca);

        rbnBuscaMarca.setBackground(new java.awt.Color(255, 255, 255));
        rbnBuscaMarca.setText("Marca");
        rbnBuscaMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBuscaMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 412, Short.MAX_VALUE)
                .addComponent(rbnBuscaMarca)
                .addGap(189, 189, 189))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBuscaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnBuscaMarca))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de marcas", jPanel3);

        btnNovoMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnNovoMarca.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovoMarca.setText("Novo");
        btnNovoMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoMarcaActionPerformed(evt);
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
                .addComponent(btnNovoMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoMarca)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoMarcaActionPerformed
        abreCampos();
        txtBuscaMarca.setEnabled(false);
        //btnBuscar.setEnabled(false);
        btnNovoMarca.setEnabled(false);
        btnExcluirMarca.setEnabled(false);
        btnAlterarMarca.setVisible(false);
        btnCancelarMarca.setEnabled(true);
        btnSalvarMarca.setVisible(true);
        btnSalvarMarca.setEnabled(true);
    }//GEN-LAST:event_btnNovoMarcaActionPerformed

    private void txtBuscaMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaMarcaActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void rbnBuscaMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnBuscaMarcaActionPerformed
        
        //btnBuscar.setEnabled(true);
        txtBuscaMarca.setEnabled(true);
        listarClientesFisica();
    }//GEN-LAST:event_rbnBuscaMarcaActionPerformed

    private void tabelaMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMarcaMouseClicked
        /* Pegando os Dados */
        jTabbedPane1.setSelectedIndex(0);
        txtCodMarca.setText(tabelaMarca.getValueAt(tabelaMarca.getSelectedRow(), 0).toString());
        txtNomeMarca.setText(tabelaMarca.getValueAt(tabelaMarca.getSelectedRow(), 1).toString());

        abreCampos();
        btnSalvarMarca.setVisible(false);
        btnNovoMarca.setEnabled(false);

        btnAlterarMarca.setVisible(true);
        btnAlterarMarca.setEnabled(true);
        btnExcluirMarca.setEnabled(true);
        btnCancelarMarca.setEnabled(true);

    }//GEN-LAST:event_tabelaMarcaMouseClicked

    private void txtBuscaMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaMarcaKeyPressed
        if (rbnBuscaMarca.isSelected()) {
            String nome = "%" + txtBuscaMarca.getText() + "%";

            clienteDAO = new ClienteDAO();

            List<Cliente> lista = clienteDAO.buscaClientesFisica(nome);
            DefaultTableModel dados = (DefaultTableModel) tabelaMarca.getModel();

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
    }//GEN-LAST:event_txtBuscaMarcaKeyPressed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnAlterarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarMarcaActionPerformed
        List<String> listaCampos = getCampos();
        cliente = new Cliente();

        cliente.setCod_cliente(Integer.parseInt(txtCodMarca.getText()));
        cliente.setNome_cliente(listaCampos.get(0));

        cliEndereco = new Endereco();

        cliEndereco.setBairro(listaCampos.get(2));
        cliEndereco.setCep(listaCampos.get(4));
        cliEndereco.setCidade(listaCampos.get(3));
        cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
        cliEndereco.setRua(listaCampos.get(8));

        cliContato = new Contato();

        cliContato.setCelular_cliente(listaCampos.get(5));
        cliContato.setTelefone_cliente(listaCampos.get(6));
        cliContato.setEmail(listaCampos.get(7));

        try {
            clienteDAO.alterar(cliente, cliEndereco, cliContato);
        } catch (SQLException ex) {
            Logger.getLogger(MarcaVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        btnAlterarMarca.setVisible(false);
        btnSalvarMarca.setVisible(true);
        btnSalvarMarca.setEnabled(false);
        btnNovoMarca.setEnabled(true);
        limpaCampos();
        fechaCampos();
        fechaBotoes();
    }//GEN-LAST:event_btnAlterarMarcaActionPerformed

    private void btnExcluirMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMarcaActionPerformed
        cliente = new Cliente();
        cliente.setCod_cliente(Integer.parseInt(txtCodMarca.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtNomeMarca.getText() + "?");

        if (confirma == 0) {
            try {
                clienteDAO.excluir(cliente);

            } catch (SQLException ex) {
                Logger.getLogger(MarcaVIEW.class
                    .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            limpaCampos();
            fechaBotoes();
            fechaCampos();
            btnAlterarMarca.setVisible(false);
            btnSalvarMarca.setVisible(true);
            btnNovoMarca.setEnabled(true);

        }
    }//GEN-LAST:event_btnExcluirMarcaActionPerformed

    private void btnCancelarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarMarcaActionPerformed
        limpaCampos();
        fechaBotoes();
        fechaCampos();

        btnAlterarMarca.setEnabled(false);
        btnNovoMarca.setEnabled(true);
    }//GEN-LAST:event_btnCancelarMarcaActionPerformed

    private void btnSalvarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMarcaActionPerformed
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
                cliEndereco.setEstado((String) jcbEstado.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                cliFisica = new ClienteFisica();

                cliFisica.setCpf(txtCpf.getText());
                cliFisica.setRg(txtRg.getText());

                try {
                    clienteDAO.salvarFisica(cliente, cliFisica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(MarcaVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoMarca.setEnabled(true);
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
                cliEndereco.setEstado((String) jcbEstado.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                cliJuridica = new ClienteJuridica();

                cliJuridica.setCnpj(txtCnpj.getText());
                cliJuridica.setInscricao_estadual(txtIe.getText());

                try {
                    clienteDAO.salvarJuridica(cliente, cliJuridica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(MarcaVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoMarca.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnSalvarMarcaActionPerformed

    private void txtNomeMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeMarcaActionPerformed

    private void txtCodMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMarcaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarMarca;
    private javax.swing.JButton btnCancelarMarca;
    private javax.swing.JButton btnExcluirMarca;
    private javax.swing.JButton btnNovoMarca;
    private javax.swing.JButton btnSalvarMarca;
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
    private javax.swing.JRadioButton rbnBuscaMarca;
    private javax.swing.JTable tabelaMarca;
    private javax.swing.JTextField txtBuscaMarca;
    private javax.swing.JTextField txtCodMarca;
    private javax.swing.JTextField txtNomeMarca;
    // End of variables declaration//GEN-END:variables
}
