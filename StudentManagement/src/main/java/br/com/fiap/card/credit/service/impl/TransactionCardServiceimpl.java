package br.com.fiap.card.credit.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;
import br.com.fiap.card.credit.dto.TransactionCardDTO;
import br.com.fiap.card.credit.entity.TransactionCard;
import br.com.fiap.card.credit.repository.TransactionCardRepository;
import br.com.fiap.card.credit.service.TransactionCardService;

@Service
public class TransactionCardServiceimpl implements TransactionCardService{
	
	private TransactionCardRepository transactionCardRepository;
	
	public TransactionCardServiceimpl(TransactionCardRepository transactionCardRepository) {
		this.transactionCardRepository = transactionCardRepository;
	}

	@Override
	public TransactionCardDTO findById(int id) {
		return saveAndGetTransactionCardDTO(getDetailCard(id));
	}
	@Override
	private TransactionCardDTO saveAndGetDetailCardDTO(TransactionCard transactionCard) {
		TransactionCard savedDetailCard = transactionCardRepository.save(transactionCard);
		return new TransactionCardDTO(savedDetailCard);
	}
	
	@Override
	private TransactionCard getDetailCard(int id) {
		return transactionCardRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public List<TransactionCardDTO> findAll() {
		return transactionCardRepository.findAll().stream().map(TransactionCardDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<TransactionCardDTO> findAllByStudentId(String studentId) {
		List<TransactionCard> detailCardList;
		detailCardList = transactionCardRepository.findAllByStudentId(studentId);
		return detailCardList
                .stream()
                .map(TransactionCardDTO::new)
                .collect(Collectors.toList());
	}
	
	@Override
	public ResponseEntity<Object> downloadFile(String id) throws IOException {
		
		FileWriter filewriter = null;
		try {

			List<TransactionCardDTO> txtDataList = findAllByStudentId(id);

			StringBuilder filecontent = new StringBuilder("ID_OPERACAO;TIPO_OPERACAO;DESCRICAO_OPERACAO;DATA_OPERACAO;VALOR_OPERACAO\n");
			for (TransactionCardDTO txt : txtDataList) {
				filecontent.append(txt.getOperationId())
						   .append(";")
						   .append(txt.getTypeOperation())
						   .append(";")
						   .append(txt.getDescriptionOperation())
						   .append(";")
						   .append(txt.getDateOperation())
						   .append(";")
						   .append(txt.getValueOperation())
						   .append("\n");
			}

			String filename = "C:\\Users\\ADM\\Desktop\\Java\\StudentsCredits\\StudentsCredits\\txtdata.txt";

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
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (filewriter != null)
				filewriter.close();
		}
		
	}

}
