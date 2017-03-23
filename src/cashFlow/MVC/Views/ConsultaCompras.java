package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.ComprasDAO;
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

public class ConsultaCompras extends javax.swing.JDialog implements InterfaceListener {

    private Compras compra = new Compras();
    private final ComprasDAO persistCompra;
    private final NumberFormat f;
    private InterfaceListener listner = null;
    private CadastroCancelamentoCompras cancelamentoCompra;
    private List<Compras> listaCompras;
    private final ConsultaFornecedor consultaFornecedor;
    DefaultTableModel val;
    SimpleDateFormat sdf;

    public ConsultaCompras() {
        initComponents();//inicia os componentes
        persistCompra = new ComprasDAO();
        consultaFornecedor = new ConsultaFornecedor();
        sdf = new SimpleDateFormat("dd/MM/yyyy");//formata a data no textField
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        carregaDados();//carrega valores e configurações na tela
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {
        campoCodigo.setText(msg);
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    private void carregaDados() {
        consultaFornecedor.setListener(this);
        campoDataInicial.setText(compra.getDataInicial());//pega primeiro dia do mes para o filtro
        campoDataFinal.setText(compra.getDataFinal());//pega primeiro dia do mes para o filtro
        botaoImprimir.setEnabled(false);
        botaoExcluir.setEnabled(false);
        //configura os campos
        botaoImprimir.setFocusTraversalKeysEnabled(false);
        botaoFiltrar.setFocusTraversalKeysEnabled(false);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabelaCompras.getColumnModel().getColumn(3).setCellRenderer(direita);
        val = (DefaultTableModel) tabelaCompras.getModel();
        campoCodigo.setDocument(new IntegerDocument(5));
    }

    public void preparaCampos() {
        //prepara campos para digitação do proximo lançamento
        botaoImprimir.setEnabled(false);
        botaoFiltrarActionPerformed(null);
    }

    public void insereNaTabela(Compras compra) {
        String situacao = (compra.getCancelamentoCompra() == null) ? "Ativa" : "Cancelada";
        val.addRow(new String[]{persistCompra.convDataSistema(compra.getDataCompra()),
            Integer.toString(compra.getCod()),
            compra.getFornecedor().getRazaosocial(),
            f.format(compra.getValorTotalCompra()),
            situacao});
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCompras = new javax.swing.JTable();
        botaoImprimir = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
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
        setTitle("Consulta Compra");
        setName("Cadastro de Caixas"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tabelaCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCompras.setNextFocusableComponent(botaoImprimir);
        tabelaCompras.setShowHorizontalLines(false);
        tabelaCompras.setSurrendersFocusOnKeystroke(true);
        tabelaCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaComprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCompras);
        if (tabelaCompras.getColumnModel().getColumnCount() > 0) {
            tabelaCompras.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabelaCompras.getColumnModel().getColumn(0).setMaxWidth(70);
            tabelaCompras.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaCompras.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaCompras.getColumnModel().getColumn(4).setMinWidth(80);
            tabelaCompras.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaCompras.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        botaoImprimir.setText("Imprimir");

        botaoExcluir.setText("Cancelar Compra");
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

        botaoBuscar.setText("Buscar");
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
                        .addComponent(botaoExcluir)))
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
                    .addComponent(botaoExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar esta compra?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int posicao = Integer.parseInt((String) tabelaCompras.getValueAt(tabelaCompras.getSelectedRow(), 1));
            cancelamentoCompra = new CadastroCancelamentoCompras();
            compra = persistCompra.pesquisaProdutos(posicao);
            if (compra.getCancelamentoCompra() != null) {
                JOptionPane.showMessageDialog(null, "Compra já cancelada!");
            } else {
                this.setListener(cancelamentoCompra);
                listner.compraCancelar(compra);
                val.setValueAt("Cancelada", tabelaCompras.getSelectedRow(), 4);
            }
        }
        botaoExcluir.setEnabled(false);
    }//GEN-LAST:event_botaoExcluirActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        ConsultaCompras.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void botaoFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFiltrarActionPerformed
        ((DefaultTableModel) tabelaCompras.getModel()).setRowCount(0);//zera a tabela
        listaCompras = persistCompra.getListaComprasData(
                persistCompra.convDataBanco(campoDataInicial.getText()),
                persistCompra.convDataBanco(campoDataFinal.getText()));
        for (Compras each : listaCompras) {
            insereNaTabela(each);
        }
        if (listaCompras.isEmpty()) {
            campoTipoFiltro.setText("");
            JOptionPane.showMessageDialog(null, "Nenhuma compra localizada neste periodo!");
        } else {
            campoTipoFiltro.setText("Filtrado por DATA entre " + campoDataInicial.getText() + " e " + campoDataFinal.getText());
        }


    }//GEN-LAST:event_botaoFiltrarActionPerformed
    private void tabelaComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaComprasMouseClicked
        int posicao = Integer.parseInt((String) tabelaCompras.getValueAt(
                tabelaCompras.getSelectedRow(), 1));
        Compras temp = new Compras();
        for (int i = 0; i < listaCompras.size(); i++) {
            if (posicao == listaCompras.get(i).getCod()) {
                temp = listaCompras.get(i);
            }
        }
        if (evt.getClickCount() == 2) {
            CadastroCompras exibir = new CadastroCompras(temp);
            exibir.setLocationRelativeTo(this);
            exibir.setVisible(true);
            int v = 0;
        } else if (evt.getClickCount() == 1) {
            if (temp.getCancelamentoCompra() != null) {
                campoDataCancelamento.setText(persistCompra.convDataSistema(
                        temp.getCancelamentoCompra().getDataCancelamento()));
                campoMotivoCancelamento.setText(temp.getCancelamentoCompra().getMotivo());
            } else {
                campoDataCancelamento.setText("");
                campoMotivoCancelamento.setText("");
            }
        }
        botaoExcluir.setEnabled(true);
    }//GEN-LAST:event_tabelaComprasMouseClicked
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
            ConsultaCompras.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    private void campoDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFinalFocusGained
        campoDataFinal.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFinalFocusGained
    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (campoCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira um código de fornecedor!");
            campoCodigo.requestFocus();
        } else {
            ((DefaultTableModel) tabelaCompras.getModel()).setRowCount(0);//zera a tabela
            listaCompras = persistCompra.getListaComprasFornecedor(Integer.parseInt(campoCodigo.getText()));
            for (Compras each : listaCompras) {
                insereNaTabela(each);
            }
            if (listaCompras.isEmpty()) {
                campoTipoFiltro.setText("");
                JOptionPane.showMessageDialog(null, "Nenhuma compra localizada para este fornecedor!");
            } else {
                campoTipoFiltro.setText("Filtrado por FORNECEDOR Cód: " + campoCodigo.getText() + " RAZAO SOCIAL: " + listaCompras.get(0).getFornecedor().getRazaosocial());
            }
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed
    private void campoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaFornecedor.setVisible(true);
            consultaFornecedor.setLocationRelativeTo(null);
        }else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoBuscar.requestFocus();
        }
    }//GEN-LAST:event_campoCodigoKeyPressed

    private void campoDataInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataInicialFocusGained
        campoDataInicial.setCaretPosition(0);
    }//GEN-LAST:event_campoDataInicialFocusGained
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ConsultaCompras().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
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
    private javax.swing.JTable tabelaCompras;
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
    public void mensagemCodClienteConsultVenda(String msg) {

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
