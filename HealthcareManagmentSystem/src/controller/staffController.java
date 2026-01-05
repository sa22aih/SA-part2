
package controller;

import java.util.ArrayList;
import model.Staff;
import utils.staffCSV;

public class staffController {

    private staffCSV csvHelper;
    private ArrayList<Staff> staffs;
    private final String filePath = "resources/staff.csv";

    public staffController() {
        this.csvHelper = new staffCSV(filePath);
    }

    public void addStaff(Staff p) {
        csvHelper.writeCSV(p);
    }

    public void updateStaff(int index, Staff appp) {
        csvHelper.updateCSV(index, appp);
    }

    public void deleteStaff(int index) {
        csvHelper.deleteCSV(index);
    }

    public ArrayList<Staff> getStaffs() {
        return this.staffs;
    }

    public ArrayList<Staff> readData() {

        staffs = csvHelper.readStaffs();

        return this.staffs;
    }

}
