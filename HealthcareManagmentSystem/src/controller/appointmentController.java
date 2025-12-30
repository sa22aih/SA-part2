/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Appointment;
import utils.Appointment_CSV_Helper;



public class appointmentController {
    private Appointment_CSV_Helper csvHelper;

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
    
    public void readData (DefaultTableModel model){
        model.setRowCount(0); 

        List<Appointment> appointments = csvHelper.readAppointments();
  
        for (Appointment a : appointments) {
            model.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getClinicianId(),
                a.getFacilityId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getDurationMinutes(),
                a.getAppointmentType(),
                a.getStatus(),
                a.getNotes(),
                a.getReasonForVisit(),
                a.getCreatedDate(),
                a.getLastModified()
            });
        }
    }
    
}
