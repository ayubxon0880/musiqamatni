package uz.musiqamatni.musiqamatni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lyric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String music;
    private String text;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @ManyToMany
    private List<Singer> singers;
    @ManyToOne
    private User creator;
}
