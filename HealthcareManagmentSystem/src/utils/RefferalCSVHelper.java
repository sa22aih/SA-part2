package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import model.Referral;

public class RefferalCSVHelper {

    private final String filePath;
    private String headerLine;
    private CSV_Line_Parser parser;

    public RefferalCSVHelper(String filePath) {
        this.filePath = filePath;
        this.parser = new CSV_Line_Parser();
    }

    public ArrayList<Referral> readRefferals() {
        ArrayList<Referral> refferals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            headerLine = br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = this.parser.parseCSVLine(line);
                refferals.add(new Referral(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8],
                        data[9],
                        data[10],
                        data[11],
                        data[12],
                        data[13],
                        data[14],
                        data[15]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return refferals;
    }

    private ArrayList<Referral> removeHeaders(ArrayList<Referral> data) {
        if (data.size() > 0) {
            data.remove(0);
        }
        return data;
    }

    public void writeCSV(Referral record) {
        File file = new File(filePath);
        boolean needsNewLine = false;

        if (file.exists() && file.length() > 0) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                raf.seek(file.length() - 1);
                char lastChar = (char) raf.readByte();
                if (lastChar != '\n') {
                    needsNewLine = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.newLine();
            bw.write(
                    record.getReferralId()
                    + "," + record.getPatientId()
                    + "," + record.getReferringClinicianId()
                    + "," + record.getReferredToClinicianId()
                    + "," + record.getReferringFacilityId()
                    + "," + record.getReferredToFacilityId()
                    + "," + record.getReferralDate()
                    + ",\"" + record.getUrgencyLevel()
                    + "\"," + record.getReferralReason()
                    + "," + record.getClinicalSummary()
                    + "," + record.getRequestedInvestigations()
                    + "," + record.getStatus()
                    + "," + record.getAppointmentId()
                    + "," + record.getNotes()
                    + "," + record.getCreatedDate()
                    + "," + record.getLastUpdated()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCSV(int index, Referral newRecord) {
        ArrayList<Referral> data = readRefferals();

        if (index >= 0 && index < data.size()) {
            data.set(index, newRecord);
            rewriteCSV(data);
        }
    }

    public void deleteCSV(int index) {
        ArrayList<Referral> data = readRefferals();

        // JTable index maps directly to data list now
        if (index < 0 || index >= data.size()) {
            return;
        }

        data.remove(index);
        rewriteCSV(data);
    }

    private void rewriteCSV(ArrayList<Referral> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(headerLine);
//            bw.newLine();
            for (Referral record : data) {
                bw.newLine();
                bw.write(
                        record.getReferralId()
                        + "," + record.getPatientId()
                        + "," + record.getReferringClinicianId()
                        + "," + record.getReferredToClinicianId()
                        + "," + record.getReferringFacilityId()
                        + "," + record.getReferredToFacilityId()
                        + "," + record.getReferralDate()
                        + ",\"" + record.getUrgencyLevel()
                        + "\"," + record.getReferralReason()
                        + "," + record.getClinicalSummary()
                        + "," + record.getRequestedInvestigations()
                        + "," + record.getStatus()
                        + "," + record.getAppointmentId()
                        + "," + record.getNotes()
                        + "," + record.getCreatedDate()
                        + "," + record.getLastUpdated()
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTextFile(Referral pre) {
        String txtfilename = "textfile/refferals/" + pre.getReferralId() + "_output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtfilename))) {

            writer.write("Refferal ID: " + pre.getReferralId());
            writer.newLine();
            writer.write("Patient ID: " + pre.getPatientId());
            writer.newLine();
            writer.write("Reffering Clinician ID: " + pre.getReferringClinicianId());
            writer.newLine();
            writer.write("Reffered to Clinician ID: " + pre.getReferredToClinicianId());
            writer.newLine();
            writer.write("Reffering Facility ID: " + pre.getReferringFacilityId());
            writer.newLine();
            writer.write("Reffered to Facility ID: " + pre.getReferredToFacilityId());
            writer.newLine();
            writer.write("Refferal Date: " + pre.getReferralDate());
            writer.newLine();
            writer.write("Urgency Level: " + pre.getUrgencyLevel());
            writer.newLine();
            writer.write("Refferal Reason: " + pre.getReferralReason());
            writer.newLine();
            writer.write("Clinical Summary: " + pre.getClinicalSummary());
            writer.newLine();
            writer.write("Requested Investigations: " + pre.getRequestedInvestigations());
            writer.newLine();
            writer.write("Status: " + pre.getStatus());
            writer.newLine();
            writer.write("Appointment ID: " + pre.getAppointmentId());
            writer.newLine();
            writer.write("Notes: " + pre.getNotes());
            writer.newLine();
            writer.write("Creation Date: " + pre.getCreatedDate());
            writer.newLine();
            writer.write("Last Update: " + pre.getLastUpdated());
            writer.newLine();

            System.out.println("File created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
