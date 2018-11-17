package br.com.alura.forum.service.infra;

public class ForumMailServiceException extends RuntimeException {

	public ForumMailServiceException(String message, Throwable cause) {
		
		super (message,cause);
		
	}

}
