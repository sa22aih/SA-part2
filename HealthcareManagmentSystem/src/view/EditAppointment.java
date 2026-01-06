package view;

import controller.appointmentController;
import controller.patientController;
import controller.clinicianController;
import controller.facilityController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.Appointment;
import model.Patient;
import model.Clinician;
import model.Facility;

public class EditAppointment extends JDialog {

    private JTextField txtId, txtappointmentdate,
            txtappointmenttime, txtdurationMin,
            txtappointmentType, txtstatus, txtnotes, txtreason, txtcreated, txtmodified;
    private JComboBox txtPatientId;
    private JComboBox txtclinicId;
    private JComboBox txtfacilityId;
    private appointmentController controller;
    private ArrayList<Patient> patients;
    private ArrayList<Clinician> clinicians;
    private ArrayList<Facility> facilities;
    private patientController patientController;
    private clinicianController clinicianController;
    private facilityController facilityController;
    private int rowIndex;

    public EditAppointment(JFrame parent,
            appointmentController controller,
            Appointment appointment,
            int rowIndex) {

        super(parent, "Edit Appointment", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(appointment);
    }

    private void initForm() {
        this.patientController = new patientController();
        this.clinicianController = new clinicianController();
        this.facilityController = new facilityController();
        this.patients = this.patientController.readData();
        this.clinicians = this.clinicianController.readData();
        this.facilities = this.facilityController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId = addClinicianComboBox(form, "Clinic ID");
        txtfacilityId = addFacilityComboBox(form, "Facility ID");
        txtappointmentdate = addField(form, "Appointment Date");
        txtappointmenttime = addField(form, "Appointment Time");
        txtdurationMin = addField(form, "Duration Min");
        txtappointmentType = addField(form, "Type");
        txtstatus = addField(form, "Status");
        txtnotes = addField(form, "Notes");
        txtreason = addField(form, "Reason");
        txtcreated = addField(form, "Created");
        txtmodified = addField(form, "Modified");

        txtId.setEditable(false);

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

    private JComboBox addFacilityComboBox(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JComboBox field = new JComboBox();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Facility ff : facilities) {
            field.addItem(ff.getFacility_name());

        }
        panel.add(field);
        return field;
    }

    private void fillData(Appointment f) {
        txtId.setText(f.getAppointmentId());

        for (Patient patient : patients) {

            if (f.getPatientId().equals(patient.getId())) {
                txtPatientId.setSelectedIndex(patients.indexOf(patient));
            }

        }
        for (Clinician cc : clinicians) {

            if (f.getClinicianId().equals(cc.getId())) {
                txtclinicId.setSelectedIndex(clinicians.indexOf(cc));
            }

        }

        for (Facility ff : facilities) {

            if (f.getFacilityId().equals(ff.getFacility_id())) {
                txtfacilityId.setSelectedIndex(facilities.indexOf(ff));
            }

        }

        txtappointmentdate.setText(f.getAppointmentDate());
        txtappointmenttime.setText(f.getAppointmentTime());
        txtdurationMin.setText(f.getDurationMinutes());
        txtappointmentType.setText(f.getAppointmentType());
        txtstatus.setText(f.getStatus());
        txtnotes.setText(f.getReasonForVisit());
        txtreason.setText(f.getNotes());
        txtcreated.setText(f.getCreatedDate());
        txtmodified.setText(f.getLastModified());
    }

    private void updateApppointment() {

        Appointment a = new Appointment(
                txtId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId(),
                clinicians.get(this.txtclinicId.getSelectedIndex()).getId(),
                facilities.get(this.txtfacilityId.getSelectedIndex()).getFacility_id(),
                txtappointmentdate.getText(),
                txtappointmenttime.getText(),
                txtdurationMin.getText(),
                txtappointmentType.getText(),
                txtstatus.getText(),
                txtnotes.getText(),
                txtreason.getText(),
                txtcreated.getText(),
                txtmodified.getText()
        );

        controller.updateAppointment(rowIndex, a);
        dispose();
    }
}
