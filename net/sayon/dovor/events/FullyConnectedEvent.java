package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class FullyConnectedEvent extends Event {

	public FullyConnectedEvent(Buddy buddy) {
		this.buddy = buddy;
	}
}
