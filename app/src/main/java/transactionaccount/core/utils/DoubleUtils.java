/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.core.utils;

/**
 *
 * @author Tarsier
 */
public class DoubleUtils {
    public static Double toSafeDouble(Object value) {
        return DoubleUtils.toSafeDouble(value.toString());
    }
    
    public static Double toSafeDouble(String value) {
        value = StringUtils.removeNonNumeric(value);
        if(value.trim().isEmpty()) {
            value = "0.00";
        }
        return Double.valueOf(value);
    }
}
