/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.ClassificacaoProdutosCtrl;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.ProdutosDAO;
import cashFlow.MVC.Models.ClassificacaoProdutos;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.IntegerDocument;
import cashFlow.MVC.Models.Produtos;
import java.io.File;
import java.math.BigDecimal;
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
public class RelatorioEstoque extends javax.swing.JDialog {

    /**
     * Creates new form RelatorioEstoque
     */
    private final ClassificacaoProdutosCtrl classificacaoProdutos;
    private final List<ClassificacaoProdutos> listaClassificacaoProdutos;

    public RelatorioEstoque() {
        initComponents();
        classificacaoProdutos = new ClassificacaoProdutosCtrl();
        listaClassificacaoProdutos = classificacaoProdutos.getListaClassificacao();
        radioTodosProdutos.setSelected(true);
        campoCodProduto.setDocument(new IntegerDocument(5));
        botaoEmitir.setFocusTraversalKeysEnabled(false);
        carregaClassificacao();
    }

    public void carregaClassificacao() {
        for (int i = 0; i < listaClassificacaoProdutos.size(); i++) {
            comboClassificacao.addItem(listaClassificacaoProdutos.get(i).getNomeClassificacao());
        }
    }
    public ClassificacaoProdutos buscaClassificacao(String buscar) {
        for (int i = 0; i < listaClassificacaoProdutos.size(); i++) {
            if (buscar.equals(listaClassificacaoProdutos.get(i).getNomeClassificacao())) {
                return listaClassificacaoProdutos.get(i);
            }
        }
        return listaClassificacaoProdutos.get(0);
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
        grupoClassificacao = new javax.swing.ButtonGroup();
        botaoEmitir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        painelEmitir = new javax.swing.JPanel();
        campoCodProduto = new javax.swing.JTextField();
        radioPorCodigo = new javax.swing.JRadioButton();
        radioTodosProdutos = new javax.swing.JRadioButton();
        radioPorClassificacao = new javax.swing.JRadioButton();
        comboClassificacao = new javax.swing.JComboBox<>();
        painelClassificar = new javax.swing.JPanel();
        radioClassPorNome = new javax.swing.JRadioButton();
        radioClassPorCodigo = new javax.swing.JRadioButton();
        painelOpcoes = new javax.swing.JPanel();
        checkZerados = new javax.swing.JCheckBox();

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

        painelEmitir.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Emitir:"));

        campoCodProduto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoCodProduto.setEnabled(false);
        campoCodProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodProdutoActionPerformed(evt);
            }
        });

        grupoFormaEmissao.add(radioPorCodigo);
        radioPorCodigo.setText("Somente o código");
        radioPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPorCodigoMouseClicked(evt);
            }
        });
        radioPorCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPorCodigoActionPerformed(evt);
            }
        });

        grupoFormaEmissao.add(radioTodosProdutos);
        radioTodosProdutos.setSelected(true);
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

        grupoFormaEmissao.add(radioPorClassificacao);
        radioPorClassificacao.setText("Somente a Classificação");
        radioPorClassificacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioPorClassificacaoItemStateChanged(evt);
            }
        });
        radioPorClassificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPorClassificacaoMouseClicked(evt);
            }
        });

        comboClassificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboClassificacao.setEnabled(false);

        javax.swing.GroupLayout painelEmitirLayout = new javax.swing.GroupLayout(painelEmitir);
        painelEmitir.setLayout(painelEmitirLayout);
        painelEmitirLayout.setHorizontalGroup(
            painelEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEmitirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEmitirLayout.createSequentialGroup()
                        .addComponent(radioPorClassificacao)
                        .addGap(16, 16, 16)
                        .addComponent(comboClassificacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelEmitirLayout.createSequentialGroup()
                        .addComponent(radioTodosProdutos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelEmitirLayout.createSequentialGroup()
                        .addComponent(radioPorCodigo)
                        .addGap(46, 46, 46)
                        .addComponent(campoCodProduto)))
                .addContainerGap())
        );
        painelEmitirLayout.setVerticalGroup(
            painelEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEmitirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioTodosProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPorCodigo)
                    .addComponent(campoCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPorClassificacao)
                    .addComponent(comboClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        painelClassificar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Classificar:"));

        grupoClassificacao.add(radioClassPorNome);
        radioClassPorNome.setSelected(true);
        radioClassPorNome.setText("Por Nome do Produto");

        grupoClassificacao.add(radioClassPorCodigo);
        radioClassPorCodigo.setText("Por código do Produto");

        javax.swing.GroupLayout painelClassificarLayout = new javax.swing.GroupLayout(painelClassificar);
        painelClassificar.setLayout(painelClassificarLayout);
        painelClassificarLayout.setHorizontalGroup(
            painelClassificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClassificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelClassificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioClassPorNome)
                    .addComponent(radioClassPorCodigo))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        painelClassificarLayout.setVerticalGroup(
            painelClassificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClassificarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioClassPorNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioClassPorCodigo))
        );

        painelOpcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opções:"));

        checkZerados.setSelected(true);
        checkZerados.setText("Não emitir produtos zerados");

        javax.swing.GroupLayout painelOpcoesLayout = new javax.swing.GroupLayout(painelOpcoes);
        painelOpcoes.setLayout(painelOpcoesLayout);
        painelOpcoesLayout.setHorizontalGroup(
            painelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkZerados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelOpcoesLayout.setVerticalGroup(
            painelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkZerados))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelEmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoEmitir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addComponent(painelClassificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelClassificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        if (radioPorCodigo.isSelected()) {
            lista = persistProdutos.getUmProduto(Integer.parseInt(campoCodProduto.getText()));
        } else {
            if (radioClassPorNome.isSelected()) {
                lista = persistProdutos.getListaProdutosPorNome();
            } else {
                lista = persistProdutos.getListaProdutos();
            }
        }
        //retira produtos não selecionados
        if (radioPorClassificacao.isSelected()) {
            String buscar = comboClassificacao.getSelectedItem().toString();
            for (int i = 0; i < lista.size(); i++) {
                String classificacao;
                if (lista.get(i).getClassificacaoProdutos()!=null) {
                    classificacao = lista.get(i).getClassificacaoProdutos().getNomeClassificacao();
                } else {
                classificacao = " ";
                }
                if (!buscar.equals(classificacao)) {
                    lista.remove(i);
                    i--;
                }
            }
        }

        //retira os produtos zerados
        if (checkZerados.isSelected()) {
            for (int i = 0; i < lista.size(); i++) {
                BigDecimal temp = lista.get(i).getQuantidade();
                boolean btemp = temp.compareTo(BigDecimal.ZERO) <= 0;
                if (btemp) {
                    lista.remove(i);
                    i--;
                }
            }
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
        comboClassificacao.setEnabled(false);
        comboClassificacao.setSelectedItem(" ");
        campoCodProduto.requestFocus();
    }//GEN-LAST:event_radioPorCodigoMouseClicked

    private void radioTodosProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTodosProdutosMouseClicked
        campoCodProduto.setEnabled(false);
        comboClassificacao.setEnabled(false);
        comboClassificacao.setSelectedItem(" ");
        campoCodProduto.setText("");
    }//GEN-LAST:event_radioTodosProdutosMouseClicked

    private void radioPorCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPorCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPorCodigoActionPerformed

    private void radioPorClassificacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioPorClassificacaoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPorClassificacaoItemStateChanged

    private void radioPorClassificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPorClassificacaoMouseClicked
        campoCodProduto.setEnabled(false);
        comboClassificacao.setEnabled(true);
        campoCodProduto.setText("");
    }//GEN-LAST:event_radioPorClassificacaoMouseClicked

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
    private javax.swing.JCheckBox checkZerados;
    private javax.swing.JComboBox<String> comboClassificacao;
    private javax.swing.ButtonGroup grupoClassificacao;
    private javax.swing.ButtonGroup grupoFormaEmissao;
    private javax.swing.JPanel painelClassificar;
    private javax.swing.JPanel painelEmitir;
    private javax.swing.JPanel painelOpcoes;
    private javax.swing.JRadioButton radioClassPorCodigo;
    private javax.swing.JRadioButton radioClassPorNome;
    private javax.swing.JRadioButton radioPorClassificacao;
    private javax.swing.JRadioButton radioPorCodigo;
    private javax.swing.JRadioButton radioTodosProdutos;
    // End of variables declaration//GEN-END:variables
}
