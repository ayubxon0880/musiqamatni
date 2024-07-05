package uz.musiqamatni.musiqamatni.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.musiqamatni.musiqamatni.dto.LyricDTO;
import uz.musiqamatni.musiqamatni.dto.response.CommonResponse;
import uz.musiqamatni.musiqamatni.exceptions.NotFoundException;
import uz.musiqamatni.musiqamatni.mapper.LyricMapper;
import uz.musiqamatni.musiqamatni.model.Lyric;
import uz.musiqamatni.musiqamatni.model.Singer;
import uz.musiqamatni.musiqamatni.model.User;
import uz.musiqamatni.musiqamatni.repository.LyricRepository;
import uz.musiqamatni.musiqamatni.repository.SingerRepository;
import uz.musiqamatni.musiqamatni.service.LyricService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LyricServiceImpl implements LyricService {
    private final LyricRepository lyricRepository;
    private final SingerRepository singerRepository;
    private final LyricMapper lyricMapper;

    @Override
    public ResponseEntity<LyricDTO> save(LyricDTO lyric) {
        Lyric entity = lyricMapper.toEntity(lyric);
        List<Singer> singers = new ArrayList<>();
        lyric.getSingers().forEach(singer -> {
            if (singerRepository.existsByName(singer.getName())){
                singers.add(singerRepository.findByName(singer.getName()).get());
            } else {
                Singer save = singerRepository.save(new Singer(null, singer.getName()));
                singers.add(save);
            }
        });
        entity.setSingers(singers);
        entity.setCreator((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Lyric save = lyricRepository.save(entity);
        return ResponseEntity.ok(lyricMapper.toDTO(save));
    }

    @Override
    public ResponseEntity<LyricDTO> getById(Long id) {
        Lyric lyric = lyricRepository.findById(id).orElseThrow(() -> new NotFoundException("Music lyric not found"));
        LyricDTO lyricDTO = lyricMapper.toDTO(lyric);
        return ResponseEntity.ok(lyricDTO);
    }

    @Override
    public ResponseEntity<?> getPagination(Integer size, Integer page, String search, Boolean sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("music").descending());

        Page<Lyric> data = lyricRepository.findAllByMusic(search, pageable);

        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.add("data", data.getContent().stream().map(lyricMapper::toDTO).toList());
        commonResponse.add("pageNumber", data.getPageable().getPageNumber());
        commonResponse.add("pageSize", data.getPageable().getPageSize());
        commonResponse.add("sort", data.getPageable().getSort().isSorted());
        commonResponse.add("first", data.isFirst());
        commonResponse.add("last", data.isLast());
        commonResponse.add("totalElements", data.getTotalElements());
        commonResponse.add("totalPages", data.getTotalPages());
        commonResponse.add("totalPages", data.getTotalPages());
        commonResponse.add("numberOfElements", data.getNumberOfElements());

        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<LyricDTO> update(Long id, LyricDTO lyricDTO) {
        Lyric lyric = lyricRepository.findById(id).orElseThrow(() -> new NotFoundException("Music not found"));
        Long userId = lyric.getCreator().getId();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principal.getId(), userId)){
            throw new NotFoundException("Music not found");
        }
        lyricDTO.setId(id);
        Lyric save = lyricRepository.save(lyricMapper.toEntity(lyricDTO));
        return ResponseEntity.ok(lyricMapper.toDTO(save));
    }
}
