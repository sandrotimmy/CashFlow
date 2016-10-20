/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Views;

import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.IntegerDocument;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
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
public class RelatorioEstoque extends javax.swing.JDialog {

    /**
     * Creates new form RelatorioEstoque
     */
    public RelatorioEstoque() {
        initComponents();
        radioTodosProdutos.setSelected(true);
        campoCodProduto.setDocument(new IntegerDocument(5));
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

        grupoFormaEmissao = new javax.swing.ButtonGroup();
        botaoEmitir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        radioTodosProdutos = new javax.swing.JRadioButton();
        radioPorCodigo = new javax.swing.JRadioButton();
        campoCodProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Estoque");

        botaoEmitir.setText("Emitir");
        botaoEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEmitirActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        grupoFormaEmissao.add(radioTodosProdutos);
        radioTodosProdutos.setText("Todos os Produtos");
        radioTodosProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioTodosProdutosMouseClicked(evt);
            }
        });
        radioTodosProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodosProdutosActionPerformed(evt);
            }
        });

        grupoFormaEmissao.add(radioPorCodigo);
        radioPorCodigo.setText("Por Código");
        radioPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPorCodigoMouseClicked(evt);
            }
        });

        campoCodProduto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoCodProduto.setEnabled(false);
        campoCodProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodProdutoActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoEmitir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioTodosProdutos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioPorCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(campoCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioTodosProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPorCodigo)
                    .addComponent(campoCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoEmitir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEmitirActionPerformed
        List<Produtos> lista;
        ProdutosDAO persistProdutos = new ProdutosDAO();
        if (radioTodosProdutos.isSelected()) {
            lista = persistProdutos.getListaProdutos();
        } else {
            lista = persistProdutos.getUmProduto(Integer.parseInt(campoCodProduto.getText()));
        }
        try {
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Produtos não encontrados!");
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
                JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/EstoqueProdutos.jasper");
                JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                JasperViewer viwer = new JasperViewer(print, false);
                viwer.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioLivroCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoEmitirActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void radioTodosProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodosProdutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioTodosProdutosActionPerformed

    private void campoCodProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodProdutoActionPerformed

    private void radioPorCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPorCodigoMouseClicked
        campoCodProduto.setEnabled(true);
        campoCodProduto.requestFocus();
    }//GEN-LAST:event_radioPorCodigoMouseClicked

    private void radioTodosProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTodosProdutosMouseClicked
        campoCodProduto.setEnabled(false);
        campoCodProduto.setText("");
    }//GEN-LAST:event_radioTodosProdutosMouseClicked

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
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioEstoque dialog = new RelatorioEstoque();
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
    private javax.swing.JButton botaoEmitir;
    private javax.swing.JTextField campoCodProduto;
    private javax.swing.ButtonGroup grupoFormaEmissao;
    private javax.swing.JRadioButton radioPorCodigo;
    private javax.swing.JRadioButton radioTodosProdutos;
    // End of variables declaration//GEN-END:variables
}
