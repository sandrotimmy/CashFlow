package cashFlow.MVC.Views;

import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.DAO.ProdutosDAO;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

public final class ConsultaProdutos extends javax.swing.JDialog {

    private final ArrayList<Produtos> listaProdutos;
    private final ProdutosDAO persist;
    private final DefaultTableModel val;
    private InterfaceListener listner = null;

    public ConsultaProdutos() {
        initComponents();
        campoBusca.requestFocus();
        setModal(true);
        setLocationRelativeTo(null);
        persist = new ProdutosDAO();
        listaProdutos = persist.getListaProdutos();
        val = (DefaultTableModel) tabelaCosultaProdutos.getModel();
        carregaTabela();
    }

    public void setListener(InterfaceListener listener) {
        this.listner = listener;
    }

    public InterfaceListener getListener() {
        return listner;
    }

    public void carregaTabela() {

        listaProdutos.stream().forEach((listaProdutos) -> {
            val.addRow(new String[]{
                Integer.toString(listaProdutos.getCod()),
                listaProdutos.getIdentificador(),
                listaProdutos.getDescricao(),
                listaProdutos.getUnidade(),
                listaProdutos.getQuantidade().toString(),});
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelBusca = new javax.swing.JLabel();
        campoBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCosultaProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Produtos");
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

        tabelaCosultaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód:", "Identificador:", "Descrição:", "Und:", "Quant:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCosultaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCosultaProdutosMouseClicked(evt);
            }
        });
        tabelaCosultaProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaCosultaProdutosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCosultaProdutos);
        if (tabelaCosultaProdutos.getColumnModel().getColumnCount() > 0) {
            tabelaCosultaProdutos.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaCosultaProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaCosultaProdutos.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaCosultaProdutos.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaCosultaProdutos.getColumnModel().getColumn(4).setMinWidth(90);
            tabelaCosultaProdutos.getColumnModel().getColumn(4).setPreferredWidth(90);
            tabelaCosultaProdutos.getColumnModel().getColumn(4).setMaxWidth(90);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
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
            listaProdutos.stream().forEach((listaProdutos) -> {
                String pesquisa = campoBusca.getText();
                String lista = "";
                if (listaProdutos.getDescricao().length() >= quantCar) {
                    lista = listaProdutos.getDescricao().substring(0, quantCar);
                }
                if (pesquisa.equalsIgnoreCase(lista)) {
                    val.addRow(new String[]{Integer.toString(listaProdutos.getCod()),
                        listaProdutos.getIdentificador(),
                        listaProdutos.getDescricao(),
                        listaProdutos.getUnidade(),
                        listaProdutos.getQuantidade().toString()});
                }
            });
        }
    }//GEN-LAST:event_campoBuscaKeyReleased

    private void tabelaCosultaProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaCosultaProdutosKeyPressed
    }//GEN-LAST:event_tabelaCosultaProdutosKeyPressed

    private void tabelaCosultaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCosultaProdutosMouseClicked
        if (evt.getClickCount() == 2 && listner != null) {
            if (getListener() instanceof CadastroVendas) {
                listner.mensagemCodProdutoVenda(tabelaCosultaProdutos.getValueAt(tabelaCosultaProdutos.getSelectedRow(), 0).toString());
            } else if (getListener() instanceof CadastroCompras) {
                listner.mensagemCodProdutoCompra(tabelaCosultaProdutos.getValueAt(tabelaCosultaProdutos.getSelectedRow(), 0).toString());
            } else if (getListener() instanceof CadastroReajusteProdutos) {
                listner.mensagemCodProdutoReajuste(tabelaCosultaProdutos.getValueAt(tabelaCosultaProdutos.getSelectedRow(), 0).toString());
            }else if (getListener() instanceof CadastroProdutos){
            listner.mensagemCodProdutoBusca(tabelaCosultaProdutos.getValueAt(tabelaCosultaProdutos.getSelectedRow(), 0).toString());
            }
            ConsultaProdutos.this.dispose();
            try {
                Robot robo = new Robot();
                robo.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
                Logger.getLogger(ConsultaProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_tabelaCosultaProdutosMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ConsultaProdutos.this.dispose();
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
            java.util.logging.Logger.getLogger(ConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBusca;
    private javax.swing.JTable tabelaCosultaProdutos;
    // End of variables declaration//GEN-END:variables

}
