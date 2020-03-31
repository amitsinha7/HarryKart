package se.atg.service.harrykart.exception;

public class HKException extends Exception {

	private static final long serialVersionUID = -2907253767842746242L;

	public HKException() {
		super();
	}


	public HKException(String message) {
		super(message);
	}

	public HKException(Exception e) {
		super(e);
	}

}
