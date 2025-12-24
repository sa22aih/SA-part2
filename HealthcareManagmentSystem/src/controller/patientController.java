/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Patient;
import utils.Patients_CSV_Helper;


public class patientController {
    private Patients_CSV_Helper csvHelper;

    private final String filePath = "resources/patients.csv";
    
    public patientController(){
        this.csvHelper = new Patients_CSV_Helper(filePath);
    }
    public void addPatient(Patient p) {
    csvHelper.writeCSV(p);
}
    public void updatePatient(int index, Patient updatedPatient) {
    csvHelper.updateCSV(index, updatedPatient);
}
    public void deletePatient(int index) {
    csvHelper.deleteCSV(index);
}
    
    public void readData (DefaultTableModel model){
        model.setRowCount(0); 

        List<Patient> patients = csvHelper.readPatients();
  
        for (Patient p : patients) {
            model.addRow(new Object[]{
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDate_of_birth(),
                p.getNhs_number(),
                p.getGender(),
                p.getPhone(),
                p.getEmail(),
                p.getAddress(),
                p.getPostcode(),
                p.getEmergency_contact_name(),
                p.getEmergency_contact_phone(),
                p.getRegistration_date(),
                p.getGp_surgery_id()
            });
        }
    }
    
}
