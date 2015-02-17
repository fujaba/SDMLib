package org.sdmlib.models.modelsets;

@FunctionalInterface
public interface Condition<T>
{
	public boolean check(T elem);
}
