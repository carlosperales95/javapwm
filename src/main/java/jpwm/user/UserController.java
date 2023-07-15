package jpwm.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpwm.exceptions.ResourceNotFoundException;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @CrossOrigin()
    @PostMapping("/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();
        
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PostMapping("/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                other.setLoggedIn(true);
                userRepository.save(other);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user) && other.isLoggedIn()) {
                other.setLoggedIn(false);
                userRepository.save(other);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/users/all")
    public List<User> listUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @CrossOrigin()
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        else {userRepository.deleteById(userId);}

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin()
    @PutMapping("/users/{userId}")
    public Status updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody User editedUser) {
        List<User> users = userRepository.findAll();
        if (!userRepository.existsById(userId)) {
            return Status.FAILURE;
        }

        for (User user : users) {
            if (user.getUsername().equals(editedUser.getUsername())) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        
        userRepository.save(editedUser);

        return Status.SUCCESS;
    }
}