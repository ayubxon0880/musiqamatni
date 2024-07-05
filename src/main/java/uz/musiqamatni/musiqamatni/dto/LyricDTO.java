package uz.musiqamatni.musiqamatni.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LyricDTO {
    private Long id;
    private String music;
    private String text;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<SingerDTO> singers;
    @NotNull
    private UserDTO creator;
}
