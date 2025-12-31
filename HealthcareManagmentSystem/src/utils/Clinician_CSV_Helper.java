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
import model.Clinician;

public class Clinician_CSV_Helper {
      private final String filePath;
      private String headerLine;
      private CSV_Line_Parser parser;

  
    public Clinician_CSV_Helper(String filePath) {
        this.filePath = filePath;
        this.parser = new CSV_Line_Parser();
    }
    public  ArrayList<Clinician> readClinician() {
        ArrayList<Clinician> Clinicians = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
             headerLine = br.readLine(); 
            String line;
 
            while ((line = br.readLine()) != null) {
                     String[] data =this.parser.parseCSVLine(line);
                     Clinicians.add(new Clinician(
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
                          data[11]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Clinicians;
    }
    
    private ArrayList<Clinician> removeHeaders( ArrayList<Clinician> data ){
        if(data.size()>0){
           data.remove(0);
        }
        return data;
    }

    public  void writeCSV(Clinician record) {
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
                    +","+record.getTitle()
                    +","+record.getSpeciality()
                    +","+record.getGmc()
                    +","+record.getPhone()
                    + "," + record.getEmail()
                    +",\""+record.getWorkPlaceId()
                    +"\","+record.getWorkPlaceType()
                    +","+record.getEmploymentStatus()
                    +","+record.getStartDate()
            );
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public  void updateCSV(int index, Clinician newRecord) {
        ArrayList<Clinician> data = readClinician();

        if (index >= 0 && index < data.size()) {
            data.set(index, newRecord);
            rewriteCSV(data);
        }
    }

    public  void deleteCSV(int index) {
        ArrayList<Clinician> data = readClinician();

         // JTable index maps directly to data list now
    if (index < 0 || index >= data.size()) {
        return;
    }

    data.remove(index);
    rewriteCSV(data);
    }

 
    private void rewriteCSV(ArrayList<Clinician> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        bw.write(headerLine);
            for (Clinician record : data) {
                bw.newLine();
                bw.write( record.getId()
                    + "," + record.getFirstName()
                    + "," + record.getLastName()
                    + "," + record.getTitle()
                    + "," + record.getSpeciality()
                    + "," + record.getGmc()
                    + "," + record.getPhone()
                    + "," + record.getEmail()
                    + "," + record.getWorkPlaceId()
                    + "," + record.getWorkPlaceType()
                    + "," + record.getEmploymentStatus()
                    + "," + record.getStartDate());
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
