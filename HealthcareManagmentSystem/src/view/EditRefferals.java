package view;

import controller.refferalcontroller;
import controller.patientController;
import controller.clinicianController;
import controller.facilityController;
import controller.appointmentController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.Referral;
import model.Patient;
import model.Clinician;
import model.Facility;
import model.Appointment;

public class EditRefferals extends JDialog {

    private JTextField txtrefferallId, txtprefferaldate,
            txturgencyLevel, txtrefferalReason,
            txtclinicSummary, txtrequestedInvestigations, txtstatus, txtnotes, txtcreatedDate, txtlastupdate;
    private JComboBox txtPatientId;
    private JComboBox txtclinicId1;
    private JComboBox txtclinicId2;
    private JComboBox txtfacilityId1;
    private JComboBox txtfacilityId2;
    private JComboBox txtappointmentId;
    private refferalcontroller controller;
    private ArrayList<Patient> patients;
    private ArrayList<Clinician> clinicians;
    private ArrayList<Appointment> appointments;
     private ArrayList<Facility> facilities;
    private patientController patientController;
    private clinicianController clinicianController;
     private facilityController facilityController;
    private appointmentController appointmentController;
    private int rowIndex;

    public EditRefferals(JFrame parent,
            refferalcontroller controller,
            Referral refreee,
            int rowIndex) {

        super(parent, "Edit Refferalls", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(refreee);
    }

    private void initForm() {
        this.patientController = new patientController();
        this.clinicianController = new clinicianController();
        this.appointmentController = new appointmentController();
        this.facilityController = new facilityController();
        this.patients = this.patientController.readData();
        this.clinicians = this.clinicianController.readData();
        this.appointments = this.appointmentController.readData();
        this.facilities = this.facilityController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtrefferallId = addField(form, "Refferal ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId1 = addClinicianComboBox(form, "Clinic Reffering ID");
        txtclinicId2 = addClinicianComboBox(form, "Clinic Reffered to ID");
        txtfacilityId1 = addFacilityComboBox(form, "Facility Reffering ID");
        txtfacilityId2 = addFacilityComboBox(form, "Facility Reffered to ID");
        txtprefferaldate = addField(form, "Refferal Date");
        txturgencyLevel = addField(form, "Urgency Level");
        txtrefferalReason = addField(form, "Refferal Reason");
        txtclinicSummary = addField(form, "Clinic Summary");
        txtrequestedInvestigations = addField(form, "Requested Investigations");
        txtstatus = addField(form, "Status");
        txtappointmentId = addAppointmentComboBox(form, "Appointment ID");
        txtnotes = addField(form, "Notes");
        txtcreatedDate = addField(form, "Creation Date");
        txtlastupdate = addField(form, "Last Update");

        txtrefferallId.setEditable(false);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(cancelBtn);
        btnPanel.add(updateBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());
        updateBtn.addActionListener(e -> updateRefferals());
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

    private void fillData(Referral refree) {
        txtrefferallId.setText(refree.getReferralId());

        for (Patient patient : patients) {

            if (refree.getPatientId().equals(patient.getId())) {
                txtPatientId.setSelectedIndex(patients.indexOf(patient));
            }

        }
        for (Clinician cc : clinicians) {

            if (refree.getReferringClinicianId().equals(cc.getId())) {
                txtclinicId1.setSelectedIndex(clinicians.indexOf(cc));
            }
            if (refree.getReferredToClinicianId().equals(cc.getId())) {
                txtclinicId2.setSelectedIndex(clinicians.indexOf(cc));
            }

        }
        
        for (Facility ff : facilities) {

            if (refree.getReferringFacilityId().equals(ff.getFacility_id())) {
                txtfacilityId1.setSelectedIndex(facilities.indexOf(ff));
            }
            if (refree.getReferredToFacilityId().equals(ff.getFacility_id())) {
                txtfacilityId2.setSelectedIndex(facilities.indexOf(ff));
            }

        }
        

        for (Appointment aa : appointments) {

            if (refree.getAppointmentId().equals(aa.getAppointmentId())) {
                txtappointmentId.setSelectedIndex(appointments.indexOf(aa));
            }

        }

        txtprefferaldate.setText(refree.getReferralDate());
        txturgencyLevel.setText(refree.getUrgencyLevel());
        txtrefferalReason.setText(refree.getReferralReason());
        txtclinicSummary.setText(refree.getClinicalSummary());
        txtrequestedInvestigations.setText(refree.getRequestedInvestigations());
        txtstatus.setText(refree.getStatus());
        txtnotes.setText(refree.getNotes());
        txtcreatedDate.setText(refree.getCreatedDate());
        txtlastupdate.setText(refree.getLastUpdated());
    }

    private void updateRefferals() {

       Referral ap = new Referral(
                txtrefferallId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId(),
                clinicians.get(this.txtclinicId1.getSelectedIndex()).getId(),
                clinicians.get(this.txtclinicId2.getSelectedIndex()).getId(),
                facilities.get(this.txtfacilityId1.getSelectedIndex()).getFacility_id(),
                facilities.get(this.txtfacilityId2.getSelectedIndex()).getFacility_id(),
                txtprefferaldate.getText(),
                txturgencyLevel.getText(),
                txtrefferalReason.getText(),
                txtclinicSummary.getText(),
                txtrequestedInvestigations.getText(),
                txtstatus.getText(),
                appointments.get(this.txtappointmentId.getSelectedIndex()).getAppointmentId(),
                txtnotes.getText(),
                txtcreatedDate.getText(),
                txtlastupdate.getText()
        );

        controller.updateRefferal(rowIndex, ap);
        dispose();
    }
}
