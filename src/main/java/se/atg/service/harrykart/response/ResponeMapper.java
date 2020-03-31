package se.atg.service.harrykart.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import se.atg.service.harrykart.model.RaceResult;

@Component
public class ResponeMapper {

	public ArrayList<RaceResultDTO> getTopThreePostions(ArrayList<RaceResult> results) {

		ArrayList<RaceResultDTO> resultresponse = new ArrayList<RaceResultDTO>();

		int position = 1;
		RaceResultDTO result = null;
		result = new RaceResultDTO(position, results.get(0).getHorse());
		resultresponse.add(result);
		for (int i = 1; i < results.size(); i++) {
			if (results.get(i).getAverageSpeed() == results.get(i - 1).getAverageSpeed()) {
				result = new RaceResultDTO(position, results.get(i).getHorse());
				resultresponse.add(result);
			} else {
				result = new RaceResultDTO(++position, results.get(i).getHorse());
				resultresponse.add(result);
			}
			if (position == 3) {
				break;
			}
		}
		return resultresponse;
	}

}
