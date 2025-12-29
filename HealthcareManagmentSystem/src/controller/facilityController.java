/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Facility;
import utils.Facility_CSV_Helper;



public class facilityController {
    private Facility_CSV_Helper csvHelper;

    private final String filePath = "resources/facilities.csv";
    
    public facilityController(){
        this.csvHelper = new Facility_CSV_Helper(filePath);
    }
    public void addFacility(Facility p) {
    csvHelper.writeCSV(p);
}
    public void updateFacility(int index, Facility updatedFacility) {
    csvHelper.updateCSV(index, updatedFacility);
}
    public void deleteFacility(int index) {
    csvHelper.deleteCSV(index);
}
    
    public void readData (DefaultTableModel model){
        model.setRowCount(0); 

        List<Facility> facilities = csvHelper.readFacilities();
  
        for (Facility f : facilities) {
            model.addRow(new Object[]{
                f.getFacility_id(),
                f.getFacility_name(),
                f.getFacility_type(),
                f.getAddress(),
                f.getPostcode(),
                f.getPhone_number(),
                f.getEmail(),
                f.getOpening_hours(),
                f.getManager_name(),
                f.getCapacity(),
                f.getSpecialities_offered()
            });
        }
    }
    
}
