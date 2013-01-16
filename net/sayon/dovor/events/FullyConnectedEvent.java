package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class FullyConnectedEvent extends Event {
	private Buddy buddy;

	public FullyConnectedEvent(Buddy buddy) {
		this.buddy = buddy;
	}

	public Buddy getBuddy() {
		return buddy;
	}

}
