/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.ClienteDAO;
import MODEL.Cliente;
import MODEL.ClienteContato;
import MODEL.ClienteEndereco;
import MODEL.ClienteFisica;
import MODEL.ClienteJuridica;
import com.sun.glass.events.KeyEvent;
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
public class ClienteVIEW extends javax.swing.JFrame {

    Cliente cliente;
    ClienteContato cliContato;
    ClienteEndereco cliEndereco;
    ClienteFisica cliFisica;
    ClienteJuridica cliJuridica;
    ClienteDAO clienteDAO;

    public ClienteVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);

        clienteDAO = new ClienteDAO();

        txtCod.setEnabled(false);

        btnAlterar.setVisible(false);

        //btnBuscar.setEnabled(false);
        txtBusca.setEnabled(false);
        fechaBotoes();
        fechaCampos();
    }

    public void listarClientesFisica() {
        clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listarClientesFisica();
        DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();
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
        DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();
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
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    public void fechaCampos() {
        txtBairro.setEnabled(false);
        txtCelular.setEnabled(false);
        txtCep.setEnabled(false);
        txtCidade.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtCpf.setEnabled(false);
        txtEmail.setEnabled(false);
        txtIe.setEnabled(false);
        txtNome.setEnabled(false);
        txtNumero.setEnabled(false);
        txtRg.setEnabled(false);
        txtRua.setEnabled(false);
        txtTelefone.setEnabled(false);
        rbnPF.setEnabled(false);
        rbnPJ.setEnabled(false);
        jcbEstado.setEnabled(false);
        rbnPF.setSelected(false);
        rbnPJ.setSelected(false);
    }

    public void abreCampos() {
        txtBairro.setEnabled(true);
        txtCelular.setEnabled(true);
        txtCep.setEnabled(true);
        txtCidade.setEnabled(true);
        txtEmail.setEnabled(true);
        txtNome.setEnabled(true);
        txtNumero.setEnabled(true);
        txtRua.setEnabled(true);
        txtTelefone.setEnabled(true);
        rbnPF.setEnabled(true);
        rbnPJ.setEnabled(true);
        jcbEstado.setEnabled(true);
    }

    public void limpaCampos() {
        txtBairro.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtCidade.setText("");
        txtCnpj.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtIe.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtRg.setText("");
        txtRua.setText("");
        txtTelefone.setText("");
        jcbEstado.setSelectedItem("Selecione");
        txtCod.setText("");
    }

    public List<String> getCampos() {
        List<String> listaCampos = new ArrayList<>();

        if (txtNome.getText().isEmpty() || txtNumero.getText().isEmpty()
                || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty()
                || txtCep.getText().isEmpty() || txtCelular.getText().isEmpty()
                || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()
                || jcbEstado.getSelectedItem() == "Selecione" || txtRua.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            listaCampos.add(txtNome.getText());
            listaCampos.add(txtNumero.getText());
            listaCampos.add(txtBairro.getText());
            listaCampos.add(txtCidade.getText());

            listaCampos.add(txtCep.getText());
            listaCampos.add(txtCelular.getText());
            listaCampos.add(txtTelefone.getText());
            listaCampos.add(txtEmail.getText());
            listaCampos.add(txtRua.getText());

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
        txtRua = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jcbEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        rbnPF = new javax.swing.JRadioButton();
        rbnPJ = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtRg = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        txtCnpj = new javax.swing.JFormattedTextField();
        txtIe = new javax.swing.JFormattedTextField();
        txtCep = new javax.swing.JFormattedTextField();
        btnAlterar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        rbnBuscaFisica = new javax.swing.JRadioButton();
        rbnBuscaJuridica = new javax.swing.JRadioButton();
        btnNovo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Clientes");

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

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        jLabel12.setText("Celular");

        jLabel13.setText("Telefone fixo");

        jLabel14.setText("Email");

        rbnPF.setBackground(new java.awt.Color(255, 255, 255));
        rbnPF.setText("Pessoa Física ");
        rbnPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPFActionPerformed(evt);
            }
        });

        rbnPJ.setBackground(new java.awt.Color(255, 255, 255));
        rbnPJ.setText("Pessoa Jurídica");
        rbnPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPJActionPerformed(evt);
            }
        });

        jLabel15.setText("RG");

        jLabel16.setText("CPF");

        jLabel17.setText("CNPJ");

        jLabel18.setText("IE");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Auto Peças");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Barcelos");

        btnSalvar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        try {
            txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtIe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########.##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCepKeyPressed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
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
                                    .addComponent(txtRua, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtBairro)
                                    .addComponent(txtCidade)
                                    .addComponent(txtNumero)
                                    .addComponent(txtCep)
                                    .addComponent(jcbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbnPF)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(104, 104, 104))))
                                .addGap(10, 10, 10)))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(64, 64, 64))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(txtIe))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rbnPJ)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel20)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbnPF)
                            .addComponent(rbnPJ))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtIe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel20)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar))
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

        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        tabelaClientes.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome / Razão", "CPF / CNPJ", "RG / IE", "Celular", "Telefone fixo", "Email", "Endereço", "Numero", "Bairro", "Cidade", "Estado", "CEP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);

        rbnBuscaFisica.setBackground(new java.awt.Color(255, 255, 255));
        rbnBuscaFisica.setText("Pessoa Física");
        rbnBuscaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBuscaFisicaActionPerformed(evt);
            }
        });

        rbnBuscaJuridica.setBackground(new java.awt.Color(255, 255, 255));
        rbnBuscaJuridica.setText("Pessoa Jurídica");
        rbnBuscaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnBuscaJuridicaActionPerformed(evt);
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
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                .addComponent(rbnBuscaFisica)
                .addGap(18, 18, 18)
                .addComponent(rbnBuscaJuridica)
                .addGap(59, 59, 59))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnBuscaFisica)
                    .addComponent(rbnBuscaJuridica))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de clientes", jPanel3);

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovo)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        abreCampos();
        txtBusca.setEnabled(false);
        //btnBuscar.setEnabled(false);
        btnNovo.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAlterar.setVisible(false);
        btnCancelar.setEnabled(true);
        btnSalvar.setVisible(true);
        btnSalvar.setEnabled(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        fechaBotoes();
        fechaCampos();

        btnAlterar.setEnabled(false);
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
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

                cliContato = new ClienteContato();

                cliContato.setCelular_cliente(listaCampos.get(5));
                cliContato.setTelefone_cliente(listaCampos.get(6));
                cliContato.setEmail(listaCampos.get(7));

                cliEndereco = new ClienteEndereco();

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
                    Logger.getLogger(ClienteVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovo.setEnabled(true);
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

                cliContato = new ClienteContato();

                cliContato.setCelular_cliente(listaCampos.get(5));
                cliContato.setTelefone_cliente(listaCampos.get(6));
                cliContato.setEmail(listaCampos.get(7));

                cliEndereco = new ClienteEndereco();

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
                    Logger.getLogger(ClienteVIEW.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                limpaCampos();
                fechaCampos();
                fechaBotoes();

                btnNovo.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void rbnPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPFActionPerformed
        rbnPJ.setSelected(false);

        txtCpf.setEnabled(true);
        txtRg.setEnabled(true);

        txtCnpj.setEnabled(false);
        txtIe.setEnabled(false);

        txtCnpj.setText("");
        txtIe.setText("");
    }//GEN-LAST:event_rbnPFActionPerformed

    private void rbnPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPJActionPerformed
        rbnPF.setSelected(false);

        txtCpf.setText("");
        txtRg.setText("");

        txtCpf.setEnabled(false);
        txtRg.setEnabled(false);

        txtCnpj.setEnabled(true);
        txtIe.setEnabled(true);
    }//GEN-LAST:event_rbnPJActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void rbnBuscaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnBuscaFisicaActionPerformed
        rbnBuscaJuridica.setSelected(false);
        //btnBuscar.setEnabled(true);
        txtBusca.setEnabled(true);
        listarClientesFisica();
    }//GEN-LAST:event_rbnBuscaFisicaActionPerformed

    private void rbnBuscaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnBuscaJuridicaActionPerformed
        rbnBuscaFisica.setSelected(false);
        //btnBuscar.setEnabled(true);
        txtBusca.setEnabled(true);

        listarClientesJuridica();
    }//GEN-LAST:event_rbnBuscaJuridicaActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        List<String> listaCampos = getCampos();
        cliente = new Cliente();

        cliente.setCod_cliente(Integer.parseInt(txtCod.getText()));
        cliente.setNome_cliente(listaCampos.get(0));

        cliEndereco = new ClienteEndereco();

        cliEndereco.setBairro(listaCampos.get(2));
        cliEndereco.setCep(listaCampos.get(4));
        cliEndereco.setCidade(listaCampos.get(3));
        cliEndereco.setEstado((String) jcbEstado.getSelectedItem());
        cliEndereco.setNumero(Integer.parseInt(listaCampos.get(1)));
        cliEndereco.setRua(listaCampos.get(8));

        cliContato = new ClienteContato();

        cliContato.setCelular_cliente(listaCampos.get(5));
        cliContato.setTelefone_cliente(listaCampos.get(6));
        cliContato.setEmail(listaCampos.get(7));

        try {
            clienteDAO.alterar(cliente, cliEndereco, cliContato);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        btnAlterar.setVisible(false);
        btnSalvar.setVisible(true);
        btnSalvar.setEnabled(false);
        btnNovo.setEnabled(true);
        limpaCampos();
        fechaCampos();
        fechaBotoes();

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        cliente = new Cliente();
        cliente.setCod_cliente(Integer.parseInt(txtCod.getText()));

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário " + txtNome.getText() + "?");

        if (confirma == 0) {
            try {
                clienteDAO.excluir(cliente);

            } catch (SQLException ex) {
                Logger.getLogger(ClienteVIEW.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            limpaCampos();
            fechaBotoes();
            fechaCampos();
            btnAlterar.setVisible(false);
            btnSalvar.setVisible(true);
            btnNovo.setEnabled(true);

        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
        /* Pegando os Dados */
        jTabbedPane1.setSelectedIndex(0);
        txtCod.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 0).toString());
        txtNome.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 1).toString());
        txtCelular.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 4).toString());
        txtTelefone.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 5).toString());
        txtEmail.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 6).toString());
        txtRua.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 7).toString());
        txtNumero.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 8).toString());
        txtBairro.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 9).toString());
        txtCidade.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 10).toString());
        jcbEstado.setSelectedItem(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 11).toString());
        txtCep.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 12).toString());

        if (rbnBuscaFisica.isSelected()) {
            txtCpf.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2).toString());
            txtRg.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3).toString());

            txtCnpj.setText("");
            txtIe.setText("");
        } else {
            if (rbnBuscaJuridica.isSelected()) {
                txtCnpj.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2).toString());
                txtIe.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3).toString());

                txtCpf.setText("");
                txtRg.setText("");
            }
        }

        abreCampos();
        btnSalvar.setVisible(false);
        btnNovo.setEnabled(false);

        btnAlterar.setVisible(true);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);

        rbnPF.setEnabled(false);
        rbnPJ.setEnabled(false);
        txtCpf.setEnabled(false);
        txtRg.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtIe.setEnabled(false);
    }//GEN-LAST:event_tabelaClientesMouseClicked

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        if (rbnBuscaFisica.isSelected()) {
            String nome = "%" + txtBusca.getText() + "%";

            clienteDAO = new ClienteDAO();

            List<Cliente> lista = clienteDAO.buscaClientesFisica(nome);
            DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();

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
                String nome = "%" + txtBusca.getText() + "%";

                clienteDAO = new ClienteDAO();

                List<Cliente> lista2 = clienteDAO.buscaClienteJuridica(nome);
                DefaultTableModel dados = (DefaultTableModel) tabelaClientes.getModel();

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
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyPressed

    }//GEN-LAST:event_txtCepKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JComboBox<String> jcbEstado;
    private javax.swing.JRadioButton rbnBuscaFisica;
    private javax.swing.JRadioButton rbnBuscaJuridica;
    private javax.swing.JRadioButton rbnPF;
    private javax.swing.JRadioButton rbnPJ;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCod;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtEmail;
    private javax.swing.JFormattedTextField txtIe;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JTextField txtRua;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
