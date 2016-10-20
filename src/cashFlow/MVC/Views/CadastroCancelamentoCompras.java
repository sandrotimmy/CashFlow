package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.CancelamentoComprasDAO;
import cashFlow.MVC.DAO.ComprasDAO;
import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.Models.CancelamentoCompras;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.ItemCompra;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.Lancamentos;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroCancelamentoCompras extends javax.swing.JDialog implements InterfaceListener {

    private final CancelamentoComprasDAO persistCancelamento;//persistencia dos dados
    private final ComprasDAO persistCompra;
    private int posicao;//controla o caminhamento do cadastro
    private final ConsultaCompras consultaCompra;
    private InterfaceListener listner = null;
    private final NumberFormat f;
    private Compras compra;
    private CancelamentoCompras cancelamentoCompra;
    private final ProdutosDAO persistProdutos;
    private final Lancamentos lancamento;
    private final LancamentosDAO persistLancamento;

    public CadastroCancelamentoCompras() {
        initComponents();//inicia componentes da tela
        this.persistCancelamento = new CancelamentoComprasDAO();
        this.cancelamentoCompra = new CancelamentoCompras();
        this.persistCompra = new ComprasDAO();
        this.persistProdutos = new ProdutosDAO();
        this.lancamento = new Lancamentos();
        this.persistLancamento = new LancamentosDAO();
        consultaCompra = new ConsultaCompras();
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        consultaCompra.setListener(this);
        setCamposFocus();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    @Override
    public void compraCancelar(Compras compra) {
        this.compra = compra;
        campoCodigoCompra.setText(Integer.toString(compra.getCod()));
        String data = persistCancelamento.convDataSistema(compra.getDataCompra());
        campoData.setText(data);
        campoDataCancelamento.setText(cancelamentoCompra.getDataAtual());//pega a data do Computador
        campoNomeCliente.setText(compra.getFornecedor().getRazaosocial());
        campoValorTotal.setText(f.format(compra.getValorTotalCompra()));
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        campoDataCancelamento.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoCancelar = new javax.swing.JButton();
        botaoCancelarCompra = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        campoCodigoCompra = new javax.swing.JTextField();
        labelTipo = new javax.swing.JLabel();
        campoNomeCliente = new javax.swing.JTextField();
        labelHistorico = new javax.swing.JLabel();
        campoValorTotal = new JNumberFormatField();
        labelData = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        labelDataCancelamento = new javax.swing.JLabel();
        campoDataCancelamento = new javax.swing.JTextField();
        labelMotivo = new javax.swing.JLabel();
        campoMotivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Históricos");
        setModal(true);

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoCancelarCompra.setText("OK");
        botaoCancelarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarCompraActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dados da Nota:"));

        labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCodigo.setText("Compra:");

        campoCodigoCompra.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoCodigoCompra.setForeground(new java.awt.Color(0, 51, 255));
        campoCodigoCompra.setBorder(null);
        campoCodigoCompra.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoCodigoCompra.setEnabled(false);

        labelTipo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTipo.setText("Fornecedor:");

        campoNomeCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoNomeCliente.setForeground(new java.awt.Color(0, 51, 255));
        campoNomeCliente.setBorder(null);
        campoNomeCliente.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoNomeCliente.setEnabled(false);

        labelHistorico.setText("Valor:");

        campoValorTotal.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoValorTotal.setForeground(new java.awt.Color(0, 51, 255));
        campoValorTotal.setBorder(null);
        campoValorTotal.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoValorTotal.setEnabled(false);

        labelData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelData.setText("Data:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoData.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoData.setForeground(new java.awt.Color(0, 51, 255));
        campoData.setBorder(null);
        campoData.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoData.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTipo)
                    .addComponent(labelHistorico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNomeCliente)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(campoCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelData)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHistorico)
                    .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dados do Cancelamento:"));

        labelDataCancelamento.setText("Data:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataCancelamento = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoDataCancelamento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoDataCancelamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoDataCancelamentoFocusGained(evt);
            }
        });
        campoDataCancelamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoDataCancelamentoKeyPressed(evt);
            }
        });

        labelMotivo.setText("Motivo:");

        campoMotivo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoMotivoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelDataCancelamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMotivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataCancelamento)
                    .addComponent(campoDataCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMotivo)
                    .addComponent(campoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoCancelarCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoCancelarCompra});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCancelarCompra))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private CancelamentoCompras coletaDadosCampos() {
        cancelamentoCompra = new CancelamentoCompras(
                persistCancelamento.convDataBanco(campoDataCancelamento.getText()),
                campoMotivo.getText(),
                this.compra);
        return cancelamentoCompra;
    }

    private void setCamposFocus() {
        botaoCancelarCompra.setFocusTraversalKeysEnabled(false);
    }

    private void limitaCampos() {//limita os campos de texto
        campoMotivo.setDocument(new DocumentoLimitado(300));
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoValorTotal.setEnabled(campos);
//        comboUsuarioSistema.setEnabled(campos);
        botaoCancelarCompra.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoValorTotal.setText("");
            } else {
            }
        } else if (tipo == 2) {
            botaoCancelarCompra.setEnabled(botoes);
        }
    }
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroCancelamentoCompras.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCancelarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarCompraActionPerformed
        cancelamentoCompra = coletaDadosCampos();
        persistCancelamento.cadastrarCancelamento(cancelamentoCompra);
        compra.setCancelamentoCompra(cancelamentoCompra);
        persistCompra.atualizaCompra(compra);
        List<ItemCompra> itens = compra.getItemCompra();
        itens.stream().forEach((each) -> {
            Produtos altProduto = persistProdutos.pesquisaProdutos(each.getCodProduto());
            altProduto.setQuantidade(altProduto.getQuantidade().subtract(each.getQuantidade()));
            altProduto.setValorTotal(altProduto.getQuantidade().multiply(altProduto.getValorUnitario()));
            altProduto.setValorTotalVenda(altProduto.getQuantidade().multiply(altProduto.getValorUnitarioVenda()));
            persistProdutos.atualizaProduto(altProduto);
            persistProdutos.atualizaProduto(altProduto);
        });
        //Gera um lançamento no Caixa
        if (JOptionPane.showConfirmDialog(null, "Deseja extornar o Lançamento no Caixa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HistoricosDAO hd = new HistoricosDAO();
            HistoricoPadrao hp = hd.getHistorico(4);
            lancamento.setCod(persistLancamento.getProximoCodLancamento());
            lancamento.setDataLancamento(compra.getDataCompra());
            lancamento.setHistorico(hp);
            lancamento.setObservacoes(hp.getNomeHistorico() + compra.getCod() + " do Fornecedor: " + compra.getFornecedor().getRazaosocial());
            lancamento.setValorDebito(new BigDecimal("0.00"));
            lancamento.setValorCredito(compra.getValorTotalCompra());
            persistLancamento.cadastrarLancamento(lancamento);
        }
        this.dispose();
    }//GEN-LAST:event_botaoCancelarCompraActionPerformed

    private void campoDataCancelamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataCancelamentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoMotivo.requestFocus();
        }
    }//GEN-LAST:event_campoDataCancelamentoKeyPressed
    private void campoMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoCancelarCompra.requestFocus();
        }
    }//GEN-LAST:event_campoMotivoKeyPressed

    private void campoDataCancelamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataCancelamentoFocusGained
        campoDataCancelamento.setCaretPosition(0);
    }//GEN-LAST:event_campoDataCancelamentoFocusGained
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCancelamentoCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new CadastroCancelamentoCompras().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoCancelarCompra;
    private javax.swing.JTextField campoCodigoCompra;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDataCancelamento;
    private javax.swing.JTextField campoMotivo;
    private javax.swing.JTextField campoNomeCliente;
    private javax.swing.JTextField campoValorTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataCancelamento;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel labelTipo;
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
    public void mensagemCodHistoricoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override

    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
