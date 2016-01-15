package org.sdmlib.models.classes.logic;

import java.io.File;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.list.SimpleKeyValueList;

public abstract class GenClazzEntity extends Generator<Clazz>{
	protected Parser parser = null;
	protected Parser modelSetParser = null;
	protected Parser patternObjectParser = null;
	protected Parser creatorParser = null;
	public abstract void generate(String rootDir, String helpersDir);
	public abstract Parser getOrCreateParser(String rootDir);
	public abstract void printFile(Parser parser);
	public abstract Parser getParser();

	public boolean isShowDiff() {
		ClassModel model = (ClassModel) getModel().getClassModel();
		if (model != null) {
			return model.getGenerator().getShowDiff() != DIFF.NONE;
		}
		return false;
	}
	
	protected void insertLicense(Parser parser)
	   {
	      // file should start with head comment
	      int pos = parser.search("/*");
	      if (pos < 0 || pos > 20)
	      {
	         // insert MIT License otherwise.
	         String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
	         String developer = model.getClassModel().getAuthorName();
	         if(pos>0) {
	        	 int existingIndex = parser.indexOf("Copyright (c) ");
	        	 String lineForPos = parser.getLineForPos(existingIndex);
	        	 String[] items = lineForPos.split(" ");
	        	 if(!items[items.length-1].trim().isEmpty()) {
	        		 developer = items[items.length-1].trim();
	        	 }
	         }
	         parser
	         .replaceAll(0,
	            "/*\n" +
	                  "   Copyright (c) <year> <developer>\r\n" +
	                  "   \r\n" +
	                  "   Permission is hereby granted, free of charge, to any person obtaining a copy of this software \n" +
	                  "   and associated documentation files (the \"Software\"), to deal in the Software without restriction, \n" +
	                  "   including without limitation the rights to use, copy, modify, merge, publish, distribute, \n" +
	                  "   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is \n" +
	                  "   furnished to do so, subject to the following conditions: \n" +
	                  "   \n" +
	                  "   The above copyright notice and this permission notice shall be included in all copies or \n" +
	                  "   substantial portions of the Software. \n" +
	                  "   \n" +
	                  "   The Software shall be used for Good, not Evil. \n" +
	                  "   \n" +
	                  "   THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING \n" +
	                  "   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND \n" +
	                  "   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, \n" +
	                  "   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, \n" +
	                  "   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. \n" +
	                  " */\n" +
	                  "   \n", 
	                  "<year>", year,
	                  "<developer>", developer
	               );
	      }

	   }
	
   public void printFile()
   {
      if (model.getClassModel() == null || ((ClassModel) model.getClassModel()).getGenerator().getShowDiff() == DIFF.NONE)
      {
         CGUtil.printFile(parser);
      }
   }
   
   public void insertImport(String className)
   {
	   parser.insertImport(className);
   }
   
   public String shortNameAndImport(String typeName, Parser parser)
   {
      // no dot no import
      if (typeName.indexOf('.') < 0)
      {
         return typeName;
      }

      String baseName = CGUtil.shortClassName(typeName);

      // generic type?
      int pos = typeName.indexOf('<');
      if (pos >= 0)
      {
         typeName = typeName.substring(0, pos);
      }

      parser.insertImport(typeName);

      return baseName;
   }
   
   /**
    * Deletes a fragment of code, by using the parser, that is associated to the matching class type.<br>
    * Chooses a code fragment to delete, with the given symbol name, based on the first matching entry within the parsers symbol table.
    * 
    * @param parser used to delete the code fragment from a class, which is determined by the parsers type
    * @param symbName name of the symbol, as it would be contained in the symbol table of the corresponding parser
    */
   public void removeFragment(Parser parser, String symbName) {
	   parser.indexOf(Parser.CLASS_END);
	   SimpleKeyValueList<String, SymTabEntry> symTab = parser.getSymTab();
	   SymTabEntry symTabEntry = symTab.get(symbName);
	   
	   if (symTabEntry != null) {
		   StringBuilder fileBody = parser.getFileBody();
		   int startPos = symTabEntry.getStartPos();
		   if (symTabEntry.getPreCommentStartPos() > 0) {
			   startPos = symTabEntry.getPreCommentStartPos();
		   }
		   fileBody.replace(startPos, symTabEntry.getEndPos() + 1, "");
		   parser.withFileChanged(true);
	   }
   }
   
