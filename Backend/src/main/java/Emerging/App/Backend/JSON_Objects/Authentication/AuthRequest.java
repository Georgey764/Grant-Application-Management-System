package Emerging.App.Backend.JSON_Objects.Authentication;

public class AuthRequest {

    private String email;
    private String password;


    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequests{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
