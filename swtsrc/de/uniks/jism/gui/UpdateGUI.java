package de.uniks.jism.gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpdateGUI implements Runnable{
private PropertyChangeEvent event;
		private PropertyChangeListener listener;

		public UpdateGUI(PropertyChangeListener listener, PropertyChangeEvent evt){
			this.listener = listener;
			this.event = evt;
		}

		@Override
		public void run() {
			this.listener.propertyChange(event);
		}
		
	}
