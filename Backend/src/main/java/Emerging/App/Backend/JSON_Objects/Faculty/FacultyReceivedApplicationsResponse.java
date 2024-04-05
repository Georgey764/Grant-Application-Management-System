package Emerging.App.Backend.JSON_Objects.Faculty;

public class FacultyReceivedApplicationsResponse {

    private int sentApplicationId;
    private String firstName;
    private String lastName;
    private String decision;
    private String statusMessage;

    public FacultyReceivedApplicationsResponse(int sentApplicationId, String firstName, String lastName, String decision) {
        this.sentApplicationId = sentApplicationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.decision = decision;
    }

    public FacultyReceivedApplicationsResponse() {
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getSentApplicationId() {
        return sentApplicationId;
    }

    public void setSentApplicationId(int sentApplicationId) {
        this.sentApplicationId = sentApplicationId;
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
}
