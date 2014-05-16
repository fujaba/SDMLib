package org.sdmlib.models.classes.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.ParameterSet;
import org.sdmlib.models.classes.util.RoleSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;

public class GenClassModel
{
   public static final String UTILPATH=".util";

   private Parser modelPatternParser;
   private String modelPatternClassName;
   private boolean modelPatternFileHasChanged;
   private ClassModel model;
   
   private File javaFile;
   private StringBuilder fileBody;
   private LinkedHashMap<String, Clazz> handledClazzes = new LinkedHashMap<String, Clazz>();
   private AssociationSet associations = null;
   private HashMap<Object, Generator<?>> generators=new HashMap<Object, Generator<?>>();
   private Parser creatorCreatorParser;

   
   public boolean addToAssociations(Association value)
   {
       boolean changed = false;

       if (value != null)
       {
           if (this.associations == null)
           {
               this.associations = new AssociationSet();
           }

           changed = this.associations.add(value);
       }

       return changed;
   }

   public boolean removeFromAssociations(Association value)
   {
       boolean changed = false;

       if ((this.associations != null) && (value != null))
       {
           changed = this.associations.remove(value);
       }

       return changed;
   }
   
   public AssociationSet getAssociations()
   {
       if (this.associations == null)
       {
           return new AssociationSet();
       }

       return this.associations;
   }
   
   public GenClass getOrCreate(Clazz clazz){
      if(generators.containsKey(clazz)){
         return (GenClass) generators.get(clazz);
      }
      Generator<Clazz> gen = new GenClass().withModel(clazz);
      generators.put(clazz, gen);
      return (GenClass) gen;
   }
   
   public GenMethod getOrCreate(Method method){
      if(generators.containsKey(method)){
         return (GenMethod) generators.get(method);
      }
      Generator<Method> gen = new GenMethod().withModel(method);
      generators.put(method, gen);
      return (GenMethod) gen;
   }
   
   public GenAttribute getOrCreate(Attribute attribute){
      if(generators.containsKey(attribute)){
         return (GenAttribute) generators.get(attribute);
      }
      Generator<Attribute> gen = new GenAttribute().withModel(attribute);
      generators.put(attribute, gen);
      return (GenAttribute) gen;
   }
   
   public GenAssociation getOrCreate(Association association){
      if(generators.containsKey(association)){
         return (GenAssociation) generators.get(association);
      }
      Generator<Association> gen = new GenAssociation().withModel(association);
      generators.put(association, gen);
      return (GenAssociation) gen;
   }
   
   public GenRole getOrCreate(Role role){
      if(generators.containsKey(role)){
         return (GenRole) generators.get(role);
      }
      Generator<Role> gen = new GenRole().withModel(role);
      generators.put(role, gen);
      return (GenRole) gen;
   }
   
   public boolean generate(String rootDir)
   {
      resetParsers();
      
      addHelperClassesForUnknownAttributeTypes();
      getOrCreateCreatorCreatorParser(rootDir);
      generateModelPatternClass(rootDir);
      
      
      for (Clazz clazz : model.getClasses())
      {
         getOrCreate(clazz).generate(rootDir, rootDir);
      }

      for (Association assoc : getAssociations())
      {
         getOrCreate(assoc).generate(rootDir, rootDir, false);
      }

      Exception e = new RuntimeException();

      attributNameConsistenceCheck(e, rootDir);
      return true;
   }
   
   public Parser getOrCreateCreatorCreatorParser(String rootDir)
   {
      if (!model.getClasses().isEmpty() && creatorCreatorParser == null && model.getName() != null)
      {
         String packageName = model.getName();
         
         packageName = packageName + UTILPATH;
         String creatorCreatorClassName = packageName + ".CreatorCreator";

         String fileName = creatorCreatorClassName;
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

            StringBuilder text =
                  new StringBuilder(
                        "package packageName;\n"
                           +
                           "\n"
                           +
                           "import org.sdmlib.serialization.json.JsonIdMap;\n"
                           +
                           "import org.sdmlib.serialization.json.SDMLibJsonIdMap;\n"
                           +
                           "\n"
                           +
                           "class className{\n"+
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
            for (Clazz clazz : model.getClasses())
            {
               if (!clazz.isInterface() )
               {
                  String creatorName = "";
                  if(clazz.isExternal()){
                     creatorName = model.getName()+UTILPATH+"."+CGUtil.shortClassName(clazz.getFullName());
                  }else{
                     creatorName = CGUtil.packageName(clazz.getFullName())+UTILPATH+"."+CGUtil.shortClassName(clazz.getFullName());
                  }

                  creators.append("      jsonIdMap.withCreator(new "+creatorName+"Creator());\n" +
                        "      jsonIdMap.withCreator(new "+creatorName+"POCreator());\n");
               }
            }

            
            CGUtil.replaceAll(text, 
                  "className", CGUtil.shortClassName(creatorCreatorClassName), 
                  "packageName", packageName,
                  "JSONCREATORS", creators.toString());

            fileBody.append(text.toString());
         }
         
         creatorCreatorParser = new Parser().withFileName(fileName).withFileBody(fileBody);
         
         for (Clazz clazz : this.getModel().getClasses())
         {
            if (!clazz.getInterfaze() && !clazz.isExternal())
            {
               insertCreatorClassInCreatorCreator(creatorCreatorParser, clazz);
            }
         }
         
         CGUtil.printFile(javaFile, fileBody.toString());
      }

      return creatorCreatorParser;
   }
   
