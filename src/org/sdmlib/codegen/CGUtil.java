/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

public class CGUtil 
{
   public static boolean isPrimitiveType(String type)
   {
      String primitiveTypes = "String long int char boolean byte float double";
      
      if (type == null)  return false;
      
      return primitiveTypes.indexOf(type) >= 0;
   }
   
   public static void printFile(File file, String text)
   {
      try {
         File parentFile = file.getParentFile();
         if ( ! parentFile.exists())
         {
            parentFile.mkdirs();
         }
         PrintStream out = new PrintStream(file);
         out.println(text);
         out.flush();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
   
   public static StringBuilder readFile(File file)
   {
      StringBuilder result = new StringBuilder();
      try
      {
         BufferedReader in = new BufferedReader(new FileReader(file));
         
         String line = in.readLine();
         while (line != null)
         {
            result.append(line).append('\n');
            line = in.readLine();
         }
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return result;
   }

   public static void replace(StringBuilder fileBody, String placeholder,
         String text)
   {
      // find placeholder
      int pos = fileBody.indexOf(placeholder);
      
      if (pos > 0)
      {
         fileBody.replace(pos, pos+placeholder.length(), text);
      }
   }

   public static void replaceAll(StringBuilder text, String... args)
   {
      int pos = -1 - args[0].length();
      String placeholder;
      // args are pairs of placeholder, replacement
      
      // in the first run, replace placeholders by <$<placeholders>$> to mark them uniquely
      for (int i = 0; i < args.length; i += 2)
      {
         placeholder = args[i];
         pos = -1 - placeholder.length();
         
         pos = text.indexOf(placeholder, pos + placeholder.length());
         
         while (pos >= 0)
         {
            text.replace(pos, pos + placeholder.length(), "<$<" + placeholder + ">$>");
            pos = text.indexOf(placeholder, pos + placeholder.length());
         }
      }
      
      // in the second run, replace <$<placeholders>$> by replacement
      for (int i = 0; i < args.length; i += 2)
      {
         placeholder = "<$<" + args[i] + ">$>";
         pos = -1 - placeholder.length();
           
         pos = text.indexOf(placeholder, pos + placeholder.length());
         
         while (pos >= 0)
         {
            text.replace(pos, pos + placeholder.length(), args[i+1]);
            pos = text.indexOf(placeholder, pos + placeholder.length());
         }
      }
      
   }
   
   public static String encodeHTML(String s)
   {
       StringBuffer out = new StringBuffer();
       for(int i=0; i<s.length(); i++)
       {
           char c = s.charAt(i);
           if(c > 127 || c=='"' || c=='<' || c=='>')
           {
              out.append("&#"+(int)c+";");
           }
           else
           {
               out.append(c);
           }
       }
       return out.toString();
   }
   
   public static String shortClassNameHTMLEncoded(String name)
   {
      return encodeHTML(shortClassName(name));
   }

   public static String shortClassName(String name)
   {
      int pos = name.lastIndexOf('.');
      return name.substring(pos + 1);
   }

   public static String packageName(String name)
   {
      int pos = name.lastIndexOf('.');
      
      return name.substring(0, pos);
   }

   public static String encodeJavaName(String text)
   {
      StringBuilder result = new StringBuilder();
      
      if ( text.length() == 0 || ! Character.isLetter(text.charAt(0)))
      {
         result.append('_');
      }
      
      for(int i = 0; i < text.length(); i++)
      {
         if (Character.isLetter(text.charAt(i)) ||  Character.isDigit(text.charAt(i)))
         {
            result.append(text.charAt(i));
         }
      }
      
      return result.toString();
   }
}



