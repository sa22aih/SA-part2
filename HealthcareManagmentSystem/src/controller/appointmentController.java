/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Appointment;
import utils.Appointment_CSV_Helper;



public class appointmentController {
    private Appointment_CSV_Helper csvHelper;
    private  ArrayList<Appointment> appointments ;
    private final String filePath = "resources/appointments.csv";
    
    public appointmentController(){
        this.csvHelper = new Appointment_CSV_Helper(filePath);
    }
    public void addAppointment(Appointment p) {
    csvHelper.writeCSV(p);
}
    public void updateAppointment(int index, Appointment updatedFacility) {
    csvHelper.updateCSV(index, updatedFacility);
}
    public void deleteAppointment(int index) {
    csvHelper.deleteCSV(index);
}
    
    public ArrayList<Appointment> getAppointments(){
    return this.appointments;
    }
    
    public ArrayList<Appointment> readData (){
     
         appointments = csvHelper.readAppointments();
  
       
        return this.appointments;
    }
    
}
