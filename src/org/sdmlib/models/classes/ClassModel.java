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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class ClassModel implements PropertyChangeInterface {
	public static ClassModel classModel = null;

	private Parser parser;

	private File javaFile;

	private StringBuilder fileBody;

	private boolean fileHasChanged;

	public void setFileHasChanged(boolean fileHasChanged) 
	{
		this.fileHasChanged = fileHasChanged;
	}

	public ClassModel() 
	{
		classModel = this;
	}

	public ClassModel generate(String rootDir, String helpersDir) 
	{
		for (Clazz clazz : getClasses()) 
		{
			clazz.generate(rootDir, helpersDir);
		}

		generateCreatorCreatorClass(helpersDir);

		for (Association assoc : getAssociations()) 
		{
			assoc.generate(rootDir, false);
		}

		return this;
	}

	private void generateCreatorCreatorClass(String rootDir) 
	{
		// take first class to find package

		getOrCreateParser(rootDir);

		for (Clazz clazz : getClasses()) 
		{
			clazz.insertCreatorClassInCreatorCreator(parser);
		}

		printFile(fileHasChanged);
	}

	public void printFile(boolean really) 
	{
		if (really) 
		{
			CGUtil.printFile(javaFile, fileBody.toString());
		}
	}

	public Parser getOrCreateParser(String rootDir) 
	{
		if (parser == null ) 
		{
			// try to find existing file
			Clazz firstClass = getClasses().iterator().next();

			String className = firstClass.getName();

			int pos = className.lastIndexOf('.');

			String packageName = className.substring(0, pos) + ".creators";

			className = packageName + ".CreatorCreator";

			String fileName = className;

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
						"import org.sdmlib.serialization.json.JsonIdMap;\n" + 
					    "\n" + 
						"public class className\n" + 
					    "{\n" + 
					    "   public static JsonIdMap createIdMap(String sessionID)\n" + 
					    "   {\n" +
						"      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);\n" + 
					    "      \n" + 
						"      return jsonIdMap;\n" + 
						"   }\n" + 
						"}\n");

				CGUtil.replaceAll(text, "className", CGUtil.shortClassName(className), "packageName", packageName);

				fileBody.append(text.toString());

				fileHasChanged = true;
			}

			parser = new Parser().withFileName(fileName).withFileBody(fileBody);
		}

		return parser;
	}

	// ==========================================================================

	public static final String PROPERTY_CLASSES = "classes";

	private LinkedHashSet<Clazz> classes;

	public LinkedHashSet<Clazz> getClasses() 
	{
		if (classes == null) 
		{
			return Clazz.EMPTY_SET;
		}
		return this.classes;
	}

	public ClassModel withClasses(Clazz value) 
	{
		addToClasses(value);
		return this;
	}

	 public void addToClasses(Clazz value)
	 {
	      if (this.classes == null)
	      {
	         this.classes = new LinkedHashSet<Clazz>();
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

	   public String dumpClassDiag(String diagName)
	   {
		   // generate dot file 
		   StringBuilder dotFileText = new StringBuilder
				   (  "\n graph ClassDiagram {" +
						   "\n    node [shape = none, fontsize = 10]; " +
						   "\n    edge [fontsize = 10];" +
						   "\n    " +
						   "\n    modelClasses" +
						   "\n    " +
						   //            "\n    g1 -- p2 " +
						   //            "\n    g1 -- p3 [headlabel = \"persons\" taillabel = \"groupAccounter\"];" +
						   "\n    " +
						   "\n    modelAssocs" +
						   "\n}" +
						   "\n"
						   );

		   // add classes
		   StringBuilder modelClassesText = new StringBuilder();

		   for (Clazz clazz : this.getClasses())
		   {
			   StringBuilder modelClassText = new StringBuilder
					   (  "\n    className [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td>className</td> </tr> attrCompartment methodCompartment </table>>];");

			   CGUtil.replaceAll(modelClassText, 
					   "className", CGUtil.shortClassNameHTMLEncoded(clazz.getName()),
					   "attrCompartment", dumpAttributes(clazz),
					   "methodCompartment", dumpMethods(clazz));

			   modelClassesText.append(modelClassText.toString());
		   }

		   // add associations
		   StringBuilder allAssocsText = new StringBuilder();

		   for (Association assoc : getAssociations())
		   {
			   StringBuilder oneAssocText = new StringBuilder
					   (  "\n    sourceClass -- targetClass [headlabel = \"targetRole\" taillabel = \"sourceRole\"];" );

			   CGUtil.replaceAll(oneAssocText, 
					   "sourceClass", CGUtil.shortClassName(assoc.getSource().getClazz().getName()),
					   "targetClass", CGUtil.shortClassName(assoc.getTarget().getClazz().getName()),
					   "sourceRole", assoc.getSource().getName(),
					   "targetRole", assoc.getTarget().getName());

			   allAssocsText.append(oneAssocText.toString());
		   }

		   CGUtil.replaceAll(dotFileText, 
				   "modelClasses", modelClassesText.toString(),
				   "modelAssocs", allAssocsText.toString());

		   // write dot file 
		   File docDir = new File("doc");
		   docDir.mkdir();

//		   BufferedWriter out;

		   File dotFile = new File("doc/" + diagName + ".dot");
		   ScenarioManager.get().printFile(dotFile, dotFileText.toString());

		   // generate image
		   String command = "";
		   
		   if ((System.getProperty("os.name").toLowerCase()).contains("mac")) 
		   {
			   command = "../SDMLib/tools/Graphviz/osx_lion/makeimage.command " + diagName;
		   } 
		   else 
		   {
			   command = "../SDMLib/tools/makeimage.bat " + diagName;
		   }
		   try 
		   {
			   Runtime.getRuntime().exec(command);
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
		   
		return diagName + ".svg";
	}

	private String dumpMethods(Clazz clazz) 
	{
		StringBuilder allMethodsText = new StringBuilder("<tr><td><table border='0' cellborder='0' cellspacing='0'> methodRow </table></td></tr>");

		if (clazz.getMethods().size() > 0) 
		{
			for (Method method : clazz.getMethods()) 
			{
				StringBuilder oneMethodText = new StringBuilder("<tr><td align='left'>methodDecl</td></tr>");

				CGUtil.replaceAll(oneMethodText, "methodDecl", method.getSignature());

				CGUtil.replaceAll(allMethodsText, "methodRow", oneMethodText.append(" methodRow").toString());
			}

			CGUtil.replaceAll(allMethodsText, "methodRow", "");
		} 
		else 
		{
			CGUtil.replaceAll(allMethodsText, "methodRow", "<tr><td> </td></tr>");
		}

		return allMethodsText.toString();
	}

	private String dumpAttributes(Clazz clazz) 
	{
		StringBuilder allAttrsText = new StringBuilder("<tr><td><table border='0' cellborder='0' cellspacing='0'> attrRow </table></td></tr>");

		if (clazz.getAttributes().size() > 0) 
		{
			for (Attribute attr : clazz.getAttributes()) 
			{
				StringBuilder oneAttrText = new StringBuilder("<tr><td align='left'>attrDecl</td></tr>");

				CGUtil.replaceAll(oneAttrText, "attrDecl", attr.getName() + " :" + CGUtil.shortClassNameHTMLEncoded(attr.getType()));

				CGUtil.replaceAll(allAttrsText, "attrRow", oneAttrText.append(" attrRow").toString());
			}

			CGUtil.replaceAll(allAttrsText, "attrRow", "");
		} 
		else 
		{
			CGUtil.replaceAll(allAttrsText, "attrRow", "<tr><td> </td></tr>");
		}

		return allAttrsText.toString();
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ClassModel ----------------------------------- Association
	 *              model                   associations
	 * </pre>
	 */

	public static final String PROPERTY_ASSOCIATIONS = "associations";

	private LinkedHashSet<Association> associations = null;

	public LinkedHashSet<Association> getAssociations() 
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
				this.associations = new LinkedHashSet<Association>();
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
	

	public void updateFromCode(String includePathes, String packages)
	{
		// find java files
		String binDir = getClass().getClassLoader().getResource(".").getPath();
		String srcDir = binDir.substring(0, binDir.length() - 4);
		File srcFolder = new File(srcDir);

		if (srcFolder != null)
		{
			ArrayList<File> javaFiles = searchForJavaFiles(includePathes, packages, srcFolder);
			addJavaFilesToClasses(packages, srcFolder, javaFiles);

			// parse each java file
			for (Clazz clazz : getClasses())
			{
				handleMember(clazz);
			}
		}

		// add model creation code at invocation place, if not yet there
	}

	private void handleMember(Clazz clazz)
	{
		Parser parser = clazz.getOrCreateParser("examples");
		parser.indexOf(Parser.CLASS_END);

		LinkedHashMap<String, SymTabEntry> symTab = new LinkedHashMap<String, SymTabEntry>();// parser.getSymTab();
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
		for (SymTabEntry symTabEntry : attributes.keySet())
		{
			String memberName = symTabEntry.getMemberName();
			memberNames.add(memberName);
			String partnerTypeName = symTabEntry.getType();

			if (Attribute.COMPLEX.equals(attributes.get(symTabEntry)))
			{

				String card = findRoleCard(partnerTypeName);
				String partnerClassName = findPartnerClassName(partnerTypeName);
				Clazz partnerClass = findPartnerClass(partnerTypeName);
				String setterPrefix = findSetterPrefix(partnerTypeName);

				if (partnerClass == null)
					continue;

				String name = StrUtil.upFirstChar(memberName);
				
				Method addToMethod = findMethod(clazz, setterPrefix + name + "(" + partnerClassName + ")");

				if (addToMethod == null)
					continue;

				SymTabEntry addToSymTabEntry = symTab.get(Parser.METHOD + ":" + addToMethod.getSignature());

				if (addToSymTabEntry == null)
					continue;

				// TODO : fix methodBodyIndexOf clears symtab
				parser.methodBodyIndexOf(Parser.METHOD_END, addToSymTabEntry.getBodyStartPos());

				LinkedHashSet<String> methodBodyQualifiedNames = new LinkedHashSet<String>(); // = parser.getMethodBodyQualifiedNames();
				for (String key : parser.getMethodBodyQualifiedNames())
				{
					methodBodyQualifiedNames.add(key);
				}

				for (String qualifiedName : methodBodyQualifiedNames)
				{
					if (qualifiedName.startsWith("value.with"))
					{
						handleAssoc(clazz, memberName, card, partnerClassName, partnerClass, qualifiedName);
					}
				}
			}
			// remove getter with setter or addTo removeFrom removeAllFrom without
//			findAndRemoveMethods(clazz, memberName, "get set with without addTo removeFrom removeAllFrom");
		}
		for (String memberName : memberNames)
    {
		// remove getter with setter or addTo removeFrom removeAllFrom without
					findAndRemoveMethods(clazz, memberName, "get set with without addTo removeFrom removeAllFrom");
    }
	}

	private void handleAssoc(Clazz clazz, String memberName, String card, String partnerClassName, Clazz partnerClass, String qName)
	{
		String partnerAttrName = qName.substring("value.with".length());
		partnerAttrName = StrUtil.downFirstChar(partnerAttrName);
		Parser partnerParser = partnerClass.getOrCreateParser("examples"); // TODO fix rootdir
		String searchString = Parser.ATTRIBUTE + ":" + partnerAttrName;

		// TODO : fix indexOf clears symtab
		int attributePosition = partnerParser.indexOf(searchString);

		if (attributePosition > -1)
		{
			String partnerCard = findRoleCard(partnerParser, searchString);
			tryToCreateAssoc(clazz, memberName, card, partnerClassName, partnerClass, partnerAttrName, partnerCard);
		}
	}
	

	private void addMemberToModel(Clazz clazz, Parser parser, LinkedHashMap<SymTabEntry, String> attributes, String memberName) 
	{
		// add new methods
		
		if (memberName.startsWith(Parser.METHOD)) 
		{
			addMemberAsMethod(clazz, memberName);
		}
		// add new attributes
		else if (memberName.startsWith(Parser.ATTRIBUTE)) 
		{		
			String[] split = memberName.split(":");
			String attrName = split[1];
			SymTabEntry symTabEntry = parser.getSymTab().get(memberName);			
			addMemberAsAttribut(clazz, attributes, attrName, symTabEntry);
		}

		// TODO : add super classes

		// TODO : add interfaces
	}
	

	private void addMemberAsAttribut(Clazz clazz, LinkedHashMap<SymTabEntry, String> attributes, String attrName, SymTabEntry symTabEntry) 
	{
		// filter public static final constances
		String modifiers = symTabEntry.getModifiers();
		if (  (modifiers.indexOf("public") >= 0 || modifiers.indexOf("private") >= 0) 
				&& modifiers.indexOf("static") >= 0 
				&& modifiers.indexOf("final") >= 0) 
		{
			// ignore
			return;
		}

		String type = symTabEntry.getType();
		// include arrays
		type = type.replace("[]", "");
		String primitiveTypes = "String long int char boolean byte float double";
		
		if (primitiveTypes.indexOf(type) > -1) 
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
	

	private void addMemberAsMethod(Clazz clazz, String memberName) 
	{
		String[] split = memberName.split(":");
		String signature = split[1];

		// filter internal generated methods
		String filterString = "get(String) set(String,Object) getPropertyChangeSupport() removeYou()";

		if (filterString.indexOf(signature) < 0) 
		{
			new Method().withClazz(clazz).withSignature(signature);
		}
	}
	

	private ArrayList<File> searchForJavaFiles(String includePathes, String packages, File srcFolder) 
	{
		ArrayList<File> javaFiles = new ArrayList<File>();
		String packagepath = packages.replace('.', '/');
		String[] includes = includePathes.split("\\s+");
		
		for (String include : includes) 
		{
			String newPath = srcFolder.getPath() + "/" + include + "/" + packagepath;
			javaFiles.addAll(searchForJavaFiles(newPath));
		}
		return javaFiles;
	}
	

	private void addJavaFilesToClasses(String packages, File srcFolder, ArrayList<File> javaFiles) 
	{
		for (File file : javaFiles) 
		{
			String filePath = file.getAbsolutePath();
			filePath = filePath.replace(srcFolder.getPath(), "");
			filePath = filePath.replace(File.separatorChar, '.');
			int indexOfPackage = filePath.lastIndexOf(packages, filePath.length() - 1);
			filePath = filePath.substring(indexOfPackage, filePath.length() - 5);

			if (!classExists(filePath)) 
			{
				Clazz clazz = new Clazz(filePath);
				classes.add(clazz);
			}
		}
	}

	private String findSetterPrefix(String partnerTypeName) 
	{
		int openAngleBracket = partnerTypeName.indexOf("<");
		int closeAngleBracket = partnerTypeName.indexOf(">");
		
		if (openAngleBracket > -1 && closeAngleBracket > openAngleBracket) 
		{
			return "addTo";
		} 
		return "set";
	}
	
	private String findRoleCard(Parser partnerParser, String searchString) 
	{
		String partnerTypeName;
		SymTabEntry partnerSymTabEntry = partnerParser.getSymTab().get(searchString);
		partnerTypeName = partnerSymTabEntry.getType();
		
		return findRoleCard(partnerTypeName);
	}
	
	private String findRoleCard(String partnerTypeName) 
	{
		String partnerCard = Role.ONE;
		int _openAngleBracket = partnerTypeName.indexOf("<");
		int _closeAngleBracket = partnerTypeName.indexOf(">");
		if (_openAngleBracket > -1 && _closeAngleBracket > _openAngleBracket) 
		{
			// partner to many
			partnerCard = Role.MANY;
		}
		return partnerCard;
	}
	

	private void tryToCreateAssoc(Clazz clazz, String memberName, String card, String partnerClassName, Clazz partnerClass, String partnerAttrName, String partnerCard) 
	{
		Role sourceRole = new Role()
			.withName(partnerAttrName)
			.withClazz(clazz)
			.withCard(partnerCard);
		
		Role targetRole = new Role()
			.withName(memberName)
			.withClazz(partnerClass)
			.withCard(card);
		
		if (!assocWithRolesExists(sourceRole, targetRole)) 
		{
			new Association()
			.withSource(sourceRole)
			.withTarget(targetRole);
			
			clazz.addToSourceRoles(sourceRole);
			partnerClass.addToTargetRoles(targetRole);
			
			System.out.println("assoc detected : " + CGUtil.shortClassName(clazz.getName()) + " " + partnerAttrName + " " + partnerCard + " " + " -- "
					+ CGUtil.shortClassName(partnerClassName) + " " + memberName + " " + card);
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
		for (Clazz clazz  : getClasses()) 
		{
			if(clazz.getName().equals(filePath))
				return true;
		}
		return false;
	}
	

	private boolean assocWithRolesExists(Role source, Role target) 
	{
		for (Association assoc : getAssociations()) 
		{
			if ( isEqual(source, target, assoc) || isEqual(target, source, assoc))
				return true;
		}
		return false;
	}
	

	private boolean isEqual(Role source, Role target, Association assoc) 
	{
		return isEqual(assoc.getSource(),source) &&
			 isEqual(assoc.getTarget() ,target);
	}
	

	private boolean isEqual(Role first, Role second) 
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
		
		for (Clazz partnerClass : classes) 
		{

			String shortClassName = CGUtil.shortClassName(partnerClass.getName());

			if (partnerClassName.equals(shortClassName)) 
			{

				return partnerClass;

			}
		}
//		System.err.println("type note found : " + partnerTypeName);
		return null;
	}

	private String findPartnerClassName(String partnerTypeName) 
	{
		String partnerClassName;
		int openAngleBracket = partnerTypeName.indexOf("<");
		int closeAngleBracket = partnerTypeName.indexOf(">");
		
		if (openAngleBracket > -1 && closeAngleBracket > openAngleBracket) 
		{
			partnerClassName = partnerTypeName.substring(openAngleBracket + 1, closeAngleBracket);
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
			for (File dir : directory) {
				javaFiles.addAll(searchForJavaFiles(dir.getPath()));
			}
		}
		return javaFiles;
	}

	public Object get(String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}

		if (PROPERTY_CLASSES.equalsIgnoreCase(attrName)) {
			return getClasses();
		}

		if (PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName)) {
			return getAssociations();
		}

		return null;
	}

	// ==========================================================================

	public boolean set(String attrName, Object value) {
		if (PROPERTY_CLASSES.equalsIgnoreCase(attrName)) {
			addToClasses((Clazz) value);
			return true;
		}

		if ((PROPERTY_CLASSES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			removeFromClasses((Clazz) value);
			return true;
		}

		if (PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName)) {
			addToAssociations((Association) value);
			return true;
		}

		if ((PROPERTY_ASSOCIATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName)) {
			removeFromAssociations((Association) value);
			return true;
		}

		return false;
	}

	// ==========================================================================

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	// ==========================================================================

	public void removeYou() {
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

		try
		{
			// find here
			throw new RuntimeException();
		}
		catch (Exception e)
		{
			StackTraceElement[] stackTrace = e.getStackTrace();

			StackTraceElement firstStackTraceElement = stackTrace[0];
			callMethodName = firstStackTraceElement.getMethodName();

			StackTraceElement secondStackTraceElement = stackTrace[1];
			fileName = secondStackTraceElement.getFileName();
			className = secondStackTraceElement.getClassName();
			methodName = secondStackTraceElement.getMethodName();
		}
		
		// parse the model creation file
		Clazz modelCreationClass = getOrCreateClazz(className);
		
		parser = modelCreationClass.getOrCreateParser(rootDir);

		String signature = Parser.METHOD + ":" + methodName + "()";// TODO should work for methods with params, too. Parse to method end and search in symtab.
//		int pos = parser.indexOf(signature);

		SymTabEntry symTabEntry = parser.getSymTab().get(signature);

		parser.methodBodyIndexOf(Parser.METHOD_END, symTabEntry.getBodyStartPos());

		LinkedHashMap<String, LocalVarTableEntry> localVarTable = (LinkedHashMap<String, LocalVarTableEntry>) parser.getLocalVarTable().clone();
		// insert code
		int currentInsertPos = parser.methodCallIndexOf(Parser.NAME_TOKEN + ":model", symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
		currentInsertPos = parser.IndexOfInMethodBody(Parser.NAME_TOKEN + ":;", currentInsertPos, symTabEntry.getEndPos()) + 1;
		currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass, symTabEntry);
		
		
		LinkedHashMap<String, Clazz> handledClazzes = new LinkedHashMap<String, Clazz>();
		for (Clazz clazz : getClasses())
		{
			String modelClassName = clazz.getName();
			handledClazzes.put(modelClassName, clazz);
			LocalVarTableEntry entry = findInLocalVarTable(localVarTable, modelClassName);
	
			if (entry == null)
			{
				// insert code for new Clazz()
				currentInsertPos = createAndInsertCodeForNewClazz(callMethodName, modelCreationClass, symTabEntry, clazz, handledClazzes, currentInsertPos);			
			}
			else 
			{				
				// check code for clazz			
				currentInsertPos = checkCodeForClazz(entry, callMethodName, modelCreationClass, symTabEntry, clazz, currentInsertPos);
			}
			parser.indexOf(Parser.CLASS_END);
			symTabEntry = parser.getSymTab().get(signature);
		}

		modelCreationClass.printFile(modelCreationClass.isFileHasChanged());
	}

	private int checkCodeForClazz( LocalVarTableEntry entry, String callMethodName, Clazz modelCreationClass, 
																 SymTabEntry symTabEntry, Clazz clazz, int currentInsertPos)
  {
//		String modelClassName = clazz.getName();
	  // check code for attribut
  	LinkedHashSet<Attribute> clazzAttributes = clazz.getAttributes();
  	for (Attribute attribute : clazzAttributes)
    {
  		if (!hasAttribute(attribute, entry)) 
  		{
  		// find insert position	
  			String token =  Parser.NAME_TOKEN + ":" + entry.getName();		
				int methodCallStartPos = parser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
				token = Parser.NAME_TOKEN + ":;";			
				currentInsertPos = parser.IndexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
  			// add attribut
				currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass, symTabEntry);
				currentInsertPos = insertAttributeCode(attribute, currentInsertPos, modelCreationClass, symTabEntry);
				currentInsertPos = insertCreationCode(" /* add attribut */", currentInsertPos, modelCreationClass, symTabEntry);
				currentInsertPos++;
  		}
  		// set insert position to next line
  		currentInsertPos++;
    }
  
  // check code for method
  	LinkedHashSet<Method> methods = clazz.getMethods();
  	for (Method method : methods)
    {
	    
    }

  // check code for assoc
  	LinkedHashSet<Role> sourceRoles = clazz.getSourceRoles();
  	for (Role role : sourceRoles)
    {
	    
    }
  	
  	return currentInsertPos;
  }

	private int createAndInsertCodeForNewClazz(String callMethodName, Clazz modelCreationClass, 
																							SymTabEntry symTabEntry, Clazz clazz, LinkedHashMap<String, 
																							Clazz> handledClazzes, int currentInsertPos)
  {
		String modelClassName = clazz.getName();
	  // no creation code yet. Insert it.
	  currentInsertPos = insertCreationClassCode( currentInsertPos, modelClassName, modelCreationClass, symTabEntry);
	  

	  // insert code for new Attr()
	  LinkedHashSet<Attribute> clazzAttributes = clazz.getAttributes();
	  for (Attribute attribute : clazzAttributes)
	  { 	
	  	currentInsertPos = insertAttributeCode(attribute, currentInsertPos, modelCreationClass, symTabEntry);
	  	currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass, symTabEntry);	
	  }
	  currentInsertPos = 1 +insertCreationCode( ";", currentInsertPos -1, modelCreationClass, symTabEntry);

	  // insert code for new Method()
	  LinkedHashSet<Method> methods = clazz.getMethods();
	  for (Method method : methods)
	  {
	  	currentInsertPos = insertMethodeCode(method, currentInsertPos, modelCreationClass, symTabEntry);

	  }
	  // insert code for new Assoc
	  
	  LinkedHashSet<Role> roles = clazz.getSourceRoles();
	  roles.addAll(clazz.getTargetRoles());
	  currentInsertPos = handleAssocs(roles, currentInsertPos, modelCreationClass, symTabEntry, handledClazzes);
	  
	  return currentInsertPos;
  }

	private int handleAssocs(LinkedHashSet<Role> roles, int currentInsertPos, Clazz modelCreationClass, 
													SymTabEntry symTabEntry, LinkedHashMap<String, Clazz> handledClazzes)
  {
		ArrayList<Association> handledAssocs = new ArrayList<Association>();
		
		for (Role firstRole : roles)
    {
	    Association assoc = firstRole.getAssoc();
	    
	    if(assocHasHandled(assoc, handledAssocs)) {
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
	    	currentInsertPos = insertAssocCode(assoc, currentInsertPos, modelCreationClass, symTabEntry);
	    }
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
	  	
			if ( isEqual(source, target2) && isEqual(target, source2))
				return true;

    }
	  return false;
  }

	private int insertCreationClassCode(int currentInsertPos, String modelClassName, Clazz modelCreationClass, SymTabEntry symTabEntry)
  {
		StringBuilder text = new StringBuilder("\n      Clazz localVar = new Clazz(\"className\")\n");

		CGUtil.replaceAll(text, 
				"localVar", StrUtil.downFirstChar(CGUtil.shortClassName(modelClassName)) + "Class",
				"className", modelClassName);
		
	  return insertCreationCode( text, currentInsertPos, modelCreationClass, symTabEntry);
  }

	private int insertAttributeCode(Attribute attribute, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
  {
		StringBuilder text = new StringBuilder("      .withAttribute(\"attributeName\", \"attributeType\"attributeInit)");
		
		// has init value
		String initialization = attribute.getInitialization();
		if(initialization != null) 
		{
			initialization = ", \"" + initialization + "\"";
		}
		else
		{
			initialization = "";
		}
		
		CGUtil.replaceAll(text, 
				"attributeName", attribute.getName(),
				"attributeType", attribute.getType(),
				"attributeInit", initialization);
		return insertCreationCode( text, currentInsertPos, modelCreationClass, symTabEntry);

  }

	private int insertMethodeCode(Method method, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
  {
		
		StringBuilder text = new StringBuilder("\n      new Method()" +
																					 "\n			.withClazz(clazzName)" +
																					 "\n			.withSignature(\"methodSignature\");\n");
		
		String clazzName = method.getClazz().getName();
		clazzName = StrUtil.downFirstChar(CGUtil.shortClassName(clazzName)) + "Class";
		String signature = method.getSignature();
		
		CGUtil.replaceAll(text, 
				"clazzName", clazzName,
				"methodSignature", signature);
		
		return insertCreationCode( text, currentInsertPos, modelCreationClass, symTabEntry);
  }
	

	private int insertAssocCode(Association assoc, int currentInsertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
	{
		StringBuilder text = new StringBuilder("\n      new Association()" +
				 "\n			.withSource(\"sourceName\", sourceClazz, \"sourceRole\"sourceKind)" +
				 "\n			.withTarget(\"targetName\", targetClazz, \"targetRole\"targetKind);\n");
		
		String sourceRole = assoc.getSource().getCard();
		String sourceName = assoc.getSource().getName();
		String sourceClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getSource().getClazz().getName()))
												+ "Class";
		String sourceKind = assoc.getSource().getKind();
		if( !Role.VANILLA.equals(sourceKind)) 
		{
			sourceKind = ", \"" +sourceKind+"\"";
		}	
		else
		{
			sourceKind = "";
		}

		String targetRole = assoc.getTarget().getCard(); 
		String targetName = assoc.getTarget().getName();
		String targetClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getTarget().getClazz().getName()))
												+ "Class";
		String targetKind = assoc.getTarget().getKind();
		if( !Role.VANILLA.equals(targetKind)) 
		{
			targetKind = ", \"" +targetKind +"\"";
		}
		else
		{
			targetKind = "";
		}
		
		CGUtil.replaceAll(text, 
				"sourceName", sourceName,
				"sourceClazz", sourceClazz,
				"sourceRole", sourceRole,
				"sourceKind", sourceKind,
				"targetName", targetName,
				"targetClazz", targetClazz,
				"targetRole", targetRole,
				"targetKind", targetKind);

		return insertCreationCode(text, currentInsertPos, modelCreationClass, symTabEntry);
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
				if (StrUtil.stringEquals(name, sequencePartName) && StrUtil.stringEquals(type, sequencePartType) )
					return true;
			}
    }
		return false;
  }

	private int insertCreationCode(StringBuilder text, int insertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
  {   
		parser.getFileBody().insert(insertPos, text.toString());
		modelCreationClass.setFileHasChanged(true);
		return insertPos + text.length();
  }
	
	private int insertCreationCode(String string, int insertPos, Clazz modelCreationClass, SymTabEntry symTabEntry)
  { 
		StringBuilder text = new StringBuilder(string);
		return insertCreationCode( text, insertPos, modelCreationClass, symTabEntry);
  }
	
	private int searchForQualifiedNamePosition(String methodCall, int methodEndPos, Parser parser) {
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
	  for (Clazz clazz : classes)
	  {
	    if (StrUtil.stringEquals(clazz.getName(), className)) 
	    {
	    	return clazz;
	    }
	  }    
	  return new Clazz(className);
  }

   private LocalVarTableEntry findInLocalVarTable(LinkedHashMap<String, LocalVarTableEntry> localVarTable, String name)
   {
  	 for (LocalVarTableEntry entry : localVarTable.values())
  	 {
  		 if ("Clazz".equals(entry.getType()))
  		 {
  			 ArrayList<String> initSequence = entry.getInitSequence().get(0);
  			 if (initSequence.size() == 2 && initSequence.get(0).startsWith("new"))
  			 {
  				 String className = initSequence.get(1).replaceAll("\"", "");
  				 if (StrUtil.stringEquals(name, className))
  				 {
  					 return entry;
  				 }
  			 }
  		 }
  	 }

  	 return null;
   }
}

