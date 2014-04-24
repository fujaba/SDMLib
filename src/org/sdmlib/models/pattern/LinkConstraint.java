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

import java.beans.PropertyChangeSupport;
import java.util.Collection;

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.R;
import org.sdmlib.models.classes.SDMLibConfig;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.util.PropertyChangeInterface;

public class LinkConstraint extends PatternLink implements PropertyChangeInterface
{

   
   //==========================================================================
   
   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            this.setHasMatch(false);
            return false;
         }
         else
         {
            Object srcObj = this.getSrc().getCurrentMatch();
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(srcObj);
            creatorClass.setValue(srcObj, this.getTgtRoleName(), this.getTgt().getCurrentMatch(), "");
            this.setHasMatch(true);
            
            if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
            {
               getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                  + ".set" + StrUtil.upFirstChar(getTgtRoleName()) + "(" + this.getTgt().getPatternObjectName() + ")");
            }

            return true;
         }
      }
      else if (Pattern.DESTROY.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            this.setHasMatch(false);
            return false;
         }
         else
         {
            Object srcObj = this.getSrc().getCurrentMatch();
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(srcObj);
            Object value = creatorClass.getValue(srcObj, this.getTgtRoleName());
            if (value == null)
            {
            	// do nothing
            }
            else if (value instanceof Collection)
            {
               if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
               {
                  getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                     + ".removeFrom" + StrUtil.upFirstChar(getTgtRoleName()) + "(" + this.getTgt().getPatternObjectName() + ")");
               }
               
            	creatorClass.setValue(srcObj, this.getTgtRoleName()  + JsonIdMap.REMOVE, this.getTgt().getCurrentMatch(), "");
            }
            else
            {
               if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
               {
                  getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                     + ".set" + StrUtil.upFirstChar(getTgtRoleName()) + "(null); // remove" + this.getTgt().dumpHostGraphObject(this.getTgt().getCurrentMatch()));
               }
               
            	creatorClass.setValue(srcObj, this.getTgtRoleName(), null, "");
            }
            this.setHasMatch(true);
            return true;
         }
      }
      	
      
      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (getHostGraphSrcObject() != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(getHostGraphSrcObject());
            Object value = creatorClass.getValue(getHostGraphSrcObject(), getTgtRoleName());
            Object hostGraphTgtObject = this.getTgt().getCurrentMatch();
            
            if (hostGraphTgtObject != null && 
                  (hostGraphTgtObject == value || (value instanceof Collection && ((Collection) value).contains(hostGraphTgtObject))))
            {
               if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
               {
                  String msg = "// cnet link from x to y exists";
                  msg = msg.replaceFirst("y", getTopPattern().getJsonIdMap().getId(value) + " " + value.toString());
                  msg = msg.replaceFirst("x", getTopPattern().getJsonIdMap().getId(getHostGraphSrcObject()) + " " + getHostGraphSrcObject().toString());
                  msg = msg.replaceFirst("cnet", getTgtRoleName());
                  getTopPattern().addLogMsg(msg);
               }

               return true;
            }
            else
            {
               if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
               {
                  String msg = "// cnet link from x to ? does not exists, backtrack";
                  if (value != null)
                  {
                     msg = msg.replaceFirst("\\?", getTopPattern().getJsonIdMap().getId(value) + " " + value.toString());
                  }
                  msg = msg.replaceFirst("x", getTopPattern().getJsonIdMap().getId(getHostGraphSrcObject()) + " " + getHostGraphSrcObject().toString());
                  msg = msg.replaceFirst("cnet", getTgtRoleName());
                  getTopPattern().addLogMsg(msg);
               }
               
               this.setHostGraphSrcObject(null);
               
               return false;
            }
         }
         else 
         {
            return false;
         }
      }
      else
      {
         this.setHostGraphSrcObject(null);
      
         return false;
      }
   }
   
   @Override
   public void resetSearch()
   {
      this.setHasMatch(false);
      super.resetSearch();
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         return getTgtRoleName();
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return getHostGraphSrcObject();
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return getHasMatch();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return getTgt();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
      }
      
      return super.get(attrName);
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         setTgtRoleName((String) value);
         return true;
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         setHostGraphSrcObject((Object) value);
         return true;
      }

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

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         setTgt((PatternObject) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((PatternObject) value);
         return true;
      }

      return super.set(attrName, value);
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
      setTgt(null);
      setSrc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTgtRoleName());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }

}

