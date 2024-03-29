package Emerging.App.Backend.JSON_Objects;

public class AuthResponse {

    private String jwt;

    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
