package org.sdmlib.models.classes.logic;

import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;

public class GenMethod
{
   private Method model;

   public void setModel(Method value)
   {
      if (this.model != value)
      {
         Method oldValue = this.model;
         if (this.model != null)
         {
            this.model = null;
            oldValue.setGenerator(null);
         }
         this.model = value;
         if (value != null)
         {
            value.setGenerator(this);
         }
      }
   }
   
   public GenMethod generate(Clazz clazz,  String rootDir, String helpersDir, boolean doGenerate)
   {
      // get parser from class
      Parser parser = clazz.getGenerator().getOrCreateParser(rootDir);

      insertMethodDecl(clazz, parser);

      //    insertCaseInGenericGetSet(parser);

      Parser modelSetParser = clazz.getGenerator().getOrCreateParserForModelSetFile(helpersDir);
      insertMethodInModelSet(clazz, modelSetParser);
      clazz.getGenerator().printModelSetFile(doGenerate);

      Parser patternObjectParser = clazz.getGenerator().getOrCreateParserForPatternObjectFile(helpersDir);
      insertMethodInPatternObject(clazz, patternObjectParser);
      clazz.getGenerator().printPatternObjectFile(doGenerate);

      return this;
   }
   
   private void insertMethodDecl(Clazz clazz, Parser parser)
   {     
      String signature = model.getSignature();
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder
               (  "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   modifiers returnType mehodName( parameter )");

         if ( clazz.isInterfaze())
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

         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")") );

         String parameter = "";

         String[] parameters = parameterSig.split("\\s*,\\s*");

         if (!(parameters.length == 1 && parameters[0].isEmpty())) 
         {
            for (int i = 0; i < parameters.length; i++)
            {
               parameter += parameters[i] + " p" + i;
               if (i + 1 < parameters.length)
                  parameter += ", ";
            }
         }
         
         String returnClause = "";
         
         if ("int float double".indexOf(model.getReturnType()) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("void".indexOf(model.getReturnType()) >= 0)
         {
            returnClause = "";
         }
         else 
         {
            returnClause = "return null;";
         }
         
         CGUtil.replaceAll(text, 
            "modifiers", model.getModifier(), 
            "returnType", model.getReturnType(),
            "mehodName", methodName,
            "parameter", parameter, 
            "returnClause", returnClause
               );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         clazz.getGenerator().setFileHasChanged(true);
      }
      
      pos = parser.indexOf(Parser.METHOD + ":" + signature);

      symTabEntry = parser.getSymTab().get(string);

      // in case of a method body, remove old method
      if (pos >= 0 && model.getBody() != null)
      {
        parser.getFileBody().replace(symTabEntry.getBodyStartPos()+1, symTabEntry.getEndPos(), "\n" + model.getBody() + "   ");
        pos = -1;

          clazz.getGenerator().setFileHasChanged(true);
      }
      
      

   }

   public void generate(String rootDir, String helpersDir, boolean doGenerate)
   {
      generate(model.getClazz(),  rootDir, helpersDir, doGenerate);  
   }

   private void insertMethodInModelSet(Clazz clazz2, Parser parser)
   {
      String signature = model.getSignature();
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder
               (  "   " +
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

         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")") );

         String formalParameter = "";
         String actualParameter = "";
         
         String[] parameters = parameterSig.split("\\s*,\\s*");

         if (!(parameters.length == 1 && parameters[0].isEmpty())) 
         {
            for (int i = 0; i < parameters.length; i++)
            {
               formalParameter += parameters[i] + " p" + i;
               actualParameter += " p" + i;
               
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
         
         String type = model.getReturnType();
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
            type = CGUtil.shortClassName(clazz2.getName()) + "Set";
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
               importType = model.getClazz().getName();
               int dotpos = importType.lastIndexOf('.');
               importType = importType.substring(0, dotpos + 1) + "creators." + type ;
            }
            
            model.getClazz().getGenerator().insertImport(parser, importType);  // TODO: import might not be correct for user defined classes
            
            returnSetCreate = type + " result = new " + type + "();\n      ";
            
            returnSetAdd = "result.add(";
            returnSetAddEnd =")";
            returnStat = "return result;";
         }

         CGUtil.replaceAll(text, 
            "returnSetCreate\n      ", returnSetCreate,
            "returnSetAdd ", returnSetAdd,
            " returnSetAddEnd", returnSetAddEnd,
            "returnStat", returnStat,
            "modifiers", model.getModifier(), 
            "returnType", type,
            "methodName", methodName,
            "memberType", CGUtil.shortClassName(clazz2.getName()),
            "formalParameter", formalParameter,
            "actualParameter", actualParameter
               );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         model.getClazz().getGenerator().setModelSetFileHasChanged(true);
      }
   }


   private void insertMethodInPatternObject(Clazz clazz2, Parser parser)
   {
      String signature = model.getSignature();

      String key = Parser.METHOD + ":" + signature;

      int pos = parser.indexOf(key);

      SymTabEntry symTabEntry = parser.getSymTab().get(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder
               (  "   " +
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

         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")") );

         String formalParameter = "";
         String actualParameter = "";
         
         String[] parameters = parameterSig.split("\\s*,\\s*");

         if (!(parameters.length == 1 && parameters[0].isEmpty())) 
         {
            for (int i = 0; i < parameters.length; i++)
            {
               formalParameter += parameters[i] + " p" + i;
               actualParameter += " p" + i;
               
               if (i + 1 < parameters.length)
               {
                  formalParameter += ", ";
                  actualParameter += ", ";
               }
            }
         }
         
         String returnStart = "";
         String returnStat = "";
         
         String type = model.getReturnType();
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         String importType = type;

         if ( ! ("Object".indexOf(type) >= 0))
         {
            model.getClazz().getGenerator().insertImport(parser, importType);  // TODO: import might not be correct for user defined classes
         }

         if ( ! "void".equals(type))
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
            "memberType", CGUtil.shortClassName(clazz2.getName()),
            "formalParameter", formalParameter,
            "actualParameter", actualParameter
               );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         model.getClazz().getGenerator().setPatternObjectFileHasChanged(true);
      }
   }
}