   public void insertCreatorClassInCreatorCreator(Parser ccParser, Clazz clazz)
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
      String creatorClassName = CGUtil.packageName(clazz.getName()) + ".util." + shortCreatorClassName;
      String creatorPOClassName = CGUtil.packageName(clazz.getName()) + ".util." + shortCreatorPOClassName;

//      if (getWrapped())
//      {
//         // generate creator for external class. Put it in the model package
//         creatorClassName = getClassModel().getPackageName() + ".creators." + shortCreatorClassName;
//         creatorPOClassName = getClassModel().getPackageName() + ".creators." + shortCreatorPOClassName;
//      }

      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);

      if (pos < 0)
      {
         int medthodEndPos = ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int addCreatorPos = ccParser.getFileBody().indexOf("return jsonIdMap;", methodBodyStartPos);

         StringBuilder text = new StringBuilder
               (  "jsonIdMap.withCreator(new ClassCreator());\n" + 
                  "      jsonIdMap.withCreator(new ClassPOCreator());\n" +
                  "      "           
               );

         CGUtil.replaceAll(text,
            "ClassCreator", creatorClassName,
            "ClassPOCreator", creatorPOClassName
            );

         ccParser.getFileBody().insert(addCreatorPos, text.toString());
         // this.setFileHasChanged(true);
      }

   }
   
   public void addHelperClassesForUnknownAttributeTypes()
   {
      // for attribute types like java.util.Date we add a class with that name and mark it as wrapped. This generates the required DateSet class.
      for (DataType item : this.model.getClasses().getAttributes().getType())
      {
         String typeName = item.getValue();
         // seems to be a non trivial type. We should have a clazz with that type
         
         // it may be an instance of a generic type like ArrayList<String>, cut off generic part
         int pos = typeName.indexOf('<');
         
         if (pos >= 0)
         {
            typeName = typeName.substring(0, pos);
         }
         pos = typeName.indexOf('[');
         
         if (pos >= 0)
         {
            typeName = typeName.substring(0, pos);
         }
         pos = "int float double long String boolean Object java.util.Date".indexOf(typeName);
         if (pos < 0)
         {
            
            
            Clazz clazz = this.model.getClazz(typeName);
            
            if (clazz == null)
            {
               // class is missing, create it.
               model.createClazz(typeName).withExternal(true);
            }
         }
      }
   }
   
   private void attributNameConsistenceCheck(Exception e, String rootDir) {
      
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement secondStackTraceElement = stackTrace[1];
      String fileName = secondStackTraceElement.getFileName();
      String className = secondStackTraceElement.getClassName();
      String methodName = secondStackTraceElement.getMethodName();
      int callMethodLineNumber = secondStackTraceElement.getLineNumber();

      int i = 2;
      while (ClassModel.class.getName().equals(className))
      {
         secondStackTraceElement = stackTrace[i];
         fileName = secondStackTraceElement.getFileName();
         className = secondStackTraceElement.getClassName();
         methodName = secondStackTraceElement.getMethodName();
         callMethodLineNumber = secondStackTraceElement.getLineNumber();
         i++;
      }
      
      //FIXME ALEX SCHAUEN
      Clazz modelCreationClass = new ClassModel().withName(".").getGenerator().getOrCreateClazz(className);
      
      Parser parser = getOrCreate(modelCreationClass).getOrCreateParser(rootDir);
      parser.indexOf(Parser.CLASS_END);
      String signature = "method:"+methodName+"(";
      ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(signature);
      SymTabEntry symTabEntry = null;
      
      for (SymTabEntry symTabEnt : symTabEntries) {
         long startPos = parser.getLineIndexOf(symTabEnt.getStartPos());
         long endPos = parser.getLineIndexOf(symTabEnt.getEndPos());
         
         if( startPos <= callMethodLineNumber && endPos >= callMethodLineNumber ) {
            symTabEntry = symTabEnt;
         }  
      }
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = null;
      
      if (symTabEntry != null) {
         parser.parseMethodBody(symTabEntry);
         localVarTable =  parser.getLocalVarTable();
      }
      
      for (Clazz clazz : model.getClasses())
      {
         HashMap <String, Object> attributs = new HashMap <String, Object>(); 
         HashSet< Object> duplicateEntries = new HashSet< Object>();
         for( Attribute attr : clazz.getAttributes()) {
            String name = attr.getName();
            if (attributs.containsKey(name)) {
               duplicateEntries.add(attr);
               duplicateEntries.add(attributs.get(name));
            }
            attributs.put(name, attr);
         }
         
         RoleSet roles = clazz.getRoles().getOtherRoles();

         for( Role role : roles) {
            String name = role.getName();
            if (name.equals(role.getPartnerRole().getName()))
            {
               // no problem, 
               continue;
            }
            if (attributs.containsKey(name)) {
               duplicateEntries.add(role);
               duplicateEntries.add(attributs.get(name));
            }
            attributs.put(name, role);
         }
         
         
         for (Object object : duplicateEntries) {
            
            if(object instanceof Attribute) {
               Attribute attribute = (Attribute)object;
               String position = defPosition(parser, localVarTable, attribute.getName(), attribute.getType(), attribute.getClazz().getFullName() );
               
               System.err.println("in " + fileName + ":  duplicate name found in definition for " + attribute.getClazz() +"\n    "+ position+ "\n     Attribute " + attribute );
               attribute.removeYou();
            }
            else if(object instanceof Role) {
               Role role = (Role)object;
               String position = defPosition(parser, localVarTable, role.getName(), DataType.ref("NONE") ,role.getClazz().getFullName() );
               System.err.println("in " + fileName + "  duplicate name found in definition for "+ role.getClazz() + "\n    " + position + "\n      Role " + role );
               Association assoc = role.getAssoc();
               
               if(assoc != null)
                  assoc.removeYou();
               
                  role.removeYou();
            }
         }
      }        
   }
   
   private String defPosition(Parser parser, LinkedHashMap<String, LocalVarTableEntry> localVarTable, String name, DataType attrType , String clazzType) {
       String position = "";
       
      if ( localVarTable != null ) {
         
         for (LocalVarTableEntry entry : localVarTable.values()) {
         
            if (entry.getType() != null && entry.getType().equals("Clazz")) {
               String type = entry.getInitSequence().get(0).get(1).replace("\"", "");
               
               if (clazzType.endsWith(type)) {
                  CharSequence subSequence = parser.getFileBody().subSequence(entry.getStartPos(), entry.getEndPos()+1);
                  String subSequenceString = subSequence.toString();
                  
                  Pattern fileName = Pattern.compile("\""+name+"\"\\s*,\\s*\""+attrType+"\"");
                  Matcher matcher = fileName.matcher(subSequenceString);
                  
                  int indexOf = 0;
                  while (matcher.find()) {
                     String group = matcher.group(0);
                     indexOf = subSequenceString.indexOf(group);
                  }
                  position =  "at line " + parser.getLineIndexOf(entry.getStartPos() + indexOf) + "    ";
               }
            }
         }
      }
      return position;
   }

   private void generateModelPatternClass(String rootDir)
   {
      // take first class to find package

     if( getOrCreateModelPatternParser(rootDir) == null)
     {
        return;
     }

      for (Clazz clazz : model.getClasses())
      {
         getOrCreate(clazz).insertHasMethodsInModelPattern(modelPatternParser);
      }

      printModelPatternFile(modelPatternFileHasChanged);
   }


   public void printModelPatternFile(boolean really)
   {
      if (really)
      {
         File modelPatternFile = new File(modelPatternParser.getFileName());
         CGUtil.printFile(modelPatternFile, modelPatternParser.getFileBody().toString());
      }
   }
   
   public void printFile(boolean really)
   {
      if (really)
      {
         CGUtil.printFile(javaFile, fileBody.toString());
      }
   }
   
   private void writeToFile(Clazz modelCreationClass)
   {
      getOrCreate(modelCreationClass).printFile(getOrCreate(modelCreationClass).isFileHasChanged());
   }

   
   private void resetParsers()
   {
      for (Clazz clazz : this.model.getClasses())
      {
         getOrCreate(clazz).setParser(null);
      }
   }
   
   public void setModel(ClassModel value)
   {
      if (this.model != value)
      {
         ClassModel oldValue = this.model;
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
   
   protected Clazz getOrCreateClazz(String className)
   {
      for (Clazz clazz : getModel().getClasses())
      {
         if (StrUtil.stringEquals(clazz.getFullName(), className))
         {
            return clazz;
         }
      }
      Clazz clazz = new Clazz(className);
      return clazz;
   }
   
   public ClassModel getModel()
   {
      return model;
   }
   
   public Parser getOrCreateModelPatternParser(String rootDir)
   {
      if(!model.hasFeature(Feature.PatternObject)){
         return null;
      }
      
      if (!model.getClasses().isEmpty() && modelPatternParser == null)
      {
         // try to find existing file
         String packageName = model.getName();
         if (model.getName() != null)
         {
            packageName = packageName + UTILPATH;
            modelPatternClassName = packageName + ".ModelPattern";
         }
         else
         {
            Clazz firstClass = model.getClasses().iterator().next();

            modelPatternClassName = firstClass.getFullName();

            int pos = modelPatternClassName.lastIndexOf('.');

            packageName = modelPatternClassName.substring(0, pos) + UTILPATH;

            modelPatternClassName = packageName + ".ModelPattern";
         }

         String fileName = modelPatternClassName;

         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File mpJavaFile = new File(fileName);

         // found old one?
         StringBuilder mpFileBody;
         if (mpJavaFile.exists())
         {
            mpFileBody = CGUtil.readFile(mpJavaFile);
         }
         else
         {
            mpFileBody = new StringBuilder();

            StringBuilder text = 
                  new StringBuilder(
                        "package packageName;\n" + 
                              "\n" + 
                              "import org.sdmlib.models.pattern.Pattern;\n" + 
                              "\n" + 
                              "public class ModelPattern extends Pattern\n" + 
                              "{\n" + 
                              "   public ModelPattern()\n" + 
                              "   {\n" + 
                              "      super(CreatorCreator.createIdMap(\"hg\"));\n" + 
                              "   }\n" + 
                              "\n" +
                              "   public ModelPattern startCreate()\n" + 
                              "   {\n" + 
                              "      super.startCreate();\n" + 
                              "      return this;\n" + 
                              "   }\n" + 
                              "\n" +
                              "}\n" + 
                        "\n" );

            CGUtil.replaceAll(text, 
                  "packageName", packageName);

            mpFileBody.append(text.toString());

            modelPatternFileHasChanged = true;
         }

         modelPatternParser = new Parser().withFileName(fileName).withFileBody(mpFileBody);

      }

      return modelPatternParser;
   }
   
   public void insertModelCreationCodeHere(String rootDir)
   {
//      String fileName = null;
      String className = null;
      String methodName = null;

      String callMethodName = null;
//      int callMethodLineNumber = -1;

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();

      StackTraceElement firstStackTraceElement = stackTrace[0];
      callMethodName = firstStackTraceElement.getMethodName();

      StackTraceElement secondStackTraceElement = stackTrace[1];
//      fileName = secondStackTraceElement.getFileName();
      className = secondStackTraceElement.getClassName();
      methodName = secondStackTraceElement.getMethodName();
//      callMethodLineNumber = secondStackTraceElement.getLineNumber();


      // parse the model creation file
      Clazz modelCreationClass = getOrCreateClazz(className);

      modelCreationClass.getClassModel().removeFromClasses(modelCreationClass);
      
      String signature = Parser.METHOD + ":" + methodName + "(";

      // insert code
      int currentInsertPos = 0;
      
      currentInsertPos = completeCreationClasses(callMethodName, modelCreationClass, signature, currentInsertPos);

      currentInsertPos = insertNewCreationClasses(callMethodName, modelCreationClass, signature, currentInsertPos);

      writeToFile(modelCreationClass);
   }
   
   private int completeCreationClasses(String callMethodName, Clazz modelCreationClass, String signature,
         int currentInsertPos)
   {
      for (Clazz clazz : model.getClasses())
      {
         String modelClassName = clazz.getFullName();
         LocalVarTableEntry entry = findInLocalVarTable(getOrCreate(modelCreationClass).getParser().getLocalVarTable(), modelClassName);

         if (entry != null)
         {
            // check code for clazz
            handledClazzes.put(modelClassName, clazz);
            currentInsertPos = checkCodeForClazz(entry, signature, callMethodName, modelCreationClass, refreshMethodScan(signature, clazz), clazz, handledClazzes, currentInsertPos);
         }
         writeToFile(modelCreationClass);

      }
      return currentInsertPos;
   }
   
   private SymTabEntry refreshMethodScan(String signature , Clazz clazz)
   {
      SymTabEntry symTabEntry;
      rescanCode( clazz);
      symTabEntry = getOrCreate(clazz).getParser().getSymTab().get(signature);
      getOrCreate(clazz).getParser().parseMethodBody(symTabEntry);
      return symTabEntry;
   }
   
   private void rescanCode(Clazz clazz)
   {
      getOrCreate(clazz).getParser().indexOf(Parser.CLASS_END);
   }
   
   private int checkCodeForClazz(LocalVarTableEntry entry, String signature, String callMethodName, Clazz modelCreationClass, SymTabEntry symTabEntry, Clazz clazz,
         LinkedHashMap<String, Clazz> handledClazzes, int currentInsertPos)
   {
      rescanCode(modelCreationClass);
      Parser creatorCreatorParser = getOrCreate(modelCreationClass).getParser();

      // check has superclass
      if (clazz.getSuperClass() != null && !checkSuper(clazz, entry, "withSuperClass")) 
      {
         String token = Parser.NAME_TOKEN + ":" + entry.getName();
         int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
         token = Parser.NAME_TOKEN + ":;";
         currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
         // set interface
         currentInsertPos = insertCreationCode("\n       /*set superclass*/", currentInsertPos, modelCreationClass);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass); 
         StringBuilder text = new StringBuilder("      .withSuperClass(superClassName)");
         CGUtil.replaceAll(text, "superClassName", StrUtil.downFirstChar(CGUtil.shortClassName(clazz.getSuperClass().getFullName())) + "Class" );
         currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
         currentInsertPos++;
         //       currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, clazz);
         getOrCreate(clazz).isFileHasChanged();
      }

      // check is interface
      else if (clazz.isInterface() && !isInterface(entry))
      {
         String token = Parser.NAME_TOKEN + ":" + entry.getName();
         int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
         token = Parser.NAME_TOKEN + ":;";
         currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
         // set interface
         currentInsertPos = insertCreationCode("\n       /*set interface*/", currentInsertPos, modelCreationClass);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass); 
         StringBuilder text = new StringBuilder("      .withInterfaze(true)");
         currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
         currentInsertPos++;
         //       currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, clazz);
         getOrCreate(clazz).isFileHasChanged();
      }

      // check has interfaces
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (!checkSuper(interfaze, entry, "withInterfaces")) 
         {
            //          writeToFile(modelCreationClass);
            // find insert position
            String token = Parser.NAME_TOKEN + ":" + entry.getName();
            int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
            token = Parser.NAME_TOKEN + ":;";
            currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
            // add attribut
            currentInsertPos = insertCreationCode("\n       /*add interface*/", currentInsertPos, modelCreationClass);
            currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
            StringBuilder text = new StringBuilder("      .withInterfaces(interfaceName)");
            CGUtil.replaceAll(text, "interfaceName", StrUtil.downFirstChar(CGUtil.shortClassName(interfaze.getFullName())) + "Class" );
            currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
            currentInsertPos++;
         }

         // set insert position to next line
         currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, interfaze);
      }

      // check code for attribut
      LinkedHashSet<Attribute> clazzAttributes = clazz.getAttributes();

      for (Attribute attribute : clazzAttributes)
      {
         if (!hasAttribute(attribute, entry) && !"PropertyChangeSupport".equals(attribute.getType()))
         {
            writeToFile(modelCreationClass);

            // find insert position
            if (entry.getInitSequence().get(0).get(0).endsWith(".createClassAndAssoc"))
            {
               boolean found = false;
               // attributes belong to a separate playerClass.withAttributes(...) statement
               for (StatementEntry stat : creatorCreatorParser.getCurrentStatement().getParent().getBodyStats())
               {
                  if (stat.getTokenList().get(0).equals(entry.getName() + ".withAttributes"))
                  {
                     // here we are
                     currentInsertPos = stat.getEndPos() - 1;
                     found = true;
                     break;
                  }
               }

               if (! found)
               {
                  // there is no class.withAttributes(...) yet. Create one
                  StringBuilder text = new StringBuilder(
                        "\n" +
                              "\n" +
                              "      classVar.withAttributes(\n" +
                              "         \"name\", \"type\");"
                        );

                  CGUtil.replaceAll(text, 
                        "classVar", entry.getName(),
                        "name", attribute.getName(),
                        "type", attribute.getType());

                  currentInsertPos = insertCreationCode(text.toString(), entry.getEndPos() + 2, modelCreationClass);

                  symTabEntry = refreshMethodScan(signature, clazz);

                  continue;
               }
            }
            else 
            {
               String token = Parser.NAME_TOKEN + ":" + entry.getName();
               int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());

               token = Parser.NAME_TOKEN + ":;";
               currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos()) - 1;
            }

            // add attribute
            StringBuilder text = new StringBuilder(
                  ",\n" +
                        "         /*add attribut*/\n" +
                  "         \"name\", \"type\"");

            CGUtil.replaceAll(text, 
                  "name", attribute.getName(),
                  "type", attribute.getType());

            currentInsertPos = insertCreationCode(text.toString(), currentInsertPos, modelCreationClass);
            currentInsertPos++;
         }

         // set insert position to next line
         currentInsertPos += 2;
         symTabEntry = refreshMethodScan(signature, clazz);
      }

      // check code for method
      LinkedHashSet<Method> methods = clazz.getMethods();

      for (Method method : methods)
      {
         currentInsertPos = tryToInsertMethod(symTabEntry, method, currentInsertPos, modelCreationClass);
      }

      // check code for assoc
      LinkedHashSet<Role> roles = new LinkedHashSet<Role>();

      if (!clazz.getRoles().isEmpty())
         roles.addAll(clazz.getRoles());

      for (Role role : roles)
      {
         Association  association = role.getAssoc();
         if (association == null)
            continue;
         String sourceClassName = association.getSource().getClazz().getFullName();
         String targetClassName = association.getTarget().getClazz().getFullName();

         if (handledClazzes.containsKey(sourceClassName) && handledClazzes.containsKey(targetClassName))
         {

            symTabEntry = refreshMethodScan(signature, clazz);

            int indexOfSourceClassPos = positionOfClazzDecl(sourceClassName, clazz);
            int indexOfTargetClassPos = positionOfClazzDecl(targetClassName, clazz);
            int resultPos = Math.max(indexOfSourceClassPos, indexOfTargetClassPos) + 3;
            currentInsertPos = Math.max(resultPos, currentInsertPos);
            currentInsertPos = tryToInsertAssoc(symTabEntry, role, currentInsertPos, modelCreationClass);
         }

      }
      return currentInsertPos;
   }
   
   private LocalVarTableEntry findInLocalVarTable(LinkedHashMap<String, LocalVarTableEntry> localVarTable, String name)
   {
      for (LocalVarTableEntry entry : localVarTable.values())
      {
         if ("Clazz".equals(entry.getType()))
         {
            if (entry.getInitSequence() == null) continue;
            ArrayList<String> initSequence = entry.getInitSequence().get(0);
            if (initSequence.size() >= 2 && 
                  (initSequence.get(0).startsWith("new") 
                        || initSequence.get(0).startsWith("model.createClazz")
                        || initSequence.get(0).endsWith(".createClassAndAssoc")))
            {
               String className = initSequence.get(1).replaceAll("\"", "");
               if (StrUtil.stringEquals(name, className) || name.endsWith("." + className))
               {
                  return entry;
               }
            }
         }
      }

      return null;
   }
   
   private int positionOfClazzDecl(String sourceClassName, Clazz clazz)
   {
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = getOrCreate(clazz).getParser().getLocalVarTable();
      for (String localVarTableEntityName : localVarTable.keySet())
      {
         LocalVarTableEntry localVarTableEntry = localVarTable.get(localVarTableEntityName);
         ArrayList<String> arrayList = localVarTableEntry.getInitSequence().get(0);
         String string = "";
         if (arrayList.size() > 1)
         {
            string = arrayList.get(1).replaceAll("\"", "");

            if (sourceClassName.equals(string) || sourceClassName.endsWith("." + string))
            {
               return localVarTableEntry.getEndPos();
            }
         }
      }
      return -1;
   }

   private int tryToInsertAssoc(SymTabEntry symTabEntry, Role role, int currentInsertPos, Clazz modelCreationClass)
   {
      getOrCreate(modelCreationClass).getParser().parseMethodBody(symTabEntry);
      boolean assocIsNew = true;
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = getOrCreate(modelCreationClass).getParser().getLocalVarTable();

      Association assoc = role.getAssoc();
      for (String string : localVarTable.keySet())
      {

         if (string.startsWith("Association_"))
         {
            LocalVarTableEntry localVarTableEntry = localVarTable.get(string);

            if (compareAssocDecl(assoc, localVarTableEntry))
            {
               assocIsNew = false;
               break;
            }
         }
      }

      for (StatementEntry stat : getOrCreate(modelCreationClass).getParser().getStatementList().getBodyStats())
      {
         if (stat.getTokenList().get(0).endsWith(".withAssoc"))
         {
            // looks like sourceClass.withAssoc(targetClass, "targetRole", targetCard, "sourceRole", sourceCard);

            // ensure source class name
            String classVar = CGUtil.packageName(stat.getTokenList().get(0));
            LocalVarTableEntry localVarTableEntry = localVarTable.get(classVar);
            String classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
            String classNameFromAssoc = assoc.getSource().getClazz().getFullName();

            if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
            {
               // ensure target class name
               classVar = stat.getTokenList().get(2);
               localVarTableEntry = localVarTable.get(classVar);
               classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
               classNameFromAssoc = assoc.getTarget().getClazz().getFullName();

               if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
               {
                  // ensure target role name
                  String roleNameFromText = stat.getTokenList().get(4).replaceAll("\"", "");

                  if (StrUtil.stringEquals(roleNameFromText, assoc.getTarget().getName()))
                  {
                     // ensure source role name
                     roleNameFromText = stat.getTokenList().get(8).replaceAll("\"", "");
                     if (StrUtil.stringEquals(roleNameFromText, assoc.getSource().getName()))
                     {
                        // found it
                        assocIsNew = false;
                        break;
                     }
                  }
                  System.out.println("found assoc");
               }
            }            
         } 
         else if (stat.getTokenList().get(0).endsWith(".createClassAndAssoc"))
         {
            // looks like sourceClass.withAssoc(targetClass, "targetRole", targetCard, "sourceRole", sourceCard);

            // ensure source class name
            String classVar = CGUtil.packageName(stat.getTokenList().get(0));
            LocalVarTableEntry localVarTableEntry = localVarTable.get(classVar);
            String classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
            String classNameFromAssoc = assoc.getSource().getClazz().getFullName();

            if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
            {
               // ensure target class name
               classNameFromText = stat.getTokenList().get(2).replaceAll("\"", "");
               classNameFromAssoc = assoc.getTarget().getClazz().getFullName();

               if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
               {
                  // ensure target role name
                  String roleNameFromText = stat.getTokenList().get(4).replaceAll("\"", "");

                  if (StrUtil.stringEquals(roleNameFromText, assoc.getTarget().getName()))
                  {
                     // ensure source role name
                     roleNameFromText = stat.getTokenList().get(8).replaceAll("\"", "");
                     if (StrUtil.stringEquals(roleNameFromText, assoc.getSource().getName()))
                     {
                        // found it
                        assocIsNew = false;
                        break;
                     }
                  }
                  System.out.println("found assoc");
               }
            }            
         } 

      }


      if (assocIsNew)
      {
         String name = CGUtil.shortClassName(role.getClazz().getFullName()) + "Class";
         name = StrUtil.downFirstChar(name);
         LocalVarTableEntry localVarTableEntry = localVarTable.get(name);
         int insertPos = localVarTableEntry.getEndPos() + 3;
         insertPos = currentInsertPos;
         currentInsertPos = insertCreationCode("      /* add assoc */", insertPos, modelCreationClass);
         currentInsertPos = insertCreationAssociationCode(assoc, currentInsertPos, modelCreationClass, symTabEntry);
      }
      return currentInsertPos;
   }

   private boolean compareAssocDecl(Association assoc, LocalVarTableEntry localVarTableEntry)
   {

      if (compareAssocs(assoc, localVarTableEntry.getInitSequence()))
      {
         return true;
      }

      return false;
   }

   private boolean compareAssocs(Association assoc, ArrayList<ArrayList<String>> initSequence)
   {

      String sourceInitSequence = findInitSequenceAsString(".withSource", initSequence);
      sourceInitSequence = cutCardinality(sourceInitSequence);

      String targetInitSequence = findInitSequenceAsString(".withTarget", initSequence);
      targetInitSequence = cutCardinality(targetInitSequence);

      String sourceSequence = getInitSequenceAsString(".withSource", assoc);
      sourceSequence = cutCardinality(sourceSequence);

      String targetSequence = getInitSequenceAsString(".withTarget", assoc);
      targetSequence = cutCardinality(targetSequence);

      if ((sourceInitSequence.equals(sourceSequence) && targetInitSequence.equals(targetSequence))
            || (targetInitSequence.equals(sourceSequence) && sourceInitSequence.equals(targetSequence)))
         return true;

      return false;
   }

   private String cutCardinality(String sourceInitSequence)
   {
      sourceInitSequence = sourceInitSequence.substring(0, sourceInitSequence.lastIndexOf(','));
      return sourceInitSequence;
   }

   private String getInitSequenceAsString(String string, Association assoc)
   {
      String sequence = string + "(";
      Role role = null;
      if (".withSource".equals(string))
         role = assoc.getSource();
      else if (".withTarget".equals(string))
         role = assoc.getTarget();
      else
         return null;
      sequence += "\"" + role.getName() + "\"";
      String shortClassName = CGUtil.shortClassName(role.getClazz().getFullName());
      String clazzString = StrUtil.downFirstChar(shortClassName) + "Class";
      sequence += "," + clazzString + ",";
      sequence += "\"" + role.getCard() + "\"" + ")";
      return sequence;
   }

