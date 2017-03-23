package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.IntegerDocument;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ConsultaVendas extends javax.swing.JDialog implements InterfaceListener {

    private Vendas venda = new Vendas();
    private final VendasDAO persistVenda;
    private final NumberFormat f;
    private InterfaceListener listner = null;
    private CadastroCancelamentoVendas cancelamentoVenda;
    private List<Vendas> listaVendas;
    private final ConsultaCliente consultaCliente;
    DefaultTableModel val;
    SimpleDateFormat sdf;

    public ConsultaVendas() {
        initComponents();//inicia os componentes
        persistVenda = new VendasDAO();
        consultaCliente = new ConsultaCliente();
        sdf = new SimpleDateFormat("dd/MM/yyyy");//formata a data no textField
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        carregaDados();//carrega valores e configurações na tela
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        campoCodigo.setText(msg);
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    private void carregaDados() {
        consultaCliente.setListener(this);
        campoDataInicial.setText(venda.getDataInicial());//pega primeiro dia do mes para o filtro
        campoDataFinal.setText(venda.getDataFinal());//pega primeiro dia do mes para o filtro
        botaoImprimir.setEnabled(false);
        botaoCancelarVenda.setEnabled(false);
        //configura os campos
        botaoImprimir.setFocusTraversalKeysEnabled(false);
        botaoFiltrar.setFocusTraversalKeysEnabled(false);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaVendas.getColumnModel().getColumn(3).setCellRenderer(direita);
        val = (DefaultTableModel) tabelaVendas.getModel();
        campoCodigo.setDocument(new IntegerDocument(5));
    }

    public void preparaCampos() {
        //prepara campos para digitação do proximo lançamento
        botaoImprimir.setEnabled(false);
        botaoFiltrarActionPerformed(null);
    }

    public void insereNaTabela(Vendas venda) {
        String situacao = (venda.getCancelamentoVenda() == null) ? "Ativa" : "Cancelada";
        val.addRow(new String[]{persistVenda.convDataSistema(venda.getDataVenda()),
            Integer.toString(venda.getCod()),
            venda.getCliente().getNome(),
            f.format(venda.getValorTotalVenda()),
            situacao});
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        botaoImprimir = new javax.swing.JButton();
        botaoCancelarVenda = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        painelFiltroData = new javax.swing.JPanel();
        campoDataInicial = new javax.swing.JTextField();
        campoDataFinal = new javax.swing.JTextField();
        labelDataFinal = new javax.swing.JLabel();
        labelDataInicial = new javax.swing.JLabel();
        botaoFiltrar = new javax.swing.JButton();
        painelFiltroCliente = new javax.swing.JPanel();
        botaoBuscar = new javax.swing.JButton();
        labelCodigo = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        labelTipoFiltro = new javax.swing.JLabel();
        campoTipoFiltro = new javax.swing.JTextField();
        painelCancelamento = new javax.swing.JPanel();
        labelDataCancelamento = new javax.swing.JLabel();
        campoDataCancelamento = new javax.swing.JTextField();
        labelMotivoCancelamento = new javax.swing.JLabel();
        campoMotivoCancelamento = new javax.swing.JTextField();

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
        setTitle("Consulta Venda");
        setName("Cadastro de Caixas"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data:", "Cód:", "Cliente:", "Valor:", "Situação:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaVendas.setNextFocusableComponent(botaoImprimir);
        tabelaVendas.setShowHorizontalLines(false);
        tabelaVendas.setSurrendersFocusOnKeystroke(true);
        tabelaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaVendas);
        if (tabelaVendas.getColumnModel().getColumnCount() > 0) {
            tabelaVendas.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabelaVendas.getColumnModel().getColumn(0).setMaxWidth(70);
            tabelaVendas.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaVendas.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaVendas.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaVendas.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaVendas.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        botaoImprimir.setText("Imprimir");

        botaoCancelarVenda.setText("Cancelar Venda");
        botaoCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarVendaActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        painelFiltroData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro por data:"));

        try{
            javax.swing.text.MaskFormatter dataInicial = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataInicial = new javax.swing.JFormattedTextField(dataInicial);
        }
        catch (Exception e){
        }
        campoDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDataInicialFocusGained(evt);
            }
        });
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

        javax.swing.GroupLayout painelFiltroDataLayout = new javax.swing.GroupLayout(painelFiltroData);
        painelFiltroData.setLayout(painelFiltroDataLayout);
        painelFiltroDataLayout.setHorizontalGroup(
            painelFiltroDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFiltroDataLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelDataInicial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDataFinal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(botaoFiltrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelFiltroDataLayout.setVerticalGroup(
            painelFiltroDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFiltroDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelDataFinal)
                .addComponent(labelDataInicial)
                .addComponent(botaoFiltrar))
        );

        painelFiltroCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro Por Cliente:"));

        botaoBuscar.setText("Filtrar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        labelCodigo.setText("Código:");

        campoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodigoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelFiltroClienteLayout = new javax.swing.GroupLayout(painelFiltroCliente);
        painelFiltroCliente.setLayout(painelFiltroClienteLayout);
        painelFiltroClienteLayout.setHorizontalGroup(
            painelFiltroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFiltroClienteLayout.createSequentialGroup()
                .addComponent(labelCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoBuscar)
                .addContainerGap())
        );
        painelFiltroClienteLayout.setVerticalGroup(
            painelFiltroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFiltroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botaoBuscar)
                .addComponent(labelCodigo)
                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelTipoFiltro.setText("Filtro:");

        campoTipoFiltro.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        campoTipoFiltro.setBorder(null);
        campoTipoFiltro.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        campoTipoFiltro.setEnabled(false);

        painelCancelamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Informações do Cancelamento:"));

        labelDataCancelamento.setText("Data:");

        try{
            javax.swing.text.MaskFormatter dataCancelamento = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataCancelamento = new javax.swing.JFormattedTextField(dataCancelamento);
        }
        catch (Exception e){
        }
        campoDataCancelamento.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        campoDataCancelamento.setBorder(null);
        campoDataCancelamento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDataCancelamento.setEnabled(false);

        labelMotivoCancelamento.setText("Motivo:");

        campoMotivoCancelamento.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        campoMotivoCancelamento.setBorder(null);
        campoMotivoCancelamento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoMotivoCancelamento.setEnabled(false);

        javax.swing.GroupLayout painelCancelamentoLayout = new javax.swing.GroupLayout(painelCancelamento);
        painelCancelamento.setLayout(painelCancelamentoLayout);
        painelCancelamentoLayout.setHorizontalGroup(
            painelCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCancelamentoLayout.createSequentialGroup()
                .addComponent(labelDataCancelamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMotivoCancelamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoMotivoCancelamento, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelCancelamentoLayout.setVerticalGroup(
            painelCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelDataCancelamento)
                .addComponent(campoDataCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelMotivoCancelamento)
                .addComponent(campoMotivoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelFiltroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelFiltroData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(labelTipoFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTipoFiltro))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelarVenda)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelFiltroData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelFiltroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoFiltro)
                    .addComponent(campoTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelarVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarVendaActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar esta venda?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int posicao = Integer.parseInt((String) tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 1));
            cancelamentoVenda = new CadastroCancelamentoVendas();
            venda = persistVenda.pesquisaProdutos(posicao);
            if (venda.getCancelamentoVenda() != null) {
                JOptionPane.showMessageDialog(null, "Venda já cancelada!");
            } else {
                this.setListener(cancelamentoVenda);
                listner.vendaCancelar(venda);
                val.setValueAt("Cancelada", tabelaVendas.getSelectedRow(), 4);
            }
        }
        botaoCancelarVenda.setEnabled(false);
    }//GEN-LAST:event_botaoCancelarVendaActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        ConsultaVendas.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void botaoFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFiltrarActionPerformed

        ((DefaultTableModel) tabelaVendas.getModel()).setRowCount(0);//zera a tabela
        listaVendas = persistVenda.getListaVendasData(
                persistVenda.convDataBanco(campoDataInicial.getText()),
                persistVenda.convDataBanco(campoDataFinal.getText()));
        for (Vendas each : listaVendas) {
            insereNaTabela(each);
        }
        if (listaVendas.isEmpty()) {
            campoTipoFiltro.setText("");
            JOptionPane.showMessageDialog(null, "Nenhuma venda localizada neste periodo!");
        } else {
            campoTipoFiltro.setText("Filtrado por DATA entre " + campoDataInicial.getText() + " e " + campoDataFinal.getText());
        }
    }//GEN-LAST:event_botaoFiltrarActionPerformed
    private void tabelaVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaVendasMouseClicked
        int posicao = Integer.parseInt((String) tabelaVendas.getValueAt(
                tabelaVendas.getSelectedRow(), 1));
        Vendas temp = new Vendas();
        for (int i = 0; i < listaVendas.size(); i++) {
            if (posicao == listaVendas.get(i).getCod()) {
                temp = listaVendas.get(i);
            }
        }
        if (evt.getClickCount() == 2) {
            CadastroVendas exibir = new CadastroVendas(temp);
            exibir.setLocationRelativeTo(this);
            exibir.setVisible(true);
        }
        if (evt.getClickCount() == 1) {
            if (temp.getCancelamentoVenda() != null) {
                campoDataCancelamento.setText(persistVenda.convDataSistema(
                        temp.getCancelamentoVenda().getDataCancelamento()));
                campoMotivoCancelamento.setText(temp.getCancelamentoVenda().getMotivo());
            } else {
                campoDataCancelamento.setText("");
                campoMotivoCancelamento.setText("");
            }
        }
        botaoCancelarVenda.setEnabled(true);
    }//GEN-LAST:event_tabelaVendasMouseClicked
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
            ConsultaVendas.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    private void campoDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFinalFocusGained
        campoDataFinal.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFinalFocusGained
    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (campoCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira um código de cliente!");
            campoCodigo.requestFocus();
        } else {
            ((DefaultTableModel) tabelaVendas.getModel()).setRowCount(0);//zera a tabela
            listaVendas = persistVenda.getListaVendasCliente(Integer.parseInt(campoCodigo.getText()));
            for (Vendas each : listaVendas) {
                insereNaTabela(each);
            }
            if (listaVendas.isEmpty()) {
                campoTipoFiltro.setText("");
                JOptionPane.showMessageDialog(null, "Nenhuma venda localizada para este cliente!");
            } else {
                campoTipoFiltro.setText("Filtrado por CLIENTE Cód: " + campoCodigo.getText() + " Nome: " + listaVendas.get(0).getCliente().getNome());
            }
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed
    private void campoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaCliente.setVisible(true);
            consultaCliente.setLocationRelativeTo(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoBuscar.requestFocus();
        }
    }//GEN-LAST:event_campoCodigoKeyPressed

    private void campoDataInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataInicialFocusGained
        campoDataInicial.setCaretPosition(0);
    }//GEN-LAST:event_campoDataInicialFocusGained
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ConsultaVendas().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoCancelarVenda;
    private javax.swing.JButton botaoFiltrar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoDataCancelamento;
    private javax.swing.JTextField campoDataFinal;
    private javax.swing.JTextField campoDataInicial;
    private javax.swing.JTextField campoMotivoCancelamento;
    private javax.swing.JTextField campoTipoFiltro;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelDataCancelamento;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    private javax.swing.JLabel labelMotivoCancelamento;
    private javax.swing.JLabel labelTipoFiltro;
    private javax.swing.JPanel painelCancelamento;
    private javax.swing.JPanel painelFiltroCliente;
    private javax.swing.JPanel painelFiltroData;
    private javax.swing.JTable tabelaVendas;
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
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet.");
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
