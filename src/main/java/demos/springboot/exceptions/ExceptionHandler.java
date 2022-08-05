package demos.springboot.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceNotFoundException.class})
	public ExceptionDetails handleResourceNotFoundException(ResourceNotFoundException e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {NullObjectException.class})
	public ExceptionDetails handleNullObjectException(NullObjectException e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
	public ExceptionDetails handleGenericException(Exception e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}

}
