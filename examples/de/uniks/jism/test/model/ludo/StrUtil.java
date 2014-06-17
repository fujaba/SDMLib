package de.uniks.jism.test.model.ludo;

public class StrUtil
{
   /**
    * Safe String comparison.
    *
    * @param s1
    *           first string
    * @param s2
    *           second string
    * @return true if both parameters are null or equal
    */
   public static boolean stringEquals(String s1, String s2)
   {
      return s1 == null ? s2 == null : s1.equals(s2);
   }
}