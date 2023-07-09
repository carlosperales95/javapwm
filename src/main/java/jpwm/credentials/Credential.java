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
    @NotBlank
    private String name;

    private String description;

    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;
    
    public Credential() {
    }

    public Credential(
        String username,
        @NotBlank String password,
        @NotBlank String name,
        String description,
        User user) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this. name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this. description = description;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credential)) return false;
        Credential cred = (Credential) o;
        return Objects.equals(name, cred.name) &&
                Objects.equals(username, cred.username) &&
                Objects.equals(password, cred.password) &&
                Objects.equals(user, cred.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, description, user);
    }

    @Override
    public String toString() {
        return "Credential {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user + '\'' +
                ", name=" + name + '\'' +
                ", description=" + description +
                '}';
    }
}