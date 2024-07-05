package uz.musiqamatni.musiqamatni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.musiqamatni.musiqamatni.model.EmailCode;
import uz.musiqamatni.musiqamatni.model.User;

import java.util.Optional;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCode, Long> {
    Optional<EmailCode> findByUserId(Long userId);
}
