package org.sdmlib.models.modelsets;

public abstract class Condition<T>
{
   public abstract boolean check(T elem);
}
