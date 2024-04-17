package Emerging.App.Backend.JSON_Objects.Students;

public class Details {

    private String firstName;
    private String lastName;
    private String major;
    private String CWID;

    public Details(String firstName, String lastName, String major, String CWID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.CWID = CWID;
    }

    public Details() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCWID() {
        return CWID;
    }

    public void setCWID(String CWID) {
        this.CWID = CWID;
    }
}
