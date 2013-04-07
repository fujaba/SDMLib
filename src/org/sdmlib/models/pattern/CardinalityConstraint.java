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

import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

import org.sdmlib.utils.StrUtil;
import org.sdmlib.models.pattern.creators.CardinalityConstraintSet;

public class CardinalityConstraint extends PatternElement implements PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         // does not make sense
         throw new RuntimeException("cannot create a cardinality constraint");
      }

      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         // check that the size of the targeted assoc is in between min and max card
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());

         if (hostGraphSrcObject != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(hostGraphSrcObject);
            Object value = creatorClass.getValue(hostGraphSrcObject, tgtRoleName);

            long valueSize = -1;

            if (value == null)
            {
               valueSize = 0;
            }
            else if (value instanceof Collection)
            {
               valueSize = ((Collection) value).size();
            }
            else
            {
               valueSize = 1;
            }

            if (minCard <= valueSize && valueSize <= maxCard)
            {
               if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
               {  
                  String msg = "// node x has size tgtRole neighbors, which is between minCard and maxCard";
                  msg = msg.replaceFirst("maxCard", "" + maxCard);
                  msg = msg.replaceFirst("minCard", "" + minCard);
                  msg = msg.replaceFirst("tgtRole", "" + tgtRoleName);
                  msg = msg.replaceFirst("size", "" + valueSize);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getJsonIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
                  getTopPattern().addLogMsg(msg);
               }

               return true;
            }
            else 
            {
               if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
               {  
                  String msg = "// node x has size tgtRole neighbors, which is NOT between minCard and maxCard";
                  msg = msg.replaceFirst("maxCard", "" + maxCard);
                  msg = msg.replaceFirst("minCard", "" + minCard);
                  msg = msg.replaceFirst("tgtRole", "" + tgtRoleName);
                  msg = msg.replaceFirst("size", "" + valueSize);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getJsonIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
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




   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         return getTgtRoleName();
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return getHostGraphSrcObject();
      }

      if (PROPERTY_MINCARD.equalsIgnoreCase(attrName))
      {
         return getMinCard();
      }

      if (PROPERTY_MAXCARD.equalsIgnoreCase(attrName))
      {
         return getMaxCard();
      }

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

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
      }

      return null;
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

      if (PROPERTY_MINCARD.equalsIgnoreCase(attrName))
      {
         setMinCard(Long.parseLong(value.toString()));
         return true;
      }

      if (PROPERTY_MAXCARD.equalsIgnoreCase(attrName))
      {
         setMaxCard(Long.parseLong(value.toString()));
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

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((PatternObject) value);
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
      setSrc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
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

   public CardinalityConstraint withTgtRoleName(String value)
   {
      setTgtRoleName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getTgtRoleName());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
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

   public CardinalityConstraint withHostGraphSrcObject(Object value)
   {
      setHostGraphSrcObject(value);
      return this;
   } 


   //==========================================================================

   public static final String PROPERTY_MINCARD = "minCard";

   private long minCard;

   public long getMinCard()
   {
      return this.minCard;
   }

   public void setMinCard(long value)
   {
      if (this.minCard != value)
      {
         long oldValue = this.minCard;
         this.minCard = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MINCARD, oldValue, value);
      }
   }

   public CardinalityConstraint withMinCard(long value)
   {
      setMinCard(value);
      return this;
   } 


   //==========================================================================

   public static final String PROPERTY_MAXCARD = "maxCard";

   private long maxCard;

   public long getMaxCard()
   {
      return this.maxCard;
   }

   public void setMaxCard(long value)
   {
      if (this.maxCard != value)
      {
         long oldValue = this.maxCard;
         this.maxCard = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MAXCARD, oldValue, value);
      }
   }

   public CardinalityConstraint withMaxCard(long value)
   {
      setMaxCard(value);
      return this;
   } 


   /********************************************************************
    * <pre>
    *              many                       one
    * CardinalityConstraint ----------------------------------- PatternObject
    *              cardConstraints                   src
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
            oldValue.withoutCardConstraints(this);
         }

         this.src = value;

         if (value != null)
         {
            value.withCardConstraints(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public CardinalityConstraint withSrc(PatternObject value)
   {
      setSrc(value);
      return this;
   } 

   public PatternObject createSrc()
   {
      PatternObject value = new PatternObject();
      withSrc(value);
      return value;
   } 


   public static final CardinalityConstraintSet EMPTY_SET = new CardinalityConstraintSet();
}
