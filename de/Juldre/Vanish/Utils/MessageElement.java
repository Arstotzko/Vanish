package de.Juldre.Vanish.Utils;

public class MessageElement {
	private String message;
	private boolean showMessage;

	public MessageElement(String message, boolean showMessage) {
		this.message = message;
		this.showMessage = showMessage;
	}

	public boolean isShowMessage() {
		return showMessage;
	}

	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
