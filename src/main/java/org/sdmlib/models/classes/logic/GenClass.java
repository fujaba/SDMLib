package org.sdmlib.models.classes.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;
import org.sdmlib.models.classes.templates.ReplaceText;
import org.sdmlib.models.classes.templates.Template;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.ClazzType;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.FeatureProperty;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Import;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.util.ClazzSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.list.SimpleSet;

/**
 * @author Stefan
 *
 */
public class GenClass extends GenClazzEntity
{
   public static final String PROPERTY_FILEPATH = "filePath";

   private String filePath;

   private LinkedHashMap<String, String> constantDecls = new LinkedHashMap<String, String>();


   private Parser patternObjectCreatorParser = null;
 

   public void generate(String rootDir, String helpersDir)
   {
      // first generate the class itself
	  ClassModel classModel = (ClassModel) model.getClassModel();
      if (!model.isExternal() && ! classModel.hasFeature(Feature.EMFSTYLE))
      {
         getOrCreateParser(rootDir);

         insertLicense(parser);

         insertInterfaces();

         insertConstants();

         insertImports();

         insertMethods(rootDir, helpersDir);

         if (GraphUtil.isInterface(model) == false)
         {
            insertSuperClass();
            insertPropertyChangeSupport(rootDir);
            
            if (classModel.hasFeature(Feature.REMOVEYOUMETHOD, model))
            	insertRemoveYouMethod(rootDir);
            
            insertInterfaceMethods(model, rootDir, helpersDir);
            

            if (classModel.hasFeature(Feature.SERIALIZATION, model))
               insertInterfaceAttributesInCreatorClass(model, rootDir, helpersDir);
         }

         generateAnnotations(rootDir, helpersDir);
         generateAttributes(rootDir, helpersDir, false);
         printFile();
      }
      else
      {
         generateAttributes(rootDir, helpersDir, false);
      }

      if (classModel.hasFeature(Feature.SERIALIZATION, model))
      { 
         // now generate the corresponding creator class
         if (getRepairClassModel().hasFeature(Feature.SERIALIZATION))
         {
            getOrCreateParserForCreatorClass(helpersDir);

            insertClassInCreatorCreatorClass(getModel(), rootDir, creatorParser);

            if (classModel.hasFeature(Feature.REMOVEYOUMETHOD, model)) {
            	insertRemoveObjectInCreatorClass();
            }
            printFile(creatorParser);
         }
      }

      // now generate the corresponding ModelSet class
      if (classModel.hasFeature(Feature.SERIALIZATION, model))
      {
         getOrCreateParserForModelSetFile(helpersDir);
         printFile(modelSetParser);

         if (getRepairClassModel().hasFeature(Feature.PATTERNOBJECT))
         {

            // now generate the corresponding PatterObject class
            getOrCreateParserForPatternObjectFile(helpersDir);
            printFile(patternObjectParser);
            
            // now generate the corresponding PatterObjectCreator class
            getOrCreateParserForPatternObjectCreatorFile(helpersDir);
            printFile(patternObjectCreatorParser);
         }

      }
   }

   private void insertMethods(String rootDir, String helpersDir)
   {
      for (Method method : model.getMethods())
      {
         getGenerator(method).generate(rootDir, helpersDir);

         String signature = method.getName(false); // TODO: this signature contains parameter name, the parser signature not.
         parser.parse();
         ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(signature);

         if (symTabEntries.size() > 0)
         {
            SymTabEntry symTabEntry = symTabEntries.get(0);
            parser.parseMethodBody(symTabEntry);
            LinkedHashMap<String, LocalVarTableEntry> localVarTable = parser.getLocalVarTable();
            String[] array = localVarTable.keySet().toArray(new String[0]);
            for (String key : array)
            {
               LocalVarTableEntry localVarTableEntry = localVarTable.get(key);
               if (localVarTableEntry == null)
               {
                  continue;
               }
               String type = localVarTableEntry.getType();
               SimpleSet<Clazz> classes = this.getModel().getClassModel().getClazzes();
               for (Clazz clazz : classes)
               {
                  if (clazz.getName().equals(type) || clazz.getName().endsWith("." + type))
                  {
                     insertImport(clazz.getName(false));
                  }
               }
            }
         }
      }
   }

   private void generateAnnotations(String rootDir, String helpersDir)
   {
      for (Annotation annotation : GraphUtil.getAnnotations(model))
      {
         getGenerator(annotation).generate(rootDir, helpersDir);
      }
   }

