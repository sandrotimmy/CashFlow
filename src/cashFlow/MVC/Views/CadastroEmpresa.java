package cashFlow.MVC.Views;

import cashFlow.MVC.Controllers.MetodosGerais;
import cashFlow.MVC.DAO.EmpresasDAO;
import cashFlow.MVC.Models.AlteraMinusculo;
import cashFlow.MVC.Models.DocumentoLimitado;
import cashFlow.MVC.Models.Empresa;
import cashFlow.MVC.Models.IntegerDocument;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CadastroEmpresa extends javax.swing.JDialog {

    private final EmpresasDAO persist;
    private Empresa empresa;
    private MetodosGerais mg;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;

    public CadastroEmpresa() throws SQLException {
        initComponents();
        botaoCadastrar.setFocusTraversalKeysEnabled(false);
        campoCpfCnpj.setFocusTraversalKeysEnabled(false);
        botaoCadastrar.requestFocus();
        this.persist = new EmpresasDAO();
        this.mg = new MetodosGerais();
        carregaMascaraCpfCnpj();
        limitaCampos();
        exibeDados();
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

    private void limitaCampos() {

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

    private void exibeDados() throws SQLException {
        empresa = persist.getCadastroEmpresa();
        if (empresa != null) {
            atribuiCpfCnpj(empresa.getTipoInscricao());
            comboCpfCnpj.setSelectedIndex(empresa.getTipoInscricao());
            campoCpfCnpj.setText(empresa.getCpfCnpj());
            campoRazaoSocial.setText(empresa.getNomeEmpresa());
            campoNomeFantasia.setText(empresa.getNomeFantasia());
            campoEndereco.setText(empresa.getEndereco());
            campoComplemento.setText(empresa.getComplemento());
            campoNumero.setText(empresa.getNumero());
            campoMunicipio.setText(empresa.getMunicipio());
            comboBoxUF.setSelectedItem(empresa.getUf());
            campoCEP.setText(empresa.getCep());
            campoCaixaPostal.setText(empresa.getCaixaPostal());
            campoDDD.setText(empresa.getDdd());
            campoFone.setText(empresa.getFone());
            campoFax.setText(empresa.getCel());
            campoEmail.setText(empresa.getEmail());
            campoCpfCnpj.setEnabled(false);
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
            botaoCadastrar.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(labelComplemento, "Nenhuma empresa cadastrada, Entrando em modo de Inclusão!");
            botaoCadastrarActionPerformed(null);
        }
    }

    public Empresa coletaDadosCampos() {
        Empresa empresa = new Empresa();
        empresa = new Empresa(1,
                comboCpfCnpj.getSelectedIndex(),
                mg.limpaCnpj(campoCpfCnpj.getText()),
                campoRazaoSocial.getText(),
                campoNomeFantasia.getText(),
                campoEndereco.getText(),
                campoComplemento.getText(),
                mg.validaNumeros(campoNumero.getText()),
                campoMunicipio.getText(),
                comboBoxUF.getSelectedItem().toString(),
                mg.validaCep(campoCEP.getText()),
                mg.validaNumeros(campoCaixaPostal.getText()),
                mg.validaNumeros(campoDDD.getText()),
                mg.validaFone(campoFone.getText()),
                mg.validaFone(campoFax.getText()),
                campoEmail.getText()
        );
        return empresa;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFax = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        campoFone = new javax.swing.JFormattedTextField();
        campoFax = new javax.swing.JFormattedTextField();
        campoCEP = new javax.swing.JFormattedTextField();
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
        labelCaixaPostal = new javax.swing.JLabel();
        campoRazaoSocial = new javax.swing.JTextField();
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
        campoNomeFantasia = new javax.swing.JTextField();
        labelNomeFantasia = new javax.swing.JLabel();
        comboCpfCnpj = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        campoCpfCnpj = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Empresa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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

        campoCEP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoCEP.setOpaque(false);
        try{
            javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("##.###-###");
            campoCEP = new javax.swing.JFormattedTextField(cep);
            campoCEP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        }
        catch (Exception e){
        }

        labelEndereco.setText("Endereço:");

        comboBoxUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "LS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        comboBoxUF.setOpaque(false);

        campoEndereco.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNumero.setText("Número:");

        campoNumero.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labemMunicipio.setText("Município:");

        campoMunicipio.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelUF.setText("UF:");

        labelRazaoSocial.setText("Razão Social:");

        labelCEP.setText("CEP:");

        labelCaixaPostal.setText("Caixa Postal:");

        campoRazaoSocial.setDisabledTextColor(new java.awt.Color(0, 0, 0));

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

        campoNomeFantasia.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNomeFantasia.setText("Nome Fantasia:");

        comboCpfCnpj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.P.F.", "C.N.P.J." }));
        comboCpfCnpj.setSelectedIndex(1);
        comboCpfCnpj.setToolTipText("");
        comboCpfCnpj.setEnabled(false);

        jLabel1.setText("Inscrição:");

        campoCpfCnpj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoCpfCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoCpfCnpjFocusGained(evt);
            }
        });
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRazaoSocial)
                            .addComponent(labelEndereco)
                            .addComponent(labemMunicipio)
                            .addComponent(labelDDD)
                            .addComponent(labelNomeFantasia))
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
                                            .addComponent(campoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(campoDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelFone)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoFone, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                            .addComponent(campoCEP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
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
                            .addComponent(campoNomeFantasia)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, botaoCadastrar, botaoCancelar, botaoExcluir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRazaoSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeFantasia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumero)
                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelComplemento)
                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        try {
            if (!botaoCadastrar.getText().equals("Gravar")) {
                atribuiCpfCnpj(1);
                campoCpfCnpj.setEnabled(true);
                campoRazaoSocial.setEnabled(true);
                campoNomeFantasia.setEnabled(true);
                campoEndereco.setEnabled(true);
                campoComplemento.setEnabled(true);
                campoNumero.setEnabled(true);
                campoMunicipio.setEnabled(true);
                comboBoxUF.setEnabled(true);
                campoCEP.setEnabled(true);
                campoCaixaPostal.setEnabled(true);
                campoDDD.setEnabled(true);
                campoFone.setEnabled(true);
                campoFax.setEnabled(true);
                campoEmail.setEnabled(true);
                botaoCadastrar.setEnabled(true);
                botaoAtualizar.setEnabled(false);
                botaoExcluir.setEnabled(false);

                campoCpfCnpj.setText("");
                campoRazaoSocial.setText("");
                campoNomeFantasia.setText("");
                campoEndereco.setText("");
                campoComplemento.setText("");
                campoNumero.setText("");
                campoMunicipio.setText("");
                comboBoxUF.setSelectedIndex(0);
                campoCEP.setText("");
                campoCaixaPostal.setText("");
                campoDDD.setText("");
                campoFone.setText("");
                campoFax.setText("");
                campoEmail.setText("");
                campoCpfCnpj.requestFocus();
                botaoCadastrar.setText("Gravar");
            } else {
                botaoCadastrar.setText("Cadastrar");

                empresa = coletaDadosCampos();

                persist.cadastraEmpresa(empresa);
                comboCpfCnpj.setEnabled(false);
                campoCpfCnpj.setEnabled(false);
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
                botaoCadastrar.setEnabled(false);
                botaoAtualizar.setEnabled(true);
                botaoExcluir.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            botaoCadastrar.setText("Gravar");
            JOptionPane.showMessageDialog(labelComplemento, "Algum Campo não preenchido ou incorreto!");

        }


    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover esta Empresa?", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            persist.removeEmpresa();
            botaoCadastrarActionPerformed(evt);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        if (!botaoAtualizar.getText().equals("Gravar")) {
            comboCpfCnpj.setEnabled(true);
            campoCpfCnpj.setEnabled(true);
            campoRazaoSocial.setEnabled(true);
            campoNomeFantasia.setEnabled(true);
            campoEndereco.setEnabled(true);
            campoComplemento.setEnabled(true);
            campoNumero.setEnabled(true);
            campoMunicipio.setEnabled(true);
            comboBoxUF.setEnabled(true);
            campoCEP.setEnabled(true);
            campoCaixaPostal.setEnabled(true);
            campoDDD.setEnabled(true);
            campoFone.setEnabled(true);
            campoFax.setEnabled(true);
            campoEmail.setEnabled(true);
            campoCpfCnpj.requestFocus();
            botaoAtualizar.setText("Gravar");
            botaoExcluir.setEnabled(false);
        } else {

            Empresa empresa = coletaDadosCampos();

            persist.atualizaEmpresa(empresa);
            comboCpfCnpj.setEnabled(false);
            campoCpfCnpj.setEnabled(false);
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
            botaoAtualizar.setText("Atualizar");
            botaoExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        CadastroEmpresa.this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CadastroEmpresa.this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

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

    private void campoCpfCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoCpfCnpjFocusGained
        campoCpfCnpj.setCaretPosition(0);
    }//GEN-LAST:event_campoCpfCnpjFocusGained

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new CadastroEmpresa().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JFormattedTextField campoCEP;
    private javax.swing.JTextField campoCaixaPostal;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCaixaPostal;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDDD;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelFone;
    private javax.swing.JLabel labelNomeFantasia;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRazaoSocial;
    private javax.swing.JLabel labelUF;
    private javax.swing.JLabel labemMunicipio;
    // End of variables declaration//GEN-END:variables
}
