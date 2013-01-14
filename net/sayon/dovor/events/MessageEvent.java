package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class MessageEvent extends Event {
	private Buddy buddy;
	private String message;

	public MessageEvent(Buddy buddy, String message) {
		this.buddy = buddy;
		this.message = message;
	}

	public Buddy getBuddy() {
		return buddy;
	}

	public String getMessage() {
		return message;
	}
}
