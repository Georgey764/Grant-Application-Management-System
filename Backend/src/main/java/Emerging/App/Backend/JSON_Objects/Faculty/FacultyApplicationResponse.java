package Emerging.App.Backend.JSON_Objects.Faculty;

public class FacultyApplicationResponse {

    private String firstName;
    private String lastName;
    private String department;
    private String cwid;
    private String gpa;
    private String classification;
    private String resumeLink;
    private String message;
    private String decision;
    private String statusMessage;

    public FacultyApplicationResponse(String firstName, String lastName, String department, String cwid, String gpa, String classification, String resumeLink, String message, String decision) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.cwid = cwid;
        this.gpa = gpa;
        this.classification = classification;
        this.resumeLink = resumeLink;
        this.message = message;
        this.decision = decision;
    }

    public FacultyApplicationResponse() {
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
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

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
