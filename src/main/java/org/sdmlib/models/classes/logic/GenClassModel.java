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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.relation.Role;
import javax.smartcardio.Card;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.codegen.util.StatementEntrySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.GraphMember;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Parameter;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class GenClassModel implements ClassModelAdapter
{
   public static final String UTILPATH = ".util";
   ClassModel model;
   private LinkedHashMap<String, Clazz> handledClazzes = new LinkedHashMap<String, Clazz>();
   private AssociationSet associations = null;
   private HashMap<GraphMember, Generator<?>> generators = new HashMap<GraphMember, Generator<?>>();
   private DIFF showDiff = DIFF.NONE;
   private List<String> ignoreDiff;

   public enum DIFF
   {
      NONE, DIFF, FULL
   };

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

   public GenClass getOrCreate(Clazz clazz)
   {
      if (generators.containsKey(clazz))
      {
         return (GenClass) generators.get(clazz);
      }
      Generator<Clazz> gen = new GenClass().withModel(clazz);
      generators.put(clazz, gen);
      return (GenClass) gen;
   }
   
   @Override
   public GenClass getClazz(String name) {
	   for(Iterator<Entry<GraphMember, Generator<?>>> iterator = generators.entrySet().iterator();iterator.hasNext();){
		   Entry<GraphMember, Generator<?>> item = iterator.next();
		   if(item.getKey().getName().equals(name)) {
			   return (GenClass)item.getValue();
		   }
	   }
	   return null;
   }


   public GenMethod getOrCreate(Method method)
   {
      if (generators.containsKey(method))
      {
         return (GenMethod) generators.get(method);
      }
      Generator<Method> gen = new GenMethod().withModel(method);
      generators.put(method, gen);
      return (GenMethod) gen;
   }

   public GenAttribute getOrCreate(Attribute attribute)
   {
      if (generators.containsKey(attribute))
      {
         return (GenAttribute) generators.get(attribute);
      }
      Generator<Attribute> gen = new GenAttribute().withModel(attribute);
      generators.put(attribute, gen);
      return (GenAttribute) gen;
   }

   public GenAnnotation getOrCreate(Annotation annotation)
   {
      if (generators.containsKey(annotation))
      {
         return (GenAnnotation) generators.get(annotation);
      }
      Generator<Annotation> gen = new GenAnnotation().withModel(annotation);
      generators.put(annotation, gen);
      return (GenAnnotation) gen;
   }

   public GenAssociation getOrCreate(Association association)
   {
      if (generators.containsKey(association))
      {
         return (GenAssociation) generators.get(association);
      }
      Generator<Association> gen = new GenAssociation().withModel(association);
      generators.put(association, gen);
      return (GenAssociation) gen;
   }

   public GenRole getOrCreate(Role role)
   {
      if (generators.containsKey(role))
      {
         return (GenRole) generators.get(role);
      }
      Generator<Role> gen = new GenRole().withModel(role);
      generators.put(role, gen);
      return (GenRole) gen;
   }

   public boolean generate(String rootDir)
   {
      resetParsers();

      fixClassModel();

      addHelperClassesForUnknownAttributeTypes();

      for (Enumeration enumeration : model.getEnumerations())
      {
         getOrCreate(enumeration).generate(rootDir, rootDir);
      }

      for (Clazz clazz : model.getClasses())
      {
         getOrCreate(clazz).generate(rootDir, rootDir);
      }
      for (Association assoc : getAssociations())
      {
         getOrCreate(assoc).generate(rootDir, rootDir);
      }

      Exception e = new RuntimeException();

      attributNameConsistenceCheck(e, rootDir);

      // Write all
      if (getShowDiff() != DIFF.NONE)
      {
         int count = 0;
         for (Clazz clazz : model.getClasses())
         {
            count += getOrCreate(clazz).printAll(getShowDiff(), this.ignoreDiff);
         }
         System.out.println("Totalchanges of all Files: " + count);
      }

      // perhabs there is already generated and compiled code from previous run.
      // Try to do coverage of model code
      this.doCoverageOfModelCode();
      
      this.addJavadocReferences(rootDir);

      return true;
   }

   private void addJavadocReferences(String rootDir)
   {
      // find method that called ClassModel.generate()
      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = null;

      // find first method above ClassModel.generate()
      int i = 1;

      while (true)
      {
         callEntry = stackTrace[i];

         if (callEntry.getClassName().equals(GenClassModel.class.getName())
               || callEntry.getClassName().equals(ClassModel.class.getName()))
         {
            i++;
            continue;
         }
         else
         {
            break;
         }
      }
      
      String classModelConstructionClass = callEntry.getClassName();
      

      // search for a subdirectory containing the javaTestFile of the
      // execution directory and search for the subdi
      File projectDir = new File(".");
      
      Storyboard story = new Storyboard();
      
      story.setJavaTestFileName(classModelConstructionClass.replaceAll("\\.", "/") + ".java");
      
      if (story.searchDirectoryTree(projectDir))
      {
         // found it
         // add javadoc references to all generated model classes
         for (Clazz clazz : this.model.getClasses())
         {
            if (clazz.isExternal()) continue;
            
            String fullClazzFileName = rootDir + "/" + clazz.getFullName().replaceAll("\\.", "/") + ".java";
            
            story.addReferenceToJavaDoc(fullClazzFileName, Parser.CLASS + ":" + CGUtil.shortClassName(clazz.getName()), story.getJavaTestFileName().substring(3));
         }
         System.out.println();
      }
   }

   private void fixClassModel()
   {
      Clazz[] classes = model.getClasses().toArray(new Clazz[model.getClasses().size()]);
      HashSet<Clazz> visited = new HashSet<Clazz>();
      for (Clazz item : classes)
      {
         fixClassModel(item, visited);
      }
   }

   private void fixClassModel(Clazz item, HashSet<Clazz> visited)
   {
      for (Clazz entity : item.getInterfaces())
      {
         if (entity.getClassModel() == null)
         {
            if (visited.add(entity))
            {
               fixClassModel(entity, visited);
            }
            entity.with(model);
         }
      }

      for (Clazz entity : item.getSuperClazzes())
      {
         if (entity.getClassModel() == null)
         {
            if (visited.add(entity))
            {
               fixClassModel(entity, visited);
            }
            entity.with(model);
         }
      }

      for (Clazz entity : item.getKidClazzes())
      {
         if (entity.getClassModel() == null)
         {
            entity.with(model);
            if (visited.add(entity))
            {
               fixClassModel(entity, visited);
            }
         }
      }

      for (Role role : item.getRoles())
      {
         Clazz clazz = role.getPartnerRole().getClazz();
         if (clazz.getClassModel() == null)
         {
            clazz.with(model);
            if (visited.add(clazz))
            {
               fixClassModel(clazz, visited);
            }
         }
         this.addToAssociations(role.getAssoc());
      }
   }
   
   public void addHelperClassesForUnknownAttributeTypes()
   {
      // for attribute types like java.util.Date we add a class with that name
      // and mark it as wrapped. This generates the required DateSet class.
      for (DataType item : this.model.getClasses().getAttributes().getType())
      {
         String typeName = item.getValue();
         // seems to be a non trivial type. We should have a clazz with that
         // type

         // it may be an instance of a generic type like ArrayList<String>, cut
         // off generic part
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

         if (!CGUtil.isPrimitiveType(typeName))
         {

            Clazz clazz = this.model.getClazz(typeName);

            if (clazz == null)
            {
               // class is missing, create it.
               if (typeName.indexOf(".") >= 0)
               {
                  model.createClazz(typeName).withExternal(true);
               }
               else
               {
                  model.createClazz(typeName);
               }
            }
         }
      }
   }

   private void attributNameConsistenceCheck(Exception e, String rootDir)
   {

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

      // FIXME ALEX SCHAUEN
      Clazz modelCreationClass = new ClassModel().withName(".").getGenerator().getOrCreateClazz(className);

      Parser parser = getOrCreate(modelCreationClass).getOrCreateParser(rootDir);
      parser.indexOf(Parser.CLASS_END);
      String signature = "method:" + methodName + "(";
      ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(signature);
      SymTabEntry symTabEntry = null;

      for (SymTabEntry symTabEnt : symTabEntries)
      {
         long startPos = parser.getLineIndexOf(symTabEnt.getStartPos());
         long endPos = parser.getLineIndexOf(symTabEnt.getEndPos());

         if (startPos <= callMethodLineNumber && endPos >= callMethodLineNumber)
         {
            symTabEntry = symTabEnt;
         }
      }
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = null;

      if (symTabEntry != null)
      {
         parser.parseMethodBody(symTabEntry);
         localVarTable = parser.getLocalVarTable();
      }

      for (Clazz clazz : model.getClasses())
      {
         HashMap<String, Object> attributs = new HashMap<String, Object>();
         HashSet<Object> duplicateEntries = new HashSet<Object>();
         for (Attribute attr : clazz.getAttributes())
         {
            String name = attr.getName();
            if (attributs.containsKey(name))
            {
               duplicateEntries.add(attr);
               duplicateEntries.add(attributs.get(name));
            }
            attributs.put(name, attr);
         }

         RoleSet roles = clazz.getRoles().getOtherRoles();

         for (Role role : roles)
         {
            String name = role.getName();
            if (name == null)
            {
               name = "";
            }
            if (name.equals(role.getPartnerRole().getName()))
            {
               // no problem,
               continue;
            }
            if (name.equals(""))
            {
               // uni directional assoc, name is not used, no problem
               continue;
            }
            if (attributs.containsKey(name))
            {
               duplicateEntries.add(role);
               duplicateEntries.add(attributs.get(name));
            }
            attributs.put(name, role);
         }

         for (Object object : duplicateEntries)
         {

            if (object instanceof Attribute)
            {
               Attribute attribute = (Attribute) object;
               String position = defPositionAsString(parser, localVarTable, attribute.getName(), attribute.getType(),
                     attribute.getClazz().getFullName());

               System.out.println("in " + fileName + ":  duplicate name found in definition for attribute"
                     + attribute.getClazz() + "\n    " + position + "\n     Attribute " + attribute);
               attribute.removeYou();
            }
            else if (object instanceof Role)
            {
               Role role = (Role) object;
               String position = defPositionAsString(parser, localVarTable, role.getName(), DataType.ref("NONE"), role
                     .getClazz().getFullName());
               System.out.println("in " + fileName + "  duplicate name found in definition for " + role.getClazz()
                     + "\n    " + position + "\n      Role " + role);
               System.out.println(parser.getLineForPos((int) defPosition(parser, localVarTable, role.getName(),
                     DataType.ref("NONE"), role.getClazz().getFullName())));
               Association assoc = role.getAssoc();

               if (assoc != null)
                  assoc.removeYou();

               role.removeYou();
            }
         }
      }
   }

   private String defPositionAsString(Parser parser, LinkedHashMap<String, LocalVarTableEntry> localVarTable,
         String name, DataType attrType, String clazzType)
   {
      String position = "";

      if (localVarTable != null)
      {

         for (LocalVarTableEntry entry : localVarTable.values())
         {

            if (entry.getType() != null && entry.getType().equals("Clazz"))
            {
               String type = entry.getInitSequence().get(0).get(1).replace("\"", "");

               if (clazzType.endsWith(type))
               {
                  CharSequence subSequence = parser.getText().subSequence(entry.getStartPos(), entry.getEndPos() + 1);
                  String subSequenceString = subSequence.toString();

                  Pattern fileName = Pattern.compile("\"" + name + "\"\\s*,\\s*\"" + attrType + "\"");
                  Matcher matcher = fileName.matcher(subSequenceString);

                  int indexOf = 0;
                  while (matcher.find())
                  {
                     String group = matcher.group(0);
                     indexOf = subSequenceString.indexOf(group);
                  }
                  position = "at line " + parser.getLineIndexOf(entry.getStartPos() + indexOf) + "    ";
               }
            }
         }
      }
      return position;
   }

   private long defPosition(Parser parser, LinkedHashMap<String, LocalVarTableEntry> localVarTable, String name,
         DataType attrType, String clazzType)
   {
      long position = 0;

      if (localVarTable != null)
      {

         for (LocalVarTableEntry entry : localVarTable.values())
         {

            if (entry.getType() != null && entry.getType().equals("Clazz"))
            {
               String type = entry.getInitSequence().get(0).get(1).replace("\"", "");

               if (clazzType.endsWith(type))
               {
                  CharSequence subSequence = parser.getText().subSequence(entry.getStartPos(), entry.getEndPos() + 1);
                  String subSequenceString = subSequence.toString();

                  Pattern fileName = Pattern.compile("\"" + name + "\"\\s*,\\s*\"" + attrType + "\"");
                  Matcher matcher = fileName.matcher(subSequenceString);

                  int indexOf = 0;
                  while (matcher.find())
                  {
                     String group = matcher.group(0);
                     indexOf = subSequenceString.indexOf(group);
                  }
                  position = entry.getStartPos() + indexOf;
               }
            }
         }
      }
      return position;
   }

   private void writeToFile(Clazz modelCreationClass)
   {
      getOrCreate(modelCreationClass).printFile();
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
         // ClassModel oldValue = this.model;
         // if (this.model != null)
         // {
         // this.model = null;
         // oldValue.setGenerator(null);
         // }
         this.model = value;
         // if (value != null)
         // {
         // value.setGenerator(this);
         // }
      }
   }

   public Clazz getOrCreateClazz(String className)
   {
      for (Clazz clazz : getModel().getClasses())
      {
         if (StrUtil.stringEquals(clazz.getFullName(), className))
         {
            return clazz;
         }
      }

      Clazz clazz = getModel().createClazz(className);

      return clazz;
   }

   public ClassModel getModel()
   {
      return model;
   }

   public void insertModelCreationCodeHere(String rootDir, String newMethod)
   {
      StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();

      //      StackTraceElement firstStackTraceElement = stackTrace[0];
      //      String callMethodName = firstStackTraceElement.getMethodName();
      StackTraceElement secondStackTraceElement = stackTrace[1];
      String className = secondStackTraceElement.getClassName();
      insertModelCreationCodeHere(rootDir, className, newMethod);
   }

   public void insertModelCreationCodeHere(String rootDir, String className, String newMethod)
   {
      Clazz modelCreationClass = getOrCreateClazz(className);
      modelCreationClass.getClassModel().without(modelCreationClass);
      GenClass modelCreationGenerator = getOrCreate(modelCreationClass);
      Parser modelCreationParser = modelCreationGenerator.getOrCreateParser(rootDir);
      modelCreationParser.indexOf(Parser.CLASS_END);

      String signature = Parser.METHOD + ":" + newMethod + "(";
      ArrayList<SymTabEntry> symTabEntriesFor = modelCreationParser.getSymTabEntriesFor(signature);
      int currentInsertPos = modelCreationParser.indexOf(Parser.CLASS_BODY);

      if (symTabEntriesFor.size() < 1)
      {

         // int currentInsertPos = symTabEntry.getEndPos() + 2;
         currentInsertPos = modelCreationParser.insert(currentInsertPos, "      @Test\n      public void " + newMethod
               + "() {\n"
               + "      	ClassModel clazzModel = new ClassModel(\"" + model.getName() + "\");\n");
         modelCreationParser.insert(currentInsertPos, "      }\n");

      }
      else
      {
         SymTabEntry symTabEntry = symTabEntriesFor.get(0);
         currentInsertPos = symTabEntry.getBodyStartPos() + 2;
      }

      modelCreationParser.indexOf(Parser.CLASS_END);

      TreeSet<Clazz> sortedClazz = new TreeSet<Clazz>(new Comparator<Clazz>()
      {
         @Override
         public int compare(Clazz o1, Clazz o2)
         {
            return o1.getFullName().compareTo(o2.getFullName());
         }

      });
      currentInsertPos = insertNewCreationClasses(modelCreationClass, signature, currentInsertPos,
            rootDir, sortedClazz);

      completeImports();

      writeToFile(modelCreationClass);

   }

   public void insertModelCreationCodeHere(String rootDir)
   {
      // String fileName = null;
      String className = null;
      String methodName = null;

      String callMethodName = null;
      // int callMethodLineNumber = -1;

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();

      StackTraceElement firstStackTraceElement = stackTrace[0];
      callMethodName = firstStackTraceElement.getMethodName();

      StackTraceElement secondStackTraceElement = stackTrace[1];
      // fileName = secondStackTraceElement.getFileName();
      className = secondStackTraceElement.getClassName();
      methodName = secondStackTraceElement.getMethodName();

      int callMethodLineNumber = secondStackTraceElement.getLineNumber();

      // parse the model creation file
      Clazz modelCreationClass = getOrCreateClazz(className);

      modelCreationClass.getClassModel().without(modelCreationClass);

      String signature = Parser.METHOD + ":" + methodName + "(";

      GenClass modelCreationGenerator = getOrCreate(modelCreationClass);
      Parser modelCreationParser = modelCreationGenerator.getOrCreateParser(rootDir);
      modelCreationParser.indexOf(Parser.CLASS_END);

      SymTabEntry symTabEntry = modelCreationParser.getMethodEntryWithLineNumber(signature, callMethodLineNumber);
      if (symTabEntry == null)
      {
         System.out.println("call method for model creation code not found");
         return;
      }

      // insert code
      int currentInsertPos = modelCreationParser.methodCallIndexOf(Parser.NAME_TOKEN + ":model",
            symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());

      if (currentInsertPos > 0)
      {
         currentInsertPos = modelCreationParser.indexOfInMethodBody(Parser.NAME_TOKEN + ":;", currentInsertPos + 1,
               symTabEntry.getEndPos() - 1) + 1;
      }
      else
      {
         currentInsertPos = symTabEntry.getBodyStartPos() + 2;
         currentInsertPos = modelCreationParser.insert(currentInsertPos,
               "      ClassModel clazzModel = new ClassModel(\"" + model.getName() + "\");");
      }

      currentInsertPos = completeCreationClasses(callMethodName, modelCreationClass, signature, currentInsertPos,
            rootDir);

      currentInsertPos = insertNewCreationClasses(callMethodName, modelCreationClass, signature, currentInsertPos,
            rootDir);

      completeImports();

      writeToFile(modelCreationClass);
   }

   private void completeImports()
   {
      for (String className : imports.keySet())
      {
         GenClass genClass = imports.get(className);
         genClass.insertImport(className);
      }
      imports.clear();
   }

   private int completeCreationClasses(String callMethodName, Clazz modelCreationClass, String signature,
         int currentInsertPos, String rootDir)
   {

      refreshMethodScan(signature, modelCreationClass, rootDir);
      for (Clazz clazz : model.getClasses())
      {
         String modelClassName = clazz.getFullName();
         Parser parser = getOrCreate(modelCreationClass)
               .getParser();
         LocalVarTableEntry entry = findInLocalVarTable(
               parser
                     .getLocalVarTable(),
               modelClassName);

         if (entry != null)
         {
            // check code for clazz
            handledClazzes.put(modelClassName, clazz);
            getOrCreate(clazz).getOrCreateParser(rootDir);
            currentInsertPos = checkCodeForClazz(entry, signature, callMethodName, modelCreationClass,
                  refreshMethodScan(signature, modelCreationClass, rootDir), clazz, handledClazzes, currentInsertPos,
                  rootDir);
         }
         writeToFile(modelCreationClass);

      }
      return currentInsertPos;
   }

   private SymTabEntry refreshMethodScan(String signature, Clazz clazz, String rootDir)
   {
      SymTabEntry symTabEntry = null;
      rescanCode(clazz, rootDir);
      Map<String, SymTabEntry> symTab = getOrCreate(clazz).getOrCreateParser(rootDir).getSymTab();
      for (String key : symTab.keySet())
      {
         if (key.startsWith(signature))
         {
            symTabEntry = symTab.get(key);
            break;
         }
      }

      getOrCreate(clazz).getParser().parseMethodBody(symTabEntry);

      return symTabEntry;
   }

   private void rescanCode(Clazz clazz, String rootDir)
   {
      getOrCreate(clazz).getOrCreateParser(rootDir).indexOf(Parser.CLASS_END);
   }

   private int checkCodeForClazz(LocalVarTableEntry entry, String signature, String callMethodName,
         Clazz modelCreationClass, SymTabEntry symTabEntry, Clazz clazz,
         LinkedHashMap<String, Clazz> handledClazzes, int currentInsertPos, String rootDir)
   {
      // rescanCode(modelCreationClass, rootDir);
      Parser modelCreationClassParser = getOrCreate(modelCreationClass).getParser();

      // check has superclass
      if (clazz.getSuperClass() != null && !checkSuper(clazz, entry, "withSuperClazz"))
      {
         String token = Parser.NAME_TOKEN + ":" + entry.getName();
         int methodCallStartPos = modelCreationClassParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(),
               symTabEntry.getEndPos());
         token = Parser.NAME_TOKEN + ":;";
         currentInsertPos = modelCreationClassParser.indexOfInMethodBody(token, methodCallStartPos,
               symTabEntry.getEndPos());
         // set interface
         currentInsertPos = insertCreationCode("\n       /*set superclass*/", currentInsertPos, modelCreationClass);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
         StringBuilder text = new StringBuilder("      .withSuperClazz(superClassName)");
         CGUtil.replaceAll(text, "superClassName",
               StrUtil.downFirstChar(CGUtil.shortClassName(clazz.getSuperClass().getFullName())) + "Class");
         currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass);
         currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);
         // FIXME ALEX NICHT NOTWENDIG ODER
         // getOrCreate(clazz).isFileHasChanged();
      }

      // check is interface
      else if (clazz.isInterface() && !isInterface(entry))
      {
         String token = Parser.NAME_TOKEN + ":" + entry.getName();
         int methodCallStartPos = modelCreationClassParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(),
               symTabEntry.getEndPos());
         token = Parser.NAME_TOKEN + ":;";
         currentInsertPos = modelCreationClassParser.indexOfInMethodBody(token, methodCallStartPos,
               symTabEntry.getEndPos());
         // set interface
         currentInsertPos = insertCreationCode("\n       /*set interface*/", currentInsertPos, modelCreationClass);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
         StringBuilder text = new StringBuilder("      .withInterface(true)");
         currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass);
         currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);
         // FIXME ALEX NICHT NOTWENDIG ODER
         // getOrCreate(clazz).isFileHasChanged();
      }

      // check has interfaces
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (!checkSuper(interfaze, entry, "withInterfaces"))
         {
            // writeToFile(modelCreationClass);
            // find insert position
            String token = Parser.NAME_TOKEN + ":" + entry.getName();
            int methodCallStartPos = modelCreationClassParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(),
                  symTabEntry.getEndPos());
            token = Parser.NAME_TOKEN + ":;";
            currentInsertPos = modelCreationClassParser.indexOfInMethodBody(token, methodCallStartPos,
                  symTabEntry.getEndPos());
            // add attribut
            currentInsertPos = insertCreationCode("\n       /*add interface*/", currentInsertPos, modelCreationClass);
            currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
            StringBuilder text = new StringBuilder("      .withSuperClazz(interfaceName)");
            CGUtil.replaceAll(text, "interfaceName",
                  StrUtil.downFirstChar(CGUtil.shortClassName(interfaze.getFullName())) + "Class");
            currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass);
            currentInsertPos++;
         }

         // set insert position to next line
         currentInsertPos++;
         symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);
      }

      // check code for attribut
      AttributeSet clazzAttributes = clazz.getAttributes();

      for (Attribute attribute : clazzAttributes)
      {
         if (!hasAttribute(attribute, entry, rootDir) && !"PropertyChangeSupport".equals(attribute.getType()))
         {
            writeToFile(modelCreationClass);

            // find insert position
            String tokenString = entry.getInitSequence().get(0).get(0);
            if (tokenString.endsWith(".withAssoc"))
            {
               boolean found = false;
               // attributes belong to a separate
               // playerClass.withAttributes(...) statement
               for (StatementEntry stat : modelCreationClassParser.getCurrentStatement().getParent().getBodyStats())
               {
                  String token = stat.getTokenList().get(0);
                  if (token.equals(entry.getName() + ".withAttribute") ||
                        token.equals(entry.getName() + ".with(new Attribute"))
                  {
                     // here we are
                     currentInsertPos = stat.getEndPos() - 1;
                     found = true;
                     break;
                  }
               }

               if (!found)
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

                  symTabEntry = refreshMethodScan(signature, clazz, rootDir);

                  continue;
               }
            }
            else
            {
               String token = Parser.NAME_TOKEN + ":" + entry.getName();
               int methodCallStartPos = modelCreationClassParser.methodCallIndexOf(token,
                     symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());

               token = Parser.NAME_TOKEN + ":;";
               currentInsertPos = modelCreationClassParser.indexOfInMethodBody(token, methodCallStartPos,
                     symTabEntry.getEndPos());
            }

            // add attribute
            StringBuilder text = new StringBuilder(
                  "\n" +
                        "         /*add attribut*/\n" +
                        "       .with(new Attribute(\"name\", DataType.ref(\"type\")) )");
            // + "         \"name\", \"type\"");

            CGUtil.replaceAll(text,
                  "name", attribute.getName(),
                  "type", attribute.getType().getValue());

            currentInsertPos = insertCreationCode(text.toString(), currentInsertPos, modelCreationClass);

            GenClass genCreationClass = getOrCreate(modelCreationClass);
            addImportForClazz("org.sdmlib.models.classes.Attribute", genCreationClass);
            currentInsertPos++;
         }

         // set insert position to next line
         currentInsertPos += 2;
         symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);
      }

      // check code for method
      MethodSet methods = clazz.getMethods();

      for (Method method : methods)
      {
         currentInsertPos = tryToInsertMethod(symTabEntry, method, currentInsertPos, modelCreationClass);
         symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);
      }

      // check code for assoc
      LinkedHashSet<Role> roles = new LinkedHashSet<Role>();

      if (!clazz.getRoles().isEmpty())
         roles.addAll(clazz.getRoles());

      for (Role role : roles)
      {
         Association association = role.getAssoc();
         if (association == null)
            continue;
         String sourceClassName = association.getSource().getClazz().getFullName();
         String targetClassName = association.getTarget().getClazz().getFullName();

         if (handledClazzes.containsKey(sourceClassName) && handledClazzes.containsKey(targetClassName))
         {

            symTabEntry = refreshMethodScan(signature, modelCreationClass, rootDir);

            int indexOfSourceClassPos = positionOfClazzDecl(sourceClassName, modelCreationClass);
            int indexOfTargetClassPos = positionOfClazzDecl(targetClassName, modelCreationClass);
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
            if (entry.getInitSequence() == null)
               continue;
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
      Parser parser = getOrCreate(modelCreationClass).getParser();
      parser.parseMethodBody(symTabEntry);
      boolean assocIsNew = true;
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = parser.getLocalVarTable();

      Association assoc = role.getAssoc();
      for (String string : localVarTable.keySet())
      {
         LocalVarTableEntry localVarTableEntry = localVarTable.get(string);

         if ("Clazz".equals(localVarTableEntry.getType()))
         {
            ArrayList<ArrayList<String>> sequence = localVarTableEntry.getInitSequence();

            for (ArrayList<String> subSequence : sequence)
            {
               if ("withAssoc".equals(subSequence.get(0)))
               {

                  String className = sequence.get(0).get(1);
                  if (compareAssocDecl(assoc, subSequence, className, localVarTable))
                  {
                     assocIsNew = false;
                     break;
                  }
               }
            }
         }
         else if (string.startsWith("Association_"))
         {
            if (compareAssocDecl(assoc, localVarTableEntry))
            {
               assocIsNew = false;
               break;
            }
         }
      }

      for (StatementEntry stat : getOrCreate(modelCreationClass).getParser().getStatementList().getBodyStats())
      {
         if (stat.getTokenList().get(0).endsWith(".withAssoc")
               || stat.getTokenList().get(0).endsWith(".createClassAndAssoc"))
         {
            // looks like sourceClass.withAssoc(targetClass, "targetRole",
            // targetCard, "sourceRole", sourceCard);
            // ensure source class name
            String classVar = CGUtil.packageName(stat.getTokenList().get(0));
            LocalVarTableEntry localVarTableEntry = localVarTable.get(classVar);
            String classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
            String classNameFromAssoc = assoc.getSource().getClazz().getFullName();

            if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc)
                  || classNameFromAssoc.endsWith("." + classNameFromText))
            {
               // ensure target class name
               classVar = stat.getTokenList().get(2);
               localVarTableEntry = localVarTable.get(classVar);

               if (stat.getTokenList().get(0).endsWith(".withAssoc"))
                  classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
               else
                  classNameFromText = stat.getTokenList().get(2).replaceAll("\"", ""); //

               classNameFromAssoc = assoc.getTarget().getClazz().getFullName();

               if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc)
                     || classNameFromAssoc.endsWith("." + classNameFromText))
               {
                  // ensure target role name
                  String tokenRoleName1 = stat.getTokenList().get(4).replaceAll("\"", "");
                  String assocRoleName1 = assoc.getTarget().getName();
                  String tokenRoleName2 = stat.getTokenList().get(8).replaceAll("\"", "");
                  String assocRoleName2 = assoc.getSource().getName();

                  if (stringsPairwiseEquals(tokenRoleName1, assocRoleName1, tokenRoleName2, assocRoleName2))
                  {
                     // found it
                     assocIsNew = false;
                     break;
                  }
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

   private boolean stringsPairwiseEquals(String string1, String string2, String string3,
         String string4)
   {
      if (StrUtil.stringEquals(string1, string2) && StrUtil.stringEquals(string3, string4)
            || StrUtil.stringEquals(string1, string3) && StrUtil.stringEquals(string2, string4)
            || StrUtil.stringEquals(string1, string4) && StrUtil.stringEquals(string2, string3))
         return true;

      return false;
   }

   private boolean compareAssocDecl(Association assoc, ArrayList<String> subSequence, String className,
         LinkedHashMap<String, LocalVarTableEntry> localVarTable)
   {
      String sourceInitSequence = subSequence.get(7).replaceAll("\"", "") + "|" + className.replaceAll("\"", "");

      String targetClassName = subSequence.get(1);

      for (String key : localVarTable.keySet())
      {
         if (key.equals(targetClassName))
         {
            LocalVarTableEntry entry = localVarTable.get(key);
            ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
            targetClassName = initSequence.get(0).get(1);
         }
      }
      String targetInitSequence = subSequence.get(2).replaceAll("\"", "") + "|" + targetClassName.replaceAll("\"", "");

      String sourceSequence = assoc.getSource().getName() + "|" + assoc.getSource().getClazz().getFullName();
      String targetSequence = assoc.getTarget().getName() + "|" + assoc.getTarget().getClazz().getFullName();

      if ((sourceInitSequence.equals(sourceSequence) && targetInitSequence.equals(targetSequence))
            || (targetInitSequence.equals(sourceSequence) && sourceInitSequence.equals(targetSequence)))
         return true;

      return false;
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

      Parser parser = getOrCreate(modelCreationClass).getParser();

      parser.parseMethodBody(symTabEntry);

      boolean methodIsNew = true;
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = parser.getLocalVarTable();

      for (LocalVarTableEntry localVarTableEntry : localVarTable.values())
      {

         if (compareMethodDecl(method, localVarTableEntry, modelCreationClass))
         {
            currentInsertPos = localVarTableEntry.getEndPos() + 2;

            // Parser parser = getOrCreate(modelCreationClass).getParser();
            // System.out.println("line: "+parser.getLineIndexOf(currentInsertPos));
            // System.out.println(parser.getLineForPos(currentInsertPos));

            methodIsNew = false;
            break;
         }
      }

      if (methodIsNew && !isSDMLibMethod(method))
      {
         String name = CGUtil.shortClassName(method.getClazz().getFullName()) + "Class";
         name = StrUtil.downFirstChar(name);
         LocalVarTableEntry localVarTableEntry = localVarTable.get(name);
         if (localVarTableEntry == null)
            return currentInsertPos;
         int insertPos = localVarTableEntry.getEndPos() + 1;
         currentInsertPos = insertCreationCode("\n      /* add method */", insertPos, modelCreationClass);
         currentInsertPos = insertCreationMethodCode(method, currentInsertPos, modelCreationClass, symTabEntry);
         writeToFile(modelCreationClass);
      }

      return currentInsertPos;
   }

   private boolean isSDMLibMethod(Method method)
   {
      return (method.getName().startsWith("get") && method.getName().endsWith("Transitive"));
   }

   private boolean compareMethodDecl(Method method, LocalVarTableEntry localVarTableEntry, Clazz modelCreationClass)
   {
      String shortClassName = CGUtil.shortClassName(method.getClazz().getFullName());

      // String signature = method.getSignature(true);

      // String methodClass = "";
      // String methodSignature = "";
      ArrayList<ArrayList<String>> initSequence = localVarTableEntry.getInitSequence();

      // the first method call should be model.createClazz(<className>)
      ArrayList<String> init = initSequence.get(0);

      if (init.size() != 2)
         return false;

      if (!init.get(0).endsWith(".createClazz"))
         return false;

      String initClassName = init.get(1);
      initClassName = initClassName.substring(1, initClassName.length() - 1);

      if (!shortClassName.equals(CGUtil.shortClassName(initClassName)))
         return false;

      for (int i = 1; i < initSequence.size(); i++)
      {
         init = initSequence.get(i);

         if ("withMethod".equals(init.get(0)) && ("\"" + method.getName() + "\"").equals(init.get(1)))
         {
            // looks good, do we need to check more?
            if (init.size() <= 7 && method.getParameter().size() == 0)
               return true;

            // there should be more:
            if (init.size() >= 4)
            {
               String dataType = init.get(3);
               String searchType = method.getReturnType().toString();

               if (!dataType.equals(searchType))
                  return false;

               // boolean found = false;
               try
               {
                  int j = 6;
                  Iterator<Parameter> paramIter = method.getParameter().iterator();
                  while (j < init.size() && paramIter.hasNext())
                  {
                     Parameter searchParam = paramIter.next();
                     String token = init.get(j);

                     if (!token.equals("["))
                        return false;

                     j++;
                     token = init.get(j);
                     if (!token.equals("new"))
                        return false;

                     j++;
                     token = init.get(j);
                     if (!token.equals("Parameter"))
                        return false;

                     j++;
                     token = init.get(j);
                     if (!token.equals(searchParam.getType().toString()))
                     {
                        if (!token.equals("DataType.ref")
                              || !init.get(j + 1).equals(("\"" + searchParam.getType().getValue() + "\"")))
                           return false;
                        else
                        {
                           j++;
                        }
                     }

                     // this parameter matches
                     j = j + 3;

                     if (j == init.size() && !paramIter.hasNext())
                     {
                        return true;
                     }
                  }

               }
               catch (Exception e)
               {

               }
            }
         }

      }

      return false;
   }

   protected String parseDataType(String typeString, Clazz modelCreationClass)
   {

      String type = "";

      if (typeString.startsWith("DataType.ref"))
      {

         String typeSplit = typeString.substring("DataType.ref".length() + 1, typeString.length() - 1);

         // DataType.ref("String")
         if (typeSplit.startsWith("\""))
         {
            typeSplit = typeSplit.replaceAll("\"", "");
            typeSplit = typeSplit.toUpperCase();
            return "DataType." + typeSplit;
         }

         // DataType.ref(objectname)
         else
         {
            LocalVarTableEntry tableEntry = getOrCreate(modelCreationClass).getParser()
                  .getLocalVarEntriesFor(typeSplit);
            if (tableEntry != null)
            {
               String className = tableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
               className = className.toUpperCase();
               return "DataType." + className;
            }
         }
      }
      // DataType.STRING
      else if (typeString.startsWith("DataType."))
      {
         // TODO : parse primitive types
      }

      return type;
   }

   private int createAndInsertCodeForNewClazz(Clazz modelCreationClass, SymTabEntry symTabEntry,
         Clazz clazz, LinkedHashMap<String, Clazz> handledClazzes,
         int currentInsertPos)
   {

      String modelClassName = clazz.getFullName();
      String classModelName = clazz.getClassModel().getName();

      if (modelClassName.startsWith(classModelName))
      {
         modelClassName = modelClassName.replaceFirst(classModelName + ".", "");
      }

      // no creation code yet. Insert it.
      currentInsertPos = insertCreationClassCode(currentInsertPos, modelClassName, modelCreationClass, symTabEntry);

      // check interface attr
      if (clazz.isInterface())
      {
         currentInsertPos = insertCreationIsInterfaceCode(currentInsertPos, modelCreationClass, symTabEntry);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
      }

      // insert code for superclass
      Clazz superClass = clazz.getSuperClass();
      if (superClass != null)
      {
         currentInsertPos = insertCreationSuperClassCode(currentInsertPos, superClass.getFullName(),
               modelCreationClass, symTabEntry);
         currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
      }

      // insert code for interfaces
      for (Clazz interfaze : clazz.getInterfaces())
      {
         if (interfaze != null)
         {
            currentInsertPos = insertCreationInterfaceCode(currentInsertPos, interfaze.getFullName(),
                  modelCreationClass, symTabEntry);
            currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
         }
      }

      // insert code for new Attr()
      AttributeSet clazzAttributes = clazz.getAttributes();

      TreeSet<Attribute> sortedAttributes = new TreeSet<Attribute>(new Comparator<Attribute>()
      {
         @Override
         public int compare(Attribute o1, Attribute o2)
         {
            return o1.getName().compareTo(o2.getName());
         }

      });

      for (Attribute attribute : clazzAttributes)
      {
         sortedAttributes.add(attribute);
      }

      for (Attribute attribute : sortedAttributes)
      {
         if (!"PropertyChangeSupport".equals(attribute.getType()))
         {
            currentInsertPos = insertCreationAttributeCode(attribute,
                  currentInsertPos, modelCreationClass, symTabEntry);
            currentInsertPos = insertCreationCode("\n", currentInsertPos,
                  modelCreationClass);
         }
      }
      currentInsertPos = 1 + insertCreationCode(";", currentInsertPos - 1, modelCreationClass);

      // insert code for new Method()
      MethodSet methods = clazz.getMethods();

      TreeSet<Method> sortedMethods = new TreeSet<Method>(new Comparator<Method>()
      {
         @Override
         public int compare(Method o1, Method o2)
         {
            return o1.getName().compareTo(o2.getName());
         }

      });

      for (Method method : methods)
      {
         sortedMethods.add(method);
      }

      for (Method method : sortedMethods)
      {
         currentInsertPos = insertCreationMethodCode(method, currentInsertPos, modelCreationClass, symTabEntry);
      }

      // insert code for new Assoc
      // LinkedHashSet<Role> roles = new LinkedHashSet<Role>();
      TreeSet<Role> roles = new TreeSet<Role>(new Comparator<Role>()
      {
         @Override
         public int compare(Role o1, Role o2)
         {
            return o1.getPartnerRole().getName().compareTo(o2.getPartnerRole().getName());
         }

      });

      if (!clazz.getRoles().isEmpty())
      {

         for (Role role : clazz.getRoles())
         {
            roles.add(role);
         }
      }

      currentInsertPos = handleAssocs(roles, currentInsertPos, modelCreationClass, symTabEntry, handledClazzes);

      return currentInsertPos;
   }

   // FIXME private int searchForQualifiedNamePosition(String methodCall, int
   // methodEndPos, Parser parser)
   // {
   // Set<String> methodBodyQualifiedNames =
   // parser.getMethodBodyQualifiedNames();
   // for (String qualifiedName : methodBodyQualifiedNames)
   // {
   // if (qualifiedName.contains(methodCall))
   // {
   // int callPos = parser.getMethodBodyQualifiedNamesMap().get(qualifiedName);
   // String substring = parser.getFileBody().substring(callPos, methodEndPos);
   // return callPos + substring.indexOf(';') + 1;
   // }
   // }
   // return -1;
   // }

   private int handleAssocs(TreeSet<Role> roles, int currentInsertPos, Clazz modelCreationClass,
         SymTabEntry symTabEntry, LinkedHashMap<String, Clazz> handledClazzes)
   {
      ArrayList<Association> handledAssocs = new ArrayList<Association>();
      ArrayList<Role> handledRoles = new ArrayList<Role>();

      for (Role firstRole : roles)
      {
         Association assoc = firstRole.getAssoc();

         if (assoc == null || assocHasHandled(assoc, handledAssocs))
         {
            continue;
         }

         Role secondRole;

         if (assoc.getTarget() != firstRole)
            secondRole = assoc.getTarget();
         else
            secondRole = assoc.getSource();

         String secondClassName = secondRole.getClazz().getFullName();

         if (handledClazzes.containsKey(secondClassName) && !handledRoles.contains(secondRole))
         {
            handledAssocs.add(assoc);
            handledRoles.add(firstRole);
            currentInsertPos = insertCreationAssociationCode(assoc, currentInsertPos, modelCreationClass, symTabEntry);
         }

         writeToFile(modelCreationClass);
      }
      return currentInsertPos;
   }

   private int insertNewCreationClasses(String callMethodName, Clazz modelCreationClass, String signature,
         int currentInsertPos, String rootDir)
   {
      return insertNewCreationClasses(modelCreationClass, signature, currentInsertPos, rootDir,
            new LinkedHashSet<Clazz>());
   }

   private int insertNewCreationClasses(Clazz modelCreationClass, String signature,
         int currentInsertPos, String rootDir, Set<Clazz> clazzQueue)
   {

      // find last creation code position
      for (Clazz clazz : model.getClasses())
      {
         clazzQueue.add(clazz);
      }

      boolean format = false;

      while (!clazzQueue.isEmpty())
      {
         Clazz clazz = clazzQueue.iterator().next();
         clazzQueue.remove(clazz);

         String modelClassName = clazz.getFullName();

         Parser parser = getOrCreate(modelCreationClass).getParser().parse();
         ArrayList<SymTabEntry> symTabEntry = parser.getSymTabEntriesFor(signature);
         parser.parseMethodBody(symTabEntry.get(0));

         LocalVarTableEntry entry = findInLocalVarTable(getOrCreate(modelCreationClass).getParser().getLocalVarTable(),
               modelClassName);

         if (entry == null)
         {
            // insert code for new Clazz()
            if (!checkDependencies(clazz))
            {
               clazzQueue.add(clazz);
            }
            else
            {
               if (!format)
               {
                  currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
                  format = true;
               }

               handledClazzes.put(modelClassName, clazz);
               currentInsertPos = createAndInsertCodeForNewClazz(modelCreationClass,
                     refreshMethodScan(signature, modelCreationClass, rootDir), clazz, handledClazzes, currentInsertPos);
            }
            writeToFile(modelCreationClass);
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
         if (handledClazzes.get(depClazz.getFullName()) == null)
         {
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

   private int insertCreationClassCode(int currentInsertPos, String modelClassName, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("\n      Clazz localVar = clazzModel.createClazz(\"className\")\n");

      Parser parser = getOrCreate(modelCreationClass).getParser();
      LinkedHashMap<String, LocalVarTableEntry> localVarTable = parser.getLocalVarTable();
      for (String key : localVarTable.keySet())
      {
         LocalVarTableEntry localVarTableEntry = localVarTable.get(key);

         if ("ClassModel".equals(localVarTableEntry.getType()))
         {
            String classmodelName = localVarTableEntry.getName();

            CGUtil.replaceAll(text, "clazzModel", classmodelName);
            break;
         }
      }

      CGUtil.replaceAll(text, "localVar", StrUtil.downFirstChar(CGUtil.shortClassName(modelClassName)) + "Class",
            "className", modelClassName);

      currentInsertPos = checkImport("Clazz", currentInsertPos, modelCreationClass, symTabEntry);
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationSuperClassCode(int currentInsertPos, String superClassName, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withSuperClazz(superClassName)");

      CGUtil.replaceAll(text, "superClassName", StrUtil.downFirstChar(CGUtil.shortClassName(superClassName)) + "Class");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationInterfaceCode(int currentInsertPos, String interfaceName, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withSuperClazz(interfaceName)");

      CGUtil.replaceAll(text, "interfaceName", StrUtil.downFirstChar(CGUtil.shortClassName(interfaceName)) + "Class");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationIsInterfaceCode(int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder("      .withInterface(true)");
      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private int insertCreationAttributeCode(Attribute attribute, int currentInsertPos, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {

      GenClass genCreationClass = getOrCreate(modelCreationClass);
      Parser parser = genCreationClass.getParser();

      // has init value
      String initialization = attribute.getInitialization();
      if (initialization != null)
      {
         initialization = ".withInitialization(\"" + initialization + "\")";
      }
      else
      {
         initialization = "";
      }
      StringBuilder result = parser.replaceAll(currentInsertPos,
            "      .with(new Attribute(\"attributeName\", DataType.ref(\"attributeType\")) attributeInit)",
            "attributeType", attribute.getType().getValue(),
            "attributeName", attribute.getName(),
            "attributeInit", initialization);

      addImportForClazz("org.sdmlib.models.classes.Attribute", genCreationClass);
      return currentInsertPos + result.length();
   }

   HashMap<String, GenClass> imports = new HashMap<>();

   private void addImportForClazz(String className, GenClass genCreationClass)
   {
      imports.put(className, genCreationClass);
   }

   private int insertCreationMethodCode(Method method, int currentInsertPos, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {
      String methodName = method.getName();
      if (methodName.startsWith("get") && methodName.endsWith("Transitive"))
      {

         String part = methodName.substring("get".length(), methodName.length() - "Transitive".length());

         Clazz clazz = method.getClazz();
         for (Role role : clazz.getRoles())
         {
            if (role.getName().toLowerCase().equals(part.toLowerCase()))
               return currentInsertPos;
         }
      }

      Parser parser = getOrCreate(modelCreationClass).getParser();

      String clazzName = method.getClazz().getFullName();
      clazzName = StrUtil.downFirstChar(CGUtil.shortClassName(clazzName)) + "Class";

      StringBuilder paString = new StringBuilder();
      for (Parameter parameter : method.getParameter())
      {
         paString.append(", new Parameter(" + parameter.getType().toString() + ")");
      }

      StringBuilder result = parser.replaceAll(currentInsertPos - 2, "\n" +
            "      .withMethod(\"METHODNAME\", Return_TypePARAMETERS)",
            "Return_Type", method.getReturnType().toString(),
            "PARAMETERS", paString.toString(),
            "METHODNAME", methodName);
      currentInsertPos = checkImport("Method", currentInsertPos, modelCreationClass, symTabEntry);

      currentInsertPos += result.length();
      if (paString.length() > 0)
      {
         currentInsertPos = checkImport("Parameter", currentInsertPos, modelCreationClass, symTabEntry);
      }
      return currentInsertPos;
   }

   private int checkImport(String newImport, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
   {
      getOrCreate(modelCreationClass).getParser().indexOf(Parser.CLASS_END);
      Map<String, SymTabEntry> symTab = getOrCreate(modelCreationClass).getParser().getSymTab();
      LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

      for (String key : symTab.keySet())
      {
         if (key.startsWith(Parser.IMPORT) && (key.endsWith("." + newImport) || key.endsWith(".ClassModel")))
         {
            String path = key.replace(Parser.IMPORT + ":", "");
            int lastIndexOf = path.lastIndexOf('.') + 1;
            String name = path.substring(lastIndexOf);
            String pathString = path.substring(0, lastIndexOf - 1);
            result.put(name, pathString);
         }
      }

      if (!result.containsKey(newImport) && result.containsKey("ClassModel"))
      {
         String symTabEntryName = result.get("ClassModel");
         int endOfImports = getOrCreate(modelCreationClass).getParser().getEndOfImports() + 1;
         String importString = "\n" + Parser.IMPORT + " " + symTabEntryName + "." + newImport + ";";
         insertCreationCode(importString, endOfImports, modelCreationClass);
         currentInsertPos += importString.length();
      }

      return currentInsertPos;
   }

   private int insertCreationAssociationCode(Association assoc, int currentInsertPos, Clazz modelCreationClass,
         SymTabEntry symTabEntry)
   {
      StringBuilder text = new StringBuilder(
            "\n      sourceClazz.withAssoc(targetClazz, \"targetName\", targetCard, \"sourceName\", sourceCard);\n");

      Role source = assoc.getSource();
      Role target = assoc.getTarget();

      if (source.getName().compareTo(target.getName()) < 1)
      {
         Role tempRole = source;
         source = target;
         target = tempRole;
      }

      String sourceCard = "Card." + source.getCard().toUpperCase();
      String sourceName = source.getName();
      String sourceClazz = StrUtil.downFirstChar(CGUtil.shortClassName(source.getClazz().getFullName())) + "Class";

      String targetCard = "Card." + target.getCard().toUpperCase();
      String targetName = target.getName();
      String targetClazz = StrUtil.downFirstChar(CGUtil.shortClassName(target.getClazz().getFullName())) + "Class";

      CGUtil.replaceAll(text,
            "sourceName", sourceName,
            "sourceClazz", sourceClazz,
            "sourceCard", sourceCard,
            "targetName", targetName,
            "targetClazz", targetClazz,
            "targetCard", targetCard);

      currentInsertPos = checkImport("Association", currentInsertPos, modelCreationClass, symTabEntry);

      GenClass genCreationClass = getOrCreate(modelCreationClass);
      addImportForClazz("org.sdmlib.models.classes.Card", genCreationClass);

      return insertCreationCode(text, currentInsertPos, modelCreationClass);
   }

   private boolean checkSuper(Clazz clazz, LocalVarTableEntry entry, String classType)
   {
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
      for (ArrayList<String> sequencePart : initSequence)
      {
         if (classType.equals(sequencePart.get(0)))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isInterface(LocalVarTableEntry entry)
   {
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
      for (ArrayList<String> sequencePart : initSequence)
      {
         if ("withInterface".equals(sequencePart.get(0)))
         {
            return true;
         }
      }
      return false;
   }

   private int insertCreationCode(StringBuilder text, int insertPos, Clazz modelCreationClass)
   {
      getOrCreate(modelCreationClass).getParser().insert(insertPos, text.toString());
      return insertPos + text.length();
   }

   private int insertCreationCode(String string, int insertPos, Clazz modelCreationClass)
   {
      StringBuilder text = new StringBuilder(string);
      return insertCreationCode(text, insertPos, modelCreationClass);
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
                  // currentTypeCard = role.getCard();
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

      while (!todoObjects.isEmpty())
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
         if (sourceType == null)
            continue;

         String targetType = currentLink.getTgt().getType();
         if (targetType == null)
            continue;

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

         // search for an assoc with similar srcClazz, srcLabel, tgtClass,
         // tgtLabel
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
                  .withSource(this.getOrCreateClazz(packageName + "." + sourceType), sourceLabel, Cardinality.ONE)
                  .withTarget(getOrCreateClazz(packageName + "." + targetType), targetLabel, Cardinality.ONE);
            this.addToAssociations(currentAssoc);
         }

         if (alreadyUsedLabels.contains(currentLink.getSrc().hashCode() + ":" + targetLabel))
         {
            currentAssoc.getTarget().setCard(Cardinality.MANY.toString());
         }

         if (alreadyUsedLabels.contains(currentLink.getTgt().hashCode() + ":" + sourceLabel))
         {
            currentAssoc.getSource().setCard(Cardinality.MANY.toString());
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

   public Clazz findClass(String partnerClassName)
   {
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
      javaFiles.sort(new Comparator<File>()
      {

         @Override
         public int compare(File file1, File file2)
         {
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());

         }

      });
      return javaFiles;
   }

   public void updateFromCode(String includePathes, String packages)
   {

      File projectRootFolder = new File(".");

      updateFromCode(includePathes, packages, projectRootFolder);
   }

   /*
    * usage for maven project model.updateFromCode("java", "org.package.name",
    * new File((new
    * File(this.getClass().getResource(".").getPath())).getParentFile
    * ().getParent() + "/src/main/" ));
    */

   public void updateFromCode(String includePathes, String packages, File projectRoot)
   {
      // find java files in parent directory

      if (projectRoot != null)
      {
         ArrayList<File> javaFiles = searchForJavaFiles(includePathes, packages, projectRoot);
         addJavaFilesToClasses(packages, projectRoot, javaFiles);

         if (model.getClasses().isEmpty())
         {
            System.out.println("no class files found !!!! END");
            return;
         }

         // parse each java file
         for (Iterator<Clazz> i = model.getClasses().cloneIterator(); i.hasNext();)
         {
            Clazz clazz = i.next();
            handleMember(clazz, includePathes, projectRoot);
         }
      }

      // add model creation code at invocation place, if not yet there
   }

   private Clazz handleMember(Clazz clazz, String rootDir, File projectRoot)
   {
      System.out.println("parse " + clazz.getFullName());
      Parser parser = getOrCreate(clazz).getOrCreateParser(projectRoot.getAbsolutePath() + "//" + rootDir);
      parser.indexOf(Parser.CLASS_END);

      if (isHelperClass(parser))
      {
         clazz.removeYou();
      }

      // set class or interface
      if (Parser.INTERFACE.equals(parser.getClassType()))
      {
         clazz.setInterface(true);
      }

      LinkedHashMap<String, SymTabEntry> symTab = new LinkedHashMap<String, SymTabEntry>();
      for (String key : parser.getSymTab().keySet())
      {
         symTab.put(key, parser.getSymTab().get(key));
      }

      for (String memberName : symTab.keySet())
      {
         addMemberToModel(clazz, parser, memberName, projectRoot + "/" + rootDir);
      }

      return clazz;
   }

   private boolean isSDMLibClass(Clazz clazz)
   {

      String className = clazz.getName(false);

      String sDMLibClasses =
            "org.sdmlib.serialization.EntityFactory "
                  + "org.sdmlib.models.pattern.PatternObject "
                  + "org.sdmlib.models.pattern.util.PatternObjectCreator "
                  + "org.sdmlib.models.modelsets.SDMSet "
                  + "org.sdmlib.serialization.PropertyChangeInterface";

      if (sDMLibClasses.indexOf(className) >= 0)
      {
         return true;
      }

      return false;
   }

   private boolean isHelperClass(Parser parser)
   {
      String className = parser.getClassName();

      // is creatorcreator class
      if ("CreatorCreator".equals(className))
      {
         return true;
      }

      // is creator class
      if (className.endsWith("Creator")
            && isClassExtends("EntityFactory PatternObjectCreator", parser))
      {
         return true;
      }

      // is PO class
      if (isClassExtends("PatternObject", parser))
      {
         return true;
      }

      // is Set class
      if (isClassExtends("SDMSet", parser))
      {
         return true;
      }

      return false;
   }

   private boolean isClassExtends(String extendsString, Parser parser)
   {
      for (String extendClass : extendsString.split(" "))
      {
         if (parser.getSymTabEntriesFor("extends:" + extendClass).size() > 0)
            return true;
      }

      return false;
   }

   private void addMemberToModel(Clazz clazz, Parser parser, String memberName, String rootDir)
   {
      //add annotations
      if(memberName.startsWith("annotation")) {
         addMemberAsAnnotation(clazz, memberName, parser);
      }
      
      // add new methods
      if (memberName.startsWith(Parser.METHOD))
      {
         addMemberAsMethod(clazz, memberName, parser);
      }
      // add new attributes
      else if (memberName.startsWith(Parser.ATTRIBUTE))
      {
         String[] split = memberName.split(":");
         String attrName = split[1];
         SymTabEntry symTabEntry = parser.getSymTab().get(memberName);
         if (symTabEntry != null)
            addMemberAsAttribut(clazz, attrName, symTabEntry, rootDir);
      }

      // add super classes
      if (memberName.startsWith(Parser.EXTENDS))
      {
         if (clazz.isInterface())
         {
            addMemberAsInterface(clazz, memberName, parser);
         }
         else
         {
            addMemberAsSuperClass(clazz, memberName, parser);
         }
      }
      else if (memberName.startsWith(Parser.IMPLEMENTS))
      {
         addMemberAsInterface(clazz, memberName, parser);
      }

   }

   private void addMemberAsAnnotation(Clazz clazz, String memberName, Parser parser)
   {
      String[] split = memberName.split(":");
      clazz.withAnnotation(new Annotation().withName(split[1]));
   }

   private void addMemberAsAttribut(Clazz clazz, String attrName, SymTabEntry symTabEntry, String rootDir)
   {
      // filter public static final constances
      String modifiers = symTabEntry.getModifiers();
      if ((modifiers.indexOf("public") >= 0 || modifiers.indexOf("private") >= 0) && modifiers.indexOf("static") >= 0
            && modifiers.indexOf("final") >= 0)
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
            new Attribute(attrName, DataType.ref(symTabEntry.getType())).with(clazz);
         }
      }

      else
      {
         // handle complex attributes
         handleComplexAttr(clazz, attrName, symTabEntry, rootDir);
      }
   }

   private void handleComplexAttr(Clazz clazz, String attrName, SymTabEntry symTabEntry, String rootDir)
   {

      String memberName = symTabEntry.getMemberName();
      String partnerTypeName = symTabEntry.getType();

      String partnerClassName = findPartnerClassName(partnerTypeName);
      Clazz partnerClass = model.getClazz(partnerTypeName);

      if (partnerClass == null)
         return;

      Card card = findRoleCard(partnerTypeName);

      String setterPrefix = "set";
      if (Cardinality.MANY.equals(card))
      {
         setterPrefix = "addTo";
      }

      String name = StrUtil.upFirstChar(memberName);
      Parser parser = getOrCreate(clazz).getParser().parse();

      SymTabEntry addToSymTabEntry = parser.getSymTab().get(
            Parser.METHOD + ":" + setterPrefix + name + "(" + partnerClassName + ")");

      if (addToSymTabEntry == null && "addTo".equals(setterPrefix))
      {
         addToSymTabEntry = parser.getSymTab().get(
               Parser.METHOD + ":" + "with" + name + "(" + partnerClassName + "...)");
      }

      // type is unknown
      if (addToSymTabEntry == null)
      {
         new Attribute(memberName, DataType.ref(partnerTypeName))
               .with(clazz);
         return;
      }

      parser.parseMethodBody(addToSymTabEntry);
      LinkedHashSet<String> methodBodyQualifiedNames = new LinkedHashSet<String>();
      for (String key : parser.getMethodBodyQualifiedNames())
      {
         methodBodyQualifiedNames.add(key);
      }

      boolean done = false;
      for (String qualifiedName : methodBodyQualifiedNames)
      {
         if (qualifiedName.startsWith("value.set"))
         {

            handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass,
                  qualifiedName.substring("value.set".length()));
            done = true;
         }
         else if (qualifiedName.startsWith("value.with") || qualifiedName.startsWith("item.with"))
         {
            handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass,
                  qualifiedName.substring("value.with".length()));
            done = true;
         }
         else if (qualifiedName.startsWith("value.addTo"))
         {

            handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass,
                  qualifiedName.substring("value.addTo".length()));
            done = true;
         }
      }
      if (!done)
      {
         // did not find reverse role, add as attribute
         new Attribute(memberName, DataType.ref(partnerTypeName))
               .with(clazz);
      }

      // // remove getter with setter or addTo removeFrom removeAllFrom without
      // for (String memberName : memberNames)
      // {
      // // remove getter with setter or addTo removeFrom removeAllFrom without
      // findAndRemoveMethods(
      // clazz,
      // memberName,
      // "get set with without addTo create removeFrom removeAllFrom iteratorOf hasIn sizeOf removePropertyChange addPropertyChange");
      // findAndRemoveAttributs(clazz, "listeners");
      // }

   }

   private void handleAssoc(Clazz clazz, String rootDir, String memberName, Card card,
         String partnerClassName, Clazz partnerClass, String partnerAttrName)
   {
      partnerAttrName = StrUtil.downFirstChar(partnerAttrName);
      GenClass partnerGenClass = getOrCreate(partnerClass);
      Parser partnerParser = partnerGenClass.getOrCreateParser(rootDir);
      String searchString = Parser.ATTRIBUTE + ":" + partnerAttrName;

      int attributePosition = partnerParser.indexOf(searchString);

      if (attributePosition > -1)
      {
         Card partnerCard = findRoleCard(partnerParser, searchString);
         tryToCreateAssoc(clazz, memberName, card, partnerClassName, partnerClass, partnerAttrName, partnerCard);
      }
   }

   private void tryToCreateAssoc(Clazz clazz, String memberName, Card card, String partnerClassName,
         Clazz partnerClass, String partnerAttrName, Card partnerCard)
   {
      Role sourceRole = new Role(clazz, partnerAttrName, partnerCard);
      Role targetRole = new Role(partnerClass, memberName, card);

      if (!assocWithRolesExists(sourceRole, targetRole))
      {
         new Association().withSource(sourceRole).withTarget(targetRole);

         clazz.with(sourceRole);
      }
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

   private boolean compareRoles(Role source, Role target, Association assoc)
   {
      return compareRoles(assoc.getSource(), source) && compareRoles(assoc.getTarget(), target);
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
      Card partnerCard = Cardinality.ONE;
      int _openAngleBracket = partnerTypeName.indexOf("<");
      int _closeAngleBracket = partnerTypeName.indexOf(">");
      if (_openAngleBracket > 1 && _closeAngleBracket > _openAngleBracket)
      {
         // partner to many
         partnerCard = Cardinality.MANY;
      }
      else if (partnerTypeName.endsWith("Set") && partnerTypeName.length() > 3)
      {
         // it might be a ModelSet. Look if it starts with a clazz name
         String prefix = partnerTypeName.substring(0, partnerTypeName.length() - 3);
         for (Clazz clazz : model.getClasses())
         {
            if (prefix.equals(CGUtil.shortClassName(clazz.getName())))
            {
               partnerCard = Cardinality.MANY;
               break;
            }
         }
      }
      return partnerCard;
   }

   private void addMemberAsSuperClass(Clazz clazz, String memberName, Parser parser)
   {
      Clazz memberClass = findMemberClass(clazz, memberName, parser);

      // ignore helperclasses
      if (isSDMLibClass(memberClass))
      {
         memberClass.removeYou();
         return;
      }

      if (memberClass != null)
         clazz.withSuperClazz(memberClass);
   }

   private void addMemberAsInterface(Clazz clazz, String memberName, Parser parser)
   {
      Clazz memberClass = findMemberClass(clazz, memberName, parser);

      // ignore helperclasses
      if (isSDMLibClass(memberClass))
      {
         memberClass.removeYou();
         return;
      }

      if (memberClass != null)
      {
         memberClass.withInterface(true);

         clazz.withSuperClazz(memberClass);
      }
   }

   private Clazz findMemberClass(Clazz clazz, String memberName, Parser parser)
   {
      String[] split = memberName.split(":");
      String signature = split[1];

      Map<String, SymTabEntry> symTab = parser.getSymTab();

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
      ClazzSet classes = model.getClasses();

      for (Clazz eClazz : classes)
      {
         if (eClazz.getFullName().equals(name))
         {
            return eClazz;
         }
      }

      return null;
   }

   private void addMemberAsMethod(Clazz clazz, String memberName, Parser parser)
   {
      SymTabEntry symTabEntry = parser.getSymTab().get(memberName);

      // TODO: FIX Parser has no symTableEntries
      if (symTabEntry == null)
      {
         parser.parse();
         symTabEntry = parser.getSymTab().get(memberName);
      }
      String fullSignature = symTabEntry.getType();
      String[] split = fullSignature.split(":");
      String signature = split[1];

      // filter internal generated methods
      String filterString = "get(String) set(String,Object) getPropertyChangeSupport() removeYou()"
            +
            " addPropertyChangeListener(PropertyChangeListener) removePropertyChangeListener(PropertyChangeListener)"
            +
            " addPropertyChangeListener(String,PropertyChangeListener) removePropertyChangeListener(String,PropertyChangeListener)"
            + "toString()";

      if (filterString.indexOf(signature) < 0 && !isGetterSetter(signature, parser, clazz)
            && isNewMethod(signature, clazz))
      {
         int part = signature.indexOf("(");
         String[] params = signature.substring(part + 1, signature.length() - 1).split(",");

         Method method = new Method(signature.substring(0, part))
               .with(clazz)
               .withReturnType(DataType.ref(split[2]));
         for (String param : params)
         {
            if (param != null && param.length() > 0)
            {
               method.with(new Parameter(DataType.ref(param)));
            }
         }

         if (!symTabEntry.getAnnotations().isEmpty())
         {
            method.withAnnotation(new Annotation().withName(symTabEntry.getAnnotations()));
         }
         method.setThrowsTags(symTabEntry.getThrowsTags());
         method.setBody(parser.getFileBody().substring(symTabEntry.getBodyStartPos(),  symTabEntry.getEndPos()+1));
      }
   }

   private boolean isGetterSetter(String signature, Parser parser, Clazz clazz)
   {
      Map<String, SymTabEntry> symTab = parser.getSymTab();
      List<String> attributeKeys = new ArrayList<>();

      for (String key : symTab.keySet())
      {

         if (key.startsWith("attribute:"))
         {
            attributeKeys.add(key);
         }
         // TODO: check type hierarchy
      }

      // method starts with: with set get ...
      if (signature.startsWith("with")
            || signature.startsWith("set")
            || signature.startsWith("get")
            || signature.startsWith("add")
            || signature.startsWith("remove")
            || signature.startsWith("create"))
      {

         // is class attribute
         for (String attrKey : attributeKeys)
         {
            SymTabEntry symTabEntry = symTab.get(attrKey);
            String attrName = symTabEntry.getMemberName();
            String signName = signature.substring(0, signature.indexOf("("));

            if (signName.toLowerCase().endsWith(attrName.toLowerCase()))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isNewMethod(String memberName, Clazz clazz)
   {
      for (Method method : clazz.getMethods())
      {
         if (method.getSignature(true).equals(memberName))
            return false;
      }

      return true;
   }

   private ArrayList<File> searchForJavaFiles(String includePathes, String packageString, File projectRoot)
   {
      ArrayList<File> javaFiles = new ArrayList<File>();
      String[] packages = packageString.split("\\s+");
      String[] includes = includePathes.split("\\s+");

      for (String pAckage : packages)
      {
         String packagepath = pAckage.replace('.', '/');
         for (String include : includes)
         {

            String newPath = projectRoot.getPath() + "/" + include + "/" + packagepath;

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
      int pos = filePath.indexOf('.');
      // split off source folder
      String rootDir="";
//      boolean commonPrefix=false;
      for (String packageName : packages)
      {
         pos = filePath.indexOf(packageName) - 1;
         if (pos == -1) { // It is 0 - 1
//        	 pos = 0;
//        	 commonPrefix = true;
         }
         break;
      }
      if(pos > 0) {
		rootDir = filePath.substring(0, pos);
      }
      
      filePath = filePath.substring(pos + 1);
      if (commonPrefix(filePath, packages))
      {
         if (!classExists(filePath))
         {
            Clazz clazz = model.createClazz(filePath);
            getOrCreate(clazz).withFilePath(rootDir);
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

   private boolean hasAttribute(Attribute attribute, LocalVarTableEntry entry, String rootDir)
   {
      String name = attribute.getName();
      ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();

      for (ArrayList<String> sequencePart : initSequence)
      {
         ArrayList<String> isAttribute = partIsAttribute(sequencePart);
         if (isAttribute != null)
         {
            String sequencePartName = isAttribute.get(0).replace("\"", "");
            if (StrUtil.stringEquals(name, sequencePartName))
            {
               // check only for attr name, user may have changed attr type, do
               // not overwrite this.

               // if (sequencePart.size() > 6 &&
               // "DataType.ref".equals(isAttribute.get(1)) ) {
               // String typeString =
               // type.toString().substring(type.toString().indexOf(".")+1);
               // String sequencePartType = sequencePart.get(1).replaceAll("\"",
               // "").toUpperCase();
               // if (!sequencePartType.equals(typeString))
               // System.out.println("warning: " +
               // attribute.getClazz().getName()+":attribute \"" + name
               // +"\" has different type "+sequencePartType +"!="+ typeString);
               // }

               // && StrUtil.stringEquals(type, sequencePartType))
               return true;
            }
         }
         else if ("model.createClazz".equals(sequencePart.get(0)))
         {
            // initialization with varargs
            int pos = 2;
            while (pos + 1 < sequencePart.size())
            {
               String attrName = sequencePart.get(pos).replace("\"", "");
               // String typeName = sequencePart.get(pos + 1).replace("\"", "");

               if (StrUtil.stringEquals(name, attrName))
               {
                  return true;
               }

               pos += 2;
            }

         }
      }

      // there may be separate clazz.withAttributes(...) statements
      String withAttrCall = entry.getName() + ".with";
      String attrNameQuoted = "\"" + name + "\"";
      GenClass orCreate = getOrCreate(attribute.getClazz());
      Parser parser = orCreate.getOrCreateParser(rootDir);
      parser.parse();
      StatementEntry currentStatement = parser.getCurrentStatement();
      if (currentStatement == null)
         return false;
      StatementEntry parent = currentStatement.getParent();
      StatementEntrySet bodyStats = parent.getBodyStats();
      for (StatementEntry stat : bodyStats)
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
               i++;
            }
         }
      }

      return false;
   }

   private ArrayList<String> partIsAttribute(ArrayList<String> sequencePart)
   {
      ArrayList<String> attributeSequence = new ArrayList<>();

      if ("with".equals(sequencePart.get(0))
            && "[".equals(sequencePart.get(1))
            && "new".equals(sequencePart.get(2))
            && "Attribute".equals(sequencePart.get(3)))
      {
         attributeSequence.add(sequencePart.get(4));
         attributeSequence.add(sequencePart.get(6));
         return attributeSequence;
      }
      if ("withAttribute".equals(sequencePart.get(0)))
      {
         attributeSequence.add(sequencePart.get(1));
         attributeSequence.add(sequencePart.get(5));
         return attributeSequence;
      }
      return null;
   }

   public void removeAllGeneratedCode(String rootDir, String srcDir, String helpersDir)
   {
      turnRemoveCallToComment(rootDir);

      // now remove class file, creator file, and modelset file for each class
      // and the CreatorCreator
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
      
      String path = helpersDir + "/" + (model.getName() + UTILPATH).replaceAll("\\.", "/") + "/";

      String fileName = path + "CreatorCreator.java";

      deleteFile(fileName);
   }

   public void turnRemoveCallToComment(String rootDir)
   {
      int codeLineNumber = -1;
      String className = null;

      // first find the call to this method and make it a comment, to avoid
      // undesired execution on later runs.
      try
      {
         throw new RuntimeException();
      }
      catch (Exception e)
      {
         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[2];
         codeLineNumber = callEntry.getLineNumber();

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

               if (line == null)
                  break;

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

      String path = helpersDir + "/" + (packageName + UTILPATH).replaceAll("\\.", "/") + "/";

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

//      // CreatorCreator in that package
//      fileName = path + "CreatorCreator.java";
//      deleteFile(fileName);
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

   public DIFF getShowDiff()
   {
      return showDiff;
   }

   public GenClassModel withShowDiff(DIFF showDiff)
   {
      this.showDiff = showDiff;
      return this;
   }

   public GenClassModel withIgnoreClazz(String name)
   {
      if (this.ignoreDiff == null)
      {
         this.ignoreDiff = new ArrayList<String>();
      }
      this.ignoreDiff.add(name);
      return this;
   }

   public void doCoverageOfModelCode()
   {
      // try to create example object structure and use storyboard to do
      // coverage
      Clazz firstClazz = this.getModel().getClasses().first();

      try
      {
         // try to load creator class
         String creatorClassName = CGUtil.helperClassName(firstClazz.getFullName(), "Creator");
         Class<?> creatorClass = Class.forName(creatorClassName);
         java.lang.reflect.Method method = creatorClass.getMethod("createIdMap", String.class);
         JsonIdMap map = (JsonIdMap) method.invoke(null, "t");

         // now loop through model classes and create two objects for each class
         // using the corresponding creator class
         for (Clazz clazz : model.getClasses())
         {
            try
            {
               SendableEntityCreator creator = map.getCreator(clazz.getFullName(), true);

               Object object1 = creator.getSendableInstance(false);
               map.getId(object1);

               // try to add some attribute values
               for (Attribute attr : clazz.getAttributes())
               {
                  // for number and string attributes "42" might work.
                  try
                  {
                     creator.setValue(object1, attr.getName(), "42", "");
                  }
                  catch (Exception g)
                  {
                  }
               }

               // try to add role values
               for (Role role : clazz.getRoles())
               {
                  role = role.getPartnerRole();

                  Clazz partnerClazz = role.getClazz();

                  SendableEntityCreator partnerCreator = map.getCreator(partnerClazz.getFullName(), true);

                  Object partnerObject = partnerCreator.getSendableInstance(false);
                  map.getId(partnerObject);

                  creator.setValue(object1, role.getName(), partnerObject, "");

                  try
                  {
                     java.lang.reflect.Method createMethod = object1.getClass().getMethod(
                           "create" + StrUtil.upFirstChar(role.getName()));
                     createMethod.invoke(object1);
                  }
                  catch (Exception g)
                  {
                  }
               }

            }
            catch (Exception f)
            {
            }
         }

         // now ask the storyboad to do the coverage things
         Storyboard story = new Storyboard("coverage");
         story.coverSetAndPOClasses(map);
         story.coverSeldomModelMethods(map);
      }
      catch (Exception e)
      {
         // e.printStackTrace();
      }
   }

   /**
	 * Removes the associated class from the current model and deletes
	 * the generated code from the model and util classes.<br> 
	 * This includes the set, creator and pattern object classes, that are associated with the class.
	 * 
	 * 
	 * @param rootDir root directory, where the code of the associated class is located
	 */
   public void removeFromModelAndCode(Clazz model, String rootDir) {
		
 		for (Role role : model.getRoles()) {
 			
 			Clazz clazz = role.getPartnerRole().getClazz();
 			
 			GenClass partnerClass = this.getOrCreate(clazz);
 			
 			String helperClassName = CGUtil.helperClassName(model.getFullName(), "Set");
 			
 			String helpPoClassName = CGUtil.helperClassName(model.getFullName(), "PO");
 			
 			Parser partnerParser = partnerClass.getParser();
 			
 			partnerClass.removeFragment(partnerParser, Parser.IMPORT + ":" + model.getFullName());
 			
 			partnerClass.removeFragment(partnerParser, Parser.IMPORT + ":" + helperClassName);
 			
 			Parser partnerSetParser = partnerClass.getOrCreateParserForModelSetFile(rootDir);
 			
 			partnerClass.removeFragment(partnerSetParser, Parser.IMPORT + ":" + model.getFullName());
 			
 			partnerClass.removeFragment(partnerSetParser, Parser.IMPORT + ":" + helperClassName);
 			
 			Parser partnerPOParser = partnerClass.getOrCreateParserForPatternObjectFile(rootDir);
 			
 			partnerClass.removeFragment(partnerPOParser, Parser.IMPORT + ":" + model.getFullName());
 			
 			partnerClass.removeFragment(partnerPOParser, Parser.IMPORT + ":" + helpPoClassName);
 			
 			partnerClass.removeFragment(partnerPOParser, Parser.IMPORT + ":" + helperClassName);
 			
 			Parser partnerCreatorParser = partnerClass.getOrCreateParserForCreatorClass(rootDir);
 			
 			partnerClass.removeFragment(partnerCreatorParser, Parser.IMPORT + ":" + model.getFullName());
 			
 			partnerClass.removeFragment(partnerCreatorParser, Parser.IMPORT + ":" + helperClassName);
 			
 			role.getAssoc().removeFromModelAndCode(rootDir);
 			
 		}
 		
 		GenClass genClass = this.getOrCreate(model);
 		
 		genClass.removeGeneratedCode(rootDir);
    }
   public GenClass getGenerator(Object generator, String name)
   {
	   return getClazz(name);
   }
}
