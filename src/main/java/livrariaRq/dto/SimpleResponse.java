package livrariaRq.dto;

public class SimpleResponse {
	private boolean statusOk;
	private String message;

	public SimpleResponse() {
		statusOk = false;
		message = "An error has occurred.";
	}

	public boolean isStatus() {
		return statusOk;
	}

	public void setStatus(boolean aStatus) {
		statusOk = aStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String aMessage) {
		message = aMessage;
	}

	public void setAsSuccess(String aMessage) {
		statusOk = true;
		message = aMessage;
	}

}