package CapstoneDesign.Backendserver.validator;

import CapstoneDesign.Backendserver.domain.User;
import CapstoneDesign.Backendserver.domain.UserLogin;
import CapstoneDesign.Backendserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegisterValidator implements Validator {
    private final UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserLogin userLogin = (UserLogin) target;
        if(userService.findUser(userLogin.getId()).equals("ID_NOT_FOUND")){
            errors.rejectValue("id","required");
            return;
        }
        if(
           !((User)userService.findUser(userLogin.getId())).getPassword().
                   equals(userLogin.getPw())){
            errors.rejectValue("pw","required");
        }
    }


}
