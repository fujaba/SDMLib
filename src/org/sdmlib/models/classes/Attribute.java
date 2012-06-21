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


import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Attribute implements PropertyChangeInterface 
{
	public static final String SIMPLE = "simple";
	   public static final String COMPLEX = "complex";
	
   public static final LinkedHashSet<Attribute> EMPTY_SET = new LinkedHashSet<Attribute>();
   
   public Attribute()
   {
      withClazz(Clazz.clazz);
      Clazz.clazz.withAttributes(this);   
   }
   
   /********************************************************************
    * <pre>
    *              many                       one
    * Attribute ----------------------------------- Clazz
    *              attributes                   clazz
    * </pre>
    */
   
   public static final String PROPERTY_CLAZZ = "clazz";
   
   private Clazz clazz = null;
   
   public Clazz getClazz()
   {
      return this.clazz;
   }
   
   public boolean setClazz(Clazz value)
   {
      boolean changed = false;
      
      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;
         
         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutAttributes(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.withAttributes(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Attribute withClazz(Clazz value)
   {
      setClazz(value);
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

   public Attribute generate(String rootDir, String helpersDir)
   {
      generate(this.clazz, rootDir, helpersDir, true);
      
      return this;
   } 

   public Attribute generate(String rootDir, String helpersDir, boolean doGenerate)
   {
      generate(this.clazz, rootDir, helpersDir, doGenerate);
      
      return this;
   } 
   
   public Attribute generate(Clazz clazz, String rootDir, String helpersDir)
   {
      generate(clazz, rootDir, helpersDir, true);
      
      return this;
   } 

   public Attribute generate(Clazz clazz, String rootDir, String helpersDir, boolean doGenerate)
   {
      // get parser from class
      Parser parser = clazz.getOrCreateParser(rootDir);
      
      insertAttrDeclPlusAccessors(parser);
      
      if ( !clazz.isInterfaze())
      	insertCaseInGenericGetSet(parser);
      
      clazz.printFile(doGenerate);

      if ( !clazz.isInterfaze())
      {
	      Parser creatorParser = clazz.getOrCreateParserForCreatorClass(helpersDir);
	      
	      insertPropertyInCreatorClass(creatorParser, clazz );
	      
	      clazz.printCreatorFile(doGenerate);

	      Parser modelSetParser = clazz.getOrCreateParserForModelSetFile(helpersDir);
	      insertGetterInModelSetClass(modelSetParser);
	      getClazz().printModelSetFile(doGenerate);
      }
      return this;
   }
   
   public void insertPropertyInCreatorClass(String className, Parser creatorParser, String helpersDir, boolean doGenerate) 
   {
  	 insertPropertyInCreatorClass(creatorParser, getClazz());
  	 
     int pos = creatorParser.indexOf(Parser.IMPORT);

     String prefix = "";
     StringBuilder fileBody = creatorParser.getFileBody();
		if (fileBody .indexOf(Parser.IMPORT, pos) < 0)
     {
        prefix = "\n";
     }
     
     SymTabEntry symTabEntry = creatorParser.getSymTab().get(Parser.IMPORT + ":" + className);
     if (symTabEntry == null)
     {
    	 creatorParser.getFileBody().insert(creatorParser.getEndOfImports() + 1, 
              prefix + "\nimport " + className + ";");
        
     }  	 
   }
   
   private void insertPropertyInCreatorClass(Parser parser, Clazz ownerClazz)
   {
      String key = Parser.ATTRIBUTE + ":properties";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         // ups, did not find generic get method. 
         System.err.println("Warning: SDMLib codgen for attribute " + getName() + " for creator class for " + getClazz().getName() 
            + ": \nDid not find properties field. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int endOfStringArrayInit = parser.getEndOfAttributeInitialization();
      
      String propertyName = "PROPERTY_" + getName().toUpperCase();
      
      int propertyNameIndex = parser.getFileBody().indexOf(propertyName, pos);

      if (propertyNameIndex < 0 || propertyNameIndex > endOfStringArrayInit)
      {         
         // need to add property to string array
         
         StringBuilder text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

         CGUtil.replaceAll(text, 
            "className", CGUtil.shortClassName(ownerClazz.getName()),
            "PROPERTY_NAME", "PROPERTY_" + getName().toUpperCase()
            );

         parser.getFileBody().insert(endOfStringArrayInit, text.toString());
         getClazz().setCreatorFileHasChanged(true);
      }
   }

   private void insertGetterInModelSetClass(Parser parser)
   {
      String key = Parser.METHOD + ":get" + StrUtil.upFirstChar(this.getName()) + "()";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         // need to add property to string array
         
         StringBuilder text = new StringBuilder(  
            "   public ModelSetType getName()\n" + 
            "   {\n" + 
            "      ModelSetType result = new ModelSetType();\n" + 
            "      \n" + 
            "      for (ContentType obj : this)\n" + 
            "      {\n" + 
            "         result.add(obj.getName());\n" + 
            "      }\n" + 
            "      \n" + 
            "      return result;\n" + 
            "   }\n" +
            "\n" + 
            ""
            );

         String fullModelSetType = getType()+"Set";
         String modelSetType = CGUtil.shortClassName(getType())+"Set";
         
         if ("String int double long".indexOf(getType()) >= 0)
         {
            modelSetType = CGUtil.shortClassName(getType())+"List";
            fullModelSetType = "org.sdmlib.models.modelsets." + modelSetType;
         }
         
         CGUtil.replaceAll(text, 
            "ContentType", CGUtil.shortClassName(getClazz().getName()),
            "ModelSetType", modelSetType,
            "Name", StrUtil.upFirstChar(getName())
            );

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         getClazz().setModelSetFileHasChanged(true);
         
         getClazz().insertImport(parser, fullModelSetType);
      }
   }

 	public void generateMethodsInKindClass(Clazz clazz, String rootDir, String helpersDir, boolean b)
  {
 		Parser parser = clazz.getOrCreateParser(rootDir);
 		
 		int pos = parser.indexOf(Parser.METHOD + ":get" + StrUtil.upFirstChar(getName())+ "()");

 		// TODO : FIX ME
 		String string = Parser.METHOD + ":get" + StrUtil.upFirstChar(getName()) + "()";     
		SymTabEntry symTabEntry = parser.getSymTab().get(string);
    
    if (pos < 0 && symTabEntry == null)
    {
       // add attribute declaration and get, set, with methods in class file
       StringBuilder text = new StringBuilder
          (  "\n   " +
             "\n   //==========================================================================" +
             "\n   // methods for PROPERTY_NAME = \"name\"" +
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
             "\n      if (valueCompare)" +
             "\n      {" +
             "\n         type oldValue = this.name;" +
             "\n         this.name = value;" +
             "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);" +
             "\n      }" +
             "\n   }" +
             "\n   " +
             "\n   public ownerClass withName(type value)" +
             "\n   {" +
             "\n      setName(value);" +
             "\n      return this;" +
             "\n   } " +
             "\n"
             );
       
       String valueCompare = "this.name != value";
       
       if ("String".equals(getType()))
       {
          valueCompare = " ! StrUtil.stringEquals(this.name, value)";
          clazz.insertImport(StrUtil.class.getName());        
       }
       
       else if ("Boolean".equals(getType()))
       {
      	 	CGUtil.replaceAll(text, "getName()", "isName()");
      	 	clazz.insertImport(StrUtil.class.getName()); 
       }
       
       CGUtil.replaceAll(text, "valueCompare", valueCompare);
       
       CGUtil.replaceAll(text, 
             "type", getType(), 
             "name", getName(),
             "Name", StrUtil.upFirstChar(getName()),
             "NAME", getName().toUpperCase(),
             " init", getInitialization() == null ? "" : " = " + getInitialization(),
             "ownerClass", CGUtil.shortClassName( clazz.getName())
             );
       
       pos = parser.indexOf(Parser.CLASS_END);
       
       parser.getFileBody().insert(pos, text.toString());
       clazz.setFileHasChanged(true);
    }
  }
   
   private void insertAttrDeclPlusAccessors(Parser parser)
   {
//	   int pos = parser.indexOf(Parser.ATTRIBUTE+":" + getName()); //remove

      // TODO : FIX ME
	  parser.indexOf(Parser.CLASS_END);
      String string = Parser.ATTRIBUTE+":PROPERTY_" + getName().toUpperCase();      
      SymTabEntry symTabEntry = parser.getSymTab().get(string);
      
      if (symTabEntry == null)
      {
         // add attribute declaration and get, set, with methods in class file
         StringBuilder text = null;
         
         if(clazz.isInterfaze()) {    	 
        	 text = insertPropertyInInterface(parser); 	 
         }
         else {
        	 text = insertPropertyInClass(parser);
         }
         
         if (text == null)
        	 return;
         
         String valueCompare = "this.name != value";
         
         if ("String".equals(getType()))
         {
            valueCompare = " ! StrUtil.stringEquals(this.name, value)";
            getClazz().insertImport(StrUtil.class.getName());        
         }
         
         else if ("Boolean".equals(getType()))
         {
        	 	CGUtil.replaceAll(text, "getName()", "isName()");
        	  getClazz().insertImport(StrUtil.class.getName()); 
         }
         
         CGUtil.replaceAll(text, "valueCompare", valueCompare);
         
         CGUtil.replaceAll(text, 
               "type", getType(), 
               "name", getName(),
               "Name", StrUtil.upFirstChar(getName()),
               "NAME", getName().toUpperCase(),
               " init", getInitialization() == null ? "" : " = " + getInitialization(),
               "ownerClass", CGUtil.shortClassName(this.getClazz().getName())
               );
         
         int pos = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(pos, text.toString());
         getClazz().setFileHasChanged(true);
      }
   }

private StringBuilder insertPropertyInClass(Parser parser) {
	StringBuilder text;
	text = new StringBuilder
	(  "\n   " +
	   "\n   //==========================================================================" +
	   "\n   " +
	   "\n   public static final String PROPERTY_NAME = \"name\";" +
	   "\n   " 
//               "\n   private type name init;" +
//               "\n   " +
//               "\n   public type getName()" +
//               "\n   {" +
//               "\n      return this.name;" +
//               "\n   }" +
//               "\n   " +
//               "\n   public void setName(type value)" +
//               "\n   {" +
//               "\n      if (valueCompare)" +
//               "\n      {" +
//               "\n         type oldValue = this.name;" +
//               "\n         this.name = value;" +
//               "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);" +
//               "\n      }" +
//               "\n   }" +
//               "\n   " +
//               "\n   public ownerClass withName(type value)" +
//               "\n   {" +
//               "\n      setName(value);" +
//               "\n      return this;" +
//               "\n   } " +
//               "\n"
	   );
	 if (!entryExist(Parser.ATTRIBUTE+":" + getName(), parser))
		 text.append("\n   private type name init;\n");
	 
	 if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public type getName()" +
	               "\n   {" +
	               "\n      return this.name;" +
	               "\n   }" +
	               "\n   ");
	 
	 if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public void setName(type value)" +
	               "\n   {" +
	               "\n      if (valueCompare)" +
	               "\n      {" +
	               "\n         type oldValue = this.name;" +
	               "\n         this.name = value;" +
	               "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);" +
	               "\n      }" +
	               "\n   }" +
	               "\n   ");
	 
	 if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public ownerClass withName(type value)" +
	               "\n   {" +
	               "\n      setName(value);" +
	               "\n      return this;" +
	               "\n   } " +
	               "\n");
	return text;
}

private StringBuilder insertPropertyInInterface(Parser parser) {
	StringBuilder text;
	text = new StringBuilder
         (  "\n   " +
	  "\n   //==========================================================================" +
	  "\n   " +
	  "\n   public static final String PROPERTY_NAME = \"name\";" +
	  "\n   " 
//              "\n   public type getName();" +
//              "\n   " +
//              "\n   public void setName(type value);" +
//              "\n   " +
//              "\n   public ownerClass withName(type value);" +
//              "\n"
	  );

	 if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public type getName();\n");
	 
	 if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public void setName(type value);\n");
	 
	 if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(getName())+ "()", parser))
		 text.append("\n   public ownerClass withName(type value);\n");
	return text;
}

   private boolean entryExist(String string, Parser parser) {
	   if (parser.indexOf(string) > -1)
		   return true;
	return false;
}

