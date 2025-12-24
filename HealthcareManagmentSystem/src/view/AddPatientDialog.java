/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import javax.swing.*;
import java.awt.*;
import controller.patientController;
import model.Patient;

public class AddPatientDialog extends JDialog {

    private JTextField txtId, txtFirstName, txtLastName, txtDob, txtNhs,
            txtGender, txtPhone, txtAddress, txtPostcode,
            txtEmergencyName, txtEmergencyPhone, txtRegDate, txtGpId,txtEmail;

    private patientController controller;

    public AddPatientDialog(JFrame parent, patientController controller) {
        super(parent, "Add Patient", true); // modal
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

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // 🔥 BUTTON ACTIONS
        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> savePatient());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void savePatient() {

        Patient p = new Patient(
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

        controller.addPatient(p);
        dispose(); // close dialog
    }
}
