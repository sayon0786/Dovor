package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class TextEvent extends Event {
	private String command;
	private String content;

	public TextEvent(Buddy buddy, String command, String content) {
		this.buddy = buddy;
		this.command = command;
		this.content = content;
	}

	public String getCommand() {
		return command;
	}

	public String getContent() {
		return content;
	}

}
