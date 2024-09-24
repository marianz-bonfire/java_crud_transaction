/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.core.utils;

import java.text.DecimalFormat;

/**
 *
 * @author Tarsier
 */
public class Converter {
    public static String toCurrency(Double value) {
       DecimalFormat format = new DecimalFormat("#,###.00");
       return format.format(value);
    }
}
