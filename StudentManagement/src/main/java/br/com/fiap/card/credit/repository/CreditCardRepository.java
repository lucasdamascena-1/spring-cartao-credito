package br.com.fiap.card.credit.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.card.credit.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
	
	/*@Query("from Student s where s.studentName =: name")
	List<CreditCard> findAllByInitial(String name);*/
}
