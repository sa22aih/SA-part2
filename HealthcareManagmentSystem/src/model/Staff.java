package model;

public class Staff {

    private String staffId;
    private String firstName;
    private String lastName;
    private String role;
    private String department;
    private String facilityId;
    private String phoneNumber;
    private String email;
    private String employmentStatus;
    private String startDate;
    private String lineManager;
    private String accessLevel;

    public Staff(String staffId, String firstName, String lastName, String role,
            String department, String facilityId, String phoneNumber,
            String email, String employmentStatus, String startDate, String lineManager,
            String accessLevel) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department = department;
        this.facilityId = facilityId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }

    public String getId() {
        return staffId;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getrole() {
        return role;
    }

    public String getdepartment() {
        return department;
    }

    public String getfacilityId() {
        return facilityId;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public String getemail() {
        return email;
    }

    public String getemploymentStatus() {
        return employmentStatus;
    }

    public String getstartDate() {
        return startDate;
    }

    public String getlineManager() {
        return lineManager;
    }

    public String getaccessLevel() {
        return accessLevel;
    }

   

}
