package model;

public class Facility {

    private String facility_id;
    private String facility_name;
    private String facility_type;
    private String address;
    private String postcode;
    private String phone_number;
    private String email;
    private String opening_hours;
    private String manager_name;
    private String capacity;
    private String specialities_offered;

    public Facility(String facility_id, String facility_name, String facility_type, String address, String postcode, String phone_number, String email,
            String opening_hours, String manager_name,
            String capacity, String specialities_offered) {
        this.facility_id = facility_id;
        this.facility_name = facility_name;
        this.facility_type = facility_type;
        this.address = address;
        this.postcode = postcode;
        this.phone_number = phone_number;
        this.email = email;
        this.opening_hours = opening_hours;
        this.manager_name = manager_name;
        this.capacity = capacity;
        this.specialities_offered = specialities_offered;

    }

    public String getFacility_id() {
        return this.facility_id;
    }

    public String getFacility_name() {
        return this.facility_name;
    }

    public String getFacility_type() {
        return this.facility_type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public String getEmail() {
        return this.email;
    }

    public String getOpening_hours() {
        return this.opening_hours;
    }

    public String getManager_name() {
        return this.manager_name;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public String getSpecialities_offered() {
        return this.specialities_offered;
    }

}
