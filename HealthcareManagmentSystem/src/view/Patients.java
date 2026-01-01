
package view;

import controller.patientController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import model.Patient;
import javax.swing.table.DefaultTableModel;

public class Patients extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private patientController controller;

    public Patients() {
        this.controller = new patientController();
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

        addBtn.addActionListener(e -> {
            AddPatientDialog dialog
                    = new AddPatientDialog(this, controller);
            dialog.setVisible(true);

            loadData();
        });

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }

    private void renderTable() {
        String[] columns = {"ID", "First Name", "Last Name", "DOB",
            "NHS No", "Gender", "Phone", "Email", "Address",
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
        this.tableModel.setRowCount(0);
        ArrayList<Patient> patients = this.controller.readData();
        for (Patient p : patients) {
            tableModel.addRow(new Object[]{
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDate_of_birth(),
                p.getNhs_number(),
                p.getGender(),
                p.getPhone(),
                p.getEmail(),
                p.getAddress(),
                p.getPostcode(),
                p.getEmergency_contact_name(),
                p.getEmergency_contact_phone(),
                p.getRegistration_date(),
                p.getGp_surgery_id()
            });
        }
    }

    private void editPatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit");
            return;
        }

        Patient patient = new Patient(
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
                tableModel.getValueAt(selectedRow, 11).toString(),
                tableModel.getValueAt(selectedRow, 12).toString(),
                tableModel.getValueAt(selectedRow, 13).toString()
        );

        EditPatientDialog dialog
                = new EditPatientDialog(this, controller, patient, selectedRow);
        dialog.setVisible(true);

        loadData();
    }

    private void deletePatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this patient?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deletePatient(selectedRow);
            loadData();
        }
    }

}