   /**
    * Deletes a fragment of code, by using the parser, that is associated to the matching class type.<br>
    * Chooses a code fragment to delete, with the given symbol name, based on the first matching entry within the parsers symbol table.
    * On finding a matching code fragment, the lines of code, that are supposed to be deleted from the fragment, are determined
    * by searching for a matching start and end line, within the fragment.
    * 
    * @param parser used to delete the code fragment from a class, which is determined by the parsers type
    * @param symTabKey name of the symbol, as it would be contained in the symbol table of the corresponding parser
    * @param startLineContent portion of the first line of code, that is supposed to be removed
    * @param endLineContent portion of the last line of code, that is supposed to be removed
    * 
    */
   public void removeLineFromFragment(Parser parser, String symTabKey, String startLineContent, String endLineContent) {
	   parser.indexOf(Parser.CLASS_END);
	   SimpleKeyValueList<String, SymTabEntry> symTab = parser.getSymTab();
	   SymTabEntry symTabEntry = symTab.get(symTabKey);
	   
	   if (symTabEntry != null) {
		   String substring = parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos() + 1);
		   int indexOf = substring.indexOf(startLineContent);
		   if (indexOf >= 0) {
			   String[] split = substring.split("\n");
			   for (int i = 0; i < split.length; i++) {
				   if (split[i].indexOf(startLineContent) >= 0) {
					   if (split[i].indexOf(endLineContent) < 0) {
						   while(i < split.length) {
							   if (split[i].indexOf(endLineContent) >= 0) {
								   split[i] = "";
								   break;
							   }
							   split[i] = "";
							   i++;
						   }
					   } else {
						   split[i] = "";
					   }
					   break;
				   }
			   }
			   StringBuilder builder = new StringBuilder();
			   for (int i = 0; i < split.length; i++) {
				   builder.append(split[i]).append("\n");
			   }
			   
			   parser.getFileBody().replace(symTabEntry.getStartPos(), symTabEntry.getEndPos() + 1, builder.toString());
			   parser.withFileChanged(true);
		   }
	   }
   }
   
   private boolean repairThis = false;

   public ClassModel getRepairClassModel()
   {
      if (model.getClassModel() != null)
      {
         return ((ClassModel) model.getClassModel());
      }
      if (repairThis)
      {
         return null;
      }
      this.repairThis = true;
      for (Iterator<Clazz> i = model.getSuperClazzes(false).iterator(); i.hasNext();)
      {
         Clazz item = i.next();

         if (item.getClassModel() != null)
         {
            model.with(item.getClassModel());
            System.err.println("Classmodel try to repair automaticly from Superclass ("
               + getRepairClassModel().getName() + "). Please add Classmodel to Clazz: " + model.getName());
            this.repairThis = false;
            return getRepairClassModel();
         }
      }

      for (Iterator<Clazz> i = model.getKidClazzes(false).iterator(); i.hasNext();)
      {
         Clazz item = i.next();

         if (item.getClassModel() != null)
         {
            model.with(item.getClassModel());
            System.err.println("Classmodel try to repair automaticly from Kindclass (" + getRepairClassModel()
               + "). Please add Classmodel to Clazz: " + model.getName());
            this.repairThis = false;
            return getRepairClassModel();
         }
      }
      for (Iterator<Association> i = model.getAssociation().iterator(); i.hasNext();)
      {
    	 Association item = i.next();
         Clazz otherClazz = item.getOtherClazz();
         if (otherClazz != model)
         {
            if (otherClazz.getClassModel() != null)
            {
               model.with(otherClazz.getClassModel());
               System.err.println("Classmodel try to repair automaticly from Assoc (" + getRepairClassModel().getName()
                  + "). Please add Classmodel to Clazz: " + model.getName());
               this.repairThis = false;
               return getRepairClassModel();
            }
         }
      }
      System.err.println("Classmodel try to repair automaticly. Please add Classmodel to Clazz: " + model.getName());
      this.repairThis = false;
      return getRepairClassModel();
   }
   
   public Parser getOrCreateParserForModelSetFile(String rootDir)
   {
      if (!getRepairClassModel().hasFeature(Feature.ALBERTsSets) && !getRepairClassModel().hasFeature(Feature.Serialization))
      {
         return null;
      }

      if (modelSetParser == null)
      {
         if (model.getName(false).equals("java.util.Date"))
         {
            System.out.println("ups");
         }
         // try to find existing file
         String name = model.getName(false);
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

         if (model.isExternal())
         {
            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String modelSetClassName = entitiyClassName + "Set";

         String fileName = packageName + "." + modelSetClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File modelSetJavaFile = new File(fileName);

         if (!modelSetJavaFile.exists() && ((ClassModel) model.getClassModel()).hasFeature(Feature.Serialization))
         {
            HashSet<String> featureSet = Feature.Serialization.getPath();

            for (String featureValue : featureSet)
            {
               String alternativePackageName = featureValue;
               String alternativeFileName = alternativePackageName + "." + modelSetClassName;
               alternativeFileName = alternativeFileName.replaceAll("\\.", "/");
               alternativeFileName = rootDir + "/" + alternativeFileName + ".java";
               File alternativeJavaFile = new File(alternativeFileName);

               if (alternativeJavaFile.exists())
               {
                  fileName = alternativeFileName;
                  modelSetJavaFile = alternativeJavaFile;
                  break;
               }
            }
         }

         modelSetParser = new Parser()
            .withFileName(fileName);

         // found old one?
         if (modelSetJavaFile.exists() && !isShowDiff())
         {
            modelSetParser.withFileBody(CGUtil.readFile(modelSetJavaFile));
         }
         else
         {
            StringBuilder text = new StringBuilder("" +
               "package packageName;\n" +
               "\n" +
               "import de.uniks.networkparser.list.SDMSet;\n" +
               "import fullEntityClassName;\n" +
               "\n" +
               "public class modelSetClassName extends SDMSet<entitiyClassName>\n" +
               "{\n" +
               "}\n");

            CGUtil.replaceAll(text,
               "modelSetClassName", modelSetClassName,
               "entitiyClassName", entitiyClassName,
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName,
               "Item", entitiyClassName
               );
            modelSetParser.withFileBody(text).withFileChanged(true);
         }
         insertLicense(modelSetParser);
         insertEmptySetDecl(modelSetParser, modelSetClassName);
         if(((ClassModel) model.getClassModel()).hasFeature(Feature.PatternObject)) {
        	 insertSetStartModelPattern(modelSetParser);
         }
         insertSetEntryType(modelSetParser);
         insertSetWithWithout(modelSetParser);
      }

      return modelSetParser;
   }
   
   private void insertSetStartModelPattern(Parser parser)
   {
      String searchString = Parser.METHOD + ":has" + CGUtil.shortClassName(model.getName()) + "PO()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n" +
                  "   public ModelPO hasModelPO()\n" +
                  "   {\n" +
                  "      return new ModelPO(this.toArray(new ModelItem[this.size()]));\n" +
                  "   }\n"
               );

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelPO", CGUtil.shortClassName(model.getName()) + "PO",
            "ModelPatternClass", packageName + ".creators.ModelPattern",
            "ModelItem", CGUtil.shortClassName(model.getName())
            );

         // insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }
   
   private void insertEmptySetDecl(Parser parser, String modelSetClassName)
   {
      int partnerPos = parser.indexOf(Parser.ATTRIBUTE + ":EMPTY_SET");

      if (partnerPos < 0)
      {
         // add attribute declaration in class file
         partnerPos = parser.indexOf(Parser.CLASS_END);

         StringBuilder partnerText = new StringBuilder
               ("\n   public static final type EMPTY_SET = new type()READONLY;" +
                  "\n"
               );

         String replaceReadOnly = ".withReadOnly()";

         CGUtil.replaceAll(partnerText,
            "type", modelSetClassName,
            "READONLY", replaceReadOnly
            );

         parser.insert(partnerPos, partnerText.toString());
      }
   }
   
   
   private void insertSetWithWithout(Parser parser)
   {
      String searchString = Parser.METHOD + ":with(Object)";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n"
                  + "   @SuppressWarnings(\"unchecked\")\n"
                  + "   public ModelTypeSet with(Object value)\n"
                  + "   {\n"
                  + "      if (value == null)\n" 
                  + "      {\n" 
                  + "         return this;\n"  
                  + "      }\n"  
                  + "      else if (value instanceof java.util.Collection)\n"
                  + "      {\n"
                  + "         this.addAll((Collection<ModelType>)value);\n"
                  + "      }\n"
                  + "      else if (value != null)\n"
                  + "      {\n"
                  + "         this.add((ModelType) value);\n"
                  + "      }\n"
                  + "      \n"
                  + "      return this;\n" +
                  "   }\n" +
                  "   \n" +
                  "   public ModelTypeSet without(ModelType value)\n" +
                  "   {\n" +
                  "      this.remove(value);\n" +
                  "      return this;\n" +
                  "   }\n"
                  + "\n"
               );

         CGUtil.replaceAll(text,
            "ModelType", CGUtil.shortClassName(model.getName(false)));

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());

         parser.insertImport("java.util.Collection");
      }
   }

   private void insertSetEntryType(Parser parser)
   {
      String searchString = Parser.METHOD + ":getEntryType()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n" +
                  "   public String getEntryType()\n" +
                  "   {\n" +
                  "      return \"ModelType\";\n" +
                  "   }\n"
               );

         CGUtil.replaceAll(text, "ModelType", model.getName(false));

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }
   
   public String getModelSetClassName()
   {
      String name = model.getName(false);
      int pos = name.lastIndexOf('.');
      String entitiyClassName = model.getName(false).substring(pos + 1);

      if (!((ClassModel) getModel().getClassModel()).hasFeature(Feature.ALBERTsSets))
      {
         return "java.util.LinkedHashSet<" + entitiyClassName + ">";
      }

      String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

      if (model.isExternal())
      {
         packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
      }

      String modelSetClassName = entitiyClassName + "Set";

      String fullModelSetClassName = packageName + "." + modelSetClassName;

      return fullModelSetClassName;
   }

   public String getModelSetClassNameShort()
   {
      String result = getModelSetClassName();
      int pos = result.lastIndexOf(".");
      if (pos > 0)
      {
         result = result.substring(pos + 1);
      }
      // pos = result.lastIndexOf("<");
      // if(pos>0) {
      // result = result.substring(0, pos);
      // }
      return result;
   }
   public Parser getOrCreateParserForPatternObjectFile(String rootDir)
   {
      if (!getRepairClassModel().hasFeature(Feature.ALBERTsSets))
      {
         return null;
      }
      
      if (patternObjectParser == null)
      {
         // try to find existing file
         String name = model.getName(false);
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

         if (model.isExternal())
         {
            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String patternObjectClassName = entitiyClassName + "PO";

         String fileName = packageName + "." + patternObjectClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File patternObjectJavaFile = new File(fileName);

         if (!patternObjectJavaFile.exists() && ((ClassModel) model.getClassModel()).hasFeature(Feature.Serialization))
         {
            HashSet<String> featureSet = Feature.Serialization.getPath();

            for (String featureValue : featureSet)
            {
               String alternativePackageName = featureValue;
               String alternativeFileName = alternativePackageName + "." + patternObjectClassName;
               alternativeFileName = alternativeFileName.replaceAll("\\.", "/");
               alternativeFileName = rootDir + "/" + alternativeFileName + ".java";
               File alternativeJavaFile = new File(alternativeFileName);

               if (alternativeJavaFile.exists())
               {
                  fileName = alternativeFileName;
                  patternObjectJavaFile = alternativeJavaFile;
                  break;
               }
            }
         }

         patternObjectParser = new Parser()
            .withFileName(fileName);
         // found old one?
         if (patternObjectJavaFile.exists() && !isShowDiff())
         {
            patternObjectParser.withFileBody(CGUtil.readFile(patternObjectJavaFile));
         }
         else
         {
            StringBuilder text = new StringBuilder(
                  ""
                     + "package packageName;\n\n"
                     + "import org.sdmlib.models.pattern.PatternObject;\n"
                     + "import fullEntityClassName;\n\n"
                     + "public class patternObjectClassName extends PatternObject<patternObjectClassName, entitiyClassName>\n"
                     + "{\nALLMATCHES\n\n"
                     + "   public patternObjectClassName(){\n"
                     + "      newInstance(ClassModelPackageCreatorCreator.createIdMap(\"PatternObjectType\"));\n"
                     + "   }\n\n"
                     + "   public patternObjectClassName(ModelClass... hostGraphObject) {\n"
                     + "      if(hostGraphObject==null || hostGraphObject.length<1){\n"
                     + "         return ;\n"
                     + "      }\n"
                     + "      newInstance(ClassModelPackageCreatorCreator.createIdMap(\"PatternObjectType\"), hostGraphObject);\n"
                     + "   }\n"
                     + "}\n");

            if (getRepairClassModel().hasFeature(Feature.ALBERTsSets))
            {
               CGUtil.replaceAll(text,
                  "ALLMATCHES", "\n    public entitiyClassNameSet allMatches()\n" +
                     "   {\n" +
                     "      this.setDoAllMatches(true);\n" +
                     "      \n" +
                     "      entitiyClassNameSet matches = new entitiyClassNameSet();\n" +
                     "\n" +
                     "      while (this.getPattern().getHasMatch())\n" +
                     "      {\n" +
                     "         matches.add((entitiyClassName) this.getCurrentMatch());\n" +
                     "         \n" +
                     "         this.getPattern().findMatch();\n" +
                     "      }\n" +
                     "      \n" +
                     "      return matches;\n" +
                     "   }\n");
            }
            else
            {
               CGUtil.replaceAll(text,
                  "ALLMATCHES", "");
            }
            CGUtil.replaceAll(text,
               "patternObjectClassName", patternObjectClassName,
               "entitiyClassName", entitiyClassName,
               "fullEntityClassName", fullEntityClassName,
               "ModelClass", entitiyClassName,
               "packageName", packageName,
               "ClassModelPackage", model.getClassModel().getName() + ".util.");

            patternObjectParser.withFileBody(text).withFileChanged(true);
         }

         // FIXME STEFAN
         // if(getRepairClassModel().hasFeature(Feature.ALBERTsSets)){
         // this.insertImport(patternObjectParser, packageName + "." +
         // entitiyClassName + "Set");
         // }
      }
      return patternObjectParser;
   }
   
   public Parser getOrCreateParserForCreatorClass(String rootDir)
   {
      if (creatorParser == null)
      {
         // try to find existing file
         String name = model.getName(false);
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

         if (model.isExternal())
         {
            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String creatorClassName = entitiyClassName + "Creator";

         String fileName = packageName + "." + creatorClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File creatorJavaFile = new File(fileName);

         if (!creatorJavaFile.exists() && ((ClassModel) model.getClassModel()).hasFeature(Feature.Serialization))
         {
            HashSet<String> featureSet = Feature.Serialization.getPath();
            for (String featureValue : featureSet)
            {
               String alternativePackageName = featureValue;
               String alternativeFileName = alternativePackageName + "." + creatorClassName;
               alternativeFileName = alternativeFileName.replaceAll("\\.", "/");
               alternativeFileName = rootDir + "/" + alternativeFileName + ".java";
               File alternativeJavaFile = new File(alternativeFileName);

               if (alternativeJavaFile.exists())
               {
                  fileName = alternativeFileName;
                  creatorJavaFile = alternativeJavaFile;
                  break;
               }
            }
         }

         creatorParser = new Parser()
            .withFileName(fileName);

         // found old one?
         if (creatorJavaFile.exists() && !isShowDiff())
         {
            creatorParser.withFileBody(CGUtil.readFile(creatorJavaFile));
         }
         else
         {
            StringBuilder text = new StringBuilder(
                  "package packageName;\n" +
                     "\n" +
                     "import de.uniks.networkparser.interfaces.SendableEntityCreator;\n" +
                     "import " + JsonIdMap.class.getName() + ";\n" +
                     "fullEntityClassName" +
                     "\n" +
                     "public class creatorClassName implements SendableEntityCreator\n" +
                     "{\n" +
                     "   private final String[] properties = new String[]\n" +
                     "   {\n" +
                     "   };\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public String[] getProperties()\n" +
                     "   {\n" +
                     "      return properties;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public Object getSendableInstance(boolean reference)\n" +
                     "   {\n" +
                     "      return instanceCreationClause;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public Object getValue(Object target, String attrName)\n" +
                     "   {\n" +
                     "      int pos = attrName.indexOf('.');\n" +
                     "      String attribute = attrName;\n" +
                     "      \n" +
                     "      if (pos > 0)\n" +
                     "      {\n" +
                     "         attribute = attrName.substring(0, pos);\n" +
                     "      }\n" +
                     "      \n" +
                     "      return null;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public boolean setValue(Object target, String attrName, Object value, String type)\n" +
                     "   {\n" +
                     "      if (JsonIdMap.REMOVE.equals(type) && value != null)\n" +
                     "      {\n" +
                     "         attrName = attrName + type;\n" +
                     "      }\n" +
                     "      \n" +
                     "      return false;\n" +
                     "   }\n" +
                     "   public static JsonIdMap createIdMap(String sessionID)\n" +
                     "   {\n" +
                     "      return ClassModelPackageCreatorCreator.createIdMap(sessionID);\n" +
                     "   }" +
                     "}\n");

            if (model.isExternal())
            {
               // check if it has a constructor
               ClassLoader classLoader = this.getClass().getClassLoader();
               boolean hasConstructor = false;
               try
               {
                  Class<?> loadClass = classLoader.loadClass(model.getName(false));

                  if (loadClass != null)
                  {
                     Constructor<?> constructor = loadClass.getConstructor(loadClass);
                     hasConstructor = constructor != null;
                  }
               }
               catch (Exception e)
               {
               }

               if (!hasConstructor)
               {
                  CGUtil.replaceAll(text,
                     "instanceCreationClause", "null", "fullEntityClassName", "");
               }
            }

            String instanceCreationClause = "new " + entitiyClassName + "()";

            String modelPackage = CGUtil.packageName(model.getName(false));

            if (model.getName(false).endsWith("Impl") && modelPackage.endsWith(".impl"))
            {
               // emf style get package and name prefix
               modelPackage = CGUtil.packageName(modelPackage);
               String modelName = CGUtil.shortClassName(modelPackage);

               String basicClassName = entitiyClassName.substring(0, entitiyClassName.length() - 4);

               instanceCreationClause = modelPackage + "." + modelName + "Factory.eINSTANCE.create" + basicClassName
                  + "()";
            }
            
            if (model.hasModifier(Modifier.ABSTRACT))
            {
               instanceCreationClause = "null";
            }

            String classModelPackage = model.getClassModel().getName() + ".util.";

            CGUtil.replaceAll(text,
               "creatorClassName", creatorClassName,
               "entitiyClassName", entitiyClassName,
               "fullEntityClassName", "import " + fullEntityClassName + ";\n",
               "packageName", packageName,
               "instanceCreationClause", instanceCreationClause,
               "ClassModelPackage", classModelPackage);

            creatorParser.withFileBody(text).withFileChanged(true);

            insertLicense(creatorParser);
         }
      }
      return creatorParser;
   }
}
