package demos.springboot.exceptions;

public class ResourceNotEmptyException extends RuntimeException{
	
	private String msg;
	
	
	public ResourceNotEmptyException() {
		super("ResourceNotEmptyException occured");
		this.msg = "ResourceNotEmptyException occured";
	}
	
	public ResourceNotEmptyException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
