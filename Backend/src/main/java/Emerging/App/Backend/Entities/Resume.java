package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @Column(name = "resume_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resumeId;

    @Column(name = "resume_link")
    private String resumeLink;

    public Resume(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Resume() {
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }
}
