/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.patientController;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableModel;
public class Patients extends JFrame{
    private JTable table;
    private DefaultTableModel tableModel;
    private patientController controller;
    public Patients(){
        setTitle("Patients");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backBtn = new JButton("← Back");
        JButton addBtn = new JButton("+ Add New");
       
        topPanel.add(backBtn, BorderLayout.WEST);
        topPanel.add(addBtn, BorderLayout.EAST);
        
         backBtn.addActionListener(e -> {
            dispose();
            new MainDashboard();
        });

        addBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Add Patient Clicked")
        );

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }
    private void renderTable(){
        String[] columns = { "ID", "First Name", "Last Name", "DOB",
                "NHS No", "Gender", "Phone", "Address",
                "Postcode", "Emergency Name", "Emergency Phone",
                "Registration Date", "GP Surgery ID"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        actionPanel.add(editBtn);
        actionPanel.add(deleteBtn);

        
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);


 
      
        editBtn.addActionListener(e -> editPatient());

        deleteBtn.addActionListener(e -> deletePatient());

        loadData();
    }
     private void loadData() {
         this.controller = new patientController();
         this.controller.readData(this.tableModel);
    }

    private void editPatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit");
            return;
        }
        JOptionPane.showMessageDialog(this, "Edit Patient ID: " +
                tableModel.getValueAt(selectedRow, 0));
    }

    private void deletePatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete");
            return;
        }
        tableModel.removeRow(selectedRow);
    }
}
