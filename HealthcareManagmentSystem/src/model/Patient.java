/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
public class Patient {
    
    private String patient_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String nhs_number;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String postcode;
    private String emergency_contact_name;
    private String emergency_contact_phone;
    private String registration_date;
    private String gp_surgery_id;

    public Patient(String id, String name, String last_name,String dob,String nhs,String gender,String phone,String email,String address,
            String postCode,String ecn,String ecp,String r_date,String gp_id) {
        this.patient_id = id;
        this.first_name = name;
        this.last_name = last_name;
        this.date_of_birth = dob;
        this.nhs_number=nhs;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address= address;
        this.postcode = postCode;
        this.emergency_contact_name = ecn;
        this.emergency_contact_phone = ecp;
        this.registration_date = r_date;
        this.gp_surgery_id = gp_id;
        
    }

    
    public String getId() {
        return this.patient_id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getDate_of_birth() {
        return this.date_of_birth;
    }

    public String getNhs_number() {
        return this.nhs_number;
    }   
    public String getGender() {
        return this.gender;
    }

    public String getPhone() {
        return this.phone;
    }    public String email() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }    public String postcode() {
        return this.postcode;
    }

    public String getEmergency_contact_name() {
        return this.emergency_contact_name;
    }
    
    public String getEmergency_contact_phone() {
        return this.emergency_contact_phone;
    }
    
    public String getRegistration_date() {
        return this.registration_date;
    }
    
    public String getGp_surgery_id() {
        return this.gp_surgery_id;
    }
    public void setFirstName(String name) {
        this.first_name = name;
    }

    public void setLastName(String name) {
        this.last_name= name;
    }
    
  

    public void setDate_of_birth(String dob) {
         this.date_of_birth = dob;
    }

    public void setNhs_number(String nhs) {
         this.nhs_number = nhs;
    }   
    public void gender(String gender) {
         this.gender=gender;
    }

    public void phone(String phone) {
         this.phone= phone;
    }   
    public void email(String email) {
         this.email=email;
    }

    public void address(String address) {
         this.address = address;
    }    public void postcode(String postCode) {
         this.postcode=postCode;
    }

    public void emergency_contact_name(String ecn) {
         this.emergency_contact_name= ecn;
    }
    
    public void emergency_contact_phone(String ecp) {
         this.emergency_contact_phone=ecp;
    }
    
    public void registration_date(String r_date) {
         this.registration_date=r_date;
    }
    
    
}
