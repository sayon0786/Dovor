package net.sayon.dovor.listeners;

import net.sayon.dovor.events.*;

public interface BuddyListener {
	public void onAddMe(AddMeEvent e);

	public void onRemoveMe(RemoveMeEvent e);

	public void onMessage(MessageEvent e);

	public void onNotImplemented(NotImplementedEvent e);
}
