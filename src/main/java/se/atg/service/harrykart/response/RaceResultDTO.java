package se.atg.service.harrykart.response;

public class RaceResultDTO {

	int position;
	String horse;
	
	public RaceResultDTO(int position, String horse) {
		super();
		this.position = position;
		this.horse = horse;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getHorse() {
		return horse;
	}

	public void setHorse(String horse) {
		this.horse = horse;
	}

}
