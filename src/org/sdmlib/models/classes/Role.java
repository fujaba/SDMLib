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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.creators.RoleSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.util.PropertyChangeInterface;

public class Role implements PropertyChangeInterface
{
   @Override
   public String toString()
   {
   	//   	StringBuilder _ = new StringBuilder();
   	//   	_.append(" ").append(this.getName());
   	//      _.append(" ").append(this.getCard());
   	//      _.append(" ").append(this.getKind());
      return "" + name + " " + card;
   }
   // public static final String ONE = "one";
   // public static final String MANY = "many";
   public static final String VANILLA = "vanilla";
   public static final String AGGREGATION = "aggregation";
   
   public enum R 
   {
      ONE, MANY;
      
      @Override
      public String toString()
      {
         return super.toString().toLowerCase();
      }
      
      public static final String INT = "int";
      public static final String LONG = "long";
      public static final String DOUBLE = "double";
      public static final String STRING = "String";
      public static final String BOOLEAN = "boolean";
      
      public static final String MODELING = "modeling";
      public static final String ACTIVE = "active";
      public static final String DONE = "done";
      public static final String IMPLEMENTATION = "implementation";
      public static final String BACKLOG = "backlog";
      public static final String BUG = "bug";
      public static final String ISSUE = "issue";

      
      public static final int DEBUG_ON = 1;
      public static final int TRACE_ON = 2;
   }
   
   public String labelForRole()
   {
      String result = getName();

      if (getCard().equals(R.MANY.toString()))
      {
         result = result + " *";
      }

      return result;
   }
   
   //==========================================================================
   
   public static final String PROPERTY_CLAZZ = "clazz";
   
   private Clazz clazz= null;
   
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
            oldValue.withoutSourceRoles(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.withSourceRoles(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Role withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CARD = "card";
   
   private String card= R.MANY.toString();
   
   public String getCard()
   {
      return this.card;
   }
   
   public void setCard(String value)
   {
      this.card = value;
   }
   
   public Role withCard(String value)
   {
      setCard(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name= null;
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      this.name = value;
   }
   
   public Role withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_KIND = "kind";
   
   private String kind= VANILLA;
   
   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      this.kind = value;
   }
   
   public Role withKind(String value)
   {
      setKind(value);
      return this;
   }

   public void generate(String rootDir, String helperDir, Role partnerRole, boolean doGenerate)
   {
      generate(getClazz(), rootDir, helperDir, partnerRole, doGenerate, false);
   }
   
   
   public void generate(Clazz clazz, String rootDir, String helperDir, Role partnerRole, boolean doGenerate, boolean fromSuperClass)
   {
      if (clazz.isExternal())
      {
         return;
      }
      
      Parser myParser = clazz.getOrCreateParser(rootDir);
      
      if ( ! fromSuperClass)
      {
            // add attribute declaration in class file
            StringBuilder text = new StringBuilder();

            if (StrUtil.stringEquals(partnerRole.getCard(), R.MANY.toString()))
            {
               generateToManyRole(myParser, clazz, partnerRole, text);
               clazz.insertImport(LinkedHashSet.class.getName());
            }
            else
            {
               generateToOneRole(myParser, clazz, partnerRole, text);
            }

            int pos = myParser.indexOf(Parser.CLASS_END);
            myParser.getFileBody().insert(pos, text.toString());
            clazz.setFileHasChanged(true);

         if (StrUtil.stringEquals(partnerRole.getCard(), R.MANY.toString()))
         {
            generateEmptySetInPartnerClass(rootDir, partnerRole, doGenerate);
         }
      }
      
      //import partner role class if package name has changed
      if(!StrUtil.stringEquals(clazz.getName().substring(0, clazz.getName().lastIndexOf(".")), partnerRole.getClazz().getName().substring(0, partnerRole.getClazz().getName().lastIndexOf(".")))){
    	  clazz.insertImport(partnerRole.getClazz().getName());
      }
      
      insertCaseInGenericGet(clazz, myParser, partnerRole, rootDir);

      if (StrUtil.stringEquals(partnerRole.getCard(), R.MANY.toString()))
      {
         insertCaseInGenericSetToMany(clazz, myParser, partnerRole, rootDir);
      }
      else
      {
         insertCaseInGenericSetToOne(clazz, myParser, partnerRole, rootDir);
      }
      
      insertRemovalInRemoveYou(clazz, myParser, partnerRole);
      
      clazz.printFile(doGenerate);
      
      
      // generate property in creator class
		if (!clazz.isInterfaze())
		{
			Parser creatorParser = clazz.getOrCreateParserForCreatorClass(helperDir);

			insertPropertyInCreatorClass(clazz, creatorParser, partnerRole);

			clazz.printCreatorFile(doGenerate);
		}
      
      // generate property in model set class
      Parser modelSetParser = clazz.getOrCreateParserForModelSetFile(helperDir);
      
      insertGetterInModelSetFile(clazz, modelSetParser, partnerRole);
      insertSetterInModelSetFile(clazz, modelSetParser, partnerRole);
      
      clazz.printModelSetFile(doGenerate);

      // generate property in pattern object class
      Parser patternObjectParser = clazz.getOrCreateParserForPatternObjectFile(helperDir);
      
      insertGetterInPatternObjectFile(clazz, patternObjectParser, partnerRole);
      
      clazz.printPatternObjectFile(doGenerate);

   }
   
   private void insertRemovalInRemoveYou(Clazz clazz, Parser parser, Role partnerRole)
   {
      if (clazz.getInterfaze())
      {
         return;
      }
      
      int pos = parser.indexOf(Parser.METHOD + ":removeYou()");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for role " + partnerRole.getName() + " for class " + clazz.getName() 
            + ": \nDid not find method removeYou(). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      String removeCall = "set" + StrUtil.upFirstChar(partnerRole.getName());
      String fullRemoveCall = removeCall + "(null);\n      ";
      if (partnerRole.getCard().equals(R.MANY.toString()))
      {
         removeCall = "removeAllFrom" + StrUtil.upFirstChar(partnerRole.getName());
         fullRemoveCall = removeCall + "();\n      ";
      }            
      
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + removeCall, methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add remove call
         pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":getPropertyChangeSupport", methodBodyStartPos);
         
         if (pos < 0)
         {
            System.err.println("Warning: SDMLib codgen for role " + partnerRole.getName() + " for class " + clazz.getName() 
               + ": \nDid not find getPropertyChangeSupport call in method removeYou(). Should have been generated by my clazz. " 
               + "\nCould not add required code fragment there. :( ");

            return;
         }
         
         parser.getFileBody().insert(pos, fullRemoveCall);
         clazz.setFileHasChanged(true);
      }
   }

