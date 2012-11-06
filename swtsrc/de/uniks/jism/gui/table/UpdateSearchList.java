package de.uniks.jism.gui.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.utils.PropertyChangeClient;

public class UpdateSearchList implements PropertyChangeListener {
	private TableComponent tableComponent;

	public UpdateSearchList(TableComponent tableComponent, PeerMessage list){
		this.tableComponent = tableComponent;
		if (list instanceof PropertyChangeClient) {
			((PropertyChangeClient) list).addPropertyChangeListener(this);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		tableComponent.propertyChange(evt);
	}
}
