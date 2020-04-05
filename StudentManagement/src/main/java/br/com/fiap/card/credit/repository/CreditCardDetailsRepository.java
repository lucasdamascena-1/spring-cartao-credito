package br.com.fiap.card.credit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.card.credit.entity.CreditCardDetails;
import br.com.fiap.card.credit.entity.CreditCardDetailsId;

@Repository
@Transactional
public interface CreditCardDetailsRepository extends JpaRepository<CreditCardDetails, CreditCardDetailsId> {
	
	List<CreditCardDetails> findByIdStudentId(String studentId);

	CreditCardDetails findByIdOperationIdAndIdStudentId(int operationId, String studentId);

	void deleteByIdOperationIdAndIdStudentId(int operationId, String studentId);
}
