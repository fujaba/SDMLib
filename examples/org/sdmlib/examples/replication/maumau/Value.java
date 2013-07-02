package org.sdmlib.examples.replication.maumau;

public enum Value
{
   _7, _8, _9, _10, Jack, Queen, King, Ace;
   
   @Override
   public String toString() 
   {
      String superString = super.toString();
      
      if (superString.startsWith("_"))
      {
         superString = superString.substring(1);
      }
      
      return superString;
   };
}
