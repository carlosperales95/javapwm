package jpwm.credentials;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jpwm.user.User;

@Entity
@Table(name = "credentials")
public class Credential {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    @NotBlank
    private String password;

    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;
    
    public Credential() {
    }

    public Credential(String username, @NotBlank String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credential)) return false;
        Credential cred = (Credential) o;
        return Objects.equals(username, cred.username) &&
                Objects.equals(password, cred.password) &&
                Objects.equals(user, cred.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, user);
    }

    @Override
    public String toString() {
        return "Credential {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_id=" + user.getId() +
                '}';
    }
}