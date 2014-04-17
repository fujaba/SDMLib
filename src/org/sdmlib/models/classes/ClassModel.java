/*
   Copyright (c) 2012 Albert Zündorf

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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.classes.creators.AssociationSet;
import org.sdmlib.models.classes.creators.ClazzSet;
import org.sdmlib.models.classes.creators.RoleSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class ClassModel implements PropertyChangeInterface
{
	public static ClassModel classModel = null;

	private Parser creatorCreatorParser;

	private Parser modelPatternParser;

	private boolean modelPatternFileHasChanged;

	public void setModelPatternFileHasChanged(boolean modelPatternFileHasChanged)
	{
		this.modelPatternFileHasChanged = modelPatternFileHasChanged;
	}

	private File javaFile;

	private StringBuilder fileBody;

	private boolean fileHasChanged;

	private String creatorCreatorClassName;

	private String modelPatternClassName;

	public void setFileHasChanged(boolean fileHasChanged)
	{
		this.fileHasChanged = fileHasChanged;
	}

	public ClassModel()
	{
		classModel = this;
	}

	public ClassModel(String packageName)
	{
		this();
		withPackageName(packageName);
	}

	public ClassModel generate()
	{
		return generate("src");
	}

	public ClassModel generate(String rootDir)
	{
		return generate(rootDir, rootDir);
	}

   public ClassModel generate(String rootDir, String helpersDir)
   {
      return generate(rootDir, helpersDir, rootDir);
   }
   
   public ClassModel generate(String rootDir, String helpersDir, String modelCreationDir)
	{
		resetParsers();
		
		addHelperClassesForUnknownAttributeTypes();

		generateCreatorCreatorClass(helpersDir);
		generateModelPatternClass(helpersDir);
		
		
		for (Clazz clazz : getClasses())
		{
			clazz.generate(rootDir, helpersDir);
		}

		for (Association assoc : getAssociations())
		{
			assoc.generate(rootDir, helpersDir, false);
		}

		Exception e = new RuntimeException();

      attributNameConsistenceCheck(e, modelCreationDir);
	   
		return this;
	}

	public void addHelperClassesForUnknownAttributeTypes()
   {
      // for attribute types like java.util.Date we add a class with that name and mark it as wrapped. This generates the required DateSet class.
      for (String typeName : this.getClasses().getAttributes().getType())
      {
         int pos = "int float double long String boolean Object java.util.Date".indexOf(typeName);
         
         if (pos < 0)
         {
            // seems to be a non trivial type. We should have a clazz with that type
            
            // it may be an instance of a generic type like ArrayList<String>, cut off generic part
            pos = typeName.indexOf('<');
            
            if (pos >= 0)
            {
               typeName = typeName.substring(0, pos);
            }
            
            Clazz clazz = this.getClazz(typeName);
            
            if (clazz == null)
            {
               // class is missing, create it.
               createClazz(typeName).withWrapped(true);
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
		
		
		Clazz modelCreationClass = new ClassModel(".").getOrCreateClazz(className);
		
		Parser parser = modelCreationClass.getOrCreateParser(rootDir);
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
		
		for (Clazz clazz : getClasses())
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
			
			RoleSet roles = clazz.getSourceRoles().getOtherRoles();

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
					String position = defPosition(parser, localVarTable, attribute.getName(), attribute.getType(), attribute.getClazz().getName() );
					
					System.err.println("in " + fileName + ":  duplicate name found in definition for " + attribute.getClazz() +"\n		"+ position+ "\n		Attribute " + attribute );
					attribute.removeYou();
				}
				else if(object instanceof Role) {
					Role role = (Role)object;
					String position = defPosition(parser, localVarTable, role.getName(), "NONE" ,role.getClazz().getName() );
					System.err.println("in " + fileName + "  duplicate name found in definition for "+ role.getClazz() + "\n	  	" + position + "\n		Role " + role );
					Association assoc = role.getAssoc();
					
					if(assoc != null)
						assoc.removeYou();
					
					if(role != null)
						role.removeYou();
				}
			}
		}			
	}

	private String defPosition(Parser parser, LinkedHashMap<String, LocalVarTableEntry> localVarTable, String name, String attrType , String clazzType) {
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

	private void resetParsers()
	{
		for (Clazz c : getClasses())
		{
			c.setParser(null);
		}
	}

	private void generateCreatorCreatorClass(String rootDir)
	{
		// take first class to find package

		getOrCreateCreatorCreatorParser(rootDir);

		for (Clazz clazz : getClasses())
		{
			if (!clazz.isInterfaze() && ! clazz.isExternal()) 
				clazz.insertCreatorClassInCreatorCreator(creatorCreatorParser);
		}

		printFile(fileHasChanged);
	}

	private void generateModelPatternClass(String rootDir)
	{
		// take first class to find package

		getOrCreateModelPatternParser(rootDir);

		for (Clazz clazz : getClasses())
		{
			if (! clazz.isExternal()) 
				clazz.insertHasMethodsInModelPattern(modelPatternParser);
		}

		printModelPatternFile(modelPatternFileHasChanged);
	}

	public void printFile(boolean really)
	{
		if (really)
		{
			CGUtil.printFile(javaFile, fileBody.toString());
		}
	}

	public void printModelPatternFile(boolean really)
	{
		if (really)
		{
			File modelPatternFile = new File(modelPatternParser.getFileName());
			CGUtil.printFile(modelPatternFile, modelPatternParser.getFileBody().toString());
		}
	}

	public String getCreatorCreatorClassName() {
		return creatorCreatorClassName;
	}

	public Parser getOrCreateCreatorCreatorParser(String rootDir)
	{
		if (!getClasses().isEmpty() && creatorCreatorParser == null)
		{
			String packageName = this.getPackageName();
			if (this.getPackageName() != null)
			{
				packageName = packageName + ".creators";
				creatorCreatorClassName = packageName + ".CreatorCreator";
			}
			else
			{
				// try to find existing file
				Clazz firstClass = getClasses().iterator().next();
				creatorCreatorClassName = firstClass.getName();
				int pos = creatorCreatorClassName.lastIndexOf('.');
				packageName = creatorCreatorClassName.substring(0, pos)
						+ ".creators";
				creatorCreatorClassName = packageName + ".CreatorCreator";
			}
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
								"package packageName;\n" + 
										"\n" + 
										"import java.util.LinkedHashSet;\n" + 
										"import org.sdmlib.serialization.interfaces.SendableEntityCreator;\n" + 
										"import org.sdmlib.serialization.json.JsonIdMap;\n" +
										"import org.sdmlib.serialization.json.SDMLibJsonIdMap;\n" + 
										"\n" + 
										"public class className\n" + 
										"{\n" + 
										"   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;\n" + 
										"   \n" +
										"   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()\n" + 
										"   {\n" + 
										"      if (creatorSet == null)\n" + 
										"      {\n" + 
										"         creatorSet = new LinkedHashSet<SendableEntityCreator>();\n" + 
										"         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());\n" + 
										"      }\n" + 
										"      \n" + 
										"      return creatorSet;\n" + 
										"   }\n" + 
										"\n" + 
										"   public static JsonIdMap createIdMap(String sessionID)\n" + 
										"   {\n" + 
										"      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);\n" + 
										"      \n" + 
										"      jsonIdMap.withCreator(getCreatorSet());\n" + 
										"\n" + 
										"      return jsonIdMap;\n" + 
										"   }\n" + 
								"}\n");

				CGUtil.replaceAll(text, "className", CGUtil.shortClassName(creatorCreatorClassName), "packageName", packageName);

				fileBody.append(text.toString());

				fileHasChanged = true;
			}

			creatorCreatorParser = new Parser().withFileName(fileName).withFileBody(fileBody);

		}

		return creatorCreatorParser;
	}

	public Parser getOrCreateModelPatternParser(String rootDir)
	{
		if (!getClasses().isEmpty() && modelPatternParser == null)
		{
			// try to find existing file
			String packageName = this.getPackageName();
			if (this.getPackageName() != null)
			{
				packageName = packageName + ".creators";
				modelPatternClassName = packageName + ".ModelPattern";
			}
			else
			{
				Clazz firstClass = getClasses().iterator().next();

				modelPatternClassName = firstClass.getName();

				int pos = modelPatternClassName.lastIndexOf('.');

				packageName = modelPatternClassName.substring(0, pos) + ".creators";

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
										"   \n" + 
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

	// ==========================================================================

	public static final String PROPERTY_CLASSES = "classes";

	private ClazzSet classes;

	public ClazzSet getClasses()
	{
		if (classes == null)
		{
			return Clazz.EMPTY_SET;
		}
		return this.classes;
	}
	
	public Clazz getClazz(String name)
	{
	   for (Clazz c : getClasses())
      {
         if (c.getName().endsWith(name))
         {
            return c;
         }
      }
	   return null;
	}

	public ClassModel withClasses(Clazz value)
	{
		addToClasses(value);
		return this;
	}

	public ClassModel withoutClasses(Clazz value)
	{
		removeFromClasses(value);
		return this;
	}

	public Clazz createClazz()
	{
		Clazz clazz = new Clazz();
		this.addToClasses(clazz);
		return clazz;
	}

	public Clazz createClazz(String name, String... attrNameTypePairs)
	{
		Clazz clazz = new Clazz(name, attrNameTypePairs);
		this.addToClasses(clazz);
		return clazz;
	}

	public void addToClasses(Clazz value)
	{
		if (this.classes == null)
		{
			this.classes = new ClazzSet();
		}

		this.classes.add(value);
	}

	public boolean removeFromClasses(Clazz value)
	{
		boolean changed = false;

		if ((this.classes != null) && (value != null))
		{
			changed = this.classes.remove(value);

			if (changed)
			{
				value.setClassModel(null);
				getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, value, null);
			}
		}

		return changed;
	}

	public void removeAllFromClasses()
	{
		LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getClasses());

		for (Clazz value : tmpSet)
		{
			this.removeFromClasses(value);
		}
	}

	public String dumpClassDiagram(String diagName)
	{
		return dumpClassDiagram("src", diagName);
	}

	public String dumpClassDiagram(String rootDir, String diagName)
	{
		JsonToGraphViz graphViz = new JsonToGraphViz().withRootDir(rootDir);
		
		return graphViz.dumpClassDiagram(rootDir, diagName, this);
	}

	public String labelForRole(Role role)
	{
		String result = role.getName();

		if (role.getCard().equals(R.MANY.toString()))
		{
			result = result + " *";
		}

		return result;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ClassModel ----------------------------------- Association
	 *              model                   associations
	 * </pre>
	 */

	public static final String PROPERTY_ASSOCIATIONS = "associations";

	private AssociationSet associations = null;

	public AssociationSet getAssociations()
	{
		if (this.associations == null)
		{
			return Association.EMPTY_SET;
		}

		return this.associations;
	}

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

			if (changed)
			{
				value.withModel(this);
				// getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS,
						// null, value);
			}
		}

		return changed;
	}

	public boolean removeFromAssociations(Association value)
	{
		boolean changed = false;

		if ((this.associations != null) && (value != null))
		{
			changed = this.associations.remove(value);

			if (changed)
			{
				value.setModel(null);
				// getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS,
				// null, value);
			}
		}

		return changed;
	}

	public ClassModel withAssociations(Association value)
	{
		addToAssociations(value);
		return this;
	}

	public ClassModel withoutAssociations(Association value)
	{
		removeFromAssociations(value);
		return this;
	}

	public void removeAllFromAssociations()
	{
		LinkedHashSet<Association> tmpSet = new LinkedHashSet<Association>(this.getAssociations());

		for (Association value : tmpSet)
		{
			this.removeFromAssociations(value);
		}
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

			if (getClasses().isEmpty()) {
				System.out.println("no class files found !!!! END");
				return;
			}			

			// parse each java file
			for (Clazz clazz : (ClazzSet) getClasses().clone())
			{
				handleMember(clazz, clazz.getFilePath());
			}
		}

		// add model creation code at invocation place, if not yet there
	}

	private Clazz handleMember(Clazz clazz, String rootDir)
	{
		System.out.println("parse " + clazz.getName());
		Parser parser = clazz.getOrCreateParser(rootDir);
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

		LinkedHashMap<SymTabEntry, String> attributes = new LinkedHashMap<SymTabEntry, String>();

		for (String memberName : symTab.keySet())
		{
			addMemberToModel(clazz, parser, attributes, memberName);
		}

		// add new assocs with roles,
		ArrayList<String> memberNames = new ArrayList<String>();
		//ArrayList<Attribute> assocWithoutPartnerClass = new ArrayList<Attribute>();
		for (SymTabEntry symTabEntry : attributes.keySet())
		{
			String memberName = symTabEntry.getMemberName();
			memberNames.add(memberName);
			String partnerTypeName = symTabEntry.getType();

			if (Attribute.COMPLEX.equals(attributes.get(symTabEntry)))
			{

				R card = findRoleCard(partnerTypeName);
				String partnerClassName = findPartnerClassName(partnerTypeName);
				Clazz partnerClass = findPartnerClass(partnerTypeName);
				String setterPrefix = findSetterPrefix(partnerTypeName);

				String name = StrUtil.upFirstChar(memberName);

				Method addToMethod = findMethod(clazz, setterPrefix + name + "(" + partnerClassName + ")");

				// type is unknown
				if (partnerClass == null || addToMethod == null) 
				{
					new Attribute()
					.withName(memberName)
					.withType(partnerTypeName)
					.withClazz(clazz);
					continue;
				}


				SymTabEntry addToSymTabEntry = symTab.get(Parser.METHOD + ":" + addToMethod.getSignature());

				if (addToSymTabEntry == null)
					continue;

				//				parser.methodBodyIndexOf(Parser.METHOD_END, addToSymTabEntry.getBodyStartPos());
				parser.parseMethodBody(addToSymTabEntry);

				LinkedHashSet<String> methodBodyQualifiedNames = new LinkedHashSet<String>(); // = parser.getMethodBodyQualifiedNames();
				for (String key : parser.getMethodBodyQualifiedNames())
				{
					methodBodyQualifiedNames.add(key);
				}

				boolean done = false;
				for (String qualifiedName : methodBodyQualifiedNames)
				{
					if (qualifiedName.startsWith("value.with"))
					{
						handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass, qualifiedName.substring("value.with".length()));
						done = true; 
						break;
					}
					else if (qualifiedName.startsWith("value.set"))
					{
						handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass, qualifiedName.substring("value.set".length()));
						done = true; 
						break;
					}
					else if (qualifiedName.startsWith("value.addTo"))
					{
						handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass, qualifiedName.substring("value.addTo".length()));
						done = true; 
						break;
					}
				}
				if ( ! done)
				{
					// did not find reverse role, add as attribute
					new Attribute()
					.withName(memberName)
					.withType(partnerTypeName)
					.withClazz(clazz);
				}

			}
		}

		// remove getter with setter or addTo removeFrom removeAllFrom without
		for (String memberName : memberNames)
		{
			// remove getter with setter or addTo removeFrom removeAllFrom without
			findAndRemoveMethods(clazz, memberName, "get set with without addTo create removeFrom removeAllFrom iteratorOf hasIn sizeOf removePropertyChange addPropertyChange");
			findAndRemoveAttributs(clazz, "listeners");
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

	private void handleAssoc(Clazz clazz, String rootDir, String memberName, R card, String partnerClassName, Clazz partnerClass, String partnerAttrName)
	{
		partnerAttrName = StrUtil.downFirstChar(partnerAttrName);
		Parser partnerParser = partnerClass.getOrCreateParser(rootDir); 
		String searchString = Parser.ATTRIBUTE + ":" + partnerAttrName;

		int attributePosition = partnerParser.indexOf(searchString);

		if (attributePosition > -1)
		{
			R partnerCard = findRoleCard(partnerParser, searchString);
			tryToCreateAssoc(clazz, memberName, card, partnerClassName, partnerClass, partnerAttrName, partnerCard);
		}
	}

	private void addMemberToModel(Clazz clazz, Parser parser, LinkedHashMap<SymTabEntry, String> attributes, String memberName)
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
			addMemberAsAttribut(clazz, attributes, attrName, symTabEntry);
		}

		// add super classes 
		if (memberName.startsWith(Parser.EXTENDS))
		{
			if (clazz.isInterfaze()) {
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

	private void addMemberAsAttribut(Clazz clazz, LinkedHashMap<SymTabEntry, String> attributes, String attrName, SymTabEntry symTabEntry)
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
				new Attribute().withClazz(clazz).withName(attrName).withType(symTabEntry.getType());
			}
			attributes.put(symTabEntry, Attribute.SIMPLE);
		}
		else
		{
			attributes.put(symTabEntry, Attribute.COMPLEX);
		}
	}

	private void addMemberAsSuperClass(Clazz clazz, String memberName, Parser parser)
	{
		Clazz memberClass = findMemberClass(clazz, memberName, parser);

		if (memberClass != null)
			clazz.setSuperClass(memberClass);
	}

	private void addMemberAsInterface(Clazz clazz, String memberName, Parser parser)
	{
		Clazz memberClass = findMemberClass(clazz, memberName, parser);
		
		if (memberClass != null) 
		{
		   memberClass.withInterfaze(true);

		   clazz.addToInterfaces(memberClass);
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
			      Clazz externClass = this.createClazz(importName).withExternal(true);
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
		
		String name = clazz.getName();
		int lastIndex = name.lastIndexOf('.');
		name = name.substring(0, lastIndex + 1) + signature;

		return findClassInModel(name);
	}

   private Clazz findClassInModel(String name)
   {
      LinkedHashSet<Clazz> classes = getClasses();

		for (Clazz eClazz : classes) {
			if (eClazz.getName().equals(name)) {
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
			.withClazz(clazz)
			.withSignature(signature)
			.withReturnType(split[2]);
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
				Clazz clazz = new Clazz(filePath).withFilePath(rootDir);
				classes.add(clazz);
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

	private R findRoleCard(Parser partnerParser, String searchString)
	{
		String partnerTypeName;
		SymTabEntry partnerSymTabEntry = partnerParser.getSymTab().get(searchString);
		partnerTypeName = partnerSymTabEntry.getType();

		return findRoleCard(partnerTypeName);
	}

	private R findRoleCard(String partnerTypeName)
	{
		R partnerCard = R.ONE;
		int _openAngleBracket = partnerTypeName.indexOf("<");
		int _closeAngleBracket = partnerTypeName.indexOf(">");
		if (_openAngleBracket > -1 && _closeAngleBracket > _openAngleBracket)
		{
			// partner to many
			partnerCard = R.MANY;
		}
		else if (partnerTypeName.endsWith("Set") && partnerTypeName.length() > 3)
		{
			// it might be a ModelSet. Look if it starts with a clazz name
			String prefix = partnerTypeName.substring(0, partnerTypeName.length() - 3);
			for (Clazz clazz : getClasses())
			{
				if (prefix.equals(CGUtil.shortClassName(clazz.getName())))
				{
					partnerCard = R.MANY;
					break;
				}
			}
		}
		return partnerCard;
	}

	private void tryToCreateAssoc(Clazz clazz, String memberName, R card, String partnerClassName, Clazz partnerClass, String partnerAttrName, R partnerCard)
	{
		Role sourceRole = new Role().withName(partnerAttrName).withClazz(clazz).withCard(partnerCard.toString());

		Role targetRole = new Role().withName(memberName).withClazz(partnerClass).withCard(card.toString());

		if (!assocWithRolesExists(sourceRole, targetRole))
		{
			new Association().withSource(sourceRole).withTarget(targetRole);

			clazz.addToSourceRoles(sourceRole);
			partnerClass.addToTargetRoles(targetRole);
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
		for (Clazz clazz : getClasses())
		{
			if (clazz.getName().equals(filePath))
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
		for (Clazz partnerClass : classes)
		{
			String shortClassName = CGUtil.shortClassName(partnerClass.getName());
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

	public Object get(String attrName)
	{
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0)
		{
			attribute = attrName.substring(0, pos);
		}

		if (PROPERTY_CLASSES.equalsIgnoreCase(attrName))
		{
			return getClasses();
		}

		if (PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName))
		{
			return getAssociations();
		}

		if (PROPERTY_PACKAGENAME.equalsIgnoreCase(attribute))
		{
			return getPackageName();
		}

		return null;
	}

	// ==========================================================================

	public boolean set(String attrName, Object value)
	{
		if (PROPERTY_CLASSES.equalsIgnoreCase(attrName))
		{
			addToClasses((Clazz) value);
			return true;
		}

		if ((PROPERTY_CLASSES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
		{
			removeFromClasses((Clazz) value);
			return true;
		}

		if (PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName))
		{
			addToAssociations((Association) value);
			return true;
		}

		if ((PROPERTY_ASSOCIATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
		{
			removeFromAssociations((Association) value);
			return true;
		}

		if (PROPERTY_PACKAGENAME.equalsIgnoreCase(attrName))
		{
			setPackageName((String) value);
			return true;
		}

		return false;
	}

	// ==========================================================================

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	private String currentTypeCard;

	public String getCurrentTypeCard()
	{
		return currentTypeCard;
	}

	public PropertyChangeSupport getPropertyChangeSupport()
	{
		return listeners;
	}

	// ==========================================================================

	public void removeYou()
	{
		removeAllFromClasses();
		removeAllFromAssociations();
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	public void insertModelCreationCodeHere(String rootDir)
	{
		String fileName = null;
		String className = null;
		String methodName = null;

		String callMethodName = null;
		int callMethodLineNumber = -1;

		Exception e = new RuntimeException();

		StackTraceElement[] stackTrace = e.getStackTrace();

		StackTraceElement firstStackTraceElement = stackTrace[0];
		callMethodName = firstStackTraceElement.getMethodName();

		StackTraceElement secondStackTraceElement = stackTrace[1];
		fileName = secondStackTraceElement.getFileName();
		className = secondStackTraceElement.getClassName();
		methodName = secondStackTraceElement.getMethodName();
		callMethodLineNumber = secondStackTraceElement.getLineNumber();


		// parse the model creation file
		Clazz modelCreationClass = getOrCreateClazz(className);

		creatorCreatorParser = modelCreationClass.getOrCreateParser(rootDir);

		modelCreationClass.getClassModel().removeFromClasses(modelCreationClass);
		
		String signature = Parser.METHOD + ":" + methodName + "(";

		rescanCode();

		SymTabEntry symTabEntry = creatorCreatorParser.getMethodEntryWithLineNumber(signature, callMethodLineNumber);

		if (symTabEntry == null) {
			System.out.println("call method for model creation code not found");
			return;
		}

		signature = creatorCreatorParser.getSignatureFor(symTabEntry);

		creatorCreatorParser.methodBodyIndexOf(Parser.METHOD_END, symTabEntry.getBodyStartPos());

		@SuppressWarnings("unchecked")
		LinkedHashMap<String, LocalVarTableEntry> localVarTable = (LinkedHashMap<String, LocalVarTableEntry>) creatorCreatorParser.getLocalVarTable().clone();
		// insert code
		int currentInsertPos = creatorCreatorParser.methodCallIndexOf(Parser.NAME_TOKEN + ":model", symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
		currentInsertPos = creatorCreatorParser.indexOfInMethodBody(Parser.NAME_TOKEN + ":;", currentInsertPos + 1, symTabEntry.getEndPos() - 1) + 1;

		currentInsertPos = completeCreationClasses(callMethodName, modelCreationClass, signature, localVarTable, currentInsertPos);

		currentInsertPos = insertNewCreationClasses(callMethodName, modelCreationClass, signature, localVarTable, currentInsertPos);

		writeToFile(modelCreationClass);

		creatorCreatorParser = null;
		creatorCreatorClassName = null;
	}

	private void writeToFile(Clazz modelCreationClass)
	{
		modelCreationClass.printFile(modelCreationClass.isFileHasChanged());
	}

	LinkedHashMap<String, Clazz> handledClazzes = new LinkedHashMap<String, Clazz>();

	private int insertNewCreationClasses(String callMethodName, Clazz modelCreationClass, String signature, LinkedHashMap<String, LocalVarTableEntry> localVarTable,
			int currentInsertPos)
	{

		Queue<Clazz> clazzQueue = new LinkedList<Clazz>(); 

		for (Clazz clazz : getClasses())
		{
			clazzQueue.offer(clazz);
		}

		boolean format = false;

		while (!clazzQueue.isEmpty())
		{
			Clazz clazz  = clazzQueue.poll();

			String modelClassName = clazz.getName();

			LocalVarTableEntry entry = findInLocalVarTable(localVarTable, modelClassName);

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
					currentInsertPos = createAndInsertCodeForNewClazz(callMethodName, modelCreationClass, refreshMethodScan(signature), clazz, handledClazzes, currentInsertPos);
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
			if (handledClazzes.get(depClazz.getName()) == null) {
				return false;
			}
		}  
		return true;
	}

	private int completeCreationClasses(String callMethodName, Clazz modelCreationClass, String signature, LinkedHashMap<String, LocalVarTableEntry> localVarTable,
			int currentInsertPos)
	{
		for (Clazz clazz : getClasses())
		{
			String modelClassName = clazz.getName();
			LocalVarTableEntry entry = findInLocalVarTable(localVarTable, modelClassName);

			if (entry != null)
			{
				// check code for clazz
				handledClazzes.put(modelClassName, clazz);
				currentInsertPos = checkCodeForClazz(entry, signature, callMethodName, modelCreationClass, refreshMethodScan(signature), clazz, handledClazzes, currentInsertPos);
			}
			writeToFile(modelCreationClass);

		}
		return currentInsertPos;
	}

	private int checkCodeForClazz(LocalVarTableEntry entry, String signature, String callMethodName, Clazz modelCreationClass, SymTabEntry symTabEntry, Clazz clazz,
			LinkedHashMap<String, Clazz> handledClazzes, int currentInsertPos)
	{
		rescanCode();

		// check has superclass
		if (clazz.getSuperClass() != null && !checkSuper(clazz, entry, "withSuperClass")) 
		{
			String token = Parser.NAME_TOKEN + ":" + entry.getName();
			int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
			token = Parser.NAME_TOKEN + ":;";
			currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
			// set interface
			currentInsertPos = insertCreationCode("\n			/*set superclass*/", currentInsertPos, modelCreationClass);
			currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass); 
			StringBuilder text = new StringBuilder("      .withSuperClass(superClassName)");
			CGUtil.replaceAll(text, "superClassName", StrUtil.downFirstChar(CGUtil.shortClassName(clazz.getSuperClass().getName())) + "Class" );
			currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
			currentInsertPos++;
			//			currentInsertPos++;
			symTabEntry = refreshMethodScan(signature);
			clazz.isFileHasChanged();
		}

		// check is interface
		else if (clazz.isInterfaze() && !isInterface(entry))
		{
			String token = Parser.NAME_TOKEN + ":" + entry.getName();
			int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
			token = Parser.NAME_TOKEN + ":;";
			currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
			// set interface
			currentInsertPos = insertCreationCode("\n			/*set interface*/", currentInsertPos, modelCreationClass);
			currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass); 
			StringBuilder text = new StringBuilder("      .withInterfaze(true)");
			currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
			currentInsertPos++;
			//			currentInsertPos++;
			symTabEntry = refreshMethodScan(signature);
			clazz.isFileHasChanged();
		}

		// check has interfaces
		for (Clazz interfaze : clazz.getInterfaces())
		{
			if (!checkSuper(interfaze, entry, "withInterfaces")) 
			{
				//				writeToFile(modelCreationClass);
				// find insert position
				String token = Parser.NAME_TOKEN + ":" + entry.getName();
				int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
				token = Parser.NAME_TOKEN + ":;";
				currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
				// add attribut
				currentInsertPos = insertCreationCode("\n			/*add interface*/", currentInsertPos, modelCreationClass);
				currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
				StringBuilder text = new StringBuilder("      .withInterfaces(interfaceName)");
				CGUtil.replaceAll(text, "interfaceName", StrUtil.downFirstChar(CGUtil.shortClassName(interfaze.getName())) + "Class" );
				currentInsertPos = insertCreationCode(text, currentInsertPos, modelCreationClass); 
				currentInsertPos++;
			}

			// set insert position to next line
			currentInsertPos++;
			symTabEntry = refreshMethodScan(signature);
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

						symTabEntry = refreshMethodScan(signature);

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
			symTabEntry = refreshMethodScan(signature);
		}

		// check code for method
		LinkedHashSet<Method> methods = clazz.getMethods();

		for (Method method : methods)
		{
			currentInsertPos = tryToInsertMethod(symTabEntry, method, currentInsertPos, modelCreationClass);
		}

		// check code for assoc
		LinkedHashSet<Role> roles = new LinkedHashSet<Role>();

		if (!clazz.getSourceRoles().isEmpty())
			roles.addAll(clazz.getSourceRoles());

		if (!clazz.getTargetRoles().isEmpty())
			roles.addAll(clazz.getTargetRoles());

		for (Role role : roles)
		{
			Association  association = role.getAssoc();
			if (association == null)
				continue;
			String sourceClassName = association.getSource().getClazz().getName();
			String targetClassName = association.getTarget().getClazz().getName();

			if (handledClazzes.containsKey(sourceClassName) && handledClazzes.containsKey(targetClassName))
			{

				symTabEntry = refreshMethodScan(signature);

				int indexOfSourceClassPos = positionOfClazzDecl(sourceClassName);
				int indexOfTargetClassPos = positionOfClazzDecl(targetClassName);
				int resultPos = Math.max(indexOfSourceClassPos, indexOfTargetClassPos) + 3;
				currentInsertPos = Math.max(resultPos, currentInsertPos);
				currentInsertPos = tryToInsertAssoc(symTabEntry, role, currentInsertPos, modelCreationClass);
			}

		}
		return currentInsertPos;
	}

	private int positionOfClazzDecl(String sourceClassName)
	{
		LinkedHashMap<String, LocalVarTableEntry> localVarTable = creatorCreatorParser.getLocalVarTable();
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

	private SymTabEntry refreshMethodScan(String signature)
	{
		SymTabEntry symTabEntry;
		rescanCode();
		symTabEntry = creatorCreatorParser.getSymTab().get(signature);
		creatorCreatorParser.parseMethodBody(symTabEntry);
		return symTabEntry;
	}

	private void rescanCode()
	{
		creatorCreatorParser.indexOf(Parser.CLASS_END);
	}

	private int tryToInsertAssoc(SymTabEntry symTabEntry, Role role, int currentInsertPos, Clazz modelCreationClass)
	{
		creatorCreatorParser.parseMethodBody(symTabEntry);
		boolean assocIsNew = true;
		LinkedHashMap<String, LocalVarTableEntry> localVarTable = creatorCreatorParser.getLocalVarTable();

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

		for (StatementEntry stat : creatorCreatorParser.getStatementList().getBodyStats())
		{
			if (stat.getTokenList().get(0).endsWith(".withAssoc"))
			{
				// looks like sourceClass.withAssoc(targetClass, "targetRole", targetCard, "sourceRole", sourceCard);

				// ensure source class name
				String classVar = CGUtil.packageName(stat.getTokenList().get(0));
				LocalVarTableEntry localVarTableEntry = localVarTable.get(classVar);
				String classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
				String classNameFromAssoc = assoc.getSource().getClazz().getName();

				if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
				{
					// ensure target class name
					classVar = stat.getTokenList().get(2);
					localVarTableEntry = localVarTable.get(classVar);
					classNameFromText = localVarTableEntry.getInitSequence().get(0).get(1).replaceAll("\"", "");
					classNameFromAssoc = assoc.getTarget().getClazz().getName();

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
				String classNameFromAssoc = assoc.getSource().getClazz().getName();

				if (StrUtil.stringEquals(classNameFromText, classNameFromAssoc) || classNameFromAssoc.endsWith("." + classNameFromText))
				{
					// ensure target class name
					classNameFromText = stat.getTokenList().get(2).replaceAll("\"", "");
					classNameFromAssoc = assoc.getTarget().getClazz().getName();

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
			String name = CGUtil.shortClassName(role.getClazz().getName()) + "Class";
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
		String shortClassName = CGUtil.shortClassName(role.getClazz().getName());
		String clazzString = StrUtil.downFirstChar(shortClassName) + "Class";
		sequence += "," + clazzString + ",";
		sequence += "\"" + role.getCard() + "\"" + ")";
		return sequence;
	}

	private ArrayList<String> findSequence(String searchString, ArrayList<ArrayList<String>> initSequence)
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
				return sequence;
			}
		}
		return null;
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
		creatorCreatorParser.parseMethodBody(symTabEntry);
		boolean methodIsNew = true;
		LinkedHashMap<String, LocalVarTableEntry> localVarTable = creatorCreatorParser.getLocalVarTable();

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
			String name = CGUtil.shortClassName(method.getClazz().getName()) + "Class";
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
		String shortClassName = CGUtil.shortClassName(method.getClazz().getName()) + "Class";
		shortClassName = StrUtil.downFirstChar(shortClassName);
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
		String modelClassName = clazz.getName();
		// no creation code yet. Insert it.
		currentInsertPos = insertCreationClassCode(currentInsertPos, modelClassName, modelCreationClass, symTabEntry);

		// check interface attr
		if (clazz.isInterfaze()) {
			currentInsertPos = insertCreationIsInterfaceCode(currentInsertPos, modelCreationClass, symTabEntry);
			currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
		}

		// insert code for superclass
		Clazz superClass = clazz.getSuperClass();
		if (superClass != null) {
			currentInsertPos = insertCreationSuperClassCode( currentInsertPos, superClass.getName(), modelCreationClass, symTabEntry);
			currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
		}

		// insert code for interfaces
		for ( Clazz interfaze :clazz.getInterfaces() ){
			if (interfaze != null) {
				currentInsertPos = insertCreationInterfaceCode(currentInsertPos, interfaze.getName(), modelCreationClass, symTabEntry);
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

		if (!clazz.getSourceRoles().isEmpty())
			roles.addAll(clazz.getSourceRoles());

		if (!clazz.getTargetRoles().isEmpty())
			roles.addAll(clazz.getTargetRoles());

		currentInsertPos = handleAssocs(roles, currentInsertPos, modelCreationClass, symTabEntry, handledClazzes);

		return currentInsertPos;
	}

	//	private boolean isInteralMethod(Method method, LinkedHashSet<Attribute> clazzAttributes) {
	//		for (Attribute attribute : clazzAttributes)
	//		{
	//			String signature = method.getSignature(); 
	//			String methodName = signature.substring(0, signature.indexOf("("));
	//			String attributeName = StrUtil.upFirstChar(attribute.getName());
	//			String result = methodName.replace(attributeName, "");		
	//			System.out.println(attributeName + "  " + result);		
	//		}
	//		return false;
	//	}

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

			String secondClassName = secondRole.getClazz().getName();

			if (handledClazzes.containsKey(secondClassName))
			{
				currentInsertPos = insertCreationAssociationCode(assoc, currentInsertPos, modelCreationClass, symTabEntry);
			}

			writeToFile(modelCreationClass);
		}
		return currentInsertPos;
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

		StringBuilder text = new StringBuilder("\n      new Method()" + "\n			.withClazz(clazzName)" + "\n			.withSignature(\"methodSignature\");\n");

		String clazzName = method.getClazz().getName();
		clazzName = StrUtil.downFirstChar(CGUtil.shortClassName(clazzName)) + "Class";
		String signature = method.getSignature();

		CGUtil.replaceAll(text, "clazzName", clazzName, "methodSignature", signature);

		currentInsertPos = checkImport("Method", currentInsertPos, modelCreationClass, symTabEntry);
		return insertCreationCode(text, currentInsertPos, modelCreationClass);
	}

	private int checkImport(String string, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
	{
		creatorCreatorParser.indexOf(Parser.CLASS_END);
		LinkedHashMap<String, SymTabEntry> symTab = creatorCreatorParser.getSymTab();
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
			int endOfImports = creatorCreatorParser.getEndOfImports() + 1;
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
		String sourceClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getSource().getClazz().getName())) + "Class";

		String targetRole = assoc.getTarget().getCard().toUpperCase();
		String targetName = assoc.getTarget().getName();
		String targetClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getTarget().getClazz().getName())) + "Class";

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

	private boolean hasAttribute(Attribute attribute, LocalVarTableEntry entry)
	{
		String name = attribute.getName();
		String type = attribute.getType();
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
		for (StatementEntry stat : creatorCreatorParser.getCurrentStatement().getParent().getBodyStats())
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


	private boolean checkSuper(Clazz clazz, LocalVarTableEntry entry, String classType)
	{
		String name = CGUtil.shortClassName( clazz.getName() );
		ArrayList<ArrayList<String>> initSequence = entry.getInitSequence();
		for (ArrayList<String> sequencePart : initSequence)
		{
			if (classType.equals(sequencePart.get(0)))
			{
				String sequencePartName = sequencePart.get(1).replace("\"", "");
				//				if ( StrUtil.stringEquals(name, sequencePartName) )
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
		creatorCreatorParser.getFileBody().insert(insertPos, text.toString());
		modelCreationClass.setFileHasChanged(true);
		return insertPos + text.length();
	}

	private int insertCreationCode(String string, int insertPos, Clazz modelCreationClass )
	{
		StringBuilder text = new StringBuilder(string);
		return insertCreationCode(text, insertPos, modelCreationClass );
	}

	private int searchForQualifiedNamePosition(String methodCall, int methodEndPos, Parser parser)
	{
		Set<String> methodBodyQualifiedNames = parser.getMethodBodyQualifiedNames();
		for (String qualifiedName : methodBodyQualifiedNames)
		{
			if (qualifiedName.contains(methodCall))
			{
				int callPos = parser.getMethodBodyQualifiedNamesMap().get(qualifiedName);
				String substring = parser.getFileBody().substring(callPos, methodEndPos);
				return callPos + substring.indexOf(';') + 1;
			}
		}
		return -1;
	}

	private Clazz getOrCreateClazz(String className)
	{
		for (Clazz clazz : getClasses())
		{
			if (StrUtil.stringEquals(clazz.getName(), className))
			{
				return clazz;
			}
		}
		Clazz clazz = new Clazz(className);
		return clazz;
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

	public String getMemberType(String currentType, String varName)
	{
		String result = null;

		for (Clazz clazz : this.getClasses())
		{
			String name = CGUtil.shortClassName(clazz.getName());
			if (StrUtil.stringEquals(name, currentType))
			{
				for (Attribute attr : clazz.getAttributes())
				{
					if (StrUtil.stringEquals(attr.getName(), varName))
					{
						return attr.getType();
					}
				}

				for (Role role : clazz.getSourceRoles())
				{
					role = role.getPartnerRole();
					if (StrUtil.stringEquals(role.getName(), varName))
					{
						currentTypeCard = role.getCard();
						return CGUtil.shortClassName(role.getClazz().getName());
					}
				}

				for (Role role : clazz.getTargetRoles())
				{
					role = role.getPartnerRole();
					if (StrUtil.stringEquals(role.getName(), varName))
					{
						currentTypeCard = role.getCard();
						return CGUtil.shortClassName(role.getClazz().getName());
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
					Attribute attrDecl = currentClazz.getOrCreateAttribute(attr.getName(), "Object");
					
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
				.withSource(sourceLabel, this.getOrCreateClazz(packageName + "." + sourceType), R.ONE)
				.withTarget(targetLabel, getOrCreateClazz(packageName + "." + targetType), R.ONE)
				.withModel(this);
			}

			if (alreadyUsedLabels.contains(currentLink.getSrc().hashCode() + ":" + targetLabel))
			{
			   currentAssoc.getTarget().setCard(R.MANY.toString());
			}
			
			if (alreadyUsedLabels.contains(currentLink.getTgt().hashCode() + ":" + sourceLabel))
         {
            currentAssoc.getSource().setCard(R.MANY.toString());
         }
         
			alreadyUsedLabels.add(currentLink.getSrc().hashCode() + ":" + targetLabel);
         alreadyUsedLabels.add(currentLink.getTgt().hashCode() + ":" + sourceLabel);
		}

		
		
		
		
		return this;
	}

   private void learnAttrType(GenericAttribute attr, Attribute attrDecl)
   {
      String valueString = attr.getValue();
      
      String attrType = "String";
      try
      {
         Integer.parseInt(valueString);
         
         attrType = "int";
      }
      catch (NumberFormatException e)
      {
         try
         {
            Double.parseDouble(valueString);
            
            attrType = "double";
         }
         catch (NumberFormatException e1)
         {
            try
            {
               DateFormat.getDateInstance().parse(valueString);
               
               attrType = "java.util.Date";
            }
            catch (ParseException e2)
            {
               try
               {
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss");
                  simpleDateFormat.parse(valueString);
                  
                  attrType = "java.util.Date";
               }
               catch (ParseException e3)
               {
               }
            }
         }
      }
      
      String typeOrder = "Object int double java.util.Date String";
      
      if (typeOrder.indexOf(attrDecl.getType()) < typeOrder.indexOf(attrType))
      {
         attrDecl.setType(attrType);
      }
   }

	public void removeAllGeneratedCode()
   {
      removeAllGeneratedCode("src", "src", "src");
   }
	
	public void removeAllGeneratedCode(String rootDir)
   {
	   removeAllGeneratedCode(rootDir, rootDir, rootDir);
   }
	
	public void removeAllGeneratedCode(String rootDir, String srcDir, String helpersDir)
	{
		turnRemoveCallToComment(rootDir);

		// now remove class file, creator file, and modelset file for each class and the CreatorCreator
		String packageName = null;
		for (Clazz clazz : this.getClasses())
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
		className = clazz.getName();
		int pos = className.lastIndexOf('.');
		packageName = className.substring(0, pos);

		// class file
		fileName = srcDir + "/" + className.replaceAll("\\.", "/") + ".java";
		deleteFile(fileName);

		// creator file
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/" + CGUtil.shortClassName(className) + "Creator.java";
		deleteFile(fileName);

		// modelset file
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/" + CGUtil.shortClassName(className) + "Set.java";
		deleteFile(fileName);

		// pattern object file
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/" + CGUtil.shortClassName(className) + "PO.java";
		deleteFile(fileName);

		// pattern object creator file
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/" + CGUtil.shortClassName(className) + "POCreator.java";
		deleteFile(fileName);

		// model pattern file
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/ModelPattern.java";
		deleteFile(fileName);

		// CreatorCreator in that package
		fileName = helpersDir + "/" + packageName.replaceAll("\\.", "/") + "/creators/CreatorCreator.java";
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




	//==========================================================================

	public static final String PROPERTY_PACKAGENAME = "packageName";

	private String packageName;

	public String getPackageName()
	{
		return this.packageName;
	}

	public void setPackageName(String value)
	{
		if ( ! StrUtil.stringEquals(this.packageName, value))
		{
			String oldValue = this.packageName;
			this.packageName = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_PACKAGENAME, oldValue, value);
		}
	}

	public ClassModel withPackageName(String value)
	{
		setPackageName(value);
		return this;
	} 

	public String toString()
	{
		StringBuilder _ = new StringBuilder();

		_.append(" ").append(this.getPackageName());
		return _.substring(1);
	}
   public ClassModel withClasses(Clazz... value)
   {
      for (Clazz item : value)
      {
         addToClasses(item);
      }
      return this;
   } 

   public ClassModel withoutClasses(Clazz... value)
   {
      for (Clazz item : value)
      {
         removeFromClasses(item);
      }
      return this;
   }

   public Clazz createClasses()
   {
      Clazz value = new Clazz();
      withClasses(value);
      return value;
   } 

   public ClassModel withAssociations(Association... value)
   {
      for (Association item : value)
      {
         addToAssociations(item);
      }
      return this;
   } 

   public ClassModel withoutAssociations(Association... value)
   {
      for (Association item : value)
      {
         removeFromAssociations(item);
      }
      return this;
   }

   public Association createAssociations()
   {
      Association value = new Association();
      withAssociations(value);
      return value;
   } 
}

