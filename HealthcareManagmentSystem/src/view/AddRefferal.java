package view;

import controller.refferalcontroller;
import controller.patientController;
import controller.clinicianController;
import controller.appointmentController;
import controller.facilityController;
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
import model.Referral;
import model.Facility;

public class AddRefferal extends JDialog {

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
    private ArrayList<Clinician> clinicianss;
    private ArrayList<Appointment> appointments;
    private ArrayList<Facility> facilities;
    private patientController patientController;
    private clinicianController clinicianController;
    private appointmentController appointmentController;
    private facilityController facilityController;

    public AddRefferal(JFrame parent, refferalcontroller controller) {
        super(parent, "Add Refferall", true); // modal
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
        facilityController = new facilityController();

        patients = patientController.readData();
        clinicianss = clinicianController.readData();
        appointments = appointmentController.readData();
        facilities = facilityController.readData();
        
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtrefferallId = addField(form, "Refferal ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId1 = addClinicComboBox(form, "Clinic Reffering ID");
        txtclinicId2 = addClinicComboBox(form, "Clinic Reffered to ID");
        txtfacilityId1 = addFacilityComboBox(form, "Facility Reffering ID");
        txtfacilityId2 = addFacilityComboBox(form, "Facility Reffered to ID");
        txtprefferaldate = addField(form, "Refferal Date");
        txturgencyLevel = addField(form, "Urgency Level");
        txtrefferalReason = addField(form, "Refferal Reason");
        txtclinicSummary = addField(form, "Clinic Summary");
        txtrequestedInvestigations = addField(form, "Requested Investigations");
        txtstatus = addField(form, "Status");
        txtappointmentId = addAppointmentsComboBox(form, "Appointment ID");
        txtnotes = addField(form, "Notes");
        txtcreatedDate = addField(form, "Creation Date");
        txtlastupdate = addField(form, "Last Update");
        

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> saveRefferalls());
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

    private void saveRefferalls() {

        Referral ap = new Referral(
                txtrefferallId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId(),
                clinicianss.get(this.txtclinicId1.getSelectedIndex()).getId(),
                clinicianss.get(this.txtclinicId2.getSelectedIndex()).getId(),
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

        controller.addRefferal(ap);
        dispose();
    }
}
