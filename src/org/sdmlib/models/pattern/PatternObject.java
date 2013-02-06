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
import java.util.LinkedHashSet;

import org.sdmlib.models.pattern.creators.AttributeConstraintSet;
import org.sdmlib.models.pattern.creators.PatternLinkSet;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class PatternObject<POC, MC> extends PatternElement<POC> implements PropertyChangeInterface
{
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
            // backward execution, backtrack and prepare for forward execution
            this.setHasMatch(false);
            return false;
         }
         else
         {
            // forward execution: create hostgraph object and bind it
            String className = this.getClass().getName();
            className = className.replace(".creators.", ".");
            className = className.substring(0, className.length()-2);
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClasses(className);
            Object sendableInstance = creatorClass.getSendableInstance(false);
            this.setCurrentMatch(sendableInstance);
            this.setHasMatch(true);
            return true;
         }
      }
      else if (Pattern.BOUND.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            // backward execution, backtrack and prepare for forward execution
            this.setHasMatch(false);
            return false;
         }
         else
         {
            this.setHasMatch(getCurrentMatch() != null);
            
            return this.getHasMatch();
         }
      }
      
      if (this.getCandidates() == null 
            || this.getCandidates() instanceof Collection && ((Collection) this.getCandidates()).isEmpty()) 
      {
         return false;
      }
      
      boolean resultStat = false;
      if (this.getCandidates() instanceof Collection)
      {
         for (Object obj : (Collection) this.getCandidates())
         {
            this.setCurrentMatch(obj);
            
            ((Collection) this.getCandidates()).remove(obj);
            
            resultStat = true;
            
            break;
         }
         return false;
      }
      else
      {
         this.setCurrentMatch(this.getCandidates());
         this.setCandidates(null);
         
         resultStat = true;
      }
      
      if (Pattern.DESTROY.equals(getModifier()) && this.getCurrentMatch() != null)
      {
      	Object currentMatch = this.getCurrentMatch();
         
         EntityFactory creatorClass = (EntityFactory) this.getPattern().getJsonIdMap().getCreatorClass(currentMatch);
         
         creatorClass.removeObject(currentMatch);
      }
      
      return resultStat;
   }
   
   
   
   
   @Override
   public void resetSearch()
   {
      this.setCandidates(null);
      this.setCurrentMatch(null);
   }

   public POC startCreate()
   {
      this.getPattern().startCreate();
      
      return (POC) this;
   }


   public POC startDestroy()
   {
      this.getPattern().startDestroy();
      
      return (POC) this;
   }


   public POC startNAC()
   {
      NegativeApplicationCondition nac = new NegativeApplicationCondition();
      
      this.getPattern().addToElements(nac);

      return (POC) this;
   }


   public POC endNAC()
   {
      Pattern directPattern = this.getPattern();
      if (directPattern instanceof NegativeApplicationCondition)
      {
         directPattern = directPattern.getPattern();
      }
      
      directPattern.setCurrentSubPattern(null);
      
      directPattern.findMatch();
      
      return (POC) this;
   }
   
   public POC startSubPattern()
   {
      OptionalSubPattern optionalSubPattern = new OptionalSubPattern();
      
      this.getPattern().addToElements(optionalSubPattern);
      
      return (POC) this;
   }
   
   public POC endSubPattern()
   {
      Pattern directPattern = this.getPattern();
      if (directPattern instanceof OptionalSubPattern)
      {
         directPattern = directPattern.getPattern();
      }
      
      directPattern.setCurrentSubPattern(null);
      
      return (POC) this;
   }
   
   public POC destroy()
   {
      DestroyObjectElem destroyObjectElem = (DestroyObjectElem) new DestroyObjectElem()
      .withPatternObject(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return (POC) this;
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

      if (PROPERTY_CURRENTMATCH.equalsIgnoreCase(attrName))
      {
         return getCurrentMatch();
      }

      if (PROPERTY_INCOMMING.equalsIgnoreCase(attrName))
      {
         return getIncomming();
      }

      if (PROPERTY_OUTGOING.equalsIgnoreCase(attrName))
      {
         return getOutgoing();
      }

      if (PROPERTY_CANDIDATES.equalsIgnoreCase(attrName))
      {
         return getCandidates();
      }

      if (PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         return getAttrConstraints();
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return getHasMatch();
      }

      if (PROPERTY_DESTROYELEM.equalsIgnoreCase(attrName))
      {
         return getDestroyElem();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      return super.get(attrName);
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_CURRENTMATCH.equalsIgnoreCase(attrName))
      {
         setCurrentMatch((Object) value);
         return true;
      }

      if (PROPERTY_INCOMMING.equalsIgnoreCase(attrName))
      {
         addToIncomming((PatternLink) value);
         return true;
      }

      if ((PROPERTY_INCOMMING + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromIncomming((PatternLink) value);
         return true;
      }

      if (PROPERTY_OUTGOING.equalsIgnoreCase(attrName))
      {
         addToOutgoing((PatternLink) value);
         return true;
      }

      if ((PROPERTY_OUTGOING + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOutgoing((PatternLink) value);
         return true;
      }

      if (PROPERTY_CANDIDATES.equalsIgnoreCase(attrName))
      {
         setCandidates((LinkedHashSet<Object>) value);
         return true;
      }

      if (PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         addToAttrConstraints((AttributeConstraint) value);
         return true;
      }
      
      if ((PROPERTY_ATTRCONSTRAINTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromAttrConstraints((AttributeConstraint) value);
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

      if (PROPERTY_DESTROYELEM.equalsIgnoreCase(attrName))
      {
         setDestroyElem((DestroyObjectElem) value);
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

      return super.set(attrName, value);
   }


   //==========================================================================

   public void removeYou()
   {
      removeAllFromIncomming();
      removeAllFromOutgoing();
      removeAllFromAttrConstraints();
      setDestroyElem(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }


   //==========================================================================

   public static final String PROPERTY_CURRENTMATCH = "currentMatch";

   private Object currentMatch;

   public MC getCurrentMatch()
   {
      return (MC) this.currentMatch;
   }

   public void setCurrentMatch(Object value)
   {
      if (this.currentMatch != value)
      {
         Object oldValue = this.currentMatch;
         this.currentMatch = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTMATCH, oldValue, value);
      }
   }

   public PatternObject withCurrentMatch(Object value)
   {
      setCurrentMatch(value);
      return this;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- PatternLink
    *              tgt                   incomming
    * </pre>
    */

   public static final String PROPERTY_INCOMMING = "incomming";

   private PatternLinkSet incomming = null;

   public PatternLinkSet getIncomming()
   {
      if (this.incomming == null)
      {
         return PatternLink.EMPTY_SET;
      }

      return this.incomming;
   }

   public boolean addToIncomming(PatternLink value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.incomming == null)
         {
            this.incomming = new PatternLinkSet();
         }

         changed = this.incomming.add (value);

         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMMING, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromIncomming(PatternLink value)
   {
      boolean changed = false;

      if ((this.incomming != null) && (value != null))
      {
         changed = this.incomming.remove (value);

         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMMING, value, null);
         }
      }

      return changed;   
   }

   public PatternObject withIncomming(PatternLink value)
   {
      addToIncomming(value);
      return this;
   } 

   public PatternObject withoutIncomming(PatternLink value)
   {
      removeFromIncomming(value);
      return this;
   } 

   public void removeAllFromIncomming()
   {
      LinkedHashSet<PatternLink> tmpSet = new LinkedHashSet<PatternLink>(this.getIncomming());

      for (PatternLink value : tmpSet)
      {
         this.removeFromIncomming(value);
      }
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- PatternLink
    *              src                   outgoing
    * </pre>
    */

   public static final String PROPERTY_OUTGOING = "outgoing";

   private PatternLinkSet outgoing = null;

   public PatternLinkSet getOutgoing()
   {
      if (this.outgoing == null)
      {
         return PatternLink.EMPTY_SET;
      }

      return this.outgoing;
   }

   public boolean addToOutgoing(PatternLink value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.outgoing == null)
         {
            this.outgoing = new PatternLinkSet();
         }

         changed = this.outgoing.add (value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOING, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromOutgoing(PatternLink value)
   {
      boolean changed = false;

      if ((this.outgoing != null) && (value != null))
      {
         changed = this.outgoing.remove (value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOING, value, null);
         }
      }

      return changed;   
   }

   public PatternObject withOutgoing(PatternLink value)
   {
      addToOutgoing(value);
      return this;
   } 

   public PatternObject withoutOutgoing(PatternLink value)
   {
      removeFromOutgoing(value);
      return this;
   } 

   public void removeAllFromOutgoing()
   {
      LinkedHashSet<PatternLink> tmpSet = new LinkedHashSet<PatternLink>(this.getOutgoing());

      for (PatternLink value : tmpSet)
      {
         this.removeFromOutgoing(value);
      }
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CANDIDATES = "candidates";
   
   private Object candidates;

   public Object getCandidates()
   {
      return this.candidates;
   }
   
   public void setCandidates(Object value)
   {
      if (this.candidates != value)
      {
         Object oldValue = this.candidates;
         this.candidates = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CANDIDATES, oldValue, value);
      }
   }
   
   public PatternObject withCandidates(Object value)
   {
      setCandidates(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- AttributeConstraint
    *              src                   attrConstraints
    * </pre>
    */
   
   public static final String PROPERTY_ATTRCONSTRAINTS = "attrConstraints";
   
   private AttributeConstraintSet attrConstraints = null;
   
   public AttributeConstraintSet getAttrConstraints()
   {
      if (this.attrConstraints == null)
      {
         return AttributeConstraint.EMPTY_SET;
      }
   
      return this.attrConstraints;
   }
   
   public boolean addToAttrConstraints(AttributeConstraint value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.attrConstraints == null)
         {
            this.attrConstraints = new AttributeConstraintSet();
         }
         
         changed = this.attrConstraints.add (value);
         
         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRCONSTRAINTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromAttrConstraints(AttributeConstraint value)
   {
      boolean changed = false;
      
      if ((this.attrConstraints != null) && (value != null))
      {
         changed = this.attrConstraints.remove (value);
         
         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRCONSTRAINTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public PatternObject withAttrConstraints(AttributeConstraint value)
   {
      addToAttrConstraints(value);
      return this;
   } 
   
   public PatternObject withoutAttrConstraints(AttributeConstraint value)
   {
      removeFromAttrConstraints(value);
      return this;
   } 
   
   public void removeAllFromAttrConstraints()
   {
      LinkedHashSet<AttributeConstraint> tmpSet = new LinkedHashSet<AttributeConstraint>(this.getAttrConstraints());
   
      for (AttributeConstraint value : tmpSet)
      {
         this.removeFromAttrConstraints(value);
      }
   }

   public void hasLink(String roleName, PatternObject result)
   {
      if (Pattern.CREATE.equals(this.getPattern().getModifier()))
      {
         this.getPattern().addToElements(result);
         
         this.getPattern().findMatch();
         
         LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(result).withTgtRoleName(roleName)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier());
         
         this.getPattern().addToElements(patternLink);
         
         this.getPattern().findMatch();
      }
      else
      {
         PatternLink patternLink = new PatternLink()
         .withTgt(result).withTgtRoleName(roleName)
         .withSrc(this);
         patternLink.setModifier(this.getPattern().getModifier());
   
         this.getPattern().addToElements(patternLink);
   
         this.getPattern().addToElements(result);
   
         this.getPattern().findMatch();
      }
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * PatternObject ----------------------------------- DestroyObjectElem
    *              patternObject                   destroyElem
    * </pre>
    */
   
   public static final String PROPERTY_DESTROYELEM = "destroyElem";
   
   private DestroyObjectElem destroyElem = null;
   
   public DestroyObjectElem getDestroyElem()
   {
      return this.destroyElem;
   }
   
   public boolean setDestroyElem(DestroyObjectElem value)
   {
      boolean changed = false;
      
      if (this.destroyElem != value)
      {
         DestroyObjectElem oldValue = this.destroyElem;
         
         if (this.destroyElem != null)
         {
            this.destroyElem = null;
            oldValue.setPatternObject(null);
         }
         
         this.destroyElem = value;
         
         if (value != null)
         {
            value.withPatternObject(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DESTROYELEM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternObject withDestroyElem(DestroyObjectElem value)
   {
      setDestroyElem(value);
      return this;
   } 
   
   public DestroyObjectElem createDestroyElem()
   {
      DestroyObjectElem value = new DestroyObjectElem();
      withDestroyElem(value);
      return value;
   } 
}

