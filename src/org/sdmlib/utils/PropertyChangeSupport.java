/**
 * 
 */
package org.sdmlib.utils;

import java.util.ArrayList;
import java.util.HashMap;


public class PropertyChangeSupport
{
	private HashMap children;
	private ArrayList listeners;
	private Object source;

	public PropertyChangeSupport(Object sourceObj)
	{
		this.source = sourceObj;
	}

	public PropertyChangeSupport()
	{
		// Auto-generated constructor stub suffices
	}

	public void firePropertyChange(String propertyDescription, Object oldValue,
			Object value)
	{
		firePropertyChange(new PropertyChangeEvent(source, propertyDescription,
				oldValue, value));
	}

	public void firePropertyChange(PropertyChangeEvent evt)
	{
		if (listeners != null)
		{
			for (int i = 0; i < listeners.size(); i++)
			{
				((PropertyChangeListener) listeners.get(i))
						.propertyChanged(evt);
			}
		}
		if (children != null)
		{
			PropertyChangeSupport child = (PropertyChangeSupport) children
					.get(evt.getPropertyName());
			if (child != null)
			{
				child.firePropertyChange(evt);
			}
		}
	}

	public void addPropertyChangeListener(String property,
			PropertyChangeListener listener)
	{
		if (children == null)
			children = new HashMap();
		PropertyChangeSupport child = (PropertyChangeSupport) children
				.get(property);
		if (child == null)
		{
			child = new PropertyChangeSupport(); 
			children.put(property, child); 
		}
		child.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if (listeners != null)
		{
			listeners.remove(listener);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		if (listeners == null)
			listeners = new ArrayList();
		listeners.add(listener);
	}

	public void removePropertyChangeListener(String property,
			PropertyChangeListener listener)
	{
		if (children != null)
		{
			PropertyChangeSupport child = (PropertyChangeSupport) children
					.get(property);
			if (child != null)
			{
				child.removePropertyChangeListener(listener);
			}
		}
	}

}