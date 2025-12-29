/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainDashboard extends JFrame {
    public MainDashboard(){
            setTitle("Healthcare Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        renderMenu();
     }
    
    private  void  renderMenu(){
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create cards
        container.add(createCard("Patients"));
        container.add(createCard("Clinicians"));
        container.add(createCard("Appointments"));
        container.add(createCard("prescriptions"));
        container.add(createCard("Facility"));

        add(container);
        setVisible(true);
    }
     private JPanel createCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.DARK_GRAY);
        card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.white);
        card.add(label, BorderLayout.CENTER);
        card.setPreferredSize(new Dimension(200, 120));
        // Click event
        card.addMouseListener(new MouseAdapter() {
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
                }
            }
        });

        return card;
    }
}
