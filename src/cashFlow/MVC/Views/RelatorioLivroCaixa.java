package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.Lancamentos;
import java.io.File;
import java.math.BigDecimal;
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

public class RelatorioLivroCaixa extends javax.swing.JDialog {

    private final MetodosGerais mg;

    RelatorioLivroCaixa() {
        initComponents();
        mg = new MetodosGerais();
        campoDataInicial.setText(mg.getDataInicial());//pega primeiro dia do mes para o filtro
        campoDataFinal.setText(mg.getDataFinal());//pega primeiro dia do mes para o filtro
        botaoOK.setFocusTraversalKeysEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoOK = new javax.swing.JButton();
        campoDataFinal = new javax.swing.JTextField();
        labelDataFinal = new javax.swing.JLabel();
        campoDataInicial = new javax.swing.JTextField();
        labelDataInicial = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        botaoOK.setText("OK");
        botaoOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOKActionPerformed(evt);
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

        labelDataFinal.setText("Data Final:");

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

        labelDataInicial.setText("Data Inicial:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoOK, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDataInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOKActionPerformed
        try {
            LancamentosDAO persistLancamento = new LancamentosDAO();
            List<Lancamentos> lista = persistLancamento.getListaLancamentos(mg.convDataBanco(campoDataInicial.getText()), mg.convDataBanco(campoDataFinal.getText()));
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há lançamentos no periodo selecionado!");
            } else {
                EmpresasDAO persistEmpresa = new EmpresasDAO();
                Empresa temp = persistEmpresa.getCadastroEmpresa();
                List <Empresa> empresa = new ArrayList();
                empresa.add(temp);
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
                java.sql.Date dataAnterior;
                dataAnterior = mg.convDataBanco(campoDataInicial.getText());
                dataAnterior.setDate(dataAnterior.getDate() - 1);//Pega o dia anterior ao periodo selecionado
                BigDecimal totalSaldoAnterior = persistLancamento.getSaldoPeriodo(mg.convDataBanco("01/01/1900"), dataAnterior);
                Map parametros = new HashMap();
                parametros.put("empresa", empresa);
                parametros.put("periodo", campoDataInicial.getText() +" à " + campoDataFinal.getText());
                parametros.put("saldoAnterior", totalSaldoAnterior);
                System.out.println(new File("").getAbsolutePath()+"\\Relatorios\\");
                parametros.put ("SUBREPORT_DIR",new File("").getAbsolutePath()+"\\Relatorios\\");
                JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/FluxoCaixa.jasper");
                JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                JasperViewer viwer = new JasperViewer(print, false);
                viwer.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioLivroCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoOKActionPerformed
    private void campoDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFinalFocusGained
        campoDataFinal.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFinalFocusGained
    private void campoDataInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataInicialFocusGained
        campoDataInicial.setCaretPosition(0);
    }//GEN-LAST:event_campoDataInicialFocusGained
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            RelatorioLivroCaixa dialog = new RelatorioLivroCaixa();
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoOK;
    private javax.swing.JTextField campoDataFinal;
    private javax.swing.JTextField campoDataInicial;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    // End of variables declaration//GEN-END:variables
}
