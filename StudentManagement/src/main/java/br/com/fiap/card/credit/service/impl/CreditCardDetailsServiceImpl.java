package br.com.fiap.card.credit.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreateCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.DeleteCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.CreditCardDetailsDTO;
import br.com.fiap.card.credit.entity.CreditCardDetails;
import br.com.fiap.card.credit.repository.CreditCardDetailsRepository;
import br.com.fiap.card.credit.service.CreditCardDetailsService;

@Service
public class CreditCardDetailsServiceImpl implements CreditCardDetailsService {

	private CreditCardDetailsRepository detailCardRepository;

	public CreditCardDetailsServiceImpl(CreditCardDetailsRepository detailCardRepository) {
		this.detailCardRepository = detailCardRepository;
	}

	@Override
	public CreditCardDetailsDTO create(CreateCreditCardDetailsDTO createDetailCardDTO) {
		CreditCardDetails detailCard = new CreditCardDetails(createDetailCardDTO);
		return saveAndGetDetailCardDTO(detailCard);
	}

	private CreditCardDetailsDTO saveAndGetDetailCardDTO(CreditCardDetails detailCard) {
		CreditCardDetails savedDetailCard = detailCardRepository.save(detailCard);
		return new CreditCardDetailsDTO(savedDetailCard);
	}

	@Override
	public List<CreditCardDetailsDTO> findAll() {
		return detailCardRepository.findAll().stream().map(CreditCardDetailsDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<CreditCardDetailsDTO> findAllByStudentId(String studentId) {
		List<CreditCardDetails> detailCardList;
		detailCardList = detailCardRepository.findByIdStudentId(studentId);
		return detailCardList.stream().map(CreditCardDetailsDTO::new).collect(Collectors.toList());
	}

	@Override
	public CreditCardDetailsDTO update(CreateCreditCardDetailsDTO createDetailCardDTO) {
		CreditCardDetails detailCard = getDetailCard(createDetailCardDTO.getOperationId(),
				createDetailCardDTO.getStudentId());
		detailCard.setDescriptionOperation(createDetailCardDTO.getDescriptionOperation());
		detailCard.setDateOperation(StringToLocalDate(createDetailCardDTO.getDateOperation()));
		detailCard.setTypeOperation(createDetailCardDTO.getTypeOperation());
		detailCard.setValueOperation(createDetailCardDTO.getValueOperation());
		return saveAndGetDetailCardDTO(detailCard);
	}

	private CreditCardDetails getDetailCard(int operartionId, String studentId) {
		CreditCardDetails detailCard;
		detailCard = detailCardRepository.findByIdOperationIdAndIdStudentId(operartionId, studentId);
		if (detailCard == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Register Not Found");
		} else {
			return detailCard;
		}
	}

	@Override
	public void delete(DeleteCreditCardDetailsDTO deleteDetailCardDTO) {
		CreditCardDetails detailCard = getDetailCard(deleteDetailCardDTO.getOperationId(),
				deleteDetailCardDTO.getStudentId());
		detailCardRepository.deleteByIdOperationIdAndIdStudentId(detailCard.getId().getOperationId(),
				detailCard.getId().getStudentId());
	}

	@Override
	public ResponseEntity<Object> downloadFile(String id) throws IOException {

		FileWriter filewriter = null;
		try {

			List<CreditCardDetailsDTO> txtDataList = findAllByStudentId(id);

			StringBuilder filecontent = new StringBuilder(
					"ID_OPERACAO;TIPO_OPERACAO;DESCRICAO_OPERACAO;DATA_OPERACAO;VALOR_OPERACAO\n");
			for (CreditCardDetailsDTO txt : txtDataList) {
				filecontent.append(txt.getOperationId()).append(";").append(txt.getTypeOperation()).append(";")
						.append(txt.getDescriptionOperation()).append(";").append(txt.getDateOperation()).append(";")
						.append(txt.getValueOperation()).append("\n");
			}

			String filename = String.format("CreditCardUser_%s.txt", id);

			filewriter = new FileWriter(filename);
			filewriter.write(filecontent.toString());
			filewriter.flush();

			File file = new File(filename);

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/txt")).body(resource);
			return responseEntity;

		} catch (Exception e) {
			return new ResponseEntity<>("Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (filewriter != null)
				filewriter.close();
		}
	}

	private LocalDate StringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);

		return localDate;
	}
}
