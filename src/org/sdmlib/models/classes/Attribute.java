/*   Copyright (c) 2012 zuendorf 
 
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

package org.sdmlib.models.classes;

import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.utils.StrUtil;

public class Attribute 
{
   public static final LinkedHashSet<Attribute> EMPTY_SET = new LinkedHashSet<Attribute>();
   
   public Attribute()
   {
      withClazz(Clazz.clazz);
      Clazz.clazz.withAttributes(this);   
   }
   
   private Clazz clazz = null;
   
   public Clazz getClazz()
   {
      return clazz;
   }
   
   public void setClazz(Clazz clazz)
   {
      this.clazz = clazz;
   }
   
   private Attribute withClazz(Clazz clazz)
   {
      setClazz(clazz);
      return this;
   }

   private String name = null;

   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public Attribute withName(String string)
   {
      setName(string);
      return this;
   }
   
   private String type = null;

   public String getType()
   {
      return type;
   }
   
   public void setType(String type)
   {
      this.type = type;
   }
   public Attribute withType(String string)
   {
      setType(string);
      return this;
   }

   public Attribute generate(String rootDir)
   {
      generate(rootDir, true);
      
      return this;
   } 

   public Attribute generate(String rootDir, boolean doGenerate)
   {
      // get parser from class
      Parser parser = clazz.getOrCreateParser(rootDir);
      
      insertAttrDeclPlusAccessors(parser);
      
      insertCaseInGenericGetSet(parser);
      
      getClazz().printFile(doGenerate);

      return this;
   }

   private void insertAttrDeclPlusAccessors(Parser parser)
   {
      int pos = parser.indexOf(Parser.ATTRIBUTE+":"+getName());
      
      if (pos < 0)
      {
         // add attribute declaration and get, set, with methods in class file
         pos = parser.indexOf(Parser.CLASS_END);
         
         StringBuilder text = new StringBuilder
            (  "\n   " +
               "\n   //==========================================================================" +
               "\n   " +
               "\n   public static final String PROPERTY_NAME = \"name\";" +
               "\n   " +
               "\n   private type name init;" +
               "\n   " +
               "\n   public type getName()" +
               "\n   {" +
               "\n      return this.name;" +
               "\n   }" +
               "\n   " +
               "\n   public void setName(type value)" +
               "\n   {" +
               "\n      this.name = value;" +
               "\n   }" +
               "\n   " +
               "\n   public ownerClass withName(type value)" +
               "\n   {" +
               "\n      setName(value);" +
               "\n      return this;" +
               "\n   } " +
               "\n"
               );

         
         CGUtil.replaceAll(text, 
               "type", getType(), 
               "name", getName(),
               "Name", StrUtil.upFirstChar(getName()),
               "NAME", getName().toUpperCase(),
               " init", getInitialization() == null ? "" : " = " + getInitialization(),
               "ownerClass", CGUtil.shortClassName(this.getClazz().getName())
               );
         
         parser.getFileBody().insert(pos, text.toString());
         getClazz().setFileHasChanged(true);
      }
   }

   private void insertCaseInGenericGetSet(Parser parser)
   {
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for attribute " + getName() + " for class " + getClazz().getName() 
            + ": \nDid not find method set(String,Object). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment their. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic set method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }
         
         StringBuilder text = new StringBuilder
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         setPropertyName((type) value);" +
               "\n         return true;" +
               "\n      }" +
               "\n" 
               );

         String typePlaceholder = "type";
         String type = getType();
         if ("int".equals(type))
         {
            type = "Integer";
         }
         else if ("long".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Long.parseLong(value.toString())";
         }

         CGUtil.replaceAll(text, 
            typePlaceholder, type, 
            "PropertyName", StrUtil.upFirstChar(getName()),
            "PROPERTY_NAME", "PROPERTY_" + getName().toUpperCase()
            );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         getClazz().setFileHasChanged(true);
      }
   }

   
   //==========================================================================
   
   public static final String PROPERTY_INITIALIZATION = "initialization";
 
   private String initialization = null;
   
   public String getInitialization()
   {
      return this.initialization;
   }
   
   public void setInitialization(String value)
   {
      this.initialization = value;
   }
   
   public Attribute withInitialization(String value)
   {
      setInitialization(value);
      return this;
   }


   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         setInitialization((String) value);
         return true;
      }

      return false;
   }
}




