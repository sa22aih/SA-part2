/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Patient;
import utils.Patients_CSV_Helper;


public class patientController {
    private Patients_CSV_Helper csvHelper;
    private  ArrayList<Patient> patients ;
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
    public ArrayList<Patient> getPatients(){
    return this.patients;
    }
    
    
    public ArrayList<Patient> readData (){
        

        patients = csvHelper.readPatients();
  
        return patients;
    }
    
}
