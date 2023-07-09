package jpwm.user;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jpwm.credentials.Credential;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue
    private long id;
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private boolean loggedIn;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Credential> credentials;

    public User() {
    }

    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;

        this.credentials = null;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void addCredential(Credential cred) {
        this.credentials.add(cred);
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, loggedIn);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn + '\'' +
                ", credentials=" + credentials.size() +
                '}';
    }
}
