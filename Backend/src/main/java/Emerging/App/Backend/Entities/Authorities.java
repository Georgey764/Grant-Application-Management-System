package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "authority_id")
    private int authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }
}
