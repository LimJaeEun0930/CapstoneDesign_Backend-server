package CapstoneDesign.Backendserver.repository;

import CapstoneDesign.Backendserver.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Board boardDTO) {
        em.persist(boardDTO);
    }



    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public void updateHits(Long setid) {
        em.createQuery("update Board b set b.boardHits =b.boardHits+1 where b.id= :setid")
                .setParameter("setid", setid).executeUpdate();
    }

    public Board findById(Long id) {

        Optional<Board> optionalBoard = Optional.ofNullable(em.find(Board.class, id));
        if (optionalBoard.isPresent()) {
            return optionalBoard.get();
        } else {
            return null;
        }
    }

    public void updateBoard(Board board, Long boardId) {
        Board boardUpdate = em.find(Board.class, boardId);
        boardUpdate.setBoardTitle(board.getBoardTitle());
        boardUpdate.setBoardContents(board.getBoardContents());

    }
}
