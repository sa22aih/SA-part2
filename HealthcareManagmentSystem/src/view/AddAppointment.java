/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.Facility;

public class AddAppointment extends JDialog{
       private JTextField txtId,  txtappointmentdate,
            txtappointmenttime, txtdurationMin,
            txtappointmentType, txtstatus,txtnotes, txtreason, txtcreated, txtmodified;
       private JComboBox txtPatientId;
       private JComboBox txtclinicId;
       private JComboBox txtfacilityId;
    private appointmentController controller;
    private ArrayList<Patient> patients;
     private ArrayList<Clinician> clinicianss;
     private ArrayList<Facility> facilitieess;
    private patientController patientController;
     private clinicianController clinicianController;
     private facilityController facilityController;

    public AddAppointment(JFrame parent, appointmentController controller) {
        super(parent, "Add Appointment", true); // modal
        this.controller = controller;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        
    }

    private void initForm() {
         patientController = new patientController();         
         clinicianController = new clinicianController();
         facilityController = new facilityController();

         patients = patientController.readData();
         clinicianss = clinicianController.readData();
         facilitieess = facilityController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtPatientId = addPatientComboBox(form, "Patient ID");
        txtclinicId = addClinicComboBox(form, "Clinic ID");
        txtfacilityId = addFacilityComboBox(form, "Facility ID");
        txtappointmentdate = addField(form, "Appointment Date");
        txtappointmenttime = addField(form, "Time");
        txtdurationMin = addField(form, "Duration MIN");
        txtappointmentType = addField(form, "TYPE");
        txtstatus = addField(form, "Status");
        txtreason = addField(form, "Reason");
        txtnotes = addField(form, "Notes");
        txtcreated = addField(form, "Creation Time");
        txtmodified = addField(form, "Last Modified");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> saveAppointment());
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
       
       

        for (Facility ff : facilitieess) {
            field.addItem(ff.getFacility_name());
            
        }
        panel.add(field);
        return field;
    }

    private void saveAppointment() {

        Appointment ap = new Appointment(
                txtId.getText(),
                patients.get(this.txtPatientId.getSelectedIndex()).getId() ,
                clinicianss.get(this.txtclinicId.getSelectedIndex()).getId(),
                facilitieess.get(this.txtfacilityId.getSelectedIndex()).getFacility_id(),
                txtappointmentdate.getText(),
                txtappointmenttime.getText(),
                txtdurationMin.getText(),
                txtappointmentType.getText(),
                txtstatus.getText(),
                txtreason.getText(),
                txtnotes.getText(),
                txtcreated.getText(),
                txtmodified.getText()
        );

        controller.addAppointment(ap);
        dispose();
    }
}
