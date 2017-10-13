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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.modelspace.CloudModelDirectory;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
 */
   public  class CloudModelFile implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }

	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setDir(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_FILENAME = "fileName";
   
   private String fileName;

   public String getFileName()
   {
      return this.fileName;
   }
   
   public void setFileName(String value)
   {
      if ( ! StrUtil.stringEquals(this.fileName, value)) {
      
         String oldValue = this.fileName;
         this.fileName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILENAME, oldValue, value);
      }
   }
   
   public CloudModelFile withFileName(String value)
   {
      setFileName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getFileName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_LASTMODIFIEDTIME = "lastModifiedTime";
   
   private long lastModifiedTime;

   public long getLastModifiedTime()
   {
      return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(long value)
   {
      if (this.lastModifiedTime != value) {
      
         long oldValue = this.lastModifiedTime;
         this.lastModifiedTime = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTMODIFIEDTIME, oldValue, value);
      }
   }
   
   public CloudModelFile withLastModifiedTime(long value)
   {
      setLastModifiedTime(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * CloudModelFile ----------------------------------- CloudModelDirectory
    *              files                   dir
    * </pre>
    */
   
   public static final String PROPERTY_DIR = "dir";

   private CloudModelDirectory dir = null;

   public CloudModelDirectory getDir()
   {
      return this.dir;
   }

   public boolean setDir(CloudModelDirectory value)
   {
      boolean changed = false;
      
      if (this.dir != value)
      {
         CloudModelDirectory oldValue = this.dir;
         
         if (this.dir != null)
         {
            this.dir = null;
            oldValue.withoutFiles(this);
         }
         
         this.dir = value;
         
         if (value != null)
         {
            value.withFiles(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DIR, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public CloudModelFile withDir(CloudModelDirectory value)
   {
      setDir(value);
      return this;
   } 

   public CloudModelDirectory createDir()
   {
      CloudModelDirectory value = new CloudModelDirectory();
      withDir(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
