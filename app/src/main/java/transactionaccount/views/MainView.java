/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.views;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import javax.swing.tree.TreeSelectionModel;

public class MainView extends JFrame {

    private JTree tree;
    private JTabbedPane tabbedPane;

    public MainView() {
        // Set up the frame
        setTitle("SplitPane with Tree and TabbedPane");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the split pane
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(250); // Set initial divider position

        // Create the left side: TreeView
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode connectionNode = new DefaultMutableTreeNode("Connection");
        root.add(connectionNode);

        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Add listener to tree node selection
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return; // Nothing is selected
                }
                String selectedNodeName = selectedNode.toString();
                // When "Connection" is selected, add a new tab
                if ("Connection".equals(selectedNodeName)) {
                    addNewTab();
                }
            }
        });

        JScrollPane treeScrollPane = new JScrollPane(tree);
        splitPane.setLeftComponent(treeScrollPane); // Add tree to the left side of the split pane

        // Create the right side: TabbedPane
        tabbedPane = new JTabbedPane();
        splitPane.setRightComponent(tabbedPane); // Add tabbed pane to the right side of the split pane

        // Add the split pane to the frame
        getContentPane().add(splitPane, BorderLayout.CENTER);

        // Set the frame visible
        setVisible(true);
    }

    // Method to add a new tab with JTable
    private void addNewTab() {
        // Create a table with sample data
        String[] columnNames = {"ID", "Name", "Value"};
        Object[][] data = {
            {1, "Item 1", "Value 1"},
            {2, "Item 2", "Value 2"},
            {3, "Item 3", "Value 3"},};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Wrap table in a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Add new tab with the table
        String tabTitle = "Connection Tab " + (tabbedPane.getTabCount() + 1);
        tabbedPane.addTab(tabTitle, tableScrollPane);
        tabbedPane.setSelectedComponent(tableScrollPane); // Select the newly added tab
    }
}
