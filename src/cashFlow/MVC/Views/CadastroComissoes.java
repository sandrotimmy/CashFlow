package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.FolhaDePagamentoCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Models.Comissoes;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroComissoes extends javax.swing.JDialog implements InterfaceListener {

    private final FolhaDePagamentoCtrl folhaDePagamentoCtrl;
    private final List<Comissoes> listaComissoes;//recebe as comissões para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaComissoes consultaComissoes;//recebe a instancia da tela de consulta
    private Comissoes comissoes;
    private MetodosGerais mg;

    public CadastroComissoes() {
        initComponents();//inicia componentes da tela
        this.folhaDePagamentoCtrl = new FolhaDePagamentoCtrl();
        this.listaComissoes = folhaDePagamentoCtrl.getListaComissoes();//carrega a lista de comissoes
        carregaPrimeiraComissao();//carrega primeiros dados e configurações na tela
        mg = new MetodosGerais();
        setCamposFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        campoNome = new javax.swing.JTextField();
        BotaoListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        comboTipoComissao = new javax.swing.JComboBox();
        botaoBuscar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        labelValor = new javax.swing.JLabel();
        campoValor = new JNumberFormatField();
        labelPorcentagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Comissões");
        setModal(true);

        labelCodigo.setText("Código:");

        labelTipo.setText("Tipo:");

        labelNome.setText("Nome:");

        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCodigo.setEnabled(false);
        campoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodigoActionPerformed(evt);
            }
        });

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));

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

        comboTipoComissao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Porcentagem", "Valor" }));
        comboTipoComissao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoComissaoItemStateChanged(evt);
            }
        });
        comboTipoComissao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboTipoComissaoMouseClicked(evt);
            }
        });

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        labelValor.setText("Valor:");

        campoValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoValorActionPerformed(evt);
            }
        });

        labelPorcentagem.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelValor)
                                .addComponent(labelTipo)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboTipoComissao, 0, 98, Short.MAX_VALUE)
                                    .addComponent(campoValor))
                                .addGap(2, 2, 2)
                                .addComponent(labelPorcentagem)
                                .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(labelNome)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoComissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValor)
                    .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPorcentagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoAtualizar)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private Comissoes coletaDadosCampos() {
        comissoes = new Comissoes(
                this.campoNome.getText(),
                this.comboTipoComissao.getSelectedItem().toString(),
                mg.convValorBanco(campoValor.getText())
        );
        return comissoes;
    }

    private void carregaPrimeiraComissao() {//carrega na tela os dados do Fornecedor
        limitaCampos();
        if (!listaComissoes.isEmpty()) {
            exibeDados(listaComissoes.get(listaComissoes.size() - 1));
            campoCodigo.setEnabled(false);
            comboTipoComissao.setEnabled(false);
            campoNome.setEnabled(false);
            campoValor.setEnabled(false);
            consultaComissoes = new ConsultaComissoes();
            consultaComissoes.setListener(this);
            botaoCadastrar.requestFocus();
            posicao = listaComissoes.size() - 1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhuma Comissão Cadastrada, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);
        }
    }

    private void setCamposFocus() {
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoAtualizar.setFocusTraversalKeysEnabled(false);
    }

    private void limitaCampos() {//limita os campos de texto
        campoNome.setDocument(new DocumentoLimitado(300));
    }

    @Override
    public void mensagemCodComissoes(String idbusca) {//recebe o codigo da busca por listener e busca na lista
        int id = Integer.parseInt(idbusca);
        Comissoes comissoesBusca = new Comissoes();
        for (int i = 0; i < listaComissoes.size(); i++) {
            if (listaComissoes.get(i).getCod() == id) {
                comissoesBusca = listaComissoes.get(i);
                break;
            }
        }
        exibeDados(comissoesBusca);
        posicao = listaComissoes.indexOf(comissoesBusca);
    }

    private void exibeDados(Comissoes comissoes) {
        campoCodigo.setText(Integer.toString(comissoes.getCod()));
        campoNome.setText(comissoes.getNome());
        comboTipoComissao.setSelectedItem(comissoes.getTipo());
        campoValor.setText(comissoes.getValor().toString());
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoNome.setEnabled(campos);
        comboTipoComissao.setEnabled(campos);
        campoValor.setEnabled(campos);
        comboTipoComissao.setEnabled(botoes);
        BotaoListaPrimeiro.setEnabled(botoes);
        BotaoListaAnterior.setEnabled(botoes);
        BotaoListaProximo.setEnabled(botoes);
        BotaoListaUltimo.setEnabled(botoes);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoNome.setText("");
                comboTipoComissao.setEnabled(campos);
                campoNome.setEnabled(campos);
                campoValor.setEnabled(campos);
                campoValor.setText("");
                comboTipoComissao.setEnabled(campos);
                comboTipoComissao.setSelectedIndex(0);
            } else {
                comboTipoComissao.setEnabled(campos);
            }
            BotaoListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
            botaoAtualizar.setEnabled(botoes);
        } else if (tipo == 2) {
            campoNome.setEnabled(campos);
            comboTipoComissao.setEnabled(campos);
            campoValor.setEnabled(campos);
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
        }
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoCodigo.setText(Integer.toString(folhaDePagamentoCtrl.getProximoCodComissao()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            botaoCadastrar.setText("Gravar");
            campoNome.requestFocus();
        } else {
            comissoes = coletaDadosCampos();
            folhaDePagamentoCtrl.cadastrarComissao(comissoes);//persiste no banco
            listaComissoes.add(comissoes);//insere na lista
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            posicao = listaComissoes.size() - 1;//atualiza a posição
            botaoCadastrar.setText("Novo");
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroComissoes.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void BotaoListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaPrimeiroActionPerformed
        exibeDados(listaComissoes.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaoListaPrimeiroActionPerformed
    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaComissoes.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed
    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaComissoes.size() - 1) {
            exibeDados(listaComissoes.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed
    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaComissoes.get(listaComissoes.size() - 1));
        posicao = listaComissoes.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed
    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            habilitaDesabilitaCampos(2, false, true, false);
            botaoAtualizar.setText("Gravar");
        } else {
            comissoes = coletaDadosCampos();
            listaComissoes.remove(posicao);//remove da lista
            listaComissoes.add(posicao, comissoes);//adiciona atualizado na mesma posição
            folhaDePagamentoCtrl.atualizaComissao(comissoes);//persiste no banco
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void campoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodigoActionPerformed
    }//GEN-LAST:event_campoCodigoActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaComissoes.setVisible(true);
            consultaComissoes.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir esta Comissão?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            if (folhaDePagamentoCtrl.removeComissao(Integer.parseInt(campoCodigo.getText()))) {
                listaComissoes.remove(posicao);
                posicao--;
                if (listaComissoes.isEmpty()) {
                    botaoCadastrarActionPerformed(evt);
                } else {
                    exibeDados(listaComissoes.get(posicao));
                }
            }

        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void campoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoValorActionPerformed

    private void comboTipoComissaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTipoComissaoMouseClicked

    }//GEN-LAST:event_comboTipoComissaoMouseClicked

    private void comboTipoComissaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoComissaoItemStateChanged
        if (comboTipoComissao.getSelectedIndex() == 0) {
            labelPorcentagem.setText("%");
        } else {
            labelPorcentagem.setText("");
        }
    }//GEN-LAST:event_comboTipoComissaoItemStateChanged
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new CadastroComissoes().setVisible(true);
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
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoValor;
    private javax.swing.JComboBox comboTipoComissao;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPorcentagem;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelValor;
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

    @Override
    public void mensagemCodHistoricoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodDescontos(String codDesconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodCalculoFolhaPagamento(String codBusca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
