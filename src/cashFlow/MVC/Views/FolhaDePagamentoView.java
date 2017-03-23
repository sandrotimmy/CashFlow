package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.FolhaDePagamentoCtrl;
import cashFlow.MVC.Controllers.GuiUtils;
import cashFlow.MVC.Controllers.LancamentosCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Models.Comissoes;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.Descontos;
import cashFlow.MVC.Models.Empregados;
import cashFlow.MVC.Models.FolhaDePagamento;
import cashFlow.MVC.Models.ItemDesconto;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.Lancamentos;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Proventos;
import cashFlow.MVC.Models.Vendas;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FolhaDePagamentoView extends javax.swing.JDialog implements InterfaceListener {

    private final FolhaDePagamentoCtrl folhaDePagamentoCtrl;
    private final Empregados empregado;
    private final MetodosGerais mg;
    private Comissoes comissoes;
    private Descontos descontos;
    private final ConsultaComissoes consultaComissoes;
    private final ConsultaDescontos consultaDescontos;
    private FolhaDePagamento folhaDePagamento;
    private final NumberFormat f;
    private int[] linhasSelecionadas;
    DefaultTableModel valProventos;
    DefaultTableModel valDescontos;

    public FolhaDePagamentoView(Date competencia, Empregados empregado) {
        initComponents();
        valProventos = (DefaultTableModel) tabelaProventos.getModel();
        valDescontos = (DefaultTableModel) tabelaDescontos.getModel();
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        consultaComissoes = new ConsultaComissoes();
        consultaComissoes.setListener(this);
        consultaDescontos = new ConsultaDescontos();
        consultaDescontos.setListener(this);
        comissoes = new Comissoes();
        descontos = new Descontos();
        mg = new MetodosGerais();
        folhaDePagamentoCtrl = new FolhaDePagamentoCtrl();
        this.empregado = empregado;
        campoNome.setText(empregado.getNome());
        campoCompetencia.setText(mg.convCompetenciaSistema(competencia));
        carregaFolha(competencia, empregado);
        campoCodProvento.setFocusTraversalKeysEnabled(false);
        campoCodDesconto.setFocusTraversalKeysEnabled(false);
        botaoIncluirProvento.setFocusTraversalKeysEnabled(false);
        botaoIncluirDesconto.setFocusTraversalKeysEnabled(false);
        campoDataProvento.setText(mg.getDataAtual());
        campoDataDesconto.setText(mg.getDataAtual());
    }

    private void carregaFolha(Date competencia, Empregados empregado) {
        if (!empregado.getListaFolhaPagamento().isEmpty()) {
            boolean possue = false;
            for (int i = 0; i < empregado.getListaFolhaPagamento().size(); i++) {
                if (empregado.getListaFolhaPagamento().get(i).getCompetencia().compareTo(competencia) == 0) {
                    folhaDePagamento = empregado.getListaFolhaPagamento().get(i);
                    for (int j = 0; j < folhaDePagamento.getListaProventos().size(); j++) {
                        Proventos proventoTemp = folhaDePagamento.getListaProventos().get(j);
                        insereNaTabelaProv(proventoTemp);
                        calculaTotais(1, proventoTemp.getValorTotalComissao(), BigDecimal.ZERO);
                    }
                    possue = true;
                    break;
                }
            }
            if (!possue) {
                JOptionPane.showMessageDialog(campoCodProvento, "Competência não Localizada!\nEntrando em modo de Inclusão");
                folhaDePagamento = new FolhaDePagamento(competencia, empregado);
            }
            if (folhaDePagamento.getListaItemDescontos() != null) {
                List<ItemDesconto> listaItemDescontos = folhaDePagamento.getListaItemDescontos();

                for (int i = 0; i < listaItemDescontos.size(); i++) {
                    ItemDesconto itemDescontoTemp = listaItemDescontos.get(i);
                    insereNaTabelaDesc(itemDescontoTemp);
                    calculaTotais(1, BigDecimal.ZERO, itemDescontoTemp.getValorTotalDesconto());
                }
            }
        } else {
            JOptionPane.showMessageDialog(campoCodProvento, "Competência não Localizada!\nEntrando em modo de Inclusão");
            folhaDePagamento = new FolhaDePagamento(competencia, empregado);
        }
        scrollProventos.getVerticalScrollBar().setValue(tabelaProventos.getHeight());
        scrollPaneDescontos.getVerticalScrollBar().setValue(tabelaDescontos.getHeight());
    }

    @Override
    public void mensagemCodDescontos(String codDesconto) {
        campoCodDesconto.setText(codDesconto);
        try {
            Robot rb = new Robot();
            rb.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            Logger.getLogger(FolhaDePagamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpaCamposProventos() {
        campoCodProvento.setText("");
        campoNomeProvento.setText("");
        campoObservacoesProventos.setText("");
        campoValorServico.setText("");
        campoComissao.setText("");
        campoTipo.setText("");
        campoBloco.setText("");
        campoTotalComissao.setText("");

    }

    public void limpaCamposDescontos() {
        campoCodDesconto.setText("");
        campoNomeDesconto.setText("");
        campoObservacoesDesc.setText("");
        campoValorBase.setText("");
        campoValorDesconto.setText("");
        campoTotalDesconto.setText("");
        campoTipoDesconto.setText("");
    }

    public Lancamentos coletaDadosLancamento() {
        Lancamentos lancamento = new Lancamentos();
        lancamento.setDataLancamento(mg.convDataBanco(campoDataDesconto.getText()));
        lancamento.setObservacoes("Desconto de " + campoNomeDesconto.getText() + " do funcionario " + campoNome.getText());
        lancamento.setValorDebito(BigDecimal.ZERO);
        lancamento.setValorCredito(mg.convValorBanco(campoTotalDesconto.getText()));
        return lancamento;
    }

    public void insereNaTabelaProv(Proventos provento) {
        valProventos.addRow(new String[]{Integer.toString(provento.getCod()),
            mg.convDataSistema(provento.getDataComissao()),
            provento.getNome(),
            provento.getObservacoes(),
            provento.getBloco(),
            f.format(provento.getValorServico()),
            f.format(provento.getComissao()),
            f.format(provento.getValorTotalComissao()),
            provento.getSituacao()
        });
        GuiUtils.scrollToVisible(tabelaProventos, tabelaProventos.getHeight());
    }

    public void totalizarSelecao() {
        BigDecimal somaLinhas = BigDecimal.ZERO;
        linhasSelecionadas = tabelaProventos.getSelectedRows();
        for (int i = 0; i < linhasSelecionadas.length; i++) {
            BigDecimal temp = mg.convValorTabela(tabelaProventos.getValueAt(linhasSelecionadas[i], 7).toString());
            somaLinhas = somaLinhas.add(temp);
        }
        campoTotalSelecionado.setText(somaLinhas.toString());
    }

    public void insereNaTabelaDesc(ItemDesconto itemDesconto) {
        valDescontos.addRow(new String[]{Integer.toString(itemDesconto.getCod()),
            mg.convDataSistema(itemDesconto.getDataDesconto()),
            itemDesconto.getNome(),
            itemDesconto.getObservacoes(),
            f.format(itemDesconto.getValorBase()),
            f.format(itemDesconto.getValorDesconto()),
            f.format(itemDesconto.getValorTotalDesconto())
        });
        GuiUtils.scrollToVisible(tabelaDescontos, tabelaDescontos.getHeight());
    }

    @Override
    public void mensagemCodComissoes(String codComissao) {
        campoCodProvento.setText(codComissao);
    }

    public void calculaTotais(int operacao, BigDecimal prov, BigDecimal desc) {
        BigDecimal totalProventos = mg.convValorBanco(campoTotalProv.getText());
        BigDecimal totalDescontos = mg.convValorBanco(campoTotalDesc.getText());

        if (operacao == 1) {//soma
            totalProventos = totalProventos.add(prov);
            totalDescontos = totalDescontos.add(desc);
        } else if (operacao == 2) {//subtrai
            totalProventos = totalProventos.subtract(prov);
            totalDescontos = totalDescontos.subtract(desc);
        }
        BigDecimal totalSaldoPagar = totalProventos.subtract(totalDescontos);
        campoTotalProv.setText(totalProventos.toString());
        campoTotalDesc.setText(totalDescontos.toString());
        campoSaldoPagar.setText(totalSaldoPagar.toString());
        campoTotalProventos.setText(totalProventos.toString());
        campoTotalItemDescontos.setText(totalDescontos.toString());

        folhaDePagamento.setTotalProventos(totalProventos);
        folhaDePagamento.setTotalDescontos(totalDescontos);
        folhaDePagamento.setSaldoApagar(totalSaldoPagar);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCompetencia = new javax.swing.JLabel();
        campoCompetencia = new javax.swing.JTextField();
        labelFuncionario = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        painelDescontos = new javax.swing.JPanel();
        labelCodigoDesconto = new javax.swing.JLabel();
        campoCodDesconto = new javax.swing.JTextField();
        campoNomeDesconto = new javax.swing.JTextField();
        labelObervacoes = new javax.swing.JLabel();
        campoObservacoesDesc = new javax.swing.JTextField();
        labelValorDesconto = new javax.swing.JLabel();
        campoValorDesconto = new JNumberFormatField();
        botaoIncluirDesconto = new javax.swing.JButton();
        botaoExcluirDesconto = new javax.swing.JButton();
        labelDataDesconto = new javax.swing.JLabel();
        campoDataDesconto = new javax.swing.JTextField();
        labelTotalDescontos = new javax.swing.JLabel();
        campoTotalItemDescontos = new JNumberFormatField();
        labelValorBase = new javax.swing.JLabel();
        campoValorBase = new JNumberFormatField();
        labelTipoDesconto = new javax.swing.JLabel();
        campoTipoDesconto = new javax.swing.JTextField();
        labelTotalDesconto = new javax.swing.JLabel();
        campoTotalDesconto = new JNumberFormatField();
        scrollPaneDescontos = new javax.swing.JScrollPane();
        tabelaDescontos = new javax.swing.JTable();
        painelProventos = new javax.swing.JPanel();
        scrollProventos = new javax.swing.JScrollPane();
        tabelaProventos = new javax.swing.JTable();
        labelCodProventos = new javax.swing.JLabel();
        campoCodProvento = new javax.swing.JTextField();
        campoNomeProvento = new javax.swing.JTextField();
        labelObservacoes = new javax.swing.JLabel();
        campoObservacoesProventos = new javax.swing.JTextField();
        labelValorServico = new javax.swing.JLabel();
        campoValorServico = new JNumberFormatField();
        labelComissao = new javax.swing.JLabel();
        campoComissao = new JNumberFormatField();
        labelTipo = new javax.swing.JLabel();
        campoTipo = new javax.swing.JTextField();
        labelTotalComissao = new javax.swing.JLabel();
        campoTotalComissao = new JNumberFormatField();
        botaoIncluirProvento = new javax.swing.JButton();
        botaoExcluirProvento = new javax.swing.JButton();
        labelDataProvento = new javax.swing.JLabel();
        campoDataProvento = new javax.swing.JTextField();
        labelTotalProventos = new javax.swing.JLabel();
        campoTotalProventos = new JNumberFormatField();
        labelTotalSelecionado = new javax.swing.JLabel();
        campoTotalSelecionado = new JNumberFormatField();
        botaoPagar = new javax.swing.JButton();
        labelBloco = new javax.swing.JLabel();
        campoBloco = new javax.swing.JTextField();
        painelTotais = new javax.swing.JPanel();
        labelTotalProv = new javax.swing.JLabel();
        labelTotalDesc = new javax.swing.JLabel();
        labelSandoPagar = new javax.swing.JLabel();
        campoTotalProv = new JNumberFormatField();
        campoTotalDesc = new JNumberFormatField();
        campoSaldoPagar = new JNumberFormatField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Folha de Pagamento");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelCompetencia.setText("Competência:");

        campoCompetencia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCompetencia.setEnabled(false);

        labelFuncionario.setText("Funcionário:");

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNome.setEnabled(false);
        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        painelDescontos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descontos:"));

        labelCodigoDesconto.setText("código:");

        campoCodDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodDescontoKeyPressed(evt);
            }
        });

        campoNomeDesconto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeDesconto.setEnabled(false);

        labelObervacoes.setText("Observações:");

        labelValorDesconto.setText("Valor do Desconto:");

        campoValorDesconto.setEnabled(false);
        campoValorDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorDescontoFocusLost(evt);
            }
        });

        botaoIncluirDesconto.setText("Incluir");
        botaoIncluirDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirDescontoActionPerformed(evt);
            }
        });

        botaoExcluirDesconto.setText("Excluir");
        botaoExcluirDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirDescontoActionPerformed(evt);
            }
        });

        labelDataDesconto.setText("Data:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataDesconto = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }

        labelTotalDescontos.setText("Total Descontos:");

        campoTotalItemDescontos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalItemDescontos.setEnabled(false);

        labelValorBase.setText("Valor Base:");

        campoValorBase.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorBaseFocusLost(evt);
            }
        });

        labelTipoDesconto.setText("Tipo:");

        campoTipoDesconto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTipoDesconto.setEnabled(false);

        labelTotalDesconto.setText("Total Desconto:");

        campoTotalDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalDescontoActionPerformed(evt);
            }
        });

        tabelaDescontos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód:", "Data:", "Desconto:", "Observações:", "Valor Base:", "Desconto:", "Valor:"
            }
        ));
        scrollPaneDescontos.setViewportView(tabelaDescontos);
        if (tabelaDescontos.getColumnModel().getColumnCount() > 0) {
            tabelaDescontos.getColumnModel().getColumn(0).setMinWidth(60);
            tabelaDescontos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabelaDescontos.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaDescontos.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaDescontos.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaDescontos.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaDescontos.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaDescontos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaDescontos.getColumnModel().getColumn(4).setMaxWidth(80);
            tabelaDescontos.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaDescontos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaDescontos.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaDescontos.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaDescontos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabelaDescontos.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        javax.swing.GroupLayout painelDescontosLayout = new javax.swing.GroupLayout(painelDescontos);
        painelDescontos.setLayout(painelDescontosLayout);
        painelDescontosLayout.setHorizontalGroup(
            painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDescontosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneDescontos)
                    .addGroup(painelDescontosLayout.createSequentialGroup()
                        .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelCodigoDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelValorDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelObervacoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelValorBase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painelDescontosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelTotalDescontos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTotalItemDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDescontosLayout.createSequentialGroup()
                                .addComponent(campoCodDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomeDesconto))
                            .addComponent(campoObservacoesDesc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDescontosLayout.createSequentialGroup()
                                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campoValorBase, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoValorDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDescontosLayout.createSequentialGroup()
                                        .addComponent(labelTipoDesconto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(painelDescontosLayout.createSequentialGroup()
                                        .addComponent(labelDataDesconto)
                                        .addGap(7, 7, 7)))
                                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoDataDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(campoTipoDesconto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTotalDesconto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTotalDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoIncluirDesconto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExcluirDesconto)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        painelDescontosLayout.setVerticalGroup(
            painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDescontosLayout.createSequentialGroup()
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigoDesconto)
                    .addComponent(campoCodDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelObervacoes)
                    .addComponent(campoObservacoesDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDataDesconto)
                        .addComponent(campoDataDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelValorBase)
                        .addComponent(campoValorBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorDesconto)
                    .addComponent(campoValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluirDesconto)
                    .addComponent(botaoIncluirDesconto)
                    .addComponent(labelTipoDesconto)
                    .addComponent(campoTipoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalDesconto)
                    .addComponent(campoTotalDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(painelDescontosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTotalItemDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalDescontos)))
        );

        painelProventos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Proventos:"));

        tabelaProventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód:", "Data:", "Provento:", "Observações:", "nº Bloco:", "Vlr Serviço:", "Comissão:", "Valor:", "Situação:"
            }
        ));
        tabelaProventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProventosMouseClicked(evt);
            }
        });
        tabelaProventos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaProventosKeyReleased(evt);
            }
        });
        scrollProventos.setViewportView(tabelaProventos);
        if (tabelaProventos.getColumnModel().getColumnCount() > 0) {
            tabelaProventos.getColumnModel().getColumn(0).setMinWidth(60);
            tabelaProventos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabelaProventos.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaProventos.getColumnModel().getColumn(1).setMinWidth(60);
            tabelaProventos.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabelaProventos.getColumnModel().getColumn(1).setMaxWidth(60);
            tabelaProventos.getColumnModel().getColumn(4).setMinWidth(60);
            tabelaProventos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabelaProventos.getColumnModel().getColumn(4).setMaxWidth(60);
            tabelaProventos.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaProventos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabelaProventos.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaProventos.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaProventos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabelaProventos.getColumnModel().getColumn(6).setMaxWidth(80);
            tabelaProventos.getColumnModel().getColumn(7).setMinWidth(80);
            tabelaProventos.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabelaProventos.getColumnModel().getColumn(7).setMaxWidth(80);
            tabelaProventos.getColumnModel().getColumn(8).setMinWidth(70);
            tabelaProventos.getColumnModel().getColumn(8).setPreferredWidth(70);
            tabelaProventos.getColumnModel().getColumn(8).setMaxWidth(70);
        }

        labelCodProventos.setText("código:");

        campoCodProvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodProventoKeyPressed(evt);
            }
        });

        campoNomeProvento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeProvento.setEnabled(false);

        labelObservacoes.setText("Observações:");

        labelValorServico.setText("Valor do Serviço:");

        campoValorServico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoValorServicoFocusLost(evt);
            }
        });

        labelComissao.setText("Comissão:");

        campoComissao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoComissao.setEnabled(false);
        campoComissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoComissaoFocusLost(evt);
            }
        });
        campoComissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoComissaoActionPerformed(evt);
            }
        });

        labelTipo.setText("Tipo:");

        campoTipo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTipo.setEnabled(false);

        labelTotalComissao.setText("Total Comissão:");

        botaoIncluirProvento.setText("Incluir");
        botaoIncluirProvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirProventoActionPerformed(evt);
            }
        });

        botaoExcluirProvento.setText("Excluir");
        botaoExcluirProvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirProventoActionPerformed(evt);
            }
        });

        labelDataProvento.setText("Data:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataProvento = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }

        labelTotalProventos.setText("Total Proventos:");

        campoTotalProventos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalProventos.setEnabled(false);

        labelTotalSelecionado.setText("Total Selecionado:");

        campoTotalSelecionado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalSelecionado.setEnabled(false);
        campoTotalSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalSelecionadoActionPerformed(evt);
            }
        });

        botaoPagar.setText("Pagar");
        botaoPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPagarActionPerformed(evt);
            }
        });

        labelBloco.setText("Bloco:");

        javax.swing.GroupLayout painelProventosLayout = new javax.swing.GroupLayout(painelProventos);
        painelProventos.setLayout(painelProventosLayout);
        painelProventosLayout.setHorizontalGroup(
            painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProventosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelProventosLayout.createSequentialGroup()
                        .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelComissao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCodProventos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelValorServico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelObservacoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelProventosLayout.createSequentialGroup()
                                .addComponent(campoCodProvento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomeProvento))
                            .addComponent(campoObservacoesProventos)
                            .addGroup(painelProventosLayout.createSequentialGroup()
                                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoValorServico)
                                    .addComponent(campoComissao, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTipo)
                                    .addComponent(labelDataProvento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoTipo)
                                    .addComponent(campoDataProvento, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelProventosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelTotalProventos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTotalProventos, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelProventosLayout.createSequentialGroup()
                        .addComponent(labelTotalComissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoTotalComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBloco)
                        .addGap(2, 2, 2)
                        .addComponent(campoBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoIncluirProvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluirProvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTotalSelecionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoTotalSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPagar))
                    .addGroup(painelProventosLayout.createSequentialGroup()
                        .addComponent(scrollProventos, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelProventosLayout.setVerticalGroup(
            painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelProventosLayout.createSequentialGroup()
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodProventos)
                    .addComponent(campoCodProvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeProvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelObservacoes)
                    .addComponent(campoObservacoesProventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorServico)
                    .addComponent(campoValorServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataProvento)
                    .addComponent(campoDataProvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelComissao)
                    .addComponent(campoComissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipo)
                    .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalComissao)
                    .addComponent(campoTotalComissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoIncluirProvento)
                    .addComponent(botaoExcluirProvento)
                    .addComponent(labelTotalSelecionado)
                    .addComponent(campoTotalSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPagar)
                    .addComponent(labelBloco)
                    .addComponent(campoBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollProventos, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTotalProventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalProventos))
                .addGap(4, 4, 4))
        );

        painelTotais.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Totais:"));

        labelTotalProv.setText("Total Proventos:.............");

        labelTotalDesc.setText("TotalDescontos:.............");

        labelSandoPagar.setText("Saldo a Pagar:...............");

        campoTotalProv.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalProv.setEnabled(false);

        campoTotalDesc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoTotalDesc.setEnabled(false);
        campoTotalDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTotalDescActionPerformed(evt);
            }
        });

        campoSaldoPagar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoSaldoPagar.setEnabled(false);

        javax.swing.GroupLayout painelTotaisLayout = new javax.swing.GroupLayout(painelTotais);
        painelTotais.setLayout(painelTotaisLayout);
        painelTotaisLayout.setHorizontalGroup(
            painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTotalDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSandoPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoSaldoPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(campoTotalDesc)
                    .addComponent(campoTotalProv))
                .addContainerGap())
        );
        painelTotaisLayout.setVerticalGroup(
            painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalProv)
                    .addComponent(campoTotalProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalDesc)
                    .addComponent(campoTotalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSandoPagar)
                    .addComponent(campoSaldoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCompetencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelProventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelDescontos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelTotais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCompetencia)
                    .addComponent(campoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFuncionario)
                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelProventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelTotais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void campoComissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoComissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoComissaoActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void campoTotalSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalSelecionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTotalSelecionadoActionPerformed

    private void campoTotalDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalDescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTotalDescActionPerformed

    private void campoCodProventoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodProventoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            comissoes = folhaDePagamentoCtrl.getComissao(Integer.parseInt(campoCodProvento.getText()));
            if (empregado != null) {
                campoCodProvento.setText(Integer.toString(comissoes.getCod()));
                campoNomeProvento.setText(comissoes.getNome());
                campoObservacoesProventos.requestFocus();
                campoComissao.setText(comissoes.getValor().toString());
                campoTipo.setText(comissoes.getTipo());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Comissão Inexistente!");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaComissoes.setVisible(true);
            consultaComissoes.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodProventoKeyPressed

    private void botaoIncluirProventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirProventoActionPerformed
        Proventos provento = new Proventos();
        provento.setDataComissao(mg.convDataBanco(campoDataProvento.getText()));
        provento.setNome(campoNomeProvento.getText());
        provento.setObservacoes(campoObservacoesProventos.getText());
        provento.setBloco(campoBloco.getText());
        provento.setValorServico(mg.convValorBanco(campoValorServico.getText()));
        provento.setComissao(mg.convValorBanco(campoComissao.getText()));
        provento.setValorTotalComissao(mg.convValorBanco(campoTotalComissao.getText()));
        provento.setSituacao("Aberto");
        provento.setFolhaDePagamento(folhaDePagamento);
        provento.setComissoes(comissoes);
        calculaTotais(1, provento.getValorTotalComissao(), BigDecimal.ZERO);

        if (folhaDePagamentoCtrl.existeFolhaDePagamento(folhaDePagamento)) {
            folhaDePagamentoCtrl.atualizaFolhaDePagamento(folhaDePagamento);
        } else {
            folhaDePagamentoCtrl.cadastraFolhaDePagamento(folhaDePagamento);
        }
        folhaDePagamentoCtrl.cadastrarProvento(provento);
        insereNaTabelaProv(provento);
        limpaCamposProventos();
        campoCodProvento.requestFocus();
        scrollProventos.getVerticalScrollBar().setValue(tabelaProventos.getHeight());
    }//GEN-LAST:event_botaoIncluirProventoActionPerformed

    private void campoValorServicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorServicoFocusLost
        BigDecimal valorServico = mg.convValorBanco(campoValorServico.getText());
        if (campoTipo.getText().equalsIgnoreCase("Porcentagem")) {
            BigDecimal porcentagem = comissoes.getValor().divide(new BigDecimal("100.00"));
            campoTotalComissao.setText(f.format(valorServico.multiply(porcentagem)));
        } else if (comissoes.getValor().compareTo(BigDecimal.ZERO) == 0) {
            campoComissao.setEnabled(true);
        } else {
            campoTotalComissao.setText(f.format(comissoes.getValor()));
        }
    }//GEN-LAST:event_campoValorServicoFocusLost

    private void botaoExcluirProventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirProventoActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover este Provento?",
                null,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int codProvento = Integer.parseInt((String) tabelaProventos.getValueAt(tabelaProventos.getSelectedRow(), 0));
            Proventos proventoTemp = folhaDePagamentoCtrl.getProventos(codProvento);
            ((DefaultTableModel) tabelaProventos.getModel()).removeRow(tabelaProventos.getSelectedRow());
            folhaDePagamentoCtrl.removeProvento(codProvento);
            calculaTotais(2, proventoTemp.getValorTotalComissao(), BigDecimal.ZERO);
        }
    }//GEN-LAST:event_botaoExcluirProventoActionPerformed

    private void botaoIncluirDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirDescontoActionPerformed
        ItemDesconto itemDesconto = new ItemDesconto();
        itemDesconto.setNome(campoNomeDesconto.getText());
        itemDesconto.setDataDesconto(mg.convDataBanco(campoDataDesconto.getText()));
        itemDesconto.setObservacoes(campoObservacoesDesc.getText());
        itemDesconto.setValorBase(mg.convValorBanco(campoValorBase.getText()));
        itemDesconto.setValorDesconto(mg.convValorBanco(campoValorDesconto.getText()));
        itemDesconto.setValorTotalDesconto(mg.convValorBanco(campoTotalDesconto.getText()));
        itemDesconto.setTipo(campoTipoDesconto.getText());
        itemDesconto.setFolhaDePagamento(folhaDePagamento);
        folhaDePagamentoCtrl.cadastrarItemDesconto(itemDesconto);

        calculaTotais(1, BigDecimal.ZERO, itemDesconto.getValorBase());

        if (JOptionPane.showConfirmDialog(null, "Deseja efetuar lançamento no Caixa?",
                null,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Lancamentos lancamento = coletaDadosLancamento();
            LancamentosCtrl lancamentosCtrl = new LancamentosCtrl();
            lancamentosCtrl.cadastrarLancamento(lancamento);
        }
        insereNaTabelaDesc(itemDesconto);
        limpaCamposDescontos();
        GuiUtils.scrollToVisible(tabelaDescontos, tabelaDescontos.getHeight());
    }//GEN-LAST:event_botaoIncluirDescontoActionPerformed

    private void campoTotalDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTotalDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTotalDescontoActionPerformed

    private void campoCodDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodDescontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            descontos = folhaDePagamentoCtrl.getDesconto(Integer.parseInt(campoCodDesconto.getText()));
            campoCodDesconto.setText(Integer.toString(descontos.getCod()));
            campoNomeDesconto.setText(descontos.getNome());
            campoObservacoesDesc.requestFocus();
            campoValorDesconto.setText(descontos.getValor().toString());
            campoTipoDesconto.setText(descontos.getTipo());

        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaDescontos.setVisible(true);
            consultaDescontos.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodDescontoKeyPressed

    private void campoComissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoComissaoFocusLost
        campoTotalComissao.setText(campoComissao.getText());
        campoComissao.setEnabled(false);
    }//GEN-LAST:event_campoComissaoFocusLost

    private void campoValorBaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorBaseFocusLost
        BigDecimal valorBase = mg.convValorBanco(campoValorBase.getText());
        if (campoTipoDesconto.getText().equalsIgnoreCase("Porcentagem")) {
            BigDecimal porcentagem = descontos.getValor().divide(new BigDecimal("100.00"));
            campoTotalDesconto.setText(f.format(valorBase.multiply(porcentagem)));
        } else if (descontos.getValor().compareTo(BigDecimal.ZERO) == 0) {
            campoValorDesconto.setEnabled(true);
        } else {
            campoTotalDesconto.setText(f.format(descontos.getValor()));
        }
    }//GEN-LAST:event_campoValorBaseFocusLost

    private void campoValorDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoValorDescontoFocusLost
        campoTotalDesconto.setText(campoValorDesconto.getText());
    }//GEN-LAST:event_campoValorDescontoFocusLost

    private void botaoExcluirDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirDescontoActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover este Adiantamento?",
                null,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int codDesconto = Integer.parseInt((String) tabelaDescontos.getValueAt(tabelaDescontos.getSelectedRow(), 0));
            ItemDesconto descontoTemp = folhaDePagamentoCtrl.getItemDesconto(codDesconto);
            ((DefaultTableModel) tabelaDescontos.getModel()).removeRow(tabelaDescontos.getSelectedRow());
            folhaDePagamentoCtrl.removeItemDesconto(codDesconto);
            calculaTotais(2, BigDecimal.ZERO, descontoTemp.getValorBase());
        }
    }//GEN-LAST:event_botaoExcluirDescontoActionPerformed

    private void tabelaProventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProventosMouseClicked
        totalizarSelecao();
    }//GEN-LAST:event_tabelaProventosMouseClicked

    private void botaoPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPagarActionPerformed
        String situacao = "Pago";
        for (int i = 0; i < linhasSelecionadas.length; i++) {
            if (tabelaProventos.getValueAt(linhasSelecionadas[i], 8).toString().compareTo(situacao) == 0) {
                JOptionPane.showMessageDialog(null, "Você selecionou proventos já pagos\nCancelando o pagamento!");
                break;
            }
            int codProvento = Integer.parseInt(tabelaProventos.getValueAt(linhasSelecionadas[i], 0).toString());
            tabelaProventos.setValueAt(situacao, linhasSelecionadas[i], 8);
            Proventos provento = folhaDePagamentoCtrl.getProventos(codProvento);
            provento.setSituacao(situacao);
            folhaDePagamentoCtrl.atualizaProvento(provento);
            campoValorBase.setText(campoTotalSelecionado.getText());
            campoCodDesconto.requestFocus();
        }

    }//GEN-LAST:event_botaoPagarActionPerformed

    private void tabelaProventosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaProventosKeyReleased
        if (evt.getKeyCode() == 40 || evt.getKeyCode() == 38) {
            totalizarSelecao();
        }
    }//GEN-LAST:event_tabelaProventosKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluirDesconto;
    private javax.swing.JButton botaoExcluirProvento;
    private javax.swing.JButton botaoIncluirDesconto;
    private javax.swing.JButton botaoIncluirProvento;
    private javax.swing.JButton botaoPagar;
    private javax.swing.JTextField campoBloco;
    private javax.swing.JTextField campoCodDesconto;
    private javax.swing.JTextField campoCodProvento;
    private javax.swing.JTextField campoComissao;
    private javax.swing.JTextField campoCompetencia;
    private javax.swing.JTextField campoDataDesconto;
    private javax.swing.JTextField campoDataProvento;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeDesconto;
    private javax.swing.JTextField campoNomeProvento;
    private javax.swing.JTextField campoObservacoesDesc;
    private javax.swing.JTextField campoObservacoesProventos;
    private javax.swing.JTextField campoSaldoPagar;
    private javax.swing.JTextField campoTipo;
    private javax.swing.JTextField campoTipoDesconto;
    private javax.swing.JTextField campoTotalComissao;
    private javax.swing.JTextField campoTotalDesc;
    private javax.swing.JTextField campoTotalDesconto;
    private javax.swing.JTextField campoTotalItemDescontos;
    private javax.swing.JTextField campoTotalProv;
    private javax.swing.JTextField campoTotalProventos;
    private javax.swing.JTextField campoTotalSelecionado;
    private javax.swing.JTextField campoValorBase;
    private javax.swing.JTextField campoValorDesconto;
    private javax.swing.JTextField campoValorServico;
    private javax.swing.JLabel labelBloco;
    private javax.swing.JLabel labelCodProventos;
    private javax.swing.JLabel labelCodigoDesconto;
    private javax.swing.JLabel labelComissao;
    private javax.swing.JLabel labelCompetencia;
    private javax.swing.JLabel labelDataDesconto;
    private javax.swing.JLabel labelDataProvento;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelObervacoes;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelSandoPagar;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTipoDesconto;
    private javax.swing.JLabel labelTotalComissao;
    private javax.swing.JLabel labelTotalDesc;
    private javax.swing.JLabel labelTotalDesconto;
    private javax.swing.JLabel labelTotalDescontos;
    private javax.swing.JLabel labelTotalProv;
    private javax.swing.JLabel labelTotalProventos;
    private javax.swing.JLabel labelTotalSelecionado;
    private javax.swing.JLabel labelValorBase;
    private javax.swing.JLabel labelValorDesconto;
    private javax.swing.JLabel labelValorServico;
    private javax.swing.JPanel painelDescontos;
    private javax.swing.JPanel painelProventos;
    private javax.swing.JPanel painelTotais;
    private javax.swing.JScrollPane scrollPaneDescontos;
    private javax.swing.JScrollPane scrollProventos;
    private javax.swing.JTable tabelaDescontos;
    private javax.swing.JTable tabelaProventos;
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
    public void mensagemCodProdutoReajuste(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compraCancelar(Compras compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodCalculoFolhaPagamento(String codBusca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
