package org.sdmlib.models.classes.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonIdMap;

/**
 * @author Stefan
 *
 */
public class GenClass extends Generator<Clazz>
{
   public static final String PROPERTY_FILEPATH = "filePath";

   private String filePath;

   private LinkedHashMap<String, String> constantDecls = new LinkedHashMap<String, String>();
   private Parser creatorParser = null;
   private Parser modelSetParser = null;
   private Parser patternObjectParser = null;
   private Parser patternObjectCreatorParser = null;
   private Parser parser = null;
   
   public GenClass generate(String rootDir, String helpersDir)
   {
       // first generate the class itself
      if ( ! model.isExternal())
      {
         getOrCreateParser(rootDir);

         insertLicense(parser);

         insertInterfaces();

         insertConstants();
         
         for (Method method : model.getMethods()) {
            getGenerator(method).generate(rootDir, helpersDir, false);
         }
         
         if ( !model.isInterface())
         {
            insertSuperClass();
            insertPropertyChangeSupport(rootDir);
            insertInterfaceMethods(model, rootDir, helpersDir);
            insertRemoveYouMethod(rootDir);
            insertInterfaceAttributesInCreatorClass(model, rootDir, helpersDir);
         }

         generateAttributes(rootDir, helpersDir);
         printFile();
      }else{
         generateAttributes(rootDir, helpersDir);
      }


      if ( !model.isInterface() )
      {
         // now generate the corresponding creator class
         if(getRepairClassModel().hasFeature(Feature.Serialization)){
            getOrCreateParserForCreatorClass(helpersDir);
            insertRemoveObjectInCreatorClass();
            printFile(creatorParser);
         }
      }

      // now generate the corresponding ModelSet class
      getOrCreateParserForModelSetFile(helpersDir);
      printFile(modelSetParser);
      if(getRepairClassModel().hasFeature(Feature.PatternObject)){
   
         // now generate the corresponding PatterObject class
         getOrCreateParserForPatternObjectFile(helpersDir);
         printFile(patternObjectParser);
   
         // now generate the corresponding PatterObjectCreator class
         getOrCreateParserForPatternObjectCreatorFile(helpersDir);
         printFile(patternObjectCreatorParser);
      }

      return this;
   }
   
   
   private void insertConstants()
   {
      if (constantDecls.size() == 0)
      {
         return;
      }

      for (String constName : constantDecls.keySet())
      {
         int endOfClass = parser.indexOf(Parser.CLASS_END);
         String string = Parser.ATTRIBUTE+":" + constName;      
         SymTabEntry symTabEntry = parser.getSymTab().get(string);

         if (symTabEntry == null)
         {
            parser.insert(endOfClass, constantDecls.get(constName));
         }
      }
   }
   private boolean repairThis=false;
   public ClassModel getRepairClassModel(){
      if(model.getClassModel()!=null){
         return model.getClassModel();
      }
      if(repairThis){
         return null;
      }
      this.repairThis = true;
      for(Iterator<Clazz> i = model.getSuperClazzes().iterator(); i.hasNext(); ){
         Clazz item = i.next();
         
         if(item.getClassModel()!=null){
            model.with(item.getClassModel());
            System.err.println("Classmodel try to repair automaticly from Superclass ("+getRepairClassModel().getName()+"). Please add Classmodel to Clazz: "+model.getName());
            this.repairThis = false;
            return getRepairClassModel();
         }
      }
      
      for(Iterator<Clazz> i = model.getKidClazzes().iterator();i.hasNext();){
         Clazz item = i.next();
         
         if(item.getClassModel()!=null){
            model.with(item.getClassModel());
            System.err.println("Classmodel try to repair automaticly from Kindclass ("+getRepairClassModel()+"). Please add Classmodel to Clazz: "+model.getName());
            this.repairThis = false;
            return getRepairClassModel();
         }
      }
      for(Iterator<Role> i = model.getRoles().iterator();i.hasNext();){
         Role item = i.next();
         Clazz otherClazz=item.getPartnerRole().getClazz();
         if(otherClazz != model){
            if(otherClazz.getClassModel()!=null){
               model.with(otherClazz.getClassModel());
               System.err.println("Classmodel try to repair automaticly from Assoc ("+getRepairClassModel().getName()+"). Please add Classmodel to Clazz: "+model.getName());
               this.repairThis = false;
               return getRepairClassModel();
            }
         }
      }
      System.err.println("Classmodel try to repair automaticly. Please add Classmodel to Clazz: "+model.getName());
      this.repairThis = false;
      return getRepairClassModel();
   }

