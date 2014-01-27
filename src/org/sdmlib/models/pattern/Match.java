package org.sdmlib.models.pattern;

public class Match
{
   public int number = 0;
   
   public int getNumber()
   {
      return number;
   }
   
   public Match withNumber(int n)
   {
      number = n;
      return this;
   }
}
