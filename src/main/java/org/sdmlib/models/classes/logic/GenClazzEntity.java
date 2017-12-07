package org.sdmlib.models.classes.logic;

import java.io.File;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.ClazzType;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.FeatureProperty;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.util.ClazzSet;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleSet;

public abstract class GenClazzEntity extends Generator<Clazz>
{
   protected Parser parser = null;

   protected Parser modelSetParser = null;

   protected Parser patternObjectParser = null;

   protected Parser creatorParser = null;


   public abstract void generate(String rootDir, String helpersDir);


   public abstract Parser getOrCreateParser(String rootDir);


   public abstract void printFile(Parser parser);


   public void setParser(Parser parser)
   {
      this.parser = parser;
   }


   public Parser getParser()
   {
      return parser;
   }


   public boolean isShowDiff()
   {
      ClassModel model = (ClassModel) getModel().getClassModel();
      if (model != null)
      {
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
         if (pos > 0)
         {
            int existingIndex = parser.indexOf("Copyright (c) ");
            if (existingIndex > 0)
            {
               String lineForPos = parser.getLineForPos(existingIndex);
               String[] items = lineForPos.split(" ");
               if (!items[items.length - 1].trim().isEmpty())
               {
                  developer = items[items.length - 1].trim();
               }
            }
         }
         parser
            .replaceAll(0,
               "/*\n" +
                  "   Copyright (c) <year> <developer>\n" +
                  "   \n" +
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
               "<developer>", developer);
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
    * Deletes a fragment of code, by using the parser, that is associated to the matching class type.<br> Chooses a code fragment to delete, with the given symbol name, based on the first matching entry within the parsers symbol table.
    * 
    * @param parser used to delete the code fragment from a class, which is determined by the parsers type
    * @param symbName name of the symbol, as it would be contained in the symbol table of the corresponding parser
    */
   public void removeFragment(Parser parser, String symbName)
   {
      parser.indexOf(Parser.CLASS_END);
      SimpleKeyValueList<String, SymTabEntry> symTab = parser.getSymTab();
      SymTabEntry symTabEntry = symTab.get(symbName);

      if (symTabEntry != null)
      {
         StringBuilder fileBody = parser.getFileBody();
         int startPos = symTabEntry.getStartPos();
         if (symTabEntry.getPreCommentStartPos() > 0)
         {
            startPos = symTabEntry.getPreCommentStartPos();
         }
         fileBody.replace(startPos, symTabEntry.getEndPos() + 1, "");
         parser.withFileChanged(true);
      }
   }


   /**
    * Deletes a fragment of code, by using the parser, that is associated to the matching class type.<br> Chooses a code fragment to delete, with the given symbol name, based on the first matching entry within the parsers symbol table. On finding a matching code fragment, the lines of code, that are supposed to be deleted from the fragment, are determined by searching for a matching start and end line, within the
    * fragment.
    * 
    * @param parser used to delete the code fragment from a class, which is determined by the parsers type
    * @param symTabKey name of the symbol, as it would be contained in the symbol table of the corresponding parser
    * @param startLineContent portion of the first line of code, that is supposed to be removed
    * @param endLineContent portion of the last line of code, that is supposed to be removed
    * 
    */
   public void removeLineFromFragment(Parser parser, String symTabKey, String startLineContent, String endLineContent)
   {
      parser.indexOf(Parser.CLASS_END);
      SimpleKeyValueList<String, SymTabEntry> symTab = parser.getSymTab();
      SymTabEntry symTabEntry = symTab.get(symTabKey);

      if (symTabEntry != null)
      {
         String substring = parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos() + 1);
         int indexOf = substring.indexOf(startLineContent);
         if (indexOf >= 0)
         {
            String[] split = substring.split("\n");
            for (int i = 0; i < split.length; i++)
            {
               if (split[i].indexOf(startLineContent) >= 0)
               {
                  if (split[i].indexOf(endLineContent) < 0)
                  {
                     while (i < split.length)
                     {
                        if (split[i].indexOf(endLineContent) >= 0)
                        {
                           split[i] = "";
                           break;
                        }
                        split[i] = "";
                        i++;
                     }
                  }
                  else
                  {
                     split[i] = "";
                  }
                  break;
               }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < split.length; i++)
            {
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
            model.setClassModel(item.getClassModel());
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
            model.setClassModel(item.getClassModel());
            System.err.println("Classmodel try to repair automaticly from Kindclass (" + getRepairClassModel()
               + "). Please add Classmodel to Clazz: " + model.getName());
            this.repairThis = false;
            return getRepairClassModel();
         }
      }
      for (Iterator<Association> i = model.getAssociations().iterator(); i.hasNext();)
      {
         Association item = i.next();
         Clazz otherClazz = item.getOtherClazz();
         if (otherClazz != model)
         {
            if (otherClazz.getClassModel() != null)
            {
               model.setClassModel(otherClazz.getClassModel());
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
      if (getRepairClassModel().hasFeature(Feature.SETCLASS) == false
         && getRepairClassModel().hasFeature(Feature.SERIALIZATION) == false)
      {
         return null;
      }
      if (((ClassModel) model.getClassModel()).hasFeature(Feature.SETCLASS) == false)
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

         FeatureProperty feature = ((ClassModel) model.getClassModel()).getFeature(Feature.SERIALIZATION);
         if (!modelSetJavaFile.exists() && feature != null)
         {
        	 List<String> featureSet = feature.getPath();

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
            Class<?> setClass;
            feature = getRepairClassModel().getFeature(Feature.SETCLASS);
            if (feature != null)
            {
               setClass = feature.getClassValue();
            }
            else
            {
               setClass = SimpleSet.class;
            }
            StringBuilder text = new StringBuilder("" +
               "package packageName;\n" +
               "\n" +
               "import sdmsetimport;\n" +
               "import fullEntityClassName;\n" +
               "\n" +
               "public class modelSetClassName extends SDMSet<entitiyClassName>\n" +
               "{\n" +
               "	public Class<?> getTypClass() {\n" +
               "		return entitiyClassName.class;\n"+
               "	}\n"+
               "}\n");

            CGUtil.replaceAll(text,
               "modelSetClassName", modelSetClassName,
               "entitiyClassName", entitiyClassName,
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName,
               "sdmsetimport", setClass.getName(),
               "SDMSet", EntityUtil.shortClassName(setClass.getName()),
               "Item", entitiyClassName);
            modelSetParser.withFileBody(text).withFileChanged(true);
         }
         insertLicense(modelSetParser);
         insertConstructor(modelSetParser);
         insertEmptySetDecl(modelSetParser, modelSetClassName);
         if (((ClassModel) model.getClassModel()).hasFeature(Feature.PATTERNOBJECT))
         {
            insertSetStartModelPattern(modelSetParser);
         }
         insertSetEntryType(modelSetParser);
         if (((ClassModel) model.getClassModel()).getFeature(Feature.SETCLASS).getClassValue().equals(SimpleSet.class)) 
         {
        	 insertGetNewListMethod(modelSetParser);
//        	 insertFilterMethod(modelSetParser);
         }
         insertInstanceOfMethods(modelSetParser);
         insertSetWithWithout(modelSetParser);
      }

      return modelSetParser;
   }


   private void insertConstructor(Parser parser)
   {
      String shortClassName = CGUtil.shortClassName(model.getName());
      String searchString = Parser.CONSTRUCTOR + ":" + shortClassName + "Set()";

      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n" +
                  "   public ModelSet()\n" +
                  "   {\n" +
                  "      // empty\n" +
                  "   }\n");

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelSet", shortClassName + "Set",
            "ModelClass", shortClassName);

         // insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }

      searchString = Parser.CONSTRUCTOR + ":" + shortClassName
         + "Set(" + shortClassName + "...)";

      pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n" +
                  "   public ModelSet(ModelClass... objects)\n" +
                  "   {\n" +
                  "      for (ModelClass obj : objects)\n" +
                  "      {\n" +
                  "         this.add(obj);\n" +
                  "      }\n" +
                  "   }\n");

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelSet", shortClassName + "Set",
            "ModelClass", shortClassName);

         // insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }

      searchString = Parser.CONSTRUCTOR + ":" + shortClassName
         + "Set(Collection<" + shortClassName + ">)";

      pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n" +
                  "   public ModelSet(Collection<ModelClass> objects)\n" +
                  "   {\n" +
                  "      this.addAll(objects);\n" +
                  "   }\n");

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelSet", shortClassName + "Set",
            "ModelClass", shortClassName);

         // insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }


   private void insertSetStartModelPattern(Parser parser)
   {
      String searchString = Parser.METHOD + ":create" + CGUtil.shortClassName(model.getName()) + "PO()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n" +
                  "   public ModelPO createModelPO()\n" +
                  "   {\n" +
                  "      return new ModelPO(this.toArray(new ModelItem[this.size()]));\n" +
                  "   }\n");

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelPO", CGUtil.shortClassName(model.getName()) + "PO",
            "ModelPatternClass", packageName + ".creators.ModelPattern",
            "ModelItem", CGUtil.shortClassName(model.getName()));

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
         FeatureProperty feature = getRepairClassModel().getFeature(Feature.SETCLASS);
         StringBuilder partnerText;
         if (feature.getClassValue() == null || SimpleSet.class.isAssignableFrom(feature.getClassValue()))
         {
            partnerText = new StringBuilder("\n   public static final type EMPTY_SET = new type().withFlag(type.READONLY);\n");
         }
         else
         {
            partnerText = new StringBuilder("\n   public static final type EMPTY_SET = new type();\n");
         }

         CGUtil.replaceAll(partnerText,
            "type", modelSetClassName);

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
                  + "\n");
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
                  "   }\n");

         CGUtil.replaceAll(text, "ModelType", model.getName(false));

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }


   private void insertInstanceOfMethods(Parser parser)
   {
      // add an instanceOfXY method for each (direct) subclass
      ClazzSet kidClazzes = model.getKidClazzes(false);

      for (Clazz kid : kidClazzes)
      {
         String shortClassName = CGUtil.shortClassName(kid.getName(false));
         String searchString = Parser.METHOD + ":instanceOf" + shortClassName +"()";
         int pos = parser.indexOf(searchString);

         if (pos < 0)
         {
            StringBuilder text = new StringBuilder(
               "\n\n" +
                     "   public ModelSetType instanceOfModelType()\n" + 
                     "   {\n" + 
                     "      ModelSetType result = new ModelSetType();\n" + 
                     "      \n" + 
                     "      for(Object obj : this)\n" + 
                     "      {\n" + 
                     "         if (obj instanceof ModelType)\n" + 
                     "         {\n" + 
                     "            result.with(obj);\n" + 
                     "         }\n" + 
                     "      }\n" + 
                     "      \n" + 
                     "      return result;\n" + 
                     "   }"
                     );

            CGUtil.replaceAll(text,
               "ModelType", shortClassName,
               "ModelSetType", shortClassName + "Set");

            pos = parser.indexOf(Parser.CLASS_END);

            parser.insert(pos, text.toString());

            parser.insertImport(kid.getName(false));
            
            String helperClassName = CGUtil.helperClassName(kid.getName(false), "Set");
               
            parser.insertImport(helperClassName);
         }
      }
   }


   private void insertGetNewListMethod(Parser parser)
   {
      String shortClassName = CGUtil.shortClassName(model.getName(false));
      String searchString = Parser.METHOD + ":getNewList(boolean)";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n" +
                  "   @Override\n" + 
                  "   public ModelSetType getNewList(boolean keyValue)\n" + 
                  "   {\n" + 
                  "      return new ModelSetType();\n" + 
                  "   }\n" + 
                  "");

         CGUtil.replaceAll(text,
            "ModelSetType", shortClassName + "Set");

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());

         parser.insertImport(Condition.class.getName());
      }
   }


