
package controller;

import java.util.ArrayList;
import model.Appointment;
import utils.Appointment_CSV_Helper;

public class appointmentController {

    private Appointment_CSV_Helper csvHelper;
    private ArrayList<Appointment> appointments;
    private final String filePath = "resources/appointments.csv";

    public appointmentController() {
        this.csvHelper = new Appointment_CSV_Helper(filePath);
    }

    public void addAppointment(Appointment p) {
        csvHelper.writeCSV(p);
    }

    public void updateAppointment(int index, Appointment appp) {
        csvHelper.updateCSV(index, appp);
    }

    public void deleteAppointment(int index) {
        csvHelper.deleteCSV(index);
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }

    public ArrayList<Appointment> readData() {

        appointments = csvHelper.readAppointments();

        return this.appointments;
    }

}
