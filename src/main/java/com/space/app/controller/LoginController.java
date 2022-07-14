package com.space.app.controller;

import com.space.app.domain.UserDTO;
import com.space.app.service.MemberService;
import com.space.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MemberService ms;

    @InitBinder
    public void toDate(WebDataBinder binder){
        ConversionService conversionService = binder.getConversionService();
        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
    }

    @GetMapping("/join") // 회원가입 페이지
    public String add() { return "register"; }

    @PostMapping("/join") // 회원가입 버튼을 눌렀을 때
    public String save(@Valid UserDTO user, BindingResult result, RedirectAttributes rattr) {
        if(result.hasErrors()) { return "register"; }

        try {
            int check = ms.register(user);
            rattr.addFlashAttribute("msg", "JOIN_OK");

            if (check != 1)
                throw new Exception("Join Failed");

            return "redirect:/login/login";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "JOIN_ERR");
            return "redirect:/login/join";
        }
    }

    @GetMapping("/login") // 로그인 페이지
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login") // 로그인 버튼을 눌렀을 때
    public String login(String id, String pw, String toURL, boolean rememberId, HttpServletRequest request,
                        HttpServletResponse response, RedirectAttributes rattr) {
        
        // 아이디와 비밀번호가 데이터 베이스와 일치하는지 검증
        if(!loginCheck(id, pw)) {
            rattr.addFlashAttribute("msg", "INFO_ERR");
            return "redirect:/login/login";
        }

        // 세션 객체에 아이디 정보를 담기
        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        // 체크박스가 true일 경우 쿠키를 생성하고, 아이디 정보를 저장.
        if(rememberId){
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        } else {
            // 체크박스가 false일 경우 쿠키의 유효기간을 0으로 변경(폐기).
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pw) {
        UserDTO user = null;

        try {
            user = ms.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return user!=null && user.getPw().equals(pw);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }








} // MemberController