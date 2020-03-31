package se.atg.service.harrykart.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "loop")
public class Loop implements Serializable {

	@JacksonXmlProperty(isAttribute = true)
	int number;

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "lane")
	ArrayList<Lane> lanes;

	public Loop() {
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(ArrayList<Lane> lanes) {
		this.lanes = lanes;
	}

}
