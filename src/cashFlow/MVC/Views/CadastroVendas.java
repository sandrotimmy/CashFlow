package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.ClientesDAO;
import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.DAO.ItemVendaDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.DAO.ParcelamentoVendasDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Compras;
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

    private Lancamentos lancamento;
    private LancamentosDAO persist;
    private final VendasDAO persistVenda;
    private ClientesDAO persistCliente;
    private ItemVendaDAO persistItemVenda;
    private ProdutosDAO persistProdutos;
    private final NumberFormat f;
    private ArrayList<ItemVenda> ListaItens;
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
    private ParcelamentoVendas parcelamentoVendas;
    private ParcelamentoVendasDAO persistParcelamentoVendas;
    private ArrayList<ParcelasVenda> listaParcelas;
    private MetodosGerais mg;

    public CadastroVendas(Vendas consultaVenda) {
        initComponents();
        valVendas = (DefaultTableModel) tabelaVendas.getModel();
        f = NumberFormat.getCurrencyInstance();
        this.persistVenda = new VendasDAO();
        this.mg = new MetodosGerais();
        listaParcelas = new ArrayList();
        exibeDados(consultaVenda);
        habilitaDesabilitaCampos(false);
        botaoCancelar.requestFocus();
        pc = new PassaCamposComEnter();
        valParcelamento = (DefaultTableModel) tabelaParcelamento.getModel();
    }

    public CadastroVendas() throws SQLException, ParseException {
        initComponents();
        valVendas = (DefaultTableModel) tabelaVendas.getModel();
        valParcelamento = (DefaultTableModel) tabelaParcelamento.getModel();
        this.persist = new LancamentosDAO();
        this.lancamento = new Lancamentos();
        this.persistVenda = new VendasDAO();
        this.persistCliente = new ClientesDAO();
        this.persistProdutos = new ProdutosDAO();
        this.persistItemVenda = new ItemVendaDAO();
        codProxVenda = persistVenda.getProximaVenda();
        this.venda = new Vendas(codProxVenda);
        consultaCliente = new ConsultaCliente();
        consultaProduto = new ConsultaProdutos();
        carregaDados();
        ListaItens = new ArrayList();
        produto = new Produtos();
        codItemSequecial = 1;
        f = NumberFormat.getCurrencyInstance();
        persistParcelamentoVendas = new ParcelamentoVendasDAO();
        listaParcelas = new ArrayList();

    }

    public void carregaDados() {
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
        campoDesconto.setEnabled(false);
        consultaCliente.setListener(this);
        consultaProduto.setListener(this);
        campoCod.setText(Integer.toString(codProxVenda));
        campoData.requestFocus();
        campoData.setText(mg.getDataAtual());//pega a data do Computador
        campoData.setCaretPosition(0);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaVendas.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabelaVendas.getColumnModel().getColumn(4).setCellRenderer(direita);
        tabelaVendas.getColumnModel().getColumn(5).setCellRenderer(direita);
        persistParcelamentoVendas = new ParcelamentoVendasDAO();
        listaParcelas = new ArrayList();
        insereCampoData();
    }

    public void habilitaDesabilitaCampos(boolean campos) {
        campoData.setEnabled(campos);
        campoCodCliente.setEnabled(campos);
        campoCodProduto.setEnabled(campos);
        campoQuantidade.setEnabled(campos);
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
        campoData.setText(persistVenda.convDataSistema(v.getDataVenda()));
        campoCodCliente.setText(Integer.toString(v.getCliente().getCod()));
        campoNomeCliente.setText(v.getCliente().getNome());
        campoObservacoes.setText(v.getObservacoes());
        campoMotivoAcrescimo.setText(v.getMotivoAcrescimo());
        campoTotalProdutos.setText(f.format(v.getValorTotalProdutos()));
        campoDesconto.setText(f.format(v.getValorDesconto()));
        campoAcrescimo.setText(f.format(v.getValorAcrescimo()));
        campoTotalVenda.setText(f.format(v.getValorTotalVenda()));
        for (int i = 0; i < v.getItemVenda().size(); i++) {
            insereNaTabela(v.getItemVenda().get(i));
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
        campoDataPrimParcela.setText(sdf.format(cal.getTime()));

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
            item.getQuantidade().toString(),
            f.format(item.getValorUnitario()),
            f.format(item.getValorTotal())});
    }

    public void insereNaTabelaParc(int qtdParcela, String data, BigDecimal valor) {
        valParcelamento.addRow(new String[]{
            Integer.toString(qtdParcela),
            data,
            f.format(valor)});
    }

    public ItemVenda coletaDadosCampos() {
        itemVenda = new ItemVenda(
                codItemSequecial,
                Integer.parseInt(campoCodProduto.getText()),
                campoNomeProduto.getText(),
                venda.convValorBanco(campoQuantidade.getText()),
                venda.convValorBanco(campoValorUnitario.getText()),
                venda.convValorBanco(campoValorTotal.getText()),
                venda);
        return itemVenda;
    }

    @SuppressWarnings("unchecked")
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        campoTotalProdutos = new JNumberFormatField();
        labelDesconto = new javax.swing.JLabel();
        botaoGravarVenda = new javax.swing.JButton();
        campoDesconto = new JNumberFormatField();
        painelTipoDesconto = new javax.swing.JPanel();
        BotaoRadioNenhum = new javax.swing.JRadioButton();
        botaoRadioPorcentagem = new javax.swing.JRadioButton();
        botaoRadioValor = new javax.swing.JRadioButton();
        labelAcrescimo = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        campoAcrescimo = new JNumberFormatField();
        botaoCancelar = new javax.swing.JButton();
        campoTotalVenda = new JNumberFormatField();
        labelObservacoes = new javax.swing.JLabel();
        painelMotivoCrescimo = new javax.swing.JPanel();
        labelMotivoAcrescimo = new javax.swing.JLabel();
        campoObservacao1 = new javax.swing.JScrollPane();
        campoMotivoAcrescimo = new javax.swing.JTextArea();
        campoObservacao = new javax.swing.JScrollPane();
        campoObservacoes = new javax.swing.JTextArea();
        labelTotalEmCaixa = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        campoFormaPgto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaParcelamento = new javax.swing.JTable();
        campoNumParcelas = new javax.swing.JTextField();
        campoEntrada = new JNumberFormatField();
        campoTotalVendaParc = new JNumberFormatField();
        LabelEntrada = new javax.swing.JLabel();
        campoRestante = new JNumberFormatField();
        labelRestante = new javax.swing.JLabel();
        botaoParcelar = new javax.swing.JButton();
        labelNparcelas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoDataPrimParcela = new javax.swing.JTextField();
        campoDiasPrazo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        labelFormaPgto = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();

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
        campoValorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoValorTotalFocusGained(evt);
            }
        });
        campoValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoValorTotalKeyPressed(evt);
            }
        });

        campoValorUnitario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorUnitarioFocusLost(evt);
            }
        });
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
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addComponent(labelValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosDoProdutoLayout.createSequentialGroup()
                                .addComponent(labelValorTotal)
                                .addGap(18, 18, 18)
                                .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
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
            tabelaVendas.getColumnModel().getColumn(3).setMinWidth(70);
            tabelaVendas.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaVendas.getColumnModel().getColumn(3).setMaxWidth(70);
            tabelaVendas.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(4).setMaxWidth(80);
            tabelaVendas.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        campoTotalProdutos.setEditable(false);
        campoTotalProdutos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalProdutos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalProdutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoTotalProdutos.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelDesconto.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelDesconto.setText("Desconto:");

        botaoGravarVenda.setText("Gravar");
        botaoGravarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGravarVendaActionPerformed(evt);
            }
        });

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

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
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
            .addComponent(labelMotivoAcrescimo)
            .addComponent(campoObservacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        campoObservacoes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoObservacoes.setMinimumSize(new java.awt.Dimension(0, 0));
        campoObservacao.setViewportView(campoObservacoes);

        labelTotalEmCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotalEmCaixa.setText("Total Produtos:");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(botaoGravarVenda)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botaoCancelar))))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoGravarVenda});

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelObservacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoObservacao))
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTotal)))
                            .addComponent(painelMotivoCrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoCancelar)
                            .addComponent(botaoGravarVenda))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        abaParcelamento.addTab("Vendas", jPanel1);

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

        campoNumParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoNumParcelasKeyPressed(evt);
            }
        });

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

        campoTotalVendaParc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalVendaParc.setEnabled(false);
        campoTotalVendaParc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalVendaParcActionPerformed(evt);
            }
        });

        LabelEntrada.setText("Estrada:");

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
            campoDataPrimParcela = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoDataPrimParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataPrimParcelaActionPerformed(evt);
            }
        });
        campoDataPrimParcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataPrimParcelaKeyPressed(evt);
            }
        });

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
                    .addComponent(campoDataPrimParcela)
                    .addComponent(campoDiasPrazo, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoDataPrimParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelEntrada)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelFormaPgto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campoEntrada, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoTotalVendaParc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEntrada))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        abaParcelamento.addTab("Parcelamento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(abaParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaVendasAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabelaVendasAncestorAdded
    }//GEN-LAST:event_tabelaVendasAncestorAdded

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        itemVenda = coletaDadosCampos();
        ListaItens.add(itemVenda);
        insereNaTabela(itemVenda);
        BigDecimal totalProduto = venda.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalItem = itemVenda.getValorTotal();
        campoTotalProdutos.setText(f.format(totalProduto.add(totalItem)));
        campoTotalVenda.setText(f.format(totalProduto.add(totalItem)));
        campoTotalVendaParc.setText(f.format(totalProduto.add(totalItem)));
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
        int posicao = Integer.parseInt((String) tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 0));
        ((DefaultTableModel) tabelaVendas.getModel()).removeRow(tabelaVendas.getSelectedRow());
        for (int i = 0; i < ListaItens.size(); i++) {
            if (ListaItens.get(i).getSequenciaProduto() == posicao) {
                BigDecimal totalProduto = venda.convValorBanco(campoTotalProdutos.getText());
                BigDecimal totalItem = ListaItens.get(i).getValorTotal();
                campoTotalProdutos.setText(f.format(totalProduto.subtract(totalItem)));
                campoTotalVenda.setText(f.format(totalProduto.subtract(totalItem)));
                campoTotalVendaParc.setText(f.format(totalProduto.subtract(totalItem)));
                ListaItens.remove(i);
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
            for (ItemVenda each : ListaItens) {
                if (codItem == each.getCodProduto()) {
                    existeProduto = true;
                    break;
                }
            }
            if (existeProduto == true) {
                JOptionPane.showMessageDialog(campoCodCliente, "Produto já incluído!");
                campoCodProduto.requestFocus();
            } else {
                produto = persistProdutos.pesquisaProdutos(Integer.parseInt(campoCodProduto.getText()));
                campoNomeProduto.setText(produto.getDescricao());
                campoEstoqueQuantidade.setText(f.format(produto.getQuantidade()));
                campoEstoqueValorVenda.setText(f.format(produto.getValorTotalVenda()));
                campoValorUnitario.setText(f.format(produto.getValorUnitarioVenda()));
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
            venda.setItemVenda(ListaItens);
            campoDescontoFocusLost(null);
            campoAcrescimoFocusLost(null);
            persistVenda.cadastraVenda(venda);
            ListaItens.stream().forEach((each) -> {
                Produtos atuProduto = persistProdutos.pesquisaProdutos(each.getCodProduto());
                atuProduto.setQuantidade(atuProduto.getQuantidade().subtract(each.getQuantidade()));
                atuProduto.setValorTotal(atuProduto.getQuantidade().multiply(atuProduto.getValorUnitario()));
                atuProduto.setValorTotalVenda(atuProduto.getQuantidade().multiply(atuProduto.getValorUnitarioVenda()));
                persistProdutos.atualizaProduto(atuProduto);
                persistItemVenda.cadastraItemVenda(each);
            });
            //Gera um lançamento no Caixa
            if (JOptionPane.showConfirmDialog(null, "Deseja Gerar Lançamento no Caixa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                HistoricosDAO hd = new HistoricosDAO();
                HistoricoPadrao hp = hd.getHistorico(1);
                BigDecimal totalEntrada = persistParcelamentoVendas.convValorBanco(
                campoEntrada.getText());
                lancamento.setCod(persist.getProximoCodLancamento());
                lancamento.setDataLancamento(venda.getDataVenda());
                lancamento.setHistorico(hp);
                lancamento.setObservacoes(hp.getNomeHistorico() + venda.getCod() + " do Cliente: " + venda.getCliente().getNome());
                lancamento.setValorDebito(totalEntrada);
                lancamento.setValorCredito(new BigDecimal("0.00"));
                persist.cadastrarLancamento(lancamento);
            }
        } catch (ParseException ex) {
            Logger.getLogger(CadastroVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Venda efetuada com Sucesso!");
        this.dispose();
    }//GEN-LAST:event_botaoGravarVendaActionPerformed

    private void campoValorTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorTotalFocusGained

    }//GEN-LAST:event_campoValorTotalFocusGained

    private void campoCodClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Clientes cliente = persistCliente.pesquisaCliente(Integer.parseInt(campoCodCliente.getText()));
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

    private void campoValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorUnitarioFocusLost

    }//GEN-LAST:event_campoValorUnitarioFocusLost

    private void campoValorUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoValorUnitarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoValorTotal.setText(f.format(new BigDecimal(campoQuantidade.getText().replaceAll("\\.", "").replaceAll(",", "\\.")).multiply(new BigDecimal(campoValorUnitario.getText().replaceAll("\\.", "").replaceAll(",", "\\.")))));
            campoValorTotal.requestFocus();
        }
    }//GEN-LAST:event_campoValorUnitarioKeyPressed

    private void campoQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoQuantidadeFocusLost
        BigDecimal qtd = new BigDecimal(campoQuantidade.getText().replaceAll("\\.", "").replaceAll(",", "\\."));
        if (qtd.compareTo(produto.getQuantidade()) > 0) {
            JOptionPane.showMessageDialog(campoCodCliente, "Quantidade Maior doque a Existente em Estoque!");
            campoQuantidade.requestFocus();
        } else {
            campoValorUnitario.requestFocus();
            campoValorTotal.setText(f.format(qtd.multiply(produto.getValorUnitarioVenda())));
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
        campoTotalVenda.setText(f.format(venda.convValorBanco(campoTotalProdutos.getText())));
        campoTotalVendaParc.setText(f.format(venda.convValorBanco(campoTotalProdutos.getText())));
    }//GEN-LAST:event_BotaoRadioNenhumMouseClicked
    private void botaoRadioPorcentagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioPorcentagemMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalVenda.setText(f.format(venda.convValorBanco(campoTotalProdutos.getText())));
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioPorcentagemMouseClicked
    private void botaoRadioValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioValorMouseClicked
        campoDesconto.setEnabled(true);
        campoDesconto.setText("");
        campoMotivoAcrescimo.setText("");
        campoAcrescimo.setText("");
        campoAcrescimo.setEnabled(false);
        campoTotalVenda.setText(f.format(venda.convValorBanco(campoTotalProdutos.getText())));
        campoDesconto.requestFocus();
    }//GEN-LAST:event_botaoRadioValorMouseClicked
    private void campoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoGravarVenda.requestFocus();
        }
    }//GEN-LAST:event_campoDescontoKeyPressed
    private void campoDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDescontoFocusLost
        BigDecimal desconto = venda.convValorBanco(campoDesconto.getText());
        BigDecimal totalProdutos = venda.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalVenda = venda.convValorBanco(campoTotalVenda.getText());
        if (botaoRadioPorcentagem.isSelected()) {
            BigDecimal divPorcent = desconto.divide(new BigDecimal("100"));
            BigDecimal totalDesconto = totalProdutos.multiply(divPorcent);
            campoTotalVenda.setText(f.format(totalProdutos.subtract(totalDesconto)));
            campoTotalVendaParc.setText(f.format(totalProdutos.subtract(totalDesconto)));
            totalVenda = totalProdutos.subtract(totalDesconto);
            venda.setTipoDesconto("Porcentagem");
        } else if (botaoRadioValor.isSelected()) {
            campoTotalVenda.setText(f.format(totalProdutos.subtract(desconto)));
            campoTotalVendaParc.setText(f.format(totalProdutos.subtract(desconto)));
            totalVenda = totalProdutos.subtract(desconto);
            venda.setTipoDesconto("Valor");
        } else {
            venda.setTipoDesconto("Nenhum");
        }
        venda.setValorTotalProdutos(totalProdutos);
        venda.setValorDesconto(desconto);
        venda.setValorTotalVenda(totalVenda);

    }//GEN-LAST:event_campoDescontoFocusLost

    private void campoAcrescimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoAcrescimoFocusLost
        BigDecimal acrescimo = venda.convValorBanco(campoAcrescimo.getText());
        BigDecimal totalProdutos = venda.convValorBanco(campoTotalProdutos.getText());
        BigDecimal totalVenda = totalProdutos.add(acrescimo);
        campoTotalVenda.setText(f.format(totalVenda));
        campoTotalVendaParc.setText(f.format(totalVenda));
        venda.setValorAcrescimo(acrescimo);
        venda.setMotivoAcrescimo(campoMotivoAcrescimo.getText());
        venda.setValorTotalVenda(totalVenda);
    }//GEN-LAST:event_campoAcrescimoFocusLost

    private void campoMotivoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoAcrescimo.setEnabled(rootPaneCheckingEnabled);
            campoDesconto.setText("");
            campoDesconto.setEnabled(false);
            BotaoRadioNenhum.setSelected(true);
            campoAcrescimo.requestFocus();
            campoTotalVenda.setText(f.format(venda.convValorBanco(campoTotalProdutos.getText())));
        }
    }//GEN-LAST:event_campoMotivoAcrescimoKeyPressed

    private void campoAcrescimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoAcrescimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoGravarVenda.requestFocus();
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
            campoEntrada.requestFocus();
        }
    }//GEN-LAST:event_campoFormaPgtoKeyPressed

    private void campoNumParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumParcelasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoDataPrimParcela.requestFocus();
        }
    }//GEN-LAST:event_campoNumParcelasKeyPressed

    private void campoEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoEntradaFocusLost
        BigDecimal valorTotal = persistParcelamentoVendas.convValorBanco(campoTotalVenda.getText());
        BigDecimal valorEntrada = persistParcelamentoVendas.convValorBanco(campoEntrada.getText());
        campoRestante.setText(valorTotal.subtract(valorEntrada).toString());
    }//GEN-LAST:event_campoEntradaFocusLost

    private void campoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEntradaActionPerformed

    private void campoEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoNumParcelas.requestFocus();
        }
    }//GEN-LAST:event_campoEntradaKeyPressed

    private void campoTotalVendaParcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalVendaParcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTotalVendaParcActionPerformed

    private void campoRestanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRestanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRestanteActionPerformed

    private void botaoParcelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoParcelarActionPerformed
        ((DefaultTableModel) tabelaParcelamento.getModel()).setRowCount(0);//zera as linhas da tabela pra receber o novo filtro
        Calendar cal = Calendar.getInstance();
        String data = campoDataPrimParcela.getText();
        Date date = persistParcelamentoVendas.convDataBanco(data);
        cal.setTime(date);
        int diasPrazo = Integer.parseInt(campoDiasPrazo.getText());
        BigDecimal valorParcela;
        BigDecimal restante = persistParcelamentoVendas.convValorBanco(campoRestante.getText());
        int parcelas = Integer.parseInt(campoNumParcelas.getText());
        valorParcela = restante.divide(new BigDecimal(parcelas));
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < parcelas; i++) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + diasPrazo);
            if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 2);
            }
            listaParcelas.add(new ParcelasVenda(i + 1, cal.getTime(), valorParcela, parcelamentoVendas));
            insereNaTabelaParc(i + 1, sdf.format(cal.getTime()), valorParcela);
        }
    }//GEN-LAST:event_botaoParcelarActionPerformed

    private void campoDataPrimParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataPrimParcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataPrimParcelaActionPerformed

    private void campoDataPrimParcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataPrimParcelaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoDiasPrazo.requestFocus();
        }
    }//GEN-LAST:event_campoDataPrimParcelaKeyPressed

    private void campoDiasPrazoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDiasPrazoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoParcelar.requestFocus();
        }
    }//GEN-LAST:event_campoDiasPrazoKeyPressed

    private void campoQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoQuantidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoValorUnitario.requestFocus();
        }
    }//GEN-LAST:event_campoQuantidadeKeyPressed
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
    private javax.swing.JTextField campoDataPrimParcela;
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
