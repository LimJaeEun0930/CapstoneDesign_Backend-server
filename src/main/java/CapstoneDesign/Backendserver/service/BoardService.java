package CapstoneDesign.Backendserver.service;

import CapstoneDesign.Backendserver.domain.Board;
import CapstoneDesign.Backendserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(Board boardDTO) {
        boardRepository.save(boardDTO);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(Board board,Long board_id) {
        boardRepository.updateBoard(board, board_id);
    }
}
