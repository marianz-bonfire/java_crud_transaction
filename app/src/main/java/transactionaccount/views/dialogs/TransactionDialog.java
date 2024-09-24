/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.views.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import transactionaccount.controllers.TransactionController;
import transactionaccount.core.constants.MockUser;
import transactionaccount.core.utils.Converter;
import transactionaccount.core.utils.DoubleUtils;
import transactionaccount.models.Transaction;

/**
 *
 * @author Tarsier
 */
public class TransactionDialog extends JDialog {

    private JTextField chkNumberField, memoField, splitsField, debitField, creditField, balanceField;
    private JComboBox<String> createdByField, lastEditedByField;
    private JCheckBox clearFlagField;
    private JButton saveButton;
    private TransactionController controller;
    private Transaction latestTransaction;
    private Boolean success = false;
    private Double previousBalance;

    public TransactionDialog(JFrame parent, TransactionController controller, Transaction latestTransaction) {
        super(parent, "Add Transaction", true);
        this.controller = controller;
        this.previousBalance = latestTransaction.getBalance();
                
        setLayout(new GridBagLayout());  // Use GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding around components
        gbc.anchor = GridBagConstraints.WEST;  // Align labels and fields to the left

        // Fields initialization
        chkNumberField = new JTextField(15);
        memoField = new JTextField(15);
        splitsField = new JTextField(15);
        debitField = new JTextField(15);
        creditField = new JTextField(15);
        balanceField = new JTextField(15);
        balanceField.setText(String.valueOf(this.previousBalance));  // Update balance field
        balanceField.setEditable(false);  // Make balance field non-editable (calculated automatically)

        createdByField = new JComboBox<>(MockUser.list);
        lastEditedByField = new JComboBox<>(MockUser.list);
        clearFlagField = new JCheckBox();

        // Adding components to the dialog in a grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Chk #"), gbc);
        gbc.gridx = 1;
        add(chkNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Memo"), gbc);
        gbc.gridx = 1;
        add(memoField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Splits"), gbc);
        gbc.gridx = 1;
        add(splitsField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Debit"), gbc);
        gbc.gridx = 1;
        add(debitField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Credit"), gbc);
        gbc.gridx = 1;
        add(creditField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Balance"), gbc);
        gbc.gridx = 1;
        add(balanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Created By"), gbc);
        gbc.gridx = 1;
        add(createdByField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Last Edited By"), gbc);
        gbc.gridx = 1;
        add(lastEditedByField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Clear"), gbc);
        gbc.gridx = 1;
        add(clearFlagField, gbc);

        // Save Button (span two columns)
        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        add(saveButton, gbc);

        // Add listeners to the debit and credit fields
        debitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calculateBalance();
            }
        });

        creditField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calculateBalance();
            }
        });
        // Add Save action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Transaction transaction = new Transaction();
                    transaction.setChkNumber(chkNumberField.getText());
                    transaction.setMemo(memoField.getText());
                    transaction.setSplits(splitsField.getText());
                    transaction.setDebit(DoubleUtils.toSafeDouble(debitField.getText()));
                    transaction.setCredit(DoubleUtils.toSafeDouble(creditField.getText()));
                    transaction.setBalance(DoubleUtils.toSafeDouble(balanceField.getText()));
                    transaction.setCreatedBy((String) createdByField.getSelectedItem());
                    transaction.setLastEditedBy((String) lastEditedByField.getSelectedItem());
                    transaction.setClearFlag(clearFlagField.isSelected() ? '1' : '0');
                    transaction.setDate(new Date());

                    controller.addTransaction(transaction);
                    success = true;
                    dispose();
                } catch (Exception ex) {
                    success = false;
                    ex.printStackTrace();
                }
            }
        });

        setSize(400, 400);
        setLocationRelativeTo(parent);
    }

    public Boolean showDialog() {
        setVisible(true);
        return success;
    }
    
    // Method to calculate balance
    private void calculateBalance() {
        double debit = 0;
        double credit = 0;

        // Try to parse debit and credit inputs, handle empty fields
        try {
            debit = DoubleUtils.toSafeDouble(debitField.getText());
        } catch (NumberFormatException e) {
            debit = 0; // If input is invalid or empty, assume 0
        }

        try {
            credit = DoubleUtils.toSafeDouble(creditField.getText());
        } catch (NumberFormatException e) {
            credit = 0; // If input is invalid or empty, assume 0
        }

        // Balance calculation: previous balance + credit - debit
        double calculatedBalance = previousBalance + credit - debit;
        balanceField.setText(Converter.toCurrency(calculatedBalance));  // Update balance field
    }
}
