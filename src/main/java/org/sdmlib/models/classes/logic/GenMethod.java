package org.sdmlib.models.classes.logic;

import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Enumeration;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Modifier;

public class GenMethod extends Generator<Method>
{
   public GenMethod generate(Clazz clazz, String rootDir, String helpersDir)
   {
      // get parser from class
	   ClassModel clazzModel =(ClassModel) clazz.getClassModel();
      GenClass generator = clazzModel.getGenerator().getOrCreate(clazz);
      Parser parser = clazzModel.getGenerator().getOrCreate(clazz).getOrCreateParser(rootDir);

      insertMethodDecl(clazz, parser);

      Annotation annotation = model.getAnnotations();
      getGenerator(annotation).generate(rootDir, helpersDir);
      while(annotation.hasNext() ) {
    	  annotation = annotation.next();
    	  getGenerator(annotation).generate(rootDir, helpersDir);
      }

      // insertCaseInGenericGetSet(parser);

      Parser modelSetParser = generator.getOrCreateParserForModelSetFile(helpersDir);
      insertMethodInModelSet(clazz, modelSetParser);
      generator.printFile(modelSetParser);

      if (clazzModel.hasFeature(Feature.PatternObject)) {
	      Parser patternObjectParser = generator.getOrCreateParserForPatternObjectFile(helpersDir);
	      insertMethodInPatternObject(clazz, patternObjectParser);
	      generator.printFile(patternObjectParser);
      }

      return this;
   }

   public GenMethod generate(Enumeration enumeration, String rootDir, String helpersDir)
   {
      // get parser from class
	   ClassModel clazzModel =(ClassModel) enumeration.getClassModel();

      GenEnumeration genEnumeration = clazzModel.getGenerator().getOrCreate(enumeration);
      Parser parser = genEnumeration.getOrCreateParser(rootDir);

      insertMethodDecl(enumeration, parser);

      return this;
   }

