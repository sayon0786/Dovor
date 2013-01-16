package net.sayon.dovor.listeners;

import net.sayon.dovor.events.*;

public interface BuddyListener {
	public void onAddMe(AddMeEvent e);

	public void onRemoveMe(RemoveMeEvent e);

	public void onMessage(MessageEvent e);

	public void onNotImplemented(NotImplementedEvent e);

	public void onClient(ClientEvent e);

	public void onVersion(VersionEvent e);

	public void onProfileName(ProfileNameEvent e);

	public void onProfileText(ProfileTextEvent e);

	public void onCommand(TextEvent e);

	public void onStatus(StatusEvent e);

	public void onFullyConnected(FullyConnectedEvent e);
}
