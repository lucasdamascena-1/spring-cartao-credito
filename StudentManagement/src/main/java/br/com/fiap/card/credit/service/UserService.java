package br.com.fiap.card.credit.service;

import br.com.fiap.card.credit.dto.AuthDTO;
import br.com.fiap.card.credit.dto.CreateUserDTO;
import br.com.fiap.card.credit.dto.JwtDTO;
import br.com.fiap.card.credit.dto.UserDTO;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO);

    JwtDTO login(AuthDTO authDTO);
}
