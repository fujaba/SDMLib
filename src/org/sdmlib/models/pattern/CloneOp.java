/*
   Copyright (c) 2013 zuendorf 
   
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

import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class CloneOp extends PatternElement implements PropertyChangeInterface
{
   private JsonIdMap origMap;

   private JsonIdMap cloneMap;

   private PatternObject firstPO;
   
   private Object origGraph;

   private Object cloneGraph;

   //==========================================================================
   @Override
   public boolean findNextMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         if (!getHasMatch())
         {
            this.setHasMatch(true);
            
            origMap = this.getPattern().getJsonIdMap();
            origMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());
            cloneMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());
            
            firstPO = (PatternObject) this.getPattern().getElements().first();
            
            origGraph = firstPO.getCurrentMatch();
            
            JsonArray jsonArray = origMap.toJsonArray(origGraph);
            
            cloneGraph = cloneMap.decode(jsonArray);
            
            // change matches to point to the new nodes
            for (PatternElement pe : this.getPattern().getElements())
            {
               if (pe instanceof PatternObject)
               {
                  PatternObject po = (PatternObject) pe;
                  if (po.getCurrentMatch() != null)
                  {
                     String id = origMap.getId(po.getCurrentMatch());
                     Object cloneObj = cloneMap.getObject(id);
                     po.setCurrentMatch(cloneObj);
                  }
               }
               else if (pe instanceof UnifyGraphsOp)
               {
                  break;
               }
            }
            
            // go on
            return true;
         }
         else
         {
            this.setHasMatch(false);

            // backtracking, restore previous matches of all patternobjects
            for (PatternElement pe : this.getPattern().getElements())
            {
               if (pe instanceof PatternObject)
               {
                  PatternObject po = (PatternObject) pe;
                  if (po.getCurrentMatch() != null)
                  {
                     String id = cloneMap.getId(po.getCurrentMatch());
                     Object cloneObj = origMap.getObject(id);
                     po.setCurrentMatch(cloneObj);
                  }
               }
               else if (pe instanceof UnifyGraphsOp)
               {
                  break;
               }
            }
            
            return false;
         }
      }
      else
      {
         return false;
      }
   }

   public void resetSearch()
   {
      this.setHasMatch(false);
   } 


   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return getHasMatch();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         setModifier((String) value);
         return true;
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
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
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }

}

