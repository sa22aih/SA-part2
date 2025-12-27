/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Clinician {
     
    private String clinican_id;
    private String first_name;
    private String last_name;
    private String title;
    private String speciality;
    private String gmc;
    private String phone;
    private String email;
    private String workplace_id;
    private String workplace_type;
    private String employment_status;
    private String start_date;
 

    public Clinician(String id, String name, String last_name,String title,String speciality,String gmc,String phone,String email,String workplace_id,
            String workplace_type,String employment_status,String start_date) {
        this.clinican_id = id;
        this.first_name = name;
        this.last_name = last_name;
        this.title = title;
        this.speciality=speciality;
        this.gmc = gmc;
        this.phone = phone;
        this.email = email;
        this.workplace_id= workplace_id;
        this.workplace_type = workplace_type;
        this.employment_status = employment_status;
        this.start_date = start_date;

        
    }

    
    public String getId() {
        return this.clinican_id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSpeciality() {
        return this.speciality;
    }   
    public String getGmc() {
        return this.gmc;
    }

    public String getPhone() {
        return this.phone;
    }    
    public String getEmail() {
        return this.email;
    }

    public String getWorkPlaceId() {
        return this.workplace_id;
    }    
    public String getWorkPlaceType() {
        return this.workplace_type;
    }

    public String getEmploymentStatus() {
        return this.employment_status;
    }
    
    public String getStartDate() {
        return this.start_date;
    }
    
    
}
