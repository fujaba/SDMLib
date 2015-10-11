/*
   Copyright (c) 2014 zuendorf

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.DocEnvironment;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.EnumerationSet;

public class ClassModel extends SDMLibClass
{
	public static final String DEFAULTPACKAGE = "i.love.sdmlib";
   public static final String PROPERTY_CLASSES = "classes";
   private static final String PROPERTY_FEATURE = "feature";
   private Set<Feature> features=Feature.getAll();
   private String defaultAuthorName=System.getProperty("user.name");
   private ClazzSet classes;
   private GenClassModel generator;

   /**
    * @deprecated Use new ClassModel(packageName) instead
    */
   @Deprecated
   public ClassModel()
   {
		name = DEFAULTPACKAGE;
		
		Feature.reset();
   }
   
   /**
    * Root of an SDMLib class model. May be used to generate code or a class diagram.
    * May also be used to create Clazz objects, with attibutes and assocications.
    * 
    * <pre>
    *    ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
    * 
    *    Clazz groupAccountClass = model.createClazz("GroupAccount")
    *       .withAttribute("task", DataType.STRING);
    *                
    *    groupAccountClass.createMethod("getTaskNames")
    *       .with(new Parameter(DataType.DOUBLE))
    *       .with(new Parameter(DataType.STRING))
    *       .withReturnType(DataType.DOUBLE);
    *       
    *    Clazz personClass = model.createClazz("Person")
    *       .withAttribute("name", DataType.STRING)
    *       .withAttribute("balance", DataType.DOUBLE);
    *  
    *    groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
    * 
    *    model.generate();
    *    
    *    model.dumpClassDiagram("GroupAccountClassDiag");
    * </pre>
    * 
    * @param packageName
    * 
    * @see <a href="../GroupAccountClassModel.java">GroupAccountClassModel.java</a> 
    * 
    * @see #generate()
    * @see #dumpClassDiagram(String)
    */
   public ClassModel(String packageName)
   {
	  this();
      withName(packageName);
   }
   
   /**
    * Generate Java code from a class model:
    * 
    * <pre>
    *    ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
    * 
    *    Clazz groupAccountClass = model.createClazz("GroupAccount")
    *       .withAttribute("task", DataType.STRING);
    *                
    *    groupAccountClass.createMethod("getTaskNames")
    *       .with(new Parameter(DataType.DOUBLE))
    *       .with(new Parameter(DataType.STRING))
    *       .withReturnType(DataType.DOUBLE);
    *       
    *    Clazz personClass = model.createClazz("Person")
    *       .withAttribute("name", DataType.STRING)
    *       .withAttribute("balance", DataType.DOUBLE);
    *  
    *    groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
    * 
    *    model.generate();
    *    
    *    model.dumpClassDiagram("GroupAccountClassDiag");
    * </pre>
    * 
    * @return this for fluent code style
    * 
    * @see #dumpClassDiagram(String)
    */
   public ClassModel generate()
	{
	   File srcDir = new File("src/main/java");
	   
	   if (srcDir.exists())
	   {
	      return generate("src/main/java");
	   }
	   else 
	   {
	      return generate("src");
	   }
	}

   /**
    * Generate Java code from a class model:
    * 
    * <pre>
    *    ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
    * 
    *    Clazz groupAccountClass = model.createClazz("GroupAccount")
    *       .withAttribute("task", DataType.STRING);
    *                
    *    groupAccountClass.createMethod("getTaskNames")
    *       .with(new Parameter(DataType.DOUBLE))
    *       .with(new Parameter(DataType.STRING))
    *       .withReturnType(DataType.DOUBLE);
    *       
    *    Clazz personClass = model.createClazz("Person")
    *       .withAttribute("name", DataType.STRING)
    *       .withAttribute("balance", DataType.DOUBLE);
    *  
    *    groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
    * 
    *    model.generate("src/test/java");
    *    
    *    model.dumpClassDiagram("GroupAccountClassDiag");
    * </pre>
    * 
    * @param rootDir
    * 
    * @return this for fluent code style
    * 
    * @see #dumpClassDiagram(String)
    */
   public ClassModel generate(String rootDir)
	{
	   getGenerator().generate(rootDir);
	   return this;
	}
	
	public GenClassModel getGenerator(){
	   if(generator==null){
         this.setGenerator(new GenClassModel());
      }
	   return generator;
	}
	
   protected void setGenerator(GenClassModel value)
   {
      if (this.generator != value)
      {
         GenClassModel oldValue = this.generator;
         if (this.generator != null)
         {
            this.generator = null;
            oldValue.setModel(null);
         }
         this.generator = value;
         if (value != null)
         {
            value.setModel(this);
         }
      }
   }
	
   public ClazzSet getClasses()
   {
      if (this.classes == null)
      {
         return Clazz.EMPTY_SET;
      }
   
      return this.classes;
   }
	
	public Clazz getClazz(String name)
	{
		if (name == null) {
			return null;
		}
		for (Clazz c : getClasses()) {
			if (c.getName().equals(name) ) {
				return c;
			}else if(name.indexOf(".")>0) {
				if(c.getName().equals(name.substring(name.lastIndexOf(".")+1))) {
					return c;
				}
			}else if(c.getName().endsWith("." + name)){
				return c;
			}
		}
		return null;
	}
	
	

	/**
	 * Add a class to the class model. 
	 * On code generation this will generate the corresponding Java File
	 * 
	 * <pre>
    *    ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
    * 
    *    Clazz groupAccountClass = model.createClazz("GroupAccount")
    *       .withAttribute("task", DataType.STRING);
    *                
    *    groupAccountClass.createMethod("getTaskNames")
    *       .with(new Parameter(DataType.DOUBLE))
    *       .with(new Parameter(DataType.STRING))
    *       .withReturnType(DataType.DOUBLE);
    *       
    *    Clazz personClass = model.createClazz("Person")
    *       .withAttribute("name", DataType.STRING)
    *       .withAttribute("balance", DataType.DOUBLE);
    *  
    *    groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
    * 
    *    model.generate("src/test/java");
    *    
    *    model.dumpClassDiagram("GroupAccountClassDiag");
    * </pre>
    * 
    * 
	 * @param name Name of the class
	 * @return The representation of the class within the class model. 
	 * 
    * @see #generate()
	 */
	public Clazz createClazz(String name)
	{
	   if (this.name == null)
	   {
	      this.name = CGUtil.packageName(name);
	   }
		Clazz clazz = new Clazz(name);
		clazz.with(this);
		return clazz;
	}
	
	public Enumeration createEnumeration(String name) {
		if (this.name == null) {
			this.name = CGUtil.packageName(name);
		}
		Enumeration enumeration = (Enumeration) new Enumeration().withName(name);
		withEnumerations(enumeration);
		enumeration.withClassModel(this);
		return enumeration;
	} 

	/**
	 * Not for public use. 
	 * Use
	 *  
	 * @see org.sdmlib.storyboards.Storyboard#addClassDiagram(ClassModel model)
	 * 
	 * @param diagName
	 * @return json script to be embedded in an html page to display a class diagram 
	 */
	public String dumpClassDiagram(String diagName)
	{
	   GuiAdapter graphViz = GraphFactory.getAdapter();

	   return graphViz.dumpClassDiagram(diagName, this);
	}
	 
	 private String dumpClassDiagram(String diagName, String outputType)
	 {
	    GuiAdapter graphViz = GraphFactory.getAdapter(outputType);
	    
	    return graphViz.dumpClassDiagram(diagName, this);
	 }

	// ==========================================================================

	@Override
   public void removeYou()
	{
	   super.removeYou();
	   without(this.getClasses().toArray(new Clazz[this.getClasses().size()]));
      withoutClasses(this.getClasses().toArray(new Clazz[this.getClasses().size()]));
      withoutEnumerations(this.getEnumerations().toArray(new Enumeration[this.getEnumerations().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
		
	}

	public void removeAllGeneratedCode()
   {
	   getGenerator().removeAllGeneratedCode("src", "src", "src");
   }
	
	public void removeAllGeneratedCode(String rootDir)
   {
	   getGenerator().removeAllGeneratedCode(rootDir, rootDir, rootDir);
   }

	@Override
	public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }

   public ClassModel with(Clazz... value)
   {
      if(value==null){
         return this;
      }
      if (this.classes == null)
      {
         this.classes = new ClazzSet();
      }
      for (Clazz item : value)
      {
         if (item != null)
         {
            if ( this.classes.add (item))
            {
               item.with(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, null, item);
            }
         }
      }
      return this;
   } 

   public ClassModel without(Clazz... value)
   {
      if (this.classes == null){
         return this;
      }
      for (Clazz item : value)
      {
         if (item != null)
         {
            if (this.classes.remove(item))
            {
               item.setClassModel(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, item, null);
            }
         }
      }
      return this;
   }

   public ClassModel withFeature(Feature... value)
   {
      if(value==null){
         return this;
      }
      for (Feature item : value)
      {
         if (item != null)
         {
            if ( this.features.add (item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, null, item);
            }
         }
      }
      return this;
   }
   
   public ClassModel withoutFeature(Feature... value)
   {
      if(value==null){
         return this;
      }
      for (Feature item : value)
      {
         if (item != null)
         {
            if ( this.features.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, item, null);
            }
         }
      }
      return this;
   }
 
   public ClassModel withFeatures(HashSet<Feature> value)
   {
		if (value == null) {
			this.features.clear();
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.add(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_FEATURE, null, item);
				}
			}
		}
		return this;
   }

   public boolean hasFeature(Feature value)
   {
      return features.contains(value);
   }
   
   public boolean hasFeature(Feature feature, Clazz value) {
	  if(hasFeature(feature)) {
		  return feature.match(value);
	  }
	  return false;
   }
   
   @Override
   public ClassModel withName(String value)
   {
      setName(value);
      return this;
   }

   ClassModel withClasses(Clazz... value)
   {
      return with(value);
   } 

   ClassModel withoutClasses(Clazz... value)
   {
      return without(value);
   }

   Clazz createClasses()
   {
      Clazz value = new Clazz(null);
      withClasses(value);
      return value;
   }

   /**
    * dump classdiagram
    * 
    * @param diagramName  Diagrammname
    */
   public void dumpHTML(String diagramName) {
	   dumpHTML(diagramName, "doc", Javascript.NAME);
	}
   
   /**
    * dump classdiagram
    * 
    * @param diagramName  Diagrammname
    * @param folder       target folder
    * @param outputType   GuiAdapter name  (Javascript.NAME or GraphViz.NAME)
    */
   
   public void dumpHTML(String diagramName, String folder, String outputType)
   {
      new File(folder).mkdirs();
      
      String dumpClassDiagram = dumpClassDiagram(diagramName ,outputType);
      
      // build index 
      String htmlTemplate = "<html>\n" +
            "<head>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"includes/diagramstyle.css\">\n" +
            "<script src=\"includes/dagre.min.js\"></script>\n"+
            "<script src=\"includes/graph.js\"></script>\n"+
            "<script src=\"includes/drawer.js\"></script>\n"+
            "</head>\n" +
            "<body>\n" +
            "bodytext\n" + 
            "</body>\n" + 
            "</html>\n";

      htmlTemplate = htmlTemplate.replaceFirst("bodytext", dumpClassDiagram);
      
      File file = new File (folder+"/"+diagramName+".html");
      try {
         PrintStream out = new PrintStream(file);
         out.println(htmlTemplate);
         out.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      new DocEnvironment().copyJS(folder);
   }
   
   /********************************************************************
    * <pre>
    *              one                       many
    * ClassModel ----------------------------------- Enumeration
    *              classModel                   enumerations
    * </pre>
    */
   
   public static final String PROPERTY_ENUMERATIONS = "enumerations";

   private EnumerationSet enumerations = null;
   
   public EnumerationSet getEnumerations()
   {
      if (this.enumerations == null)
      {
         return Enumeration.EMPTY_SET;
      }
   
      return this.enumerations;
   }

   public ClassModel withEnumerations(Enumeration... value)
   {
      if(value==null){
         return this;
      }
      for (Enumeration item : value)
      {
         if (item != null)
         {
            if (this.enumerations == null)
            {
               this.enumerations = new EnumerationSet();
            }
            
            boolean changed = this.enumerations.add (item);

            if (changed)
            {
               item.withClassModel(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMERATIONS, null, item);
            }
         }
      }
      return this;
   } 

   public ClassModel withoutEnumerations(Enumeration... value)
   {
      for (Enumeration item : value)
      {
         if ((this.enumerations != null) && (item != null))
         {
            if (this.enumerations.remove(item))
            {
               item.setClassModel(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMERATIONS, item, null);
            }
         }
      }
      return this;
   }

   public Enumeration createEnumerations()
   {
      Enumeration value = new Enumeration();
      withEnumerations(value);
      return value;
   }

	public String getAuthorName() {
		return defaultAuthorName;
	}

	public void withAuthorName(String defaultAuthorName) {
		this.defaultAuthorName = defaultAuthorName;
	}
}
