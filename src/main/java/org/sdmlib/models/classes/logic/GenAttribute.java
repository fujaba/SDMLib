package org.sdmlib.models.classes.logic;

import java.util.ArrayList;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Visibility;
import org.sdmlib.models.pattern.AttributeConstraint;

public class GenAttribute extends Generator<Attribute>
{
   private void insertAttrDeclPlusAccessors(Clazz clazz, Parser parser)
   {
      //    int pos = parser.indexOf(Parser.ATTRIBUTE+":" + getName()); //remove

      parser.indexOf(Parser.CLASS_END);
      // add attribute declaration and get, set, with methods in class file
      StringBuilder text = null;

      if(clazz.isInterface()) 
      {      
         text = insertPropertyInInterface(parser);     
      }
      else 
      {
         text = insertPropertyInClass(parser);
      }

      if (text == null)
         return;

      String valueCompare = "this.name != value";
      if ("String".equalsIgnoreCase(model.getType().getValue()))
      {
         valueCompare = " ! StrUtil.stringEquals(this.name, value)";
         getGenerator(clazz).insertImport(StrUtil.class.getName()); 
      }

      else if ("boolean".equalsIgnoreCase(model.getType().getValue()))
      {
         CGUtil.replaceAll(text, "getName()", "isName()");
      }

      CGUtil.replaceAll(text, "valueCompare", valueCompare);

      CGUtil.replaceAll(text, 
         "type", CGUtil.shortClassName(model.getType().getValue()), 
         "modifier", model.getVisibility().getValue(),
         "name", model.getName(),
         "Name", StrUtil.upFirstChar(model.getName()),
         "NAME", model.getName().toUpperCase(),
         " init", model.getInitialization() == null ? "" : " = " +model.getInitialization(),
               "ownerClass", CGUtil.shortClassName(clazz.getFullName())
            );

      int pos = parser.indexOf(Parser.CLASS_END);

      parser.insert(pos, text.toString());
      ArrayList<String> importClassesFromTypes = checkImportClassesFromType(model.getType());
      for (String typeImport : importClassesFromTypes) {
         getGenerator(clazz).insertImport(parser, typeImport);
      }
   }

