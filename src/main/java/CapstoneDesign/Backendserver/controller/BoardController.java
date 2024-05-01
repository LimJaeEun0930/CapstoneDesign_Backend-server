package CapstoneDesign.Backendserver.controller;

import CapstoneDesign.Backendserver.SessionConst;
import CapstoneDesign.Backendserver.domain.Board;
import CapstoneDesign.Backendserver.domain.User;
import CapstoneDesign.Backendserver.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String writePage(@ModelAttribute("board") Board board, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
            log.info("user 들어옴?1{}", user.toString());
            model.addAttribute("user", user);
//            model.addAttribute("board", board);이코드 없어도 @ModelAttribute에 담김.
            return "board/writeBoard";
        }
        else
        {
            return  "redirect:/";
        }

    }

    @PostMapping("/board/write") //board의 필드들이 들어오지 않는 문제 있었는데,Board에 setter설정해주니 됨
    public String save(@ModelAttribute Board board, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        log.info("user 들어옴?{}", user.toString());
        board.setBoardWriter(user.getId()); //유저아이디 넣어야 한다.
        log.info("board = {}", board);

        boardService.save(board);
        return "redirect:/boardList";
    }

    @GetMapping("/board/edit/{board_id}") // PathVariable로 받아도 모델에 추가가 되는것 같다
    public String boardEdit(@PathVariable Long board_id, Model model,HttpServletRequest request) {
        Board board = boardService.findById(board_id);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        model.addAttribute("user", user);
        model.addAttribute("boardUpdate", board);
        return "board/boardEdit";
    }

    @PostMapping("/board/edit/{board_id}")
    public String updateDetail(@PathVariable Long board_id, @ModelAttribute Board board) {
        boardService.update(board,board_id);//error. dirty checking으로 메서드 하나 만들어야할듯

        return "redirect:/board/" + board_id;
    }



    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Long id, Model model,HttpServletRequest request) {
        boardService.updateHits(id);
        Board board = boardService.findById(id);

        Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
        if (sessionOptional.isPresent())
        {
            log.info("세션존재");
            HttpSession session = sessionOptional.get();

            if ((//로그인유저와 게시글의 유저가 같다면
                    (User) session.getAttribute(SessionConst.LOGIN_USER)).getId()
                    .equals(board.getBoardWriter()))
            {
                model.addAttribute("user", session.getAttribute(SessionConst.LOGIN_USER));
            }
            else {
                log.info("세션은있는데..");
            }
        }
        model.addAttribute("board", board);
        return "board/boardDetail";
    }

}
