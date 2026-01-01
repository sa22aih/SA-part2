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

    public Patient(String id, String name, String last_name, String dob, String nhs, String gender, String phone, String email, String address,
            String postCode, String ecn, String ecp, String r_date, String gp_id) {
        this.patient_id = id;
        this.first_name = name;
        this.last_name = last_name;
        this.date_of_birth = dob;
        this.nhs_number = nhs;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
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
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostcode() {
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

}