//   private void insertFilterMethod(Parser parser)
//   {
//      String shortClassName = CGUtil.shortClassName(model.getName(false));
//      String searchString = Parser.METHOD + ":filter(Condition<" + shortClassName + ">)";
//      int pos = parser.indexOf(searchString);
//
//      if (pos < 0)
//      {
//         StringBuilder text = new StringBuilder(
//               "\n\n" +
//                  "   public ModelSetType filter(Condition<ModelType> condition) {\n" +
//                  "      ModelSetType filterList = new ModelSetType();\n" +
//                  "      filterItems(filterList, condition);\n" +
//                  "      return filterList;\n" +
//                  "   }");
//
//         CGUtil.replaceAll(text,
//            "ModelType", shortClassName,
//            "ModelSetType", shortClassName + "Set");
//
//         pos = parser.indexOf(Parser.CLASS_END);
//
//         parser.insert(pos, text.toString());
//
//         parser.insertImport(Condition.class.getName());
//      }
//   }


   public String getModelSetClassName()
   {
      String name = model.getName(false);
      int pos = name.lastIndexOf('.');
      String entitiyClassName = model.getName(false).substring(pos + 1);

      if (!((ClassModel) getModel().getClassModel()).hasFeature(Feature.SETCLASS))
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
      if (getRepairClassModel().hasFeature(Feature.SETCLASS) == false)
      {
         return null;
      }
      if (((ClassModel) model.getClassModel()).hasFeature(Feature.PATTERNOBJECT) == false)
      {
         return null;
      }

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

      if (patternObjectParser == null)
      {
         // try to find existing file
         String fileName = packageName + "." + patternObjectClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File patternObjectJavaFile = new File(fileName);

         FeatureProperty feature = ((ClassModel) model.getClassModel()).getFeature(Feature.SERIALIZATION);
         if (!patternObjectJavaFile.exists() && feature != null)
         {
        	 List<String> featureSet = feature.getPath();

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
                     + "      newInstance(null);\n"
                     + "   }\n\n"
                     + "   public patternObjectClassName(ModelClass... hostGraphObject) {\n"
                     + "      if(hostGraphObject==null || hostGraphObject.length<1){\n"
                     + "         return ;\n"
                     + "      }\n"
                     + "      newInstance(null, hostGraphObject);\n"
                     + "   }\n"
                     + "}\n");

            if (getRepairClassModel().hasFeature(Feature.SETCLASS))
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
      }
      
      // add constructor with modifier paramter
      String searchString = Parser.CONSTRUCTOR + ":" + patternObjectClassName + "(String)";

      pos = patternObjectParser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n" +
                  "   public ModelPO(String modifier)\n" +
                  "   {\n" +
                  "      this.setModifier(modifier);\n" +
                  "   }\n");

         CGUtil.replaceAll(text,
            "ModelPO", patternObjectClassName
            );

         // insertImport(parser, StringList.class.getName());
         pos = patternObjectParser.indexOf(Parser.CLASS_END);

         patternObjectParser.insert(pos, text.toString());
      }

      return patternObjectParser;
   }


   public Parser getOrCreateParserForCreatorClass(String rootDir)
   {
      ClassModel classModel = (ClassModel) model.getClassModel();

      if (classModel.hasFeature(Feature.SERIALIZATION) == false)
      {
         return null;
      }

      if (model.getType().equals(ClazzType.INTERFACE)) {
    	  return null;
      }
      
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

         FeatureProperty feature = classModel.getFeature(Feature.SERIALIZATION);

         if (!creatorJavaFile.exists() && feature != null)
         {
            List<String> featureSet = feature.getPath();
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
            boolean standAlone = this.getRepairClassModel().hasFeature(Feature.STANDALONE);
            StringBuilder text = new StringBuilder(
                  "package packageName;\n" +
                     "\n" +
                     "import de.uniks.networkparser.interfaces.AggregatedEntityCreator;\n" +
                     "fullEntityClassName" +
                     "\n" +
                     "public class creatorClassName implements AggregatedEntityCreator\n" +
                     "{\n" +
                     "   public static final creatorClassName it = new creatorClassName();\n" +
                     "   \n" +
                     "   private final String[] properties = new String[]\n" +
                     "   {\n" +
                     "   };\n" +
                     "   \n" +
                     "   private final String[] upProperties = new String[]\n" +
                     "   {\n" +
                     "   };\n" +
                     "   \n" +
                     "   private final String[] downProperties = new String[]\n" +
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
                     "   public String[] getUpProperties()\n" +
                     "   {\n" +
                     "      return upProperties;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public String[] getDownProperties()\n" +
                     "   {\n" +
                     "      return downProperties;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public Object getSendableInstance(boolean reference)\n" +
                     "   {\n" +
                     "      return instanceCreationClause;\n" +
                     "   }\n" +
                     "   \n" +
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
                     "      remove_you_clause" +
                     "      if (SendableEntityCreator.REMOVE.equals(type) && value != null)\n" +
                     "      {\n" +
                     "         attrName = attrName + type;\n" +
                     "      }\n" +
                     "      \n" +
                     "      return false;\n" +
                     "   }\n");

            if (standAlone == false)
            {
               text.append("   public static IdMap createIdMap(String sessionID)\n" +
                  "   {\n" +
                  "      return ClassModelPackageCreatorCreator.createIdMap(sessionID);\n" +
                  "   }");
            }

            text.append("}\n");

            String removeYouClause =  "" +
                  "      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {\n"+
                  "           ((entitiyClassName)target).removeYou();\n"+
                  "           return true;\n"+
                  "      }\n";

            if (model.isExternal())
            {
               removeYouClause = "";
               
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
            
            if (model.getType() == ClazzType.ENUMERATION)
            {
               removeYouClause = "";
            }

            String instanceCreationClause = "";
            String fullFactoryName = null;

            if (classModel.hasFeature(Feature.EMFSTYLE))
            {
               String factoryName = classModel.getName(true) + "Factory";
               fullFactoryName = classModel.getName() + "." + factoryName;
               instanceCreationClause = factoryName + ".eINSTANCE.create" + model.getName(true) + "()";
            }
            else if (GraphUtil.isInterface(model) || GraphUtil.isEnumeration(model))
            {
               instanceCreationClause = entitiyClassName + ".class";
            }
            else
            {
               instanceCreationClause = "new " + entitiyClassName + "()";
            }

            // String instanceCreationClause = "new " + entitiyClassName + "()";

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

            if (model.getModifier().has(Modifier.ABSTRACT))
            {
               instanceCreationClause = "null";
            }

            String classModelPackage = model.getClassModel().getName() + ".util.";
            CGUtil.replaceAll(text, "      remove_you_clause", removeYouClause);

            CGUtil.replaceAll(text,
               "creatorClassName", creatorClassName,
               "entitiyClassName", entitiyClassName,
               "fullEntityClassName", "import " + fullEntityClassName + ";\n",
               "packageName", packageName,
               "instanceCreationClause", instanceCreationClause,
               "ClassModelPackage", classModelPackage
               );

            creatorParser.withFileBody(text).withFileChanged(true);
            creatorParser.insertImport(ObjectSet.class.getName());
            creatorParser.insertImport(SendableEntityCreator.class.getName());
            if (standAlone == false)
            {
               creatorParser.insertImport(IdMap.class.getName());
            }
            if (fullFactoryName != null)
            {
               creatorParser.insertImport(fullFactoryName);
            }
            insertLicense(creatorParser);
         }
      }
      return creatorParser;
   }
}
