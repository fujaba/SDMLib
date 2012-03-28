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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class ClassModel implements PropertyChangeInterface
{
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

   public ClassModel generate(String rootDir)
   {
      for (Clazz clazz : getClasses())
      {
         clazz.generate(rootDir);
      }
      
      generateCreatorCreatorClass(rootDir);
      
      for (Association assoc : getAssociations())
      {
         assoc.generate(rootDir);
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
      if (parser == null)
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
            
            CGUtil.replaceAll(text, 
               "className", CGUtil.shortClassName(className), 
               "packageName", packageName);
            
            fileBody.append(text.toString());
            
            fileHasChanged = true;
         }
         
         parser = new Parser()
         .withFileName(fileName)
         .withFileBody(fileBody);         
      }
      
      return parser;
   }


   //==========================================================================
   
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
         changed = this.classes.remove (value);
         
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

	   BufferedWriter out;

	   File dotFile = new File("doc/" + diagName + ".dot");
	   ScenarioManager.get().printFile(dotFile, dotFileText.toString());

	   // generate image
	   String command = "";
	   
	   if ((System.getProperty("os.name").toLowerCase()).contains("mac")) {
		   command = "../SDMLib/tools/Graphviz/osx_lion/makeimage.command " + diagName;
	   } else {
		   command = "../SDMLib/tools/makeimage.bat " + diagName;
	   }
	   try {
		   Process child = Runtime.getRuntime().exec(command);
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	   
	return diagName + ".svg";
   }

   private String dumpMethods(Clazz clazz) 
   {
	   StringBuilder allMethodsText = new StringBuilder
			   ( "<tr><td><table border='0' cellborder='0' cellspacing='0'> methodRow </table></td></tr>");

	   if (clazz.getMethods().size() > 0)
	   {
		   for (Method method : clazz.getMethods())
		   {
			   StringBuilder oneMethodText = new StringBuilder(
					   "<tr><td align='left'>methodDecl</td></tr>");

			   CGUtil.replaceAll(oneMethodText, "methodDecl", method.getSignature());

			   CGUtil.replaceAll(allMethodsText, "methodRow",
					   oneMethodText.append(" methodRow").toString());
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
      StringBuilder allAttrsText = new StringBuilder
         ( "<tr><td><table border='0' cellborder='0' cellspacing='0'> attrRow </table></td></tr>");

      if (clazz.getAttributes().size() > 0)
      {
         for (Attribute attr : clazz.getAttributes())
         {
            StringBuilder oneAttrText = new StringBuilder(
               "<tr><td align='left'>attrDecl</td></tr>");

            CGUtil.replaceAll(oneAttrText, "attrDecl", attr.getName() + " :" +
               CGUtil.shortClassNameHTMLEncoded(attr.getType()));

            CGUtil.replaceAll(allAttrsText, "attrRow",
               oneAttrText.append(" attrRow").toString());
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
         
         changed = this.associations.add (value);
         
         if (changed)
         {
            value.withModel(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromAssociations(Association value)
   {
      boolean changed = false;
      
      if ((this.associations != null) && (value != null))
      {
         changed = this.associations.remove (value);
         
         if (changed)
         {
            value.setModel(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS, null, value);
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
	  String srcDir = binDir.substring(0, binDir.length()-4);
	  File srcFolder = new File(srcDir);
//	  System.out.println(binDir);
	  if (srcFolder != null) {
//		System.out.println("package "+ srcFolder + "  found");
		
		ArrayList<File> javaFiles = new ArrayList<File>();
		String packagepath = packages.replace('.', '/');
		String[] includes = includePathes.split("\\s+");
		for (String include : includes) {	
			String newPath = srcFolder.getPath() + "/" + include + "/"  + packagepath;
//			System.out.println("source " + newPath);
			javaFiles.addAll(searchForJavaFiles(newPath));
		}
		
		// classes.add(foundClass)
//		System.out.println("java classes");
		for (File file : javaFiles) {
			String filePath = file.getAbsolutePath();
			filePath = filePath.replace(srcFolder.getPath() , "");
			filePath = filePath.replace(File.separatorChar, '.');
			int indexOfPackage = filePath.lastIndexOf(packages, filePath.length()-1);
			System.out.println("FilePath: " + filePath);
			filePath = filePath.substring(indexOfPackage, filePath.length() - 5);	
			
			if(!classes.contains(filePath)){
//				System.out.println("add " + filePath);
				Clazz clazz = new Clazz(filePath);
				classes.add(clazz );
			}
		}
			
		// parse each java file
		for (Clazz clazz : getClasses()) {
			// get list of members
			Parser parser = clazz.getOrCreateParser("examples");

			parser.indexOf(Parser.CLASS_END);

			LinkedHashMap<String, SymTabEntry> symTab = parser.getSymTab();
			
			Set<String> keySet = symTab.keySet();
			for (String memberName : keySet) {
				SymTabEntry entry = symTab.get(memberName);
//				System.out.println(clazz.getName()+":"+memberName);
				// do something with it.
				
				// add new methods
				if (memberName.startsWith(Parser.METHOD))
				{
					String[] split = memberName.split(":");
					String signature = split[1];
					
					// filter internal generated methods
					String filterString = "get(String) set(String,Object) getPropertyChangeSupport() removeYou()";
					
					if(filterString.indexOf(signature)<0) {
						new Method()
						.withClazz(clazz)
						.withSignature(signature);
					}
				}
				
				// add new attributes
				else if (memberName.startsWith(Parser.ATTRIBUTE))
				{
					String[] split = memberName.split(":");
					String attrName = split[1];

					// get type
					SymTabEntry symTabEntry = parser.getSymTab().get(memberName);

					// filter public static final constances
					String modifiers = symTabEntry.getModifiers();
					if (modifiers.indexOf("public") >= 0 
							&& modifiers.indexOf("static") >= 0
							&& modifiers.indexOf("final") >= 0)
					{
						// ignore
						continue;
					}

					new Attribute()
					.withClazz(clazz)
					.withName(attrName)
					.withType(symTabEntry.getType());
				}
				
				// TODO : add new assocs with roles,  
				
				// TODO : add super classes
				
				// TODO : add interfaces 
			}
		}
	}
      
      // add model creation code at invocation place, if not yet there
      
      
   }

   
   //==========================================================================
   
	private ArrayList<File> searchForJavaFiles(String path) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			File[] listFiles = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File file, String string) {
					return string.toLowerCase().endsWith(".java");
				}
			});
			Collections.addAll(javaFiles, listFiles);
			File[] directory = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File file, String string) {
					return  file.isDirectory();
				}
			});
			for (File dir : directory) {
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
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_CLASSES.equalsIgnoreCase(attrName))
      {
         addToClasses((Clazz) value);
         return true;
      }
      
      if ((PROPERTY_CLASSES + JsonIdMap.REMOVE_SUFFIX).equalsIgnoreCase(attrName))
      {
         removeFromClasses((Clazz) value);
         return true;
      }

      if (PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName))
      {
         addToAssociations((Association) value);
         return true;
      }
      
      if ((PROPERTY_ASSOCIATIONS + JsonIdMap.REMOVE_SUFFIX).equalsIgnoreCase(attrName))
      {
         removeFromAssociations((Association) value);
         return true;
      }

      return false;
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
      removeAllFromClasses();
      removeAllFromAssociations();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

