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
import javax.swing.*;
import model.Prescription;
import model.Patient;
import model.Clinician;
import model.Appointment;

public class EditPrescription extends JDialog {

   private JTextField txtpresId, txtprescriptiondate,
            txtmedicationName, txtdosage,
            txtfrequency, txtdurationdays, txtquantity, txtinstructions, txtpharmacyname, txtstatus, txtissuedate, txtcollectiondate;
    private JComboBox txtPatientId;
    private JComboBox txtclinicId;
    private JComboBox txtappointmentId;
    private prescriptionController controller;
    private ArrayList<Patient> patients;
    private ArrayList<Clinician> clinicians;
    private ArrayList<Appointment> appointments;
    private patientController patientController;
    private clinicianController clinicianController;
    private appointmentController appointmentController;
    private int rowIndex;

    public EditPrescription(JFrame parent,
            prescriptionController controller,
            Prescription pres,
            int rowIndex) {

        super(parent, "Edit Facility", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(pres);
    }

    private void initForm() {
        this.patientController = new patientController();
        this.clinicianController = new clinicianController();
        this.appointmentController = new appointmentController();
        this.patients = this.patientController.readData();
        this.clinicians = this.clinicianController.readData();
        this.appointments = this.appointmentController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        
        
        txtpresId = addField(form, "Prescription ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId = addClinicianComboBox(form, "Clinic ID");
        txtappointmentId = addAppointmentComboBox(form, "Appointment ID");
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

        txtpresId.setEditable(false);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(cancelBtn);
        btnPanel.add(updateBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());
        updateBtn.addActionListener(e -> updateApppointment());
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

    private JComboBox addClinicianComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Clinician cc : clinicians) {
            field.addItem(cc.getFirstName() + " " + cc.getLastName());

        }
        panel.add(field);
        return field;
    }

    private JComboBox addAppointmentComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Appointment aa : appointments) {
            field.addItem(aa.getAppointmentTime() + " " + aa.getAppointmentDate());

        }
        panel.add(field);
        return field;
    }

    private void fillData(Prescription pres) {
        txtpresId.setText(pres.getAppointmentId());

        for (Patient patient : patients) {

            if (pres.getPatientId().equals(patient.getId())) {
                txtPatientId.setSelectedIndex(patients.indexOf(patient));
            }

        }
        for (Clinician cc : clinicians) {

            if (pres.getClinicianId().equals(cc.getId())) {
                txtclinicId.setSelectedIndex(clinicians.indexOf(cc));
            }

        }

        for (Appointment aa : appointments) {

            if (pres.getAppointmentId().equals(aa.getAppointmentId())) {
                txtappointmentId.setSelectedIndex(appointments.indexOf(aa));
            }

        }

        txtprescriptiondate.setText(pres.getPrescriptionDate());
        txtmedicationName.setText(pres.getMedicationName());
        txtdosage.setText(pres.getDosage());
        txtfrequency.setText(pres.getFrequency());
        txtdurationdays.setText(pres.getDurationDays());
        txtquantity.setText(pres.getQuantity());
        txtinstructions.setText(pres.getInstructions());
        txtpharmacyname.setText(pres.getPharmacyName());
        txtstatus.setText(pres.getStatus());
        txtissuedate.setText(pres.getIssueDate());
        txtcollectiondate.setText(pres.getCollectionDate());
    }

    private void updateApppointment() {

        Prescription pre = new Prescription(
                txtpresId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId(),
                clinicians.get(this.txtclinicId.getSelectedIndex()).getId(),
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

        controller.updatePrescription(rowIndex, pre);
        dispose();
    }
}
