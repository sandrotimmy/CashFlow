package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.EmpregadosCtrl;
import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.Models.AlteraMinusculo;
import cashFlow.MVC.Models.Compras;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.Empregados;
import cashFlow.MVC.Models.IntegerDocument;
import cashFlow.MVC.Models.JNumberFormatField;
import cashFlow.MVC.Models.ParcelamentoVendas;
import cashFlow.MVC.Models.Vendas;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CadastroEmpregados extends javax.swing.JDialog implements InterfaceListener {

    private final EmpregadosCtrl empregadosCtrl;//controle de empregados
    private final List<Empregados> listaEmpregados;//recebe os empregados para exibir na tela
    private int posicao;//controla o caminhamento do cadastro
    private ConsultaEmpregado consultaEmpregado;//recebe a instancia da tela de consulta
    private Empregados empregado;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    private MetodosGerais mg;

    public CadastroEmpregados() {
        initComponents();//inicia componentes da tela
        this.empregadosCtrl = new EmpregadosCtrl();
        this.mg = new MetodosGerais();
        listaEmpregados = empregadosCtrl.getListaEmpregados();//busca do banco a lista de empregados
        carregaMascaraCpfCnpj();
        carregaPrimeiroEmpregado();//carrega primeiros dados e configurações na tela
        setCamposFocus();
    }

    public void carregaMascaraCpfCnpj() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            Logger.getLogger(CadastroEmpregados.class.getName()).log(Level.SEVERE, null, ex);
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

    private void carregaPrimeiroEmpregado() {//carega dados e configurações
        limitaCampos();
        if (!listaEmpregados.isEmpty()) {
            posicao = listaEmpregados.size() - 1;//atualiza a posicao inicial
            exibeDados(listaEmpregados.get(posicao));//exibe primeiro empregado
            //desabilita todos campos
            comboCpfCnpj.setEnabled(false);
            campoCpfCnpj.setEnabled(false);
            campoRg.setEnabled(false);
            campoNome.setEnabled(false);
            campoDataNasc.setEnabled(false);
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
            campoCel.setEnabled(false);
            campoEmail.setEnabled(false);
            campoCargo.setEnabled(false);
            campoDataAdmissao.setEnabled(false);
            comboCategoria.setEnabled(false);
            comboFormaPgto.setEnabled(false);
            campoSalario.setEnabled(false);
            //instancia a consulta a empregados
            consultaEmpregado = new ConsultaEmpregado();
            consultaEmpregado.setListener(this);
            //ajusta configurações
            botaoCadastrar.requestFocus();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Empregado Cadastrado, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);//se não tem empregado invoca o cadastrar empregado
        }
    }

    private void setCamposFocus() {
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        botaoAtualizar.setFocusTraversalKeysEnabled(false);
        campoCpfCnpj.setFocusTraversalKeysEnabled(false);
    }

    private Empregados coletaDadosCampos() {
        empregado = new Empregados(
                Integer.parseInt(campoCodigo.getText()),
                comboCpfCnpj.getSelectedIndex(),
                mg.limpaCnpj(campoCpfCnpj.getText()),
                campoRg.getText(),
                campoNome.getText(),
                mg.convDataBanco(campoDataNasc.getText()),
//                mg.convDataBanco(campoDataNasc.getText()),
                campoEndereco.getText(),
                mg.validaNumeros(campoNumero.getText()),
                campoComplemento.getText(),
                campoMunicipio.getText(),
                campoBairro.getText(),
                comboBoxUF.getSelectedItem().toString(),
                mg.validaNumeros(campoCaixaPostal.getText()),
                mg.validaCep(campoCEP.getText()),
                mg.validaNumeros(campoDDD.getText()),
                mg.validaFone(campoFone.getText()),
                mg.validaFone(campoCel.getText()),
                campoEmail.getText(),
                campoCargo.getText(),
                mg.convDataBanco(campoDataAdmissao.getText()),
                comboCategoria.getSelectedItem().toString(),
                comboFormaPgto.getSelectedItem().toString(),
                mg.convValorBanco(campoSalario.getText())
        );
        return empregado;
    }

    private void limitaCampos() {//limita os campos de texto
        campoRg.setDocument(new DocumentoLimitado(14));
        campoNome.setDocument(new DocumentoLimitado(100));
        campoEndereco.setDocument(new DocumentoLimitado(30));
        campoComplemento.setDocument(new DocumentoLimitado(10));
        campoNumero.setDocument(new IntegerDocument(5));
        campoMunicipio.setDocument(new DocumentoLimitado(20));
        campoBairro.setDocument(new DocumentoLimitado(30));
        campoCaixaPostal.setDocument(new IntegerDocument(5));
        campoDDD.setDocument(new IntegerDocument(3));
        campoEmail.setDocument(new AlteraMinusculo(40));
        campoCargo.setDocument(new DocumentoLimitado(40));
        
    }

    public void mensagemCodEmpregadoBusca(String msg) {//recebe o codigo da busca por listener e busca na lista
        int cod = Integer.parseInt(msg);
        Empregados empregadoBusca = new Empregados();
        for (int i = 0; i < listaEmpregados.size(); i++) {
            if (listaEmpregados.get(i).getCod() == cod) {
                empregadoBusca = listaEmpregados.get(i);
                break;
            }
        }
        exibeDados(empregadoBusca);//exibe o empregado encontrado
        posicao = listaEmpregados.indexOf(empregadoBusca);//atualiza a posicao
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

    private void exibeDados(Empregados empregado) {//carrega na tela os dados do Empregado

        atribuiCpfCnpj(empregado.getTipoInscricao());
        campoCodigo.setText(Integer.toString(empregado.getCod()));
        comboCpfCnpj.setSelectedIndex(empregado.getTipoInscricao());
        campoCpfCnpj.setText(empregado.getCpfCnpj());
        campoRg.setText(empregado.getInscEstadual());
        campoNome.setText(empregado.getNome());
        campoDataNasc.setText(mg.convDataSistema(empregado.getDataNascimento()));
        campoEndereco.setText(empregado.getEndereco());
        campoNumero.setText(empregado.getNumero());
        campoComplemento.setText(empregado.getComplemento());
        campoMunicipio.setText(empregado.getMunicipio());
        campoBairro.setText(empregado.getBairro());
        comboBoxUF.setSelectedItem(empregado.getUf());
        campoCEP.setText(empregado.getCep());
        campoCaixaPostal.setText(empregado.getCaixapostal());
        campoDDD.setText(empregado.getDdd());
        campoFone.setText(empregado.getFone());
        campoCel.setText(empregado.getCel());
        campoEmail.setText(empregado.getEmail());
        campoCargo.setText(empregado.getCargo());
        campoDataAdmissao.setText(mg.convDataSistema(empregado.getDataAdmissao()));
        comboCategoria.setSelectedItem(empregado.getCategoria());
        comboFormaPgto.setSelectedItem(empregado.getFormaPgto());
        campoSalario.setText(empregado.getSalario().toString());
    }

    public void habilitaDesabilitaCampos(int tipo, boolean limpaCampos, boolean campos, boolean botoes) {
        comboCpfCnpj.setEnabled(campos);
        campoCpfCnpj.setEnabled(campos);
        campoRg.setEnabled(campos);
        campoNome.setEnabled(campos);
        campoDataNasc.setEnabled(campos);
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
        campoCel.setEnabled(campos);
        campoEmail.setEnabled(campos);
        campoCargo.setEnabled(campos);
        campoDataAdmissao.setEnabled(campos);
        comboCategoria.setEnabled(campos);
        comboFormaPgto.setEnabled(campos);
        campoSalario.setEnabled(campos);
        BotaListaPrimeiro.setEnabled(campos);
        BotaoListaAnterior.setEnabled(campos);
        BotaoListaProximo.setEnabled(campos);
        BotaoListaUltimo.setEnabled(campos);
        botaoExcluir.setEnabled(botoes);
        if (tipo == 1) {//Cadastrar
            if (limpaCampos == true) {
                campoRg.setText("");
                campoNome.setText("");
                campoDataNasc.setText("");
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
                campoCel.setText("");
                campoEmail.setText("");
                campoCargo.setText("");
                campoDataAdmissao.setText("");
                comboCategoria.setSelectedIndex(0);
                comboFormaPgto.setSelectedIndex(0);
                campoSalario.setText("");
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

        botaoCadastrar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        BotaListaPrimeiro = new javax.swing.JButton();
        abas = new javax.swing.JTabbedPane();
        abaGeral = new javax.swing.JPanel();
        labelFax = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        campoCaixaPostal = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        labelCNPJ = new javax.swing.JLabel();
        labelDDD = new javax.swing.JLabel();
        labelCaixaPostal = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        labelFone = new javax.swing.JLabel();
        campoDDD = new javax.swing.JTextField();
        labelRg = new javax.swing.JLabel();
        labelComplemento = new javax.swing.JLabel();
        campoRg = new javax.swing.JTextField();
        campoComplemento = new javax.swing.JTextField();
        labelDataNascimento = new javax.swing.JLabel();
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
        campoFone = new javax.swing.JTextField();
        campoCel = new javax.swing.JTextField();
        campoCEP = new javax.swing.JTextField();
        campoCodigo = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        labelInscricao = new javax.swing.JLabel();
        campoCpfCnpj = new javax.swing.JFormattedTextField();
        comboCpfCnpj = new javax.swing.JComboBox<>();
        labelBairro = new javax.swing.JLabel();
        campoBairro = new javax.swing.JTextField();
        campoDataNasc = new javax.swing.JTextField();
        abaAdmissao = new javax.swing.JPanel();
        labelCargo = new javax.swing.JLabel();
        campoCargo = new javax.swing.JTextField();
        labelDataAdmissao = new javax.swing.JLabel();
        labelSalario = new javax.swing.JLabel();
        campoSalario = new JNumberFormatField();
        campoDataAdmissao = new javax.swing.JTextField();
        labelCategoria = new javax.swing.JLabel();
        comboFormaPgto = new javax.swing.JComboBox<>();
        labelFormaPgto = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox<>();
        BotaoListaAnterior = new javax.swing.JButton();
        BotaoListaProximo = new javax.swing.JButton();
        BotaoListaUltimo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Empregados");

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        BotaListaPrimeiro.setText("|<");
        BotaListaPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaListaPrimeiroActionPerformed(evt);
            }
        });

        labelFax.setText("Cel:");

        labelEmail.setText("e-mail:");

        campoCaixaPostal.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        campoEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelCNPJ.setText("Código:");

        labelDDD.setText("DDD:");

        labelCaixaPostal.setText("Caixa Postal:");

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelFone.setText("Fone:");

        campoDDD.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelRg.setText("RG:");

        labelComplemento.setText("Compl:");

        campoRg.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        campoComplemento.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelDataNascimento.setText("Dt. Nasc.");

        labelEndereco.setText("Endereço:");

        comboBoxUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "LS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        comboBoxUF.setToolTipText("");

        campoEndereco.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNumero.setText("Número:");

        campoNumero.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labemMunicipio.setText("Município:");

        campoMunicipio.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelUF.setText("UF:");

        labelRazaoSocial.setText("Nome:");

        labelCEP.setText("CEP:");

        try{
            javax.swing.text.MaskFormatter fone = new javax.swing.text.MaskFormatter("####-####");
            campoFone = new javax.swing.JFormattedTextField(fone);
            campoFone.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        try{
            javax.swing.text.MaskFormatter fax = new javax.swing.text.MaskFormatter("#####-####");
            campoCel = new javax.swing.JFormattedTextField(fax);
            campoCel.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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

        labelInscricao.setText("Inscrição:");

        campoCpfCnpj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCpfCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCpfCnpjKeyPressed(evt);
            }
        });

        comboCpfCnpj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.P.F.", "C.N.P.J." }));

        labelBairro.setText("Bairro:");

        campoBairro.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataNasc = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoDataNasc.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout abaGeralLayout = new javax.swing.GroupLayout(abaGeral);
        abaGeral.setLayout(abaGeralLayout);
        abaGeralLayout.setHorizontalGroup(
            abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRazaoSocial)
                    .addComponent(labelEndereco)
                    .addComponent(labelInscricao)
                    .addComponent(labemMunicipio)
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelUF))
                    .addComponent(labelDDD)
                    .addComponent(labelCNPJ))
                .addGap(18, 18, 18)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaGeralLayout.createSequentialGroup()
                                .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCpfCnpj))
                            .addGroup(abaGeralLayout.createSequentialGroup()
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRg, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(labelFone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEmail)
                        .addGap(18, 18, 18)
                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCEP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCaixaPostal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaGeralLayout.createSequentialGroup()
                        .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelComplemento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaGeralLayout.createSequentialGroup()
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoEndereco)
                            .addComponent(campoNome))
                        .addGap(5, 5, 5)
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNumero)
                            .addComponent(labelDataNascimento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(campoDataNasc))))
                .addContainerGap())
        );
        abaGeralLayout.setVerticalGroup(
            abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCNPJ)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInscricao)
                    .addComponent(campoCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRg)
                    .addComponent(campoRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRazaoSocial)
                    .addComponent(labelDataNascimento)
                    .addComponent(campoDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumero)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelComplemento))
                    .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labemMunicipio)
                        .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelBairro)
                        .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCaixaPostal)
                    .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCEP))
                    .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelUF)
                        .addComponent(campoCaixaPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFone)
                    .addComponent(labelFax)
                    .addComponent(labelEmail)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDDD)
                    .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        abas.addTab("Geral", abaGeral);

        labelCargo.setText("Cargo:");

        campoCargo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelDataAdmissao.setText("Dt. Admissão:");

        labelSalario.setText("Salário:");

        campoSalario.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            campoDataAdmissao = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        campoDataAdmissao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelCategoria.setText("Catagoria:");

        comboFormaPgto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Salário Fixo", "Comissões", "Salário fixo + Comissões" }));

        labelFormaPgto.setText("Forma de Pagamento");

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Mensalista", "Semanalista", "Diarista", "Horista", "Tarefeiro" }));

        javax.swing.GroupLayout abaAdmissaoLayout = new javax.swing.GroupLayout(abaAdmissao);
        abaAdmissao.setLayout(abaAdmissaoLayout);
        abaAdmissaoLayout.setHorizontalGroup(
            abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaAdmissaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCargo)
                    .addGroup(abaAdmissaoLayout.createSequentialGroup()
                        .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSalario)
                            .addComponent(labelFormaPgto))
                        .addGap(20, 20, 20)
                        .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoDataAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelDataAdmissao)
                    .addComponent(labelCategoria))
                .addGap(0, 349, Short.MAX_VALUE))
        );
        abaAdmissaoLayout.setVerticalGroup(
            abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaAdmissaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCargo)
                    .addComponent(campoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataAdmissao)
                    .addComponent(campoDataAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaAdmissaoLayout.createSequentialGroup()
                        .addComponent(labelCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFormaPgto)
                            .addComponent(comboFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaAdmissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSalario)
                    .addComponent(campoSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        abas.addTab("Admissão", abaAdmissao);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BotaListaPrimeiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaProximo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoListaUltimo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botaoCadastrar)
                                .addGap(4, 4, 4)
                                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(botaoAtualizar)
                                .addGap(2, 2, 2)
                                .addComponent(botaoCancelar)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotaListaPrimeiro, BotaoListaAnterior, BotaoListaProximo, BotaoListaUltimo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaListaPrimeiro)
                    .addComponent(BotaoListaAnterior)
                    .addComponent(BotaoListaProximo)
                    .addComponent(BotaoListaUltimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            campoCodigo.setText(Integer.toString(empregadosCtrl.getProximoEmpregado()));
            habilitaDesabilitaCampos(1, true, true, false);// tipo 1 - cadastrar, campos habilitados, botoes desabilitados
            atribuiCpfCnpj(0);
            comboCpfCnpj.setSelectedIndex(0);
            botaoCadastrar.setText("Gravar");
            campoCpfCnpj.requestFocus();
        } else {
            empregado = coletaDadosCampos();
            empregadosCtrl.cadastraEmpregados(empregado);
            listaEmpregados.add(empregado);
            habilitaDesabilitaCampos(1, false, false, true);// tipo 1 - cadastrar, limpar campos, campos desabilitados, botoes habilitados
            botaoCadastrar.setText("Cadastrar");
            posicao = listaEmpregados.size() - 1;
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este Empregado?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (empregadosCtrl.removeEmpregado(Integer.parseInt(campoCodigo.getText()))) {
                listaEmpregados.remove(posicao);
                posicao--;
                if (listaEmpregados.isEmpty()) {
                    botaoCadastrarActionPerformed(evt);
                } else {
                    exibeDados(listaEmpregados.get(posicao));
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
            empregado = coletaDadosCampos();
            listaEmpregados.remove(posicao);
            listaEmpregados.add(posicao, empregado);
            empregadosCtrl.atualizaEmpregados(empregado);
            habilitaDesabilitaCampos(2, false, false, true);
            botaoAtualizar.setText("Atualizar");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroEmpregados.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void campoCpfCnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCpfCnpjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cnpjLimpo = mg.limpaCnpj(campoCpfCnpj.getText());
            if (!mg.validaCpfCnpj(cnpjLimpo)) {
                if (cnpjLimpo.equals("")) {
                    campoRg.requestFocus();
                    campoCpfCnpj.setValue(null);
                } else {
                    JOptionPane.showMessageDialog(this, "CPF/CNPJ Incorreto!");
                    campoCpfCnpj.requestFocus();
                }
            } else {
                campoRg.requestFocus();
            }
        }
    }//GEN-LAST:event_campoCpfCnpjKeyPressed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            consultaEmpregado.setVisible(true);
            consultaEmpregado.setLocationRelativeTo(null);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Feche a Tela de cadastro e abra Novamente!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void BotaoListaUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaUltimoActionPerformed
        exibeDados(listaEmpregados.get(listaEmpregados.size() - 1));
        posicao = listaEmpregados.size() - 1;
    }//GEN-LAST:event_BotaoListaUltimoActionPerformed

    private void BotaoListaProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaProximoActionPerformed
        if (posicao < listaEmpregados.size() - 1) {
            exibeDados(listaEmpregados.get(posicao + 1));
            posicao++;
        }
    }//GEN-LAST:event_BotaoListaProximoActionPerformed

    private void BotaoListaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoListaAnteriorActionPerformed
        if (posicao > 0) {
            exibeDados(listaEmpregados.get(posicao - 1));
            posicao--;
        }
    }//GEN-LAST:event_BotaoListaAnteriorActionPerformed

    private void BotaListaPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaListaPrimeiroActionPerformed
        exibeDados(listaEmpregados.get(0));
        posicao = 0;
    }//GEN-LAST:event_BotaListaPrimeiroActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroEmpregados().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaListaPrimeiro;
    private javax.swing.JButton BotaoListaAnterior;
    private javax.swing.JButton BotaoListaProximo;
    private javax.swing.JButton BotaoListaUltimo;
    private javax.swing.JPanel abaAdmissao;
    private javax.swing.JPanel abaGeral;
    private javax.swing.JTabbedPane abas;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JTextField campoCEP;
    private javax.swing.JTextField campoCaixaPostal;
    private javax.swing.JTextField campoCargo;
    private javax.swing.JTextField campoCel;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JFormattedTextField campoCpfCnpj;
    private javax.swing.JTextField campoDDD;
    private javax.swing.JTextField campoDataAdmissao;
    private javax.swing.JTextField campoDataNasc;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoFone;
    private javax.swing.JTextField campoMunicipio;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoRg;
    private javax.swing.JTextField campoSalario;
    private javax.swing.JComboBox comboBoxUF;
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboCpfCnpj;
    private javax.swing.JComboBox<String> comboFormaPgto;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCaixaPostal;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDDD;
    private javax.swing.JLabel labelDataAdmissao;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelFone;
    private javax.swing.JLabel labelFormaPgto;
    private javax.swing.JLabel labelInscricao;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRazaoSocial;
    private javax.swing.JLabel labelRg;
    private javax.swing.JLabel labelSalario;
    private javax.swing.JLabel labelUF;
    private javax.swing.JLabel labemMunicipio;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mensagemCodHistorico(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mensagemCodEmpregadoVenda(String msg) {
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

    public void mensagemCodEmpregadoConsultVenda(String msg) {
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

    @Override
    public void mensagemCodClienteVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteBusca(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodClienteConsultVenda(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodComissoes(String codComissao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodDescontos(String codDesconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mensagemCodCalculoFolhaPagamento(String codBusca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
