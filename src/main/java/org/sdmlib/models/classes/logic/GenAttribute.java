package org.sdmlib.models.classes.logic;

import java.util.ArrayList;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.templates.AddTemplate;
import org.sdmlib.models.classes.templates.AttributeTemplates;
import org.sdmlib.models.classes.templates.ExistTemplate;
import org.sdmlib.models.classes.templates.Template;
import org.sdmlib.models.pattern.AttributeConstraint;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Enumeration;
import de.uniks.networkparser.graph.GraphModel;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Interfaze;
import de.uniks.networkparser.graph.Modifier;

public class GenAttribute extends Generator<Attribute>
{
   private void insertAttrDeclPlusAccessors(Clazz clazz, Parser parser)
   {
      parser.indexOf(Parser.CLASS_END);
      // add attribute declaration and get, set, with methods in class file
      ExistTemplate templates;
      if (clazz instanceof Interfaze)
      {
    	  templates = AttributeTemplates.insertPropertyInInterface(model);
      }
      else
      {
    	  templates = AttributeTemplates.insertPropertyInClass(model);
      }
	  templates.insert(parser, (ClassModel) model.getClazz().getClassModel(),
			  	"name", model.getName(),
			  	"type", CGUtil.shortClassName(model.getType().getName(false)),
			  	"modifier", model.getVisibility().getName(),
			  	"init", model.getValue() == null ? "" : " = " +
                        (DataType.STRING.equals(model.getType()) ? "\"" + model.getValue() + "\"" : model.getValue()),
                "ownerclass", CGUtil.shortClassName(clazz.getName(false)));

      ArrayList<String> importClassesFromTypes = checkImportClassesFromType(model.getType());
      for (String typeImport : importClassesFromTypes)
      {
    	  parser.insertImport(typeImport);
      }
   }

   private void insertCaseInToString(Parser parser)
   {
      // if constant field -> return
	   if(model.getVisibility().has(Modifier.STATIC)) {
		   return;
	   }
      if (model.getVisibility().has(Modifier.PUBLIC)
            && model.getVisibility().has(Modifier.STATIC)
            && model.getVisibility().has(Modifier.FINAL)
            && model.getValue() != null)
         return;

      if ("String int double float".indexOf(CGUtil.shortClassName(model.getType().getName(false))) < 0)
      {
         // only standard types contribute to toString()
         return;
      }

      // do we have a toString() method?
      Template attrtoString = new Template(Parser.METHOD + ":toString()");
		attrtoString.withTemplate(
	               "\n" +
	                     "\n   @Override" +
	                     "\n   public String toString()\n" +
	                     "   {\n" +
	                     "      StringBuilder result = new StringBuilder();\n" +
	                     "      \n" +
	                     "      return result.substring(1);\n" +
	                     "   }\n\n"
	               );
		attrtoString.insert(parser);
      
      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      
      AddTemplate templateAttr = new AddTemplate(methodBodyStartPos, "get" + StrUtil.upFirstChar(model.getName()), ".append(" + model.getName() + ")");
      templateAttr.withLast("return");
      templateAttr.withTemplate("result.append(\" \").append(this.get{{Name}}());\n      ");
      templateAttr.insert(parser, (ClassModel) model.getClazz().getClassModel(), "name", model.getName());
   }

