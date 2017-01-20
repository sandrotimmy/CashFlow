package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.ClientesCtrl;
import cashFlow.MVC.Controllers.LancamentosCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Controllers.VendasCtrl;
import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.ItemVenda;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.Lancamentos;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.ParcelasVenda;
import cashFlow.MVC.Models.PassaCamposComEnter;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CadastroVendas extends javax.swing.JDialog implements InterfaceListener {

    private final VendasCtrl vendasCtrl;
    private final LancamentosCtrl lancamentosCtrl;
    private final ClientesCtrl clientesCtrl;
    private int codItemSequecial;
    private Vendas venda;
    private ConsultaCliente consultaCliente;
    private ConsultaProdutos consultaProduto;
    private Produtos produto;
    private int codProxVenda;
    private final DefaultTableModel valVendas;
    private final DefaultTableModel valParcelamento;
    private ItemVenda itemVenda;
    public PassaCamposComEnter pc;
    private MetodosGerais mg;
    private ParcelamentoVendas parcelamentoVendas;
    private final NumberFormat f;

    public CadastroVendas(Vendas consultaVenda) {
        initComponents();
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        valVendas = (DefaultTableModel) tabelaVendas.getModel();
        valParcelamento = (DefaultTableModel) tabelaParcelamento.getModel();
        this.mg = new MetodosGerais();
        this.vendasCtrl = new VendasCtrl();
        this.lancamentosCtrl = new LancamentosCtrl();
        this.clientesCtrl = new ClientesCtrl();
        exibeDados(consultaVenda);
        habilitaDesabilitaCampos(false);
        botaoCancelar.requestFocus();
        pc = new PassaCamposComEnter();
    }

    public CadastroVendas() throws SQLException, ParseException {
        initComponents();
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        valVendas = (DefaultTableModel) tabelaVendas.getModel();
        valParcelamento = (DefaultTableModel) tabelaParcelamento.getModel();
        this.mg = new MetodosGerais();
        this.vendasCtrl = new VendasCtrl();
        codProxVenda = vendasCtrl.getProximaVenda();
        this.venda = new Vendas(codProxVenda);
        this.lancamentosCtrl = new LancamentosCtrl();
        this.clientesCtrl = new ClientesCtrl();
        consultaCliente = new ConsultaCliente();
        consultaProduto = new ConsultaProdutos();
        carregaDados();
        produto = new Produtos();
        codItemSequecial = 1;

    }

    public void limitaCampos() {
        campoObservacoes.setDocument(new DocumentoLimitado(200));
        campoMotivoAcrescimo.setDocument(new DocumentoLimitado(200));
        campoUnidade.setDocument(new DocumentoLimitado(3));
        campoFormaPgto.setDocument(new DocumentoLimitado(100));

    }

    public void carregaDados() {
        limitaCampos();
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        campoMotivoAcrescimo.setLineWrap(true);
        campoMotivoAcrescimo.setWrapStyleWord(true);
        botaoGravarVenda.setFocusTraversalKeysEnabled(false);
        campoCodCliente.setFocusTraversalKeysEnabled(false);
        campoCodProduto.setFocusTraversalKeysEnabled(false);
        botaoIncluir.setFocusTraversalKeysEnabled(false);
        campoValorUnitario.setFocusTraversalKeysEnabled(false);
        campoDesconto.setFocusTraversalKeysEnabled(false);
        campoEntrada.setFocusTraversalKeysEnabled(false);
        campoValorUnitario.setFocusTraversalKeysEnabled(false);
        campoDesconto.setEnabled(false);
        consultaCliente.setListener(this);
        consultaProduto.setListener(this);
        campoCod.setText(Integer.toString(codProxVenda));
        campoData.requestFocus();
        campoData.setText(mg.getDataAtual());//pega a data do Computador
        campoData.setCaretPosition(0);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaVendas.getColumnModel().getColumn(4).setCellRenderer(direita);
        tabelaVendas.getColumnModel().getColumn(5).setCellRenderer(direita);
        tabelaVendas.getColumnModel().getColumn(6).setCellRenderer(direita);
        insereCampoData();
    }

    public void habilitaDesabilitaCampos(boolean campos) {
        campoData.setEnabled(campos);
        campoCodCliente.setEnabled(campos);
        campoCodProduto.setEnabled(campos);
        campoQuantidade.setEnabled(campos);
        campoUnidade.setEnabled(campos);
        campoEntrada.setEnabled(campos);
        campoValorUnitario.setEnabled(campos);
        campoValorTotal.setEnabled(campos);
        tabelaVendas.setEnabled(campos);
        campoObservacoes.setEnabled(campos);
        campoMotivoAcrescimo.setEnabled(campos);
        campoDesconto.setEnabled(campos);
        botaoIncluir.setEnabled(campos);
        botaoExcluir.setEnabled(campos);
        botaoGravarVenda.setEnabled(campos);
        BotaoRadioNenhum.setEnabled(campos);
        botaoRadioPorcentagem.setEnabled(campos);
        botaoRadioValor.setEnabled(campos);
    }

    public void exibeDados(Vendas v) {
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        campoMotivoAcrescimo.setLineWrap(true);
        campoMotivoAcrescimo.setWrapStyleWord(true);
        campoCod.setText(Integer.toString(v.getCod()));
        campoData.setText(mg.convDataSistema(v.getDataVenda()));
        campoCodCliente.setText(Integer.toString(v.getCliente().getCod()));
        campoNomeCliente.setText(v.getCliente().getNome());
        campoObservacoes.setText(v.getObservacoes());
        campoMotivoAcrescimo.setText(v.getMotivoAcrescimo());
        campoTotalProdutos.setText(v.getValorTotalProdutos().toString());
        campoDesconto.setText(v.getValorDesconto().toString());
        campoAcrescimo.setText(v.getValorAcrescimo().toString());
        campoTotalVenda.setText(v.getValorTotalVenda().toString());
        campoFormaPgto.setEnabled(false);
        campoNumParcelas.setEnabled(false);
        campoDataEntrada.setEnabled(false);
        campoDiasPrazo.setEnabled(false);
        tabelaParcelamento.setEnabled(false);
        tabelaVendas.setEnabled(false);
        botaoParcelar.setEnabled(false);
        for (int i = 0; i < v.getItemVenda().size(); i++) {
            insereNaTabela(v.getItemVenda().get(i));
        }
        if (v.getParcelamentoVenda() != null) {
            campoFormaPgto.setText(v.getParcelamentoVenda().getFormaDePgto());
            campoTotalVendaParc.setText(v.getParcelamentoVenda().getTotalVenda().toString());
            campoRestante.setText(v.getParcelamentoVenda().getRestante().toString());
            campoNumParcelas.setText(Integer.toString(v.getParcelamentoVenda().getNumParcelas()));
            campoDataEntrada.setText(mg.convDataSistema(v.getParcelamentoVenda().getDataEntrada()));
            campoDiasPrazo.setText(Integer.toString(v.getParcelamentoVenda().getDemaisParcelas()));
            for (int i = 0; i < v.getParcelamentoVenda().getParcelasVenda().size(); i++) {
                int parcela = v.getParcelamentoVenda().getParcelasVenda().get(i).getNumParcela();
                String data = mg.convDataSistema(v.getParcelamentoVenda().getParcelasVenda().get(i).getDataParcela());
                BigDecimal valor = v.getParcelamentoVenda().getParcelasVenda().get(i).getValorParcela();
                insereNaTabelaParc(parcela, data, valor);
            }
        }
    }

    public void insereCampoData() {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        campoDataEntrada.setText(sdf.format(cal.getTime()));

    }

    @Override
    public void mensagemCodClienteVenda(String msg) {
        campoCodCliente.setText(msg);
        campoCodCliente.requestFocus();
    }

    @Override
    public void mensagemCodProdutoVenda(String msg) {
        campoCodProduto.setText(msg);
        campoCodProduto.requestFocus();
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        campoObservacoes.setText(parcelamentoVendas.getFormaDePgto());

    }

    public void insereNaTabela(ItemVenda item) {
        valVendas.addRow(new String[]{
            Integer.toString(codItemSequecial),
            Integer.toString(item.getCodProduto()),
            item.getProduto(),
            item.getUnidade(),
            item.getQuantidade().toString(),
            f.format(item.getValorUnitario()),
            f.format(item.getValorTotal())});
    }

    public void insereNaTabelaParc(int parcela, String data, BigDecimal valor) {
        valParcelamento.addRow(new String[]{
            Integer.toString(parcela),
            data,
            f.format((valor))});
    }

    public ItemVenda coletaDadosCampos() {
        String valorTotal = campoValorTotal.getText();
        itemVenda = new ItemVenda(
                codItemSequecial,
                Integer.parseInt(campoCodProduto.getText()),
                campoNomeProduto.getText(),
                campoUnidade.getText(),
                mg.convValorBanco(campoQuantidade.getText()),
                mg.convValorBanco(campoValorUnitario.getText()),
                mg.convValorBanco(valorTotal),
                venda);
        return itemVenda;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotaoDesconto = new javax.swing.ButtonGroup();
        abaParcelamento = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        labelData = new javax.swing.JLabel();
        campoCod = new javax.swing.JTextField();
        campoData = new javax.swing.JTextField();
        campoCodCliente = new javax.swing.JTextField();
        labelCliente = new javax.swing.JLabel();
        campoNomeCliente = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
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
        labelUnidade = new javax.swing.JLabel();
        campoUnidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        campoTotalProdutos = new JNumberFormatField();
        labelDesconto = new javax.swing.JLabel();
        campoDesconto = new JNumberFormatField();
        painelTipoDesconto = new javax.swing.JPanel();
        BotaoRadioNenhum = new javax.swing.JRadioButton();
        botaoRadioPorcentagem = new javax.swing.JRadioButton();
        botaoRadioValor = new javax.swing.JRadioButton();
        labelAcrescimo = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        campoAcrescimo = new JNumberFormatField();
        campoTotalVenda = new JNumberFormatField();
        labelObservacoes = new javax.swing.JLabel();
        painelMotivoCrescimo = new javax.swing.JPanel();
        labelMotivoAcrescimo = new javax.swing.JLabel();
        campoObservacao1 = new javax.swing.JScrollPane();
        campoMotivoAcrescimo = new javax.swing.JTextArea();
        campoObservacao = new javax.swing.JScrollPane();
        campoObservacoes = new javax.swing.JTextArea();
        labelTotalEmCaixa = new javax.swing.JLabel();
        campoEntrada = new JNumberFormatField();
        LabelEntrada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        campoFormaPgto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaParcelamento = new javax.swing.JTable();
        campoNumParcelas = new javax.swing.JTextField();
        campoTotalVendaParc = new JNumberFormatField();
        campoRestante = new JNumberFormatField();
        labelRestante = new javax.swing.JLabel();
        botaoParcelar = new javax.swing.JButton();
        labelNparcelas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoDataEntrada = new javax.swing.JTextField();
        campoDiasPrazo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        labelFormaPgto = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();
        botaoGravarVenda = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Vendas");
        setName("Cadastro de Caixas"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelData.setText("Data:");

        campoCod.setEditable(false);
        campoCod.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCod.setEnabled(false);

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoData.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataKeyPressed(evt);
            }
        });

        campoCodCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodClienteActionPerformed(evt);
            }
        });
        campoCodCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodClienteKeyPressed(evt);
            }
        });

        labelCliente.setText("Cliente:");

        campoNomeCliente.setEditable(false);
        campoNomeCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoNomeCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeCliente.setEnabled(false);

        labelCodigo.setText("Código:");

        painelDadosDoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Inserir Produtos"));

        labelProduto.setText("Produto:");

        labelQuantidade.setText("Quantidade:");

        labelValor.setText("Valor Unitário:");

        labelValorTotal.setText("Valor Total:");

        campoValorTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorTotalKeyPressed(evt);
            }
        });

        campoValorUnitario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorUnitario.setEnabled(false);
        campoValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorUnitarioKeyPressed(evt);
            }
        });

        campoQuantidade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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

        campoCodProduto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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
        campoEstoqueQuantidade.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        campoEstoqueValorVenda.setBackground(new java.awt.Color(240, 240, 240));
        campoEstoqueValorVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));

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

        labelUnidade.setText("Unidade:");

        campoUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUnidadeActionPerformed(evt);
            }
        });
        campoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoUnidadeKeyPressed(evt);
            }
        });

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
                                .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUnidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addComponent(labelValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addComponent(labelValorTotal)
                                .addGap(18, 18, 18)
                                .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
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
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUnidade)
                            .addComponent(campoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item:", "Cod. Produto:", "Produto:", "Und:", "Quantidade:", "Valor Unit.:", "ValorTotal:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaVendas.setNextFocusableComponent(botaoIncluir);
        tabelaVendas.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabelaVendasAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tabelaVendas);
        if (tabelaVendas.getColumnModel().getColumnCount() > 0) {
            tabelaVendas.getColumnModel().getColumn(0).setMinWidth(40);
            tabelaVendas.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabelaVendas.getColumnModel().getColumn(0).setMaxWidth(40);
            tabelaVendas.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaVendas.getColumnModel().getColumn(3).setMinWidth(40);
            tabelaVendas.getColumnModel().getColumn(3).setPreferredWidth(40);
            tabelaVendas.getColumnModel().getColumn(3).setMaxWidth(40);
            tabelaVendas.getColumnModel().getColumn(4).setMinWidth(70);
            tabelaVendas.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabelaVendas.getColumnModel().getColumn(4).setMaxWidth(70);
            tabelaVendas.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaVendas.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        campoTotalProdutos.setEditable(false);
        campoTotalProdutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalProdutos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalProdutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoTotalProdutos.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelDesconto.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelDesconto.setText("Desconto:");

        campoDesconto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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

        labelAcrescimo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelAcrescimo.setText("Acréscimo:");

        labelTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotal.setText("Total:");

        campoAcrescimo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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

        campoTotalVenda.setEditable(false);
        campoTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoTotalVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalVenda.setEnabled(false);
        campoTotalVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalVendaActionPerformed(evt);
            }
        });

        labelObservacoes.setText("Observações:");

        painelMotivoCrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Acréscimo:"));

        labelMotivoAcrescimo.setText("Motivo:");

        campoMotivoAcrescimo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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
                .addComponent(campoObservacao1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        painelMotivoCrescimoLayout.setVerticalGroup(
            painelMotivoCrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMotivoCrescimoLayout.createSequentialGroup()
                .addComponent(labelMotivoAcrescimo)
                .addGap(10, 48, Short.MAX_VALUE))
            .addComponent(campoObservacao1)
        );

        campoObservacoes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoObservacoes.setMinimumSize(new java.awt.Dimension(0, 0));
        campoObservacao.setViewportView(campoObservacoes);

        labelTotalEmCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotalEmCaixa.setText("Total Produtos:");

        campoEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoEntradaFocusLost(evt);
            }
        });
        campoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEntradaActionPerformed(evt);
            }
        });
        campoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoEntradaKeyPressed(evt);
            }
        });

        LabelEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelEntrada.setText("Entrada:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelData)
                            .addComponent(labelCodigo)
                            .addComponent(labelCliente))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomeCliente))
                            .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(painelDadosDoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelObservacoes)
                                .addGap(155, 155, 155))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(campoObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelTipoDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelMotivoCrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelTotalEmCaixa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(campoAcrescimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelEntrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCliente)
                    .addComponent(campoCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDadosDoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelObservacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoObservacao)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTotalEmCaixa)
                                    .addComponent(campoTotalProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDesconto)))
                            .addComponent(painelTipoDesconto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelAcrescimo)
                                    .addComponent(campoAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelTotal)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(campoTotalVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LabelEntrada))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(painelMotivoCrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        abaParcelamento.addTab("Vendas", jPanel1);

        campoFormaPgto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoFormaPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormaPgtoActionPerformed(evt);
            }
        });
        campoFormaPgto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoFormaPgtoKeyPressed(evt);
            }
        });

        tabelaParcelamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PARC", "VENC", "VALOR"
            }
        ));
        jScrollPane2.setViewportView(tabelaParcelamento);

        campoNumParcelas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNumParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoNumParcelasKeyPressed(evt);
            }
        });

        campoTotalVendaParc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalVendaParc.setEnabled(false);
        campoTotalVendaParc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalVendaParcActionPerformed(evt);
            }
        });

        campoRestante.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoRestante.setEnabled(false);
        campoRestante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRestanteActionPerformed(evt);
            }
        });

        labelRestante.setText("Restante:");

        botaoParcelar.setText("Parcelar");
        botaoParcelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoParcelarActionPerformed(evt);
            }
        });

        labelNparcelas.setText("N.Parcelas:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Vencimento das Parcelas:"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Entrada:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel2.setText("Demais:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataEntrada = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoDataEntrada.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDataEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataEntradaActionPerformed(evt);
            }
        });
        campoDataEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataEntradaKeyPressed(evt);
            }
        });

        campoDiasPrazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDiasPrazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDiasPrazoKeyPressed(evt);
            }
        });

        jLabel3.setText("<- Quant. de Dias");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDataEntrada)
                    .addComponent(campoDiasPrazo, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoDiasPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        labelFormaPgto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelFormaPgto.setText("Forma de Pgto:");

        labelTotalVenda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalVenda.setText("Total Venda:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelRestante)
                            .addComponent(labelNparcelas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoNumParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoParcelar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelFormaPgto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoTotalVendaParc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 538, Short.MAX_VALUE))
                            .addComponent(campoFormaPgto))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFormaPgto)
                    .addComponent(campoFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalVenda)
                    .addComponent(campoTotalVendaParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRestante)
                    .addComponent(campoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNparcelas)
                    .addComponent(campoNumParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoParcelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        abaParcelamento.addTab("Parcelamento", jPanel2);

        botaoGravarVenda.setText("Gravar");
        botaoGravarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGravarVendaActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoGravarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelar)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoGravarVenda});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(abaParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoGravarVenda))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaVendasAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabelaVendasAncestorAdded
    }//GEN-LAST:event_tabelaVendasAncestorAdded

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        itemVenda = coletaDadosCampos();
        venda.getItemVenda().add(itemVenda);
        insereNaTabela(itemVenda);
        BigDecimal totalProduto = mg.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalItem = itemVenda.getValorTotal();
        campoTotalProdutos.setText(totalProduto.add(totalItem).toString());
        campoTotalVenda.setText(totalProduto.add(totalItem).toString());
        campoTotalVendaParc.setText(totalProduto.add(totalItem).toString());
        botaoIncluir.setEnabled(false);
        campoCodProduto.setText("");
        campoUnidade.setText("");
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
        int posicao = Integer.parseInt((String) tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 0));
        ((DefaultTableModel) tabelaVendas.getModel()).removeRow(tabelaVendas.getSelectedRow());
        for (int i = 0; i < venda.getItemVenda().size(); i++) {
            if (venda.getItemVenda().get(i).getSequenciaProduto() == posicao) {
                BigDecimal totalProduto = mg.convValorBanco(campoTotalProdutos.getText());
                BigDecimal totalItem = venda.getItemVenda().get(i).getValorTotal();
                campoTotalProdutos.setText(totalProduto.subtract(totalItem).toString());
                campoTotalVenda.setText(totalProduto.subtract(totalItem).toString());
                campoTotalVendaParc.setText(totalProduto.subtract(totalItem).toString());
                venda.getItemVenda().remove(i);
                JOptionPane.showMessageDialog(campoCodCliente, "Item Removido com Sucesso!");
                break;
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroVendas.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void campoCodProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodProdutoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int codItem = Integer.parseInt(campoCodProduto.getText());
            boolean existeProduto = false;
            if (!venda.getItemVenda().isEmpty()) {
                for (ItemVenda each : venda.getItemVenda()) {
                    if (codItem == each.getCodProduto()) {
                        existeProduto = true;
                        break;
                    }
                }
            }
            if (existeProduto == true) {
                JOptionPane.showMessageDialog(campoCodCliente, "Produto já incluído!");
                campoCodProduto.requestFocus();
            } else {
                produto = vendasCtrl.pesquisaProdutos(Integer.parseInt(campoCodProduto.getText()));
                campoNomeProduto.setText(produto.getDescricao());
                campoUnidade.setText(produto.getUnidade());
                campoEstoqueQuantidade.setText(produto.getQuantidade().toString());
                campoEstoqueValorVenda.setText(produto.getValorTotalVenda().toString());
                campoValorUnitario.setText(produto.getValorUnitarioVenda().toString());
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
            CadastroVendas.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void botaoGravarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGravarVendaActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataVenda = sdf.parse(campoData.getText());
            venda.setDataVenda(dataVenda);
            venda.setObservacoes(campoObservacoes.getText());
            campoDescontoFocusLost(null);
            campoAcrescimoFocusLost(null);
            venda.setValorTotalProdutos(mg.convValorBanco(campoTotalProdutos.getText()));
            vendasCtrl.cadastraVenda(venda);
            JOptionPane.showMessageDialog(null, "Venda Cadastrada com Sucesso!");
            //Gera um lançamento no Caixa
            BigDecimal entrada = mg.convValorBanco(campoEntrada.getText());
            BigDecimal zero = new BigDecimal("0");
            if (entrada.compareTo(zero) > 0) {
                if (JOptionPane.showConfirmDialog(null, "Deseja Gerar Lançamento no Caixa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    HistoricosDAO hd = new HistoricosDAO();
                    HistoricoPadrao hp = hd.getHistorico(1);
                    BigDecimal totalEntrada = mg.convValorBanco(campoEntrada.getText());
                    Lancamentos lancamento = new Lancamentos();
                    lancamento.setDataLancamento(venda.getDataVenda());
                    lancamento.setHistorico(hp);
                    lancamento.setObservacoes(hp.getNomeHistorico() + venda.getCod() + " do Cliente: " + venda.getCliente().getNome());
                    lancamento.setValorDebito(totalEntrada);
                    lancamento.setValorCredito(new BigDecimal("0.00"));
                    lancamentosCtrl.cadastrarLancamento(lancamento);
                    JOptionPane.showMessageDialog(null, "Lancamento Cadastrado com Sucesso!");
                }
            }

            if (JOptionPane.showConfirmDialog(null, "Deseja Imprimir o Comprovante da Venda?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                new RelatorioVendas(campoCod.getText());
            }
        } catch (ParseException ex) {
            Logger.getLogger(CadastroVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_botaoGravarVendaActionPerformed

    private void campoCodClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Clientes cliente = clientesCtrl.pesquisaCliente(Integer.parseInt(campoCodCliente.getText()));
            venda.setCliente(cliente);
            campoNomeCliente.setText(cliente.getNome());
            campoCodProduto.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaCliente.setVisible(true);
            consultaCliente.setLocationRelativeTo(null);
        }

    }//GEN-LAST:event_campoCodClienteKeyPressed

    private void campoDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoCodCliente.requestFocus();
        }
    }//GEN-LAST:event_campoDataKeyPressed

    private void campoValorUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorUnitarioKeyPressed

    }//GEN-LAST:event_campoValorUnitarioKeyPressed

    private void campoQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoQuantidadeFocusLost

        BigDecimal qtd = mg.convValorBanco(campoQuantidade.getText());
        if (qtd.compareTo(produto.getQuantidade()) > 0) {
            JOptionPane.showMessageDialog(campoCodCliente, "Quantidade Maior doque a Existente em Estoque!");
            campoQuantidade.requestFocus();
        } else {
            campoUnidade.requestFocus();
            BigDecimal cem = new BigDecimal(100);
            campoValorTotal.setText(qtd.multiply(produto.getValorUnitarioVenda()).divide(cem).toString());
        }

    }//GEN-LAST:event_campoQuantidadeFocusLost

    private void campoTotalVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTotalVendaActionPerformed

    private void BotaoRadioNenhumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRadioNenhumMouseClicked
        campoDesconto.setText("");
        campoDesconto.setEnabled(false);
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalVenda.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());
        campoTotalVendaParc.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());

    }//GEN-LAST:event_BotaoRadioNenhumMouseClicked
    private void botaoRadioPorcentagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioPorcentagemMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalVenda.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioPorcentagemMouseClicked
    private void botaoRadioValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioValorMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalVenda.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioValorMouseClicked
    private void campoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoEntrada.requestFocus();
        }
    }//GEN-LAST:event_campoDescontoKeyPressed
    private void campoDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescontoFocusLost
        BigDecimal desconto = mg.convValorBanco(campoDesconto.getText());
        BigDecimal totalProdutos = mg.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalVenda = mg.convValorBanco(campoTotalVenda.getText());
        if (botaoRadioPorcentagem.isSelected()) {
            BigDecimal divPorcent = desconto.divide(new BigDecimal("100"));
            BigDecimal totalDesconto = totalProdutos.multiply(divPorcent);
            campoTotalVenda.setText(totalProdutos.subtract(totalDesconto).toString());
            campoTotalVendaParc.setText(totalProdutos.subtract(totalDesconto).toString());

            totalVenda = totalProdutos.subtract(totalDesconto);
            venda.setTipoDesconto("Porcentagem");
        } else if (botaoRadioValor.isSelected()) {
            campoTotalVenda.setText(totalProdutos.subtract(desconto).toString());
            campoTotalVendaParc.setText(totalProdutos.subtract(desconto).toString());
            totalVenda = totalProdutos.subtract(desconto);
            venda.setTipoDesconto("Valor");
        } else {
            venda.setTipoDesconto("Nenhum");
        }
        venda.setValorDesconto(desconto);
        venda.setValorTotalVenda(totalVenda);

    }//GEN-LAST:event_campoDescontoFocusLost

    private void campoAcrescimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoAcrescimoFocusLost
        BigDecimal acrescimo = mg.convValorBanco(campoAcrescimo.getText());
        BigDecimal totalProdutos = mg.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalVenda = totalProdutos.add(acrescimo);
        campoTotalVenda.setText(totalVenda.toString());
        campoTotalVendaParc.setText(totalVenda.toString());
        venda.setValorAcrescimo(acrescimo);
        venda.setMotivoAcrescimo(campoMotivoAcrescimo.getText());
        venda.setValorTotalVenda(totalVenda);
    }//GEN-LAST:event_campoAcrescimoFocusLost

    private void campoMotivoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoAcrescimo.setEnabled(true);
            campoDesconto.setText("");
            campoDesconto.setEnabled(false);
            BotaoRadioNenhum.setSelected(true);
            campoAcrescimo.requestFocus();
            campoTotalVenda.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());
            campoTotalVenda.setText(mg.convValorBanco(campoTotalProdutos.getText()).toString());
        }
    }//GEN-LAST:event_campoMotivoAcrescimoKeyPressed

    private void campoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoEntrada.requestFocus();
        }
    }//GEN-LAST:event_campoAcrescimoKeyPressed

    private void campoDescontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescontoFocusGained
        campoMotivoAcrescimo.setText("");
    }//GEN-LAST:event_campoDescontoFocusGained

    private void campoCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodClienteActionPerformed

    private void campoFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormaPgtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFormaPgtoActionPerformed

    private void campoFormaPgtoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoFormaPgtoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoNumParcelas.requestFocus();
        }
    }//GEN-LAST:event_campoFormaPgtoKeyPressed

    private void campoNumParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumParcelasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoDataEntrada.requestFocus();
        }
    }//GEN-LAST:event_campoNumParcelasKeyPressed

    private void campoEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoEntradaFocusLost
        BigDecimal valorTotal = mg.convValorBanco(campoTotalVenda.getText());
        BigDecimal valorEntrada = mg.convValorBanco(campoEntrada.getText());
        campoRestante.setText(valorTotal.subtract(valorEntrada).toString());
    }//GEN-LAST:event_campoEntradaFocusLost

    private void campoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEntradaActionPerformed

    }//GEN-LAST:event_campoEntradaActionPerformed

    private void campoEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BigDecimal entrada = mg.convValorBanco(campoEntrada.getText());
            int compara = entrada.compareTo(new BigDecimal("0.00"));
            if (compara <= 0) {
                botaoGravarVenda.requestFocusInWindow();
            } else {
                abaParcelamento.setSelectedIndex(1);
                campoFormaPgto.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_campoEntradaKeyPressed

    private void campoTotalVendaParcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalVendaParcActionPerformed

    }//GEN-LAST:event_campoTotalVendaParcActionPerformed

    private void campoRestanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRestanteActionPerformed

    }//GEN-LAST:event_campoRestanteActionPerformed

    private void botaoParcelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoParcelarActionPerformed
        parcelamentoVendas = new ParcelamentoVendas();

        ((DefaultTableModel) tabelaParcelamento.getModel()).setRowCount(0);//zera as linhas da tabela pra receber o novo filtro
        Calendar cal = Calendar.getInstance();
        Date dataEntrada = mg.convDataBanco(campoDataEntrada.getText());
        cal.setTime(dataEntrada);
        int diasPrazo = Integer.parseInt(campoDiasPrazo.getText());
        BigDecimal valorParcela;
        BigDecimal restante = mg.convValorBanco(campoRestante.getText());
        int parcelas = Integer.parseInt(campoNumParcelas.getText());
        valorParcela = restante.divide(new BigDecimal(parcelas), BigDecimal.ROUND_UP);
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        parcelamentoVendas.setDataEntrada(dataEntrada);
        parcelamentoVendas.setTotalVenda(mg.convValorBanco(campoTotalVendaParc.getText()));
        parcelamentoVendas.setEntrada(mg.convValorBanco(campoEntrada.getText()));
        parcelamentoVendas.setFormaDePgto(campoFormaPgto.getText());
        parcelamentoVendas.setRestante(restante);
        parcelamentoVendas.setNumParcelas(parcelas);
        parcelamentoVendas.setDemaisParcelas(diasPrazo);
        parcelamentoVendas.setParcelasVenda(new ArrayList());

        for (int i = 0; i < parcelas; i++) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + diasPrazo);
            if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 2);
            }
            ParcelasVenda pv = new ParcelasVenda(i + 1, cal.getTime(), valorParcela, parcelamentoVendas);
            parcelamentoVendas.getParcelasVenda().add(pv);
            insereNaTabelaParc(i + 1, sdf.format(cal.getTime()), valorParcela);
        }
        venda.setParcelamentoVenda(parcelamentoVendas);

    }//GEN-LAST:event_botaoParcelarActionPerformed

    private void campoDataEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataEntradaActionPerformed

    private void campoDataEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataEntradaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoDiasPrazo.requestFocus();
        }
    }//GEN-LAST:event_campoDataEntradaKeyPressed

    private void campoDiasPrazoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDiasPrazoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoParcelar.requestFocus();
        }
    }//GEN-LAST:event_campoDiasPrazoKeyPressed

    private void campoQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoQuantidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoUnidade.requestFocus();
        }
    }//GEN-LAST:event_campoQuantidadeKeyPressed

    private void campoUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUnidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUnidadeActionPerformed

    private void campoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUnidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoValorTotal.requestFocus();
        }
    }//GEN-LAST:event_campoUnidadeKeyPressed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new CadastroVendas().setVisible(true);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(CadastroVendas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BotaoRadioNenhum;
    private javax.swing.JLabel LabelEntrada;
    private javax.swing.JTabbedPane abaParcelamento;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoGravarVenda;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JButton botaoParcelar;
    private javax.swing.JRadioButton botaoRadioPorcentagem;
    private javax.swing.JRadioButton botaoRadioValor;
    private javax.swing.JTextField campoAcrescimo;
    private javax.swing.JTextField campoCod;
    private javax.swing.JTextField campoCodCliente;
    private javax.swing.JTextField campoCodProduto;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDataEntrada;
    private javax.swing.JTextField campoDesconto;
    private javax.swing.JTextField campoDiasPrazo;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JTextField campoEstoqueQuantidade;
    private javax.swing.JTextField campoEstoqueValorVenda;
    private javax.swing.JTextField campoFormaPgto;
    private javax.swing.JTextArea campoMotivoAcrescimo;
    private javax.swing.JTextField campoNomeCliente;
    private javax.swing.JTextField campoNomeProduto;
    private javax.swing.JTextField campoNumParcelas;
    private javax.swing.JScrollPane campoObservacao;
    private javax.swing.JScrollPane campoObservacao1;
    private javax.swing.JTextArea campoObservacoes;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoRestante;
    private javax.swing.JTextField campoTotalProdutos;
    private javax.swing.JTextField campoTotalVenda;
    private javax.swing.JTextField campoTotalVendaParc;
    private javax.swing.JTextField campoUnidade;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.JTextField campoValorUnitario;
    private javax.swing.ButtonGroup grupoBotaoDesconto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAcrescimo;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelEstoqueValorVenda;
    private javax.swing.JLabel labelFormaPgto;
    private javax.swing.JLabel labelMotivoAcrescimo;
    private javax.swing.JLabel labelNparcelas;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelRestante;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotalEmCaixa;
    private javax.swing.JLabel labelTotalVenda;
    private javax.swing.JLabel labelUnidade;
    private javax.swing.JLabel labelValor;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JPanel painelDadosDoProduto;
    private javax.swing.JPanel painelEstoque;
    private javax.swing.JPanel painelMotivoCrescimo;
    private javax.swing.JPanel painelTipoDesconto;
    private javax.swing.JTable tabelaParcelamento;
    private javax.swing.JTable tabelaVendas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
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

}
