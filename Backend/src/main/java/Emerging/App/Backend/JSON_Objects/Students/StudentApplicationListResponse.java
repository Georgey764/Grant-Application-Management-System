package Emerging.App.Backend.JSON_Objects.Students;

public class StudentApplicationListResponse {
    private String professorName;
    private String department;
    private String projectName;
    private String status;
    private String decision;

    public StudentApplicationListResponse(String professorName, String department, String projectName, String status) {
        this.professorName = professorName;
        this.department = department;
        this.projectName = projectName;
        this.status = status;
    }

    public StudentApplicationListResponse() {
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
