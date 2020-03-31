package se.atg.service.harrykart.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.atg.service.harrykart.constant.HKConstants;
import se.atg.service.harrykart.exception.HKException;
import se.atg.service.harrykart.helper.XMLValidatorDeserialize;
import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.RaceResult;
import se.atg.service.harrykart.response.ErrorInfoDTO;
import se.atg.service.harrykart.response.PlayResponseDTO;
import se.atg.service.harrykart.response.ResponeMapper;
import se.atg.service.harrykart.service.HarryKartService;

@RestController
@RequestMapping("/api")
public class HarryKartController {

	@Autowired
	XMLValidatorDeserialize validatorDeserialize;
	@Autowired
	HarryKartService harryKartService;
	@Autowired
	ResponeMapper responeMapper;

	@RequestMapping(method = RequestMethod.POST, path = "/play", consumes = "application/xml", produces = "application/json")
	public ResponseEntity<PlayResponseDTO> playHarryKart(@RequestBody String inputXML, BindingResult validationErrors) {

		PlayResponseDTO playResponseDTO = new PlayResponseDTO();
		HarryKart hk = null;
		try {

			hk = validatorDeserialize.validateAndDeserializeXML(inputXML, validationErrors);

			if (validationErrors.hasErrors()) {
				playResponseDTO.setErrorInfoDTO(getErrorInfoDTO("110002"));
				return new ResponseEntity<PlayResponseDTO>(playResponseDTO, HttpStatus.BAD_REQUEST);
			} else {
				ArrayList<RaceResult> results = harryKartService.getResults(hk);
				if (results != null) {
					playResponseDTO.setRanking(responeMapper.getTopThreePostions(results));
				} else {
					playResponseDTO.setErrorInfoDTO(getErrorInfoDTO("110001"));
					return new ResponseEntity<PlayResponseDTO>(playResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				return new ResponseEntity<PlayResponseDTO>(playResponseDTO, HttpStatus.OK);
			}
		} catch (HKException e) {
			System.out.println("HKException: " + e.getMessage());
			playResponseDTO.setErrorInfoDTO(getErrorInfoDTO("110001"));
			return new ResponseEntity<PlayResponseDTO>(playResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private ErrorInfoDTO getErrorInfoDTO(String errorCode) {
		ErrorInfoDTO errorInfo = new ErrorInfoDTO();
		errorInfo.setErrorCode(errorCode);
		errorInfo.setErrorMessage(HKConstants.errorStore.get(errorCode));
		return errorInfo;
	}
}
