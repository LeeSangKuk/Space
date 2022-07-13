package com.space.app.validator;

import com.space.app.domain.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        System.out.println("LocalValidator.validate() is called");

//        if(!target instanceof UserDTO) return; // 위에서 검증을 했기 때문에 생략.
        UserDTO user = (UserDTO)target;

        if(user.getId()==null || "".equals(user.getId().trim()) || user.getId().length() < 8 || user.getId().length() > 16 || !isId(user.getId())){
            errors.rejectValue("id", "invalidLength.id", new String[]{"8", "16"}, null);
        }
        if(user.getPw()==null || "".equals(user.getPw().trim()) || user.getPw().length() < 8 ){
            errors.rejectValue("pw", "invalidLength.pw", new String[]{"8"}, null);
        }
        if(user.getName()==null || "".equals(user.getName().trim()) || user.getName().length() < 2 ){
            errors.rejectValue("name", "invalidLength.name", new String[]{"2"}, null);
        }
        if(user.getEmail()==null || "".equals(user.getEmail().trim()) || !isEmail(user.getEmail())){
            errors.rejectValue("email", "invalidLength.email");
        }
        if(user.getBirth()==null || "".equals(user.getBirth())){
            errors.rejectValue("birth", "invalidLength.birth");
        }

    } // validate();

    private boolean isId(String str){
        return Pattern.matches("^[A-Za-z]+[A-Za-z0-9]{4,15}$", str);
    }

    private boolean isEmail(String str){ // 이메일 유효성 검사 메서드
        return Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", str);
    }

}