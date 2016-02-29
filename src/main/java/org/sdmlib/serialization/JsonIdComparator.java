package org.sdmlib.serialization;

import java.util.Comparator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonObject;

public class JsonIdComparator implements Comparator<Object>
{
   @Override
   public int compare(Object o1, Object o2)
   {
      if (o1 instanceof JsonObject)
      {
         o1 = ((JsonObject) o1).get(IdMap.ID);
         o2 = ((JsonObject) o2).get(IdMap.ID);
      }
      String id1 = (String) o1;
      String id2 = (String) o2;

      int i1 = -1;
      int i2 = -1;

      int lastIndex1 = id1.length() - 1;
      int lastIndex2 = id2.length() - 1;

      while (true)
      {
         i1++;
         i2++;

         if (i1 > lastIndex1 && i2 > lastIndex2)
         {
            return 0;
         }
         else if (i1 > lastIndex1)
         {
            // ab < abc
            return -1;
         }
         else if (i2 > lastIndex2)
         {
            // abc > ab
            return 1;
         }

         char c1 = id1.charAt(i1);
         char c2 = id2.charAt(i2);

         if (!Character.isDigit(c1) && !Character.isDigit(c2))
         {
            // two letters just compare
            if (c1 == c2)
            {
               continue;
            }
            else if (c1 > c2)
            {
               return 1;
            }
            else
            {
               return -1;
            }
         }
         else if (!Character.isDigit(c1))
         {
            // abc > ab1
            return 1;
         }
         else if (!Character.isDigit(c2))
         {
            // ab1 > abc
            return -1;
         }
         else
         // two digits, compute the numbers and compare them
         {
            long num1 = c1;
            while (i1 + 1 <= lastIndex1
               && Character.isDigit(id1.charAt(i1 + 1)))
            {
               num1 = num1 * 10 + id1.charAt(i1 + 1);
               i1++;
            }

            long num2 = c2;
            while (i2 + 1 <= lastIndex2
               && Character.isDigit(id2.charAt(i2 + 1)))
            {
               num2 = num2 * 10 + id2.charAt(i2 + 1);
               i2++;
            }

            if (num1 == num2)
            {
               continue;
            }
            else if (num1 > num2)
            {
               return 1;
            }
            else
            {
               return -1;
            }
         }
      }
   }

   protected int compareSimple(String str1, String str2)
   {
      int numberSuffixPos1 = indexOfNumberSuffix(str1);
      int numberSuffixPos2 = indexOfNumberSuffix(str2);

      // compare prefixes
      for (int i = 0; i < numberSuffixPos1; i++)
      {
         if (i >= numberSuffixPos2)
         {
            // ab1 > a1
            return 1;
         }

         char c1 = str1.charAt(i);
         char c2 = str2.charAt(i);

         if (c1 == c2)
         {
            continue;
         }
         else
         {
            if (c1 > c2)
            {
               return 1;
            }
            else
            {
               return -1;
            }
         }
      }

      if (numberSuffixPos1 < numberSuffixPos2)
      {
         // a1 < ab1
         return -1;
      }

      // now the numbers decide
      if (numberSuffixPos1 >= 0)
      {
         String numberSuffix1 = str1.substring(numberSuffixPos1);
         String numberSuffix2 = str2.substring(numberSuffixPos2);

         long num1 = Long.parseLong(numberSuffix1);
         long num2 = Long.parseLong(numberSuffix2);

         if (num1 == num2)
         {
            return 0;
         }
         else if (num1 > num2)
         {
            return 1;
         }
         else
         {
            return -1;
         }
      }

      return 0;
   }

   private int indexOfNumberSuffix(String str1)
   {
      if (str1 == null || str1.length() == 0)
      {
         return -1;
      }

      int i;
      for (i = str1.length() - 1; i >= 0; i--)
      {
         if (!Character.isDigit(str1.charAt(i)))
         {
            if (i + 1 >= str1.length())
            {
               return -1;
            }
            else
            {
               return i + 1;
            }
         }
      }

      return i + 1;
   }
}