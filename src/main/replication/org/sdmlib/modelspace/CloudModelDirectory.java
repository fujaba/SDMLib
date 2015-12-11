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
   
package org.sdmlib.modelspace;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.modelspace.util.CloudModelFileSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonIdMap;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
*/
   public  class CloudModelDirectory implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutFiles(this.getFiles().toArray(new CloudModelFile[this.getFiles().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * CloudModelDirectory ----------------------------------- CloudModelFile
    *              dir                   files
    * </pre>
    */
   
   public static final String PROPERTY_FILES = "files";

   private CloudModelFileSet files = null;
   
   public CloudModelFileSet getFiles()
   {
      if (this.files == null)
      {
         return CloudModelFileSet.EMPTY_SET;
      }
   
      return this.files;
   }

   public CloudModelDirectory withFiles(CloudModelFile... value)
   {
      if(value==null){
         return this;
      }
      for (CloudModelFile item : value)
      {
         if (item != null)
         {
            if (this.files == null)
            {
               this.files = new CloudModelFileSet();
            }
            
            boolean changed = this.files.add (item);

            if (changed)
            {
               item.withDir(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FILES, null, item);
            }
         }
      }
      return this;
   } 

   public CloudModelDirectory withoutFiles(CloudModelFile... value)
   {
      for (CloudModelFile item : value)
      {
         if ((this.files != null) && (item != null))
         {
            if (this.files.remove(item))
            {
               item.setDir(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FILES, item, null);
            }
         }
      }
      return this;
   }

   public CloudModelFile createFiles()
   {
      CloudModelFile value = new CloudModelFile();
      withFiles(value);
      return value;
   }

   public CloudModelFile getOrCreateFiles(JsonIdMap fileDataIdMap, String fileName)
   {
      for (CloudModelFile file : this.getFiles())
      {
         if (file.getFileName().equals(fileName))
         {
            return file;
         }
      }
      
      CloudModelFile newFile = new CloudModelFile();
      
      fileDataIdMap.put(fileName, newFile);
      
      newFile.withFileName(fileName);
      
      this.withFiles(newFile);
      
      return newFile;
   } 
}
