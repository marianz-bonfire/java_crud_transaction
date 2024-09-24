/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.views;

/**
 *
 * @author Tarsier
 */
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class SplitterContainerExample {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame frame = new JFrame("Splitter Container Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JSplitPane splitPane = new JSplitPane();
        //splitPane.setDividerLocation(250); // Set initial divider position


        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode connectionNode = new DefaultMutableTreeNode("Connection");
        root.add(connectionNode);

        JTree tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        JTabbedPane tabbedPane = new JTabbedPane();
        JTable table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tabbedPane.addTab("Tab 1", tableScrollPane);

        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(tabbedPane);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
               DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (selectedNode != null && selectedNode.equals(connectionNode))
 {
                    JTable newTable = new JTable();
                    JScrollPane newTableScrollPane = new JScrollPane(newTable);
                    tabbedPane.addTab("New Tab", newTableScrollPane);
                }
            }
        });

        frame.add(splitPane);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}