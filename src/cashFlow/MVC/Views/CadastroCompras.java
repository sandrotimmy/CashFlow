package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.ComprasDAO;
import cashFlow.MVC.DAO.FornecedoresDAO;
import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.DAO.ItemCompraDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.Fornecedores;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.ItemCompra;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.Lancamentos;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CadastroCompras extends javax.swing.JDialog implements InterfaceListener {

    private Lancamentos lancamento;
    private LancamentosDAO persist;
    private final ComprasDAO persistCompra;
    private FornecedoresDAO persistFornecedor;
    private ItemCompraDAO persistItemCompra;
    private ProdutosDAO persistProdutos;
    private final NumberFormat f;
    private ArrayList<ItemCompra> itens;
    private Fornecedores fornecedor;
    private int codItemSequecial;
    private Compras compra;
    private ConsultaFornecedor consultaFornecedor;
    private ConsultaProdutos consultaProduto;
    private Produtos produto;
    private int codProxCompra;
    private ItemCompra itemCompra;
    private DefaultTableModel val;
    private MetodosGerais mg;

    public CadastroCompras(Compras consultaCompra) {
        initComponents();
        val = (DefaultTableModel) tabelaCompras.getModel();
        f = NumberFormat.getCurrencyInstance();
        this.persistCompra = new ComprasDAO();
        this.mg = new MetodosGerais();
        exibeDados(consultaCompra);
        habilitaDesabilitaCampos(false);
        botaoCancelar.requestFocus();
    }

    public CadastroCompras() throws SQLException, ParseException {
        initComponents();
        campoDesconto.setEnabled(false);
        this.lancamento = new Lancamentos();
        this.persist = new LancamentosDAO();
        this.persistCompra = new ComprasDAO();
        this.persistFornecedor = new FornecedoresDAO();
        this.persistProdutos = new ProdutosDAO();
        this.persistItemCompra = new ItemCompraDAO();
        codProxCompra = persistCompra.getProximaCompra();
        this.compra = new Compras(codProxCompra);
        consultaFornecedor = new ConsultaFornecedor();
        consultaProduto = new ConsultaProdutos();
        carregaDados();
        itens = new ArrayList();
        fornecedor = new Fornecedores();
        produto = new Produtos();
        codItemSequecial = 1;
        f = NumberFormat.getCurrencyInstance();
    }

    public void habilitaDesabilitaCampos(boolean campos) {
        campoData.setEnabled(campos);
        campoCodFornecedor.setEnabled(campos);
        campoCodProduto.setEnabled(campos);
        campoQuantidade.setEnabled(campos);
        campoValorUnitario.setEnabled(campos);
        campoValorTotal.setEnabled(campos);
        tabelaCompras.setEnabled(campos);
        campoObservacoes.setEnabled(campos);
        campoMotivoAcrescimo.setEnabled(campos);
        campoDesconto.setEnabled(campos);
        botaoIncluir.setEnabled(campos);
        botaoExcluir.setEnabled(campos);
        botaoGravarCompra.setEnabled(campos);
        BotaoRadioNenhum.setEnabled(campos);
        botaoRadioPorcentagem.setEnabled(campos);
        botaoRadioValor.setEnabled(campos);
    }

    public void exibeDados(Compras c) {
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        campoMotivoAcrescimo.setLineWrap(true);
        campoMotivoAcrescimo.setWrapStyleWord(true);
        campoCod.setText(Integer.toString(c.getCod()));
        campoData.setText(persistCompra.convDataSistema(c.getDataCompra()));
        campoCodFornecedor.setText(Integer.toString(c.getFornecedor().getCod()));
        campoNomeCliente.setText(c.getFornecedor().getRazaosocial());
        campoObservacoes.setText(c.getObservacoes());
        campoMotivoAcrescimo.setText(c.getMotivoAcrescimo());
        campoTotalProdutos.setText(f.format(c.getValorTotalProdutos()));
        campoDesconto.setText(f.format(c.getValorDesconto()));
        campoAcrescimo.setText(f.format(c.getValorAcrescimo()));
        campoTotalCompra.setText(f.format(c.getValorTotalCompra()));
        for (int i = 0; i < c.getItemCompra().size(); i++) {
            insereNaTabela(c.getItemCompra().get(i));
        }
    }

    public void insereNaTabela(ItemCompra item) {
        val.addRow(new String[]{
            Integer.toString(codItemSequecial),
            Integer.toString(item.getCodProduto()),
            item.getProduto(),
            item.getQuantidade().toString(),
            f.format(item.getValorUnitario()),
            f.format(item.getValorTotal())});
    }

    public void carregaDados() {
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        campoMotivoAcrescimo.setLineWrap(true);
        campoMotivoAcrescimo.setWrapStyleWord(true);
        botaoGravarCompra.setFocusTraversalKeysEnabled(false);
        campoCodFornecedor.setFocusTraversalKeysEnabled(false);
        campoCodProduto.setFocusTraversalKeysEnabled(false);
        botaoIncluir.setFocusTraversalKeysEnabled(false);
        campoValorUnitario.setFocusTraversalKeysEnabled(false);
        campoDesconto.setFocusTraversalKeysEnabled(false);
        consultaFornecedor.setListener(this);
        consultaProduto.setListener(this);
        campoCod.setText(Integer.toString(codProxCompra));
        campoData.requestFocus();
//        campoData.setText(mg.getDataAtual());//pega a data do Computador
        campoData.setCaretPosition(0);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaCompras.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabelaCompras.getColumnModel().getColumn(4).setCellRenderer(direita);
        tabelaCompras.getColumnModel().getColumn(5).setCellRenderer(direita);
    }

    @Override
    public void mensagemCodFornecedorCompra(String msg) {
        campoCodFornecedor.setText(msg);
        campoCodFornecedor.requestFocus();
    }

    @Override
    public void mensagemCodProdutoCompra(String msg) {
        campoCodProduto.setText(msg);
        campoCodProduto.requestFocus();
    }

    public ItemCompra coletaDadosCampos() {
        itemCompra = new ItemCompra(
                codItemSequecial,
                Integer.parseInt(campoCodProduto.getText()),
                campoNomeProduto.getText(),
                compra.convValorBanco(campoQuantidade.getText()),
                compra.convValorBanco(campoValorUnitario.getText()),
                compra.convValorBanco(campoValorTotal.getText()),
                compra);
        return itemCompra;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotaoDesconto = new javax.swing.ButtonGroup();
        labelCodigo = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCompras = new javax.swing.JTable();
        botaoCancelar = new javax.swing.JButton();
        labelData = new javax.swing.JLabel();
        labelObservacoes = new javax.swing.JLabel();
        campoObservacao = new javax.swing.JScrollPane();
        campoObservacoes = new javax.swing.JTextArea();
        labelTotalEmCaixa = new javax.swing.JLabel();
        campoTotalProdutos = new JNumberFormatField();
        botaoGravarCompra = new javax.swing.JButton();
        labelCliente = new javax.swing.JLabel();
        campoNomeCliente = new javax.swing.JTextField();
        painelDadosDoProduto = new javax.swing.JPanel();
        labelProduto = new javax.swing.JLabel();
        labelQuantidade = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        campoValorTotal =  new JNumberFormatField();
        campoValorUnitario = new JNumberFormatField();
        campoQuantidade = new JNumberFormatField();
        campoCodProduto = new javax.swing.JTextField();
        campoNomeProduto = new javax.swing.JTextField();
        botaoIncluir = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        painelEstoque = new javax.swing.JPanel();
        labelEstoqueQuantidade = new javax.swing.JLabel();
        labelEstoqueValorVenda = new javax.swing.JLabel();
        campoEstoqueQuantidade = new JNumberFormatField();
        campoEstoqueValorVenda = new JNumberFormatField();
        campoCod = new javax.swing.JTextField();
        campoData = new javax.swing.JTextField();
        campoCodFornecedor = new javax.swing.JTextField();
        painelTipoDesconto = new javax.swing.JPanel();
        BotaoRadioNenhum = new javax.swing.JRadioButton();
        botaoRadioPorcentagem = new javax.swing.JRadioButton();
        botaoRadioValor = new javax.swing.JRadioButton();
        campoTotalCompra = new JNumberFormatField();
        painelMotivoCrescimo = new javax.swing.JPanel();
        labelMotivoAcrescimo = new javax.swing.JLabel();
        campoObservacao1 = new javax.swing.JScrollPane();
        campoMotivoAcrescimo = new javax.swing.JTextArea();
        labelDesconto = new javax.swing.JLabel();
        campoDesconto = new JNumberFormatField();
        labelAcrescimo = new javax.swing.JLabel();
        campoAcrescimo = new JNumberFormatField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Compras");
        setModal(true);
        setName("Cadastro de Caixas"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelCodigo.setText("Código:");

        labelTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotal.setText("Total:");

        tabelaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item:", "Cod. Produto:", "Produto:", "Quantidade:", "Valor Unit.:", "ValorTotal:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCompras.setNextFocusableComponent(botaoIncluir);
        jScrollPane1.setViewportView(tabelaCompras);
        if (tabelaCompras.getColumnModel().getColumnCount() > 0) {
            tabelaCompras.getColumnModel().getColumn(0).setMinWidth(40);
            tabelaCompras.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabelaCompras.getColumnModel().getColumn(0).setMaxWidth(40);
            tabelaCompras.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaCompras.getColumnModel().getColumn(3).setMinWidth(70);
            tabelaCompras.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaCompras.getColumnModel().getColumn(3).setMaxWidth(70);
            tabelaCompras.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(4).setMaxWidth(80);
            tabelaCompras.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        labelData.setText("Data:");

        labelObservacoes.setText("Observações:");

        campoObservacoes.setMinimumSize(new java.awt.Dimension(0, 0));
        campoObservacao.setViewportView(campoObservacoes);

        labelTotalEmCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotalEmCaixa.setText("Total Produtos:");

        campoTotalProdutos.setEditable(false);
        campoTotalProdutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalProdutos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalProdutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaoGravarCompra.setText("Gravar");
        botaoGravarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGravarCompraActionPerformed(evt);
            }
        });

        labelCliente.setText("Fornecedor:");

        campoNomeCliente.setEditable(false);
        campoNomeCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoNomeCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeCliente.setEnabled(false);

        painelDadosDoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Inserir Produtos"));

        labelProduto.setText("Produto:");

        labelQuantidade.setText("Quantidade:");

        labelValor.setText("Valor Unitário:");

        labelValorTotal.setText("Valor Total:");

        campoValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorTotalKeyPressed(evt);
            }
        });

        campoValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorUnitarioKeyPressed(evt);
            }
        });

        campoQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoQuantidadeFocusLost(evt);
            }
        });
        campoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoQuantidadeKeyPressed(evt);
            }
        });

        campoCodProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodProdutoKeyPressed(evt);
            }
        });

        campoNomeProduto.setEditable(false);
        campoNomeProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoNomeProduto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeProduto.setEnabled(false);

        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        painelEstoque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Em Estoque:"));

        labelEstoqueQuantidade.setText("Quantidade:");

        labelEstoqueValorVenda.setText("Valor de Venda:");

        campoEstoqueQuantidade.setBackground(new java.awt.Color(240, 240, 240));

        campoEstoqueValorVenda.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout painelEstoqueLayout = new javax.swing.GroupLayout(painelEstoque);
        painelEstoque.setLayout(painelEstoqueLayout);
        painelEstoqueLayout.setHorizontalGroup(
            painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEstoqueQuantidade)
                    .addComponent(labelEstoqueValorVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoEstoqueValorVenda)
                    .addComponent(campoEstoqueQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        painelEstoqueLayout.setVerticalGroup(
            painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstoqueLayout.createSequentialGroup()
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstoqueQuantidade)
                    .addComponent(campoEstoqueQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEstoqueValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstoqueValorVenda))
                .addContainerGap())
        );

        javax.swing.GroupLayout painelDadosDoProdutoLayout = new javax.swing.GroupLayout(painelDadosDoProduto);
        painelDadosDoProduto.setLayout(painelDadosDoProdutoLayout);
        painelDadosDoProdutoLayout.setHorizontalGroup(
            painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                        .addComponent(labelProduto)
                        .addGap(29, 29, 29)
                        .addComponent(campoCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNomeProduto))
                    .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                        .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(botaoIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExcluir))
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addComponent(labelQuantidade)
                                .addGap(11, 11, 11)
                                .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosDoProdutoLayout.createSequentialGroup()
                                    .addComponent(labelValor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoValorUnitario))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosDoProdutoLayout.createSequentialGroup()
                                    .addComponent(labelValorTotal)
                                    .addGap(18, 18, 18)
                                    .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(painelEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        painelDadosDoProdutoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoExcluir, botaoIncluir});

        painelDadosDoProdutoLayout.setVerticalGroup(
            painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNomeProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelProduto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                        .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelQuantidade)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosDoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelValorTotal)
                            .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoIncluir)
                            .addComponent(botaoExcluir))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(painelEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        campoCod.setEditable(false);
        campoCod.setEnabled(false);

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataKeyPressed(evt);
            }
        });

        campoCodFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodFornecedorKeyPressed(evt);
            }
        });

        painelTipoDesconto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de Desconto:"));

        grupoBotaoDesconto.add(BotaoRadioNenhum);
        BotaoRadioNenhum.setSelected(true);
        BotaoRadioNenhum.setText("Nenhum");
        BotaoRadioNenhum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRadioNenhumMouseClicked(evt);
            }
        });

        grupoBotaoDesconto.add(botaoRadioPorcentagem);
        botaoRadioPorcentagem.setText("Porcentagem");
        botaoRadioPorcentagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoRadioPorcentagemMouseClicked(evt);
            }
        });

        grupoBotaoDesconto.add(botaoRadioValor);
        botaoRadioValor.setText("Valor");
        botaoRadioValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoRadioValorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelTipoDescontoLayout = new javax.swing.GroupLayout(painelTipoDesconto);
        painelTipoDesconto.setLayout(painelTipoDescontoLayout);
        painelTipoDescontoLayout.setHorizontalGroup(
            painelTipoDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTipoDescontoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotaoRadioNenhum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoRadioPorcentagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoRadioValor))
        );
        painelTipoDescontoLayout.setVerticalGroup(
            painelTipoDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTipoDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BotaoRadioNenhum)
                .addComponent(botaoRadioPorcentagem)
                .addComponent(botaoRadioValor))
        );

        campoTotalCompra.setEditable(false);
        campoTotalCompra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalCompra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoTotalCompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalCompra.setEnabled(false);

        painelMotivoCrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Acréscimo:"));

        labelMotivoAcrescimo.setText("Motivo:");

        campoMotivoAcrescimo.setMinimumSize(new java.awt.Dimension(0, 0));
        campoMotivoAcrescimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoMotivoAcrescimoKeyPressed(evt);
            }
        });
        campoObservacao1.setViewportView(campoMotivoAcrescimo);

        javax.swing.GroupLayout painelMotivoCrescimoLayout = new javax.swing.GroupLayout(painelMotivoCrescimo);
        painelMotivoCrescimo.setLayout(painelMotivoCrescimoLayout);
        painelMotivoCrescimoLayout.setHorizontalGroup(
            painelMotivoCrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelMotivoCrescimoLayout.createSequentialGroup()
                .addComponent(labelMotivoAcrescimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoObservacao1))
        );
        painelMotivoCrescimoLayout.setVerticalGroup(
            painelMotivoCrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMotivoCrescimoLayout.createSequentialGroup()
                .addComponent(labelMotivoAcrescimo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(campoObservacao1)
        );

        labelDesconto.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelDesconto.setText("Desconto:");

        campoDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDescontoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoDescontoFocusLost(evt);
            }
        });
        campoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDescontoKeyPressed(evt);
            }
        });

        labelAcrescimo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelAcrescimo.setText("Acréscimo:");

        campoAcrescimo.setEnabled(false);
        campoAcrescimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoAcrescimoFocusLost(evt);
            }
        });
        campoAcrescimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoAcrescimoKeyPressed(evt);
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelData)
                                    .addComponent(labelCodigo)
                                    .addComponent(labelCliente))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(painelDadosDoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelObservacoes)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(campoObservacao))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelTipoDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelMotivoCrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botaoGravarCompra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoCancelar))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelTotalEmCaixa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(campoAcrescimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(1, 1, 1)))
                        .addGap(14, 14, 14))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoGravarCompra});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCliente)
                    .addComponent(campoCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDadosDoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObservacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTotalEmCaixa)
                                    .addComponent(campoTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDesconto)))
                            .addComponent(painelTipoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelAcrescimo)
                                    .addComponent(campoAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTotal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoCancelar)
                                    .addComponent(botaoGravarCompra)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(painelMotivoCrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(campoObservacao))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        val = (DefaultTableModel) tabelaCompras.getModel();
        itemCompra = coletaDadosCampos();
        itens.add(itemCompra);
        val.addRow(new String[]{
            Integer.toString(codItemSequecial),
            Integer.toString(itemCompra.getCodProduto()),
            itemCompra.getProduto(),
            itemCompra.getQuantidade().toString(),
            f.format(itemCompra.getValorUnitario()),
            f.format(itemCompra.getValorTotal())});
        BigDecimal totalProduto = compra.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalItem = itemCompra.getValorTotal();
        campoTotalProdutos.setText(f.format(totalProduto.add(totalItem)));
        campoTotalCompra.setText(f.format(totalProduto.add(totalItem)));
        botaoIncluir.setEnabled(false);
        campoCodProduto.setText("");
        campoQuantidade.setText("");
        campoValorUnitario.setText("");
        campoValorTotal.setText("");
        campoNomeProduto.setText("");
        campoEstoqueQuantidade.setText("");
        campoEstoqueValorVenda.setText("");
        campoCodProduto.requestFocus();
        codItemSequecial++;
    }//GEN-LAST:event_botaoIncluirActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int posicao = Integer.parseInt((String) tabelaCompras.getValueAt(tabelaCompras.getSelectedRow(), 0));
        ((DefaultTableModel) tabelaCompras.getModel()).removeRow(tabelaCompras.getSelectedRow());
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getSequenciaProduto() == posicao) {
                BigDecimal totalProduto = compra.convValorBanco(campoTotalProdutos.getText());
                BigDecimal totalItem = itens.get(i).getValorTotal();
                campoTotalProdutos.setText(f.format(totalProduto.subtract(totalItem)));
                campoTotalCompra.setText(f.format(totalProduto.subtract(totalItem)));
                itens.remove(i);
                JOptionPane.showMessageDialog(campoCodFornecedor, "Item Removido com Sucesso!");
                break;
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroCompras.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void campoCodProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodProdutoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int codItem = Integer.parseInt(campoCodProduto.getText());
            boolean existeProduto = false;
            for (ItemCompra each : itens) {
                if (codItem == each.getCodProduto()) {
                    existeProduto = true;
                    break;
                }
            }
            if (existeProduto == true) {
                JOptionPane.showMessageDialog(campoCodFornecedor, "Produto já incluído!");
                campoCodProduto.requestFocus();
            } else {
                produto = persistProdutos.pesquisaProdutos(Integer.parseInt(campoCodProduto.getText()));
                campoNomeProduto.setText(produto.getDescricao());
                campoEstoqueQuantidade.setText(f.format(produto.getQuantidade()));
                campoEstoqueValorVenda.setText(f.format(produto.getValorTotalVenda()));
                campoValorUnitario.setText(f.format(produto.getValorUnitario()));
                campoQuantidade.requestFocus();
                botaoIncluir.setEnabled(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaProduto.setVisible(true);
            consultaProduto.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodProdutoKeyPressed
    private void campoValorTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorTotalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoIncluir.requestFocus();
        }
    }//GEN-LAST:event_campoValorTotalKeyPressed
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CadastroCompras.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    private void botaoGravarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGravarCompraActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(campoData.getText());
            compra.setDataCompra(date);
            compra.setObservacoes(campoObservacoes.getText());
            compra.setItemCompra(itens);
            campoDescontoFocusLost(null);
            campoAcrescimoFocusLost(null);
            persistCompra.cadastraCompra(compra);
            itens.stream().forEach((each) -> {
                Produtos altProduto = persistProdutos.pesquisaProdutos(each.getCodProduto());
                altProduto.setQuantidade(altProduto.getQuantidade().add(each.getQuantidade()));
                altProduto.setValorUnitario(each.getValorUnitario());
                altProduto.setValorTotal(altProduto.getQuantidade().multiply(each.getValorUnitario()));
                altProduto.setValorTotalVenda(altProduto.getQuantidade().multiply(altProduto.getValorUnitarioVenda()));
                persistProdutos.atualizaProduto(altProduto);
                persistItemCompra.cadastraItemCompra(each);
            });
            //Gera um lançamento no Caixa
            if (JOptionPane.showConfirmDialog(null, "Deseja Gerar Lançamento no Caixa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                HistoricosDAO hd = new HistoricosDAO();
                HistoricoPadrao hp = hd.getHistorico(2);
                lancamento.setCod(persist.getProximoCodLancamento());
                lancamento.setDataLancamento(compra.getDataCompra());
                lancamento.setHistorico(hp);
                lancamento.setObservacoes(hp.getNomeHistorico() + compra.getCod() + " do Fornecedor: " + compra.getFornecedor().getRazaosocial());
                lancamento.setValorDebito(new BigDecimal("0.00"));
                lancamento.setValorCredito(compra.getValorTotalCompra());
                persist.cadastrarLancamento(lancamento);
            }

        } catch (ParseException ex) {
            Logger.getLogger(CadastroCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Compra Efetuada com Sucesso!");
        this.dispose();

    }//GEN-LAST:event_botaoGravarCompraActionPerformed
    private void campoCodFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodFornecedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fornecedor = persistFornecedor.pesquisaFornecedor(Integer.parseInt(campoCodFornecedor.getText()));
            compra.setFornecedor(fornecedor);
            campoNomeCliente.setText(fornecedor.getRazaosocial());
            campoCodProduto.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaFornecedor.setVisible(true);
            consultaFornecedor.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodFornecedorKeyPressed
    private void campoDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoCodFornecedor.requestFocus();
        }
    }//GEN-LAST:event_campoDataKeyPressed
    private void campoValorUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorUnitarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoValorTotal.setText(f.format(compra.convValorBanco(
                    campoQuantidade.getText()).multiply(compra.convValorBanco(
                            campoValorUnitario.getText()))));
            campoValorTotal.requestFocus();
        }
    }//GEN-LAST:event_campoValorUnitarioKeyPressed
    private void campoQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoQuantidadeFocusLost
        BigDecimal qtd = compra.convValorBanco(campoQuantidade.getText());
        campoValorUnitario.requestFocus();
        campoValorTotal.setText(f.format(qtd.multiply(produto.getValorUnitario())));
    }//GEN-LAST:event_campoQuantidadeFocusLost
    private void BotaoRadioNenhumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRadioNenhumMouseClicked
        campoDesconto.setText("");
        campoDesconto.setEnabled(false);
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalCompra.setText(f.format(compra.convValorBanco(campoTotalProdutos.getText())));
    }//GEN-LAST:event_BotaoRadioNenhumMouseClicked
    private void botaoRadioPorcentagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioPorcentagemMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalCompra.setText(f.format(compra.convValorBanco(campoTotalProdutos.getText())));
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioPorcentagemMouseClicked
    private void botaoRadioValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioValorMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalCompra.setText(f.format(compra.convValorBanco(campoTotalProdutos.getText())));
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioValorMouseClicked
    private void campoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoGravarCompra.requestFocus();
        }
    }//GEN-LAST:event_campoDescontoKeyPressed
    private void campoDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescontoFocusLost
        BigDecimal desconto = compra.convValorBanco(campoDesconto.getText());
        BigDecimal totalProdutos = compra.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalCompra = compra.convValorBanco(campoTotalCompra.getText());
        if (botaoRadioPorcentagem.isSelected()) {
            BigDecimal divPorcent = desconto.divide(new BigDecimal("100"));
            BigDecimal totalDesconto = totalProdutos.multiply(divPorcent);
            campoTotalCompra.setText(f.format(totalProdutos.subtract(totalDesconto)));
            totalCompra = totalProdutos.subtract(totalDesconto);
            compra.setTipoDesconto("Porcentagem");
        } else if (botaoRadioValor.isSelected()) {
            campoTotalCompra.setText(f.format(totalProdutos.subtract(desconto)));
            totalCompra = totalProdutos.subtract(desconto);
            compra.setTipoDesconto("Valor");
        } else {
            compra.setTipoDesconto("Nenhum");
        }
        compra.setValorTotalProdutos(totalProdutos);
        compra.setValorDesconto(desconto);
        compra.setValorTotalCompra(totalCompra);
        botaoGravarCompra.requestFocus();
    }//GEN-LAST:event_campoDescontoFocusLost
    private void campoAcrescimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoAcrescimoFocusLost
        BigDecimal acrescimo = compra.convValorBanco(campoAcrescimo.getText());
        BigDecimal totalProdutos = compra.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalCompra = totalProdutos.add(acrescimo);
        campoTotalCompra.setText(f.format(totalCompra));
        compra.setValorAcrescimo(acrescimo);
        compra.setMotivoAcrescimo(campoMotivoAcrescimo.getText());
        compra.setValorTotalCompra(totalCompra);
        botaoGravarCompra.requestFocus();
    }//GEN-LAST:event_campoAcrescimoFocusLost
    private void campoQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoQuantidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoValorUnitario.requestFocus();
        }
    }//GEN-LAST:event_campoQuantidadeKeyPressed

    private void campoMotivoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoAcrescimo.setEnabled(true);
            campoDesconto.setText("");
            campoDesconto.setEnabled(false);
            BotaoRadioNenhum.setSelected(true);
            campoAcrescimo.requestFocus();
            campoTotalCompra.setText(f.format(compra.convValorBanco(campoTotalProdutos.getText())));
        }
    }//GEN-LAST:event_campoMotivoAcrescimoKeyPressed
    private void campoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoGravarCompra.requestFocus();
        }
    }//GEN-LAST:event_campoAcrescimoKeyPressed

    private void campoDescontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescontoFocusGained
        campoMotivoAcrescimo.setText("");
    }//GEN-LAST:event_campoDescontoFocusGained
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CadastroCompras().setVisible(true);
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(CadastroCompras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BotaoRadioNenhum;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoGravarCompra;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JRadioButton botaoRadioPorcentagem;
    private javax.swing.JRadioButton botaoRadioValor;
    private javax.swing.JTextField campoAcrescimo;
    private javax.swing.JTextField campoCod;
    private javax.swing.JTextField campoCodFornecedor;
    private javax.swing.JTextField campoCodProduto;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDesconto;
    private javax.swing.JTextField campoEstoqueQuantidade;
    private javax.swing.JTextField campoEstoqueValorVenda;
    private javax.swing.JTextArea campoMotivoAcrescimo;
    private javax.swing.JTextField campoNomeCliente;
    private javax.swing.JTextField campoNomeProduto;
    private javax.swing.JScrollPane campoObservacao;
    private javax.swing.JScrollPane campoObservacao1;
    private javax.swing.JTextArea campoObservacoes;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTotalCompra;
    private javax.swing.JTextField campoTotalProdutos;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.JTextField campoValorUnitario;
    private javax.swing.ButtonGroup grupoBotaoDesconto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAcrescimo;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelEstoqueValorVenda;
    private javax.swing.JLabel labelMotivoAcrescimo;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotalEmCaixa;
    private javax.swing.JLabel labelValor;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JPanel painelDadosDoProduto;
    private javax.swing.JPanel painelEstoque;
    private javax.swing.JPanel painelMotivoCrescimo;
    private javax.swing.JPanel painelTipoDesconto;
    private javax.swing.JTable tabelaCompras;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodHistoricoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodClienteVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodProdutoVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodProdutoReajuste(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet.");
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
