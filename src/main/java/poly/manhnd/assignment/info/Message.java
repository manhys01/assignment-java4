package poly.manhnd.assignment.info;

public class Message {

	public static final int ERROR = 0;
	public static final int SUCCESS = 1;

	private int id;
	private String message;

	public Message() {
		super();
	}

	public Message(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
