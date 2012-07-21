package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.classes.Clazz;

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

}