   private void insertGetterInModelSetFile(Clazz tgtClass, Parser parser, Role partnerRole)
   {
      String key = Parser.METHOD + ":get" + StrUtil.upFirstChar(partnerRole.getName()) + "()";
      int pos = parser.indexOf(key);

      StringBuilder text = new StringBuilder();
      
      if (pos < 0)
      {
            text.append(
            "   public ModelSetType getName()\n" + 
            "   {\n" + 
            "      ModelSetType result = new ModelSetType();\n" + 
            "      \n" + 
            "      for (ContentType obj : this)\n" + 
            "      {\n" + 
            "         result.with(obj.getName());\n" + 
            "      }\n" + 
            "      \n" + 
            "      return result;\n" + 
            "   }\n" + 
            "\n" + 
            "   public ContentTypeSet hasName(Object value)\n" + 
            "   {\n" + 
            "      ObjectSet neighbors = new ObjectSet();\n" + 
            "\n" + 
            "      if (value instanceof Collection)\n" + 
            "      {\n" + 
            "         neighbors.addAll((Collection) value);\n" + 
            "      }\n" + 
            "      else\n" + 
            "      {\n" + 
            "         neighbors.add(value);\n" + 
            "      }\n" + 
            "      \n" + 
            "      ContentTypeSet answer = new ContentTypeSet();\n" + 
            "      \n" + 
            "      for (ContentType obj : this)\n" + 
            "      {\n" + 
            "         if (containsClause)\n" + 
            "         {\n" + 
            "            answer.add(obj);\n" + 
            "         }\n" + 
            "      }\n" + 
            "      \n" + 
            "      return answer;\n" + 
            "   }\n" + 
            "\n");
      }
       
      
      key = Parser.METHOD + ":get" + StrUtil.upFirstChar(partnerRole.getName()) + "Transitive()";
      int pos2 = parser.indexOf(key);
      
      if (pos2 < 0)
      {
         if (this.getClazz() == this.getPartnerRole().getClazz())
         {
            text
               .append("\n   public ModelSetType getNameTransitive()\n"
                  + "   {\n"
                  + "      ModelSetType todo = new ModelSetType().with(this);\n"
                  + "      \n"
                  + "      ModelSetType result = new ModelSetType();\n"
                  + "      \n" + "      while ( ! todo.isEmpty())\n"
                  + "      {\n"
                  + "         ModelType current = todo.first();\n"
                  + "         \n" + "         todo.remove(current);\n"
                  + "         \n"
                  + "         if ( ! result.contains(current))\n"
                  + "         {\n" + "            result.add(current);\n"
                  + "            \n"
                  + "            todo.with(current.getName().minus(result));\n"
                  + "         }\n" + "      }\n" + "      \n"
                  + "      return result;\n" + "   }\n" + "\n" + "");
            
            if (partnerRole.getCard().equals(R.ONE.toString()))
            {
               CGUtil.replaceAll(text, 
                  "todo.with(current.getName().minus(result));", 
                  "if ( ! result.contains(current.getName()))\n"
                  + "            {\n"
                  + "               todo.with(current.getName());\n"
                  + "            }");
            }
         }
      }
      
      if (pos < 0 || pos2 < 0)
      {      
         String containsClause = "neighbors.contains(obj.get"
               + StrUtil.upFirstChar(partnerRole.getName()) + "())";
         
         if (partnerRole.getCard().equals(R.MANY.toString()))
         {
            containsClause = " ! Collections.disjoint(neighbors, obj.get" 
                  + StrUtil.upFirstChar(partnerRole.getName()) + "())";
         }
         
         String fullModelSetType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "Set");
//         String modelSetType = CGUtil.shortClassName(fullModelSetType);
         
         
         CGUtil.replaceAll(text, 
            "ContentType", CGUtil.shortClassName(tgtClass.getName()),
            "ModelType", CGUtil.shortClassName(partnerRole.getClazz().getName()),
            "ModelSetType", CGUtil.shortClassName(partnerRole.getClazz().getName()) + "Set",
            "Name", StrUtil.upFirstChar(partnerRole.getName()),
            "containsClause", containsClause
            );

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         tgtClass.setModelSetFileHasChanged(true);
         
         tgtClass.insertImport(parser, fullModelSetType);
         tgtClass.insertImport(parser, Collection.class.getName());
         tgtClass.insertImport(parser, Collections.class.getName());
         tgtClass.insertImport(parser, ObjectSet.class.getName());
      }
   }


   private void insertGetterInPatternObjectFile(Clazz clazz, Parser parser, Role partnerRole)
   {
      insertHasNoParamInPatternObjectFile(clazz, parser, partnerRole);
      insertCreateNoParamInPatternObjectFile(clazz, parser, partnerRole);
      insertHasWithParamInPatternObjectFile(clazz, parser, partnerRole);
      insertCreateWithParamInPatternObjectFile(clazz, parser, partnerRole);
      insertGetInPatternObjectFile(clazz, parser, partnerRole);
   }

   private void insertGetInPatternObjectFile(Clazz clazz, Parser parser, Role partnerRole)
   {
      String key = Parser.METHOD + ":get" + StrUtil.upFirstChar(partnerRole.getName()) + "()";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder();
         
         if (elistPos < 0 || partnerRole.getCard().equals(R.ONE.toString()))
         {
            text.append
            (       "   public TargetType getRoleName()\n"
                  + "   {\n"
                  + "      if (this.getPattern().getHasMatch())\n"
                  + "      {\n"
                  + "         return ((ModelClass) this.getCurrentMatch()).getRoleName();\n"
                  + "      }\n" + "      return null;\n" + "   }\n\n");
         }
         else
         {
            text.append
            (       "   public TargetType getRoleName()\n"
                  + "   {\n"
                  + "      if (this.getPattern().getHasMatch())\n"
                  + "      {\n"
                  + "         return ((ModelClass) this.getCurrentMatch()).getRoleNameSet();\n"
                  + "      }\n" + "      return null;\n" + "   }\n\n");
         }
         
         
         clazz.insertImport(parser, PatternLink.class.getName());
         
         String targetType = partnerRole.getClazz().shortNameAndImport(partnerRole.getClazz().getName(), parser);
         
         if (partnerRole.getCard().equals(R.MANY.toString()))
         {
            String fullTargetType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "Set");
            targetType = partnerRole.getClazz().shortNameAndImport(fullTargetType, parser);
         }
         
         CGUtil.replaceAll(text, 
            "TargetType", targetType,
            "ModelClass", this.getClazz().shortNameAndImport(this.getClazz().getName(), parser),
            "RoleName", StrUtil.upFirstChar(partnerRole.getName()), 
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }


   private void insertHasNoParamInPatternObjectFile(Clazz clazz, Parser parser,
         Role partnerRole)
   {
      String key = Parser.METHOD + ":has" + StrUtil.upFirstChar(partnerRole.getName()) + "()";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "   public PatternObjectType hasName()\n" + 
            "   {\n" + 
            "      PatternObjectType result = new PatternObjectType();\n" + 
            "      result.setModifier(this.getPattern().getModifier());\n" + 
            "      \n" + 
            "      super.hasLink(ModelClass.PROPERTY_NAME, result);\n" + 
            "      \n" + 
            "      return result;\n" + 
            "   }\n\n");

         clazz.insertImport(parser, PatternLink.class.getName());
         
         String fullPatternObjectType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "PO");
         String patternObjectType = partnerRole.getClazz().shortNameAndImport(fullPatternObjectType, parser);
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(partnerRole.getName()), 
            "ModelClass", getClazz().shortNameAndImport(getClazz().getName(), parser),
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }


   private void insertCreateNoParamInPatternObjectFile(Clazz clazz, Parser parser,
         Role partnerRole)
   {
      String key = Parser.METHOD + ":create" + StrUtil.upFirstChar(partnerRole.getName()) + "()";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "   public PatternObjectType createName()\n" + 
            "   {\n" + 
            "      return this.startCreate().hasName().endCreate();\n" + 
            "   }\n\n");

         clazz.insertImport(parser, PatternLink.class.getName());
         
         String fullPatternObjectType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "PO");
         String patternObjectType = partnerRole.getClazz().shortNameAndImport(fullPatternObjectType, parser);
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "Name", StrUtil.upFirstChar(partnerRole.getName()), 
            "ModelClass", getClazz().shortNameAndImport(getClazz().getName(), parser));

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }


   private void insertHasWithParamInPatternObjectFile(Clazz clazz, Parser parser,
         Role partnerRole)
   {
      String fullPatternObjectType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "PO");
      String patternObjectType = partnerRole.getClazz().shortNameAndImport(fullPatternObjectType, parser);
      
      String key = Parser.METHOD + ":has" + StrUtil.upFirstChar(partnerRole.getName()) + "(" + patternObjectType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "   public ModelPOType hasName(PatternObjectType tgt)\n" + 
            "   {\n" + 
            "      return hasLinkConstraint(tgt, ModelClass.PROPERTY_NAME);\n" + 
            "   }\n\n");

         clazz.insertImport(parser, LinkConstraint.class.getName());
         
         String fullModelPOType = CGUtil.helperClassName(clazz.getName(), "PO");
         String modelPOType = clazz.shortNameAndImport(fullModelPOType, parser);
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(partnerRole.getName()), 
            "ModelClass", getClazz().shortNameAndImport(getClazz().getName(), parser),
            "ModelPOType", modelPOType, 
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }


   private void insertCreateWithParamInPatternObjectFile(Clazz clazz, Parser parser,
         Role partnerRole)
   {
      String fullPatternObjectType = CGUtil.helperClassName(partnerRole.getClazz().getName(), "PO");
      String patternObjectType = partnerRole.getClazz().shortNameAndImport(fullPatternObjectType, parser);
      
      String key = Parser.METHOD + ":create" + StrUtil.upFirstChar(partnerRole.getName()) + "(" + patternObjectType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "   public ModelPOType createName(PatternObjectType tgt)\n" + 
            "   {\n" + 
            "      return this.startCreate().hasName(tgt).endCreate();\n" + 
            "   }\n\n");

         clazz.insertImport(parser, LinkConstraint.class.getName());
         
         String fullModelPOType = CGUtil.helperClassName(clazz.getName(), "PO");
         String modelPOType = clazz.shortNameAndImport(fullModelPOType, parser);
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "Name", StrUtil.upFirstChar(partnerRole.getName()), 
            "ModelPOType", modelPOType);

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         clazz.setPatternObjectFileHasChanged(true);
      }
   }


   private void insertSetterInModelSetFile(Clazz tgtClass, Parser parser, Role partnerRole)
   {
      String targetType = CGUtil.shortClassName(partnerRole.getClazz().getName());
      
      String key = Parser.METHOD + ":with" + StrUtil.upFirstChar(partnerRole.getName()) + "(" + targetType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "   public ModelSetType withName(TargetType value)\n" + 
            "   {\n" + 
            "      for (ContentType obj : this)\n" + 
            "      {\n" + 
            "         obj.withName(value);\n" + 
            "      }\n" + 
            "      \n" + 
            "      return this;\n" + 
            "   }\n\n"
            );

         CGUtil.replaceAll(text, 
            "TargetType", targetType,
            "ContentType", CGUtil.shortClassName(tgtClass.getName()),
            "ModelSetType", CGUtil.shortClassName(tgtClass.getName()) + "Set",
            "Name", StrUtil.upFirstChar(partnerRole.getName())
            );

         int classEnd = parser.indexOf(Parser.CLASS_END);
         
         parser.getFileBody().insert(classEnd, text.toString());
         tgtClass.setModelSetFileHasChanged(true);
         
         tgtClass.insertImport(parser, partnerRole.getClazz().getName());
      }
      
      if (partnerRole.getCard().equals(R.MANY.toString()))
      {
         key = Parser.METHOD + ":without" + StrUtil.upFirstChar(partnerRole.getName()) + "(" + targetType + ")";
         pos = parser.indexOf(key);

         if (pos < 0)
         {
            StringBuilder text = new StringBuilder(
               "   public ModelSetType withoutName(TargetType value)\n" + 
               "   {\n" + 
               "      for (ContentType obj : this)\n" + 
               "      {\n" + 
               "         obj.withoutName(value);\n" + 
               "      }\n" + 
               "      \n" + 
               "      return this;\n" + 
               "   }\n\n"
               );

            CGUtil.replaceAll(text, 
               "TargetType", targetType,
               "ContentType", CGUtil.shortClassName(tgtClass.getName()),
               "ModelSetType", CGUtil.shortClassName(tgtClass.getName()) + "Set",
               "Name", StrUtil.upFirstChar(partnerRole.getName())
               );

            int classEnd = parser.indexOf(Parser.CLASS_END);
            
            parser.getFileBody().insert(classEnd, text.toString());
            tgtClass.setModelSetFileHasChanged(true);
            
            tgtClass.insertImport(parser, partnerRole.getClazz().getName());
         }
      }
   }


   private void insertPropertyInCreatorClass(Clazz clazz, Parser parser, Role partnerRole)
   {
      String key = Parser.ATTRIBUTE + ":properties";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         // ups, did not find generic get method. 
         System.err.println("Warning: SDMLib codgen for role " + partnerRole.getName() + " for creator class for " + clazz.getName() 
            + ": \nDid not find properties field. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int endOfStringArrayInit = parser.getEndOfAttributeInitialization();
      
      String propertyName = "PROPERTY_" + partnerRole.getName().toUpperCase() +",";
      
      int propertyNameIndex = parser.getFileBody().indexOf(propertyName, pos);

      if (propertyNameIndex < 0 || propertyNameIndex > endOfStringArrayInit)
      {         
         // need to add property to string array
         
         StringBuilder text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

         String shortClassName = CGUtil.shortClassName(getClazz().getName());
         CGUtil.replaceAll(text, 
            "className", shortClassName,
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase()
            );

         parser.getFileBody().insert(endOfStringArrayInit, text.toString());
         clazz.insertImport(parser, getClazz().getName());
         clazz.setCreatorFileHasChanged(true);
      }
   }


   
   
   private void insertCaseInGenericSetToMany(Clazz clazz, Parser parser, Role partnerRole, String rootDir)
   {   
      if (clazz.getInterfaze())
      {
         return;
      }
      
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for role " + partnerRole.getName() + " for class " + clazz.getName() 
            + ": \nDid not find method set(String,Object). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + partnerRole.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic set method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }
         
         StringBuilder text = new StringBuilder
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         addToPropertyName((type) value);" +
               "\n         return true;" +
               "\n      }" +
               "\n      " + 
               "\n      if ((PROPERTY_NAME + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         removeFromPropertyName((type) value);" +
               "\n         return true;" +
               "\n      }" +
               "\n" 
               );

         String typePlaceholder = "type";
         String type = CGUtil.shortClassName(partnerRole.clazz.getName());

         CGUtil.replaceAll(text, 
            typePlaceholder, type, 
            "PropertyName", StrUtil.upFirstChar(partnerRole.getName()),
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase()
            );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         
         clazz.insertImport(JsonIdMap.class.getName());
         
         clazz.setFileHasChanged(true);
      }
   }

   private void insertCaseInGenericSetToOne(Clazz clazz, Parser parser, Role partnerRole, String rootDir)
   {
      if (clazz.getInterfaze())
      {
         return;
      }
      
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for role " + partnerRole.getName() + " for class " + clazz.getName() 
            + ": \nDid not find method set(String,Object). Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + partnerRole.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic set method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }
         
         StringBuilder text = new StringBuilder
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         setPropertyName((type) value);" +
               "\n         return true;" +
               "\n      }" +
               "\n" 
               );

         String typePlaceholder = "type";
         String type = CGUtil.shortClassName(partnerRole.getClazz().getName());

         CGUtil.replaceAll(text, 
            typePlaceholder, type, 
            "PropertyName", StrUtil.upFirstChar(partnerRole.getName()),
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase()
            );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         clazz.setFileHasChanged(true);
      }
   }


   private void insertCaseInGenericGet(Clazz clazz, Parser parser, Role partnerRole, String rootDir)
   {
      if (clazz.getInterfaze())
      {
         return;
      }
      
      int pos = parser.indexOf(Parser.METHOD + ":get(String)");

      if (pos < 0)
      {
         // ups, did not find generic get method. 
         System.err.println("Warning: SDMLib codgen for role" + partnerRole.getName() + " in class " + clazz.getName() 
            + ": \nDid not find method get(String). Should have been generated by the clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();
      
      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + partnerRole.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic get method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }
         
         StringBuilder text = new StringBuilder
            (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
               "\n      {" +
               "\n         return getPropertyName();" +
               "\n      }" +
               "\n" 
               );

         CGUtil.replaceAll(text, 
            "PropertyName", StrUtil.upFirstChar(partnerRole.getName()),
            "PROPERTY_NAME", "PROPERTY_" + partnerRole.getName().toUpperCase()
            );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         clazz.setFileHasChanged(true);         
      }
   }

   
	private void generateEmptySetInPartnerClass(String rootDir, Role partnerRole, boolean doGenerate)
   {
      // generate EMPTY_SET in partner class
      Clazz partnerClass = partnerRole.getClazz();
      Parser partnerParser = partnerClass.getOrCreateParser(rootDir);
      
      int partnerPos = partnerParser.indexOf(Parser.ATTRIBUTE + ":EMPTY_SET");
      
      String partnerClassName = CGUtil.shortClassName(partnerRole.getClazz().getName());

      if (partnerPos < 0)
      {
         // add attribute declaration in class file
         partnerPos = partnerParser.indexOf(Parser.CLASS_END);

         StringBuilder partnerText = new StringBuilder
            (  "\n   " +
               "\n   public static final type EMPTY_SET = new type();" +
               "\n"
               );
         
         String setClass = partnerClassName + "Set";
		CGUtil.replaceAll(partnerText, 
            "type", setClass
            );
         
         partnerParser.getFileBody().insert(partnerPos, partnerText.toString());
         
         // insert import 
         String ownerClassName = partnerClass.getName();
         String packageName = CGUtil.packageName(ownerClassName);
         String shortClassName = CGUtil.shortClassName(ownerClassName);
         partnerClass.insertImport(packageName + ".creators." + shortClassName + "Set");
         
         partnerRole.getClazz().setFileHasChanged(true);
         partnerRole.getClazz().printFile(doGenerate);

      }
      partnerRole.getClazz().insertImport(partnerRole.getClazz().getModelSetClassName());
   }

   private void generateToManyRole(Parser myParser, Clazz genClazz, Role partnerRole, StringBuilder text)
   {
      String myClassName = CGUtil.shortClassName(getClazz().getName());
      
      String partnerClassName = CGUtil.shortClassName(partnerRole.getClazz().getName());
      
      String partnerRoleName = partnerRole.getName();
      
      String partnerRoleUpFirstChar = StrUtil.upFirstChar(partnerRoleName);
      
      int pos = myParser.indexOf(Parser.ATTRIBUTE + ":PROPERTY_" + partnerRole.getName().toUpperCase());

      if (pos < 0 && ! this.getClazz().isInterfaze())
      {
         text.append
         (  "\n   " +
               "\n   /********************************************************************" +
               "\n    * <pre>" +
               "\n    *              myCard                       partnerCard" +
               "\n    * myClassName ----------------------------------- partnerClassName" +
               "\n    *              myRoleName                   partnerRoleName" +
               "\n    * </pre>" +
               "\n    */" + 
               "\n   " +
               "\n   public static final String PROPERTY_PARTNER_ROLE_NAME = \"partnerRoleName\";" +
               "\n" );
      }
      
      if (! genClazz.getInterfaze())
      {
         pos = myParser.indexOf(Parser.ATTRIBUTE + ":" + partnerRoleName);

         if (pos < 0)
         {
            text.append ("\n   private type partnerRoleName = null;" +
                         "\n   ");
         }
      }
      
      pos = myParser.indexOf(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "()");

      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public type getPartnerRoleName()" +
                  "\n   {" +
                  "\n      if (this.partnerRoleName == null)" +
                  "\n      {" +
                  "\n         return partnerClassName.EMPTY_SET;" +
                  "\n      }" +
                  "\n   " +
                  "\n      return this.partnerRoleName;" +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public type getPartnerRoleName();" +
                  "\n");
         }
      }
      
      ArrayList<SymTabEntry> symTabEntries = myParser.getSymTabEntriesFor(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "()");
      
      elistPos = -1;
      
      if (symTabEntries.size() > 0)
      {
         String type = symTabEntries.get(0).getType();
         elistPos = type.indexOf(":EList<");
      }
      
      if (elistPos >= 0)
      {
         pos = myParser.indexOf(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "Set()");

         if (pos < 0)
         {
            if (! genClazz.getInterfaze())
            {
               text.append 
               (     "  public type getPartnerRoleNameSet()\n" + 
                     "  {\n" + 
                     "     return new type().with(getPartnerRoleName());\n" + 
                     "  }\n" + 
                     "\n");
            }
            else
            {
               text.append
               (     "\n   public type getPartnerRoleNameSet();" +
                     "\n");
            }
         }
      }
      
      
      if (this.getClazz() == this.getPartnerRole().getClazz())
      {
         // recursive assoc, add getTransitive methods
         
         pos = myParser.indexOf(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "Transitive()");
         
         if (pos < 0)
         {
            if (! genClazz.getInterfaze())
            {
               text.append(
                  "   public partnerClassNameSet getPartnerRoleNameTransitive()\n" + 
                  "   {\n" + 
                  "      partnerClassNameSet result = new partnerClassNameSet().with(this);\n" + 
                  "      return result.getPartnerRoleNameTransitive();\n" + 
                  "   }\n" + 
                  "\n" + 
                  ""
                  );
            }
            else
            {
               text.append
               (     "\n   public partnerClassName getPartnerRoleNameTransitive();" +
                     "\n");
            }      
         
         }
      }
      
      
      
      pos = myParser.indexOf(Parser.METHOD + ":addTo" + partnerRoleUpFirstChar + "(" + partnerClassName  +  ")");
      
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            if (elistPos < 0)
            {
               text.append 
               (     "\n   public boolean addToPartnerRoleName(partnerClassName value)" +
                     "\n   {" +
                     "\n      boolean changed = false;" +
                     "\n      " +
                     "\n      if (value != null)" +
                     "\n      {" +
                     "\n         if (this.partnerRoleName == null)" +
                     "\n         {" +
                     "\n            this.partnerRoleName = new type();" +
                     "\n         }" +
                     "\n         " +
                     "\n         changed = this.partnerRoleName.add (value);" +
                     "\n         " +
                     "\n         if (changed)" +
                     "\n         {" +
                     "\n            value.withMyRoleName(this);" +
                     "\n            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNER_ROLE_NAME, null, value);" +
                     "\n         }" +
                     "\n      }" +
                     "\n         " +
                     "\n      return changed;   " +
                     "\n   }" +
                     "\n");
            }
            else
            {
               text.append 
               (     "\n   public boolean addToPartnerRoleName(partnerClassName value)" +
                     "\n   {" +
                     "\n      boolean changed = false;" +
                     "\n      " +
                     "\n      if (value != null)" +
                     "\n      {" +
                     "\n         changed = this.getPartnerRoleName().add (value);" +
                     "\n         " +
                     "\n         if (changed)" +
                     "\n         {" +
                     "\n            value.withMyRoleName(this);" +
                     "\n            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNER_ROLE_NAME, null, value);" +
                     "\n         }" +
                     "\n      }" +
                     "\n         " +
                     "\n      return changed;   " +
                     "\n   }" +
                     "\n");
            }
         }
         else
         {
            text.append
            (     "\n   boolean addToPartnerRoleName(partnerClassName value);" +
                  "\n");
         }
      }
      
      pos = myParser.indexOf(Parser.METHOD + ":removeFrom" + partnerRoleUpFirstChar + "(" + partnerClassName  +  ")");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public boolean removeFromPartnerRoleName(partnerClassName value)" +
                  "\n   {" +
                  "\n      boolean changed = false;" +
                  "\n      " +
                  "\n      if ((this.partnerRoleName != null) && (value != null))" +
                  "\n      {" +
                  "\n         changed = this.partnerRoleName.remove (value);" +
                  "\n         " +
                  "\n         if (changed)" +
                  "\n         {" +
                  "\n            value.reverseWithoutCall(this);" +
                  "\n            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNER_ROLE_NAME, value, null);" +
                  "\n         }" +
                  "\n      }" +
                  "\n         " +
                  "\n      return changed;   " +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   boolean removeFromPartnerRoleName(partnerClassName value);" +
                  "\n");
         }
      }
      
      
      pos = myParser.indexOf(Parser.METHOD + ":with" + partnerRoleUpFirstChar + "(" + partnerClassName  +  "...)");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public myClassName withPartnerRoleName(partnerClassName... value)" +
                  "\n   {" +
                  "\n      for (partnerClassName item : value)" +
                  "\n      {" +
                  "\n         addToPartnerRoleName(item);" +
                  "\n      }" +
                  "\n      return this;" +
                  "\n   } " +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public myClassName withPartnerRoleName(partnerClassName... value);" +
                  "\n");
         }
      }
      
      
      pos = myParser.indexOf(Parser.METHOD + ":without" + partnerRoleUpFirstChar + "(" + partnerClassName  +  "...)");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public myClassName withoutPartnerRoleName(partnerClassName... value)" +
                  "\n   {" +
                  "\n      for (partnerClassName item : value)" +
                  "\n      {" +
                  "\n         removeFromPartnerRoleName(item);" +
                  "\n      }" +
                  "\n      return this;" +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public myClassName withoutPartnerRoleName(partnerClassName... value);" +
                  "\n");
         }
      }
      
      
      pos = myParser.indexOf(Parser.METHOD + ":removeAllFrom" + partnerRoleUpFirstChar + "()");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public void removeAllFromPartnerRoleName()" +
                  "\n   {" +
                  "\n      LinkedHashSet<partnerClassName> tmpSet = new LinkedHashSet<partnerClassName>(this.getPartnerRoleName());" +
                  "\n   " +
                  "\n      for (partnerClassName value : tmpSet)" +
                  "\n      {" +
                  "\n         this.removeFromPartnerRoleName(value);" +
                  "\n      }" +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public void removeAllFromPartnerRoleName();" +
                  "\n");
         }
      }
      
      
      pos = myParser.indexOf(Parser.METHOD + ":create" + partnerRoleUpFirstChar + "()");
      
      String realPartnerClassName = partnerClassName;
      
      if (partnerRole.getClazz().getInterfaze() && partnerRole.getClazz().getKidClassesAsInterface().size() == 1)
      {
         realPartnerClassName = CGUtil.shortClassName(partnerRole.getClazz().getKidClassesAsInterface().first().getName());
      }
      
      if (pos < 0 && ! (partnerRole.getClazz().getInterfaze() && partnerRole.getClazz().getKidClassesAsInterface().size() != 1))
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public partnerClassName createPartnerRoleName()" +
                  "\n   {" +
                  "\n      partnerClassName value = new realPartnerClassName();" +
                  "\n      withPartnerRoleName(value);" +
                  "\n      return value;" +
                  "\n   } " +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public partnerClassName createPartnerRoleName();" +
                  "\n");
         }
      }
      
      
      String reverseWithoutCall = "set" + StrUtil.upFirstChar(getName()) + "(null)";
      
      if (getCard().equals(R.MANY.toString()))
      {
         reverseWithoutCall = "without" + StrUtil.upFirstChar(getName()) + "(this)";
      }
      
      CGUtil.replaceAll(text, 
         "myCard", this.getCard(),
         "partnerCard", partnerRole.getCard(),
         "type", partnerClassName + "Set", 
         "myClassName", myClassName,
         "partnerClassName", partnerClassName,
         "realPartnerClassName", realPartnerClassName,
         "myRoleName", getName(),
         "MyRoleName", StrUtil.upFirstChar(getName()),
         "partnerRoleName", partnerRoleName,
         "PARTNER_ROLE_NAME", partnerRoleName.toUpperCase(),
         "PartnerRoleName", partnerRoleUpFirstChar,
         "reverseWithoutCall(this)", reverseWithoutCall
         );
      
      getClazz().insertImport(myParser, partnerRole.getClazz().getModelSetClassName());
   } 
   
   private void generateToOneRole(Parser myParser, Clazz genClazz, Role partnerRole, StringBuilder text)
   {
      String myClassName = CGUtil.shortClassName(getClazz().getName());
      
      String partnerClassName = CGUtil.shortClassName(partnerRole.getClazz().getName());
      
      String partnerRoleName = partnerRole.getName();
      
      String partnerRoleUpFirstChar = StrUtil.upFirstChar(partnerRoleName);
      
      int pos = myParser.indexOf(Parser.ATTRIBUTE + ":PROPERTY_" + partnerRole.getName().toUpperCase());

      if (pos < 0  && ! this.getClazz().isInterfaze())
      {
         text.append
         (  "\n   " +
               "\n   /********************************************************************" +
               "\n    * <pre>" +
               "\n    *              myCard                       partnerCard" +
               "\n    * myClassName ----------------------------------- partnerClassName" +
               "\n    *              myRoleName                   partnerRoleName" +
               "\n    * </pre>" +
               "\n    */" + 
               "\n   " +
               "\n   public static final String PROPERTY_PARTNER_ROLE_NAME = \"partnerRoleName\";" +
               "\n" );
      }
      
      if (! genClazz.getInterfaze())
      {
         pos = myParser.indexOf(Parser.ATTRIBUTE + ":" + partnerRoleName);

         if (pos < 0)
         {
            text.append ("\n   private partnerClassName partnerRoleName = null;" +
                         "\n");
         }
      }
      
      pos = myParser.indexOf(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "()");

      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public partnerClassName getPartnerRoleName()" +
                  "\n   {" +
                  "\n      return this.partnerRoleName;" +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public partnerClassName getPartnerRoleName();" +
                  "\n");
         }
      }
      
      
      if (this.getClazz() == this.getPartnerRole().getClazz())
      {
         // recursive assoc, add getTransitive methods
         
         pos = myParser.indexOf(Parser.METHOD + ":get" + partnerRoleUpFirstChar + "Transitive()");
         
         if (pos < 0)
         {
            if (! genClazz.getInterfaze())
            {
               text.append(
                  "   public partnerClassNameSet getPartnerRoleNameTransitive()\n" + 
                  "   {\n" + 
                  "      partnerClassNameSet result = new partnerClassNameSet().with(this);\n" + 
                  "      return result.getPartnerRoleNameTransitive();\n" + 
                  "   }\n" + 
                  "\n" + 
                  ""
                  );
            }
            else
            {
               text.append
               (     "\n   public partnerClassName getPartnerRoleNameTransitive();" +
                     "\n");
            }      
         
         }
      }
      
      pos = myParser.indexOf(Parser.METHOD + ":set" + partnerRoleUpFirstChar + "(" + partnerClassName  +  ")");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public boolean setPartnerRoleName(partnerClassName value)" +
                  "\n   {" +
                  "\n      boolean changed = false;" +
                  "\n      " +
                  "\n      if (this.partnerRoleName != value)" +
                  "\n      {" +
                  "\n         partnerClassName oldValue = this.partnerRoleName;" +
                  "\n         " +
                  "\n         if (this.partnerRoleName != null)" +
                  "\n         {" +
                  "\n            this.partnerRoleName = null;" +
                  "\n            oldValue.withoutMethodCall(this);" +
                  "\n         }" +
                  "\n         " +
                  "\n         this.partnerRoleName = value;" +
                  "\n         " +
                  "\n         if (value != null)" +
                  "\n         {" +
                  "\n            value.withMyRoleName(this);" +
                  "\n         }" +
                  "\n         " +
                  "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNER_ROLE_NAME, oldValue, value);" +
                  "\n         changed = true;" +
                  "\n      }" +
                  "\n      " +
                  "\n      return changed;" +
                  "\n   }" +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public boolean setPartnerRoleName(partnerClassName value);" +
                  "\n");
         }
      }
      
      
      pos = myParser.indexOf(Parser.METHOD + ":with" + partnerRoleUpFirstChar + "(" + partnerClassName  +  ")");
      
      if (pos < 0)
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public myClassName withPartnerRoleName(partnerClassName value)" +
                  "\n   {" +
                  "\n      setPartnerRoleName(value);" +
                  "\n      return this;" +
                  "\n   } " +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public myClassName withPartnerRoleName(partnerClassName value);" +
                  "\n");
         }
      }
      

      pos = myParser.indexOf(Parser.METHOD + ":create" + partnerRoleUpFirstChar + "()");
      
      String realPartnerClassName = partnerClassName;
      
      if (partnerRole.getClazz().getInterfaze() && partnerRole.getClazz().getKidClassesAsInterface().size() == 1)
      {
         realPartnerClassName = CGUtil.shortClassName(partnerRole.getClazz().getKidClassesAsInterface().first().getName());
      }
      
      if (pos < 0 && ! (partnerRole.getClazz().getInterfaze() && partnerRole.getClazz().getKidClassesAsInterface().size() != 1))
      {
         if (! genClazz.getInterfaze())
         {
            text.append 
            (     "\n   public partnerClassName createPartnerRoleName()" +
                  "\n   {" +
                  "\n      partnerClassName value = new realPartnerClassName();" +
                  "\n      withPartnerRoleName(value);" +
                  "\n      return value;" +
                  "\n   } " +
                  "\n");
         }
         else
         {
            text.append
            (     "\n   public partnerClassName createPartnerRoleName();" +
                  "\n");
         }
      }
      
      

      String reverseWithoutCall = "set" + StrUtil.upFirstChar(getName()) + "(null)";
      
      if (getCard().equals(R.MANY.toString()))
      {
         reverseWithoutCall = "without" + StrUtil.upFirstChar(getName()) + "(this)";
      }
      
      CGUtil.replaceAll(text, 
         "myCard", this.getCard(),
         "partnerCard", partnerRole.getCard(),
         "myClassName", myClassName,
         "partnerClassName", partnerClassName,
         "realPartnerClassName", realPartnerClassName,
         "myRoleName", getName(),
         "MyRoleName", StrUtil.upFirstChar(getName()),
         "partnerRoleName", partnerRoleName,
         "PARTNER_ROLE_NAME", partnerRoleName.toUpperCase(),
         "PartnerRoleName", partnerRoleUpFirstChar,
         "withoutMethodCall(this)", reverseWithoutCall
         );
   } 


   
   public static final RoleSet EMPTY_SET = new RoleSet();

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Role ----------------------------------- Association
    *              source                   assoc
    * </pre>
    */
   
   public static final String PROPERTY_ASSOC = "assoc";
   
   private Association assoc = null;
   
   public Association getAssoc()
   {
      return this.assoc;
   }
   
   public boolean setAssoc(Association value)
   {
      boolean changed = false;
      
      if (this.assoc != value)
      {
         Association oldValue = this.assoc;
         
         this.assoc = value;
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Role withAssoc(Association value)
   {
      setAssoc(value);
      return this;
   } 

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_CARD.equalsIgnoreCase(attribute))
      {
         return getCard();
      }

      if (PROPERTY_KIND.equalsIgnoreCase(attribute))
      {
         return getKind();
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return getClazz();
      }

      if (PROPERTY_ASSOC.equalsIgnoreCase(attribute))
      {
         return getAssoc();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         setKind((String) value);
         return true;
      }

      if (PROPERTY_CARD.equalsIgnoreCase(attrName))
      {
         setCard((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         setClazz((Clazz) value);
         return true;
      }

      if (PROPERTY_ASSOC.equalsIgnoreCase(attrName))
      {
         setAssoc((Association) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   private int elistPos;
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setClazz(null);
      setAssoc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public Role getPartnerRole()
   {
      if (this == this.getAssoc().getSource())
      {
         return this.getAssoc().getTarget();
      }
      else 
      {
         return this.getAssoc().getSource();
      }
   }

   public Clazz createClazz()
   {
      Clazz value = new Clazz();
      withClazz(value);
      return value;
   } 

   public Association createAssoc()
   {
      Association value = new Association();
      withAssoc(value);
      return value;
   } 
}