   private void insertMethodDecl(Enumeration enumeration, Parser parser)
   {
      String signature = model.getSignature(false);
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);
      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);
      enumeration.getClassModel().getGenerator().getOrCreate(enumeration);
      if (pos < 0)
      {
         signature = model.getSignature(true);
         StringBuilder text = new StringBuilder
               ("\n   " +
                  "\n   //==========================================================================" +
                  "\n   modifiers returnType mehodName( parameter )");
         text.append(
            "\n   {" +
               "\n      returnClause" +
               "\n   }" +
               "\n"
            );
         String methodName = signature.substring(0, signature.indexOf("("));
         String parameter = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));
         String returnClause = "";

         if ("int float double".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("void".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "";
         }
         else
         {
            returnClause = "return null;";
         }
         String returnType = model.getReturnType().getValue();

         if (returnType.contains("."))
            returnType = returnType.substring(returnType.lastIndexOf(".") + 1);
         CGUtil.replaceAll(text,
            "modifiers", model.getModifier().getValue(),
            "returnType", returnType,
            "mehodName", methodName,
            "parameter", parameter,
            "returnClause", returnClause
            );
         pos = parser.indexOf(Parser.CLASS_END);
         parser.insert(pos, text.toString());
      }
      String signatureSimple = model.getSignature(false);
      pos = parser.indexOf(Parser.METHOD + ":" + signatureSimple);
      symTabEntry = parser.getSymTab().get(string);
      // in case of a method body, remove old method
      if (pos >= 0 && model.getBody() != null)
      {
         parser.parseMethodBody(symTabEntry);
         int startPos = symTabEntry.getEndPos();
         parser.replace(symTabEntry.getBodyStartPos() + 1, startPos, "\n" + model.getBody() + "   ");
         pos = -1;
      }
   }

   private void insertMethodDecl(Clazz clazz, Parser parser)
   {
      String signature = model.getSignature(false);
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);
      clazz.getClassModel().getGenerator().getOrCreate(clazz);
      if (pos < 0)
      {
         signature = model.getSignature(true);
         StringBuilder text = new StringBuilder
               ("\n   " +
                  "\n   //==========================================================================" +
                  "\n   modifiers returnType mehodName( parameter )");

         if (clazz.isInterface() || model.getModifier().has(Modifier.ABSTRACT))
         {
            text.append(";\n");
         }
         else
         {
            text.append(
               "\n   {" +
                  "\n      returnClause" +
                  "\n   }" +
                  "\n"
               );
         }

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameter = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));

         String returnClause = "";

         if ("int float double".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("boolean".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "return false;";
         }
         else if ("void".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "";
         }
         else
         {
            returnClause = "return null;";
         }

         String returnType = model.getReturnType().getValue();
         if (returnType.contains("."))
            returnType = returnType.substring(returnType.lastIndexOf(".") + 1);
         CGUtil.replaceAll(text,
            "modifiers", model.getModifier().getValue(),
            "returnType", returnType,
            "mehodName", methodName,
            "parameter", parameter,
            "returnClause", returnClause
            );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }

      String signatureSimple = model.getSignature(false);
      pos = parser.indexOf(Parser.METHOD + ":" + signatureSimple);

      symTabEntry = parser.getSymTab().get(string);

      // in case of a method body, remove old method
      if (pos >= 0 && model.getBody() != null)
      {
         parser.parseMethodBody(symTabEntry);
         int startPos = symTabEntry.getEndPos();

         // TODO: override return statement ??
         // HashMap<StatementEntry, Integer> returnStatements =
         // parser.getReturnStatements();
         //
         // if (returnStatements.size() == 1) {
         // Object[] array = returnStatements.keySet().toArray();
         // StatementEntry entry = (StatementEntry) array[0];
         // startPos = returnStatements.get(entry);
         // }

         parser.replace(symTabEntry.getBodyStartPos() + 1, startPos, "\n" + model.getBody() + "   ");
         pos = -1;
      }
   }

   public void generate(String rootDir, String helpersDir)
   {
      if (model.getClazz() != null)
         generate(model.getClazz(), rootDir, helpersDir);
   }

   private void insertMethodInModelSet(Clazz clazz2, Parser parser)
   {
      if (parser == null || model.getModifier().has(Modifier.STATIC))
      {
         return;
      }
      String signature = model.getSignature(false);
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      if (pos < 0 && model.getModifier().has(Modifier.PUBLIC))
      {
         signature = model.getSignature(true);
         StringBuilder text = new StringBuilder
               ("   " +
                  "\n   //==========================================================================" +
                  "\n   " +
                  "\n   modifiers returnType methodName(formalParameter)" +
                  "\n   {" +
                  "\n      returnSetCreate" +
                  "\n      for (memberType obj : this)" +
                  "\n      {" +
                  "\n         returnSetAdd obj.methodName(actualParameter) returnSetAddEnd;" +
                  "\n      }" +
                  "\n      returnStat" +
                  "\n   }" +
                  "\n\n"
               );

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));

         String formalParameter = "";
         String actualParameter = "";

         String[] parameters = parameterSig.split("\\s*,\\s*");

         if (!(parameters.length == 1 && parameters[0].isEmpty()))
         {
            for (int i = 0; i < parameters.length; i++)
            {
               String[] item = parameters[i].split(" ");

               formalParameter += item[0] + " " + item[1];
               actualParameter += item[1];

               if (i + 1 < parameters.length)
               {
                  formalParameter += ", ";
                  actualParameter += ", ";
               }
            }
         }

         String returnSetCreate = "";
         String returnSetAdd = "";
         String returnSetAddEnd = "";
         String returnStat = "return this;";

         String type = model.getReturnType().getValue();
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         String importType = type;
         if ("void".equals(type))
         {
            type = CGUtil.shortClassName(clazz2.getFullName()) + "Set";
         }
         else
         {
            if ("String int double long boolean".indexOf(type) >= 0)
            {
               type = type + "List";
               importType = "org.sdmlib.models.modelsets." + type;
            }
            else if ("Object".indexOf(type) >= 0)
            {
               type = "LinkedHashSet<Object>";
               importType = LinkedHashSet.class.getName();
            }
            else
            {
               type = type + "Set";
               importType = model.getClazz().getFullName();
               int dotpos = importType.lastIndexOf('.');
               int typePos = type.lastIndexOf('.');
               type = type.substring(typePos + 1);
               importType = importType.substring(0, dotpos) + GenClassModel.UTILPATH + "." + type;
            }

            parser.insertImport(importType);

            returnSetCreate = type + " result = new " + type + "();\n      ";

            returnSetAdd = "result.add(";
            returnSetAddEnd = ")";
            returnStat = "return result;";
         }

         if (model.getModifier().has(Modifier.STATIC))
         {
            returnStat = "";
         }

         CGUtil.replaceAll(text,
            "returnSetCreate\n      ", returnSetCreate,
            "returnSetAdd ", returnSetAdd,
            " returnSetAddEnd", returnSetAddEnd,
            "returnStat", returnStat,
            "modifiers", model.getModifier().getValue(),
            "returnType", type,
            "methodName", methodName,
            "memberType", CGUtil.shortClassName(clazz2.getFullName()),
            "formalParameter", formalParameter,
            "actualParameter", actualParameter
            );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }

   private void insertMethodInPatternObject(Clazz clazz2, Parser parser)
   {
      if (parser == null)
      {
         return;
      }
      String signature = model.getSignature(false);

      String key = Parser.METHOD + ":" + signature;

      int pos = parser.indexOf(key);

      if (pos < 0 && model.getModifier().has(Modifier.PUBLIC))
      {
         signature = model.getSignature(true);
         StringBuilder text = new StringBuilder
               ("   " +
                  "\n   //==========================================================================" +
                  "\n   " +
                  "\n   public returnType methodName(formalParameter)" +
                  "\n   {" +
                  "\n      if (this.getPattern().getHasMatch())\n" +
                  "      {\n" +
                  "         returnStart ((memberType) getCurrentMatch()).methodName(actualParameter);\n" +
                  "      }" +
                  "\n      returnStat" +
                  "\n   }" +
                  "\n\n"
               );

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));

         String formalParameter = "";
         String actualParameter = "";

         String[] parameters = parameterSig.split("\\s*,\\s*");

         if (!(parameters.length == 1 && parameters[0].isEmpty()))
         {
            for (int i = 0; i < parameters.length; i++)
            {
               String[] item = parameters[i].split(" ");

               formalParameter += item[0] + " " + item[1];
               actualParameter += item[1];

               if (i + 1 < parameters.length)
               {
                  formalParameter += ", ";
                  actualParameter += ", ";
               }
            }
         }

         String returnStart = "";
         String returnStat = "";

         String type = model.getReturnType().getValue();
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         String importType = type;
         if(type.indexOf(".")<0 && type.equals(model.getClazz().getName())) {
        	 type = model.getClazz().getFullName();
         }
         if (!("Object".indexOf(type) >= 0))
         {
        	 parser.insertImport(importType); 
         }

         if (!"void".equals(type))
         {
            returnStart = "return";
            if ("int double float".indexOf(type) >= 0)
            {
               returnStat = "      return 0;\n";
            }
            else if ("boolean".equals(type))
            {
               returnStat = "      return false;\n";
            }
            else
            {
               returnStat = "      return null;\n";
            }
         }

         CGUtil.replaceAll(text,
            "returnSetCreate\n      ", returnStart,
            "returnStart", returnStart,
            "      returnStat\n", returnStat,
            "returnType", type,
            "methodName", methodName,
            "memberType", CGUtil.shortClassName(clazz2.getFullName()),
            "formalParameter", formalParameter,
            "actualParameter", actualParameter
            );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }

   /**
    * Deletes the generated code of the associated method, within the corresponding model, set, creator and pattern object classes.
    * 
    * 
    * @param rootDir root directory, where the code of the associated method is located
    */
   public void removeGeneratedCode(String rootDir) {
	   
	   GenClass genClass = getGenerator(this.getModel().getClazz());
	   
	   Parser parser = genClass.getParser();	   
   
	   String methodName = StrUtil.upFirstChar(this.getModel().getName());
	   
	   genClass.removeFragment(parser, Parser.METHOD + ":" + this.getModel().getSignature(false));
	   
	   CGUtil.printFile(parser);
	   
	   Parser poParser = genClass.getOrCreateParserForPatternObjectFile(rootDir);
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":" + this.getModel().getSignature(false));
	   
	   CGUtil.printFile(poParser);
	   
	   Parser setParser = genClass.getOrCreateParserForModelSetFile(rootDir);
	   
	   genClass.removeFragment(setParser, Parser.METHOD + ":" + this.getModel().getSignature(false));
	   
	   CGUtil.printFile(setParser);
   }
	@Override
	ClassModel getClazz() {
		return this.getModel().getClazz().getClassModel();
	}

}