   private StringBuilder insertPropertyInClass(Parser parser) 
   {
      boolean hasNewContent = false;
      StringBuilder text;
      text = new StringBuilder( "" +
            "\n   " +
            "\n   //==========================================================================" +
            "\n   " 
            );
      
      if (!entryExist(Parser.ATTRIBUTE+":PROPERTY_" + model.getName().toUpperCase(), parser)
            && ! model.getClazz().isInterface() && !model.getVisibility().has(Visibility.STATIC) )
      {
         text.append("" +
               "\n   public static final String PROPERTY_NAME = \"name\";" +
               "\n   " );
         hasNewContent = true;
      }
      
      if (!entryExist(Parser.ATTRIBUTE+":" + model.getName(), parser))
      {
         text.append("\n   modifier type name init;\n");
         hasNewContent = true;
      }
      
      if(model.getVisibility().same(Visibility.PRIVATE) ){
         if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(model.getName())+ "()", parser) && !entryExist(Parser.METHOD + ":is" + StrUtil.upFirstChar(model.getName())+ "()", parser))
         {
            text.append("\n   public type getName()" +
                  "\n   {" +
                  "\n      return this.name;" +
                  "\n   }" +
                  "\n   ");
   
            hasNewContent = true;
         }
         if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(model.getName())+ "(" + CGUtil.shortClassName(model.getType().getValue()) + ")", parser))
         {
            text.append("\n   public void setName(type value)" +
                  "\n   {" +
                  "\n      if (valueCompare)" +
                  "\n      {PGOLD" +
                  "\n         this.name = value;PROPERTYCHANGE"+
                  "\n      }" +
                  "\n   }" +
                  "\n   ");
            
            if(model.getClazz().getClassModel().hasFeature(Feature.PropertyChangeSupport)){
               CGUtil.replaceAll(text, 
                     "PGOLD", "\n         type oldValue = this.name;",
                     "PROPERTYCHANGE", "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);");
            }else{
               CGUtil.replaceAll(text, 
                     "PGOLD", "",
                     "PROPERTYCHANGE", "");
            }
            hasNewContent = true;
         }
   
         if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(model.getName())+ "(" + CGUtil.shortClassName(model.getType().getValue())  + ")", parser))
         {
            text.append("\n   public ownerClass withName(type value)" +
                  "\n   {" +
                  "\n      setName(value);" +
                  "\n      return this;" +
                  "\n   } " +
                  "\n");
            hasNewContent = true;
         }
      }
      
      if (hasNewContent)
      {
         return text;
      }
      else
      {
         return null;
      }
   }

   private StringBuilder insertPropertyInInterface(Parser parser) 
   {
      boolean hasNewContent = false;
      StringBuilder text;
      text = new StringBuilder( "" +
            "\n   " +
            "\n   //==========================================================================" +
            "\n   " 
            );
      
      if (!entryExist(Parser.ATTRIBUTE+":PROPERTY_" + model.getName().toUpperCase(), parser))
      {
         text.append("" +
               "\n   public static final String PROPERTY_NAME = \"name\";" +
               "\n   " );
         hasNewContent = true;
      }
      
      if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(model.getName())+ "()", parser))
      {
         text.append("\n   public type getName();\n");
         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(model.getName())+ "(" + CGUtil.shortClassName(model.getType().getValue()) + ")", parser))
      {
         text.append("\n   public void setName(type value);\n");
         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(model.getName())+ "(" + CGUtil.shortClassName(model.getType().getValue()) + ")", parser))
      {
         text.append("\n   public ownerClass withName(type value);\n");
         hasNewContent = true;
      }
      
      if (hasNewContent)
      {
         return text;
      }
      else
      {
         return null;
      }
   }

   private boolean entryExist(String string, Parser parser) {
      if (parser.indexOf(string) > -1)
         return true;
      return false;
   }

  private void insertCaseInToString(Parser parser)
   {
      if ("String int double float".indexOf(CGUtil.shortClassName(model.getType().getValue())) < 0)
      {
         // only standard types contribute to toString()
         return;
      }
      
      // do we have a toString() method?
      int pos = parser.indexOf(Parser.METHOD + ":toString()");

      if (pos < 0)
      {
         // insert empty toString()
         StringBuilder text = new StringBuilder(  
            "\n" +
            "\n   @Override" +
            "\n   public String toString()\n" + 
            "   {\n" + 
            "      StringBuilder result = new StringBuilder();\n" + 
            "      \n" + 
            "      return result.substring(1);\n" + 
            "   }\n\n" 
               );
         
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
         
         pos = parser.indexOf(Parser.METHOD + ":toString()");
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
      
      int attrPos = parser.search("get" + StrUtil.upFirstChar(model.getName()), methodBodyStartPos);
      
      boolean noAttr = attrPos < 0 || attrPos > pos;
      
      if ( noAttr)
      {
         attrPos = parser.search(".append(" + model.getName() +")", methodBodyStartPos);
         noAttr = attrPos < 0 || attrPos > pos;
      }
      
      if ( noAttr)
      {         
         // need to add attr to text
         int returnPos = parser.search("return", methodBodyStartPos);
         
         // maybe there are multiple returns, search for the last one
         int secondReturnPos = returnPos;
         while (secondReturnPos >= 0 && secondReturnPos < pos)
         {
            secondReturnPos = parser.search("return", secondReturnPos + 4);

            if (secondReturnPos >= 0 && secondReturnPos < pos)
            {
               returnPos = secondReturnPos;
            }
         }
         
         
         StringBuilder text = new StringBuilder(  
            "result.append(\" \").append(this.getName());\n      " 
               );

         CGUtil.replaceAll(text, 
            "Name", StrUtil.upFirstChar(model.getName())
               );

         parser.insert(returnPos, text.toString());
      }
   }
   private ArrayList<String> checkImportClassesFromType(DataType value) 
   {
      String modelSetType = value.getValue();
      ArrayList<String> importList = new ArrayList<String>();
      modelSetType = modelSetType.trim();
      int index = modelSetType.indexOf("<");
      
      if(index >= 0) 
      {
         String listType = modelSetType.substring(0, index);
         
         if ("List Vector HashSet HashMap".indexOf(listType) >= 0)
         {
            listType = "java.util." +listType;
         }
         
         importList.add( listType);
         String substring = modelSetType.substring(index+1, modelSetType.lastIndexOf(">"));
         String[] strings = substring.split(",");
         
         for (String string : strings) 
         {
            Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(string);
            if(findClass != null)
            {
               importList.add(findClass.getFullName());
            }
         }
      } 
      else 
      {
         Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(modelSetType);
         
         if (findClass != null)
         {
            importList.add(findClass.getFullName());
         }
         else
         {
            if (modelSetType.indexOf('.') >= 0)
            {
               importList.add(modelSetType);
            }
         }
      }

      return importList;
   }
   private void insertCreateMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz) 
   {
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getValue(), parser);
      attrType = CGUtil.shortClassName(attrType);
      String key = Parser.METHOD + ":create"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType createName(AttrType value)\n" + 
                  "   {\n" + 
                  "      this.startCreate().hasName(value).endCreate();\n" + 
                  "      return this;\n" + 
                  "   }\n" +
               "   \n");

         getGenerator(ownerClazz).insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getFullName()) + "PO";

         String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getFullName(), parser);
         
         if (ownerClazz.isExternal())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "Name", StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass);

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());
      }
   }


   private void insertGetterInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getValue(), parser);
      attrType = CGUtil.shortClassName(attrType);
      String attrNameUpFirstChar = StrUtil.upFirstChar(model.getName());

      String key = Parser.METHOD + ":get" + attrNameUpFirstChar + "()";

      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public AttrType getAttrName()\n" + 
                  "   {\n" + 
                  "      if (this.getPattern().getHasMatch())\n" + 
                  "      {\n" + 
                  "         return ((ModelClass) getCurrentMatch()).VALUEGET;\n" + 
                  "      }\n" + 
                  "      return nullValue;\n" + 
                  "   }\n" +
                  "   \n" +
                  "   public ModelClassPO withAttrName(AttrType value)\n" + 
                  "   {\n" + 
                  "      if (this.getPattern().getHasMatch())\n" + 
                  "      {\n" + 
                  "         ((ModelClass) getCurrentMatch()).VALUESET;\n" + 
                  "      }\n" + 
                  "      return this;\n" + 
                  "   }\n" +
                  "   \n"
               );

         String nullValue = "null";

         if ("int long double float".indexOf(attrType) >= 0)
         {
            nullValue = "0";
         }
         else if ("boolean".equalsIgnoreCase(attrType))
         {
            nullValue = "false";
         }
         String name = StrUtil.upFirstChar(model.getName());
         String attrNameGetter = "get"+name+"()";
         if ("boolean".equalsIgnoreCase(attrType))
         {
            attrNameGetter = "is"+name+"()";
         }
         String attrNameSetter = "set"+name+"(value)";
         if(model.getVisibility().same(Visibility.PUBLIC)){
            attrNameGetter = model.getName();
            attrNameSetter = model.getName()+" = value";
         }

         CGUtil.replaceAll(text, 
            "nullValue", nullValue,
            "VALUEGET", attrNameGetter,
            "VALUESET", attrNameSetter,
            "AttrName", name, 
            "AttrType", attrType, 
            "ModelClass", getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getFullName(), parser));

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());
      }
   }

   private String checkSetImportFor(String type) {
      Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(type);
      if (findClass == null)
         return null;
      Clazz attributClass = model.getClazz();
      String packageNameFromFindClass = CGUtil.packageName(findClass.getFullName());
      String packageNameFromOwnerClass = CGUtil.packageName(attributClass.getFullName());
      if (findClass.isExternal())
      {
         return packageNameFromFindClass + GenClassModel.UTILPATH + "." + CGUtil.shortClassName(findClass.getFullName()) + "Set";
      }
      else if (!packageNameFromFindClass.equals(packageNameFromOwnerClass)) 
      {
         return packageNameFromOwnerClass + GenClassModel.UTILPATH+"." + CGUtil.shortClassName(findClass.getFullName()) + "Set";
      }
      return null;
   }
   
   private String checkImportFor(String type) {
	   Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(type);
	   if (findClass == null)
		   return null;
	   Clazz attributClass = model.getClazz();
	   String packageNameFromFindClass = CGUtil.packageName(findClass.getFullName());
	   String packageNameFromOwnerClass = CGUtil.packageName(attributClass.getFullName());
	   if (findClass.isExternal())
	   {
		   return packageNameFromFindClass + "." + CGUtil.shortClassName(findClass.getFullName());
	   }
	   else if (!packageNameFromFindClass.equals(packageNameFromOwnerClass)) 
	   {
		   return packageNameFromOwnerClass + "." + CGUtil.shortClassName(findClass.getFullName());
	   }
	   return null;
   }
   
   private boolean checkIsExternalFor(String type) {
	   Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(type);
	   if (findClass == null)
		   return false;
	   else if (findClass.isExternal()){
		   return true;
	   }
	   else {
		   return false;
	   }
   }
   
   private void insertHasMethodInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      insertHasMethodInPatternObjectClassOneParam(parser, ownerClazz);
      insertHasMethodInPatternObjectClassRange(parser, ownerClazz);
      insertCreateMethodInPatternObjectClassOneParam(parser, ownerClazz);
   }
   
   private void insertHasMethodInPatternObjectClassRange(Parser parser, Clazz ownerClazz) 
   {
      if ("int long float double String".indexOf(model.getType().getValue()) < 0)
      {
         // range query only for numbers and strings
         return;
      }
      
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getValue(), parser);
      attrType = CGUtil.shortClassName(attrType);
      String key = Parser.METHOD + ":has"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + "," + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType hasName(AttrType lower, AttrType upper)\n" + 
                  "   {\n" + 
                  "      new AttributeConstraint()\n" + 
                  "      .withAttrName(ModelClass.PROPERTY_NAME)\n" + 
                  "      .withTgtValue(lower)\n" + 
                  "      .withUpperTgtValue(upper)\n" + 
                  "      .withSrc(this)\n" +
                  "      .withModifier(this.getPattern().getModifier())\n" + 
                  "      .withPattern(this.getPattern());\n" + 
                  "      \n" + 
                  "      super.hasAttribute()\n" + 
                  "      \n" + 
                  "      return this;\n" + 
                  "   }\n" +
               "   \n");

         getGenerator(ownerClazz).insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getFullName()) + "PO";

         String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getFullName(), parser);
         
         if (ownerClazz.isExternal())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass,
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());
      }
   }


   private void insertHasMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz) 
   {
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getValue(), parser);
      attrType = CGUtil.shortClassName(attrType);
      String key = Parser.METHOD + ":has"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType hasName(AttrType value)\n" + 
                  "   {\n" + 
                  "      new AttributeConstraint()\n" + 
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

         getGenerator(ownerClazz).insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getFullName()) + "PO";

         String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getFullName(), parser);
         
         if (ownerClazz.isExternal())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass,
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());
      }
   }

	private void insertGetterInModelSetClass(Parser parser, Clazz ownerClazz) {
      if(parser==null){
         return;
      }
      String key = Parser.METHOD + ":get"
            + StrUtil.upFirstChar(model.getName()) + "()";
      int pos = parser.indexOf(key);
      int enumPos = -1;
      
 	 boolean isEnum = isEnumType(model, ownerClazz);
	if (isEnum) {
 		 String enumKey = Parser.METHOD + ":has"
              + StrUtil.upFirstChar(CGUtil.shortClassName(model.getName())) + "("+CGUtil.shortClassName(model.getType().getValue())+")";
         enumPos = parser.indexOf(enumKey);
 	 }

      if ( (isEnum && pos < 0 && enumPos < 0) ||
      	  (!isEnum && pos < 0 )	) {
         // need to add property to string array
    	  
    	 String getterString =    "   public ModelSetType getName()\n"
                 				+ "   {\n"
                 				+ "      ModelSetType result = new ModelSetType();\n"
				                + "      \n"
				                + "      for (ContentType obj : this)\n"
				                + "      {\n"
				                + "         result.addOneOrMore(obj.VALUEGET);\n"
				                + "      }\n" + "      \n"
				                + "      return result;\n" 
				                + "   }\n" 
				                + "\n";
    	  
    	 // no getter vor enum
    	 if (isEnumType(model, ownerClazz)) {
    		 getterString ="";
    	 }
    	  

         StringBuilder text = new StringBuilder(
                  getterString
                  + 
                  "   public ObjectSetType hasName(AttrType value)\n" + 
                  "   {\n" + 
                  "      ObjectSetType result = new ObjectSetType();\n" + 
                  "      \n" + 
                  "      for (ContentType obj : this)\n" + 
                  "      {\n" + 
                  "         if (valueComparison)\n" + 
                  "         {\n" + 
                  "            result.add(obj);\n" + 
                  "         }\n" + 
                  "      }\n" + 
                  "      \n" + 
                  "      return result;\n" + 
                  "   }\n" + 
                  "\n" );
         
         if ( " int long float double String ".indexOf( " " + model.getType().getValue() + " " ) >= 0)
         {
          text.append(
                  "   public ObjectSetType hasName(AttrType lower, AttrType upper)\n" + 
                  "   {\n" + 
                  "      ObjectSetType result = new ObjectSetType();\n" + 
                  "      \n" + 
                  "      for (ContentType obj : this)\n" + 
                  "      {\n" + 
                  "         if (rangeCheck)\n" + 
                  "         {\n" + 
                  "            result.add(obj);\n" + 
                  "         }\n" + 
                  "      }\n" + 
                  "      \n" + 
                  "      return result;\n" + 
                  "   }\n"
                  + "\n"    
               );
         }
         
         String name = StrUtil.upFirstChar(model.getName());
         String attrNameGetter = "get"+name+"()";
         if ("boolean".equalsIgnoreCase(model.getType().getValue()))
         {
            attrNameGetter = "is"+name+"()";
         }
         
         if(model.getVisibility().same(Visibility.PUBLIC)){
            attrNameGetter = model.getName();
         }

         DataType dataType = model.getType();
         String fullModelSetType = dataType.getValue();
         String modelSetType = CGUtil.shortClassName(fullModelSetType);

         ArrayList<String> importClassesFromTypes = new ArrayList<String>();

         if ( ! CGUtil.isPrimitiveType(fullModelSetType) && !fullModelSetType.contains("<") && !fullModelSetType.endsWith("Set")) 
         {
            modelSetType = CGUtil.shortClassName(fullModelSetType) + "Set";
            fullModelSetType = CGUtil.packageName(fullModelSetType) + GenClassModel.UTILPATH + "." + CGUtil.shortClassName(fullModelSetType)+ "Set";
            String importForSet = checkSetImportFor(CGUtil.shortClassName(dataType.getValue()));
            
            if (!isEnumType(model, ownerClazz)) {
            
	            if(importForSet != null)
	            {
	               importClassesFromTypes.add(importForSet);
	            }
	            else if ( ! fullModelSetType.startsWith("."))
	            {
	               importClassesFromTypes.add(fullModelSetType);
	            }
            }
         }
         
         String add = "add";
         
         if (dataType.getValue().contains("<") || dataType.getValue().endsWith("Set")) 
         {
            add = "addAll";
         }

         if (CGUtil.isPrimitiveType(fullModelSetType)) {
            modelSetType = CGUtil.shortClassName(fullModelSetType) + "List";
            fullModelSetType = "org.sdmlib.models.modelsets."
                  + modelSetType;
            importClassesFromTypes.add(fullModelSetType);
//            importClassesFromTypes.add("java.util.List");
         }
         else
         {
            // check for enum types
            try
            {
               String innerClassName = CGUtil.packageName(fullModelSetType) + "$" + CGUtil.shortClassName(fullModelSetType); 
               Class<?> forName = Class.forName(innerClassName);
               
               if (forName.isEnum())
               {
                  // use an ArrayList<Enum> as ModelSetType
                  modelSetType = "ArrayList<ElemType>".replaceAll("ElemType", CGUtil.shortClassName(fullModelSetType));
                  importClassesFromTypes.remove(importClassesFromTypes.size()-1);
                  importClassesFromTypes.add("java.util.ArrayList");
               }
            }
            catch (ClassNotFoundException e)
            {
               // did not find class on class path. Thus, it probably still needs to be generated. Ignore 
               // e.printStackTrace();
            }
         }
            
         String objectSetType = CGUtil.shortClassName(ownerClazz.getFullName() + "Set");

         String valueComparison = "value.equals(obj.get" + StrUtil.upFirstChar(model.getName()) + "())";
         String rangeCheck = "lower.compareTo(obj.get" + StrUtil.upFirstChar(model.getName()) + "()) <= 0 && obj.get" + StrUtil.upFirstChar(model.getName()) + "().compareTo(upper) <= 0";
         
         if ( ! DataType.STRING.getValue().equals(model.getType().getValue()))
         {
            valueComparison = "value == obj.get" + StrUtil.upFirstChar(model.getName()) + "()";
            if ("boolean".equalsIgnoreCase(model.getType().getValue()))
            {
               valueComparison = "value == obj.is" + StrUtil.upFirstChar(model.getName()) + "()";
            }
            rangeCheck = "lower <= obj.get" + StrUtil.upFirstChar(model.getName()) + "() && obj.get" + StrUtil.upFirstChar(model.getName()) + "() <= upper";
         }
         
         CGUtil.replaceAll(text, "ContentType",
            CGUtil.shortClassName(ownerClazz.getFullName()),
            "VALUEGET", attrNameGetter,
            "ModelSetType", modelSetType, 
            "Name", StrUtil.upFirstChar(model.getName()), 
            "addOneOrMore", add, 
            "ObjectSetType", objectSetType, 
            "AttrType",  CGUtil.shortClassName(model.getType().getValue()),
            "valueComparison", valueComparison,
            "rangeCheck", rangeCheck
            );

         //       importClassesFromTypes.addAll(checkImportClassesFromType(fullModelSetType));
         //       
         //       ownerClazz.printFile(true);
         //       
         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());

         // getClazz()
         for (String setType : importClassesFromTypes) {
            getGenerator(ownerClazz).insertImport(parser, setType);
         }

      }
   }
   private void insertCaseInGenericGetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      int pos = parser.indexOf(Parser.METHOD + ":getValue(Object,String)");

      if (pos < 0)
      {
         if (model.getClazz().isInterface())
            // ups, did not find generic get method. 
            System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for class " + CGUtil.shortClassName(model.getClazz().getFullName()) + "Creator" 
               + ": \nDid not find method get(Object,String). Should have been generated by my clazz. " 
               + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

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
               (  "\n      if (entitiyClassName.PROPERTY_NAME.equalsIgnoreCase(attribute))" +
                     "\n      {" +
                     "\n         return ((entitiyNameClass) target).VALUEGET;" +
                     "\n      }" +
                     "\n" 
                     );

         String name = StrUtil.upFirstChar(model.getName());
         String attrNameGetter;
         if ("boolean".equalsIgnoreCase(model.getType().getValue())){
            attrNameGetter = "is"+name+"()";
         }else{
            attrNameGetter = "get"+name+"()";
         }
         if(model.getVisibility().same(Visibility.PUBLIC)){
            attrNameGetter = model.getName();
         }
         
         String entitiyClassName = CGUtil.shortClassName(model.getClazz().getFullName());
         String entitiyNameClass = entitiyClassName;
         if(model.getClazz().isExternal()){
            entitiyClassName = CGUtil.shortClassName(model.getClazz().getName()+"Creator");
         }

         CGUtil.replaceAll(text,
            "VALUEGET", attrNameGetter,
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase(), 
            "entitiyClassName", entitiyClassName,
            "entitiyNameClass", entitiyNameClass
               );

         parser.insert(lastIfEndPos, text.toString());
      }
   }

   private void insertSetterInModelSetClass(Parser parser, Clazz ownerClazz) {
      if(parser==null){
         return;
      }
      String attrType = getGenerator( ownerClazz).shortNameAndImport(model.getType().getValue(), parser);
      attrType = CGUtil.shortClassName(attrType);
      String key = Parser.METHOD + ":with"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public ModelSetType withName(AttrType value)\n"
                  + "   {\n"
                  + "      for (ContentType obj : this)\n"
                  + "      {\n"
                  + "         obj.VALUESET;\n"
                  + "      }\n" + "      \n"
                  + "      return this;\n" 
                  + "   }\n" 
                  + "\n");

