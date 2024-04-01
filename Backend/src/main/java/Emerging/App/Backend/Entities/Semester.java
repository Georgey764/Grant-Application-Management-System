package Emerging.App.Backend.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "semester")
public class Semester {

    @Id
    @Column(name = "semester_id")
    private int semesterId;

    @Column(name = "semester_name")
    private String semesterName;

    public Semester(int semesterId, String semesterName) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
    }

    public Semester() {
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
}
