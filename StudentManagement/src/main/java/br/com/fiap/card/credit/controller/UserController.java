package br.com.fiap.card.credit.controller;

import br.com.fiap.card.credit.dto.AuthDTO;
import br.com.fiap.card.credit.dto.CreateUserDTO;
import br.com.fiap.card.credit.dto.JwtDTO;
import br.com.fiap.card.credit.dto.UserDTO;
import br.com.fiap.card.credit.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDTO create(@RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @PostMapping("/login")
    public JwtDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }

}
