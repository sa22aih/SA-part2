
package controller;

import java.util.ArrayList;
import model.Prescription;
import utils.Prescription_CSV_HELPER;

public class prescriptionController {

    private Prescription_CSV_HELPER csvHelper;
    private ArrayList<Prescription> prescriptions;
    private final String filePath = "resources/prescriptions.csv";

    public prescriptionController() {
        this.csvHelper = new Prescription_CSV_HELPER(filePath);
    }

    public void addPrescription(Prescription p) {
        csvHelper.writeCSV(p);
        csvHelper.writeTextFile(p);
    }

    public void updatePrescription(int index, Prescription up) {
        csvHelper.updateCSV(index, up);
    }

    public void deletePrescription(int index) {
        csvHelper.deleteCSV(index);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    public ArrayList<Prescription> readData() {

        prescriptions = csvHelper.readPrescription();

        return this.prescriptions;
    }

}
