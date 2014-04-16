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

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.creators.AttributeConstraintSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.util.PropertyChangeInterface;

import java.beans.PropertyChangeListener;

public class AttributeConstraint extends PatternElement implements PropertyChangeInterface
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
            creatorClass.setValue(srcObj, this.getAttrName(), this.getTgtValue(), "");
            this.setHasMatch(true);
            return true;
         }
      }
      
      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (hostGraphSrcObject != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(hostGraphSrcObject);
            Object value = creatorClass.getValue(hostGraphSrcObject, attrName);
            
            boolean itWorks = (value == null && tgtValue == null || value != null && value.equals(tgtValue));
            
            if (cmpOp != null && cmpOp.equals("!="))
            {
               itWorks = (value == null && tgtValue != null || value != null && ! value.equals(tgtValue));
            }
            
            if (upperTgtValue != null)
            {
               Comparable comparable = (Comparable) value;
               itWorks = comparable.compareTo(tgtValue) >= 0 && comparable.compareTo(upperTgtValue) <= 0;
            }
            
            if (itWorks)
            {
               if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
               {  
                  String msg = "// attribute a1 of node x has required value y";
                  msg = msg.replaceFirst("y", "" + value);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getJsonIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
                  msg = msg.replaceFirst("a1", "" + attrName);
                  getTopPattern().addLogMsg(msg);
               }
               
               return true;
            }
            else
            {
               if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
               {  
                  String msg = "// attribute a1 of node x has value actual and not required value y, backtrack!";
                  msg = msg.replaceFirst("y", "" + tgtValue);
                  msg = msg.replaceFirst("actual", "" + value);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getJsonIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
                  msg = msg.replaceFirst("a1", "" + attrName);
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
      this.setHostGraphSrcObject(null);
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         return getAttrName();
      }

      if (PROPERTY_TGTVALUE.equalsIgnoreCase(attrName))
      {
         return getTgtValue();
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return getHostGraphSrcObject();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
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

      if (PROPERTY_CMPOP.equalsIgnoreCase(attrName))
      {
         return getCmpOp();
      }

      if (PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attrName))
      {
         return getUpperTgtValue();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         setAttrName((String) value);
         return true;
      }

      if (PROPERTY_TGTVALUE.equalsIgnoreCase(attrName))
      {
         setTgtValue(value);
         return true;
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         setHostGraphSrcObject((Object) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((PatternObject) value);
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

      if (PROPERTY_CMPOP.equalsIgnoreCase(attrName))
      {
         setCmpOp((String) value);
         return true;
      }

      if (PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attrName))
      {
         setUpperTgtValue((Object) value);
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
      setSrc(null);
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ATTRNAME = "attrName";
   
   private String attrName;

   public String getAttrName()
   {
      return this.attrName;
   }
   
   public void setAttrName(String value)
   {
      if ( ! StrUtil.stringEquals(this.attrName, value))
      {
         String oldValue = this.attrName;
         this.attrName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRNAME, oldValue, value);
      }
   }
   
   public AttributeConstraint withAttrName(String value)
   {
      setAttrName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TGTVALUE = "tgtValue";
   
   private Object tgtValue;

   public Object getTgtValue()
   {
      return this.tgtValue;
   }
   
   public void setTgtValue(Object value)
   {
      if ( (tgtValue == null && value != null)
            || (tgtValue != null && ! tgtValue.equals(value)))
      {
         Object oldValue = this.tgtValue;
         this.tgtValue = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTVALUE, oldValue, value);
      }
   }
   
   public AttributeConstraint withTgtValue(Object value)
   {
      setTgtValue(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTGRAPHSRCOBJECT = "hostGraphSrcObject";
   
   private Object hostGraphSrcObject;

   public Object getHostGraphSrcObject()
   {
      return this.hostGraphSrcObject;
   }
   
   public void setHostGraphSrcObject(Object value)
   {
      if (this.hostGraphSrcObject != value)
      {
         Object oldValue = this.hostGraphSrcObject;
         this.hostGraphSrcObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTGRAPHSRCOBJECT, oldValue, value);
      }
   }
   
   public AttributeConstraint withHostGraphSrcObject(Object value)
   {
      setHostGraphSrcObject(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * AttributeConstraint ----------------------------------- PatternObject
    *              attrConstraints                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private PatternObject src = null;
   
   public PatternObject getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(PatternObject value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         PatternObject oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutAttrConstraints(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withAttrConstraints(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public AttributeConstraint withSrc(PatternObject value)
   {
      setSrc(value);
      return this;
   } 

   
   public static final AttributeConstraintSet EMPTY_SET = new AttributeConstraintSet();

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getPatternObjectName());
      _.append(" ").append(this.getAttrName());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getCmpOp());
      _.append(" ").append(this.getTgtValue());
      
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_CMPOP = "cmpOp";
   
   private String cmpOp;

   public String getCmpOp()
   {
      return this.cmpOp;
   }
   
   public void setCmpOp(String value)
   {
      if ( ! StrUtil.stringEquals(this.cmpOp, value))
      {
         String oldValue = this.cmpOp;
         this.cmpOp = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CMPOP, oldValue, value);
      }
   }
   
   public AttributeConstraint withCmpOp(String value)
   {
      setCmpOp(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_UPPERTGTVALUE = "upperTgtValue";
   
   private Object upperTgtValue;

   public Object getUpperTgtValue()
   {
      return this.upperTgtValue;
   }
   
   public void setUpperTgtValue(Object value)
   {
      if (this.upperTgtValue != value)
      {
         Object oldValue = this.upperTgtValue;
         this.upperTgtValue = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UPPERTGTVALUE, oldValue, value);
      }
   }
   
   public AttributeConstraint withUpperTgtValue(Object value)
   {
      setUpperTgtValue(value);
      return this;
   } 

   public PatternObject createSrc()
   {
      PatternObject value = new PatternObject();
      withSrc(value);
      return value;
   } 
}

