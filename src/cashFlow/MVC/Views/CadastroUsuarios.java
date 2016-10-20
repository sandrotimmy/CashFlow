
package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.UsuariosDAO;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.Usuarios;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class CadastroUsuarios extends javax.swing.JDialog {

    private final List<Usuarios> listaUsuarios;
    private final UsuariosDAO persist;
    private int posicao;//controla o caminhamento do cadastro

    public CadastroUsuarios() throws SQLException {
        initComponents();
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoCadastrar.requestFocus();
        setModal(true);
        this.persist = new UsuariosDAO();
        this.listaUsuarios = persist.getListaUsuarios();
        posicao = listaUsuarios.size() - 1;
        limitaCampos();
        carregaPrimeiroUsuario();
    }

    public void limitaCampos() {
        campoNomeUsuario.setDocument(new DocumentoLimitado(100));
        campoNickUsuario.setDocument(new DocumentoLimitado(10));
        campoSenha.setDocument(new DocumentoLimitado(8));
    }

    public void carregaPrimeiroUsuario() {
        if (!listaUsuarios.isEmpty()) {
            exibeDados(listaUsuarios.get(posicao));
        } else {
            campoCodigo.setText("");
            campoNomeUsuario.setText("");
            campoNickUsuario.setText("");
            campoSenha.setText("");
        }
        campoCodigo.setEnabled(false);
        campoNomeUsuario.setEnabled(false);
        campoNickUsuario.setEnabled(false);
        campoSenha.setEnabled(false);
    }
  
    public void exibeDados(Usuarios usuario) {
        campoCodigo.setText(Integer.toString(usuario.getIdUsuario()));
        campoNomeUsuario.setText(usuario.getNomeUsuario());
        campoNickUsuario.setText(usuario.getNickUsuario());
        campoSenha.setText(usuario.getSenhaUsuario());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        labelNomeUsuario = new javax.swing.JLabel();
        campoNomeUsuario = new javax.swing.JTextField();
        labelUserName = new javax.swing.JLabel();
        campoNickUsuario = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        botaoCadastrar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JToggleButton();
        botaoCancelar = new javax.swing.JToggleButton();
        BotaListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        labelMaximoCaracteres = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Usuários");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelCodigo.setText("Codigo:");

        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNomeUsuario.setText("Nome do Usuário:");

        campoNomeUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelUserName.setText("Username:");

        campoNickUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelSenha.setText("Senha:");

        campoSenha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });

        botaoCadastrar.setText("Incluir");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        BotaListaPrimeiro.setText("|<");
        BotaListaPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaListaPrimeiroActionPerformed(evt);
            }
        });

        BotaoListaAnterior.setText("<");
        BotaoListaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaAnteriorActionPerformed(evt);
            }
        });

        BotaoListaProximo.setText(">");
        BotaoListaProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaProximoActionPerformed(evt);
            }
        });

        BotaoListaUltimo.setText(">|");
        BotaoListaUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaUltimoActionPerformed(evt);
            }
        });

        labelMaximoCaracteres.setText("Máximo 8 Caracteres");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campoNomeUsuario)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaListaPrimeiro))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaUltimo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeUsuario)
                            .addComponent(labelUserName)
                            .addComponent(campoNickUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSenha)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelMaximoCaracteres)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, botaoCadastrar, botaoCancelar, botaoExcluir});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaListaPrimeiro, BotaoListaAnterior, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCodigo)
                        .addComponent(BotaListaPrimeiro))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotaoListaAnterior)
                        .addComponent(BotaoListaProximo)
                        .addComponent(BotaoListaUltimo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNickUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaximoCaracteres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoAtualizar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaListaPrimeiroActionPerformed
        exibeDados(listaUsuarios.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaListaPrimeiroActionPerformed

    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed

        if (posicao > 0) {
            exibeDados(listaUsuarios.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed

    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed

        if (posicao < listaUsuarios.size() - 1) {
            exibeDados(listaUsuarios.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed

    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed

        exibeDados(listaUsuarios.get(listaUsuarios.size() - 1));
        posicao = listaUsuarios.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed

    }//GEN-LAST:event_campoSenhaActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroUsuarios.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoNomeUsuario.setText("");
            campoNickUsuario.setText("");
            campoSenha.setText("");
            campoNomeUsuario.setEnabled(true);
            campoNickUsuario.setEnabled(true);
            campoSenha.setEnabled(true);
            campoCodigo.setText(Integer.toString(persist.getProximoCodUsuario()));
            campoNomeUsuario.requestFocus();
            botaoCadastrar.setText("Gravar");
        } else {
            Usuarios usuario = new Usuarios(
                    Integer.parseInt(this.campoCodigo.getText()),
                    this.campoNomeUsuario.getText(),
                    this.campoNickUsuario.getText(),
                    this.campoSenha.getText());
            listaUsuarios.add(usuario);
            persist.insereUsuario(usuario);
            botaoCadastrar.setText("Incluir");
            campoCodigo.setEnabled(false);
            campoNomeUsuario.setEnabled(false);
            campoNickUsuario.setEnabled(false);
            campoSenha.setEnabled(false);
            posicao = listaUsuarios.size() - 1;
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Usuário?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (posicao == 0) {
                JOptionPane.showMessageDialog(null, "Não é possivel Excluir a Conta do Administrador!");
            } else if (posicao == listaUsuarios.size() - 1) {
                persist.removeUsuario(Integer.parseInt(campoCodigo.getText()));
                listaUsuarios.remove(posicao);
                posicao--;
                exibeDados(listaUsuarios.get(posicao));
            } else {
                persist.removeUsuario(Integer.parseInt(campoCodigo.getText()));
                listaUsuarios.remove(posicao);
                exibeDados(listaUsuarios.get(posicao));
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            campoNomeUsuario.setEnabled(true);
            campoNickUsuario.setEnabled(true);
            campoSenha.setEnabled(true);
            botaoCadastrar.setEnabled(false);
            botaoAtualizar.setText("Gravar");
        } else {

            Usuarios usuario = new Usuarios(
                    Integer.parseInt(this.campoCodigo.getText()),
                    this.campoNomeUsuario.getText(),
                    this.campoNickUsuario.getText(),
                    this.campoSenha.getText());

            listaUsuarios.remove(posicao);//remove da lista
            listaUsuarios.add(posicao, usuario);//adiciona na mesma posição
            persist.atualizaUsuario(usuario);//persiste no banco

            campoNomeUsuario.setEnabled(false);
            campoNickUsuario.setEnabled(false);
            campoSenha.setEnabled(false);
            botaoAtualizar.setText("Atualizar");
            botaoCadastrar.setEnabled(true);
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CadastroUsuarios.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroUsuarios().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaListaPrimeiro;
    private javax.swing.JButton BotaoListaAnterior;
    private javax.swing.JButton BotaoListaProximo;
    private javax.swing.JButton BotaoListaUltimo;
    private javax.swing.JToggleButton botaoAtualizar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JToggleButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoNickUsuario;
    private javax.swing.JTextField campoNomeUsuario;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelMaximoCaracteres;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUserName;
    // End of variables declaration//GEN-END:variables
}
