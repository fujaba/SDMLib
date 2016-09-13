package org.sdmlib.models.classes.logic;

import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.buffer.CharacterBuffer;
import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Clazz.ClazzType;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class GenMethod extends Generator<Method>
{
   public GenMethod generateClazz(Clazz clazz, String rootDir, String helpersDir)
   {
      // get parser from class
	   ClassModel clazzModel =(ClassModel) clazz.getClassModel();
      GenClazzEntity generator = clazzModel.getGenerator().getOrCreate(clazz);
      Parser parser = generator.getOrCreateParser(rootDir);

      insertMethodDeclClazz(clazz, parser);
      
      
      for(Annotation annotation : GraphUtil.getAnnotations(model)) {
    	  getGenerator(annotation).generate(rootDir, helpersDir);
      }
      // insertCaseInGenericGetSet(parser);

      Parser modelSetParser = generator.getOrCreateParserForModelSetFile(helpersDir);
      insertMethodInModelSet(clazz, modelSetParser);
      generator.printFile(modelSetParser);

      if (clazzModel.hasFeature(Feature.PATTERNOBJECT)) {
	      Parser patternObjectParser = generator.getOrCreateParserForPatternObjectFile(helpersDir);
	      insertMethodInPatternObject(clazz, patternObjectParser);
	      generator.printFile(patternObjectParser);
      }

      return this;
   }

   public GenMethod generateEnum(Clazz enumeration, String rootDir, String helpersDir)
   {
      // get parser from class
	   ClassModel clazzModel =(ClassModel) enumeration.getClassModel();

      GenClazzEntity genEnumeration = ((ClassModel) clazzModel).getGenerator().getOrCreate(enumeration);
      Parser parser = genEnumeration.getOrCreateParser(rootDir);

      insertMethodDeclEnum(enumeration, parser);

      return this;
   }
   
   private void insertMethodDeclEnum(Clazz enumeration, Parser parser)
   {
	   SymTabEntry symTabEntry;
//	   SymTabEntry symTabEntry = getMethodSymTabEntry(Parser.METHOD, enumeration, parser);
	   if (model.getReturnType().equals(DataType.CONSTRUCTOR)) {
	    	  symTabEntry = getMethodSymTabEntry(Parser.CONSTRUCTOR, model.getClazz(), parser);
	      } else {
	    	  symTabEntry = getMethodSymTabEntry(Parser.METHOD, model.getClazz(), parser);
	      }
//	   int pos = 0;
      ((ClassModel) enumeration.getClassModel()).getGenerator().getOrCreate(enumeration);
      if (symTabEntry == null)
      {
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
         String methodName = model.getName();
         CharacterBuffer methodParameters = GraphUtil.getMethodParameters(model, true);
// 		String signature = methodName + methodParameters.toString();
          String parameter = methodParameters.withStartPosition(1).withLength(methodParameters.length() - 1).toString();
//         String methodName = signature.substring(0, signature.indexOf("("));
//         String parameter = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));
         String returnClause = "";

         String name = model.getReturnType().getName(false);
         if("".equals(name)) {
        	 returnClause = "";
         }
         else if ("int float double".indexOf(name) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("void".indexOf(name) >= 0)
         {
            returnClause = "";
         }
         else
         {
            returnClause = "return null;";
         }
         String returnType = model.getReturnType().getName(false);

         if (returnType.contains("."))
            returnType = returnType.substring(returnType.lastIndexOf(".") + 1);
         CGUtil.replaceAll(text,
            "modifiers", model.getModifier().getName(),
            "returnType", returnType,
            "mehodName", methodName,
            "parameter", parameter,
            "returnClause", returnClause
            );
         int pos = parser.indexOf(Parser.CLASS_END);
         parser.insert(pos, text.toString());
         pos = parser.indexOf(Parser.CLASS_END);
         if (model.getReturnType().equals(DataType.CONSTRUCTOR)) {
	    	  symTabEntry = getMethodSymTabEntry(Parser.CONSTRUCTOR, model.getClazz(), parser);
	      } else {
	    	  symTabEntry = getMethodSymTabEntry(Parser.METHOD, model.getClazz(), parser);
	      }
      }
//      String signatureSimple = model.getName(false);
     
      // in case of a method body, remove old method
      if (symTabEntry != null && model.getBody() != null)
      {
    	  parser.parseMethodBody(symTabEntry);
         int startPos = symTabEntry.getEndPos();
         parser.replace(symTabEntry.getBodyStartPos() + 1, startPos, "\n" + model.getBody() + "   ");
      }
   }

   private SymTabEntry getMethodSymTabEntry(String type, Clazz clazz, Parser parser) {
      String signature = type + ":" +model.getName()+ "(";
      SimpleSet<Parameter> parameters = model.getParameter();
      for(int i = 0; i < parameters.size(); i++) {
         Parameter param = parameters.get(i); 
         if(i > 0) {
            signature += ",";
         } 
         signature += param.getType(true);
      }

      signature += ")";

      parser.indexOf(signature);
      SymTabEntry symTabEntry = parser.getSymTab().get(signature);
      return symTabEntry;
   }
   
   private void insertMethodDeclClazz(Clazz clazz, Parser parser)
   {
	   SymTabEntry symTabEntry = getMethodSymTabEntry(Parser.METHOD, clazz, parser);
      if (symTabEntry == null)
      {
//         String signature = model.getName(false);
         StringBuilder text = new StringBuilder
               ("\n   " +
                  "\n   //==========================================================================" +
                  "\n   modifiers returnType mehodName( parameter )");

         if (GraphUtil.isInterface(clazz) || model.getModifier().has(Modifier.ABSTRACT))
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

         String methodName = model.getName();
         
         CharacterBuffer methodParameters = GraphUtil.getMethodParameters(model, true);
//		String signature = methodName + methodParameters.toString();
         String parameter = methodParameters.withStartPosition(1).withLength(methodParameters.length() - 1).toString();
//        		 (len)wisignature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));

         String returnClause = "";

         if ("int float double".indexOf(model.getReturnType().getName(false)) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("boolean".indexOf(model.getReturnType().getName(false)) >= 0)
         {
            returnClause = "return false;";
         }
         else if ("void".indexOf(model.getReturnType().getName(false)) >= 0)
         {
            returnClause = "";
         }
         else
         {
            returnClause = "return null;";
         }

         String returnType = model.getReturnType().getName(false);
         if (returnType.contains("."))
            returnType = returnType.substring(returnType.lastIndexOf(".") + 1);
         CGUtil.replaceAll(text,
            "modifiers", model.getModifier().getName(),
            "returnType", returnType,
            "mehodName", methodName,
            "parameter", parameter,
            "returnClause", returnClause
            );

         int pos = parser.indexOf(Parser.CLASS_END);
         parser.insert(pos, text.toString());
         
         // Add Imports for all Parameters to Clazz-File
         for (Parameter param : model.getParameter()) 
         {
            String paramType = param.getType().getClazz().getName();
            int endOfName = paramType.length();
            if (paramType.endsWith("..."))
            {
               endOfName -= 3;
            }
            int firstPos = paramType.indexOf(".");
            if(firstPos > 0 && firstPos < endOfName) 
            {
               parser.insertImport(paramType);
            }
         }

         symTabEntry = getMethodSymTabEntry(Parser.METHOD, clazz, parser);
      }

//      String signatureSimple = model.getName(false);
//      pos = parser.indexOf(string);

//      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":" + string);

      // in case of a method body, remove old method
      if (symTabEntry != null && model.getBody() != null)
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
      }
   }

   public void generate(String rootDir, String helpersDir)
   {
      if (model.getClazz() != null) {
    	  if(model.getClazz().getType()==ClazzType.CLAZZ || model.getClazz().getType()==ClazzType.INTERFACE) {
    		  generateClazz(model.getClazz(), rootDir, helpersDir);
    	  } else if(model.getClazz().getType()==ClazzType.ENUMERATION) {
    		  generateEnum(model.getClazz(), rootDir, helpersDir);
    	  }
      }
         
   }

   private void insertMethodInModelSet(Clazz clazz2, Parser parser)
   {
      if (parser == null || model.getModifier().has(Modifier.STATIC))
      {
         return;
      }
      SymTabEntry entry = getMethodSymTabEntry(Parser.METHOD, clazz2, parser);
      
      if (entry == null && model.getModifier().has(Modifier.PUBLIC))
      {
         StringBuilder text = new StringBuilder
               ("   " +
                  "\n   //==========================================================================" +
                  "\n   " +
                  "\n   modifiers returnType methodName(formalParameter)" +
                  "\n   {" +
                  "\n      body" +
                  "\n   }" +
                  "\n\n"
               );

         StringBuilder formalParameter = new StringBuilder();
         StringBuilder actualParameter = new StringBuilder();
         calculateParameters(parser, formalParameter, actualParameter);


         String type = model.getReturnType().getName(false);
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         if (type.endsWith("..."))
         {
            type = type.substring(0, type.length() - 3);
         }
         String importType = type;
         String body="";
         if ("void".equals(type))
         {
            type =  clazz2.getName(true) + "Set";
            body = "return "+type+".EMPTY_SET;";
         }
         else
         {
        	 String returnStat = "return this;";
        	 if (EntityUtil.isNumericType(type)) {
        		 type = "NumberList";
                 importType = NumberList.class.getName();
        	 } else if ("String".indexOf(type) >= 0) {
        		 type = "StringList";
        		 importType = StringList.class.getName();
        	 } else if ("boolean".indexOf(type) >= 0) {
        		 type = BooleanList.class.getName();
        		 importType = BooleanList.class.getSimpleName();
        	 } else if ("Object".indexOf(type) >= 0) {
               type = "LinkedHashSet<Object>";
               importType = LinkedHashSet.class.getName();
            }
            else
            {
            	
               type = type + "Set";
               importType = model.getClazz().getName(false);
               int dotpos = importType.lastIndexOf('.');
               int typePos = type.lastIndexOf('.');
               type = type.substring(typePos + 1);
               importType = importType.substring(0, dotpos) + GenClassModel.UTILPATH + "." + type;
            }
        	 returnStat = "return result;";

            parser.insertImport(importType);
            if (model.getModifier().has(Modifier.STATIC))
            {
               returnStat = "";
            }
//            if(model.getBody()!=null &&model.getBody().length()>0) {
////            	body = model.getBody();
//            } else {
            	body = 	"\n      returnSetCreate" +
            			"\n      for (memberType obj : this)" +
            			"\n      {" +
            			"\n         returnSetAdd obj.methodName(actualParameter) returnSetEnd;" +
            			"\n      }" +
            			"\n      returnStat";
//            }
            body = CGUtil.replaceAll(body,
            		"returnSetCreate", type + " result = new " + type + "();\n      ",
            		"returnStat", returnStat,
            		"returnSetAdd", "result.add(",
            		"returnSetEnd", ")",
            		"methodName", model.getName(),
                    "memberType", clazz2.getName(true),
                    "actualParameter", actualParameter.toString()
            		);
         }

         CGUtil.replaceAll(text,
        	"body", body,
            "modifiers", model.getModifier().getName(),
            "methodName", model.getName(),
            "formalParameter", formalParameter.toString(),
            "returnType", type
            );

         int pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }
   
   private void calculateParameters(Parser parser, StringBuilder formalParameter, StringBuilder actualParameter) {
       int i=0;
       SimpleSet<Parameter> parameters = model.getParameter();
		for (int p = 0; p < parameters.size(); p++) {
			Parameter param = parameters.get(p);
			formalParameter.append(param.getType(true)).append(" ");
			String name = "";
			if (param.getName() != null) {
				name = param.getName().trim();
			}
			if (name == "") {
				name = "p" + (i++);
			}
			formalParameter.append(name);
			parser.insertImport(param.getType(false));
			actualParameter.append(name);
			if (p + 1 < parameters.size()) {
				formalParameter.append(", ");
				actualParameter.append(", ");
			}
		}
	}

   private void insertMethodInPatternObject(Clazz clazz2, Parser parser)
   {
      if (parser == null)
      {
         return;
      }
      
      
      SymTabEntry entry = getMethodSymTabEntry(Parser.METHOD, clazz2, parser);
      
      if (entry == null && model.getModifier().has(Modifier.PUBLIC))
      {
//         signature = model.getName(true);
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

//         String methodName = signature.substring(0, signature.indexOf("("));
//         String parameterSig = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")"));
  	   StringBuilder formalParameter = new StringBuilder();
       StringBuilder actualParameter = new StringBuilder();
       calculateParameters(parser, formalParameter, actualParameter);
        
         String returnStart = "";
         String returnStat = "";

         String type = model.getReturnType().getName(false);
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         if(type.indexOf(".")<0 && type.equals(model.getClazz().getName())) {
        	 type = model.getClazz().getName(false);
         }
        parser.insertImport(type); 

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
            "methodName", model.getName(),
            "memberType", clazz2.getName(true),
            "formalParameter", formalParameter.toString(),
            "actualParameter", actualParameter.toString()
            );

         int pos = parser.indexOf(Parser.CLASS_END);

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
	   
	   GenClazzEntity genClass = getGenerator(this.getModel().getClazz());
	   
	   Parser parser = genClass.getParser();	   
   
//	   String methodName = StrUtil.upFirstChar(this.getModel().getName());
	   
	   genClass.removeFragment(parser, Parser.METHOD + ":" + this.getModel().getName(false));
	   
	   CGUtil.printFile(parser);
	   
	   Parser poParser = genClass.getOrCreateParserForPatternObjectFile(rootDir);
	   
	   genClass.removeFragment(poParser, Parser.METHOD + ":" + this.getModel().getName(false));
	   
	   CGUtil.printFile(poParser);
	   
	   Parser setParser = genClass.getOrCreateParserForModelSetFile(rootDir);
	   
	   genClass.removeFragment(setParser, Parser.METHOD + ":" + this.getModel().getName(false));
	   
	   CGUtil.printFile(setParser);
   }
	@Override
	ClassModel getClazz() {
		return (ClassModel) this.getModel().getClazz().getClassModel();
	}
	
	@Override
	public String toString()
	{
	   return "gen " + model;
	}

}
