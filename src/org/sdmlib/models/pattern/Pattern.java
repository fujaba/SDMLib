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
   
package org.sdmlib.models.pattern;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

import org.sdmlib.examples.ludo.creators.PawnPO;
import org.sdmlib.models.pattern.creators.PatternElementSet;
import java.util.LinkedHashSet;

import org.sdmlib.scenarios.JsonToImg;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonFilter;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.NegativeApplicationCondition;

public class Pattern extends PatternElement implements PropertyChangeInterface
{
   private JsonIdMap jsonIdMap;
   
   public JsonIdMap getJsonIdMap()
   {
      return jsonIdMap;
   }

   public void setJsonIdMap(JsonIdMap jsonIdMap)
   {
      this.jsonIdMap = jsonIdMap;
   }
   
   //==========================================================================
   
   public Pattern(JsonIdMap createIdMap)
   {
      jsonIdMap = createIdMap;
   }
   
   public Pattern()
   {
      // empty
   }

   public boolean findMatch()
   {
      boolean done = false; 
      
      // start with the last element and go backward until a new choice is made, then go forward to propagate the new choice
      int i = this.getElements().size() - 1;
      PatternElement currentPE = null;
      while (i >= 0 && i < this.getElements().size())
      {
         currentPE = this.getElements().get(i);
         
         boolean hasNextMatch = currentPE.findNextMatch();
         
         if (hasNextMatch)
         {
            i++;
         }
         else
         {
            i--;
         }
      }
      
      hasMatch = i >= this.getElements().size();
      
      return hasMatch;
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         return getElements();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return getHasMatch();
      }

      if (PROPERTY_CURRENTNAC.equalsIgnoreCase(attrName))
      {
         return getCurrentNAC();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         addToElements((PatternElement) value);
         return true;
      }
      
      if ((PROPERTY_ELEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromElements((PatternElement) value);
         return true;
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_CURRENTNAC.equalsIgnoreCase(attrName))
      {
         setCurrentNAC((NegativeApplicationCondition) value);
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
      removeAllFromElements();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Pattern ----------------------------------- PatternElement
    *              pattern                   elements
    * </pre>
    */
   
   public static final String PROPERTY_ELEMENTS = "elements";
   
   private PatternElementSet elements = null;
   
   public PatternElementSet getElements()
   {
      if (this.elements == null)
      {
         return PatternElement.EMPTY_SET;
      }
   
      return this.elements;
   }
   
   public boolean addToElements(PatternElement value)
   {
      boolean changed = false;
      
      if (currentNAC != null)
      {
         // add element to nac
         changed = currentNAC.addToElements(value);
      }
      else
      {
         if (value != null)
         {
            if (this.elements == null)
            {
               this.elements = new PatternElementSet();
            }

            if ( ! this.elements.contains(value))
            {
               changed = this.elements.add (value);

               value.withPattern(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, null, value);
            }

            if (value instanceof NegativeApplicationCondition)
            {
               this.setCurrentNAC((NegativeApplicationCondition) value);
               ((NegativeApplicationCondition) value).setJsonIdMap(jsonIdMap);
            }
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromElements(PatternElement value)
   {
      boolean changed = false;
      
      if ((this.elements != null) && (value != null))
      {
         changed = this.elements.remove (value);
         
         if (changed)
         {
            value.setPattern(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Pattern withElements(PatternElement value)
   {
      addToElements(value);
      return this;
   } 
   
   public Pattern withoutElements(PatternElement value)
   {
      removeFromElements(value);
      return this;
   } 
   
   public void removeAllFromElements()
   {
      LinkedHashSet<PatternElement> tmpSet = new LinkedHashSet<PatternElement>(this.getElements());
   
      for (PatternElement value : tmpSet)
      {
         this.removeFromElements(value);
      }
   }

   public String getPOClassName(String modelClassName)
   {
      int pos = modelClassName.lastIndexOf('.');
      return modelClassName.substring(0, pos+1) + "creators." + modelClassName.substring(pos+1, modelClassName.length()) + "PO";
   }

   public PatternObject bind(Object hostGraphObject)
   {
      SendableEntityCreator creatorClass = jsonIdMap.getCreatorClasses(getPOClassName(hostGraphObject.getClass().getName()));
      
      PatternObject po = (PatternObject) creatorClass.getSendableInstance(false);
      
      this.addToElements(po);
      
      po.setCurrentMatch(hostGraphObject);
      
      return po;
   }

   public String dumpDiagram(String string)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(this.getElements().get(0), 
         new JsonFilter()
      {
         

         @Override
         public boolean isRegard(IdMap map, Object entity,
               String property, Object value, boolean isMany)
         {
            if (value.getClass().equals(Pattern.class) || entity.getClass().equals(Pattern.class))
            {
               return false;
            }
            else
            {
               return super.isRegard(map, entity, property, value, isMany);
            }
         }
         
      });
      
      System.out.println(jsonArray.toString(3));
      
      String imgLink = JsonToImg.get().toImg(string, jsonArray);
      
      return imgLink;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_HASMATCH = "hasMatch";
   
   private boolean hasMatch;

   public boolean getHasMatch()
   {
      return this.hasMatch;
   }
   
   public void setHasMatch(boolean value)
   {
      if (this.hasMatch != value)
      {
         boolean oldValue = this.hasMatch;
         this.hasMatch = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HASMATCH, oldValue, value);
      }
   }
   
   public Pattern withHasMatch(boolean value)
   {
      setHasMatch(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTNAC = "currentNAC";
   
   private NegativeApplicationCondition currentNAC;

   public NegativeApplicationCondition getCurrentNAC()
   {
      return this.currentNAC;
   }
   
   public void setCurrentNAC(NegativeApplicationCondition value)
   {
      if (this.currentNAC != value)
      {
         NegativeApplicationCondition oldValue = this.currentNAC;
         this.currentNAC = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTNAC, oldValue, value);
      }
   }
   
   public Pattern withCurrentNAC(NegativeApplicationCondition value)
   {
      setCurrentNAC(value);
      return this;
   } 
}

