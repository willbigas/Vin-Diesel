/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.uteis;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author william.mauro
 */
public class UtilDecimalFormat {

    /**
     * Formatando um Double em Moeda - "R$ 1,110.00"
     *
     * @param numero
     * @return String
     */
    public static String decimalFormatR$(Double numero) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Integer em Moeda - "R$ 1,110.00"
     *
     * @param numero
     * @return String
     */
    public static String decimalFormatR$(Integer numero) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Decimal em Valor decimal Normal - "1,110.00"
     *
     * @param numero
     * @return
     */
    public static String decimalFormat(Double numero) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.applyPattern("###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Inteiro em Valor decimal Normal - "1,110.00"
     *
     * @param numero
     * @return
     */
    public static String decimalFormat(Integer numero) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.applyPattern("###,###,##0.00");
        return df.format(numero);
    }

    public static Double toDecimalUs(String valorDecimal) {
        double d = 0;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(valorDecimal);
            d = number.doubleValue();
        } catch (ParseException parseException) {
            Mensagem.erro("Erro ao converter para NumberFormat com [.]");
        }
        return d;
    }
    
      public static String trocarParaPonto(String valorDecimal) {
         return valorDecimal.replace(",",".");
    }
}
