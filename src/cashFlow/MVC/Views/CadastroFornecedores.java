package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.FornecedoresDAO;
import cashFlow.MVC.Models.AlteraMinusculo;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.Fornecedores;
import cashFlow.MVC.Models.IntegerDocument;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public final class CadastroFornecedores extends javax.swing.JDialog implements InterfaceListener {

    private final FornecedoresDAO persistFornecedor;//persistencia dos dados
    private final ArrayList<Fornecedores> listaFornecedores;//recebe os fornecedores para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaFornecedor consultaFornecedor;//recebe a instancia da tela de consulta
    private Fornecedores fornecedor;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    private MetodosGerais mg;

    public CadastroFornecedores() {
        initComponents();//inicia componentes da tela
        this.persistFornecedor = new FornecedoresDAO();
        this.mg = new MetodosGerais();
        listaFornecedores = persistFornecedor.getListaFornecedores();//busca do banco a lista de fornecedores
        carregaMascaraCpfCnpj();
        carregaPrimeiroFornecedor();//carrega primeiros dados e configurações na tela
        setCamposFocus();
    }

    public void carregaMascaraCpfCnpj() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            Logger.getLogger(CadastroClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboCpfCnpj.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboCpfCnpj.getSelectedIndex() == 0) {
                        campoCpfCnpj.setValue(null);
                        campoCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
                    } else {
                        campoCpfCnpj.setValue(null);
                        campoCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
                    }
                }
            }
        });
    }

    public void carregaPrimeiroFornecedor() {
        limitaCampos();
        if (!listaFornecedores.isEmpty()) {
            posicao = listaFornecedores.size() - 1;//atualiza a posicao inicial
            exibeDados(listaFornecedores.get(posicao));
            campoCpfCnpj.setEnabled(false);
            comboCpfCnpj.setEnabled(false);
            campoRazaoSocial.setEnabled(false);
            campoNomeFantasia.setEnabled(false);
            campoEndereco.setEnabled(false);
            campoComplemento.setEnabled(false);
            campoNumero.setEnabled(false);
            campoMunicipio.setEnabled(false);
            comboBoxUF.setEnabled(false);
            campoCEP.setEnabled(false);
            campoCaixaPostal.setEnabled(false);
            campoDDD.setEnabled(false);
            campoFone.setEnabled(false);
            campoFax.setEnabled(false);
            campoEmail.setEnabled(false);
            //instancia a consulta a clientes
            consultaFornecedor = new ConsultaFornecedor();
            consultaFornecedor.setListener(this);
            posicao = listaFornecedores.size() - 1;
            botaoCadastrar.requestFocus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Fornecedor Cadastrado, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);
        }
    }

    private void setCamposFocus() {
        //ajusta as configuracoes
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoAtualizar.setFocusTraversalKeysEnabled(false);
        campoCpfCnpj.setFocusTraversalKeysEnabled(false);
    }

    private void limitaCampos() {//limita os campos de texto
        campoRazaoSocial.setDocument(new DocumentoLimitado(50));
        campoNomeFantasia.setDocument(new DocumentoLimitado(30));
        campoEndereco.setDocument(new DocumentoLimitado(30));
        campoComplemento.setDocument(new DocumentoLimitado(10));
        campoNumero.setDocument(new IntegerDocument(5));
        campoMunicipio.setDocument(new DocumentoLimitado(20));
        campoCaixaPostal.setDocument(new IntegerDocument(5));
        campoDDD.setDocument(new IntegerDocument(3));
        campoEmail.setDocument(new AlteraMinusculo(40));
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {//recebe o codigo da busca por listener e busca na lista
        int cod = Integer.parseInt(msg);
        Fornecedores fornecedorBusca = new Fornecedores();
        for (int i = 0; i < listaFornecedores.size(); i++) {
            if (listaFornecedores.get(i).getCod() == cod) {
                fornecedorBusca = listaFornecedores.get(i);
                break;
            }
        }
        exibeDados(fornecedorBusca);//exibe o fornecedor encontrado
        posicao = listaFornecedores.indexOf(fornecedorBusca);//atualiza a posicao

    }

    public void atribuiCpfCnpj(int tipoInscricao) {
        int pos = tipoInscricao;
        if (pos == 0) {
            campoCpfCnpj.setValue(null);
            campoCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } else {
            campoCpfCnpj.setValue(null);
            campoCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        }
    }

    private void exibeDados(Fornecedores fornecedor) {//carrega na tela os dados do Fornecedor
        if (!listaFornecedores.isEmpty()) {
            atribuiCpfCnpj(fornecedor.getTipoInscricao());
            campoCodigo.setText(Integer.toString(fornecedor.getCod()));
            comboCpfCnpj.setSelectedIndex(fornecedor.getTipoInscricao());
            campoCpfCnpj.setText(fornecedor.getCpfCnpj());
            campoRazaoSocial.setText(fornecedor.getRazaosocial());
            campoNomeFantasia.setText(fornecedor.getNomefantasia());
            campoEndereco.setText(fornecedor.getEndereco());
            campoNumero.setText(fornecedor.getNumero());
            campoComplemento.setText(fornecedor.getComplemento());
            campoMunicipio.setText(fornecedor.getMunicipio());
            comboBoxUF.setSelectedItem(fornecedor.getUf());
            campoCEP.setText(fornecedor.getCep());
            campoCaixaPostal.setText(fornecedor.getCaixapostal());
            campoDDD.setText(fornecedor.getDdd());
            campoFone.setText(fornecedor.getFone());
            campoFax.setText(fornecedor.getCel());
            campoEmail.setText(fornecedor.getEmail());
        }
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        campoCpfCnpj.setEnabled(campos);
        campoRazaoSocial.setEnabled(campos);
        campoNomeFantasia.setEnabled(campos);
        campoEndereco.setEnabled(campos);
        campoComplemento.setEnabled(campos);
        campoNumero.setEnabled(campos);
        campoMunicipio.setEnabled(campos);
        comboBoxUF.setEnabled(campos);
        campoCEP.setEnabled(campos);
        campoCaixaPostal.setEnabled(campos);
        campoDDD.setEnabled(campos);
        campoFone.setEnabled(campos);
        campoFax.setEnabled(campos);
        campoEmail.setEnabled(campos);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoCpfCnpj.setText("");
                campoRazaoSocial.setText("");
                campoNomeFantasia.setText("");
                campoEndereco.setText("");
                campoComplemento.setText("");
                campoNumero.setText("");
                campoMunicipio.setText("");
                comboBoxUF.setSelectedItem("");
                campoCEP.setText("");
                campoCaixaPostal.setText("");
                campoDDD.setText("");
                campoFone.setText("");
                campoFax.setText("");
                campoEmail.setText("");
            }
            botaoAtualizar.setEnabled(botoes);
        } else if (tipo == 2) {
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
        }
    }

    private Fornecedores coletaDadosCampos() {
        fornecedor = new Fornecedores();
        fornecedor = new Fornecedores(Integer.parseInt(
                campoCodigo.getText()),
                comboCpfCnpj.getSelectedIndex(),
                mg.limpaCnpj(campoCpfCnpj.getText()),
                campoRazaoSocial.getText(),
                campoNomeFantasia.getText(),
                campoEndereco.getText(),
                mg.validaNumeros(campoNumero.getText()),
                campoComplemento.getText(),
                campoMunicipio.getText(),
                comboBoxUF.getSelectedItem().toString(),
                mg.validaCep(campoCEP.getText()),
                mg.validaNumeros(campoCaixaPostal.getText()),
                mg.validaNumeros(campoDDD.getText()),
                mg.validaFone(campoFone.getText()),
                mg.validaFone(campoFax.getText()),
                campoEmail.getText());

        return fornecedor;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCaixaPostal = new javax.swing.JTextField();
        botaoCadastrar = new javax.swing.JButton();
        labelDDD = new javax.swing.JLabel();
        botaoExcluir = new javax.swing.JButton();
        labelFone = new javax.swing.JLabel();
        botaoAtualizar = new javax.swing.JButton();
        campoDDD = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        labelComplemento = new javax.swing.JLabel();
        campoComplemento = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        campoNomeFantasia = new javax.swing.JTextField();
        comboBoxUF = new javax.swing.JComboBox();
        labelNomeFantasia = new javax.swing.JLabel();
        campoEndereco = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        campoNumero = new javax.swing.JTextField();
        labemMunicipio = new javax.swing.JLabel();
        campoMunicipio = new javax.swing.JTextField();
        labelUF = new javax.swing.JLabel();
        labelRazaoSocial = new javax.swing.JLabel();
        labelCEP = new javax.swing.JLabel();
        labelFax = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        campoFone = new javax.swing.JFormattedTextField();
        campoFax = new javax.swing.JFormattedTextField();
        labelInscricao = new javax.swing.JLabel();
        campoCEP = new javax.swing.JFormattedTextField();
        labelCaixaPostal = new javax.swing.JLabel();
        campoRazaoSocial = new javax.swing.JTextField();
        BotaListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        labelCodigo = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        comboCpfCnpj = new javax.swing.JComboBox<>();
        campoCpfCnpj = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecedores");

        campoCaixaPostal.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        labelDDD.setText("DDD:");

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        labelFone.setText("Fone:");

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        campoDDD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        labelComplemento.setText("Compl:");

        campoComplemento.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelEndereco.setText("Endereço:");

        campoNomeFantasia.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        comboBoxUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "LS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        labelNomeFantasia.setText("Nome Fantasia:");

        campoEndereco.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNumero.setText("Número:");

        campoNumero.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labemMunicipio.setText("Município:");

        campoMunicipio.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelUF.setText("UF:");

        labelRazaoSocial.setText("Razão Social:");

        labelCEP.setText("CEP:");

        labelFax.setText("Cel:");

        labelEmail.setText("e-mail:");

        campoEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        campoFone.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoFone.setOpaque(false);
        try{
            javax.swing.text.MaskFormatter fone = new javax.swing.text.MaskFormatter("####-####");
            campoFone = new javax.swing.JFormattedTextField(fone);
            campoFone.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        campoFax.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoFax.setOpaque(false);
        try{
            javax.swing.text.MaskFormatter fax = new javax.swing.text.MaskFormatter("#####-####");
            campoFax = new javax.swing.JFormattedTextField(fax);
            campoFax.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        labelInscricao.setText("Inscrição:");

        campoCEP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoCEP.setOpaque(false);
        try{
            javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("##.###-###");
            campoCEP = new javax.swing.JFormattedTextField(cep);
            campoCEP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        labelCaixaPostal.setText("Caixa Postal:");

        campoRazaoSocial.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        BotaListaPrimeiro.setText("|<");
        BotaListaPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaListaPrimeiroActionPerformed(evt);
            }
        });

        BotaoListaAnterior.setText("<");
        BotaoListaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaAnteriorActionPerformed(evt);
            }
        });

        BotaoListaProximo.setText(">");
        BotaoListaProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaProximoActionPerformed(evt);
            }
        });

        BotaoListaUltimo.setText(">|");
        BotaoListaUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoListaUltimoActionPerformed(evt);
            }
        });

        labelCodigo.setText("Código:");

        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCodigo.setEnabled(false);

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        comboCpfCnpj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.P.F.", "C.N.P.J." }));

        campoCpfCnpj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCpfCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCpfCnpjKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRazaoSocial)
                    .addComponent(labelInscricao)
                    .addComponent(labelEndereco)
                    .addComponent(labemMunicipio)
                    .addComponent(labelDDD)
                    .addComponent(labelNomeFantasia)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoEndereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNumero))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelFone)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelUF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCEP))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelFax)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoFax, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelEmail)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoEmail)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campoNumero, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoCEP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelCaixaPostal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelComplemento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoComplemento))))))
                    .addComponent(campoRazaoSocial, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoNomeFantasia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCpfCnpj))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaListaPrimeiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaUltimo)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, botaoBuscar, botaoCadastrar, botaoCancelar, botaoExcluir});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaListaPrimeiro, BotaoListaAnterior, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotaoListaUltimo)
                        .addComponent(BotaoListaProximo)
                        .addComponent(BotaoListaAnterior)
                        .addComponent(BotaListaPrimeiro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCodigo)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoBuscar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelInscricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRazaoSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeFantasia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumero)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelComplemento)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labemMunicipio)
                    .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUF)
                    .addComponent(labelCEP)
                    .addComponent(labelCaixaPostal)
                    .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFone)
                    .addComponent(labelFax)
                    .addComponent(labelEmail)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDDD)
                    .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoAtualizar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (!botaoCadastrar.getText().equals("Gravar")) {
            campoCodigo.setText(Integer.toString(persistFornecedor.getProximoFornecedor()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            atribuiCpfCnpj(1);
            comboCpfCnpj.setSelectedIndex(1);
            botaoCadastrar.setText("Gravar");
            campoCpfCnpj.requestFocus();
        } else {
            fornecedor = coletaDadosCampos();
            persistFornecedor.cadastraFornecedor(fornecedor);
            listaFornecedores.add(fornecedor);
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            posicao = listaFornecedores.size() - 1;//atualiza a posição
            botaoCadastrar.setText("Cadastrar");
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Fornecedor?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (persistFornecedor.removeFornecedor(Integer.parseInt(campoCodigo.getText()))) {
                listaFornecedores.remove(posicao);
                posicao--;
                if (listaFornecedores.isEmpty()) {
                    botaoCadastrarActionPerformed(evt);
                } else {
                    exibeDados(listaFornecedores.get(posicao));
                }
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed
    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            habilitaDesabilitaCampos(2, false, true, false);
            campoCpfCnpj.requestFocus();
            botaoAtualizar.setText("Gravar");
        } else {
            fornecedor = coletaDadosCampos();
            listaFornecedores.remove(posicao);//remove da lista
            listaFornecedores.add(posicao, fornecedor);//adiciona atualizado na mesma posição
            persistFornecedor.atualizaFornecedor(fornecedor);//persiste no banco
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroFornecedores.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void BotaListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaListaPrimeiroActionPerformed
        posicao = 0;
        exibeDados(listaFornecedores.get(posicao));
    }//GEN-LAST:event_BotaListaPrimeiroActionPerformed
    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            posicao--;
            exibeDados(listaFornecedores.get(posicao));
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed
    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaFornecedores.size() - 1) {
            posicao++;
            exibeDados(listaFornecedores.get(posicao));

        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed
    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        posicao = listaFornecedores.size() - 1;
        exibeDados(listaFornecedores.get(posicao));
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed
    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaFornecedor.setVisible(true);
            consultaFornecedor.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void campoCpfCnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCpfCnpjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cnpjLimpo = mg.limpaCnpj(campoCpfCnpj.getText());
            if (!mg.validaCpfCnpj(cnpjLimpo)) {
                if (cnpjLimpo.equals("")) {
                    campoRazaoSocial.requestFocus();
                    campoCpfCnpj.setValue(null);
                } else {
                    JOptionPane.showMessageDialog(this, "CPF/CNPJ Incorreto!");
                    campoCpfCnpj.requestFocus();
                }
            } else {
                campoRazaoSocial.requestFocus();
            }
        }
    }//GEN-LAST:event_campoCpfCnpjKeyPressed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CadastroFornecedores dialog = new CadastroFornecedores();
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
    private javax.swing.JButton BotaListaPrimeiro;
    private javax.swing.JButton BotaoListaAnterior;
    private javax.swing.JButton BotaoListaProximo;
    private javax.swing.JButton BotaoListaUltimo;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JFormattedTextField campoCEP;
    private javax.swing.JTextField campoCaixaPostal;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JFormattedTextField campoCpfCnpj;
    private javax.swing.JTextField campoDDD;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JFormattedTextField campoFax;
    private javax.swing.JFormattedTextField campoFone;
    private javax.swing.JTextField campoMunicipio;
    private javax.swing.JTextField campoNomeFantasia;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoRazaoSocial;
    private javax.swing.JComboBox comboBoxUF;
    private javax.swing.JComboBox<String> comboCpfCnpj;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCaixaPostal;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDDD;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelFone;
    private javax.swing.JLabel labelInscricao;
    private javax.swing.JLabel labelNomeFantasia;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRazaoSocial;
    private javax.swing.JLabel labelUF;
    private javax.swing.JLabel labemMunicipio;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodFornecedorCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodHistoricoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodProdutoReajuste(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vendaCancelar(Vendas venda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compraCancelar(Compras compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parcelamentoVendas(ParcelamentoVendas parcelamentoVendas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
