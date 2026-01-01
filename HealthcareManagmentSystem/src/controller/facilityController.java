package controller;

import java.util.ArrayList;
import model.Facility;
import utils.Facility_CSV_Helper;

public class facilityController {

    private Facility_CSV_Helper csvHelper;
    private ArrayList<Facility> facilities;
    private final String filePath = "resources/facilities.csv";

    public facilityController() {
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

    public ArrayList<Facility> getFacilites() {
        return this.facilities;
    }

    public ArrayList<Facility> readData() {

        facilities = csvHelper.readFacilities();

        return this.facilities;
    }

}
