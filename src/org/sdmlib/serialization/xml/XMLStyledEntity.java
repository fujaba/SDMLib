package org.sdmlib.serialization.xml;

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
import org.sdmlib.serialization.gui.Style;

public class XMLStyledEntity extends XMLEntity
{
   private Style style = new Style();

   @Override
   protected String toStringValue(int indentFactor)
   {
      StringBuilder sb = new StringBuilder();

      // Starttag
      if (style.isBold())
      {
         sb.append("<b>");
      }
      if (style.isItalic())
      {
         sb.append("<i>");
      }
      sb.append(super.toStringValue(indentFactor));

      // EndTag
      if (style.isItalic())
      {
         sb.append("</i>");
      }
      if (style.isBold())
      {
         sb.append("</b>");
      }
      return sb.toString();
   }

   public boolean set(String attribute, Object value)
   {
      if (style.set(attribute, value))
      {
         return true;
      }
      return false;
   }

   @Override
   public Object get(String key)
   {
      Object attrValue = style.get(key);
      if (attrValue != null)
      {
         return attrValue;
      }
      return super.get(key);
   }

   public boolean isBold()
   {
      return style.isBold();
   }

   public void setBold(boolean value)
   {
      style.withBold(value);
   }
}
