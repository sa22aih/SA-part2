/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.clinicianController;
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
import model.Clinician;

public class AddNewClinician extends JDialog{
       private JTextField txtId, txtFirstName, txtLastName, txtTitle, txtGmc,
            txtSpeciality, txtPhone,
            txtWorkplaceId, txtWorkplaceType, txtEmploymentStatus, txtStartDate, txtEmail;

    private clinicianController controller;

    public AddNewClinician(JFrame parent, clinicianController controller) {
        super(parent, "Add Clinician", true); // modal
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
        txtTitle = addField(form, "Title");
        txtSpeciality = addField(form, "Speciality");
        txtGmc = addField(form, "GMC Number");
        txtPhone = addField(form, "Phone");
        txtEmail = addField(form, "Email");
        txtWorkplaceId = addField(form, "Workplace Id");
        txtWorkplaceType = addField(form, "Workplace Type");
        txtEmploymentStatus = addField(form, "Employment Status");
        txtStartDate = addField(form, "Start Date");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> saveClinician());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void saveClinician() {

        Clinician c = new Clinician(
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

        controller.addClinician(c);
        dispose();
    }
}
