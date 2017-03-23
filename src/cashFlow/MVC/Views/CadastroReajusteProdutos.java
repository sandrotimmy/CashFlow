package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.DAO.ReajusteProdutosDAO;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.ReajusteProdutos;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CadastroReajusteProdutos extends javax.swing.JDialog implements InterfaceListener {

    private final ProdutosDAO persist;
    private final ReajusteProdutosDAO persistReajuste;
    private ReajusteProdutos reajusteProdutos;
    private final ArrayList<Produtos> listaProdutos;
    private int posicao;//controla a posicao do objeto na tela
    private final NumberFormat f;
    private ConsultaProdutos cp;
    private DefaultTableModel val;
    private ArrayList<ReajusteProdutos> listaReajustes;
    SimpleDateFormat sdf;

    public CadastroReajusteProdutos(java.awt.Frame parent, boolean modal) throws SQLException {
        initComponents();
        cp = new ConsultaProdutos();
        cp.setListener(this);
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoCadastrar.requestFocus();
        setModal(true);
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        campoReajuste.setFocusTraversalKeysEnabled(false);
        campoMotivo.setFocusTraversalKeysEnabled(false);
        reajusteProdutos = new ReajusteProdutos();
        val = (DefaultTableModel) tabelaReajustes.getModel();
        this.persist = new ProdutosDAO();
        this.persistReajuste = new ReajusteProdutosDAO();
        f = NumberFormat.getCurrencyInstance();
        this.listaProdutos = persist.getListaProdutos();
        carregaPrimeiroProduto();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaReajustes.getColumnModel().getColumn(4).setCellRenderer(direita);
        tabelaReajustes.getColumnModel().getColumn(5).setCellRenderer(direita);
        tabelaReajustes.getColumnModel().getColumn(6).setCellRenderer(direita);
    }

    @Override
    public void mensagemCodProdutoReajuste(String msg) {
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

    private void carregaPrimeiroProduto() throws SQLException {
        if (!listaProdutos.isEmpty()) {
            exibeDados(listaProdutos.get(listaProdutos.size() - 1));
            posicao = listaProdutos.size() - 1;
            //bloqueia todos os campos e seta o focus do botao Cadastrar
            campoData.setEnabled(false);
            campoMotivo.setEnabled(false);
            botaoRadioPorcentagem.setEnabled(false);
            botaoRadioValor.setEnabled(false);
            botaoRadioAdicionar.setEnabled(false);
            campoReajuste.setEnabled(false);
            botaoCadastrar.requestFocus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Produto Cadastrado!");
            this.dispose();
        }
    }

    private void exibeDados(Produtos produto) {
        ((DefaultTableModel) tabelaReajustes.getModel()).setRowCount(0);//zera as linhas da tabela pra receber o novo filtro
        campoCodigo.setText(produto.getCod().toString());
        campoDescricao.setText(produto.getDescricao());
        campoEstoqueQuantidade.setText(f.format(produto.getQuantidade()));
        campoEstoqueValorUnitario.setText(f.format(produto.getValorUnitarioVenda()));
        campoEstoqueValorVenda.setText(f.format(produto.getValorTotalVenda()));
        listaReajustes = persistReajuste.getListaReajuste(produto.getCod());
        for (ReajusteProdutos each : listaReajustes) {
            insereNaTabela(each);//carrega os reajustes do banco na tabela de exibição
        }
    }

    public void insereNaTabela(ReajusteProdutos reajuste) {
        String valorReajuste = f.format(reajuste.getReajuste());
        if (reajuste.getTipoReajuste().equalsIgnoreCase("porcentagem")) {
            valorReajuste = valorReajuste.replaceAll("R", "").replaceAll("\\$", "") + "%";
        }
        val.addRow(new String[]{
            Integer.toString(reajuste.getCod()),
            persistReajuste.convDataSistema(reajuste.getDataReajuste()),
            reajuste.getMotivo(),
            reajuste.getTipoReajuste(),
            f.format(reajuste.getValorAntigo()),
            valorReajuste,
            f.format(reajuste.getValorReajustado())});
    }

    public ReajusteProdutos coletaDadosCampos(Produtos produto, String tipoReajuste) {
        BigDecimal valorAntigo = reajusteProdutos.convValorBanco(campoEstoqueValorUnitario.getText());
        BigDecimal valorReajuste = reajusteProdutos.convValorBanco(campoReajuste.getText());
        BigDecimal valorReajustado = reajusteProdutos.convValorBanco(campoValorReajustado.getText());
        reajusteProdutos = new ReajusteProdutos(
                persistReajuste.convDataBanco(campoData.getText()),
                campoMotivo.getText(),
                tipoReajuste,
                valorAntigo,
                valorReajuste,
                valorReajustado,
                produto
        );
        return reajusteProdutos;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoesTipoReajuste = new javax.swing.ButtonGroup();
        botaoCadastrar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        campoCodigo = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        BotaoListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        labelCodigo = new javax.swing.JLabel();
        labelProduto = new javax.swing.JLabel();
        campoDescricao = new javax.swing.JTextField();
        painelEstoque = new javax.swing.JPanel();
        labelEstoqueQuantidade = new javax.swing.JLabel();
        labelEstoqueValorVenda = new javax.swing.JLabel();
        campoEstoqueQuantidade = new JNumberFormatField();
        campoEstoqueValorVenda = new JNumberFormatField();
        labelValorUnitario = new javax.swing.JLabel();
        campoEstoqueValorUnitario = new JNumberFormatField();
        painelTipoReajuste = new javax.swing.JPanel();
        botaoRadioPorcentagem = new javax.swing.JRadioButton();
        botaoRadioValor = new javax.swing.JRadioButton();
        botaoRadioAdicionar = new javax.swing.JRadioButton();
        labelReajuste = new javax.swing.JLabel();
        campoReajuste = new JNumberFormatField();
        painelReajustes = new javax.swing.JScrollPane();
        tabelaReajustes = new javax.swing.JTable();
        campoValorReajustado = new JNumberFormatField();
        labelValorReajustado = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        labelMotivo = new javax.swing.JLabel();
        campoMotivo = new javax.swing.JTextField();
        labelPorcentagem = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reajuste de Produtos");

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);
        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

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

        labelCodigo.setText("Código:");

        labelProduto.setText("Produto:");

        campoDescricao.setEditable(false);
        campoDescricao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDescricao.setEnabled(false);

        painelEstoque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Em Estoque:"));

        labelEstoqueQuantidade.setText("Quantidade:");

        labelEstoqueValorVenda.setText("Valor de Venda:");

        campoEstoqueQuantidade.setEditable(false);

        campoEstoqueValorVenda.setEditable(false);

        labelValorUnitario.setText("Valor Unitário:");

        campoEstoqueValorUnitario.setEditable(false);
        campoEstoqueValorUnitario.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoEstoqueValorUnitario.setForeground(new java.awt.Color(0, 51, 255));
        campoEstoqueValorUnitario.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoEstoqueValorUnitario.setEnabled(false);

        javax.swing.GroupLayout painelEstoqueLayout = new javax.swing.GroupLayout(painelEstoque);
        painelEstoque.setLayout(painelEstoqueLayout);
        painelEstoqueLayout.setHorizontalGroup(
            painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEstoqueQuantidade)
                    .addComponent(labelEstoqueValorVenda)
                    .addComponent(labelValorUnitario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoEstoqueValorVenda)
                    .addComponent(campoEstoqueQuantidade)
                    .addComponent(campoEstoqueValorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelEstoqueLayout.setVerticalGroup(
            painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstoqueLayout.createSequentialGroup()
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstoqueQuantidade)
                    .addComponent(campoEstoqueQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorUnitario)
                    .addComponent(campoEstoqueValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstoqueValorVenda)
                    .addComponent(campoEstoqueValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        painelTipoReajuste.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de Reajuste:"));

        grupoBotoesTipoReajuste.add(botaoRadioPorcentagem);
        botaoRadioPorcentagem.setText("Porcentagem");
        botaoRadioPorcentagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoRadioPorcentagemMouseClicked(evt);
            }
        });

        grupoBotoesTipoReajuste.add(botaoRadioValor);
        botaoRadioValor.setText("Valor");
        botaoRadioValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoRadioValorMouseClicked(evt);
            }
        });

        grupoBotoesTipoReajuste.add(botaoRadioAdicionar);
        botaoRadioAdicionar.setText("Adicionar");
        botaoRadioAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoRadioAdicionarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelTipoReajusteLayout = new javax.swing.GroupLayout(painelTipoReajuste);
        painelTipoReajuste.setLayout(painelTipoReajusteLayout);
        painelTipoReajusteLayout.setHorizontalGroup(
            painelTipoReajusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTipoReajusteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoRadioPorcentagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoRadioValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoRadioAdicionar))
        );
        painelTipoReajusteLayout.setVerticalGroup(
            painelTipoReajusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTipoReajusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botaoRadioPorcentagem)
                .addComponent(botaoRadioValor)
                .addComponent(botaoRadioAdicionar))
        );

        labelReajuste.setText("Reajuste:");

        campoReajuste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoReajusteKeyPressed(evt);
            }
        });

        tabelaReajustes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód:", "Data:", "Motivo:", "Tipo:", "Vlr. Antigo:", "Reajuste:", "Reajustado:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaReajustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaReajustesMouseClicked(evt);
            }
        });
        painelReajustes.setViewportView(tabelaReajustes);
        if (tabelaReajustes.getColumnModel().getColumnCount() > 0) {
            tabelaReajustes.getColumnModel().getColumn(0).setMinWidth(40);
            tabelaReajustes.getColumnModel().getColumn(0).setMaxWidth(40);
            tabelaReajustes.getColumnModel().getColumn(1).setMinWidth(70);
            tabelaReajustes.getColumnModel().getColumn(1).setMaxWidth(70);
            tabelaReajustes.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaReajustes.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaReajustes.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaReajustes.getColumnModel().getColumn(4).setMaxWidth(80);
            tabelaReajustes.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaReajustes.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaReajustes.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaReajustes.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        campoValorReajustado.setEditable(false);
        campoValorReajustado.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        campoValorReajustado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoValorReajustado.setEnabled(false);

        labelValorReajustado.setText("Val.Reajust:");

        labelData.setText("Data:");

        labelMotivo.setText("Motivo:");

        campoMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoMotivoKeyPressed(evt);
            }
        });

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDataFocusGained(evt);
            }
        });
        campoData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelProduto)
                    .addComponent(labelCodigo)
                    .addComponent(labelReajuste)
                    .addComponent(labelValorReajustado)
                    .addComponent(labelData)
                    .addComponent(labelMotivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoListaPrimeiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaUltimo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addComponent(campoDescricao)
                    .addComponent(campoMotivo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campoValorReajustado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(campoReajuste, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(painelTipoReajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(painelEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelReajustes, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaoListaAnterior, BotaoListaPrimeiro, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoListaUltimo)
                    .addComponent(BotaoListaProximo)
                    .addComponent(BotaoListaAnterior)
                    .addComponent(BotaoListaPrimeiro)
                    .addComponent(botaoBuscar)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProduto)
                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelData)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMotivo)
                    .addComponent(campoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelTipoReajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelReajuste)
                            .addComponent(campoReajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoValorReajustado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorReajustado)))
                    .addComponent(painelEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelReajustes, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void cadastraReajuste() {
        Produtos produto = persist.pesquisaProdutos(Integer.parseInt(campoCodigo.getText()));
        String tipoReajuste = "";
        if (botaoRadioPorcentagem.isSelected()) {
            tipoReajuste = "porcentagem";
        } else if (botaoRadioValor.isSelected()) {
            tipoReajuste = "valor";
        } else if (botaoRadioAdicionar.isSelected()) {
            tipoReajuste = "adicionar";
        }

        reajusteProdutos = coletaDadosCampos(produto, tipoReajuste);
        persistReajuste.cadastrarReajuste(reajusteProdutos);
        produto.atualizaValorDeVenda(reajusteProdutos.convValorBanco(campoValorReajustado.getText()));
        persist.atualizaProduto(produto);
        listaProdutos.remove(posicao);
        listaProdutos.add(posicao, produto);
        insereNaTabela(reajusteProdutos);
        exibeDados(produto);
    }
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoData.setEnabled(true);
            campoData.setText(reajusteProdutos.getDataAtual());
            botaoRadioPorcentagem.setEnabled(true);
            botaoRadioValor.setEnabled(true);
            botaoRadioAdicionar.setEnabled(true);
            campoMotivo.setEnabled(true);
            campoReajuste.setEnabled(true);
            botaoExcluir.setEnabled(false);
            campoData.requestFocus();
            campoMotivo.setText("");
            campoReajuste.setText("");
            botaoCadastrar.setText("Gravar");
            BotaoListaPrimeiro.setEnabled(false);
            BotaoListaAnterior.setEnabled(false);
            BotaoListaProximo.setEnabled(false);
            BotaoListaUltimo.setEnabled(false);
            botaoExcluir.setEnabled(false);
            botaoCadastrar.setEnabled(false);

        } else if (listaReajustes.isEmpty() || persistReajuste.convDataBanco(campoData.getText()).after(listaReajustes.get(listaReajustes.size() - 1).getDataReajuste())) {

            cadastraReajuste();
            campoData.setEnabled(false);
            botaoRadioPorcentagem.setEnabled(false);
            botaoRadioValor.setEnabled(false);
            botaoRadioAdicionar.setEnabled(false);
            campoMotivo.setEnabled(false);
            campoReajuste.setEnabled(false);
            campoData.setText("");
            campoMotivo.setText("");
            campoReajuste.setText("");
            campoValorReajustado.setText("");
            botaoRadioPorcentagem.setSelected(false);
            botaoRadioAdicionar.setSelected(false);
            botaoRadioValor.setSelected(false);
            BotaoListaPrimeiro.setEnabled(true);
            BotaoListaAnterior.setEnabled(true);
            BotaoListaProximo.setEnabled(true);
            BotaoListaUltimo.setEnabled(true);
            botaoExcluir.setEnabled(true);
            botaoCadastrar.setText("Cadastrar");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Alteração não permitida!\n"
                    + "Data do Reajuste igual ou anterior a data do último reajuste!");
            botaoCadastrar.setEnabled(false);
            campoData.requestFocus();
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover este Reajuste?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int posTabela = tabelaReajustes.getSelectedRow() + 1;
            int quantLinhas = tabelaReajustes.getRowCount();
            if (posTabela == quantLinhas) {
                int codigo = Integer.parseInt((String) tabelaReajustes.getValueAt(tabelaReajustes.getSelectedRow(), 0));
                Produtos produto = persist.pesquisaProdutos(Integer.parseInt(campoCodigo.getText()));
                produto.atualizaValorDeVenda(listaReajustes.get(listaReajustes.size() - 1).getValorAntigo());
                persist.atualizaProduto(produto);
                listaReajustes.remove(listaReajustes.size() - 1);
                persistReajuste.removeReajuste(codigo);
                ((DefaultTableModel) tabelaReajustes.getModel()).removeRow(tabelaReajustes.getSelectedRow());
                exibeDados(produto);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Só é possivel Excluir o último Reajuste Cadastrado!");
            }
        }
        botaoExcluir.setEnabled(false);
    }//GEN-LAST:event_botaoExcluirActionPerformed

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

    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaProdutos.get(listaProdutos.size() - 1));
        posicao = listaProdutos.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed

    private void botaoRadioPorcentagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioPorcentagemMouseClicked
        labelPorcentagem.setText("%");
        campoReajuste.requestFocus();
    }//GEN-LAST:event_botaoRadioPorcentagemMouseClicked

    private void botaoRadioValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioValorMouseClicked
        labelPorcentagem.setText("");
        campoReajuste.requestFocus();
    }//GEN-LAST:event_botaoRadioValorMouseClicked

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        cp.setVisible(true);
        cp.setLocationRelativeTo(null);
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoRadioAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRadioAdicionarMouseClicked
        labelPorcentagem.setText("");
        campoReajuste.requestFocus();
    }//GEN-LAST:event_botaoRadioAdicionarMouseClicked

    private void campoReajusteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoReajusteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (botaoRadioPorcentagem.isSelected()) {
                BigDecimal campo = new BigDecimal(campoReajuste.getText().replaceAll("\\.", "").replaceAll(",", "."));
                BigDecimal reajuste = campo.divide(new BigDecimal(100));
                reajuste = reajuste.add(new BigDecimal(1));
                BigDecimal valorAntes = listaProdutos.get(posicao).getValorUnitarioVenda();
                campoValorReajustado.setText(f.format(valorAntes.multiply(reajuste)));
                botaoCadastrar.setEnabled(true);
                botaoCadastrar.requestFocus();
            } else if (botaoRadioValor.isSelected()) {
                campoValorReajustado.setText(campoReajuste.getText());
                botaoCadastrar.setEnabled(true);
                botaoCadastrar.requestFocus();
            } else if (botaoRadioAdicionar.isSelected()) {
                BigDecimal reajuste = new BigDecimal(campoReajuste.getText().replaceAll("\\.", "").replaceAll(",", ".")).add(listaProdutos.get(posicao).getValorUnitarioVenda());
                campoValorReajustado.setText(f.format(reajuste));
                botaoCadastrar.setEnabled(true);
                botaoCadastrar.requestFocus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Você deve Selecionar a forma de Reajuste!");
                campoReajuste.requestFocus();
            }
        }
    }//GEN-LAST:event_campoReajusteKeyPressed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void tabelaReajustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaReajustesMouseClicked
        if (botaoCadastrar.getText().equals("Cadastrar")) {
            botaoExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tabelaReajustesMouseClicked

    private void campoDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoMotivo.requestFocus();
        }
    }//GEN-LAST:event_campoDataKeyPressed

    private void campoDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFocusGained
        campoData.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFocusGained

    private void campoMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoReajuste.requestFocus();
        }
    }//GEN-LAST:event_campoMotivoKeyPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroReajusteProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroReajusteProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroReajusteProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroReajusteProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            CadastroReajusteProdutos dialog = null;
            try {
                dialog = new CadastroReajusteProdutos(new javax.swing.JFrame(), true);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroReajusteProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoListaAnterior;
    private javax.swing.JButton BotaoListaPrimeiro;
    private javax.swing.JButton BotaoListaProximo;
    private javax.swing.JButton BotaoListaUltimo;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JRadioButton botaoRadioAdicionar;
    private javax.swing.JRadioButton botaoRadioPorcentagem;
    private javax.swing.JRadioButton botaoRadioValor;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoEstoqueQuantidade;
    private javax.swing.JTextField campoEstoqueValorUnitario;
    private javax.swing.JTextField campoEstoqueValorVenda;
    private javax.swing.JTextField campoMotivo;
    private javax.swing.JTextField campoReajuste;
    private javax.swing.JTextField campoValorReajustado;
    private javax.swing.ButtonGroup grupoBotoesTipoReajuste;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelEstoqueValorVenda;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel labelPorcentagem;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelReajuste;
    private javax.swing.JLabel labelValorReajustado;
    private javax.swing.JLabel labelValorUnitario;
    private javax.swing.JPanel painelEstoque;
    private javax.swing.JScrollPane painelReajustes;
    private javax.swing.JPanel painelTipoReajuste;
    private javax.swing.JTable tabelaReajustes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodHistoricoBusca(String msg) {
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
    public void mensagemCodProdutoVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
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
    public void mensagemCodComissoes(String codComissao) {
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
