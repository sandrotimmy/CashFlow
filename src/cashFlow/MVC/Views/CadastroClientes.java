package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.ClientesDAO;
import cashFlow.MVC.Models.AlteraMinusculo;
import cashFlow.MVC.Models.Clientes;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
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

public class CadastroClientes extends javax.swing.JDialog implements InterfaceListener {

    private final ClientesDAO persistCliente;//persistencia dos dados
    private final ArrayList<Clientes> listaClientes;//recebe os clientes para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaCliente consultaCliente;//recebe a instancia da tela de consulta
    private Clientes cliente;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    private MetodosGerais mg;

    public CadastroClientes() {
        initComponents();//inicia componetnes da tela
        this.persistCliente = new ClientesDAO();
        this.mg = new MetodosGerais();
        listaClientes = persistCliente.getListaClientes();//busca do banco a lista de clientes
        carregaMascaraCpfCnpj();
        carregaPrimeiroCliente();//carrega primeiros dados e configurações na tela
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

    private void carregaPrimeiroCliente() {//carega dados e configurações
        limitaCampos();
        if (!listaClientes.isEmpty()) {
            posicao = listaClientes.size() - 1;//atualiza a posicao inicial
            exibeDados(listaClientes.get(posicao));//exibe primeiro cliente
            //desabilita todos campos
            comboCpfCnpj.setEnabled(false);
            campoCpfCnpj.setEnabled(false);
            campoInscEstadual.setEnabled(false);
            campoNome.setEnabled(false);
            campoEndereco.setEnabled(false);
            campoComplemento.setEnabled(false);
            campoNumero.setEnabled(false);
            campoMunicipio.setEnabled(false);
            campoBairro.setEnabled(false);
            comboBoxUF.setEnabled(false);
            campoCEP.setEnabled(false);
            campoCaixaPostal.setEnabled(false);
            campoDDD.setEnabled(false);
            campoFone.setEnabled(false);
            campoFax.setEnabled(false);
            campoEmail.setEnabled(false);
            //instancia a consulta a clientes
            consultaCliente = new ConsultaCliente();
            consultaCliente.setListener(this);
            //ajusta configurações
            botaoCadastrar.requestFocus();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Cliente Cadastrado, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);//se não tem cliente invoca o cadastrar cliente
        }
    }

    private void setCamposFocus() {
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoAtualizar.setFocusTraversalKeysEnabled(false);
        campoCpfCnpj.setFocusTraversalKeysEnabled(false);
    }

    private Clientes coletaDadosCampos() {

        cliente = new Clientes(
                Integer.parseInt(campoCodigo.getText()),
                comboCpfCnpj.getSelectedIndex(),
                mg.limpaCnpj(campoCpfCnpj.getText()),
                campoInscEstadual.getText(),
                campoNome.getText(),
                campoEndereco.getText(),
                mg.validaNumeros(campoNumero.getText()),
                campoComplemento.getText(),
                campoMunicipio.getText(),
                campoBairro.getText(),
                comboBoxUF.getSelectedItem().toString(),
                mg.validaCep(campoCEP.getText()),
                mg.validaNumeros(campoCaixaPostal.getText()),
                mg.validaNumeros(campoDDD.getText()),
                mg.validaFone(campoFone.getText()),
                mg.validaFone(campoFax.getText()),
                campoEmail.getText());
        return cliente;
    }

    private void limitaCampos() {//limita os campos de texto
        campoInscEstadual.setDocument(new DocumentoLimitado(14));
        campoNome.setDocument(new DocumentoLimitado(100));
        campoEndereco.setDocument(new DocumentoLimitado(30));
        campoComplemento.setDocument(new DocumentoLimitado(10));
        campoNumero.setDocument(new IntegerDocument(5));
        campoMunicipio.setDocument(new DocumentoLimitado(20));
        campoBairro.setDocument(new DocumentoLimitado(30));
        campoCaixaPostal.setDocument(new IntegerDocument(5));
        campoDDD.setDocument(new IntegerDocument(3));
        campoEmail.setDocument(new AlteraMinusculo(40));
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {//recebe o codigo da busca por listener e busca na lista
        int cod = Integer.parseInt(msg);
        Clientes clienteBusca = new Clientes();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCod() == cod) {
                clienteBusca = listaClientes.get(i);
                break;
            }
        }
        exibeDados(clienteBusca);//exibe o cliente encontrado
        posicao = listaClientes.indexOf(clienteBusca);//atualiza a posicao
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

