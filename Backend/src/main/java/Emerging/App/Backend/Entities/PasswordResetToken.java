package Emerging.App.Backend.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private int tokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public PasswordResetToken(int tokenId, String token, int userId, LocalDate expirationDate) {
        this.tokenId = tokenId;
        this.token = token;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public PasswordResetToken() {
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
