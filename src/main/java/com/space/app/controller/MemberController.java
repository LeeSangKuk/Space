package com.space.app.controller;

import com.space.app.domain.UserDTO;
import com.space.app.service.MemberService;
import com.space.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class MemberController {
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
}
