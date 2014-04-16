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

import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.StrUtil;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.ClazzSet;

public class ClassModel extends SDMLibClass
{
   public static final String PROPERTY_ASSOCIATIONS = "associations";
   public static final String PROPERTY_CLASSES = "classes";
   public static final String PROPERTY_PACKAGENAME = "packageName";
   private Set<Feature> features=Feature.getNone();
   private AssociationSet associations = null;
   private ClazzSet classes;
   private String packageName;
   private GenClassModel generator;
	
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
	
	public ClazzSet getClasses()
	{
		if (classes == null)
		{
			return new ClazzSet();
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
		Clazz clazz = new Clazz(name);
		clazz.withClassModel(this);
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
				value.withClassModel(null);
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

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * ClassModel ----------------------------------- Association
	 *              model                   associations
	 * </pre>
	 */
	public AssociationSet getAssociations()
	{
		if (this.associations == null)
		{
			return new AssociationSet();
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

	// ==========================================================================

	public void removeYou()
	{
		removeAllFromClasses();
		removeAllFromAssociations();
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

	//==========================================================================
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

	@Override
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
}
