
package view;

import controller.prescriptionController;
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
import model.Prescription;

public class Prescriptions extends JFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private prescriptionController controller;
    public Prescriptions(){
        this.controller = new prescriptionController();
        setTitle("Prescriptions");
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
        AddPrescription dialog=
                new AddPrescription(this, controller);
        dialog.setVisible(true);

 
    loadData();
});

        add(topPanel, BorderLayout.NORTH);
        renderTable();
        setVisible(true);
    }
    private void renderTable(){
        String[] columns = { "Prescription ID", "Patient ID", "Clinician ID", "Appointment ID",
                "Prescription Date", "Medication Name","Dosage" ,"Freqeuncy", "Duration Days",
                "Quantity", "Instructions", "Pharmacy Name","Status","Issue Date","Collection Date"};
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


 
      
        editBtn.addActionListener(e -> editPrescription());

        deleteBtn.addActionListener(e -> deletePrescription());

        loadData();
    }
     private void loadData() {
         this.tableModel.setRowCount(0);
       ArrayList<Prescription>  presc = this.controller.readData();
        for (Prescription a : presc) {
            tableModel.addRow(new Object[]{
                a.getPrescriptionId(),
                a.getPatientId(),
                a.getClinicianId(),
                a.getAppointmentId(),
                a.getPrescriptionDate(),
                a.getMedicationName(),
                a.getDosage(),
                a.getFrequency(),
                a.getDurationDays(),
                a.getQuantity(),
                a.getInstructions(),
                a.getPharmacyName(),
                a.getStatus(),
                a.getIssueDate(),
                a.getCollectionDate()
            });
        }
    }

    private void editPrescription() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to edit");
        return;
    }

    Prescription app = new Prescription(
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
             tableModel.getValueAt(selectedRow, 11).toString(),
            tableModel.getValueAt(selectedRow, 12).toString()
    );

    EditPrescription dialog =
            new EditPrescription(this, controller, app, selectedRow);
    dialog.setVisible(true);

    loadData();
}

   private void deletePrescription() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to delete");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this Prescription?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        controller.deletePrescription(selectedRow);
        loadData();
    }
}
}
