
package view;

import controller.appointmentController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import model.Appointment;


public class EditAppointment extends JDialog{
  private JTextField txtId, txtPatientId, txtclinicId, txtfacilityId, txtappointmentdate,
            txtappointmenttime, txtdurationMin,
            txtappointmentType, txtstatus,txtnotes, txtreason, txtcreated, txtmodified;

    private appointmentController controller;
    private int rowIndex;

    public EditAppointment(JFrame parent,
                             appointmentController controller,
                             Appointment appointment,
                             int rowIndex) {

        super(parent, "Edit Facility", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(appointment);
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtPatientId = addField(form, "Patient ID");
        txtclinicId = addField(form, "Clinic ID");
        txtfacilityId = addField(form, "Facility ID");
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

    private void fillData(Appointment f) {
        txtId.setText(f.getAppointmentId());
        txtPatientId.setText(f.getPatientId());
        txtclinicId.setText(f.getClinicianId());
        txtfacilityId.setText(f.getFacilityId());
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
                txtPatientId.getText(),
                txtclinicId.getText(),
                txtfacilityId.getText(),
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
