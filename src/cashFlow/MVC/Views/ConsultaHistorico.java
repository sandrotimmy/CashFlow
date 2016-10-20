
package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.HistoricoPadraoCtrl;
import cashFlow.MVC.Models.HistoricoPadrao;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public final class ConsultaHistorico extends javax.swing.JDialog {

    private final List <HistoricoPadrao> listaHistoricos;
    private final HistoricoPadraoCtrl historicoPadraoCtrl;
    private final DefaultTableModel val;
    private InterfaceListener listner = null;

    public ConsultaHistorico(){
        initComponents();
        campoBusca.requestFocus();
        setModal(true);
        setLocationRelativeTo(null);
        historicoPadraoCtrl = new HistoricoPadraoCtrl();
        listaHistoricos = historicoPadraoCtrl.getListaHistorico();
        val = (DefaultTableModel) tabelaCosultaHistorico.getModel();
        carregaTabela();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }
    public InterfaceListener getListener() {
        return listner;
    }
    public void carregaTabela() {
        listaHistoricos.stream().forEach((listaHistorico) -> {
            val.addRow(new String[]{
                Integer.toString(listaHistorico.getCod()),
                listaHistorico.getTipo(),
                listaHistorico.getNomeHistorico()});
        });

    }  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelBusca = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCosultaHistorico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Históricos");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelBusca.setText("Busca:");

        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscaKeyReleased(evt);
            }
        });

        tabelaCosultaHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo:", "Tipo:", "Descrição:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCosultaHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCosultaHistoricoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCosultaHistorico);
        if (tabelaCosultaHistorico.getColumnModel().getColumnCount() > 0) {
            tabelaCosultaHistorico.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaCosultaHistorico.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaCosultaHistorico.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaCosultaHistorico.getColumnModel().getColumn(1).setMinWidth(60);
            tabelaCosultaHistorico.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabelaCosultaHistorico.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBusca)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusca)
                    .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void campoBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            int quantCar = campoBusca.getText().length();//pega quantas letras foram digitadas
            val.setRowCount(0);//zera a tabela para popular com novo resultado refinado
            listaHistoricos.stream().forEach((listaHistorico) -> {
                String pesquisa = campoBusca.getText();
                String lista = "";
                if (listaHistorico.getNomeHistorico().length() >= quantCar) {
                    lista = listaHistorico.getNomeHistorico().substring(0, quantCar);
                }
                if (pesquisa.equalsIgnoreCase(lista)) {
                    val.addRow(new String[]{Integer.toString(listaHistorico.getCod()), listaHistorico.getTipo(), listaHistorico.getNomeHistorico()});
                }
            });
        }
    }//GEN-LAST:event_campoBuscaKeyReleased
    private void tabelaCosultaHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCosultaHistoricoMouseClicked
        if (evt.getClickCount() == 2 && listner != null) {
            if (getListener() instanceof CadastroLancamentos){
            listner.mensagemCodHistorico(tabelaCosultaHistorico.getValueAt(tabelaCosultaHistorico.getSelectedRow(), 0).toString());
            }else{
            listner.mensagemCodHistoricoBusca(tabelaCosultaHistorico.getValueAt(tabelaCosultaHistorico.getSelectedRow(), 0).toString());
            }
            ConsultaHistorico.this.dispose();
            try {
                Robot robo = new Robot();
                robo.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
                Logger.getLogger(ConsultaHistorico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabelaCosultaHistoricoMouseClicked
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        ConsultaHistorico.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new ConsultaHistorico().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBusca;
    private javax.swing.JTable tabelaCosultaHistorico;
    // End of variables declaration//GEN-END:variables
}
