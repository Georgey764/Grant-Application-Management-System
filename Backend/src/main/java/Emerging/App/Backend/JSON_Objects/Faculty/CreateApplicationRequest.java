package Emerging.App.Backend.JSON_Objects.Faculty;

public class CreateApplicationRequest {
    private String projectName;
    private String projectDescription;

    public CreateApplicationRequest(String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public CreateApplicationRequest() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
