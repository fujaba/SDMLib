/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.chats;

import java.beans.PropertyChangeSupport;

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;

public class DrawPointFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      HandleMouseMove, HandleMouseMoveMessage
   }
   
   public Object[] getTaskNames()
	{
		return TaskNames.values();
	}

   //==========================================================================
   
   public void run(  )
   {
      switch (TaskNames.values()[taskNo])
      {
      case HandleMouseMove:
         switchToThisAnd(gui.getPeer());
         break;

      case HandleMouseMoveMessage:
         gui.paintPoint(x, y, r, g, b);
         break;
         
      default:
         break;
      }
   }

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return getX();
      }

      if (PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return getY();
      }

      if (PROPERTY_GUI.equalsIgnoreCase(attribute))
      {
         return getGui();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_R.equalsIgnoreCase(attribute))
      {
         return getR();
      }

      if (PROPERTY_G.equalsIgnoreCase(attribute))
      {
         return getG();
      }

      if (PROPERTY_B.equalsIgnoreCase(attribute))
      {
         return getB();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_X.equalsIgnoreCase(attrName))
      {
         setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_GUI.equalsIgnoreCase(attrName))
      {
         setGui((PeerToPeerChat) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_R.equalsIgnoreCase(attrName))
      {
         setR(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_G.equalsIgnoreCase(attrName))
      {
         setG(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_B.equalsIgnoreCase(attrName))
      {
         setB(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((TaskFlow) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setSubFlow(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_X = "x";
   
   private int x;

   public int getX()
   {
      return this.x;
   }
   
   public void setX(int value)
   {
      if (this.x != value)
      {
         int oldValue = this.x;
         this.x = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_X, oldValue, value);
      }
   }
   
   public DrawPointFlow withX(int value)
   {
      setX(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_Y = "y";
   
   private int y;

   public int getY()
   {
      return this.y;
   }
   
   public void setY(int value)
   {
      if (this.y != value)
      {
         int oldValue = this.y;
         this.y = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_Y, oldValue, value);
      }
   }
   
   public DrawPointFlow withY(int value)
   {
      setY(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_GUI = "gui";
   
   private PeerToPeerChat gui;

   public PeerToPeerChat getGui()
   {
      return this.gui;
   }
   
   public void setGui(PeerToPeerChat value)
   {
      if (this.gui != value)
      {
         PeerToPeerChat oldValue = this.gui;
         this.gui = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GUI, oldValue, value);
      }
   }
   
   public DrawPointFlow withGui(PeerToPeerChat value)
   {
      setGui(value);
      return this;
   } 


   
   //==========================================================================
   
   public static final String PROPERTY_R = "r";
   
   private int r;

   public int getR()
   {
      return this.r;
   }
   
   public void setR(int value)
   {
      if (this.r != value)
      {
         int oldValue = this.r;
         this.r = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_R, oldValue, value);
      }
   }
   
   public DrawPointFlow withR(int value)
   {
      setR(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_G = "g";
   
   private int g;

   public int getG()
   {
      return this.g;
   }
   
   public void setG(int value)
   {
      if (this.g != value)
      {
         int oldValue = this.g;
         this.g = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_G, oldValue, value);
      }
   }
   
   public DrawPointFlow withG(int value)
   {
      setG(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_B = "b";
   
   private int b;

   public int getB()
   {
      return this.b;
   }
   
   public void setB(int value)
   {
      if (this.b != value)
      {
         int oldValue = this.b;
         this.b = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_B, oldValue, value);
      }
   }
   
   public DrawPointFlow withB(int value)
   {
      setB(value);
      return this;
   } 


   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getX());
      _.append(" ").append(this.getY());
      _.append(" ").append(this.getR());
      _.append(" ").append(this.getG());
      _.append(" ").append(this.getB());
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

