package cashFlow.MVC.Views;

import cashFlow.MVC.Models.Fornecedores;
import cashFlow.MVC.DAO.FornecedoresDAO;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public final class ConsultaFornecedor extends javax.swing.JDialog {

    private final ArrayList<Fornecedores> listaFornecedores;
    private final FornecedoresDAO persist;
    private final DefaultTableModel val;
    private InterfaceListener listner = null;

    public ConsultaFornecedor(){
        initComponents();
        campoBusca.requestFocus();
        setModal(true);
        setLocationRelativeTo(null);
        persist = new FornecedoresDAO();
        listaFornecedores = persist.getListaFornecedores();
        val = (DefaultTableModel) tabelaCosultaFornecedores.getModel();
        carregaTabela();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    public void mensagemCodFornecedorBusca(String msg) {

    }

    public void carregaTabela() {
         listaFornecedores.stream().forEach((Fornecedores listaFornecedores) -> {
            val.addRow(new String[]{
                Integer.toString(listaFornecedores.getCod()),
                listaFornecedores.getRazaosocial(),
                listaFornecedores.getEndereco(),
                listaFornecedores.getNumero()});
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelBusca = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCosultaFornecedores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Fornecedores");
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

        tabelaCosultaFornecedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCosultaFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCosultaFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCosultaFornecedores);
        if (tabelaCosultaFornecedores.getColumnModel().getColumnCount() > 0) {
            tabelaCosultaFornecedores.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaCosultaFornecedores.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaCosultaFornecedores.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaCosultaFornecedores.getColumnModel().getColumn(1).setMinWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(1).setMaxWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(2).setMinWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(2).setMaxWidth(250);
            tabelaCosultaFornecedores.getColumnModel().getColumn(3).setMinWidth(70);
            tabelaCosultaFornecedores.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabelaCosultaFornecedores.getColumnModel().getColumn(3).setMaxWidth(70);
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

    private void campoBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            int quantCar = campoBusca.getText().length();//pega quantas letras foram digitadas
            val.setRowCount(0);//zera a tabela para popular com novo resultado refinado
            listaFornecedores.stream().forEach((Fornecedores listaFornecedores) -> {
                String pesquisa = campoBusca.getText();
                String lista = "";
                listaFornecedores.getRazaosocial().length();
                if (listaFornecedores.getRazaosocial().length() >= quantCar) {
                    lista = listaFornecedores.getRazaosocial().substring(0, quantCar);
                }
                if (pesquisa.equalsIgnoreCase(lista)) {
                    val.addRow(new String[]{Integer.toString(listaFornecedores.getCod()), listaFornecedores.getRazaosocial(), listaFornecedores.getEndereco(), listaFornecedores.getNumero()});
                }
            });
        }
    }//GEN-LAST:event_campoBuscaKeyReleased

    private void tabelaCosultaFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCosultaFornecedoresMouseClicked
        if (evt.getClickCount() == 2 && listner != null) {
            if (getListener() instanceof CadastroCompras) {
                listner.mensagemCodFornecedorCompra(tabelaCosultaFornecedores.getValueAt(tabelaCosultaFornecedores.getSelectedRow(), 0).toString());
            } else {
                listner.mensagemCodFornecedorBusca(tabelaCosultaFornecedores.getValueAt(tabelaCosultaFornecedores.getSelectedRow(), 0).toString());
            }
            ConsultaFornecedor.this.dispose();
        }
    }//GEN-LAST:event_tabelaCosultaFornecedoresMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ConsultaFornecedor.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new ConsultaFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBusca;
    private javax.swing.JTable tabelaCosultaFornecedores;
    // End of variables declaration//GEN-END:variables
}
