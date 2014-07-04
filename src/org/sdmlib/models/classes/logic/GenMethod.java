package org.sdmlib.models.classes.logic;

import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Visibility;

public class GenMethod extends Generator<Method>
{
   public GenMethod generate(Clazz clazz,  String rootDir, String helpersDir, boolean overrideFlag)
   {
      // get parser from class
      GenClass generator = clazz.getClassModel().getGenerator().getOrCreate(clazz);
      Parser parser = clazz.getClassModel().getGenerator().getOrCreate(clazz).getOrCreateParser(rootDir);

      insertMethodDecl(clazz, parser, overrideFlag);

      //    insertCaseInGenericGetSet(parser);

      Parser modelSetParser = generator.getOrCreateParserForModelSetFile(helpersDir);
      insertMethodInModelSet(clazz, modelSetParser);
      generator.printFile(modelSetParser);

      Parser patternObjectParser = generator.getOrCreateParserForPatternObjectFile(helpersDir);
      insertMethodInPatternObject(clazz, patternObjectParser);
      generator.printFile(patternObjectParser);

      return this;
   }
   
   private void insertMethodDecl(Clazz clazz, Parser parser, boolean overrideFlag)
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
               (  "\n   " +
                     "\n   //==========================================================================" +
                     "\n   OVERRIDE" +
                     "\n   modifiers returnType mehodName( parameter )");

         if ( clazz.isInterface())
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

         String parameter = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")") );
         
         String returnClause = "";
         
         if ("int float double".indexOf(model.getReturnType().getValue()) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("void".indexOf(model.getReturnType().getValue() ) >= 0)
         {
            returnClause = "";
         }
         else 
         {
            returnClause = "return null;";
         }
         String overrideText="";
         if(overrideFlag){
            overrideText="@Override";      
         }
         
         String returnType = model.getReturnType().getValue();
         if (returnType.contains("."))
            returnType = returnType.substring(returnType.lastIndexOf(".")+1);
         CGUtil.replaceAll(text, 
            "modifiers", model.getModifier().getValue(), 
            "returnType", returnType,
            "mehodName", methodName,
            "parameter", parameter, 
            "returnClause", returnClause,
            "OVERRIDE", overrideText
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
//        HashMap<StatementEntry, Integer> returnStatements = parser.getReturnStatements();
//
//        if (returnStatements.size() == 1) {
//         Object[] array = returnStatements.keySet().toArray();
//         StatementEntry entry = (StatementEntry) array[0];
//           startPos = returnStatements.get(entry);
//        }
        
        parser.replace(symTabEntry.getBodyStartPos()+1, startPos, "\n" + model.getBody() + "   ");
        pos = -1;
      }
   }

   public void generate(String rootDir, String helpersDir,  boolean overrideFlag)
   {
      generate(model.getClazz(),  rootDir, helpersDir, overrideFlag);
   }

   private void insertMethodInModelSet(Clazz clazz2, Parser parser)
   {
      String signature = model.getSignature(false);
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      if (pos < 0 && model.getModifier().has(Visibility.PUBLIC))
      {
         signature = model.getSignature(true);
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
               String[] item = parameters[i].split(" ");
               
               formalParameter += item[0] + " "+item[1];
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
         GenClass generator =  model.getClazz().getClassModel().getGenerator().getOrCreate( model.getClazz());
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
               type = type.substring(typePos +1);
               importType = importType.substring(0, dotpos ) + GenClassModel.UTILPATH+"." + type ;
            }
            
            generator.insertImport(parser, importType);
            
            returnSetCreate = type + " result = new " + type + "();\n      ";
            
            returnSetAdd = "result.add(";
            returnSetAddEnd =")";
            returnStat = "return result;";
         }
         
         if(model.getModifier().has(Visibility.STATIC)){
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
      String signature = model.getSignature(false);

      String key = Parser.METHOD + ":" + signature;

      int pos = parser.indexOf(key);

      if (pos < 0 && model.getModifier().has(Visibility.PUBLIC))
      {
         signature = model.getSignature(true);
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
               String[] item = parameters[i].split(" ");
               
               formalParameter += item[0] + " "+item[1];
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
         GenClass generator =  model.getClazz().getClassModel().getGenerator().getOrCreate( model.getClazz());
         if ( ! ("Object".indexOf(type) >= 0))
         {
            generator.insertImport(parser, importType);  // TODO: import might not be correct for user defined classes
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
            "memberType", CGUtil.shortClassName(clazz2.getFullName()),
            "formalParameter", formalParameter,
            "actualParameter", actualParameter
               );

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }
}
