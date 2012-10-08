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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.classes.creators.AssociationSet;
import org.sdmlib.models.classes.creators.ClazzSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.scenarios.ScenarioManager;
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
	
	public ClassModel generate(String rootDir)
	{
	   return generate(rootDir, rootDir);
	}

	public ClassModel generate(String rootDir, String helpersDir)
	{
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

		return this;
	}

	private void generateCreatorCreatorClass(String rootDir)
   {
      // take first class to find package

      getOrCreateCreatorCreatorParser(rootDir);

      for (Clazz clazz : getClasses())
      {
         if (!clazz.isInterfaze()) 
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
			if (!clazz.isInterfaze()) 
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
         // try to find existing file
         Clazz firstClass = getClasses().iterator().next();

         creatorCreatorClassName = firstClass.getName();

         int pos = creatorCreatorClassName.lastIndexOf('.');

         String packageName = creatorCreatorClassName.substring(0, pos) + ".creators";

         creatorCreatorClassName = packageName + ".CreatorCreator";

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
                           "      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);\n" + 
                           "      \n" + 
                           "      jsonIdMap.addCreator(getCreatorSet());\n" + 
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
			Clazz firstClass = getClasses().iterator().next();

			modelPatternClassName = firstClass.getName();

			int pos = modelPatternClassName.lastIndexOf('.');

			String packageName = modelPatternClassName.substring(0, pos) + ".creators";

			modelPatternClassName = packageName + ".ModelPattern";

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

	public String dumpClassDiag(String rootdir, String diagName)
	{
		// generate dot file
		StringBuilder dotFileText = new StringBuilder(
				"\n digraph ClassDiagram {" 
			+ "\n    node [shape = none, fontsize = 10]; " 
			+ "\n    edge [fontsize = 10];" 
			+ "\n    "
		  + "\n    modelClasses" 
			+ "\n    " +
		    // "\n    g1 -- p2 " +
		    // "\n    g1 -- p3 [headlabel = \"persons\" taillabel = \"groupAccounter\"];" +
		    "\n    " 
		  + "\n    modelAssocs" 
		  + "\n}" 
		  + "\n");

		// add classes
		StringBuilder modelClassesText = new StringBuilder();

		for (Clazz clazz : this.getClasses())
		{
			StringBuilder modelClassText = new StringBuilder(
			    "\n    _className [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td HREF=\"classfilename\">className</td> </tr> attrCompartment methodCompartment </table>>];");

			if (clazz.isInterfaze())
				CGUtil.replaceAll(modelClassText, "table border", "table color='lightgrey' border");
			
			CGUtil.replaceAll(modelClassText, 
			   "className", CGUtil.shortClassNameHTMLEncoded(clazz.getName()),
			   "classfilename", "../" + rootdir + "/" + clazz.getName().replaceAll("\\.", "/") + ".java", 
			   "attrCompartment", dumpAttributes(clazz), 
			   "methodCompartment", dumpMethods(clazz));

			modelClassesText.append(modelClassText.toString());
		}


		StringBuilder allAssocsText = new StringBuilder();
		
		// add class inheritance	
		for (Clazz clazz : this.getClasses())
		{
			if (clazz.getSuperClass() != null)
			{

				StringBuilder oneSuperClassText = new StringBuilder("\n    _superClass ->  _mClass [dir = \"back\" arrowtail = \"empty\"];");

				CGUtil.replaceAll(oneSuperClassText, "superClass", CGUtil.shortClassName(clazz.getSuperClass().getName())
																			, "mClass", CGUtil.shortClassName(clazz.getName()));

				allAssocsText.append(oneSuperClassText.toString());
			}
		}
		
		// add interface inheritance
		for (Clazz clazz : this.getClasses())
		{
		   for (Clazz interfaceClass : clazz.getInterfaces())
		   {	
		      if (interfaceClass.isInterfaze())
		      {
		         StringBuilder oneSuperClassText = new StringBuilder("\n    _interfaceClass ->  _mClass [dir = \"back\" arrowtail = \"empty\"];");

		         CGUtil.replaceAll(oneSuperClassText, 
		            "interfaceClass", CGUtil.shortClassName(interfaceClass.getName()), 
		            "mClass", CGUtil.shortClassName(clazz.getName()));

		         allAssocsText.append(oneSuperClassText.toString());
		      }
		   }
		}

		// add associations
		for (Association assoc : getAssociations())
		{
			StringBuilder oneAssocText = new StringBuilder("\n    _sourceClass -> _targetClass [headlabel = \"targetRole\" taillabel = \"sourceRole\" arrowhead = \"none\" ];");

			CGUtil.replaceAll(oneAssocText, 
			   "sourceClass", CGUtil.shortClassName(assoc.getSource().getClazz().getName()), 
			   "targetClass", CGUtil.shortClassName(assoc.getTarget().getClazz().getName()), 
			   "sourceRole", labelForRole(assoc.getSource()), 
			   "targetRole", labelForRole(assoc.getTarget()));

			allAssocsText.append(oneAssocText.toString());
		}
		
		CGUtil.replaceAll(dotFileText, "modelClasses", modelClassesText.toString(), "modelAssocs", allAssocsText.toString());

		// write dot file
		File docDir = new File("doc");
		docDir.mkdir();

		// BufferedWriter out;

		File dotFile = new File("doc/" + diagName + ".dot");
		ScenarioManager.get().printFile(dotFile, dotFileText.toString());

		// generate image
		String command = "";

		if ((System.getProperty("os.name").toLowerCase()).contains("linux"))
		{
			command = "../SDMLib/tools/Graphviz/linux/makeimage.sh " + diagName;
		}
		else if ((System.getProperty("os.name").toLowerCase()).contains("mac"))
		{
			command = "../SDMLib/tools/Graphviz/osx_lion/makeimage.command " + diagName;
		}
		else
		{
			command = "../SDMLib/tools/makeimage.bat " + diagName;
		}
		try
		{
			Process exec = Runtime.getRuntime().exec(command);
			exec.waitFor();
			int exitValue = exec.exitValue();
			if (exitValue != 0 ) {
				System.err.println("Something is wrong with Graphviz. May missing executable");
				if ((System.getProperty("os.name").toLowerCase()).contains("linux"))
				{
					System.err.println("You have to install graphviz, try \'sudo apt-get install graphviz\'");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return diagName + ".svg";
	}

   private String labelForRole(Role role)
   {
      String result = role.getName();
      
      if (role.getCard().equals(R.MANY))
      {
         result = result + " *";
      }
      
      return result;
   }
   
	private String dumpMethods(Clazz clazz)
	{
		StringBuilder allMethodsText = new StringBuilder("<tr><td><table border='0' cellborder='0' cellspacing='0'> methodRow </table></td></tr>");

		if (clazz.getMethods().size() > 0)
		{
			for (Method method : clazz.getMethods())
			{
				StringBuilder oneMethodText = new StringBuilder("<tr><td align='left'>methodDecl</td></tr>");

				CGUtil.replaceAll(oneMethodText, "methodDecl", CGUtil.shortClassNameHTMLEncoded(method.getSignature()));

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

	public void updateFromCode(String rootDir, String includePathes, String packages)
	{
		// find java files
		String binDir = getClass().getClassLoader().getResource(".").getPath();
		String srcDir = binDir.substring(0, binDir.length() - 4);
		File srcFolder = new File(srcDir);

		if (srcFolder != null)
		{
			ArrayList<File> javaFiles = searchForJavaFiles(includePathes, packages, srcFolder);
			addJavaFilesToClasses(packages, srcFolder, javaFiles);
			
			if (getClasses().isEmpty()) {
				System.out.println("no class files found !!!! END");
				return;
			}			
			
			// parse each java file
			for (Clazz clazz : getClasses())
			{
				handleMember(clazz, rootDir);
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

				// type is unknown
				if (partnerClass == null) {
					new Attribute()
						.withName(memberName)
						.withType(partnerTypeName)
						.withClazz(clazz);
					continue;
				}

				String name = StrUtil.upFirstChar(memberName);

				Method addToMethod = findMethod(clazz, setterPrefix + name + "(" + partnerClassName + ")");

				if (addToMethod == null)
					continue;

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

				for (String qualifiedName : methodBodyQualifiedNames)
				{
					if (qualifiedName.startsWith("value.with"))
					{
						handleAssoc(clazz, rootDir, memberName, card, partnerClassName, partnerClass, qualifiedName);
					}
				}
			}
			// remove getter with setter or addTo removeFrom removeAllFrom without
		}
		for (String memberName : memberNames)
		{
			// remove getter with setter or addTo removeFrom removeAllFrom without
			findAndRemoveMethods(clazz, memberName, "get set with without addTo removeFrom removeAllFrom iteratorOf hasIn sizeOf removePropertyChange addPropertyChange");
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

	private void handleAssoc(Clazz clazz, String rootDir, String memberName, R card, String partnerClassName, Clazz partnerClass, String qName)
	{
		String partnerAttrName = qName.substring("value.with".length());
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
		
		if (memberClass != null) {
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
				LinkedHashSet<Clazz> classes = getClasses();

				for (Clazz eClazz : classes)
				{
					if (eClazz.getName().equals(importName))
					{
						return eClazz;
					}
				}
			}		
		}
		String name = clazz.getName();
		int lastIndex = name.lastIndexOf('.');
		name = name.substring(0, lastIndex + 1) + signature;

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
		for (File file : javaFiles)
		{
			String filePath = file.getAbsolutePath();
			filePath = filePath.replace(srcFolder.getPath(), "");
			filePath = filePath.replace(File.separatorChar, '.');
			String[] packages = packageString.split("\\s+"); 
			addClassToClasses(filePath, packages);	
		}
	}

	private void addClassToClasses(String filePath, String[] packages) {
		for (String pAckage : packages) {
			if (filePath.contains(pAckage)) {
				int indexOfPackage = filePath.lastIndexOf(pAckage, filePath.length() - 1);					
				filePath = filePath.substring(indexOfPackage, filePath.length() - 5);
				if (!classExists(filePath))
				{
					Clazz clazz = new Clazz(filePath);
					classes.add(clazz);
				}
				return;
			}
		}
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

	private String findPartnerClassName(String partnerTypeName)
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
			callMethodLineNumber = secondStackTraceElement.getLineNumber();
		}

		// parse the model creation file
		Clazz modelCreationClass = getOrCreateClazz(className);

		creatorCreatorParser = modelCreationClass.getOrCreateParser(rootDir);
		
		removeFromClasses(modelCreationClass);

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
				String token = Parser.NAME_TOKEN + ":" + entry.getName();
				int methodCallStartPos = creatorCreatorParser.methodCallIndexOf(token, symTabEntry.getBodyStartPos(), symTabEntry.getEndPos());
				token = Parser.NAME_TOKEN + ":;";
				currentInsertPos = creatorCreatorParser.indexOfInMethodBody(token, methodCallStartPos, symTabEntry.getEndPos());
				// add attribut
				currentInsertPos = insertCreationCode("\n			/*add attribut*/", currentInsertPos, modelCreationClass);
				currentInsertPos = insertCreationCode("\n", currentInsertPos, modelCreationClass);
				currentInsertPos = insertCreationAttributeCode(attribute, currentInsertPos, modelCreationClass, symTabEntry);
				currentInsertPos++;
			}

			// set insert position to next line
			currentInsertPos++;
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

				if (sourceClassName.equals(string))
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

		for (String string : localVarTable.keySet())
		{

			if (string.startsWith("Association_"))
			{
				LocalVarTableEntry localVarTableEntry = localVarTable.get(string);

				if (compareAssocDecl(role.getAssoc(), localVarTableEntry))
				{
					assocIsNew = false;
					break;
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
			currentInsertPos = insertCreationAssociationCode(role.getAssoc(), currentInsertPos, modelCreationClass, symTabEntry);
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
		StringBuilder text = new StringBuilder("\n      new Association()" + "\n			.withSource(\"sourceName\", sourceClazz, \"sourceRole\"sourceKind)"
		    + "\n			.withTarget(\"targetName\", targetClazz, \"targetRole\"targetKind);\n");

		String sourceRole = assoc.getSource().getCard();
		String sourceName = assoc.getSource().getName();
		String sourceClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getSource().getClazz().getName())) + "Class";
		String sourceKind = assoc.getSource().getKind();
		if (!Role.VANILLA.equals(sourceKind))
		{
			sourceKind = ", \"" + sourceKind + "\"";
		}
		else
		{
			sourceKind = "";
		}

		String targetRole = assoc.getTarget().getCard();
		String targetName = assoc.getTarget().getName();
		String targetClazz = StrUtil.downFirstChar(CGUtil.shortClassName(assoc.getTarget().getClazz().getName())) + "Class";
		String targetKind = assoc.getTarget().getKind();
		if (!Role.VANILLA.equals(targetKind))
		{
			targetKind = ", \"" + targetKind + "\"";
		}
		else
		{
			targetKind = "";
		}

		CGUtil.replaceAll(text, "sourceName", sourceName, "sourceClazz", sourceClazz, "sourceRole", sourceRole, "sourceKind", sourceKind, "targetName", targetName, "targetClazz",
		    targetClazz, "targetRole", targetRole, "targetKind", targetKind);

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

         for (GenericLink currentLink : currentObject.getOutgoingLinks())
         {
            allLinks.add(currentLink);
            
            GenericObject neighbor = currentLink.getTgt();
            if ( ! allObjects.contains(neighbor))
            {
               todoObjects.add(neighbor);
            }
         }

         for (GenericLink currentLink : currentObject.getIncommingLinks())
         {
            allLinks.add(currentLink);
            
            GenericObject neighbor = currentLink.getSrc();
            if ( ! allObjects.contains(neighbor))
            {
               todoObjects.add(neighbor);
            }
         }
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
               currentClazz.getOrCreateAttribute(attr.getName(), "String");
            }
         }
      }
      
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
            .withSource(sourceLabel, this.getOrCreateClazz(packageName + "." + sourceType), R.MANY)
            .withTarget(targetLabel, getOrCreateClazz(packageName + "." + targetType), R.MANY)
            .withModel(this);
         }
      }

      return this;
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
}

