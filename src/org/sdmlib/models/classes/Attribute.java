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
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.models.pattern.AttributeConstraint;

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
	   return generate( clazz,  rootDir,  helpersDir,  doGenerate, false);
   }
   
   public Attribute generate(Clazz clazz, String rootDir, String helpersDir, boolean doGenerate, boolean fromSuperClass)
   {
      // get parser from class
      Parser parser = clazz.getOrCreateParser(rootDir);
      if(  ! fromSuperClass)   
    	  insertAttrDeclPlusAccessors(clazz, parser);
      
      if ( !clazz.isInterfaze())
      	insertCaseInGenericGetSet(parser);
      
      clazz.printFile(doGenerate);

      if ( !clazz.isInterfaze())
      {
	      Parser creatorParser = clazz.getOrCreateParserForCreatorClass(helpersDir);
	      
	      insertPropertyInCreatorClass(creatorParser, clazz );
	      
	      clazz.printCreatorFile(doGenerate);

	      Parser modelSetParser = clazz.getOrCreateParserForModelSetFile(helpersDir);
	      insertGetterInModelSetClass(modelSetParser, clazz);
	      insertSetterInModelSetClass(modelSetParser, clazz);
         getClazz().printModelSetFile(doGenerate);
         
         Parser patternObjectParser = clazz.getOrCreateParserForPatternObjectFile(helpersDir);
         insertHasMethodInPatternObjectClass(patternObjectParser, clazz);
         insertGetterInPatternObjectClass(patternObjectParser, clazz);
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
   
   private void insertSetterInModelSetClass(Parser parser, Clazz ownerClazz) {
      String attrType = CGUtil.shortClassName(getType());
      String key = Parser.METHOD + ":with"
            + StrUtil.upFirstChar(this.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
               "   public ModelSetType withName(AttrType value)\n"
                     + "   {\n"
                     + "      for (ContentType obj : this)\n"
                     + "      {\n"
                     + "         obj.withName(value);\n"
                     + "      }\n" + "      \n"
                     + "      return this;\n" 
                     + "   }\n" 
                     + "\n");

         String fullModelSetType = getType();
         String modelSetType = CGUtil.shortClassName(ownerClazz.getName()) + "Set";
         
         CGUtil.replaceAll(text, 
            "AttrType", attrType,
            "ContentType", CGUtil.shortClassName(ownerClazz.getName()),
            "ModelSetType", modelSetType, 
            "Name", StrUtil.upFirstChar(getName()));
         
         ownerClazz.printFile(true);
         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         // getClazz()
         ownerClazz.setModelSetFileHasChanged(true);         
      }
   }

	private void insertGetterInModelSetClass(Parser parser, Clazz ownerClazz) {
		String key = Parser.METHOD + ":get"
				+ StrUtil.upFirstChar(this.getName()) + "()";
		int pos = parser.indexOf(key);

		if (pos < 0) {
			// need to add property to string array

			StringBuilder text = new StringBuilder(
					"   public ModelSetType getName()\n"
							+ "   {\n"
							+ "      ModelSetType result = new ModelSetType();\n"
							+ "      \n"
							+ "      for (ContentType obj : this)\n"
							+ "      {\n"
							+ "         result.add(obj.getName());\n"
							+ "      }\n" + "      \n"
							+ "      return result;\n" 
							+ "   }\n" 
							+ "\n");

			String fullModelSetType = getType();
			String modelSetType = CGUtil.shortClassName(getType());
			
			ArrayList<String> importClassesFromTypes = new ArrayList<String>();
			
			if (!getType().contains("<") && !getType().endsWith("Set")) {
				fullModelSetType = getType() + "Set";
				modelSetType = CGUtil.shortClassName(getType()) + "Set";
				String importForSet = checkSetImportFor(CGUtil.shortClassName(getType()));
				if(importForSet != null)
					importClassesFromTypes.add(importForSet);
			}
			else {
//				System.out.println("no set");
			}
			
			if ("String int double long boolean".indexOf(getType()) >= 0) {
				modelSetType = CGUtil.shortClassName(getType()) + "List";
				fullModelSetType = "org.sdmlib.models.modelsets."
						+ modelSetType;
				importClassesFromTypes.add(fullModelSetType);
				importClassesFromTypes.add("java.util.List");
			}

			
			CGUtil.replaceAll(text, "ContentType",
					CGUtil.shortClassName(ownerClazz.getName()),
					"ModelSetType", modelSetType, "Name",
					StrUtil.upFirstChar(getName()));

			importClassesFromTypes.addAll(checkImportClassesFromType(fullModelSetType));
			
			ownerClazz.printFile(true);
			int classEnd = parser.indexOf(Parser.CLASS_END);
			parser.getFileBody().insert(classEnd, text.toString());
			// getClazz()
			ownerClazz.setModelSetFileHasChanged(true);
			
			

			// getClazz()
			for (String setType : importClassesFromTypes) {
				ownerClazz.insertImport(parser, setType);
			}
			
		}
	}

   private void insertHasMethodInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      String attrType = ownerClazz.shortNameAndImport(getType(), parser);
      String key = Parser.METHOD + ":has"
            + StrUtil.upFirstChar(this.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
                     "   public PatternObjectType hasName(AttrType value)\n" + 
                     "   {\n" + 
                     "      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()\n" + 
                     "      .withAttrName(ModelClass.PROPERTY_NAME)\n" + 
                     "      .withTgtValue(value)\n" + 
                     "      .withSrc(this)\n" +
                     "      .withModifier(this.getPattern().getModifier())\n" + 
                     "      .withPattern(this.getPattern());\n" + 
                     "      \n" + 
                     "      this.getPattern().findMatch();\n" + 
                     "      \n" + 
                     "      return this;\n" + 
                     "   }\n" +
                     "   \n");

         ownerClazz.insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getName()) + "PO";
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(getName()), 
            "AttrType", attrType, 
            "ModelClass", ownerClazz.shortNameAndImport(ownerClazz.getName(), parser),
            "PROPERTY_NAME", "PROPERTY_" + getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.setPatternObjectFileHasChanged(true);
      }
   }

   private void insertGetterInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      String attrType = ownerClazz.shortNameAndImport(getType(), parser);
      
      String attrNameUpFirstChar = StrUtil.upFirstChar(this.getName());
      
      String key = Parser.METHOD + ":get" + attrNameUpFirstChar + "()";
      
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
                     "   public AttrType getAttrName()\n" + 
                     "   {\n" + 
                     "      if (this.getPattern().getHasMatch())\n" + 
                     "      {\n" + 
                     "         return ((ModelClass) getCurrentMatch()).getAttrName();\n" + 
                     "      }\n" + 
                     "      return nullValue;\n" + 
                     "   }\n" +
                     "   \n");

         String nullValue = "null";
         
         if ("int long double float".indexOf(attrType) >= 0)
         {
            nullValue = "0";
         }
         else if ("boolean".equals(attrType))
         {
            nullValue = "false";
         }
         
         CGUtil.replaceAll(text, 
            "nullValue", nullValue,
            "AttrName", StrUtil.upFirstChar(getName()), 
            "AttrType", attrType, 
            "ModelClass", ownerClazz.shortNameAndImport(ownerClazz.getName(), parser));

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.setPatternObjectFileHasChanged(true);
      }
   }

   private String checkSetImportFor(String type) {
		Clazz findClass = clazz.getClassModel().findClass(type);
		if (findClass == null)
			return null;
		Clazz attributClass = getClazz();
		String packageNameFromFindClass = CGUtil.packageName(findClass.getName());
		String packageNameFromOwnerClass = CGUtil.packageName(attributClass.getName());
		if (!packageNameFromFindClass.equals(packageNameFromOwnerClass)) {
			return packageNameFromFindClass + ".creators." + CGUtil.shortClassName(findClass.getName()) + "Set";
		}
		else 
			return null;
	}

	private ArrayList<String> checkImportClassesFromType(String modelSetType) {
 		ArrayList<String> importList = new ArrayList<String>();
 		modelSetType = modelSetType.trim();
 		int index = modelSetType.indexOf("<");
		if(index >= 0) {
			String listType = modelSetType.substring(0, index);
			if ("List Vector HashSet HashMap".indexOf(listType) >= 0)
				listType = "java.util." +listType;
			importList.add( listType);
			String substring = modelSetType.substring(index+1, modelSetType.lastIndexOf(">"));
			String[] strings = substring.split(",");
			for (String string : strings) {
				Clazz findClass = clazz.getClassModel().findClass(string);
				if(findClass != null)
					importList.add(findClass.getName());
			}
		} else {
			Clazz findClass = getClazz().getClassModel().findClass(modelSetType);
			if (findClass != null)
				importList.add(findClass.getName());
		}
		
		return importList;
	}

	public void generateMethodsInKindClass(Clazz clazz, String rootDir, String helpersDir, boolean b)
  {
 		Parser parser = clazz.getOrCreateParser(rootDir);
 		
 		int pos = parser.indexOf(Parser.METHOD + ":get" + StrUtil.upFirstChar(getName())+ "()");

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
       clazz.printFile(true);
    }
  }
   
   private void insertAttrDeclPlusAccessors(Clazz clazz, Parser parser)
   {
//	   int pos = parser.indexOf(Parser.ATTRIBUTE+":" + getName()); //remove

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
               "ownerClass", CGUtil.shortClassName(this.getClazz().getName())
               );
         
         int pos = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(pos, text.toString());
         ArrayList<String> importClassesFromTypes = checkImportClassesFromType(getType());
			for (String typeImport : importClassesFromTypes) {
				clazz.insertImport(parser, typeImport);
			}
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
         else if ("float".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Float.parseFloat(value.toString())";
         }
         else if ("boolean".equals(type))
         {
           type = "Boolean";
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
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attribute))" +
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

