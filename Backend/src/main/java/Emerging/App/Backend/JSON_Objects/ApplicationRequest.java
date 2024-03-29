package Emerging.App.Backend.JSON_Objects;

public class ApplicationRequest {

    private int applicationId;
    private String sender;
    private String receiver;
    private String message;
    private String resumeLink;

    public ApplicationRequest(int applicationId, String sender, String receiver, String message, String resumeLink) {
        this.applicationId = applicationId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.resumeLink = resumeLink;
    }

    public ApplicationRequest() {
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }
}
