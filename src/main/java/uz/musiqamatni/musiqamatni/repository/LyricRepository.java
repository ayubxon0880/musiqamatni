package uz.musiqamatni.musiqamatni.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import uz.musiqamatni.musiqamatni.model.Lyric;

import java.util.List;

@RestController
public interface LyricRepository extends JpaRepository<Lyric, Long> {
    Page<Lyric> findAllByMusic(String music, Pageable pageable);
}
