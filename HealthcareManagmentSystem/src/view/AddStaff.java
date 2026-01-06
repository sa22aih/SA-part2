package view;

import controller.staffController;
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
import model.Staff;
import model.Facility;

public class AddStaff extends JDialog {

    private JTextField txtId, txtfirstname,
            txtlastname, txtrole,
            txtdepartment, txtphone, txtemail, txtempstatus, txtstartdate, txtlinemanager,txtaccesslevel;
    private JComboBox txtfacilityId;
    private staffController controller;
    private ArrayList<Facility> facilitieess;
    private facilityController facilityController;

    public AddStaff(JFrame parent, staffController controller) {
        super(parent, "Add Staff", true); 
        this.controller = controller;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();

    }

    private void initForm() {
        facilityController = new facilityController();

        facilitieess = facilityController.readData();
        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtId = addField(form, "ID");
        txtfirstname = addField(form, "First Name");
        txtlastname = addField(form, "Last Name");
        txtrole = addField(form, "Role");
        txtdepartment = addField(form, "Department");
        txtfacilityId = addFacilityComboBox(form, "Facility ID");
        txtphone = addField(form, "Phone");
        txtemail = addField(form, "Email");
        txtempstatus = addField(form, "Employee Status");
        txtstartdate = addField(form, "Start Date");
        txtlinemanager = addField(form, "Line Manager");
        txtaccesslevel = addField(form, "Access Level");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());

        saveBtn.addActionListener(e -> saveStaff());
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
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

    private void saveStaff() {

        Staff ss = new Staff(
                txtId.getText(),
                txtfirstname.getText(),
                txtlastname.getText(),
                txtrole.getText(),
                txtdepartment.getText(),
                facilitieess.get(this.txtfacilityId.getSelectedIndex()).getFacility_id(),
                txtphone.getText(),
                txtemail.getText(),
                txtempstatus.getText(),
                txtstartdate.getText(),
                txtlinemanager.getText(),
                txtaccesslevel.getText()
        );

        controller.addStaff(ss);
        dispose();
    }
}
