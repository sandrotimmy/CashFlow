package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.FolhaDePagamentoCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Controllers.ParametrosCtrl;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.Empregados;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.PassaCamposComEnter;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CalculoFolhaPagamento extends javax.swing.JDialog implements InterfaceListener {

    private FolhaDePagamentoCtrl folhaDePagamentoCtrl;
    private Empregados empregado;
    private ConsultaEmpregado consultaEmpregados;
    private MetodosGerais mg;
    public PassaCamposComEnter pc;
    private Properties parametros;

    public CalculoFolhaPagamento() throws IOException {

        initComponents();
        folhaDePagamentoCtrl = new FolhaDePagamentoCtrl();
        empregado = new Empregados();
        consultaEmpregados = new ConsultaEmpregado();
        consultaEmpregados.setListener(this);
        botaoOk.setFocusTraversalKeysEnabled(false);
        mg = new MetodosGerais();
        pc = new PassaCamposComEnter();
        parametros = ParametrosCtrl.getInstance();
        carregaDados();
    }

    @Override
    public void mensagemCodCalculoFolhaPagamento(String codBusca) {

        int busca = Integer.parseInt(codBusca);
        empregado = folhaDePagamentoCtrl.getEmpregado(busca);
        campoCodEmpregado.setText(empregado.getCod().toString());
        campoNomeEmpregado.setText(empregado.getNome());
        botaoOk.setEnabled(true);
        botaoOk.requestFocus();
    }
    public void carregaDados(){
    campoCompetencia.setText(parametros.getProperty("competenciaFolha"));  
    campoCompetencia.setCaretPosition(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        labelCompetencia = new javax.swing.JLabel();
        campoCompetencia = new javax.swing.JTextField();
        labelNomeempregado = new javax.swing.JLabel();
        campoNomeEmpregado = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoOk = new javax.swing.JButton();
        campoCodEmpregado = new javax.swing.JTextField();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calculo Folha de Pagamento");

        labelCompetencia.setText("Competência:");

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/####");
            campoCompetencia = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoCompetencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCompetenciaKeyPressed(evt);
            }
        });

        labelNomeempregado.setText("Empregado:");

        campoNomeEmpregado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNomeEmpregado.setEnabled(false);

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoOk.setText("OK");
        botaoOk.setEnabled(false);
        botaoOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOkActionPerformed(evt);
            }
        });

        campoCodEmpregado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodEmpregadoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCompetencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelNomeempregado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCodEmpregado, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNomeEmpregado, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomeempregado)
                    .addComponent(campoNomeEmpregado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodEmpregado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void campoCodEmpregadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodEmpregadoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            empregado = folhaDePagamentoCtrl.getEmpregado(Integer.parseInt(campoCodEmpregado.getText()));
            if (empregado != null) {
                campoNomeEmpregado.setText(empregado.getNome());
                botaoOk.setEnabled(true);
                botaoOk.requestFocus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Empregado Inexistente!");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
            consultaEmpregados.setVisible(true);
            consultaEmpregados.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_campoCodEmpregadoKeyPressed

    private void botaoOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOkActionPerformed
        Date competencia = mg.convCompetenciaBanco(campoCompetencia.getText());
        FolhaDePagamentoView folhaDePagamento = new FolhaDePagamentoView(competencia, empregado);
        this.dispose();
        pc.passaCamposComEnter(folhaDePagamento);
        folhaDePagamento.setVisible(true);
        parametros.setProperty("competenciaFolha", campoCompetencia.getText().replaceAll("/", ""));
        try {
            parametros.store(new FileOutputStream("./parametros.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculoFolhaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculoFolhaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoOkActionPerformed

    private void campoCompetenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCompetenciaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            campoCodEmpregado.requestFocus();
        }
    }//GEN-LAST:event_campoCompetenciaKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CalculoFolhaPagamento dialog = null;
                try {
                    dialog = new CalculoFolhaPagamento();
                } catch (IOException ex) {
                    Logger.getLogger(CalculoFolhaPagamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoOk;
    private javax.swing.JTextField campoCodEmpregado;
    private javax.swing.JTextField campoCompetencia;
    private javax.swing.JTextField campoNomeEmpregado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelCompetencia;
    private javax.swing.JLabel labelNomeempregado;
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
    public void mensagemCodComissoes(String codComissao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodDescontos(String codDesconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
