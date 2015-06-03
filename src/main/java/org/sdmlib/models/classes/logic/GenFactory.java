package org.sdmlib.models.classes.logic;

import java.io.File;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

import de.uniks.networkparser.json.JsonIdMap;

public class GenFactory
{

   public Parser getOrCreateFactoryFactoryParser(GenClassModel genClassModel, String rootDir)
   {
      if (!genClassModel.getModel().getClasses().isEmpty() && genClassModel.creatorCreatorParser == null && genClassModel.model.getName() != null
            && genClassModel.getModel().hasFeature(Feature.Serialization))
      {
         String packageName = genClassModel.getModel().getName();
   
         packageName = packageName + GenClassModel.UTILPATH;
         String creatorCreatorClassName = packageName + ".CreatorCreator";
   
         String fileName = creatorCreatorClassName;
         fileName = fileName.replaceAll("\\.", "/");
   
         fileName = rootDir + "/" + fileName + ".java";
   
         File javaFile = new File(fileName);
   
         genClassModel.creatorCreatorParser = new Parser().withFileName(fileName);
         // found old one?
         if (javaFile.exists())
         {
            genClassModel.creatorCreatorParser.withFileBody(CGUtil.readFile(javaFile));
         }
         else
         {
            StringBuilder text =
                  new StringBuilder(
                        "package packageName;\n\n"
                              + "import " + JsonIdMap.class.getName() + ";\n"
                              +
                              "import org.sdmlib.serialization.SDMLibJsonIdMap;\n"
                              +
                              "\n"
                              +
                              "class className{\n" +
                              "\n" +
                              "   public static JsonIdMap createIdMap(String sessionID)\n" +
                              "   {\n" +
                              "      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);\n" +
                              "      \n" +
                              "JSONCREATORS\n" +
                              "      return jsonIdMap;\n" +
                              "   }\n" +
                              "}\n");
   
            StringBuilder creators = new StringBuilder();
            // boolean publicCreatorCreator = false;
            for (Clazz clazz : genClassModel.model.getClasses())
            {
               if (!clazz.isInterface() && !clazz.isEnumeration() && clazz.hasFeature(Feature.Serialization))
               {
                  String creatorName = "";
                  if (clazz.isExternal())
                  {
                     creatorName = genClassModel.model.getName() + GenClassModel.UTILPATH + "." + CGUtil.shortClassName(clazz.getFullName());
   
                     GenClass genClass = genClassModel.getOrCreate(clazz);
                     Parser creatorClassParser = genClass.getOrCreateParserForCreatorClass(rootDir);
                     String string = creatorClassParser.getFileName();
                     String alternativeFilePath = string.substring(rootDir.length() + 1,
                           string.length() - "Creator.java".length()).replaceAll("/", ".");
   
                     if (!creatorName.equals(alternativeFilePath))
                        creatorName = alternativeFilePath;
   
                  }
                  else
                  {
                     creatorName = CGUtil.packageName(clazz.getFullName()) + GenClassModel.UTILPATH + "."
                           + CGUtil.shortClassName(clazz.getFullName());
                  }
   
                  creators.append("      jsonIdMap.withCreator(new " + creatorName + "Creator());\n");
   
                  if (clazz.hasFeature(Feature.PatternObject))
                  {
                     creators.append("      jsonIdMap.withCreator(new " + creatorName + "POCreator());\n");
                  }
   
                  // if there are multiple packages, the CreatorCreator must be
                  // public
                  if (!genClassModel.model.getName().equals(CGUtil.packageName(clazz.getFullName())))
                  {
                     // publicCreatorCreator = true;
                  }
               }
            }
   
            CGUtil.replaceAll(text, "class className", "public class className");
   
            CGUtil.replaceAll(text,
                  "className", CGUtil.shortClassName(creatorCreatorClassName),
                  "packageName", packageName,
                  "JSONCREATORS", creators.toString());
   
            genClassModel.creatorCreatorParser.withFileBody(text).withFileChanged(true);
         }
   
         for (Clazz clazz : genClassModel.getModel().getClasses())
         {
            if (!clazz.isInterface() && !clazz.isExternal() && clazz.hasFeature(Feature.Serialization) && !clazz.isEnumeration())
            {
               genClassModel.genFactory.insertCreatorClassInFactoryFactory(genClassModel.creatorCreatorParser, clazz);
            }
         }
         if (genClassModel.getShowDiff() == DIFF.NONE)
         {
            CGUtil.printFile(genClassModel.creatorCreatorParser);
         }
      }
   
      return genClassModel.creatorCreatorParser;
   }

   public void insertCreatorClassInFactoryFactory(Parser ccParser, Clazz clazz)
   {
      int pos = ccParser.indexOf(Parser.METHOD + ":createIdMap(String)");
   
      if (pos < 0)
      {
         // ups, did not find createIdMap method.
         System.err.println("Warning: SDMLib codgen for creatorSet initialisation invocation " + clazz.getName()
               + "Creator for class " + ccParser.getFileName()
               + ": \nDid not find method getCreatorSet(). Should have been generated by my model. "
               + "\nCould not add required code fragment there. :( ");
   
         return;
      }
   
      // OK, found method, parse its body to find if that handles me.
      int methodBodyStartPos = ccParser.getMethodBodyStartPos();
   
      String shortCreatorClassName = CGUtil.shortClassName(clazz.getName()) + "Creator";
      String shortCreatorPOClassName = CGUtil.shortClassName(clazz.getName()) + "POCreator";
      String creatorClassName = CGUtil.packageName(clazz.getFullName()) + ".util." + shortCreatorClassName;
      String creatorPOClassName = CGUtil.packageName(clazz.getFullName()) + ".util." + shortCreatorPOClassName;
   
      // if (getWrapped())
      // {
      // // generate creator for external class. Put it in the model package
      // creatorClassName = getClassModel().getPackageName() + ".creators." +
      // shortCreatorClassName;
      // creatorPOClassName = getClassModel().getPackageName() + ".creators." +
      // shortCreatorPOClassName;
      // }
   
      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);
   
      if (pos < 0)
      {
         ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
   
         int addCreatorPos = ccParser.search("return jsonIdMap;", methodBodyStartPos);
   
         StringBuilder text = new StringBuilder
               ("jsonIdMap.withCreator(new ClassCreator());\n" // +
               //                  "      jsonIdMap.withCreator(new ClassPOCreator());\n" +
               //                  "      "
               );
   
         if (clazz.hasFeature(Feature.PatternObject))
         {
            text.append("      jsonIdMap.withCreator(new ClassPOCreator());\n");
         }
         text.append("      ");
   
         CGUtil.replaceAll(text,
               "ClassCreator", creatorClassName,
               "ClassPOCreator", creatorPOClassName
               );
   
         ccParser.insert(addCreatorPos, text.toString());
         // FIXME ALEX WARUM NICHT this.setFileHasChanged(true);
      }
   
   }

}
