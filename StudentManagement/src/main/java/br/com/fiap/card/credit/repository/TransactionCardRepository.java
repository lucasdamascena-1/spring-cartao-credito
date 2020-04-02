package br.com.fiap.card.credit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.card.credit.entity.TransactionCard;

@Repository
public interface TransactionCardRepository extends JpaRepository<TransactionCard,Integer> {
	
	List<TransactionCard> findAllByStudentId(String studentId);
	
}
