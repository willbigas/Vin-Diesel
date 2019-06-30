/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.view;

import br.com.vindiesel.control.TelaReceitaGerenciarControl;
import br.com.vindiesel.model.FormaPagamento;
import br.com.vindiesel.uteis.InterfaceJanela;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class TelaReceitaGerenciar extends javax.swing.JInternalFrame {

    TelaReceitaGerenciarControl receitaGerenciarControl;

    /**
     * Creates new form TelaReceitaGerenciar
     */
    public TelaReceitaGerenciar() {
        initComponents();
    }

    public TelaReceitaGerenciar(TelaReceitaGerenciarControl control) {
        initComponents();
        receitaGerenciarControl = control;
        InterfaceJanela.centralizarInternalFrame(this);
        InterfaceJanela.alteraIconePrincipaldoJInternalFrame(this, "br/com/vindiesel/img/delivery_truck_16x16.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpReceita = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tfPesquisarReceita = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReceita = new javax.swing.JTable();
        brEditarReceita = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblValorTotalFiltrado = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbFormaPagamento = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btGravarReceita = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfValorRecebido = new javax.swing.JTextField();
        checkFinalizarReceita = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        lblValorTotalReceita = new javax.swing.JLabel();
        lblValorReceitaRestante = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfDataVencimento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciamento de receitas");
        setToolTipText("");

        tpReceita.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tpReceita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tpReceitaMouseReleased(evt);
            }
        });

        tfPesquisarReceita.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfPesquisarReceita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisarReceitaKeyReleased(evt);
            }
        });

        tblReceita.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblReceita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblReceita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReceitaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblReceita);

        brEditarReceita.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        brEditarReceita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/visualizar_32x32.png"))); // NOI18N
        brEditarReceita.setText("Editar");
        brEditarReceita.setBorder(null);
        brEditarReceita.setBorderPainted(false);
        brEditarReceita.setContentAreaFilled(false);
        brEditarReceita.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        brEditarReceita.setFocusPainted(false);
        brEditarReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brEditarReceitaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/lupa_32x32.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Listagem de Receitas");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Valor Total:");

        lblValorTotalFiltrado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblValorTotalFiltrado.setForeground(new java.awt.Color(0, 0, 0));
        lblValorTotalFiltrado.setText("[ValorTotalFiltrado]");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("de");

        lblValorTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblValorTotal.setText("[ValorTotal]");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("PESQUISAR:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotalFiltrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotal)
                        .addGap(0, 438, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesquisarReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(brEditarReceita)
                .addGap(22, 22, 22))
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisarReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(brEditarReceita)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblValorTotalFiltrado)
                    .addComponent(jLabel13)
                    .addComponent(lblValorTotal))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tpReceita.addTab("RECEITAS", jPanel1);

        cbFormaPagamento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Forma de Pagamento:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Recebimento de Pagamento");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Valor Recebido:");

        btGravarReceita.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btGravarReceita.setText("Gravar");
        btGravarReceita.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btGravarReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarReceitaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Valor Restante :");

        tfValorRecebido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        checkFinalizarReceita.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        checkFinalizarReceita.setText("Finalizar Receita");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Valor do Contrato :");

        lblValorTotalReceita.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblValorTotalReceita.setText("[ValorTotal]");

        lblValorReceitaRestante.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblValorReceitaRestante.setText("[ValorRestante]");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Data Vencimento:");

        tfDataVencimento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkFinalizarReceita)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblValorTotalReceita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValorReceitaRestante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(658, Short.MAX_VALUE)
                .addComponent(btGravarReceita)
                .addGap(22, 22, 22))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblValorTotalReceita)
                    .addComponent(jLabel7)
                    .addComponent(tfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblValorReceitaRestante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkFinalizarReceita)
                .addGap(48, 48, 48)
                .addComponent(btGravarReceita)
                .addGap(18, 18, 18))
        );

        tpReceita.addTab("RECEBER PAGAMENTO", jPanel2);

        jPanel3.setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gerenciar Receitas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpReceita, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tpReceita))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brEditarReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brEditarReceitaActionPerformed
        // TODO add your handling code here:
        receitaGerenciarControl.carregarCamposReceitaAction();

    }//GEN-LAST:event_brEditarReceitaActionPerformed

    private void tpReceitaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpReceitaMouseReleased
        // TODO add your handling code here:
        if (tpReceita.getSelectedIndex() == 0) {
            receitaGerenciarControl.resetarReceitaAction();
        }
    }//GEN-LAST:event_tpReceitaMouseReleased

    private void btGravarReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarReceitaActionPerformed
        // TODO add your handling code here:
        receitaGerenciarControl.editarReceitaAction();
    }//GEN-LAST:event_btGravarReceitaActionPerformed

    private void tfPesquisarReceitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarReceitaKeyReleased
        receitaGerenciarControl.pesquisarReceitaAction();
    }//GEN-LAST:event_tfPesquisarReceitaKeyReleased

    private void tblReceitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReceitaMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblReceitaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brEditarReceita;
    private javax.swing.JButton btGravarReceita;
    private javax.swing.JComboBox<FormaPagamento> cbFormaPagamento;
    private javax.swing.JCheckBox checkFinalizarReceita;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lblValorReceitaRestante;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblValorTotalFiltrado;
    private javax.swing.JLabel lblValorTotalReceita;
    private javax.swing.JTable tblReceita;
    private javax.swing.JTextField tfDataVencimento;
    private javax.swing.JTextField tfPesquisarReceita;
    private javax.swing.JTextField tfValorRecebido;
    private javax.swing.JTabbedPane tpReceita;
    // End of variables declaration//GEN-END:variables

    public JComboBox<FormaPagamento> getCbFormaPagamento() {
        return cbFormaPagamento;
    }

    public void setCbFormaPagamento(JComboBox<FormaPagamento> cbFormaPagamento) {
        this.cbFormaPagamento = cbFormaPagamento;
    }

    public JCheckBox getCheckFinalizarReceita() {
        return checkFinalizarReceita;
    }

    public void setCheckFinalizarReceita(JCheckBox checkFinalizarReceita) {
        this.checkFinalizarReceita = checkFinalizarReceita;
    }

    public JLabel getLblValorReceitaRestante() {
        return lblValorReceitaRestante;
    }

    public void setLblValorReceitaRestante(JLabel lblValorReceitaRestante) {
        this.lblValorReceitaRestante = lblValorReceitaRestante;
    }

    public JLabel getLblValorTotalReceita() {
        return lblValorTotalReceita;
    }

    public void setLblValorTotalReceita(JLabel lblValorTotalReceita) {
        this.lblValorTotalReceita = lblValorTotalReceita;
    }

    public JTable getTblReceita() {
        return tblReceita;
    }

    public void setTblReceita(JTable tblReceita) {
        this.tblReceita = tblReceita;
    }

    public JTextField getTfPesquisarReceita() {
        return tfPesquisarReceita;
    }

    public void setTfPesquisarReceita(JTextField tfPesquisarReceita) {
        this.tfPesquisarReceita = tfPesquisarReceita;
    }

    public JTextField getTfValorRecebido() {
        return tfValorRecebido;
    }

    public void setTfValorRecebido(JTextField tfValorRecebido) {
        this.tfValorRecebido = tfValorRecebido;
    }

    public JTabbedPane getTpReceita() {
        return tpReceita;
    }

    public void setTpReceita(JTabbedPane tpReceita) {
        this.tpReceita = tpReceita;
    }

    public JTextField getTfDataVencimento() {
        return tfDataVencimento;
    }

    public void setTfDataVencimento(JTextField tfDataVencimento) {
        this.tfDataVencimento = tfDataVencimento;
    }

    
    public JLabel getLblValorTotal() {
        return lblValorTotal;
    }

    public void setLblValorTotal(JLabel lblValorTotal) {
        this.lblValorTotal = lblValorTotal;
    }

    public JLabel getLblValorTotalFiltrado() {
        return lblValorTotalFiltrado;
    }

    public void setLblValorTotalFiltrado(JLabel lblValorTotalFiltrado) {
        this.lblValorTotalFiltrado = lblValorTotalFiltrado;
    }

}
