package br.com.vindiesel.view;

import br.com.vindiesel.control.TelaPrincipalControl;
import br.com.vindiesel.uteis.InterfaceJanela;
import br.com.vindiesel.uteis.Relatorio;
import java.io.InputStream;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author William
 */
public class TelaPrincipal extends javax.swing.JFrame {

    TelaPrincipalControl telaPrincipalControl;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    public TelaPrincipal(TelaPrincipalControl control) {
        initComponents();
        telaPrincipalControl = control;
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
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPessoa = new javax.swing.JMenu();
        menuItemGerenciarRemetente = new javax.swing.JMenuItem();
        menuItemGerenciarDestinatario = new javax.swing.JMenuItem();
        menuEntrega = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenu();
        menuItemGerenciarUsuario = new javax.swing.JMenuItem();
        menuItemGerenciarTipoUsuario = new javax.swing.JMenuItem();
        menuFinanceiro = new javax.swing.JMenu();
        menuItemReceita = new javax.swing.JMenuItem();
        menuRelatorio = new javax.swing.JMenu();
        menuItemRelatorioEntrega = new javax.swing.JMenuItem();
        menuItemRelatorioRementente = new javax.swing.JMenuItem();
        menuItemRelatorioDestinatario = new javax.swing.JMenuItem();
        menuItemRelatorioEncomenda = new javax.swing.JMenuItem();
        menuItemRelatorioReceitas = new javax.swing.JMenuItem();
        menuItemRelatorioUsuario = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();
        menuItemAjuda = new javax.swing.JMenuItem();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");

        desktopPane.setBackground(new java.awt.Color(207, 207, 207));
        desktopPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        desktopPane.setForeground(new java.awt.Color(207, 207, 207));

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VIN DIESEL");

        jMenuBar1.setBorder(null);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(767, 45));

        menuPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/people_32x32.png"))); // NOI18N
        menuPessoa.setText("Pessoas");
        menuPessoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        menuItemGerenciarRemetente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemGerenciarRemetente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/remetente_32x32.png"))); // NOI18N
        menuItemGerenciarRemetente.setText("Gerenciar Remetentes");
        menuItemGerenciarRemetente.setBorder(null);
        menuItemGerenciarRemetente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGerenciarRemetenteActionPerformed(evt);
            }
        });
        menuPessoa.add(menuItemGerenciarRemetente);

        menuItemGerenciarDestinatario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemGerenciarDestinatario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/destinatario_32x32.png"))); // NOI18N
        menuItemGerenciarDestinatario.setText("Gerenciar Destinatários");
        menuItemGerenciarDestinatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGerenciarDestinatarioActionPerformed(evt);
            }
        });
        menuPessoa.add(menuItemGerenciarDestinatario);

        jMenuBar1.add(menuPessoa);

        menuEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/entrega_32x32.png"))); // NOI18N
        menuEntrega.setText("Entrega");
        menuEntrega.setContentAreaFilled(false);
        menuEntrega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/gerenciar_32x32.png"))); // NOI18N
        jMenuItem4.setText("Gerenciar Entregas");
        jMenuItem4.setBorder(null);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuEntrega.add(jMenuItem4);

        jMenuBar1.add(menuEntrega);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/encomenda_32x32.png"))); // NOI18N
        jMenu1.setText("Encomenda");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/gerenciar_32x32.png"))); // NOI18N
        jMenuItem1.setText("Gerenciar Encomendas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        menuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/user_32x32.png"))); // NOI18N
        menuUsuario.setText("Usuários");
        menuUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        menuItemGerenciarUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menuItemGerenciarUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemGerenciarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/gerenciar_32x32.png"))); // NOI18N
        menuItemGerenciarUsuario.setText("Gerenciar Usuários");
        menuItemGerenciarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGerenciarUsuarioActionPerformed(evt);
            }
        });
        menuUsuario.add(menuItemGerenciarUsuario);

        menuItemGerenciarTipoUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menuItemGerenciarTipoUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemGerenciarTipoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/cardUser_32x32.png"))); // NOI18N
        menuItemGerenciarTipoUsuario.setText("Tipo de Usuário");
        menuItemGerenciarTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGerenciarTipoUsuarioActionPerformed(evt);
            }
        });
        menuUsuario.add(menuItemGerenciarTipoUsuario);

        jMenuBar1.add(menuUsuario);

        menuFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/money_32x32.png"))); // NOI18N
        menuFinanceiro.setText("Financeiro");
        menuFinanceiro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        menuItemReceita.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuItemReceita.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemReceita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/receita_32x32.png"))); // NOI18N
        menuItemReceita.setText("Receitas");
        menuItemReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemReceitaActionPerformed(evt);
            }
        });
        menuFinanceiro.add(menuItemReceita);

        jMenuBar1.add(menuFinanceiro);

        menuRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/relatorio_32x32.png"))); // NOI18N
        menuRelatorio.setText("Relatórios");
        menuRelatorio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        menuItemRelatorioEntrega.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/entrega_32x32.png"))); // NOI18N
        menuItemRelatorioEntrega.setText("Entregas");
        menuItemRelatorioEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioEntregaActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioEntrega);

        menuItemRelatorioRementente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioRementente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/remetente_32x32.png"))); // NOI18N
        menuItemRelatorioRementente.setText("Remetentes");
        menuItemRelatorioRementente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioRemententeActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioRementente);

        menuItemRelatorioDestinatario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioDestinatario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/destinatario_32x32.png"))); // NOI18N
        menuItemRelatorioDestinatario.setText("Destinatarios");
        menuItemRelatorioDestinatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioDestinatarioActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioDestinatario);

        menuItemRelatorioEncomenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioEncomenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/encomenda_32x32.png"))); // NOI18N
        menuItemRelatorioEncomenda.setText("Encomendas");
        menuItemRelatorioEncomenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioEncomendaActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioEncomenda);

        menuItemRelatorioReceitas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioReceitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/receita_32x32.png"))); // NOI18N
        menuItemRelatorioReceitas.setText("Receitas");
        menuItemRelatorioReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioReceitasActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioReceitas);

        menuItemRelatorioUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemRelatorioUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/user_32x32.png"))); // NOI18N
        menuItemRelatorioUsuario.setText("Usuarios");
        menuItemRelatorioUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioUsuarioActionPerformed(evt);
            }
        });
        menuRelatorio.add(menuItemRelatorioUsuario);

        jMenuBar1.add(menuRelatorio);

        menuSobre.setBorder(null);
        menuSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/info_32x32.png"))); // NOI18N
        menuSobre.setText("Sobre");
        menuSobre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        menuItemAjuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAjuda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/ajuda_32x32.png"))); // NOI18N
        menuItemAjuda.setText("Ajuda");
        menuSobre.add(menuItemAjuda);

        menuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSobre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vindiesel/img/developer_32x32.png"))); // NOI18N
        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuSobre.add(menuItemSobre);

        jMenuBar1.add(menuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemGerenciarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGerenciarUsuarioActionPerformed
        telaPrincipalControl.chamarTelaGerenciarFuncionarioAction();
    }//GEN-LAST:event_menuItemGerenciarUsuarioActionPerformed

    private void menuItemGerenciarTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGerenciarTipoUsuarioActionPerformed
        telaPrincipalControl.chamarTelaTipoUsuarioGerenciarAction();
    }//GEN-LAST:event_menuItemGerenciarTipoUsuarioActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaSobreAction();
    }//GEN-LAST:event_menuItemSobreActionPerformed

    private void menuItemReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReceitaActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaReceitaGerenciarAction();

    }//GEN-LAST:event_menuItemReceitaActionPerformed

    private void menuItemRelatorioReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioReceitasActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/receitas.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioReceitasActionPerformed

    private void menuItemRelatorioEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioEntregaActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/entregas.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioEntregaActionPerformed

    private void menuItemRelatorioUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioUsuarioActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/usuarios.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioUsuarioActionPerformed

    private void menuItemGerenciarRemetenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGerenciarRemetenteActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaRemetenteGerenciarAction();
    }//GEN-LAST:event_menuItemGerenciarRemetenteActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaEntregaAction();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuItemGerenciarDestinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGerenciarDestinatarioActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaDestinatarioGerenciarAction();
    }//GEN-LAST:event_menuItemGerenciarDestinatarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        telaPrincipalControl.chamarTelaEncomendaAction();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemRelatorioRemententeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioRemententeActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/remetentes.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioRemententeActionPerformed

    private void menuItemRelatorioDestinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioDestinatarioActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/destinatarios.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioDestinatarioActionPerformed

    private void menuItemRelatorioEncomendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioEncomendaActionPerformed
        // TODO add your handling code here:
        InputStream jasperFile = getClass().getResourceAsStream("/reports/encomendas.jasper");
        Relatorio.chamarRelatorio(jasperFile, null);
    }//GEN-LAST:event_menuItemRelatorioEncomendaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JDesktopPane desktopPane = new javax.swing.JDesktopPane();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu menuEntrega;
    private javax.swing.JMenu menuFinanceiro;
    private javax.swing.JMenuItem menuItemAjuda;
    private javax.swing.JMenuItem menuItemGerenciarDestinatario;
    private javax.swing.JMenuItem menuItemGerenciarRemetente;
    private javax.swing.JMenuItem menuItemGerenciarTipoUsuario;
    private javax.swing.JMenuItem menuItemGerenciarUsuario;
    private javax.swing.JMenuItem menuItemReceita;
    private javax.swing.JMenuItem menuItemRelatorioDestinatario;
    private javax.swing.JMenuItem menuItemRelatorioEncomenda;
    private javax.swing.JMenuItem menuItemRelatorioEntrega;
    private javax.swing.JMenuItem menuItemRelatorioReceitas;
    private javax.swing.JMenuItem menuItemRelatorioRementente;
    private javax.swing.JMenuItem menuItemRelatorioUsuario;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenu menuPessoa;
    private javax.swing.JMenu menuRelatorio;
    private javax.swing.JMenu menuSobre;
    private javax.swing.JMenu menuUsuario;
    // End of variables declaration//GEN-END:variables

    public JMenu getMenuEntrega() {
        return menuEntrega;
    }

    public void setMenuEntrega(JMenu menuEntrega) {
        this.menuEntrega = menuEntrega;
    }

    public JMenu getMenuFinanceiro() {
        return menuFinanceiro;
    }

    public void setMenuFinanceiro(JMenu menuFinanceiro) {
        this.menuFinanceiro = menuFinanceiro;
    }

    public JMenuItem getMenuItemAjuda() {
        return menuItemAjuda;
    }

    public void setMenuItemAjuda(JMenuItem menuItemAjuda) {
        this.menuItemAjuda = menuItemAjuda;
    }

    public JMenuItem getMenuItemGerenciarDestinatario() {
        return menuItemGerenciarDestinatario;
    }

    public void setMenuItemGerenciarDestinatario(JMenuItem menuItemGerenciarDestinatario) {
        this.menuItemGerenciarDestinatario = menuItemGerenciarDestinatario;
    }

    public JMenuItem getMenuItemGerenciarRemetente() {
        return menuItemGerenciarRemetente;
    }

    public void setMenuItemGerenciarRemetente(JMenuItem menuItemGerenciarRemetente) {
        this.menuItemGerenciarRemetente = menuItemGerenciarRemetente;
    }

    public JMenuItem getMenuItemGerenciarTipoUsuario() {
        return menuItemGerenciarTipoUsuario;
    }

    public void setMenuItemGerenciarTipoUsuario(JMenuItem menuItemGerenciarTipoUsuario) {
        this.menuItemGerenciarTipoUsuario = menuItemGerenciarTipoUsuario;
    }

    public JMenuItem getMenuItemGerenciarUsuario() {
        return menuItemGerenciarUsuario;
    }

    public void setMenuItemGerenciarUsuario(JMenuItem menuItemGerenciarUsuario) {
        this.menuItemGerenciarUsuario = menuItemGerenciarUsuario;
    }

    public JMenuItem getMenuItemReceita() {
        return menuItemReceita;
    }

    public void setMenuItemReceita(JMenuItem menuItemReceita) {
        this.menuItemReceita = menuItemReceita;
    }

    public JMenuItem getMenuItemRelatorioEntrega() {
        return menuItemRelatorioEntrega;
    }

    public void setMenuItemRelatorioEntrega(JMenuItem menuItemRelatorioEntrega) {
        this.menuItemRelatorioEntrega = menuItemRelatorioEntrega;
    }

    public JMenuItem getMenuItemRelatorioReceitas() {
        return menuItemRelatorioReceitas;
    }

    public void setMenuItemRelatorioReceitas(JMenuItem menuItemRelatorioReceitas) {
        this.menuItemRelatorioReceitas = menuItemRelatorioReceitas;
    }

    public JMenuItem getMenuItemRelatorioUsuario() {
        return menuItemRelatorioUsuario;
    }

    public void setMenuItemRelatorioUsuario(JMenuItem menuItemRelatorioUsuario) {
        this.menuItemRelatorioUsuario = menuItemRelatorioUsuario;
    }

    public JMenuItem getMenuItemSobre() {
        return menuItemSobre;
    }

    public void setMenuItemSobre(JMenuItem menuItemSobre) {
        this.menuItemSobre = menuItemSobre;
    }

    public JMenu getMenuPessoa() {
        return menuPessoa;
    }

    public void setMenuPessoa(JMenu menuPessoa) {
        this.menuPessoa = menuPessoa;
    }

    public JMenu getMenuRelatorio() {
        return menuRelatorio;
    }

    public void setMenuRelatorio(JMenu menuRelatorio) {
        this.menuRelatorio = menuRelatorio;
    }

    public JMenu getMenuSobre() {
        return menuSobre;
    }

    public void setMenuSobre(JMenu menuSobre) {
        this.menuSobre = menuSobre;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

}
