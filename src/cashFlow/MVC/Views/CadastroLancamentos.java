package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.LancamentosCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.Lancamentos;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CadastroLancamentos extends javax.swing.JDialog implements InterfaceListener {

    private Lancamentos lancamento = new Lancamentos();
    private final LancamentosCtrl lancamentosCtrl;
    private final NumberFormat f;
    private final MetodosGerais mg;
    ConsultaHistorico consultaHistorico;
    DefaultTableModel val;
    SimpleDateFormat sdf;

    public CadastroLancamentos() throws SQLException {
        initComponents();//inicia os componentes
        this.mg = new MetodosGerais();
        lancamentosCtrl = new LancamentosCtrl();
        sdf = mg.getFormatoData();//formata a data no textField
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        consultaHistorico = new ConsultaHistorico();
        carregaDados();//carrega valores e configurações na tela
    }

    private void carregaDados() {
        consultaHistorico.setListener(this);
        campoCod.setText(Integer.toString(lancamentosCtrl.getProximoCodLancamento()));//pega o próximo codigo de lamçamento
        campoData.setText(mg.getDataAtual());//pega a data do Computador
        campoDataInicial.setText(mg.getDataInicial());//pega primeiro dia do mes para o filtro
        campoDataFinal.setText(mg.getDataFinal());//pega primeiro dia do mes para o filtro
        campoTipo.setEnabled(false);
        campoValorDebito.setEnabled(false);
        campoValorCredito.setEnabled(false);
        botaoIncluir.setEnabled(false);
        botaoExcluir.setEnabled(false);
        //configura os campos
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        botaoIncluir.setFocusTraversalKeysEnabled(false);
        campoCodHistorico.setFocusTraversalKeysEnabled(false);
        botaoFiltrar.setFocusTraversalKeysEnabled(false);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaLancamentos.getColumnModel().getColumn(5).setCellRenderer(direita);
        tabelaLancamentos.getColumnModel().getColumn(6).setCellRenderer(direita);
        val = (DefaultTableModel) tabelaLancamentos.getModel();
        campoData.requestFocus();
        campoData.setCaretPosition(0);
        botaoFiltrarActionPerformed(null);
    }

    public void preparaCampos() {
        //prepara campos para digitação do proximo lançamento
        campoCod.setText(Integer.toString(lancamentosCtrl.getProximoCodLancamento()));
        campoCodHistorico.setText("");
        campoHistorico.setText("");
        campoTipo.setText("");
        campoObservacoes.setText("");
        campoValorDebito.setText("");
        campoValorCredito.setText("");
        botaoIncluir.setEnabled(false);
        botaoFiltrarActionPerformed(null);
        campoData.requestFocus();

    }

    public void setDebitoCredito(boolean valorDebito, boolean valorCredito) {
        campoValorDebito.setEnabled(valorDebito);
        campoValorCredito.setEnabled(valorCredito);
    }

    @Override
    public void mensagemCodHistorico(String msg) {//recebe por listener o codigo do historico para busca
        campoCodHistorico.setText(msg);
        campoCodHistorico.requestFocus();
    }

    public Lancamentos coletaDadosCampos() throws ParseException {
        lancamento.setCod(Integer.parseInt(campoCod.getText()));
        lancamento.setDataLancamento(sdf.parse(campoData.getText()));
        lancamento.setObservacoes(campoObservacoes.getText());
        lancamento.setValorDebito(mg.convValorBanco(campoValorDebito.getText()));
        lancamento.setValorCredito(mg.convValorBanco(campoValorCredito.getText()));
        return lancamento;
    }

    public void insereNaTabela(Lancamentos lancamento) {
        val.addRow(new String[]{mg.convDataSistema(lancamento.getDataLancamento()),
            Integer.toString(lancamento.getHistorico().getCod()),
            lancamento.getHistorico().getTipo(),
            Integer.toString(lancamento.getCod()),
            lancamento.getObservacoes(),
            f.format(lancamento.getValorDebito()),
            f.format(lancamento.getValorCredito())});
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        labelCodigo = new javax.swing.JLabel();
        campoCod = new javax.swing.JTextField();
        labelHistorico = new javax.swing.JLabel();
        campoHistorico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaLancamentos = new javax.swing.JTable();
        labelEntrada = new javax.swing.JLabel();
        campoValorDebito = new JNumberFormatField();
        labelSaida = new javax.swing.JLabel();
        campoValorCredito = new JNumberFormatField();
        botaoIncluir = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        labelData = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        campoCodHistorico = new javax.swing.JTextField();
        labelObservacoes = new javax.swing.JLabel();
        campoObservacao = new javax.swing.JScrollPane();
        campoObservacoes = new javax.swing.JTextArea();
        painelFiltro = new javax.swing.JPanel();
        campoDataInicial = new javax.swing.JTextField();
        campoDataFinal = new javax.swing.JTextField();
        labelDataFinal = new javax.swing.JLabel();
        labelDataInicial = new javax.swing.JLabel();
        botaoFiltrar = new javax.swing.JButton();
        labelTipo = new javax.swing.JLabel();
        campoTipo = new javax.swing.JTextField();
        labelSaldoAnterior = new javax.swing.JLabel();
        campoSaldoAnterior = new javax.swing.JTextField();
        painelDeTotais = new javax.swing.JPanel();
        campoTotalDebito = new javax.swing.JTextField();
        campoTotalCredito = new javax.swing.JTextField();
        campoSaldoPeriodo = new javax.swing.JTextField();
        labelSaldoTotal = new javax.swing.JLabel();
        labelTotalEntradasSaidas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelTotalEmCaixa = new javax.swing.JLabel();
        campoTotalEmCaixa = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lançamento Livro Caixa");
        setModal(true);
        setName("Cadastro de Caixas"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelCodigo.setText("Código:");

        campoCod.setEditable(true);
        campoCod.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCod.setEnabled(false);
        campoCod.setOpaque(true);

        labelHistorico.setText("Histórico:");

        campoHistorico.setEditable(false);
        campoHistorico.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        tabelaLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data:", "Hist:", "Tipo:", "Codigo:", "Observações:", "Entrada:", "Saida:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLancamentos.setNextFocusableComponent(botaoIncluir);
        tabelaLancamentos.setShowHorizontalLines(false);
        tabelaLancamentos.setSurrendersFocusOnKeystroke(true);
        tabelaLancamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLancamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaLancamentos);
        if (tabelaLancamentos.getColumnModel().getColumnCount() > 0) {
            tabelaLancamentos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabelaLancamentos.getColumnModel().getColumn(0).setMaxWidth(70);
            tabelaLancamentos.getColumnModel().getColumn(1).setPreferredWidth(40);
            tabelaLancamentos.getColumnModel().getColumn(1).setMaxWidth(40);
            tabelaLancamentos.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabelaLancamentos.getColumnModel().getColumn(2).setMaxWidth(70);
            tabelaLancamentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabelaLancamentos.getColumnModel().getColumn(3).setMaxWidth(50);
            tabelaLancamentos.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        labelEntrada.setText("Entrada:");

        campoValorDebito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorDebitoFocusLost(evt);
            }
        });

        labelSaida.setText("Saida:");

        campoValorCredito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorCreditoFocusLost(evt);
            }
        });

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

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        labelData.setText("Data:");

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
        campoData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoDataMouseClicked(evt);
            }
        });
        campoData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataKeyPressed(evt);
            }
        });

        campoCodHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoCodHistorico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodHistoricoKeyPressed(evt);
            }
        });

        labelObservacoes.setText("Observações:");

        campoObservacoes.setColumns(20);
        campoObservacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoObservacoesKeyPressed(evt);
            }
        });
        campoObservacao.setViewportView(campoObservacoes);

        painelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro"));

        try{
            javax.swing.text.MaskFormatter dataInicial = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataInicial = new javax.swing.JFormattedTextField(dataInicial);
        }
        catch (Exception e){
        }
        campoDataInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataInicialKeyPressed(evt);
            }
        });

        try{
            javax.swing.text.MaskFormatter dataFinal = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataFinal = new javax.swing.JFormattedTextField(dataFinal);
        }
        catch (Exception e){
        }
        campoDataFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDataFinalFocusGained(evt);
            }
        });
        campoDataFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataFinalKeyPressed(evt);
            }
        });

        labelDataFinal.setText("Data Final:");

        labelDataInicial.setText("Data Inicial:");

        botaoFiltrar.setText("Filtrar");
        botaoFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelFiltroLayout = new javax.swing.GroupLayout(painelFiltro);
        painelFiltro.setLayout(painelFiltroLayout);
        painelFiltroLayout.setHorizontalGroup(
            painelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFiltroLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(painelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFiltroLayout.createSequentialGroup()
                        .addComponent(labelDataInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoFiltrar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        painelFiltroLayout.setVerticalGroup(
            painelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFiltroLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(painelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataFinal)
                    .addComponent(labelDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFiltrar))
        );

        labelTipo.setText("Tipo:");

        campoTipo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelSaldoAnterior.setText("Saldo Anterior: ");

        campoSaldoAnterior.setEditable(false);
        campoSaldoAnterior.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoSaldoAnterior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        painelDeTotais.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total do Periodo:"));

        campoTotalDebito.setEditable(false);
        campoTotalDebito.setForeground(Color.BLUE);
        campoTotalDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalDebito.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        campoTotalCredito.setEditable(false);
        campoTotalCredito.setForeground(Color.RED);
        campoTotalCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalCredito.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        campoSaldoPeriodo.setEditable(false);
        campoSaldoPeriodo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoSaldoPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelSaldoTotal.setText("Saldo Periodo:");

        labelTotalEntradasSaidas.setText("Entradas:");

        jLabel1.setText("Saidas:");

        javax.swing.GroupLayout painelDeTotaisLayout = new javax.swing.GroupLayout(painelDeTotais);
        painelDeTotais.setLayout(painelDeTotaisLayout);
        painelDeTotaisLayout.setHorizontalGroup(
            painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDeTotaisLayout.createSequentialGroup()
                .addGroup(painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(labelTotalEntradasSaidas)
                    .addComponent(labelSaldoTotal))
                .addGap(31, 31, 31)
                .addGroup(painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTotalDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(campoTotalCredito)
                    .addComponent(campoSaldoPeriodo))
                .addContainerGap())
        );
        painelDeTotaisLayout.setVerticalGroup(
            painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDeTotaisLayout.createSequentialGroup()
                .addGroup(painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalEntradasSaidas)
                    .addComponent(campoTotalDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDeTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoSaldoPeriodo)
                    .addComponent(labelSaldoTotal))
                .addGap(24, 24, 24))
        );

        labelTotalEmCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTotalEmCaixa.setText("TOTAL EM CAIXA:");

        campoTotalEmCaixa.setEditable(false);
        campoTotalEmCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        campoTotalEmCaixa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoTotalEmCaixa.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTotalEmCaixa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTotalEmCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelObservacoes)
                                    .addComponent(labelHistorico)
                                    .addComponent(labelData)
                                    .addComponent(labelTipo)
                                    .addComponent(labelSaida)
                                    .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(painelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelSaldoAnterior)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCodHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(campoHistorico))
                                            .addComponent(campoObservacao)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(campoValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEntrada)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelDeTotais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botaoIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCancelar)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoExcluir, botaoIncluir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelData)))
                    .addComponent(painelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHistorico))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelObservacoes)
                    .addComponent(campoObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSaldoAnterior)
                    .addComponent(campoSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSaida))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelDeTotais, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTotalEmCaixa)
                            .addComponent(campoTotalEmCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoIncluir))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        painelDeTotais.getAccessibleContext().setAccessibleName("Total Do Periodo:");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        try {
            val = (DefaultTableModel) tabelaLancamentos.getModel();
            lancamento = coletaDadosCampos();
            lancamentosCtrl.cadastrarLancamento(lancamento);//insere dados no banco
            insereNaTabela(lancamento);//insere na tabela
            preparaCampos();//prepara os campos para digitar novo                
        } catch (ParseException ex) {
            Logger.getLogger(CadastroLancamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover este Lançamento?",
                null,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int idLancamento = Integer.parseInt((String) tabelaLancamentos.getValueAt(tabelaLancamentos.getSelectedRow(), 3));
            ((DefaultTableModel) tabelaLancamentos.getModel()).removeRow(tabelaLancamentos.getSelectedRow());
            lancamentosCtrl.removeLancamento(idLancamento);
            botaoFiltrarActionPerformed(evt);
        }
        botaoExcluir.setEnabled(false);
    }//GEN-LAST:event_botaoExcluirActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroLancamentos.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void campoCodHistoricoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodHistoricoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            lancamento.setHistorico(lancamentosCtrl.pesquisaHistorico(Integer.parseInt(campoCodHistorico.getText())));
            campoHistorico.setText(lancamento.getHistorico().getNomeHistorico());
            campoTipo.setText(lancamento.getHistorico().getTipo());
            campoObservacoes.setText(lancamento.getHistorico().getNomeHistorico());
            if (lancamento.getHistorico().getTipo().equalsIgnoreCase("Entrada")) {
                setDebitoCredito(true, false);
            } else {
                setDebitoCredito(false, true);
            }
            botaoIncluir.setEnabled(true);
            campoObservacoes.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaHistorico.setVisible(true);
            consultaHistorico.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodHistoricoKeyPressed
    private void campoObservacoesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoObservacoesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (campoTipo.getText().equalsIgnoreCase("Entrada")) {
                campoValorDebito.requestFocus();
            } else if (campoTipo.getText().equalsIgnoreCase("Saida")) {
                campoValorCredito.requestFocus();
            }
        }
    }//GEN-LAST:event_campoObservacoesKeyPressed
    private void botaoFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFiltrarActionPerformed
        ((DefaultTableModel) tabelaLancamentos.getModel()).setRowCount(0);//zera as linhas da tabela pra receber o novo filtro
        BigDecimal totalDebitoPeriodo = new BigDecimal(0.00);//recebe o somatório de entradas do periodo para calculo de saldo
        BigDecimal totalCreditoPeriodo = new BigDecimal(0.00);//recebe o somatório de saidas do periodo para calculo de saldo
        List<Lancamentos> listaLancamentos = lancamentosCtrl.getListaLancamentos(
                mg.convDataBanco(campoDataInicial.getText()), mg.convDataBanco(campoDataFinal.getText()));
        for (Lancamentos each : listaLancamentos) {//carrega os historicos do banco na tabela de exibição
            insereNaTabela(each);
            totalDebitoPeriodo = totalDebitoPeriodo.add(each.getValorDebito());
            totalCreditoPeriodo = totalCreditoPeriodo.add(each.getValorCredito());
        }
        java.sql.Date dataAnterior;
        //variavel que vai receber a data para o banco
        dataAnterior = mg.convDataBanco(campoDataInicial.getText());
        dataAnterior.setDate(dataAnterior.getDate() - 1);//Pega o dia anterior ao periodo selecionado
        BigDecimal totalSaldoAnterior = lancamentosCtrl.getSaldoPeriodo(mg.convDataBanco("01/01/1900"), dataAnterior);
        BigDecimal totalSaldoPeriodo = totalDebitoPeriodo.subtract(totalCreditoPeriodo).add(totalSaldoAnterior);
        BigDecimal totalEmCaixa = lancamentosCtrl.getSaldoPeriodo(mg.convDataBanco("01/01/1900"),
                mg.convDataBanco(mg.getDataAtual()));
        campoTotalDebito.setText(f.format((totalDebitoPeriodo)));
        campoTotalCredito.setText(f.format((totalCreditoPeriodo)));
        campoSaldoAnterior.setText(f.format((totalSaldoAnterior)));
        if (totalSaldoPeriodo.compareTo(new BigDecimal(0)) >= 0) {
            campoSaldoPeriodo.setForeground(Color.BLUE);
        } else {
            campoSaldoPeriodo.setForeground(Color.RED);
        }
        if (totalSaldoAnterior.compareTo(new BigDecimal(0)) >= 0) {
            campoSaldoAnterior.setForeground(Color.BLUE);
        } else {
            campoSaldoAnterior.setForeground(Color.RED);
        }
        if (totalEmCaixa.compareTo(new BigDecimal(0)) >= 0) {
            campoTotalEmCaixa.setForeground(Color.BLUE);
        } else {
            campoTotalEmCaixa.setForeground(Color.RED);
        }
        campoSaldoPeriodo.setText(f.format(totalSaldoPeriodo));
        campoTotalEmCaixa.setText(f.format(totalEmCaixa));
    }//GEN-LAST:event_botaoFiltrarActionPerformed
    private void campoDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoCodHistorico.requestFocus();
        }
    }//GEN-LAST:event_campoDataKeyPressed
    private void tabelaLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLancamentosMouseClicked
        botaoExcluir.setEnabled(true);
    }//GEN-LAST:event_tabelaLancamentosMouseClicked
    private void campoDataInicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataInicialKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoDataFinal.requestFocus();
        }
    }//GEN-LAST:event_campoDataInicialKeyPressed
    private void campoDataFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataFinalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            botaoFiltrar.requestFocus();
        }
    }//GEN-LAST:event_campoDataFinalKeyPressed
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CadastroLancamentos.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    private void campoDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoDataMouseClicked
        campoData.setCaretPosition(0);
    }//GEN-LAST:event_campoDataMouseClicked
    private void campoDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFinalFocusGained
        campoDataFinal.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFinalFocusGained
    private void campoValorDebitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorDebitoFocusLost
        botaoIncluir.requestFocus();
    }//GEN-LAST:event_campoValorDebitoFocusLost
    private void campoValorCreditoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorCreditoFocusLost
        botaoIncluir.requestFocus();
    }//GEN-LAST:event_campoValorCreditoFocusLost

    private void campoDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFocusGained
        campoData.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFocusGained
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new CadastroLancamentos().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroLancamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFiltrar;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JTextField campoCod;
    private javax.swing.JTextField campoCodHistorico;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDataFinal;
    private javax.swing.JTextField campoDataInicial;
    private javax.swing.JTextField campoHistorico;
    private javax.swing.JScrollPane campoObservacao;
    private javax.swing.JTextArea campoObservacoes;
    private javax.swing.JTextField campoSaldoAnterior;
    private javax.swing.JTextField campoSaldoPeriodo;
    private javax.swing.JTextField campoTipo;
    private javax.swing.JTextField campoTotalCredito;
    private javax.swing.JTextField campoTotalDebito;
    private javax.swing.JTextField campoTotalEmCaixa;
    private javax.swing.JTextField campoValorCredito;
    private javax.swing.JTextField campoValorDebito;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    private javax.swing.JLabel labelEntrada;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelSaida;
    private javax.swing.JLabel labelSaldoAnterior;
    private javax.swing.JLabel labelSaldoTotal;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTotalEmCaixa;
    private javax.swing.JLabel labelTotalEntradasSaidas;
    private javax.swing.JPanel painelDeTotais;
    private javax.swing.JPanel painelFiltro;
    private javax.swing.JTable tabelaLancamentos;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mensagemCodClienteVenda(String msg) {
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
    public void mensagemCodFornecedorCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodHistoricoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodProdutoVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mensagemCodProdutoCompra(String msg) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void compraCancelar(Compras compra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
