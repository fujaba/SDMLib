package org.sdmlib.models.modelsets;

@FunctionalInterface
public interface Condition<T>
{
   
   public abstract boolean check(T elem);
}
