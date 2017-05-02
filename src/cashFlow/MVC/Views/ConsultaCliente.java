package cashFlow.MVC.Views;

import cashFlow.MVC.DAO.ClientesDAO;
import cashFlow.MVC.Models.Clientes;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public final class ConsultaCliente extends javax.swing.JDialog {

    private final List<Clientes> listaClientes;
    private final ClientesDAO persist;
    private final DefaultTableModel val;
    private InterfaceListener listner = null;

    public ConsultaCliente() {
        initComponents();
        campoBusca.requestFocus();
        setModal(true);
        setLocationRelativeTo(null);
        persist = new ClientesDAO();
        listaClientes = persist.getListaClientes();
        val = (DefaultTableModel) tabelaCosultaClientes.getModel();
        carregaTabela();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    public void carregaTabela() {
        listaClientes.stream().forEach((Clientes listaClientes) -> {
            val.addRow(new String[]{
                Integer.toString(listaClientes.getCod()),
                listaClientes.getNome(),
                listaClientes.getEndereco(),
                listaClientes.getNumero()});
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelBusca = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCosultaClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Clientes");
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

        tabelaCosultaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCosultaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCosultaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCosultaClientes);
        if (tabelaCosultaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaCosultaClientes.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaCosultaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaCosultaClientes.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaCosultaClientes.getColumnModel().getColumn(1).setMinWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(1).setMaxWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(2).setMinWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(2).setMaxWidth(250);
            tabelaCosultaClientes.getColumnModel().getColumn(3).setMinWidth(70);
            tabelaCosultaClientes.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaCosultaClientes.getColumnModel().getColumn(3).setMaxWidth(70);
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
            listaClientes.stream().forEach((Clientes listaClientes) -> {
                String pesquisa = campoBusca.getText();
                String lista = "";
                if (listaClientes.getNome().length() >= quantCar) {
                    lista = listaClientes.getNome().substring(0, quantCar);
                }
                if (pesquisa.equalsIgnoreCase(lista)) {
                    val.addRow(new String[]{Integer.toString(listaClientes.getCod()), listaClientes.getNome(), listaClientes.getEndereco(), listaClientes.getNumero()});
                }
            });
        }
    }//GEN-LAST:event_campoBuscaKeyReleased

    private void tabelaCosultaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCosultaClientesMouseClicked
        if (evt.getClickCount() == 2 && listner != null) {
            String codBusca = tabelaCosultaClientes.getValueAt(tabelaCosultaClientes.getSelectedRow(), 0).toString();
            if (getListener() instanceof CadastroVendas) {
                listner.mensagemCodClienteVenda(codBusca);
            } else if (getListener() instanceof CadastroClientes) {
                listner.mensagemCodClienteBusca(codBusca);
            } else if (getListener() instanceof ConsultaVendas) {
                listner.mensagemCodClienteConsultVenda(codBusca);
            }
            ConsultaCliente.this.dispose();
            try {
                Robot robo = new Robot();
                robo.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
                Logger.getLogger(ConsultaProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabelaCosultaClientesMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ConsultaCliente.this.dispose();
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
            java.util.logging.Logger.getLogger(ConsultaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBusca;
    private javax.swing.JTable tabelaCosultaClientes;
    // End of variables declaration//GEN-END:variables
}
