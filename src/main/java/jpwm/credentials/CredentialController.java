package jpwm.credentials;

import java.util.List;

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
import jpwm.user.UserRepository;

@RestController
// @RequestMapping("credentials")
public class CredentialController {
    @Autowired
    CredentialRepository credRepository;

    @CrossOrigin()
    @GetMapping("/all")
    public List<Credential> listUsers() {
        List<Credential> credentials = credRepository.findAll();
        return credentials;
    }

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{userId}/credentials/all")
    public ResponseEntity<List<Credential>> getAllCredentialsByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }

        List<Credential> comments = credRepository.findByUserId(userId);
        
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{userId}/credentials/{id}")
    public ResponseEntity<Credential> getCredentialsByUserId(
    @PathVariable(value = "userId") Long userId,
    @PathVariable(value = "id") Long id) {
        
        Credential credential = null;
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        else {
            credential = credRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id + " for User with id = " + userId));
        }
    return new ResponseEntity<>(credential, HttpStatus.OK);
    }

    @PostMapping("/{userId}/credentials")
    public ResponseEntity<Credential> createCredential(
    @PathVariable(value = "userId") Long userId,
    @RequestBody Credential credentialRequest) {

        Credential credential = userRepository.findById(userId).map(user -> {
            credentialRequest.setUser(user);
            return credRepository.save(credentialRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return new ResponseEntity<>(credential, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/credentials/{id}")
    public ResponseEntity<Credential> updateComment(
    @PathVariable(value = "userId") Long userId,
    @PathVariable("id") long id,
    @RequestBody Credential credentialRequest) {

        Credential credential = null;
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        else {
            credential = credRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

            credential.setUsername(credentialRequest.getUsername());
            credential.setPassword(credentialRequest.getPassword());
        }

        return new ResponseEntity<>(credRepository.save(credential), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/credentials/{id}")
    public ResponseEntity<HttpStatus> deleteComment(
    @PathVariable(value = "userId") Long userId,
    @PathVariable("id") long id) {

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        else {credRepository.deleteById(id);}

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