//FIXME   private ArrayList<String> findSequence(String searchString, ArrayList<ArrayList<String>> initSequence)
//   {
//      for (ArrayList<String> sequence : initSequence)
//      {
//         String sequenceString = "";
//         for (String string : sequence)
//         {
//            sequenceString += string;
//         }
//         if (sequenceString.startsWith(searchString))
//         {
//            return sequence;
//         }
//      }
//      return null;
//   }

   private String findInitSequenceAsString(String searchString, ArrayList<ArrayList<String>> initSequence)
   {
      for (ArrayList<String> sequence : initSequence)
      {
         String sequenceString = "";
         for (String string : sequence)
         {
            sequenceString += string;
         }
         if (sequenceString.startsWith(searchString))
         {
            return sequenceString;
         }
      }
      return null;
   }

   private int tryToInsertMethod(SymTabEntry symTabEntry, Method method, int currentInsertPos, Clazz modelCreationClass)
   {
      getOrCreate(modelCreationClass).getParser().parseMethodBody(symTabEntry);
      boolean methodIsNew = true;
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = getOrCreate(modelCreationClass).getParser().getLocalVarTable();

      for (String string : localVarTable.keySet())
      {

         if (string.startsWith("Method_"))
         {
            LocalVarTableEntry localVarTableEntry = localVarTable.get(string);

            if (compareMethodDecl(method, localVarTableEntry))
            {
               methodIsNew = false;
               break;
            }
         }
      }

      if (methodIsNew)
      {
         String name = CGUtil.shortClassName(method.getClazz().getFullName()) + "Class";
         name = StrUtil.downFirstChar(name);
         LocalVarTableEntry localVarTableEntry = localVarTable.get(name);
         if (localVarTableEntry == null)
            return currentInsertPos;
         int insertPos = localVarTableEntry.getEndPos() + 3;
         currentInsertPos = insertCreationCode("      /* add method */", insertPos, modelCreationClass);
         currentInsertPos = insertCreationMethodeCode(method, currentInsertPos, modelCreationClass, symTabEntry);
         writeToFile(modelCreationClass);
      }

      return currentInsertPos;
   }

   private boolean compareMethodDecl(Method method, LocalVarTableEntry localVarTableEntry)
   {
      String shortClassName = CGUtil.shortClassName(method.getClazz().getFullName()) + "Class";
      shortClassName = StrUtil.downFirstChar(shortClassName);
      ParameterSet parameters = method.getParameters();
      String signature = method.getSignature();

      String methodClass = "";
      String methodSignature = "";
      ArrayList<ArrayList<String>> initSequence = localVarTableEntry.getInitSequence();

      for (ArrayList<String> arrayList : initSequence)
      {

         if (".".equals(arrayList.get(0)) && "withClazz".equals(arrayList.get(1)))
         {
            methodClass = arrayList.get(3);
         }
         else if (".".equals(arrayList.get(0)) && "withSignature".equals(arrayList.get(1)))
         {
            methodSignature = arrayList.get(3).replace("\"", "");
         }
      }

      if (StrUtil.stringEquals(shortClassName, methodClass) && StrUtil.stringEquals(signature, methodSignature))
      {
         return true;
      }
      return false;
   }

   private int createAndInsertCodeForNewClazz(String callMethodName, Clazz modelCreationClass, SymTabEntry symTabEntry, Clazz clazz, LinkedHashMap<String, Clazz> handledClazzes,
         int currentInsertPos)
   {
      String modelClassName = clazz.getFullName();
      // no creation code yet. Insert it.
      currentInsertPos = insertCreationClassCode(currentInsertPos, modelClassName, modelCreationClass, symTabEntry);

      // check interface attr
      if (clazz.isInterface()) {
         currentInsertPos = insertCreationIsInterfaceCode(currentInsertPos, modelCreationClass, symTabEntry);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
      }

      // insert code for superclass
      Clazz superClass = clazz.getSuperClass();
      if (superClass != null) {
         currentInsertPos = insertCreationSuperClassCode( currentInsertPos, superClass.getFullName(), modelCreationClass, symTabEntry);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
      }

      // insert code for interfaces
      for ( Clazz interfaze :clazz.getInterfaces() ){
         if (interfaze != null) {
            currentInsertPos = insertCreationInterfaceCode(currentInsertPos, interfaze.getFullName(), modelCreationClass, symTabEntry);
            currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
         }
      }

      // insert code for new Attr()
      LinkedHashSet<Attribute> clazzAttributes = clazz.getAttributes();
      for (Attribute attribute : clazzAttributes)
      {
         if ( !"PropertyChangeSupport".equals(attribute.getType())) {
            currentInsertPos = insertCreationAttributeCode(attribute,
                  currentInsertPos, modelCreationClass, symTabEntry);
            currentInsertPos = insertCreationCode("\n", currentInsertPos,
                  modelCreationClass);
         }
      }
      currentInsertPos = 1 + insertCreationCode(";", currentInsertPos - 1, modelCreationClass);

      // insert code for new Method()
      LinkedHashSet<Method> methods = clazz.getMethods();
      for (Method method : methods)
      {
         currentInsertPos = insertCreationMethodeCode(method, currentInsertPos, modelCreationClass, symTabEntry);
      }

      // insert code for new Assoc
      LinkedHashSet<Role> roles = new LinkedHashSet<Role>();

      if (!clazz.getRoles().isEmpty())
         roles.addAll(clazz.getRoles());

      currentInsertPos = handleAssocs(roles, currentInsertPos, modelCreationClass, symTabEntry, handledClazzes);

      return currentInsertPos;
   }
   