//         String fullModelSetType = getType();
         String modelSetType = CGUtil.shortClassName(ownerClazz.getFullName()) + "Set";

         String name = StrUtil.upFirstChar(model.getName());
         String attrNameSetter = "set"+name+"(value)";
         if(model.getVisibility().same(Visibility.PUBLIC)){
            attrNameSetter = model.getName() + " = value";
         }
         
         CGUtil.replaceAll(text,
            "VALUESET", attrNameSetter,
            "AttrType", attrType,
            "ContentType", CGUtil.shortClassName(ownerClazz.getFullName()),
            "ModelSetType", modelSetType, 
            "Name", StrUtil.upFirstChar(model.getName()));

         // ownerClazz.printFile(true);
         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.insert(classEnd, text.toString());
      }
   }
   private void insertGenericGetSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      if(model.getVisibility().same(Visibility.PRIVATE)){
         insertCaseInGenericGetForWrapperInCreatorClass(parser, ownerClazz);
         insertCaseInGenericSetForWrapperInCreatorClass(parser, ownerClazz);
      }
   }

   private void insertCaseInGenericSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      int pos = parser.indexOf(Parser.METHOD + ":setValue(Object,String,Object,String)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for wrapped class " + model.getClazz().getFullName() 
            + ": \nDid not find method set(Object,String,Object,String) in creator class. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

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
               (  "\n      if (entitiyClassName.PROPERTY_NAME.equalsIgnoreCase(attrName))" +
                     "\n      {" +
                     "\n         ((entitiyNameClass) target).VALUESET;" +
                     "\n         return true;" +
                     "\n      }" +
                     "\n" 
                     );

         String typePlaceholder = "type";
         DataType dataType = model.getType();
         String type = dataType.getValue();
         type = CGUtil.shortClassName(type);
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
         else if (isEnumType(model, ownerClazz))
         {
        	 //Suit.valueOf((String)
            type = CGUtil.shortClassName(model.getType().getValue())+".valueOf((String) value)";
         }
         
         String name = StrUtil.upFirstChar(model.getName());
         String attrNameSetter = "with"+name+"((type) value)";
         if (isEnumType(model, ownerClazz)) {
        	 attrNameSetter = "with"+name+"(type)";
         }
         if(model.getVisibility().same(Visibility.PUBLIC)){
            attrNameSetter = model.getName() + " = (type) value";
         }
         
         attrNameSetter = CGUtil.replaceAll(attrNameSetter, typePlaceholder, type);
         
         String entitiyClassName = CGUtil.shortClassName(model.getClazz().getFullName());
         String entitiyNameClass = entitiyClassName;
         if(model.getClazz().isExternal()){
            entitiyClassName += "Creator"; 
         }
         
         CGUtil.replaceAll(text, 
            "VALUESET", attrNameSetter,            
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase(),
            "entitiyClassName", entitiyClassName,
            "entitiyNameClass", entitiyNameClass
               );

         parser.insert(lastIfEndPos, text.toString());

         if (checkIsExternalFor(type)) {
        	 String iMport = checkImportFor(type);
        	 
        	 if (iMport != null)		 
        		 getGenerator(ownerClazz).insertImport(parser, iMport);
         }
         
         if (isEnumType(model, ownerClazz)){
        	 getGenerator(ownerClazz).insertImport(parser, model.getType().getValue());
         }
      }
   }
   public GenAttribute generate(String rootDir, String helpersDir)
   {
      generate(model.getClazz(), rootDir, helpersDir, true);

      return this;
   } 

   public GenAttribute generate(String rootDir, String helpersDir, boolean doGenerate)
   {
      generate(model.getClazz(), rootDir, helpersDir, doGenerate);

      return this;
   } 

   public GenAttribute generate(Clazz clazz, String rootDir, String helpersDir)
   {
      return generate( clazz,  rootDir,  helpersDir,  false);
   }
   
   @Override
   public String toString()
   {
      return "gen " + model; 
   }

   public GenAttribute generate(Clazz clazz, String rootDir, String helpersDir, boolean fromSuperClass)
   {
      // get parser from class
      Parser parser;
      if (! clazz.isExternal())
      {
         parser = getGenerator( clazz).getOrCreateParser(rootDir);
         if (!fromSuperClass)
         {
            insertAttrDeclPlusAccessors(clazz, parser);
         }
         if ( !clazz.isInterface())
         {
            insertCaseInToString(parser);
         }

         getGenerator( clazz).printFile();
      }

      if(model.getVisibility().same(Visibility.PRIVATE)){
    	  
//    	  if (!isEnumType(model, clazz)) {
   		  if ( !clazz.isInterface() && clazz.hasFeature(Feature.Serialization) )
    		  {
    			  Parser creatorParser = getGenerator( clazz).getOrCreateParserForCreatorClass(helpersDir);
    			  
    			  insertPropertyInCreatorClass(creatorParser, clazz );
    			  
    			  getGenerator( clazz).printFile(creatorParser);
    		  }
    		  
    		  Parser modelSetParser = getGenerator( clazz).getOrCreateParserForModelSetFile(helpersDir);
    		  insertGetterInModelSetClass(modelSetParser, clazz);
    		  insertSetterInModelSetClass(modelSetParser, clazz);
    		  getGenerator( clazz).printFile(modelSetParser);
    		  
    		  Parser patternObjectParser = getGenerator( clazz).getOrCreateParserForPatternObjectFile(helpersDir);
    		  insertHasMethodInPatternObjectClass(patternObjectParser, clazz);
    		  insertGetterInPatternObjectClass(patternObjectParser, clazz);
    	  }
//      }

      return this;
   }

	public boolean isEnumType(Attribute model, Clazz clazz) {
		DataType dataType = model.getType();
		String value = dataType.getValue();
		for (Enumeration enumeration : clazz.getClassModel().getEnumerations()) {
			String fullName = enumeration.getFullName();
			if (value.equals(fullName)) {
				return true;
			}
		}
		return false;
	}

	public void insertPropertyInCreatorClass(String className, Parser creatorParser, String helpersDir, boolean doGenerate) 
   {
      insertPropertyInCreatorClass(creatorParser, model.getClazz());

      int pos = creatorParser.indexOf(Parser.IMPORT);

      String prefix = "";
      StringBuilder fileBody = creatorParser.getText();
      if (fileBody .indexOf(Parser.IMPORT, pos) < 0)
      {
         prefix = "\n";
      }

      SymTabEntry symTabEntry = creatorParser.getSymTab().get(Parser.IMPORT + ":" + className);
      if (symTabEntry == null)
      {
         creatorParser.insert(creatorParser.getEndOfImports() + 1, 
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
         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for creator class for " + model.getClazz().getFullName() 
            + ": \nDid not find properties field. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int endOfStringArrayInit = parser.getEndOfAttributeInitialization();

      String propertyName = "PROPERTY_" + model.getName().toUpperCase();

      int propertyNameIndex = parser.search(propertyName, pos);

      if (propertyNameIndex < 0 || propertyNameIndex > endOfStringArrayInit)
      {         
         // need to add property to string array

         StringBuilder text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

         CGUtil.replaceAll(text, 
            "className", CGUtil.shortClassName(model.getClazz().getFullName()),
            "PROPERTY_NAME", propertyName
               );

         if (ownerClazz.isExternal())
         {
            // declare the property in the creator class
            text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

            CGUtil.replaceAll(text, 
               "className.PROPERTY_NAME", propertyName
                  );
         }

         parser.insert(endOfStringArrayInit, text.toString());

         if (ownerClazz.isExternal())
         {
            // declare the property
            text = new StringBuilder(  "public static final String PROPERTY_NAME = \"propertyName\";\n   ");

            CGUtil.replaceAll(text, 
               "PROPERTY_NAME", propertyName,
               "propertyName", model.getName()
                  );

            parser.insert(pos, text.toString());
            
         }
         insertGenericGetSetForWrapperInCreatorClass(parser, ownerClazz);

         getGenerator( ownerClazz).insertImport(parser, model.getClazz().getFullName());
      }
   }
}

