/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Clinician;
import utils.Clinician_CSV_Helper;

import utils.Patients_CSV_Helper;


public class clinicianController {
    private Clinician_CSV_Helper csvHelper;

    private final String filePath = "resources/Clinicians.csv";
    
    public clinicianController(){
        this.csvHelper = new Clinician_CSV_Helper(filePath);
    }
    public void addClinician(Clinician p) {
    csvHelper.writeCSV(p);
}
    public void updateClinician(int index, Clinician updatedClinician) {
    csvHelper.updateCSV(index, updatedClinician);
}
    public void deleteClinician(int index) {
    csvHelper.deleteCSV(index);
}
    
    public void readData (DefaultTableModel model){
        model.setRowCount(0); 

        List<Clinician> clinicians = csvHelper.readClinician();
  
        for (Clinician c : clinicians) {
            model.addRow(new Object[]{
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getTitle(),
                c.getSpeciality(),
                c.getGmc(),
                c.getPhone(),
                c.getEmail(),
                c.getWorkPlaceId(),
                c.getWorkPlaceType(),
                c.getEmploymentStatus(),
                c.getStartDate()
            });
        }
    }
    
}
