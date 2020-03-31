package se.atg.service.harrykart.response;

import java.util.ArrayList;

public class PlayResponseDTO {

	private ErrorInfoDTO errorInfoDTO;

	ArrayList<RaceResultDTO> ranking;

	public ErrorInfoDTO getErrorInfoDTO() {
		return errorInfoDTO;
	}

	public ArrayList<RaceResultDTO> getRanking() {
		return ranking;
	}

	public void setRanking(ArrayList<RaceResultDTO> ranking) {
		this.ranking = ranking;
	}

	public void setErrorInfoDTO(ErrorInfoDTO errorInfoDTO) {
		this.errorInfoDTO = errorInfoDTO;
	}

}
