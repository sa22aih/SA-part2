/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.prescriptionController;
import controller.patientController;
import controller.clinicianController;
import controller.appointmentController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Appointment;
import model.Patient;
import model.Clinician;
import model.Prescription;

public class AddPrescription extends JDialog {

    private JTextField txtpresId, txtprescriptiondate,
            txtmedicationName, txtdosage,
            txtfrequency, txtdurationdays, txtquantity, txtinstructions, txtpharmacyname, txtstatus, txtissuedate, txtcollectiondate;
    private JComboBox txtPatientId;
    private JComboBox txtclinicId;
    private JComboBox txtappointmentId;
    private prescriptionController controller;
    private ArrayList<Patient> patients;
    private ArrayList<Clinician> clinicianss;
    private ArrayList<Appointment> appointments;
    private patientController patientController;
    private clinicianController clinicianController;
    private appointmentController appointmentController;

    public AddPrescription(JFrame parent, prescriptionController controller) {
        super(parent, "Add Prescription", true); // modal
        this.controller = controller;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();

    }

    private void initForm() {
        patientController = new patientController();
        clinicianController = new clinicianController();
        appointmentController = new appointmentController();

        patients = patientController.readData();
        clinicianss = clinicianController.readData();
        appointments = appointmentController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtpresId = addField(form, "Prescription ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId = addClinicComboBox(form, "Clinic ID");
        txtappointmentId = addAppointmentsComboBox(form, "Appointment ID");
        txtprescriptiondate = addField(form, "Prescription Date");
        txtmedicationName = addField(form, "Medication Name");
        txtdosage = addField(form, "Dosage");
        txtfrequency = addField(form, "Frequency");
        txtdurationdays = addField(form, "Days");
        txtquantity = addField(form, "Quantity");
        txtinstructions = addField(form, "Instructions");
        txtpharmacyname = addField(form, "Pharmacy Name");
        txtstatus = addField(form, "Status");
        txtissuedate = addField(form, "Issue Date");
        txtcollectiondate = addField(form, "Collection Date");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> savePrescriptions());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private JComboBox addPatientComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Patient patient : patients) {
            field.addItem(patient.getFirstName() + " " + patient.getLastName());

        }
        panel.add(field);
        return field;
    }

    private JComboBox addClinicComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Clinician cc : clinicianss) {
            field.addItem(cc.getFirstName() + " " + cc.getLastName());

        }
        panel.add(field);
        return field;
    }

    private JComboBox addAppointmentsComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Appointment aa : appointments) {
            field.addItem(aa.getAppointmentTime() + " " + aa.getAppointmentDate());

        }
        panel.add(field);
        return field;
    }

    private void savePrescriptions() {

        Prescription ap = new Prescription(
                txtpresId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId(),
                clinicianss.get(this.txtclinicId.getSelectedIndex()).getId(),
                appointments.get(this.txtappointmentId.getSelectedIndex()).getAppointmentId(),
                txtprescriptiondate.getText(),
                txtmedicationName.getText(),
                txtdosage.getText(),
                txtfrequency.getText(),
                txtdurationdays.getText(),
                txtquantity.getText(),
                txtinstructions.getText(),
                txtpharmacyname.getText(),
                txtstatus.getText(),
                txtissuedate.getText(),
                txtcollectiondate.getText()
        );

        controller.addPrescription(ap);
        dispose();
    }
}
