package br.com.fiap.card.credit.repository;

import br.com.fiap.card.credit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUsername(String userName);

}
