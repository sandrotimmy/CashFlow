package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.EmpregadosDAO;
import cashFlow.MVC.Models.Empregados;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public final class ConsultaEmpregado extends javax.swing.JDialog {

    private final List<Empregados> listaEmpregados;
    private final EmpregadosDAO persist;
    private final DefaultTableModel val;
    private InterfaceListener listner = null;

    public ConsultaEmpregado() {
        initComponents();
        campoBusca.requestFocus();
        setModal(true);
        setLocationRelativeTo(null);
        persist = new EmpregadosDAO();
        listaEmpregados = persist.getListaEmpregados();
        val = (DefaultTableModel) tabelaCosultaEmpregados.getModel();
        carregaTabela();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    public void carregaTabela() {
        listaEmpregados.stream().forEach((Empregados listaEmpregados) -> {
            val.addRow(new String[]{
                Integer.toString(listaEmpregados.getCod()),
                listaEmpregados.getNome(),
                listaEmpregados.getEndereco(),
                listaEmpregados.getNumero()});
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelBusca = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCosultaEmpregados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Empregados");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelBusca.setText("Busca:");

        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBuscaKeyTyped(evt);
            }
        });

        tabelaCosultaEmpregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo:", "Nome:", "Endereço:", "Numero:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCosultaEmpregados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCosultaEmpregadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCosultaEmpregados);
        if (tabelaCosultaEmpregados.getColumnModel().getColumnCount() > 0) {
            tabelaCosultaEmpregados.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaCosultaEmpregados.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaCosultaEmpregados.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaCosultaEmpregados.getColumnModel().getColumn(1).setMinWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(1).setMaxWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(2).setMinWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(2).setMaxWidth(250);
            tabelaCosultaEmpregados.getColumnModel().getColumn(3).setMinWidth(70);
            tabelaCosultaEmpregados.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaCosultaEmpregados.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
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

    private void campoBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyPressed

    }//GEN-LAST:event_campoBuscaKeyPressed

    private void campoBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyTyped

    }//GEN-LAST:event_campoBuscaKeyTyped

    private void campoBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            int quantCar = campoBusca.getText().length();//pega quantas letras foram digitadas
            val.setRowCount(0);//zera a tabela para popular com novo resultado refinado
            listaEmpregados.stream().forEach((Empregados listaEmpregados) -> {
                String pesquisa = campoBusca.getText();
                String lista = "";
                listaEmpregados.getNome().length();
                if (listaEmpregados.getNome().length() >= quantCar) {
                    lista = listaEmpregados.getNome().substring(0, quantCar);
                }
                if (pesquisa.equalsIgnoreCase(lista)) {
                    val.addRow(new String[]{Integer.toString(listaEmpregados.getCod()), listaEmpregados.getNome(), listaEmpregados.getEndereco(), listaEmpregados.getNumero()});
                }
            });
        }
    }//GEN-LAST:event_campoBuscaKeyReleased

    private void tabelaCosultaEmpregadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCosultaEmpregadosMouseClicked
        if (evt.getClickCount() == 2 && listner != null) {
            String codBusca = tabelaCosultaEmpregados.getValueAt(tabelaCosultaEmpregados.getSelectedRow(), 0).toString();
            if (getListener() instanceof CadastroVendas) {
                listner.mensagemCodClienteVenda(codBusca);
            } else if (getListener() instanceof CadastroEmpregados) {
                listner.mensagemCodClienteBusca(codBusca);
            } else if (getListener() instanceof CalculoFolhaPagamento) {
                listner.mensagemCodCalculoFolhaPagamento(codBusca);
            }
            ConsultaEmpregado.this.dispose();
        }
    }//GEN-LAST:event_tabelaCosultaEmpregadosMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ConsultaEmpregado.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaEmpregado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultaEmpregado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBusca;
    private javax.swing.JTable tabelaCosultaEmpregados;
    // End of variables declaration//GEN-END:variables
}
