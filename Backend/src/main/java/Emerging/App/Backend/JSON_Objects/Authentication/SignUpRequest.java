package Emerging.App.Backend.JSON_Objects.Authentication;

public class SignUpRequest {
    private int authorityId;
    private String email;
    private String department;
    private String cwid;
    private String firstName;
    private String lastName;
    private String password;

    public SignUpRequest() {
    }

    public SignUpRequest(int authorityId, String email, String department, String cwid, String firstName, String lastName, String password) {
        this.authorityId = authorityId;
        this.email = email;
        this.department = department;
        this.cwid = cwid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                ", authorityId=" + authorityId +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", cwid='" + cwid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
