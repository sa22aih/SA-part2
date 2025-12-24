package view;

import controller.patientController;
import model.Patient;

import javax.swing.*;
import java.awt.*;

public class EditPatientDialog extends JDialog {

    private JTextField txtId, txtFirstName, txtLastName, txtDob, txtNhs,
            txtGender, txtPhone, txtEmail, txtAddress, txtPostcode,
            txtEmergencyName, txtEmergencyPhone, txtRegDate, txtGpId;

    private patientController controller;
    private int rowIndex;

    public EditPatientDialog(JFrame parent,
                             patientController controller,
                             Patient patient,
                             int rowIndex) {

        super(parent, "Edit Patient", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(patient);
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtFirstName = addField(form, "First Name");
        txtLastName = addField(form, "Last Name");
        txtDob = addField(form, "Date of Birth");
        txtNhs = addField(form, "NHS Number");
        txtGender = addField(form, "Gender");
        txtPhone = addField(form, "Phone");
        txtEmail = addField(form, "Email");
        txtAddress = addField(form, "Address");
        txtPostcode = addField(form, "Postcode");
        txtEmergencyName = addField(form, "Emergency Contact Name");
        txtEmergencyPhone = addField(form, "Emergency Contact Phone");
        txtRegDate = addField(form, "Registration Date");
        txtGpId = addField(form, "GP Surgery ID");

        txtId.setEditable(false); // ID should not change

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(cancelBtn);
        btnPanel.add(updateBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());
        updateBtn.addActionListener(e -> updatePatient());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void fillData(Patient p) {
        txtId.setText(p.getId());
        txtFirstName.setText(p.getFirstName());
        txtLastName.setText(p.getLastName());
        txtDob.setText(p.getDate_of_birth());
        txtNhs.setText(p.getNhs_number());
        txtGender.setText(p.getGender());
        txtPhone.setText(p.getPhone());
        txtEmail.setText(p.getEmail());
        txtAddress.setText(p.getAddress());
        txtPostcode.setText(p.getPostcode());
        txtEmergencyName.setText(p.getEmergency_contact_name());
        txtEmergencyPhone.setText(p.getEmergency_contact_phone());
        txtRegDate.setText(p.getRegistration_date());
        txtGpId.setText(p.getGp_surgery_id());
    }

    private void updatePatient() {

        Patient updated = new Patient(
                txtId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtDob.getText(),
                txtNhs.getText(),
                txtGender.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtAddress.getText(),
                txtPostcode.getText(),
                txtEmergencyName.getText(),
                txtEmergencyPhone.getText(),
                txtRegDate.getText(),
                txtGpId.getText()
        );

        controller.updatePatient(rowIndex, updated);
        dispose();
    }
}
