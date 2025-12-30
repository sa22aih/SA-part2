/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.appointmentController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Appointment;

public class AddAppointment extends JDialog{
       private JTextField txtId, txtPatientId, txtclinicId, txtfacilityId, txtappointmentdate,
            txtappointmenttime, txtdurationMin,
            txtappointmentType, txtstatus,txtnotes, txtreason, txtcreated, txtmodified;

    private appointmentController controller;

    public AddAppointment(JFrame parent, appointmentController controller) {
        super(parent, "Add Appointment", true); // modal
        this.controller = controller;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtPatientId = addField(form, "Patient ID");
        txtclinicId = addField(form, "Clinic ID");
        txtfacilityId = addField(form, "Facility ID");
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

    private void saveAppointment() {

        Appointment ap = new Appointment(
                txtId.getText(),
                txtPatientId.getText(),
                txtclinicId.getText(),
                txtfacilityId.getText(),
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
