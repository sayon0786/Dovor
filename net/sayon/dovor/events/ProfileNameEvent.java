package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class ProfileNameEvent extends Event {
	private String text;

	public ProfileNameEvent(Buddy buddy, String text) {
		this.buddy = buddy;
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
