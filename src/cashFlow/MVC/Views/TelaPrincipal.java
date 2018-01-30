package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Controllers.ParametrosCtrl;
import cashFlow.MVC.Models.PassaCamposComEnter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {

    public Statement s;
    public PassaCamposComEnter pc;
    public MetodosGerais mg;

    public TelaPrincipal(String usuarioLogado) throws SQLException, IOException {
        mg = new MetodosGerais();
        initComponents();
        mg.customizeMenuBar(menuPrincipal);
        setBotoesUsuario(usuarioLogado);
        setIcon();
        setLogo();

        pc = new PassaCamposComEnter();
    }

    public void setLogo() throws IOException {
        try {
            Properties parametros = ParametrosCtrl.getInstance();
            String caminhoLogo = parametros.getProperty("caminhoLogoTela");
            areaLogo.setIcon(new javax.swing.ImageIcon(caminhoLogo));
        } catch (NullPointerException e) {

        }
    }

    private void setBotoesUsuario(String usuarioLogado) {
        menuCadastro.setBorderPainted(false);
        menuProcessos.setBorderPainted(false);
        menuConsultas.setBorderPainted(false);
        menuRelatorios.setBorderPainted(false);
        menuUtilitarios.setBorderPainted(false);
        menuAjuda.setBorderPainted(false);
        menuSair.setBorderPainted(false);
        campoUsuarioLogado.setText(usuarioLogado);

    }

    public void setIcon() throws IOException {
//        Properties parametros = ParametrosCtrl.getInstance();
//        String caminho = parametros.getProperty("caminhoLogoTela");
//        File imagem = new File(caminho);
//        BufferedImage img = ImageIO.read(imagem);
//        labelImagemLogo.setSize(img.getHeight(), img.getWidth());
//        labelImagemLogo.setIcon(new javax.swing.ImageIcon(caminho));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        areaLogo = new javax.swing.JLabel();
        painelStatus = new javax.swing.JPanel();
        labelLogadoPor = new javax.swing.JLabel();
        campoUsuarioLogado = new javax.swing.JTextField();
        painelAtalhos = new javax.swing.JPanel();
        AtalhoEmpresa = new javax.swing.JLabel();
        atalhoRelatorioCaixa = new javax.swing.JLabel();
        atalhoLancamentoCaixa = new javax.swing.JLabel();
        atalhoSair = new javax.swing.JLabel();
        atalhoContasAReceber = new javax.swing.JLabel();
        atalhoClientes = new javax.swing.JLabel();
        AtalhoFornecedores = new javax.swing.JLabel();
        atalhoContasAPagar = new javax.swing.JLabel();
        atalhoCalculadora = new javax.swing.JLabel();
        menuPrincipal = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuCadastroEmpresa = new javax.swing.JMenuItem();
        menuCadastroUsuarios = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuCadastroClientes = new javax.swing.JMenuItem();
        menuCadastroFornecedores = new javax.swing.JMenuItem();
        menuCadastroEmpregados = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuCadastroProdutos = new javax.swing.JMenuItem();
        menuClassificacaoProdutos = new javax.swing.JMenuItem();
        menuCadastroHistoricos = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuCadastroComissoes = new javax.swing.JMenuItem();
        menuCadastroDescontos = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
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
        processosFolhaDePagamento = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        menuContasAPagar = new javax.swing.JMenuItem();
        menuContasAReceber = new javax.swing.JMenuItem();
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
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        relatoriosMovimentosVenda = new javax.swing.JMenuItem();
        relatoriosMovimentosCarne = new javax.swing.JMenuItem();
        menuAcomp = new javax.swing.JMenu();
        porCusto = new javax.swing.JMenuItem();
        clientesAniversariantes = new javax.swing.JMenuItem();
        menuUtilitarios = new javax.swing.JMenu();
        menuUtilitariosCalculadora = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuAjudaSobre = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cash Flow");
        setBackground(new java.awt.Color(39, 91, 182));
        setForeground(new java.awt.Color(39, 91, 182));

        painelPrincipal.setBackground(new java.awt.Color(39, 91, 182));
        painelPrincipal.setForeground(new java.awt.Color(39, 91, 182));

        areaLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        areaLogo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        painelStatus.setBackground(new java.awt.Color(32, 75, 151));

        labelLogadoPor.setForeground(new java.awt.Color(255, 255, 255));
        labelLogadoPor.setText("Logado por: ");

        campoUsuarioLogado.setEditable(false);
        campoUsuarioLogado.setBackground(new java.awt.Color(32, 75, 151));
        campoUsuarioLogado.setForeground(new java.awt.Color(255, 255, 255));
        campoUsuarioLogado.setBorder(null);
        campoUsuarioLogado.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        campoUsuarioLogado.setEnabled(false);
        campoUsuarioLogado.setFocusable(false);

        javax.swing.GroupLayout painelStatusLayout = new javax.swing.GroupLayout(painelStatus);
        painelStatus.setLayout(painelStatusLayout);
        painelStatusLayout.setHorizontalGroup(
            painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelStatusLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelLogadoPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelStatusLayout.setVerticalGroup(
            painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelLogadoPor, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(campoUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(areaLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addComponent(painelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelAtalhos.setBackground(new java.awt.Color(32, 75, 151));

        AtalhoEmpresa.setBackground(new java.awt.Color(27, 63, 133));
        AtalhoEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/empresa.png"))); // NOI18N
        AtalhoEmpresa.setToolTipText("Empresa");
        AtalhoEmpresa.setMaximumSize(new java.awt.Dimension(14, 14));
        AtalhoEmpresa.setMinimumSize(new java.awt.Dimension(14, 14));
        AtalhoEmpresa.setOpaque(true);
        AtalhoEmpresa.setPreferredSize(new java.awt.Dimension(14, 14));
        AtalhoEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtalhoEmpresaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AtalhoEmpresaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AtalhoEmpresaMouseExited(evt);
            }
        });

        atalhoRelatorioCaixa.setBackground(new java.awt.Color(27, 63, 133));
        atalhoRelatorioCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/relatorioCaixa.png"))); // NOI18N
        atalhoRelatorioCaixa.setToolTipText("Relatório Caixa");
        atalhoRelatorioCaixa.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoRelatorioCaixa.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoRelatorioCaixa.setOpaque(true);
        atalhoRelatorioCaixa.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoRelatorioCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoRelatorioCaixaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoRelatorioCaixaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoRelatorioCaixaMouseExited(evt);
            }
        });

        atalhoLancamentoCaixa.setBackground(new java.awt.Color(27, 63, 133));
        atalhoLancamentoCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lancamentoCaixa.png"))); // NOI18N
        atalhoLancamentoCaixa.setToolTipText("Lançamento Caixa");
        atalhoLancamentoCaixa.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoLancamentoCaixa.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoLancamentoCaixa.setOpaque(true);
        atalhoLancamentoCaixa.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoLancamentoCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoLancamentoCaixaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoLancamentoCaixaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoLancamentoCaixaMouseExited(evt);
            }
        });

        atalhoSair.setBackground(new java.awt.Color(27, 63, 133));
        atalhoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sair.png"))); // NOI18N
        atalhoSair.setToolTipText("Sair");
        atalhoSair.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoSair.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoSair.setOpaque(true);
        atalhoSair.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoSairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoSairMouseExited(evt);
            }
        });

        atalhoContasAReceber.setBackground(new java.awt.Color(27, 63, 133));
        atalhoContasAReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasReceber.png"))); // NOI18N
        atalhoContasAReceber.setToolTipText("Contas a Receber");
        atalhoContasAReceber.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoContasAReceber.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoContasAReceber.setOpaque(true);
        atalhoContasAReceber.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoContasAReceber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoContasAReceberMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoContasAReceberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoContasAReceberMouseExited(evt);
            }
        });

        atalhoClientes.setBackground(new java.awt.Color(27, 63, 133));
        atalhoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/clientes.png"))); // NOI18N
        atalhoClientes.setToolTipText("Clientes");
        atalhoClientes.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoClientes.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoClientes.setOpaque(true);
        atalhoClientes.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoClientesMouseExited(evt);
            }
        });

        AtalhoFornecedores.setBackground(new java.awt.Color(27, 63, 133));
        AtalhoFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fornecedores.png"))); // NOI18N
        AtalhoFornecedores.setToolTipText("Fornecedores");
        AtalhoFornecedores.setMaximumSize(new java.awt.Dimension(14, 14));
        AtalhoFornecedores.setMinimumSize(new java.awt.Dimension(14, 14));
        AtalhoFornecedores.setOpaque(true);
        AtalhoFornecedores.setPreferredSize(new java.awt.Dimension(14, 14));
        AtalhoFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtalhoFornecedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AtalhoFornecedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AtalhoFornecedoresMouseExited(evt);
            }
        });

        atalhoContasAPagar.setBackground(new java.awt.Color(27, 63, 133));
        atalhoContasAPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasPagar.png"))); // NOI18N
        atalhoContasAPagar.setToolTipText("Contas a Pagar");
        atalhoContasAPagar.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoContasAPagar.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoContasAPagar.setOpaque(true);
        atalhoContasAPagar.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoContasAPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoContasAPagarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoContasAPagarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoContasAPagarMouseExited(evt);
            }
        });

        atalhoCalculadora.setBackground(new java.awt.Color(27, 63, 133));
        atalhoCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/calculadora.png"))); // NOI18N
        atalhoCalculadora.setToolTipText("Calculadora");
        atalhoCalculadora.setMaximumSize(new java.awt.Dimension(14, 14));
        atalhoCalculadora.setMinimumSize(new java.awt.Dimension(14, 14));
        atalhoCalculadora.setOpaque(true);
        atalhoCalculadora.setPreferredSize(new java.awt.Dimension(14, 14));
        atalhoCalculadora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atalhoCalculadoraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atalhoCalculadoraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atalhoCalculadoraMouseExited(evt);
            }
        });

        javax.swing.GroupLayout painelAtalhosLayout = new javax.swing.GroupLayout(painelAtalhos);
        painelAtalhos.setLayout(painelAtalhosLayout);
        painelAtalhosLayout.setHorizontalGroup(
            painelAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAtalhosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(AtalhoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AtalhoFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoContasAReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoContasAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoLancamentoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoRelatorioCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoCalculadora, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atalhoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelAtalhosLayout.setVerticalGroup(
            painelAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAtalhosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atalhoRelatorioCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(AtalhoEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoLancamentoCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoSair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoContasAReceber, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(AtalhoFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoContasAPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(atalhoCalculadora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        menuPrincipal.setBorder(null);
        menuPrincipal.setBorderPainted(false);
        menuPrincipal.setFocusable(false);
        menuPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuPrincipal.setMaximumSize(new java.awt.Dimension(513, 35488));
        menuPrincipal.setMinimumSize(new java.awt.Dimension(513, 50));
        menuPrincipal.setOpaque(false);
        menuPrincipal.setPreferredSize(new java.awt.Dimension(513, 35));

        menuCadastro.setBackground(new java.awt.Color(32, 71, 151));
        menuCadastro.setBorder(null);
        menuCadastro.setMnemonic('c');
        menuCadastro.setText("<html><u>C</u>adastro");
        menuCadastro.setContentAreaFilled(false);
        menuCadastro.setFocusable(false);
        menuCadastro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuCadastro.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuCadastroMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuCadastroMenuSelected(evt);
            }
        });
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

        menuCadastroEmpregados.setText("Empregados");
        menuCadastroEmpregados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroEmpregadosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroEmpregados);
        menuCadastro.add(jSeparator1);

        menuCadastroProdutos.setText("Produtos");
        menuCadastroProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroProdutosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroProdutos);

        menuClassificacaoProdutos.setText("Classificação de Produtos");
        menuClassificacaoProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClassificacaoProdutosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuClassificacaoProdutos);

        menuCadastroHistoricos.setText("Historicos");
        menuCadastroHistoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroHistoricosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroHistoricos);
        menuCadastro.add(jSeparator5);

        menuCadastroComissoes.setText("Comissões");
        menuCadastroComissoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroComissoesActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroComissoes);

        menuCadastroDescontos.setText("Descontos");
        menuCadastroDescontos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroDescontosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroDescontos);
        menuCadastro.add(jSeparator7);

        jMenuItem1.setText("Parametros");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuCadastro.add(jMenuItem1);

        menuPrincipal.add(menuCadastro);

        menuProcessos.setBorder(null);
        menuProcessos.setMnemonic('P');
        menuProcessos.setText("<html><u>P</u>rocessos");
        menuProcessos.setContentAreaFilled(false);
        menuProcessos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuProcessos.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuProcessosMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuProcessosMenuSelected(evt);
            }
        });

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

        processosFolhaDePagamento.setText("Cálculo Folha de Pagamento");
        processosFolhaDePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processosFolhaDePagamentoActionPerformed(evt);
            }
        });
        menuProcessos.add(processosFolhaDePagamento);
        menuProcessos.add(jSeparator8);

        menuContasAPagar.setText("Contas a Pagar");
        menuProcessos.add(menuContasAPagar);

        menuContasAReceber.setText("Contas a Receber");
        menuProcessos.add(menuContasAReceber);

        menuPrincipal.add(menuProcessos);

        menuConsultas.setMnemonic('o');
        menuConsultas.setText("<html>C<u>o</u>nsultas");
        menuConsultas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuConsultas.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuConsultasMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuConsultasMenuSelected(evt);
            }
        });

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

        menuPrincipal.add(menuConsultas);

        menuRelatorios.setBorder(null);
        menuRelatorios.setMnemonic('R');
        menuRelatorios.setText("<html><u>R</u>elatórios");
        menuRelatorios.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuRelatorios.setHideActionText(true);
        menuRelatorios.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuRelatoriosMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuRelatoriosMenuSelected(evt);
            }
        });

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
        jMenu1.add(jSeparator6);

        relatoriosMovimentosVenda.setText("Vendas");
        relatoriosMovimentosVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosMovimentosVendaActionPerformed(evt);
            }
        });
        jMenu1.add(relatoriosMovimentosVenda);

        relatoriosMovimentosCarne.setText("Carne de Vendas");
        relatoriosMovimentosCarne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosMovimentosCarneActionPerformed(evt);
            }
        });
        jMenu1.add(relatoriosMovimentosCarne);

        menuRelatorios.add(jMenu1);

        menuAcomp.setText("Acompanhamentos");

        porCusto.setText("Por Histórico");
        porCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porCustoActionPerformed(evt);
            }
        });
        menuAcomp.add(porCusto);

        clientesAniversariantes.setText("Aniversariantes");
        clientesAniversariantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesAniversariantesActionPerformed(evt);
            }
        });
        menuAcomp.add(clientesAniversariantes);

        menuRelatorios.add(menuAcomp);

        menuPrincipal.add(menuRelatorios);

        menuUtilitarios.setBorder(null);
        menuUtilitarios.setMnemonic('U');
        menuUtilitarios.setText("<html><u>U</u>tilitários");
        menuUtilitarios.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuUtilitarios.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuUtilitariosMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuUtilitariosMenuSelected(evt);
            }
        });

        menuUtilitariosCalculadora.setText("Calculadora");
        menuUtilitariosCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUtilitariosCalculadoraActionPerformed(evt);
            }
        });
        menuUtilitarios.add(menuUtilitariosCalculadora);

        menuPrincipal.add(menuUtilitarios);

        menuAjuda.setBorder(null);
        menuAjuda.setMnemonic('A');
        menuAjuda.setText("<html><u>A</u>juda");
        menuAjuda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuAjuda.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuAjudaMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuAjudaMenuSelected(evt);
            }
        });
        menuAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjudaActionPerformed(evt);
            }
        });

        menuAjudaSobre.setText("Sobre");
        menuAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjudaSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuAjudaSobre);

        menuPrincipal.add(menuAjuda);

        menuSair.setBorder(null);
        menuSair.setMnemonic('S');
        menuSair.setText("Sair");
        menuSair.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuSair.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
                menuSairMenuDeselected(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuSairMenuSelected(evt);
            }
        });
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuPrincipal.add(menuSair);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelAtalhos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelAtalhos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Parametros parametros;
        parametros = new Parametros();
        parametros.setLocationRelativeTo(null);
        parametros.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void relatoriosMovimentosVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosMovimentosVendaActionPerformed
        RelatorioVendas relatorioVendas;
        relatorioVendas = new RelatorioVendas();
        pc.passaCamposComEnter(relatorioVendas);
        relatorioVendas.setLocationRelativeTo(null);
        relatorioVendas.setVisible(true);
    }//GEN-LAST:event_relatoriosMovimentosVendaActionPerformed

    private void relatoriosMovimentosCarneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosMovimentosCarneActionPerformed
        RelatorioVendasCarne relatorioVendasCarne;
        relatorioVendasCarne = new RelatorioVendasCarne();
        pc.passaCamposComEnter(relatorioVendasCarne);
        relatorioVendasCarne.setLocationRelativeTo(null);
        relatorioVendasCarne.setVisible(true);
    }//GEN-LAST:event_relatoriosMovimentosCarneActionPerformed

    private void menuAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjudaSobreActionPerformed
        Sobre sobre = new Sobre();
        sobre.setLocationRelativeTo(null);
        sobre.setVisible(true);
    }//GEN-LAST:event_menuAjudaSobreActionPerformed

    private void menuAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAjudaActionPerformed

    private void menuCadastroEmpregadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroEmpregadosActionPerformed
        CadastroEmpregados cadastroEmpregados = new CadastroEmpregados();
        pc.passaCamposComEnter(cadastroEmpregados);
        cadastroEmpregados.setModal(true);
        cadastroEmpregados.setLocationRelativeTo(null);
        cadastroEmpregados.setVisible(true);
    }//GEN-LAST:event_menuCadastroEmpregadosActionPerformed

    private void menuCadastroComissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroComissoesActionPerformed
        CadastroComissoes cadastroComissoes = new CadastroComissoes();
        pc.passaCamposComEnter(cadastroComissoes);
        cadastroComissoes.setModal(true);
        cadastroComissoes.setLocationRelativeTo(null);
        cadastroComissoes.setVisible(true);
    }//GEN-LAST:event_menuCadastroComissoesActionPerformed

    private void menuCadastroDescontosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroDescontosActionPerformed
        CadastroDescontos cadastroDescontos = new CadastroDescontos();
        pc.passaCamposComEnter(cadastroDescontos);
        cadastroDescontos.setModal(true);
        cadastroDescontos.setLocationRelativeTo(null);
        cadastroDescontos.setVisible(true);
    }//GEN-LAST:event_menuCadastroDescontosActionPerformed

    private void processosFolhaDePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processosFolhaDePagamentoActionPerformed
        CalculoFolhaPagamento calculoFolhaPagamento;
        try {
            calculoFolhaPagamento = new CalculoFolhaPagamento();
            calculoFolhaPagamento.setModal(true);
            calculoFolhaPagamento.setLocationRelativeTo(null);
            calculoFolhaPagamento.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_processosFolhaDePagamentoActionPerformed

    private void porCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porCustoActionPerformed
        RelatorioCustos relatorioCustos;
        relatorioCustos = new RelatorioCustos();
        relatorioCustos.setLocationRelativeTo(null);
        relatorioCustos.setVisible(true);
    }//GEN-LAST:event_porCustoActionPerformed

    private void clientesAniversariantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesAniversariantesActionPerformed
        RelatorioClientesAniversariantes relatorioClientesAniversariantes = new RelatorioClientesAniversariantes();
        pc.passaCamposComEnter(relatorioClientesAniversariantes);
        relatorioClientesAniversariantes.setLocationRelativeTo(null);
        relatorioClientesAniversariantes.setVisible(true);
    }//GEN-LAST:event_clientesAniversariantesActionPerformed

    private void menuClassificacaoProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClassificacaoProdutosActionPerformed
        CadastroClassificacaoProdutos cadastroClassificacaoProdutos = new CadastroClassificacaoProdutos();
        pc.passaCamposComEnter(cadastroClassificacaoProdutos);
        cadastroClassificacaoProdutos.setLocationRelativeTo(null);
        cadastroClassificacaoProdutos.setVisible(true);
    }//GEN-LAST:event_menuClassificacaoProdutosActionPerformed

    private void AtalhoEmpresaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoEmpresaMouseEntered
        AtalhoEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/empresa_s.png")));         // TODO add your handling code here:
    }//GEN-LAST:event_AtalhoEmpresaMouseEntered

    private void AtalhoEmpresaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoEmpresaMouseExited
        AtalhoEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/empresa.png")));
    }//GEN-LAST:event_AtalhoEmpresaMouseExited

    private void AtalhoEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoEmpresaMouseClicked
        menuCadastroEmpresaActionPerformed(null);
    }//GEN-LAST:event_AtalhoEmpresaMouseClicked

    private void atalhoRelatorioCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoRelatorioCaixaMouseClicked
        relatoriosControleLivroCaixaActionPerformed(null);
    }//GEN-LAST:event_atalhoRelatorioCaixaMouseClicked

    private void atalhoRelatorioCaixaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoRelatorioCaixaMouseEntered
        atalhoRelatorioCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/relatoriocaixa_s.png")));
    }//GEN-LAST:event_atalhoRelatorioCaixaMouseEntered

    private void atalhoRelatorioCaixaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoRelatorioCaixaMouseExited
        atalhoRelatorioCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/relatoriocaixa.png")));
    }//GEN-LAST:event_atalhoRelatorioCaixaMouseExited

    private void atalhoLancamentoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoLancamentoCaixaMouseClicked
        MenuProcessosLancamentosActionPerformed(null);
    }//GEN-LAST:event_atalhoLancamentoCaixaMouseClicked

    private void atalhoLancamentoCaixaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoLancamentoCaixaMouseEntered
        atalhoLancamentoCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lancamentoCaixa_s.png")));
    }//GEN-LAST:event_atalhoLancamentoCaixaMouseEntered

    private void atalhoLancamentoCaixaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoLancamentoCaixaMouseExited
        atalhoLancamentoCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lancamentoCaixa.png")));
    }//GEN-LAST:event_atalhoLancamentoCaixaMouseExited

    private void atalhoSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoSairMouseClicked
        menuSairMouseClicked(null);
    }//GEN-LAST:event_atalhoSairMouseClicked

    private void atalhoSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoSairMouseEntered
        atalhoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sair_s.png")));
    }//GEN-LAST:event_atalhoSairMouseEntered

    private void atalhoSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoSairMouseExited
        atalhoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sair.png")));
    }//GEN-LAST:event_atalhoSairMouseExited

    private void atalhoContasAReceberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAReceberMouseClicked
        JOptionPane.showMessageDialog(rootPane, "Esta nova funcionalidade será disponibilizada em breve!");
    }//GEN-LAST:event_atalhoContasAReceberMouseClicked

    private void atalhoContasAReceberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAReceberMouseEntered
        atalhoContasAReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasReceber_s.png")));
    }//GEN-LAST:event_atalhoContasAReceberMouseEntered

    private void atalhoContasAReceberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAReceberMouseExited
        atalhoContasAReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasReceber.png")));
    }//GEN-LAST:event_atalhoContasAReceberMouseExited

    private void atalhoClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoClientesMouseClicked
        menuCadastroClientesActionPerformed(null);
    }//GEN-LAST:event_atalhoClientesMouseClicked

    private void atalhoClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoClientesMouseEntered
        atalhoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/clientes_s.png")));         // TODO add your handling code here:
    }//GEN-LAST:event_atalhoClientesMouseEntered

    private void atalhoClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoClientesMouseExited
        atalhoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/clientes.png")));
    }//GEN-LAST:event_atalhoClientesMouseExited

    private void AtalhoFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoFornecedoresMouseClicked
        menuCadastroFornecedoresActionPerformed(null);
    }//GEN-LAST:event_AtalhoFornecedoresMouseClicked

    private void AtalhoFornecedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoFornecedoresMouseEntered
        AtalhoFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fornecedores_s.png")));
    }//GEN-LAST:event_AtalhoFornecedoresMouseEntered

    private void AtalhoFornecedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtalhoFornecedoresMouseExited
        AtalhoFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fornecedores.png")));
    }//GEN-LAST:event_AtalhoFornecedoresMouseExited

    private void atalhoContasAPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAPagarMouseClicked
        JOptionPane.showMessageDialog(rootPane, "Esta nova funcionalidade será disponibilizada em breve!");
    }//GEN-LAST:event_atalhoContasAPagarMouseClicked

    private void atalhoContasAPagarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAPagarMouseEntered
        atalhoContasAPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasPagar_s.png")));
    }//GEN-LAST:event_atalhoContasAPagarMouseEntered

    private void atalhoContasAPagarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoContasAPagarMouseExited
        atalhoContasAPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/contasPagar.png")));
    }//GEN-LAST:event_atalhoContasAPagarMouseExited

    private void atalhoCalculadoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoCalculadoraMouseClicked
        menuUtilitariosCalculadoraActionPerformed(null);
    }//GEN-LAST:event_atalhoCalculadoraMouseClicked

    private void atalhoCalculadoraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoCalculadoraMouseEntered
        atalhoCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/calculadora_s.png")));
    }//GEN-LAST:event_atalhoCalculadoraMouseEntered

    private void atalhoCalculadoraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atalhoCalculadoraMouseExited
        atalhoCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/calculadora.png")));
    }//GEN-LAST:event_atalhoCalculadoraMouseExited

    private void menuCadastroMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuCadastroMenuSelected
        menuCadastro.setBackground(new Color(255, 255, 255));
        menuCadastro.setForeground(new Color(0, 0, 0));        // TODO add your handling code here:
    }//GEN-LAST:event_menuCadastroMenuSelected

    private void menuCadastroMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuCadastroMenuDeselected
        menuCadastro.setBackground(new Color(32, 75, 151));
        menuCadastro.setForeground(new Color(255, 255, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_menuCadastroMenuDeselected

    private void menuProcessosMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuProcessosMenuSelected
        menuProcessos.setBackground(new Color(255, 255, 255));
        menuProcessos.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuProcessosMenuSelected

    private void menuProcessosMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuProcessosMenuDeselected
        menuProcessos.setBackground(new Color(32, 75, 151));
        menuProcessos.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuProcessosMenuDeselected

    private void menuConsultasMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuConsultasMenuSelected
        menuConsultas.setBackground(new Color(255, 255, 255));
        menuConsultas.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuConsultasMenuSelected

    private void menuConsultasMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuConsultasMenuDeselected
        menuConsultas.setBackground(new Color(32, 75, 151));
        menuConsultas.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuConsultasMenuDeselected

    private void menuRelatoriosMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuRelatoriosMenuSelected
        menuRelatorios.setBackground(new Color(255, 255, 255));
        menuRelatorios.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuRelatoriosMenuSelected

    private void menuRelatoriosMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuRelatoriosMenuDeselected
        menuRelatorios.setBackground(new Color(32, 75, 151));
        menuRelatorios.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuRelatoriosMenuDeselected

    private void menuUtilitariosMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuUtilitariosMenuSelected
        menuUtilitarios.setBackground(new Color(255, 255, 255));
        menuUtilitarios.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuUtilitariosMenuSelected

    private void menuUtilitariosMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuUtilitariosMenuDeselected
        menuUtilitarios.setBackground(new Color(32, 75, 151));
        menuUtilitarios.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuUtilitariosMenuDeselected

    private void menuAjudaMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuAjudaMenuSelected
        menuAjuda.setBackground(new Color(255, 255, 255));
        menuAjuda.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuAjudaMenuSelected

    private void menuAjudaMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuAjudaMenuDeselected
        menuAjuda.setBackground(new Color(32, 75, 151));
        menuAjuda.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuAjudaMenuDeselected

    private void menuSairMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuSairMenuSelected
        menuSair.setBackground(new Color(255, 255, 255));
        menuSair.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_menuSairMenuSelected

    private void menuSairMenuDeselected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuSairMenuDeselected
        menuSair.setBackground(new Color(32, 75, 151));
        menuSair.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_menuSairMenuDeselected
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaPrincipal(null).setVisible(true);
            } catch (SQLException | IOException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AtalhoEmpresa;
    private javax.swing.JLabel AtalhoFornecedores;
    private javax.swing.JMenuItem MenuProcessosLancamentos;
    private javax.swing.JLabel areaLogo;
    private javax.swing.JLabel atalhoCalculadora;
    private javax.swing.JLabel atalhoClientes;
    private javax.swing.JLabel atalhoContasAPagar;
    private javax.swing.JLabel atalhoContasAReceber;
    private javax.swing.JLabel atalhoLancamentoCaixa;
    private javax.swing.JLabel atalhoRelatorioCaixa;
    private javax.swing.JLabel atalhoSair;
    private javax.swing.JTextField campoUsuarioLogado;
    private javax.swing.JMenuItem clientesAniversariantes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JLabel labelLogadoPor;
    private javax.swing.JMenu menuAcomp;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenuItem menuAjudaSobre;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuCadastroClientes;
    private javax.swing.JMenuItem menuCadastroComissoes;
    private javax.swing.JMenuItem menuCadastroDescontos;
    private javax.swing.JMenuItem menuCadastroEmpregados;
    private javax.swing.JMenuItem menuCadastroEmpresa;
    private javax.swing.JMenuItem menuCadastroFornecedores;
    private javax.swing.JMenuItem menuCadastroHistoricos;
    private javax.swing.JMenuItem menuCadastroProdutos;
    private javax.swing.JMenuItem menuCadastroUsuarios;
    private javax.swing.JMenuItem menuClassificacaoProdutos;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenuItem menuConsultasClientes;
    private javax.swing.JMenuItem menuConsultasHistoricos;
    private javax.swing.JMenuItem menuConsultasLancamentos;
    private javax.swing.JMenuItem menuConsultasProdutos;
    private javax.swing.JMenuItem menuContasAPagar;
    private javax.swing.JMenuItem menuContasAReceber;
    private javax.swing.JMenuBar menuPrincipal;
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
    private javax.swing.JPanel painelAtalhos;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelStatus;
    private javax.swing.JMenuItem porCusto;
    private javax.swing.JMenu processosCompras;
    private javax.swing.JMenuItem processosComprasConsultar;
    private javax.swing.JMenuItem processosFolhaDePagamento;
    private javax.swing.JMenuItem relCadastraisClientes;
    private javax.swing.JMenuItem relCadastraisFornecedores;
    private javax.swing.JMenu relatoriosControle;
    private javax.swing.JMenuItem relatoriosControleLivroCaixa;
    private javax.swing.JMenuItem relatoriosMovimentosCarne;
    private javax.swing.JMenuItem relatoriosMovimentosVenda;
    private javax.swing.JMenu vendas;
    // End of variables declaration//GEN-END:variables
}
