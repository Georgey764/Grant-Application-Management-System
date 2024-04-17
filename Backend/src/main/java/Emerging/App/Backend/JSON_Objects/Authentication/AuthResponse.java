package Emerging.App.Backend.JSON_Objects.Authentication;

public class AuthResponse {

    private String jwt;

    private String authority;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
