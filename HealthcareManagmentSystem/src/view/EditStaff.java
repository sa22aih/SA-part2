package view;

import controller.staffController;
import controller.facilityController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.Staff;
import model.Facility;

public class EditStaff extends JDialog {

     private JTextField txtId, txtfirstname,
            txtlastname, txtrole,
            txtdepartment, txtphone, txtemail, txtempstatus, txtstartdate, txtlinemanager,txtaccesslevel;
    private JComboBox txtfacilityId;
    private staffController controller;
    private ArrayList<Facility> facilities;
    private facilityController facilityController;
    private int rowIndex;

    public EditStaff(JFrame parent,
            staffController controller,
            Staff staff,
            int rowIndex) {

        super(parent, "Edit Staff", true);
        this.controller = controller;
        this.rowIndex = rowIndex;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initForm();
        fillData(staff);
    }

    private void initForm() {
        this.facilityController = new facilityController();
        this.facilities = this.facilityController.readData();
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
        txtId.setEditable(false);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(cancelBtn);
        btnPanel.add(updateBtn);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> dispose());
        updateBtn.addActionListener(e -> updateStaff());
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

        for (Facility ff : facilities) {
            field.addItem(ff.getFacility_name());

        }
        panel.add(field);
        return field;
    }

    private void fillData(Staff f) {
        txtId.setText(f.getId());

       

        for (Facility ff : facilities) {

            if (f.getfacilityId().equals(ff.getFacility_id())) {
                txtfacilityId.setSelectedIndex(facilities.indexOf(ff));
            }

        }

        txtfirstname.setText(f.getfirstName());
        txtlastname.setText(f.getlastName());
        txtrole.setText(f.getrole());
        txtdepartment.setText(f.getdepartment());
        txtphone.setText(f.getphoneNumber());
        txtemail.setText(f.getemail());
        txtempstatus.setText(f.getemploymentStatus());
        txtstartdate.setText(f.getstartDate());
        txtlinemanager.setText(f.getlineManager());
        txtaccesslevel.setText(f.getaccessLevel());
    }

    private void updateStaff() {

        Staff ss = new Staff(
                txtId.getText(),
                txtfirstname.getText(),
                txtlastname.getText(),
                txtrole.getText(),
                txtdepartment.getText(),
                facilities.get(this.txtfacilityId.getSelectedIndex()).getFacility_id(),
                txtphone.getText(),
                txtemail.getText(),
                txtempstatus.getText(),
                txtstartdate.getText(),
                txtlinemanager.getText(),
                txtaccesslevel.getText()
        );

        controller.updateStaff(rowIndex, ss);
        dispose();
    }
}
