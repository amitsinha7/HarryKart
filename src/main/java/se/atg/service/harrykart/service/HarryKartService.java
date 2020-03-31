package se.atg.service.harrykart.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import se.atg.service.harrykart.model.HarryKart;
import se.atg.service.harrykart.model.Participant;
import se.atg.service.harrykart.model.RaceResult;

@Service
public class HarryKartService {

	@Value("${Track.Length}")
	private double trackLength;

	public ArrayList<RaceResult> getResults(HarryKart hk) {

		ArrayList<RaceResult> raceResults = new ArrayList<>();

		if (hk != null && hk.getStartList() != null) {
			for (Participant p : hk.getStartList()) {
				RaceResult raceResult = new RaceResult(0, p.getName(), trackLength / p.getBaseSpeed(), true);

				hk.getPowerUps().stream().forEach(loop -> loop.getLanes().stream()
						.filter(lane -> lane.getNumber() == p.getLane()).forEach(lane -> {
							int loopSpeed = p.getBaseSpeed() + lane.getValue();
							if (loopSpeed <= 0) {
								raceResult.setCompleted(false);
							} else {
								p.setBaseSpeed(p.getBaseSpeed() + lane.getValue());
								raceResult.setAverageSpeed(
										raceResult.getAverageSpeed() + (trackLength / p.getBaseSpeed()));
							}
						}));
				raceResults.add(raceResult);
			}
			raceResults.removeIf(raceResult -> raceResult.isCompleted() == false);
			Collections.sort(raceResults);
		}
		return raceResults;
	}
}
