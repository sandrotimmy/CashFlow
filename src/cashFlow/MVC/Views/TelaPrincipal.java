package cashFlow.MVC.Views;

import cashFlow.MVC.Models.PassaCamposComEnter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaPrincipal extends javax.swing.JFrame {

    public Statement s;
    public PassaCamposComEnter pc;

    public TelaPrincipal() throws SQLException {
        initComponents();
        pc = new PassaCamposComEnter();
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        DFGDFGD = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuCadastroEmpresa = new javax.swing.JMenuItem();
        menuCadastroUsuarios = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuCadastroClientes = new javax.swing.JMenuItem();
        menuCadastroFornecedores = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuCadastroProdutos = new javax.swing.JMenuItem();
        menuCadastroHistoricos = new javax.swing.JMenuItem();
        menuProcessos = new javax.swing.JMenu();
        MenuProcessosLancamentos = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        vendas = new javax.swing.JMenu();
        menuProcessosVendas = new javax.swing.JMenuItem();
        menuVendasConsutar = new javax.swing.JMenuItem();
        processosCompras = new javax.swing.JMenu();
        menuProcessosCompras = new javax.swing.JMenuItem();
        processosComprasConsultar = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuProcessosReajusteProdutos = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuConsultasLancamentos = new javax.swing.JMenuItem();
        menuConsultasHistoricos = new javax.swing.JMenuItem();
        menuConsultasProdutos = new javax.swing.JMenuItem();
        menuConsultasClientes = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        menuRelatoriosCadastrais = new javax.swing.JMenu();
        menuRelCadHistoricos = new javax.swing.JMenuItem();
        menuRelCadUsuários = new javax.swing.JMenuItem();
        relCadastraisFornecedores = new javax.swing.JMenuItem();
        relCadastraisClientes = new javax.swing.JMenuItem();
        relatoriosControle = new javax.swing.JMenu();
        relatoriosControleLivroCaixa = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuUtilitarios = new javax.swing.JMenu();
        menuUtilitariosCalculadora = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cash Flow");
        setBackground(new java.awt.Color(39, 91, 182));
        setForeground(new java.awt.Color(39, 91, 182));

        jPanel1.setBackground(new java.awt.Color(39, 91, 182));
        jPanel1.setForeground(new java.awt.Color(39, 91, 182));

        DFGDFGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logoAzul.png"))); // NOI18N
        DFGDFGD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addComponent(DFGDFGD)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(DFGDFGD)
                .addContainerGap())
        );

        menuCadastro.setText("Cadastro");
        menuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroActionPerformed(evt);
            }
        });

        menuCadastroEmpresa.setText("Empresa");
        menuCadastroEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroEmpresaActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroEmpresa);

        menuCadastroUsuarios.setText("Usuarios");
        menuCadastroUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroUsuariosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroUsuarios);
        menuCadastro.add(jSeparator2);

        menuCadastroClientes.setText("Clientes");
        menuCadastroClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroClientesActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroClientes);

        menuCadastroFornecedores.setText("Fornecedores");
        menuCadastroFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroFornecedoresActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroFornecedores);
        menuCadastro.add(jSeparator1);

        menuCadastroProdutos.setText("Produtos");
        menuCadastroProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroProdutosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroProdutos);

        menuCadastroHistoricos.setText("Historicos");
        menuCadastroHistoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroHistoricosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroHistoricos);

        jMenuBar1.add(menuCadastro);

        menuProcessos.setText("Processos");

        MenuProcessosLancamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MenuProcessosLancamentos.setText("Lancamentos");
        MenuProcessosLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProcessosLancamentosActionPerformed(evt);
            }
        });
        menuProcessos.add(MenuProcessosLancamentos);
        menuProcessos.add(jSeparator3);

        vendas.setText("Vendas");

        menuProcessosVendas.setText("Efetuar Venda");
        menuProcessosVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProcessosVendasActionPerformed(evt);
            }
        });
        vendas.add(menuProcessosVendas);

        menuVendasConsutar.setText("Consultar Vendas");
        menuVendasConsutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVendasConsutarActionPerformed(evt);
            }
        });
        vendas.add(menuVendasConsutar);

        menuProcessos.add(vendas);

        processosCompras.setText("Compras");

        menuProcessosCompras.setText("Efetuar Compras");
        menuProcessosCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProcessosComprasActionPerformed(evt);
            }
        });
        processosCompras.add(menuProcessosCompras);

        processosComprasConsultar.setText("Consultar Compras");
        processosComprasConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processosComprasConsultarActionPerformed(evt);
            }
        });
        processosCompras.add(processosComprasConsultar);

        menuProcessos.add(processosCompras);
        menuProcessos.add(jSeparator4);

        menuProcessosReajusteProdutos.setText("Reajuste de Produtos");
        menuProcessosReajusteProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProcessosReajusteProdutosActionPerformed(evt);
            }
        });
        menuProcessos.add(menuProcessosReajusteProdutos);

        jMenuBar1.add(menuProcessos);

        menuConsultas.setText("Consultas");

        menuConsultasLancamentos.setText("Lançamentos");
        menuConsultasLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultasLancamentosActionPerformed(evt);
            }
        });
        menuConsultas.add(menuConsultasLancamentos);

        menuConsultasHistoricos.setText("Históricos");
        menuConsultasHistoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultasHistoricosActionPerformed(evt);
            }
        });
        menuConsultas.add(menuConsultasHistoricos);

        menuConsultasProdutos.setText("Produtos");
        menuConsultasProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultasProdutosActionPerformed(evt);
            }
        });
        menuConsultas.add(menuConsultasProdutos);

        menuConsultasClientes.setText("Clientes");
        menuConsultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultasClientesActionPerformed(evt);
            }
        });
        menuConsultas.add(menuConsultasClientes);

        jMenuBar1.add(menuConsultas);

        menuRelatorios.setText("Relatórios");

        menuRelatoriosCadastrais.setText("Cadastais");

        menuRelCadHistoricos.setText("Históricos");
        menuRelCadHistoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelCadHistoricosActionPerformed(evt);
            }
        });
        menuRelatoriosCadastrais.add(menuRelCadHistoricos);

        menuRelCadUsuários.setText("Usuários");
        menuRelCadUsuários.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelCadUsuáriosActionPerformed(evt);
            }
        });
        menuRelatoriosCadastrais.add(menuRelCadUsuários);

        relCadastraisFornecedores.setText("Fornecedores");
        relCadastraisFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relCadastraisFornecedoresActionPerformed(evt);
            }
        });
        menuRelatoriosCadastrais.add(relCadastraisFornecedores);

        relCadastraisClientes.setText("Clientes");
        relCadastraisClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relCadastraisClientesActionPerformed(evt);
            }
        });
        menuRelatoriosCadastrais.add(relCadastraisClientes);

        menuRelatorios.add(menuRelatoriosCadastrais);

        relatoriosControle.setText("Lançamentos");

        relatoriosControleLivroCaixa.setText("Livro Caixa");
        relatoriosControleLivroCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosControleLivroCaixaActionPerformed(evt);
            }
        });
        relatoriosControle.add(relatoriosControleLivroCaixa);

        menuRelatorios.add(relatoriosControle);

        jMenu1.setText("Movimentos");

        jMenuItem2.setText("Produtos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuRelatorios.add(jMenu1);

        jMenuBar1.add(menuRelatorios);

        menuUtilitarios.setText("Utilitários");

        menuUtilitariosCalculadora.setText("Calculadora");
        menuUtilitariosCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUtilitariosCalculadoraActionPerformed(evt);
            }
        });
        menuUtilitarios.add(menuUtilitariosCalculadora);

        jMenuBar1.add(menuUtilitarios);

        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void menuCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroActionPerformed
    }//GEN-LAST:event_menuCadastroActionPerformed
    private void menuCadastroHistoricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroHistoricosActionPerformed
        CadastroHistorico d = new CadastroHistorico();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuCadastroHistoricosActionPerformed
    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
    }//GEN-LAST:event_menuSairActionPerformed
    private void menuCadastroUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroUsuariosActionPerformed
        try {
            CadastroUsuarios d = new CadastroUsuarios();
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuCadastroUsuariosActionPerformed
    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_menuSairMouseClicked
    private void menuCadastroEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroEmpresaActionPerformed
        try {
            CadastroEmpresa d = new CadastroEmpresa();
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuCadastroEmpresaActionPerformed
    private void menuCadastroProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroProdutosActionPerformed
        try {
            CadastroProdutos d = new CadastroProdutos();
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuCadastroProdutosActionPerformed
    private void menuConsultasHistoricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultasHistoricosActionPerformed
        ConsultaHistorico d = new ConsultaHistorico();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuConsultasHistoricosActionPerformed
    private void menuCadastroClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroClientesActionPerformed
        CadastroClientes d = new CadastroClientes();
        pc.passaCamposComEnter(d);
        d.setModal(true);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuCadastroClientesActionPerformed
    private void menuCadastroFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroFornecedoresActionPerformed
        CadastroFornecedores d = new CadastroFornecedores();
        pc.passaCamposComEnter(d);
        d.setModal(true);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuCadastroFornecedoresActionPerformed
    private void menuUtilitariosCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUtilitariosCalculadoraActionPerformed
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException e) {
        }
    }//GEN-LAST:event_menuUtilitariosCalculadoraActionPerformed
    private void menuConsultasLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultasLancamentosActionPerformed
    }//GEN-LAST:event_menuConsultasLancamentosActionPerformed
    private void menuConsultasProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultasProdutosActionPerformed
        ConsultaProdutos d = new ConsultaProdutos();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuConsultasProdutosActionPerformed
    private void menuConsultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultasClientesActionPerformed
        ConsultaCliente d = new ConsultaCliente();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_menuConsultasClientesActionPerformed
    private void menuProcessosReajusteProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProcessosReajusteProdutosActionPerformed
        try {
            CadastroReajusteProdutos d = new CadastroReajusteProdutos(this, rootPaneCheckingEnabled);
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuProcessosReajusteProdutosActionPerformed
    private void menuProcessosComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProcessosComprasActionPerformed
        try {
            CadastroCompras d = new CadastroCompras();
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuProcessosComprasActionPerformed
    private void menuProcessosVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProcessosVendasActionPerformed
        try {
            CadastroVendas d = new CadastroVendas();
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuProcessosVendasActionPerformed
    private void MenuProcessosLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProcessosLancamentosActionPerformed
        try {
            CadastroLancamentos d = new CadastroLancamentos();
            pc.passaCamposComEnter(d);
            d.setLocationRelativeTo(null);
            d.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MenuProcessosLancamentosActionPerformed
    private void menuVendasConsutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVendasConsutarActionPerformed
        ConsultaVendas consultaVenda = new ConsultaVendas();
        consultaVenda.setLocationRelativeTo(null);
        consultaVenda.setVisible(true);
    }//GEN-LAST:event_menuVendasConsutarActionPerformed
    private void processosComprasConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processosComprasConsultarActionPerformed
        ConsultaCompras consultaCompras = new ConsultaCompras();
        consultaCompras.setLocationRelativeTo(null);
        consultaCompras.setVisible(true);
    }//GEN-LAST:event_processosComprasConsultarActionPerformed
    private void menuRelCadUsuáriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelCadUsuáriosActionPerformed
    }//GEN-LAST:event_menuRelCadUsuáriosActionPerformed
    private void menuRelCadHistoricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelCadHistoricosActionPerformed
    }//GEN-LAST:event_menuRelCadHistoricosActionPerformed
    private void relatoriosControleLivroCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosControleLivroCaixaActionPerformed
        RelatorioLivroCaixa d;
        d = new RelatorioLivroCaixa();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_relatoriosControleLivroCaixaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        RelatorioEstoque d;
        d = new RelatorioEstoque();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void relCadastraisFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relCadastraisFornecedoresActionPerformed
        RelatorioFornecedores d;
        d = new RelatorioFornecedores();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_relCadastraisFornecedoresActionPerformed

    private void relCadastraisClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relCadastraisClientesActionPerformed
        RelatorioClientes d;
        d = new RelatorioClientes();
        pc.passaCamposComEnter(d);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_relCadastraisClientesActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaPrincipal().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DFGDFGD;
    private javax.swing.JMenuItem MenuProcessosLancamentos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuCadastroClientes;
    private javax.swing.JMenuItem menuCadastroEmpresa;
    private javax.swing.JMenuItem menuCadastroFornecedores;
    private javax.swing.JMenuItem menuCadastroHistoricos;
    private javax.swing.JMenuItem menuCadastroProdutos;
    private javax.swing.JMenuItem menuCadastroUsuarios;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenuItem menuConsultasClientes;
    private javax.swing.JMenuItem menuConsultasHistoricos;
    private javax.swing.JMenuItem menuConsultasLancamentos;
    private javax.swing.JMenuItem menuConsultasProdutos;
    private javax.swing.JMenu menuProcessos;
    private javax.swing.JMenuItem menuProcessosCompras;
    private javax.swing.JMenuItem menuProcessosReajusteProdutos;
    public javax.swing.JMenuItem menuProcessosVendas;
    private javax.swing.JMenuItem menuRelCadHistoricos;
    private javax.swing.JMenuItem menuRelCadUsuários;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuRelatoriosCadastrais;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuUtilitarios;
    private javax.swing.JMenuItem menuUtilitariosCalculadora;
    private javax.swing.JMenuItem menuVendasConsutar;
    private javax.swing.JMenu processosCompras;
    private javax.swing.JMenuItem processosComprasConsultar;
    private javax.swing.JMenuItem relCadastraisClientes;
    private javax.swing.JMenuItem relCadastraisFornecedores;
    private javax.swing.JMenu relatoriosControle;
    private javax.swing.JMenuItem relatoriosControleLivroCaixa;
    private javax.swing.JMenu vendas;
    // End of variables declaration//GEN-END:variables
}
