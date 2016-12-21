package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.ParametrosCtrl;
import cashFlow.MVC.Controllers.UsuariosCtrl;
import cashFlow.MVC.Models.ConexaoEntityManager;
import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public final class TelaLogin extends javax.swing.JFrame {

    private UsuariosCtrl usuariosCtrl;

    public TelaLogin() throws IOException {
        initComponents();
        setIcon();
        carregaTela();
    }

    public void setIcon() throws IOException {
        Properties parametros = ParametrosCtrl.getInstance();
        String caminho = parametros.getProperty("caminhoLogoLogin");
        File imagem = new File(caminho);
        BufferedImage img = ImageIO.read(imagem);
        labelImagemEmpresa.setSize(img.getHeight(), img.getWidth());
        labelImagemEmpresa.setIcon(new javax.swing.ImageIcon(caminho));
    }

    public void carregaTela() {
        this.usuariosCtrl = new UsuariosCtrl();
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        HashSet backup = (HashSet) conj.clone();
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        botaoOk.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, backup);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public void autenticar() throws IOException {
        try {
            //            try {
//            if (!usuariosCtrl.autenticar(campoUsuario.getText(), campoSenha.getText())) {
//                JOptionPane.showMessageDialog(null, "Login ou senha Inválidos!\n Tente Novamente!!");
//            } else {
            ConexaoEntityManager.getInstance();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            StatusBar statusBar = new StatusBar();
            telaPrincipal.add(statusBar, BorderLayout.SOUTH);
            telaPrincipal.setVisible(true);
            telaPrincipal.setExtendedState(MAXIMIZED_BOTH);
            this.dispose();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoOk = new javax.swing.JButton();
        campoSenha = new javax.swing.JPasswordField();
        labelDesemvolvido = new javax.swing.JLabel();
        labelProprietario = new javax.swing.JLabel();
        labelDireitosReservados = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        labelImagemEmpresa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBounds(new java.awt.Rectangle(0, 0, 600, 600));
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelUsuario.setText("Usuário");

        labelSenha.setText("Senha");

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoOk.setText("OK");
        botaoOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOkActionPerformed(evt);
            }
        });

        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });
        campoSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoSenhaKeyPressed(evt);
            }
        });

        labelDesemvolvido.setText("Desenvolvido Por:");

        labelProprietario.setText("Sandro Machado de Oliveira");

        labelDireitosReservados.setText("Todos Direitos Reservados");

        labelImagemEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImagemEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoSenha)
                    .addComponent(campoUsuario)
                    .addComponent(separador)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoOk, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCancelar))
                            .addComponent(labelDesemvolvido)
                            .addComponent(labelProprietario)
                            .addComponent(labelSenha)
                            .addComponent(labelUsuario)
                            .addComponent(labelDireitosReservados))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelImagemEmpresa)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDesemvolvido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelProprietario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDireitosReservados)
                        .addGap(2, 2, 2)
                        .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoCancelar)
                            .addComponent(botaoOk))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOkActionPerformed
        try {
            autenticar();
        } catch (IOException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoOkActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        System.exit(WIDTH);
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
    }//GEN-LAST:event_campoSenhaActionPerformed
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            TelaLogin.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void campoSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                autenticar();
            } catch (IOException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_campoSenhaKeyPressed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaLogin().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoOk;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JLabel labelDesemvolvido;
    private javax.swing.JLabel labelDireitosReservados;
    private javax.swing.JLabel labelImagemEmpresa;
    private javax.swing.JLabel labelProprietario;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
