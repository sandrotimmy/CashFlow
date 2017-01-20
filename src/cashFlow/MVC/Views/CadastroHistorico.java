package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.HistoricoPadraoCtrl;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroHistorico extends javax.swing.JDialog implements InterfaceListener {

    private final HistoricoPadraoCtrl historicoPadraoCtrl;
    private final List<HistoricoPadrao> listaHistorico;//recebe os fornecedores para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaHistorico consultaHistorico;//recebe a instancia da tela de consulta
    private HistoricoPadrao historico;

    public CadastroHistorico() {
        initComponents();//inicia componentes da tela
        this.historicoPadraoCtrl = new HistoricoPadraoCtrl();
        this.listaHistorico = historicoPadraoCtrl.getListaHistorico();//carrega a lista de historicos
        carregaPrimeiroHistorico();//carrega primeiros dados e configurações na tela
        setCamposFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        labelHistorico = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        campoHistorico = new javax.swing.JTextField();
        BotaoListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        comboEntradaSaida = new javax.swing.JComboBox();
        botaoBuscar = new javax.swing.JButton();
        labelUtilizador = new javax.swing.JLabel();
        comboUsuarioSistema = new javax.swing.JComboBox();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Históricos");
        setModal(true);

        labelCodigo.setText("Código:");

        labelTipo.setText("Tipo:");

        labelHistorico.setText("Histórico:");

        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCodigo.setEnabled(false);
        campoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodigoActionPerformed(evt);
            }
        });

        campoHistorico.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        BotaoListaPrimeiro.setText("|<");
        BotaoListaPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaPrimeiroActionPerformed(evt);
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

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        botaoCadastrar.setText("Novo");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        comboEntradaSaida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entrada", "Saida" }));

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        labelUtilizador.setText("Utilizador:");

        comboUsuarioSistema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuario", "Sistema" }));

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHistorico)
                            .addComponent(labelTipo))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboEntradaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelUtilizador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboUsuarioSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoHistorico)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 97, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoListaPrimeiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaUltimo)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, botaoBuscar, botaoCadastrar, botaoCancelar, botaoExcluir});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaoListaAnterior, BotaoListaPrimeiro, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoListaAnterior)
                    .addComponent(BotaoListaProximo)
                    .addComponent(BotaoListaUltimo)
                    .addComponent(BotaoListaPrimeiro)
                    .addComponent(labelCodigo)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(comboEntradaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUtilizador)
                    .addComponent(comboUsuarioSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHistorico)
                    .addComponent(campoHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoAtualizar)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private HistoricoPadrao coletaDadosCampos() {
        historico = new HistoricoPadrao(
                Integer.parseInt(this.campoCodigo.getText()),
                this.comboEntradaSaida.getSelectedItem().toString(),
                this.campoHistorico.getText(),
                this.comboUsuarioSistema.getSelectedItem().toString()
        );
        return historico;
    }

    private void carregaPrimeiroHistorico() {//carrega na tela os dados do Fornecedor
        limitaCampos();
        if (!listaHistorico.isEmpty()) {
            exibeDados(listaHistorico.get(listaHistorico.size() - 1));
            campoCodigo.setEnabled(false);
            comboEntradaSaida.setEnabled(false);
            comboUsuarioSistema.setEnabled(false);
            campoHistorico.setEnabled(false);
            consultaHistorico = new ConsultaHistorico();
            consultaHistorico.setListener(this);
            botaoCadastrar.requestFocus();
            posicao = listaHistorico.size() - 1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Historico Cadastrado, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);
        }
    }

    private void setCamposFocus() {
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoAtualizar.setFocusTraversalKeysEnabled(false);
    }

    private void limitaCampos() {//limita os campos de texto
        campoHistorico.setDocument(new DocumentoLimitado(300));
    }

    @Override
    public void mensagemCodHistoricoBusca(String idbusca) {//recebe o codigo da busca por listener e busca na lista
        int id = Integer.parseInt(idbusca);
        HistoricoPadrao historicoBusca = new HistoricoPadrao();
        for (int i = 0; i < listaHistorico.size(); i++) {
            if (listaHistorico.get(i).getCod() == id) {
                historicoBusca = listaHistorico.get(i);
                break;
            }
        }
        exibeDados(historicoBusca);
        posicao = listaHistorico.indexOf(historicoBusca);
    }

    private void exibeDados(HistoricoPadrao historico) {
        campoCodigo.setText(Integer.toString(historico.getCod()));
        comboEntradaSaida.setSelectedItem(historico.getTipo());
        comboUsuarioSistema.setSelectedItem(historico.getUtilizador());
        campoHistorico.setText(historico.getNomeHistorico());
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoHistorico.setEnabled(botoes);
        comboEntradaSaida.setEnabled(botoes);
        BotaoListaPrimeiro.setEnabled(botoes);
        BotaoListaAnterior.setEnabled(botoes);
        BotaoListaProximo.setEnabled(botoes);
        BotaoListaUltimo.setEnabled(botoes);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoHistorico.setText("");
                comboEntradaSaida.setEnabled(campos);
                campoHistorico.setEnabled(campos);
                comboUsuarioSistema.setEnabled(campos);
                comboUsuarioSistema.setSelectedIndex(0);
            } else {
                comboUsuarioSistema.setEnabled(campos);
            }
            BotaoListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
            botaoAtualizar.setEnabled(botoes);
        } else if (tipo == 2) {
            campoHistorico.setEnabled(campos);
            comboEntradaSaida.setEnabled(campos);
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
        }
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoCodigo.setText(Integer.toString(historicoPadraoCtrl.getProximoCodHistorico()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            botaoCadastrar.setText("Gravar");
            campoHistorico.requestFocus();
        } else {
            historico = coletaDadosCampos();
            historicoPadraoCtrl.cadastrarHistorico(historico);//persiste no banco
            listaHistorico.add(historico);//insere na lista
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            posicao = listaHistorico.size() - 1;//atualiza a posição
            botaoCadastrar.setText("Novo");
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroHistorico.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void BotaoListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaPrimeiroActionPerformed
        exibeDados(listaHistorico.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaoListaPrimeiroActionPerformed
    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaHistorico.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed
    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaHistorico.size() - 1) {
            exibeDados(listaHistorico.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed
    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaHistorico.get(listaHistorico.size() - 1));
        posicao = listaHistorico.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed
    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            habilitaDesabilitaCampos(2, false, true, false);
            botaoAtualizar.setText("Gravar");
        } else {
            historico = coletaDadosCampos();
            listaHistorico.remove(posicao);//remove da lista
            listaHistorico.add(posicao, historico);//adiciona atualizado na mesma posição
            historicoPadraoCtrl.atualizaHistorico(historico);//persiste no banco
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void campoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodigoActionPerformed
    }//GEN-LAST:event_campoCodigoActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaHistorico.setVisible(true);
            consultaHistorico.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Historico?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (!listaHistorico.get(posicao).getUtilizador().equalsIgnoreCase("Sistema")) {
                if (historicoPadraoCtrl.removeHistorico(Integer.parseInt(campoCodigo.getText()))) {
                    listaHistorico.remove(posicao);
                    posicao--;
                    if (listaHistorico.isEmpty()) {
                        botaoCadastrarActionPerformed(evt);
                    } else {
                        exibeDados(listaHistorico.get(posicao));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Exclusão Não permitida\nHistorico pertence as Configurações do Sistema!");
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new CadastroHistorico().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoListaAnterior;
    private javax.swing.JButton BotaoListaPrimeiro;
    private javax.swing.JButton BotaoListaProximo;
    private javax.swing.JButton BotaoListaUltimo;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoHistorico;
    private javax.swing.JComboBox comboEntradaSaida;
    private javax.swing.JComboBox comboUsuarioSistema;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelUtilizador;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodFornecedorCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoReajuste(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compraCancelar(Compras compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
