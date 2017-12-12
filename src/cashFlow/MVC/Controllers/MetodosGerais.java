/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ProdutosDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author Sandro Machado
 */
public class MetodosGerais {

    public SimpleDateFormat getFormatoData() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    public java.sql.Date convDataBanco(String dataSistema) {
        //Conversão da data do sistema para formato da data do Banco
        java.sql.Date dataBanco = new java.sql.Date(1);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataBanco = new java.sql.Date(format.parse(dataSistema).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBanco;
    }

    public java.sql.Date convCompetenciaBanco(String dataSistema) {
        //Conversão da data do sistema para formato da data do Banco
        java.sql.Date dataBanco = new java.sql.Date(1);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataBanco = new java.sql.Date(format.parse("01/" + dataSistema).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBanco;
    }

    public String convCompetenciaSistema(java.util.Date dataBanco) {
        String dataSistema;
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        dataSistema = format.format(dataBanco);
        return dataSistema;

    }

    public String convDataSistema(java.util.Date dataBanco) {
        String dataSistema;
        if (dataBanco != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataSistema = format.format(dataBanco);
            return dataSistema;
        } else {
            return "";
        }
    }

    //Pega a data do Computador
    public String getDataAtual() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.format(formatter);
    }

    public String getDataInicial() {
        LocalDate dataAtual = LocalDate.now();
        dataAtual.withDayOfMonth(01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.withDayOfMonth(1).format(formatter);
    }

    public String getDataFinal() {
        LocalDate dataAtual = LocalDate.now();
        dataAtual.withDayOfMonth(01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        dataAtual.format(formatter);
        return dataAtual.withDayOfMonth(dataAtual.lengthOfMonth()).format(formatter);
    }

    public BigDecimal convValorBanco(String valorSistema) {
        return new BigDecimal(valorSistema.replaceAll("\\.", "").replaceAll(",", "."));
    }

    public BigDecimal convValorTabela(String valorSistema) {
        String valor = valorSistema.replaceAll("\\.", "").replaceAll(",", ".").replaceAll("R", "").replaceAll("\\$", "").trim();
        return new BigDecimal(valor);
    }

    public boolean validaCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() > 12) {
            return isCNPJ(cpfCnpj);
        } else {
            return isCPF(cpfCnpj);
        }
    }

    public boolean isCNPJ(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isCPF(String CPF) {
// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0         
// (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

// Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public String validaFone(String fone) {
        fone = fone.replaceAll("-", "").replaceAll(" ", "");
        if (fone.equals("")) {
            return "";
        } else {
            return fone;
        }
    }

    public String validaCep(String cep) {
        cep = cep.replaceAll("\\.", "").replaceAll("-", "").replaceAll(" ", "");
        if (cep.equals("")) {
            return "";
        } else {
            return cep;
        }
    }

    public String limpaCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "").replaceAll(" ", "");
        if (cnpj.equals("")) {
            return "";
        } else {
            return cnpj;
        }
    }

    public String validaNumeros(String numero) {
        if (numero.equals("")) {
            return "";
        } else {
            return numero;
        }
    }

    //altera a cor da Barra de Menus
    public void customizeMenuBar(JMenuBar menuBar) {

        menuBar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new Color(32,75,151));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        MenuElement[] menus = menuBar.getSubElements();

        for (MenuElement menuElement : menus) {

            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu);
            menu.setOpaque(true);

//            MenuElement[] menuElements = menu.getSubElements();
//
////            for (MenuElement popupMenuElement : menuElements) {
////
////                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
////                popupMenu.setBorder(null);
////
////                MenuElement[] menuItens = popupMenuElement.getSubElements();
////
////                for (MenuElement menuItemElement : menuItens) {
////
////                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
////                    changeComponentColors(menuItem);
////                    menuItem.setOpaque(true);
////
////                }
////            }
        }
    }

    public void changeComponentColors(Component comp) {
        comp.setBackground(new Color(32,75,151));
        comp.setForeground(Color.white);
    }
}
