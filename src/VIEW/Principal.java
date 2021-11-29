
package VIEW;

import MODEL.Usuario;
import javax.swing.JFrame;

public class Principal extends javax.swing.JFrame {

    
    public Principal(Usuario user) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconeMarca = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iconeCliente = new javax.swing.JLabel();
        iconeFornecedor = new javax.swing.JLabel();
        iconeProdutos = new javax.swing.JLabel();
        iconeEntrada = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        labelProduto = new javax.swing.JLabel();
        labelEntrada = new javax.swing.JLabel();
        labelFornecedor = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mni_cliente = new javax.swing.JMenu();
        mni_Cliente = new javax.swing.JMenuItem();
        mni_Fornecedor = new javax.swing.JMenuItem();
        mni_Produto = new javax.swing.JMenuItem();
        mni_Entrada = new javax.swing.JMenuItem();
        mnu_sair = new javax.swing.JMenu();
        mni_sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("Auto Peças");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("Barcelos");

        iconeCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user.png"))); // NOI18N
        iconeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconeClienteMouseClicked(evt);
            }
        });

        iconeFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/fornecem (2).png"))); // NOI18N
        iconeFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconeFornecedorMouseClicked(evt);
            }
        });

        iconeProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/gestao-de-produtos.png"))); // NOI18N
        iconeProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconeProdutosMouseClicked(evt);
            }
        });

        iconeEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/caixa-de-entrada.png"))); // NOI18N
        iconeEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconeEntradaMouseClicked(evt);
            }
        });

        labelCliente.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        labelCliente.setText("Cliente");
        labelCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelClienteMouseClicked(evt);
            }
        });

        labelProduto.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        labelProduto.setText("Produtos");
        labelProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelProdutoMouseClicked(evt);
            }
        });

        labelEntrada.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        labelEntrada.setText("Entrada");
        labelEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelEntradaMouseClicked(evt);
            }
        });

        labelFornecedor.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        labelFornecedor.setText("Fornecedor");
        labelFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFornecedorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout iconeMarcaLayout = new javax.swing.GroupLayout(iconeMarca);
        iconeMarca.setLayout(iconeMarcaLayout);
        iconeMarcaLayout.setHorizontalGroup(
            iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconeMarcaLayout.createSequentialGroup()
                .addContainerGap(698, Short.MAX_VALUE)
                .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconeMarcaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconeMarcaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(84, 84, 84))))
            .addGroup(iconeMarcaLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconeMarcaLayout.createSequentialGroup()
                        .addComponent(labelCliente)
                        .addGap(142, 142, 142)
                        .addComponent(labelFornecedor)
                        .addGap(123, 123, 123)
                        .addComponent(labelProduto)
                        .addGap(125, 125, 125)
                        .addComponent(labelEntrada)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconeMarcaLayout.createSequentialGroup()
                        .addComponent(iconeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(iconeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(iconeProdutos)
                        .addGap(45, 45, 45)
                        .addComponent(iconeEntrada)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        iconeMarcaLayout.setVerticalGroup(
            iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconeMarcaLayout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(iconeMarcaLayout.createSequentialGroup()
                        .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconeEntrada)
                            .addComponent(iconeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCliente))
                    .addGroup(iconeMarcaLayout.createSequentialGroup()
                        .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconeProdutos)
                            .addComponent(iconeFornecedor))
                        .addGap(22, 22, 22)
                        .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelProduto)
                            .addGroup(iconeMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelFornecedor)
                                .addComponent(labelEntrada)))))
                .addGap(88, 88, 88)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(55, 55, 55))
        );

        mni_cliente.setText("Arquivo");

        mni_Cliente.setText("Cliente");
        mni_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ClienteActionPerformed(evt);
            }
        });
        mni_cliente.add(mni_Cliente);

        mni_Fornecedor.setText("Fornecedor");
        mni_Fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_FornecedorActionPerformed(evt);
            }
        });
        mni_cliente.add(mni_Fornecedor);

        mni_Produto.setText("Produto");
        mni_Produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ProdutoActionPerformed(evt);
            }
        });
        mni_cliente.add(mni_Produto);

        mni_Entrada.setText("Entrada");
        mni_Entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_EntradaActionPerformed(evt);
            }
        });
        mni_cliente.add(mni_Entrada);

        jMenuBar1.add(mni_cliente);

        mnu_sair.setText("Sair");

        mni_sair.setText("Sair");
        mni_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_sairActionPerformed(evt);
            }
        });
        mnu_sair.add(mni_sair);

        jMenuBar1.add(mnu_sair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconeMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(iconeMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mni_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_mni_sairActionPerformed

    private void mni_EntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_EntradaActionPerformed
        EntradaVIEW entradaview = new EntradaVIEW();
        entradaview.setVisible(true);
    }//GEN-LAST:event_mni_EntradaActionPerformed

    private void mni_ProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ProdutoActionPerformed
        ProdutoVIEW produtoview = new ProdutoVIEW();
        produtoview.setVisible(true);
    }//GEN-LAST:event_mni_ProdutoActionPerformed

    private void mni_FornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_FornecedorActionPerformed
        FornecedorVIEW fornecedorview = new FornecedorVIEW();
        fornecedorview.setVisible(true);
    }//GEN-LAST:event_mni_FornecedorActionPerformed

    private void mni_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ClienteActionPerformed
        /* Instanciando tela Cliente View*/
        ClienteVIEW view = new ClienteVIEW();
        view.setVisible(true);
    }//GEN-LAST:event_mni_ClienteActionPerformed

    private void iconeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconeClienteMouseClicked
        ClienteVIEW view = new ClienteVIEW();
        view.setVisible(true);
    }//GEN-LAST:event_iconeClienteMouseClicked

    private void labelClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelClienteMouseClicked
        ClienteVIEW view = new ClienteVIEW();
        view.setVisible(true);
    }//GEN-LAST:event_labelClienteMouseClicked

    private void iconeFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconeFornecedorMouseClicked
        FornecedorVIEW fornecedorview = new FornecedorVIEW();
        fornecedorview.setVisible(true);
    }//GEN-LAST:event_iconeFornecedorMouseClicked

    private void labelFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFornecedorMouseClicked
        FornecedorVIEW fornecedorview = new FornecedorVIEW();
        fornecedorview.setVisible(true);
    }//GEN-LAST:event_labelFornecedorMouseClicked

    private void iconeProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconeProdutosMouseClicked
        ProdutoVIEW produtoview = new ProdutoVIEW();
        produtoview.setVisible(true);
    }//GEN-LAST:event_iconeProdutosMouseClicked

    private void labelProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelProdutoMouseClicked
        ProdutoVIEW produtoview = new ProdutoVIEW();
        produtoview.setVisible(true);
    }//GEN-LAST:event_labelProdutoMouseClicked

    private void iconeEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconeEntradaMouseClicked
        EntradaVIEW entradaview = new EntradaVIEW();
        entradaview.setVisible(true);
    }//GEN-LAST:event_iconeEntradaMouseClicked

    private void labelEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEntradaMouseClicked
        EntradaVIEW entradaview = new EntradaVIEW();
        entradaview.setVisible(true);
    }//GEN-LAST:event_labelEntradaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconeCliente;
    private javax.swing.JLabel iconeEntrada;
    private javax.swing.JLabel iconeFornecedor;
    private javax.swing.JPanel iconeMarca;
    private javax.swing.JLabel iconeProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelEntrada;
    private javax.swing.JLabel labelFornecedor;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JMenuItem mni_Cliente;
    private javax.swing.JMenuItem mni_Entrada;
    private javax.swing.JMenuItem mni_Fornecedor;
    private javax.swing.JMenuItem mni_Produto;
    private javax.swing.JMenu mni_cliente;
    private javax.swing.JMenuItem mni_sair;
    private javax.swing.JMenu mnu_sair;
    // End of variables declaration//GEN-END:variables
}
