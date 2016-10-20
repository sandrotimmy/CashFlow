/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Views;

import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.DAO.ClientesDAO;
import cashFlow.MVC.DAO.EmpresasDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RelatorioClientes extends javax.swing.JDialog {

    /**
     * Creates new form RelatorioFornecedor
     */
    public RelatorioClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        radioTodosClientes.setSelected(true);
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }

    public RelatorioClientes() {
        initComponents();
        radioTodosClientes.setSelected(true);
        botaoEmitir.setFocusTraversalKeysEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoesFornecedor = new javax.swing.ButtonGroup();
        botaoEmitir = new javax.swing.JButton();
        radioTodosClientes = new javax.swing.JRadioButton();
        radioPorCodigo = new javax.swing.JRadioButton();
        campoCodCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        botaoEmitir.setText("Emitir");
        botaoEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEmitirActionPerformed(evt);
            }
        });

        grupoBotoesFornecedor.add(radioTodosClientes);
        radioTodosClientes.setText("Todos os Clientes");
        radioTodosClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioTodosClientesMouseClicked(evt);
            }
        });
        radioTodosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodosClientesActionPerformed(evt);
            }
        });

        grupoBotoesFornecedor.add(radioPorCodigo);
        radioPorCodigo.setText("Por Código");
        radioPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPorCodigoMouseClicked(evt);
            }
        });

        campoCodCliente.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoCodCliente.setEnabled(false);
        campoCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 155, Short.MAX_VALUE)
                        .addComponent(botaoEmitir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioTodosClientes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioPorCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioTodosClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPorCodigo)
                    .addComponent(campoCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(botaoEmitir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEmitirActionPerformed
        List<Clientes> lista;
        ClientesDAO persistClientes = new ClientesDAO();
        if (radioTodosClientes.isSelected()) {
            lista = persistClientes.getListaClientes();
        } else {
            lista = persistClientes.getUmCliente(Integer.parseInt(campoCodCliente.getText()));
        }

        try {
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "clientes não encontrados!");
            } else {
                EmpresasDAO persistEmpresa = new EmpresasDAO();
                Empresa temp = persistEmpresa.getCadastroEmpresa();
                List<Empresa> empresa = new ArrayList();
                empresa.add(temp);
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
                Map parametros = new HashMap();
                parametros.put("empresa", empresa);
                System.out.println(new File("").getAbsolutePath() + "\\Relatorios\\");
                parametros.put("SUBREPORT_DIR", new File("").getAbsolutePath() + "\\Relatorios\\");
                JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/Clientes.jasper");
                JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                JasperViewer viwer = new JasperViewer(print, false);
                viwer.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioLivroCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_botaoEmitirActionPerformed

    private void radioTodosClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTodosClientesMouseClicked
        campoCodCliente.setEnabled(false);
        campoCodCliente.setText("");
    }//GEN-LAST:event_radioTodosClientesMouseClicked

    private void radioTodosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodosClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioTodosClientesActionPerformed

    private void radioPorCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPorCodigoMouseClicked
        campoCodCliente.setEnabled(true);
        campoCodCliente.requestFocus();
    }//GEN-LAST:event_radioPorCodigoMouseClicked

    private void campoCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodClienteActionPerformed

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
            java.util.logging.Logger.getLogger(RelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioClientes dialog = new RelatorioClientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField campoCodCliente;
    private javax.swing.ButtonGroup grupoBotoesFornecedor;
    private javax.swing.JRadioButton radioPorCodigo;
    private javax.swing.JRadioButton radioTodosClientes;
    // End of variables declaration//GEN-END:variables
}
