package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.FolhaDePagamentoCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.Descontos;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroDescontos extends javax.swing.JDialog implements InterfaceListener {

    private final FolhaDePagamentoCtrl folhaDePagamentoCtrl;
    private final List<Descontos> listaDescontos;//recebe as comissões para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaDescontos consultaDescontos;//recebe a instancia da tela de consulta
    private Descontos descontos;
    private MetodosGerais mg;

    public CadastroDescontos() {
        initComponents();//inicia componentes da tela
        this.folhaDePagamentoCtrl = new FolhaDePagamentoCtrl();
        this.listaDescontos = folhaDePagamentoCtrl.getListaDescontos();//carrega a lista de descontos
        carregaPrimeiraDesconto();//carrega primeiros dados e configurações na tela
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
        comboTipoDesconto = new javax.swing.JComboBox();
        botaoBuscar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        labelValor = new javax.swing.JLabel();
        campoValor = new JNumberFormatField();
        labelPorcentagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Descontos");
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

        comboTipoDesconto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Porcentagem", "Valor" }));
        comboTipoDesconto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoDescontoItemStateChanged(evt);
            }
        });
        comboTipoDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboTipoDescontoMouseClicked(evt);
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
                                    .addComponent(comboTipoDesconto, 0, 98, Short.MAX_VALUE)
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
                    .addComponent(comboTipoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private Descontos coletaDadosCampos() {
        descontos = new Descontos(
                this.campoNome.getText(),
                this.comboTipoDesconto.getSelectedItem().toString(),
                mg.convValorBanco(campoValor.getText())
        );
        return descontos;
    }

    private void carregaPrimeiraDesconto() {//carrega na tela os dados do Fornecedor
        limitaCampos();
        if (!listaDescontos.isEmpty()) {
            exibeDados(listaDescontos.get(listaDescontos.size() - 1));
            campoCodigo.setEnabled(false);
            comboTipoDesconto.setEnabled(false);
            campoNome.setEnabled(false);
            campoValor.setEnabled(false);
            consultaDescontos = new ConsultaDescontos();
            consultaDescontos.setListener(this);
            botaoCadastrar.requestFocus();
            posicao = listaDescontos.size() - 1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Desconto Cadastrado, Entrando em modo de Inclusão!");
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
    public void mensagemCodDescontos(String idbusca) {//recebe o codigo da busca por listener e busca na lista
        int id = Integer.parseInt(idbusca);
        Descontos descontosBusca = new Descontos();
        for (int i = 0; i < listaDescontos.size(); i++) {
            if (listaDescontos.get(i).getCod() == id) {
                descontosBusca = listaDescontos.get(i);
                break;
            }
        }
        exibeDados(descontosBusca);
        posicao = listaDescontos.indexOf(descontosBusca);
    }

    private void exibeDados(Descontos descontos) {
        campoCodigo.setText(Integer.toString(descontos.getCod()));
        campoNome.setText(descontos.getNome());
        comboTipoDesconto.setSelectedItem(descontos.getTipo());
        campoValor.setText(descontos.getValor().toString());
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoNome.setEnabled(campos);
        comboTipoDesconto.setEnabled(campos);
        campoValor.setEnabled(campos);
        comboTipoDesconto.setEnabled(botoes);
        BotaoListaPrimeiro.setEnabled(botoes);
        BotaoListaAnterior.setEnabled(botoes);
        BotaoListaProximo.setEnabled(botoes);
        BotaoListaUltimo.setEnabled(botoes);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoNome.setText("");
                comboTipoDesconto.setEnabled(campos);
                campoNome.setEnabled(campos);
                campoValor.setEnabled(campos);
                campoValor.setText("");
                comboTipoDesconto.setEnabled(campos);
                comboTipoDesconto.setSelectedIndex(0);
            } else {
                comboTipoDesconto.setEnabled(campos);
            }
            BotaoListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
            botaoAtualizar.setEnabled(botoes);
        } else if (tipo == 2) {
            campoNome.setEnabled(campos);
            comboTipoDesconto.setEnabled(campos);
            campoValor.setEnabled(campos);
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
        }
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed

        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoCodigo.setText(Integer.toString(folhaDePagamentoCtrl.getProximoCodDesconto()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            botaoCadastrar.setText("Gravar");
            campoNome.requestFocus();
        } else {
            descontos = coletaDadosCampos();
            folhaDePagamentoCtrl.cadastrarDesconto(descontos);//persiste no banco
            listaDescontos.add(descontos);//insere na lista
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            posicao = listaDescontos.size() - 1;//atualiza a posição
            botaoCadastrar.setText("Novo");
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroDescontos.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void BotaoListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaPrimeiroActionPerformed
        exibeDados(listaDescontos.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaoListaPrimeiroActionPerformed
    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaDescontos.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed
    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaDescontos.size() - 1) {
            exibeDados(listaDescontos.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed
    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaDescontos.get(listaDescontos.size() - 1));
        posicao = listaDescontos.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed
    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            habilitaDesabilitaCampos(2, false, true, false);
            botaoAtualizar.setText("Gravar");
        } else {
            descontos = coletaDadosCampos();
            listaDescontos.remove(posicao);//remove da lista
            listaDescontos.add(posicao, descontos);//adiciona atualizado na mesma posição
            folhaDePagamentoCtrl.atualizaDesconto(descontos);//persiste no banco
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void campoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodigoActionPerformed
    }//GEN-LAST:event_campoCodigoActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaDescontos.setVisible(true);
            consultaDescontos.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir esta Comissão?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            if (folhaDePagamentoCtrl.removeDesconto(Integer.parseInt(campoCodigo.getText()))) {
                listaDescontos.remove(posicao);
                posicao--;
                if (listaDescontos.isEmpty()) {
                    botaoCadastrarActionPerformed(evt);
                } else {
                    exibeDados(listaDescontos.get(posicao));
                }
            }

        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void campoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoValorActionPerformed

    private void comboTipoDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTipoDescontoMouseClicked

    }//GEN-LAST:event_comboTipoDescontoMouseClicked

    private void comboTipoDescontoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoDescontoItemStateChanged
        if (comboTipoDesconto.getSelectedIndex() == 0) {
            labelPorcentagem.setText("%");
        } else {
            labelPorcentagem.setText("");
        }
    }//GEN-LAST:event_comboTipoDescontoItemStateChanged
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new CadastroDescontos().setVisible(true);
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
    private javax.swing.JComboBox comboTipoDesconto;
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
    public void mensagemCodComissoes(String codDesconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodCalculoFolhaPagamento(String codBusca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
