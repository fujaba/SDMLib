package org.sdmlib.serialization.graph;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import java.util.ArrayList;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;

public class GraphNode implements BaseEntity
{
   private String className;
   private String id;
   private String headImage;
   private boolean visible = true;
   private ArrayList<Attribute> values = new ArrayList<Attribute>();

   @Override
   public BaseEntityList getNewArray()
   {
      return new GraphList();
   }

   @Override
   public BaseEntity getNewObject()
   {
      return new GraphNode();
   }

   @Override
   public GraphNode withVisible(boolean value)
   {
      this.visible = value;
      return this;
   }

   @Override
   public boolean isVisible()
   {
      return visible;
   }

   // GETTER AND SETTER
   public String getClassName(boolean shortName)
   {
      if (!shortName || className == null || className.lastIndexOf(".") < 0)
      {
         return className;
      }
      return className.substring(className.lastIndexOf(".") + 1);
   }

   public GraphNode withClassName(String className)
   {
      this.className = className;
      return this;
   }

   public String getClassName()
   {
      return className;
   }

   public String getId()
   {
      return id;
   }

   public String getTyp(String typ, boolean shortName)
   {
      if (typ.equals(GraphIdMap.OBJECT))
      {
         return getId();
      }
      else if (typ.equals(GraphIdMap.CLASS))
      {
         return getClassName(shortName);
      }
      return "";
   }

   public ArrayList<Attribute> getAttributes()
   {
      return values;
   }

   public GraphNode withTyp(String typ, String value)
   {
      if (typ.equals(GraphIdMap.OBJECT))
      {
         withId(value);
      }
      else if (typ.equals(GraphIdMap.CLASS))
      {
         withClassName(value);
      }
      return this;
   }

   public GraphNode withId(String id)
   {
      this.id = id;
      return this;
   }

   public void addValue(String property, String clazz, String value)
   {
      values.add(new Attribute().withKey(property).withClazz(clazz)
         .withValue(value));
   }

   public void addValue(Attribute attribute)
   {
      values.add(attribute);
   }

   @Override
   public String toString(int indentFactor)
   {
      return toString(0, 0);
   }

   @Override
   public String toString(int indentFactor, int intent)
   {
      if (id == null)
      {
         return className;
      }
      return id;
   }

   public String getHeadImage()
   {
      return headImage;
   }

   public GraphNode withHeadImage(String headImage)
   {
      this.headImage = headImage;
      return this;
   }
}
