package jpwm.credentials;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    List<Credential> findByUserId(Long userId);
}

