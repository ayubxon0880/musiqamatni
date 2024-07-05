package uz.musiqamatni.musiqamatni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.musiqamatni.musiqamatni.model.Role;
import uz.musiqamatni.musiqamatni.model.User;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
