
package controller;

import java.util.ArrayList;
import model.Referral;
import utils.RefferalCSVHelper;

public class refferalcontroller {

    private RefferalCSVHelper csvHelper;
    private ArrayList<Referral> refferalls;
    private final String filePath = "resources/referrals.csv";

    public refferalcontroller() {
        this.csvHelper = new RefferalCSVHelper(filePath);
    
    }
    public void addRefferal(Referral re) {
        csvHelper.writeCSV(re);
        csvHelper.writeTextFile(re);
    }

    public void updateRefferal(int index, Referral re) {
        csvHelper.updateCSV(index, re);
    }

    public void deleteRefferal(int index) {
        csvHelper.deleteCSV(index);
    }

    public ArrayList<Referral> getRefferals() {
        return this.refferalls;
    }

    public ArrayList<Referral> readData() {

        refferalls = csvHelper.readRefferals();

        return this.refferalls;
    }

}
