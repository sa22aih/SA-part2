package view;

import controller.staffController;
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
import model.Staff;

public class Staffs extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private staffController controller;

    public Staffs() {
        this.controller = new staffController();
        setTitle("Staffs");
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
            AddStaff dialog
                    = new AddStaff(this, controller);
            dialog.setVisible(true);

            loadData();
        });

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }

    private void renderTable() {
        String[] columns = {"Staff ID", "First Name", "Last Name", "Role",
            "Department", "Facility ID", "Phone Number", "Email", "Status",
            "Start Date", "Line Manager", "Access Level"};
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

        editBtn.addActionListener(e -> editStaff());

        deleteBtn.addActionListener(e -> deleteStaff());

        loadData();
    }

    private void loadData() {
        this.tableModel.setRowCount(0);
        ArrayList<Staff> staffs = this.controller.readData();
        for (Staff a : staffs) {
            tableModel.addRow(new Object[]{
                a.getId(),
                a.getfirstName(),
                a.getlastName(),
                a.getrole(),
                a.getdepartment(),
                
                a.getfacilityId(),
                a.getphoneNumber(),
                a.getemail(),
                a.getemploymentStatus(),
                a.getstartDate(),
                a.getlineManager(),
                a.getaccessLevel()
            });
        }
    }

    private void editStaff() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit");
            return;
        }

        Staff app = new Staff(
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

        EditStaff dialog
                = new EditStaff(this, controller, app, selectedRow);
        dialog.setVisible(true);

        loadData();
    }

    private void deleteStaff() {
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
            controller.deleteStaff(selectedRow);
            loadData();
        }
    }
}
