package com.space.app.controller;

import com.space.app.domain.BoardDTO;
import com.space.app.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardContorller {
    @Autowired
    BoardService bs;

    @GetMapping("/list")
    public String list(Model m, HttpServletRequest request){
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();

        List<BoardDTO> list = bs.getList();
        m.addAttribute("list", list);

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 세션 객체에 아이디 정보가 없다면 false 반환.
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
    }

}// BoardController
