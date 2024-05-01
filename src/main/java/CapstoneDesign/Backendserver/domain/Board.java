package CapstoneDesign.Backendserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @NotBlank
    private String boardWriter;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContents;

    private int boardHits;

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }
}
