/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.clinicianController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Clinician;


public class Clinicians extends JFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private clinicianController controller;
    public Clinicians(){
        this.controller = new clinicianController();
        setTitle("Clinicians");
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

        addBtn.addActionListener(e -> {
        AddNewClinician dialog=
                new AddNewClinician(this, controller);
        dialog.setVisible(true);

 
    loadData();
});

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }
    private void renderTable(){
        String[] columns = { "Clinician ID", "First Name", "Last Name", "Title",
                "Speciality", "GMC Number", "Phone Number", "Email", "Workplace ID",
                "Workplace Type", "Employment Status", "Start Date"};
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


 
      
        editBtn.addActionListener(e -> editClinicians());

        deleteBtn.addActionListener(e -> deleteClinicians());

        loadData();
    }
     private void loadData() {
         this.tableModel.setRowCount(0);
        ArrayList<Clinician> clinicians =  this.controller.readData();
        for (Clinician c : clinicians) {
            tableModel.addRow(new Object[]{
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getTitle(),
                c.getSpeciality(),
                c.getGmc(),
                c.getPhone(),
                c.getEmail(),
                c.getWorkPlaceId(),
                c.getWorkPlaceType(),
                c.getEmploymentStatus(),
                c.getStartDate()
            });
        }
    }

    private void editClinicians() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to edit");
        return;
    }

    Clinician clinician = new Clinician(
            tableModel.getValueAt(selectedRow, 0).toString(),
            tableModel.getValueAt(selectedRow, 1).toString(),
            tableModel.getValueAt(selectedRow, 2).toString(),
            tableModel.getValueAt(selectedRow, 3).toString(),
            tableModel.getValueAt(selectedRow, 4).toString(),
            tableModel.getValueAt(selectedRow, 5).toString(),
            tableModel.getValueAt(selectedRow, 6).toString(),
            tableModel.getValueAt(selectedRow, 7).toString(),
            tableModel.getValueAt(selectedRow, 8).toString(),
            tableModel.getValueAt(selectedRow, 9).toString(),
            tableModel.getValueAt(selectedRow, 10).toString(),
            tableModel.getValueAt(selectedRow, 11).toString()
    );

    EditClinician dialog =
            new EditClinician(this, controller, clinician, selectedRow);
    dialog.setVisible(true);

    loadData();
}

   private void deleteClinicians() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to delete");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this Clinians?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        controller.deleteClinician(selectedRow);
        loadData();
    }
}
}
