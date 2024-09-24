/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.core.helpers;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import transactionaccount.core.utils.DoubleUtils;

/**
 *
 * @author Tarsier
 */
public class UIHelper {
    // Helper method to create a button with an icon and text

    public static JButton createToolbarButton(JFrame parent, String text, String iconPath) {
        JButton button = new JButton(text);
        ClassLoader loader = parent.getClass().getClassLoader();
        URL iconURL = loader.getResource(iconPath);  // Load icon from resource folder
        
        System.out.println(iconURL);
        if (iconURL != null) {
            button.setIcon(new ImageIcon(iconURL));
        } else {
            System.out.println("Icon not found: " + iconPath);  // In case icon is missing
        }
        return button;
    }
}
