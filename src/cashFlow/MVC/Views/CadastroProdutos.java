package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CadastroProdutos extends javax.swing.JDialog implements InterfaceListener {

    private final ProdutosDAO persistProduto;
    private Produtos produto;
    private final ArrayList<Produtos> listaProdutos;
    private int posicao;
    private final NumberFormat f;
    private ConsultaProdutos consultaProduto;

    public CadastroProdutos() throws SQLException {
        initComponents();
        produto = new Produtos();
        persistProduto = new ProdutosDAO();
        f = NumberFormat.getCurrencyInstance();
        listaProdutos = persistProduto.getListaProdutos();
        setFocusCampos();
        carregaPrimeiroProduto();
    }

    public final void setFocusCampos() {
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        campoValorTotal.setFocusTraversalKeysEnabled(false);
        campoUnidade.setFocusTraversalKeysEnabled(false);
        campoValorTotalVenda.setFocusTraversalKeysEnabled(false);
    }

    private void carregaPrimeiroProduto() {
        limitaCampos();
        if (!listaProdutos.isEmpty()) {
            exibeDados(listaProdutos.get(listaProdutos.size() - 1));
            posicao = listaProdutos.size() - 1;
            //bloqueia todos os campos e seta o focus do botao Cadastrar
            campoIdentificador.setEnabled(false);
            campoDescricao.setEnabled(false);
            campoUnidade.setEnabled(false);
            campoQuantidade.setEnabled(false);
            campoValorUnitario.setEnabled(false);
            campoValorTotal.setEnabled(false);
            campoValorUnitarioVenda.setEnabled(false);
            campoValorTotalVenda.setEnabled(false);
            consultaProduto = new ConsultaProdutos();
            consultaProduto.setListener(this);
            botaoCadastrar.requestFocus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Produto Cadastrado. Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);
        }
    }

    private void limitaCampos() {//limita os campos de texto
        campoIdentificador.setDocument(new DocumentoLimitado(10));
        campoDescricao.setDocument(new DocumentoLimitado(100));
        campoUnidade.setDocument(new DocumentoLimitado(3));
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
        int codBusca = Integer.parseInt(msg);
        Produtos produtoExibir = new Produtos();
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).getCod() == codBusca) {
                produtoExibir = listaProdutos.get(i);
                break;
            }
        }
        exibeDados(produtoExibir);
        posicao = listaProdutos.indexOf(produtoExibir);
    }

    private void exibeDados(Produtos produto) {
        campoCodigo.setText(produto.getCod().toString());
        campoIdentificador.setText(produto.getIdentificador());
        campoDescricao.setText(produto.getDescricao());
        campoUnidade.setText(produto.getUnidade());
        campoQuantidade.setText(f.format(produto.getQuantidade()));
        campoValorUnitario.setText(f.format(produto.getValorUnitario()));
        campoValorTotal.setText(f.format(produto.getValorTotal()));
        campoValorUnitarioVenda.setText(f.format(produto.getValorUnitarioVenda()));
        campoValorTotalVenda.setText(f.format(produto.getValorTotalVenda()));
    }

    public Produtos coletaDadosCampos() {
        BigDecimal vuv = produto.convValorBanco(campoValorUnitarioVenda.getText());
        if (vuv.compareTo(new BigDecimal(0.01)) < 0) {
            JOptionPane.showMessageDialog(null, "Produto deve conter o valor Unitario de Venda!");
            return null;
        } else {
            produto = new Produtos(
                    Integer.parseInt(campoCodigo.getText()),
                    campoIdentificador.getText(),
                    campoDescricao.getText(),
                    campoUnidade.getText(),
                    produto.convValorBanco(campoQuantidade.getText()),
                    produto.convValorBanco(campoValorUnitario.getText()),
                    produto.convValorBanco(campoValorTotal.getText()),
                    produto.convValorBanco(campoValorUnitarioVenda.getText()),
                    produto.convValorBanco(campoValorTotalVenda.getText()));
            return produto;
        }
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoIdentificador.setEnabled(campos);
        campoDescricao.setEnabled(campos);
        campoUnidade.setEnabled(campos);
        BotaoListaPrimeiro.setEnabled(botoes);
        BotaoListaAnterior.setEnabled(botoes);
        BotaoListaProximo.setEnabled(botoes);
        BotaoListaUltimo.setEnabled(botoes);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            campoQuantidade.setEnabled(campos);
            campoValorUnitario.setEnabled(campos);
            campoValorTotal.setEnabled(campos);
            campoValorUnitarioVenda.setEnabled(campos);
            campoValorTotalVenda.setEnabled(campos);
            if (limpaCampos == true) {

                campoIdentificador.setText("");
                campoDescricao.setText("");
                campoUnidade.setText("");
                campoQuantidade.setText("");
                campoValorUnitario.setText("");
                campoValorTotal.setText("");
                campoValorUnitarioVenda.setText("");
                campoValorTotalVenda.setText("");
            }
            BotaoListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
            botaoAtualizar.setEnabled(botoes);
        } else if (tipo == 2) {
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        labelIdentificador = new javax.swing.JLabel();
        labelUnidade = new javax.swing.JLabel();
        labelQuantidade = new javax.swing.JLabel();
        labelvalorUnitario = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        campoIdentificador = new javax.swing.JTextField();
        campoDescricao = new javax.swing.JTextField();
        campoUnidade = new javax.swing.JTextField();
        campoQuantidade = new JNumberFormatField();
        campoValorUnitario = new JNumberFormatField();
        campoValorTotal = new JNumberFormatField();
        painelVenda = new javax.swing.JPanel();
        labelValorUnitarioVenda = new javax.swing.JLabel();
        labelValorTotalVenda = new javax.swing.JLabel();
        campoValorUnitarioVenda = new JNumberFormatField();
        campoValorTotalVenda = new JNumberFormatField();
        botaoCancelar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaPrimeiro = new javax.swing.JButton();
        botaoBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        setModal(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelCodigo.setText("Código:");

        labelDescricao.setText("Descrição:");

        labelIdentificador.setText("Identificador:");

        labelUnidade.setText("Unidade:");

        labelQuantidade.setText("Quantidade:");

        labelvalorUnitario.setText("Valor Unitário:");

        labelValorTotal.setText("Valor Total:");

        campoCodigo.setEditable(false);
        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        campoIdentificador.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoIdentificador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoIdentificadorKeyPressed(evt);
            }
        });

        campoDescricao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDescricaoKeyPressed(evt);
            }
        });

        campoUnidade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoUnidadeKeyPressed(evt);
            }
        });

        campoQuantidade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoQuantidadeKeyPressed(evt);
            }
        });

        campoValorUnitario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorUnitarioFocusLost(evt);
            }
        });
        campoValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoValorUnitarioActionPerformed(evt);
            }
        });
        campoValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorUnitarioKeyPressed(evt);
            }
        });

        campoValorTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorTotalKeyPressed(evt);
            }
        });

        painelVenda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Venda"));

        labelValorUnitarioVenda.setText("Valor Unitário:");

        labelValorTotalVenda.setText("Valor Total:");

        campoValorUnitarioVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorUnitarioVenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorUnitarioVendaFocusLost(evt);
            }
        });
        campoValorUnitarioVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorUnitarioVendaKeyPressed(evt);
            }
        });

        campoValorTotalVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorTotalVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorTotalVendaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelVendaLayout = new javax.swing.GroupLayout(painelVenda);
        painelVenda.setLayout(painelVendaLayout);
        painelVendaLayout.setHorizontalGroup(
            painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVendaLayout.createSequentialGroup()
                .addGroup(painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValorUnitarioVenda)
                    .addComponent(labelValorTotalVenda))
                .addGap(19, 19, 19)
                .addGroup(painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoValorUnitarioVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(campoValorTotalVenda))
                .addContainerGap())
        );
        painelVendaLayout.setVerticalGroup(
            painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorUnitarioVenda)
                    .addComponent(campoValorUnitarioVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorTotalVenda)
                    .addComponent(campoValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
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

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.setMaximumSize(new java.awt.Dimension(75, 23));
        botaoCadastrar.setMinimumSize(new java.awt.Dimension(75, 23));
        botaoCadastrar.setPreferredSize(new java.awt.Dimension(75, 23));
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        BotaoListaUltimo.setText(">|");
        BotaoListaUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaUltimoActionPerformed(evt);
            }
        });

        BotaoListaProximo.setText(">");
        BotaoListaProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaProximoActionPerformed(evt);
            }
        });

        BotaoListaAnterior.setText("<");
        BotaoListaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaAnteriorActionPerformed(evt);
            }
        });

        BotaoListaPrimeiro.setText("|<");
        BotaoListaPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaPrimeiroActionPerformed(evt);
            }
        });

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
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
                            .addComponent(labelIdentificador)
                            .addComponent(labelDescricao))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoDescricao)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCodigo)
                                .addGap(42, 42, 42)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(BotaoListaPrimeiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaProximo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaUltimo))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelUnidade)
                                        .addGap(36, 36, 36)
                                        .addComponent(campoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelQuantidade)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelvalorUnitario)
                                            .addComponent(labelValorTotal))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(campoValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                            .addComponent(campoValorUnitario))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(painelVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, botaoBuscar, botaoCadastrar, botaoCancelar, botaoExcluir});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaoListaAnterior, BotaoListaPrimeiro, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoListaUltimo)
                    .addComponent(BotaoListaProximo)
                    .addComponent(BotaoListaAnterior)
                    .addComponent(BotaoListaPrimeiro)
                    .addComponent(botaoBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIdentificador)
                    .addComponent(campoIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescricao)
                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelUnidade)
                            .addComponent(campoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelQuantidade)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelvalorUnitario)
                            .addComponent(campoValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelValorTotal)
                            .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoCancelar)
                            .addComponent(botaoExcluir)
                            .addComponent(botaoAtualizar)
                            .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(painelVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoIdentificador.requestFocus();
            campoCodigo.setText(Integer.toString(persistProduto.getProximoProduto()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            botaoCadastrar.setText("Gravar");
        } else {
            Produtos produtoInserir = coletaDadosCampos();
            if (produtoInserir != null) {
                habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
                listaProdutos.add(produtoInserir);
                persistProduto.cadastrarProduto(produtoInserir);
                posicao = listaProdutos.size() - 1;
                botaoCadastrar.setText("Cadastrar");
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Produto?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (posicao == listaProdutos.size() - 1) {
                persistProduto.removeProduto(Integer.parseInt(campoCodigo.getText()));
                listaProdutos.remove(posicao);
                posicao--;
                if (posicao < 0) {
                    carregaPrimeiroProduto();
                    //exibeDados(listaProdutos.get(posicao));
                }
            } else {
                persistProduto.removeProduto(Integer.parseInt(campoCodigo.getText()));
                listaProdutos.remove(posicao);
                exibeDados(listaProdutos.get(posicao));
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroProdutos.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            campoIdentificador.requestFocus();
            habilitaDesabilitaCampos(2, false, true, false);
            botaoAtualizar.setText("Gravar");
        } else {
            produto = coletaDadosCampos();
            listaProdutos.remove(posicao);
            listaProdutos.add(posicao, produto);
            persistProduto.atualizaProduto(produto);
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
            JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso!");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void campoValorUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorUnitarioKeyPressed

    }//GEN-LAST:event_campoValorUnitarioKeyPressed

    private void campoValorUnitarioVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorUnitarioVendaKeyPressed

    }//GEN-LAST:event_campoValorUnitarioVendaKeyPressed

    private void campoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUnidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoQuantidade.requestFocus();
        }
    }//GEN-LAST:event_campoUnidadeKeyPressed

    private void campoValorTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorTotalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoValorUnitarioVenda.requestFocus();
        }
    }//GEN-LAST:event_campoValorTotalKeyPressed

    private void campoIdentificadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIdentificadorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoDescricao.requestFocus();
        }
    }//GEN-LAST:event_campoIdentificadorKeyPressed

    private void campoDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoUnidade.requestFocus();
        }
    }//GEN-LAST:event_campoDescricaoKeyPressed

    private void campoQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoQuantidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoValorUnitario.requestFocus();
        }
    }//GEN-LAST:event_campoQuantidadeKeyPressed

    private void campoValorTotalVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorTotalVendaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            botaoCadastrar.requestFocus();
        }
    }//GEN-LAST:event_campoValorTotalVendaKeyPressed

    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed

        exibeDados(listaProdutos.get(listaProdutos.size() - 1));
        posicao = listaProdutos.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed

    private void BotaoListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaPrimeiroActionPerformed

        exibeDados(listaProdutos.get(0));
        posicao = 0;

    }//GEN-LAST:event_BotaoListaPrimeiroActionPerformed

    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaProdutos.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed

    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed

        if (posicao < listaProdutos.size() - 1) {
            exibeDados(listaProdutos.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CadastroProdutos.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void campoValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoValorUnitarioActionPerformed

    private void campoValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorUnitarioFocusLost
        campoValorTotal.setText(f.format(new BigDecimal(campoQuantidade.getText().replaceAll("\\.", "").replaceAll(",", "\\.")).multiply(new BigDecimal(campoValorUnitario.getText().replaceAll("\\.", "").replaceAll(",", "\\.")))));
    }//GEN-LAST:event_campoValorUnitarioFocusLost

    private void campoValorUnitarioVendaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorUnitarioVendaFocusLost
        campoValorTotalVenda.setText(f.format(new BigDecimal(campoQuantidade.getText().replaceAll("\\.", "").replaceAll(",", "\\.")).multiply(new BigDecimal(campoValorUnitarioVenda.getText().replaceAll("\\.", "").replaceAll(",", "\\.")))));
    }//GEN-LAST:event_campoValorUnitarioVendaFocusLost

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaProduto.setVisible(true);
            consultaProduto.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CadastroProdutos().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoIdentificador;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoUnidade;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.JTextField campoValorTotalVenda;
    private javax.swing.JTextField campoValorUnitario;
    private javax.swing.JTextField campoValorUnitarioVenda;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelIdentificador;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelUnidade;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JLabel labelValorTotalVenda;
    private javax.swing.JLabel labelValorUnitarioVenda;
    private javax.swing.JLabel labelvalorUnitario;
    private javax.swing.JPanel painelVenda;
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
    public void mensagemCodHistoricoBusca(String msg) {
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
