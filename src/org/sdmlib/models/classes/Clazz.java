/*
   Copyright (c) 2012 Albert Z�ndorf

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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.classes.creators.AttributeSet;
import org.sdmlib.models.classes.creators.ClazzSet;
import org.sdmlib.models.classes.creators.MethodSet;
import org.sdmlib.models.classes.creators.RoleSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class Clazz implements PropertyChangeInterface
{
   public static final ClazzSet EMPTY_SET = new ClazzSet();

   public static Clazz clazz = null;

   public Clazz()
   {
      clazz = this;

      if (ClassModel.classModel != null)
      {
         setClassModel(ClassModel.classModel);
         ClassModel.classModel.addToClasses(this);
      }
   }

   public Clazz(String name, String... attrNameTypePairs)
   {
      this();

      if (getClassModel() != null)
      {
         if(name.indexOf('.') < 0)
         {
            name = "" + getClassModel().getPackageName()  + "." + name;
         }
      }
      setName(name);

      this.withAttributes(attrNameTypePairs);
   }

   public Clazz createClassAndAssoc(String tgtClassName, String tgtRoleName, R tgtCard, String srcRoleName, R srcCard)
   {
      Clazz tgtClazz = new Clazz(tgtClassName);

      new Association()
      .withTarget(tgtRoleName, tgtClazz, tgtCard)
      .withSource(srcRoleName, this, srcCard);

      return tgtClazz;
   }

   public Clazz withAssoc(Clazz tgtClass, String tgtRoleName, R tgtCard, String srcRoleName, R srcCard)
   {      
      new Association()
      .withTarget(tgtRoleName, tgtClass, tgtCard)
      .withSource(srcRoleName, this, srcCard);

      return this;
   }

   public static final String PROPERTY_NAME = "name";
   private String name = null; 

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Clazz withName(String name)
   {
      setName(name);
      return this;
   }

   public static final String PROPERTY_SUPERCLASS = "superClass";
   private Clazz superClass = null; 

   public Clazz getSuperClass()
   {
      return superClass;
   }

   public void setSuperClass(Clazz superClass)
   {
      this.superClass = superClass;
   }

   public Clazz withSuperClass(Clazz superClass)
   {
      setSuperClass(superClass);
      superClass.addToKindClasses(this);
      return this;
   }

   public static final String PROPERTY_CLASSMODEL = "classModel";
   private ClassModel classModel = null;

   private StringBuilder fileBody;
   private StringBuilder creatorFileBody;

   private boolean fileHasChanged;
   private boolean creatorFileHasChanged;

   private boolean modelSetFileHasChanged;

   private boolean patternObjectFileHasChanged;

   private boolean patternObjectCreatorFileHasChanged;

   public void setPatternObjectCreatorFileHasChanged(boolean patternObjectCreatorFileHasChanged)
   {
      this.patternObjectCreatorFileHasChanged = patternObjectCreatorFileHasChanged;
   }

   public void setPatternObjectFileHasChanged(boolean patternObjectFileHasChanged)
   {
      this.patternObjectFileHasChanged = patternObjectFileHasChanged;
   }

   public void setCreatorFileHasChanged(boolean creatorFileHasChanged)
   {
      this.creatorFileHasChanged = creatorFileHasChanged;
   }

   public void setModelSetFileHasChanged(boolean modelSetFileHasChanged)
   {
      this.modelSetFileHasChanged = modelSetFileHasChanged;
   }

   public StringBuilder getFileBody()
   {
      return fileBody;
   }

   public ClassModel getClassModel()
   {
      return classModel;
   }

   public void setClassModel(ClassModel classModel)
   {
      this.classModel = classModel;
   }

   public Clazz withClassModel(ClassModel classModel)
   {
      setClassModel(classModel);
      return this;
   }

   public Clazz generate(String rootDir, String helpersDir)
   {
      if (isExternal())
      {
         // nothing to generate 
         return this;
      }

      // first generate the class itself
      if ( ! getWrapped())
      {
         getOrCreateParser(rootDir);

         insertLicense(parser);

         insertInterfaces();

         if ( !isInterfaze() )
         {
            insertGenericGetSet();
            insertSuperClass();
            insertPropertyChangeSupport();
            insertRemoveYourMethod();
            insertInterfaceMethods(this, rootDir, helpersDir);
            insertInterfaceAttributesInCreatorClass(this, rootDir, helpersDir);
         }

         for (Method method : this.getMethods()) {
            method.generate(rootDir, helpersDir, false);
         }
      }

      generateAttributes(rootDir, helpersDir);

      if ( ! getWrapped())
      {
         printFile(isFileHasChanged());
      }

      if ( !isInterfaze() )
      {
         // now generate the corresponding creator class
         getOrCreateParserForCreatorClass(helpersDir);
         insertRemoveObjectInCreatorClass();
      }
      printCreatorFile(creatorFileHasChanged);

      // now generate the corresponding ModelSet class
      getOrCreateParserForModelSetFile(helpersDir);
      printModelSetFile(modelSetFileHasChanged);

      // now generate the corresponding PatterObject class
      getOrCreateParserForPatternObjectFile(helpersDir);
      printPatternObjectFile(patternObjectFileHasChanged);

      // now generate the corresponding PatterObjectCreator class
      getOrCreateParserForPatternObjectCreatorFile(helpersDir);
      printPatternObjectCreatorFile(patternObjectCreatorFileHasChanged);

      return this;
   }

   private void generateAttributes(String rootDir, String helpersDir) 
   {
      for (Attribute attr : this.getAttributes()) 
      {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         attr.generate(rootDir, helpersDir, false);
      }

      if (superClass != null) 
      {
         gernerateSuperAttributes(superClass, rootDir, helpersDir);
      }
   }

   private void gernerateSuperAttributes(Clazz superClazz, String rootDir, String helpersDir) {

      for (Attribute attr : superClazz.getAttributes()) {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         attr.generate(this, rootDir, helpersDir, false, true);
      }
      if (superClazz.getSuperClass() != null) {
         gernerateSuperAttributes(superClazz.getSuperClass(), rootDir, helpersDir);
      }
   }

   private void insertInterfaceAttributesInCreatorClass(Clazz clazz, String rootDir, String helpersDir)
   {
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (interfaze.isInterfaze())
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               Parser creatorParser = this.getOrCreateParserForCreatorClass(helpersDir);
               attr.insertPropertyInCreatorClass(interfaze.getName(), creatorParser, helpersDir, false);
            }

         }

         insertInterfaceAttributesInCreatorClass(interfaze, rootDir, helpersDir);

      } 
   }

   private void insertInterfaceMethods(Clazz clazz, String rootDir, String helpersDir)
   {
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (interfaze.isInterfaze())
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               attr.generateMethodsInKindClass(this, rootDir, helpersDir, false);
            }

            for (Method method : interfaze.getMethods())
            {
               method.generate(this, rootDir, helpersDir, false);
            }

         }

         insertInterfaceMethods(interfaze, rootDir, helpersDir);

      } 
   }

   private void insertInterfaces()
   {

      String string = Parser.IMPLEMENTS;
      if (isInterfaze())
         string = Parser.EXTENDS;

      for ( Clazz interfaze : getInterfaces() )
      {
         int extendsPos = parser.indexOf(string);

         if (extendsPos < 0)
         {
            extendsPos = parser.getEndOfClassName();

            parser.getFileBody().insert(extendsPos + 1, 
               " " + string + " " + CGUtil.shortClassName(interfaze.getName()));

            insertImport(interfaze.getName());

            setFileHasChanged(true);
         }
         else 
         {
            String shortClassName = CGUtil.shortClassName( interfaze.getName());

            String key = string + ":" + shortClassName;

            SymTabEntry symTabEntry = parser.getSymTab().get(key);

            if (symTabEntry == null)
            {
               parser.getFileBody().insert(parser.getEndOfImplementsClause() + 1, ", " + shortClassName);

               insertImport(interfaze.getName());

               setFileHasChanged(true);
            }               
         }
      }	  
   }

   private void insertSuperClass()
   {
      if (superClass == null) {
         return;
      }

      String searchString = Parser.EXTENDS;
      int extendsPos = parser.indexOf(searchString);

      if (extendsPos < 0)
      {
         extendsPos = parser.getEndOfClassName();

         parser.getFileBody().insert(extendsPos + 1, 
            " extends " + CGUtil.shortClassName(superClass.getName()));

         insertImport(superClass.getName());

         setFileHasChanged(true);
      }
   }

   private void insertRemoveObjectInCreatorClass()
   {
      String searchString = Parser.METHOD + ":removeObject(Object)";
      int pos = creatorParser.indexOf(searchString);

      if (pos < 0)
      {
         // add removeObject method
         pos = creatorParser.indexOf(Parser.CLASS_END);

         StringBuilder text = new StringBuilder
               (     "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   @Override\n" + 
                     "   public void removeObject(Object entity)\n" + 
                     "   {\n" + 
                     "      ((ModelClass) entity).removeYou();\n" + 
                     "   }" +
                     "\n"
                     );
         
         if (getWrapped())
         {
            CGUtil.replaceAll(text,
               "((ModelClass) entity).removeYou();", "// wrapped object has no removeYou method");
         }

         CGUtil.replaceAll(text,
            "ModelClass",  CGUtil.shortClassName(this.getName()));

         creatorParser.getFileBody().insert(pos, text.toString());
         setCreatorFileHasChanged(true);
      }
   }


   private void insertRemoveYourMethod()
   {
      String searchString = Parser.METHOD + ":removeYou()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         // add removeYou method
         pos = parser.indexOf(Parser.CLASS_END);

         StringBuilder text = new StringBuilder
               (     "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   public void removeYou()" +
                     "\n   {" +
                     "\n      getPropertyChangeSupport().firePropertyChange(\"REMOVE_YOU\", this, null);" +              
                     "\n   }" +
                     "\n"
                     );

         if (superClass != null) {

            CGUtil.replaceAll(text,"\n   }",  "\n      super.removeYou();\n   }");

         }

         parser.getFileBody().insert(pos, text.toString());
         setFileHasChanged(true);
      }
   }

   private void insertPropertyChangeSupport()
   {
      insertImplementsClauseForPropertyChangeInterface();

      // does it implement PropertyChangeSupportClient?
      String searchString = Parser.METHOD + ":getPropertyChangeSupport()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         // add property change implementation
         pos = parser.indexOf(Parser.CLASS_END);
         //System.out.println(parser.getLineIndexOf(pos));

         StringBuilder text = new StringBuilder
               (  "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);" +
                     "\n   " +
                     "\n   public PropertyChangeSupport getPropertyChangeSupport()" +
                     "\n   {" +
                     "\n      return listeners;" +
                     "\n   }" +
                     "\n"
                     );

         parser.getFileBody().insert(pos, text.toString());
         setFileHasChanged(true);
      }

      insertImport(PropertyChangeSupport.class.getName());
   }

   private void insertImplementsClauseForPropertyChangeInterface()
   {
      String searchString = Parser.IMPLEMENTS;
      int implementsPos = parser.indexOf(searchString);

      String propertyChangeInterface = PropertyChangeInterface.class.getSimpleName();

      if (implementsPos < 0)
      {
         // class has no implements clause at all
         implementsPos = parser.getEndOfExtendsClause();

         if (implementsPos == 0)
         {
            // class does not even have an extends clause
            implementsPos = parser.getEndOfClassName();
         }

         String string = " implements ";
         if (isInterfaze())
            string = " extends ";
         parser.getFileBody().insert(implementsPos + 1, string + propertyChangeInterface);

         insertImport(PropertyChangeInterface.class.getName());

         setFileHasChanged(true);
      }
      else
      {
         // there is already an implements clause, does it already implement PropertyChangeInterface? 
         SymTabEntry symTabEntry = parser.getSymTab().get(Parser.IMPLEMENTS + ":" + propertyChangeInterface);

         if (symTabEntry == null)
         {
            // propertyChangeClients is still missing.
            parser.getFileBody().insert(parser.getEndOfImplementsClause() + 1, 
               ", " + propertyChangeInterface);

            setFileHasChanged(true);
         }

         insertImport(PropertyChangeInterface.class.getName());        
      }
   }

   public void insertImport(String className)
   {
      insertImport(parser, className);
   }

   public void insertImport(Parser myParser, String className)
   {
      if ("String int double float boolean void".indexOf(className) >= 0)
      {
         return;
      }

      int pos = myParser.indexOf(Parser.IMPORT);

      String prefix = "";
      if (myParser.getFileBody().indexOf(Parser.IMPORT, pos) < 0)
      {
         prefix = "\n";
      }

      SymTabEntry symTabEntry = myParser.getSymTab().get(Parser.IMPORT + ":" + className);
      if (symTabEntry == null)
      {
         myParser.getFileBody().insert(myParser.getEndOfImports() + 1, 
            prefix + "\nimport " + className + ";");

         // setFileHasChanged(true);
      }
   }

   public void printFile(boolean really)
   {
      if (really || fileHasChanged)
      {
         while (fileBody.charAt(fileBody.length() - 1) == '\n')
         {
            fileBody.replace(fileBody.length() - 1 , fileBody.length(), "");
         }
         fileBody.append('\n');

         CGUtil.printFile(javaFile, fileBody.toString());
      }
   }

   public void printCreatorFile(boolean really)
   {
      if (really || creatorFileHasChanged)
      {
         CGUtil.printFile(creatorJavaFile, creatorFileBody.toString());
      }
   }

   public void printModelSetFile(boolean really)
   {
      if (really || modelSetFileHasChanged)
      {
         CGUtil.printFile(modelSetJavaFile, modelSetFileBody.toString());
      }
   }

   public void printPatternObjectFile(boolean really)
   {
      if (really || patternObjectFileHasChanged)
      {
         CGUtil.printFile(patternObjectJavaFile, patternObjectFileBody.toString());
      }
   }

   public void printPatternObjectCreatorFile(boolean really)
   {
      if (really || patternObjectCreatorFileHasChanged)
      {
         CGUtil.printFile(patternObjectCreatorJavaFile, patternObjectCreatorFileBody.toString());
      }
   }

   private void insertGenericGetSet()
   {
      // class should have generic get(String attrName) method;
      String searchString = Parser.METHOD + ":get(String)";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         // add generic get method in class file
         pos = parser.indexOf(Parser.CLASS_END);

         StringBuilder text = new StringBuilder
               (  "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   public Object get(String attrName)" +
                     "\n   {" +
                     "\n      int pos = attrName.indexOf('.');" +
                     "\n      String attribute = attrName;" +
                     "\n      " +
                     "\n      if (pos > 0)" +
                     "\n      {" +
                     "\n         attribute = attrName.substring(0, pos);" +
                     "\n      }" +
                     "\n      " +
                     "\n      return null;" +
                     "\n   }" +
                     "\n"
                     );

         parser.getFileBody().insert(pos, text.toString());
         setFileHasChanged(true);
      }

      searchString = Parser.METHOD + ":set(String,Object)";

      pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         // add generic get method in class file
         pos = parser.indexOf(Parser.CLASS_END);

         StringBuilder text = new StringBuilder
               (  "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   public boolean set(String attrName, Object value)" +
                     "\n   {" +
                     "\n      return false;" +
                     "\n   }" +
                     "\n"
                     );

         parser.getFileBody().insert(pos, text.toString());
         setFileHasChanged(true);
      }
   }

   private void insertLicense(Parser parser)
   {
      // file should start with head comment
      int pos = parser.getFileBody().indexOf("/*");
      if (pos < 0 || pos > 20)
      {
         // insert MIT License otherwise. 
         setFileHasChanged(true);
         StringBuilder text = new StringBuilder(
            "/*\n" +
                  "   Copyright (c) <year> <developer> \n" +
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
               "   \n");

         String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
         CGUtil.replace(text, "<year>", year);

         CGUtil.replace(text, "<developer>", System.getProperty("user.name"));

         parser.getFileBody().replace(0, 0, text.toString());
         setFileHasChanged(true);
      }

   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Attribute
    *              clazz                   attributes
    * </pre>
    */

   public static final String PROPERTY_ATTRIBUTES = "attributes";

   private AttributeSet attributes = null;

   public AttributeSet getAttributes()
   {
      if (this.attributes == null)
      {
         return Attribute.EMPTY_SET;
      }

      return this.attributes;
   }

   public boolean addToAttributes(Attribute value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.attributes == null)
         {
            this.attributes = new AttributeSet();
         }

         changed = this.attributes.add (value);

         if (changed)
         {
            value.setClazz(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromAttributes(Attribute value)
   {
      boolean changed = false;

      if ((this.attributes != null) && (value != null))
      {
         changed = this.attributes.remove (value);

         if (changed)
         {
            value.setClazz(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, value, null);
         }
      }

      return changed;   
   }

   public Clazz withAttributes(Attribute value)
   {
      addToAttributes(value);
      return this;
   } 

   public Clazz withoutAttributes(Attribute value)
   {
      removeFromAttributes(value);
      return this;
   } 

   public void removeAllFromAttributes()
   {
      LinkedHashSet<Attribute> tmpSet = new LinkedHashSet<Attribute>(this.getAttributes());

      for (Attribute value : tmpSet)
      {
         this.removeFromAttributes(value);
      }
   }


   Parser parser = null;
   Parser creatorParser = null;

   private File javaFile;
   private File creatorJavaFile;
   private File modelSetJavaFile;
   private File patternObjectJavaFile;
   private File patternObjectCreatorJavaFile;

   private Parser modelSetParser = null;

   private StringBuilder modelSetFileBody;

   private Parser patternObjectParser = null;

   private StringBuilder patternObjectFileBody;

   private Parser patternObjectCreatorParser = null;

   private StringBuilder patternObjectCreatorFileBody;

   public File getJavaFile()
   {
      return javaFile;
   }

   public Parser getOrCreateParser(String rootDir)
   {
      if (parser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');

               String packageName = name.substring(0, pos);
               String fileName = name;

               String className = name.substring(pos+1);

               fileName = fileName.replaceAll("\\.", "/");

               fileName = rootDir + "/" + fileName + ".java";

               javaFile = new File(fileName);

               // found old one?
               if (javaFile.exists())
               {
                  fileBody = CGUtil.readFile(javaFile);
               }
               else
               {
                  System.out.println("generate/modify file for " + fileName);
                  fileBody = new StringBuilder();

                  StringBuilder text = new StringBuilder(
                     "package packageName;\n" +
                           "\n" +
                           "public class className\n" +
                           "{\n" +
                        "}\n");

                  if (isInterfaze()) {
                     CGUtil.replaceAll(text, "public class className", "public interface className");
                  }

                  CGUtil.replaceAll(text, 
                     "className", className, 
                     "packageName", packageName);

                  fileBody.append(text.toString());

                  setFileHasChanged(true);
               }

               parser = new Parser()
               .withFileName(fileName)
               .withFileBody(fileBody);         
      }

      return parser;
   }

   public Parser getOrCreateParserForCreatorClass(String rootDir)
   {
      if (creatorParser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + ".creators";

         if (getWrapped())
         {
            packageName = getClassModel().getPackageName() + ".creators";
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String creatorClassName = entitiyClassName + "Creator";

         String fileName = packageName + "." + creatorClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         creatorJavaFile = new File(fileName);

         // found old one?
         if (creatorJavaFile.exists())
         {
            creatorFileBody = CGUtil.readFile(creatorJavaFile);
         }
         else
         {
            creatorFileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import CreatorCreatorClass;\n" + 
                     "import org.sdmlib.serialization.interfaces.EntityFactory;\n" +
                     "import org.sdmlib.serialization.json.JsonIdMap;\n" +
                     "import fullEntityClassName;\n" +
                     "\n" +
                     "public class creatorClassName extends EntityFactory\n" +
                     "{\n" +
                     "   private final String[] properties = new String[]\n" +
                     "   {\n" +
                     "   };\n" +
                     "   \n" +
                     "   public String[] getProperties()\n" +
                     "   {\n" +
                     "      return properties;\n" +
                     "   }\n" +
                     "   \n" +
                     "   public Object getSendableInstance(boolean reference)\n" +
                     "   {\n" +
                     "      return new entitiyClassName();\n" +
                     "   }\n" +
                     "   \n" +
                     "   public Object getValue(Object target, String attrName)\n" +
                     "   {\n" +
                     "      return ((entitiyClassName) target).get(attrName);\n" +
                     "   }\n" +
                     "   \n" +
                     "   public boolean setValue(Object target, String attrName, Object value, String type)\n" +
                     "   {\n" +
                     "      return ((entitiyClassName) target).set(attrName, value);\n" +
                     "   }\n" +
                     "   \n" +
                     "   public static JsonIdMap createIdMap(String sessionID)\n" +
                     "   {\n" +
                     "      return CreatorCreator.createIdMap(sessionID);\n" +
                     "   }\n" +
                  "}\n");

            CGUtil.replaceAll(text, "CreatorCreatorClass", classModel.getCreatorCreatorClassName());
            
            if (getWrapped())
            {
               // wrapped class does not provide generic get / set
               CGUtil.replaceAll(text, 
                  "((entitiyClassName) target).get(attrName)", "null", 
                  "((entitiyClassName) target).set(attrName, value)", "false");
            }

            CGUtil.replaceAll(text, 
               "creatorClassName", creatorClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName);

            creatorFileBody.append(text.toString());

            creatorFileHasChanged = true;
         }

         creatorParser = new Parser()
         .withFileName(fileName)
         .withFileBody(creatorFileBody);

      }

      return creatorParser;
   }


   public String getModelSetClassName()
   {
      int pos = name.lastIndexOf('.');

      String packageName = name.substring(0, pos) + ".creators";

      if (getWrapped())
      {
         packageName = getClassModel().getPackageName() + ".creators";
      }

      String entitiyClassName = name.substring(pos + 1);

      String modelSetClassName = entitiyClassName + "Set";

      String fullModelSetClassName = packageName + "." + modelSetClassName;

      return fullModelSetClassName;
   }

   public Parser getOrCreateParserForModelSetFile(String rootDir)
   {
      if (modelSetParser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + ".creators";

         if (getWrapped())
         {
            packageName = getClassModel().getPackageName() + ".creators";
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String modelSetClassName = entitiyClassName + "Set";

         String fileName = packageName + "." + modelSetClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         modelSetJavaFile = new File(fileName);

         // found old one?
         if (modelSetJavaFile.exists())
         {
            modelSetFileBody = CGUtil.readFile(modelSetJavaFile);
         }
         else
         {
            modelSetFileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import java.util.LinkedHashSet;\n" +
                     "import org.sdmlib.models.modelsets.ModelSet;\n" +
                     "import fullEntityClassName;\n" +
                     "\n" +
                     "public class modelSetClassName extends LinkedHashSet<entitiyClassName> implements ModelSet\n" +
                     "{\n" +
                  "}\n");

            CGUtil.replaceAll(text, 
               "modelSetClassName", modelSetClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName);

            modelSetFileBody.append(text.toString());

            modelSetFileHasChanged = true;
         }

         modelSetParser = new Parser()
         .withFileName(fileName)
         .withFileBody(modelSetFileBody);

         insertLicense(modelSetParser);

         insertSetToString(modelSetParser);

         insertSetEntryType(modelSetParser);

         insertSetWithWithout(modelSetParser);
      }

      return modelSetParser;
   }


   private void insertSetWithWithout(Parser parser)
   {
      String searchString = Parser.METHOD + ":with(" + CGUtil.shortClassName(this.getName()) + ")";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "\n\n" + 
                  "   public ModelTypeSet with(ModelType value)\n" + 
                  "   {\n" + 
                  "      this.add(value);\n" + 
                  "      return this;\n" + 
                  "   }\n" + 
                  "   \n" + 
                  "   public ModelTypeSet without(ModelType value)\n" + 
                  "   {\n" + 
                  "      this.remove(value);\n" + 
                  "      return this;\n" + 
                  "   }\n" 
               );

         CGUtil.replaceAll(text, 
            "ModelType", CGUtil.shortClassName(this.getName()));

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         setModelSetFileHasChanged(true);
      }
   }

   private void insertSetToString(Parser parser)
   {
      String searchString = Parser.METHOD + ":toString()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
            "\n\n" + 
                  "   public String toString()\n" + 
                  "   {\n" + 
                  "      StringList stringList = new StringList();\n" + 
                  "      \n" + 
                  "      for (ModelType elem : this)\n" + 
                  "      {\n" + 
                  "         stringList.add(elem.toString());\n" + 
                  "      }\n" + 
                  "      \n" + 
                  "      return \"(\" + stringList.concat(\", \") + \")\";\n" + 
                  "   }\n"
               );

         CGUtil.replaceAll(text, 
            "ModelType", CGUtil.shortClassName(this.getName()));

         insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         setModelSetFileHasChanged(true);
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

         CGUtil.replaceAll(text,"ModelType", this.getName());

         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         setModelSetFileHasChanged(true);
      }
   }

   public Parser getOrCreateParserForPatternObjectFile(String rootDir)
   {
      if (patternObjectParser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + ".creators";

         if (getWrapped())
         {
            packageName = getClassModel().getPackageName() + ".creators";
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String patternObjectClassName = entitiyClassName + "PO";

         String fileName = packageName + "." + patternObjectClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         patternObjectJavaFile = new File(fileName);

         // found old one?
         if (patternObjectJavaFile.exists())
         {
            patternObjectFileBody = CGUtil.readFile(patternObjectJavaFile);
         }
         else
         {
            patternObjectFileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import org.sdmlib.models.pattern.PatternObject;\n" +
                     "import fullEntityClassName;\n" +
                     "\n" +
                     "public class patternObjectClassName extends PatternObject<patternObjectClassName, entitiyClassName>\n" +
                     "{\n" +
                     "   public entitiyClassNameSet allMatches()\n" + 
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
                     "   }\n" + 
                     "   \n" + 
                     "" + 
                  "}\n");

            CGUtil.replaceAll(text, 
               "patternObjectClassName", patternObjectClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName);

            patternObjectFileBody.append(text.toString());

            patternObjectFileHasChanged = true;
         }

         patternObjectParser = new Parser()
         .withFileName(fileName)
         .withFileBody(patternObjectFileBody);

         this.insertImport(patternObjectParser, packageName + "." + entitiyClassName + "Set");
      }

      return patternObjectParser;
   }




   public Parser getOrCreateParserForPatternObjectCreatorFile(String rootDir)
   {
      if (patternObjectCreatorParser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + ".creators";

         if (getWrapped())
         {
            packageName = getClassModel().getPackageName() + ".creators";
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String patternObjectCreatorClassName = entitiyClassName + "POCreator";

         String fileName = packageName + "." + patternObjectCreatorClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         patternObjectCreatorJavaFile = new File(fileName);

         // found old one?
         if (patternObjectCreatorJavaFile.exists())
         {
            patternObjectCreatorFileBody = CGUtil.readFile(patternObjectCreatorJavaFile);
         }
         else
         {
            patternObjectCreatorFileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import org.sdmlib.models.pattern.creators.PatternObjectCreator;\n" +
                     "\n" +
                     "public class patternObjectCreatorClassName extends PatternObjectCreator\n" +
                     "{\n" +
                     "   public Object getSendableInstance(boolean reference)\n" + 
                     "   {\n" + 
                     "      return new entitiyPOClassName();\n" + 
                     "   }\n" + 
                     "   \n" + 
                     "   public Object getValue(Object target, String attrName)\n" + 
                     "   {\n" + 
                     "      return ((entitiyPOClassName) target).get(attrName);\n" + 
                     "   }\n" + 
                     "   \n" + 
                     "   public boolean setValue(Object target, String attrName, Object value, String type)\n" + 
                     "   {\n" + 
                     "      return ((entitiyPOClassName) target).set(attrName, value);\n" + 
                     "   }\n" +
                  "}\n");

            CGUtil.replaceAll(text, 
               "patternObjectCreatorClassName", patternObjectCreatorClassName, 
               "entitiyPOClassName", entitiyClassName + "PO", 
               "packageName", packageName);

            patternObjectCreatorFileBody.append(text.toString());

            patternObjectCreatorFileHasChanged = true;
         }

         patternObjectCreatorParser = new Parser()
         .withFileName(fileName)
         .withFileBody(patternObjectCreatorFileBody);

      }

      return modelSetParser;
   }


   public boolean isFileHasChanged()
   {
      return fileHasChanged;
   }

   public void setFileHasChanged(boolean fileHasChanged)
   {
      this.fileHasChanged = fileHasChanged;
   }



   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Role
    *              clazz                   sourceRoles
    * </pre>
    */

   public static final String PROPERTY_SOURCEROLES = "sourceRoles";

   private RoleSet sourceRoles = null;

   public RoleSet getSourceRoles()
   {
      if (this.sourceRoles == null)
      {
         return Role.EMPTY_SET;
      }

      return this.sourceRoles;
   }

   public boolean addToSourceRoles(Role value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.sourceRoles == null)
         {
            this.sourceRoles = new RoleSet();
         }

         changed = this.sourceRoles.add (value);

         if (changed)
         {
            value.withClazz(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCEROLES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromSourceRoles(Role value)
   {
      boolean changed = false;

      if ((this.sourceRoles != null) && (value != null))
      {
         changed = this.sourceRoles.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCEROLES, null, value);
         }
      }

      return changed;   
   }

   public Clazz withSourceRoles(Role value)
   {
      addToSourceRoles(value);
      return this;
   } 

   public Clazz withoutSourceRoles(Role value)
   {
      removeFromSourceRoles(value);
      return this;
   } 

   public void removeAllFromSourceRoles()
   {
      LinkedHashSet<Role> tmpSet = new LinkedHashSet<Role>(this.getSourceRoles());

      for (Role value : tmpSet)
      {
         this.removeFromSourceRoles(value);
      }
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Role
    *              clazz                   targetRoles
    * </pre>
    */

   public static final String PROPERTY_TARGETROLES = "targetRoles";

   private RoleSet targetRoles = null;

   public RoleSet getTargetRoles()
   {
      if (this.targetRoles == null)
      {
         return Role.EMPTY_SET;
      }

      return this.targetRoles;
   }

   public boolean addToTargetRoles(Role value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.targetRoles == null)
         {
            this.targetRoles = new RoleSet();
         }

         changed = this.targetRoles.add (value);

         if (changed)
         {
            value.withClazz(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETROLES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromTargetRoles(Role value)
   {
      boolean changed = false;

      if ((this.targetRoles != null) && (value != null))
      {
         changed = this.targetRoles.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETROLES, null, value);
         }
      }

      return changed;   
   }

   public Clazz withTargetRoles(Role value)
   {
      addToTargetRoles(value);
      return this;
   } 

   public Clazz withoutTargetRoles(Role value)
   {
      removeFromTargetRoles(value);
      return this;
   } 

   public void removeAllFromTargetRoles()
   {
      LinkedHashSet<Role> tmpSet = new LinkedHashSet<Role>(this.getTargetRoles());

      for (Role value : tmpSet)
      {
         this.removeFromTargetRoles(value);
      }
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Method
    *              clazz                   methods
    * </pre>
    */

   public static final String PROPERTY_METHODS = "methods";

   private MethodSet methods = null;

   public MethodSet getMethods()
   {
      if (this.methods == null)
      {
         return Method.EMPTY_SET;
      }

      return this.methods;
   }

   public boolean addToMethods(Method value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.methods == null)
         {
            this.methods = new MethodSet();
         }

         changed = this.methods.add (value);

         if (changed)
         {
            value.withClazz(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromMethods(Method value)
   {
      boolean changed = false;

      if ((this.methods != null) && (value != null))
      {
         changed = this.methods.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, value);
         }
      }

      return changed;   
   }

   public Clazz withMethods(String signature, String returnType)
   {
      return withMethods(new Method(signature, returnType));
   } 

   public Clazz withMethods(Method value)
   {
      addToMethods(value);
      return this;
   } 

   public Clazz withoutMethods(Method value)
   {
      removeFromMethods(value);
      return this;
   } 

   public void removeAllFromMethods()
   {
      LinkedHashSet<Method> tmpSet = new LinkedHashSet<Method>(this.getMethods());

      for (Method value : tmpSet)
      {
         this.removeFromMethods(value);
      }
   }


   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         return getClassModel();
      }

      if (PROPERTY_ATTRIBUTES.equalsIgnoreCase(attrName))
      {
         return getAttributes();
      }

      if (PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         return getMethods();
      }

      if (PROPERTY_SOURCEROLES.equalsIgnoreCase(attrName))
      {
         return getSourceRoles();
      }

      if (PROPERTY_TARGETROLES.equalsIgnoreCase(attrName))
      {
         return getTargetRoles();
      }

      if (PROPERTY_KINDCLASSES.equalsIgnoreCase(attrName))
      {
         return getKindClasses();
      }

      if (PROPERTY_SUPERCLASS.equalsIgnoreCase(attrName))
      {
         return getSuperClass();
      }

      if (PROPERTY_INTERFAZE.equalsIgnoreCase(attrName))
      {
         return isInterfaze();
      }

      if (PROPERTY_KINDCLASSESASINTERFACE.equalsIgnoreCase(attrName))
      {
         return getKindClassesAsInterface();
      }

      if (PROPERTY_INTERFACES.equalsIgnoreCase(attrName))
      {
         return getInterfaces();
      }

      if (PROPERTY_EXTERNAL.equalsIgnoreCase(attribute))
      {
         return isExternal();
      }

      if (PROPERTY_WRAPPED.equalsIgnoreCase(attribute))
      {
         return getWrapped();
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         setClassModel((ClassModel) value);
         return true;
      }

      if (PROPERTY_ATTRIBUTES.equalsIgnoreCase(attrName))
      {
         addToAttributes((Attribute) value);
         return true;
      }

      if ((PROPERTY_ATTRIBUTES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromAttributes((Attribute) value);
         return true;
      }

      if (PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         addToMethods((Method) value);
         return true;
      }

      if ((PROPERTY_METHODS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromMethods((Method) value);
         return true;
      }

      if (PROPERTY_SOURCEROLES.equalsIgnoreCase(attrName))
      {
         addToSourceRoles((Role) value);
         return true;
      }

      if ((PROPERTY_SOURCEROLES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromSourceRoles((Role) value);
         return true;
      }

      if (PROPERTY_TARGETROLES.equalsIgnoreCase(attrName))
      {
         addToTargetRoles((Role) value);
         return true;
      }

      if ((PROPERTY_TARGETROLES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromTargetRoles((Role) value);
         return true;
      }

      if (PROPERTY_KINDCLASSES.equalsIgnoreCase(attrName))
      {
         addToKindClasses((Clazz) value);
         return true;
      }

      if ((PROPERTY_KINDCLASSES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromKindClasses((Clazz) value);
         return true;
      }

      if (PROPERTY_SUPERCLASS.equalsIgnoreCase(attrName))
      {
         setSuperClass((Clazz) value);
         return true;
      }

      if (PROPERTY_INTERFAZE.equalsIgnoreCase(attrName))
      {
         setInterfaze((Boolean) value);
         return true;
      }

      if (PROPERTY_KINDCLASSESASINTERFACE.equalsIgnoreCase(attrName))
      {
         addToKindClassesAsInterface((Clazz) value);
         return true;
      }

      if ((PROPERTY_KINDCLASSESASINTERFACE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromKindClassesAsInterface((Clazz) value);
         return true;
      }

      if (PROPERTY_INTERFACES.equalsIgnoreCase(attrName))
      {
         addToInterfaces((Clazz) value);
         return true;
      }

      if ((PROPERTY_INTERFACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromInterfaces((Clazz) value);
         return true;
      }

      if (PROPERTY_EXTERNAL.equalsIgnoreCase(attrName))
      {
         setExternal((Boolean) value);
         return true;
      }

      if (PROPERTY_WRAPPED.equalsIgnoreCase(attrName))
      {
         setWrapped((Boolean) value);
         return true;
      }

      return false;
   }

   public Clazz withAttribute(String name, String type)
   {      
      this.withAttributes(new Attribute().withName(name).withType(type));
      return this;
   }

   public Clazz withAttribute(String name, String type, String initialization)
   {      
      this.withAttributes(new Attribute().withName(name).withType(type).withInitialization(initialization));
      return this;
   }

   public Clazz withAttributes(String... attrNameTypePairs)
   {  
      if (attrNameTypePairs != null)
      {
         for (int i = 0; i+2 <= attrNameTypePairs.length; i += 2)
         {
            this.withAttribute(attrNameTypePairs[i], attrNameTypePairs[i+1]);
         }
      }

      return this;
   }

   public void insertCreatorClassInCreatorCreator(Parser ccParser)
   {
      int pos = ccParser.indexOf(Parser.METHOD + ":getCreatorSet()");

      if (pos < 0)
      {
         // ups, did not find createIdMap method. 
         System.err.println("Warning: SDMLib codgen for creatorSet initialisation invocation " + getName() + "Creator for class " + ccParser.getFileName() 
            + ": \nDid not find method getCreatorSet(). Should have been generated by my model. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      //      SymTabEntry symTabEntry = ccParser.getSymTab().get(Parser.METHOD + ":createIdMap(String)");
      //      
      //      ccParser.parseMethodBody(symTabEntry);

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = ccParser.getMethodBodyStartPos();

      String shortCreatorClassName = CGUtil.shortClassName(getName()) + "Creator";
      String shortCreatorPOClassName = CGUtil.shortClassName(getName()) + "POCreator";
      String creatorClassName = CGUtil.packageName(getName()) + ".creators." + shortCreatorClassName;
      String creatorPOClassName = CGUtil.packageName(getName()) + ".creators." + shortCreatorPOClassName;

      if (getWrapped())
      {
         // generate creator for external class. Put it in the model package
         creatorClassName = getClassModel().getPackageName() + ".creators." + shortCreatorClassName;
         creatorPOClassName = getClassModel().getPackageName() + ".creators." + shortCreatorPOClassName;
      }

      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);

      if (pos < 0)
      {         
         int medthodEndPos = ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int addCreatorPos = ccParser.getFileBody().indexOf("creatorSet.addAll", methodBodyStartPos); 

         StringBuilder text = new StringBuilder
               (  "creatorSet.add(new ClassCreator());\n" +
                     "         creatorSet.add(new ClassPOCreator());\n         " 
                     );

         CGUtil.replaceAll(text, 
            "ClassCreator", creatorClassName,
            "ClassPOCreator", creatorPOClassName
               );

         ccParser.getFileBody().insert(addCreatorPos, text.toString());
         getClassModel().setFileHasChanged(true);
      }

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
      if (pos >=0)
      {
         typeName = typeName.substring(0,  pos);
      }

      insertImport(parser, typeName);

      return baseName;
   }



   //==========================================================================

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }


   //==========================================================================

   public void removeYou()
   {
      setClassModel(null);
      removeAllFromAttributes();
      removeAllFromMethods();
      removeAllFromSourceRoles();
      removeAllFromTargetRoles();
      removeAllFromKindClasses();
      setSuperClass(null);
      removeAllFromKindClassesAsInterface();
      removeAllFromInterfaces();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public String toString()
   {
      return "["+getName()+"]";
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Clazz
    *              superClass                   kindClasses
    * </pre>
    */

   public static final String PROPERTY_KINDCLASSES = "kindClasses";

   private ClazzSet kindClasses = null;

   public ClazzSet getKindClasses()
   {
      if (this.kindClasses == null)
      {
         return Clazz.EMPTY_SET;
      }

      return this.kindClasses;
   }

   public boolean addToKindClasses(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.kindClasses == null)
         {
            this.kindClasses = new ClazzSet();
         }

         changed = this.kindClasses.add (value);

         if (changed)
         {
            value.withSuperClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KINDCLASSES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromKindClasses(Clazz value)
   {
      boolean changed = false;

      if ((this.kindClasses != null) && (value != null))
      {
         changed = this.kindClasses.remove (value);

         if (changed)
         {
            value.setSuperClass(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KINDCLASSES, value, null);
         }
      }

      return changed;   
   }

   public Clazz withKindClasses(Clazz value)
   {
      addToKindClasses(value);
      return this;
   } 

   public Clazz withoutKindClasses(Clazz value)
   {
      removeFromKindClasses(value);
      return this;
   } 

   public void removeAllFromKindClasses()
   {
      LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getKindClasses());

      for (Clazz value : tmpSet)
      {
         this.removeFromKindClasses(value);
      }
   }


   //==========================================================================

   public static final String PROPERTY_INTERFAZE = "interfaze";

   private Boolean interfaze = false;

   public Boolean isInterfaze()
   {
      return this.interfaze;
   }

   public void setInterfaze(Boolean value)
   {
      if (this.interfaze != value)
      {
         Boolean oldValue = this.interfaze;
         this.interfaze = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFAZE, oldValue, value);
      }
   }

   public Clazz withInterfaze(Boolean value)
   {
      setInterfaze(value);
      return this;
   } 


   /********************************************************************
    * <pre>
    *              many                       many
    * Clazz ----------------------------------- Clazz
    *              interfaces                   kindClassesAsInterface
    * </pre>
    */

   public static final String PROPERTY_KINDCLASSESASINTERFACE = "kindClassesAsInterface";

   private LinkedHashSet<Clazz> kindClassesAsInterface = null;

   public LinkedHashSet<Clazz> getKindClassesAsInterface()
   {
      if (this.kindClassesAsInterface == null)
      {
         return Clazz.EMPTY_SET;
      }

      return this.kindClassesAsInterface;
   }

   public boolean addToKindClassesAsInterface(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.kindClassesAsInterface == null)
         {
            this.kindClassesAsInterface = new LinkedHashSet<Clazz>();
         }

         changed = this.kindClassesAsInterface.add (value);

         if (changed)
         {
            value.withInterfaces(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KINDCLASSESASINTERFACE, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromKindClassesAsInterface(Clazz value)
   {
      boolean changed = false;

      if ((this.kindClassesAsInterface != null) && (value != null))
      {
         changed = this.kindClassesAsInterface.remove (value);

         if (changed)
         {
            value.withoutInterfaces(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KINDCLASSESASINTERFACE, value, null);
         }
      }

      return changed;   
   }

   public Clazz withKindClassesAsInterface(Clazz value)
   {
      addToKindClassesAsInterface(value);
      return this;
   } 

   public Clazz withoutKindClassesAsInterface(Clazz value)
   {
      removeFromKindClassesAsInterface(value);
      return this;
   } 

   public void removeAllFromKindClassesAsInterface()
   {
      LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getInterfaces());

      for (Clazz value : tmpSet)
      {
         this.removeFromKindClassesAsInterface(value);
      }
   }

   /********************************************************************
    * <pre>
    *              many                       many
    * Clazz ----------------------------------- Clazz
    *              kindClassesAsInterface                   interfaces
    * </pre>
    */

   public static final String PROPERTY_INTERFACES = "interfaces";

   private LinkedHashSet<Clazz> interfaces = null;

   public LinkedHashSet<Clazz> getInterfaces()
   {
      if (this.interfaces == null)
      {
         return Clazz.EMPTY_SET;
      }

      return this.interfaces;
   }

   public boolean addToInterfaces(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.interfaces == null)
         {
            this.interfaces = new LinkedHashSet<Clazz>();
         }

         changed = this.interfaces.add (value);

         if (changed)
         {
            value.withKindClassesAsInterface(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromInterfaces(Clazz value)
   {
      boolean changed = false;

      if ((this.interfaces != null) && (value != null))
      {
         changed = this.interfaces.remove (value);

         if (changed)
         {
            value.withoutKindClassesAsInterface(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACES, value, null);
         }
      }

      return changed;   
   }

   public Clazz withInterfaces(Clazz value)
   {
      addToInterfaces(value);
      value.addToKindClassesAsInterface(this);
      return this;
   } 

   public Clazz withoutInterfaces(Clazz value)
   {
      removeFromInterfaces(value);
      return this;
   } 

   public void removeAllFromInterfaces()
   {
      LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getInterfaces());

      for (Clazz value : tmpSet)
      {
         this.removeFromInterfaces(value);
      }
   }

   public Attribute getOrCreateAttribute(String attrName, String attrType)
   {
      for (Attribute attrDecl : getAttributes())
      {
         if (attrDecl.getName().equals(attrName))
         {
            return attrDecl;
         }
      }

      return new Attribute().withName(attrName).withType(attrType).withClazz(this);

   }

   public void removeAllGeneratedCode(String testDir, String srcDir,
         String helpersDir)
   {
      getClassModel().turnRemoveCallToComment(testDir);
      getClassModel().removeAllCodeForClass(srcDir, helpersDir, this);
   }

   public void insertHasMethodsInModelPattern(Parser modelPatternParser)
   {
      String fullPOClassName = CGUtil.helperClassName(this.getName(), "PO");

      if (getWrapped())
      {
         fullPOClassName = CGUtil.helperClassName(getClassModel().getPackageName() + "." + CGUtil.shortClassName(this.getName()), "PO");
      }

      String poClassName = this.shortNameAndImport(fullPOClassName, modelPatternParser);

      int pos = modelPatternParser.indexOf(Parser.METHOD + ":hasElement" + poClassName + "()");

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder (
            "   public PatternObjectType hasElementPatternObjectType()\n" + 
                  "   {\n" + 
                  "      PatternObjectType value = new PatternObjectType();\n" + 
                  "      this.addToElements(value);\n" + 
                  "      value.setModifier(this.getModifier());\n" + 
                  "      \n" + 
                  "      this.findMatch();\n" + 
                  "      \n" + 
                  "      return value;\n" + 
                  "   }\n" + 
                  "   \n" +
                  "   public PatternObjectType hasElementPatternObjectType(ModelClass hostGraphObject)\n" + 
                  "   {\n" + 
                  "      PatternObjectType value = new PatternObjectType();\n" + 
                  "      this.addToElements(value);\n" + 
                  "      value.setModifier(Pattern.BOUND);\n" + 
                  "      \n" + 
                  "      value.setCurrentMatch(hostGraphObject);\n" + 
                  "      \n" + 
                  "      this.findMatch();\n" + 
                  "      \n" + 
                  "      return value;\n" + 
                  "   } \n" + 
                  "\n" 
               );

         String modelClass = this.shortNameAndImport(this.getName(), modelPatternParser);

         CGUtil.replaceAll(text, 
            "PatternObjectType", poClassName, 
            "ModelClass", modelClass
               );

         pos = modelPatternParser.indexOf(Parser.CLASS_END);

         modelPatternParser.getFileBody().insert(pos, text.toString());

         getClassModel().setModelPatternFileHasChanged(true);
      }
   }


   //==========================================================================

   public static final String PROPERTY_EXTERNAL = "external";

   private boolean external;

   public boolean isExternal()
   {
      return this.external;
   }

   public void setExternal(boolean value)
   {
      if (this.external != value)
      {
         boolean oldValue = this.external;
         this.external = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EXTERNAL, oldValue, value);
      }
   }

   public Clazz withExternal(boolean value)
   {
      setExternal(value);
      return this;
   }

   public Method createMethods(String signature, String returnType)
   {
      return new Method(signature, returnType).withClazz(this);     
   } 


   //==========================================================================

   public static final String PROPERTY_WRAPPED = "wrapped";

   private boolean wrapped;

   public boolean getWrapped()
   {
      return this.wrapped;
   }

   public void setWrapped(boolean value)
   {
      if (this.wrapped != value)
      {
         boolean oldValue = this.wrapped;
         this.wrapped = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_WRAPPED, oldValue, value);
      }
   }

   public Clazz withWrapped(boolean value)
   {
      setWrapped(value);
      return this;
   } 
}

