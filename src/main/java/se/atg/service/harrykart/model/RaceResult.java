package se.atg.service.harrykart.model;

public class RaceResult implements Comparable<RaceResult> {

	int position;
	String horse;
	double averageSpeed;
	boolean isCompleted;

	public RaceResult(int position, String horse) {
		super();
		this.position = position;
		this.horse = horse;
	}
	
	public RaceResult(int position, String horse, double averageSpeed, boolean isCompleted) {
		super();
		this.position = position;
		this.horse = horse;
		this.averageSpeed = averageSpeed;
		this.isCompleted = isCompleted;
	}

	@Override
	public int compareTo(RaceResult o) {
		if (this.getAverageSpeed() == o.getAverageSpeed())
			return 0;
		else if (this.getAverageSpeed() > o.getAverageSpeed())
			return 1;
		else
			return -1;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
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

	public double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

}
