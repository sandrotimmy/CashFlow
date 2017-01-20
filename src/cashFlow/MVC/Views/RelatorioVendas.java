
package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.VendasDAO;
import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.ItemVenda;
import cashFlow.MVC.Models.ParcelasVenda;
import cashFlow.MVC.Models.Vendas;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class RelatorioVendas extends javax.swing.JDialog {

    /**
     * Creates new form RelatorioFornecedor
     */
    public RelatorioVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }

    public RelatorioVendas() {
        initComponents();
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }
        public RelatorioVendas(String codVenda) {
        initComponents();
        campoCodVenda.setText(codVenda);    
            botaoEmitirActionPerformed(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoesFornecedor = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        campoCodVenda = new javax.swing.JTextField();
        botaoEmitir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Vendas");

        jLabel1.setText("Código da Venda:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(campoCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEmitir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoEmitir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Relatório de Vendas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEmitirActionPerformed

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
            if (listaItemVenda.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Itens não encontrados não encontrados!");
            } else {
                List<Empresa> listEmpresa = new ArrayList();
                listEmpresa.add(empresa);
                List<Clientes> listCliente = new ArrayList();
//                List <Integer> listaParcelamento= new ArrayList();
//                listaParcelamento.add(listaVenda.get(0).getParcelamentoVenda().getId());
                listCliente.add(cliente);
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaVenda);
                Map parametros = new HashMap();
                parametros.put("empresa", listEmpresa);
                parametros.put("cliente", listCliente);
                parametros.put("itemVenda", listaItemVenda);
                parametros.put("parcelasVenda", listaParcelas);
                parametros.put("SUBREPORT_DIR", new File("").getAbsolutePath() + "\\Relatorios\\");
                JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/Vendas.jasper");
                JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                JasperViewer viwer = new JasperViewer(print, false);
                viwer.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
        }
    }//GEN-LAST:event_botaoEmitirActionPerformed

    private void campoCodVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodVendaActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                RelatorioVendas dialog = new RelatorioVendas(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup grupoBotoesFornecedor;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
