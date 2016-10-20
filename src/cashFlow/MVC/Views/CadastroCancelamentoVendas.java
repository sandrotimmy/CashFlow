package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.CancelamentoVendasDAO;
import cashFlow.MVC.DAO.HistoricosDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.CancelamentoVendas;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.HistoricoPadrao;
import cashFlow.MVC.Models.ItemVenda;
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

public class CadastroCancelamentoVendas extends javax.swing.JDialog implements InterfaceListener {

    private final CancelamentoVendasDAO persistCancelamento;//persistencia dos dados
    private final VendasDAO persistVenda;
    private int posicao;//controla o caminhamento do cadastro
    private final ConsultaVendas consultaVenda;
    private InterfaceListener listner = null;
    private final NumberFormat f;
    private Vendas venda;
    private CancelamentoVendas cancelamentoVenda;
    private final ProdutosDAO persistProdutos;
    private final Lancamentos lancamento;
    private final LancamentosDAO persistLancamento;

    public CadastroCancelamentoVendas() {
        initComponents();//inicia componentes da tela
        this.persistCancelamento = new CancelamentoVendasDAO();
        this.cancelamentoVenda = new CancelamentoVendas();
        this.persistVenda = new VendasDAO();
        this.persistProdutos = new ProdutosDAO();
        this.lancamento = new Lancamentos();
        this.persistLancamento = new LancamentosDAO();
        consultaVenda = new ConsultaVendas();
        f = NumberFormat.getCurrencyInstance();//formata o numero no textField
        consultaVenda.setListener(this);
        setCamposFocus();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        this.venda = venda;
        campoCodigoVenda.setText(Integer.toString(venda.getCod()));
        String data = persistCancelamento.convDataSistema(venda.getDataVenda());
        campoData.setText(data);
        campoDataCancelamento.setText(cancelamentoVenda.getDataAtual());//pega a data do Computador
        campoNomeCliente.setText(venda.getCliente().getNome());
        campoValorTotal.setText(f.format(venda.getValorTotalVenda()));
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        campoDataCancelamento.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoCancelar = new javax.swing.JButton();
        botaoCancelarVenda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        campoCodigoVenda = new javax.swing.JTextField();
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

        botaoCancelarVenda.setText("OK");
        botaoCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarVendaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dados da Nota:"));

        labelCodigo.setText("Venda:");

        campoCodigoVenda.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        campoCodigoVenda.setForeground(new java.awt.Color(0, 51, 255));
        campoCodigoVenda.setBorder(null);
        campoCodigoVenda.setDisabledTextColor(new java.awt.Color(0, 51, 255));
        campoCodigoVenda.setEnabled(false);

        labelTipo.setText("Cliente:");

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
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCodigo)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCodigoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelHistorico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(campoCodigoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(campoMotivo)
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
                        .addComponent(botaoCancelarVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoCancelarVenda});

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
                    .addComponent(botaoCancelarVenda))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private CancelamentoVendas coletaDadosCampos() {
        cancelamentoVenda = new CancelamentoVendas(
                persistCancelamento.convDataBanco(campoDataCancelamento.getText()),
                campoMotivo.getText(),
                this.venda
        );
        return cancelamentoVenda;
    }

    private void setCamposFocus() {
        botaoCancelarVenda.setFocusTraversalKeysEnabled(false);
    }

    private void limitaCampos() {//limita os campos de texto
        campoMotivo.setDocument(new DocumentoLimitado(300));
    }

//    private void exibeDados(HistoricoPadrao historico) {
//        campoCodigoVenda.setText(Integer.toString(historico.getCod()));
//        campoValorTotal.setText(historico.getNomeHistorico());
//    }
    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoValorTotal.setEnabled(campos);
//        comboUsuarioSistema.setEnabled(campos);
        botaoCancelarVenda.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoValorTotal.setText("");
            } else {
            }
        } else if (tipo == 2) {
            botaoCancelarVenda.setEnabled(botoes);
        }
    }
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroCancelamentoVendas.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarVendaActionPerformed
        cancelamentoVenda = coletaDadosCampos();
        persistCancelamento.cadastrarCancelamento(cancelamentoVenda);
        venda.setCancelamentoVenda(cancelamentoVenda);
        persistVenda.atualizaVenda(venda);
            List <ItemVenda> itens = venda.getItemVenda();
            itens.stream().forEach((each) -> {
                Produtos altProduto = persistProdutos.pesquisaProdutos(each.getCodProduto());
                altProduto.setQuantidade(altProduto.getQuantidade().add(each.getQuantidade()));
                altProduto.setValorUnitario(each.getValorUnitario());
                altProduto.setValorTotal(altProduto.getQuantidade().multiply(each.getValorUnitario()));
                altProduto.setValorTotalVenda(altProduto.getQuantidade().multiply(altProduto.getValorUnitarioVenda()));
                persistProdutos.atualizaProduto(altProduto);
            });
            //Gera um lançamento no Caixa
            if (JOptionPane.showConfirmDialog(null, "Deseja extornar o Lançamento no Caixa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                HistoricosDAO hd = new HistoricosDAO();
                HistoricoPadrao hp = hd.getHistorico(3);
                lancamento.setCod(persistLancamento.getProximoCodLancamento());
                lancamento.setDataLancamento(venda.getDataVenda());
                lancamento.setHistorico(hp);
                lancamento.setObservacoes(hp.getNomeHistorico() + venda.getCod() + " do Cliente: " + venda.getCliente().getNome());
                lancamento.setValorDebito(new BigDecimal("0.00"));
                lancamento.setValorCredito(venda.getValorTotalVenda());
                persistLancamento.cadastrarLancamento(lancamento);
            }
        this.dispose();
    }//GEN-LAST:event_botaoCancelarVendaActionPerformed

    private void campoDataCancelamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDataCancelamentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            campoMotivo.requestFocus();
        }
    }//GEN-LAST:event_campoDataCancelamentoKeyPressed
    private void campoMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMotivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            botaoCancelarVenda.requestFocus();
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
            java.util.logging.Logger.getLogger(CadastroCancelamentoVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new CadastroCancelamentoVendas().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoCancelarVenda;
    private javax.swing.JTextField campoCodigoVenda;
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
    public void compraCancelar(Compras compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
