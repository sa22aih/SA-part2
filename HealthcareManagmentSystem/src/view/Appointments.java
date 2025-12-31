
package view;

import controller.appointmentController;
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
import model.Appointment;

public class Appointments extends JFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private appointmentController controller;
    public Appointments(){
        this.controller = new appointmentController();
        setTitle("Appointments");
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
        AddAppointment dialog=
                new AddAppointment(this, controller);
        dialog.setVisible(true);

 
    loadData();
});

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }
    private void renderTable(){
        String[] columns = { "Apponitment ID", "Patient ID", "Clinician ID", "Facility ID",
                "Appointment Date", "Appointment Time","Duration Min" ,"Appointment Type", "Status",
                "Reason", "Notes", "Creation Date","Last Modified"};
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


 
      
        editBtn.addActionListener(e -> editAppointment());

        deleteBtn.addActionListener(e -> deleteAppointment());

        loadData();
    }
     private void loadData() {
         this.tableModel.setRowCount(0);
       ArrayList<Appointment>  appointments = this.controller.readData();
        for (Appointment a : appointments) {
            tableModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getClinicianId(),
                a.getFacilityId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getDurationMinutes(),
                a.getAppointmentType(),
                a.getStatus(),
                a.getNotes(),
                a.getReasonForVisit(),
                a.getCreatedDate(),
                a.getLastModified()
            });
        }
    }

    private void editAppointment() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to edit");
        return;
    }

    Appointment app = new Appointment(
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
            tableModel.getValueAt(selectedRow, 12).toString()
    );

    EditAppointment dialog =
            new EditAppointment(this, controller, app, selectedRow);
    dialog.setVisible(true);

    loadData();
}

   private void deleteAppointment() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to delete");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this Appointment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        controller.deleteAppointment(selectedRow);
        loadData();
    }
}
}
