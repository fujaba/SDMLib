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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.EnumerationSet;

import de.uniks.networkparser.graph.GraphList;

public class ClassModel extends SDMLibClass
{
   public static final String PROPERTY_CLASSES = "classes";
   private static final String PROPERTY_FEATURE = "feature";
   private Set<Feature> features=Feature.getAll();
   private ClazzSet classes;
   private GenClassModel generator;

   public ClassModel(){
		name = "i.love.sdmlib";
   }
   
   public ClassModel(String packageName){
      withName(packageName);
   }
   
	public ClassModel generate()
	{
		return generate("src/main/java");
	}

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
	   for (Clazz c : getClasses())
      {
         if (c.getName().endsWith(name))
         {
            return c;
         }
      }
	   return null;
	}

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

   public Set<Feature> getFeatures()
   {
      return features;
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
            "<script src=\"includes/graph.js\"></script>\n"+
            "<script src=\"includes/dagre.js\"></script>\n"+
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
      
      copyJSBib(folder);
      
      
   }
   private void copyJSBib(String folder)
   {
      new File(folder+"/includes").mkdirs();

      // add javascript files
      copyDocFile(folder, "", "dagre.js");
      copyDocFile(folder, "", "drawer.js");
      copyDocFile(folder, "", "graph.js");
      copyDocFile(folder, "", "diagramstyle.css");
   } 
   
   private void copyDocFile(String targetFolder, String dir, String file)
   {
      File target=new File(targetFolder+ "/includes/" + file);

      if(dir == null){
    	  dir="";
    	  
      }
      if(dir.length()>0 && !dir.endsWith("/")) {
    	  dir += "/";
      }
      InputStream is = GraphList.class.getResourceAsStream(dir + file);

      if(is!=null)
      {
         final int BUFF_SIZE = 5 * 1024; // 5KB
         final byte[] buffer = new byte[BUFF_SIZE];

         try
         {
            if(!target.exists()){
               target.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(target);

            while (true) {
               int count = is.read(buffer);
               if (count == -1)
                  break;
               out.write(buffer, 0, count);
            }
            out.close();
            is.close();
         }
         catch (Exception e)
         {
            // e.printStackTrace();
         }
      }
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
}
