package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class AddMeEvent extends ConsumableEvent {

	public AddMeEvent(Buddy buddy) {
		this.buddy = buddy;
	}
}
