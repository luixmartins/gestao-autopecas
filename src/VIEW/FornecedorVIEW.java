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
public class FornecedorVIEW extends javax.swing.JFrame {

    Cliente cliente;
    Contato cliContato;
    Endereco cliEndereco;
    ClienteFisica cliFisica;
    ClienteJuridica cliJuridica;
    ClienteDAO clienteDAO;

    public FornecedorVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);

        clienteDAO = new ClienteDAO();

        txtCodFornecedores.setEnabled(false);

        btnAlterarFornecedores.setVisible(false);
        txtBuscaFornecedores.setEnabled(false);
        fechaBotoes();
        fechaCampos();
    }

    public void listarClientesFisica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listarClientesFisica();
        DefaultTableModel dados = (DefaultTableModel) tabelaClientesFornecedores.getModel();
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
        DefaultTableModel dados = (DefaultTableModel) tabelaClientesFornecedores.getModel();
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
        btnSalvarFornecedores.setEnabled(false);
        btnCancelarFornecedores.setEnabled(false);
        btnExcluirFornecedores.setEnabled(false);
    }

    public void fechaCampos() {
        txtBairroFornecedores.setEnabled(false);
        txtCelularFornecedores.setEnabled(false);
        txtCepFornecedores.setEnabled(false);
        txtCidadeFornecedores.setEnabled(false);
        txtCnpjFornecedores.setEnabled(false);
        txtEmailFornecedores.setEnabled(false);
        txtIeFornecedores.setEnabled(false);
        txtNomeFornecedores.setEnabled(false);
        txtNumeroFornecedores.setEnabled(false);
        txtRuaFornecedores.setEnabled(false);
        txtTelefoneFornecedores.setEnabled(false);
        rbnPJFornecedores.setEnabled(false);
        jcbEstadoFornecedores.setEnabled(false);
        rbnPJFornecedores.setSelected(false);
    }

    public void abreCampos() {
        txtBairroFornecedores.setEnabled(true);
        txtCelularFornecedores.setEnabled(true);
        txtCepFornecedores.setEnabled(true);
        txtCidadeFornecedores.setEnabled(true);
        txtEmailFornecedores.setEnabled(true);
        txtNomeFornecedores.setEnabled(true);
        txtNumeroFornecedores.setEnabled(true);
        txtRuaFornecedores.setEnabled(true);
        txtTelefoneFornecedores.setEnabled(true);
        rbnPJFornecedores.setEnabled(true);
        jcbEstadoFornecedores.setEnabled(true);
    }

    public void limpaCampos() {
        txtBairroFornecedores.setText("");
        txtCelularFornecedores.setText("");
        txtCepFornecedores.setText("");
        txtCidadeFornecedores.setText("");
        txtCnpjFornecedores.setText("");
        txtEmailFornecedores.setText("");
        txtIeFornecedores.setText("");
        txtNomeFornecedores.setText("");
        txtNumeroFornecedores.setText("");
        txtRuaFornecedores.setText("");
        txtTelefoneFornecedores.setText("");
        jcbEstadoFornecedores.setSelectedItem("Selecione");
        txtCodFornecedores.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtNomeFornecedores.getText().isEmpty() || txtNumeroFornecedores.getText().isEmpty()
                || txtBairroFornecedores.getText().isEmpty() || txtCidadeFornecedores.getText().isEmpty()
                || txtCepFornecedores.getText().isEmpty() || txtCelularFornecedores.getText().isEmpty()
                || txtTelefoneFornecedores.getText().isEmpty() || txtEmailFornecedores.getText().isEmpty()
                || jcbEstadoFornecedores.getSelectedItem() == "Selecione" || txtRuaFornecedores.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            listaCampos.add(txtNomeFornecedores.getText());
            listaCampos.add(txtNumeroFornecedores.getText());
            listaCampos.add(txtBairroFornecedores.getText());
            listaCampos.add(txtCidadeFornecedores.getText());

            listaCampos.add(txtCepFornecedores.getText());
            listaCampos.add(txtCelularFornecedores.getText());
            listaCampos.add(txtTelefoneFornecedores.getText());
            listaCampos.add(txtEmailFornecedores.getText());
            listaCampos.add(txtRuaFornecedores.getText());

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtRuaFornecedores = new javax.swing.JTextField();
        txtNumeroFornecedores = new javax.swing.JTextField();
        txtBairroFornecedores = new javax.swing.JTextField();
        txtCidadeFornecedores = new javax.swing.JTextField();
        jcbEstadoFornecedores = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCodFornecedores = new javax.swing.JTextField();
        txtNomeFornecedores = new javax.swing.JTextField();
        rbnPJFornecedores = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvarFornecedores = new javax.swing.JButton();
        btnCancelarFornecedores = new javax.swing.JButton();
        btnExcluirFornecedores = new javax.swing.JButton();
        txtTelefoneFornecedores = new javax.swing.JFormattedTextField();
        txtEmailFornecedores = new javax.swing.JFormattedTextField();
        txtCelularFornecedores = new javax.swing.JFormattedTextField();
        txtCnpjFornecedores = new javax.swing.JFormattedTextField();
        txtIeFornecedores = new javax.swing.JFormattedTextField();
        txtCepFornecedores = new javax.swing.JFormattedTextField();
        btnAlterarFornecedores = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscaFornecedores = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientesFornecedores = new javax.swing.JTable();
        rbnBuscaJuridicaFornecedores = new javax.swing.JRadioButton();
        btnNovoFornecedores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Fornecedores");

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

        jLabel3.setText("Nome / razão social");

        jLabel4.setText("UF");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Dados de endereço");

        jLabel6.setText("Cidade");

        jLabel7.setText("Número");

        jLabel8.setText("Bairro");

        jLabel9.setText("Rua");

        jLabel10.setText("CEP");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Dados de contato");

        jcbEstadoFornecedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        jLabel12.setText("Celular");

        jLabel13.setText("Telefone fixo");

        jLabel14.setText("Email");

        txtCodFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodFornecedoresActionPerformed(evt);
            }
        });

        txtNomeFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeFornecedoresActionPerformed(evt);
            }
        });

        rbnPJFornecedores.setBackground(new java.awt.Color(255, 255, 255));
        rbnPJFornecedores.setText("Pessoa Jurídica");
        rbnPJFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPJFornecedoresActionPerformed(evt);
            }
        });

        jLabel17.setText("CNPJ");

        jLabel18.setText("IE");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvarFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvarFornecedores.setText("Salvar");
        btnSalvarFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarFornecedoresActionPerformed(evt);
            }
        });

        btnCancelarFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelarFornecedores.setText("Cancelar");
        btnCancelarFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFornecedoresActionPerformed(evt);
            }
        });

        btnExcluirFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluirFornecedores.setText("Excluir");
        btnExcluirFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFornecedoresActionPerformed(evt);
            }
        });

        try {
            txtTelefoneFornecedores.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCelularFornecedores.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCnpjFornecedores.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtIeFornecedores.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########.##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCepFornecedores.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCepFornecedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCepFornecedoresKeyPressed(evt);
            }
        });

        btnAlterarFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterarFornecedores.setText("Alterar");
        btnAlterarFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFornecedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRuaFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtBairroFornecedores)
                                    .addComponent(txtCidadeFornecedores)
                                    .addComponent(txtNumeroFornecedores)
                                    .addComponent(txtCepFornecedores)
                                    .addComponent(jcbEstadoFornecedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEmailFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtTelefoneFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtCelularFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(104, 104, 104))
                                        .addComponent(rbnPJFornecedores)))
                                .addGap(80, 80, 80))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIeFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCnpjFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel19)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAlterarFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel20)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(rbnPJFornecedores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtCnpjFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtIeFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRuaFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumeroFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCelularFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBairroFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefoneFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCidadeFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtEmailFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbEstadoFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel20)
                    .addComponent(btnSalvarFornecedores)
                    .addComponent(btnCancelarFornecedores)
                    .addComponent(btnExcluirFornecedores)
                    .addComponent(txtCepFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterarFornecedores))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados clientes", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Nome / Razão social");

        txtBuscaFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaFornecedoresActionPerformed(evt);
            }
        });
        txtBuscaFornecedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaFornecedoresKeyPressed(evt);
            }
        });

        tabelaClientesFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaClientesFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome / Razão", "CNPJ", "IE", "Celular", "Telefone fixo", "Email", "Endereço", "Numero", "Bairro", "Cidade", "Estado", "CEP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientesFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientesFornecedores);

        rbnBuscaJuridicaFornecedores.setBackground(new java.awt.Color(255, 255, 255));
        rbnBuscaJuridicaFornecedores.setText("Pessoa Jurídica");
        rbnBuscaJuridicaFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBuscaJuridicaFornecedoresActionPerformed(evt);
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
                .addComponent(txtBuscaFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
                .addComponent(rbnBuscaJuridicaFornecedores)
                .addGap(59, 59, 59))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBuscaFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnBuscaJuridicaFornecedores))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de clientes", jPanel3);

        btnNovoFornecedores.setBackground(new java.awt.Color(255, 255, 255));
        btnNovoFornecedores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNovoFornecedores.setText("Novo");
        btnNovoFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoFornecedoresActionPerformed(evt);
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
                .addComponent(btnNovoFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoFornecedores)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoFornecedoresActionPerformed
        abreCampos();
        txtBuscaFornecedores.setEnabled(false);
        //btnBuscar.setEnabled(false);
        btnNovoFornecedores.setEnabled(false);
        btnExcluirFornecedores.setEnabled(false);
        btnAlterarFornecedores.setVisible(false);
        btnCancelarFornecedores.setEnabled(true);
        btnSalvarFornecedores.setVisible(true);
        btnSalvarFornecedores.setEnabled(true);
    }//GEN-LAST:event_btnNovoFornecedoresActionPerformed

    private void btnCancelarFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFornecedoresActionPerformed
        limpaCampos();
        fechaBotoes();
        fechaCampos();

        btnAlterarFornecedores.setEnabled(false);
        btnNovoFornecedores.setEnabled(true);
    }//GEN-LAST:event_btnCancelarFornecedoresActionPerformed

    private void btnSalvarFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarFornecedoresActionPerformed
        List<String> listaCampos = getCampos();
        if (rbnPF.isSelected() == false && rbnPJFornecedores.isSelected() == false) {
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
                cliEndereco.setEstado((String) jcbEstadoFornecedores.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                cliFisica = new ClienteFisica();


                try {
                    clienteDAO.salvarFisica(cliente, cliFisica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(FornecedorVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoFornecedores.setEnabled(true);
            }
        }
        if (rbnPJFornecedores.isSelected()) {
            if (txtCnpjFornecedores.getText().matches(".*\\d.*") == false
                    || txtIeFornecedores.getText().matches(".*\\d.*") == false) {
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
                cliEndereco.setEstado((String) jcbEstadoFornecedores.getSelectedItem());
                cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
                cliEndereco.setRua(listaCampos.get(8));

                cliJuridica = new ClienteJuridica();

                cliJuridica.setCnpj(txtCnpjFornecedores.getText());
                cliJuridica.setInscricao_estadual(txtIeFornecedores.getText());

                try {
                    clienteDAO.salvarJuridica(cliente, cliJuridica, cliEndereco, cliContato);

                } catch (SQLException ex) {
                    Logger.getLogger(FornecedorVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovoFornecedores.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnSalvarFornecedoresActionPerformed

    private void rbnPJFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPJFornecedoresActionPerformed

        txtCnpjFornecedores.setEnabled(true);
        txtIeFornecedores.setEnabled(true);
    }//GEN-LAST:event_rbnPJFornecedoresActionPerformed

    private void txtBuscaFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaFornecedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaFornecedoresActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void rbnBuscaJuridicaFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnBuscaJuridicaFornecedoresActionPerformed
       
        //btnBuscar.setEnabled(true);
        txtBuscaFornecedores.setEnabled(true);

        listarClientesJuridica();
    }//GEN-LAST:event_rbnBuscaJuridicaFornecedoresActionPerformed

    private void btnAlterarFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFornecedoresActionPerformed
        List<String> listaCampos = getCampos();
        cliente = new Cliente();

        cliente.setCod_cliente(Integer.parseInt(txtCodFornecedores.getText()));
        cliente.setNome_cliente(listaCampos.get(0));

        cliEndereco = new Endereco();

        cliEndereco.setBairro(listaCampos.get(2));
        cliEndereco.setCep(listaCampos.get(4));
        cliEndereco.setCidade(listaCampos.get(3));
        cliEndereco.setEstado((String) jcbEstadoFornecedores.getSelectedItem());
        cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
        cliEndereco.setRua(listaCampos.get(8));

        cliContato = new Contato();

        cliContato.setCelular_cliente(listaCampos.get(5));
        cliContato.setTelefone_cliente(listaCampos.get(6));
        cliContato.setEmail(listaCampos.get(7));

        try {
            clienteDAO.alterar(cliente, cliEndereco, cliContato);
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        btnAlterarFornecedores.setVisible(false);
        btnSalvarFornecedores.setVisible(true);
        btnSalvarFornecedores.setEnabled(false);
        btnNovoFornecedores.setEnabled(true);
        limpaCampos();
        fechaCampos();
        fechaBotoes();

    }//GEN-LAST:event_btnAlterarFornecedoresActionPerformed

    private void btnExcluirFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFornecedoresActionPerformed
        cliente = new Cliente();
        cliente.setCod_cliente(Integer.parseInt(txtCodFornecedores.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtNomeFornecedores.getText() + "?");

        if (confirma == 0) {
            try {
                clienteDAO.excluir(cliente);

            } catch (SQLException ex) {
                Logger.getLogger(FornecedorVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            limpaCampos();
            fechaBotoes();
            fechaCampos();
            btnAlterarFornecedores.setVisible(false);
            btnSalvarFornecedores.setVisible(true);
            btnNovoFornecedores.setEnabled(true);

        }
    }//GEN-LAST:event_btnExcluirFornecedoresActionPerformed

    private void tabelaClientesFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesFornecedoresMouseClicked
        /* Pegando os Dados */
        jTabbedPane1.setSelectedIndex(0);
        txtCodFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 0).toString());
        txtNomeFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 1).toString());
        txtCelularFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 4).toString());
        txtTelefoneFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 5).toString());
        txtEmailFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 6).toString());
        txtRuaFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 7).toString());
        txtNumeroFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 8).toString());
        txtBairroFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 9).toString());
        txtCidadeFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 10).toString());
        jcbEstadoFornecedores.setSelectedItem(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 11).toString());
        txtCepFornecedores.setText(tabelaClientesFornecedores.getValueAt(tabelaClientesFornecedores.getSelectedRow(), 12).toString());

        abreCampos();
        btnSalvarFornecedores.setVisible(false);
        btnNovoFornecedores.setEnabled(false);

        btnAlterarFornecedores.setVisible(true);
        btnAlterarFornecedores.setEnabled(true);
        btnExcluirFornecedores.setEnabled(true);
        btnCancelarFornecedores.setEnabled(true);


        rbnPJFornecedores.setEnabled(false);
        txtCnpjFornecedores.setEnabled(false);
        txtIeFornecedores.setEnabled(false);
    }//GEN-LAST:event_tabelaClientesFornecedoresMouseClicked

    private void txtBuscaFornecedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaFornecedoresKeyPressed

            if (rbnBuscaJuridicaFornecedores.isSelected()) {
                String nome = "%" + txtBuscaFornecedores.getText() + "%";

                clienteDAO = new ClienteDAO();

                List<Cliente> lista2 = clienteDAO.buscaClienteJuridica(nome);
                DefaultTableModel dados = (DefaultTableModel) tabelaClientesFornecedores.getModel();

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
    }//GEN-LAST:event_txtBuscaFornecedoresKeyPressed

    private void txtCepFornecedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepFornecedoresKeyPressed

    }//GEN-LAST:event_txtCepFornecedoresKeyPressed

    private void txtNomeFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeFornecedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFornecedoresActionPerformed

    private void txtCodFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodFornecedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodFornecedoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarFornecedores;
    private javax.swing.JButton btnCancelarFornecedores;
    private javax.swing.JButton btnExcluirFornecedores;
    private javax.swing.JButton btnNovoFornecedores;
    private javax.swing.JButton btnSalvarFornecedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbEstadoFornecedores;
    private javax.swing.JRadioButton rbnBuscaJuridicaFornecedores;
    private javax.swing.JRadioButton rbnPJFornecedores;
    private javax.swing.JTable tabelaClientesFornecedores;
    private javax.swing.JTextField txtBairroFornecedores;
    private javax.swing.JTextField txtBuscaFornecedores;
    private javax.swing.JFormattedTextField txtCelularFornecedores;
    private javax.swing.JFormattedTextField txtCepFornecedores;
    private javax.swing.JTextField txtCidadeFornecedores;
    private javax.swing.JFormattedTextField txtCnpjFornecedores;
    private javax.swing.JTextField txtCodFornecedores;
    private javax.swing.JFormattedTextField txtEmailFornecedores;
    private javax.swing.JFormattedTextField txtIeFornecedores;
    private javax.swing.JTextField txtNomeFornecedores;
    private javax.swing.JTextField txtNumeroFornecedores;
    private javax.swing.JTextField txtRuaFornecedores;
    private javax.swing.JFormattedTextField txtTelefoneFornecedores;
    // End of variables declaration//GEN-END:variables
}
