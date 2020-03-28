package br.com.fiap.card.credit.repository;

/*import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;*/
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.card.credit.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

	/*@Query("from CreditCard s where s.Student =: studentName")
	List<CreditCard> findByFirstLetter(String studentName);*/
}
