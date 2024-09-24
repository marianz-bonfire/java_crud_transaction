/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.core.utils;

/**
 *
 * @author Tarsier
 */
public class StringUtils {
    public static String removeNonNumeric(String value) {
        return value.replaceAll("[^0-9]", "");
    }
    
}
