package view;

import controller.facilityController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import model.Facility;

public class EditFacility extends JDialog {

    private JTextField txtId, txtFacilityName, txtFacilityType, txtAddress, txtPostCode,
            txtPhoneNumber, txtEmail,
            txtOpeningHour, txtManagerName, txtCapacity, txtSpeciality;

    private facilityController controller;
    private int rowIndex;

    public EditFacility(JFrame parent,
            facilityController controller,
            Facility facility,
            int rowIndex) {

        super(parent, "Edit Facility", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(facility);
    }

    private void initForm() {

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
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

        txtId.setEditable(false);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(cancelBtn);
        btnPanel.add(updateBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());
        updateBtn.addActionListener(e -> updateFacility());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void fillData(Facility f) {
        txtId.setText(f.getFacility_id());
        txtFacilityName.setText(f.getFacility_name());
        txtFacilityType.setText(f.getFacility_type());
        txtAddress.setText(f.getAddress());
        txtPostCode.setText(f.getPostcode());
        txtPhoneNumber.setText(f.getPhone_number());
        txtEmail.setText(f.getEmail());
        txtOpeningHour.setText(f.getOpening_hours());
        txtManagerName.setText(f.getManager_name());
        txtCapacity.setText(f.getCapacity());
        txtSpeciality.setText(f.getSpecialities_offered());
    }

    private void updateFacility() {

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

        controller.updateFacility(rowIndex, f);
        dispose();
    }
}
