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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.models.pattern.util.PatternLinkSet;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import java.lang.Object;

public class PatternLink extends PatternElement implements PropertyChangeInterface
{

   @Override
   public boolean findNextMatch()
   {
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (hostGraphSrcObject != null)
         {
            Object value = null;
            
            if (tgtRoleName.equals("instanceof"))
            {
               // if the type is correct
               Class<?> hostGraphScrObjectClass = hostGraphSrcObject.getClass();
               String fullTgtClassName = this.getTgt().getClass().getName();
               fullTgtClassName = CGUtil.baseClassName(fullTgtClassName, "PO");
               try
               {
                  Class<?> tgtPOClass = hostGraphScrObjectClass.getClassLoader().loadClass(fullTgtClassName);
                  
                  if (tgtPOClass.isAssignableFrom(hostGraphScrObjectClass))
                  {
                     value = hostGraphSrcObject;
                  }
               }
               catch (ClassNotFoundException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
            else if (hostGraphSrcObject != null && hostGraphSrcObject instanceof Collection)
            {
               // loop through src elements and collect target elements
               LinkedHashSet<Object> tgtSet = new LinkedHashSet<Object>();
               
               SendableEntityCreator creatorClass = null; 
               
               for (Object src : (Collection<?>) hostGraphSrcObject)
               {
                  if (creatorClass == null)
                  {
                     creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(src);
                  }

                  Object tgt = creatorClass.getValue(src, tgtRoleName);
                  
                  if (tgt instanceof Collection)
                  {
                     tgtSet.addAll((Collection<?>) tgt); 
                  }
                  else
                  {
                     tgtSet.add(tgt);
                  }
               }
               
               value = tgtSet;
            }
            else
            {
               SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(hostGraphSrcObject);
               value = creatorClass.getValue(hostGraphSrcObject, tgtRoleName);
               if (value != null && value instanceof Collection && ! this.getTopPattern().getRiskConcurrentModification())
               {
                  value = new ArrayList<Object>((Collection<?>)value);
               }
            }
            
            if (value != null && value instanceof Collection)
            {
               this.getTgt().setCandidates(value);
               
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  // add set of candidates to trace
                  String setVarName = this.getTgt().getPatternObjectName() + "Candidates";
                  LinkedHashSet<String> variablesAlreadyInTrace = getTopPattern().getVariablesAlreadyInTrace();
                  if ( ! variablesAlreadyInTrace.contains(setVarName))
                  {
                     variablesAlreadyInTrace.add(setVarName);
                     setVarName = value.getClass().getSimpleName() + " " + setVarName;
                  }
                  
                  getTopPattern().addLogMsg(setVarName + " = " + this.getSrc().getPatternObjectName()
                     + ".get" + StrUtil.upFirstChar(tgtRoleName) +"(); // " + valueSetString(value));
                  if (((Collection<?>) value).isEmpty())
                  {
                     getTopPattern().addLogMsg("// No candidates, backtrack!");
                  }
               }
            }
            else
            {
               this.getTgt().setCandidates(value);

               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  String setVarName = this.getTgt().getPatternObjectName();
                  LinkedHashSet<String> variablesAlreadyInTrace = getTopPattern().getVariablesAlreadyInTrace();
                  if ( ! variablesAlreadyInTrace.contains(setVarName))
                  {
                     variablesAlreadyInTrace.add(setVarName);
                     setVarName = value.getClass().getSimpleName() + " " + setVarName;
                  }
                  
                  getTopPattern().addLogMsg(setVarName + " = " + this.getSrc().getPatternObjectName()
                     + ".get" + StrUtil.upFirstChar(tgtRoleName) +"(); // " + getTopPattern().getJsonIdMap().getId(value) + " " + value);
               }
               

            }
            
            return true;
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
      this.setHostGraphSrcObject(null);
   }
  
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setTgt(null);
      setSrc(null);
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SRCROLENAME = "srcRoleName";
   
   private String srcRoleName;

   public String getSrcRoleName()
   {
      return this.srcRoleName;
   }
   
   public void setSrcRoleName(String value)
   {
      if ( ! StrUtil.stringEquals(this.srcRoleName, value))
      {
         String oldValue = this.srcRoleName;
         this.srcRoleName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRCROLENAME, oldValue, value);
      }
   }
   
   public PatternLink withSrcRoleName(String value)
   {
      setSrcRoleName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TGTROLENAME = "tgtRoleName";
   
   private String tgtRoleName;

   public String getTgtRoleName()
   {
      return this.tgtRoleName;
   }
   
   public void setTgtRoleName(String value)
   {
      if ( ! StrUtil.stringEquals(this.tgtRoleName, value))
      {
         String oldValue = this.tgtRoleName;
         this.tgtRoleName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTROLENAME, oldValue, value);
      }
   }
   
   public PatternLink withTgtRoleName(String value)
   {
      setTgtRoleName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternLink ----------------------------------- PatternObject
    *              incomming                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private PatternObject tgt = null;
   
   public PatternObject getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(PatternObject value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         PatternObject oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutIncomming(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withIncomming(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternLink withTgt(PatternObject value)
   {
      setTgt(value);
      return this;
   } 

   
   public static final PatternLinkSet EMPTY_SET = new PatternLinkSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternLink ----------------------------------- PatternObject
    *              outgoing                   src
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
            oldValue.withoutOutgoing(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withOutgoing(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternLink withSrc(PatternObject value)
   {
      setSrc(value);
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
   
   public PatternLink withHostGraphSrcObject(Object value)
   {
      setHostGraphSrcObject(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getTgtRoleName());
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }
   
}

