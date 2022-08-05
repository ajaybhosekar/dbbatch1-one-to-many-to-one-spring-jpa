package demos.springboot.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private String msg;
	
	public ResourceNotFoundException() {
		super("ResourceNotFoundException occured");
		msg = "ResourceNotFoundException occured";
	}
		
	
	public ResourceNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
