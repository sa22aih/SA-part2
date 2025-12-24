/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Patient;
public class Patients_CSV_Helper {
      private final String filePath;
      private String headerLine;
      private CSV_Line_Parser parser;

  
    public Patients_CSV_Helper(String filePath) {
        this.filePath = filePath;
        this.parser = new CSV_Line_Parser();
    }
    public  List<Patient> readPatients() {
        List<Patient> patients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
             headerLine = br.readLine(); 
            String line;
 
            while ((line = br.readLine()) != null) {
                     String[] data =this.parser.parseCSVLine(line);
                     patients.add(new Patient(
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
                          data[13]
                ));
            }
//            this.removeHeaders(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    private List<Patient> removeHeaders( List<Patient> data ){
        if(data.size()>0){
           data.remove(0);
        }
        return data;
    }

    public  void writeCSV(Patient record) {
        File file = new File(filePath);
        boolean needsNewLine = false;

        if (file.exists() && file.length() > 0) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                raf.seek(file.length() - 1);
                char lastChar = (char) raf.readByte();
                if (lastChar != '\n') {
                    needsNewLine = true;
                }
            }
            catch(IOException e){  e.printStackTrace();}
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.newLine();
            bw.write(
                    record.getId()
                    +","+record.getFirstName()
                    +","+record.getLastName()
                    +","+record.getDate_of_birth()
                    +","+record.getNhs_number()
                    +","+record.getGender()
                    +","+record.getPhone()
                    + "," + record.getEmail()
                    +",\""+record.getAddress()
                    +"\","+record.getPostcode()
                    +","+record.getEmergency_contact_name()
                    +","+record.getEmergency_contact_phone()
                    +","+record.getRegistration_date()
                    +","+record.getGp_surgery_id()
            );
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public  void updateCSV(int index, Patient newRecord) {
        List<Patient> data = readPatients();

        if (index >= 0 && index < data.size()) {
            data.set(index, newRecord);
            rewriteCSV(data);
        }
    }

    public  void deleteCSV(int index) {
        List<Patient> data = readPatients();

         // JTable index maps directly to data list now
    if (index < 0 || index >= data.size()) {
        return;
    }

    data.remove(index);
    rewriteCSV(data);
    }

 
    private void rewriteCSV(List<Patient> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        bw.write(headerLine);
        bw.newLine();
            for (Patient record : data) {
                bw.write( record.getId()
                    + "," + record.getFirstName()
                    + "," + record.getLastName()
                    + "," + record.getDate_of_birth()
                    + "," + record.getNhs_number()
                    + "," + record.getNhs_number()
                    + "," + record.getGender()
                    + "," + record.getPhone()
                    + "," + record.getEmail()
                    + "," + record.getAddress()
                    + "," + record.getPostcode()
                    + "," + record.getEmergency_contact_name()
                    + "," + record.getEmergency_contact_phone()
                    + "," + record.getRegistration_date()
                    + "," + record.getGp_surgery_id());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
