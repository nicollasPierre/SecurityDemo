/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.view;

import br.com.furb.dss.controller.UsuarioController;
import br.com.furb.dss.controller.UsuarioDao;
import br.com.furb.dss.model.Roles;
import br.com.furb.dss.model.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author nicol
 */
public class Login extends javax.swing.JFrame {

    UsuarioController usuarioController;

    /**
     * Creates new form Teste
     */
    public Login() {
        initComponents();
        usuarioController = UsuarioController.getInstance();

        Usuario admin = new Usuario("paulo", "123", Roles.usuarioNormal);
        admin.addRole(Roles.moderador);
        admin.addRole(Roles.admin);

        Usuario mod = new Usuario("daniel", "123", Roles.usuarioNormal);
        mod.addRole(Roles.moderador);

        Usuario normal = new Usuario("nicollas", "123", Roles.usuarioNormal);

        usuarioController.addUsuario(admin);
        usuarioController.addUsuario(mod);
        usuarioController.addUsuario(normal);
        UsuarioDao uDao = UsuarioDao.getInstance();
        admin.setId(1);
        //uDao.persist(teste);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonLogin = new javax.swing.JButton();
        jButtonNewUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonAdmin = new javax.swing.JButton();
        jButtonMod = new javax.swing.JButton();
        jButtonNormal = new javax.swing.JButton();
        jTextFieldSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonNewUser.setText("Novo usuario");
        jButtonNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewUserActionPerformed(evt);
            }
        });

        jLabel1.setText("Login:");

        jLabel2.setText("Senha:");

        jButtonAdmin.setText("Admin");
        jButtonAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminActionPerformed(evt);
            }
        });

        jButtonMod.setText("Mod");
        jButtonMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModActionPerformed(evt);
            }
        });

        jButtonNormal.setText("Normal");
        jButtonNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNormalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(jTextFieldSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNewUser)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonNormal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdmin)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogin)
                    .addComponent(jButtonNewUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdmin)
                    .addComponent(jButtonMod)
                    .addComponent(jButtonNormal))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        Usuario usuLogin = usuarioController.login(jTextFieldLogin.getText(), jTextFieldSenha.getText());
        if (usuLogin == null) {
            JOptionPane.showMessageDialog(null, "Dados inválidos.");
        } else {
            usuarioController.setUsuarioLogado(usuLogin);

            String mensagem = "Bem vindo, " + usuarioController.getUsuarioLogado().getLogin();
            mensagem += "\nVocê é: ";

            Roles roleUsu = Roles.usuarioNormal;
            for (Roles role : usuarioController.getUsuarioLogado().getRoles()) {
                if (role == Roles.admin) {
                    roleUsu = role;
                    break;
                }
                if (role == Roles.moderador) {
                    roleUsu = role;
                }
                if (role == Roles.usuarioNormal) {
                    roleUsu = role;
                }
            }

            mensagem += roleUsu.toString();
            //JOptionPane.showMessageDialog(null, mensagem);

            new ListaCompras(usuarioController.getUsuarioLogado(), this).setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewUserActionPerformed
        new CadastrarUsuarios(this).setVisible(true);
    }//GEN-LAST:event_jButtonNewUserActionPerformed

    private void jButtonAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminActionPerformed
        Usuario usuLogin = usuarioController.login("paulo", "123");
        new ListaCompras(usuLogin, this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAdminActionPerformed

    private void jButtonModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModActionPerformed
        Usuario usuLogin = usuarioController.login("daniel", "123");
        new ListaCompras(usuLogin, this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonModActionPerformed

    private void jButtonNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNormalActionPerformed
        Usuario usuLogin = usuarioController.login("nicollas", "123");
        new ListaCompras(usuLogin, this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonNormalActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdmin;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonMod;
    private javax.swing.JButton jButtonNewUser;
    private javax.swing.JButton jButtonNormal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JPasswordField jTextFieldSenha;
    // End of variables declaration//GEN-END:variables
}
