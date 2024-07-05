package uz.musiqamatni.musiqamatni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.musiqamatni.musiqamatni.model.Singer;

import java.util.Optional;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    boolean existsByName(String name);
    Optional<Singer> findByName(String name);
}