   private void insertImports()
   {
      for (Import importClazz : model.getImports())
      {
         insertImport(importClazz.getName());
      }
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
         String string = Parser.ATTRIBUTE + ":" + constName;
         SymTabEntry symTabEntry = parser.getSymTab().get(string);

         if (symTabEntry == null)
         {
            parser.insert(endOfClass, constantDecls.get(constName));
         }
      }
   }



   private void generateAttributes(String rootDir, String helpersDir, boolean fromSuperClass)
   {
      for (Attribute attr : model.getAttributes())
      {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         
         getGenerator(attr).generate(rootDir, helpersDir, fromSuperClass);
      }
      ClazzSet superClazzes = model.getSuperClazzes(false);

      if (superClazzes.size()>0)
      {
         gernerateSuperAttributes(superClazzes.first(), rootDir, helpersDir);
      }

      for (Clazz interfaze : model.getInterfaces(false))
      {
         gernerateSuperAttributes(interfaze, rootDir, helpersDir);
      }
   }

   private void gernerateSuperAttributes(Clazz superClazz, String rootDir, String helpersDir)
   {

      for (Attribute attr : superClazz.getAttributes())
      {
         if ("PropertyChangeSupport".equals(attr.getType()))
            continue;
         GenAttribute generator = getGenerator(attr);
         if (generator != null)
         {
            generator.generate(model, rootDir, helpersDir, true);
         }
      }
      ClazzSet superClazzes = superClazz.getSuperClazzes(false);
      if (superClazzes.size()>0)
      {
         gernerateSuperAttributes(superClazzes.first(), rootDir, helpersDir);
      }

      for (Clazz interfaze : superClazz.getInterfaces(false))
      {
         gernerateSuperAttributes(interfaze, rootDir, helpersDir);
      }
   }

   private void insertInterfaceAttributesInCreatorClass(Clazz clazz, String rootDir, String helpersDir)
   {
      for (Clazz interfaze : clazz.getInterfaces(false))
      {
         if (GraphUtil.isInterface(interfaze))
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               Parser creatorParser = this.getOrCreateParserForCreatorClass(helpersDir);
               getGenerator(attr).insertPropertyInCreatorClass(interfaze.getName(false), creatorParser, helpersDir,
                  false);
            }

         }

         insertInterfaceAttributesInCreatorClass(interfaze, rootDir, helpersDir);

      }
   }

	private void insertClassInCreatorCreatorClass(Clazz clazz, String rootDir, Parser creatorParser) {
//		if (GraphUtil.isInterface(clazz) == false && GraphUtil.isEnumeration(clazz) == false && ((ClassModel) clazz.getClassModel()).hasFeature(Feature.Serialization)) {
		ClassModel model = (ClassModel) clazz.getClassModel();
		if(model.hasFeature(Feature.SERIALIZATION) == true && model.hasFeature(Feature.STANDALONE) == false && clazz.getType().equals(ClazzType.INTERFACE) == false) {
			String creatorName = "";
			if (clazz.isExternal()) {
				ClassModelAdapter generator = ((ClassModel) clazz.getClassModel()).getGenerator();
				creatorName = clazz.getClassModel().getName() + GenClassModel.UTILPATH + "."
						+ CGUtil.shortClassName(clazz.getName(false));
				GenClazzEntity genClass = generator.getOrCreate(clazz);
				Parser creatorClassParser = genClass.getOrCreateParserForCreatorClass(rootDir);
				String string = creatorClassParser.getFileName();
				String alternativeFilePath = string
						.substring(rootDir.length() + 1, string.length() - "Creator.java".length())
						.replaceAll("/", ".");

				if (!creatorName.equals(alternativeFilePath))
					creatorName = alternativeFilePath;

			} else {
				creatorName = CGUtil.packageName(clazz.getName(false)) + GenClassModel.UTILPATH + "."
						+ CGUtil.shortClassName(clazz.getName(false));
			}

			Parser creatorcreator = getOrCreateCreatorCreator(clazz, rootDir);
			boolean isMulti =isMultiCreator(creatorcreator);
			if(isMulti){
				if(!creatorcreator.getClassModifier().contains("public")) {
					CGUtil.replaceAll(creatorcreator.getFileBody(), "class CreatorCreator", "public class CreatorCreator");
					creatorcreator.withFileChanged(true);
				}
			}else {
				creatorName = CGUtil.shortClassName(creatorName);
			}
			StringBuilder creators=new StringBuilder();
			creators.append("      jsonIdMap.with(new " + creatorName + "Creator());\n");
			if (((ClassModel) clazz.getClassModel()).hasFeature(Feature.PATTERNOBJECT)) {
				creators.append("      jsonIdMap.with(new " + creatorName + "POCreator());\n");
			}
			ArrayList<SymTabEntry> symTabEntriesFor = creatorcreator.getSymTabEntriesFor("createIdMap(String)");
			if(symTabEntriesFor.size()>0) {
				SymTabEntry symTabEntry = symTabEntriesFor.get(0);
				String lines = creatorcreator.getFileBody().substring(symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
				String newCreators = creators.toString();
				if (lines.indexOf(newCreators) < 0) 
				{
				   lines = CGUtil.replaceAll(lines, "      return jsonIdMap;", creators+"      return jsonIdMap;");
				   creatorcreator.replace(symTabEntry.getBodyStartPos(), symTabEntry.getEndPos(), lines);
				   creatorcreator.withFileChanged(true);
				   printFile(creatorcreator);
				}
			}
		}
	}
	final String searchString="jsonIdMap.withCreator(new ";
	private boolean isMultiCreator(Parser creatorcreator) {
		creatorcreator.indexOf(Parser.CLASS_END);
		ArrayList<SymTabEntry> symTabEntriesFor = creatorcreator.getSymTabEntriesFor("createIdMap(String)");
		if(symTabEntriesFor.size()>0) {
			SymTabEntry symTabEntry = symTabEntriesFor.get(0);
			String[] lines = creatorcreator.getFileBody().substring(symTabEntry.getBodyStartPos(), symTabEntry.getEndPos()).split(StrUtil.LF);

			String lastOne = null;
			for(String line : lines) {
				line = line.trim();
				if(line.indexOf(searchString)>0 && line.lastIndexOf(".")>searchString.length()) {
					String packageName=line.substring(searchString.length(), line.lastIndexOf("."));
					if(packageName.endsWith(GenClassModel.UTILPATH)) {
						packageName = packageName.substring(0, packageName.length()- GenClassModel.UTILPATH.length());
					}
					if(lastOne != null && !lastOne.equals(packageName)) {
						return true;
					}
					lastOne = packageName;
				}
			}
		}
		return false;
	}
   
	private Parser getOrCreateCreatorCreator(Clazz clazz, String rootDir) {
		ClassModel classModel = (ClassModel) clazz.getClassModel();
		String packageName = classModel.getName();
		// BOAH check DEFAULT PACKAGE
		if (ClassModel.DEFAULTPACKAGE.equals(packageName)) {
			// Get Package from clazz
			packageName = CGUtil.packageName(clazz.getName(false));
			if(packageName.length() < 1) {
				packageName = ClassModel.DEFAULTPACKAGE; 
			}
		}
	    String creatorCreatorClassName = packageName + GenClassModel.UTILPATH + ".CreatorCreator";
	    String fileName = creatorCreatorClassName;
	      fileName = fileName.replaceAll("\\.", "/");
	      fileName = rootDir + "/" + fileName + ".java";
	      Parser creatorCreator = new Parser().withFileName(fileName);
	      // found old one?
	      if (!creatorCreator.loadFile())
	      {
	    	  creatorCreator.withFileBody(
	               new StringBuilder(
	                     "package "+packageName+GenClassModel.UTILPATH+";\n\n"
	                           + "import " + IdMap.class.getName() + ";\n"
	                           +
	                           "\n"
	                           +
	                           "class CreatorCreator{\n" +
	                           "\n" +
	                           "   public static IdMap createIdMap(String session)\n" +
	                           "   {\n" +
	                           "      IdMap jsonIdMap = new IdMap().withSession(session);\n" +
	                           "      return jsonIdMap;\n" +
	                           "   }\n" +
	                           "}\n")
	               );
	    	 creatorCreator.insertImport(IdMap.class.getName());
	      }
	      return creatorCreator;
	}
	
	
   private void insertInterfaceMethods(Clazz clazz, String rootDir, String helpersDir)
   {
      for (Clazz interfaze : clazz.getInterfaces(false))
      {
         if (GraphUtil.isInterface(interfaze))
         {
            for (Attribute attr : interfaze.getAttributes())
            {
               getGenerator(attr).generate(model, rootDir, helpersDir);
            }

            for (Method method : interfaze.getMethods())
            {
               method.with(new Annotation("Override"));
               getGenerator(method).generateClazz(model, rootDir, helpersDir);
            }
          
            for (Association assoc : interfaze.getAssociations()) {
            	if (assoc.getOther().getType().equals(AssociationTypes.IMPLEMENTS) == false) {
            		assoc.with(new Annotation("Override"));
            		GenAssociation generator = getGenerator(assoc);
            		generator.generate(clazz, rootDir, helpersDir, assoc.getOther(), false);
            		generator.fixSubclasses(assoc, rootDir, helpersDir);
            	}
            }
            
         }

         insertInterfaceMethods(interfaze, rootDir, helpersDir);

      }
   }

   private void insertInterfaces()
   {

      String string = Parser.IMPLEMENTS;
      if (GraphUtil.isInterface(model))
         string = Parser.EXTENDS;

      for (Clazz interfaze : model.getInterfaces(false))
      {
         int extendsPos = parser.indexOf(string);

         if (extendsPos < 0)
         {
            extendsPos = parser.getEndOfClassName();

            parser.insert(extendsPos + 1,
               " " + string + " " + CGUtil.shortClassName(interfaze.getName(false)));

            insertImport(interfaze.getName(false));
         }
         else
         {
            String shortClassName = CGUtil.shortClassName(interfaze.getName(false));

            String key = string + ":" + shortClassName;

            SymTabEntry symTabEntry = parser.getSymTab().get(key);

            if (symTabEntry == null)
            {
               parser.insert(parser.getEndOfImplementsClause() + 1, ", " + shortClassName);
               insertImport(interfaze.getName(false));
            }
         }
      }      
   }

   private void insertSuperClass()
   {
	   ClazzSet superClazzes = model.getSuperClazzes(false);
      if (superClazzes.size()<1)
      {
         return;
      }

      String searchString = Parser.EXTENDS;
      int extendsPos = parser.indexOf(searchString);

      if (extendsPos < 0)
      {
         extendsPos = parser.getEndOfClassName();

         parser.insert(extendsPos + 1,
            " extends " + CGUtil.shortClassName(superClazzes.first().getName(false)));

         insertImport(superClazzes.first().getName(false));
      }
   }

   private void insertRemoveObjectInCreatorClass()
   {
	  if (GraphUtil.isInterface(model) == true || !getRepairClassModel().hasFeature(Feature.PROPERTYCHANGESUPPORT)) 
//      if (!getRepairClassModel().hasFeature(Feature.PropertyChangeSupport))
      {
         return;
      }
      
      // add removeObject method
      Template template = new Template(Parser.METHOD + ":removeObject(Object)");
      template.withTemplate("\n   " +
              "\n   //==========================================================================" +
              "\n   " +
              "   public void removeObject(Object entity)\n" +
              "   {\n" +
              "      {{Body}}\n" +
              "   }" +
              "\n");
        template.withVariable(new ReplaceText("Body", model.isExternal(), "// wrapped object has no removeYou method", "(({{ModelClass}}) entity).removeYou();"));
    	template.insert(creatorParser, "ModelClass", CGUtil.shortClassName(model.getName(false)));
   }

   private void insertRemoveYouMethod(String rootDir)
   {
	   // TODO : alternative removeYou() 
		String propChSupport = "firePropertyChange(\"REMOVE_YOU\", this, null);";
		if (!getRepairClassModel().hasFeature(Feature.PROPERTYCHANGESUPPORT)) {
			// return;
			propChSupport = "";
		}
		Template template = new Template(Parser.METHOD + ":removeYou()");
		// add removeYou method
		String overrideText = "";
		ClazzSet superClazzes = model.getSuperClazzes(true);
		superClazzes.without(model);
		for (Clazz clazz : superClazzes) {
			if (GraphUtil.isInterface(clazz)) {
				continue;
			}
			if (!clazz.isExternal()) {
				overrideText = "@Override";
			}
			if (getGenerator(clazz).getOrCreateParser(rootDir).indexOf(searchString) >= 0) {
				overrideText = "@Override";
				break;
			}
		}
		template.withTemplate("\n   " +
	               "\n   //==========================================================================" +
	               "\n   " +
	               "\n   {{Override}}" +
	               "\n   public void removeYou()" +
	               "\n   {" +
	               "\n      " + propChSupport +              
	               "\n   }" +
	               "\n");
		template.insert(parser, "Override",  overrideText);
   }

   private void insertPropertyChangeSupport(String rootDir)
   {
      if (!getRepairClassModel().hasFeature(Feature.PROPERTYCHANGESUPPORT))
      {
         return;
      }

      String searchString = Parser.METHOD + ":firePropertyChange(String,Object,Object)";
      // Check if no super has PropertyChange
      ClazzSet superClazzes = model.getSuperClazzes(true);
      superClazzes.without(model);
      for (Clazz clazz : superClazzes) {
         if (GraphUtil.isInterface(clazz))
         {
            continue;
         }
         if (!clazz.isExternal())
         {
            return;
         }
         if (getGenerator(clazz).getOrCreateParser(rootDir).indexOf(searchString) >= 0)
         {
            return;
         }
      }

      insertImplementsClauseForPropertyChangeInterface();

      // does it implement PropertyChangeSupportClient?

      searchString = Parser.ATTRIBUTE + ":listeners";
      int pos = parser.indexOf(searchString);
//TODO CHANGE
      if (pos < 0)
      {
         // add property change implementation
         parser.replaceAll(
            "\n   " +
               "\n   //==========================================================================" +
               "\n   " +
               "\n   protected PropertyChangeSupport listeners = null;" +
               "\n   "
        	);
         
      }
      
      searchString = Parser.METHOD + ":firePropertyChange(String,Object,Object)";
      pos = parser.indexOf(searchString);
      
      if (pos < 0) {
    	  parser.replaceAll(
    			  "\n   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)" +
                  "\n   {" +
                  "\n      if (listeners != null) {" +
                  "\n   		listeners.firePropertyChange(propertyName, oldValue, newValue);" +
                  "\n   		return true;" +
                  "\n   	}" +
                  "\n   	return false;" +
                  "\n   }" +
                  "\n   "
    		);
      }
      
      searchString = Parser.METHOD + ":addPropertyChangeListener(PropertyChangeListener)";
      pos = parser.indexOf(searchString);
      
      if (pos < 0) {
    	  parser.replaceAll(
    			  "\n   public boolean addPropertyChangeListener(PropertyChangeListener listener) " +
    		      "\n   {" +
    		      "\n   	if (listeners == null) {" +
    		      "\n   		listeners = new PropertyChangeSupport(this);" +
    		      "\n   	}" +
    		      "\n   	listeners.addPropertyChangeListener(listener);" +
    		      "\n   	return true;" +
    		      "\n   }" +
    		      "\n   "
    		);
      }
    	
      searchString = Parser.METHOD + ":addPropertyChangeListener(String,PropertyChangeListener)";
      pos = parser.indexOf(searchString);
      
      if (pos < 0) {
    	  parser.replaceAll(
    			  "\n   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {" +
    					  "\n   	if (listeners == null) {" +
    					  "\n   		listeners = new PropertyChangeSupport(this);" +
    					  "\n   	}" +
    					  "\n   	listeners.addPropertyChangeListener(propertyName, listener);" +
    					  "\n   	return true;" +
    					  "\n   }"+
    					  "\n   "
    		);
      }

      searchString = Parser.METHOD + ":removePropertyChangeListener(PropertyChangeListener)";
      pos = parser.indexOf(searchString);
      
      if (pos < 0) {
    	  parser.replaceAll(
    			  "\n   public boolean removePropertyChangeListener(PropertyChangeListener listener) {" +
    					  "\n   	if (listeners == null) {" +
    					  "\n   		listeners.removePropertyChangeListener(listener);" +
    					  "\n   	}" +
    					  "\n   	listeners.removePropertyChangeListener(listener);" +
    					  "\n   	return true;" +
    					  "\n   }"+
    					  "\n"
    			  );
      }
      searchString = Parser.METHOD + ":removePropertyChangeListener(String,PropertyChangeListener)";
      pos = parser.indexOf(searchString);
      
      if (pos < 0) {
    	  parser.replaceAll(
    			  "\n   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {" +
    					  "\n   	if (listeners != null) {" +
    					  "\n   		listeners.removePropertyChangeListener(propertyName, listener);" +
    					  "\n   	}" +
    					  "\n   	return true;" +
    					  "\n   }"+
    					  "\n"
    			  );
      }
      
      insertImport(PropertyChangeSupport.class.getName());
      insertImport(PropertyChangeListener.class.getName());
   }

   private void insertImplementsClauseForPropertyChangeInterface()
   {
	   if(getRepairClassModel().hasFeature(Feature.STANDALONE)) {
		   return;
	   }
      String searchString = Parser.IMPLEMENTS;
      int implementsPos = parser.indexOf(searchString);
      String propertyChangeInterface = SendableEntity.class.getSimpleName();

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
         if (GraphUtil.isInterface(model))
            string = " extends ";
         parser.insert(implementsPos + 1, string + propertyChangeInterface);
         insertImport(SendableEntity.class.getName());
      }
      else
      {
         // there is already an implements clause, does it already implement
         // PropertyChangeInterface?
         SymTabEntry symTabEntry = parser.getSymTab().get(Parser.IMPLEMENTS + ":" + propertyChangeInterface);

         if (symTabEntry == null)
         {
            // propertyChangeClients is still missing.
            parser.insert(parser.getEndOfImplementsClause() + 1,
               ", " + propertyChangeInterface);
         }

         insertImport(SendableEntity.class.getName());
      }
   }

   public void printFile(Parser parser)
   {
      if (parser == null)
      {
         return;
      }
      if (!isShowDiff())
      {
         CGUtil.printFile(parser);
      }
   }

   public Parser getOrCreateParser(String rootDir)
   {
      if (parser == null)
      {
    	
         // try to find existing file
         String name = model.getName(false);
         
         int pos = name.lastIndexOf('.');

         String packageName = "";
         if (pos >= 0)
         {
            packageName = name.substring(0, pos);
         }
         String fileName = name;

         String className = name.substring(pos + 1);

         String abztract = "";
         if(model.getModifier().has(Modifier.ABSTRACT)) {
            abztract = "abstract";
         }
         
         fileName = fileName.replaceAll("\\.", "/");

         fileName = rootDir + "/" + fileName + ".java";

         File javaFile = new File(fileName);

         parser = new Parser().withFileName(fileName);
         // found old one?
         if (javaFile.exists() && !isShowDiff())
         {
            parser.withFileBody(CGUtil.readFile(javaFile));
         }
         else
         {
            parser.replaceAll("" +
               "package packageName;\n" +
               "\n" +
               "public abztract clazz className\n" +
               "{\n" +
               "}\n",
               "abztract", abztract,
               "className", className,
               "packageName", packageName,
               "clazz", (GraphUtil.isInterface(model) ? "interface" : "class")
               );
            parser.withFileChanged(true);
         }
      }

      return parser;
   }

   public Parser getOrCreateParserForPatternObjectCreatorFile(String rootDir)
   {
      if (!getRepairClassModel().hasFeature(Feature.SERIALIZATION))
      {
         return null;
      }

      if (model.getType().equals(ClazzType.INTERFACE)) {
    	  return null;
      }
      
      if (patternObjectCreatorParser == null)
      {
         // try to find existing file
         String name = model.getName(false);
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

         FeatureProperty feature = ((ClassModel) model.getClassModel()).getFeature(Feature.SERIALIZATION);
         if (!patternObjectCreatorJavaFile.exists() && feature != null)
         {
            List<String> featureSet = feature.getPath();

            for (String featureValue : featureSet)
            {
               String alternativePackageName = featureValue;
               String alternativeFileName = alternativePackageName + "." + patternObjectCreatorClassName;
               alternativeFileName = alternativeFileName.replaceAll("\\.", "/");
               alternativeFileName = rootDir + "/" + alternativeFileName + ".java";
               File alternativeJavaFile = new File(alternativeFileName);

               if (alternativeJavaFile.exists())
               {
                  fileName = alternativeFileName;
                  patternObjectCreatorJavaFile = alternativeJavaFile;
                  break;
               }
            }
         }

         patternObjectCreatorParser = new Parser()
            .withFileName(fileName);
         // found old one?
         boolean addImport = false;
         if (patternObjectCreatorJavaFile.exists() && !isShowDiff())
         {
            patternObjectCreatorParser.withFileBody(CGUtil.readFile(patternObjectCreatorJavaFile));
         }
         else
         {
            StringBuilder text = new StringBuilder(
                  "package packageName;\n" +
                     "\n" +
                     "import org.sdmlib.models.pattern.util.PatternObjectCreator;\n" +
                     "import " + IdMap.class.getName() + ";\n" +
                     "\n" +
                     "public class patternObjectCreatorClassName extends PatternObjectCreator\n" +
                     "{\n" +
                     "   @Override\n" +
                     "   public Object getSendableInstance(boolean reference)\n" +
                     "   {\n" +
                     "      if(reference) {\n" +
                     "          return new entitiyPOClassName(new entitiyClassName[]{});\n" +
                     "      } else {\n" +
                     "          return new entitiyPOClassName();\n" +
                     "      }\n" +
                     "   }\n" +
                     "   \n" +
                     "   public static IdMap createIdMap(String sessionID) {\n" +
                     "      return ClassModelPackageCreatorCreator.createIdMap(sessionID);\n" +
                     "   }\n" +
                     "}\n");

            CGUtil.replaceAll(text,
               "patternObjectCreatorClassName", patternObjectCreatorClassName,
               "entitiyPOClassName", entitiyClassName + "PO",
               "entitiyClassName", entitiyClassName,
               "packageName", packageName,
               "ClassModelPackage", model.getClassModel().getName() + ".util.");

            patternObjectCreatorParser.withFileBody(text).withFileChanged(true);
            addImport = true;
         }

         if (addImport)
         {
        	 patternObjectCreatorParser.insertImport(name);
         }
      }

      return modelSetParser;
   }

   public void insertCreatorClassInCreatorCreator(Parser ccParser)
   {
      int pos = ccParser.indexOf(Parser.METHOD + ":getCreatorSet()");

      String name = model.getName(false);

      if (pos < 0)
      {
         // ups, did not find createIdMap method.
         //         System.err.println("Warning: SDMLib codgen for creatorSet initialisation invocation " + name
         //            + "Creator for class " + ccParser.getFileName()
         //            + ": \nDid not find method getCreatorSet(). Should have been generated by my model. "
         //            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me.
      int methodBodyStartPos = ccParser.getMethodBodyStartPos();
      String shortCreatorClassName = CGUtil.shortClassName(name) + "Creator";
      String shortCreatorPOClassName = CGUtil.shortClassName(name) + "POCreator";
      String creatorClassName = CGUtil.packageName(name) + GenClassModel.UTILPATH + "." + shortCreatorClassName;
      String creatorPOClassName = CGUtil.packageName(name) + GenClassModel.UTILPATH + "." + shortCreatorPOClassName;

      if (model.isExternal())
      {
         // generate creator for external class. Put it in the model package
         creatorClassName = getRepairClassModel().getName() + GenClassModel.UTILPATH + "." + shortCreatorClassName;
         creatorPOClassName = getRepairClassModel().getName() + GenClassModel.UTILPATH + "." + shortCreatorPOClassName;
      }

      pos = ccParser.methodBodyIndexOf(Parser.NAME_TOKEN + ":" + shortCreatorClassName, methodBodyStartPos);

      if (pos < 0)
      {
         ccParser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int addCreatorPos = ccParser.search("creatorSet.addAll", methodBodyStartPos);

         StringBuilder text = new StringBuilder
               ("creatorSet.add(new ClassCreator());\n" +
                  "         creatorSet.add(new ClassPOCreator());\n         "
               );

         CGUtil.replaceAll(text,
            "ClassCreator", creatorClassName,
            "ClassPOCreator", creatorPOClassName
            );

         ccParser.insert(addCreatorPos, text.toString());
         // getClassModel().getGenerator().setFileHasChanged(true);
      }

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

   // ==========================================================================
   public String getFilePath()
   {
      return this.filePath;
   }

   public void setFilePath(String value)
   {
      if (!StrUtil.stringEquals(this.filePath, value))
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
      int count = 0;
      count += showDiffForParser(parser, diff, ignoreDiff);
      count += showDiffForParser(creatorParser, diff, ignoreDiff);
      count += showDiffForParser(modelSetParser, diff, ignoreDiff);
      count += showDiffForParser(patternObjectParser, diff, ignoreDiff);
      count += showDiffForParser(patternObjectCreatorParser, diff, ignoreDiff);
      return count;
   }

   private int showDiffForParser(Parser newFileParser, DIFF diff, List<String> ignoreDiff)
   {
      int count = 0;
      if (newFileParser == null)
      {
         return 0;
      }
      File file = new File(newFileParser.getFileName());
      if (file.exists())
      {
         Parser oldFileParser = new Parser().withFileName(newFileParser.getFileName());
         oldFileParser.withFileBody(CGUtil.readFile(file));
         oldFileParser.parse();

         newFileParser.parse();
         // Diff Methods
         Map<String, SymTabEntry> oldSymTab = oldFileParser.getSymTab();

         String packageName = CGUtil.packageName(this.model.getName(false));
         String shortName = newFileParser.getFileName().replace("\\", ".").replace("/", ".");
         shortName = shortName.substring(shortName.indexOf(packageName));

         if (ignoreDiff != null && ignoreDiff.contains(shortName.substring(0, shortName.length() - 5)))
         {
            return 0;
         }

         for (Iterator<Entry<String, SymTabEntry>> i = newFileParser.getSymTab().entrySet().iterator(); i.hasNext();)
         {
            Entry<String, SymTabEntry> item = i.next();
            if (item.getValue().getMemberName().startsWith(Parser.IMPORT))
            {
               // ignore Import
               continue;
            }
            if (item.getKey().startsWith(Parser.EXTENDS) || item.getKey().startsWith(Parser.IMPLEMENTS))
            {
               // ignore Import
               continue;
            }
            if (!oldSymTab.containsKey(item.getKey()))
            {
               if (diff == DIFF.FULL)
               {
                  System.err.println(file.getAbsolutePath() + ";" + item.getKey() + ";Method not found");
               }
               continue;
            }
            SymTabEntry oldValue = oldSymTab.get(item.getKey());
            int oldValueLen = oldValue.getEndPos() - oldValue.getStartPos();
            SymTabEntry newValue = item.getValue();
            int newValueLen = newValue.getEndPos() - newValue.getStartPos();

            String oldStrValue = oldFileParser.getText().substring(oldValue.getStartPos(), oldValue.getEndPos() + 1)
               .trim();
            String newStrValue = newFileParser.getText().substring(newValue.getStartPos(), newValue.getEndPos() + 1)
               .trim();

            String[] oldSplit = oldStrValue.split("\\s+");
            String[] newSplit = newStrValue.split("\\s+");

            boolean foundDiff = oldSplit.length != newSplit.length;
            String diffToken = "";

            for (int j = 0; j < oldSplit.length && !foundDiff; j++)
            {
               if (!oldSplit[j].equals(newSplit[j]))
               {
                  foundDiff = true;
                  diffToken = oldSplit[j] + " != " + newSplit[j];
                  break;
               }
            }

            if (foundDiff)
            {
               System.err.println(file.getPath() + ";" + item.getKey() + ";Body different:" + oldValueLen + "!="
                  + newValueLen + ";");
               System.err.println("in line:" + oldFileParser.getLineIndexOf(oldValue.getStartPos()) + "-"
                  + oldFileParser.getLineIndexOf(oldValue.getEndPos()) + ";");
               System.err.println("(" + shortName + ":" + oldFileParser.getLineIndexOf(oldValue.getStartPos()) + ")");
               // System.out.println(oldStrValue);
               // System.out.println("--------------------------------------------------------------------------------------------------");
               System.err.println(newStrValue);
               System.err.println("diff token: " + diffToken);

               count += 1;
               continue;
            }
         }
      }
      else if (diff == DIFF.FULL)
      {
         System.err.println(file.getAbsolutePath() + ";;File not Found!!!");

      }
      return count;
   }

   @Override
   public String toString()
   {
      return "gen " + model;
   }

   /**
    * Deletes the generated code of the associated class, within the corresponding model, set, creator and pattern object classes.
    * Also removes the class file itself.
    * 
    * @param rootDir root directory, where the code of the associated class is located
    */
   public void removeGeneratedCode(String rootDir) {

	   Parser creatorCreatorParser = this.getOrCreateCreatorCreator(this.getModel(), rootDir);
	   
	   String className = StrUtil.upFirstChar(this.getModel().getName());
	   
	   this.removeLineFromFragment(creatorCreatorParser, Parser.METHOD + ":createIdMap(String)", className, className);
	   
	   this.removeLineFromFragment(creatorCreatorParser, Parser.METHOD + ":createIdMap(String)", className, className);
	   
	   CGUtil.printFile(creatorCreatorParser);
	   
	   this.removeAllGeneratedCode(rootDir, rootDir, rootDir);
	   
   }

	@Override
	ClassModel getClazz() {
		return (ClassModel) getModel().getClassModel();
	}
}
