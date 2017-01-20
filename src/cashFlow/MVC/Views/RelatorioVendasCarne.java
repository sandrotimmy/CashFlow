
package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.ParametrosCtrl;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.ItemVenda;
import cashFlow.MVC.Models.ParcelasVenda;
import cashFlow.MVC.Models.Vendas;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioVendasCarne extends javax.swing.JDialog {

    public RelatorioVendasCarne() {
        initComponents();
        carregaDados();
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }
    public RelatorioVendasCarne(String codVenda) {
        initComponents();
        carregaDados();
        campoCodVenda.setText(codVenda);
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }
    private RelatorioVendasCarne(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void carregaDados() {
        try {
            Properties parametros = ParametrosCtrl.getInstance();
            campoInstrucoes.setLineWrap(true);
            campoInstrucoes.setWrapStyleWord(true);
            campoLocalPgto.setText(parametros.getProperty("localPagamento"));
            campoInstrucoes.setText(parametros.getProperty("instrucoesCarne"));
        } catch (IOException ex) {
            Logger.getLogger(RelatorioVendasCarne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoesFornecedor = new javax.swing.ButtonGroup();
        labelCodVenda = new javax.swing.JLabel();
        campoCodVenda = new javax.swing.JTextField();
        botaoEmitir = new javax.swing.JButton();
        labelLocalPgto = new javax.swing.JLabel();
        painelInstrucoes = new javax.swing.JScrollPane();
        campoInstrucoes = new javax.swing.JTextArea();
        campoLocalPgto = new javax.swing.JTextField();
        labelInstrucoes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Vendas");

        labelCodVenda.setText("Código da Venda:");

        campoCodVenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoCodVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodVendaActionPerformed(evt);
            }
        });

        botaoEmitir.setText("Emitir");
        botaoEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEmitirActionPerformed(evt);
            }
        });

        labelLocalPgto.setText("Local de Pagamento:");

        campoInstrucoes.setColumns(20);
        campoInstrucoes.setRows(5);
        painelInstrucoes.setViewportView(campoInstrucoes);

        campoLocalPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoLocalPgtoActionPerformed(evt);
            }
        });

        labelInstrucoes.setText("Instruções:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoEmitir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelCodVenda)
                                .addComponent(campoCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelLocalPgto)
                                .addComponent(painelInstrucoes, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                .addComponent(campoLocalPgto))
                            .addComponent(labelInstrucoes))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLocalPgto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoLocalPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInstrucoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelInstrucoes, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEmitir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEmitirActionPerformed
        try {
            Properties propriedades = ParametrosCtrl.getInstance();
            propriedades.setProperty("localPagamento", campoLocalPgto.getText());
            propriedades.setProperty("instrucoesCarne", campoInstrucoes.getText());
            propriedades.store(new FileOutputStream("./parametros.properties"), null);
            List<Vendas> listaVenda;
            EmpresasDAO persistEmpresa = new EmpresasDAO();
            VendasDAO persistVendas = new VendasDAO();
            Empresa empresa = persistEmpresa.getCadastroEmpresa();
            listaVenda = persistVendas.getVenda(Integer.parseInt(campoCodVenda.getText()));
            Clientes cliente = listaVenda.get(0).getCliente();
            List<ItemVenda> listaItemVenda = listaVenda.get(0).getItemVenda();
            List<ParcelasVenda> listaParcelas = new ArrayList();

            if (listaVenda.get(0).getParcelamentoVenda() != null) {
                listaParcelas = listaVenda.get(0).getParcelamentoVenda().getParcelasVenda();
            }

            try {
                if (listaParcelas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada ou não parcelada!");
                } else {
                    List<Clientes> listCliente = new ArrayList();
                    listCliente.add(cliente);
                    JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaParcelas);
                    Map parametros = new HashMap();
                    parametros.put("cliente", listCliente);
                    parametros.put("codVenda", listaVenda.get(0).getCod());
                    parametros.put("qtdParcelas", listaVenda.get(0).getParcelamentoVenda().getNumParcelas());
                    parametros.put("dataVenda", listaVenda.get(0).getDataVenda());
                    parametros.put("nomeEmpresa", empresa.getNomeEmpresa());
                    parametros.put("cpfCnpj", empresa.getCpfCnpj());
                    parametros.put("numDocumento", listaVenda.get(0).getParcelamentoVenda().getId());
                    parametros.put("localPgto", campoLocalPgto.getText());
                    parametros.put("instrucoes", campoInstrucoes.getText());                    
                    parametros.put("SUBREPORT_DIR", new File("").getAbsolutePath() + "\\Relatorios\\");
                    JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/sub_parcelas_venda_carne.jasper");
                    JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                    JasperViewer viwer = new JasperViewer(print, false);
                    viwer.setVisible(true);
                }
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(RelatorioVendasCarne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoEmitirActionPerformed

    private void campoCodVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodVendaActionPerformed

    private void campoLocalPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoLocalPgtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoLocalPgtoActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioVendasCarne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendasCarne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendasCarne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendasCarne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioVendasCarne dialog = new RelatorioVendasCarne(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botaoEmitir;
    private javax.swing.JTextField campoCodVenda;
    private javax.swing.JTextArea campoInstrucoes;
    private javax.swing.JTextField campoLocalPgto;
    private javax.swing.ButtonGroup grupoBotoesFornecedor;
    private javax.swing.JLabel labelCodVenda;
    private javax.swing.JLabel labelInstrucoes;
    private javax.swing.JLabel labelLocalPgto;
    private javax.swing.JScrollPane painelInstrucoes;
    // End of variables declaration//GEN-END:variables
}