//FIXME   private int searchForQualifiedNamePosition(String methodCall, int methodEndPos, Parser parser)
//   {
//      Set<String> methodBodyQualifiedNames = parser.getMethodBodyQualifiedNames();
//      for (String qualifiedName : methodBodyQualifiedNames)
//      {
//         if (qualifiedName.contains(methodCall))
//         {
//            int callPos = parser.getMethodBodyQualifiedNamesMap().get(qualifiedName);
//            String substring = parser.getFileBody().substring(callPos, methodEndPos);
//            return callPos + substring.indexOf(';') + 1;
//         }
//      }
//      return -1;
//   }
   

   private int handleAssocs(LinkedHashSet<Role> roles, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry, LinkedHashMap<String, Clazz> handledClazzes)
   {
      ArrayList<Association> handledAssocs = new ArrayList<Association>();
      for (Role firstRole : roles)
      {
         Association assoc = firstRole.getAssoc();

         if (assoc == null || assocHasHandled(assoc, handledAssocs))
         {
            continue;
         }
         handledAssocs.add(assoc);

         Role secondRole;

         if (assoc.getTarget() != firstRole)
            secondRole = assoc.getTarget();
         else
            secondRole = assoc.getSource();

         String secondClassName = secondRole.getClazz().getFullName();

         if (handledClazzes.containsKey(secondClassName))
         {
            currentInsertPos = insertCreationAssociationCode(assoc, currentInsertPos, modelCreationClass, symTabEntry);
         }

         writeToFile(modelCreationClass);
      }
      return currentInsertPos;
   }
   
   private int insertNewCreationClasses(String callMethodName, Clazz modelCreationClass, String signature,
         int currentInsertPos)
   {

      Queue<Clazz> clazzQueue = new LinkedList<Clazz>(); 

      for (Clazz clazz : model.getClasses())
      {
         clazzQueue.offer(clazz);
      }

      boolean format = false;

      while (!clazzQueue.isEmpty())
      {
         Clazz clazz  = clazzQueue.poll();

         String modelClassName = clazz.getFullName();

         LocalVarTableEntry entry = findInLocalVarTable(getOrCreate(modelCreationClass).getParser().getLocalVarTable(), modelClassName);

         if (entry == null)
         {
            // insert code for new Clazz()
            if (!checkDependencies(clazz)) {
               clazzQueue.offer(clazz);
            }
            else
            {
               if (!format) {
                  currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
                  format = true;
               }

               handledClazzes.put(modelClassName, clazz);
               currentInsertPos = createAndInsertCodeForNewClazz(callMethodName, modelCreationClass, refreshMethodScan(signature, clazz), clazz, handledClazzes, currentInsertPos);
            }
         }
      }
      return currentInsertPos;
   }
   
   private boolean checkDependencies(Clazz clazz)
   {
      ArrayList<Clazz> dependencies = new ArrayList<Clazz>();
      if (clazz.getSuperClass() != null)
         dependencies.add(clazz.getSuperClass());
      if (clazz.getInterfaces() != null)
         dependencies.addAll(clazz.getInterfaces());

      for (Clazz depClazz : dependencies)
      {
         if (handledClazzes.get(depClazz.getFullName()) == null) {
            return false;
         }
      }  
      return true;
   }
   
   private boolean assocHasHandled(Association assoc, ArrayList<Association> handledAssocs)
   {
      Role source = assoc.getSource();
      Role target = assoc.getTarget();

      for (Association association : handledAssocs)
      {

         Role source2 = association.getSource();
         Role target2 = association.getTarget();

         if (compareRoles(source, target2) && compareRoles(target, source2))
            return true;

      }
      return false;
   }

   private int insertCreationClassCode(int currentInsertPos, String modelClassName, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("\n      Clazz localVar = new Clazz(\"className\")\n");

      CGUtil.replaceAll(text, "localVar", StrUtil.downFirstChar(CGUtil.shortClassName(modelClassName)) + "Class", 
            "className", modelClassName);

      currentInsertPos = checkImport("Clazz", currentInsertPos, modelCreationClass, symTabEntry);
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationSuperClassCode(int currentInsertPos, String superClassName, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withSuperClass(superClassName)");

      CGUtil.replaceAll(text, "superClassName", StrUtil.downFirstChar(CGUtil.shortClassName(superClassName)) + "Class");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationInterfaceCode(int currentInsertPos, String interfaceName, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withInterfaces(interfaceName)");

      CGUtil.replaceAll(text, "interfaceName", StrUtil.downFirstChar(CGUtil.shortClassName(interfaceName)) + "Class");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationIsInterfaceCode(int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withInterfaze(true)");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationAttributeCode(Attribute attribute, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withAttribute(\"attributeName\", \"attributeType\"attributeInit)");

      // has init value
      String initialization = attribute.getInitialization();
      if (initialization != null)
      {
         initialization = ", \"" + initialization + "\"";
      }
      else
      {
         initialization = "";
      }

      CGUtil.replaceAll(text, "attributeName", attribute.getName(), 
            "attributeType", attribute.getType(), 
            "attributeInit", initialization);

      return insertCreationCode(text, currentInsertPos, modelCreationClass);

   }

   private int insertCreationMethodeCode(Method method, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {

      StringBuilder text = new StringBuilder("\n      new Method()" + "\n        .withClazz(clazzName)" + "\n        .withSignature(\"methodSignature\");\n");

      String clazzName = method.getClazz().getFullName();
      clazzName = StrUtil.downFirstChar(CGUtil.shortClassName(clazzName)) + "Class";
      String signature = method.getSignature();

      CGUtil.replaceAll(text, "clazzName", clazzName, "methodSignature", signature);

      currentInsertPos = checkImport("Method", currentInsertPos, modelCreationClass, symTabEntry);
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int checkImport(String string, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      getOrCreate(modelCreationClass).getParser().indexOf(Parser.CLASS_END);
      LinkedHashMap<String, SymTabEntry> symTab = getOrCreate(modelCreationClass).getParser().getSymTab();
      LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

      for (String key : symTab.keySet())
      {
         if (key.startsWith(Parser.IMPORT) && (key.endsWith("." + string) || key.endsWith(".ClassModel")))
         {
            String path = key.replace(Parser.IMPORT + ":", "");
            int lastIndexOf = path.lastIndexOf('.') + 1;
            String name = path.substring(lastIndexOf);
            String pathString = path.substring(0, lastIndexOf - 1);
            result.put(name, pathString);
         }
      }

      if (!result.containsKey(string) && result.containsKey("ClassModel"))
      {
         String symTabEntryName = result.get("ClassModel");
         int endOfImports = getOrCreate(modelCreationClass).getParser().getEndOfImports() + 1;
         String importString = "\n" + Parser.IMPORT + " " + symTabEntryName + "." + string + ";";
         insertCreationCode(importString, endOfImports, modelCreationClass);
         currentInsertPos += importString.length();
      }

      return currentInsertPos;
   }

   private int insertCreationAssociationCode(Association assoc, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder(
            "\n      sourceClazz.withAssoc(targetClazz, \"targetName\", targetRole, \"sourceName\", sourceRole);\n");

      String sourceRole = assoc.getSource().getCard().toUpperCase();
      String sourceName = assoc.getSource().getName();
      String sourceClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getSource().getClazz().getFullName())) + "Class";

      String targetRole = assoc.getTarget().getCard().toUpperCase();
      String targetName = assoc.getTarget().getName();
      String targetClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getTarget().getClazz().getFullName())) + "Class";

      CGUtil.replaceAll(text, 
            "sourceName", sourceName, 
            "sourceClazz", sourceClazz, 
            "sourceRole", sourceRole, 
            "targetName", targetName, 
            "targetClazz", targetClazz, 
            "targetRole", targetRole);

      currentInsertPos = checkImport("Association", currentInsertPos, modelCreationClass, symTabEntry);

      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }
   
   private boolean checkSuper(Clazz clazz, LocalVarTableEntry entry, String classType)
   {
      String name = CGUtil.shortClassName( clazz.getFullName() );
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
      for (ArrayList<String> sequencePart : initSequence)
      {
         if (classType.equals(sequencePart.get(0)))
         {
            String sequencePartName = sequencePart.get(1).replace("\"", "");
            //          if ( StrUtil.stringEquals(name, sequencePartName) )
            return true;
         }
      }
      return false;
   }

   private boolean isInterface(LocalVarTableEntry entry )
   {
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
      for (ArrayList<String> sequencePart : initSequence)
      {
         if ("withInterfaze".equals(sequencePart.get(0)))
         {
            return true;
         }
      }
      return false;
   }

   private int insertCreationCode(StringBuilder text, int insertPos, Clazz modelCreationClass )
   {
      getOrCreate(modelCreationClass).getParser().getFileBody().insert(insertPos, text.toString());
      getOrCreate(modelCreationClass).setFileHasChanged(true);
      return insertPos + text.length();
   }

   private int insertCreationCode(String string, int insertPos, Clazz modelCreationClass )
   {
      StringBuilder text = new StringBuilder(string);
      return insertCreationCode(text, insertPos, modelCreationClass );
   }

   public String getMemberType(String currentType, String varName)
   {
      String result = null;

      for (Clazz clazz : model.getClasses())
      {
         String name = CGUtil.shortClassName(clazz.getFullName());
         if (StrUtil.stringEquals(name, currentType))
         {
            for (Attribute attr : clazz.getAttributes())
            {
               if (StrUtil.stringEquals(attr.getName(), varName))
               {
                  return attr.getType().getValue();
               }
            }

            for (Role role : clazz.getRoles())
            {
               role = role.getPartnerRole();
               if (StrUtil.stringEquals(role.getName(), varName))
               {
//                  currentTypeCard = role.getCard();
                  return CGUtil.shortClassName(role.getClazz().getFullName());
               }
            }
         }
      }

      return result;
   }

   public ClassModel learnFromGenericObjects(String packageName, GenericObject root)
   {
      // collect all objects and links
      LinkedHashSet<GenericObject> allObjects = new LinkedHashSet<GenericObject>();
      LinkedList<GenericObject> todoObjects = new LinkedList<GenericObject>();

      LinkedHashSet<GenericLink> allLinks = new LinkedHashSet<GenericLink>();

      todoObjects.add(root);

      while (! todoObjects.isEmpty())
      {
         GenericObject currentObject = todoObjects.pop();

         allObjects.add(currentObject);

         allLinks.addAll(currentObject.getOutgoingLinks());
         allLinks.addAll(currentObject.getIncommingLinks());

         todoObjects.addAll(currentObject.getOutgoingLinks().getTgt().minus(allObjects));
         todoObjects.addAll(currentObject.getIncommingLinks().getSrc().minus(allObjects));
      }

      // now derive classes from node types
      for (GenericObject currentObject : allObjects)
      {
         if (currentObject.getType() != null)
         {
            Clazz currentClazz = this.getOrCreateClazz(packageName + "." + currentObject.getType());

            // add attribute declarations
            for (GenericAttribute attr : currentObject.getAttrs())
            {
               Attribute attrDecl = currentClazz.getOrCreateAttribute(attr.getName(), DataType.OBJECT);

               learnAttrType(attr, attrDecl);
            }
         }
      }

      LinkedHashSet<String> alreadyUsedLabels = new LinkedHashSet<String>();

      // now derive assocs from links
      for (GenericLink currentLink : allLinks)
      {
         String sourceType = currentLink.getSrc().getType();
         if (sourceType == null) continue;

         String targetType = currentLink.getTgt().getType();
         if (targetType == null) continue;

         String sourceLabel = currentLink.getSrcLabel(); 
         if (sourceLabel == null)
         {
            sourceLabel = StrUtil.downFirstChar(sourceType) + "s";
         }

         String targetLabel = currentLink.getTgtLabel(); 
         if (targetLabel == null)
         {
            targetLabel = StrUtil.downFirstChar(sourceType) + "s";
         }

         // search for an assoc with similar srcClazz, srcLabel, tgtClass, tgtLabel
         Association currentAssoc = null; 
         for (Association assoc : this.getAssociations())
         {
            if (sourceType.equals(CGUtil.shortClassName(assoc.getSource().getClazz().getName()))
                  && targetType.equals(CGUtil.shortClassName(assoc.getTarget().getClazz().getName()))
                  && sourceLabel.equals(assoc.getSource().getName())
                  && targetLabel.equals(assoc.getTarget().getName()))
            {
               // found old one
               currentAssoc = assoc; 

               break;
            }
         }

         if (currentAssoc == null)
         {
            // need to create a new one
            currentAssoc = new Association()
            .withSource(sourceLabel, this.getOrCreateClazz(packageName + "." + sourceType), Card.ONE)
            .withTarget(targetLabel, getOrCreateClazz(packageName + "." + targetType), Card.ONE);
            this.addToAssociations(currentAssoc);
         }

         if (alreadyUsedLabels.contains(currentLink.getSrc().hashCode() + ":" + targetLabel))
         {
            currentAssoc.getTarget().setCard(Card.MANY.toString());
         }

         if (alreadyUsedLabels.contains(currentLink.getTgt().hashCode() + ":" + sourceLabel))
         {
            currentAssoc.getSource().setCard(Card.MANY.toString());
         }

         alreadyUsedLabels.add(currentLink.getSrc().hashCode() + ":" + targetLabel);
         alreadyUsedLabels.add(currentLink.getTgt().hashCode() + ":" + sourceLabel);
      }

      return this.getModel();
   }

   private void learnAttrType(GenericAttribute attr, Attribute attrDecl)
   {
      String valueString = attr.getValue();

      DataType attrType = DataType.STRING;
      try
      {
         Integer.parseInt(valueString);

         attrType = DataType.INT;
      }
      catch (NumberFormatException e)
      {
         try
         {
            Double.parseDouble(valueString);

            attrType = DataType.DOUBLE;
         }
         catch (NumberFormatException e1)
         {
            try
            {
               DateFormat.getDateInstance().parse(valueString);

               attrType = DataType.ref("java.util.Date");
            }
            catch (ParseException e2)
            {
               try
               {
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss");
                  simpleDateFormat.parse(valueString);

                  attrType = DataType.ref("java.util.Date");
               }
               catch (ParseException e3)
               {
               }
            }
         }
      }

      String typeOrder = "Object int double java.util.Date String";

      if (typeOrder.indexOf(attrDecl.getType().getValue()) < typeOrder.indexOf(attrType.getValue()))
      {
         attrDecl.setType(attrType);
      }
   }

   
   private boolean compareRoles(Role source, Role target, Association assoc)
   {
      return compareRoles(assoc.getSource(), source) && compareRoles(assoc.getTarget(), target);
   }

   private boolean compareRoles(Role first, Role second)
   {
      Clazz firstClass = first.getClazz();
      String firstName = first.getName();
      String firstCard = first.getCard();
      Clazz secondClass = second.getClazz();
      String secondName = second.getName();
      String secondCard = second.getCard();
      if (firstClass == secondClass && firstName.equals(secondName) && firstCard.equals(secondCard))
         return true;
      return false;
   }

   private Clazz findPartnerClass(String partnerTypeName)
   {

      String partnerClassName = findPartnerClassName(partnerTypeName);

      // System.err.println("type note found : " + partnerTypeName);
      return findClass(partnerClassName);
   }

   public Clazz findClass(String partnerClassName) {
      for (Clazz partnerClass : model.getClasses())
      {
         String shortClassName = CGUtil.shortClassName(partnerClass.getFullName());
         if (partnerClassName.equals(shortClassName))
         {
            return partnerClass;
         }
      }
      return null;
   }

   public String findPartnerClassName(String partnerTypeName)
   {
      String partnerClassName;
      int openAngleBracket = partnerTypeName.indexOf("<");
      int closeAngleBracket = partnerTypeName.indexOf(">");

      if (openAngleBracket > -1 && closeAngleBracket > openAngleBracket)
      {
         partnerClassName = partnerTypeName.substring(openAngleBracket + 1, closeAngleBracket);
      }
      else if (partnerTypeName.endsWith("Set"))
      {
         // TODO: should check for superclass ModelSet
         partnerClassName = partnerTypeName.substring(0, partnerTypeName.length() - 3);
      }
      else
      {
         partnerClassName = partnerTypeName;
      }
      return partnerClassName;
   }

   private void findAndRemoveMethods(Clazz clazz, String memberName, String prefix)
   {
      String name = StrUtil.upFirstChar(memberName);
      String[] split = prefix.split(" ");
      for (String post : split)
      {
         Method method = findMethod(clazz, post + name + "(");
         if (method != null)
         {
            clazz.removeFromMethods(method);
         }
      }
   }

   private Method findMethod(Clazz clazz, String name)
   {
      LinkedHashSet<Method> methods = clazz.getMethods();
      for (Method method : methods)
      {
         if (method.getSignature().contains(name))
            return method;
      }
      return null;
   }

   // ==========================================================================

   private ArrayList<File> searchForJavaFiles(String path)
   {
      ArrayList<File> javaFiles = new ArrayList<File>();
      File file = new File(path);
      if (file.exists() && file.isDirectory())
      {
         File[] listFiles = file.listFiles(new FilenameFilter()
         {
            @Override
            public boolean accept(File file, String string)
            {
               return string.toLowerCase().endsWith(".java");
            }
         });
         Collections.addAll(javaFiles, listFiles);
         File[] directory = file.listFiles(new FilenameFilter()
         {
            @Override
            public boolean accept(File file, String string)
            {
               return file.isDirectory();
            }
         });
         for (File dir : directory)
         {
            javaFiles.addAll(searchForJavaFiles(dir.getPath()));
         }
      }
      return javaFiles;
   }
   
   @Deprecated
   public void updateFromCode(String noLongerUsed, String includePathes, String packages)
   {
      updateFromCode(includePathes, packages);
   }

   public void updateFromCode(String includePathes, String packages){
      String binDir = getClass().getClassLoader().getResource(".").getPath();
      File binFolder = new File(binDir);
      File srcFolder = binFolder.getParentFile();
      
      updateFromCode(includePathes, packages, srcFolder);
   }

   /*
    * usage for maven project 
    * model.updateFromCode("java", "org.package.name", new File((new File(this.getClass().getResource(".").getPath())).getParentFile().getParent() + "/src/main/" ));
    */

   public void updateFromCode(String includePathes, String packages, File srcFolder)
   {
      // find java files in parent directory

      if (srcFolder != null)
      {
         ArrayList<File> javaFiles = searchForJavaFiles(includePathes, packages, srcFolder);
         addJavaFilesToClasses(packages, srcFolder, javaFiles);

         if (model.getClasses().isEmpty()) {
            System.out.println("no class files found !!!! END");
            return;
         }        

         // parse each java file
         for (Clazz clazz : (ClazzSet) model.getClasses().clone())
         {
            handleMember(clazz, getOrCreate(clazz).getFilePath());
         }
      }

      // add model creation code at invocation place, if not yet there
   }

   private Clazz handleMember(Clazz clazz, String rootDir)
   {
      System.out.println("parse " + clazz.getFullName());
      Parser parser = getOrCreate(clazz).getOrCreateParser(rootDir);
      parser.indexOf(Parser.CLASS_END);

      // set class or interface
      if (Parser.INTERFACE.equals(parser.getClassType()))
      {
         clazz.setInterfaze(true);
      }

      LinkedHashMap<String, SymTabEntry> symTab = new LinkedHashMap<String, SymTabEntry>();
      for (String key : parser.getSymTab().keySet())
      {
         symTab.put(key, parser.getSymTab().get(key));
      }


      for (String memberName : symTab.keySet())
      {
         addMemberToModel(clazz, parser, memberName);
      }

      return clazz;
   }

   private void findAndRemoveAttributs(Clazz clazz, String names) { 
      String[] split = names.split(" ");
      for (String attrName : split)
      {
         Attribute attr = findAttribute(clazz, attrName);
         if (attr != null)
         {
            clazz.removeFromAttributes(attr);
         }
      }

   }

   private Attribute findAttribute(Clazz clazz, String attrName) {
      LinkedHashSet<Attribute> attrs = clazz.getAttributes();
      for (Attribute attr : attrs)
      {
         if (attr.getName().equals(attrName))
            return attr;
      }
      return null;
   }

   private void handleAssoc(Clazz clazz, String rootDir, String memberName, Card card, String partnerClassName, Clazz partnerClass, String partnerAttrName)
   {
      partnerAttrName = StrUtil.downFirstChar(partnerAttrName);
      Parser partnerParser = getOrCreate(partnerClass).getOrCreateParser(rootDir); 
      String searchString = Parser.ATTRIBUTE + ":" + partnerAttrName;

      int attributePosition = partnerParser.indexOf(searchString);

      if (attributePosition > -1)
      {
         Card partnerCard = findRoleCard(partnerParser, searchString);
         tryToCreateAssoc(clazz, memberName, card, partnerClassName, partnerClass, partnerAttrName, partnerCard);
      }
   }

   private void addMemberToModel(Clazz clazz, Parser parser, String memberName)
   {
      // add new methods
      if (memberName.startsWith(Parser.METHOD))
      {
         addMemberAsMethod(clazz, memberName, parser.getSymTab().get(memberName));
      }
      // add new attributes
      else if (memberName.startsWith(Parser.ATTRIBUTE))
      {
         String[] split = memberName.split(":");
         String attrName = split[1];
         SymTabEntry symTabEntry = parser.getSymTab().get(memberName);
         addMemberAsAttribut(clazz, attrName, symTabEntry);
      }

      // add super classes 
      if (memberName.startsWith(Parser.EXTENDS))
      {
         if (clazz.isInterface()) {
            addMemberAsInterface(clazz, memberName, parser);
         }
         else {
            addMemberAsSuperClass(clazz, memberName, parser);
         }
      }
      else if (memberName.startsWith(Parser.IMPLEMENTS))
      {
         addMemberAsInterface(clazz, memberName, parser);
      }

   }

   private void addMemberAsAttribut(Clazz clazz, String attrName, SymTabEntry symTabEntry)
   {
      // filter public static final constances
      String modifiers = symTabEntry.getModifiers();
      if ((modifiers.indexOf("public") >= 0 || modifiers.indexOf("private") >= 0) && modifiers.indexOf("static") >= 0 && modifiers.indexOf("final") >= 0)
      {
         // ignore
         return;
      }

      String type = symTabEntry.getType();
      // include arrays
      type = type.replace("[]", "");

      if (CGUtil.isPrimitiveType(type))
      {

         if (!classContainsAttribut(clazz, attrName, symTabEntry.getType()))
         {
            new Attribute().withClazz(clazz).withName(attrName).withType(DataType.ref(symTabEntry.getType()));
         }
      }
   }

   private void addMemberAsSuperClass(Clazz clazz, String memberName, Parser parser)
   {
      Clazz memberClass = findMemberClass(clazz, memberName, parser);

      if (memberClass != null)
         clazz.withSuperClass(memberClass);
   }

   private void addMemberAsInterface(Clazz clazz, String memberName, Parser parser)
   {
      Clazz memberClass = findMemberClass(clazz, memberName, parser);
      
      if (memberClass != null) 
      {
         memberClass.withInterfaze(true);

         clazz.withSuperClass(memberClass);
      }
   }

   private Clazz findMemberClass(Clazz clazz, String memberName, Parser parser)
   {
      String[] split = memberName.split(":");
      String signature = split[1];

      LinkedHashMap<String, SymTabEntry> symTab = parser.getSymTab();

      for (String key : symTab.keySet())
      {
         String importName = symTab.get(key).getMemberName();

         if (key.startsWith(Parser.IMPORT + ":") && importName.endsWith(signature))
         {
            Clazz modelClass = findClassInModel(importName);

            if (modelClass != null)
            {
               return modelClass;
            }
            else
            {
               Clazz externClass = model.createClazz(importName).withExternal(true);
               return externClass;
            }
         }
         else if (key.startsWith(Parser.IMPORT + ":") && importName.endsWith("*"))
         {
            // might work
            importName = importName.substring(0, importName.length() - 1) + signature;
            
            Clazz modelClass = findClassInModel(importName);

            if (modelClass != null)
            {
               return modelClass;
            }
         }
      }
      
      String name = clazz.getFullName();
      int lastIndex = name.lastIndexOf('.');
      name = name.substring(0, lastIndex + 1) + signature;

      return findClassInModel(name);
   }

   private Clazz findClassInModel(String name)
   {
      LinkedHashSet<Clazz> classes = model.getClasses();

      for (Clazz eClazz : classes) {
         if (eClazz.getFullName().equals(name)) {
            return eClazz;
         }
      }

      return null;
   }



   private void addMemberAsMethod(Clazz clazz, String memberName, SymTabEntry symTabEntry)
   {
      String fullSignature = symTabEntry.getType();
      String[] split = fullSignature.split(":");
      String signature = split[1];

      // filter internal generated methods
      String filterString = "get(String) set(String,Object) getPropertyChangeSupport() removeYou()" +
            " addPropertyChangeListener(PropertyChangeListener) removePropertyChangeListener(PropertyChangeListener)" +
            " addPropertyChangeListener(String,PropertyChangeListener) removePropertyChangeListener(String,PropertyChangeListener)";

      if (filterString.indexOf(signature) < 0 && isNewMethod(signature, clazz))
      {
         new Method()
         .withClazz(clazz);
//FIXME MONTAG mit Alex         .withSignature(signature)
//         .withReturnType(split[2]);
      }
   }

   private boolean isNewMethod(String memberName, Clazz clazz)
   {
      for ( Method method : clazz.getMethods() )
      {
         if ( method.getSignature().equals(memberName) )
            return false;
      }

      return true;
   }

   private ArrayList<File> searchForJavaFiles(String includePathes, String packageString, File srcFolder)
   {
      ArrayList<File> javaFiles = new ArrayList<File>();
      String[] packages = packageString.split("\\s+");
      for (String pAckage :packages) {
         String packagepath = pAckage.replace('.', '/');
         String[] includes = includePathes.split("\\s+");
         for (String include : includes) {
            String newPath = srcFolder.getPath() + "/" + include + "/" + packagepath;
            javaFiles.addAll(searchForJavaFiles(newPath));
         }
      }
      return javaFiles;
   }

   private void addJavaFilesToClasses(String packageString, File srcFolder, ArrayList<File> javaFiles)
   {
      String[] packages = packageString.split("\\s+"); 

      for (File file : javaFiles)
      {
         String filePath = file.getAbsolutePath();
         filePath = filePath.replace(srcFolder.getAbsolutePath(), "");
         filePath = filePath.replace(File.separatorChar, '.').substring(1);
         filePath = filePath.substring(0, filePath.length() - 5);
         addClassToClasses(filePath, packages); 
      }
   }

   private void addClassToClasses(String filePath, String[] packages) 
   {
      // split off source folder
      int pos = filePath.indexOf('.');
      String rootDir = filePath.substring(0, pos);
      filePath = filePath.substring(pos + 1);
      if (commonPrefix(filePath, packages))
      {
         if (!classExists(filePath))
         {
            Clazz clazz = new Clazz(filePath);
            getOrCreate(clazz).withFilePath(rootDir);
            model.getClasses().add(clazz);
         }
         return;
      }
   }

   private boolean commonPrefix(String filePath, String[] packages)
   {
      for (String p : packages)
      {
         if (filePath.startsWith(p))
         {
            return true;
         }
      }
      return false;
   }

   private String findSetterPrefix(String partnerTypeName)
   {
      int openAngleBracket = partnerTypeName.indexOf("<");
      int closeAngleBracket = partnerTypeName.indexOf(">");

      if ((openAngleBracket > -1 && closeAngleBracket > openAngleBracket) || partnerTypeName.endsWith("Set"))
      {
         return "addTo";
      }
      return "set";
   }

   private Card findRoleCard(Parser partnerParser, String searchString)
   {
      String partnerTypeName;
      SymTabEntry partnerSymTabEntry = partnerParser.getSymTab().get(searchString);
      partnerTypeName = partnerSymTabEntry.getType();

      return findRoleCard(partnerTypeName);
   }

   private Card findRoleCard(String partnerTypeName)
   {
      Card partnerCard = Card.ONE;
      int _openAngleBracket = partnerTypeName.indexOf("<");
      int _closeAngleBracket = partnerTypeName.indexOf(">");
      if (_openAngleBracket > -1 && _closeAngleBracket > _openAngleBracket)
      {
         // partner to many
         partnerCard = Card.MANY;
      }
      else if (partnerTypeName.endsWith("Set") && partnerTypeName.length() > 3)
      {
         // it might be a ModelSet. Look if it starts with a clazz name
         String prefix = partnerTypeName.substring(0, partnerTypeName.length() - 3);
         for (Clazz clazz : model.getClasses())
         {
            if (prefix.equals(CGUtil.shortClassName(clazz.getFullName())))
            {
               partnerCard = Card.MANY;
               break;
            }
         }
      }
      return partnerCard;
   }

   private void tryToCreateAssoc(Clazz clazz, String memberName, Card card, String partnerClassName, Clazz partnerClass, String partnerAttrName, Card partnerCard)
   {
      Role sourceRole = new Role().withName(partnerAttrName).withClazz(clazz).withCard(partnerCard.toString());

      Role targetRole = new Role().withName(memberName).withClazz(partnerClass).withCard(card.toString());

      if (!assocWithRolesExists(sourceRole, targetRole))
      {
         new Association().withSource(sourceRole).withTarget(targetRole);

         clazz.addToRoles(sourceRole);
         partnerClass.addToRoles(targetRole);
      }
   }

   private boolean classContainsAttribut(Clazz clazz, String attrName, String type)
   {
      for (Attribute attr : clazz.getAttributes())
      {
         if (attrName.equals(attr.getName()) && type.equals(attr.getType()))
            return true;
      }
      return false;
   }

   private boolean classExists(String filePath)
   {
      for (Clazz clazz : model.getClasses())
      {
         if (clazz.getFullName().equals(filePath))
            return true;
      }
      return false;
   }

   private boolean assocWithRolesExists(Role source, Role target)
   {
      for (Association assoc : getAssociations())
      {
         if (compareRoles(source, target, assoc) || compareRoles(target, source, assoc))
            return true;
      }
      return false;
   }
   private boolean hasAttribute(Attribute attribute, LocalVarTableEntry entry)
   {
      String name = attribute.getName();
      String type = attribute.getType().getValue();
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();

      for (ArrayList<String> sequencePart : initSequence)
      {
         if ("withAttribute".equals(sequencePart.get(0)))
         {
            String sequencePartName = sequencePart.get(1).replace("\"", "");
            String sequencePartType = sequencePart.get(2).replace("\"", "");
            if (StrUtil.stringEquals(name, sequencePartName)) 
               // check only for attr name, user may have changed attr type, do not overwrite this. 
               //  && StrUtil.stringEquals(type, sequencePartType))
               return true;
         }
         else if ("model.createClazz".equals(sequencePart.get(0)))
         {
            // initialization with varargs
            int pos = 2;
            while (pos + 1 < sequencePart.size())
            {
               String attrName = sequencePart.get(pos).replace("\"", "");
               String typeName = sequencePart.get(pos + 1).replace("\"", "");

               if (StrUtil.stringEquals(name, attrName))
               {
                  return true;
               }

               pos += 2;
            }

         }
      }

      // there may be separate clazz.withAttributes(...) statements
      String withAttrCall = entry.getName() + ".withAttributes";
      String attrNameQuoted = "\"" +  name + "\"";
      for (StatementEntry stat : getOrCreate(attribute.getClazz()).getParser().getCurrentStatement().getParent().getBodyStats())
      {
         String firstToken = stat.getTokenList().get(0);
         if (StrUtil.stringEquals(withAttrCall, firstToken))
         {
            int i = 2;
            while (i < stat.getTokenList().size())
            {
               if (StrUtil.stringEquals(attrNameQuoted, stat.getTokenList().get(i)))
               {
                  // this attribute is already created in model creation code
                  return true;
               }
               i ++;
            }
         }
      }

      return false;
   }
   public void removeAllGeneratedCode(String rootDir, String srcDir, String helpersDir)
   {
      turnRemoveCallToComment(rootDir);

      // now remove class file, creator file, and modelset file for each class and the CreatorCreator
      String packageName = null;
      for (Clazz clazz : model.getClasses())
      {
         try
         {
            removeAllCodeForClass(srcDir, helpersDir, clazz);
         }
         catch (Exception e)
         {

         }
      }
   }

   public void turnRemoveCallToComment(String rootDir)
   {
      int codeLineNumber = -1;
      String className = null;

      // first find the call to this method and make it a comment, to avoid undesired execution on later runs. 
      try
      {
         throw new RuntimeException();
      }
      catch (Exception e)
      {
         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[2];
         codeLineNumber  = callEntry.getLineNumber();

         className = callEntry.getClassName();
      }

      // open java file and find code line
      String fileName = rootDir + "/" + className.replaceAll("\\.", "/") + ".java";
      File file = new File(fileName);

      if (file.exists())
      {
         try
         {
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line = "";
            int lineNo = 0;

            StringBuilder buf = new StringBuilder();

            while (true)
            {
               line = in.readLine();

               if (line == null) break;

               lineNo++;

               if (lineNo == codeLineNumber && line.indexOf("//") == -1)
               {
                  int pos = 0;
                  while (pos < line.length() && Character.isWhitespace(line.charAt(pos)))
                  {
                     pos++;
                  }

                  line = line.substring(0, pos) + "// " + line.substring(pos);   
               }
               buf.append(line).append('\n');
            }

            in.close();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(buf.toString());
            fileWriter.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   public void removeAllCodeForClass(String srcDir, String helpersDir,
         Clazz clazz)
   {
      String className;
      String fileName;
      String packageName;
      className = clazz.getFullName();
      int pos = className.lastIndexOf('.');
      packageName = className.substring(0, pos);

      // class file
      fileName = srcDir + "/" + className.replaceAll("\\.", "/") + ".java";
      deleteFile(fileName);

      String path = helpersDir + "/" + (packageName+UTILPATH).replaceAll("\\.", "/") + "/";
      
      // creator file
      fileName = path + CGUtil.shortClassName(className) + "Creator.java";
      deleteFile(fileName);

      // modelset file
      fileName = path + CGUtil.shortClassName(className) + "Set.java";
      deleteFile(fileName);

      // pattern object file
      fileName = path + CGUtil.shortClassName(className) + "PO.java";
      deleteFile(fileName);

      // pattern object creator file
      fileName = path + CGUtil.shortClassName(className) + "POCreator.java";
      deleteFile(fileName);

      // model pattern file
      fileName = path + "ModelPattern.java";
      deleteFile(fileName);

      // CreatorCreator in that package
      fileName = path + "CreatorCreator.java";
      deleteFile(fileName);
   }

   private void deleteFile(String fileName)
   {
      File file;
      file = new File(fileName);

      if (file.exists())
      {
         file.delete();
      }
   }

//   public void setFileHasChanged(boolean value)
//   {
//      this.fileHasChanged = value;
//   }

   public void setModelPatternFileHasChanged(boolean value)
   {
      this.modelPatternFileHasChanged = value;
   }
}