private void insertCaseInGenericGetSet(Parser parser)
   {
      insertCaseInGenericGet(parser);
      insertCaseInGenericSet(parser);
   }

   private void insertCaseInGenericSet(Parser parser)
   {
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for attribute " + getName() + " for class " + getClazz().getName() 
            + ": \nDid not find method set(String,Object). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

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
            typePlaceholder = "(type) value";
            type = "Integer.parseInt(value.toString())";
         }
         else if ("long".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Long.parseLong(value.toString())";
         }
         else if ("double".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Double.parseDouble(value.toString())";
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

   
   private void insertCaseInGenericGet(Parser parser)
   {
      int pos = parser.indexOf(Parser.METHOD + ":get(String)");

      if (pos < 0)
      {
      	if (getClazz().isInterfaze())
         // ups, did not find generic get method. 
         System.err.println("Warning: SDMLib codgen for attribute " + getName() + " for class " + getClazz().getName() 
            + ": \nDid not find method get(String). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic get method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }
         
         StringBuilder text = new StringBuilder
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         return getPropertyName();" +
               "\n      }" +
               "\n" 
               );

         if ("Boolean".equals(getType()))
         {
        	 CGUtil.replaceAll(text, "getPropertyName()", "isPropertyName()");
         }
         
         CGUtil.replaceAll(text, 
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
      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         return getInitialization();
      }

      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         return getInitialization();
      }

      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         return getInitialization();
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         return getClazz();
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

      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         setInitialization((String) value);
         return true;
      }

      if (PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         setInitialization((String) value);
         return true;
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         setClazz((Clazz) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


}

