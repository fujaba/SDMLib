package org.sdmlib.models.classes;

public enum Card
{
   ONE, MANY;

   @Override
   public String toString()
   {
      return super.toString().toLowerCase();
   }
}
