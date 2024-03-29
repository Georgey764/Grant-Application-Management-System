package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="created_application")
public class CreatedApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdApplication")
    private List<SentApplication> sentApplication;

    @ManyToOne
    @JoinColumn(name = "creator")
    private Users user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public CreatedApplication(Users user, String name, String description) {
        this.user = user;
        this.name = name;
        this.description = description;
    }

    public CreatedApplication() {
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public List<SentApplication> getSentApplication() {
        return sentApplication;
    }

    public void setSentApplication(List<SentApplication> sentApplication) {
        this.sentApplication = sentApplication;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
}
