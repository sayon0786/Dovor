package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class MessageEvent extends Event {
	private String message;

	public MessageEvent(Buddy buddy, String message) {
		this.buddy = buddy;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
