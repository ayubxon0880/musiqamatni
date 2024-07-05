package uz.musiqamatni.musiqamatni.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.musiqamatni.musiqamatni.dto.LyricDTO;
import uz.musiqamatni.musiqamatni.dto.SingerDTO;
import uz.musiqamatni.musiqamatni.exceptions.NotFoundException;
import uz.musiqamatni.musiqamatni.model.Lyric;
import uz.musiqamatni.musiqamatni.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LyricMapper {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Lyric toEntity(LyricDTO lyricDTO) {
        if (lyricDTO == null) return null;
        return new Lyric(
                lyricDTO.getId(),
                lyricDTO.getMusic(),
                lyricDTO.getText(),
                lyricDTO.getCreatedDate(),
                lyricDTO.getUpdatedDate(),
                null,
                userRepository.findById(lyricDTO.getId()).orElseThrow(() -> new NotFoundException("User not found"))
        );
    }

    public LyricDTO toDTO(Lyric save) {
        if (save == null) return null;
        return new LyricDTO(
                save.getId(),
                save.getMusic(),
                save.getText(),
                save.getCreatedDate(),
                save.getUpdatedDate(),
                save.getSingers().stream().map(singer -> new SingerDTO(singer.getId(), singer.getName())).toList(),
                userMapper.toDTO(save.getCreator())
        );
    }
}
