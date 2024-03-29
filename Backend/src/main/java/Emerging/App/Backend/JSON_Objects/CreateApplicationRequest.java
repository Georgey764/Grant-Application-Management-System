package Emerging.App.Backend.JSON_Objects;

public class CreateApplicationRequest {

    private String username;
    private String projectName;
    private String projectDescription;

    public CreateApplicationRequest(String username, String projectName, String projectDescription) {
        this.username = username;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public CreateApplicationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
