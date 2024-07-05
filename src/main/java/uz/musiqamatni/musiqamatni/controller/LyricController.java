package uz.musiqamatni.musiqamatni.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.musiqamatni.musiqamatni.dto.LyricDTO;
import uz.musiqamatni.musiqamatni.service.LyricService;


@RestController
@RequestMapping("/api/lyric")
@RequiredArgsConstructor
public class LyricController {
    private final LyricService lyricService;


    @PostMapping("/add")
    public ResponseEntity<LyricDTO> addLyric(@RequestBody LyricDTO lyric) {
        return lyricService.save(lyric);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LyricDTO> updateLyric(@PathVariable Long id, @RequestBody LyricDTO lyricDTO) {
        return lyricService.update(id,lyricDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LyricDTO> getLyricById(@PathVariable Long id) {
        return lyricService.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLyrics(@RequestParam("size") Integer size,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("search") String search,
                                          @RequestParam("sort") Boolean sort) {
        return lyricService.getPagination(size,page,search,sort);
    }
}