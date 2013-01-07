package org.sdmlib.serialization.interfaces;

import java.util.Collection;

public interface TypList {
	public boolean addObject(Object object);
	public boolean removeObject(Object object);
	public boolean isInstance(Object object);
	public Collection<Object> getValues();

}
