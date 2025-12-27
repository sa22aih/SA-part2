/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.clinicianController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import model.Clinician;


public class EditClinician extends JDialog{
      private JTextField txtId, txtFirstName, txtLastName, txtTitle, txtGmc,
            txtSpeciality, txtPhone,
            txtWorkplaceId, txtWorkplaceType, txtEmploymentStatus, txtStartDate, txtEmail;

    private clinicianController controller;
    private int rowIndex;

    public EditClinician(JFrame parent,
                             clinicianController controller,
                             Clinician clinician,
                             int rowIndex) {

        super(parent, "Edit Patient", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(clinician);
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtFirstName = addField(form, "First Name");
        txtLastName = addField(form, "Last Name");
        txtTitle = addField(form, "Title");
        txtSpeciality = addField(form, "Speciality");
        txtGmc = addField(form, "GMC Number");
        txtPhone = addField(form, "Phone");
        txtEmail = addField(form, "Email");
        txtWorkplaceId = addField(form, "Workplace Id");
        txtWorkplaceType = addField(form, "Workplace Type");
        txtEmploymentStatus = addField(form, "Employment Status");
        txtStartDate = addField(form, "Start Date");

        txtId.setEditable(false);

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

    private void fillData(Clinician c) {
        txtId.setText(c.getId());
        txtFirstName.setText(c.getFirstName());
        txtLastName.setText(c.getLastName());
        txtTitle.setText(c.getTitle());
        txtSpeciality.setText(c.getSpeciality());
        txtGmc.setText(c.getGmc());
        txtPhone.setText(c.getPhone());
        txtEmail.setText(c.getEmail());
        txtWorkplaceId.setText(c.getWorkPlaceId());
        txtWorkplaceType.setText(c.getWorkPlaceType());
        txtEmploymentStatus.setText(c.getEmploymentStatus());
        txtStartDate.setText(c.getStartDate());
    }

    private void updatePatient() {

        Clinician updated = new Clinician(
                txtId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtTitle.getText(),
                txtSpeciality.getText(),
                txtGmc.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtWorkplaceId.getText(),
                txtWorkplaceType.getText(),
                txtEmploymentStatus.getText(),
                txtStartDate.getText()
        );

        controller.updateClinician(rowIndex, updated);
        dispose();
    }
}
