package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.DAO.LancamentosDAO;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.Lancamentos;
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

public class RelatorioCustos extends javax.swing.JDialog {

    private final MetodosGerais mg;

    RelatorioCustos() {
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
        jPanel1 = new javax.swing.JPanel();
        campoDataInicial = new javax.swing.JTextField();
        labelDataInicial = new javax.swing.JLabel();
        labelDataFinal = new javax.swing.JLabel();
        campoDataFinal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        checkPorHistorico = new javax.swing.JCheckBox();
        campoPorHistorico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Custos e Receitas");

        botaoOK.setText("OK");
        botaoOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOKActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Período:"));

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

        labelDataFinal.setText("Data Final:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataInicial)
                        .addGap(29, 29, 29)
                        .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataFinal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opções:"));

        checkPorHistorico.setText("Somente o Histórico");
        checkPorHistorico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkPorHistoricoItemStateChanged(evt);
            }
        });
        checkPorHistorico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkPorHistoricoStateChanged(evt);
            }
        });

        campoPorHistorico.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(checkPorHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoPorHistorico)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkPorHistorico)
                    .addComponent(campoPorHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoOK, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOKActionPerformed
        try {
            LancamentosDAO persistLancamento = new LancamentosDAO();
            List<Lancamentos> lista = persistLancamento.getListaLancamentos(mg.convDataBanco(campoDataInicial.getText()), mg.convDataBanco(campoDataFinal.getText()));

            if (checkPorHistorico.isSelected()) {
                int codHistorico = Integer.parseInt(campoPorHistorico.getText());
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getHistorico().getCod() != codHistorico) {
                        lista.remove(i);
                        i--;
                    }
                }
            }
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há lançamentos para os Critérios Selecionados!");
            } else {
                EmpresasDAO persistEmpresa = new EmpresasDAO();
                Empresa temp = persistEmpresa.getCadastroEmpresa();
                List<Empresa> empresa = new ArrayList();
                empresa.add(temp);
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
                java.sql.Date dataAnterior;
                dataAnterior = mg.convDataBanco(campoDataInicial.getText());
                dataAnterior.setTime(dataAnterior.getTime() - 1);//Pega o dia anterior ao periodo selecionado
                BigDecimal totalSaldoAnterior = persistLancamento.getSaldoPeriodo(mg.convDataBanco("01/01/1900"), dataAnterior);
                Map parametros = new HashMap();
                parametros.put("empresa", empresa);
                parametros.put("periodo", campoDataInicial.getText() + " à " + campoDataFinal.getText());
                parametros.put("saldoAnterior", totalSaldoAnterior);
                System.out.println(new File("").getAbsolutePath() + "\\Relatorios\\");
                parametros.put("SUBREPORT_DIR", new File("").getAbsolutePath() + "\\Relatorios\\");
                JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile("./Relatorios/CustosPorHistorico.jasper");
                JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ds);
                JasperViewer viwer = new JasperViewer(print, false);
                viwer.setVisible(true);
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "O relatório não pode ser impresso!" + ex.getCause().toString());
        }
    }//GEN-LAST:event_botaoOKActionPerformed
    private void campoDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFinalFocusGained
        campoDataFinal.setCaretPosition(0);
    }//GEN-LAST:event_campoDataFinalFocusGained
    private void campoDataInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataInicialFocusGained
        campoDataInicial.setCaretPosition(0);
    }//GEN-LAST:event_campoDataInicialFocusGained

    private void checkPorHistoricoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkPorHistoricoStateChanged
    }//GEN-LAST:event_checkPorHistoricoStateChanged

    private void checkPorHistoricoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkPorHistoricoItemStateChanged
        if (checkPorHistorico.isSelected()) {
            campoPorHistorico.setEnabled(true);
        } else {
            campoPorHistorico.setText("");
            campoPorHistorico.setEnabled(true);
            campoPorHistorico.requestFocus();
        }
    }//GEN-LAST:event_checkPorHistoricoItemStateChanged
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            RelatorioCustos dialog = new RelatorioCustos();
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
    private javax.swing.JTextField campoPorHistorico;
    private javax.swing.JCheckBox checkPorHistorico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    // End of variables declaration//GEN-END:variables
}
