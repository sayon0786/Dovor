package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class VersionEvent extends Event {
	private Buddy buddy;
	private String text;

	public VersionEvent(Buddy buddy, String text) {
		this.buddy = buddy;
		this.text = text;
	}

	public Buddy getBuddy() {
		return buddy;
	}

	public String getText() {
		return text;
	}
}
