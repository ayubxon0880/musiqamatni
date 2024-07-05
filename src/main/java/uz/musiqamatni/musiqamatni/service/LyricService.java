package uz.musiqamatni.musiqamatni.service;

import org.springframework.http.ResponseEntity;
import uz.musiqamatni.musiqamatni.dto.LyricDTO;

public interface LyricService {
    ResponseEntity<LyricDTO> save(LyricDTO lyric);

    ResponseEntity<LyricDTO> getById(Long id);

    ResponseEntity<?> getPagination(Integer size, Integer page, String search, Boolean sort);

    ResponseEntity<LyricDTO> update(Long id, LyricDTO lyricDTO);
}
