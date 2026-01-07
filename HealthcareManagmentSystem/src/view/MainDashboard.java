package view;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Healthcare Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainContainer.setAlignmentY(Component.TOP_ALIGNMENT);
        mainContainer.add(renderMenu());
        mainContainer.add(dashboard());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(mainContainer);
        setVisible(true);
    }

    private JPanel renderMenu() {

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        container.setOpaque(false);
        container.add(createCard("Patients"));
        container.add(createCard("Clinicians"));
        container.add(createCard("Appointments"));
        container.add(createCard("Prescriptions"));
        container.add(createCard("Facility"));
        container.add(createCard("Refferalls"));
        container.add(createCard("Staffs"));

        return container;

    }

    private JPanel dashboard() {

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(5,5,3,2));
        container.setOpaque(false);
        patientController patient = new patientController();
        clinicianController clinician = new clinicianController();
        appointmentController appointment = new appointmentController();
        prescriptionController prescription = new prescriptionController();
        facilityController facility = new facilityController();
        refferalcontroller referral = new refferalcontroller();
        staffController staff = new staffController();

        container.add(createDasbhoardCard("Total Patients " + patient.readData().size()));
        container.add(createDasbhoardCard("Total Clinicians " + clinician.readData().size()));
        container.add(createDasbhoardCard("Total Appointments \n" + appointment.readData().size()));
        container.add(createDasbhoardCard("Total Prescriptions " + prescription.readData().size()));
        container.add(createDasbhoardCard("Total Facilities " + facility.readData().size()));
        container.add(createDasbhoardCard("Total Referrals " + referral.readData().size()));
        container.add(createDasbhoardCard("Total Staffs " + staff.readData().size()));

        return container;
    }

    private JPanel createDasbhoardCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(2, 69, 32));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.white);
        card.add(label, BorderLayout.CENTER);
        card.setPreferredSize(new Dimension(300, 300));

        return card;
    }

    private JPanel createCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(224, 130, 7));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.white);
        card.add(label, BorderLayout.CENTER);
        card.setPreferredSize(new Dimension(200, 120));
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(224, 144, 38));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(224, 130, 7));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                switch (title) {
                    case "Patients":
                        dispose();
                        new Patients();
                        break;
                    case "Clinicians":
                        dispose();
                        new Clinicians();
                        break;
                    case "Facility":
                        dispose();
                        new Facilities();
                        break;
                    case "Appointments":
                        dispose();
                        new Appointments();
                        break;
                    case "Prescriptions":
                        dispose();
                        new Prescriptions();
                        break;
                    case "Refferalls":
                        dispose();
                        new Refferalls();
                        break;
                    case "Staffs":
                        dispose();
                        new Staffs();
                        break;
                }
            }
        });

        return card;
    }
}
