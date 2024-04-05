package Emerging.App.Backend.JSON_Objects.Faculty;

public class FacultyProjectResponse {

    private int applicationId;
    private String username;
    private String name;
    private String description;
    private String statusMessage;

    public FacultyProjectResponse(int applicationId, String username, String name, String description) {
        this.applicationId = applicationId;
        this.username = username;
        this.name = name;
        this.description = description;
    }

    public FacultyProjectResponse() {
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
