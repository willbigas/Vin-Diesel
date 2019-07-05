/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.view;

import br.com.vindiesel.control.TelaEntregaControl;
import br.com.vindiesel.control.TelaPrincipalControl;
import br.com.vindiesel.uteis.InterfaceJanela;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class TelaEncomendaPesquisaAvancada extends javax.swing.JDialog {

    TelaEntregaControl telaEntregaControl;
    TelaPrincipalControl telaPrincipalControl;

    /**
     * Creates new form ProdutoDialogPesquisar
     */
    public TelaEncomendaPesquisaAvancada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public TelaEncomendaPesquisaAvancada(JInternalFrame parent, boolean modal, TelaEntregaControl control) {
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setModal(modal);
        telaEntregaControl = control;
        telaPrincipalControl = new TelaPrincipalControl();
        InterfaceJanela.alteraIconePrincipalDoFrame(this, "/br/com/vindiesel/img/delivery_truck_16x16.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfCampoPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEncomenda = new javax.swing.JTable();
        btAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa avancada de Encomendas");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR POR ENCOMENDAS:");

        tfCampoPesquisa.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfCampoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCampoPesquisaKeyReleased(evt);
            }
        });

        tblEncomenda.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEncomenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEncomendaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblEncomenda);

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/add_32x32.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar nova encomenda");
        btAdicionar.setBorder(null);
        btAdicionar.setBorderPainted(false);
        btAdicionar.setContentAreaFilled(false);
        btAdicionar.setFocusPainted(false);
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCampoPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAdicionar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCampoPesquisa)
                    .addComponent(jLabel1)
                    .addComponent(btAdicionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEncomendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEncomendaMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            telaEntregaControl.carregaDadosEncomendaDoDialogPesquisaAvancadaAction();
            this.dispose();
        }
    }//GEN-LAST:event_tblEncomendaMouseReleased

    private void tfCampoPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCampoPesquisaKeyReleased
        // TODO add your handling code here:
        telaEntregaControl.pesquisarEncomendasNoDialogPesquisaAvancadaAction();
    }//GEN-LAST:event_tfCampoPesquisaKeyReleased

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        telaPrincipalControl.chamarTelaEncomendaAction();
        this.dispose();
    }//GEN-LAST:event_btAdicionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEncomendaPesquisaAvancada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEncomendaPesquisaAvancada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEncomendaPesquisaAvancada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEncomendaPesquisaAvancada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaEncomendaPesquisaAvancada dialog = new TelaEncomendaPesquisaAvancada(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEncomenda;
    private javax.swing.JTextField tfCampoPesquisa;
    // End of variables declaration//GEN-END:variables

    public JTable getTblEncomenda() {
        return tblEncomenda;
    }

    public void setTblEncomenda(JTable tblEncomenda) {
        this.tblEncomenda = tblEncomenda;
    }

    public JTextField getTfCampoPesquisa() {
        return tfCampoPesquisa;
    }

    public void setTfCampoPesquisa(JTextField tfCampoPesquisa) {
        this.tfCampoPesquisa = tfCampoPesquisa;
    }

}
