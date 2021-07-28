package publisher;



@SuppressWarnings("serial")
public class publisherexception extends RuntimeException {



	    public publisherexception() { }
	    
	    public publisherexception(String message) {
	        super(message);
	    }

	    public publisherexception(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public publisherexception(Throwable cause) {
	        super(cause);
	    }
}


