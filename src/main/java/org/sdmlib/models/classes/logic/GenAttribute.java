package org.sdmlib.models.classes.logic;

import java.util.ArrayList;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.templates.AddTemplate;
import org.sdmlib.models.classes.templates.AttributeTemplates;
import org.sdmlib.models.classes.templates.ExistTemplate;
import org.sdmlib.models.classes.templates.Template;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.ClazzType;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeMap;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;

public class GenAttribute extends Generator<Attribute>
{
   private void insertAttrDeclPlusAccessors(Clazz clazz, Parser parser)
   {
      parser.indexOf(Parser.CLASS_END);
      // add attribute declaration and get, set, with methods in class file
      ExistTemplate templates;
      if (GraphUtil.isInterface(clazz))
      {
         templates = AttributeTemplates.insertPropertyInInterface(model);
      }
      else
      {
         templates = AttributeTemplates.insertPropertyInClass(model);
      }
      templates.insert(parser, (ClassModel) model.getClazz().getClassModel(),
         "name", model.getName(),
         "type", model.getType().getName(true),
         "modifier", model.getModifier().getName(),
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
      if(model.getModifier().has(Modifier.STATIC)) {
         return;
      }
      if (model.getModifier().has(Modifier.PUBLIC)
            && model.getModifier().has(Modifier.STATIC)
            && model.getModifier().has(Modifier.FINAL)
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


      AddTemplate templateAttr = new AddTemplate(methodBodyStartPos, 
         "get" + StrUtil.upFirstChar(model.getName()), 
         ".append(" + model.getName() + ")",
         ".append(this." + model.getName() + ")");
      templateAttr.withLast("return");
      if(model.getModifier().has(Modifier.PUBLIC)) {
         templateAttr.withTemplate("result.append(\" \").append(this.{{name}});\n      ");
      }else{
         templateAttr.withTemplate("result.append(\" \").append(this.get{{Name}}());\n      ");
      }
      templateAttr.insert(parser, (ClassModel) model.getClazz().getClassModel(), "name", model.getName());
   }

   private ArrayList<String> checkImportClassesFromType(DataType value)
   {
      String modelSetType = value.getName(false);
      ArrayList<String> importList = new ArrayList<String>();
      modelSetType = modelSetType.trim();
//      int index = modelSetType.indexOf("<");

      if (value instanceof DataTypeSet) {
    	  DataTypeSet setType = (DataTypeSet) value;
    	  importList.add(setType.getClazz().getName());
    	  ArrayList<String> subTypes = checkImportClassesFromType(setType.getGeneric());
    	  importList.addAll(subTypes);
      }else if(value instanceof DataTypeMap) {
    	  DataTypeMap mapType = (DataTypeMap) value;
    	  importList.add(mapType.getClazz().getName());
    	  ArrayList<String> subTypes = checkImportClassesFromType(mapType.getGenericKey());
    	  importList.addAll(subTypes);
    	  subTypes = checkImportClassesFromType(mapType.getGenericValue());
    	  importList.addAll(subTypes);
//         String listType = modelSetType.substring(0, index);
//
//         if ("List Vector HashSet HashMap".indexOf(listType) >= 0)
//         {
//            listType = "java.util." + listType;
//         }
//
//         importList.add(listType);
//         String substring = modelSetType.substring(index + 1, modelSetType.lastIndexOf(">"));
//         if(modelSetType.indexOf("<")>0 ) {
//        	 ArrayList<String> subTypes = checkImportClassesFromType(DataType.create(substring));
//        	 return subTypes;
//         }
//         
//         //de.uniks.networkparser.list.SimpleKeyValueList<String,String>
//         //String[] strings = substring.split(",");
//
//         for (String string : strings)
//         {
//            Clazz findClass = ((ClassModel) model.getClazz().getClassModel()).getGenerator().findClass(string);
//            if (findClass != null)
//            {
//               importList.add(findClass.getName(false));
//            } else if(EntityUtil.isPrimitiveType(string) == false) {
//               importList.add(string);
//            }
//         }
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

   private void insertCreateAssignmentMethodInPatternObjectClass(Parser parser, Clazz ownerClazz)
   {
      String attrType = model.getType().getName(true);
      parser.insertImport(model.getType().getName(false));
      parser.insertImport(Pattern.class.getName());
      

      attrType = CGUtil.shortClassName(attrType);

      Template template = new Template(Parser.METHOD + ":create{{Name}}Assignment({{AttrType}})");
      template.withTemplate("" + 
            "   public {{PatternObjectType}} create{{Name}}Assignment({{AttrType}} value)\n" +
            "   {\n" +
            "      new AttributeConstraint()\n" +
            "      .withAttrName({{ModelClass}}.PROPERTY_{{NAME}})\n" +
            "      .withTgtValue(value)\n" +
            "      .withSrc(this)\n" +
            "      .withModifier(Pattern.CREATE)\n" +
            "      .withPattern(this.getPattern());\n" +
            "      \n" +
            "      super.filterAttr();\n" +
            "      \n" +
            "      return this;\n" +
            "   }\n" +
            "   \n");

      String patternObjectType = CGUtil.shortClassName(ownerClazz.getName(false)) + "PO";
      String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getName(false), parser);

      template.insert(parser, 
         "PatternObjectType", patternObjectType,
         "name", model.getName(),
         "AttrType", attrType,
         "ModelClass", modelClass);
   }

   private void insertGetterInPatternObjectClass(Parser parser, Clazz ownerClazz)
   {
      String attrType = model.getType().getName(true);
      parser.insertImport(model.getType().getName(false));

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
      if (model.getModifier().has(Modifier.PUBLIC)) {
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

   private void insertFilterMethodInPatternObjectClass(Parser parser, Clazz ownerClazz)
   {
      insertFilterMethodInPatternObjectClassOneParam(parser, ownerClazz);
      insertFilterMethodInPatternObjectClassRange(parser, ownerClazz);
      insertCreateAssignmentMethodInPatternObjectClass(parser, ownerClazz);
   }

   private void insertFilterMethodInPatternObjectClassRange(Parser parser, Clazz ownerClazz)
   {
      if ("int long float double String".indexOf(model.getType().getName(false)) < 0)
      {
         // range query only for numbers and strings
         return;
      }
      String attrType = getGenerator(ownerClazz).shortNameAndImport(model.getType().getName(false), parser);
      attrType = CGUtil.shortClassName(attrType);
      Template template = new Template(Parser.METHOD + ":create{{Name}}Condition({{AttrType}},{{AttrType}})");
      template.withTemplate("" +
            "   public {{PatternObjectType}} create{{Name}}Condition({{AttrType}} lower, {{AttrType}} upper)\n" +
            "   {\n" +
            "      new AttributeConstraint()\n" +
            "      .withAttrName({{ModelClass}}.PROPERTY_{{NAME}})\n" +
            "      .withTgtValue(lower)\n" +
            "      .withUpperTgtValue(upper)\n" +
            "      .withSrc(this)\n" +
            "      .withModifier(this.getPattern().getModifier())\n" +
            "      .withPattern(this.getPattern());\n" +
            "      \n" +
            "      super.filterAttr();\n" +
            "      \n" +
            "      return this;\n" +
            "   }\n" +
            "   \n");
      parser.insertImport(AttributeConstraint.class.getName());
      String patternObjectType = CGUtil.shortClassName(ownerClazz.getName(false)) + "PO";

      String modelClass = getGenerator(ownerClazz).shortNameAndImport(ownerClazz.getName(false), parser);
      ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
      
      if (ownerClazz.isExternal() || classModel.hasFeature(Feature.EMFSTYLE)) {
         modelClass = modelClass + "Creator";
      }
      template.insert(parser, 
         "PatternObjectType", patternObjectType,
         "name", model.getName(),
         "AttrType", attrType,
         "ModelClass", modelClass);

   }

   private void insertFilterMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz)
   {
      String attrType = model.getType().getName(true);
      parser.insertImport(model.getType().getName(false));
      attrType = CGUtil.shortClassName(attrType);
      Template template = new Template(Parser.METHOD + ":create{{Name}}Condition({{AttrType}})");
      template.withTemplate("" + 
            "   public {{PatternObjectType}} create{{Name}}Condition({{AttrType}} value)\n" +
            "   {\n" +
            "      new AttributeConstraint()\n" +
            "      .withAttrName({{ModelClass}}.PROPERTY_{{NAME}})\n" +
            "      .withTgtValue(value)\n" +
            "      .withSrc(this)\n" +
            "      .withModifier(this.getPattern().getModifier())\n" +
            "      .withPattern(this.getPattern());\n" +
            "      \n" +
            "      super.filterAttr();\n" +
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

      if (ownerClazz.isExternal() || classModel.hasFeature(Feature.EMFSTYLE)) {
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

      Template templateFilter = new Template(Parser.METHOD + ":create{{Name}}Condition({{AttrType}})");
      templateFilter.withTemplate("\n" + 
            "   /**\n" + 
            "    * Loop through the current set of {{ContentType}} objects and collect those {{ContentType}} objects where the {{name}} attribute matches the parameter value. \n" + 
            "    * \n" + 
            "    * @param value Search value\n" + 
            "    * \n" + 
            "    * @return Subset of {{ContentType}} objects that match the parameter\n" + 
            "    */\n"
            + "   public {{ObjectSetType}} create{{Name}}Condition({{AttrType}} value)\n" +
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

      Template templateFilterUpper = new Template(Parser.METHOD + ":create{{Name}}Condition({{AttrType}},{{AttrType}})");
      templateFilterUpper.withCondition(" int long float double String ".indexOf(" " + model.getType().getName(false) + " ") >= 0);
      templateFilterUpper.withTemplate("\n" + 
            "   /**\n" + 
            "    * Loop through the current set of {{ContentType}} objects and collect those {{ContentType}} objects where the {{name}} attribute is between lower and upper. \n" + 
            "    * \n" + 
            "    * @param lower Lower bound \n" + 
            "    * @param upper Upper bound \n" + 
            "    * \n" + 
            "    * @return Subset of {{ContentType}} objects that match the parameter\n" + 
            "    */\n"
            + "   public {{ObjectSetType}} create{{Name}}Condition({{AttrType}} lower, {{AttrType}} upper)\n" +
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

      allTemplate.withTemplates(templateGetter, templateFilter, templateFilterUpper);


      DataType dataType = model.getType();
      String fullModelSetType = dataType.getName(false);
      String modelSetType = dataType.getName(true);

      ArrayList<String> importClassesFromTypes = new ArrayList<String>();

      if (!CGUtil.isPrimitiveType(fullModelSetType) && !fullModelSetType.contains("<") && !fullModelSetType.endsWith("Set"))
      {
         ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
         Clazz clazz = classModel.getClazz(model.getType().getName(false));
         if (clazz != null)
         {
            parser.insertImport(clazz.getName(false));
         }

         modelSetType = dataType.getName(true) + "Set";
      }

      String add = "add";

      if(isMap(dataType))
      {
         add = "with";
      } else if (isSet(dataType)) {
         add = "addAll";
      }

		if (CGUtil.isPrimitiveType(fullModelSetType)) {
			if (fullModelSetType.equalsIgnoreCase("boolean")) {
				fullModelSetType = BooleanList.class.getName();
				modelSetType = BooleanList.class.getSimpleName();
			} else if (EntityUtil.isNumericType(fullModelSetType)) {
				fullModelSetType = NumberList.class.getName();
				modelSetType = NumberList.class.getSimpleName();
			} else {
				fullModelSetType = ObjectSet.class.getName();
				modelSetType = ObjectSet.class.getSimpleName();
			}
			importClassesFromTypes.add(fullModelSetType);
		} else {
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
      if (model.getModifier().has(Modifier.PUBLIC))
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
         "AttrType", model.getType().getName(true),
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
      if(template.validate(parser)) 
      {
         if (GraphUtil.isInterface(model.getClazz()))
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
      
      int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
      
      if (lastIfEndPos - 2 < 0) 
      {
         lastIfEndPos = methodBodyStartPos + 1;
      }
      
      templateProperty.withOffset(lastIfEndPos);         
      templateProperty.withTemplate("\n      if ({{entitiyNameClass}}.PROPERTY_{{NAME}}.equalsIgnoreCase(attribute))" +
            "\n      {" +
            "\n         return (({{entitiyClassName}}) target).{{ValueGet}};" +
            "\n      }" +
            "\n"
            );
      
      String attrNameGetter;
      
      if (model.getModifier().has(Modifier.PUBLIC))
      {
         attrNameGetter = model.getName();
      }
      else if ("boolean".equalsIgnoreCase(model.getType().getName(false)))
      {
         attrNameGetter = "is{{Name}}()";
      }
      else
      {
         attrNameGetter = "get{{Name}}()";
      }

      ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
      
      String entitiyClassName = CGUtil.shortClassName(model.getClazz().getName(false));
      String entitiyNameClass = entitiyClassName;

      if (model.getClazz().isExternal() || classModel.hasFeature(Feature.EMFSTYLE))
      {
         entitiyNameClass = CGUtil.shortClassName(model.getClazz().getName() + "Creator");
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

      //      String attrType = getGenerator(ownerClazz).shortNameAndImport(, parser);
      parser.insertImport(model.getType().getName(false));
      String attrType = model.getType().getName(true);
      //      attrType = CGUtil.shortClassName(attrType);

      //         String fullModelSetType = getType();
      String modelSetType = CGUtil.shortClassName(ownerClazz.getName(false)) + "Set";

      String name = StrUtil.upFirstChar(model.getName());
      String attrNameSetter = "set" + name + "(value)";
      if (model.getModifier().has(Modifier.PUBLIC))
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
      if (model.getModifier().has(Modifier.PRIVATE))
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
      
      if(!dataType.getClazz().isExternal()) 
      {
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
         typePlaceholder = "(type) value";
         type = "Boolean.valueOf(value.toString())";
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
      String attrNameSetter = "set" + name + "((type) value)";
      
      if (isEnum)
      {
         attrNameSetter = "set" + name + "(type)";
      }
      
      if (model.getModifier().has(Modifier.PUBLIC))
      {
         attrNameSetter = model.getName() + " = (type) value";
      }

      attrNameSetter = CGUtil.replaceAll(attrNameSetter, typePlaceholder, type);

      String entitiyClassName = CGUtil.shortClassName(model.getClazz().getName(false));
      String entitiyNameClass = entitiyClassName;
      
      ClassModel classModel = (ClassModel) model.getClazz().getClassModel();
      
      if (model.getClazz().isExternal() || classModel.hasFeature(Feature.EMFSTYLE))
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
      ClassModel classModel = (ClassModel) clazz.getClassModel();
      if (!clazz.isExternal() && ! classModel.hasFeature(Feature.EMFSTYLE))
      {
         parser = getGenerator(clazz).getOrCreateParser(rootDir);
         if (!fromSuperClass)
         {
            insertAttrDeclPlusAccessors(clazz, parser);
         }
         if (GraphUtil.isInterface(clazz) == false)
         {
            insertCaseInToString(parser);
         }
         for (Annotation annotation : GraphUtil.getAnnotations(model))
         {
            getGenerator(annotation).generate(rootDir, helpersDir);
         }

         getGenerator(clazz).printFile();
      }

      if (model.getModifier().has(Modifier.PRIVATE))
      {

         Parser creatorParser = getGenerator(clazz).getOrCreateParserForCreatorClass(helpersDir);
         if(creatorParser != null) {
            insertPropertyInCreatorClass(creatorParser, clazz);
            getGenerator(clazz).printFile(creatorParser);
         }

         Parser modelSetParser = getGenerator(clazz).getOrCreateParserForModelSetFile(helpersDir);
         if(modelSetParser != null) {
            insertGetterInModelSetClass(modelSetParser, clazz);
            insertSetterInModelSetClass(modelSetParser, clazz);
            getGenerator(clazz).printFile(modelSetParser);
         }


         Parser patternObjectParser = getGenerator(clazz).getOrCreateParserForPatternObjectFile(helpersDir);
         if(patternObjectParser != null) {
            insertFilterMethodInPatternObjectClass(patternObjectParser, clazz);
            insertGetterInPatternObjectClass(patternObjectParser, clazz);
            getGenerator(clazz).printFile(patternObjectParser);
         }
      }
      //      }

      return this;
   }

   public boolean isEnumType(Attribute model, Clazz clazz, boolean shortName)
   {
      DataType dataType = model.getType();
      String value = dataType.getName(false);
      for (Clazz item : clazz.getClassModel().getClazzes()) {
         if (item.getType() != ClazzType.ENUMERATION) {
            continue;
         }
         String fullName;
         if (shortName) {
            fullName = item.getName(true);
         } else {
            fullName = item.getName(false);
         }

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
         ClassModel classModel = (ClassModel) ownerClazz.getClassModel();

         // need to add property to string array
         StringBuilder text = new StringBuilder("   className.PROPERTY_NAME,\n   ");

         if (ownerClazz.isExternal() || classModel.hasFeature(Feature.EMFSTYLE))
         {
            // declare the property in the creator class
            CGUtil.replaceAll(text,
               "className.PROPERTY_NAME", propertyName
                  );
         }
         else
         {         
            CGUtil.replaceAll(text,
               "className", CGUtil.shortClassName(model.getClazz().getName(false)),
               "PROPERTY_NAME", propertyName
                  );
         }

         parser.insert(endOfStringArrayInit, text.toString());

         if (ownerClazz.isExternal()  || classModel.hasFeature(Feature.EMFSTYLE))
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

      GenClazzEntity genClass = getGenerator(this.getModel().getClazz());

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

      genClass.removeFragment(poParser, Parser.METHOD + ":create" + attributeName + "Condition(" + attributeType + ")");

      genClass.removeFragment(poParser, Parser.METHOD + ":create" + attributeName + "Condition(" + attributeType + "," + attributeType + ")");

      genClass.removeFragment(poParser, Parser.METHOD + ":create" + attributeName + "Assignment(" + attributeType + ")");

      genClass.removeFragment(poParser, Parser.METHOD + ":create" + attributeName + "(" + attributeType + ")");

      genClass.removeFragment(poParser, Parser.METHOD + ":get" + attributeName + "()");

      genClass.removeFragment(poParser, Parser.METHOD + ":with" + attributeName + "(" + attributeType + ")");

      CGUtil.printFile(poParser);

      Parser setParser = genClass.getOrCreateParserForModelSetFile(rootDir);

      genClass.removeFragment(setParser, Parser.METHOD + ":get" + attributeName + "()");
      
      genClass.removeFragment(setParser, Parser.METHOD + ":filter" + attributeName + "(" + attributeType + ")");

      genClass.removeFragment(setParser, Parser.METHOD + ":filter" + attributeName + "(" + attributeType + "," + attributeType + ")");

      genClass.removeFragment(setParser, Parser.METHOD + ":with" + attributeName + "(" + attributeType + ")");

      CGUtil.printFile(setParser);
   }

   @Override
   ClassModel getClazz() {
      return (ClassModel) getModel().getClazz().getClassModel();
   }
}
