package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class NotImplementedEvent extends Event {
	private String additionalMessage;

	public NotImplementedEvent(Buddy buddy, String additionalMessage) {
		this.buddy = buddy;
		this.additionalMessage = additionalMessage;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}
}
