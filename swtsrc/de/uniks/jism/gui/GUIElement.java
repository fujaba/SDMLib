package de.uniks.jism.gui;

public interface GUIElement {
	public void updateGUI(String typ, Object value);

	public boolean isDisposed();
}
