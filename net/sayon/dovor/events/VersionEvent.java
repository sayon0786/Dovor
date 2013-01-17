package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class VersionEvent extends Event {
	private String text;

	public VersionEvent(Buddy buddy, String text) {
		this.buddy = buddy;
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
