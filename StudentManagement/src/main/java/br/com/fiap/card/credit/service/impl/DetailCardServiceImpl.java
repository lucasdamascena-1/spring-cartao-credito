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

import br.com.fiap.card.credit.dto.CreateDetailCardDTO;
import br.com.fiap.card.credit.dto.DeleteDetailCardDTO;
import br.com.fiap.card.credit.dto.DetailCardDTO;
import br.com.fiap.card.credit.entity.DetailCard;
import br.com.fiap.card.credit.repository.DetailCardRepository;
import br.com.fiap.card.credit.service.DetailCardService;

@Service
public class DetailCardServiceImpl implements DetailCardService {

	private DetailCardRepository detailCardRepository;

	public DetailCardServiceImpl(DetailCardRepository detailCardRepository) {
		this.detailCardRepository = detailCardRepository;
	}

	@Override
	public DetailCardDTO create(CreateDetailCardDTO createDetailCardDTO) {
		DetailCard detailCard = new DetailCard(createDetailCardDTO);
		return saveAndGetDetailCardDTO(detailCard);
	}

	private DetailCardDTO saveAndGetDetailCardDTO(DetailCard detailCard) {
		DetailCard savedDetailCard = detailCardRepository.save(detailCard);
		return new DetailCardDTO(savedDetailCard);
	}

	@Override
	public List<DetailCardDTO> findAll() {
		return detailCardRepository.findAll().stream().map(DetailCardDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<DetailCardDTO> findAllByStudentId(String studentId) {
		List<DetailCard> detailCardList;
		detailCardList = detailCardRepository.findByIdStudentId(studentId);
		return detailCardList.stream().map(DetailCardDTO::new).collect(Collectors.toList());
	}

	@Override
	public DetailCardDTO update(CreateDetailCardDTO createDetailCardDTO) {
		DetailCard detailCard = getDetailCard(createDetailCardDTO.getOperationId(), createDetailCardDTO.getStudentId());
		detailCard.setDescriptionOperation(createDetailCardDTO.getDescriptionOperation());
		detailCard.setDateOperation(createDetailCardDTO.getDateOperation());
		detailCard.setTypeOperation(createDetailCardDTO.getTypeOperation());
		detailCard.setValueOperation(createDetailCardDTO.getValueOperation());
		return saveAndGetDetailCardDTO(detailCard);
	}

	private DetailCard getDetailCard(int operartionId, String studentId) {
		DetailCard detailCard;
		detailCard = detailCardRepository.findByIdOperationIdAndIdStudentId(operartionId, studentId);
		if (detailCard == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "register not found");
		} else {
			return detailCard;
		}

	}

	@Override
	public void delete(DeleteDetailCardDTO deleteDetailCardDTO) {
		DetailCard detailCard = getDetailCard(deleteDetailCardDTO.getOperationId(), deleteDetailCardDTO.getStudentId());
		detailCardRepository.deleteByIdOperationIdAndIdStudentId(detailCard.getId().getOperationId(),
				detailCard.getId().getStudentId());
	}

	@Override
	public ResponseEntity<Object> downloadFile(String id) throws IOException {

		FileWriter filewriter = null;
		try {

			List<DetailCardDTO> txtDataList = findAllByStudentId(id);

			StringBuilder filecontent = new StringBuilder(
					"ID_OPERACAO;TIPO_OPERACAO;DESCRICAO_OPERACAO;DATA_OPERACAO;VALOR_OPERACAO\n");
			for (DetailCardDTO txt : txtDataList) {
				filecontent.append(txt.getOperationId()).append(";").append(txt.getTypeOperation()).append(";")
						.append(txt.getDescriptionOperation()).append(";").append(txt.getDateOperation()).append(";")
						.append(txt.getValueOperation()).append("\n");
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
