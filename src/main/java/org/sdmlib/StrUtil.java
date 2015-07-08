/*
   Copyright (c) 2012 Albert Zündorf

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib;

public class StrUtil
{
	public static final String LF = "\n";
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

   /**
    * Safe String comparison.
    *
    * @param s1
    *           first string
    * @param s2
    *           second string
    * @return 0 if both parameters are null or equal, a value less than 0 if the
    *         first string is lexicographically less than the second string, a
    *         value greater than 0 if lexicographically greater than
    */
   public static int stringCompare(String s1, String s2)
   {
      if (s1 != null)
      {
         return s2 != null ? s1.compareTo(s2) : s1.length();
      }
      else
      {
         return s2 == null ? 0 : -s2.length();
      }
   }

   public static String upFirstChar(String name)
   {
      if (isEmptyString(name))
      {
         return name;
      }

      StringBuilder stringBuilder = new StringBuilder(name);

      char firstChar = stringBuilder.charAt(0);

      firstChar = Character.toUpperCase(firstChar);

      stringBuilder.setCharAt(0, firstChar);

      return stringBuilder.toString();
   }

   public static boolean isEmptyString(String name)
   {
      return name == null || name.equals("");
   }

   public static String downFirstChar(String name)
   {
      if (isEmptyString(name))
      {
         return name;
      }

      StringBuilder stringBuilder = new StringBuilder(name);

      char firstChar = stringBuilder.charAt(0);

      firstChar = Character.toLowerCase(firstChar);

      stringBuilder.setCharAt(0, firstChar);

      return stringBuilder.toString();
   }

   public static String htmlEncode(String input)
   {
      return input.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
         .replaceAll(">", "&gt;").replaceAll("\"", "&quot;")
         .replaceAll("'", "&#x27;").replaceAll("/", "&#x2F;");
   }
}
