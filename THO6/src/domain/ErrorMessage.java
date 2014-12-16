package domain;

public class ErrorMessage {
	
	private String code;
	private String message;
	
	public ErrorMessage(String c,String m) {
		code = c;
		message = m;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
