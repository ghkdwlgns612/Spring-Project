package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Address;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @GetMapping("users/new")
    public String createUser(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "users/createForm";
    }
    //th:class=${#fields.hasError('name')? - : -} 이 부분은 name이란 필드가 bindingResult로 에러를 가지고 있으므로 실행되어진다.(색깔을 위해서)
    //th:if=${#fields.hasError('name') th:error="{*error}} 만약에 name필드가 에러를 가지면 객체가 가진 에러메세지를 출력하면 됩니다.(메세지를 위해서)
    //th:field는 id와 name을 만들어줌. bindingResult로 튕기지 않게 해줘서 계속 코드가 실행됨.
    @PostMapping("users/new")
    public String createUser(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors())
            return "users/createForm";
        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());
        User user = new User();
        user.setName(form.getName());
        user.setAddress(address);
        userService.join(user);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users",userService.findUsers());
        return "users/userList";
    }
}
