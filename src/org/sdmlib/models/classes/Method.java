/*
   Copyright (c) 2012 zuendorf 

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 

   The Software shall be used for Good, not Evil. 

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.models.classes;

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.creators.MethodSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class Method implements PropertyChangeInterface
{
   public Method()
   {

   }

   public Method(String signature, String returnType)
   {
      this.setSignature(signature);
      this.setReturnType(returnType);
   }

   // ==========================================================================

   public static final String PROPERTY_SIGNATURE = "signature";

   private String signature;

   public String getSignature()
   {
      return this.signature;
   }

   public void setSignature(String value)
   {
      value = value.replaceAll(" ", "");
      this.signature = value;
   }

   public Method withSignature(String value)
   {
      setSignature(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_MODIFIER = "modifier";

   private String modifier = "public";

   public String getModifier()
   {
      return this.modifier;
   }

   public void setModifier(String value)
   {
      this.modifier = value;
   }

   public Method withModifier(String value)
   {
      setModifier(value);
      return this;
   }

   public static final MethodSet EMPTY_SET = new MethodSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * Method ----------------------------------- Clazz
    *              methods                   clazz
    * </pre>
    */

   public static final String PROPERTY_CLAZZ = "clazz";

   private Clazz clazz = null;

   public Clazz getClazz()
   {
      return this.clazz;
   }

   public boolean setClazz(Clazz value)
   {
      boolean changed = false;

      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;

         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutMethods(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.withMethods(this);
         }

         // getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, null,
         // value);
         changed = true;
      }

      return changed;
   }

   public Method withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   }

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         return getSignature();
      }

      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         return getSignature();
      }

      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         return getSignature();
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         return getClazz();
      }

      if (PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         return getReturnType();
      }

      if (PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         return getBody();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         setSignature((String) value);
         return true;
      }

      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         setSignature((String) value);
         return true;
      }

      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         setSignature((String) value);
         return true;
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         setClazz((Clazz) value);
         return true;
      }

      if (PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         setReturnType((String) value);
         return true;
      }

      if (PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         setBody((String) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(
         this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public Method generate(Clazz clazz, String rootDir, String helpersDir,
         boolean doGenerate)
   {
      // get parser from class
      Parser parser = clazz.getOrCreateParser(rootDir);

      insertMethodDecl(clazz, parser);

      // insertCaseInGenericGetSet(parser);

      Parser modelSetParser = clazz
         .getOrCreateParserForModelSetFile(helpersDir);
      insertMethodInModelSet(clazz, modelSetParser);
      clazz.printModelSetFile(doGenerate);

      Parser patternObjectParser = clazz
         .getOrCreateParserForPatternObjectFile(helpersDir);
      insertMethodInPatternObject(clazz, patternObjectParser);
      clazz.printPatternObjectFile(doGenerate);

      return this;

   }

   private void insertMethodInModelSet(Clazz clazz2, Parser parser)
   {
      String signature = getSignature();
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "   "
                  + "\n   //=========================================================================="
                  + "\n   "
                  + "\n   modifiers returnType methodName(formalParameter)"
                  + "\n   {"
                  + "\n      returnSetCreate"
                  + "\n      for (memberType obj : this)"
                  + "\n      {"
                  + "\n         returnSetAdd obj.methodName(actualParameter) returnSetAddEnd;"
                  + "\n      }" + "\n      returnStat" + "\n   }" + "\n\n");

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameterSig = signature.substring(signature.indexOf("(") + 1,
            signature.indexOf(")"));

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

         String type = this.getReturnType();
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
               importType = this.getClazz().getName();
               int dotpos = importType.lastIndexOf('.');
               importType = importType.substring(0, dotpos + 1) + "creators."
                  + type;
            }

            this.getClazz().insertImport(parser, importType); // TODO: import
                                                              // might not be
                                                              // correct for
                                                              // user defined
                                                              // classes

            returnSetCreate = type + " result = new " + type + "();\n      ";

            returnSetAdd = "result.add(";
            returnSetAddEnd = ")";
            returnStat = "return result;";
         }

         CGUtil.replaceAll(text, "returnSetCreate\n      ", returnSetCreate,
            "returnSetAdd ", returnSetAdd, " returnSetAddEnd", returnSetAddEnd,
            "returnStat", returnStat, "modifiers", modifier, "returnType",
            type, "methodName", methodName, "memberType",
            CGUtil.shortClassName(clazz2.getName()), "formalParameter",
            formalParameter, "actualParameter", actualParameter);

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         clazz.setModelSetFileHasChanged(true);
      }
   }

   private void insertMethodInPatternObject(Clazz clazz2, Parser parser)
   {
      String signature = getSignature();

      String key = Parser.METHOD + ":" + signature;

      int pos = parser.indexOf(key);

      SymTabEntry symTabEntry = parser.getSymTab().get(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "   "
                  + "\n   //=========================================================================="
                  + "\n   "
                  + "\n   public returnType methodName(formalParameter)"
                  + "\n   {"
                  + "\n      if (this.getPattern().getHasMatch())\n"
                  + "      {\n"
                  + "         returnStart ((memberType) getCurrentMatch()).methodName(actualParameter);\n"
                  + "      }" + "\n      returnStat" + "\n   }" + "\n\n");

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameterSig = signature.substring(signature.indexOf("(") + 1,
            signature.indexOf(")"));

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

         String type = this.getReturnType();
         if (type == null)
         {
            type = "void";
         }
         if (type.endsWith("[]"))
         {
            type = type.substring(0, type.length() - 2);
         }
         String importType = type;

         if (!("Object".indexOf(type) >= 0))
         {
            this.getClazz().insertImport(parser, importType); // TODO: import
                                                              // might not be
                                                              // correct for
                                                              // user defined
                                                              // classes
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

         CGUtil.replaceAll(text, "returnSetCreate\n      ", returnStart,
            "returnStart", returnStart, "      returnStat\n", returnStat,
            "returnType", type, "methodName", methodName, "memberType",
            CGUtil.shortClassName(clazz2.getName()), "formalParameter",
            formalParameter, "actualParameter", actualParameter);

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }

   private void insertMethodDecl(Clazz clazz, Parser parser)
   {
      String signature = getSignature();
      int pos = parser.indexOf(Parser.METHOD + ":" + signature);

      String string = Parser.METHOD + ":" + signature;
      SymTabEntry symTabEntry = parser.getSymTab().get(string);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n   "
                  + "\n   //=========================================================================="
                  + "\n   "
                  + "\n   modifiers returnType mehodName( parameter )");

         if (clazz.isInterfaze())
         {
            text.append(";\n");
         }
         else
         {
            text.append("\n   {" + "\n      returnClause" + "\n   }" + "\n");
         }

         String methodName = signature.substring(0, signature.indexOf("("));

         String parameterSig = signature.substring(signature.indexOf("(") + 1,
            signature.indexOf(")"));

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

         if ("int float double".indexOf(returnType) >= 0)
         {
            returnClause = "return 0;";
         }
         else if ("void".indexOf(returnType) >= 0)
         {
            returnClause = "";
         }
         else
         {
            returnClause = "return null;";
         }

         CGUtil.replaceAll(text, "modifiers", modifier, "returnType",
            returnType, "mehodName", methodName, "parameter", parameter,
            "returnClause", returnClause);

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         clazz.setFileHasChanged(true);
      }

      pos = parser.indexOf(Parser.METHOD + ":" + signature);

      symTabEntry = parser.getSymTab().get(string);

      // in case of a method body, remove old method
      if (pos >= 0 && this.getBody() != null)
      {
         parser.getFileBody().replace(symTabEntry.getBodyStartPos() + 1,
            symTabEntry.getEndPos(), "\n" + getBody() + "   ");
         pos = -1;

         clazz.setFileHasChanged(true);
      }

   }

   public void generate(String rootDir, String helpersDir, boolean doGenerate)
   {
      generate(this.clazz, rootDir, helpersDir, doGenerate);
   }

   // ==========================================================================

   public static final String PROPERTY_RETURNTYPE = "returnType";

   private String returnType = "void";

   public String getReturnType()
   {
      return this.returnType;
   }

   public void setReturnType(String value)
   {
      if (!StrUtil.stringEquals(this.returnType, value))
      {
         String oldValue = this.returnType;
         this.returnType = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RETURNTYPE,
            oldValue, value);
      }
   }

   public Method withReturnType(String value)
   {
      setReturnType(value);
      return this;
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getSignature());
      _.append(" ").append(this.getReturnType());
      _.append(" ").append(this.getBody());
      return _.substring(1);
   }

   // ==========================================================================

   public static final String PROPERTY_BODY = "body";

   private String body;

   public String getBody()
   {
      return this.body;
   }

   public void setBody(String value)
   {
      if (!StrUtil.stringEquals(this.body, value))
      {
         String oldValue = this.body;
         this.body = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BODY, oldValue,
            value);
      }
   }

   public Method withBody(String value)
   {
      setBody(value);
      return this;
   }

   public Clazz createClazz()
   {
      Clazz value = new Clazz();
      withClazz(value);
      return value;
   }
}
