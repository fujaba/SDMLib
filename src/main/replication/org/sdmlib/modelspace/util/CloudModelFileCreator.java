/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace.util;

import org.sdmlib.modelspace.CloudModelDirectory;
import org.sdmlib.modelspace.CloudModelFile;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class CloudModelFileCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      CloudModelFile.PROPERTY_FILENAME,
      CloudModelFile.PROPERTY_LASTMODIFIEDTIME,
      CloudModelFile.PROPERTY_DIR,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new CloudModelFile();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (CloudModelFile.PROPERTY_FILENAME.equalsIgnoreCase(attribute))
      {
         return ((CloudModelFile) target).getFileName();
      }

      if (CloudModelFile.PROPERTY_LASTMODIFIEDTIME.equalsIgnoreCase(attribute))
      {
         return ((CloudModelFile) target).getLastModifiedTime();
      }

      if (CloudModelFile.PROPERTY_DIR.equalsIgnoreCase(attribute))
      {
         return ((CloudModelFile) target).getDir();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (CloudModelFile.PROPERTY_FILENAME.equalsIgnoreCase(attrName))
      {
         ((CloudModelFile) target).withFileName((String) value);
         return true;
      }

      if (CloudModelFile.PROPERTY_LASTMODIFIEDTIME.equalsIgnoreCase(attrName))
      {
         ((CloudModelFile) target).withLastModifiedTime(Long.parseLong(value.toString()));
         return true;
      }

      if (CloudModelFile.PROPERTY_DIR.equalsIgnoreCase(attrName))
      {
         ((CloudModelFile) target).setDir((CloudModelDirectory) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CloudModelFile) entity).removeYou();
   }
}
