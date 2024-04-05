package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class MyUserDetails {
    @Id
    @Column(name = "user_id")
    private int userDetailsId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    private Authorities authority;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "CWID", nullable = false)
    private String cwid;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gpa")
    private String gpa;

    @Column(name = "classification")
    private String classification;

    public MyUserDetails(Authorities authorities, String email, String department, String cwid, String firstName, String lastName) {
        this.authority = authorities;
        this.email = email;
        this.department = department;
        this.cwid = cwid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MyUserDetails(int userDetailsId, Users user, Authorities authority, String email, String department, String cwid, String firstName, String lastName, String gpa, String classification) {
        this.userDetailsId = userDetailsId;
        this.user = user;
        this.authority = authority;
        this.email = email;
        this.department = department;
        this.cwid = cwid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.classification = classification;
    }

    public MyUserDetails() {
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(int userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Authorities getAuthority() {
        return authority;
    }

    public void setAuthority(Authorities authority) {
        this.authority = authority;
    }

    public Authorities getAuthorities() {
        return authority;
    }

    public void setAuthorities(Authorities authorities) {
        this.authority = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
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

    @Override
    public String toString() {
        return "UserDetails{" +
                ", authorities=" + authority +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", cwid='" + cwid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
