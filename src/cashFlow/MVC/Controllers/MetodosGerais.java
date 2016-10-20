/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ProdutosDAO;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String convDataSistema(java.util.Date dataBanco) {
        String dataSistema;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dataSistema = format.format(dataBanco);
        return dataSistema;

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

}
