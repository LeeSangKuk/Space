package com.space.app.controller;

import com.space.app.domain.BoardDTO;
import com.space.app.domain.PageHandler;
import com.space.app.domain.SearchCondition;
import com.space.app.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardContorller {
    @Autowired
    BoardService bs;

    @GetMapping("/list") // 게시글 목록
    public String list(SearchCondition sc, Model m, HttpServletRequest request){
        if(!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();

        int totalCnt = bs.getSearchResultCnt(sc);
        PageHandler pageHandler = new PageHandler(totalCnt, sc);

        List<BoardDTO> list = bs.getSearchResultPage(sc);
        m.addAttribute("totalCnt", totalCnt);
        m.addAttribute("list", list);
        m.addAttribute("ph", pageHandler);

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 세션 객체에 아이디 정보가 없다면 false 반환.
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
    }

    @GetMapping("/write") // 글쓰기 페이지
    public String write(Model m){
        m.addAttribute("mode", "new");
        return "board";
    }

    @PostMapping("/write") // 게시글 작성
    public String write(BoardDTO boardDTO, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDTO.setWriter(writer);

        int rowCnt = bs.write(boardDTO);
        if(rowCnt!=1)        // 글쓰기가 실패한다면
            rattr.addFlashAttribute("msg", "WRT_ERR");

        rattr.addFlashAttribute("msg", "WRT_OK");
        return "redirect:/board/list";
    }

    @GetMapping("/read") // 게시글 읽기
    public String read(Integer bno, SearchCondition sc, Model m){
        BoardDTO boardDTO = bs.read(bno);
        m.addAttribute("boardDTO", boardDTO);
        m.addAttribute("sc", sc);
        return "board";
    }

    @PostMapping("/modify") // 게시글 수정
    public String modify(BoardDTO boardDTO, SearchCondition sc, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDTO.setWriter(writer);

        int rowCnt = bs.modify(boardDTO);
        if(rowCnt!=1){            // 수정이 실패한다면
            m.addAttribute("boardDTO", boardDTO);
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "board";
        }
        rattr.addFlashAttribute("sc", sc);
        rattr.addFlashAttribute("msg", "MOD_OK");
        return "redirect:/board/list";
    }

    @PostMapping("/remove") // 게시글 삭제
    public String remove(Integer bno, SearchCondition sc, RedirectAttributes rattr, HttpSession session){
        String writer = (String)session.getAttribute("id");
        int rowCnt = bs.remove(bno, writer);
        if(rowCnt!=1)            // 삭제가 싫패한다면
            rattr.addFlashAttribute("msg", "DEL_ERR");

        rattr.addFlashAttribute("sc", sc);
        rattr.addFlashAttribute("msg", "DEL_OK");
        return "redirect:/board/list";
    }



}// BoardController
