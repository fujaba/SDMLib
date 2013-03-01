package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.modelsets.StringList;

public class AttributeSet extends LinkedHashSet<Attribute>
{
   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }

   public AttributeSet withInitialization(String value)
   {
      for (Attribute obj : this)
      {
         obj.withInitialization(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Attribute obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   public AttributeSet withClazz(Clazz value)
   {
      for (Attribute obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Attribute elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Attribute";
   }


   public AttributeSet with(Attribute value)
   {
      this.add(value);
      return this;
   }
   
   public AttributeSet without(Attribute value)
   {
      this.remove(value);
      return this;
   }
}


