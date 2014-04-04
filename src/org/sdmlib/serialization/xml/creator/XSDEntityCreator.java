package org.sdmlib.serialization.xml.creator;

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

import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorXML;
import org.sdmlib.serialization.xml.XMLEntity;
import org.sdmlib.serialization.xml.XSDEntity;

public class XSDEntityCreator implements SendableEntityCreatorXML, XMLGrammar
{
   private String nameSpace = "";
   private ArrayList<String> privateStack = new ArrayList<String>();
   public static final String[] IGNORETAGS = new String[]
   { "annotation", "documentation", "complextype", "simpletype" };

   /**
    * @param namespace
    *           the NameSpace for xsd
    */
   public XSDEntityCreator withNameSpace(String namespace)
   {
      this.nameSpace = namespace;
      return this;
   }

   @Override
   public String[] getProperties()
   {
      return new String[]
      { XSDEntity.PROPERTY_CHOICE, XSDEntity.PROPERTY_SEQUENCE,
            XSDEntity.PROPERTY_ATTRIBUTE, XSDEntity.PROPERTY_MINOCCURS,
            XSDEntity.PROPERTY_MAXOCCURS };
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new XSDEntity();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      return ((XSDEntity) entity).getValue(attribute);
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      ((XSDEntity) entity).put(attribute, value);
      return true;
   }

   @Override
   public String getTag()
   {
      return nameSpace + ":element";
   }

   @Override
   public boolean parseChild(XMLEntity entity, XMLEntity child, Tokener value)
   {
      String tag = child.getTag();
      for (String ignoreTag : IGNORETAGS)
      {
         if (tag.equalsIgnoreCase(nameSpace + ":" + ignoreTag))
         {
            return true;
         }
      }
      if (child.getTag().equalsIgnoreCase(
         nameSpace + ":" + XSDEntity.PROPERTY_SEQUENCE))
      {
         this.privateStack.add(XSDEntity.PROPERTY_SEQUENCE);
      }
      else if (child.getTag().equalsIgnoreCase(
         nameSpace + ":" + XSDEntity.PROPERTY_CHOICE))
      {
         this.privateStack.add(XSDEntity.PROPERTY_CHOICE);
      }
      return false;
   }

   @Override
   public void addChildren(XMLEntity parent, XMLEntity child)
   {
      if (this.privateStack.size() > 0)
      {
         String lastTag = this.privateStack.get(this.privateStack.size() - 1);
         if (lastTag.equals(XSDEntity.PROPERTY_CHOICE))
         {
            ((XSDEntity) parent).setValue(XSDEntity.PROPERTY_CHOICE, child);
         }
         else if (lastTag.equals(XSDEntity.PROPERTY_SEQUENCE))
         {
            ((XSDEntity) parent).setValue(XSDEntity.PROPERTY_SEQUENCE, child);
         }

      }
      parent.addChild(child);
   }

   @Override
   public void endChild(String tag)
   {
      this.privateStack.remove(this.privateStack.size() - 1);
   }

}