   private void generateAttributes(String rootDir, String helpersDir) 
   {
      for (Attribute attr : model.getAttributes()) 
      {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         getGenerator(attr).generate(rootDir, helpersDir, false);
      }

      if (model.getSuperClass() != null) 
      {
         gernerateSuperAttributes(model.getSuperClass(), rootDir, helpersDir);
      }
      
      for (Clazz interfaze : model.getInterfaces())
      {
         gernerateSuperAttributes(interfaze, rootDir, helpersDir);
      }
   }

   private void gernerateSuperAttributes(Clazz superClazz, String rootDir, String helpersDir) {

      for (Attribute attr : superClazz.getAttributes()) {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         GenAttribute generator = getGenerator(attr);
         if(generator!=null){
            generator.generate(model, rootDir, helpersDir, false);
         }
      }
      if (superClazz.getSuperClass() != null) {
         gernerateSuperAttributes(superClazz.getSuperClass(), rootDir, helpersDir);
      }

      for (Clazz interfaze : superClazz.getInterfaces())
      {
         gernerateSuperAttributes(interfaze, rootDir, helpersDir);
      }
}

   private void insertInterfaceAttributesInCreatorClass(Clazz clazz, String rootDir, String helpersDir)
   {
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (interfaze.isInterface())
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               Parser creatorParser = this.getOrCreateParserForCreatorClass(helpersDir);
               getGenerator(attr).insertPropertyInCreatorClass(interfaze.getFullName(), creatorParser, helpersDir, false);
            }

         }

         insertInterfaceAttributesInCreatorClass(interfaze, rootDir, helpersDir);

      } 
   }

   private void insertInterfaceMethods(Clazz clazz, String rootDir, String helpersDir)
   {
      
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (interfaze.isInterface())
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               getGenerator(attr).generate(clazz, rootDir, helpersDir);
            }

            for (Method method : interfaze.getMethods())
            {
               getGenerator(method).generate(clazz, rootDir, helpersDir, true);
            }

         }

         insertInterfaceMethods(interfaze, rootDir, helpersDir);

      } 
   }

   private void insertInterfaces()
   {

      String string = Parser.IMPLEMENTS;
      if (model.isInterface())
         string = Parser.EXTENDS;

      for ( Clazz interfaze : model.getInterfaces() )
      {
         int extendsPos = parser.indexOf(string);

         if (extendsPos < 0)
         {
            extendsPos = parser.getEndOfClassName();

            parser.insert(extendsPos + 1, 
               " " + string + " " + CGUtil.shortClassName(interfaze.getFullName()));

            insertImport(interfaze.getFullName());
         }
         else 
         {
            String shortClassName = CGUtil.shortClassName( interfaze.getFullName());

            String key = string + ":" + shortClassName;

            SymTabEntry symTabEntry = parser.getSymTab().get(key);

            if (symTabEntry == null)
            {
               parser.insert(parser.getEndOfImplementsClause() + 1, ", " + shortClassName);
               insertImport(interfaze.getFullName());
            }               
         }
      }    
   }

   private void insertSuperClass()
   {
      if (model.getSuperClass() == null) {
         return;
      }

      String searchString = Parser.EXTENDS;
      int extendsPos = parser.indexOf(searchString);

      if (extendsPos < 0)
      {
         extendsPos = parser.getEndOfClassName();

         parser.insert(extendsPos + 1, 
            " extends " + CGUtil.shortClassName(model.getSuperClass().getFullName()));

         insertImport(model.getSuperClass().getFullName());
      }
   }

   private void insertRemoveObjectInCreatorClass()
   {
      if(!getRepairClassModel().hasFeature(Feature.PropertyChangeSupport)){
         return;
      }
      if (creatorParser.indexOf(Parser.METHOD + ":removeObject(Object)") < 0)
      {
         // add removeObject method
         
         StringBuilder template = creatorParser.replace(
            "\n   " +
                  "\n   //==========================================================================" +
                  "\n   " +
                  "\n   @Override\n" + 
                  "   public void removeObject(Object entity)\n" + 
                  "   {\n" + 
                  "      BODY\n" + 
                  "   }" +
                  "\n"
                  ,
                  "BODY", (model.isExternal() ? "// wrapped object has no removeYou method" : "((ModelClass) entity).removeYou();")
               ); 

         creatorParser.replaceAll(template,    
            "ModelClass", CGUtil.shortClassName(model.getFullName()));
      }
   }
   

   private void insertRemoveYouMethod(String rootDir)
   {
      if(!getRepairClassModel().hasFeature(Feature.PropertyChangeSupport)){
         return;
      }
      String searchString = Parser.METHOD + ":removeYou()";
      if (parser.indexOf(searchString) < 0)
      {
         // add removeYou method
         String overrideText="";
         for (Clazz clazz : model.getSuperClazzesTransitive().minus(model)) 
         {
            if(clazz.isInterface()){
               continue;
            }
            if ( ! model.isExternal()){
               overrideText = "@Override";
            }
            if(getGenerator(clazz).getOrCreateParser(rootDir).indexOf(searchString)>=0){
               overrideText = "@Override";
               break;
            }
         }
         String superReplacement="";
         if (model.getSuperClass() != null && !model.getSuperClass().isExternal()) {
            superReplacement = "\n      super.removeYou();\n";
         }
         parser.replaceAll("\n   " +
               "\n   //==========================================================================" +
               "\n   " +
               "\n   OVERRIDE" +
               "\n   public void removeYou()" +
               "\n   {SUPER" +
               "\n      getPropertyChangeSupport().firePropertyChange(\"REMOVE_YOU\", this, null);" +              
               "\n   }" +
               "\n", 
               "OVERRIDE",  overrideText,
               "SUPER", superReplacement);
      }
   }

   private void insertPropertyChangeSupport(String rootDir)
   {
         if(!getRepairClassModel().hasFeature(Feature.PropertyChangeSupport)){
            return;
         }
         
         String searchString = Parser.METHOD + ":getPropertyChangeSupport()";
         // Check if no super has PropertyChange
         for (Clazz clazz : model.getSuperClazzesTransitive().minus(model)) 
         {
            if(clazz.isInterface()){
               continue;
            }
            if(!clazz.isExternal()){
               return;
            }
            if(getGenerator(clazz).getOrCreateParser(rootDir).indexOf(searchString)>=0){
               return;
            }
         }
      
      insertImplementsClauseForPropertyChangeInterface();

      // does it implement PropertyChangeSupportClient?
      
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         // add property change implementation
         parser.replaceAll(
               "\n   " +
                     "\n   //==========================================================================" +
                     "\n   " +
                     "\n   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);" +
                     "\n   " +
                     "\n   @Override" +
                     "\n   public PropertyChangeSupport getPropertyChangeSupport()" +
                     "\n   {" +
                     "\n      return listeners;" +
                     "\n   }" +
                     "\n   " +
                     "\n   public void addPropertyChangeListener(PropertyChangeListener listener) " + 
                     "\n   {" + 
                     "\n      getPropertyChangeSupport().addPropertyChangeListener(listener);" + 
                     "\n   }" +
                     "\n"  
               );
      }

      insertImport(PropertyChangeSupport.class.getName());
      insertImport(PropertyChangeListener.class.getName());
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
         if (model.isInterface())
            string = " extends ";
         parser.insert(implementsPos + 1, string + propertyChangeInterface);
         insertImport(PropertyChangeInterface.class.getName());
      }
      else
      {
         // there is already an implements clause, does it already implement PropertyChangeInterface? 
         SymTabEntry symTabEntry = parser.getSymTab().get(Parser.IMPLEMENTS + ":" + propertyChangeInterface);

         if (symTabEntry == null)
         {
            // propertyChangeClients is still missing.
            parser.insert(parser.getEndOfImplementsClause() + 1, 
               ", " + propertyChangeInterface);
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
      if (myParser.search(Parser.IMPORT, pos) < 0)
      {
         prefix = "\n";
      }

      SymTabEntry symTabEntry = myParser.getSymTab().get(Parser.IMPORT + ":" + className);
      if (symTabEntry == null)
      {
         myParser.insert(myParser.getEndOfImports() + 1, 
            prefix + "\nimport " + className + ";");
      }
   }

   public void printFile()
   {
      if(model.getClassModel() == null || model.getClassModel().getGenerator().getShowDiff()==DIFF.NONE)
      {
         CGUtil.printFile(parser);
      }
   }
//      if (really || parser.isFileBodyChanged())
//      {
//         StringBuilder fileBody = parser.getText();
//         while (fileBody.charAt(fileBody.length() - 1) == '\n')
//         {
//            fileBody.replace(fileBody.length() - 1 , fileBody.length(), "");
//         }
//         fileBody.append('\n');
//      }
//   }

   public void printFile(Parser parser)
   {
      if(!isShowDiff()){
         CGUtil.printFile(parser);
      }
   }

   private void insertLicense(Parser parser)
   {
      // file should start with head comment
      int pos = parser.search("/*");
      if (pos < 0 || pos > 20)
      {
         // insert MIT License otherwise.
         String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
         parser.replaceAll(0, 
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
               "   \n", "<year>", year,
                "<developer>", System.getProperty("user.name")
            );
      }

   }
   
   public void setParser(Parser parser)
   {
      this.parser = parser;
   }
   
   public Parser getParser()
   {
      return parser;
   }

   public Parser getOrCreateParser(String rootDir)
   {
      if (parser == null)
      {
         // try to find existing file
         String name = model.getFullName();
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos);
         String fileName = name;

         String className = name.substring(pos+1);

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File javaFile = new File(fileName);

         parser = new Parser().withFileName(fileName);
         // found old one?
         if (javaFile.exists() && !isShowDiff())
         {
            parser.withFileBody( CGUtil.readFile(javaFile) );
         }
         else
         {
            parser.replaceAll("" +
                  "package packageName;\n" +
                  "\n" +
                  "public clazz className\n" +
                  "{\n" +
                  "}\n",
                  "className", className, 
                  "packageName", packageName,
                  "clazz", (model.isInterface() ? "interface" : "class")
                  );
            parser.withFileChanged(true);
         }
      }

      return parser;
   }
   
   public boolean isShowDiff(){
      ClassModel model = getModel().getClassModel();
      if(model != null){
         return model.getGenerator().getShowDiff()!=DIFF.NONE;
      }
      return false;
   }

   public Parser getOrCreateParserForCreatorClass(String rootDir)
   {
      if (creatorParser == null)
      {
         // try to find existing file
         String name = model.getFullName();
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
         creatorParser = new Parser()
         .withFileName(fileName);

         // found old one?
         if (creatorJavaFile.exists() && !isShowDiff())
         {
            creatorParser.withFileBody( CGUtil.readFile(creatorJavaFile) );
         }
         else
         {
            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import org.sdmlib.serialization.EntityFactory;\n" +
                     "import "+JsonIdMap.class.getName()+";\n" +
                     "fullEntityClassName" +
                     "\n" +
                     "public class creatorClassName extends EntityFactory\n" +
                     "{\n" +
                     "   private final String[] properties = new String[]\n" +
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
                     "   public Object getSendableInstance(boolean reference)\n" +
                     "   {\n" +
                     "      return instanceCreationClause;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public Object getValue(Object target, String attrName)\n" +
                     "   {\n" +
                     "      int pos = attrName.indexOf('.');\n"+
                     "      String attribute = attrName;\n"+
                     "      \n" +
                     "      if (pos > 0)\n"+
                     "      {\n"+
                     "         attribute = attrName.substring(0, pos);\n"+
                     "      }\n"+
                     "      \n"+
                     "      return null;\n" +
                     "   }\n" +
                     "   \n" +
                     "   @Override\n" +
                     "   public boolean setValue(Object target, String attrName, Object value, String type)\n" +
                     "   {\n" +
                     "      if (JsonIdMap.REMOVE.equals(type) && value != null)\n" + 
                     "      {\n" + 
                     "         attrName = attrName + type;\n" + 
                     "      }\n" + 
                     "      \n"+
                     "      return false;\n" +
                     "   }\n" +
                     "   public static JsonIdMap createIdMap(String sessionID)\n" +
                     "   {\n" +
                     "      return CreatorCreator.createIdMap(sessionID);\n" +
                     "   }"+
                  "}\n");
            
            if (model.isExternal())
            {
               // check if it has a constructor
               ClassLoader classLoader = this.getClass().getClassLoader();
               boolean hasConstructor = false;
               try
               {
                  Class<?> loadClass = classLoader.loadClass(model.getFullName());

                  if (loadClass != null)
                  {
                     Constructor<?> constructor = loadClass.getConstructor(loadClass);
                     hasConstructor = constructor != null;
                  }
               }
               catch (Exception e)
               {
               }

               if (! hasConstructor)
               {
                  CGUtil.replaceAll(text, 
                     "instanceCreationClause", "null", "fullEntityClassName", ""); 
               }
            }
            
            String instanceCreationClause = "new " + entitiyClassName + "()";
            
            String modelPackage = CGUtil.packageName(model.getFullName());
            
            if (model.getFullName().endsWith("Impl") && modelPackage.endsWith(".impl"))
            {
               // emf style get package and name prefix
               modelPackage = CGUtil.packageName(modelPackage);
               String modelName = CGUtil.shortClassName(modelPackage);
               
               String basicClassName = entitiyClassName.substring(0, entitiyClassName.length() - 4);
               
               instanceCreationClause = modelPackage + "." + modelName + "Factory.eINSTANCE.create" + basicClassName+ "()";
            }
            
            CGUtil.replaceAll(text, 
               "creatorClassName", creatorClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", "import "+fullEntityClassName+";\n",
               "packageName", packageName,
               "instanceCreationClause", instanceCreationClause);
            
            creatorParser.withFileBody(text).withFileChanged(true);
            
            insertLicense(creatorParser);
         }
      }
      return creatorParser;
   }

   public String getModelSetClassName()
   {
      String name=model.getFullName();
      int pos = name.lastIndexOf('.');

      String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

      if (model.isExternal())
      {
         packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
      }

      String entitiyClassName = model.getFullName().substring(pos + 1);

      String modelSetClassName = entitiyClassName + "Set";

      String fullModelSetClassName = packageName + "." + modelSetClassName;

      return fullModelSetClassName;
   }

   public Parser getOrCreateParserForModelSetFile(String rootDir)
   {
      if(!getRepairClassModel().hasFeature(Feature.ALBERTsSets)){
         return null;
      }

      if (modelSetParser == null)
      {
         if (model.getFullName().equals("java.util.Date"))
         {
            System.out.println("ups");
         }
         // try to find existing file
         String name = model.getFullName();
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
         modelSetParser = new Parser()
         .withFileName(fileName);

         // found old one?
         if (modelSetJavaFile.exists() && !isShowDiff())
         {
            modelSetParser.withFileBody( CGUtil.readFile(modelSetJavaFile) );
         }
         else
         {
            StringBuilder text = new StringBuilder("" + 
                  "package packageName;\n" +
                  "\n" +
                  "import org.sdmlib.models.modelsets.SDMSet;\n" +
                  "import fullEntityClassName;\n" +
                  "\n" +
                  "public class modelSetClassName extends SDMSet<entitiyClassName>\n" +
                  "{\n" +
                  "}\n");

            CGUtil.replaceAll(text, 
               "modelSetClassName", modelSetClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", fullEntityClassName,
               "packageName", packageName,
               "Item", entitiyClassName
                  );
            modelSetParser.withFileBody( text ).withFileChanged(true);
         }
         insertLicense(modelSetParser);
         insertSetStartModelPattern(modelSetParser);
         insertSetEntryType(modelSetParser);
         insertSetWithWithout(modelSetParser);
      }

      return modelSetParser;
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
                  + "      if (value instanceof java.util.Collection)\n"
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
                  + "\n" 
               );

         CGUtil.replaceAll(text, 
            "ModelType", CGUtil.shortClassName(model.getFullName()));

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
         
         this.insertImport(parser, "java.util.Collection");
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
                  "   @Override\n" +
                  "   public String getEntryType()\n" + 
                  "   {\n" + 
                  "      return \"ModelType\";\n" + 
                  "   }\n"
               );

         CGUtil.replaceAll(text,"ModelType", model.getFullName());

         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }

   public Parser getOrCreateParserForPatternObjectFile(String rootDir)
   {
      if (patternObjectParser == null)
      {
         // try to find existing file
         String name=model.getFullName();
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

         if (model.isExternal())
         {
            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
         }

         String fullEntityClassName = name;

         String entitiyClassName = name.substring(pos + 1);

         String patternObjectClassName = entitiyClassName + "PO";

         String fileName = packageName + "." + patternObjectClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File patternObjectJavaFile = new File(fileName);
         patternObjectParser = new Parser()
         .withFileName(fileName);
         // found old one?
         if (patternObjectJavaFile.exists() && !isShowDiff())
         {
            patternObjectParser.withFileBody( CGUtil.readFile(patternObjectJavaFile) );
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
                     + "      newInstance(CreatorCreator.createIdMap(\"PatternObjectType\"));\n"
                     + "   }\n\n"
                     + "   public patternObjectClassName(ModelClass... hostGraphObject) {\n"
                     + "      if(hostGraphObject==null || hostGraphObject.length<1){\n" 
                     + "         return ;\n" 
                     + "      }\n" 
                     + "      newInstance(CreatorCreator.createIdMap(\"PatternObjectType\"), hostGraphObject);\n"
                     + "   }\n"
                     + "}\n");

            if(getRepairClassModel().hasFeature(Feature.ALBERTsSets)){
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
            }else{
               CGUtil.replaceAll(text, 
                  "ALLMATCHES", "");
            }
            CGUtil.replaceAll(text, 
               "patternObjectClassName", patternObjectClassName, 
               "entitiyClassName", entitiyClassName, 
               "fullEntityClassName", fullEntityClassName,
               "ModelClass", entitiyClassName,
               "packageName", packageName);
            
            patternObjectParser.withFileBody( text ).withFileChanged(true);
         }

//FIXME STEFAN         if(getRepairClassModel().hasFeature(Feature.ALBERTsSets)){
//            this.insertImport(patternObjectParser, packageName + "." + entitiyClassName + "Set");
//         }
      }
      return patternObjectParser;
   }
   
   private void insertSetStartModelPattern(Parser parser)
   {
      String searchString = Parser.METHOD + ":has" + CGUtil.shortClassName(model.getName()) + "PO()";
      int pos = parser.indexOf(searchString);

      if (pos < 0)
      {
         StringBuilder text = new StringBuilder(
               "\n\n" +
                  "   public ModelPO hasModelPO()\n" +
                  "   {\n" +
                  "      return new ModelPO(this.toArray(new ModelItem[this.size()]));\n" +
                  "   }\n"
               );

         String packageName = CGUtil.packageName(model.getName());

         if (model.getName().endsWith("Impl") && packageName.endsWith(".impl"))
         {
            packageName = packageName.substring(0, packageName.length() - 5);
         }

         CGUtil.replaceAll(text,
            "ModelPO", CGUtil.shortClassName(model.getName()) + "PO",
            "ModelPatternClass", packageName + ".creators.ModelPattern",
            "ModelItem", CGUtil.shortClassName(model.getName())
            );

//         insertImport(parser, StringList.class.getName());
         pos = parser.indexOf(Parser.CLASS_END);

         parser.insert(pos, text.toString());
      }
   }

   public Parser getOrCreateParserForPatternObjectCreatorFile(String rootDir)
   {
      if(!getRepairClassModel().hasFeature(Feature.Serialization)){
         return null;
      }

      if (patternObjectCreatorParser == null)
      {
         // try to find existing file
         String name=model.getFullName();
         int pos = name.lastIndexOf('.');

         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

         if (model.isExternal())
         {
            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
         }

         String entitiyClassName = name.substring(pos + 1);

         String patternObjectCreatorClassName = entitiyClassName + "POCreator";

         String fileName = packageName + "." + patternObjectCreatorClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File patternObjectCreatorJavaFile = new File(fileName);
         patternObjectCreatorParser = new Parser()
         .withFileName(fileName);
         // found old one?
         boolean addImport=false;
         if (patternObjectCreatorJavaFile.exists() && !isShowDiff())
         {
            patternObjectCreatorParser.withFileBody( CGUtil.readFile(patternObjectCreatorJavaFile) );
         }
         else
         {
            StringBuilder text = new StringBuilder(
               "package packageName;\n" +
                     "\n" +
                     "import org.sdmlib.models.pattern.util.PatternObjectCreator;\n" +
                     "import "+JsonIdMap.class.getName()+";\n" +
                     "\n" +
                     "public class patternObjectCreatorClassName extends PatternObjectCreator\n" +
                     "{\n" +
                     "   @Override\n" +
                     "   public Object getSendableInstance(boolean reference)\n" + 
                     "   {\n" +
                     "      if(reference) {\n"+
                     "          return new entitiyPOClassName(new entitiyClassName[]{});\n" +
                     "      } else {\n" +
                     "          return new entitiyPOClassName();\n" +
                     "      }\n"+
                     "   }\n" + 
                     "   \n" + 
                     "   public static JsonIdMap createIdMap(String sessionID) {\n" +
                     "      return CreatorCreator.createIdMap(sessionID);\n" +
                     "   }\n"+
                  "}\n");

            CGUtil.replaceAll(text, 
               "patternObjectCreatorClassName", patternObjectCreatorClassName, 
               "entitiyPOClassName", entitiyClassName + "PO",
               "entitiyClassName", entitiyClassName,
               "packageName", packageName);

            patternObjectCreatorParser.withFileBody( text ).withFileChanged(true);
            addImport=true;
         }

         if(addImport){
            insertImport(patternObjectCreatorParser, name);
         }
      }

      return modelSetParser;
   }

   public void insertCreatorClassInCreatorCreator(Parser ccParser)
   {
      int pos = ccParser.indexOf(Parser.METHOD + ":getCreatorSet()");

      String name=model.getFullName();

      if (pos < 0)
      {
         // ups, did not find createIdMap method. 
         System.err.println("Warning: SDMLib codgen for creatorSet initialisation invocation " + name + "Creator for class " + ccParser.getFileName() 
            + ": \nDid not find method getCreatorSet(). Should have been generated by my model. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = ccParser.getMethodBodyStartPos();
      String shortCreatorClassName = CGUtil.shortClassName(name) + "Creator";
      String shortCreatorPOClassName = CGUtil.shortClassName(name) + "POCreator";
      String creatorClassName = CGUtil.packageName(name) + GenClassModel.UTILPATH + "."+shortCreatorClassName;
      String creatorPOClassName = CGUtil.packageName(name) + GenClassModel.UTILPATH + "."+shortCreatorPOClassName;

      if (model.isExternal())
      {
         // generate creator for external class. Put it in the model package
         creatorClassName = getRepairClassModel().getName() + GenClassModel.UTILPATH + "."+shortCreatorClassName;
         creatorPOClassName = getRepairClassModel().getName() + GenClassModel.UTILPATH + "."+shortCreatorPOClassName;
      }

      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);

      if (pos < 0)
      {         
        ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int addCreatorPos = ccParser.search("creatorSet.addAll", methodBodyStartPos); 

         StringBuilder text = new StringBuilder
               (  "creatorSet.add(new ClassCreator());\n" +
                     "         creatorSet.add(new ClassPOCreator());\n         " 
                     );

         CGUtil.replaceAll(text, 
            "ClassCreator", creatorClassName,
            "ClassPOCreator", creatorPOClassName
               );

         ccParser.insert(addCreatorPos, text.toString());
//         getClassModel().getGenerator().setFileHasChanged(true);
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

   public void removeAllGeneratedCode(String testDir, String srcDir,
         String helpersDir)
   {
      getRepairClassModel().getGenerator().turnRemoveCallToComment(testDir);
      getRepairClassModel().getGenerator().removeAllCodeForClass(srcDir, helpersDir, model);
   }

   
   public GenClass withConstant(String name, int i)
   {
      StringBuilder decl = new StringBuilder(
         "   public static int string = number;\n"
            );

      CGUtil.replaceAll(decl, "string", name, "number", "" + i);

      constantDecls.put(name, decl.toString());

      return this;
   }

   public GenClass withConstant(String name, String value)
   {
      StringBuilder decl = new StringBuilder(
         "   public static String name = \"value\";\n"
            );

      CGUtil.replaceAll(decl, "name", name, "value", value);

      constantDecls.put(name, decl.toString());

      return this;
   }

   public GenClass withRunningConstants(String... names)
   {
      int i = 0;
      for (String string : names)
      {
         withConstant(string, i);
         i++;
      }

      return this;
   }
   //==========================================================================
   public String getFilePath()
   {
      return this.filePath;
   }

   public void setFilePath(String value)
   {
      if ( ! StrUtil.stringEquals(this.filePath, value))
      {
         this.filePath = value;
      }
   }

   public GenClass withFilePath(String value)
   {
      setFilePath(value);
      return this;
   }


   public int printAll(DIFF diff, List<String> ignoreDiff)
   {
      int count=0;
      count += showDiffForParser(parser, diff, ignoreDiff);
      count += showDiffForParser(creatorParser, diff, ignoreDiff);
      count += showDiffForParser(modelSetParser, diff, ignoreDiff);
      count += showDiffForParser(patternObjectParser, diff, ignoreDiff);
      count += showDiffForParser(patternObjectCreatorParser, diff, ignoreDiff);
      return count;
   }
   private int showDiffForParser(Parser newFileParser, DIFF diff, List<String> ignoreDiff){
      int count=0;
      if(newFileParser==null){
         return 0;
      }
      File file = new File(newFileParser.getFileName());
      if(file.exists()){
         Parser oldFileParser = new Parser().withFileName(newFileParser.getFileName());
         oldFileParser.withFileBody( CGUtil.readFile(file) );
         oldFileParser.parse();
         
         newFileParser.parse();
         // Diff Methods
         LinkedHashMap<String, SymTabEntry> oldSymTab = oldFileParser.getSymTab();
         
         String packageName = CGUtil.packageName(this.model.getFullName());
         String shortName = newFileParser.getFileName().replace("\\", ".").replace("/", ".");
         shortName = shortName.substring(shortName.indexOf(packageName));
         
         if(ignoreDiff != null && ignoreDiff.contains(shortName.substring(0, shortName.length()-5))){
            return 0;
         }
         
         for(Iterator<Entry<String, SymTabEntry>> i = newFileParser.getSymTab().entrySet().iterator();i.hasNext();){
            Entry<String, SymTabEntry> item = i.next();
            if(item.getValue().getMemberName().startsWith(Parser.IMPORT)){
               //ignore Import
               continue;
            }
            if(item.getKey().startsWith(Parser.EXTENDS) || item.getKey().startsWith(Parser.IMPLEMENTS)){
               //ignore Import
               continue;
            }
            if(!oldSymTab.containsKey(item.getKey())){
               if(diff==DIFF.FULL){
                  System.err.println(file.getAbsolutePath()+";"+item.getKey()+";Method not found");
               }
               continue;
            }
            SymTabEntry oldValue = oldSymTab.get(item.getKey());
            int oldValueLen = oldValue.getEndPos()-oldValue.getStartPos();
            SymTabEntry newValue = item.getValue();
            int newValueLen = newValue.getEndPos()-newValue.getStartPos();
            
            String oldStrValue = oldFileParser.getText().substring(oldValue.getStartPos(), oldValue.getEndPos() + 1).trim();
            String newStrValue = newFileParser.getText().substring(newValue.getStartPos(), newValue.getEndPos() + 1).trim();
            
            String[] oldSplit = oldStrValue.split("\\s+");
            String[] newSplit = newStrValue.split("\\s+");
            
            boolean foundDiff = oldSplit.length != newSplit.length;
            String diffToken = "";
            
            for (int j = 0; j < oldSplit.length && ! foundDiff; j++ )
            {
               if ( ! oldSplit[j].equals(newSplit[j]))
               {
                  foundDiff = true;
                  diffToken = oldSplit[j] + " != " + newSplit[j];
                  break;
               }
            }
            
            if(foundDiff){
               System.err.println(file.getPath()+";"+item.getKey()+";Body different:"+oldValueLen+"!="+newValueLen+";");
               System.err.println("in line:"+oldFileParser.getLineIndexOf(oldValue.getStartPos())+"-"+oldFileParser.getLineIndexOf(oldValue.getEndPos())+";");
               System.err.println("("+shortName+":"+oldFileParser.getLineIndexOf(oldValue.getStartPos())+")");
//                  System.out.println(oldStrValue);
//                  System.out.println("--------------------------------------------------------------------------------------------------");
               System.err.println(newStrValue);
               System.err.println("diff token: " + diffToken);
               
               count += 1;
               continue;
            }
         }
      }else if(diff==DIFF.FULL){
         System.err.println(file.getAbsolutePath()+";;File not Found!!!");
         
      }
      return count;
   }
}