    private void exibeDados(Clientes cliente) {//carrega na tela os dados do Cliente

        atribuiCpfCnpj(cliente.getTipoInscricao());
        campoCodigo.setText(Integer.toString(cliente.getCod()));
        comboCpfCnpj.setSelectedIndex(cliente.getTipoInscricao());
        campoCpfCnpj.setText(cliente.getCpfCnpj());
        campoInscEstadual.setText(cliente.getInscEstadual());
        campoNome.setText(cliente.getNome());
        campoEndereco.setText(cliente.getEndereco());
        campoNumero.setText(cliente.getNumero());
        campoComplemento.setText(cliente.getComplemento());
        campoMunicipio.setText(cliente.getMunicipio());
        campoBairro.setText(cliente.getBairro());
        comboBoxUF.setSelectedItem(cliente.getUf());
        campoCEP.setText(cliente.getCep());
        campoCaixaPostal.setText(cliente.getCaixapostal());
        campoDDD.setText(cliente.getDdd());
        campoFone.setText(cliente.getFone());
        campoFax.setText(cliente.getCel());
        campoEmail.setText(cliente.getEmail());
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        comboCpfCnpj.setEnabled(campos);
        campoCpfCnpj.setEnabled(campos);
        campoInscEstadual.setEnabled(campos);
        campoNome.setEnabled(campos);
        campoEndereco.setEnabled(campos);
        campoComplemento.setEnabled(campos);
        campoNumero.setEnabled(campos);
        campoMunicipio.setEnabled(campos);
        campoBairro.setEnabled(campos);
        comboBoxUF.setEnabled(campos);
        campoCEP.setEnabled(campos);
        campoCaixaPostal.setEnabled(campos);
        campoDDD.setEnabled(campos);
        campoFone.setEnabled(campos);
        campoFax.setEnabled(campos);
        campoEmail.setEnabled(campos);
        BotaListaPrimeiro.setEnabled(campos);
        BotaoListaAnterior.setEnabled(campos);
        BotaoListaProximo.setEnabled(campos);
        BotaoListaUltimo.setEnabled(campos);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoInscEstadual.setText("");
                campoNome.setText("");
                campoEndereco.setText("");
                campoComplemento.setText("");
                campoNumero.setText("");
                campoMunicipio.setText("");
                campoBairro.setText("");
                comboBoxUF.setSelectedItem("");
                campoCEP.setText("");
                campoCaixaPostal.setText("");
                campoDDD.setText("");
                campoFone.setText("");
                campoFax.setText("");
                campoEmail.setText("");
            }
            botaoAtualizar.setEnabled(botoes);
            BotaListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
        } else if (tipo == 2) {
            botaoExcluir.setEnabled(botoes);
            botaoCadastrar.setEnabled(botoes);
            BotaListaPrimeiro.setEnabled(botoes);
            BotaoListaAnterior.setEnabled(botoes);
            BotaoListaProximo.setEnabled(botoes);
            BotaoListaUltimo.setEnabled(botoes);
        }
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
        comboBoxUF = new javax.swing.JComboBox();
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
        labelCNPJ = new javax.swing.JLabel();
        labelCaixaPostal = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        BotaListaPrimeiro = new javax.swing.JButton();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();
        campoFone = new javax.swing.JTextField();
        campoFax = new javax.swing.JTextField();
        campoCEP = new javax.swing.JTextField();
        campoCodigo = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoCpfCnpj = new javax.swing.JFormattedTextField();
        comboCpfCnpj = new javax.swing.JComboBox<>();
        labelBairro = new javax.swing.JLabel();
        campoBairro = new javax.swing.JTextField();
        labelInscEstadual = new javax.swing.JLabel();
        campoInscEstadual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

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

        comboBoxUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "LS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        comboBoxUF.setToolTipText("");
        comboBoxUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUFActionPerformed(evt);
            }
        });

        campoEndereco.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNumero.setText("Número:");

        campoNumero.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labemMunicipio.setText("Município:");

        campoMunicipio.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelUF.setText("UF:");

        labelRazaoSocial.setText("Nome:");

        labelCEP.setText("CEP:");

        labelFax.setText("Cel:");

        labelEmail.setText("e-mail:");

        campoEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelCNPJ.setText("Código:");

        labelCaixaPostal.setText("Caixa Postal:");

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));

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

        try{
            javax.swing.text.MaskFormatter fone = new javax.swing.text.MaskFormatter("####-####");
            campoFone = new javax.swing.JFormattedTextField(fone);
            campoFone.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        try{
            javax.swing.text.MaskFormatter fax = new javax.swing.text.MaskFormatter("#####-####");
            campoFax = new javax.swing.JFormattedTextField(fax);
            campoFax.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        try{
            javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("##.###-###");
            campoCEP = new javax.swing.JFormattedTextField(cep);
            campoCEP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        campoCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCodigo.setEnabled(false);

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Inscrição:");

        campoCpfCnpj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCpfCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCpfCnpjKeyPressed(evt);
            }
        });

        comboCpfCnpj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.P.F.", "C.N.P.J." }));

        labelBairro.setText("Bairro:");

        campoBairro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBairroActionPerformed(evt);
            }
        });

        labelInscEstadual.setText("Insc. Estadual:");

        campoInscEstadual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoInscEstadual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoInscEstadualActionPerformed(evt);
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
                    .addComponent(labelCNPJ)
                    .addComponent(labelEndereco)
                    .addComponent(jLabel1)
                    .addComponent(labemMunicipio)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelUF))
                    .addComponent(labelDDD))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelFone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoFax, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEmail)
                        .addGap(18, 18, 18)
                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCaixaPostal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelComplemento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campoNome, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(labelCEP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(botaoCadastrar)
                                .addGap(4, 4, 4)))
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(botaoAtualizar)
                        .addGap(2, 2, 2)
                        .addComponent(botaoCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCpfCnpj))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BotaListaPrimeiro))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelInscEstadual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoListaUltimo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(campoEndereco)
                        .addGap(9, 9, 9)
                        .addComponent(labelNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(BotaListaPrimeiro)
                        .addComponent(BotaoListaAnterior)
                        .addComponent(BotaoListaProximo)
                        .addComponent(BotaoListaUltimo))
                    .addComponent(labelCNPJ)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInscEstadual)
                    .addComponent(campoInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRazaoSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumero)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelComplemento))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labemMunicipio)
                        .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelBairro)
                        .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCaixaPostal)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelUF)
                        .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCEP)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            campoCodigo.setText(Integer.toString(persistCliente.getProximoCliente()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            atribuiCpfCnpj(0);
            comboCpfCnpj.setSelectedIndex(0);
            botaoCadastrar.setText("Gravar");
            campoCpfCnpj.requestFocus();
        } else {
            cliente = coletaDadosCampos();
            persistCliente.cadastraClientes(cliente);
            listaClientes.add(cliente);
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            botaoCadastrar.setText("Cadastrar");
            posicao = listaClientes.size() - 1;
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Cliente?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (persistCliente.removeCliente(Integer.parseInt(campoCodigo.getText()))) {
                listaClientes.remove(posicao);
                posicao--;
                if (listaClientes.isEmpty()) {
                    botaoCadastrarActionPerformed(evt);
                } else {
                    exibeDados(listaClientes.get(posicao));
                }
            }
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed
    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            habilitaDesabilitaCampos(2, false, true, false);
            campoNome.requestFocus();
            botaoAtualizar.setText("Gravar");
        } else {
            cliente = coletaDadosCampos();
            listaClientes.remove(posicao);
            listaClientes.add(posicao, cliente);
            persistCliente.atualizaClientes(cliente);
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroClientes.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed
    private void BotaListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaListaPrimeiroActionPerformed
        exibeDados(listaClientes.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaListaPrimeiroActionPerformed
    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaClientes.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed
    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaClientes.size() - 1) {
            exibeDados(listaClientes.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed
    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaClientes.get(listaClientes.size() - 1));
        posicao = listaClientes.size() - 1;

    }//GEN-LAST:event_BotaoListaUltimoActionPerformed
    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaCliente.setVisible(true);
            consultaCliente.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }

    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void campoCpfCnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCpfCnpjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cnpjLimpo = mg.limpaCnpj(campoCpfCnpj.getText());
            if (!mg.validaCpfCnpj(cnpjLimpo)) {
                if (cnpjLimpo.equals("")) {
                    campoInscEstadual.requestFocus();
                    campoCpfCnpj.setValue(null);
                } else {
                    JOptionPane.showMessageDialog(this, "CPF/CNPJ Incorreto!");
                    campoCpfCnpj.requestFocus();
                }
            } else {
                campoInscEstadual.requestFocus();
            }
        }
    }//GEN-LAST:event_campoCpfCnpjKeyPressed

    private void campoBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBairroActionPerformed

    private void comboBoxUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUFActionPerformed

    private void campoInscEstadualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoInscEstadualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoInscEstadualActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroClientes().setVisible(true);
            }
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
    private javax.swing.JTextField campoBairro;
    private javax.swing.JTextField campoCEP;
    private javax.swing.JTextField campoCaixaPostal;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JFormattedTextField campoCpfCnpj;
    private javax.swing.JTextField campoDDD;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoFax;
    private javax.swing.JTextField campoFone;
    private javax.swing.JTextField campoInscEstadual;
    private javax.swing.JTextField campoMunicipio;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JComboBox comboBoxUF;
    private javax.swing.JComboBox<String> comboCpfCnpj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCaixaPostal;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDDD;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelFone;
    private javax.swing.JLabel labelInscEstadual;
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
    public void mensagemCodProdutoBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodFornecedorCompra(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodFornecedorBusca(String msg) {
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
