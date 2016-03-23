package org.sdmlib.serialization;

public class NullCreator extends EntityFactory
{
   private static NullCreator theInstance = new NullCreator();
   
   public static NullCreator get()
   {
      return theInstance;
   }
}
