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
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class Clazz implements PropertyChangeInterface
{
   public static final LinkedHashSet<Clazz> EMPTY_SET = new LinkedHashSet<Clazz>();

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
   
   public Clazz(String name)
   {
      this();
      setName(name);
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
   
   public static final String PROPERTY_CLASSMODEL = "classModel";
   private ClassModel classModel = null;
   
   private StringBuilder fileBody;
   private StringBuilder creatorFileBody;

   private boolean fileHasChanged;
   private boolean creatorFileHasChanged;
   
   public void setCreatorFileHasChanged(boolean creatorFileHasChanged)
   {
      this.creatorFileHasChanged = creatorFileHasChanged;
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
      // first generate the class itself
      getOrCreateParser(rootDir);
                 
      insertLicense(parser);
      
      insertGenericGetSet();
      
      insertPropertyChangeSupport();
      
      insertRemoveYourMethod();
      
      for (Attribute attr : this.getAttributes())
      {
         attr.generate(rootDir, helpersDir, false);
      }
      
      printFile(isFileHasChanged());
      
      // now generate the corresponding creator class
      getOrCreateParserForCreatorClass(helpersDir);
      printCreatorFile(creatorFileHasChanged);
      
      return this;
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
            (  "\n   " +
               "\n   //==========================================================================" +
               "\n   " +
               "\n   public void removeYou()" +
               "\n   {" +
               "\n      getPropertyChangeSupport().firePropertyChange(\"REMOVE_YOU\", this, null);" +
               "\n   }" +
               "\n"
               );
         
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
         
         StringBuilder text = new StringBuilder
            (  "\n   " +
               "\n   //==========================================================================" +
               "\n   " +
               "\n   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);" +
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
         
         parser.getFileBody().insert(implementsPos + 1, 
               " implements " + PropertyChangeInterface.class.getSimpleName());
         
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
      // TODO Auto-generated method stub
      int pos = parser.indexOf(Parser.IMPORT);

      String prefix = "";
      if (fileBody.indexOf(Parser.IMPORT, pos) < 0)
      {
         prefix = "\n";
      }
      
      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.IMPORT + ":" + className);
      if (symTabEntry == null)
      {
         parser.getFileBody().insert(parser.getEndOfImports() + 1, 
               prefix + "\nimport " + className + ";");
         
         setFileHasChanged(true);
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
      if (really)
      {
         CGUtil.printFile(creatorJavaFile, creatorFileBody.toString());
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
      int pos = fileBody.indexOf("/*");
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
         
         fileBody.replace(0, 0, text.toString());
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
   
   private LinkedHashSet<Attribute> attributes = null;
   
   public LinkedHashSet<Attribute> getAttributes()
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
            this.attributes = new LinkedHashSet<Attribute>();
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
            fileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
                  "package packageName;\n" +
                  "\n" +
                  "public class className\n" +
                  "{\n" +
                  "}\n");
            
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
                  "import org.sdmlib.serialization.interfaces.SendableEntityCreator;\n" +
                  "import org.sdmlib.serialization.json.JsonIdMap;\n" +
                  "import fullEntityClassName;\n" +
                  "\n" +
                  "public class creatorClassName implements SendableEntityCreator\n" +
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
                  "   public boolean setValue(Object target, String attrName, Object value)\n" +
                  "   {\n" +
                  "      return ((entitiyClassName) target).set(attrName, value);\n" +
                  "   }\n" +
                  "   \n" +
                  "   public static JsonIdMap createIdMap(String sessionID)\n" +
                  "   {\n" +
                  "      return CreatorCreator.createIdMap(sessionID);\n" +
                  "   }\n" +
                  "}\n");
            
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
   
   private LinkedHashSet<Role> sourceRoles = null;
   
   public LinkedHashSet<Role> getSourceRoles()
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
            this.sourceRoles = new LinkedHashSet<Role>();
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
   
   private LinkedHashSet<Role> targetRoles = null;
   
   public LinkedHashSet<Role> getTargetRoles()
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
            this.targetRoles = new LinkedHashSet<Role>();
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
   
   private LinkedHashSet<Method> methods = null;
   
   public LinkedHashSet<Method> getMethods()
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
            this.methods = new LinkedHashSet<Method>();
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

   public void insertCreatorClassInCreatorCreator(Parser ccParser)
   {
      int pos = ccParser.indexOf(Parser.METHOD + ":createIdMap(String)");

      if (pos < 0)
      {
         // ups, did not find createIdMap method. 
         System.err.println("Warning: SDMLib codgen for creator construction invocation " + getName() + "Creator for class " + ccParser.getFileName() 
            + ": \nDid not find method createIdMap(String). Should have been generated by my model. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = ccParser.getMethodBodyStartPos();
      
      String shortCreatorClassName = CGUtil.shortClassName(getName()) + "Creator";
      String creatorClassName = CGUtil.packageName(getName()) + ".creators." + shortCreatorClassName;
      
      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic get method
         ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
         
         int lastReturnPos = ccParser.getLastReturnStart(); 
         
         StringBuilder text = new StringBuilder
            (  "jsonIdMap.addCreator(new ClassCreator());\n      " 
               );

         CGUtil.replaceAll(text, 
            "ClassCreator", creatorClassName
            );

         ccParser.getFileBody().insert(lastReturnPos, text.toString());
         getClassModel().setFileHasChanged(true);
      }
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   public String toString()
   {
      return "["+getName()+"]";
   }
}

