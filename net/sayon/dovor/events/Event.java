package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class Event {
	protected long time = System.currentTimeMillis();
	protected Buddy buddy;

	public Event() {

	}

	public Event(Buddy buddy) {
		this.buddy = buddy;
	}

	public long getTime() {
		return time;
	}

	public Buddy getBuddy() {
		return buddy;
	}

}
