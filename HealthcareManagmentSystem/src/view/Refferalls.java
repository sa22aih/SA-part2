package view;

import controller.refferalcontroller;
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
import model.Referral;

public class Refferalls extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private refferalcontroller controller;

    public Refferalls() {
        this.controller = new refferalcontroller();
        setTitle("Refferalls");
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
            AddRefferal dialog
                    = new AddRefferal(this, controller);
            dialog.setVisible(true);

            loadData();
        });

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }

    private void renderTable() {
        String[] columns = {"Refferral ID", "Patient ID", "Reffering Clinincian ID", "Reffereed Clinician ID",
            "Reffering Facility ID", "Reffered Facility ID", "Reffered Date", "Urgency Level", "Refferal Reasons",
            "Clinicail Summary", "Investigations", "Status", "Appointment ID", "Notes", "Created Date","Last Update"};
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

        editBtn.addActionListener(e -> editRefferalls());

        deleteBtn.addActionListener(e -> deleteRefferalls());

        loadData();
    }

    private void loadData() {
        this.tableModel.setRowCount(0);
        ArrayList<Referral> ref = this.controller.readData();
        for (Referral a : ref) {
            tableModel.addRow(new Object[]{
                a.getReferralId(),
                a.getPatientId(),
                a.getReferringClinicianId(),
                a.getReferredToClinicianId(),
                a.getReferringFacilityId(),
                a.getReferredToFacilityId(),
                a.getReferralDate(),
                a.getUrgencyLevel(),
                a.getReferralReason(),
                a.getClinicalSummary(),
                a.getRequestedInvestigations(),
                a.getStatus(),
                a.getAppointmentId(),
                a.getNotes(),
                a.getCreatedDate(),
                a.getLastUpdated()
            });
        }
    }

    private void editRefferalls() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit");
            return;
        }

        Referral app = new Referral(
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
                tableModel.getValueAt(selectedRow, 13).toString(),
                tableModel.getValueAt(selectedRow, 14).toString(),
                 tableModel.getValueAt(selectedRow, 15).toString()
        );

        EditRefferals dialog
                = new EditRefferals(this, controller, app, selectedRow);
        dialog.setVisible(true);

        loadData();
    }

    private void deleteRefferalls() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteRefferal(selectedRow);
            loadData();
        }
    }
}
