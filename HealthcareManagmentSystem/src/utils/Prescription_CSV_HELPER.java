/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import model.Prescription;

public class Prescription_CSV_HELPER {

    private final String filePath;
    private String headerLine;
    private CSV_Line_Parser parser;

    public Prescription_CSV_HELPER(String filePath) {
        this.filePath = filePath;
        this.parser = new CSV_Line_Parser();
    }

    public ArrayList<Prescription> readPrescription() {
        ArrayList<Prescription> prescriptions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            headerLine = br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = this.parser.parseCSVLine(line);
                prescriptions.add(new Prescription(
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
                        data[14]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    private ArrayList<Prescription> removeHeaders(ArrayList<Prescription> data) {
        if (data.size() > 0) {
            data.remove(0);
        }
        return data;
    }

    public void writeCSV(Prescription record) {
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
                    record.getPrescriptionId()
                    + "," + record.getPatientId()
                    + "," + record.getClinicianId()
                    + "," + record.getAppointmentId()
                    + "," + record.getPrescriptionDate()
                    + "," + record.getMedicationName()
                    + "," + record.getDosage()
                    + ",\"" + record.getFrequency()
                    + "\"," + record.getDurationDays()
                    + "," + record.getQuantity()
                    + "," + record.getInstructions()
                    + "," + record.getPharmacyName()
                    + "," + record.getPharmacyName()
                    + "," + record.getIssueDate()
                    + "," + record.getCollectionDate()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCSV(int index, Prescription newRecord) {
        ArrayList<Prescription> data = readPrescription();

        if (index >= 0 && index < data.size()) {
            data.set(index, newRecord);
            rewriteCSV(data);
        }
    }

    public void deleteCSV(int index) {
        ArrayList<Prescription> data = readPrescription();

        // JTable index maps directly to data list now
        if (index < 0 || index >= data.size()) {
            return;
        }

        data.remove(index);
        rewriteCSV(data);
    }

    private void rewriteCSV(ArrayList<Prescription> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(headerLine);
//            bw.newLine();
            for (Prescription record : data) {
                 bw.newLine();
                bw.write(
                        record.getPrescriptionId()
                    + "," + record.getPatientId()
                    + "," + record.getClinicianId()
                    + "," + record.getAppointmentId()
                    + "," + record.getPrescriptionDate()
                    + "," + record.getMedicationName()
                    + "," + record.getDosage()
                    + ",\"" + record.getFrequency()
                    + "\"," + record.getDurationDays()
                    + "," + record.getQuantity()
                    + "," + record.getInstructions()
                    + "," + record.getPharmacyName()
                    + "," + record.getPharmacyName()
                    + "," + record.getIssueDate()
                    + "," + record.getCollectionDate()
                );
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeTextFile (Prescription pre){
    String txtfilename = "textfile/prescriptions/"+ pre.getPrescriptionId()+"_output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtfilename))) {

                writer.write("Prescription ID: " + pre.getPrescriptionId());
                writer.newLine();
                writer.write("Patient ID: " + pre.getPatientId());
                writer.newLine();
                writer.write("Clinician ID: " + pre.getClinicianId());
                writer.newLine();
                writer.write("Appointment ID: " + pre.getAppointmentId());
                writer.newLine();
                writer.write("Prescription Date: " + pre.getPrescriptionDate());
                writer.newLine();
                writer.write("Medication Name: " + pre.getMedicationName());
                writer.newLine();
                writer.write("Dosage: " + pre.getDosage());
                writer.newLine();
                writer.write("Frequency: " + pre.getFrequency());
                writer.newLine();
                writer.write("Duration (Days): " + pre.getDurationDays());
                writer.newLine();
                writer.write("Quantity: " + pre.getQuantity());
                writer.write("Instructions: " + pre.getInstructions());
                writer.newLine();
                writer.write("Pharmacy Name: " + pre.getPharmacyName());
                writer.newLine();
                writer.write("Status: " + pre.getStatus());
                writer.newLine();
                writer.write("Issue Date: " + pre.getIssueDate());
                writer.newLine();
                writer.write("Collection Date: " + pre.getCollectionDate());
                writer.newLine();

            System.out.println("File created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
