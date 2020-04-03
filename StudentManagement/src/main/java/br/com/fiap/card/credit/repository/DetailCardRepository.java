package br.com.fiap.card.credit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.card.credit.entity.DetailCard;
import br.com.fiap.card.credit.entity.DetailCardId;

@Repository
@Transactional
public interface DetailCardRepository extends JpaRepository<DetailCard,DetailCardId> {
	
	//List<DetailCard> findAllByStudentId(String studentId);
	List<DetailCard> findByIdStudentId(String studentId);
	DetailCard findByIdOperationIdAndIdStudentId(int operationId, String studentId);
	void deleteByIdOperationIdAndIdStudentId(int operationId,String studentId);
}
