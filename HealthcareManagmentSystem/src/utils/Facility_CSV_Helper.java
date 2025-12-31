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
import java.util.List;
import model.Facility;

public class Facility_CSV_Helper {
      private final String filePath;
      private String headerLine;
      private CSV_Line_Parser parser;

  
    public Facility_CSV_Helper(String filePath) {
        this.filePath = filePath;
        this.parser = new CSV_Line_Parser();
    }
    public  ArrayList<Facility> readFacilities() {
        ArrayList<Facility> facility = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
             headerLine = br.readLine(); 
            String line;
 
            while ((line = br.readLine()) != null) {
                     String[] data =this.parser.parseCSVLine(line);
                     facility.add(new Facility(
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
                          data[10]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return facility;
    }
    
    private ArrayList<Facility> removeHeaders( ArrayList<Facility> data ){
        if(data.size()>0){
           data.remove(0);
        }
        return data;
    }

    public  void writeCSV(Facility record) {
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
                    record.getFacility_id()
                    +","+record.getFacility_name()
                    +","+record.getFacility_type()
                    +","+record.getAddress()
                    +","+record.getPostcode()
                    +","+record.getPhone_number()
                    + "," + record.getEmail()
                    +",\""+record.getOpening_hours()
                    +"\","+record.getManager_name()
                    +","+record.getCapacity()
                    +","+record.getSpecialities_offered()
            );
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public  void updateCSV(int index, Facility newRecord) {
        ArrayList<Facility> data = readFacilities();

        if (index >= 0 && index < data.size()) {
            data.set(index, newRecord);
            rewriteCSV(data);
        }
    }

    public  void deleteCSV(int index) {
        ArrayList<Facility> data = readFacilities();

         // JTable index maps directly to data list now
    if (index < 0 || index >= data.size()) {
        return;
    }

    data.remove(index);
    rewriteCSV(data);
    }

 
    private void rewriteCSV(List<Facility> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        bw.write(headerLine);
            for (Facility record : data) {
                 bw.newLine();
                bw.write( record.getFacility_id()
                    + "," + record.getFacility_name()
                    + "," + record.getFacility_type()
                    + "," + record.getAddress()
                    + "," + record.getPostcode()
                    + "," + record.getPhone_number()
                    + "," + record.getEmail()
                    + "," + record.getOpening_hours()
                    + "," + record.getManager_name()
                    + "," + record.getCapacity()
                    + "," + record.getSpecialities_offered());
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
