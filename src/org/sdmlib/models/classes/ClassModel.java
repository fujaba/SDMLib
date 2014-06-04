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

import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.util.ClazzSet;

public class ClassModel extends SDMLibClass
{
   public static final String PROPERTY_CLASSES = "classes";
   private Set<Feature> features=Feature.getAll();
   private ClazzSet classes;
   private GenClassModel generator;

   public ClassModel(){
      
   }
   public ClassModel(String packageName){
      withName(packageName);
   }
   
	public ClassModel generate()
	{
		return generate("src");
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
	
   public void setGenerator(GenClassModel value)
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
		clazz.withClassModel(this);
		return clazz;
	}

	public boolean addToClasses(Clazz value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.classes == null)
         {
            this.classes = new ClazzSet();
         }
         
         changed = this.classes.add (value);
         
         if (changed)
         {
            value.withClassModel(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSES, null, value);
         }
      }
         
      return changed;   
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
		JsonToGraphViz graphViz = new JsonToGraphViz();
		
		return graphViz.dumpClassDiagram(diagName, this);
	}

	// ==========================================================================

	@Override
   public void removeYou()
	{
	   super.removeYou();
		removeAllFromClasses();
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
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      return _.substring(1);
   }

   public ClassModel withClasses(Clazz... value)
   {
      if(value==null){
         return this;
      }
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

   public Set<Feature> getFeatures()
   {
      return features;
   }

   public ClassModel withFeature(Feature value)
   {
      this.features.add(value);
      return this;
   }
   public ClassModel withFeatures(Set<Feature> value)
   {
      this.features = value;
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
}