   private ArrayList<String> checkImportClassesFromType(DataType value)
   {
      String modelSetType = value.getName(false);
      ArrayList<String> importList = new ArrayList<String>();
      modelSetType = modelSetType.trim();
      int index = modelSetType.indexOf("<");

      if (index >= 0)
      {
         String listType = modelSetType.substring(0, index);

         if ("List Vector HashSet HashMap".indexOf(listType) >= 0)
         {
            listType = "java.util." + listType;
         }

         importList.add(listType);
         String substring = modelSetType.substring(index + 1, modelSetType.lastIndexOf(">"));
         String[] strings = substring.split(",");

         for (String string : strings)
         {
            Clazz findClass = ((ClassModel) model.getClazz().getClassModel()).getGenerator().findClass(string);
            if (findClass != null)
            {
               importList.add(findClass.getName(false));
            }
         }
      }
      else
      {
         Clazz findClass = ((ClassModel) model.getClazz().getClassModel()).getGenerator().findClass(modelSetType);

         if (findClass != null)
         {
            importList.add(findClass.getName(false));
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
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);
      
      Template template = new Template(Parser.METHOD + ":create{{Name}}({{AttrType}})");
      template.withTemplate("   public {{PatternObjectType}} create{{Name}}({{AttrType}} value)\n" +
              "   {\n" +
              "      this.startCreate().has{{Name}}(value).endCreate();\n" +
              "      return this;\n" +
              "   }\n" +
              "   \n");
      
      String patternObjectType = CGUtil.shortClassName(ownerClazz.getName(false)) + "PO";
     template.insert(parser, "PatternObjectType", patternObjectType,
               "name", StrUtil.upFirstChar(model.getName()),
               "AttrType", attrType);
   }

   private void insertGetterInPatternObjectClass(Parser parser, Clazz ownerClazz)
   {
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);

      Template template = new Template(Parser.METHOD + ":get{{Name}}()");
      template.withTemplate("   public {{AttrType}} get{{Name}}()\n" +
              "   {\n" +
              "      if (this.getPattern().getHasMatch())\n" +
              "      {\n" +
              "         return (({{ModelClass}}) getCurrentMatch()).{{ValueGet}};\n" +
              "      }\n" +
              "      return {{nullValue}};\n" +
              "   }\n" +
              "   \n" +
              "   public {{ModelClass}}PO with{{Name}}({{AttrType}} value)\n" +
              "   {\n" +
              "      if (this.getPattern().getHasMatch())\n" +
              "      {\n" +
              "         (({{ModelClass}}) getCurrentMatch()).{{ValueSet}};\n" +
              "      }\n" +
              "      return this;\n" +
              "   }\n" +
              "   \n");
		String nullValue = "null";

		if ("int short byte long double float char".indexOf(attrType) >= 0) {
			nullValue = "0";
		} else if ("boolean".equalsIgnoreCase(attrType)) {
			nullValue = "false";
		}
		String name = StrUtil.upFirstChar(model.getName());
		String attrNameGetter = "get" + name + "()";
		if ("boolean".equalsIgnoreCase(attrType)) {
			attrNameGetter = "is" + name + "()";
		}
		String attrNameSetter = "set" + name + "(value)";
		if (model.getVisibility().equals(Modifier.PUBLIC)) {
			attrNameGetter = model.getName();
			attrNameSetter = model.getName() + " = value";
		}
		
		template.insert(parser, (ClassModel) model.getClazz().getClassModel(),
               "nullValue", nullValue,
               "ValueGet", attrNameGetter,
               "ValueSet", attrNameSetter,
               "name", name,
               "AttrType", attrType,
               "ModelClass", getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getName(false), parser));
   }

   private String checkImportFor(String type)
   {
      Clazz findClass = ((ClassModel) model.getClazz().getClassModel()).getGenerator().findClass(type);
      if (findClass == null)
         return null;
      Clazz attributClass = model.getClazz();
      String packageNameFromFindClass = CGUtil.packageName(findClass.getName(false));
      String packageNameFromOwnerClass = CGUtil.packageName(attributClass.getName(false));
      if (findClass.isExternal())
      {
         return packageNameFromFindClass + "." + CGUtil.shortClassName(findClass.getName(false));
      }
      else if (!packageNameFromFindClass.equals(packageNameFromOwnerClass))
      {
         return packageNameFromOwnerClass + "." + CGUtil.shortClassName(findClass.getName(false));
      }
      return null;
   }

   private boolean checkIsExternalFor(String type)
   {
      Clazz findClass = ((ClassModel) model.getClazz().getClassModel()).getGenerator().findClass(type);
      if (findClass == null)
         return false;
      else if (findClass.isExternal())
      {
         return true;
      }
      else
      {
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
      if ("int long float double String".indexOf(model.getType().getName(false)) < 0)
      {
         // range query only for numbers and strings
         return;
      }
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);
      Template template = new Template(Parser.METHOD + ":has{{Name}}({{AttrType}},{{AttrType}})");
      template.withTemplate("   public {{PatternObjectType}} has{{Name}}({{AttrType}} lower, {{AttrType}} upper)\n" +
              "   {\n" +
              "      new AttributeConstraint()\n" +
              "      .withAttrName({{ModelClass}}.PROPERTY_{{NAME}})\n" +
              "      .withTgtValue(lower)\n" +
              "      .withUpperTgtValue(upper)\n" +
              "      .withSrc(this)\n" +
              "      .withModifier(this.getPattern().getModifier())\n" +
              "      .withPattern(this.getPattern());\n" +
              "      \n" +
              "      super.hasAttr();\n" +
              "      \n" +
              "      return this;\n" +
              "   }\n" +
              "   \n");
		parser.insertImport(AttributeConstraint.class.getName());
		String patternObjectType = CGUtil.shortClassName(ownerClazz.getName(false)) + "PO";

		String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getName(false), parser);

		if (ownerClazz.isExternal()) {
			modelClass = modelClass + "Creator";
		}
		template.insert(parser, 
				"PatternObjectType", patternObjectType,
				"name", model.getName(),
				"AttrType", attrType,
				"ModelClass", modelClass);

   }

   private void insertHasMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz)
   {
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);
      Template template = new Template(Parser.METHOD + ":has{{Name}}({{AttrType}})");
      template.withTemplate("   public {{PatternObjectType}} has{{Name}}({{AttrType}} value)\n" +
              "   {\n" +
              "      new AttributeConstraint()\n" +
              "      .withAttrName({{ModelClass}}.PROPERTY_{{NAME}})\n" +
              "      .withTgtValue(value)\n" +
              "      .withSrc(this)\n" +
              "      .withModifier(this.getPattern().getModifier())\n" +
              "      .withPattern(this.getPattern());\n" +
              "      \n" +
              "      super.hasAttr();\n" +
              "      \n" +
              "      return this;\n" +
              "   }\n" +
              "   \n");
		parser.insertImport(AttributeConstraint.class.getName());

		ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
		Clazz clazz = classModel.getClazz(model.getType().getName(false));
		if (clazz != null) {
			parser.insertImport(clazz.getName(false));
		}
		String patternObjectType = CGUtil.shortClassName(ownerClazz.getName(false)) + "PO";

		String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getName(false), parser);

		if (ownerClazz.isExternal()) {
			modelClass = modelClass + "Creator";
		}
		template.insert(parser, 
           "PatternObjectType", patternObjectType,
           "name", model.getName(),
           "AttrType", attrType,
           "ModelClass", modelClass);
   }

   private void insertGetterInModelSetClass(Parser parser, Clazz ownerClazz)
   {
      if (parser == null)
      {
         return;
      }
      ExistTemplate allTemplate=new ExistTemplate();
      Template templateGetter = new Template(Parser.METHOD + ":get{{Name}}()");
      templateGetter.withTemplate("\n" + 
         "   /**\n" + 
         "    * Loop through the current set of {{ContentType}} objects and collect a list of the {{name}} attribute values. \n" + 
         "    * \n" + 
         "    * @return List of {{ModelType}} objects reachable via {{name}} attribute\n" + 
         "    */\n"
         + "   public {{ModelSetType}} get{{Name}}()\n"
         + "   {\n"
         + "      {{ModelSetType}} result = new {{ModelSetType}}();\n"
         + "      \n"
         + "      for ({{ContentType}} obj : this)\n"
         + "      {\n"
         + "         result.{{addOneOrMore}}(obj.{{ValueGet}});\n"
         + "      }\n" + "      \n"
         + "      return result;\n"
         + "   }\n"
         + "\n");

      Template templateHas = new Template(Parser.METHOD + ":has{{Name}}({{AttrType}})");
      templateHas.withTemplate("\n" + 
            "   /**\n" + 
            "    * Loop through the current set of {{ContentType}} objects and collect those {{ContentType}} objects where the {{name}} attribute matches the parameter value. \n" + 
            "    * \n" + 
            "    * @param value Search value\n" + 
            "    * \n" + 
            "    * @return Subset of {{ContentType}} objects that match the parameter\n" + 
            "    */\n"
            + "   public {{ObjectSetType}} has{{Name}}({{AttrType}} value)\n" +
              "   {\n" +
              "      {{ObjectSetType}} result = new {{ObjectSetType}}();\n" +
              "      \n" +
              "      for ({{ContentType}} obj : this)\n" +
              "      {\n" +
              "         if ({{valueComparison}})\n" +
              "         {\n" +
              "            result.add(obj);\n" +
              "         }\n" +
              "      }\n" +
              "      \n" +
              "      return result;\n" +
              "   }\n" +
              "\n");
      Template templateHasUpper = new Template(Parser.METHOD + ":has{{Name}}({{AttrType}},{{AttrType}})");
      templateHasUpper.withCondition(" int long float double String ".indexOf(" " + model.getType().getName(false) + " ") >= 0);
      templateHasUpper.withTemplate("\n" + 
            "   /**\n" + 
            "    * Loop through the current set of {{ContentType}} objects and collect those {{ContentType}} objects where the {{name}} attribute is between lower and upper. \n" + 
            "    * \n" + 
            "    * @param lower Lower bound \n" + 
            "    * @param upper Upper bound \n" + 
            "    * \n" + 
            "    * @return Subset of {{ContentType}} objects that match the parameter\n" + 
            "    */\n"
            + "   public {{ObjectSetType}} has{{Name}}({{AttrType}} lower, {{AttrType}} upper)\n" +
                        "   {\n" +
                        "      {{ObjectSetType}} result = new {{ObjectSetType}}();\n" +
                        "      \n" +
                        "      for ({{ContentType}} obj : this)\n" +
                        "      {\n" +
                        "         if ({{rangeCheck}})\n" +
                        "         {\n" +
                        "            result.add(obj);\n" +
                        "         }\n" +
                        "      }\n" +
                        "      \n" +
                        "      return result;\n" +
                        "   }\n"
                        + "\n"); 
      
      allTemplate.withTemplates(templateGetter, templateHas, templateHasUpper);


     DataType dataType = model.getType();
     String fullModelSetType = dataType.getName(false);
     String modelSetType = CGUtil.shortClassName(fullModelSetType);

     ArrayList<String> importClassesFromTypes = new ArrayList<String>();

     if (!CGUtil.isPrimitiveType(fullModelSetType) && !fullModelSetType.contains("<") && !fullModelSetType.endsWith("Set"))
     {
        ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
		Clazz clazz = classModel.getClazz(model.getType().getName(false));
        if (clazz != null)
        {
        	parser.insertImport(clazz.getName(false));
        }

        modelSetType = CGUtil.shortClassName(fullModelSetType) + "Set";
     }

     String add = "add";

     if(isMap(dataType))
     {
        add = "withAll";
     } else if (isSet(dataType)) {
    	 add = "addAll";
     }

     if (CGUtil.isPrimitiveType(fullModelSetType))
     {
    	 if(!fullModelSetType.equalsIgnoreCase("object")) {
    		 modelSetType = CGUtil.shortClassName(fullModelSetType) + "List";
    	 }
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
              if(importClassesFromTypes.size() > 0) {
            	  importClassesFromTypes.remove(importClassesFromTypes.size() - 1);
              }
              importClassesFromTypes.add("java.util.ArrayList");
           }
        }
        catch (ClassNotFoundException e)
        {
           // did not find class on class path. Thus, it probably still needs to be generated. Ignore 
           // e.printStackTrace();
        }
     }
     String objectSetType = CGUtil.shortClassName(ownerClazz.getName(false) + "Set");

     String valueComparison = "value.equals(obj.get" + StrUtil.upFirstChar(model.getName()) + "())";
     String rangeCheck = "lower.compareTo(obj.get" + StrUtil.upFirstChar(model.getName()) + "()) <= 0 && obj.get" + StrUtil.upFirstChar(model.getName())
           + "().compareTo(upper) <= 0";

     if (!DataType.STRING.getName(false).equals(model.getType().getName(false)))
     {
        valueComparison = "value == obj.get" + StrUtil.upFirstChar(model.getName()) + "()";
        if ("boolean".equalsIgnoreCase(model.getType().getName(false)))
        {
           valueComparison = "value == obj.is" + StrUtil.upFirstChar(model.getName()) + "()";
        }
        rangeCheck = "lower <= obj.get" + StrUtil.upFirstChar(model.getName()) + "() && obj.get" + StrUtil.upFirstChar(model.getName()) + "() <= upper";
     }
     String name = StrUtil.upFirstChar(model.getName());
     String attrNameGetter = "get" + name + "()";
     if (model.getVisibility().equals(Modifier.PUBLIC))
     {
        attrNameGetter = model.getName();
     } else if ("boolean".equalsIgnoreCase(model.getType().getName(false)))
     {
        attrNameGetter = "is" + name + "()";
     }
     allTemplate.insert(parser, "ContentType", CGUtil.shortClassName(ownerClazz.getName(false)),
           "ValueGet", attrNameGetter,
           "ModelSetType", modelSetType,
           "ModelType", model.getType().getName(false),
           "name", model.getName(),
           "addOneOrMore", add,
           "ObjectSetType", objectSetType,
           "AttrType", CGUtil.shortClassName(model.getType().getName(false)),
           "valueComparison", valueComparison,
           "rangeCheck", rangeCheck
           );
     // getClazz()
     for (String setType : importClassesFromTypes)
     {
    	 parser.insertImport(setType);
     }
   }
   private boolean isMap(DataType dataType) {
	   String value = dataType.getName(false);
	   int pos = value.indexOf("<");
	   if(pos > 0) {
		   int end = value.indexOf(">");
		   if(end < pos) {
			   return false;
		   }
		   boolean found = false;
		   while(pos < end) {
			   if ( value.charAt(pos) == ',') {
				   found = true;
				   break;
			   }
			   pos ++;
		   }
		   return found;
	   }
	   return value.endsWith("Map");
   }

   
   private boolean isSet(DataType dataType) {
	   return (dataType.getName(false).contains("<") || dataType.getName(false).endsWith("Set"));
   }

   private void insertCaseInGenericGetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
	   Template template = new Template(Parser.METHOD + ":getValue(Object,String)");
	   if(template.validate(parser)) {
         if (model.getClazz() instanceof Interfaze)
            // ups, did not find generic get method. 
            //            System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for class " + CGUtil.shortClassName(model.getClazz().getFullName()) + "Creator"
            //                  + ": \nDid not find method get(Object,String). Should have been generated by my clazz. "
            //                  + "\nCould not add required code fragment there. :( ");

         return;
      }
      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

		Template templateProperty = new Template(Parser.NAME_TOKEN + ":PROPERTY_{{NAME}});");

		// need to add if block to generic get method
		parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
		int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to
													// be after \n
		if (lastIfEndPos - 2 < 0) {
			lastIfEndPos = methodBodyStartPos + 1;
		}
		templateProperty.withOffset(lastIfEndPos);         
		templateProperty.withTemplate("\n      if ({{entitiyClassName}}.PROPERTY_{{NAME}}.equalsIgnoreCase(attribute))" +
                     "\n      {" +
                     "\n         return (({{entitiyNameClass}}) target).{{ValueGet}};" +
                     "\n      }" +
                     "\n"
               );
         String attrNameGetter;
         if (model.getVisibility().equals(Modifier.PUBLIC))
         {
            attrNameGetter = model.getName();
         }else if ("boolean".equalsIgnoreCase(model.getType().getName(false)))
         {
            attrNameGetter = "is{{Name}}()";
         }
         else
         {
            attrNameGetter = "get{{Name}}()";
         }
         String entitiyClassName = CGUtil.shortClassName(model.getClazz().getName(false));
         String entitiyNameClass = entitiyClassName;
         if (model.getClazz().isExternal())
         {
            entitiyClassName = CGUtil.shortClassName(model.getClazz().getName() + "Creator");
         }
         templateProperty.withPos(methodBodyStartPos);
         templateProperty.insert(parser,
               "ValueGet", attrNameGetter,
               "name", model.getName(),
               "entitiyClassName", entitiyClassName,
               "entitiyNameClass", entitiyNameClass
               );
   }

   private void insertSetterInModelSetClass(Parser parser, Clazz ownerClazz)
   {
      if (parser == null)
      {
         return;
      }
      Template template = new Template(Parser.METHOD + ":with{{Name}}({{AttrType}})");
      template.withTemplate("\n" + 
            "   /**\n" + 
            "    * Loop through the current set of {{ContentType}} objects and assign value to the {{name}} attribute of each of it. \n" + 
            "    * \n" + 
            "    * @param value New attribute value\n" + 
            "    * \n" + 
            "    * @return Current set of {{ContentType}} objects now with new attribute values.\n" + 
            "    */\n"
            + "   public {{ModelSetType}} with{{Name}}({{AttrType}} value)\n"
              + "   {\n"
              + "      for ({{ContentType}} obj : this)\n"
              + "      {\n"
              + "         obj.{{ValueSet}};\n"
              + "      }\n" + "      \n"
              + "      return this;\n"
              + "   }\n"
              + "\n");
      
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);

     //         String fullModelSetType = getType();
     String modelSetType = CGUtil.shortClassName(ownerClazz.getName(false)) + "Set";

     String name = StrUtil.upFirstChar(model.getName());
     String attrNameSetter = "set" + name + "(value)";
     if (model.getVisibility().equals(Modifier.PUBLIC))
     {
        attrNameSetter = model.getName() + " = value";
     }
     template.insert(parser, 
               "ValueSet", attrNameSetter,
               "AttrType", attrType,
               "ContentType", CGUtil.shortClassName(ownerClazz.getName(false)),
               "ModelSetType", modelSetType,
               "name", model.getName());
   }

   private void insertGenericGetSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      if (model.getVisibility().equals(Modifier.PRIVATE))
      {
         insertCaseInGenericGetForWrapperInCreatorClass(parser, ownerClazz);
         insertCaseInGenericSetForWrapperInCreatorClass(parser, ownerClazz);
      }
   }

   private void insertCaseInGenericSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
	   Template template = new Template(Parser.METHOD + ":setValue(Object,String,Object,String)");
	   if(template.validate(parser)) {
         // ups, did not find generic set method. 
	      //         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for wrapped class " + model.getClazz().getFullName()
	      //               + ": \nDid not find method set(Object,String,Object,String) in creator class. Should have been generated by my clazz. "
	      //               + "\nCould not add required code fragment there. :( ");

         return;
      }
      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      Template templateProperty = new Template(Parser.NAME_TOKEN + ":PROPERTY_{{NAME}}");
      templateProperty.withOffset(methodBodyStartPos +1);
      templateProperty.withTemplate("\n      if ({{entitiyClassName}}.PROPERTY_{{NAME}}.equalsIgnoreCase(attrName))" +
              "\n      {" +
              "\n         (({{entitiyNameClass}}) target).{{ValueSet}};" +
              "\n         return true;" +
              "\n      }" +
              "\n");
      
     // need to add if block to generic set method
     parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

     int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
     if (lastIfEndPos - 2 < 0)
     {
        lastIfEndPos = methodBodyStartPos + 1;
     }
     String typePlaceholder = "type";
     DataType dataType = model.getType();
     String type = dataType.getName(false);
     if(!dataType.getClazz().isExternal()) {
    	 type = CGUtil.shortClassName(type);
     }
     boolean modelClass = true;
     boolean isEnum = false;
     if ("int".equals(type))
     {
        typePlaceholder = "(type) value";
        type = "Integer.parseInt(value.toString())";
        modelClass = false;
     }
     else if ("long".equals(type))
     {
        typePlaceholder = "(type) value";
        type = "Long.parseLong(value.toString())";
        modelClass = false;
     }
     else if ("double".equals(type))
     {
        typePlaceholder = "(type) value";
        type = "Double.parseDouble(value.toString())";
        modelClass = false;
     }
     else if ("float".equals(type))
     {
        typePlaceholder = "(type) value";
        type = "Float.parseFloat(value.toString())";
        modelClass = false;
     }
     else if ("boolean".equals(type))
     {
        type = "Boolean";
        modelClass = false;
     }
     else if (isEnumType(model, ownerClazz, false))
     {
        //Suit.valueOf((String)
        type = CGUtil.shortClassName(model.getType().getName(false)) + ".valueOf((String) value)";
        modelClass = false;
        isEnum = true;
     }
     else if (isEnumType(model, ownerClazz, true))
     {
        type = CGUtil.shortClassName(model.getType().getName(false)) + ".valueOf((String) value)";
        parser.insertImport(model.getType().getName(false));
        isEnum = true;
     }

     String name = StrUtil.upFirstChar(model.getName());
     String attrNameSetter = "with" + name + "((type) value)";
     if (isEnum)
     {
        attrNameSetter = "with" + name + "(type)";
     }
     if (model.getVisibility().equals(Modifier.PUBLIC))
     {
        attrNameSetter = model.getName() + " = (type) value";
     }

     attrNameSetter = CGUtil.replaceAll(attrNameSetter, typePlaceholder, type);

     String entitiyClassName = CGUtil.shortClassName(model.getClazz().getName(false));
     String entitiyNameClass = entitiyClassName;
     if (model.getClazz().isExternal())
     {
        entitiyClassName += "Creator";
     }
     templateProperty.withPos(methodBodyStartPos);
     templateProperty.insert(parser,
               "ValueSet", attrNameSetter,
               "name", model.getName(),
               "entitiyClassName", entitiyClassName,
               "entitiyNameClass", entitiyNameClass
               );

     // Is Nessessary??
     if (checkIsExternalFor(type))
     {
        String iMport = checkImportFor(type);

        if (iMport != null)
        	parser.insertImport(iMport);
     }

     if (isEnumType(model, ownerClazz, false))
     {
    	 parser.insertImport(model.getType().getName(false));
     }
     if (modelClass)
     {
        ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
		Clazz clazz = classModel.getClazz(model.getType().getName(false));
        if (clazz != null)
        {
        	parser.insertImport(clazz.getName(false));
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
      return generate(clazz, rootDir, helpersDir, false);
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
      if (!clazz.isExternal())
      {
         parser = getGenerator(clazz).getOrCreateParser(rootDir);
         if (!fromSuperClass)
         {
            insertAttrDeclPlusAccessors(clazz, parser);
         }
         if (!(clazz instanceof Interfaze))
         {
            insertCaseInToString(parser);
         }
         for (Annotation annotation : GraphUtil.getAnnotations(model))
         {
            getGenerator(annotation).generate(rootDir, helpersDir);
         }

         getGenerator(clazz).printFile();
      }

      if (model.getVisibility().equals(Modifier.PRIVATE))
      {

         //    	  if (!isEnumType(model, clazz)) {
         if (!(clazz instanceof Interfaze) && ((ClassModel) clazz.getClassModel()).hasFeature(Feature.Serialization))
         {
            Parser creatorParser = getGenerator(clazz).getOrCreateParserForCreatorClass(helpersDir);

            insertPropertyInCreatorClass(creatorParser, clazz);

            getGenerator(clazz).printFile(creatorParser);
         }

         if (((ClassModel) clazz.getClassModel()).hasFeature(Feature.ALBERTsSets))
         {

            Parser modelSetParser = getGenerator(clazz).getOrCreateParserForModelSetFile(helpersDir);
            insertGetterInModelSetClass(modelSetParser, clazz);
            insertSetterInModelSetClass(modelSetParser, clazz);
            getGenerator(clazz).printFile(modelSetParser);
         }

         if (((ClassModel) clazz.getClassModel()).hasFeature(Feature.PatternObject))
         {
         Parser patternObjectParser = getGenerator(clazz).getOrCreateParserForPatternObjectFile(helpersDir);
            insertHasMethodInPatternObjectClass(patternObjectParser, clazz);
            insertGetterInPatternObjectClass(patternObjectParser, clazz);
         }
      }
      //      }

      return this;
   }

   public boolean isEnumType(Attribute model, Clazz clazz, boolean shortName)
   {
      DataType dataType = model.getType();
      String value = dataType.getName(false);
      for (Enumeration enumeration : ((ClassModel) clazz.getClassModel()).getEnumerations())
      {
         String fullName;
         if (shortName)
         {
            fullName = enumeration.getName(true);
         }
         else
         {
            fullName = enumeration.getName(false);
         }

         if (value.equals(fullName))
         {
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
      if (fileBody.indexOf(Parser.IMPORT, pos) < 0)
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
	   //FIXME CHANGE
      String key = Parser.ATTRIBUTE + ":properties";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         // ups, did not find generic get method. 
         //         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for creator class for " + model.getClazz().getFullName()
         //               + ": \nDid not find properties field. Should have been generated by my clazz. "
         //               + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int endOfStringArrayInit = parser.getEndOfAttributeInitialization();

      String propertyName = "PROPERTY_" + model.getName().toUpperCase();

      int propertyNameIndex = parser.search(propertyName, pos);

      if (propertyNameIndex < 0 || propertyNameIndex > endOfStringArrayInit)
      {
         // need to add property to string array

         StringBuilder text = new StringBuilder("   className.PROPERTY_NAME,\n   ");

         CGUtil.replaceAll(text,
               "className", CGUtil.shortClassName(model.getClazz().getName(false)),
               "PROPERTY_NAME", propertyName
               );

         if (ownerClazz.isExternal())
         {
            // declare the property in the creator class
            text = new StringBuilder("   className.PROPERTY_NAME,\n   ");

            CGUtil.replaceAll(text,
                  "className.PROPERTY_NAME", propertyName
                  );
         }

         parser.insert(endOfStringArrayInit, text.toString());

         if (ownerClazz.isExternal())
         {
            // declare the property
            text = new StringBuilder("public static final String PROPERTY_NAME = \"propertyName\";\n   ");

            CGUtil.replaceAll(text,
                  "PROPERTY_NAME", propertyName,
                  "propertyName", model.getName()
                  );

            parser.insert(pos, text.toString());

         }
         insertGenericGetSetForWrapperInCreatorClass(parser, ownerClazz);

         parser.insertImport(model.getClazz().getName(false));
      }
   }

   /**
    * Deletes the generated code of the associated attribute, within the corresponding model, set, creator and pattern object classes.
    * 
    * 
    * @param rootDir root directory, where the code of the associated attribute is located
    */
   public void removeGeneratedCode(String rootDir) {

	   GenClass genClass = getGenerator(this.getModel().getClazz());
	   
	   Parser parser = genClass.getParser();	   
   
	   String attributeName = StrUtil.upFirstChar(this.getModel().getName());
	   
	   String attributeType = this.getModel().getType().getName(false);
	   
	   genClass.removeFragment(parser, Parser.ATTRIBUTE + ":" + this.getModel().getName());
	   
	   genClass.removeFragment(parser, Parser.ATTRIBUTE + ":PROPERTY_" + this.getModel().getName().toUpperCase());
	   
	   genClass.removeFragment(parser, Parser.METHOD + ":get" + attributeName + "()");
	   
	   genClass.removeFragment(parser, Parser.METHOD + ":set" + attributeName + "(" + attributeType + ")");
	   
	   genClass.removeFragment(parser, Parser.METHOD + ":with" + attributeName + "(" + attributeType + ")");
	   
	   genClass.removeLineFromFragment(parser, Parser.METHOD + ":toString()", "get" + attributeName, attributeName);
	   
	   CGUtil.printFile(parser);
	   
	   Parser creatorParser = genClass.getOrCreateParserForCreatorClass(rootDir);
	   
	   String lineContent = "PROPERTY_" + this.getModel().getName().toUpperCase();
	
	   genClass.removeLineFromFragment(creatorParser, Parser.ATTRIBUTE + ":properties", lineContent, lineContent);
   
	   genClass.removeLineFromFragment(creatorParser, Parser.METHOD + ":getValue(Object,String)", lineContent, "}");
	   
	   genClass.removeLineFromFragment(creatorParser, Parser.METHOD + ":setValue(Object,String,Object,String)", lineContent, "}");
	   
	   CGUtil.printFile(creatorParser);
	   
	   Parser poParser = genClass.getOrCreateParserForPatternObjectFile(rootDir);
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":has" + attributeName + "(" + attributeType + ")");
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":has" + attributeName + "(" + attributeType + "," + attributeType + ")");
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":create" + attributeName + "(" + attributeType + ")");
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":get" + attributeName + "()");
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":with" + attributeName + "(" + attributeType + ")");
	   
	   CGUtil.printFile(poParser);
	   
	   Parser setParser = genClass.getOrCreateParserForModelSetFile(rootDir);
   
	   genClass.removeFragment(setParser, Parser.METHOD + ":get" + attributeName + "()");
	   
	   genClass.removeFragment(setParser, Parser.METHOD + ":has" + attributeName + "(" + attributeType + ")");
	   
	   genClass.removeFragment(setParser, Parser.METHOD + ":has" + attributeName + "(" + attributeType + "," + attributeType + ")");
	   
	   genClass.removeFragment(setParser, Parser.METHOD + ":with" + attributeName + "(" + attributeType + ")");
	   
	   CGUtil.printFile(setParser);
   }

	@Override
	ClassModel getClazz() {
		return (ClassModel) getModel().getClazz().getClassModel();
	}
}
