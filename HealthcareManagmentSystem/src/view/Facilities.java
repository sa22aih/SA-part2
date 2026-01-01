package view;

import controller.facilityController;
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
import model.Facility;

public class Facilities extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private facilityController controller;

    public Facilities() {
        this.controller = new facilityController();
        setTitle("Facilities");
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
            AddFacility dialog
                    = new AddFacility(this, controller);
            dialog.setVisible(true);

            loadData();
        });

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }

    private void renderTable() {
        String[] columns = {"Facility ID", "Facility Name", "Facility Type", "Address",
            "Post Code", "Phone Number", "Email", "Opening Hours",
            "Manager Name", "Capicity", "Specialities Offered"};
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

        editBtn.addActionListener(e -> editFacility());

        deleteBtn.addActionListener(e -> deleteFacility());

        loadData();
    }

    private void loadData() {
        this.tableModel.setRowCount(0);
        ArrayList<Facility> facilities = this.controller.readData();
        for (Facility f : facilities) {
            this.tableModel.addRow(new Object[]{
                f.getFacility_id(),
                f.getFacility_name(),
                f.getFacility_type(),
                f.getAddress(),
                f.getPostcode(),
                f.getPhone_number(),
                f.getEmail(),
                f.getOpening_hours(),
                f.getManager_name(),
                f.getCapacity(),
                f.getSpecialities_offered()
            });
        }
    }

    private void editFacility() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit");
            return;
        }

        Facility facility = new Facility(
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
                tableModel.getValueAt(selectedRow, 10).toString()
        );

        EditFacility dialog
                = new EditFacility(this, controller, facility, selectedRow);
        dialog.setVisible(true);

        loadData();
    }

    private void deleteFacility() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this Facility?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteFacility(selectedRow);
            loadData();
        }
    }
}
