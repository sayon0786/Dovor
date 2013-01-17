package net.sayon.dovor.listeners;

import java.io.IOException;

import net.sayon.dovor.events.*;

public interface BuddyListener {
	public void onEvent(Event e) throws IOException;
}
