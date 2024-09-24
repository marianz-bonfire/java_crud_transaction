/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import transactionaccount.controllers.TransactionController;
import transactionaccount.core.helpers.UIHelper;
import transactionaccount.core.utils.Converter;
import transactionaccount.models.Transaction;
import transactionaccount.views.dialogs.TransactionDialog;

/**
 *
 * @author Tarsier
 */
public class TransactionView extends JFrame {

    private TransactionController controller;
    private Transaction latestTransaction;

    private JTable table;
    private DefaultTableModel model;

    public TransactionView() {
        controller = new TransactionController();
        setTitle("Transaction History");
        setLayout(new BorderLayout());

        // Create the MenuBar
        createMenuBar();

        // Create the ToolBar
        createToolBar();

        // Table
        String[] columns = {"Chk #", "Date", "Memo", "Splits", "Debit", "Credit", "Balance", "Created By", "Last Edited By", "Clear"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);

        // Load data
        loadData();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    // Create Menu Bar
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        // Menu Items
        JMenuItem addEntryItem = new JMenuItem("Add Entry");
        JMenuItem printItem = new JMenuItem("Print");
        JMenuItem exportItem = new JMenuItem("Export");
        JMenuItem closeItem = new JMenuItem("Close");

        // Add actions
        addEntryItem.addActionListener(e -> addAction());
        printItem.addActionListener(e -> printAction());
        exportItem.addActionListener(e -> exportAction());
        closeItem.addActionListener(e -> dispose());  // Close the application

        // Add Menu Items to File Menu
        fileMenu.add(addEntryItem);
        fileMenu.addSeparator(); // Adds a line separator
        fileMenu.add(printItem);
        fileMenu.add(exportItem);
        fileMenu.addSeparator();
        fileMenu.add(closeItem);

        // Add File Menu to MenuBar
        menuBar.add(fileMenu);

        // Set the MenuBar for the Frame
        setJMenuBar(menuBar);
    }

    // Create Tool Bar
    private void createToolBar() {
        JToolBar toolBar = new JToolBar();

        // Add Entry Button
        JButton addEntryButton = UIHelper.createToolbarButton(TransactionView.this, "Add Entry", "icons/Document.png");// new JButton("Add Entry");
        addEntryButton.addActionListener(e -> addAction());
        toolBar.add(addEntryButton);

        // Print Button
        JButton printButton = UIHelper.createToolbarButton(TransactionView.this, "Print", "icons/Toolbox.png");// new JBu
        printButton.addActionListener(e -> printAction());
        toolBar.add(printButton);

        // Export Button
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(e -> exportAction());
        toolBar.add(exportButton);

        // Close Button
        JButton closeButton = UIHelper.createToolbarButton(TransactionView.this, "Close", "icons/Cancel.png");// new JBu
        closeButton.addActionListener(e -> dispose());
        toolBar.add(closeButton);

        // Add ToolBar to Frame
        add(toolBar, BorderLayout.NORTH);
    }

    private void addAction() {
        TransactionDialog dialog = new TransactionDialog(TransactionView.this, controller, latestTransaction);
        Boolean result = dialog.showDialog();
        if (result == true) {
            loadData();
        } else {

        }
    }

    // Dummy actions for Print and Export (you can replace these with real implementations)
    private void printAction() {
        JOptionPane.showMessageDialog(this, "Print action triggered.");
    }

    private void exportAction() {
        JOptionPane.showMessageDialog(this, "Export action triggered.");
    }

    private void loadData() {
        model.setRowCount(0); // Clear previous data
        try {
            List<Transaction> transactions = controller.getAllTransactions();
            for (Transaction transaction : transactions) {
                model.addRow(new Object[]{
                    transaction.getChkNumber(),
                    transaction.getDate(),
                    transaction.getMemo(),
                    transaction.getSplits(),
                    Converter.toCurrency(transaction.getDebit()),
                    Converter.toCurrency(transaction.getCredit()),
                    Converter.toCurrency(transaction.getBalance()),
                    transaction.getCreatedBy(),
                    transaction.getLastEditedBy(),
                    transaction.getClearFlag()
                });
            }
            latestTransaction = controller.getLatestTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
