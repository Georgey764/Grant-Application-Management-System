package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sent_application")
public class SentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sent_application_id")
    private int sentApplicationId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "created_application_id")
    public CreatedApplication createdApplication;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sender")
    private Users sender;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "receiver")
    private Users receiver;

    @Column(name = "message")
    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "decision")
    private String decision;

    public SentApplication(CreatedApplication createdApplication, Users sender, Users receiver, String message, Resume resume) {
        this.createdApplication = createdApplication;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.resume = resume;
    }

    public SentApplication() {
    }

    public CreatedApplication getCreatedApplication() {
        return createdApplication;
    }

    public void setCreatedApplication(CreatedApplication createdApplication) {
        this.createdApplication = createdApplication;
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

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
