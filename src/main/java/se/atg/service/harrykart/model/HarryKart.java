package se.atg.service.harrykart.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "harryKart")
public class HarryKart implements Serializable {

	int numberOfLoops;
	ArrayList<Participant> startList;
	ArrayList<Loop> powerUps;

	public HarryKart() {
	}

	public int getNumberOfLoops() {
		return numberOfLoops;
	}

	public void setNumberOfLoops(int numberOfLoops) {
		this.numberOfLoops = numberOfLoops;
	}

	public ArrayList<Participant> getStartList() {
		return startList;
	}

	public void setStartList(ArrayList<Participant> startList) {
		this.startList = startList;
	}

	public ArrayList<Loop> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(ArrayList<Loop> powerUps) {
		this.powerUps = powerUps;
	}

}
