package view;

import controller.facilityController;
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
import model.Facility;

public class AddFacility extends JDialog {

    private JTextField txtId, txtFacilityName, txtFacilityType, txtAddress, txtPostCode,
            txtPhoneNumber, txtEmail,
            txtOpeningHour, txtManagerName, txtCapacity, txtSpeciality;

    private facilityController controller;

    public AddFacility(JFrame parent, facilityController controller) {
        super(parent, "Add Facility", true); // modal
        this.controller = controller;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "Facility ID");
        txtFacilityName = addField(form, "Facility Name");
        txtFacilityType = addField(form, "Facility Type");
        txtAddress = addField(form, "Address");
        txtPostCode = addField(form, "Post Code");
        txtPhoneNumber = addField(form, "Phone Number");
        txtEmail = addField(form, "Email");
        txtOpeningHour = addField(form, "Opening Hour");
        txtManagerName = addField(form, "Manager Name");
        txtCapacity = addField(form, "Capacity");
        txtSpeciality = addField(form, "Speciality");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> saveFacility());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void saveFacility() {

        Facility f = new Facility(
                txtId.getText(),
                txtFacilityName.getText(),
                txtFacilityType.getText(),
                txtAddress.getText(),
                txtPostCode.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                txtOpeningHour.getText(),
                txtManagerName.getText(),
                txtCapacity.getText(),
                txtSpeciality.getText()
        );

        controller.addFacility(f);
        dispose();
    }
}
