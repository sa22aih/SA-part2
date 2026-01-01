package controller;

import java.util.ArrayList;
import model.Clinician;
import utils.Clinician_CSV_Helper;


public class clinicianController {

    private Clinician_CSV_Helper csvHelper;
    private ArrayList<Clinician> clinicians;
    private final String filePath = "resources/Clinicians.csv";

    public clinicianController() {
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

    public ArrayList<Clinician> getClinicians() {

        return this.getClinicians();
    }

    public ArrayList<Clinician> readData() {

        clinicians = csvHelper.readClinician();

        return this.clinicians;
    }

}
